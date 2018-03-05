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
import com.pinetree.mobile.bean.BI_ExternalProfessionalService;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_external_professional_service)
public class BIExternalProfessionalServiceActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{

	@ViewInject(R.id.tv_third_title0)
	private TextView tv_third_title0;
	@ViewInject(R.id.tv_third_title1)
	private TextView tv_third_title1;
	@ViewInject(R.id.tv_third_title2)
	private TextView tv_third_title2;
	@ViewInject(R.id.rg_NursingGoal)
	private RadioGroup rg_NursingGoal;
	@ViewInject(R.id.rg_NursingDemandChange)
	private RadioGroup rg_NursingDemandChange;
	@ViewInject(R.id.et_EmotionDuration0)
	private EditText et_EmotionDuration0;
	@ViewInject(R.id.et_EmotionDuration1)
	private EditText et_EmotionDuration1;
	@ViewInject(R.id.et_EmotionDuration2)
	private EditText et_EmotionDuration2;
	@ViewInject(R.id.et_PersonalLifeDuration0)
	private EditText et_PersonalLifeDuration0;
	@ViewInject(R.id.et_PersonalLifeDuration1)
	private EditText et_PersonalLifeDuration1;
	@ViewInject(R.id.et_PersonalLifeDuration2)
	private EditText et_PersonalLifeDuration2;
	@ViewInject(R.id.et_HomeLifeDuration0)
	private EditText et_HomeLifeDuration0;
	@ViewInject(R.id.et_HomeLifeDuration1)
	private EditText et_HomeLifeDuration1;
	@ViewInject(R.id.et_HomeLifeDuration2)
	private EditText et_HomeLifeDuration2;

	@ResInject(id = R.array.array_khmb, type = ResType.StringArray)
	private String[] array_khmb;
	@ResInject(id = R.array.array_khxqgb, type = ResType.StringArray)
	private String[] array_khxqgb;
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
		Tools.addRadioButtonV(mContext, array_khmb, rg_NursingGoal);
		Tools.addRadioButtonV(mContext, array_khxqgb, rg_NursingDemandChange);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_ExternalProfessionalService bi = dbUtils.findFirst(Selector.from(BI_ExternalProfessionalService.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_NursingGoal.check(Integer.parseInt(bi.getNursingGoal()));
				rg_NursingDemandChange.check(Integer.parseInt(bi.getNursingDemandChange()));
				if (bi.getEmotionDuration().contains("-")) {
					String[] array_t = bi.getEmotionDuration().split("-");
					if (array_t.length > 2) {
						et_EmotionDuration0.setText(array_t[0]);
						et_EmotionDuration1.setText(array_t[1]);
						et_EmotionDuration2.setText(array_t[2]);
					}
				}
				if (bi.getPersonalLifeDuration().contains("-")) {
					String[] array_t = bi.getPersonalLifeDuration().split("-");
					if (array_t.length > 2) {
						et_PersonalLifeDuration0.setText(array_t[0]);
						et_PersonalLifeDuration1.setText(array_t[1]);
						et_PersonalLifeDuration2.setText(array_t[2]);
					}
				}
				if (bi.getHomeLifeDuration().contains("-")) {
					String[] array_t = bi.getHomeLifeDuration().split("-");
					if (array_t.length > 2) {
						et_HomeLifeDuration0.setText(array_t[0]);
						et_HomeLifeDuration1.setText(array_t[1]);
						et_HomeLifeDuration2.setText(array_t[2]);
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
			BI_ExternalProfessionalService bi = null;
			bi = new BI_ExternalProfessionalService();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			String str0 = et_EmotionDuration0.getText().toString();
			String str1 = et_EmotionDuration1.getText().toString();
			String str2 = et_EmotionDuration2.getText().toString();
			String str3 = et_PersonalLifeDuration0.getText().toString();
			String str4 = et_PersonalLifeDuration1.getText().toString();
			String str5 = et_PersonalLifeDuration2.getText().toString();
			String str6 = et_HomeLifeDuration0.getText().toString();
			String str7 = et_HomeLifeDuration1.getText().toString();
			String str8 = et_HomeLifeDuration2.getText().toString();

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
			if (str4.equals("")) {
				str4 = "0";
			}
			if (str5.equals("")) {
				str5 = "0";
			}
			if (str6.equals("")) {
				str6 = "0";
			}
			if (str7.equals("")) {
				str7 = "0";
			}
			if (str8.equals("")) {
				str8 = "0";
			}

			bi.setEmotionDuration(str0 + "-" + str1 + "-" + str2);
			bi.setPersonalLifeDuration(str3 + "-" + str4 + "-" + str5);
			bi.setHomeLifeDuration(str6 + "-" + str7 + "-" + str8);
			bi.setNursingGoal(rg_NursingGoal.getCheckedRadioButtonId() + "");
			bi.setNursingDemandChange(rg_NursingDemandChange.getCheckedRadioButtonId() + "");

			try {
				if (dbUtils.findFirst(Selector.from(BI_ExternalProfessionalService.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(BI_ExternalProfessionalService.class).where("ReportId", "=", reportId));
					bi.setEmotionDuration(str0 + "-" + str1 + "-" + str2);
					bi.setPersonalLifeDuration(str3 + "-" + str4 + "-" + str5);
					bi.setHomeLifeDuration(str6 + "-" + str7 + "-" + str8);
					bi.setNursingGoal(rg_NursingGoal.getCheckedRadioButtonId() + "");
					bi.setNursingDemandChange(rg_NursingDemandChange.getCheckedRadioButtonId() + "");
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
			intent.putExtra("guid", "bi_zyfw");
			break;
		case R.id.tv_third_title1:
		case R.id.tv_third_title2:
			intent.putExtra("guid", "bi_khmb");
			break;
		
		
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
