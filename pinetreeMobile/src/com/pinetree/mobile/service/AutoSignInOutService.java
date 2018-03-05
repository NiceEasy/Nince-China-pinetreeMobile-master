package com.pinetree.mobile.service;

import java.util.List;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.pinetree.mobile.GlobalContext;
import com.pinetree.mobile.bean.SignIn;
import com.pinetree.mobile.bean.SignInOut;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.receiver.AutoSignInOutReceiver;
import com.pinetree.mobile.utils.GsonUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;

public class AutoSignInOutService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				submitToService();
			}
		}).start();
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour = 1000 * 30;//
		long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
		Intent i = new Intent(this, AutoSignInOutReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		int interval = 60 * 1000;
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				triggerAtTime, interval, pi);
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 查询本地数据签退签退表中数据，提交数据
	 */
	protected void submitToService() {
		List<SignInOut> list = PinetreeDB.getInstance(
				GlobalContext.getContext()).getSignInOut();
		
		SharedPreferences sp = getSharedPreferences("UserInfo",
				MODE_PRIVATE);
		String employeeId = sp.getString("employeeId", "");
		String employeeName = sp.getString("employeeName", "");
		if (list.size() > 0) {
			for (final SignInOut signInOut : list) {
				if (signInOut.getState().equals("1")) {
					if (signInOut.getIsSignInOut().equals("1")) {
						RequestParams params = new RequestParams();
						params.addBodyParameter("taskID", signInOut.getTaskId());//日程id
						params.addBodyParameter("inOut", "1");	
						params.addBodyParameter("date", signInOut.getDate());
						params.addBodyParameter("empID", employeeId);
						params.addBodyParameter("empName", employeeName);
						params.addBodyParameter("empNum", signInOut.getEmpNum());
						System.out.println("taskID:"+signInOut.getTaskId()+"  date:"+signInOut.getDate()+"  empID:"+employeeId);
						HttpUtil.post("staffSign.action", params,
								new RequestCallBack<String>() {

									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										SignIn signIn = GsonUtils.json2bean(responseInfo.result,SignIn.class);
										if (Boolean.parseBoolean(signIn.getSuccess())) {
											System.out.println("签到成功");
											PinetreeDB.getInstance(GlobalContext.getContext()).updateSignInOutState(signInOut.getTaskId(),signInOut.getDate());
										} else {
											System.out.println("签到失败"+signIn.getSuccess());
										}
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										System.out.println("签到失败");
									}
								});
					} else if(signInOut.getIsSignInOut().equals("0")){
						RequestParams params = new RequestParams();
						params.addBodyParameter("taskID", signInOut.getTaskId());
						params.addBodyParameter("inOut", "0");
						params.addBodyParameter("empID", employeeId);
						params.addBodyParameter("date", signInOut.getDate());
						params.addBodyParameter("empName", employeeName);
						params.addBodyParameter("empNum", signInOut.getEmpNum());
						HttpUtil.post("staffSign.action", params,
								new RequestCallBack<String>() {

									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										SignIn signIn = GsonUtils.json2bean(responseInfo.result,SignIn.class);
										if (Boolean.parseBoolean(signIn.getSuccess())) {
											System.out.println("签退成功");
											PinetreeDB.getInstance(GlobalContext.getContext()).updateSignInOutState(signInOut.getTaskId(),signInOut.getDate());
										} else {
											System.out.println("签退失败");
										}
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										System.out.println("签退失败");
									}
								});
					}

				}
			}
		}

	}

}
