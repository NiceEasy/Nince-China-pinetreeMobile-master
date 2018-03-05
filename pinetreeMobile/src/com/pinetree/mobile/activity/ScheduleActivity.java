package com.pinetree.mobile.activity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.fr.android.activity.LoadAppFromWelcomeActivity;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.Customers;
import com.pinetree.mobile.bean.SignInOut;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;
import com.pinetree.mobile.view.RefreshableView;
import com.pinetree.mobile.view.RefreshableView.PullToRefreshListener;

/**
 * 工作安排
 * 
 * @author Administrator
 * 
 */
public class ScheduleActivity extends Activity implements OnClickListener {

	protected static final int REFRESHABLE_RESULT_DB = 20;
	private ArrayList<Customer> customerList=new ArrayList<Customer>();
	private SwipeMenuListView listView;

	/**
	 * 下拉刷新返回的结果tag
	 */
	private final int REFRESHABLE_RESULT = 10;
	/**
	 * 下拉刷新view
	 */
	private RefreshableView refreshableView;
	/**
	 * 下拉刷新次数
	 */
	private int RefreshableCount = 0;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case REFRESHABLE_RESULT:
				ArrayList<Customer> customerListRefresh = (ArrayList<Customer>) msg.obj;
				if (RefreshableCount == 0) {
					PinetreeDB pinetreeDB = PinetreeDB
							.getInstance(ScheduleActivity.this);
					for (int i = customerListRefresh.size()-1; i >=0; i--) {
						Customer customer2 = customerListRefresh.get(i);
						customer2.setLoadDataTag("1");
						customer2.setSignResignTag("0");
						pinetreeDB.saveCustomer(customer2);
					}
					RefreshableCount = 1;
				}
				customerList.addAll(0, customerListRefresh);
				adapter.notifyDataSetChanged();
				break;
			case REFRESHABLE_RESULT_DB:// 本地数据，下拉刷新
				ArrayList<Customer> customerListRefreshDB = (ArrayList<Customer>) msg.obj;
				customerList.addAll(0, customerListRefreshDB);
				adapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};
	private CustomerAdapter adapter;
	private Dialog dialog;
	private ImageView mapTitleImage;
	private TextView accountManage;
	private TextView offLineFillTextView;
	private TextView nursingPlanTextView;//康复护理计划
	private String employeeName;
	private String employeeId;
	private String empNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_schedule);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(System.currentTimeMillis());
		todayDate = dateFormat.format(date);
		Bundle bundle = getIntent().getExtras();
		customerList = (ArrayList<Customer>) bundle
				.getSerializable("customerList");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		if (customerList.size() > 0) {
			empNum = customerList.get(0).getEmpNum();
		}
		
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		listView = (SwipeMenuListView) findViewById(R.id.listView);

		offLineFillTextView = (TextView) findViewById(R.id.finereport_textView);
		offLineFillTextView.setOnClickListener(this);
		mapTitleImage = (ImageView) findViewById(R.id.map_title_image);
		mapTitleImage.setOnClickListener(this);
		accountManage = (TextView) findViewById(R.id.account_manage);
		accountManage.setOnClickListener(this);
		nursingPlanTextView = (TextView) findViewById(R.id.nursingPlan_textView);
		nursingPlanTextView.setOnClickListener(this);
		
		dialog = new Dialog(ScheduleActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);

		adapter = new CustomerAdapter();
		listView.setAdapter(adapter);

		refreshableView.setOnRefreshListener(new PullToRefreshListener() {

			@Override
			public void onRefresh() {

				String date="";
				if (customerList.size()>0) {
					// 2014-12-
					String date_year_month = customerList.get(listView.getFirstVisiblePosition()).getBeginTime().substring(0, 8);
					//年
					String date_year = customerList.get(listView.getFirstVisiblePosition()).getBeginTime().substring(0, 4);
					//月
					String date_mouth = customerList.get(listView.getFirstVisiblePosition()).getBeginTime().substring(5, 7);
					// 31
					String date_day = customerList.get(listView.getFirstVisiblePosition()).getBeginTime().substring(8, 10);
					int date_day_temp_ = Integer.parseInt(date_day) + 1;
					String date_day_temp = "";
					if (date_day_temp_ < 10) {
						date_day_temp = "0" + date_day_temp_;
					} else {
						date_day_temp = date_day_temp_ + "";
					}
					
					// date 2014-12-30
					String date1 = date_year_month + date_day_temp;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						java.util.Date dbDate = format.parse(date1);
						if (dbDate.before(new java.util.Date())){
							date = format.format(new java.util.Date()).toString();
						}else {
							date = date_year_month + date_day_temp;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else {
					SimpleDateFormat dateFormat_=new SimpleDateFormat("yyyy-MM-dd");
					date=dateFormat_.format(new java.util.Date()).toString();
				}
			
				// 判断网络
				if (NetUtil.checkNetWork(ScheduleActivity.this)) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (StringUtils.isBlank(date)) {
						ToastUtils.showToast(ScheduleActivity.this,
								"亲，您暂时没有日程安排哦");
					} else {
						RequestParams params = new RequestParams();
						params.addBodyParameter("date", date);
						params.addBodyParameter("add", "5");
						params.addBodyParameter("empID", employeeId);
						HttpUtil.post("futureTask.action", params,
								new RequestCallBack<String>() {

									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										Customers customers = GsonUtils
												.json2bean(responseInfo.result,
														Customers.class);
										if (Boolean.parseBoolean(customers
												.getSuccess())) {
											Message message = new Message();
											message.obj = customers
													.getResultData();
											message.what = REFRESHABLE_RESULT;
											handler.sendMessage(message);
											adapter.notifyDataSetChanged();
										} else {
											ToastUtils.showToast(ScheduleActivity.this,"亲，没有日程安排啦啦");
										}
									}

									@Override
									public void onFailure(HttpException error,String msg) {
										ToastUtils.showToast(ScheduleActivity.this,"请求服务器失败，请重试！");
									}
								});

					}

				}

				refreshableView.finishRefreshing();
			}
		}, 0);

		listView.setOnScrollListener(new OnScrollListener() {
			/**
			 * 当滚动状态发生改变时调用这个方法
			 */
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:// 空闲状态
					int lastVisiblePosition = listView.getLastVisiblePosition();
					if (customerList.size() == lastVisiblePosition + 1) {
					}
					break;
				case OnScrollListener.SCROLL_STATE_FLING:// 惯性
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 触摸滚动
					break;

				default:
					break;
				}
			}

			/**
			 * 当滚动时调用
			 */
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem mapItem = new SwipeMenuItem(ScheduleActivity.this);
				mapItem.setBackground(new ColorDrawable(Color
						.parseColor("#ffd467")));
				mapItem.setWidth(dp2px(90));
				mapItem.setIcon(R.drawable.map_icon);
				menu.addMenuItem(mapItem);

				SwipeMenuItem workItem = new SwipeMenuItem(
						ScheduleActivity.this);
				workItem.setBackground(new ColorDrawable(Color
						.parseColor("#cc560f77")));
				workItem.setWidth(dp2px(90));
				workItem.setIcon(R.drawable.work_icon);
				menu.addMenuItem(workItem);
			}
		};
		listView.setMenuCreator(creator);

		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,
					int index) {
				Customer customer = customerList.get(position);
				String prodID = customer.getProdID();
				String categoryID = customer.getCategoryID();
				switch (index) {
				case 0:
					dialog.show();
					enterMap(customer);
					break;
				case 1:
					if ("55439e9f5f464f3faca459dfde73e8d3".equals(categoryID)) {	
						Intent intent = new Intent(ScheduleActivity.this,MainListActivity
								.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("initData", customer);
						bundle.putString("employeeName", employeeName);
						bundle.putString("employeeId", employeeId);
						intent.putExtras(bundle);
						startActivity(intent);
						
					}else {
						enterFragment(customer);
					}
					
					break;

				default:
					break;
				}
				return false;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.changeLayoutVisable(position);
			}
		});

		deleteSignInOutData();
	}
	
	/**
	 * 删除15天之前的签到签退表的数据
	 */
	private void deleteSignInOutData(){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(ScheduleActivity.this);
		List<SignInOut> list = pinetreeDB.selectAllSignInOut();
		if (list.size() > 0) {
			Date time = new Date(System.currentTimeMillis()  - 16*24*60*60*1000);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(time);
			int timeHis = Integer.parseInt(dateStr);
			for (int i = 0; i < list.size(); i++) {
				String createTime = list.get(i).getDate();
				if (!"".equals(createTime) && null != createTime) {
					String timeDB = createTime.substring(0, 4)+ createTime.substring(5, 7)+ createTime.substring(8, 10);
					int timeDb = Integer.parseInt(timeDB);
					if (timeHis > timeDb) {
						String scheID = list.get(i).getTaskId();
						pinetreeDB.deleteSignInOut(scheID);
					}
				}
			}
		}
	}
	
	
	/**
	 * 进入服务记录，客户签字，评估报告
	 * 
	 * @param customer
	 */
	protected void enterFragment(Customer customer) {
		Intent intent = new Intent(ScheduleActivity.this,
				ActivityFragment.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("customer", customer);
		bundle.putString("employeeName", employeeName);
		bundle.putString("employeeId", employeeId);
		bundle.putString("prodID", customer.getProdID());
		bundle.putString("prodType", customer.getProdType());
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 进入地图
	 * 
	 * @param customer
	 */
	protected void enterMap(Customer customer) {
		if (NetUtil.checkNetWork(ScheduleActivity.this)) {
			Intent intent = new Intent(ScheduleActivity.this, MapActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("customer", customer);
			intent.putExtras(bundle);
			startActivity(intent);
			dialog.hide();
		} else {
			ToastUtils.showToast(ScheduleActivity.this, "亲，请检查网络哦！");
			dialog.hide();
		}
	}

	class CustomerAdapter extends BaseAdapter {
		private int mLastPosition = -1;

		@Override
		public int getCount() {
			return customerList.size();
		}

		public void changeLayoutVisable(int position) {
			if (position != mLastPosition) {
				mLastPosition = position;
			} else {
				mLastPosition = -1;
			}
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			return customerList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(ScheduleActivity.this,
						R.layout.listview_item, null);
				new ViewHolder(convertView);
			}
			final ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.btn_signIn.setTag(position);
			final Customer customer = (Customer) getItem(position);

			holder.tv_serveDate.setText(customer.getBeginTime()
					.substring(0, 10));
			holder.tv_serveTime.setText(customer.getBeginTimeSub() + "-"
					+ customer.getEndTimeSub());
			holder.tv_Customer.setText("为" + customer.getCustName() + "做"
					+ customer.getProdName());
			holder.tv_customer_phone.setText(" ：" + customer.getCustPhone());
			holder.tv_customer_address
					.setText(" ：" + customer.getCustAddress());
			holder.ll_view_hide.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setAction("android.intent.action.DIAL");
					intent.setData(Uri.parse("tel:" + customer.getCustPhone()));
					startActivity(intent);
				}
			});

			String itemDate = customer.getBeginTime().substring(0, 4)
					+ customer.getBeginTime().substring(5, 7)
					+ customer.getBeginTime().substring(8, 10);
			if (Integer.parseInt(todayDate) == Integer.parseInt(itemDate)) {
				String selectSignInOut = PinetreeDB.getInstance(
						ScheduleActivity.this).selectSignInOut(
						customer.getId());

				if (selectSignInOut != null) {
					if (selectSignInOut.equals("1")) {
						holder.btn_signIn.setBackgroundColor(Color
								.parseColor("#cc560f77"));
						holder.btn_signIn.setText("签退");
					} else if (selectSignInOut.equals("0")) {
						holder.btn_signIn.setBackgroundColor(Color
								.parseColor("#a5a5a5"));
						holder.btn_signIn.setText("完成");
						holder.btn_signIn.setClickable(false);
					}
				} else {
					holder.btn_signIn.setBackgroundColor(Color
							.parseColor("#ff983d"));
					holder.btn_signIn.setText("签到");
				}
			} else if (Integer.parseInt(todayDate) > Integer.parseInt(itemDate)) {
				holder.btn_signIn.setBackgroundColor(Color
						.parseColor("#a5a5a5"));
				holder.btn_signIn.setText("完成");
				holder.btn_signIn.setClickable(false);
			} else if (Integer.parseInt(todayDate) < Integer.parseInt(itemDate)) {
				holder.btn_signIn.setBackgroundColor(Color
						.parseColor("#a5a5a5"));
				holder.btn_signIn.setText("未进行");
				holder.btn_signIn.setClickable(false);
			}

			holder.btn_signIn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					PinetreeDB pinetreeDB = PinetreeDB
							.getInstance(ScheduleActivity.this);
					if (holder.btn_signIn.getText().equals("签到")) {

						SignInOut signInOut = new SignInOut();
						signInOut.setDate(new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(System
								.currentTimeMillis()));
						signInOut.setIsSignInOut("1");
						signInOut.setTaskId(customer.getId());
						signInOut.setState("1");
						signInOut.setEmpNum(empNum);
						boolean b = pinetreeDB.saveSignInOut(signInOut);
						if (b) {
							ToastUtils.showToast(ScheduleActivity.this, "签到成功");
							holder.btn_signIn.setBackgroundColor(Color
									.parseColor("#cc560f77"));
							holder.btn_signIn.setText("签退");
							adapter.notifyDataSetChanged();
						} else {
							ToastUtils.showToast(ScheduleActivity.this,
									"签到失败请重试");
						}

					} else if (holder.btn_signIn.getText().equals("签退")) {
						SignInOut signInOut = new SignInOut();
						signInOut.setDate(new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(System
								.currentTimeMillis()));
						signInOut.setIsSignInOut("0");
						signInOut.setTaskId(customer.getId());
						signInOut.setState("1");
						signInOut.setEmpNum(empNum);

						boolean b = pinetreeDB.saveSignInOut(signInOut);
						if (b) {
							ToastUtils.showToast(ScheduleActivity.this, "签退成功");
							holder.btn_signIn.setBackgroundColor(Color
									.parseColor("#a5a5a5"));
							holder.btn_signIn.setText("完成");
							holder.btn_signIn.setClickable(false);
							adapter.notifyDataSetChanged();
						} else {
							ToastUtils.showToast(ScheduleActivity.this,
									"签退失败请重试");
						}

					}

				}
			});
			
			if (position == mLastPosition) {
				holder.ll_view_hide.setVisibility(View.VISIBLE);
			} else {
				holder.ll_view_hide.setVisibility(View.GONE);
			}
			return convertView;
		}
		
	}

	class ViewHolder {
		Button btn_signIn;
		TextView tv_serveDate, tv_Customer, tv_customer_phone,
				tv_customer_address, tv_serveTime;
		LinearLayout ll_view_hide;

		public ViewHolder(View view) {
			btn_signIn = (Button) view.findViewById(R.id.btn_signIn);
			tv_serveDate = (TextView) view.findViewById(R.id.tv_serveDate);
			tv_Customer = (TextView) view.findViewById(R.id.tv_Customer);
			tv_customer_phone = (TextView) view
					.findViewById(R.id.tv_customer_phone);
			tv_customer_address = (TextView) view
					.findViewById(R.id.tv_customer_address);
			ll_view_hide = (LinearLayout) view.findViewById(R.id.ll_view_hide);
			tv_serveTime = (TextView) view.findViewById(R.id.tv_serveTime);
			view.setTag(this);
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.map_title_image:// 首页顶部进入地图，应该请求服务器拿到数据在地图上打标签
			dialog.show();
			RequestParams params = new RequestParams();
			params.addBodyParameter("empID", employeeId);
			HttpUtil.post("browCurCust.action", params,
					new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							Customers customers = GsonUtils.json2bean(
									responseInfo.result, Customers.class);
							dialog.hide();
							if (Boolean.parseBoolean(customers.getSuccess())) {
								Intent intent = new Intent(
										ScheduleActivity.this,
										TitelMapActivity.class);
								Bundle bundle = new Bundle();
								bundle.putSerializable("customer",
										(ArrayList<Customer>) customers
												.getResultData());
								intent.putExtras(bundle);
								startActivity(intent);
							} else {
								ToastUtils.showToast(ScheduleActivity.this,
										"请求服务器失败，请重试");
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
							ToastUtils.showToast(ScheduleActivity.this,
									"请求服务器失败，请重试");
						}
					});
			break;
		case R.id.account_manage:
			Intent intent2 = new Intent(ScheduleActivity.this,
					AccountmManageActivity.class);
			startActivity(intent2);
			break;
		case R.id.finereport_textView://离线填报
			Intent welcomeIntent = new Intent(this, LoadAppFromWelcomeActivity.class);
			SharedPreferences sp = getSharedPreferences("userName&Pwd",
					MODE_PRIVATE);
			String userName = sp.getString("userName", "");
			String userPwd = sp.getString("userPwd", "");
			
	        welcomeIntent.putExtra("username", userName); // 数据决策系统 用户名
	        welcomeIntent.putExtra("password", userPwd); // 数据决策系统 用户密码
	        welcomeIntent.putExtra("serverIp", "http://fine.qskh.cn/WebReport/ReportServer"); // 数据决策系统 地址ip
	        welcomeIntent.putExtra("serverName", "青松"); // 数据决策系统 名称

	        startActivity(welcomeIntent);
			break;
		case R.id.nursingPlan_textView://康复护理计划
			Intent intent = new Intent(ScheduleActivity.this,RecoveryNursingPlanListActivity.class);
			intent.putExtra("employeeId", employeeId);
			intent.putExtra("employeeName", employeeName);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	private boolean isExit;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			ToastUtils.showToast(ScheduleActivity.this, "再按一次退出程序");
			mHandler.sendEmptyMessageDelayed(0, 1000);
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			System.exit(0);
		}
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}

	};
	private String todayDate;
}
