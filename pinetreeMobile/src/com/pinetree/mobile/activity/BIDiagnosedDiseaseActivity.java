package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import com.pinetree.mobile.bean.BI_DiagnosedDisease;
import com.pinetree.mobile.utils.MultiLineRadioGroup;
import com.pinetree.mobile.utils.MultiLineRadioGroup.OnCheckedChangedListener;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_diagnosed_disease)
public class BIDiagnosedDiseaseActivity extends Activity implements MyBaseInterface {

	@ViewInject(R.id.rg_InfectiousDiseases)
	int[] id = { R.id.mrg_item0, R.id.mrg_item1, R.id.mrg_item2, R.id.mrg_item3 };

	@ResInject(id = R.array.array_crjb, type = ResType.StringArray)
	private String[] array_crjb;
	@ResInject(id = R.array.array_fxgwjb, type = ResType.StringArray)
	private String[] array_fxgwjb;
	@ResInject(id = R.array.array_ysxzxjb, type = ResType.StringArray)
	private String[] array_ysxzxjb;
	@ResInject(id = R.array.array_qtjb, type = ResType.StringArray)
	private String[] array_qtjb;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	@ViewInject(R.id.et_others0)
	EditText et_others0;
	@ViewInject(R.id.et_others1)
	EditText et_others1;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private View view;
	private List<Integer> idMrgs;
	private List<String[]> arrays;

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
		arrays.add(array_crjb);
		arrays.add(array_fxgwjb);
		arrays.add(array_ysxzxjb);
		arrays.add(array_qtjb);
		for (int i = 0; i < id.length; i++) {
			view = findViewById(id[i]);
			MultiLineRadioGroup mrg = (MultiLineRadioGroup) view.findViewById(R.id.mrg);
			int idMrg = Tools.getCurrentTimeMillis() + i;
			mrg.setId(idMrg);
			idMrgs.add(idMrg);
			mrg.setOnCheckChangedListener(new OnCheckedChangedListener() {
				@Override
				public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
					
					if (group.getId() == idMrgs.get(3)) {
						if (position == 7) {
							if (checked)
								et_others0.setVisibility(View.VISIBLE);
							else {
								et_others0.setVisibility(View.GONE);
								et_others0.setText("");
							}
						}
						if (position == 8) {
							if (checked)
								et_others1.setVisibility(View.VISIBLE);
							else {
								et_others1.setVisibility(View.GONE);
								et_others1.setText("");
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
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_DiagnosedDisease bi = dbUtils.findFirst(Selector.from(BI_DiagnosedDisease.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				for (int i = 0; i < id.length; i++) {
					MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
					String item = "";
					if (i == 0)
						item = bi.getInfectiousDiseases();
					if (i == 1)
						item = bi.getHighRiskDisease();
					if (i == 2)
						item = bi.getDietaryRestrictionDisease();
					if (i == 3) {
						item = bi.getOtherDisease();
						if (item.contains("6")) {
							et_others0.setVisibility(View.VISIBLE);
							et_others0.setText(bi.getOtherInfoOne());
						}
						if (item.contains("7")) {
							et_others1.setVisibility(View.VISIBLE);
							et_others1.setText(bi.getOtherInfoTwo());
						}
					}

					if (item!=null&&item.split(",").length > 0) {
						for (int j = 0; j < item.split(",").length; j++) {
							if(!item.split(",")[j].equals("")){
								mrg.setItemChecked(Integer.parseInt(item.split(",")[j])+1);

							}
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
			BI_DiagnosedDisease bi = null;
			bi = new BI_DiagnosedDisease();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			for (int i = 0; i < id.length; i++) {
				MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
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
				}}
				if (i == 0)
					bi.setInfectiousDiseases(item);
				if (i == 1)
					bi.setHighRiskDisease(item);
				if (i == 2)
					bi.setDietaryRestrictionDisease(item);
				if (i == 3)
					bi.setOtherDisease(item);
			}
			if (et_others0.getVisibility() == View.VISIBLE) {
				bi.setOtherInfoOne(et_others0.getText().toString());
			}
			if (et_others1.getVisibility() == View.VISIBLE) {
				bi.setOtherInfoTwo(et_others1.getText().toString());
			}
			try {
				if (dbUtils.findFirst(Selector.from(BI_DiagnosedDisease.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(BI_DiagnosedDisease.class).where("ReportId", "=", reportId));
					for (int i = 0; i < id.length; i++) {
						MultiLineRadioGroup mrg = (MultiLineRadioGroup) findViewById(id[i]).findViewById(idMrgs.get(i));
						String item = "";
						int[] items;
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
						}
						if (i == 0)
							bi.setInfectiousDiseases(item);
						if (i == 1)
							bi.setHighRiskDisease(item);
						if (i == 2)
							bi.setDietaryRestrictionDisease(item);
						if (i == 3)
							bi.setOtherDisease(item);
					}
					if (et_others0.getVisibility() == View.VISIBLE) {
						bi.setOtherInfoOne(et_others0.getText().toString());
					}
					if (et_others1.getVisibility() == View.VISIBLE) {
						bi.setOtherInfoTwo(et_others1.getText().toString());
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
}
