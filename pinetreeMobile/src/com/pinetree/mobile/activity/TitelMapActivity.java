package com.pinetree.mobile.activity;

import java.util.ArrayList;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class TitelMapActivity extends Activity implements OnClickListener,
		InfoWindowAdapter {
	private MapView mapView;
	private AMap aMap;
	private ArrayList<Customer> customerList;
	private ImageView ivTitelMapBack;
	private Marker addMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_titel_map);

		Bundle bundle = getIntent().getExtras();
		customerList = (ArrayList<Customer>) bundle.getSerializable("customer");
		mapView = (MapView) findViewById(R.id.map_titel);
		mapView.onCreate(savedInstanceState);
		init();
		for (Customer customer : customerList) {
			if (null != customer && !"".equals(customer.getLat())
					&& !"".equals(customer.getLng())) {
				addMarkersToMap(
						new LatLng(Double.parseDouble(customer.getLat()),
								Double.parseDouble(customer.getLng())),
						customer);
			}
		}

	}

	private void init() {
		ivTitelMapBack = (ImageView) findViewById(R.id.iv_titel_map_back);
		ivTitelMapBack.setOnClickListener(this);
		if (aMap == null) {
			aMap = mapView.getMap();
			aMap.setInfoWindowAdapter(this);
		}

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
	 * 在地图上添加marker
	 */
	private void addMarkersToMap(LatLng point, Customer customer) {
		addMarker = aMap.addMarker(new MarkerOptions()
				.position(point)
				.anchor(0.5f, 0.5f)
				.icon(BitmapDescriptorFactory.fromBitmap(getBitmap(customer
						.getCustName()))).visible(true));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_titel_map_back:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public View getInfoContents(Marker arg0) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	/*
	 * 在Bitmap上写文字
	 */
	public Bitmap getBitmap(String info) {
		@SuppressWarnings("deprecation")
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.custom_info_bubble).copy(Bitmap.Config.ARGB_8888,
				true);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight());
		Canvas canvas = new Canvas(bitmap);
		TextPaint textPaint = new TextPaint();
		textPaint.setTextSize(30f);
		textPaint.setColor(Color.BLACK);
		canvas.drawText(info, 10, 45, textPaint);
		return bitmap;
	}
}
