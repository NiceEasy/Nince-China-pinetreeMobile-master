package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.utils.SharedPreferencesUtil;

public class BaseActivity extends Activity implements MyBaseInterface {

	@ViewInject(R.id.bt_back)
	protected Button bt_back;
	@ViewInject(R.id.bt_save)
	protected Button bt_save;
	@ViewInject(R.id.tv_title)
	protected TextView tv_title;
	@ViewInject(R.id.tv_second_title)
	protected TextView tv_second_title;
	protected Context mContext;
	protected String reportId = "";
	protected SharedPreferencesUtil sharedPreferencesUtil;
	protected LayoutInflater inflater;
	@ViewInject(R.id.message_content_fl)
	protected FrameLayout frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_base);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		mContext = this;
		inflater = getLayoutInflater();
		frameLayout = (FrameLayout) findViewById(R.id.message_content_fl);
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
	}

	@Override
	public void initData() {
		

	}

	@OnClick({ R.id.bt_save, R.id.bt_back })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:

			break;

		default:
			break;
		}
	}

}
