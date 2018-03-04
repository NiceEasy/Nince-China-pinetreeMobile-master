package com.pinetree.mobile;



import android.app.Application;
import android.content.Context;

/**
 * 获取全局Context
 * @author Administrator
 * 
 */
public class GlobalContext extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		// 获取应用程序级别的Context
		context = getApplicationContext();
	}

	public static Context getContext() {
		return context;
	}
	
}
