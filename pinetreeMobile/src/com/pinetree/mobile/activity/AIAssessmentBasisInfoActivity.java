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
import com.pinetree.mobile.bean.AI_AssessmentBasisInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_assessment_basis_info)
public class AIAssessmentBasisInfoActivity extends Activity implements MyBaseInterface, 
	OnCheckedChangeListener, android.widget.CompoundButton.OnCheckedChangeListener ,
	OnLongClickListener{
	@ViewInject(R.id.tv_third_title1)
	private TextView tv_third_title1;
	@ViewInject(R.id.tv_third_title2)
	private TextView tv_third_title2;
	@ViewInject(R.id.rg_isSelf)
	private RadioGroup rg_isSelf;
	@ViewInject(R.id.cb_RelationWithOldPeople0)
	private CheckBox cb_RelationWithOldPeople0;
	@ViewInject(R.id.cb_RelationWithOldPeople1)
	private CheckBox cb_RelationWithOldPeople1;
	@ViewInject(R.id.cb_RelationWithOldPeople2)
	private CheckBox cb_RelationWithOldPeople2;
	@ViewInject(R.id.cb_RelationWithOldPeople3)
	private CheckBox cb_RelationWithOldPeople3;
	private List<CheckBox> listRelationWithOldPeople;
	@ViewInject(R.id.rg_AssessmentReason)
	private RadioGroup rg_AssessmentReason;
	@ViewInject(R.id.et_Name)
	private EditText et_Name;
	@ViewInject(R.id.et_Age)
	private EditText et_Age;
	@ViewInject(R.id.et_Telephone)
	private EditText et_Telephone;
	@ViewInject(R.id.et_Screening)
	private EditText et_Screening;
	@ViewInject(R.id.et_others0)
	private EditText et_others0;
	@ResInject(id = R.array.array_sfbr, type = ResType.StringArray)
	private String[] array_sfbr;
	@ResInject(id = R.array.array_ylrgx, type = ResType.StringArray)
	private String[] array_ylrgx;
	@ResInject(id = R.array.array_pgyy, type = ResType.StringArray)
	private String[] array_pgyy;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		mContext = this;
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		reportId = sharedPreferencesUtil.get("reportId");
		Tools.addRadioButtonH(mContext, array_sfbr, rg_isSelf);
		Tools.addRadioButtonV(mContext, array_pgyy, rg_AssessmentReason);
		rg_AssessmentReason.setOnCheckedChangeListener(this);
		
		listRelationWithOldPeople = new ArrayList<CheckBox>();
		listRelationWithOldPeople.add(cb_RelationWithOldPeople0);
		listRelationWithOldPeople.add(cb_RelationWithOldPeople1);
		listRelationWithOldPeople.add(cb_RelationWithOldPeople2);
		listRelationWithOldPeople.add(cb_RelationWithOldPeople3);
		for (int i = 0; i < listRelationWithOldPeople.size(); i++) {
			listRelationWithOldPeople.get(i).setText(array_ylrgx[i]);
			listRelationWithOldPeople.get(i).setOnCheckedChangeListener(this);
		}
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_AssessmentBasisInformation bi = dbUtils.findFirst(Selector.from(AI_AssessmentBasisInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_isSelf.check(Integer.parseInt(bi.getIsSelf()));
				rg_AssessmentReason.check(Integer.parseInt(bi.getAssessmentReason()));
				et_Name.setText(bi.getName());
				et_Age.setText(bi.getAge());
				et_Telephone.setText(bi.getTelephone());
				et_Screening.setText(bi.getScreening());
				et_others0.setText(bi.getAssessmentReasonOther());
				if (!bi.getRelationWithOldPeople().equals("")) {
					String[] array_temp = bi.getRelationWithOldPeople().split(",");
					if (array_temp.length > 0) {
						for (int i = 0; i < array_temp.length; i++) {
							int n = Integer.parseInt(array_temp[i]);
							listRelationWithOldPeople.get(n).setChecked(true);
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
			AI_AssessmentBasisInformation bi = null;
			bi = new AI_AssessmentBasisInformation();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setIsSelf(rg_isSelf.getCheckedRadioButtonId() + "");
			bi.setRelationWithOldPeople(Tools.getCheckBoxStatus(listRelationWithOldPeople));
			bi.setName(et_Name.getText().toString());
			bi.setAge(et_Age.getText().toString());
			bi.setTelephone(et_Telephone.getText().toString());
			bi.setScreening(et_Screening.getText().toString());
			bi.setAssessmentReason(rg_AssessmentReason.getCheckedRadioButtonId() + "");
			bi.setAssessmentReasonOther(et_others0.getText().toString());
			try {
				if (dbUtils.findFirst(Selector.from(AI_AssessmentBasisInformation.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_AssessmentBasisInformation.class).where("ReportId", "=", reportId));
					bi.setIsSelf(rg_isSelf.getCheckedRadioButtonId() + "");
					bi.setRelationWithOldPeople(Tools.getCheckBoxStatus(listRelationWithOldPeople));
					bi.setName(et_Name.getText().toString());
					bi.setAge(et_Age.getText().toString());
					bi.setTelephone(et_Telephone.getText().toString());
					bi.setScreening(et_Screening.getText().toString());
					bi.setAssessmentReason(rg_AssessmentReason.getCheckedRadioButtonId() + "");
					bi.setAssessmentReasonOther(et_others0.getText().toString());
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
		
		if (checkedId == 5)
			et_others0.setVisibility(View.VISIBLE);
		else {
			et_others0.setText("");
			et_others0.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		int n=0;
		if(isChecked){
			for(int i=0;i<listRelationWithOldPeople.size();i++){
				listRelationWithOldPeople.get(i).setChecked(false);
				if(buttonView.getId()==listRelationWithOldPeople.get(i).getId()){
					n=i;
				}
				
			}
			listRelationWithOldPeople.get(n).setChecked(true);
		}
		
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_third_title1:
			intent.putExtra("guid", "ai_xxtgz");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "ai_pgyy");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
