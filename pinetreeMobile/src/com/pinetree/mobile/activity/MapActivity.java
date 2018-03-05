package com.pinetree.mobile.activity;

import java.lang.reflect.Field;
import java.util.List;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapLongClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.overlay.BusRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.CustomerNearbyMarker;
import com.pinetree.mobile.bean.CustomerNearbyMarkerItem;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 地图
 * 
 * @author Administrator
 * 
 */
public class MapActivity extends Activity implements LocationSource,
		AMapLocationListener, OnClickListener, OnRouteSearchListener,
		OnInfoWindowClickListener, InfoWindowAdapter, OnMapLongClickListener,
		OnMarkerClickListener {

	private MapView mapView;
	private Customer customer;
	private Customer customer1;
	private AMap aMap;
	private Marker customerMarker;// 客户标记
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private TextView busRouteTextView;
	private AMapLocation myLocation;
	private GeocodeAddress geocodeAddress;
	private RouteSearch routeSearch;
	private BusRouteResult busRouteResult;// 公交模式查询结果
	private ImageView customerNearbyMarkImageView;
	private AlertDialog alertDialog;
	private Dialog dialog;
	private ImageView correctCustomerLocationImageView;
	private double lng = 0;
	private double lat = 0; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_map);

		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(MapActivity.this);
		customer1 = pinetreeDB1.getCustomer(customer.getId());
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);

		init();
		if (!"".equals(customer1.getLat()) && !"".equals(customer1.getLng())) {
			double lng = Double.valueOf(customer1.getLng());
			double lat =Double.valueOf(customer1.getLat());
			addMarkersToMap(new LatLng(lat, lng), customer1.getCustAddress());
		}
		dialog = new Dialog(MapActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
	}

	private void init() {
		busRouteTextView = (TextView) findViewById(R.id.bus_route_textView);// 公交
		busRouteTextView.setOnClickListener(this);
		customerNearbyMarkImageView = (ImageView) findViewById(R.id.customer_nearby_mark_imageView);
		customerNearbyMarkImageView.setOnClickListener(this);// 客户附近的坐标
		correctCustomerLocationImageView = (ImageView) findViewById(R.id.correct_customer_location_imageView);
		correctCustomerLocationImageView.setOnClickListener(this);// 矫正客户经纬度
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	/**
	 * 设置amap的属性
	 */
	private void setUpMap() {
		routeSearch = new RouteSearch(this);
		routeSearch.setRouteSearchListener(this);
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// 设置定位监听
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		aMap.setInfoWindowAdapter(this);
		aMap.setOnMapLongClickListener(this);
		aMap.setOnMarkerClickListener(this);
	}

	/**
	 * 在地图上添加marker
	 */
	private void addMarkersToMap(LatLng point, String address) {
		customerMarker = aMap.addMarker(new MarkerOptions()
				.position(point)
				.title(address)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
				.draggable(true));
		customerMarker.showInfoWindow();
	}

	/**
	 * 长按地图添加marker
	 */
	private void addMarkersToMapLongClick(LatLng latLng) {
		aMap.addMarker(new MarkerOptions()
				.position(latLng)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED))
				.visible(true));
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		stopLocation();// 停止定位
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * title back
	 */
	@OnClick(R.id.iv_map_back)
	public void backMain(View view) {
		finish();
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			mAMapLocationManager.requestLocationUpdates(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		stopLocation();
	}

	/**
	 * 停止定位
	 */
	private void stopLocation() {
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;
	}

	/**
	 * 定位成功后的回调
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			myLocation = aLocation;
			mListener.onLocationChanged(aLocation);
		}
	}

	/**
	 * 过时啦
	 */
	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bus_route_textView:// 公交
			if (myLocation != null && null != customer1
					&& !"".equals(customer1.getLat())
					&& !"".equals(customer1.getLng())) {
				double lat = Double.parseDouble(customer1.getLat());
				double lng = Double.parseDouble(customer1.getLng());
				RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
						new LatLonPoint(myLocation.getLatitude(),
								myLocation.getLongitude()), new LatLonPoint(
								lat, lng));
				BusRouteQuery query = new BusRouteQuery(fromAndTo,
						RouteSearch.BusLeaseWalk, "010", 0);
				routeSearch.calculateBusRouteAsyn(query);
			} else {
				ToastUtils.showToast(MapActivity.this, "请重试！");
			}
			break;
		case R.id.customer_nearby_mark_imageView:// 查询客户附近的标签
			dialog.show();
			RequestParams params_marker = new RequestParams();
			params_marker.addBodyParameter("custID", customer1.getCustID());
			HttpUtil.post("browMapMark.action", params_marker,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							CustomerNearbyMarker nearbyMarker = GsonUtils
									.json2bean(responseInfo.result,
											CustomerNearbyMarker.class);
							if (!"".equals(nearbyMarker.getSuccess())
									&& null != nearbyMarker.getSuccess()
									&& Boolean.parseBoolean(nearbyMarker
											.getSuccess())) {
								dialog.hide();
								// 有标签
								List<CustomerNearbyMarkerItem> listMarker = nearbyMarker
										.getResultData();
								for (CustomerNearbyMarkerItem item : listMarker) {

									if ("wc".equals(item.getType())) {
										// 添加厕所marker
										addWCMarkersToMap(new LatLng(Double
												.parseDouble(item.getLat()),
												Double.parseDouble(item
														.getLng())));
									} else if ("store".equals(item.getType())) {
										addShopMarkersToMap(new LatLng(Double
												.parseDouble(item.getLat()),
												Double.parseDouble(item
														.getLng())));
									} else if ("bus".equals(item.getType())) {
										addBusMarkersToMap(new LatLng(Double
												.parseDouble(item.getLat()),
												Double.parseDouble(item
														.getLng())));
									} else if ("wifi".equals(item.getType())) {
										addWifiMarkersToMap(new LatLng(Double
												.parseDouble(item.getLat()),
												Double.parseDouble(item
														.getLng())));
									}
								}
							} else {
								// 没
								dialog.hide();
								ToastUtils.showToast(MapActivity.this,
										"客户附近还没有打过标签");
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
							ToastUtils.showToast(MapActivity.this,
									"查询客户附近标签失败，请重试！");
						}
					});
			break;
		case R.id.correct_customer_location_imageView:// 矫正客户经纬度
			if (gPSIsOPen(MapActivity.this) == true) {
				
				dialog.show();
				if (myLocation != null) {
					RequestParams params = new RequestParams();
					params.addBodyParameter("lng", myLocation.getLongitude() + "");
					params.addBodyParameter("lat", myLocation.getLatitude() + "");
					params.addBodyParameter("custID", customer1.getCustID());
					HttpUtil.post("fixCustAddress.action", params,
							new RequestCallBack<String>() {
	
								@Override
								public void onSuccess(ResponseInfo<String> responseInfo) {
									GlobalResult result = GsonUtils.json2bean(responseInfo.result,GlobalResult.class);
									if (!"".equals(result.getSuccess())&& null != result.getSuccess()&& Boolean.parseBoolean(result.getSuccess())) {
										ToastUtils.showToast(MapActivity.this,"矫正客户位置成功！");
										if (!"".equals(customerMarker) && null != customerMarker) {
											customerMarker.remove();
										}
										
										onLocationChanged(myLocation);
										addMarkersToMap(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), customer1.getCustAddress());
										customer1.setLat(myLocation.getLatitude()+"");
										customer1.setLng(myLocation.getLongitude() + "");
										lng = myLocation.getLongitude();
										lat = myLocation.getLatitude();
										PinetreeDB pinetreeDB = PinetreeDB.getInstance(MapActivity.this);
										pinetreeDB.updateCustomer(customer.getId(), myLocation.getLatitude()+"", myLocation.getLongitude() + "");
										dialog.hide();
									} else {
										ToastUtils.showToast(MapActivity.this,
												"矫正客户位置失败，请重试！");
										dialog.hide();
									}
								}
	
								@Override
								public void onFailure(HttpException error,
										String msg) {
									ToastUtils.showToast(MapActivity.this,
											"请求服务器失败，请重试！");
									dialog.hide();
								}
							});
				} else {
					ToastUtils.showToast(MapActivity.this, "亲，定位失败，请重试！");
					dialog.hide();
				}
			}else {
				ToastUtils.showToast(MapActivity.this, "请打开GPS！");
			}
			break;
		default:
			break;
		}
	}

	/**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    public static final boolean gPSIsOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }
    
	/**
	 * 公交路线回调
	 */
	@Override
	public void onBusRouteSearched(BusRouteResult result, int rCode) {
		if (rCode == 0) {
			if (result != null && result.getPaths() != null
					&& result.getPaths().size() > 0) {
				busRouteResult = result;
				BusPath busPath = busRouteResult.getPaths().get(0);
				aMap.clear();// 清理地图上的所有覆盖物
				BusRouteOverlay routeOverlay = new BusRouteOverlay(this, aMap,
						busPath, busRouteResult.getStartPos(),
						busRouteResult.getTargetPos());
				routeOverlay.removeFromMap();
				routeOverlay.addToMap();
				routeOverlay.zoomToSpan();
			} else {
				ToastUtils.showToast(MapActivity.this, "没有查询到线路！");
			}
		}
	}

	@Override
	public void onDriveRouteSearched(DriveRouteResult arg0, int arg1) {
	}

	@Override
	public void onWalkRouteSearched(WalkRouteResult arg0, int arg1) {
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
	}

	@Override
	public View getInfoContents(Marker arg0) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	/**
	 * 对长按事件的回调
	 */
	@Override
	public void onMapLongClick(final LatLng point) {

		AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
		builder.setTitle("亲，这个点标记为：");
		final View view = getLayoutInflater().inflate(
				R.layout.dialog_map_marker, null);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					Field field = alertDialog.getClass().getSuperclass()
							.getDeclaredField("mShowing");
					field.setAccessible(true);
					field.setBoolean(alertDialog, false);
					alertDialog.dismiss();

					RadioGroup mapMarkerRadioGroup = (RadioGroup) view
							.findViewById(R.id.map_marker_radioGroup);
					RadioButton markerText = (RadioButton) view
							.findViewById(mapMarkerRadioGroup
									.getCheckedRadioButtonId());
					if ("商店".equals(markerText.getText())) {

						if (NetUtil.checkNetWork(MapActivity.this)) {
							RequestParams params = new RequestParams();
							params.addBodyParameter("lng", point.longitude + "");
							params.addBodyParameter("lat", point.latitude + "");
							params.addBodyParameter("custID",
									customer1.getCustID());
							params.addBodyParameter("type", "store");
							HttpUtil.post("tagMapMark.action", params,
									new RequestCallBack<String>() {
										@Override
										public void onSuccess(
												ResponseInfo<String> responseInfo) {
											GlobalResult globalResult = GsonUtils
													.json2bean(
															responseInfo.result,
															GlobalResult.class);
											if (Boolean
													.parseBoolean(globalResult
															.getSuccess())) {
												addShopMarkersToMap(point);
											} else {
												ToastUtils.showToast(
														MapActivity.this,
														"亲，打标记失败，请重试");
											}
										}

										@Override
										public void onFailure(
												HttpException error, String msg) {
											ToastUtils.showToast(
													MapActivity.this,
													"亲，打标记失败，请重试");
										}
									});
							field.setBoolean(alertDialog, true);
						} else {
							ToastUtils.showToast(MapActivity.this, "亲，请检查网络");
						}

					} else if ("厕所".equals(markerText.getText())) {
						if (NetUtil.checkNetWork(MapActivity.this)) {
							RequestParams params = new RequestParams();
							params.addBodyParameter("lng", point.longitude + "");
							params.addBodyParameter("lat", point.latitude + "");
							params.addBodyParameter("custID",
									customer1.getCustID());
							params.addBodyParameter("type", "wc");
							HttpUtil.post("tagMapMark.action", params,
									new RequestCallBack<String>() {
										@Override
										public void onSuccess(
												ResponseInfo<String> responseInfo) {
											GlobalResult globalResult = GsonUtils
													.json2bean(
															responseInfo.result,
															GlobalResult.class);
											if (Boolean
													.parseBoolean(globalResult
															.getSuccess())) {
												addWCMarkersToMap(point);
											} else {
												ToastUtils.showToast(
														MapActivity.this,
														"亲，打标记失败，请重试");
											}
										}

										@Override
										public void onFailure(
												HttpException error, String msg) {
											ToastUtils.showToast(
													MapActivity.this,
													"亲，打标记失败，请重试");
										}
									});
							field.setBoolean(alertDialog, true);
						} else {
							ToastUtils.showToast(MapActivity.this, "亲，请检查网络");
						}

					} else if ("公交".equals(markerText.getText())) {
						if (NetUtil.checkNetWork(MapActivity.this)) {
							RequestParams params = new RequestParams();
							params.addBodyParameter("lng", point.longitude + "");
							params.addBodyParameter("lat", point.latitude + "");
							params.addBodyParameter("custID",
									customer1.getCustID());
							params.addBodyParameter("type", "bus");
							HttpUtil.post("tagMapMark.action", params,
									new RequestCallBack<String>() {
										@Override
										public void onSuccess(
												ResponseInfo<String> responseInfo) {
											GlobalResult globalResult = GsonUtils
													.json2bean(
															responseInfo.result,
															GlobalResult.class);
											if (Boolean
													.parseBoolean(globalResult
															.getSuccess())) {
												addBusMarkersToMap(point);
											} else {
												ToastUtils.showToast(
														MapActivity.this,
														"亲，打标记失败，请重试");
											}
										}

										@Override
										public void onFailure(
												HttpException error, String msg) {
											ToastUtils.showToast(
													MapActivity.this,
													"亲，打标记失败，请重试");
										}
									});
							field.setBoolean(alertDialog, true);
						} else {
							ToastUtils.showToast(MapActivity.this, "亲，请检查网络");
						}

					} else if ("wifi".equals(markerText.getText())) {
						if (NetUtil.checkNetWork(MapActivity.this)) {
							RequestParams params = new RequestParams();
							params.addBodyParameter("lng", point.longitude + "");
							params.addBodyParameter("lat", point.latitude + "");
							params.addBodyParameter("custID",
									customer1.getCustID());
							params.addBodyParameter("type", "wifi");
							HttpUtil.post("tagMapMark.action", params,
									new RequestCallBack<String>() {
										@Override
										public void onSuccess(
												ResponseInfo<String> responseInfo) {
											GlobalResult globalResult = GsonUtils
													.json2bean(
															responseInfo.result,
															GlobalResult.class);
											if (Boolean
													.parseBoolean(globalResult
															.getSuccess())) {
												addWifiMarkersToMap(point);
											} else {
												ToastUtils.showToast(
														MapActivity.this,
														"亲，打标记失败，请重试");
											}
										}

										@Override
										public void onFailure(
												HttpException error, String msg) {
											ToastUtils.showToast(
													MapActivity.this,
													"亲，打标记失败，请重试");
										}
									});
							field.setBoolean(alertDialog, true);
						} else {
							ToastUtils.showToast(MapActivity.this, "亲，请检查网络");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					Field field = alertDialog.getClass().getSuperclass()
							.getDeclaredField("mShowing");
					field.setAccessible(true);
					field.setBoolean(alertDialog, true);
					alertDialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * 添加WiFi Marker
	 * 
	 * @param point
	 */
	protected void addWifiMarkersToMap(LatLng point) {
		aMap.addMarker(new MarkerOptions()
				.position(point)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.map_wifi_marker))
				.visible(true));
	}

	/**
	 * 添加公交Marker
	 * 
	 * @param point
	 */
	protected void addBusMarkersToMap(LatLng point) {
		aMap.addMarker(new MarkerOptions()
				.position(point)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.map_bus_marker)).visible(true));
	}

	/**
	 * 添加WC Marker
	 * 
	 * @param point
	 */
	protected void addWCMarkersToMap(LatLng point) {
		aMap.addMarker(new MarkerOptions()
				.position(point)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.map_wc_marker)).visible(true));
	}

	/**
	 * 添加商店Marker
	 * 
	 * @param point
	 */
	protected void addShopMarkersToMap(LatLng point) {
		aMap.addMarker(new MarkerOptions()
				.position(point)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.map_shop_marker))
				.visible(true));
	}

	/**
	 * Marker点击事件
	 */
	@Override
	public boolean onMarkerClick(final Marker marker) {

		AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
		builder.setMessage("亲，是否删除标签？");
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				RequestParams params = new RequestParams();
				params.addBodyParameter("custID", customer1.getCustID());
				params.addBodyParameter("Lng", marker.getPosition().longitude
						+ "");
				params.addBodyParameter("lat", marker.getPosition().latitude
						+ "");
				HttpUtil.post("deleteMapMark.action", params,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								GlobalResult globalResult = GsonUtils
										.json2bean(responseInfo.result,
												GlobalResult.class);
								if (!"".equals(globalResult.getSuccess())
										&& null != globalResult.getSuccess()
										&& Boolean.parseBoolean(globalResult
												.getSuccess())) {
									marker.remove();
								} else {
									ToastUtils.showToast(MapActivity.this,
											"删除坐标失败，请重试！");
								}
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								ToastUtils.showToast(MapActivity.this,
										"删除坐标失败，请重试！");
							}
						});

			}
		});
		builder.create().show();
		return false;
	}

}
