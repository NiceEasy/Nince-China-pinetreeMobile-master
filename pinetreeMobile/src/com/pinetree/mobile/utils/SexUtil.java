package com.pinetree.mobile.utils;

public class SexUtil {
	/**
	 * 处理服务器给的傻逼性别
	 */
	public static String getSex(String sexNum) {
		if (!"".equals(sexNum)) {
			if ("1".equals(sexNum)) {
				//男
				return "男";
			}else {
				//女
				return "女";
			}
		}
		return "";
	}
}
