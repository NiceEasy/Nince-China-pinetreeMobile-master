package com.pinetree.mobile.activity;

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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
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
import com.pinetree.mobile.bean.SF_MFA;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_sf_mfa)
public class SFMFAActivity extends Activity implements MyBaseInterface, OnItemSelectedListener, OnCheckedChangeListener {
	@ViewInject(R.id.sp_MFASittingBalance)
	Spinner sp_MFASittingBalance;
	@ViewInject(R.id.sp_MFAPositionBalance)
	Spinner sp_MFAPositionBalance;
	@ViewInject(R.id.sp_MFAHoldenWalkingAbility)
	Spinner sp_MFAHoldenWalkingAbility;
	@ViewInject(R.id.et_MFASittingBalanceOther)
	EditText et_MFASittingBalanceOther;
	@ViewInject(R.id.et_MFAPositionBalanceOther)
	EditText et_MFAPositionBalanceOther;
	@ViewInject(R.id.et_MFAHoldenWalkingAbilityOther)
	EditText et_MFAHoldenWalkingAbilityOther;
	@ResInject(id = R.array.array_MFASittingBalance, type = ResType.StringArray)
	private String[] array_MFASittingBalance;
	@ResInject(id = R.array.array_MFAHoldenWalkingAbility, type = ResType.StringArray)
	private String[] array_MFAHoldenWalkingAbility;

	@ResInject(id = R.array.array_MFAMuscleForce, type = ResType.StringArray)
	private String[] array_MFAMuscleForce;
	@ResInject(id = R.array.array_MFAMuscularTension, type = ResType.StringArray)
	private String[] array_MFAMuscularTension;
	@ResInject(id = R.array.array_MFAPainScore, type = ResType.StringArray)
	private String[] array_MFAPainScore;
	@ResInject(id = R.array.array_MFAState, type = ResType.StringArray)
	private String[] array_MFAState;
	@ResInject(id = R.array.array_RiskLevel, type = ResType.StringArray)
	private String[] array_RiskLevel;

	@ViewInject(R.id.et_MFAMuscleForceLeftOtherName)
	EditText et_MFAMuscleForceLeftOtherName;
	@ViewInject(R.id.et_MFAMuscleForceTrunkOtherName)
	EditText et_MFAMuscleForceTrunkOtherName;
	@ViewInject(R.id.et_MFAMuscleForceRightOtherName)
	EditText et_MFAMuscleForceRightOtherName;
	@ViewInject(R.id.et_MFAMuscularTensionLeftOtherName)
	EditText et_MFAMuscularTensionLeftOtherName;
	@ViewInject(R.id.et_MFAMuscularTensionTrunkOtherName)
	EditText et_MFAMuscularTensionTrunkOtherName;
	@ViewInject(R.id.et_MFAMuscularTensionRightOtherName)
	EditText et_MFAMuscularTensionRightOtherName;
	@ViewInject(R.id.et_MFAPainScoreLeftOtherName)
	EditText et_MFAPainScoreLeftOtherName;
	@ViewInject(R.id.et_MFAPainScoreTrunkOtherName)
	EditText et_MFAPainScoreTrunkOtherName;
	@ViewInject(R.id.et_MFAPainScoreRightOtherName)
	EditText et_MFAPainScoreRightOtherName;

