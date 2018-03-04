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
import com.pinetree.mobile.bean.AI_SensoryPerceptionAndCommunication;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_sensory_perception)
public class AISensoryPerceptionAndCommunicationActivity extends Activity implements MyBaseInterface,
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
	@ViewInject(R.id.rg_ConsciousnessLevel)
	private RadioGroup rg_ConsciousnessLevel;
	@ViewInject(R.id.rg_Vision)
	private RadioGroup rg_Vision;
	@ViewInject(R.id.rg_Listening)
	private RadioGroup rg_Listening;
	@ViewInject(R.id.rg_Communication)
	private RadioGroup rg_Communication;
	@ViewInject(R.id.rg_SPCLevel)
	private RadioGroup rg_SPCLevel;
	@ViewInject(R.id.et_SPCSumScore)
	private EditText et_SPCSumScore;
	private int score = 0;
	private List<Integer> maps;

	@ResInject(id = R.array.array_yssp, type = ResType.StringArray)
	private String[] array_yssp;
	@ResInject(id = R.array.array_sl, type = ResType.StringArray)
	private String[] array_sl;
	@ResInject(id = R.array.array_tl, type = ResType.StringArray)
	private String[] array_tl;
	@ResInject(id = R.array.array_gtjl, type = ResType.StringArray)
	private String[] array_gtjl;
	@ResInject(id = R.array.array_gzjygtfj, type = ResType.StringArray)
	private String[] array_gzjygtfj;
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
		Tools.addRadioButtonV(mContext, array_yssp, rg_ConsciousnessLevel);
		Tools.addRadioButtonV(mContext, array_sl, rg_Vision);
		Tools.addRadioButtonV(mContext, array_tl, rg_Listening);
		Tools.addRadioButtonV(mContext, array_gtjl, rg_Communication);
		Tools.addRadioButtonV(mContext, array_gzjygtfj, rg_SPCLevel);
		maps = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			maps.add(i, 0);
		}
		et_SPCSumScore.setText(score + "");
		rg_ConsciousnessLevel.setOnCheckedChangeListener(this);
		rg_Vision.setOnCheckedChangeListener(this);
		rg_Listening.setOnCheckedChangeListener(this);
		rg_Communication.setOnCheckedChangeListener(this);
		
		tv_second_title.setOnLongClickListener(this);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title4.setOnLongClickListener(this);
		tv_third_title5.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_SensoryPerceptionAndCommunication bi = dbUtils.findFirst(Selector.from(AI_SensoryPerceptionAndCommunication.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_ConsciousnessLevel.check(Integer.parseInt(bi.getConsciousnessLevelExplain()));
				rg_Vision.check(Integer.parseInt(bi.getVisionExplain()));
				rg_Listening.check(Integer.parseInt(bi.getListeningExplain()));
				rg_Communication.check(Integer.parseInt(bi.getCommunicationExplain()));
				rg_SPCLevel.check(Integer.parseInt(bi.getSPCLevel()));
				et_SPCSumScore.setText(bi.getSPCSumScore());
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
			AI_SensoryPerceptionAndCommunication bi = null;
			bi = new AI_SensoryPerceptionAndCommunication();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setConsciousnessLevelExplain(rg_ConsciousnessLevel.getCheckedRadioButtonId() + "");
			bi.setVisionExplain(rg_Vision.getCheckedRadioButtonId() + "");
			bi.setListeningExplain(rg_Listening.getCheckedRadioButtonId() + "");
			bi.setCommunicationExplain(rg_Communication.getCheckedRadioButtonId() + "");
			bi.setSPCLevel(rg_SPCLevel.getCheckedRadioButtonId() + "");
			bi.setSPCSumScore(et_SPCSumScore.getText().toString());

			try {
				if (dbUtils.findFirst(Selector.from(AI_SensoryPerceptionAndCommunication.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_SensoryPerceptionAndCommunication.class).where("ReportId", "=", reportId));
					bi.setConsciousnessLevelExplain(rg_ConsciousnessLevel.getCheckedRadioButtonId() + "");
					bi.setVisionExplain(rg_Vision.getCheckedRadioButtonId() + "");
					bi.setListeningExplain(rg_Listening.getCheckedRadioButtonId() + "");
					bi.setCommunicationExplain(rg_Communication.getCheckedRadioButtonId() + "");
					bi.setSPCLevel(rg_SPCLevel.getCheckedRadioButtonId() + "");
					bi.setSPCSumScore(et_SPCSumScore.getText().toString());
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
		case R.id.rg_ConsciousnessLevel:
			maps.set(0, checkedId);
			break;
		case R.id.rg_Vision:
			maps.set(1, checkedId);
			break;
		case R.id.rg_Listening:
			maps.set(2, checkedId);
			break;
		case R.id.rg_Communication:
			maps.set(3, checkedId);
			break;

		default:
			break;
		}
		score = 0;
		for (int k = 0; k < 4; k++) {
			score += maps.get(k);
		}
		et_SPCSumScore.setText(score + "");

		
		if(maps.get(0) == 0 &&(maps.get(1) == 0 || maps.get(1) == 1)&&(maps.get(2) == 0 || maps.get(2) == 1) && maps.get(3) == 0)
		{
			rg_SPCLevel.check(0);
		}
		else if((maps.get(0) == 0 && (maps.get(1) == 2 || maps.get(2) == 2))||maps.get(3) == 1)
		{
			rg_SPCLevel.check(1);
		}
		else if((maps.get(0) == 0 && (maps.get(1) == 3 || maps.get(2) == 3))||maps.get(3) == 2||(maps.get(0) == 1&&(maps.get(1) == 3||maps.get(1) == 4||maps.get(2) == 3||maps.get(2) == 4)&&(maps.get(3) == 2||maps.get(3) == 3)))
		{
			rg_SPCLevel.check(2);
		}
		else
		{
			rg_SPCLevel.check(3);
		}
		
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_gzjygt");
			break;
		case R.id.tv_third_title0:
			intent.putExtra("guid", "ai_yssp");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "ai_sl");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "ai_tl");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "ai_gtjl");
			break;
		case R.id.tv_third_title4:
			intent.putExtra("guid", "ai_gzjygtzf");
			break;
		case R.id.tv_third_title5:
			intent.putExtra("guid", "ai_gzjygtfj");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
