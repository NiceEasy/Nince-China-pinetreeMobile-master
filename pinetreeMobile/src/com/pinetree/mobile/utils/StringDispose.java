package com.pinetree.mobile.utils;

/**
 * String
 * @author Administrator
 *
 */
public class StringDispose {

	public static String getNotNullStr(Object str) {
		if (str!=null) {
			return str.toString();
		}
		return "";
	}
	
}
