package com.pinetree.mobile.activity;

import com.pinetree.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AccountmManageActivity extends Activity implements OnClickListener {
	private ImageView ivAccountManageBack;
	private LinearLayout otherAccountLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_account_manage);

		ivAccountManageBack = (ImageView) findViewById(R.id.iv_accountManage_back);
		ivAccountManageBack.setOnClickListener(this);
		otherAccountLogin = (LinearLayout) findViewById(R.id.other_account_login);
		otherAccountLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_accountManage_back:
			finish();
			break;
		case R.id.other_account_login://使用其它账号登陆
			//删除保存用户账号和密码的配置文件，跳转到主页从新登陆，销户所有的activity
			//哈哈，其实不用删除，key一样，自己给覆盖啦
			Intent intent=new Intent(AccountmManageActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}

}
