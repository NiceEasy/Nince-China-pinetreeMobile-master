package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.pinetree.mobile.bean.SF_PersonalSupplement;
import com.pinetree.mobile.utils.MultiLineRadioGroup;
import com.pinetree.mobile.utils.MultiLineRadioGroup.OnCheckedChangedListener;
import com.pinetree.mobile.utils.SharedPreferencesUtil;

@ContentView(R.layout.activity_sf_personal_supplement)
public class SFPersonalSupplementActivity extends Activity implements MyBaseInterface {
	@ViewInject(R.id.sp_OccupationPreRetirement)
	private Spinner sp_OccupationPreRetirement;
	@ViewInject(R.id.sp_RoomBedMattressSoftness)
	private Spinner sp_RoomBedMattressSoftness;
	@ViewInject(R.id.sp_RoomBedMattress)
	private Spinner sp_RoomBedMattress;
	@ViewInject(R.id.sp_RoomBedTidy)
	private Spinner sp_RoomBedTidy;
	@ViewInject(R.id.sp_RoomEnviromentAcuteAngle)
	private Spinner sp_RoomEnviromentAcuteAngle;
	@ViewInject(R.id.sp_RoomEnviromentStrong)
	private Spinner sp_RoomEnviromentStrong;
	@ViewInject(R.id.sp_RoomEnviromentAction)
	private Spinner sp_RoomEnviromentAction;
	@ViewInject(R.id.sp_RoomEnviromentAntiskid)
	private Spinner sp_RoomEnviromentAntiskid;
	@ViewInject(R.id.sp_RoomEnviromentBarrier)
	private Spinner sp_RoomEnviromentBarrier;
	@ViewInject(R.id.sp_RoomEnviromentTidy)
	private Spinner sp_RoomEnviromentTidy;
	@ViewInject(R.id.sp_RoomEnviromentSundries)
	private Spinner sp_RoomEnviromentSundries;
	@ViewInject(R.id.sp_RoomEnviromentDoorsill)
	private Spinner sp_RoomEnviromentDoorsill;
	@ViewInject(R.id.sp_RoomDressingClean)
	private Spinner sp_RoomDressingClean;
	@ViewInject(R.id.sp_RoomDressingFit)
	private Spinner sp_RoomDressingFit;
	@ViewInject(R.id.sp_RoomDressingWarm)
	private Spinner sp_RoomDressingWarm;
	@ViewInject(R.id.sp_VitalSignsBloodGlucoseMeasurementTime)
	private Spinner sp_VitalSignsBloodGlucoseMeasurementTime;

	@ViewInject(R.id.et_OccupationPreRetirementOther)
	private EditText et_OccupationPreRetirementOther;
	@ViewInject(R.id.et_NursingStatus)
	private EditText et_NursingStatus;
	@ViewInject(R.id.et_AssistantToolOtherInfo)
	private EditText et_AssistantToolOtherInfo;
	@ViewInject(R.id.et_HomeLighting)
	private EditText et_HomeLighting;
	@ViewInject(R.id.et_HomeAir)
	private EditText et_HomeAir;
	@ViewInject(R.id.et_HomeDryHumidity)
	private EditText et_HomeDryHumidity;
	@ViewInject(R.id.et_HomeTemperature)
	private EditText et_HomeTemperature;
	@ViewInject(R.id.et_HomeSmell)
	private EditText et_HomeSmell;

	@ViewInject(R.id.mrg_ChildrenStatus)
	private MultiLineRadioGroup mrg_ChildrenStatus;
	@ViewInject(R.id.mrg_AssistantToolAction)
	private MultiLineRadioGroup mrg_AssistantToolAction;
	@ViewInject(R.id.mrg_AssistantToolOrthopedic)
	private MultiLineRadioGroup mrg_AssistantToolOrthopedic;
	@ViewInject(R.id.mrg_AssistantToolOther)
	private MultiLineRadioGroup mrg_AssistantToolOther;
	@ViewInject(R.id.mrg_RoomToilet)
	private MultiLineRadioGroup mrg_RoomToilet;
	@ViewInject(R.id.mrg_RoomBed)
	private MultiLineRadioGroup mrg_RoomBed;
	@ViewInject(R.id.mrg_RoomGetupIntheNight)
	private MultiLineRadioGroup mrg_RoomGetupIntheNight;
	@ViewInject(R.id.mrg_DietCondition)
	private MultiLineRadioGroup mrg_DietCondition;
	@ViewInject(R.id.mrg_SleepCondition)
	private MultiLineRadioGroup mrg_SleepCondition;
	@ViewInject(R.id.mrg_UrinationCondition)
	private MultiLineRadioGroup mrg_UrinationCondition;
	@ViewInject(R.id.mrg_PsychologyCondition)
	private MultiLineRadioGroup mrg_PsychologyCondition;
	@ViewInject(R.id.mrg_PersonalHygiene)
	private MultiLineRadioGroup mrg_PersonalHygiene;
	@ViewInject(R.id.mrg_VitalSignsPulseConfition)
	private MultiLineRadioGroup mrg_VitalSignsPulseConfition;
	@ViewInject(R.id.mrg_VitalSignsBreathingCondition)
	private MultiLineRadioGroup mrg_VitalSignsBreathingCondition;
	@ViewInject(R.id.mrg_VitalSignsBreathingCough)
	private MultiLineRadioGroup mrg_VitalSignsBreathingCough;
	@ViewInject(R.id.mrg_VitalSignsPressureLocation)
	private MultiLineRadioGroup mrg_VitalSignsPressureLocation;
	@ViewInject(R.id.mrg_PersonsFace)
	private MultiLineRadioGroup mrg_PersonsFace;
	@ViewInject(R.id.mrg_SkinEdima)
	private MultiLineRadioGroup mrg_SkinEdima;
	@ViewInject(R.id.mrg_SkinPressureSoreCondition)
	private MultiLineRadioGroup mrg_SkinPressureSoreCondition;
	@ViewInject(R.id.mrg_SkinPresstheRedCondition)
	private MultiLineRadioGroup mrg_SkinPresstheRedCondition;
	@ViewInject(R.id.mrg_ThoraxChest)
	private MultiLineRadioGroup mrg_ThoraxChest;
	@ViewInject(R.id.mrg_Abdomen)
	private MultiLineRadioGroup mrg_Abdomen;

