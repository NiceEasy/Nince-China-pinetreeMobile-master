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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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
import com.pinetree.mobile.bean.BI_EarlyResults;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_early_results)
public class BIEarlyResultsActivity extends Activity implements MyBaseInterface, 
	android.widget.RadioGroup.OnCheckedChangeListener,OnLongClickListener {

	@ViewInject(R.id.tv_third_title1)
	private TextView tv_third_title1;
	@ViewInject(R.id.tv_third_title2)
	private TextView tv_third_title2;
	@ViewInject(R.id.tv_third_title3)
	private TextView tv_third_title3;
	@ViewInject(R.id.cb_FirstImpression0)
	private CheckBox cb_FirstImpression0;
	@ViewInject(R.id.cb_FirstImpression1)
	private CheckBox cb_FirstImpression1;
	@ViewInject(R.id.cb_FirstImpression2)
	private CheckBox cb_FirstImpression2;
	@ViewInject(R.id.cb_FirstImpression3)
	private CheckBox cb_FirstImpression3;
	@ViewInject(R.id.cb_FirstImpression4)
	private CheckBox cb_FirstImpression4;
	@ViewInject(R.id.cb_FirstImpression5)
	private CheckBox cb_FirstImpression5;
	
	@ViewInject(R.id.cb_ScreenInfo1)
	private CheckBox cb_ScreenInfo1;
	@ViewInject(R.id.cb_ScreenInfo2)
	private CheckBox cb_ScreenInfo2;
	@ViewInject(R.id.cb_ScreenInfo3)
	private CheckBox cb_ScreenInfo3;

	@ViewInject(R.id.et_others0)
	private EditText et_others0;
	@ViewInject(R.id.et_others1)
	private EditText et_others1;
	@ViewInject(R.id.et_others2)
	private EditText et_others2;
	@ViewInject(R.id.rg_FirstAdvices)
	private RadioGroup rg_FirstAdvices;
	private List<CheckBox> listFirstImpression;
	private List<CheckBox> listScreenInfo;
	private List<CheckBox> listFirstAdvices;

	@ResInject(id = R.array.array_cbyx, type = ResType.StringArray)
	private String[] array_cbyx;
	@ResInject(id = R.array.array_scxx, type = ResType.StringArray)
	private String[] array_scxx;
	
	@ResInject(id = R.array.array_cpjy, type = ResType.StringArray)
	private String[] array_cpjy;
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
		listFirstImpression = new ArrayList<CheckBox>();
		listFirstImpression.add(cb_FirstImpression0);
		listFirstImpression.add(cb_FirstImpression1);
		listFirstImpression.add(cb_FirstImpression2);
		listFirstImpression.add(cb_FirstImpression3);
		listFirstImpression.add(cb_FirstImpression4);
		listFirstImpression.add(cb_FirstImpression5);
		
		for (int i = 0; i < listFirstImpression.size(); i++) {
			listFirstImpression.get(i).setText(array_cbyx[i]);
		}
		Tools.addRadioButtonV(mContext, array_cpjy, rg_FirstAdvices);
		rg_FirstAdvices.setOnCheckedChangeListener(this);
/*
		cb_FirstImpression8.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked)
					et_others0.setVisibility(View.VISIBLE);
				else {
					et_others0.setText("");
					et_others0.setVisibility(View.GONE);
				}
			}
		});
		cb_FirstImpression9.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked)
					et_others2.setVisibility(View.VISIBLE);
				else {
					et_others2.setText("");
					et_others2.setVisibility(View.GONE);
				}
			}
		});
		*/
		
		listScreenInfo = new ArrayList<CheckBox>();
		listScreenInfo.add(cb_ScreenInfo1);
		listScreenInfo.add(cb_ScreenInfo2);
		listScreenInfo.add(cb_ScreenInfo3);
		
		for (int i = 0; i < listScreenInfo.size(); i++) {
			listScreenInfo.get(i).setText(array_scxx[i]);
		}
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_EarlyResults bi = dbUtils.findFirst(Selector.from(BI_EarlyResults.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				if (!bi.getFirstImpressionCodes().equals("")) {
					String[] array_temp = bi.getFirstImpressionCodes().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listFirstImpression.get(n).setChecked(true);
							if (n == 8) {
								et_others0.setVisibility(View.VISIBLE);
								et_others0.setText(bi.getFirstImpressionOtherOne());
							}
							if (n == 9) {
								et_others2.setVisibility(View.VISIBLE);
								et_others2.setText(bi.getFirstImpressionOtherTwo());
							}
						}
					}
				}
				rg_FirstAdvices.check(Integer.parseInt(bi.getFirstAdvices()));
				et_others1.setText(bi.getFirstAdvicesOther());
				
				if(bi.getRemark()!=null)
				{
					if(!bi.getRemark().equals(""))
					{
						String[] array_temp = bi.getRemark().split(",");
						if (array_temp.length > 0) {
							for (int i = 0; i < array_temp.length; i++) {
								int n = Integer.parseInt(array_temp[i]);
								listScreenInfo.get(n).setChecked(true);
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
			BI_EarlyResults bi = null;
			bi = new BI_EarlyResults();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setFirstImpressionCodes(Tools.getCheckBoxStatus(listFirstImpression));
			bi.setFirstImpressionContents(Tools.getCheckBoxStatusText(listFirstImpression));
			bi.setRemark(Tools.getCheckBoxStatus(listScreenInfo));
			bi.setFirstAdvices(rg_FirstAdvices.getCheckedRadioButtonId() + "");
			if (Tools.getCheckBoxStatus(listFirstImpression).contains("8")) {
				bi.setFirstImpressionOtherOne(et_others0.getText().toString());
			} else {
				bi.setFirstImpressionOtherOne("");
			}
			if (Tools.getCheckBoxStatus(listFirstImpression).contains("9")) {
				bi.setFirstImpressionOtherTwo(et_others2.getText().toString());
			} else {
				bi.setFirstImpressionOtherTwo("");
			}
			if (rg_FirstAdvices.getCheckedRadioButtonId() == 3) {
				bi.setFirstAdvicesOther(et_others1.getText().toString());
			} else {
				bi.setFirstAdvicesOther("");
			}

			try {
				if (dbUtils.findFirst(Selector.from(BI_EarlyResults.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(BI_EarlyResults.class).where("ReportId", "=", reportId));
					bi.setFirstImpressionCodes(Tools.getCheckBoxStatus(listFirstImpression));
					bi.setFirstImpressionContents(Tools.getCheckBoxStatusText(listFirstImpression));
					bi.setRemark(Tools.getCheckBoxStatus(listScreenInfo));
					bi.setFirstAdvices(rg_FirstAdvices.getCheckedRadioButtonId() + "");
					if (Tools.getCheckBoxStatus(listFirstImpression).contains("8")) {
						bi.setFirstImpressionOtherOne(et_others0.getText().toString());
					} else {
						bi.setFirstImpressionOtherOne("");
					}
					if (Tools.getCheckBoxStatus(listFirstImpression).contains("9")) {
						bi.setFirstImpressionOtherTwo(et_others2.getText().toString());
					} else {
						bi.setFirstImpressionOtherTwo("");
					}
					if (rg_FirstAdvices.getCheckedRadioButtonId() == 3) {
						bi.setFirstAdvicesOther(et_others1.getText().toString());
					} else {
						bi.setFirstAdvicesOther("");
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
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		if (checkedId == 3) {
			et_others1.setVisibility(View.VISIBLE);
		} else {
			et_others1.setText("");
			et_others1.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_third_title1:
			intent.putExtra("guid", "bi_cbyx");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "bi_cpjy");
			break;
		
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
