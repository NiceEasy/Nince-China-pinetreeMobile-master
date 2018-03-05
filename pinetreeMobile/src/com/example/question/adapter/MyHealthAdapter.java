package com.example.question.adapter;

import java.util.List;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.RehabilitationMeasures;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyHealthAdapter extends BaseAdapter {

	private Context mContext;
	private List<RehabilitationMeasures> list;
	
	public MyHealthAdapter(Context mContext,List<RehabilitationMeasures> list){
		this.list = list;
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {

		return list.size();
		
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
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_myadapter_tv, null);
			holder = new ViewHolder();
			holder.titleTextView = (TextView) convertView.findViewById(R.id.myAdapter_textView);
			
			convertView.setTag(holder);
			
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		if ("0".equals(list.get(position).getType())) {
			holder.titleTextView.setText(list.get(position).getTitle());
		}else if("1".equals(list.get(position).getType())){
			holder.titleTextView.setText(list.get(position).getTitle());
		}else if("2".equals(list.get(position).getType())){
			holder.titleTextView.setText(list.get(position).getContent());
		}
		
		return convertView;
	}
	
	public class ViewHolder{
		public TextView titleTextView;
	}

}
