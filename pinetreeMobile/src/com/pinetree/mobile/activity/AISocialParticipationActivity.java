package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
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
import com.pinetree.mobile.bean.AI_SocialParticipation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_social_participation)
public class AISocialParticipationActivity extends Activity implements MyBaseInterface, 
	OnCheckedChangeListener,OnLongClickListener {
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
	@ViewInject(R.id.rg_LifeAbility)
	private RadioGroup rg_LifeAbility;
	@ViewInject(R.id.rg_WorkingAbility)
	private RadioGroup rg_WorkingAbility;
	@ViewInject(R.id.rg_TimeOrSpatialOrientation)
	private RadioGroup rg_TimeOrSpatialOrientation;
	@ViewInject(R.id.rg_PersonalOrientation)
	private RadioGroup rg_PersonalOrientation;
	@ViewInject(R.id.rg_SocialIntercourseAbility)
	private RadioGroup rg_SocialIntercourseAbility;
	@ViewInject(R.id.rg_SPLevel)
	private RadioGroup rg_SPLevel;
	@ViewInject(R.id.et_SPSumScore)
	private EditText et_SPSumScore;

	@ResInject(id = R.array.array_shnl, type = ResType.StringArray)
	private String[] array_shnl;
	@ResInject(id = R.array.array_gznl, type = ResType.StringArray)
	private String[] array_gznl;
	@ResInject(id = R.array.array_sjkjdx, type = ResType.StringArray)
	private String[] array_sjkjdx;
	@ResInject(id = R.array.array_rwdx, type = ResType.StringArray)
	private String[] array_rwdx;
	@ResInject(id = R.array.array_shjwnl, type = ResType.StringArray)
	private String[] array_shjwnl;
	@ResInject(id = R.array.array_shcyfj, type = ResType.StringArray)
	private String[] array_shcyfj;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private int score = 0;
	private List<Integer> maps;

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
		maps = new ArrayList<Integer>();
		Tools.addRadioButtonV(mContext, array_shnl, rg_LifeAbility);
		Tools.addRadioButtonV(mContext, array_gznl, rg_WorkingAbility);
		Tools.addRadioButtonV(mContext, array_sjkjdx, rg_TimeOrSpatialOrientation);
		Tools.addRadioButtonV(mContext, array_rwdx, rg_PersonalOrientation);
		Tools.addRadioButtonV(mContext, array_shjwnl, rg_SocialIntercourseAbility);
		Tools.addRadioButtonV(mContext, array_shcyfj, rg_SPLevel);
		for (int i = 0; i < 5; i++) {
			maps.add(i, 0);
		}
		et_SPSumScore.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				score = Integer.parseInt(et_SPSumScore.getText().toString());
				int n = 0;
				if (0 <= score && score <= 2) {
					n = 0;
				} else if (3 <= score && score <= 7) {
					n = 1;
				} else if (8 <= score && score <= 13) {
					n = 2;
				} else {
					n = 3;
				}
				rg_SPLevel.check(n);
			}
		});
		et_SPSumScore.setText(score + "");
		rg_LifeAbility.setOnCheckedChangeListener(this);
		rg_WorkingAbility.setOnCheckedChangeListener(this);
		rg_TimeOrSpatialOrientation.setOnCheckedChangeListener(this);
		rg_PersonalOrientation.setOnCheckedChangeListener(this);
		rg_SocialIntercourseAbility.setOnCheckedChangeListener(this);
		tv_second_title.setOnLongClickListener(this);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title4.setOnLongClickListener(this);
		tv_third_title5.setOnLongClickListener(this);
		tv_third_title6.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_SocialParticipation bi = dbUtils.findFirst(Selector.from(AI_SocialParticipation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_LifeAbility.check(Integer.parseInt(bi.getLifeAbilityExplain()));
				rg_WorkingAbility.check(Integer.parseInt(bi.getWorkingAbilityExplain()));
				rg_TimeOrSpatialOrientation.check(Integer.parseInt(bi.getTimeOrSpatialOrientationExplain()));
				rg_PersonalOrientation.check(Integer.parseInt(bi.getPersonalOrientationExplain()));
				rg_SocialIntercourseAbility.check(Integer.parseInt(bi.getSocialIntercourseAbilityExplain()));
				rg_SPLevel.check(Integer.parseInt(bi.getSPLevel()));
				et_SPSumScore.setText(bi.getSPSumScore());
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
			AI_SocialParticipation bi = null;
			bi = new AI_SocialParticipation();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setLifeAbilityExplain(rg_LifeAbility.getCheckedRadioButtonId() + "");
			bi.setWorkingAbilityExplain(rg_WorkingAbility.getCheckedRadioButtonId() + "");
			bi.setTimeOrSpatialOrientationExplain(rg_TimeOrSpatialOrientation.getCheckedRadioButtonId() + "");
			bi.setPersonalOrientationExplain(rg_PersonalOrientation.getCheckedRadioButtonId() + "");
			bi.setSocialIntercourseAbilityExplain(rg_SocialIntercourseAbility.getCheckedRadioButtonId() + "");
			bi.setSPLevel(rg_SPLevel.getCheckedRadioButtonId() + "");
			bi.setSPSumScore(et_SPSumScore.getText().toString());

			try {
				if (dbUtils.findFirst(Selector.from(AI_SocialParticipation.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_SocialParticipation.class).where("ReportId", "=", reportId));
					bi.setLifeAbilityExplain(rg_LifeAbility.getCheckedRadioButtonId() + "");
					bi.setWorkingAbilityExplain(rg_WorkingAbility.getCheckedRadioButtonId() + "");
					bi.setTimeOrSpatialOrientationExplain(rg_TimeOrSpatialOrientation.getCheckedRadioButtonId() + "");
					bi.setPersonalOrientationExplain(rg_PersonalOrientation.getCheckedRadioButtonId() + "");
					bi.setSocialIntercourseAbilityExplain(rg_SocialIntercourseAbility.getCheckedRadioButtonId() + "");
					bi.setSPLevel(rg_SPLevel.getCheckedRadioButtonId() + "");
					bi.setSPSumScore(et_SPSumScore.getText().toString());
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
		
		switch (group.getId()) {
		case R.id.rg_LifeAbility:
			maps.set(0, checkedId);
			break;
		case R.id.rg_WorkingAbility:
			maps.set(1, checkedId);
			break;
		case R.id.rg_TimeOrSpatialOrientation:
			maps.set(2, checkedId);
			break;
		case R.id.rg_PersonalOrientation:
			maps.set(3, checkedId);
			break;
		case R.id.rg_SocialIntercourseAbility:
			maps.set(4, checkedId);
			break;

		default:
			break;
		}
		score = 0;
		for (int k = 0; k < 5; k++) {
			score += maps.get(k);
		}
		et_SPSumScore.setText(score + "");
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_shcy");
			break;
		case R.id.tv_third_title0:
			intent.putExtra("guid", "ai_shnl");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "ai_gznl");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "ai_sjkjdx");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "ai_rwdx");
			break;
		case R.id.tv_third_title4:
			intent.putExtra("guid", "ai_shjwnl");
			break;
		case R.id.tv_third_title5:
			intent.putExtra("guid", "ai_shcyzf");
			break;
		case R.id.tv_third_title6:
			intent.putExtra("guid", "ai_shcyfj");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
