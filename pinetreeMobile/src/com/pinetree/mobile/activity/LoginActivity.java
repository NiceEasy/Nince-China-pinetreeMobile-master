package com.pinetree.mobile.activity;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.GlobalContext;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.Customers;
import com.pinetree.mobile.bean.User;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	protected static final int LOGIN_SUCCESS = 20;
	@ViewInject(R.id.et_userName)
	private EditText etUserName;
	@ViewInject(R.id.et_userPwd)
	private EditText etuserPwd;
	/**
	 * 登陆成功返回的客户集合tag
	 */
	private final int LOGIN_SUCCESS_RESULT = 10;

	private Handler handler = new Handler() {
		private User user;

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOGIN_SUCCESS:
				user = (User) msg.obj;
				if (!"".equals(user.getSuccess())&&Boolean.parseBoolean(user.getSuccess())) {
					Editor editor = GlobalContext.getContext().getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
					editor.clear();
					editor.commit();
					editor.putString("employeeName", user.getResultData().getEmployeeName());
					editor.putString("employeeId", user.getResultData().getEmployeeId());
					editor.commit();
					loginSuccessData(user.getResultData().getEmployeeId());
				} else {
					dialog.hide();
					ToastUtils.showToast(LoginActivity.this, "用户名或密码错误！");
				}
				break;
			case LOGIN_SUCCESS_RESULT:
				ArrayList<Customer> customerList = (ArrayList<Customer>) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB
						.getInstance(LoginActivity.this);
				pinetreeDB.deleteAllCustomer();
				
				for (int i = customerList.size()-1; i >=0; i--) {
					Customer customer2 = customerList.get(i);
					customer2.setLoadDataTag("1");
					customer2.setSignResignTag("0");
					pinetreeDB.saveCustomer(customer2);
				}
				Intent intent = new Intent(LoginActivity.this,
						ScheduleActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("customerList", customerList);
				bundle.putString("employeeName", user.getResultData().getEmployeeName());
				bundle.putString("employeeId", user.getResultData().getEmployeeId());
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		};
	};
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		ViewUtils.inject(this);

		dialog = new Dialog(LoginActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_login);
		dialog.setCanceledOnTouchOutside(false);
	}

	@OnClick(R.id.btn_login)
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_login:
			dialog.show();
			final String userName = etUserName.getText().toString().trim();
			final String userPwd = etuserPwd.getText().toString().trim();
			if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
				dialog.hide();
				ToastUtils.showToast(LoginActivity.this, "亲，账户或密码不能为空");
			} else {
				if (!NetUtil.checkNetWork(LoginActivity.this)) {
					dialog.hide();
					ToastUtils.showToast(LoginActivity.this, "亲，您没有网络哦！");
				} else {
					RequestParams params = new RequestParams();
					params.addBodyParameter("name", userName);
					params.addBodyParameter("password", userPwd);
					HttpUtil.post("login.action", params,new RequestCallBack<String>() {
							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								User user = GsonUtils.json2bean(responseInfo.result, User.class);
								dialog.hide();
								if (!"".equals(user)&&null!=user&&!"".equals(user.getSuccess())&&Boolean.valueOf(user.getSuccess())) {
									Editor editor = GlobalContext.getContext().getSharedPreferences("userName&Pwd",MODE_PRIVATE).edit();
									editor.clear();   
							        editor.commit();  
									
							        editor.putString("userName", userName);
									editor.putString("userPwd", userPwd);
									editor.commit();
									Message message = Message.obtain();
									message.obj = user;
									message.what = LOGIN_SUCCESS;
									handler.sendMessage(message);
								}else {
									ToastUtils.showToast(LoginActivity.this,"用户名或密码错误！");
								}
									
							}

							@Override
							public void onFailure(HttpException error,String msg) {
								dialog.hide();
								ToastUtils.showToast(LoginActivity.this,"登陆失败，请重试");
							}
						});
				}
			}

			break;

		default:
			break;
		}
	}

	private void loginSuccessData(String employeeId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("empID", employeeId);
		HttpUtil.post("currentTask.action", params,	new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						dialog.hide();
						Customers customers = GsonUtils.json2bean(
								responseInfo.result, Customers.class);
						if (!"".equals(customers.getSuccess())) {
							Message message = Message.obtain();
							if (Boolean.parseBoolean(customers.getSuccess())) {
								message.obj = customers.getResultData();
								
								message.what = LOGIN_SUCCESS_RESULT;
								handler.sendMessage(message);
							} else {
								Toast.makeText(LoginActivity.this, "亲，今天无日程哦", 1).show();
								message.obj = customers.getResultData();
								message.what = LOGIN_SUCCESS_RESULT;
								handler.sendMessage(message);
							}
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						dialog.hide();
						ToastUtils.showToast(LoginActivity.this,"亲，请求服务器失败，请重试！");
					}

				});
	}

}
