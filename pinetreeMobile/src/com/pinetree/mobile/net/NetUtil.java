package com.pinetree.mobile.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断是否有网络连接(WiFi和APN)
 * 
 * @author Administrator
 * 
 */
public class NetUtil {

	/**
	 * 判断网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkNetWork(Context context) {
		boolean isWifi = isWifiConnection(context);
		boolean isAPN = isAPNConnection(context);
		if (!isWifi && !isAPN) {
			return false;
		}

	/*	if (isAPN) {
			String host = android.net.Proxy.getDefaultHost();
			if (StringUtils.isNotBlank(host)) {
				// 判断为WAP连接，需要设置代理端口
				GlobalParams.isWap = true;
			}
		}*/
		return true;
	}

	/**
	 * 判断基站连接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isAPNConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}

	/**
	 * 判断WiFi连接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isWifiConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}
}
