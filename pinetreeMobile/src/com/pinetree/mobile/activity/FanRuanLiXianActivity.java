package com.pinetree.mobile.activity;

import com.pinetree.mobile.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FanRuanLiXianActivity extends Activity {

	private WebView lixianWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fanran_lixian);
		
		lixianWebView = (WebView) findViewById(R.id.lixian_webview );
		lixianWebView.getSettings().setJavaScriptEnabled(true);
		lixianWebView.loadUrl("http://www.finereporthelp.com:8889/app/ReportServer?reportlet=app/DetailedDrillA-phone.cpt&op=write");
	}
}
