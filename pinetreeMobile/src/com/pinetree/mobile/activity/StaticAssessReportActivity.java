package com.pinetree.mobile.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StaticAssessReportActivity  extends Activity implements OnClickListener{

	/**
	 * 顶部返回按钮图片
	 */
	@ViewInject(R.id.report_imageView)
	private ImageView ReportImageView;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_static_assess_report);
		
		bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");

		ViewUtils.inject(this);
		
		basicStateLinearLayout.setOnClickListener(this);
		brainsStateLinearLayout.setOnClickListener(this);
		everydayStateLinearLayout.setOnClickListener(this);
		drugStateLinearLayout.setOnClickListener(this);
		sportAssessLinearLayout.setOnClickListener(this);
		copmLinearLayout.setOnClickListener(this);
		ReportImageView.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.report_imageView:
			finish();
			break;
		case R.id.basic_state_linearLayout:// 基本情况
			Intent intentBasicInformation  = new Intent(this, BasicInformationActivity.class);
			intentBasicInformation.putExtras(bundle);
			startActivity(intentBasicInformation);
			break;
		case R.id.brains_state_linearLayout:// 智力状态检查（MMSE)评估
			Intent intentBrain=new Intent(this, BrainStateActivity.class);
			intentBrain.putExtras(bundle);
			startActivity(intentBrain);
			break;
		case R.id.everyday_state_linearLayout:// 日常生活自理能力表（ADL)评估
			Intent intentEveryday=new Intent(this, EverydayStateActivity.class);
			intentEveryday.putExtras(bundle);
			startActivity(intentEveryday);
			break;
		case R.id.drug_state_linearLayout:// 药品使用-查体
			Intent intentDrug=new Intent(this, DrugStateActivity.class);
			intentDrug.putExtras(bundle);
			startActivity(intentDrug);
			break;
		case R.id.sport_assess_linearLayout:// 查体-运动功能评定
			Intent intentAssess=new Intent(this, SportAssessActivity.class);
			intentAssess.putExtras(bundle);
			startActivity(intentAssess);
			break;
		case R.id.copm_linearLayout:// Copm
			Intent intentCopm=new Intent(this, CopmActivity.class);
			intentCopm.putExtras(bundle);
			startActivity(intentCopm);
			break;	
		default:
			break;
		}
		
	}
	
}
