package com.pinetree.mobile.net;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.pinetree.mobile.ConstantsValue;

/**
 * xutils(Async-Http-Client)访问网络
 * 
 * @author Administrator
 * 
 */
public class HttpUtil {
	private static HttpUtils http = new HttpUtils();

	/**
	 * get方式请求
	 * 
	 * @param url
	 * @param callBack
	 */
	public static void get(String url, RequestParams params,
			RequestCallBack<String> callBack) {
		http.send(HttpRequest.HttpMethod.GET, getAbsoluteUrl(url), params,
				callBack);
	}

	/**
	 * post方式请求
	 * 
	 * @param url
	 * @param params
	 * @param callBack
	 */
	public static void post(String url, RequestParams params,
			RequestCallBack<String> callBack) {
		http.send(HttpRequest.HttpMethod.POST, getAbsoluteUrl(url), params,
				callBack);
	}

	/**
	 * 拼装url
	 * 
	 * @param relativeUrl
	 * @return
	 */
	private static String getAbsoluteUrl(String relativeUrl) {
		// System.out.println(ConstantsValue.BASE_URL + relativeUrl);
		return ConstantsValue.BASE_URL + relativeUrl;
	}
}
