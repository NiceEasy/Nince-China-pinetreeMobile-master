package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {
	@ViewInject(R.id.button)
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewUtils.inject(this);
	}

	@OnClick({ R.id.button })
	public void clickMethod(View v) {
		Toast.makeText(this, "you clicked button!", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(
				"initData",
				"{\"age\":87,\"beginTime\":\"2015-09-18T09:00:00\",\"custAddress\":\"�������ֵ�5-4-203\",\"custID\":\"57b47b3971c0411c95747ce02338d2fc\",\"custName\":\"������\",\"custPhone\":\"64209630\",\"empNum\":\"BJ00176\",\"employeeID\":\"d0245cd714f9432e8ff94eb39e14ea0f\",\"employeeName\":\"���\",\"endTime\":\"2015-09-18T10:00:00\",\"id\":\"e38e0eba4d724d2a8626aeb91da7f476\",\"lat\":\"39.928353\",\"lng\":\"116.416357\",\"sex\":\"1\",\"projectId\":\"\"}");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
