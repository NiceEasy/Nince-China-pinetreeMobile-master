package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BI_GuardianAndEmergencyContactInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_guardian_info)
public class BIGuardianInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	@ViewInject(R.id.tv_GName)
	private TextView tv_GName;
	@ViewInject(R.id.tv_GAddress)
	private TextView tv_GAddress;
	@ViewInject(R.id.tv_GHomePhone)
	private TextView tv_GHomePhone;
	@ViewInject(R.id.tv_GMobile)
	private TextView tv_GMobile;
	@ViewInject(R.id.tv_GPostalCode)
	private TextView tv_GPostalCode;
	@ViewInject(R.id.tv_GEMail)
	private TextView tv_GEMail;
	@ViewInject(R.id.tv_GRelationship)
	private TextView tv_GRelationship;
	@ViewInject(R.id.tv_EName)
	private TextView tv_EName;
	@ViewInject(R.id.tv_EAddress)
	private TextView tv_EAddress;
	@ViewInject(R.id.tv_EHomePhone)
	private TextView tv_EHomePhone;
	@ViewInject(R.id.tv_EMobile)
	private TextView tv_EMobile;
	@ViewInject(R.id.tv_EPostalCode)
	private TextView tv_EPostalCode;
	@ViewInject(R.id.tv_EEMail)
	private TextView tv_EEMail;
	@ViewInject(R.id.tv_ERelationship)
	private TextView tv_ERelationship;
	@ViewInject(R.id.et_GName)
	private EditText etGName;
	@ViewInject(R.id.et_GAddress)
	private EditText et_GAddress;
	@ViewInject(R.id.et_GHomePhone)
	private EditText et_GHomePhone;
	@ViewInject(R.id.et_GMobile)
	private EditText et_GMobile;
	@ViewInject(R.id.et_GPostalCode)
	private EditText et_GPostalCode;
	@ViewInject(R.id.et_GEMail)
	private EditText et_GEMail;
	@ViewInject(R.id.rg_GRelationship)
	private RadioGroup rg_GRelationship;
	@ViewInject(R.id.et_EName)
	private EditText etEName;
	@ViewInject(R.id.et_EAddress)
	private EditText et_EAddress;
	@ViewInject(R.id.et_EHomePhone)
	private EditText et_EHomePhone;
	@ViewInject(R.id.et_EMobile)
	private EditText et_EMobile;
	@ViewInject(R.id.et_EPostalCode)
	private EditText et_EPostalCode;
	@ViewInject(R.id.et_EEMail)
	private EditText et_EEMail;
	@ViewInject(R.id.rg_ERelationship)
	private RadioGroup rg_ERelationship;
	@ResInject(id = R.array.array_ylrgx, type = ResType.StringArray)
	private String[] array_ylrgx;
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
		ViewUtils.inject(this);
		mContext = this;
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
		Tools.addRadioButtonH(mContext, array_ylrgx, rg_GRelationship);
		Tools.addRadioButtonH(mContext, array_ylrgx, rg_ERelationship);
		
		tv_GName.setOnLongClickListener(this);
		tv_GAddress.setOnLongClickListener(this);
		tv_GHomePhone.setOnLongClickListener(this);
		tv_GMobile.setOnLongClickListener(this);
		tv_GPostalCode.setOnLongClickListener(this);
		tv_GEMail.setOnLongClickListener(this);
		tv_GRelationship.setOnLongClickListener(this);
		tv_EName.setOnLongClickListener(this);
		tv_EAddress.setOnLongClickListener(this);
		tv_EHomePhone.setOnLongClickListener(this);
		tv_EMobile.setOnLongClickListener(this);
		tv_EPostalCode.setOnLongClickListener(this);
		tv_EEMail.setOnLongClickListener(this);
		tv_ERelationship.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_GuardianAndEmergencyContactInformation bi = dbUtils.findFirst(Selector.from(BI_GuardianAndEmergencyContactInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				etGName.setText(bi.getGName());
				rg_GRelationship.check(Integer.parseInt(bi.getGRelationship()));
				et_GAddress.setText(bi.getGAddress());
				et_GHomePhone.setText(bi.getGHomePhone());
				et_GMobile.setText(bi.getGMobile());
				et_GPostalCode.setText(bi.getGPostalCode());
				et_GEMail.setText(bi.getGEMail());
				etEName.setText(bi.getEName());
				rg_ERelationship.check(Integer.parseInt(bi.getERelationship()));
				et_EAddress.setText(bi.getEAddress());
				et_EHomePhone.setText(bi.getEHomePhone());
				et_EMobile.setText(bi.getEMobile());
				et_EPostalCode.setText(bi.getEPostalCode());
				et_EEMail.setText(bi.getEEMail());
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
				BI_GuardianAndEmergencyContactInformation bi = null;
				bi = new BI_GuardianAndEmergencyContactInformation();
				bi.setID(Integer.parseInt(reportId));
				bi.setReportId(reportId);
				bi.setGName(etGName.getText().toString());
				bi.setGRelationship(rg_GRelationship.getCheckedRadioButtonId() + "");
				bi.setGAddress(et_GAddress.getText().toString());
				bi.setGHomePhone(et_GHomePhone.getText().toString());
				bi.setGMobile(et_GMobile.getText().toString());
				bi.setGPostalCode(et_GPostalCode.getText().toString());
				bi.setGEMail(et_GEMail.getText().toString());
				bi.setEName(etEName.getText().toString());
				bi.setERelationship(rg_ERelationship.getCheckedRadioButtonId() + "");
				bi.setEAddress(et_EAddress.getText().toString());
				bi.setEHomePhone(et_EHomePhone.getText().toString());
				bi.setEMobile(et_EMobile.getText().toString());
				bi.setEPostalCode(et_EPostalCode.getText().toString());
				bi.setEEMail(et_EEMail.getText().toString());
				try {
					if (dbUtils.findFirst(Selector.from(BI_GuardianAndEmergencyContactInformation.class).where("ReportId", "=", reportId)) != null) {
						bi = dbUtils.findFirst(Selector.from(BI_GuardianAndEmergencyContactInformation.class).where("ReportId", "=", reportId));
						bi.setGName(etGName.getText().toString());
						bi.setGRelationship(rg_GRelationship.getCheckedRadioButtonId() + "");
						bi.setGAddress(et_GAddress.getText().toString());
						bi.setGHomePhone(et_GHomePhone.getText().toString());
						bi.setGMobile(et_GMobile.getText().toString());
						bi.setGPostalCode(et_GPostalCode.getText().toString());
						bi.setGEMail(et_GEMail.getText().toString());
						bi.setEName(etEName.getText().toString());
						bi.setERelationship(rg_ERelationship.getCheckedRadioButtonId() + "");
						bi.setEAddress(et_EAddress.getText().toString());
						bi.setEHomePhone(et_EHomePhone.getText().toString());
						bi.setEMobile(et_EMobile.getText().toString());
						bi.setEPostalCode(et_EPostalCode.getText().toString());
						bi.setEEMail(et_EEMail.getText().toString());
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
		if (!"".equals(et_GEMail.getText().toString().trim()) && !Tools.isEmailAdress(et_GEMail.getText().toString())) {
			Tools.showToast(mContext, getResources().getString(R.string.str_not_match_email));
			return false;
		}
		if (!"".equals(et_EEMail.getText().toString().trim()) && !Tools.isEmailAdress(et_EEMail.getText().toString())) {
			Tools.showToast(mContext, getResources().getString(R.string.str_not_match_email));
			return false;
		}
		return true;
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_GName:
		case R.id.tv_GAddress:
		case R.id.tv_GRelationship:
			intent.putExtra("guid", "bi_jhrxm");
			break;
		case R.id.tv_GHomePhone:
		case R.id.tv_GMobile:
			intent.putExtra("guid", "bi_jhrzzdh");
			break;
		case R.id.tv_GPostalCode:
			intent.putExtra("guid", "bi_jhryzbm");
			break;
		case R.id.tv_GEMail:
			intent.putExtra("guid", "bi_jhrdzyx");
			break;
		case R.id.tv_EName:
		case R.id.tv_EAddress:
		case R.id.tv_ERelationship:
			intent.putExtra("guid", "bi_lxrxm");
			break;
		case R.id.tv_EHomePhone:
		case R.id.tv_EMobile:
			intent.putExtra("guid", "bi_lxrzzdh");
			break;
		case R.id.tv_EPostalCode:
			intent.putExtra("guid", "bi_lxryzbm");
			break;
		case R.id.tv_EEMail:
			intent.putExtra("guid", "bi_lxrdzyx");
			break;
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
