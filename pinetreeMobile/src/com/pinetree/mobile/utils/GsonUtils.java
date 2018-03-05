package com.pinetree.mobile.utils;

import com.google.gson.Gson;

/**
 * gson解析工具类
 * 
 * @author Administrator
 * 
 */
public class GsonUtils {

	private static Gson gson = new Gson();

	public static <T> T json2bean(String result, Class<T> clazz) {

		T t = gson.fromJson(result, clazz);
		return t;
	}

}
