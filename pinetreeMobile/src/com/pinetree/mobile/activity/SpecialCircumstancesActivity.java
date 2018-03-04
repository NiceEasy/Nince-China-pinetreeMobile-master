package com.pinetree.mobile.activity;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.SpecialCircumstances;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SpecialCircumstancesActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.back_specialCircumstances_imageView)
	private ImageView backSpecialCircumstancesImageView;
	@ViewInject(R.id.specialCircumstances_listView)
	private ListView specialCircumstancesListView;
	private ArrayList<SpecialCircumstances> specialList = new ArrayList<SpecialCircumstances>();
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special_circumstances);
		
		ViewUtils.inject(this);
		backSpecialCircumstancesImageView.setOnClickListener(this);
		
		Intent intent = getIntent();
		String custID = intent.getStringExtra("custID");
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(this);
		specialList.addAll(pinetreeDB.selectSpecialCircumstancesByCustId(custID));
		adapter = new MyAdapter();
		if (specialList != null && !"".equals(specialList) && specialList.size() > 0) {
			specialCircumstancesListView.setAdapter(adapter);
		}else {
			ToastUtils.showToast(SpecialCircumstancesActivity.this,"暂无特殊详情！");
		}
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.back_specialCircumstances_imageView:
			finish();
			break;

		default:
			break;
		}
		
	}

	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			return specialList.size();
		}

		@Override
		public Object getItem(int arg0) {
			
			return specialList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			
			return arg0;
		}

		@Override
		public View getView(int arg0, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(SpecialCircumstancesActivity.this).inflate(R.layout.special_item, null);
				holder.specialTextView = (TextView) convertView.findViewById(R.id.special_textView);
				convertView.setTag(holder); 
			}else
			holder = (ViewHolder) convertView.getTag();
			
			if (!"".equals(specialList.get(arg0).getSpecial().toString()) && null != specialList.get(arg0).getSpecial().toString()) {
				holder.specialTextView.setText(specialList.get(arg0).getSpecial().toString());
			}
			
			return convertView;
		}
		
	}

}

	class ViewHolder{
		public TextView specialTextView;
	}
