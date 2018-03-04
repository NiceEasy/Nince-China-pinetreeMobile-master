package com.pinetree.mobile.activity;

import java.io.File;
import java.io.FileNotFoundException;

import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.CustomerSign;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.utils.SDPath;
import com.pinetree.mobile.utils.ToastUtils;
import com.pinetree.mobile.view.WriteView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class WritePadActivity extends Activity implements OnClickListener {
	private Button backBtn;
	private Button saveBtn;
	private Button clearBtn;
	private com.pinetree.mobile.view.WriteView activityWriteView;
	private String writePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dailog_writepad);

		writePath = getIntent().getStringExtra("writePath");
		backBtn = (Button) findViewById(R.id.back_btn);
		saveBtn = (Button) findViewById(R.id.save_btn);
		clearBtn = (Button) findViewById(R.id.clear_btn);
		activityWriteView = (WriteView) findViewById(R.id.activity_writeView);
		backBtn.setOnClickListener(this);
		saveBtn.setOnClickListener(this);
		clearBtn.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			finish();
			break;
		case R.id.clear_btn:
			activityWriteView.clear();
			break;
		case R.id.save_btn:
			try {
				if (!"".equals(writePath)) {
					File nowFile = new File(writePath);
					if (nowFile.exists()) {
						nowFile.delete();
					}
				}
				Log.v("TAG", "WritePadActivity writePath:" + writePath);
				activityWriteView.saveToFile(writePath);
				ToastUtils.showToast(WritePadActivity.this, "保存成功");
				Intent intent1 = new Intent();
				intent1.putExtra("Respond", "1");//保存成功
				setResult(RESULT_OK, intent1);
				finish();
			} catch (FileNotFoundException e) {
				ToastUtils.showToast(WritePadActivity.this, "签字保存失败，请重试");
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}

	}
}