	@ViewInject(R.id.cb_RoomEnviromentOther)
	private CheckBox cb_RoomEnviromentOther;
	@ViewInject(R.id.cb_DefecationConstipationAndDiarrhea)
	private CheckBox cb_DefecationConstipationAndDiarrhea;

	@ViewInject(R.id.et_RoomEnviromentOtherInfo)
	private EditText et_RoomEnviromentOtherInfo;
	@ViewInject(R.id.et_RoomGetupIntheNightRemark)
	private EditText et_RoomGetupIntheNightRemark;
	@ViewInject(R.id.et_AssistantToolDemand)
	private EditText et_AssistantToolDemand;
	@ViewInject(R.id.et_DrugUseCondition)
	private EditText et_DrugUseCondition;

	@ViewInject(R.id.et_DCDiet)
	private EditText et_DCDiet;
	@ViewInject(R.id.et_DietDrinkingWater)
	private EditText et_DietDrinkingWater;
	@ViewInject(R.id.et_DietVegetables)
	private EditText et_DietVegetables;
	@ViewInject(R.id.et_DietProtein)
	private EditText et_DietProtein;

	@ViewInject(R.id.et_SleepReason)
	private EditText et_SleepReason;
	@ViewInject(R.id.et_SleepDrugUse)
	private EditText et_SleepDrugUse;
	@ViewInject(R.id.et_SleepInfluence)
	private EditText et_SleepInfluence;
	@ViewInject(R.id.et_SleepOther)
	private EditText et_SleepOther;
	@ViewInject(R.id.et_DefecationCondition)
	private EditText et_DefecationCondition;
	@ViewInject(R.id.et_DefecationReason)
	private EditText et_DefecationReason;
	@ViewInject(R.id.et_DefecationDrugUse)
	private EditText et_DefecationDrugUse;

	@ViewInject(R.id.et_UrinationReason)
	private EditText et_UrinationReason;
	@ViewInject(R.id.et_UrinationDrugUse)
	private EditText et_UrinationDrugUse;
	@ViewInject(R.id.et_UrinationInfluence)
	private EditText et_UrinationInfluence;

	@ViewInject(R.id.et_PsychologyReason)
	private EditText et_PsychologyReason;
	@ViewInject(R.id.et_PsychologyDrugUse)
	private EditText et_PsychologyDrugUse;
	@ViewInject(R.id.et_PsychologyInfluence)
	private EditText et_PsychologyInfluence;

	@ViewInject(R.id.et_VitalSignsBloodGlucoseMeasurementValue)
	private EditText et_VitalSignsBloodGlucoseMeasurementValue;

	@ViewInject(R.id.et_VitalSignsPulseTimes)
	private EditText et_VitalSignsPulseTimes;

	@ViewInject(R.id.et_VitalSignsBreathingTimes)
	private EditText et_VitalSignsBreathingTimes;
	@ViewInject(R.id.et_VitalSignsSystolicPressure)
	private EditText et_VitalSignsSystolicPressure;
	@ViewInject(R.id.et_VitalSignsDiastolicBloodPressure)
	private EditText et_VitalSignsDiastolicBloodPressure;
	@ViewInject(R.id.et_VitalSignsBodyTemperature)
	private EditText et_VitalSignsBodyTemperature;

	@ViewInject(R.id.et_SkinOtherPosition)
	private EditText et_SkinOtherPosition;

	@ViewInject(R.id.et_SkinPressureSorePosition)
	private EditText et_SkinPressureSorePosition;
	@ViewInject(R.id.et_SkinPressureSoreSize)
	private EditText et_SkinPressureSoreSize;
	@ViewInject(R.id.et_SkinPressureSoreExudationCondition)
	private EditText et_SkinPressureSoreExudationCondition;
	@ViewInject(R.id.et_SkinPresstheRedPosition)
	private EditText et_SkinPresstheRedPosition;
	@ViewInject(R.id.et_SkinPresstheRedSize)
	private EditText et_SkinPresstheRedSize;
	@ViewInject(R.id.et_ThoraxChestOther)
	private EditText et_ThoraxChestOther;
	@ViewInject(R.id.et_AbdomenOther)
	private EditText et_AbdomenOther;

	@ResInject(id = R.array.array_OccupationPreRetirement, type = ResType.StringArray)
	private String[] array_OccupationPreRetirement;
	@ResInject(id = R.array.array_ChildrenStatus, type = ResType.StringArray)
	private String[] array_ChildrenStatus;
	@ResInject(id = R.array.array_AssistantToolOther, type = ResType.StringArray)
	private String[] array_AssistantToolOther;
	@ResInject(id = R.array.array_RoomBedMattressSoftness, type = ResType.StringArray)
	private String[] array_RoomBedMattressSoftness;
	@ResInject(id = R.array.array_RoomBedTidy, type = ResType.StringArray)
	private String[] array_RoomBedTidy;
	@ResInject(id = R.array.array_YesOrNo, type = ResType.StringArray)
	private String[] array_YesOrNo;
	@ResInject(id = R.array.array_PleaseYesOrNo, type = ResType.StringArray)
	private String[] array_PleaseYesOrNo;
	@ResInject(id = R.array.array_VitalSignsBloodGlucoseMeasurementTime, type = ResType.StringArray)
	private String[] array_VitalSignsBloodGlucoseMeasurementTime;

