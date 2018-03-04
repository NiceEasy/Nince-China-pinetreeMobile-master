package com.pinetree.mobile.utils;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	public final static String DEFAULT_CONFIG_NAME = "com.example.question";
	private SharedPreferences sp;
	private Editor editor;
	private final static int MODE = Context.MODE_PRIVATE;

	public SharedPreferencesUtil(Context context) {
		this.sp = context.getSharedPreferences(DEFAULT_CONFIG_NAME, MODE);
		this.editor = sp.edit();
	}

	public SharedPreferencesUtil(Context context, String SharedPreferencesFileName) {
		sp = context.getSharedPreferences(SharedPreferencesFileName, MODE);
		editor = sp.edit();
	}

	/**
	 * 创建一个工具类，默认打开名字为SharedPreferencesFileName的SharedPreferences实例
	 * 
	 * @param context
	 * @param SharedPreferencesFileName
	 *            唯一标识
	 * @param mode
	 *            权限标识
	 */
	public SharedPreferencesUtil(Context context, String SharedPreferencesFileName, int mode) {
		this.sp = context.getSharedPreferences(SharedPreferencesFileName, mode);
		this.editor = sp.edit();
	}

	/**
	 * 添加信息到SharedPreferences
	 * 
	 * @param name
	 * @param map
	 * @throws Exception
	 */
	public void add(Map<String, String> map) {
		Set<String> set = map.keySet();
		for (String key : set) {
			editor.putString(key, map.get(key));
		}
		editor.commit();
	}

	/**
	 * 删除信息
	 * 
	 * @throws Exception
	 */
	public void clear() throws Exception {
		editor.clear();
		editor.commit();
	}

	/**
	 *删除一条信息
	 */
	public void remove(String key) {
		editor.remove(key);
		editor.commit();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean put(String key, String value) {
		editor.putString(key, value);
		return editor.commit();
	}

	/**
	 * 获取信息
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String get(String key) {
		if (sp != null) {
			return sp.getString(key, "");
		}
		return "";
	}

	/**
	 * 获取信息
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String get(String key, String defaultValue) {
		if (sp != null) {
			return sp.getString(key, defaultValue);
		}
		return defaultValue;
	}

	/**
	 * 获取此SharedPreferences的Editor实例
	 * 
	 * @return
	 */
	public Editor getEditor() {
		return editor;
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putInt(String key, int value) {
		editor.putInt(key, value);
		return editor.commit();
	}

	/**
	 * 获取信息
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int getInt(String key) {
		if (sp != null) {
			return sp.getInt(key, 0);
		}
		return 0;
	}
}
