package com.example.question.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;
import com.shizhefei.view.indicator.FragmentListPageAdapter;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

public class MoreTabActivity extends FragmentActivity {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	private String[] names = { "主页", "基本信息", "评估信息",  "HONEYCOMB", "ICE CREAM SANDWICH", "JELLY BEAN", "KITKAT" };
	private ScrollIndicatorView indicator;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private int size = 3;
	@ViewInject(R.id.tv_title)
	private TextView tvTitle;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private String scheduleId = "";
	private String employeeID = "";
	private String employeeName = "";
	private String prodID = "";
	private String projectId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private Customer customer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_moretab);
		ViewUtils.inject(this);
		sharedPreferencesUtil = new SharedPreferencesUtil(getApplicationContext());
		if (getIntent().getBooleanExtra("isNew", false)) {
			tvTitle.setText(getResources().getString(R.string.new_title));
			sharedPreferencesUtil.put("reportId", Tools.getCurrentTimeMillis() + "");
		} else {
			reportId = getIntent().getExtras().getString("reportId");
			scheduleId = getIntent().getExtras().getString("scheduleId");
			employeeID = getIntent().getExtras().getString("employeeID");
			employeeName = getIntent().getExtras().getString("employeeName");
			prodID = getIntent().getExtras().getString("prodID");
			projectId = getIntent().getExtras().getString("projectId");
			customer = (Customer) getIntent().getExtras().getSerializable("customer");
			sharedPreferencesUtil.put("reportId", reportId);
			sharedPreferencesUtil.put("scheduleId", scheduleId);
			sharedPreferencesUtil.put("employeeID", employeeID);
			sharedPreferencesUtil.put("employeeName", employeeName);
			sharedPreferencesUtil.put("prodID", prodID);
			sharedPreferencesUtil.put("projectId", projectId);
			sharedPreferencesUtil.put("bI_IDInformationList", 
					getIntent().getExtras().getString("bI_IDInformationList"));
			
		}
		ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
		indicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
		indicator.setScrollBar(new ColorBar(this, getResources().getColor(R.color.bg_title), 5));// ���û��������ɫΪ������ɫ

		// 设置滚动监听
		int selectColorId = R.color.tab_top_text_2;
		int unSelectColorId = R.color.tab_top_text_1;
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));

		viewPager.setOffscreenPageLimit(2);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());
		indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

		// 默认true ，自动布局
		indicator.setSplitAuto(true);
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return size;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(names[position % names.length]);
			textView.setPadding(20, 0, 20, 0);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			Fragment fragment = new Fragment();
			Bundle bundle = new Bundle();
			bundle.putSerializable("customer", customer);
			switch (position) {
			case 0:
				fragment = new FirstFragment();
				break;
			case 1:
				fragment = new SecondFragment();
				fragment.setArguments(bundle);
				break;
			case 2:
				fragment = new ThirdFragment();
				break;
			case 3:
				fragment = new FourthFragment();
				break;

			default:
				break;
			}
			bundle.putInt(INTENT_INT_INDEX, position);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getItemPosition(Object object) {
			return FragmentListPageAdapter.POSITION_NONE;
		}

	};

	@OnClick({ R.id.bt_save, R.id.bt_back })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;

		default:
			break;
		}

	}
}