	@ResInject(id = R.array.array_AssistantToolAction, type = ResType.StringArray)
	private String[] array_AssistantToolAction;
	@ResInject(id = R.array.array_AssistantToolOrthopedic, type = ResType.StringArray)
	private String[] array_AssistantToolOrthopedic;
	@ResInject(id = R.array.array_RoomToilet, type = ResType.StringArray)
	private String[] array_RoomToilet;
	@ResInject(id = R.array.array_RoomBed, type = ResType.StringArray)
	private String[] array_RoomBed;
	@ResInject(id = R.array.array_RoomGetupIntheNight, type = ResType.StringArray)
	private String[] array_RoomGetupIntheNight;
	@ResInject(id = R.array.array_DietCondition, type = ResType.StringArray)
	private String[] array_DietCondition;
	@ResInject(id = R.array.array_SleepCondition, type = ResType.StringArray)
	private String[] array_SleepCondition;
	@ResInject(id = R.array.array_UrinationCondition, type = ResType.StringArray)
	private String[] array_UrinationCondition;
	@ResInject(id = R.array.array_PsychologyCondition, type = ResType.StringArray)
	private String[] array_PsychologyCondition;
	@ResInject(id = R.array.array_PersonalHygiene, type = ResType.StringArray)
	private String[] array_PersonalHygiene;
	@ResInject(id = R.array.array_PersonsFace, type = ResType.StringArray)
	private String[] array_PersonsFace;
	@ResInject(id = R.array.array_ThoraxChest, type = ResType.StringArray)
	private String[] array_ThoraxChest;
	@ResInject(id = R.array.array_Abdomen, type = ResType.StringArray)
	private String[] array_Abdomen;
	@ResInject(id = R.array.array_VitalSignsPulseConfition, type = ResType.StringArray)
	private String[] array_VitalSignsPulseConfition;
	@ResInject(id = R.array.array_VitalSignsBreathingCondition, type = ResType.StringArray)
	private String[] array_VitalSignsBreathingCondition;
	@ResInject(id = R.array.array_VitalSignsBreathingCough, type = ResType.StringArray)
	private String[] array_VitalSignsBreathingCough;
	@ResInject(id = R.array.array_VitalSignsPressureLocation, type = ResType.StringArray)
	private String[] array_VitalSignsPressureLocation;
	@ResInject(id = R.array.array_SkinEdima, type = ResType.StringArray)
	private String[] array_SkinEdima;
	@ResInject(id = R.array.array_SkinPressureSoreCondition, type = ResType.StringArray)
	private String[] array_SkinPressureSoreCondition;

	// @ViewInject(R.id.et_MFASittingBalance)
	// private EditText et_MFASittingBalance;
	// @ViewInject(R.id.et_MFAPositionBalance)
	// private EditText et_MFAPositionBalance;
	// @ViewInject(R.id.et_MFAHoldenWalkingAbility)
	// private EditText et_MFAHoldenWalkingAbility;
	// @ViewInject(R.id.et_MFAMuscleForce)
	// private EditText et_MFAMuscleForce;
	// @ViewInject(R.id.et_MFAMuscularTension)
	// private EditText et_MFAMuscularTension;
	// @ViewInject(R.id.et_MFAPainScore)
	// private EditText et_MFAPainScore;
	// @ViewInject(R.id.et_RiskLevel)
	// private EditText et_RiskLevel;

	@ViewInject(R.id.lay_OccupationPreRetirementOther)
	private LinearLayout lay_OccupationPreRetirementOther;
	@ViewInject(R.id.lay_RoomGetupIntheNightRemark)
	private LinearLayout lay_RoomGetupIntheNightRemark;
	@ViewInject(R.id.lay_ThoraxChestOther)
	private LinearLayout lay_ThoraxChestOther;
	@ViewInject(R.id.lay_AbdomenOther)
	private LinearLayout lay_AbdomenOther;
	@ViewInject(R.id.lay_SkinPressureSoreCondition)
	private LinearLayout lay_SkinPressureSoreCondition;
	@ViewInject(R.id.lay_SkinPresstheRedCondition)
	private LinearLayout lay_SkinPresstheRedCondition;

	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	String item = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		reportId = sharedPreferencesUtil.get("reportId");
		initSpinner(array_OccupationPreRetirement, sp_OccupationPreRetirement);
		initSpinner(array_RoomBedMattressSoftness, sp_RoomBedMattressSoftness);
		initSpinner(array_RoomBedMattressSoftness, sp_RoomBedMattress);
		initSpinner(array_RoomBedTidy, sp_RoomBedTidy);
		initSpinner(array_YesOrNo, sp_RoomEnviromentAcuteAngle);
		initSpinner(array_YesOrNo, sp_RoomEnviromentStrong);
		initSpinner(array_YesOrNo, sp_RoomEnviromentAction);
		initSpinner(array_YesOrNo, sp_RoomEnviromentAntiskid);
		initSpinner(array_YesOrNo, sp_RoomEnviromentBarrier);
		initSpinner(array_YesOrNo, sp_RoomEnviromentTidy);
		initSpinner(array_YesOrNo, sp_RoomEnviromentSundries);
		initSpinner(array_YesOrNo, sp_RoomEnviromentDoorsill);
		initSpinner(array_PleaseYesOrNo, sp_RoomDressingClean);
		initSpinner(array_PleaseYesOrNo, sp_RoomDressingFit);
		initSpinner(array_PleaseYesOrNo, sp_RoomDressingWarm);
		initSpinner(array_VitalSignsBloodGlucoseMeasurementTime, sp_VitalSignsBloodGlucoseMeasurementTime);

