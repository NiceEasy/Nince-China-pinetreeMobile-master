package com.example.question.adapter;

import java.util.ArrayList;
import java.util.List;

import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AdapterMainList extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private List<YangLaoServiceAssessmentReport> arrayList = new ArrayList<YangLaoServiceAssessmentReport>();
	private boolean isShowDel = false;// 控制每行的删除按钮是否可见

	public boolean isShowDel() {
		return isShowDel;
	}

	public void setShowDel(boolean isShowDel) {
		this.isShowDel = isShowDel;
	}

	public AdapterMainList(Context mContext, List<YangLaoServiceAssessmentReport> arrayList) {
		this.arrayList = arrayList;
		this.mContext = mContext;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		
		if (arrayList != null && arrayList.size() > 0) {
			return arrayList.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageView imageView = null, delete;
		TextView path = null, size = null, time = null;

		convertView = mInflater.inflate(R.layout.main_list_item, null);
		path = (TextView) convertView.findViewById(R.id.file_list_item_file_path);
		size = (TextView) convertView.findViewById(R.id.file_list_item_file_size);
		time = (TextView) convertView.findViewById(R.id.file_list_item_file_time);

		path.setText(arrayList.get(position).getReportId());
		size.setText(arrayList.get(position).getCustName());
		time.setText(arrayList.get(position).getReportDate());
		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.color.list_item_bg);
		} else {
			convertView.setBackgroundResource(R.color.list_item_bg1);
		}

		return convertView;
	}

	private void showDeleteAlert(final AdapterView<?> parent, final int position) {

	}
}
