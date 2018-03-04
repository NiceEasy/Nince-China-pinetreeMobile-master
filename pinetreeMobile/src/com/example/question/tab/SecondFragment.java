package com.example.question.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.BIDemandServiceActivity;
import com.pinetree.mobile.activity.BIDiagnosedDiseaseActivity;
import com.pinetree.mobile.activity.BIEarlyResultsActivity;
import com.pinetree.mobile.activity.BIExternalProfessionalServiceActivity;
import com.pinetree.mobile.activity.BIGuardianInfoActivity;
import com.pinetree.mobile.activity.BIHomePrimaryCargiversInfoActivity;
import com.pinetree.mobile.activity.BIIDInfoActivity;
import com.pinetree.mobile.activity.BIManagementInfoActivity;
import com.pinetree.mobile.activity.BINowLivingActivity;
import com.pinetree.mobile.activity.BIPersonnalInfoActivity;
import com.pinetree.mobile.activity.BISupplementaryInfoActivity;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.shizhefei.fragment.LazyFragment;

/**
 * @类描述	第二个tab：基本信息主列表
 * @author wcm 
 * @createDate 2015-10-13 上午9:23:44
 */
public class SecondFragment extends LazyFragment implements OnItemClickListener {
	private ProgressBar progressBar;
	private TextView textView;
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	@ViewInject(R.id.lv)
	private ListView lv;
	private Context mContext;
	private String[] arrayTitle;
	private String reportId;
	private SharedPreferencesUtil sharedPreferencesUtil;
	private Bundle bundle;
	private Customer customer;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain_item);
		ViewUtils.inject(this, getContentView());
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		reportId = sharedPreferencesUtil.get("reportId");
		bundle = this.getArguments();
		initData();
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
	}

	private void initData() {
		Resources rs = getResources();
		arrayTitle = rs.getStringArray(R.array.array_jbxx);
		lv.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, getData()));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent();
				switch (position) {
				case 0:
					intent.setClass(mContext, BIIDInfoActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					break;
				case 1:
					intent.setClass(mContext, BIPersonnalInfoActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					break;
				case 2:
					intent.setClass(mContext, BIGuardianInfoActivity.class);
					startActivity(intent);
					break;
				case 3:
					intent.setClass(mContext, BINowLivingActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent.setClass(mContext, BIDiagnosedDiseaseActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent.setClass(mContext, BIHomePrimaryCargiversInfoActivity.class);
					startActivity(intent);
					break;
				case 6:
					intent.setClass(mContext, BIExternalProfessionalServiceActivity.class);
					startActivity(intent);
					break;
				case 7:
					intent.setClass(mContext, BIEarlyResultsActivity.class);
					startActivity(intent);
					break;
				case 8:
					intent.setClass(mContext, BIManagementInfoActivity.class);
					startActivity(intent);
					break;
				case 9:
					intent.setClass(mContext, BIDemandServiceActivity.class);
					startActivity(intent);
					break;
				case 10:
					intent.setClass(mContext, BISupplementaryInfoActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}

			}
		});
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data = Arrays.asList(arrayTitle);
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		

	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
		}
	};
}
