package com.pinetree.mobile.utils;

public class DateTimeUtil {
	/**
	 * 截取服务端给的操蛋日期格式
	 */
	public static String getMSDateTiem(String dateTime){
		if (!"".equals(dateTime)&&null!=dateTime) {
			return dateTime.substring(0, 10)+ " "+dateTime.substring(11, 16);
		}
		return "";
	}
}
