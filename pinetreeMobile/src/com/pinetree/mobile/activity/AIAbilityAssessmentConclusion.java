package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.pinetree.mobile.bean.AI_AbilityAssessmentConclusion;
import com.pinetree.mobile.bean.AI_ActivityofDailyLliving;
import com.pinetree.mobile.bean.AI_MentalState;
import com.pinetree.mobile.bean.AI_SensoryPerceptionAndCommunication;
import com.pinetree.mobile.bean.AI_SocialParticipation;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;
import com.pinetree.mobile.utils.MultiLineRadioGroup;
import com.pinetree.mobile.utils.MultiLineRadioGroup.OnCheckedChangedListener;
import com.pinetree.mobile.utils.MyDatePickerDialog;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@ContentView(R.layout.activity_ai_ability_assessment_conclusion)
public class AIAbilityAssessmentConclusion extends Activity implements
		MyBaseInterface, OnCheckedChangedListener, OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener, OnTouchListener,
		OnLongClickListener{
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	@ViewInject(R.id.tv_second_title)
	private TextView tv_second_title;
	@ViewInject(R.id.mrg_DailyActivityLevel)
	MultiLineRadioGroup mrg_DailyActivityLevel;
	@ViewInject(R.id.mrg_MSLevel)
	MultiLineRadioGroup mrg_MSLevel;
	@ViewInject(R.id.mrg_SPCLevel)
	MultiLineRadioGroup mrg_SPCLevel;
	@ViewInject(R.id.mrg_SPLevel)
	MultiLineRadioGroup mrg_SPLevel;
	@ViewInject(R.id.cb_ChangeBasis0)
	CheckBox cb_ChangeBasis0;
	@ViewInject(R.id.cb_ChangeBasis1)
	CheckBox cb_ChangeBasis1;
	@ViewInject(R.id.cb_ChangeBasis2)
	CheckBox cb_ChangeBasis2;
	@ViewInject(R.id.rg_FirstAdvices)
	private RadioGroup rg_FirstAdvices;
	@ViewInject(R.id.rg_LastLevel)
	RadioGroup rg_LastLevel;
	@ViewInject(R.id.tl_ability)
	TableLayout tl_ability;
	@ViewInject(R.id.et_FirstName)
	EditText et_FirstName;
	@ViewInject(R.id.et_FirstHomePhone)
	EditText et_FirstHomePhone;
	@ViewInject(R.id.et_AssessDate)
	EditText et_AssessDate;
	@ViewInject(R.id.et_VerifyName)
	EditText et_VerifyName;
	@ViewInject(R.id.et_VerifyMobile)
	EditText et_VerifyMobile;
	@ViewInject(R.id.et_VerifyDate)
	EditText et_VerifyDate;
	@ViewInject(R.id.iv_ability_1)
	ImageButton iv_ability_1;
	@ViewInject(R.id.iv_ability_2)
	ImageButton iv_ability_2;
	@ViewInject(R.id.iv_ability_3)
	ImageButton iv_ability_3;
	@ViewInject(R.id.iv_ability_4)
	ImageButton iv_ability_4;

	TableRow tr;
	@ResInject(id = R.array.array_djbgyj, type = ResType.StringArray)
	private String[] array_djbgyj;
	@ResInject(id = R.array.array_nlzzdj, type = ResType.StringArray)
	private String[] array_nlzzdj;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private List<CheckBox> listChangeBasis;
	private List<Integer> maps;
	private List<ImageButton> ibs;
	private int initialLevel = 0;
	private int lastLevel = 0;
	Calendar c = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
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
		ibs = new ArrayList<ImageButton>();
		Tools.addRadioButtonV(mContext, array_nlzzdj, rg_LastLevel);
		mrg_DailyActivityLevel.setItemChecked(0);
		mrg_MSLevel.setItemChecked(0);
		mrg_SPCLevel.setItemChecked(0);
		mrg_SPLevel.setItemChecked(0);
		for (int i = 0; i < 4; i++) {
			maps.add(i, 0);
		}
		ibs.add((ImageButton) findViewById(R.id.iv_ability_1));
		ibs.add((ImageButton) findViewById(R.id.iv_ability_2));
		ibs.add((ImageButton) findViewById(R.id.iv_ability_3));
		ibs.add((ImageButton) findViewById(R.id.iv_ability_4));

		mrg_DailyActivityLevel.setOnCheckChangedListener(this);
		mrg_MSLevel.setOnCheckChangedListener(this);
		mrg_SPCLevel.setOnCheckChangedListener(this);
		mrg_SPLevel.setOnCheckChangedListener(this);
		tl_ability.setStretchAllColumns(true);

		listChangeBasis = new ArrayList<CheckBox>();
		listChangeBasis.add(cb_ChangeBasis0);
		listChangeBasis.add(cb_ChangeBasis1);
		listChangeBasis.add(cb_ChangeBasis2);
		for (int i = 0; i < listChangeBasis.size(); i++) {
			listChangeBasis.get(i).setText(array_djbgyj[i]);
		}
		cb_ChangeBasis0.setOnCheckedChangeListener(this);
		cb_ChangeBasis1.setOnCheckedChangeListener(this);
		cb_ChangeBasis2.setOnCheckedChangeListener(this);


		et_AssessDate.setText(Tools.getCurrDate() + "");
		et_VerifyDate.setText(Tools.getCurrDate() + "");
		et_AssessDate.setOnTouchListener(this);
		et_VerifyDate.setOnTouchListener(this);
		tv_second_title.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_ActivityofDailyLliving bi0 = dbUtils.findFirst(Selector.from(
					AI_ActivityofDailyLliving.class).where("ReportId", "=",
					reportId));
			AI_MentalState bi1 = dbUtils.findFirst(Selector.from(
					AI_MentalState.class).where("ReportId", "=", reportId));
			AI_SensoryPerceptionAndCommunication bi2 = dbUtils
					.findFirst(Selector.from(
							AI_SensoryPerceptionAndCommunication.class).where(
							"ReportId", "=", reportId));
			AI_SocialParticipation bi3 = dbUtils.findFirst(Selector.from(
					AI_SocialParticipation.class).where("ReportId", "=",
					reportId));

			if (bi0 != null && bi0.getID() > 0) {
				mrg_DailyActivityLevel.setItemChecked(Integer.parseInt(bi0
						.getDailyActivityLevel()));
				maps.set(0, Integer.parseInt(bi0.getDailyActivityLevel()));
			}
			if (bi1 != null && bi1.getID() > 0) {
				mrg_MSLevel.setItemChecked(Integer.parseInt(bi1.getMSLevel()));
				maps.set(1, Integer.parseInt(bi1.getMSLevel()));
			}
			if (bi2 != null && bi2.getID() > 0) {
				mrg_SPCLevel
						.setItemChecked(Integer.parseInt(bi2.getSPCLevel()));
				maps.set(2, Integer.parseInt(bi2.getSPCLevel()));
			}
			if (bi3 != null && bi3.getID() > 0) {
				mrg_SPLevel.setItemChecked(Integer.parseInt(bi3.getSPLevel()));
				maps.set(3, Integer.parseInt(bi3.getSPLevel()));
			}

			getInitLevel();
			AI_AbilityAssessmentConclusion bi4 = dbUtils.findFirst(Selector
					.from(AI_AbilityAssessmentConclusion.class).where(
							"ReportId", "=", reportId));
			if (bi4 != null && bi4.getID() > 0) {

				rg_LastLevel.check(Integer.parseInt(bi4.getLastLevel()));
				et_FirstName.setText(bi4.getFirstName());
				et_FirstHomePhone.setText(bi4.getFirstHomePhone());
				et_AssessDate.setText(bi4.getAssessDate());
				et_VerifyName.setText(bi4.getVerifyName());
				et_VerifyMobile.setText(bi4.getVerifyMobile());
				et_VerifyDate.setText(bi4.getVerifyDate());
				if (!bi4.getChangeBasis().equals("")) {
					rg_FirstAdvices
							.check(Integer.parseInt(bi4.getChangeBasis()));
					change(Integer.parseInt(bi4.getChangeBasis()));
				}
				if (!bi4.getChangeBasis().equals("")) {
					String[] array_temp = bi4.getChangeBasis().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listChangeBasis.get(n).setChecked(true);
						}
					}
				}
			}
			YangLaoServiceAssessmentReport bi5 = dbUtils.findFirst(Selector
					.from(YangLaoServiceAssessmentReport.class).where(
							"ReportId", "=", reportId));

			if (bi5 != null && bi5.getID() > 0) {
				et_AssessDate.setText(bi5.getReportDate());
				et_AssessDate.setEnabled(false);
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
			AI_AbilityAssessmentConclusion bi = null;
			bi = new AI_AbilityAssessmentConclusion();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setLastLevel(rg_LastLevel.getCheckedRadioButtonId() + "");
			bi.setFirstName(et_FirstName.getText().toString());
			bi.setFirstHomePhone(et_FirstHomePhone.getText().toString());
			bi.setSecondName("");
			bi.setSecondHomePhone("");
			bi.setAssessDate(et_AssessDate.getText().toString());
			bi.setVerifyName(et_VerifyName.getText().toString());
			bi.setVerifyMobile(et_VerifyMobile.getText().toString());
			bi.setVerifyDate(et_VerifyDate.getText().toString());
			bi.setChangeBasis(Tools.getCheckBoxStatus(listChangeBasis));
			bi.setInitialLevel(initialLevel + "");

			try {
				if (dbUtils.findFirst(Selector.from(
						AI_AbilityAssessmentConclusion.class).where("ReportId",
						"=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(
							AI_AbilityAssessmentConclusion.class).where(
							"ReportId", "=", reportId));
					bi.setLastLevel(rg_LastLevel.getCheckedRadioButtonId() + "");
					bi.setFirstName(et_FirstName.getText().toString());
					bi.setFirstHomePhone(et_FirstHomePhone.getText().toString());
					bi.setSecondName("");
					bi.setSecondHomePhone("");
					bi.setAssessDate(et_AssessDate.getText().toString());
					bi.setVerifyName(et_VerifyName.getText().toString());
					bi.setVerifyMobile(et_VerifyMobile.getText().toString());
					bi.setVerifyDate(et_VerifyDate.getText().toString());
					bi.setChangeBasis(Tools.getCheckBoxStatus(listChangeBasis));
					bi.setInitialLevel(initialLevel + "");
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
	public void onItemChecked(MultiLineRadioGroup group, int position,
			boolean checked) {
		
		int checkItem = group.getCheckedItems()[0];
		 if (cb_ChangeBasis0.isChecked()) {
		 if ((group.getCheckedItems()[0] + 1) >= 3) {
		 checkItem = 3;
		 } else {
		 checkItem += 1;
		 }
		 }
		if (cb_ChangeBasis1.isChecked()) {
			if ((group.getCheckedItems()[0] + 1) >= 3) {
				checkItem = 3;
			} else {
				checkItem += 1;
			}
		}
		if (cb_ChangeBasis2.isChecked()) {
			checkItem = 3;
		}
		switch (group.getId()) {
		case R.id.mrg_DailyActivityLevel:
			maps.set(0, group.getCheckedItems()[0]);
			break;
		case R.id.mrg_MSLevel:
			maps.set(1, group.getCheckedItems()[0]);
			break;
		case R.id.mrg_SPCLevel:
			maps.set(2, group.getCheckedItems()[0]);
			break;
		case R.id.mrg_SPLevel:
			maps.set(3, group.getCheckedItems()[0]);
			break;
		default:
			break;
		}
		getInitLevel();
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		
		int n=0;
		if(arg1){
			for(int i=0;i<listChangeBasis.size();i++){
				listChangeBasis.get(i).setChecked(false);
				if(arg0.getId()==listChangeBasis.get(i).getId()){
					n=i;
				}
			}
			listChangeBasis.get(n).setChecked(true);
		}
		
		switch (arg0.getId()) {
		case R.id.cb_ChangeBasis0:
		case R.id.cb_ChangeBasis1:
			if (arg0.isChecked() == true) {
				if(initialLevel+1>=3){
					rg_LastLevel.check(3);
				}else{
				 rg_LastLevel.check(initialLevel+1);
				}
			}else{
				rg_LastLevel.check(initialLevel);
			}
			break;
		case R.id.cb_ChangeBasis2:
			if (arg0.isChecked() == true) {
				rg_LastLevel.check(3);
			} else {
				rg_LastLevel.check(initialLevel);
			}
			break;
			
		default:
			break;
		}
	}

	
	@SuppressLint("NewApi") 
	private void getInitLevel() {
		if (maps.get(0) == 0) {
			if (maps.get(1) == 0 && maps.get(2) == 0 && (maps.get(3)>=0 && maps.get(3)<2)) {
				initialLevel = 0;
			}else {
				initialLevel = 1;
			}
		}
		if (maps.get(0) == 1) {
			if ((maps.get(1) >=2 && maps.get(1) <=3) && (maps.get(2) >= 2 && maps.get(2) <= 3) && (maps.get(3) >=2 && maps.get(3)<=3) || maps.get(1) ==3
					|| maps.get(2) ==3 || maps.get(3) ==3) {
				initialLevel = 2;
			}else {
				initialLevel = 1;
			}
		}
		if (maps.get(0) == 2) {
			if ((maps.get(1) >=2 && maps.get(1) <=3) && (maps.get(2) >= 2 && maps.get(2) <= 3) && (maps.get(3) >=2 && maps.get(3)<=3) || maps.get(1) ==3
					|| maps.get(2) ==3 || maps.get(3) ==3) {
				initialLevel = 3;
			}else {
				initialLevel = 2;
			}
		}
		if (maps.get(0) == 3) {
			initialLevel = 3;
		}
		
		switch (initialLevel) {
		case 0:
			ibs.get(0).setBackground(
					getResources().getDrawable(R.drawable.iv_ability_1_p));
			ibs.get(1).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_2_selecter));
			ibs.get(2).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_3_selecter));
			ibs.get(3).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_4_selecter));
			break;
		case 1:
			ibs.get(0).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_1_selecter));
			ibs.get(1).setBackground(
					getResources().getDrawable(R.drawable.iv_ability_2_p));
			ibs.get(2).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_3_selecter));
			ibs.get(3).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_4_selecter));
			break;
		case 2:
			ibs.get(0).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_1_selecter));
			ibs.get(1).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_2_selecter));
			ibs.get(2).setBackground(
					getResources().getDrawable(R.drawable.iv_ability_3_p));
			ibs.get(3).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_4_selecter));
			break;
		case 3:
			ibs.get(0).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_1_selecter));
			ibs.get(1).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_2_selecter));
			ibs.get(2).setBackground(
					getResources()
							.getDrawable(R.drawable.iv_ability_3_selecter));
			ibs.get(3).setBackground(
					getResources().getDrawable(R.drawable.iv_ability_4_p));
			break;
		}
