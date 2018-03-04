package com.pinetree.mobile.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.bean.NoteBean;
import com.pinetree.mobile.db.NoteOperationDB;
import com.pinetree.mobile.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@ContentView(R.layout.activity_note)
public class NoteActivity extends Activity implements MyBaseInterface {
	private Context mContext;
	@ViewInject(R.id.tv_goal)
	private TextView tv_goal;
	@ViewInject(R.id.tv_definition)
	private TextView tv_definition;
	@ViewInject(R.id.tv_method)
	private TextView tv_method;
	@ViewInject(R.id.tv_record)
	private TextView tv_record;
	@ViewInject(R.id.ll_goal)
	private LinearLayout ll_goal;
	@ViewInject(R.id.ll_definition)
	private LinearLayout ll_definition;
	@ViewInject(R.id.ll_method)
	private LinearLayout ll_method;
	@ViewInject(R.id.ll_record)
	private LinearLayout ll_record;
	@ViewInject(R.id.bt_back)
	private Button bt_back;
	private NoteBean noteBean;
	private NoteOperationDB noteOperationDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewUtils.inject(this);
		mContext = this;
		initView();
		initData();

	}

	@Override
	public void initView() {
		
	}

	@Override
	public void initData() {
		
		if (getIntent().getExtras() != null) {
			String guid = getIntent().getExtras().getString("guid");
			noteOperationDB = new NoteOperationDB(mContext);
			noteBean = noteOperationDB.queryNote(guid);
			if (noteBean != null) {
				if (noteBean.getGoal() != null && !noteBean.getGoal().trim().equals("")) {
					tv_goal.setText(changeRow(noteBean.getGoal()));
				}else{
					ll_goal.setVisibility(View.GONE);
				}
				if (noteBean.getDefinition() != null &&!noteBean.getDefinition().trim().equals("")) {
					tv_definition.setText(changeRow(noteBean.getDefinition()));
				}else{
					ll_definition.setVisibility(View.GONE);
				}
				if (noteBean.getMethod() != null && !noteBean.getMethod().trim().equals("")) {
					tv_method.setText(changeRow(noteBean.getMethod()));
				}else{
					ll_method.setVisibility(View.GONE);
				}
				if (noteBean.getRecord() != null && !noteBean.getRecord().trim().equals("")) {
					tv_record.setText(changeRow(noteBean.getRecord()));
				}else{
					ll_record.setVisibility(View.GONE);
				}
			}

		}

	}

	@OnClick(R.id.bt_back)
	public void clickMethod(View v) {
		finish();
	}
	/**
	 * 实现从sqlite查询到的字符串进行换行
	 * @param a
	 * @return
	 */
	public String changeRow(String a){
		return a.replaceAll("\\\\n", "\\\n");
	}
}
