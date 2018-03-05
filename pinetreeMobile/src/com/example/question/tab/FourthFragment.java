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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.SFCOPMActivity;
import com.pinetree.mobile.activity.SFMFAActivity;
import com.pinetree.mobile.activity.SFMentalStateAssessmentActivity;
import com.pinetree.mobile.activity.SFPersonalSupplementActivity;
import com.shizhefei.fragment.LazyFragment;

/**
 * 
 * @类描述	第四个tab：补充信息主列表
 * @author wcm 
 * @createDate 2015-10-13 上午9:22:44
 */
public class FourthFragment extends LazyFragment implements OnItemClickListener {
	private ProgressBar progressBar;
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
		arrayTitle = rs.getStringArray(R.array.array_bcxx);
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
			intent.setClass(mContext, SFPersonalSupplementActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(mContext, SFMentalStateAssessmentActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent.setClass(mContext, SFMFAActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent.setClass(mContext, SFCOPMActivity.class);
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
