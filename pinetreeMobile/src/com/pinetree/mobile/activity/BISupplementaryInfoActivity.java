package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BI_SupplementaryInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;

@ContentView(R.layout.activity_bi_supplementary_info)
public class BISupplementaryInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	@ViewInject(R.id.tv_second_title)
	private TextView tv_second_title;
	@ViewInject(R.id.tv_third_title0)
	private TextView tv_third_title0;
	@ViewInject(R.id.et_SupplementaryInfo)
	private EditText et_SupplementaryInfo;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		reportId = sharedPreferencesUtil.get("reportId");
		
		tv_third_title0.setOnLongClickListener(this);
		tv_second_title.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_SupplementaryInformation bi = dbUtils.findFirst(Selector.from(BI_SupplementaryInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				et_SupplementaryInfo.setText(bi.getSupplementaryInfo());
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		dbUtils.close();
	}

	@OnClick({ R.id.bt_save, R.id.bt_back })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:
			DbUtils dbUtils = DbUtils.create(this);
			BI_SupplementaryInformation bi = null;
			bi = new BI_SupplementaryInformation();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setSupplementaryInfo(et_SupplementaryInfo.getText().toString());
			try {
				if (dbUtils.findFirst(Selector.from(BI_SupplementaryInformation.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(BI_SupplementaryInformation.class).where("ReportId", "=", reportId));
					bi.setSupplementaryInfo(et_SupplementaryInfo.getText().toString());
					dbUtils.update(bi);
					Toast.makeText(this, getResources().getString(R.string.success_update), Toast.LENGTH_SHORT).show();
				} else {
					dbUtils.save(bi);
					Toast.makeText(this, getResources().getString(R.string.success_save), Toast.LENGTH_SHORT).show();
				}
			} catch (DbException e) {
				e.printStackTrace();
			}
			dbUtils.close();
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_third_title0:
			intent.putExtra("guid", "bi_bcxx");
			break;
		case R.id.tv_second_title:
			intent.putExtra("guid", "bi_bcxx");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
