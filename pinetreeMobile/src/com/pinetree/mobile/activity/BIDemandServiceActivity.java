package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BI_DemandService;
import com.pinetree.mobile.utils.MultiLineRadioGroup;
import com.pinetree.mobile.utils.MultiLineRadioGroup.OnCheckedChangedListener;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

public class BIDemandServiceActivity extends Activity implements
		MyBaseInterface, OnCheckedChangeListener,OnLongClickListener {
	int[] id = { R.id.activity_bi_demand_service_part0,
			R.id.activity_bi_demand_service_part1,
			R.id.activity_bi_demand_service_part2,
			R.id.activity_bi_demand_service_part3,
			R.id.activity_bi_demand_service_part4,
			R.id.activity_bi_demand_service_part5,
			R.id.activity_bi_demand_service_part6,
			R.id.activity_bi_demand_service_part7,
			R.id.activity_bi_demand_service_part8,
			R.id.activity_bi_demand_service_part9,
			R.id.activity_bi_demand_service_part10,
			R.id.activity_bi_demand_service_part11,
			R.id.activity_bi_demand_service_part12,
			R.id.activity_bi_demand_service_part13,
			R.id.activity_bi_demand_service_part14,
			R.id.activity_bi_demand_service_part15,
			R.id.activity_bi_demand_service_part16,
			R.id.activity_bi_demand_service_part17,
			R.id.activity_bi_demand_service_part18,
			R.id.activity_bi_demand_service_part20,
			R.id.activity_bi_demand_service_part21,
			R.id.activity_bi_demand_service_part22,
			R.id.activity_bi_demand_service_part23,
			R.id.activity_bi_demand_service_part24,
			R.id.activity_bi_demand_service_part25,
			R.id.activity_bi_demand_service_part26,
			R.id.activity_bi_demand_service_part27,
			R.id.activity_bi_demand_service_part28,
			R.id.activity_bi_demand_service_part29,
			R.id.activity_bi_demand_service_part30,
			R.id.activity_bi_demand_service_part31,
			R.id.activity_bi_demand_service_part32,
			R.id.activity_bi_demand_service_part33,
			R.id.activity_bi_demand_service_part34,
			R.id.activity_bi_demand_service_part35,
			R.id.activity_bi_demand_service_part36,
			R.id.activity_bi_demand_service_part37,
			R.id.activity_bi_demand_service_part38,
			R.id.activity_bi_demand_service_part39,
			R.id.activity_bi_demand_service_part40,
			R.id.activity_bi_demand_service_part41,
			R.id.activity_bi_demand_service_part42,
			R.id.activity_bi_demand_service_part43,
	};

	@ViewInject(R.id.tv_second_title)
	private TextView tv_second_title;
	@ViewInject(R.id.tv_third_title0)
	private TextView tv_third_title0;
	@ViewInject(R.id.tv_third_title1)
	private TextView tv_third_title1;
	@ViewInject(R.id.tv_third_title2)
	private TextView tv_third_title2;
	@ViewInject(R.id.tv_third_title3)
	private TextView tv_third_title3;
	@ViewInject(R.id.tv_third_title4)
	private TextView tv_third_title4;
	@ViewInject(R.id.tv_third_title5)
	private TextView tv_third_title5;
	@ViewInject(R.id.tv_third_title6)
	private TextView tv_third_title6;
	@ViewInject(R.id.tv_third_title7)
	private TextView tv_third_title7;
	@ViewInject(R.id.tv_third_title8)
	private TextView tv_third_title8;
	@ViewInject(R.id.tv_third_title9)
	private TextView tv_third_title9;
	@ViewInject(R.id.cb_LivingEnvironment0)
	private CheckBox cb_LivingEnvironment0;
	@ViewInject(R.id.cb_LivingEnvironment1)
	private CheckBox cb_LivingEnvironment1;
	@ViewInject(R.id.cb_LivingEnvironment2)
	private CheckBox cb_LivingEnvironment2;
	@ViewInject(R.id.cb_LivingEnvironment3)
	private CheckBox cb_LivingEnvironment3;
	@ViewInject(R.id.cb_LivingEnvironment4)
	private CheckBox cb_LivingEnvironment4;
	@ViewInject(R.id.cb_LivingEnvironment5)
	private CheckBox cb_LivingEnvironment5;
	@ViewInject(R.id.cb_LivingEnvironment6)
	private CheckBox cb_LivingEnvironment6;
	@ViewInject(R.id.cb_LivingEnvironment7)
	private CheckBox cb_LivingEnvironment7;
	@ViewInject(R.id.cb_LivingEnvironment8)
	private CheckBox cb_LivingEnvironment8;
	@ViewInject(R.id.cb_LivingEnvironment9)
	private CheckBox cb_LivingEnvironment9;
	@ViewInject(R.id.cb_LivingEnvironment10)
	private CheckBox cb_LivingEnvironment10;
	@ViewInject(R.id.cb_LivingEnvironment11)
	private CheckBox cb_LivingEnvironment11;
	@ViewInject(R.id.cb_LivingEnvironment12)
	private CheckBox cb_LivingEnvironment12;
	@ViewInject(R.id.cb_LivingEnvironment13)
	private CheckBox cb_LivingEnvironment13;
	@ViewInject(R.id.cb_LivingEnvironment14)
	private CheckBox cb_LivingEnvironment14;
	@ViewInject(R.id.et_others0)
	private EditText et_others0;
	@ViewInject(R.id.et_others1)
	private EditText et_others1;
	@ViewInject(R.id.et_others2)
	private EditText et_others2;
	@ViewInject(R.id.et_others3)
	private EditText et_others3;
	@ViewInject(R.id.et_others4)
	private EditText et_others4;
	@ViewInject(R.id.et_others5)
	private EditText et_others5;
	@ViewInject(R.id.et_others6)
	private EditText et_others6;
	@ViewInject(R.id.et_others7)
	private EditText et_others7;
	@ViewInject(R.id.et_others8)
	private EditText et_others8;
	@ViewInject(R.id.et_others9)
	private EditText et_others9;
	@ViewInject(R.id.et_others10)
	private EditText et_others10;
	@ViewInject(R.id.et_others11)
	private EditText et_others11;
	@ViewInject(R.id.et_others12)
	private EditText et_others12;
	@ViewInject(R.id.et_others13)
	private EditText et_others13;
	@ViewInject(R.id.et_others14)
	private EditText et_others14;
	
	/** Called when the activity is first created. */
	@ResInject(id = R.array.array_DemandService, type = ResType.StringArray)
	private String[] array_DemandService;
	@ResInject(id = R.array.array_demand_service_all, type = ResType.StringArray)
	private String[] array_demand_service_all;
	@ResInject(id = R.array.array_qt, type = ResType.StringArray)
	private String[] array_qt;

	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private View view;
	private List<Integer> idMrgs;
	private List<CheckBox> listLivingEnvironment;
	private List<EditText> listothers;
	private List<String> listOtherTemp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bi_demand_service);
		ViewUtils.inject(this);
		mContext = this;
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
		idMrgs = new ArrayList<Integer>();
		for (int i = 0; i < array_demand_service_all.length; i++) {
			view = findViewById(id[i]);
			TextView tv = (TextView) view.findViewById(R.id.tv_fourth_title0);
			tv.setText(array_demand_service_all[i]);
			MultiLineRadioGroup mrg = (MultiLineRadioGroup) view
					.findViewById(R.id.mrg);
			int idMrg = Tools.getCurrentTimeMillis() + i;
			mrg.setId(idMrg);
			idMrgs.add(idMrg);
			mrg.setItemChecked(6);
			setRadioButtonAndEdit();
			mrg.setOnCheckChangedListener(new OnCheckedChangedListener() {
				@Override
				public void onItemChecked(MultiLineRadioGroup group,
						int position, boolean checked) {
					
					for (int j = 0; j < id.length; j++) {
						if (group.getId() == idMrgs.get(j)) {
							EditText et_others = (EditText) findViewById(id[j])
									.findViewById(R.id.et_others);
							if (position == 7) {
								if (checked) {
									et_others.setVisibility(View.VISIBLE);
								} else {
									et_others.setText("");
									et_others.setVisibility(View.GONE);
								}
							} 
							else {
								et_others.setText("");
								et_others.setVisibility(View.GONE);
							}
						}
					}

				}
			});
		}
		listLivingEnvironment = new ArrayList<CheckBox>();
		listLivingEnvironment.add(cb_LivingEnvironment0);
		listLivingEnvironment.add(cb_LivingEnvironment1);
		listLivingEnvironment.add(cb_LivingEnvironment2);
		listLivingEnvironment.add(cb_LivingEnvironment3);
		listLivingEnvironment.add(cb_LivingEnvironment4);
		listLivingEnvironment.add(cb_LivingEnvironment5);
		listLivingEnvironment.add(cb_LivingEnvironment6);
		listLivingEnvironment.add(cb_LivingEnvironment7);
		listLivingEnvironment.add(cb_LivingEnvironment8);
		listLivingEnvironment.add(cb_LivingEnvironment9);
		listLivingEnvironment.add(cb_LivingEnvironment10);
		listLivingEnvironment.add(cb_LivingEnvironment11);
		listLivingEnvironment.add(cb_LivingEnvironment12);
		listLivingEnvironment.add(cb_LivingEnvironment13);
		listLivingEnvironment.add(cb_LivingEnvironment14);
		listothers = new ArrayList<EditText>();
		listothers.add(et_others0);
		listothers.add(et_others1);
		listothers.add(et_others2);
		listothers.add(et_others3);
		listothers.add(et_others4);
		listothers.add(et_others5);
		listothers.add(et_others6);
		listothers.add(et_others7);
		listothers.add(et_others8);
		listothers.add(et_others9);
		listothers.add(et_others10);
		listothers.add(et_others11);
		listothers.add(et_others12);
		listothers.add(et_others13);
		listothers.add(et_others14);
		for (int i = 0; i < listLivingEnvironment.size(); i++) {
			listLivingEnvironment.get(i).setText(array_qt[i]);
			listLivingEnvironment.get(i).setOnCheckedChangeListener(this);
		}
		listOtherTemp = new ArrayList<String>();
		
		
		tv_second_title.setOnLongClickListener(this);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title4.setOnLongClickListener(this);
		tv_third_title5.setOnLongClickListener(this);
		tv_third_title6.setOnLongClickListener(this);
		tv_third_title7.setOnLongClickListener(this);
	}
	
	private void setRadioButtonAndEdit(){
		for (int j = 0; j < id.length; j++) {
			
			EditText et_others = (EditText) findViewById(id[j])
						.findViewById(R.id.et_others);
				
			et_others.setVisibility(View.GONE);
					
		}
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_DemandService bi = dbUtils.findFirst(Selector.from(
					BI_DemandService.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {

				View view;
				String item = "", others = "";
				item = bi.getServiceFrequencyCode();
				others = bi.getServiceFrequencyOtherDesc();
				listOtherTemp.add(others.substring(0, others.indexOf(",", 0)));
				int location = others.indexOf(",", 0);
				for (int j = 0; j < subCounter(others, ",") - 1; j++) {
					Log.d("test", "test" + j);
					listOtherTemp.add(others.substring(location + 1,
							others.indexOf(",", location + 1)));
					location = others.indexOf(",", location + 1);
				}
				if (others.lastIndexOf(",") == others.length() - 1) {
					listOtherTemp.add("");
				} else {
					listOtherTemp.add(others.substring(
							others.lastIndexOf(",") + 1, others.length()));
				}
				for (int i = 0; i < array_demand_service_all.length; i++) {
					view = findViewById(id[i]);
					MultiLineRadioGroup mrg = (MultiLineRadioGroup) view
							.findViewById(idMrgs.get(i));
					EditText et_others = (EditText) view
							.findViewById(R.id.et_others);
					if (item.split(",").length > 0) {
						mrg.setItemChecked(Integer.parseInt(item.split(",")[i]));
						if (Integer.parseInt(item.split(",")[i]) == 7) {
							et_others.setVisibility(View.VISIBLE);
						}
					}
					if (!listOtherTemp.get(i).equals("")) {
						et_others.setText(listOtherTemp.get(i));
					}
				}

				if (!bi.getServiceFrequencyCode().equals("")) {
					String[] array_temp = bi.getServiceFrequencyCode().split(
							",");
					String[] array_temp_name = bi.getServiceFrequencyName()
							.split(",");
					if (array_temp.length > 44) {
						for (int i = 0; i < array_temp.length - 44; i++) {
							int n = Integer.parseInt(array_temp[44 + i]);
							listLivingEnvironment.get(n).setChecked(true);
						}
						for (int i = 0; i < array_temp_name.length - 44; i++) {
							int n = Integer.parseInt(array_temp[44 + i]);
							listothers.get(n)
									.setText(
											bi.getServiceFrequencyName().split(
													",")[44 + i]);
						}
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
			DbUtils dbUtils = DbUtils.create(this);
			BI_DemandService bi = null;
			bi = new BI_DemandService();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			String item = "",
			value = "",
			others = "";
			for (int i = 0; i < array_demand_service_all.length; i++) {
				view = findViewById(id[i]);
				MultiLineRadioGroup mrg = (MultiLineRadioGroup) view
						.findViewById(idMrgs.get(i));
				EditText et_others = (EditText) view
						.findViewById(R.id.et_others);
				if (mrg.getCheckedItems() != null) {
					item += mrg.getCheckedItems()[0] + ",";
					value += mrg.getCheckedValues().get(0) + ",";
				}
				else
				{
					item += " " + ",";
					value += " " + ",";
				}
				others += et_others.getText().toString() + ",";
			}

			item += Tools.getCheckBoxStatus(listLivingEnvironment);
			String str_t = "";
			for (int i = 0; i < listLivingEnvironment.size(); i++) {
				if (listLivingEnvironment.get(i).isChecked()) {
					str_t += listothers.get(i).getText().toString() + ",";
				}
				else
				{
					str_t += " " + ",";
				}
			}
			if (str_t.indexOf(",") != -1) {
				str_t = str_t.substring(0, str_t.length() - 1);
			}
			value += str_t;

			bi.setServiceFrequencyCode(item);
			bi.setServiceFrequencyName(value);
			bi.setServiceFrequencyOtherDesc(others.substring(0,
					others.length() - 1));
			try {
				if (dbUtils.findFirst(Selector.from(BI_DemandService.class)
						.where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector
							.from(BI_DemandService.class).where("ReportId",
									"=", reportId));
					item = "";
					value = "";
					others = "";
					for (int i = 0; i < array_demand_service_all.length; i++) {
						view = findViewById(id[i]);
						MultiLineRadioGroup mrg = (MultiLineRadioGroup) view
								.findViewById(idMrgs.get(i));
						EditText et_others = (EditText) view
								.findViewById(R.id.et_others);
						if (mrg.getCheckedItems() != null) {
							item += mrg.getCheckedItems()[0] + ",";
							value += mrg.getCheckedValues().get(0) + ",";
						}
						else
						{
							item += " " + ",";
							value += " " + ",";
						}
						others += et_others.getText().toString() + ",";
					}
					item += Tools.getCheckBoxStatus(listLivingEnvironment);
					str_t = "";
					for (int i = 0; i < listLivingEnvironment.size(); i++) {
						if (listLivingEnvironment.get(i).isChecked()) {
							str_t += listothers.get(i).getText().toString()
									+ ",";
						}
					}
					if (str_t.indexOf(",") != -1) {
						str_t = str_t.substring(0, str_t.length() - 1);
					}
					value += str_t;

					bi.setServiceFrequencyCode(item);
					bi.setServiceFrequencyName(value);
					bi.setServiceFrequencyOtherDesc(others.substring(0,
							others.length() - 1));
					dbUtils.update(bi);
					Toast.makeText(this,
							getResources().getString(R.string.success_update),
							Toast.LENGTH_SHORT).show();
				} else {
					dbUtils.save(bi);
					Toast.makeText(this,
							getResources().getString(R.string.success_save),
							Toast.LENGTH_SHORT).show();
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
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		for (int i = 0; i < listothers.size(); i++) {
			if (buttonView.getId() == listLivingEnvironment.get(i).getId()) {
				if (isChecked)
					listothers.get(i).setVisibility(View.VISIBLE);
				else {
					listothers.get(i).setText("");
					listothers.get(i).setVisibility(View.GONE);
				}
			}

		}
	}

	public int subCounter(String str1, String str2) {

		int counter = 0;
		for (int i = 0; i <= str1.length() - str2.length(); i++) {
			if (str1.substring(i, i + str2.length()).equalsIgnoreCase(str2)) {
				counter++;
			}
		}
		return counter;

	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "bi_fwxq");
			break;
		case R.id.tv_third_title0:
			intent.putExtra("guid", "bi_yyss");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "bi_ylws");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "bi_jthl");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "bi_jjjy");
			break;
		case R.id.tv_third_title4:
			intent.putExtra("guid", "bi_sqrjzl");
			break;
		case R.id.tv_third_title5:
			intent.putExtra("guid", "bi_jzfw");
			break;
		case R.id.tv_third_title6:
			intent.putExtra("guid", "bi_xljwyhd");
			break;
		case R.id.tv_third_title7:
			intent.putExtra("guid", "bi_qt");
			break;

		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}