//		change(rg_FirstAdvices.getCheckedRadioButtonId());
		int checkItem=initialLevel;
		 if (cb_ChangeBasis0.isChecked()) {
		 if ((initialLevel + 1) >= 3) {
		 checkItem = 3;
		 } else {
		 checkItem += 1;
		 }
		 }
		 if (cb_ChangeBasis1.isChecked()) {
		 if ((initialLevel + 1) >= 3) {
		 checkItem = 3;
		 } else {
		 checkItem += 1;
		 }
		 }
		 if (cb_ChangeBasis2.isChecked()) {
		 checkItem = 3;
		 }
		 rg_LastLevel.check(checkItem);
			lastLevel = checkItem;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		change(checkedId);
	}

	private void change(int checkedId) {
		int checkItem = rg_LastLevel.getCheckedRadioButtonId();
		if (checkedId == 0) {
			if ((initialLevel + 1) >= 3) {
				checkItem = 3;
			} else {
				checkItem += 1;
			}
		}
		if (checkedId == 1) {
			if ((initialLevel + 1) >= 3) {
				checkItem = 3;
			} else {
				checkItem += 1;
			}
		}
		if (checkedId == 2) {
			checkItem = 3;
		}
		rg_LastLevel.check(checkItem);
		lastLevel = checkItem;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return false;
		case MotionEvent.ACTION_UP:
			switch (arg0.getId()) {
			case R.id.et_AssessDate:
				new MyDatePickerDialog(mContext, et_AssessDate, c)
						.showMyDatePickerDialog();
				break;
			case R.id.et_VerifyDate:
				new MyDatePickerDialog(mContext, et_VerifyDate, c)
						.showMyDatePickerDialog();
				break;
			default:
				break;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_nlpgjl");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}

}
