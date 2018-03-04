package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.Customers;
import com.pinetree.mobile.bean.User;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.service.AutoSignInOutService;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

public class SplashActivity extends Activity {

	protected static final int SELECT_CUSTOMER_DB = 20;

	private final int FIRST_SUCCESS_RESULT = 10;
	private final int BACKGROUND_LOGIN = 30;// 后台登陆

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case FIRST_SUCCESS_RESULT:
				ArrayList<Customer> customerList = (ArrayList<Customer>) msg.obj;
				
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(SplashActivity.this);
				pinetreeDB.deleteAllCustomer();
				for (int i = customerList.size() - 1; i >= 0; i--) {
					Customer customer2 = customerList.get(i);
					customer2.setLoadDataTag("1");
					customer2.setSignResignTag("0");
					pinetreeDB.saveCustomer(customer2);
				}
				Intent intent = new Intent(SplashActivity.this,
						ScheduleActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("customerList", customerList);
				String userName = msg.getData().getString("userName");
				String selectEmployeeId = msg.getData().getString("selectEmployeeId");
				bundle.putString("employeeName", userName);
				bundle.putString("employeeId", selectEmployeeId);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			case SELECT_CUSTOMER_DB:
				ArrayList<Customer> customerListDB = (ArrayList<Customer>) msg.obj;
				String userNameDB = msg.getData().getString("userName");
				String selectEmployeeIdDB = msg.getData().getString("selectEmployeeId");
				Intent intent2 = new Intent(SplashActivity.this,ScheduleActivity.class);
				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("customerList", customerListDB);
				bundle2.putString("employeeName", userNameDB);
				bundle2.putString("employeeId", selectEmployeeIdDB);
				intent2.putExtras(bundle2);
				startActivity(intent2);
				
				break;
			case BACKGROUND_LOGIN:// 后台登陆
				User user = (User) msg.obj;
				if (!"".equals(user.getSuccess())&&Boolean.parseBoolean(user.getSuccess())) {
					SharedPreferences sp = getSharedPreferences("UserInfo",MODE_PRIVATE);
					String employeeId = sp.getString("employeeId", "");
					String employeeName = sp.getString("employeeName", "");
					getBackgroundFirstData(employeeId, employeeName);
				} else {
					ToastUtils.showToast(SplashActivity.this, "后台登陆失败，请重新登陆");
					Intent i = new Intent(SplashActivity.this,LoginActivity.class);
					startActivity(i);
					finish();
				}
				break;
			default:
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
		animation.setDuration(3000);
		findViewById(R.id.splash_root).startAnimation(animation);

		Intent intent = new Intent(SplashActivity.this,
				AutoSignInOutService.class);
		startService(intent);

		new Handler().postDelayed(new Runnable() {

			private PinetreeDB pinetreeDB;

			@Override
			public void run() {
				SharedPreferences sp = getSharedPreferences("userName&Pwd",
						MODE_PRIVATE);
				String userName = sp.getString("userName", "");
				String userPwd = sp.getString("userPwd", "");
				if (!userName.equals("") && !userPwd.equals("")) {
					if (NetUtil.checkNetWork(SplashActivity.this)) {
						RequestParams params = new RequestParams();
						params.addBodyParameter("name", userName);
						params.addBodyParameter("password", userPwd);
						HttpUtil.post("login.action", params,
								new RequestCallBack<String>() {
									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										User user = GsonUtils.json2bean(responseInfo.result,User.class);
										if (!"".equals(user)&&null!=user) {
											Message message = Message.obtain();
											message.obj = user;
											message.what = BACKGROUND_LOGIN;
											handler.sendMessage(message);
										}else {
											Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
											startActivity(intent);
											finish();
										}
										
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										ToastUtils.showToast(SplashActivity.this,"后台登陆失败，请重新登陆");
										Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
										startActivity(intent);
										finish();
									}
								});
					} else {
						List<Customer> customerList = PinetreeDB.getInstance(SplashActivity.this).selectCustomerWork();
						Message message = Message.obtain();
						message.obj = customerList;
						SharedPreferences sp_ = getSharedPreferences("UserInfo", MODE_PRIVATE);
						String employeeId = sp_.getString("employeeId", "");
						String employeeName = sp_.getString("employeeName", "");
						Bundle bundle = new Bundle();
						bundle.putString("userName", employeeName);
						bundle.putString("selectEmployeeId", employeeId);
						message.setData(bundle);
						message.what = SELECT_CUSTOMER_DB;
						handler.sendMessage(message);

					}
				} else {
					Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, 3000);

	}

	/**
	 * 后台获取第一登陆成功后的数据
	 * 
	 * @param employeeId
	 */
	protected void getBackgroundFirstData(final String employeeId,final String employeeName) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("empID", employeeId);
		HttpUtil.post("currentTask.action", params,new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Customers customers = GsonUtils.json2bean(responseInfo.result, Customers.class);
						Message message = Message.obtain();
						if (!"".equals(customers.getSuccess())&&Boolean.parseBoolean(customers.getSuccess())) {
							message.obj = customers.getResultData();
							Bundle bundle = new Bundle();
							bundle.putString("userName", employeeName);
							bundle.putString("selectEmployeeId", employeeId);
							message.setData(bundle);
							message.what = FIRST_SUCCESS_RESULT;
							handler.sendMessage(message);
						} else {
							Toast.makeText(SplashActivity.this, "亲，今天无日程哦", 1).show();
							message.obj = customers.getResultData();
							Bundle bundle = new Bundle();
							bundle.putString("userName", employeeName);
							bundle.putString("selectEmployeeId", employeeId);
							message.setData(bundle);
							message.what = FIRST_SUCCESS_RESULT;
							handler.sendMessage(message);
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						ToastUtils.showToast(SplashActivity.this,"亲，请求服务器失败，请重试！");
						Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});
	}
}
