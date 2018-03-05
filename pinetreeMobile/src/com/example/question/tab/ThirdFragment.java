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
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.AIAbilityAssessmentConclusion;
import com.pinetree.mobile.activity.AIActivityofDailyLlivingActivity;
import com.pinetree.mobile.activity.AIAssessmentBasisInfoActivity;
import com.pinetree.mobile.activity.AIAssessmentSupplementaryInfoActivity;
import com.pinetree.mobile.activity.AIMainAssessorInfoActivity;
import com.pinetree.mobile.activity.AIMentalStateActivity;
import com.pinetree.mobile.activity.AISensoryPerceptionAndCommunicationActivity;
import com.pinetree.mobile.activity.AISocialParticipationActivity;
import com.pinetree.mobile.activity.AISupplementaryAssessmentInfoActivity;
import com.shizhefei.fragment.LazyFragment;

/**
 * 
 * @类描述	第三个tab：评估信息主列表
 * @author wcm 
 * @createDate 2015-10-13 上午9:23:13
 */
public class ThirdFragment extends LazyFragment implements OnItemClickListener {
	private ProgressBar progressBar;
	@SuppressWarnings("unused")
	private TextView textView;
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	@ViewInject(R.id.lv)
	private ListView lv;
	private Context mContext;
	private String[] arrayTitle;
	private String reportId;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain_item);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		reportId = getArguments().getString("reportId");
		lv = (ListView) findViewById(R.id.lv);
		initData();
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		mContext = activity;
	}

	private void initData() {
		Resources rs = getResources();
		arrayTitle = rs.getStringArray(R.array.array_pgxx);
		lv.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, getData()));
		lv.setOnItemClickListener(this);
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data = Arrays.asList(arrayTitle);
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent.setClass(mContext, AIAssessmentBasisInfoActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(mContext, AIActivityofDailyLlivingActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent.setClass(mContext, AIMentalStateActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent.setClass(mContext, AISensoryPerceptionAndCommunicationActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent.setClass(mContext, AISocialParticipationActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent.setClass(mContext, AISupplementaryAssessmentInfoActivity.class);
			startActivity(intent);
			break;
		case 6:
			intent.setClass(mContext, AIAbilityAssessmentConclusion.class);
			startActivity(intent);
			break;
		case 7:
			intent.setClass(mContext, AIMainAssessorInfoActivity.class);
			startActivity(intent);
			break;
		case 8:
			intent.setClass(mContext, AIAssessmentSupplementaryInfoActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
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
