package com.example.question.adapter;

import java.util.HashMap;
import java.util.List;

import com.pinetree.mobile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class AdapterCheckBox extends BaseAdapter {
		// 填充数据的list
		private List<String> list;
		// 用来控制CheckBox的选中状况
		private static HashMap<Integer, Boolean> isSelected;
		// 上下文
		private Context context;
		// 用来导入布局
		private LayoutInflater inflater = null;

		// 构造器
		public AdapterCheckBox(List<String> list, Context context) {
			this.context = context;
			this.list = list;
			inflater = LayoutInflater.from(context);
			isSelected = new HashMap<Integer, Boolean>();
			// 初始化数据
			initData();
		}

		// 初始化isSelected的数据
		private void initData() {
			for (int i = 0; i < list.size(); i++) {
				getIsSelected().put(i, false);
			}
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				// 获得ViewHolder对象
				holder = new ViewHolder();
				// 导入布局并赋值给convertview
				convertView = inflater.inflate(R.layout.item_checkbox, null);
				holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
				holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
				// 为view设置标签
				convertView.setTag(holder);
			} else {
				// 取出holder
				holder = (ViewHolder) convertView.getTag();
			}

			// 设置list中TextView的显示
			holder.tv.setText(list.get(position));
			// 根据isSelected来设置checkbox的选中状况
			holder.cb.setChecked(getIsSelected().get(position));
			return convertView;
		}

		public static HashMap<Integer, Boolean> getIsSelected() {
			return isSelected;
		}

		public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
			AdapterCheckBox.isSelected = isSelected;
		}

		public class ViewHolder {
			public TextView tv = null;
			public CheckBox cb = null;
		}
}