package com.pinetree.mobile.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static void showToast(Context context, String str) {
		Toast.makeText(context, str, 0).show();
	}
}