		sp_OccupationPreRetirement.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				if (position == 5) {
					lay_OccupationPreRetirementOther.setVisibility(View.VISIBLE);
				} else {
					lay_OccupationPreRetirementOther.setVisibility(View.GONE);
					et_OccupationPreRetirementOther.setText("");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				

			}
		});

		mrg_RoomGetupIntheNight.clearChecked();
		mrg_RoomGetupIntheNight.setOnCheckChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
				if (position == 7) {
					if (checked) {
						lay_RoomGetupIntheNightRemark.setVisibility(View.VISIBLE);
						et_RoomGetupIntheNightRemark.setVisibility(View.VISIBLE);
					} else {
						lay_RoomGetupIntheNightRemark.setVisibility(View.GONE);
						et_RoomGetupIntheNightRemark.setText("");
						et_RoomGetupIntheNightRemark.setVisibility(View.GONE);
					}
				}

			}
		});
		mrg_ThoraxChest.setOnCheckChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
				if (position == 2) {
					if (checked) {
						lay_ThoraxChestOther.setVisibility(View.VISIBLE);
						et_ThoraxChestOther.setVisibility(View.VISIBLE);
					} else {
						lay_ThoraxChestOther.setVisibility(View.GONE);
						et_ThoraxChestOther.setText("");
						et_ThoraxChestOther.setVisibility(View.GONE);
					}
				}

			}
		});
		mrg_Abdomen.setOnCheckChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
				if (position == 6) {
					if (checked) {
						lay_AbdomenOther.setVisibility(View.VISIBLE);
						et_AbdomenOther.setVisibility(View.VISIBLE);
					} else {
						lay_AbdomenOther.setVisibility(View.GONE);
						et_AbdomenOther.setText("");
						et_AbdomenOther.setVisibility(View.GONE);
					}
				}

			}
		});
		mrg_SkinPressureSoreCondition.setOnCheckChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
				if (position == 1) {
					if (checked) {
						lay_SkinPressureSoreCondition.setVisibility(View.VISIBLE);
					} else {
						lay_SkinPressureSoreCondition.setVisibility(View.GONE);
						et_SkinPressureSorePosition.setText("");
						et_SkinPressureSoreSize.setText("");
						et_SkinPressureSoreExudationCondition.setText("");
					}
				} else {
					lay_SkinPressureSoreCondition.setVisibility(View.GONE);
					et_SkinPressureSorePosition.setText("");
					et_SkinPressureSoreSize.setText("");
					et_SkinPressureSoreExudationCondition.setText("");
				}

			}
		});
		mrg_SkinPresstheRedCondition.setOnCheckChangedListener(new OnCheckedChangedListener() {
			@Override
			public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
				if (position == 1) {
					if (checked) {
						lay_SkinPresstheRedCondition.setVisibility(View.VISIBLE);
					} else {
						lay_SkinPresstheRedCondition.setVisibility(View.GONE);
						et_SkinPresstheRedPosition.setText("");
						et_SkinPresstheRedSize.setText("");
					}
				} else {
					lay_SkinPresstheRedCondition.setVisibility(View.GONE);
					et_SkinPresstheRedPosition.setText("");
					et_SkinPresstheRedSize.setText("");
				}

			}
		});
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			SF_PersonalSupplement bi = dbUtils.findFirst(Selector.from(SF_PersonalSupplement.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				sp_OccupationPreRetirement.setSelection(Integer.parseInt(bi.getOccupationPreRetirement()));
				et_OccupationPreRetirementOther.setText(bi.getOccupationPreRetirementOther());
				et_NursingStatus.setText(bi.getNursingStatus());
				et_AssistantToolOtherInfo.setText(bi.getAssistantToolOtherInfo());
				sp_RoomBedMattressSoftness.setSelection(Integer.parseInt(bi.getRoomBedMattressSoftness()));
				sp_RoomBedMattress.setSelection(Integer.parseInt(bi.getRoomBedMattress()));
				sp_RoomBedTidy.setSelection(Integer.parseInt(bi.getRoomBedTidy()));
				et_HomeLighting.setText(bi.getHomeLighting());
				et_HomeAir.setText(bi.getHomeAir());
				et_HomeDryHumidity.setText(bi.getHomeDryHumidity());
				et_HomeTemperature.setText(bi.getHomeTemperature());
				et_HomeSmell.setText(bi.getHomeSmell());
				setMrgCheck(bi.getChildrenStatus(), mrg_ChildrenStatus);
				setMrgCheck(bi.getRoomToilet(), mrg_RoomToilet);
				setMrgCheck(bi.getRoomBed(), mrg_RoomBed);
				setMrgCheck(bi.getAssistantToolAction(), mrg_AssistantToolAction);
				setMrgCheck(bi.getAssistantToolOrthopedic(), mrg_AssistantToolOrthopedic);
				setMrgCheck(bi.getAssistantToolOther(), mrg_AssistantToolOther);

				setMrgCheck(bi.getRoomGetupIntheNight(), mrg_RoomGetupIntheNight);
				setMrgCheck(bi.getDietCondition(), mrg_DietCondition);
				setMrgCheck(bi.getSleepCondition(), mrg_SleepCondition);
				setMrgCheck(bi.getDietCondition(), mrg_DietCondition);
				setMrgCheck(bi.getUrinationCondition(), mrg_UrinationCondition);
				setMrgCheck(bi.getPsychologyCondition(), mrg_PsychologyCondition);
				setMrgCheck(bi.getPersonalHygiene(), mrg_PersonalHygiene);
				setMrgCheck(bi.getVitalSignsBreathingCondition(), mrg_VitalSignsBreathingCondition);
				setMrgCheck(bi.getVitalSignsPulseConfition(), mrg_VitalSignsPulseConfition);
				setMrgCheck(bi.getVitalSignsBreathingCough(), mrg_VitalSignsBreathingCough);
				setMrgCheck(bi.getVitalSignsPressureLocation(), mrg_VitalSignsPressureLocation);
				setMrgCheck(bi.getPersonsFace(), mrg_PersonsFace);
				setMrgCheck(bi.getSkinEdima(), mrg_SkinEdima);
				setMrgCheck(bi.getSkinPressureSoreCondition(), mrg_SkinPressureSoreCondition);
				setMrgCheck(bi.getThoraxChest(), mrg_ThoraxChest);
				setMrgCheck(bi.getAbdomen(), mrg_Abdomen);
				setMrgCheck(bi.getSkinPresstheRedCondition(), mrg_SkinPresstheRedCondition);

				sp_RoomEnviromentAcuteAngle.setSelection(Integer.parseInt(bi.getRoomEnviromentAcuteAngle()));
				sp_RoomEnviromentStrong.setSelection(Integer.parseInt(bi.getRoomEnviromentStrong()));
				sp_RoomEnviromentAction.setSelection(Integer.parseInt(bi.getRoomEnviromentAction()));
				sp_RoomEnviromentAntiskid.setSelection(Integer.parseInt(bi.getRoomEnviromentAntiskid()));
				sp_RoomEnviromentBarrier.setSelection(Integer.parseInt(bi.getRoomEnviromentBarrier()));
				sp_RoomEnviromentTidy.setSelection(Integer.parseInt(bi.getRoomEnviromentTidy()));
				sp_RoomEnviromentSundries.setSelection(Integer.parseInt(bi.getRoomEnviromentSundries()));
				sp_RoomEnviromentDoorsill.setSelection(Integer.parseInt(bi.getRoomEnviromentDoorsill()));
				sp_RoomDressingClean.setSelection(Integer.parseInt(bi.getRoomDressingClean()));
				sp_RoomDressingFit.setSelection(Integer.parseInt(bi.getRoomDressingFit()));
				sp_RoomDressingWarm.setSelection(Integer.parseInt(bi.getRoomDressingWarm()));
				sp_VitalSignsBloodGlucoseMeasurementTime.setSelection(Integer.parseInt(bi.getVitalSignsBloodGlucoseMeasurementTime()));
				if (bi.getRoomEnviromentOther().equals("0")) {
					cb_RoomEnviromentOther.setChecked(true);
				} else {
					cb_RoomEnviromentOther.setChecked(false);
				}
				if (bi.getDefecationConstipationAndDiarrhea().equals("0")) {
					cb_DefecationConstipationAndDiarrhea.setChecked(true);
				} else {
					cb_DefecationConstipationAndDiarrhea.setChecked(false);
				}
				et_RoomEnviromentOtherInfo.setText(bi.getRoomEnviromentOtherInfo());
				et_RoomGetupIntheNightRemark.setText(bi.getRoomGetupIntheNightRemark());
				et_AssistantToolDemand.setText(bi.getAssistantToolDemand());
				et_DrugUseCondition.setText(bi.getDrugUseCondition());
				et_DCDiet.setText(bi.getDCDiet());
				et_DietDrinkingWater.setText(bi.getDietDrinkingWater());
				et_DietVegetables.setText(bi.getDietVegetables());
				et_DietProtein.setText(bi.getDietProtein());
				et_SleepReason.setText(bi.getSleepReason());
				et_SleepDrugUse.setText(bi.getSleepDrugUse());
				et_SleepInfluence.setText(bi.getSleepInfluence());
				et_SleepOther.setText(bi.getSleepOther());
				et_DefecationCondition.setText(bi.getDefecationCondition());
				et_DefecationReason.setText(bi.getDefecationReason());
				et_DefecationDrugUse.setText(bi.getDefecationDrugUse());
				et_UrinationReason.setText(bi.getUrinationReason());
				et_UrinationDrugUse.setText(bi.getUrinationDrugUse());
				et_UrinationInfluence.setText(bi.getUrinationInfluence());
				et_PsychologyReason.setText(bi.getPsychologyReason());
				et_PsychologyDrugUse.setText(bi.getPsychologyDrugUse());
				et_PsychologyInfluence.setText(bi.getPsychologyInfluence());
				et_VitalSignsBloodGlucoseMeasurementValue.setText(bi.getVitalSignsBloodGlucoseMeasurementValue());
				et_VitalSignsPulseTimes.setText(bi.getVitalSignsPulseTimes());
				et_VitalSignsBreathingTimes.setText(bi.getVitalSignsBreathingTimes());
				et_VitalSignsSystolicPressure.setText(bi.getVitalSignsSystolicPressure());
				et_VitalSignsDiastolicBloodPressure.setText(bi.getVitalSignsDiastolicBloodPressure());
				et_VitalSignsBodyTemperature.setText(bi.getVitalSignsBodyTemperature());
				et_SkinOtherPosition.setText(bi.getSkinOtherPosition());
				et_SkinPressureSorePosition.setText(bi.getSkinPressureSorePosition());
				et_SkinPressureSoreSize.setText(bi.getSkinPressureSoreSize());
				et_SkinPressureSoreExudationCondition.setText(bi.getSkinPressureSoreExudationCondition());
				et_SkinPresstheRedPosition.setText(bi.getSkinPresstheRedPosition());
				et_SkinPresstheRedSize.setText(bi.getSkinPresstheRedSize());
				et_ThoraxChestOther.setText(bi.getThoraxChestOther());
				et_AbdomenOther.setText(bi.getAbdomenOther());
				if (bi.getOccupationPreRetirement().contains(array_OccupationPreRetirement.length-1+"")) {
					lay_OccupationPreRetirementOther.setVisibility(View.VISIBLE);
					et_OccupationPreRetirementOther.setVisibility(View.VISIBLE);
				}
				if (bi.getRoomGetupIntheNight().contains(array_RoomGetupIntheNight.length-1+"")) {
					lay_RoomGetupIntheNightRemark.setVisibility(View.VISIBLE);
					et_RoomGetupIntheNightRemark.setVisibility(View.VISIBLE);
				}

				if (bi.getThoraxChest().contains(array_ThoraxChest.length-1+"")) {
					lay_ThoraxChestOther.setVisibility(View.VISIBLE);
					et_ThoraxChestOther.setVisibility(View.VISIBLE);
				}

				if (bi.getAbdomen().contains(array_Abdomen.length-1+"")) {
					lay_AbdomenOther.setVisibility(View.VISIBLE);
					et_AbdomenOther.setVisibility(View.VISIBLE);
				}
				if (bi.getSkinPressureSoreCondition().equals("1")) {
					lay_SkinPressureSoreCondition.setVisibility(View.VISIBLE);
				}
				if (bi.getSkinPresstheRedCondition().equals("1")) {
					lay_SkinPresstheRedCondition.setVisibility(View.VISIBLE);
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
			SF_PersonalSupplement bi = null;
			bi = new SF_PersonalSupplement();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setOccupationPreRetirement(sp_OccupationPreRetirement.getSelectedItemPosition() + "");
			bi.setNursingStatus(et_NursingStatus.getText().toString());
			bi.setRoomBedMattressSoftness(sp_RoomBedMattressSoftness.getSelectedItemPosition() + "");
			bi.setRoomBedMattress(sp_RoomBedMattress.getSelectedItemPosition() + "");
			bi.setRoomBedTidy(sp_RoomBedTidy.getSelectedItemPosition() + "");
			bi.setRoomEnviromentAcuteAngle(sp_RoomEnviromentAcuteAngle.getSelectedItemPosition() + "");
			bi.setRoomEnviromentStrong(sp_RoomEnviromentStrong.getSelectedItemPosition() + "");
			bi.setRoomEnviromentAction(sp_RoomEnviromentAction.getSelectedItemPosition() + "");
			bi.setRoomEnviromentAntiskid(sp_RoomEnviromentAntiskid.getSelectedItemPosition() + "");
			bi.setRoomEnviromentBarrier(sp_RoomEnviromentBarrier.getSelectedItemPosition() + "");
			bi.setRoomEnviromentTidy(sp_RoomEnviromentTidy.getSelectedItemPosition() + "");
			bi.setRoomEnviromentSundries(sp_RoomEnviromentSundries.getSelectedItemPosition() + "");
			bi.setRoomEnviromentDoorsill(sp_RoomEnviromentDoorsill.getSelectedItemPosition() + "");
			bi.setRoomDressingClean(sp_RoomDressingClean.getSelectedItemPosition() + "");
			bi.setRoomDressingFit(sp_RoomDressingFit.getSelectedItemPosition() + "");
			bi.setRoomDressingWarm(sp_RoomDressingWarm.getSelectedItemPosition() + "");
			bi.setVitalSignsBloodGlucoseMeasurementTime(sp_VitalSignsBloodGlucoseMeasurementTime.getSelectedItemPosition() + "");
			bi.setHomeLighting(et_HomeLighting.getText().toString());
			bi.setHomeAir(et_HomeAir.getText().toString());
			bi.setHomeDryHumidity(et_HomeDryHumidity.getText().toString());
			bi.setHomeTemperature(et_HomeTemperature.getText().toString());
			bi.setHomeSmell(et_HomeSmell.getText().toString());
			bi.setChildrenStatus(getMrgValue(mrg_ChildrenStatus));
			bi.setRoomToilet(getMrgValue(mrg_RoomToilet));
			bi.setRoomBed(getMrgValue(mrg_RoomBed));
			bi.setRoomGetupIntheNight(getMrgValue(mrg_RoomGetupIntheNight));
			bi.setAssistantToolAction(getMrgValue(mrg_AssistantToolAction));
			bi.setAssistantToolOrthopedic(getMrgValue(mrg_AssistantToolOrthopedic));
			bi.setAssistantToolOther(getMrgValue(mrg_AssistantToolOther));
			bi.setDietCondition(getMrgValue(mrg_DietCondition));
			bi.setSleepCondition(getMrgValue(mrg_SleepCondition));
			bi.setUrinationCondition(getMrgValue(mrg_UrinationCondition));
			bi.setPsychologyCondition(getMrgValue(mrg_PsychologyCondition));
			bi.setPersonalHygiene(getMrgValue(mrg_PersonalHygiene));
			bi.setVitalSignsPulseConfition(getMrgValue(mrg_VitalSignsPulseConfition));
			bi.setVitalSignsBreathingCondition(getMrgValue(mrg_VitalSignsBreathingCondition));
			bi.setVitalSignsBreathingCough(getMrgValue(mrg_VitalSignsBreathingCough));
			bi.setVitalSignsPressureLocation(getMrgValue(mrg_VitalSignsPressureLocation));
			bi.setPersonsFace(getMrgValue(mrg_PersonsFace));
			bi.setSkinEdima(getMrgValue(mrg_SkinEdima));
			bi.setSkinPressureSoreCondition(getMrgValue(mrg_SkinPressureSoreCondition));
			bi.setSkinPresstheRedCondition(getMrgValue(mrg_SkinPresstheRedCondition));
			bi.setThoraxChest(getMrgValue(mrg_ThoraxChest));
			bi.setAbdomen(getMrgValue(mrg_Abdomen));
			if (cb_RoomEnviromentOther.isChecked()) {
				bi.setRoomEnviromentOther("0");
			} else {
				bi.setRoomEnviromentOther("");
			}
			if (cb_DefecationConstipationAndDiarrhea.isChecked()) {
				bi.setDefecationConstipationAndDiarrhea("0");
			} else {
				bi.setDefecationConstipationAndDiarrhea("");
			}
			bi.setOccupationPreRetirementOther(et_OccupationPreRetirementOther.getText().toString());
			bi.setAssistantToolOtherInfo(et_AssistantToolOtherInfo.getText().toString());
			bi.setRoomEnviromentOtherInfo(et_RoomEnviromentOtherInfo.getText().toString());
			bi.setRoomGetupIntheNightRemark(et_RoomGetupIntheNightRemark.getText().toString());
			bi.setAssistantToolDemand(et_AssistantToolDemand.getText().toString());
			bi.setDrugUseCondition(et_DrugUseCondition.getText().toString());
			bi.setDCDiet(et_DCDiet.getText().toString());
			bi.setDietDrinkingWater(et_DietDrinkingWater.getText().toString());
			bi.setDietVegetables(et_DietVegetables.getText().toString());
			bi.setDietProtein(et_DietProtein.getText().toString());
			bi.setSleepReason(et_SleepReason.getText().toString());
			bi.setSleepDrugUse(et_SleepDrugUse.getText().toString());
			bi.setSleepInfluence(et_SleepInfluence.getText().toString());
			bi.setSleepOther(et_SleepOther.getText().toString());
			bi.setDefecationCondition(et_DefecationCondition.getText().toString());
			bi.setDefecationReason(et_DefecationReason.getText().toString());
			bi.setDefecationDrugUse(et_DefecationDrugUse.getText().toString());
			bi.setUrinationReason(et_UrinationReason.getText().toString());
			bi.setUrinationDrugUse(et_UrinationDrugUse.getText().toString());
			bi.setUrinationInfluence(et_UrinationInfluence.getText().toString());
			bi.setPsychologyReason(et_PsychologyReason.getText().toString());
			bi.setPsychologyDrugUse(et_PsychologyDrugUse.getText().toString());
			bi.setPsychologyInfluence(et_PsychologyInfluence.getText().toString());
			bi.setVitalSignsBloodGlucoseMeasurementValue(et_VitalSignsBloodGlucoseMeasurementValue.getText().toString());
			bi.setVitalSignsPulseTimes(et_VitalSignsPulseTimes.getText().toString());
			bi.setVitalSignsBreathingTimes(et_VitalSignsBreathingTimes.getText().toString());
			bi.setVitalSignsSystolicPressure(et_VitalSignsSystolicPressure.getText().toString());
			bi.setVitalSignsDiastolicBloodPressure(et_VitalSignsDiastolicBloodPressure.getText().toString());
			bi.setVitalSignsBodyTemperature(et_VitalSignsBodyTemperature.getText().toString());
			bi.setSkinOtherPosition(et_SkinOtherPosition.getText().toString());
			bi.setSkinPressureSorePosition(et_SkinPressureSorePosition.getText().toString());
			bi.setSkinPressureSoreSize(et_SkinPressureSoreSize.getText().toString());
			bi.setSkinPressureSoreExudationCondition(et_SkinPressureSoreExudationCondition.getText().toString());
			bi.setSkinPresstheRedPosition(et_SkinPresstheRedPosition.getText().toString());
			bi.setSkinPresstheRedSize(et_SkinPresstheRedSize.getText().toString());
			bi.setThoraxChestOther(et_ThoraxChestOther.getText().toString());
			bi.setAbdomenOther(et_AbdomenOther.getText().toString());
			try {
				if (dbUtils.findFirst(Selector.from(SF_PersonalSupplement.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(SF_PersonalSupplement.class).where("ReportId", "=", reportId));
					bi.setOccupationPreRetirement(sp_OccupationPreRetirement.getSelectedItemPosition() + "");
					bi.setNursingStatus(et_NursingStatus.getText().toString());
					bi.setRoomBedMattressSoftness(sp_RoomBedMattressSoftness.getSelectedItemPosition() + "");
					bi.setRoomBedMattress(sp_RoomBedMattress.getSelectedItemPosition() + "");
					bi.setRoomBedTidy(sp_RoomBedTidy.getSelectedItemPosition() + "");
					bi.setRoomEnviromentAcuteAngle(sp_RoomEnviromentAcuteAngle.getSelectedItemPosition() + "");
					bi.setRoomEnviromentStrong(sp_RoomEnviromentStrong.getSelectedItemPosition() + "");
					bi.setRoomEnviromentAction(sp_RoomEnviromentAction.getSelectedItemPosition() + "");
					bi.setRoomEnviromentAntiskid(sp_RoomEnviromentAntiskid.getSelectedItemPosition() + "");
					bi.setRoomEnviromentBarrier(sp_RoomEnviromentBarrier.getSelectedItemPosition() + "");
					bi.setRoomEnviromentTidy(sp_RoomEnviromentTidy.getSelectedItemPosition() + "");
					bi.setRoomEnviromentSundries(sp_RoomEnviromentSundries.getSelectedItemPosition() + "");
					bi.setRoomEnviromentDoorsill(sp_RoomEnviromentDoorsill.getSelectedItemPosition() + "");
					bi.setRoomDressingClean(sp_RoomDressingClean.getSelectedItemPosition() + "");
					bi.setRoomDressingFit(sp_RoomDressingFit.getSelectedItemPosition() + "");
					bi.setRoomDressingWarm(sp_RoomDressingWarm.getSelectedItemPosition() + "");
					bi.setVitalSignsBloodGlucoseMeasurementTime(sp_VitalSignsBloodGlucoseMeasurementTime.getSelectedItemPosition() + "");
					bi.setHomeLighting(et_HomeLighting.getText().toString());
					bi.setHomeAir(et_HomeAir.getText().toString());
					bi.setHomeDryHumidity(et_HomeDryHumidity.getText().toString());
					bi.setHomeTemperature(et_HomeTemperature.getText().toString());
					bi.setHomeSmell(et_HomeSmell.getText().toString());
					bi.setChildrenStatus(getMrgValue(mrg_ChildrenStatus));
					bi.setRoomToilet(getMrgValue(mrg_RoomToilet));
					bi.setRoomBed(getMrgValue(mrg_RoomBed));
					bi.setRoomGetupIntheNight(getMrgValue(mrg_RoomGetupIntheNight));
					bi.setAssistantToolAction(getMrgValue(mrg_AssistantToolAction));
					bi.setAssistantToolOrthopedic(getMrgValue(mrg_AssistantToolOrthopedic));
					bi.setAssistantToolOther(getMrgValue(mrg_AssistantToolOther));
					bi.setDietCondition(getMrgValue(mrg_DietCondition));
					bi.setSleepCondition(getMrgValue(mrg_SleepCondition));
					bi.setUrinationCondition(getMrgValue(mrg_UrinationCondition));
					bi.setPsychologyCondition(getMrgValue(mrg_PsychologyCondition));
					bi.setPersonalHygiene(getMrgValue(mrg_PersonalHygiene));
					bi.setVitalSignsPulseConfition(getMrgValue(mrg_VitalSignsPulseConfition));
					bi.setVitalSignsBreathingCondition(getMrgValue(mrg_VitalSignsBreathingCondition));
					bi.setVitalSignsBreathingCough(getMrgValue(mrg_VitalSignsBreathingCough));
					bi.setVitalSignsPressureLocation(getMrgValue(mrg_VitalSignsPressureLocation));
					bi.setPersonsFace(getMrgValue(mrg_PersonsFace));
					bi.setSkinEdima(getMrgValue(mrg_SkinEdima));
					bi.setSkinPressureSoreCondition(getMrgValue(mrg_SkinPressureSoreCondition));
					bi.setSkinPresstheRedCondition(getMrgValue(mrg_SkinPresstheRedCondition));
					bi.setThoraxChest(getMrgValue(mrg_ThoraxChest));
					bi.setAbdomen(getMrgValue(mrg_Abdomen));
					if (cb_RoomEnviromentOther.isChecked()) {
						bi.setRoomEnviromentOther("0");
					} else {
						bi.setRoomEnviromentOther("");
					}
					if (cb_DefecationConstipationAndDiarrhea.isChecked()) {
						bi.setDefecationConstipationAndDiarrhea("0");
					} else {
						bi.setDefecationConstipationAndDiarrhea("");
					}
					bi.setOccupationPreRetirementOther(et_OccupationPreRetirementOther.getText().toString());
					bi.setAssistantToolOtherInfo(et_AssistantToolOtherInfo.getText().toString());
					bi.setRoomEnviromentOtherInfo(et_RoomEnviromentOtherInfo.getText().toString());
					bi.setRoomGetupIntheNightRemark(et_RoomGetupIntheNightRemark.getText().toString());
					bi.setAssistantToolDemand(et_AssistantToolDemand.getText().toString());
					bi.setDrugUseCondition(et_DrugUseCondition.getText().toString());
					bi.setDCDiet(et_DCDiet.getText().toString());
					bi.setDietDrinkingWater(et_DietDrinkingWater.getText().toString());
					bi.setDietVegetables(et_DietVegetables.getText().toString());
					bi.setDietProtein(et_DietProtein.getText().toString());
					bi.setSleepReason(et_SleepReason.getText().toString());
					bi.setSleepDrugUse(et_SleepDrugUse.getText().toString());
					bi.setSleepInfluence(et_SleepInfluence.getText().toString());
					bi.setSleepOther(et_SleepOther.getText().toString());
					bi.setDefecationCondition(et_DefecationCondition.getText().toString());
					bi.setDefecationReason(et_DefecationReason.getText().toString());
					bi.setDefecationDrugUse(et_DefecationDrugUse.getText().toString());
					bi.setUrinationReason(et_UrinationReason.getText().toString());
					bi.setUrinationDrugUse(et_UrinationDrugUse.getText().toString());
					bi.setUrinationInfluence(et_UrinationInfluence.getText().toString());
					bi.setPsychologyReason(et_PsychologyReason.getText().toString());
					bi.setPsychologyDrugUse(et_PsychologyDrugUse.getText().toString());
					bi.setPsychologyInfluence(et_PsychologyInfluence.getText().toString());
					bi.setVitalSignsBloodGlucoseMeasurementValue(et_VitalSignsBloodGlucoseMeasurementValue.getText().toString());
					bi.setVitalSignsPulseTimes(et_VitalSignsPulseTimes.getText().toString());
					bi.setVitalSignsBreathingTimes(et_VitalSignsBreathingTimes.getText().toString());
					bi.setVitalSignsSystolicPressure(et_VitalSignsSystolicPressure.getText().toString());
					bi.setVitalSignsDiastolicBloodPressure(et_VitalSignsDiastolicBloodPressure.getText().toString());
					bi.setVitalSignsBodyTemperature(et_VitalSignsBodyTemperature.getText().toString());
					bi.setSkinOtherPosition(et_SkinOtherPosition.getText().toString());
					bi.setSkinPressureSorePosition(et_SkinPressureSorePosition.getText().toString());
					bi.setSkinPressureSoreSize(et_SkinPressureSoreSize.getText().toString());
					bi.setSkinPressureSoreExudationCondition(et_SkinPressureSoreExudationCondition.getText().toString());
					bi.setSkinPresstheRedPosition(et_SkinPresstheRedPosition.getText().toString());
					bi.setSkinPresstheRedSize(et_SkinPresstheRedSize.getText().toString());
					bi.setThoraxChestOther(et_ThoraxChestOther.getText().toString());
					bi.setAbdomenOther(et_AbdomenOther.getText().toString());

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

	private void initSpinner(String[] arraySp, Spinner sp) {
		ArrayAdapter<?> adapter_sp;
		adapter_sp = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, arraySp);
		adapter_sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter_sp);
	}

	public String getMrgValue(MultiLineRadioGroup mrg) {
		String item = "";
		int[] items = {};
		items = mrg.getCheckedItems();
		if (items == null) {

		} else {
			for (int j = 0; j < items.length; j++) {
				item += items[j] + ",";
			}
			if (items.length > 0) {
				item = item.substring(0, item.lastIndexOf(","));
			}
		}

		return item;
	}

	private void setMrgCheck(String items, MultiLineRadioGroup mrg) {
		if (items != null) {
			if (!items.equals("") && items.split(",").length > 0) {
				for (int j = 0; j < items.split(",").length; j++) {
					mrg.setItemChecked(Integer.parseInt(items.split(",")[j]));
				}
			}
		}
	}
}
