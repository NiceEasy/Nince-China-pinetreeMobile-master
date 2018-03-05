package com.pinetree.mobile.activity;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.pinetree.mobile.bean.BI_IDInformation;
import com.pinetree.mobile.bean.BI_PersonalInformation;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.utils.MyDatePickerDialog;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_persional_info)
public class BIPersonnalInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	@ViewInject(R.id.tv_Sex)
	private TextView tv_Sex;
	@ViewInject(R.id.tv_BirthDate)
	private TextView tv_BirthDate;
	@ViewInject(R.id.tv_PlaceofOrigin)
	private TextView tv_PlaceofOrigin;
	@ViewInject(R.id.tv_CensusRegisterAddress)
	private TextView tv_CensusRegisterAddress;
	@ViewInject(R.id.tv_LiveAddress)
	private TextView tv_LiveAddress;
	@ViewInject(R.id.tv_HomePhone)
	private TextView tv_HomePhone;
	@ViewInject(R.id.tv_Mobile)
	private TextView tv_Mobile;
	@ViewInject(R.id.tv_PostalCode)
	private TextView tv_PostalCode;
	@ViewInject(R.id.tv_EMail)
	private TextView tv_EMail;
	@ViewInject(R.id.tv_Nation)
	private TextView tv_Nation;
	@ViewInject(R.id.tv_ReligiousBelief)
	private TextView tv_ReligiousBelief;
	@ViewInject(R.id.tv_MaritalStatus)
	private TextView tv_MaritalStatus;
	@ViewInject(R.id.tv_CulturalDegree)
	private TextView tv_CulturalDegree;
	@ViewInject(R.id.tv_UsingLanguage)
	private TextView tv_UsingLanguage;
	@ViewInject(R.id.rg_Sex)
	private RadioGroup rg_Sex;
	@ViewInject(R.id.et_BirthDate)
	private EditText et_BirthDate;
	@ViewInject(R.id.et_PlaceofOrigin)
	private EditText et_PlaceofOrigin;
	@ViewInject(R.id.et_CensusRegisterAddress)
	private EditText et_CensusRegisterAddress;
	@ViewInject(R.id.et_LiveAddress)
	private EditText et_LiveAddress;
	@ViewInject(R.id.et_HomePhone)
	private EditText et_HomePhone;
	@ViewInject(R.id.et_Mobile)
	private EditText et_Mobile;
	@ViewInject(R.id.et_PostalCode)
	private EditText et_PostalCode;
	@ViewInject(R.id.et_EMail)
	private EditText et_EMail;
	@ViewInject(R.id.sp_Nation)
	private Spinner sp_Nation;
	@ViewInject(R.id.sp_ReligiousBelief)
	private Spinner sp_ReligiousBelief;
	@ViewInject(R.id.rg_MaritalStatus)
	private RadioGroup rg_MaritalStatus;
	@ViewInject(R.id.rg_CulturalDegree)
	private RadioGroup rg_CulturalDegree;
	@ViewInject(R.id.rg_UsingLanguage)
	private RadioGroup rg_UsingLanguage;
	@ResInject(id = R.array.array_xb, type = ResType.StringArray)
	private String[] array_xb;
	@ResInject(id = R.array.array_hyzk, type = ResType.StringArray)
	private String[] array_hyzk;
	@ResInject(id = R.array.array_whcd, type = ResType.StringArray)
	private String[] array_whcd;
	@ResInject(id = R.array.array_syyy, type = ResType.StringArray)
	private String[] array_syyy;
	@ResInject(id = R.array.array_mz, type = ResType.StringArray)
	private String[] array_mz;
	@ResInject(id = R.array.array_zjxy, type = ResType.StringArray)
	private String[] array_zjxy;
	private ArrayAdapter<?> adapter_mz, adapter_zjxy;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private Customer customer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		customer = (Customer) getIntent().getExtras().getSerializable("customer");
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		reportId = sharedPreferencesUtil.get("reportId");
		Tools.addRadioButtonH(mContext, array_xb, rg_Sex);
		Tools.addRadioButtonV(mContext, array_hyzk, rg_MaritalStatus);
		Tools.addRadioButtonV(mContext, array_whcd, rg_CulturalDegree);
		Tools.addRadioButtonH(mContext, array_syyy, rg_UsingLanguage);
		adapter_mz = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_mz);
		adapter_mz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Nation.setAdapter(adapter_mz);
		sp_Nation.setSelection(0);
		adapter_zjxy = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_zjxy);
		adapter_zjxy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_ReligiousBelief.setAdapter(adapter_zjxy);
		et_BirthDate.setText(Tools.getCurrDate());
		et_BirthDate.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					return false;

				case MotionEvent.ACTION_UP:
					switch (v.getId()) {
					case R.id.et_BirthDate:
						Calendar c = Calendar.getInstance();
						new MyDatePickerDialog(mContext, et_BirthDate, c).showMyDatePickerDialog();
						break;
					default:
						break;
					}
					return true;
				}
				return false;
			}
		});
		tv_Sex.setOnLongClickListener(this);
		tv_PlaceofOrigin.setOnLongClickListener(this);
		tv_CensusRegisterAddress.setOnLongClickListener(this);
		tv_LiveAddress.setOnLongClickListener(this);
		tv_HomePhone.setOnLongClickListener(this);
		tv_Mobile.setOnLongClickListener(this);
		tv_PostalCode.setOnLongClickListener(this);
		tv_EMail.setOnLongClickListener(this);
		tv_Nation.setOnLongClickListener(this);
		tv_ReligiousBelief.setOnLongClickListener(this);
		tv_MaritalStatus.setOnLongClickListener(this);
		tv_CulturalDegree.setOnLongClickListener(this);
		tv_UsingLanguage.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_PersonalInformation bi = dbUtils.findFirst(Selector.from(BI_PersonalInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				Log.v("TAG", "initData  bi.getSex():" + bi.getSex());
				rg_Sex.check(Integer.parseInt(bi.getSex()));
				et_BirthDate.setText(bi.getBirthDate());
				if (bi.getNation() != null&&!bi.getNation().equals(""))
					sp_Nation.setSelection(Integer.parseInt(bi.getNation()), true);
				if (bi.getReligiousBelief() != null&&!bi.getReligiousBelief().equals(""))
					sp_ReligiousBelief.setSelection(Integer.parseInt(bi.getReligiousBelief()), true);
				if (bi.getMaritalStatus() != null&&!bi.getMaritalStatus().equals(""))
					rg_MaritalStatus.check(Integer.parseInt(bi.getMaritalStatus()));
				if (bi.getCulturalDegree() != null&&!bi.getCulturalDegree().equals(""))
					rg_CulturalDegree.check(Integer.parseInt(bi.getCulturalDegree()));
				et_PlaceofOrigin.setText(bi.getPlaceofOrigin());
				if (bi.getUsingLanguage() != null&&!bi.getUsingLanguage().equals(""))
					rg_UsingLanguage.check(Integer.parseInt(bi.getUsingLanguage()));
				et_CensusRegisterAddress.setText(bi.getCensusRegisterAddress());
				et_LiveAddress.setText(bi.getLiveAddress());
				et_HomePhone.setText(bi.getHomePhone());
				et_Mobile.setText(bi.getMobile());
				et_PostalCode.setText(bi.getPostalCode());
				et_EMail.setText(bi.getEMail());
				
			}
			BI_IDInformation bi0 = dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
			if (bi0 != null && bi0.getIDNumber() != null && !bi0.getIDNumber().equals("")) {
				String birthday = Tools.getBirthdayFromIDNumber(bi0.getIDNumber());
				if (!birthday.equals("")) {
					et_BirthDate.setText(birthday);
				}
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
//			if (isMatch()) {
				DbUtils dbUtils = DbUtils.create(this);
				BI_PersonalInformation bi = null;
				bi = new BI_PersonalInformation();
				bi.setID(Integer.parseInt(reportId));
				bi.setReportId(reportId);
				bi.setSex(rg_Sex.getCheckedRadioButtonId() + "");
				Log.v("TAG", "bt_save  rg_Sex.getCheckedRadioButtonId() + :" + rg_Sex.getCheckedRadioButtonId() + "");
				bi.setBirthDate(et_BirthDate.getText().toString());
				bi.setNation(sp_Nation.getSelectedItemPosition() + "");
				bi.setReligiousBelief(sp_ReligiousBelief.getSelectedItemPosition() + "");
				bi.setMaritalStatus(rg_MaritalStatus.getCheckedRadioButtonId() + "");
				bi.setCulturalDegree(rg_CulturalDegree.getCheckedRadioButtonId() + "");
				bi.setPlaceofOrigin(et_PlaceofOrigin.getText().toString());
				bi.setUsingLanguage(rg_UsingLanguage.getCheckedRadioButtonId() + "");
				bi.setCensusRegisterAddress(et_CensusRegisterAddress.getText().toString());
				bi.setLiveAddress(et_LiveAddress.getText().toString());
				bi.setHomePhone(et_HomePhone.getText().toString());
				bi.setMobile(et_Mobile.getText().toString());
				bi.setPostalCode(et_PostalCode.getText().toString());
				bi.setEMail(et_EMail.getText().toString());
				bi.setWhetherNew("0");
				try {
					if (dbUtils.findFirst(Selector.from(BI_PersonalInformation.class).where("ReportId", "=", reportId)) != null) {
						bi = dbUtils.findFirst(Selector.from(BI_PersonalInformation.class).where("ReportId", "=", reportId));
						bi.setSex(rg_Sex.getCheckedRadioButtonId() + "");
						bi.setBirthDate(et_BirthDate.getText().toString());
						bi.setNation(sp_Nation.getSelectedItemPosition() + "");
						bi.setReligiousBelief(sp_ReligiousBelief.getSelectedItemPosition() + "");
						bi.setMaritalStatus(rg_MaritalStatus.getCheckedRadioButtonId() + "");
						bi.setCulturalDegree(rg_CulturalDegree.getCheckedRadioButtonId() + "");
						bi.setPlaceofOrigin(et_PlaceofOrigin.getText().toString());
						bi.setUsingLanguage(rg_UsingLanguage.getCheckedRadioButtonId() + "");
						bi.setCensusRegisterAddress(et_CensusRegisterAddress.getText().toString());
						bi.setLiveAddress(et_LiveAddress.getText().toString());
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
//			}
			break;

		default:
			break;
		}

	}

	private boolean isMatch() {
		if (!"".equals(et_HomePhone.getText().toString().trim())){
			return true;
		} else {
			Tools.showToast(mContext, getResources().getString(R.string.str_homephoto_noempty));
		}
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_Sex:
			intent.putExtra("guid", "bi_xb");
			break;
		case R.id.tv_PlaceofOrigin:
			intent.putExtra("guid", "bi_jg");
			break;
		case R.id.tv_CensusRegisterAddress:
			intent.putExtra("guid", "bi_hjdz");
			break;
		case R.id.tv_LiveAddress:
			intent.putExtra("guid", "bi_jzdz");
			break;
		case R.id.tv_HomePhone:
			intent.putExtra("guid", "bi_zzdh");
			break;
		case R.id.tv_Mobile:
			intent.putExtra("guid", "bi_zzdh");
			break;
		case R.id.tv_PostalCode:
			intent.putExtra("guid", "bi_yzbm");
			break;
		case R.id.tv_EMail:
			intent.putExtra("guid", "bi_dzyx");
			break;
		case R.id.tv_Nation:
			intent.putExtra("guid", "bi_mz");
			break;
		case R.id.tv_ReligiousBelief:
			intent.putExtra("guid", "bi_zjxy");
			break;
		case R.id.tv_MaritalStatus:
			intent.putExtra("guid", "bi_hyzk");
			break;
		case R.id.tv_CulturalDegree:
			intent.putExtra("guid", "bi_whcd");
			break;
		case R.id.tv_UsingLanguage:
			intent.putExtra("guid", "bi_syyy");
			break;
		
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
