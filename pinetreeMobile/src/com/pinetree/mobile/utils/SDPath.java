package com.pinetree.mobile.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * 获取sd卡（优先）或手机内存路径路径
 * 
 * @author Administrator
 * 
 */
public class SDPath {

	public static File getSDPath(Context context) {
		File sdPath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			sdPath = context.getExternalCacheDir();
		} else {
			sdPath = context.getCacheDir();
		}
		return sdPath;
	}
}
