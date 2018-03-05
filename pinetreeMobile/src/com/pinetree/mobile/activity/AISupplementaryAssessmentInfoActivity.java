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
import com.pinetree.mobile.bean.AI_SupplementaryAssessmentInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_supplementary_assessment_info)
public class AISupplementaryAssessmentInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{

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
	@ViewInject(R.id.rg_Dementia)
	private RadioGroup rg_Dementia;
	@ViewInject(R.id.rg_Fall)
	private RadioGroup rg_Fall;
	@ViewInject(R.id.rg_Choke)
	private RadioGroup rg_Choke;
	@ViewInject(R.id.rg_Belost)
	private RadioGroup rg_Belost;
	@ViewInject(R.id.rg_Idioctonia)
	private RadioGroup rg_Idioctonia;
	@ViewInject(R.id.cb_MentalSickness0)
	private CheckBox cb_MentalSickness0;
	@ViewInject(R.id.cb_MentalSickness1)
	private CheckBox cb_MentalSickness1;
	@ViewInject(R.id.cb_MentalSickness2)
	private CheckBox cb_MentalSickness2;
	@ViewInject(R.id.cb_MentalSickness3)
	private CheckBox cb_MentalSickness3;
	@ViewInject(R.id.cb_MentalSickness4)
	private CheckBox cb_MentalSickness4;
	@ViewInject(R.id.cb_MentalSickness5)
	private CheckBox cb_MentalSickness5;
	private List<CheckBox> listMentalSickness;

	@ResInject(id = R.array.array_dd, type = ResType.StringArray)
	private String[] array_dd;
	
	@ResInject(id = R.array.array_cd, type = ResType.StringArray)
	private String[] array_cd;
	
	@ResInject(id = R.array.array_jsjb, type = ResType.StringArray)
	private String[] array_jsjb;
	
	
	@ResInject(id = R.array.array_ys, type = ResType.StringArray)
	private String[] array_ys;
	
	@ResInject(id = R.array.array_zs, type = ResType.StringArray)
	private String[] array_zs;
	
	@ResInject(id = R.array.array_zisha, type = ResType.StringArray)
	private String[] array_zisha;
	
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

			Tools.addRadioButtonV(mContext, array_cd, rg_Dementia);
			Tools.addRadioButtonV(mContext, array_dd, rg_Fall);
			Tools.addRadioButtonV(mContext, array_ys, rg_Choke);
			Tools.addRadioButtonV(mContext, array_zs, rg_Belost);
			Tools.addRadioButtonV(mContext, array_zisha, rg_Idioctonia);
		
		

		listMentalSickness = new ArrayList<CheckBox>();
		listMentalSickness.add(cb_MentalSickness0);
		listMentalSickness.add(cb_MentalSickness1);
		listMentalSickness.add(cb_MentalSickness2);
		listMentalSickness.add(cb_MentalSickness3);
		listMentalSickness.add(cb_MentalSickness4);
		listMentalSickness.add(cb_MentalSickness5);
		for (int i = 0; i < listMentalSickness.size(); i++) {
			listMentalSickness.get(i).setText(array_jsjb[i]);
		}
		tv_second_title.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_SupplementaryAssessmentInformation bi = dbUtils.findFirst(Selector.from(AI_SupplementaryAssessmentInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_Dementia.check(Integer.parseInt(bi.getDementiaCode()));
				rg_Fall.check(Integer.parseInt(bi.getFallCode()));
				rg_Choke.check(Integer.parseInt(bi.getChokeCode()));
				rg_Belost.check(Integer.parseInt(bi.getBelostCode()));
				rg_Idioctonia.check(Integer.parseInt(bi.getIdioctoniaCode()));
				if (!bi.getMentalSicknessCode().equals("")) {
					String[] array_temp = bi.getMentalSicknessCode().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listMentalSickness.get(n).setChecked(true);
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
			AI_SupplementaryAssessmentInformation bi = null;
			bi = new AI_SupplementaryAssessmentInformation();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setDementiaCode(rg_Dementia.getCheckedRadioButtonId() + "");
			bi.setFallCode(rg_Fall.getCheckedRadioButtonId() + "");
			bi.setChokeCode(rg_Choke.getCheckedRadioButtonId() + "");
			bi.setBelostCode(rg_Belost.getCheckedRadioButtonId() + "");
			bi.setIdioctoniaCode(rg_Idioctonia.getCheckedRadioButtonId() + "");
			bi.setMentalSicknessCode(Tools.getCheckBoxStatus(listMentalSickness));

			try {
				if (dbUtils.findFirst(Selector.from(AI_SupplementaryAssessmentInformation.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_SupplementaryAssessmentInformation.class).where("ReportId", "=", reportId));
					bi.setDementiaCode(rg_Dementia.getCheckedRadioButtonId() + "");
					bi.setFallCode(rg_Fall.getCheckedRadioButtonId() + "");
					bi.setChokeCode(rg_Choke.getCheckedRadioButtonId() + "");
					bi.setBelostCode(rg_Belost.getCheckedRadioButtonId() + "");
					bi.setIdioctoniaCode(rg_Idioctonia.getCheckedRadioButtonId() + "");
					bi.setMentalSicknessCode(Tools.getCheckBoxStatus(listMentalSickness));
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
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_bcpgxx");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
