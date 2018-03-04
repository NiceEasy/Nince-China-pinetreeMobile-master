package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
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
import com.pinetree.mobile.bean.BI_NowLivingCondition;
import com.pinetree.mobile.utils.MultiLineRadioGroup;
import com.pinetree.mobile.utils.MultiLineRadioGroup.OnCheckedChangedListener;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_now_living)
public class BINowLivingActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
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
	int[] id = { R.id.mrg_bi_now_living_part0, R.id.mrg_bi_now_living_part1, R.id.mrg_bi_now_living_part2, R.id.mrg_bi_now_living_part3, R.id.mrg_bi_now_living_part4, R.id.mrg_bi_now_living_part5,R.id.mrg_bi_now_living_part7,   };
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
	@ViewInject(R.id.cb_OtherConditions0)
	private CheckBox cb_OtherConditions0;
	@ViewInject(R.id.cb_OtherConditions1)
	private CheckBox cb_OtherConditions1;
	@ViewInject(R.id.cb_OtherConditions2)
	private CheckBox cb_OtherConditions2;
	@ViewInject(R.id.cb_OtherConditions3)
	private CheckBox cb_OtherConditions3;
	@ViewInject(R.id.cb_OtherConditions4)
	private CheckBox cb_OtherConditions4;
	@ViewInject(R.id.cb_OtherConditions5)
	private CheckBox cb_OtherConditions5;
	@ResInject(id = R.array.array_zfqk, type = ResType.StringArray)
	private String[] array_zfqk;
	@ResInject(id = R.array.array_jzqk, type = ResType.StringArray)
	private String[] array_jzqk;
	@ResInject(id = R.array.array_jjly, type = ResType.StringArray)
	private String[] array_jjly;
	@ResInject(id = R.array.array_srqk, type = ResType.StringArray)
	private String[] array_srqk;
	@ResInject(id = R.array.array_tzpojkzk, type = ResType.StringArray)
	private String[] array_tzpojkzk;
	@ResInject(id = R.array.array_zjkn, type = ResType.StringArray)
	private String[] array_zjkn;
	@ResInject(id = R.array.array_jzhj, type = ResType.StringArray)
	private String[] array_jzhj;
	@ResInject(id = R.array.array_ylzf, type = ResType.StringArray)
	private String[] array_ylzf;
	@ResInject(id = R.array.array_qtzk, type = ResType.StringArray)
	private String[] array_qtzk;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private View view;
	private List<Integer> idMrgs;
	private List<String[]> arrays;
	private List<CheckBox> listHouseConditions;
	private List<CheckBox> listLivingEnvironment;
	private List<CheckBox> listIncomeConditions;
	private List<CheckBox> listOtherConditions;

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
		idMrgs = new ArrayList<Integer>();
		arrays = new ArrayList<String[]>();
		arrays.add(array_zfqk);
		arrays.add(array_jzqk);
		arrays.add(array_jjly);
		arrays.add(array_srqk);
		arrays.add(array_tzpojkzk);
		arrays.add(array_zjkn);
		arrays.add(array_ylzf);
		for (int i = 0; i < id.length; i++) {
			view = findViewById(id[i]);
			MultiLineRadioGroup mrg = (MultiLineRadioGroup) view.findViewById(R.id.mrg);
			int idMrg = Tools.getCurrentTimeMillis() + i;
			mrg.setId(idMrg);
			idMrgs.add(idMrg);
			mrg.setOnCheckChangedListener(new OnCheckedChangedListener() {
				@Override
				public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
					
					for (int j = 0; j < id.length + 2; j++) {
						if (group.getId() == idMrgs.get(j)) {
							EditText et_others = (EditText) findViewById(id[j]).findViewById(R.id.et_others);
							int otherLocation = 10;
							if (j == 0)
								otherLocation = 5;
							if (j == 1)
								otherLocation = 9;
							if (j == 2)
								otherLocation = 8;
							if (j == 6)
								otherLocation = 5;
							if (position == otherLocation) {
								if (checked)
									et_others.setVisibility(View.VISIBLE);
								else {
									et_others.setText("");
									et_others.setVisibility(View.GONE);
								}
							}
						}
					}

				}
			});
			mrg.removeAllViews();
			for (int k = 0; k < arrays.get(i).length; k++) {
				mrg.append(arrays.get(i)[k]);
			}
		}
		listLivingEnvironment = new ArrayList<CheckBox>();
		listLivingEnvironment.add(cb_LivingEnvironment0);
		listLivingEnvironment.add(cb_LivingEnvironment1);
		listLivingEnvironment.add(cb_LivingEnvironment2);
		listLivingEnvironment.add(cb_LivingEnvironment3);
		listLivingEnvironment.add(cb_LivingEnvironment4);
		listLivingEnvironment.add(cb_LivingEnvironment5);
		listLivingEnvironment.add(cb_LivingEnvironment6);
		for (int i = 0; i < listLivingEnvironment.size(); i++) {
			listLivingEnvironment.get(i).setText(array_jzhj[i]);
		}
		listOtherConditions = new ArrayList<CheckBox>();
		listOtherConditions.add(cb_OtherConditions0);
		listOtherConditions.add(cb_OtherConditions1);
		listOtherConditions.add(cb_OtherConditions2);
		listOtherConditions.add(cb_OtherConditions3);
		listOtherConditions.add(cb_OtherConditions4);
		listOtherConditions.add(cb_OtherConditions5);
		for (int i = 0; i < listOtherConditions.size(); i++) {
			listOtherConditions.get(i).setText(array_qtzk[i]);
		}
		
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title4.setOnLongClickListener(this);
		tv_third_title5.setOnLongClickListener(this);
		tv_third_title6.setOnLongClickListener(this);
		tv_third_title7.setOnLongClickListener(this);
		tv_third_title8.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_NowLivingCondition bi = dbUtils.findFirst(Selector.from(BI_NowLivingCondition.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				for (int i = 0; i < id.length; i++) {
					MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
					EditText et_others = (EditText) findViewById(id[i]).findViewById(R.id.et_others);
					String item = "";
					if (i == 0) {
						item = bi.getHouseConditions();
						et_others.setText(bi.getHouseConditionsOther());
					}
					if (i == 1) {
						item = bi.getLivingConditions();
						et_others.setText(bi.getLivingConditionsOther());
					}
					if (i == 2) {
						item = bi.getEconomicSources();
						et_others.setText(bi.getEconomicSourcesOther());
					}
					if (i == 3) {
						item = bi.getIncomeConditions();
						et_others.setText(bi.getIncomeConditionsOther());
					}
					if (i == 4) {
						item = bi.getLiveSpouseHealth();
						et_others.setText(bi.getLiveSpouseHealthOther());
					}
					if (i == 5) {
						item = bi.getFinancialDifficulties();
					}
					if (i == 6) {
						item = bi.getMedicalPayment();
						et_others.setText(bi.getMedicalPaymentOther());
					}

					if (item != null && item.split(",").length > 0) {
						for (int j = 0; j < item.split(",").length; j++) {
							mrg.setItemChecked(Integer.parseInt(item.split(",")[j])+1);
						}
					}
					if (item != null && item.contains(arrays.get(i).length-1 + "")) {
						et_others.setVisibility(View.VISIBLE);
						if (i == 3) {
							et_others.setVisibility(View.GONE);
						}
					}

				}
				/*
				if (!bi.getHouseConditions().equals("")) {
					String[] array_temp = bi.getHouseConditions().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listHouseConditions.get(n).setChecked(true);
						}
					}
				}
				*/
				if (!bi.getLivingEnvironment().equals("")) {
					String[] array_temp = bi.getLivingEnvironment().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listLivingEnvironment.get(n).setChecked(true);
						}
					}
				}
				/*
				if (!bi.getIncomeConditions().equals("")) {
					String[] array_temp = bi.getIncomeConditions().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listIncomeConditions.get(n).setChecked(true);
						}
					}
				}
				*/
				if (!bi.getOtherConditions().equals("")) {
					String[] array_temp = bi.getOtherConditions().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listOtherConditions.get(n).setChecked(true);
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
			BI_NowLivingCondition bi = null;
			bi = new BI_NowLivingCondition();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			//bi.setHouseConditions(Tools.getCheckBoxStatus(listHouseConditions));
			bi.setLivingEnvironment(Tools.getCheckBoxStatus(listLivingEnvironment));
			//bi.setIncomeConditions(Tools.getCheckBoxStatus(listIncomeConditions));
			bi.setOtherConditions(Tools.getCheckBoxStatus(listOtherConditions));
			String others = "";
			String othersId = "";
			for (int i = 0; i < id.length; i++) {
				MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
				EditText et_others = (EditText) findViewById(id[i]).findViewById(R.id.et_others);
				String item = "";
				int[] items = {};
				items = mrg.getCheckedItems();
				if(items!=null){
				for (int j = 0; j < items.length; j++) {
					if(items[j]>0){
						item += items[j]-1 + ",";
					}
					
				}
				if (items.length > 0) {
					item = item.substring(0, item.lastIndexOf(","));
				}
				if (i == 0) {
					bi.setHouseConditions(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setHouseConditionsOther(et_others.getText().toString());
					} else {
						bi.setHouseConditionsOther("");
					}
				}
				if (i == 1) {
					bi.setLivingConditions(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setLivingConditionsOther(et_others.getText().toString());
					} else {
						bi.setLivingConditionsOther("");
					}
				}
				if (i == 2) {
					bi.setEconomicSources(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setEconomicSourcesOther(et_others.getText().toString());
					} else {
						bi.setEconomicSourcesOther("");
					}
				}
				if (i == 3) {
					bi.setIncomeConditions(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setIncomeConditionsOther(et_others.getText().toString());
					} else {
						bi.setIncomeConditionsOther("");
					}
				}
				if (i == 3) {
					bi.setLiveSpouseHealth(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setLiveSpouseHealthOther(et_others.getText().toString());
					} else {
						bi.setLiveSpouseHealthOther("");
					}
				}
				if (i == 4) {
					bi.setFinancialDifficulties(item);
				}
				if (i == 5) {
					bi.setMedicalPayment(item);
					if (item.contains(arrays.get(i).length-1 + "")) {
						bi.setMedicalPaymentOther(et_others.getText().toString());
					} else {
						bi.setMedicalPaymentOther("");
					}
				}
				}

			}

			try {
				if (dbUtils.findFirst(Selector.from(BI_NowLivingCondition.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(BI_NowLivingCondition.class).where("ReportId", "=", reportId));
					//bi.setHouseConditions(Tools.getCheckBoxStatus(listHouseConditions));
					bi.setLivingEnvironment(Tools.getCheckBoxStatus(listLivingEnvironment));
					//bi.setOtherConditions(Tools.getCheckBoxStatus(listIncomeConditions));
					bi.setOtherConditions(Tools.getCheckBoxStatus(listOtherConditions));
					for (int i = 0; i < id.length; i++) {
						MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
						EditText et_others = (EditText) findViewById(id[i]).findViewById(R.id.et_others);
						String item = "";
						int[] items = {};
						items = mrg.getCheckedItems();
						if(items!=null){
						for (int j = 0; j < items.length; j++) {
							if(items[j]>0){
								item += items[j]-1 + ",";
							}
						}
						if (items.length > 0) {
							item = item.substring(0, item.lastIndexOf(","));
						}
						if (i == 0) {
							bi.setHouseConditions(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setHouseConditionsOther(et_others.getText().toString());
							} else {
								bi.setHouseConditionsOther("");
							}
						}
						if (i == 1) {
							bi.setLivingConditions(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setLivingConditionsOther(et_others.getText().toString());
							} else {
								bi.setLivingConditionsOther("");
							}
						}
						if (i == 2) {
							bi.setEconomicSources(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setEconomicSourcesOther(et_others.getText().toString());
							} else {
								bi.setEconomicSourcesOther("");
							}
						}
						if (i == 3) {
							bi.setIncomeConditions(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setIncomeConditionsOther(et_others.getText().toString());
							} else {
								bi.setIncomeConditionsOther("");
							}
						}
						if (i == 4) {
							bi.setLiveSpouseHealth(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setLiveSpouseHealthOther(et_others.getText().toString());
							} else {
								bi.setLiveSpouseHealthOther("");
							}
						}
						if (i == 5) {
							bi.setFinancialDifficulties(item);
						}
						if (i == 6) {
							bi.setMedicalPayment(item);
							if (item.contains(arrays.get(i).length-1 + "")) {
								bi.setMedicalPaymentOther(et_others.getText().toString());
							} else {
								bi.setMedicalPaymentOther("");
							}
						}
						}
					}
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
			intent.putExtra("guid", "bi_zfqk");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "bi_jzqk");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "bi_jjly");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "bi_srqk");
			break;
		case R.id.tv_third_title4:
			intent.putExtra("guid", "bi_tzpojkzk");
			break;
		case R.id.tv_third_title5:
			intent.putExtra("guid", "bi_zjkn");
			break;
		case R.id.tv_third_title6:
			intent.putExtra("guid", "bi_jzhj");
			break;
		case R.id.tv_third_title7:
			intent.putExtra("guid", "bi_ylzf");
			break;
		case R.id.tv_third_title8:
			intent.putExtra("guid", "bi_qtzk");
			break;
		
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
