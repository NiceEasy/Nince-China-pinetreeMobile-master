package com.pinetree.mobile.receiver;

import com.pinetree.mobile.service.AutoSignInOutService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoSignInOutReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, AutoSignInOutService.class);
		context.startService(i);
	}

}
