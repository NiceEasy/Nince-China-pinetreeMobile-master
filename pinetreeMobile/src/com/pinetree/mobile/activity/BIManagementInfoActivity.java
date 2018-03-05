package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BI_ManagementInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_management_info)
public class BIManagementInfoActivity extends Activity implements MyBaseInterface {
	@ViewInject(R.id.et_Name)
	private EditText et_Name;
	@ViewInject(R.id.et_Organ)
	private EditText et_Organ;
	@ViewInject(R.id.et_Address)
	private EditText et_Address;
	@ViewInject(R.id.et_HomePhone)
	private EditText et_HomePhone;
	@ViewInject(R.id.et_Mobile)
	private EditText et_Mobile;
	@ViewInject(R.id.et_PostalCode)
	private EditText et_PostalCode;
	@ViewInject(R.id.et_EMail)
	private EditText et_EMail;
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
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_ManagementInformation bi = dbUtils.findFirst(Selector.from(BI_ManagementInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				et_Name.setText(bi.getName());
				et_Organ.setText(bi.getOrgan());
				et_Address.setText(bi.getAddress());
				et_HomePhone.setText(bi.getHomePhone());
				et_Mobile.setText(bi.getMobile());
				et_PostalCode.setText(bi.getPostalCode());
				et_EMail.setText(bi.getEMail());
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
			if (isMatch()) {
				DbUtils dbUtils = DbUtils.create(this);
				BI_ManagementInformation bi = null;
				bi = new BI_ManagementInformation();
				bi.setID(Integer.parseInt(reportId));
				bi.setReportId(reportId);
				bi.setName(et_Name.getText().toString());
				bi.setOrgan(et_Organ.getText().toString());
				bi.setAddress(et_Address.getText().toString());
				bi.setHomePhone(et_HomePhone.getText().toString());
				bi.setMobile(et_Mobile.getText().toString());
				bi.setPostalCode(et_PostalCode.getText().toString());
				bi.setEMail(et_EMail.getText().toString());
				try {
					if (dbUtils.findFirst(Selector.from(BI_ManagementInformation.class).where("ReportId", "=", reportId)) != null) {
						bi = dbUtils.findFirst(Selector.from(BI_ManagementInformation.class).where("ReportId", "=", reportId));
						bi.setName(et_Name.getText().toString());
						bi.setOrgan(et_Organ.getText().toString());
						bi.setAddress(et_Address.getText().toString());
						bi.setHomePhone(et_HomePhone.getText().toString());
						bi.setMobile(et_Mobile.getText().toString());
						bi.setPostalCode(et_PostalCode.getText().toString());
						bi.setEMail(et_EMail.getText().toString());
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
			}
			break;

		default:
			break;
		}

	}

	private boolean isMatch() {
		if (!"".equals(et_EMail.getText().toString().trim()) && !Tools.isEmailAdress(et_EMail.getText().toString())) {
			Tools.showToast(mContext, getResources().getString(R.string.str_not_match_email));
			return false;
		}
		return true;
	}
}
