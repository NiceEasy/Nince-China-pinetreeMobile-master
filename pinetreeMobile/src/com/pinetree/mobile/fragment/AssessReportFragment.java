package com.pinetree.mobile.fragment;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.BasicInformationActivity;
import com.pinetree.mobile.activity.BrainStateActivity;
import com.pinetree.mobile.activity.CopmActivity;
import com.pinetree.mobile.activity.DrugStateActivity;
import com.pinetree.mobile.activity.EverydayStateActivity;
import com.pinetree.mobile.activity.SportAssessActivity;
import com.pinetree.mobile.bean.Customer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 评估报告
 * 
 * @author Administrator
 * 
 */
public class AssessReportFragment extends Fragment implements OnClickListener {

	/**
	 * 基本情况
	 */
	@ViewInject(R.id.basic_state_linearLayout)
	private LinearLayout basicStateLinearLayout;
	/**
	 * 智力状态检查（MMSE)评估
	 */
	@ViewInject(R.id.brains_state_linearLayout)
	private LinearLayout brainsStateLinearLayout;
	/**
	 * 日常生活自理能力表（ADL)评估
	 */
	@ViewInject(R.id.everyday_state_linearLayout)
	private LinearLayout everydayStateLinearLayout;
	/**
	 * 查体-基本
	 */
	@ViewInject(R.id.drug_state_linearLayout)
	private LinearLayout drugStateLinearLayout;
	/**
	 * 查体-运动功能评定
	 */
	@ViewInject(R.id.sport_assess_linearLayout)
	private LinearLayout sportAssessLinearLayout;
	/**
	 * COPM
	 */
	@ViewInject(R.id.copm_linearLayout)
	private LinearLayout copmLinearLayout;
	/**
	 * 传递过来的客户对象
	 */
	private Customer customer;
	/**
	 * 服务人员name
	 */
	private String employeeName;
	/**
	 * 服务人员id
	 */
	private String employeeId;
	private Bundle bundle;
	private String prodType; //产品类型（0：护理，1：评估，2：体验，3：其他）

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		bundle = this.getArguments();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		prodType = bundle.getString("prodType");
		
		View assessReportLayout = inflater.inflate(R.layout.fragment_assess_report, null, false);
		
		ViewUtils.inject(this, assessReportLayout);
		
		
		basicStateLinearLayout.setOnClickListener(this);
		brainsStateLinearLayout.setOnClickListener(this);
		everydayStateLinearLayout.setOnClickListener(this);
		drugStateLinearLayout.setOnClickListener(this);
		sportAssessLinearLayout.setOnClickListener(this);
		copmLinearLayout.setOnClickListener(this);
		
		
		return assessReportLayout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.basic_state_linearLayout:// 基本情况
			Intent intentBasicInformation  = new Intent(getActivity(), BasicInformationActivity.class);
			intentBasicInformation.putExtras(bundle);
			startActivity(intentBasicInformation);
			break;
		case R.id.brains_state_linearLayout:// 智力状态检查（MMSE)评估
			Intent intentBrain=new Intent(getActivity(), BrainStateActivity.class);
			intentBrain.putExtras(bundle);
			startActivity(intentBrain);
			break;
		case R.id.everyday_state_linearLayout:// 日常生活自理能力表（ADL)评估
			Intent intentEveryday=new Intent(getActivity(), EverydayStateActivity.class);
			intentEveryday.putExtras(bundle);
			startActivity(intentEveryday);
			break;
		case R.id.drug_state_linearLayout:// 药品使用-查体
			Intent intentDrug=new Intent(getActivity(), DrugStateActivity.class);
			intentDrug.putExtras(bundle);
			startActivity(intentDrug);
			break;
		case R.id.sport_assess_linearLayout:// 查体-运动功能评定
			Intent intentAssess=new Intent(getActivity(), SportAssessActivity.class);
			intentAssess.putExtras(bundle);
			startActivity(intentAssess);
			break;
		case R.id.copm_linearLayout:// Copm
			Intent intentCopm=new Intent(getActivity(), CopmActivity.class);
			intentCopm.putExtras(bundle);
			startActivity(intentCopm);
			break;	
			
			
		default:
			break;
		}

	}
}
