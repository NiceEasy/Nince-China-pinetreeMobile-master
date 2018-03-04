package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.pinetree.mobile.bean.BI_HomePrimaryCargiversInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_home_primary_info)
public class BIHomePrimaryCargiversInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	
	@ViewInject(R.id.tv_fourth_title0)
	private TextView tv_fourth_title0;
	@ViewInject(R.id.tv_fourth_title1)
	private TextView tv_fourth_title1;
	@ViewInject(R.id.tv_fourth_title2)
	private TextView tv_fourth_title2;
	@ViewInject(R.id.tv_fourth_title3)
	private TextView tv_fourth_title3;
	@ViewInject(R.id.tv_fourth_title4)
	private TextView tv_fourth_title4;
	@ViewInject(R.id.tv_fourth_title5)
	private TextView tv_fourth_title5;
	@ViewInject(R.id.tv_fourth_title6)
	private TextView tv_fourth_title6;
	@ViewInject(R.id.tv_fourth_title7)
	private TextView tv_fourth_title7;
	@ViewInject(R.id.tv_fourth_title8)
	private TextView tv_fourth_title8;
	@ViewInject(R.id.tv_fourth_title9)
	private TextView tv_fourth_title9;
	@ViewInject(R.id.tv_fourth_title10)
	private TextView tv_fourth_title10;
	@ViewInject(R.id.tv_fourth_title11)
	private TextView tv_fourth_title11;
	@ViewInject(R.id.tv_fourth_title12)
	private TextView tv_fourth_title12;
	@ViewInject(R.id.et_Name)
	private EditText et_Name;
	@ViewInject(R.id.et_Age)
	private EditText et_Age;
	@ViewInject(R.id.rg_Sex)
	private RadioGroup rg_Sex;
	@ViewInject(R.id.rg_Relationship)
	private RadioGroup rg_Relationship;
	@ViewInject(R.id.rg_LiveWithOldPeople)
	private RadioGroup rg_LiveWithOldPeople;
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
	@ViewInject(R.id.cb_HomeCargiversServices0)
	private CheckBox cb_HomeCargiversServices0;
	@ViewInject(R.id.cb_HomeCargiversServices1)
	private CheckBox cb_HomeCargiversServices1;
	@ViewInject(R.id.cb_HomeCargiversServices2)
	private CheckBox cb_HomeCargiversServices2;
	@ViewInject(R.id.et_WorkingDays0)
	private EditText et_WorkingDays0;
	@ViewInject(R.id.et_WorkingDays1)
	private EditText et_WorkingDays1;
	@ViewInject(R.id.et_WorkingDays2)
	private EditText et_WorkingDays2;
	@ViewInject(R.id.et_Weekend0)
	private EditText et_Weekend0;
	@ViewInject(R.id.et_Weekend1)
	private EditText et_Weekend1;
	@ViewInject(R.id.et_Weekend2)
	private EditText et_Weekend2;
	@ViewInject(R.id.cb_HomeCaregiversStatus0)
	private CheckBox cb_HomeCaregiversStatus0;
	@ViewInject(R.id.cb_HomeCaregiversStatus1)
	private CheckBox cb_HomeCaregiversStatus1;
	@ViewInject(R.id.cb_HomeCaregiversStatus2)
	private CheckBox cb_HomeCaregiversStatus2;
	@ResInject(id = R.array.array_jtzhztgdzhnr, type = ResType.StringArray)
	private String[] array_jtzhztgdzhnr;
	@ResInject(id = R.array.array_jtzhryzt, type = ResType.StringArray)
	private String[] array_jtzhryzt;
	@ResInject(id = R.array.array_xb, type = ResType.StringArray)
	private String[] array_xb;
	@ResInject(id = R.array.array_4ylrgx, type = ResType.StringArray)
	private String[] array_4ylrgx;
	@ResInject(id = R.array.array_ylrtz, type = ResType.StringArray)
	private String[] array_ylrtz;
	private List<CheckBox> listHomeCargiversServices;
	private List<CheckBox> listHomeCaregiversStatus;
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
		Tools.addRadioButtonH(mContext, array_xb, rg_Sex);
		Tools.addRadioButtonV(mContext, array_4ylrgx, rg_Relationship);
		Tools.addRadioButtonH(mContext, array_ylrtz, rg_LiveWithOldPeople);
		cb_HomeCargiversServices0.setText(array_jtzhztgdzhnr[0]);
		cb_HomeCargiversServices1.setText(array_jtzhztgdzhnr[1]);
		cb_HomeCargiversServices2.setText(array_jtzhztgdzhnr[2]);
		cb_HomeCaregiversStatus0.setText(array_jtzhryzt[0]);
		cb_HomeCaregiversStatus1.setText(array_jtzhryzt[1]);
		cb_HomeCaregiversStatus2.setText(array_jtzhryzt[2]);
		listHomeCargiversServices = new ArrayList<CheckBox>();
		listHomeCargiversServices.add(cb_HomeCargiversServices0);
		listHomeCargiversServices.add(cb_HomeCargiversServices1);
		listHomeCargiversServices.add(cb_HomeCargiversServices2);
		listHomeCaregiversStatus = new ArrayList<CheckBox>();
		listHomeCaregiversStatus.add(cb_HomeCaregiversStatus0);
		listHomeCaregiversStatus.add(cb_HomeCaregiversStatus1);
		listHomeCaregiversStatus.add(cb_HomeCaregiversStatus2);
		
		tv_fourth_title0.setOnLongClickListener(this);
		tv_fourth_title1.setOnLongClickListener(this);
		tv_fourth_title2.setOnLongClickListener(this);
		tv_fourth_title3.setOnLongClickListener(this);
		tv_fourth_title4.setOnLongClickListener(this);
		tv_fourth_title5.setOnLongClickListener(this);
		tv_fourth_title6.setOnLongClickListener(this);
		tv_fourth_title7.setOnLongClickListener(this);
		tv_fourth_title8.setOnLongClickListener(this);
		tv_fourth_title9.setOnLongClickListener(this);
		tv_fourth_title10.setOnLongClickListener(this);
		tv_fourth_title11.setOnLongClickListener(this);
		tv_fourth_title12.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_HomePrimaryCargiversInformation bi = dbUtils.findFirst(Selector.from(BI_HomePrimaryCargiversInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_Sex.check(Integer.parseInt(bi.getSex()));
				et_Name.setText(bi.getName());
				et_Age.setText(bi.getAge());
				rg_Relationship.check(Integer.parseInt(bi.getRelationship()));
				rg_LiveWithOldPeople.check(Integer.parseInt(bi.getLiveWithOldPeople()));
				et_Address.setText(bi.getAddress());
				et_HomePhone.setText(bi.getHomePhone());
				et_Mobile.setText(bi.getMobile());
				et_PostalCode.setText(bi.getPostalCode());
				et_EMail.setText(bi.getEMail());
				if (!bi.getHomeCargiversServices().equals("")) {
					String[] array_temp = bi.getHomeCargiversServices().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listHomeCargiversServices.get(n).setChecked(true);
						}
					}
				}
				if (!bi.getHomeCaregiversStatus().equals("")) {
					String[] array_temp = bi.getHomeCaregiversStatus().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listHomeCaregiversStatus.get(n).setChecked(true);
						}
					}
				}
				if (bi.getWorkingDays().contains("-")) {
					String[] array_t = bi.getWorkingDays().split("-");
					if (array_t.length > 2) {
						et_WorkingDays2.setText(array_t[0]);
						et_WorkingDays0.setText(array_t[1]);
						et_WorkingDays1.setText(array_t[2]);
					}
					if (array_t.length > 1) {
						et_WorkingDays2.setText(array_t[0]);
						et_WorkingDays0.setText(array_t[1]);
						
					} else if (array_t.length > 0) {
						et_WorkingDays2.setText(array_t[0]);
					}

				}
				if (bi.getWeekend().contains("-")) {
					String[] array_t = bi.getWeekend().split("-");
					if (array_t.length > 2) {
						et_Weekend2.setText(array_t[0]);
						et_Weekend0.setText(array_t[1]);
						et_Weekend1.setText(array_t[2]);
					} 
					else if (array_t.length > 1) {
						et_Weekend2.setText(array_t[0]);
						et_Weekend0.setText(array_t[1]);
						
					} else if (array_t.length > 0) {
						et_Weekend2.setText(array_t[0]);
					}
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
			if (isMatch()) {
				DbUtils dbUtils = DbUtils.create(this);
				BI_HomePrimaryCargiversInformation bi = null;
				bi = new BI_HomePrimaryCargiversInformation();
				bi.setID(Integer.parseInt(reportId));
				bi.setReportId(reportId);
				bi.setName(et_Name.getText().toString());
				bi.setAge(et_Age.getText().toString());
				bi.setAddress(et_Address.getText().toString());
				bi.setHomePhone(et_HomePhone.getText().toString());
				bi.setMobile(et_Mobile.getText().toString());
				bi.setPostalCode(et_PostalCode.getText().toString());
				bi.setEMail(et_EMail.getText().toString());
				bi.setSex(rg_Sex.getCheckedRadioButtonId() + "");
				bi.setRelationship(rg_Relationship.getCheckedRadioButtonId() + "");
				bi.setLiveWithOldPeople(rg_LiveWithOldPeople.getCheckedRadioButtonId() + "");
				String str0 = et_WorkingDays0.getText().toString();
				String str1 = et_WorkingDays1.getText().toString();
				String str4 = et_WorkingDays2.getText().toString();
				String str2 = et_Weekend0.getText().toString();
				String str3 = et_Weekend1.getText().toString();
				String str5 = et_Weekend2.getText().toString();

				if (str0.equals("")) {
					str0 = "0";
				}
				if (str1.equals("")) {
					str1 = "0";
				}
				if (str2.equals("")) {
					str2 = "0";
				}
				if (str3.equals("")) {
					str3 = "0";
				}
				bi.setWorkingDays(str4 + "-" +str0 + "-" + str1);
				bi.setWeekend(str5 + "-" + str2 + "-" + str3);

				bi.setHomeCargiversServices(Tools.getCheckBoxStatus(listHomeCargiversServices));
				bi.setHomeCaregiversStatus(Tools.getCheckBoxStatus(listHomeCaregiversStatus));
				try {
					if (dbUtils.findFirst(Selector.from(BI_HomePrimaryCargiversInformation.class).where("ReportId", "=", reportId)) != null) {
						bi = dbUtils.findFirst(Selector.from(BI_HomePrimaryCargiversInformation.class).where("ReportId", "=", reportId));
						bi.setName(et_Name.getText().toString());
						bi.setAge(et_Age.getText().toString());
						bi.setAddress(et_Address.getText().toString());
						bi.setHomePhone(et_HomePhone.getText().toString());
						bi.setMobile(et_Mobile.getText().toString());
						bi.setPostalCode(et_PostalCode.getText().toString());
						bi.setEMail(et_EMail.getText().toString());
						bi.setSex(rg_Sex.getCheckedRadioButtonId() + "");
						bi.setRelationship(rg_Relationship.getCheckedRadioButtonId() + "");
						bi.setLiveWithOldPeople(rg_LiveWithOldPeople.getCheckedRadioButtonId() + "");
						bi.setWorkingDays(str4 + "-" + str0 + "-" + str1);
						bi.setWeekend(str5 + "-" + str2 + "-" + str3);

						bi.setHomeCargiversServices(Tools.getCheckBoxStatus(listHomeCargiversServices));
						bi.setHomeCaregiversStatus(Tools.getCheckBoxStatus(listHomeCaregiversStatus));
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

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_fourth_title0:
		case R.id.tv_fourth_title1:
		case R.id.tv_fourth_title2:
		case R.id.tv_fourth_title3:
		case R.id.tv_fourth_title4:
		case R.id.tv_fourth_title5:
		case R.id.tv_fourth_title6:
			intent.putExtra("guid", "bi_zhzxm");
			break;
		case R.id.tv_fourth_title7:
		case R.id.tv_fourth_title8:
			intent.putExtra("guid", "bi_zhzzzdh");
			break;
		case R.id.tv_fourth_title9:
			intent.putExtra("guid", "bi_zhzdzyx");
			break;
		case R.id.tv_fourth_title10:
			intent.putExtra("guid", "bi_zhznr");
			break;
		case R.id.tv_fourth_title11:
			intent.putExtra("guid", "bi_zhxss");
			break;
		case R.id.tv_fourth_title12:
			intent.putExtra("guid", "bi_zhryzt");
			break;
		
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
