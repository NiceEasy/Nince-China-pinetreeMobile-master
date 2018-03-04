package com.pinetree.mobile.activity;

import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.fragment.AssessReportFragment;
import com.pinetree.mobile.fragment.AssuranceFragment;
import com.pinetree.mobile.fragment.CustomerSignFragment;
import com.pinetree.mobile.fragment.ServerRecordFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * fragment的主activity
 * 
 * @author Administrator
 * 
 */
public class ActivityFragment extends Activity implements OnClickListener {
	/**
	 * 用于展示评估报告的fragment
	 */
	private AssessReportFragment assessReportFragment;
	/**
	 * 用于展示客户签字的fragment
	 */
	private CustomerSignFragment customerSignFragment;
	/**
	 * 用于展示服务记录的fragment
	 */
	private ServerRecordFragment serverRecordFragment;
	/**
	 * 担保金额
	 */
	private AssuranceFragment assuranceFragment;
	/**
	 * 服务记录点击text
	 */
	private TextView serverRecordText;
	/**
	 * 客户签字点击text
	 */
	private TextView customerSignText;
	/**
	 * 评估报告点击text
	 */
	private TextView assessReportText;
	/**
	 * 用于对fragment进行管理
	 */
	private FragmentManager fragmentManager;
	private ImageView ivTitleBack;
	private TextView tvTitleDescribe;
	private Bundle bundle;
	private TextView assuranceText;
	private String prodID = "";
	private String prodType = "";
	private Customer customer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fragment);

		// 初始化布局元素
		initViews();
		fragmentManager = getFragmentManager();

		bundle = getIntent().getExtras();
		customer = new Customer();
		prodID = bundle.getString("prodID");
		prodType = bundle.getString("prodType");
		
		//判断评估报告是否显示 产品类型（0：护理，1：评估，2：体验，3：其他）
		if("1".equals(prodType)){
			serverRecordText.setVisibility(View.GONE);
		}
		
		// 第一次启动时选中第0个tab
		setTabSelection(1);
	}

	private void initViews() {
		serverRecordText = (TextView) findViewById(R.id.serverRecord_text);
		customerSignText = (TextView) findViewById(R.id.customerSign_text);
		assessReportText = (TextView) findViewById(R.id.assessReport_text);
		assuranceText = (TextView) findViewById(R.id.assurance_text);
		ivTitleBack = (ImageView) findViewById(R.id.iv_activity_fragment_back);
		tvTitleDescribe = (TextView) findViewById(R.id.tv_title_describe);

		
		serverRecordText.setOnClickListener(this);
		customerSignText.setOnClickListener(this);
		assessReportText.setOnClickListener(this);
		assuranceText.setOnClickListener(this);
		ivTitleBack.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.serverRecord_text:// 服务记录
			setTabSelection(0);
			break;
		case R.id.customerSign_text:// 用户签字
			setTabSelection(1);
			break;
		case R.id.assessReport_text:// 评估报告
			setTabSelection(2);
			break;
		case R.id.assurance_text:// 担保金额
			setTabSelection(3);
			break;
		case R.id.iv_activity_fragment_back:// 顶部返回按钮
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中tab页
	 * 
	 * @param i
	 */
	private void setTabSelection(int i) {
		// 每次选中之前先清掉上次的选中状态
		clearSelection();
		// 开启一个fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏所有的fragment，防止多个fragment同时显示到界面上的情况
		hideFragments(transaction);

		switch (i) {
		case 0:
			// 当点击了服务记录text，设置text文本颜色为 #cc560f77
			serverRecordText.setTextColor(Color.parseColor("#cc560f77"));
			tvTitleDescribe.setText("服务记录");
			if (serverRecordFragment == null) {
				// 如果服务记录fragment为空，则创建一个添加到界面
				serverRecordFragment = new ServerRecordFragment();
				serverRecordFragment.setArguments(bundle);
				transaction.add(R.id.fragment_content, serverRecordFragment);
			} else {
				// 不为空，直接显示
				transaction.show(serverRecordFragment);
			}
			break;
		case 1:
			// 当点击了客户签字text，设置text文本颜色为 #cc560f77
			customerSignText.setTextColor(Color.parseColor("#cc560f77"));
			tvTitleDescribe.setText("客户签字");
			if (customerSignFragment == null) {
				customerSignFragment = new CustomerSignFragment();
				// 传递数据给fragment
				customerSignFragment.setArguments(bundle);
				transaction.add(R.id.fragment_content, customerSignFragment);
			} else {
				transaction.show(customerSignFragment);
			}
			break;
		case 2:
			// 当点击了评估报告
			assessReportText.setTextColor(Color.parseColor("#cc560f77"));
			tvTitleDescribe.setText("评估报告");
			if (assessReportFragment == null) {
				assessReportFragment = new AssessReportFragment();
				assessReportFragment.setArguments(bundle);
				transaction.add(R.id.fragment_content, assessReportFragment);
			} else {
				transaction.show(assessReportFragment);
			}
			break;
		case 3:
			// 当点击了担保金额
			assuranceText.setTextColor(Color.parseColor("#cc560f77"));
			tvTitleDescribe.setText("担保金额");
			if (assuranceFragment == null) {
				assuranceFragment = new AssuranceFragment();
				//传递给fragment
				assuranceFragment.setArguments(bundle);
				transaction.add(R.id.fragment_content, assuranceFragment);
			} else {
				transaction.show(assuranceFragment);
			}
			break;

		}
		transaction.commit();

	}

	/**
	 * 将所有的fragment置为隐藏状态
	 * 
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (serverRecordFragment != null) {
			transaction.hide(serverRecordFragment);
		}
		if (customerSignFragment != null) {
			transaction.hide(customerSignFragment);
		}
		if (assessReportFragment != null) {
			transaction.hide(assessReportFragment);
		}
		if (assuranceFragment != null) {
			transaction.hide(assuranceFragment);
		}

	}

	/**
	 * 清除所有的选中状态
	 */
	private void clearSelection() {
		serverRecordText.setTextColor(Color.parseColor("#000000"));
		customerSignText.setTextColor(Color.parseColor("#000000"));
		assessReportText.setTextColor(Color.parseColor("#000000"));
		assuranceText.setTextColor(Color.parseColor("#000000"));
	}
}