	@ViewInject(R.id.sp_MFAMuscleForceLeftHand)
	Spinner sp_MFAMuscleForceLeftHand;
	@ViewInject(R.id.sp_MFAMuscleForceLeftUpper)
	Spinner sp_MFAMuscleForceLeftUpper;
	@ViewInject(R.id.sp_MFAMuscleForceLeftLower)
	Spinner sp_MFAMuscleForceLeftLower;
	@ViewInject(R.id.sp_MFAMuscleForceLeftOther)
	Spinner sp_MFAMuscleForceLeftOther;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkBack)
	Spinner sp_MFAMuscleForceTrunkBack;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkWaist)
	Spinner sp_MFAMuscleForceTrunkWaist;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkButtocks)
	Spinner sp_MFAMuscleForceTrunkButtocks;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkChest)
	Spinner sp_MFAMuscleForceTrunkChest;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkAbdomen)
	Spinner sp_MFAMuscleForceTrunkAbdomen;
	@ViewInject(R.id.sp_MFAMuscleForceTrunkOther)
	Spinner sp_MFAMuscleForceTrunkOther;
	@ViewInject(R.id.sp_MFAMuscleForceRightUpper)
	Spinner sp_MFAMuscleForceRightUpper;
	@ViewInject(R.id.sp_MFAMuscleForceRightLower)
	Spinner sp_MFAMuscleForceRightLower;
	@ViewInject(R.id.sp_MFAMuscleForceRightFeet)
	Spinner sp_MFAMuscleForceRightFeet;
	@ViewInject(R.id.sp_MFAMuscleForceRightOther)
	Spinner sp_MFAMuscleForceRightOther;

	@ViewInject(R.id.sp_MFAMuscularTensionLeftHand)
	Spinner sp_MFAMuscularTensionLeftHand;
	@ViewInject(R.id.sp_MFAMuscularTensionLeftUpper)
	Spinner sp_MFAMuscularTensionLeftUpper;
	@ViewInject(R.id.sp_MFAMuscularTensionLeftLower)
	Spinner sp_MFAMuscularTensionLeftLower;
	@ViewInject(R.id.sp_MFAMuscularTensionLeftOther)
	Spinner sp_MFAMuscularTensionLeftOther;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkBack)
	Spinner sp_MFAMuscularTensionTrunkBack;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkWaist)
	Spinner sp_MFAMuscularTensionTrunkWaist;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkButtocks)
	Spinner sp_MFAMuscularTensionTrunkButtocks;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkChest)
	Spinner sp_MFAMuscularTensionTrunkChest;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkAbdomen)
	Spinner sp_MFAMuscularTensionTrunkAbdomen;
	@ViewInject(R.id.sp_MFAMuscularTensionTrunkOther)
	Spinner sp_MFAMuscularTensionTrunkOther;
	@ViewInject(R.id.sp_MFAMuscularTensionRightUpper)
	Spinner sp_MFAMuscularTensionRightUpper;
	@ViewInject(R.id.sp_MFAMuscularTensionRightLower)
	Spinner sp_MFAMuscularTensionRightLower;
	@ViewInject(R.id.sp_MFAMuscularTensionRightFeet)
	Spinner sp_MFAMuscularTensionRightFeet;
	@ViewInject(R.id.sp_MFAMuscularTensionRightOther)
	Spinner sp_MFAMuscularTensionRightOther;

	@ViewInject(R.id.sp_MFAPainScoreLeftHand)
	Spinner sp_MFAPainScoreLeftHand;
	@ViewInject(R.id.sp_MFAPainScoreLeftUpper)
	Spinner sp_MFAPainScoreLeftUpper;
	@ViewInject(R.id.sp_MFAPainScoreLeftLower)
	Spinner sp_MFAPainScoreLeftLower;
	@ViewInject(R.id.sp_MFAPainScoreLeftOther)
	Spinner sp_MFAPainScoreLeftOther;
	@ViewInject(R.id.sp_MFAPainScoreTrunkBack)
	Spinner sp_MFAPainScoreTrunkBack;
	@ViewInject(R.id.sp_MFAPainScoreTrunkWaist)
	Spinner sp_MFAPainScoreTrunkWaist;
	@ViewInject(R.id.sp_MFAPainScoreTrunkButtocks)
	Spinner sp_MFAPainScoreTrunkButtocks;
	@ViewInject(R.id.sp_MFAPainScoreTrunkChest)
	Spinner sp_MFAPainScoreTrunkChest;
	@ViewInject(R.id.sp_MFAPainScoreTrunkAbdomen)
	Spinner sp_MFAPainScoreTrunkAbdomen;
	@ViewInject(R.id.sp_MFAPainScoreTrunkOther)
	Spinner sp_MFAPainScoreTrunkOther;
	@ViewInject(R.id.sp_MFAPainScoreRightUpper)
	Spinner sp_MFAPainScoreRightUpper;
	@ViewInject(R.id.sp_MFAPainScoreRightLower)
	Spinner sp_MFAPainScoreRightLower;
	@ViewInject(R.id.sp_MFAPainScoreRightFeet)
	Spinner sp_MFAPainScoreRightFeet;
	@ViewInject(R.id.sp_MFAPainScoreRightOther)
	Spinner sp_MFAPainScoreRightOther;

	@ViewInject(R.id.rg_MFAMuscleForce)
	private RadioGroup rg_MFAMuscleForce;
	@ViewInject(R.id.rg_MFAMuscularTension)
	private RadioGroup rg_MFAMuscularTension;
	@ViewInject(R.id.rg_MFAPainScore)
	private RadioGroup rg_MFAPainScore;
	@ViewInject(R.id.rg_RiskLevel)
	private RadioGroup rg_RiskLevel;

	@ViewInject(R.id.lay_MFAMuscleForce)
	private LinearLayout lay_MFAMuscleForce;
	@ViewInject(R.id.lay_MFAMuscularTension)
	private LinearLayout lay_MFAMuscularTension;
	@ViewInject(R.id.lay_MFAPainScore)
	private LinearLayout lay_MFAPainScore;

	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private ArrayAdapter<?> adapter_MFASittingBalance, adapter_MFAHoldenWalkingAbility, adapter_MFAMuscleForce, adapter_MFAMuscularTension, adapter_MFAPainScore;

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

		adapter_MFASittingBalance = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_MFASittingBalance);
		adapter_MFASittingBalance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_MFASittingBalance.setAdapter(adapter_MFASittingBalance);
		sp_MFASittingBalance.setOnItemSelectedListener(this);
		sp_MFAPositionBalance.setAdapter(adapter_MFASittingBalance);
		sp_MFAPositionBalance.setOnItemSelectedListener(this);
		adapter_MFAHoldenWalkingAbility = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_MFAHoldenWalkingAbility);
		adapter_MFAHoldenWalkingAbility.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_MFAHoldenWalkingAbility.setAdapter(adapter_MFAHoldenWalkingAbility);
		sp_MFAHoldenWalkingAbility.setOnItemSelectedListener(this);

		Tools.addRadioButtonH(mContext, array_MFAState, rg_MFAMuscleForce);
		Tools.addRadioButtonH(mContext, array_MFAState, rg_MFAMuscularTension);
		Tools.addRadioButtonH(mContext, array_MFAState, rg_MFAPainScore);
		Tools.addRadioButtonH(mContext, array_RiskLevel, rg_RiskLevel);
		rg_MFAMuscleForce.setOnCheckedChangeListener(this);
		rg_MFAMuscularTension.setOnCheckedChangeListener(this);
		rg_MFAPainScore.setOnCheckedChangeListener(this);

		adapter_MFAMuscleForce = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_MFAMuscleForce);
		adapter_MFAMuscleForce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_MFAMuscleForceLeftHand.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceLeftUpper.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceLeftLower.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceLeftOther.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkBack.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkWaist.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkButtocks.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkChest.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkAbdomen.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceTrunkOther.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceRightUpper.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceRightLower.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceRightFeet.setAdapter(adapter_MFAMuscleForce);
		sp_MFAMuscleForceRightOther.setAdapter(adapter_MFAMuscleForce);

		adapter_MFAMuscularTension = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_MFAMuscularTension);
		adapter_MFAMuscularTension.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_MFAMuscularTensionLeftHand.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionLeftUpper.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionLeftLower.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionLeftOther.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkBack.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkWaist.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkButtocks.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkChest.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkAbdomen.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionTrunkOther.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionRightUpper.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionRightLower.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionRightFeet.setAdapter(adapter_MFAMuscularTension);
		sp_MFAMuscularTensionRightOther.setAdapter(adapter_MFAMuscularTension);

		adapter_MFAPainScore = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_MFAPainScore);
		adapter_MFAPainScore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_MFAPainScoreLeftHand.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreLeftUpper.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreLeftLower.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreLeftOther.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkBack.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkWaist.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkButtocks.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkChest.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkAbdomen.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreTrunkOther.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreRightUpper.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreRightLower.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreRightFeet.setAdapter(adapter_MFAPainScore);
		sp_MFAPainScoreRightOther.setAdapter(adapter_MFAPainScore);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			SF_MFA bi = dbUtils.findFirst(Selector.from(SF_MFA.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				sp_MFASittingBalance.setSelection(Integer.parseInt(bi.getMFASittingBalance()));
				sp_MFAPositionBalance.setSelection(Integer.parseInt(bi.getMFAPositionBalance()));
				sp_MFAHoldenWalkingAbility.setSelection(Integer.parseInt(bi.getMFAHoldenWalkingAbility()));
				et_MFASittingBalanceOther.setText(bi.getMFASittingBalanceOther());
				et_MFAPositionBalanceOther.setText(bi.getMFAPositionBalanceOther());
				et_MFAHoldenWalkingAbilityOther.setText(bi.getMFAHoldenWalkingAbilityOther());


				rg_MFAMuscleForce.check(Integer.parseInt(bi.getMFAMuscleForce()));
				rg_MFAMuscularTension.check(Integer.parseInt(bi.getMFAMuscularTension()));
				rg_MFAPainScore.check(Integer.parseInt(bi.getMFAPainScore()));
				rg_RiskLevel.check(Integer.parseInt(bi.getRiskLevel()));
				
				et_MFAMuscleForceLeftOtherName.setText(bi.getMFAMuscleForceLeftOtherName());
				et_MFAMuscleForceTrunkOtherName.setText(bi.getMFAMuscleForceTrunkOtherName());
				et_MFAMuscleForceRightOtherName.setText(bi.getMFAMuscleForceRightOtherName());
				et_MFAMuscularTensionLeftOtherName.setText(bi.getMFAMuscularTensionLeftOtherName());
				et_MFAMuscularTensionTrunkOtherName.setText(bi.getMFAMuscularTensionTrunkOtherName());
				et_MFAMuscularTensionRightOtherName.setText(bi.getMFAMuscularTensionRightOtherName());
				et_MFAPainScoreLeftOtherName.setText(bi.getMFAPainScoreLeftOtherName());
				et_MFAPainScoreTrunkOtherName.setText(bi.getMFAPainScoreTrunkOtherName());
				et_MFAPainScoreRightOtherName.setText(bi.getMFAPainScoreRightOtherName());

				sp_MFAMuscleForceLeftHand.setSelection(Integer.parseInt(bi.getMFAMuscleForceLeftHand()));
				sp_MFAMuscleForceLeftUpper.setSelection(Integer.parseInt(bi.getMFAMuscleForceLeftUpper()));
				sp_MFAMuscleForceLeftLower.setSelection(Integer.parseInt(bi.getMFAMuscleForceLeftLower()));
				sp_MFAMuscleForceLeftOther.setSelection(Integer.parseInt(bi.getMFAMuscleForceLeftOther()));
				sp_MFAMuscleForceTrunkBack.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkBack()));
				sp_MFAMuscleForceTrunkWaist.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkWaist()));
				sp_MFAMuscleForceTrunkButtocks.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkButtocks()));
				sp_MFAMuscleForceTrunkChest.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkChest()));
				sp_MFAMuscleForceTrunkAbdomen.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkAbdomen()));
				sp_MFAMuscleForceTrunkOther.setSelection(Integer.parseInt(bi.getMFAMuscleForceTrunkOther()));
				sp_MFAMuscleForceRightUpper.setSelection(Integer.parseInt(bi.getMFAMuscleForceRightUpper()));
				sp_MFAMuscleForceRightLower.setSelection(Integer.parseInt(bi.getMFAMuscleForceRightLower()));
				sp_MFAMuscleForceRightFeet.setSelection(Integer.parseInt(bi.getMFAMuscleForceRightFeet()));
				sp_MFAMuscleForceRightOther.setSelection(Integer.parseInt(bi.getMFAMuscleForceRightOther()));

				sp_MFAMuscularTensionLeftHand.setSelection(Integer.parseInt(bi.getMFAMuscularTensionLeftHand()));
				sp_MFAMuscularTensionLeftUpper.setSelection(Integer.parseInt(bi.getMFAMuscularTensionLeftUpper()));
				sp_MFAMuscularTensionLeftLower.setSelection(Integer.parseInt(bi.getMFAMuscularTensionLeftLower()));
				sp_MFAMuscularTensionLeftOther.setSelection(Integer.parseInt(bi.getMFAMuscularTensionLeftOther()));
				sp_MFAMuscularTensionTrunkBack.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkBack()));
				sp_MFAMuscularTensionTrunkWaist.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkWaist()));
				sp_MFAMuscularTensionTrunkButtocks.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkButtocks()));
				sp_MFAMuscularTensionTrunkChest.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkChest()));
				sp_MFAMuscularTensionTrunkAbdomen.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkAbdomen()));
				sp_MFAMuscularTensionTrunkOther.setSelection(Integer.parseInt(bi.getMFAMuscularTensionTrunkOther()));
				sp_MFAMuscularTensionRightUpper.setSelection(Integer.parseInt(bi.getMFAMuscularTensionRightUpper()));
				sp_MFAMuscularTensionRightLower.setSelection(Integer.parseInt(bi.getMFAMuscularTensionRightLower()));
				sp_MFAMuscularTensionRightFeet.setSelection(Integer.parseInt(bi.getMFAMuscularTensionRightFeet()));
				sp_MFAMuscularTensionRightOther.setSelection(Integer.parseInt(bi.getMFAMuscularTensionRightOther()));

				sp_MFAPainScoreLeftHand.setSelection(Integer.parseInt(bi.getMFAPainScoreLeftHand()));
				sp_MFAPainScoreLeftUpper.setSelection(Integer.parseInt(bi.getMFAPainScoreLeftUpper()));
				sp_MFAPainScoreLeftLower.setSelection(Integer.parseInt(bi.getMFAPainScoreLeftLower()));
				sp_MFAPainScoreLeftOther.setSelection(Integer.parseInt(bi.getMFAPainScoreLeftOther()));
				sp_MFAPainScoreTrunkBack.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkBack()));
				sp_MFAPainScoreTrunkWaist.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkWaist()));
				sp_MFAPainScoreTrunkButtocks.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkButtocks()));
				sp_MFAPainScoreTrunkChest.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkChest()));
				sp_MFAPainScoreTrunkAbdomen.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkAbdomen()));
				sp_MFAPainScoreTrunkOther.setSelection(Integer.parseInt(bi.getMFAPainScoreTrunkOther()));
				sp_MFAPainScoreRightUpper.setSelection(Integer.parseInt(bi.getMFAPainScoreRightUpper()));
				sp_MFAPainScoreRightLower.setSelection(Integer.parseInt(bi.getMFAPainScoreRightLower()));
				sp_MFAPainScoreRightFeet.setSelection(Integer.parseInt(bi.getMFAPainScoreRightFeet()));
				sp_MFAPainScoreRightOther.setSelection(Integer.parseInt(bi.getMFAPainScoreRightOther()));
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		dbUtils.close();
	}

	@OnClick({ R.id.bt_save, R.id.bt_back, R.id.bt_add_copm })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:
			DbUtils dbUtils = DbUtils.create(this);
			SF_MFA bi = null;
			bi = new SF_MFA();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setMFASittingBalance(sp_MFASittingBalance.getSelectedItemPosition() + "");
			bi.setMFAPositionBalance(sp_MFAPositionBalance.getSelectedItemPosition() + "");
			bi.setMFAHoldenWalkingAbility(sp_MFAHoldenWalkingAbility.getSelectedItemPosition() + "");
			bi.setMFASittingBalanceOther(et_MFASittingBalanceOther.getText().toString());
			bi.setMFAPositionBalanceOther(et_MFAPositionBalanceOther.getText().toString());
			bi.setMFAHoldenWalkingAbilityOther(et_MFAHoldenWalkingAbilityOther.getText().toString());

			bi.setMFAMuscleForceLeftOtherName(et_MFAMuscleForceLeftOtherName.getText().toString());
			bi.setMFAMuscleForceTrunkOtherName(et_MFAMuscleForceTrunkOtherName.getText().toString());
			bi.setMFAMuscleForceRightOtherName(et_MFAMuscleForceRightOtherName.getText().toString());
			bi.setMFAMuscularTensionLeftOtherName(et_MFAMuscularTensionLeftOtherName.getText().toString());
			bi.setMFAMuscularTensionTrunkOtherName(et_MFAMuscularTensionTrunkOtherName.getText().toString());
			bi.setMFAMuscularTensionRightOtherName(et_MFAMuscularTensionRightOtherName.getText().toString());
			bi.setMFAPainScoreLeftOtherName(et_MFAPainScoreLeftOtherName.getText().toString());
			bi.setMFAPainScoreTrunkOtherName(et_MFAPainScoreTrunkOtherName.getText().toString());
			bi.setMFAPainScoreRightOtherName(et_MFAPainScoreRightOtherName.getText().toString());

			bi.setMFAMuscleForce(rg_MFAMuscleForce.getCheckedRadioButtonId() + "");
			bi.setMFAMuscularTension(rg_MFAMuscularTension.getCheckedRadioButtonId() + "");
			bi.setMFAPainScore(rg_MFAPainScore.getCheckedRadioButtonId() + "");
			bi.setRiskLevel(rg_RiskLevel.getCheckedRadioButtonId() + "");

			bi.setMFAMuscleForceLeftHand(sp_MFAMuscleForceLeftHand.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceLeftUpper(sp_MFAMuscleForceLeftUpper.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceLeftLower(sp_MFAMuscleForceLeftLower.getSelectedItemPosition() + "");
			if (et_MFAMuscleForceLeftOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscleForceLeftOther("0");
			} else {
				bi.setMFAMuscleForceLeftOther(sp_MFAMuscleForceLeftOther.getSelectedItemPosition() + "");
			}
			bi.setMFAMuscleForceTrunkBack(sp_MFAMuscleForceTrunkBack.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceTrunkWaist(sp_MFAMuscleForceTrunkWaist.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceTrunkButtocks(sp_MFAMuscleForceTrunkButtocks.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceTrunkChest(sp_MFAMuscleForceTrunkChest.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceTrunkAbdomen(sp_MFAMuscleForceTrunkAbdomen.getSelectedItemPosition() + "");
			if (et_MFAMuscleForceTrunkOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscleForceTrunkOther("0");
			} else {
				bi.setMFAMuscleForceTrunkOther(sp_MFAMuscleForceTrunkOther.getSelectedItemPosition() + "");
			}
			bi.setMFAMuscleForceRightUpper(sp_MFAMuscleForceRightUpper.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceRightLower(sp_MFAMuscleForceRightLower.getSelectedItemPosition() + "");
			bi.setMFAMuscleForceRightFeet(sp_MFAMuscleForceRightFeet.getSelectedItemPosition() + "");
			if (et_MFAMuscleForceRightOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscleForceRightOther("0");
			} else {
				bi.setMFAMuscleForceRightOther(sp_MFAMuscleForceRightOther.getSelectedItemPosition() + "");
			}

			bi.setMFAMuscularTensionLeftHand(sp_MFAMuscularTensionLeftHand.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionLeftUpper(sp_MFAMuscularTensionLeftUpper.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionLeftLower(sp_MFAMuscularTensionLeftLower.getSelectedItemPosition() + "");
			if (et_MFAMuscularTensionLeftOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscularTensionLeftOther("0");
			} else {
				bi.setMFAMuscularTensionLeftOther(sp_MFAMuscularTensionLeftOther.getSelectedItemPosition() + "");
			}
			bi.setMFAMuscularTensionTrunkBack(sp_MFAMuscularTensionTrunkBack.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionTrunkWaist(sp_MFAMuscularTensionTrunkWaist.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionTrunkButtocks(sp_MFAMuscularTensionTrunkButtocks.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionTrunkChest(sp_MFAMuscularTensionTrunkChest.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionTrunkAbdomen(sp_MFAMuscularTensionTrunkAbdomen.getSelectedItemPosition() + "");
			if (et_MFAMuscularTensionTrunkOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscularTensionTrunkOther("0");
			} else {
				bi.setMFAMuscularTensionTrunkOther(sp_MFAMuscularTensionTrunkOther.getSelectedItemPosition() + "");
			}
			bi.setMFAMuscularTensionRightUpper(sp_MFAMuscularTensionRightUpper.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionRightLower(sp_MFAMuscularTensionRightLower.getSelectedItemPosition() + "");
			bi.setMFAMuscularTensionRightFeet(sp_MFAMuscularTensionRightFeet.getSelectedItemPosition() + "");
			if (et_MFAMuscularTensionRightOtherName.getText().toString().trim().equals("")) {
				bi.setMFAMuscularTensionRightOther("0");
			} else {
				bi.setMFAMuscularTensionRightOther(sp_MFAMuscularTensionRightOther.getSelectedItemPosition() + "");
			}

			bi.setMFAPainScoreLeftHand(sp_MFAPainScoreLeftHand.getSelectedItemPosition() + "");
			bi.setMFAPainScoreLeftUpper(sp_MFAPainScoreLeftUpper.getSelectedItemPosition() + "");
			bi.setMFAPainScoreLeftLower(sp_MFAPainScoreLeftLower.getSelectedItemPosition() + "");
			if (et_MFAPainScoreLeftOtherName.getText().toString().trim().equals("")) {
				bi.setMFAPainScoreLeftOther("0");
			} else {
				bi.setMFAPainScoreLeftOther(sp_MFAPainScoreLeftOther.getSelectedItemPosition() + "");
			}
			bi.setMFAPainScoreTrunkBack(sp_MFAPainScoreTrunkBack.getSelectedItemPosition() + "");
			bi.setMFAPainScoreTrunkWaist(sp_MFAPainScoreTrunkWaist.getSelectedItemPosition() + "");
			bi.setMFAPainScoreTrunkButtocks(sp_MFAPainScoreTrunkButtocks.getSelectedItemPosition() + "");
			bi.setMFAPainScoreTrunkChest(sp_MFAPainScoreTrunkChest.getSelectedItemPosition() + "");
			bi.setMFAPainScoreTrunkAbdomen(sp_MFAPainScoreTrunkAbdomen.getSelectedItemPosition() + "");
			if (et_MFAPainScoreTrunkOtherName.getText().toString().trim().equals("")) {
				bi.setMFAPainScoreTrunkOther("0");
			} else {
				bi.setMFAPainScoreTrunkOther(sp_MFAPainScoreTrunkOther.getSelectedItemPosition() + "");
			}
			bi.setMFAPainScoreRightUpper(sp_MFAPainScoreRightUpper.getSelectedItemPosition() + "");
			bi.setMFAPainScoreRightLower(sp_MFAPainScoreRightLower.getSelectedItemPosition() + "");
			bi.setMFAPainScoreRightFeet(sp_MFAPainScoreRightFeet.getSelectedItemPosition() + "");
			if (et_MFAPainScoreRightOtherName.getText().toString().trim().equals("")) {
				bi.setMFAPainScoreRightOther("0");
			} else {
				bi.setMFAPainScoreRightOther(sp_MFAPainScoreRightOther.getSelectedItemPosition() + "");
			}

			try {
				if (dbUtils.findFirst(Selector.from(SF_MFA.class).where("ReportId", "=", reportId)) != null) {

					bi = dbUtils.findFirst(Selector.from(SF_MFA.class).where("ReportId", "=", reportId));
					bi.setMFASittingBalance(sp_MFASittingBalance.getSelectedItemPosition() + "");
					bi.setMFAPositionBalance(sp_MFAPositionBalance.getSelectedItemPosition() + "");
					bi.setMFAHoldenWalkingAbility(sp_MFAHoldenWalkingAbility.getSelectedItemPosition() + "");
					bi.setMFASittingBalanceOther(et_MFASittingBalanceOther.getText().toString());
					bi.setMFAPositionBalanceOther(et_MFAPositionBalanceOther.getText().toString());
					bi.setMFAHoldenWalkingAbilityOther(et_MFAHoldenWalkingAbilityOther.getText().toString());

					bi.setMFAMuscleForceLeftOtherName(et_MFAMuscleForceLeftOtherName.getText().toString());
					bi.setMFAMuscleForceTrunkOtherName(et_MFAMuscleForceTrunkOtherName.getText().toString());
					bi.setMFAMuscleForceRightOtherName(et_MFAMuscleForceRightOtherName.getText().toString());
					bi.setMFAMuscularTensionLeftOtherName(et_MFAMuscularTensionLeftOtherName.getText().toString());
					bi.setMFAMuscularTensionTrunkOtherName(et_MFAMuscularTensionTrunkOtherName.getText().toString());
					bi.setMFAMuscularTensionRightOtherName(et_MFAMuscularTensionRightOtherName.getText().toString());
					bi.setMFAPainScoreLeftOtherName(et_MFAPainScoreLeftOtherName.getText().toString());
					bi.setMFAPainScoreTrunkOtherName(et_MFAPainScoreTrunkOtherName.getText().toString());
					bi.setMFAPainScoreRightOtherName(et_MFAPainScoreRightOtherName.getText().toString());

					bi.setMFAMuscleForce(rg_MFAMuscleForce.getCheckedRadioButtonId() + "");
					bi.setMFAMuscularTension(rg_MFAMuscularTension.getCheckedRadioButtonId() + "");
					bi.setMFAPainScore(rg_MFAPainScore.getCheckedRadioButtonId() + "");
					bi.setRiskLevel(rg_RiskLevel.getCheckedRadioButtonId() + "");

					bi.setMFAMuscleForceLeftHand(sp_MFAMuscleForceLeftHand.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceLeftUpper(sp_MFAMuscleForceLeftUpper.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceLeftLower(sp_MFAMuscleForceLeftLower.getSelectedItemPosition() + "");
					if (et_MFAMuscleForceLeftOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscleForceLeftOther("0");
					} else {
						bi.setMFAMuscleForceLeftOther(sp_MFAMuscleForceLeftOther.getSelectedItemPosition() + "");
					}
					bi.setMFAMuscleForceTrunkBack(sp_MFAMuscleForceTrunkBack.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceTrunkWaist(sp_MFAMuscleForceTrunkWaist.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceTrunkButtocks(sp_MFAMuscleForceTrunkButtocks.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceTrunkChest(sp_MFAMuscleForceTrunkChest.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceTrunkAbdomen(sp_MFAMuscleForceTrunkAbdomen.getSelectedItemPosition() + "");
					if (et_MFAMuscleForceTrunkOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscleForceTrunkOther("0");
					} else {
						bi.setMFAMuscleForceTrunkOther(sp_MFAMuscleForceTrunkOther.getSelectedItemPosition() + "");
					}
					bi.setMFAMuscleForceRightUpper(sp_MFAMuscleForceRightUpper.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceRightLower(sp_MFAMuscleForceRightLower.getSelectedItemPosition() + "");
					bi.setMFAMuscleForceRightFeet(sp_MFAMuscleForceRightFeet.getSelectedItemPosition() + "");
					if (et_MFAMuscleForceRightOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscleForceRightOther("0");
					} else {
						bi.setMFAMuscleForceRightOther(sp_MFAMuscleForceRightOther.getSelectedItemPosition() + "");
					}

					bi.setMFAMuscularTensionLeftHand(sp_MFAMuscularTensionLeftHand.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionLeftUpper(sp_MFAMuscularTensionLeftUpper.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionLeftLower(sp_MFAMuscularTensionLeftLower.getSelectedItemPosition() + "");
					if (et_MFAMuscularTensionLeftOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscularTensionLeftOther("0");
					} else {
						bi.setMFAMuscularTensionLeftOther(sp_MFAMuscularTensionLeftOther.getSelectedItemPosition() + "");
					}
					bi.setMFAMuscularTensionTrunkBack(sp_MFAMuscularTensionTrunkBack.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionTrunkWaist(sp_MFAMuscularTensionTrunkWaist.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionTrunkButtocks(sp_MFAMuscularTensionTrunkButtocks.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionTrunkChest(sp_MFAMuscularTensionTrunkChest.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionTrunkAbdomen(sp_MFAMuscularTensionTrunkAbdomen.getSelectedItemPosition() + "");
					if (et_MFAMuscularTensionTrunkOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscularTensionTrunkOther("0");
					} else {
						bi.setMFAMuscularTensionTrunkOther(sp_MFAMuscularTensionTrunkOther.getSelectedItemPosition() + "");
					}
					bi.setMFAMuscularTensionRightUpper(sp_MFAMuscularTensionRightUpper.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionRightLower(sp_MFAMuscularTensionRightLower.getSelectedItemPosition() + "");
					bi.setMFAMuscularTensionRightFeet(sp_MFAMuscularTensionRightFeet.getSelectedItemPosition() + "");
					if (et_MFAMuscularTensionRightOtherName.getText().toString().trim().equals("")) {
						bi.setMFAMuscularTensionRightOther("0");
					} else {
						bi.setMFAMuscularTensionRightOther(sp_MFAMuscularTensionRightOther.getSelectedItemPosition() + "");
					}

					bi.setMFAPainScoreLeftHand(sp_MFAPainScoreLeftHand.getSelectedItemPosition() + "");
					bi.setMFAPainScoreLeftUpper(sp_MFAPainScoreLeftUpper.getSelectedItemPosition() + "");
					bi.setMFAPainScoreLeftLower(sp_MFAPainScoreLeftLower.getSelectedItemPosition() + "");
					if (et_MFAPainScoreLeftOtherName.getText().toString().trim().equals("")) {
						bi.setMFAPainScoreLeftOther("0");
					} else {
						bi.setMFAPainScoreLeftOther(sp_MFAPainScoreLeftOther.getSelectedItemPosition() + "");
					}
					bi.setMFAPainScoreTrunkBack(sp_MFAPainScoreTrunkBack.getSelectedItemPosition() + "");
					bi.setMFAPainScoreTrunkWaist(sp_MFAPainScoreTrunkWaist.getSelectedItemPosition() + "");
					bi.setMFAPainScoreTrunkButtocks(sp_MFAPainScoreTrunkButtocks.getSelectedItemPosition() + "");
					bi.setMFAPainScoreTrunkChest(sp_MFAPainScoreTrunkChest.getSelectedItemPosition() + "");
					bi.setMFAPainScoreTrunkAbdomen(sp_MFAPainScoreTrunkAbdomen.getSelectedItemPosition() + "");
					if (et_MFAPainScoreTrunkOtherName.getText().toString().trim().equals("")) {
						bi.setMFAPainScoreTrunkOther("0");
					} else {
						bi.setMFAPainScoreTrunkOther(sp_MFAPainScoreTrunkOther.getSelectedItemPosition() + "");
					}
					bi.setMFAPainScoreRightUpper(sp_MFAPainScoreRightUpper.getSelectedItemPosition() + "");
					bi.setMFAPainScoreRightLower(sp_MFAPainScoreRightLower.getSelectedItemPosition() + "");
					bi.setMFAPainScoreRightFeet(sp_MFAPainScoreRightFeet.getSelectedItemPosition() + "");
					if (et_MFAPainScoreRightOtherName.getText().toString().trim().equals("")) {
						bi.setMFAPainScoreRightOther("0");
					} else {
						bi.setMFAPainScoreRightOther(sp_MFAPainScoreRightOther.getSelectedItemPosition() + "");
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

		case R.id.bt_add_copm:
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		
		switch (parent.getId()) {
		case R.id.sp_MFASittingBalance:
			if (position == 4) {
				et_MFASittingBalanceOther.setVisibility(View.VISIBLE);
			} else {
				et_MFASittingBalanceOther.setText("");
				et_MFASittingBalanceOther.setVisibility(View.GONE);
			}
			break;
		case R.id.sp_MFAPositionBalance:
			if (position == 4) {
				et_MFAPositionBalanceOther.setVisibility(View.VISIBLE);
			} else {
				et_MFAPositionBalanceOther.setText("");
				et_MFAPositionBalanceOther.setVisibility(View.GONE);
			}
			break;
		case R.id.sp_MFAHoldenWalkingAbility:
			if (position == 7) {
				et_MFAHoldenWalkingAbilityOther.setVisibility(View.VISIBLE);
			} else {
				et_MFAHoldenWalkingAbilityOther.setText("");
				et_MFAHoldenWalkingAbilityOther.setVisibility(View.GONE);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch (group.getId()) {
		case R.id.rg_MFAMuscleForce:
			if (checkedId == 1) {
				lay_MFAMuscleForce.setVisibility(View.VISIBLE);
			} else {
				lay_MFAMuscleForce.setVisibility(View.GONE);

				et_MFAMuscleForceLeftOtherName.setText("");
				et_MFAMuscleForceTrunkOtherName.setText("");
				et_MFAMuscleForceRightOtherName.setText("");

				sp_MFAMuscleForceLeftHand.setSelection(0);
				sp_MFAMuscleForceLeftUpper.setSelection(0);
				sp_MFAMuscleForceLeftLower.setSelection(0);
				sp_MFAMuscleForceLeftOther.setSelection(0);
				sp_MFAMuscleForceTrunkBack.setSelection(0);
				sp_MFAMuscleForceTrunkWaist.setSelection(0);
				sp_MFAMuscleForceTrunkButtocks.setSelection(0);
				sp_MFAMuscleForceTrunkChest.setSelection(0);
				sp_MFAMuscleForceTrunkAbdomen.setSelection(0);
				sp_MFAMuscleForceTrunkOther.setSelection(0);
				sp_MFAMuscleForceRightUpper.setSelection(0);
				sp_MFAMuscleForceRightLower.setSelection(0);
				sp_MFAMuscleForceRightFeet.setSelection(0);
				sp_MFAMuscleForceRightOther.setSelection(0);
			}
			break;
		case R.id.rg_MFAMuscularTension:
			if (checkedId == 1) {
				lay_MFAMuscularTension.setVisibility(View.VISIBLE);
			} else {
				lay_MFAMuscularTension.setVisibility(View.GONE);

				et_MFAMuscularTensionLeftOtherName.setText("");
				et_MFAMuscularTensionTrunkOtherName.setText("");
				et_MFAMuscularTensionRightOtherName.setText("");

				sp_MFAMuscularTensionLeftHand.setSelection(0);
				sp_MFAMuscularTensionLeftUpper.setSelection(0);
				sp_MFAMuscularTensionLeftLower.setSelection(0);
				sp_MFAMuscularTensionLeftOther.setSelection(0);
				sp_MFAMuscularTensionTrunkBack.setSelection(0);
				sp_MFAMuscularTensionTrunkWaist.setSelection(0);
				sp_MFAMuscularTensionTrunkButtocks.setSelection(0);
				sp_MFAMuscularTensionTrunkChest.setSelection(0);
				sp_MFAMuscularTensionTrunkAbdomen.setSelection(0);
				sp_MFAMuscularTensionTrunkOther.setSelection(0);
				sp_MFAMuscularTensionRightUpper.setSelection(0);
				sp_MFAMuscularTensionRightLower.setSelection(0);
				sp_MFAMuscularTensionRightFeet.setSelection(0);
				sp_MFAMuscularTensionRightOther.setSelection(0);
			}
			break;
		case R.id.rg_MFAPainScore:
			if (checkedId == 1) {
				lay_MFAPainScore.setVisibility(View.VISIBLE);
			} else {
				lay_MFAPainScore.setVisibility(View.GONE);

				et_MFAPainScoreLeftOtherName.setText("");
				et_MFAPainScoreTrunkOtherName.setText("");
				et_MFAPainScoreRightOtherName.setText("");

				sp_MFAPainScoreLeftHand.setSelection(0);
				sp_MFAPainScoreLeftUpper.setSelection(0);
				sp_MFAPainScoreLeftLower.setSelection(0);
				sp_MFAPainScoreLeftOther.setSelection(0);
				sp_MFAPainScoreTrunkBack.setSelection(0);
				sp_MFAPainScoreTrunkWaist.setSelection(0);
				sp_MFAPainScoreTrunkButtocks.setSelection(0);
				sp_MFAPainScoreTrunkChest.setSelection(0);
				sp_MFAPainScoreTrunkAbdomen.setSelection(0);
				sp_MFAPainScoreTrunkOther.setSelection(0);
				sp_MFAPainScoreRightUpper.setSelection(0);
				sp_MFAPainScoreRightLower.setSelection(0);
				sp_MFAPainScoreRightFeet.setSelection(0);
				sp_MFAPainScoreRightOther.setSelection(0);
			}
			break;

		default:
			break;
		}
	}

}