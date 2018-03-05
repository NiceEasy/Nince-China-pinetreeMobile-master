package com.example.question.adapter;

import com.pinetree.mobile.R;
import com.pinetree.mobile.utils.Tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ExpandableAdapterRadioGroup extends BaseExpandableListAdapter {

	// 组名称
	private String[] groups = new String[] { "AT-02班", "家人", "同事", "黑名单" };
	// 子选项
	private String[][] children = new String[][] { { "董祥", "刘清华", "小王子", "李世然" }, { "父亲", "母亲" }, { "王五" }, { "票贩子" } };
	// 构造方法（重写方法中，有返回值为view的方法）
	private Context context = null;

	public ExpandableAdapterRadioGroup(Context context, String[] groups, String[][] children) {
		this.context = context;
		this.groups = groups;
		this.children = children;
	}

	// 获得指定组中的指定索引的子项数据
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children[groupPosition][childPosition];
	}

	// 获得指定子项数据的ID
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	private TextView buildTextView() {
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 35);
		TextView textView = new TextView(this.context);
		textView.setLayoutParams(params);
		textView.setTextSize(20.0f);
		textView.setGravity(Gravity.LEFT);
		textView.setPadding(40, 8, 3, 3);
		return textView;
	}

	// 获得指定子项的view组件
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(this.context).inflate(R.layout.exlist_child_radiogroup, null);
		RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_exlist_child);
		Tools.addRadioButtonV(context, children[groupPosition], rg);
		return view;
	}

	// 指定组中，所有子项的个数
	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	// 取得指定组数据
	@Override
	public Object getGroup(int groupPosition) {
		return groups[groupPosition];
	}

	// 取得所有组的个数
	@Override
	public int getGroupCount() {
		return groups.length;
	}

	// 取得指定索引组的ID
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 获得指定组的view
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// 将自定义的布局，转换为View使用。
		View view = LayoutInflater.from(this.context).inflate(R.layout.exlist_group, null);
		TextView textView = (TextView) view.findViewById(R.id.textGroup);
		textView.setText(groups[groupPosition].toString());
		return view;
	}

	// 以下两种方法，通常设置返回为true就可以。
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
