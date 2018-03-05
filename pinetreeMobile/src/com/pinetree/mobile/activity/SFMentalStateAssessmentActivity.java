package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.pinetree.mobile.bean.SF_MentalStateAssessment;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_sf_mental_state_assessment)
public class SFMentalStateAssessmentActivity extends Activity implements MyBaseInterface, OnItemSelectedListener {
	int[] id = { R.id.activity_sf_mental_state_assessment_part0, R.id.activity_sf_mental_state_assessment_part1, R.id.activity_sf_mental_state_assessment_part2,
			R.id.activity_sf_mental_state_assessment_part3, R.id.activity_sf_mental_state_assessment_part4, R.id.activity_sf_mental_state_assessment_part5,
			R.id.activity_sf_mental_state_assessment_part6, R.id.activity_sf_mental_state_assessment_part7, R.id.activity_sf_mental_state_assessment_part8,
			R.id.activity_sf_mental_state_assessment_part9, R.id.activity_sf_mental_state_assessment_part10, };

	@ResInject(id = R.array.array_MentalStateAssessment, type = ResType.StringArray)
	private String[] array_MentalStateAssessment;
	@ResInject(id = R.array.array_SF_Instruction, type = ResType.StringArray)
	private String[] array_SF_Instruction;
	@ResInject(id = R.array.array_SF_MustScore, type = ResType.StringArray)
	private String[] array_SF_MustScore;
	@ResInject(id = R.array.array_SF_Score_5, type = ResType.StringArray)
	private String[] array_SF_Score_5;
	@ResInject(id = R.array.array_SF_Score_3, type = ResType.StringArray)
	private String[] array_SF_Score_3;
	@ResInject(id = R.array.array_SF_Score_2, type = ResType.StringArray)
	private String[] array_SF_Score_2;
	@ResInject(id = R.array.array_SF_Score_1, type = ResType.StringArray)
	private String[] array_SF_Score_1;
	@ResInject(id = R.array.array_CulturalDegree, type = ResType.StringArray)
	private String[] array_CulturalDegree;
	@ViewInject(R.id.tv_Instruction)
	private TextView tv_Instruction;
	@ViewInject(R.id.tv_MustScore)
	private TextView tv_MustScore;
	@ViewInject(R.id.sp_Score)
	private Spinner sp_Score;
	@ViewInject(R.id.sp_CulturalDegree)
	private Spinner sp_CulturalDegree;
	@ViewInject(R.id.et_SumScore)
	private EditText et_SumScore;
	@ViewInject(R.id.et_AssessmentResult)
	private EditText et_AssessmentResult;
	private ArrayAdapter<?> adapter_score;
	private ArrayAdapter<?> adapter_cultural_degree;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private List<Integer> listScore;
	private List<Spinner> listSpinner;
	private List<Integer> listSpinnerId;
	private int score;

	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		listScore = new ArrayList<Integer>();
		listSpinner = new ArrayList<Spinner>();
		listSpinnerId = new ArrayList<Integer>();
		View view;
		for (int i = 0; i < array_MentalStateAssessment.length; i++) {
			view = findViewById(id[i]);
			tv_Instruction = (TextView) view.findViewById(R.id.tv_Instruction);
			tv_MustScore = (TextView) view.findViewById(R.id.tv_MustScore);
			sp_Score = (Spinner) view.findViewById(R.id.sp_Score);
			tv_Instruction.setText(array_SF_Instruction[i]);
			tv_MustScore.setText(array_SF_MustScore[i]);
			listScore.add(0);
			int spinnerId = Tools.getCurrentTimeMillis() + i;
			sp_Score.setId(spinnerId);
			listSpinner.add(sp_Score);
			listSpinnerId.add(spinnerId);
			switch (i) {
			case 0:
			case 1:
			case 3:
				adapter_score = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_SF_Score_5);
				break;
			case 2:
			case 4:
			case 7:
				adapter_score = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_SF_Score_3);
				break;
			case 6:
			case 8:
			case 9:
			case 10:
				adapter_score = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_SF_Score_1);
				break;
			case 5:
				adapter_score = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_SF_Score_2);
				break;

			default:
				break;
			}
			adapter_score.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp_Score.setAdapter(adapter_score);
			sp_Score.setOnItemSelectedListener(this);
		}
		adapter_cultural_degree = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_CulturalDegree);
		adapter_cultural_degree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_CulturalDegree.setAdapter(adapter_cultural_degree);
		sp_CulturalDegree.setOnItemSelectedListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			SF_MentalStateAssessment bi = dbUtils.findFirst(Selector.from(SF_MentalStateAssessment.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				sp_CulturalDegree.setSelection(Integer.parseInt(bi.getCulturalDegree()));
				et_SumScore.setText(bi.getSumScore());
				et_AssessmentResult.setText(bi.getAssessmentResult());

				View view;
				for (int i = 0; i < array_MentalStateAssessment.length; i++) {
					view = findViewById(id[i]);
					sp_Score = listSpinner.get(i);
					switch (i) {
					case 0:
						sp_Score.setSelection(Integer.parseInt(bi.getTimeOrientaionScore()));
						break;
					case 1:
						sp_Score.setSelection(Integer.parseInt(bi.getAddressOrientaionScore()));
						break;
					case 2:
						sp_Score.setSelection(Integer.parseInt(bi.getMemoryScore()));
						break;
					case 3:
						sp_Score.setSelection(Integer.parseInt(bi.getAttentionAndCalculationScore()));
						break;
					case 4:
						sp_Score.setSelection(Integer.parseInt(bi.getRecollectScore()));
						break;
					case 5:
						sp_Score.setSelection(Integer.parseInt(bi.getNamedScore()));
						break;
					case 6:
						sp_Score.setSelection(Integer.parseInt(bi.getRepeataSentenceScore()));
						break;
					case 7:
						sp_Score.setSelection(Integer.parseInt(bi.getExecutiveOrderScore()));
						break;
					case 8:
						sp_Score.setSelection(Integer.parseInt(bi.getReadingComprehensionScore()));
						break;
					case 9:
						sp_Score.setSelection(Integer.parseInt(bi.getWritingScore()));
						break;
					case 10:
						sp_Score.setSelection(Integer.parseInt(bi.getCompositionAbilityScore()));
						break;

					default:
						break;
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
			SF_MentalStateAssessment bi = null;
			bi = new SF_MentalStateAssessment();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setCulturalDegree(sp_CulturalDegree.getSelectedItemPosition() + "");
			bi.setSumScore(et_SumScore.getText().toString());
			bi.setAssessmentResult(et_AssessmentResult.getText().toString());
			View view;
			for (int i = 0; i < array_MentalStateAssessment.length; i++) {
				view = findViewById(id[i]);
				tv_Instruction = (TextView) view.findViewById(R.id.tv_Instruction);
				sp_Score = listSpinner.get(i);
				switch (i) {
				case 0:
					bi.setTimeOrientaionInstruction(tv_Instruction.getText().toString());
					bi.setTimeOrientaionScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 1:
					bi.setAddressOrientaionInstruction(tv_Instruction.getText().toString());
					bi.setAddressOrientaionScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 2:
					bi.setMemoryInstruction(tv_Instruction.getText().toString());
					bi.setMemoryScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 3:
					bi.setAttentionAndCalculationInstruction(tv_Instruction.getText().toString());
					bi.setAttentionAndCalculationScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 4:
					bi.setRecollectInstruction(tv_Instruction.getText().toString());
					bi.setRecollectScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 5:
					bi.setNamedInstruction(tv_Instruction.getText().toString());
					bi.setNamedScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 6:
					bi.setRepeataSentenceInstruction(tv_Instruction.getText().toString());
					bi.setRepeataSentenceScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 7:
					bi.setExecutiveOrderInstruction(tv_Instruction.getText().toString());
					bi.setExecutiveOrderScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 8:
					bi.setReadingComprehensionInstruction(tv_Instruction.getText().toString());
					bi.setReadingComprehensionScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 9:
					bi.setWritingInstruction(tv_Instruction.getText().toString());
					bi.setWritingScore(sp_Score.getSelectedItemPosition() + "");
					break;
				case 10:
					bi.setCompostionAbilityInstruction(tv_Instruction.getText().toString());
					bi.setCompositionAbilityScore(sp_Score.getSelectedItemPosition() + "");
					break;

				default:
					break;
				}

			}
			try {
				if (dbUtils.findFirst(Selector.from(SF_MentalStateAssessment.class).where("ReportId", "=", reportId)) != null) {

					bi = dbUtils.findFirst(Selector.from(SF_MentalStateAssessment.class).where("ReportId", "=", reportId));
					bi.setCulturalDegree(sp_CulturalDegree.getSelectedItemPosition() + "");
					bi.setSumScore(et_SumScore.getText().toString());
					bi.setAssessmentResult(et_AssessmentResult.getText().toString());
					for (int i = 0; i < array_MentalStateAssessment.length; i++) {
						view = findViewById(id[i]);
						tv_Instruction = (TextView) view.findViewById(R.id.tv_Instruction);
						sp_Score = listSpinner.get(i);
						switch (i) {
						case 0:
							bi.setTimeOrientaionInstruction(tv_Instruction.getText().toString());
							bi.setTimeOrientaionScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 1:
							bi.setAddressOrientaionInstruction(tv_Instruction.getText().toString());
							bi.setAddressOrientaionScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 2:
							bi.setMemoryInstruction(tv_Instruction.getText().toString());
							bi.setMemoryScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 3:
							bi.setAttentionAndCalculationInstruction(tv_Instruction.getText().toString());
							bi.setAttentionAndCalculationScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 4:
							bi.setRecollectInstruction(tv_Instruction.getText().toString());
							bi.setRecollectScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 5:
							bi.setNamedInstruction(tv_Instruction.getText().toString());
							bi.setNamedScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 6:
							bi.setRepeataSentenceInstruction(tv_Instruction.getText().toString());
							bi.setRepeataSentenceScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 7:
							bi.setExecutiveOrderInstruction(tv_Instruction.getText().toString());
							bi.setExecutiveOrderScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 8:
							bi.setReadingComprehensionInstruction(tv_Instruction.getText().toString());
							bi.setReadingComprehensionScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 9:
							bi.setWritingInstruction(tv_Instruction.getText().toString());
							bi.setWritingScore(sp_Score.getSelectedItemPosition() + "");
							break;
						case 10:
							bi.setCompostionAbilityInstruction(tv_Instruction.getText().toString());
							bi.setCompositionAbilityScore(sp_Score.getSelectedItemPosition() + "");
							break;

						default:
							break;
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

	/**
	 *  判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		
		if (parent.getId() == R.id.sp_CulturalDegree) {

		} else {
			for (int i = 0; i < listSpinnerId.size(); i++) {
				if (parent.getId() == listSpinnerId.get(i)) {
					if (parent.getSelectedItemPosition() != 0) {
						listScore.set(i, Integer.parseInt(parent.getItemAtPosition(position).toString()));
					}
				}
			}
		}

		jisuan();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	private void jisuan() {
		score = 0;
		for (int i = 0; i < listScore.size(); i++) {
			score = score + listScore.get(i);
		}
		et_SumScore.setText(score + "");
		if (sp_CulturalDegree.getSelectedItemPosition() == 0) {
			if (score <= 15) {
				et_AssessmentResult.setText("严重痴呆");
			} else if (score <= 22) {
				et_AssessmentResult.setText("痴呆");
			} else {
				et_AssessmentResult.setText("正常");
			}
		} else if (sp_CulturalDegree.getSelectedItemPosition() == 1) {
			if (score <= 15) {
				et_AssessmentResult.setText("严重痴呆");
			} else if (score <= 17) {
				et_AssessmentResult.setText("痴呆");
			} else {
				et_AssessmentResult.setText("正常");
			}
		} else if (sp_CulturalDegree.getSelectedItemPosition() == 2) {
			if (score <= 15) {
				et_AssessmentResult.setText("严重痴呆");
			} else if (score <= 20) {
				et_AssessmentResult.setText("痴呆");
			} else {
				et_AssessmentResult.setText("正常");
			}
		} else if (sp_CulturalDegree.getSelectedItemPosition() == 3) {
			if (score <= 15) {
				et_AssessmentResult.setText("严重痴呆");
			} else if (score <= 24) {
				et_AssessmentResult.setText("痴呆");
			} else {
				et_AssessmentResult.setText("正常");
			}
		}
	}
}