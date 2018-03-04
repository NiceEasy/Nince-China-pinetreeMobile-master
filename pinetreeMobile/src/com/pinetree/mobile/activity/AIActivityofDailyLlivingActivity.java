package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.AI_ActivityofDailyLliving;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_activity_of_daily_living)
public class AIActivityofDailyLlivingActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	@ViewInject(R.id.tv_second_title)
	private TextView tv_second_title;
	@ViewInject(R.id.tv_third_title0)
	private TextView tv_third_title0;
	@ViewInject(R.id.tv_third_title1)
	private TextView tv_third_title1;
	@ViewInject(R.id.tv_third_title2)
	private TextView tv_third_title2;
	@ViewInject(R.id.tv_third_title3)
	private TextView tv_third_title3;
	@ViewInject(R.id.tv_third_title7)
	private TextView tv_third_title7;
	@ViewInject(R.id.tv_third_title10)
	private TextView tv_third_title10;
	@ViewInject(R.id.tv_third_title11)
	private TextView tv_third_title11;
	int[] id = { R.id.activity_ai_activity_of_daily_living_part0, R.id.activity_ai_activity_of_daily_living_part1, R.id.activity_ai_activity_of_daily_living_part2,
			R.id.activity_ai_activity_of_daily_living_part3, R.id.activity_ai_activity_of_daily_living_part4, R.id.activity_ai_activity_of_daily_living_part5,
			R.id.activity_ai_activity_of_daily_living_part6, R.id.activity_ai_activity_of_daily_living_part7, R.id.activity_ai_activity_of_daily_living_part8,
			R.id.activity_ai_activity_of_daily_living_part9 };
	@ResInject(id = R.array.array_rcshnl, type = ResType.StringArray)
	private String[] array_rcshnl;
	@ResInject(id = R.array.array_js, type = ResType.StringArray)
	private String[] array_js;
	@ResInject(id = R.array.array_xz, type = ResType.StringArray)
	private String[] array_xz;
	@ResInject(id = R.array.array_xs, type = ResType.StringArray)
	private String[] array_xs;
	@ResInject(id = R.array.array_cy, type = ResType.StringArray)
	private String[] array_cy;
	@ResInject(id = R.array.array_dbkz, type = ResType.StringArray)
	private String[] array_dbkz;
	@ResInject(id = R.array.array_xbkz, type = ResType.StringArray)
	private String[] array_xbkz;
	@ResInject(id = R.array.array_rc, type = ResType.StringArray)
	private String[] array_rc;
	@ResInject(id = R.array.array_cyzy, type = ResType.StringArray)
	private String[] array_cyzy;
	@ResInject(id = R.array.array_pdxz, type = ResType.StringArray)
	private String[] array_pdxz;
	@ResInject(id = R.array.array_sxlt, type = ResType.StringArray)
	private String[] array_sxlt;
	@ResInject(id = R.array.array_hdfj, type = ResType.StringArray)
	private String[] array_hdfj;

	private String[][] childs;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	@ViewInject(R.id.rg_DailyActivityLevel)
	RadioGroup rg_DailyActivityLevel;
	@ViewInject(R.id.et_SumScore)
	EditText et_SumScore;

	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private View view;
	private int score = 100;
	private List<RadioGroup> list_rg;
	private List<Integer> ids;
	private List<Integer> maps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewUtils.inject(this);
		mContext = this;
		initView();
		initData();

	}

	@Override
	public void initView() {
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
		list_rg = new ArrayList<RadioGroup>();
		ids = new ArrayList<Integer>();
		maps = new ArrayList<Integer>();

		childs = new String[][] { array_js, array_xz, array_xs, array_cy, array_dbkz, array_xbkz, array_rc, array_cyzy, array_pdxz, array_sxlt };
		for (int i = 0; i < array_rcshnl.length; i++) {
			view = findViewById(id[i]);
			RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg);
			int id = (int) System.currentTimeMillis();
			rg.setId(id);
			Tools.addRadioButtonV(mContext, childs[i], rg);
			list_rg.add(rg);
			ids.add(id);
			maps.add(i, getScore(childs[i].length, 0));
		}
		Tools.addRadioButtonV(mContext, array_hdfj, rg_DailyActivityLevel);
		et_SumScore.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				score = Integer.parseInt(et_SumScore.getText().toString());
				int n = 0;
				if (score == 100) {
					n = 0;
				} else if (65 <= score && score <= 95) {
					n = 1;
				} else if (45 <= score && score <= 60) {
					n = 2;
				} else {
					n = 3;
				}
				rg_DailyActivityLevel.check(n);
			}
		});
		et_SumScore.setText(score + "");
		for (int i = 0; i < array_rcshnl.length; i++) {
			list_rg.get(i).setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					
					int checkedItem = checkedId;
					for (int j = 0; j < ids.size(); j++) {
						if (group.getId() == ids.get(j)) {
							int scoreItem = getScore(childs[j].length, checkedItem);
							maps.set(j, scoreItem);
						}
					}
					score = 0;
					for (int k = 0; k < array_rcshnl.length; k++) {
						score += maps.get(k);
					}
					et_SumScore.setText(score + "");
				}
			});
		}
		tv_second_title.setOnLongClickListener(this);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title7.setOnLongClickListener(this);
		tv_third_title10.setOnLongClickListener(this);
		tv_third_title11.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_ActivityofDailyLliving bi = dbUtils.findFirst(Selector.from(AI_ActivityofDailyLliving.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {

				et_SumScore.setText(bi.getSumScore());
				rg_DailyActivityLevel.check(Integer.parseInt(bi.getDailyActivityLevel()));
				for (int i = 0; i < array_rcshnl.length; i++) {
					RadioGroup rg = list_rg.get(i);
					switch (i) {
					case 0:
						rg.check(Integer.parseInt(bi.getEatExplain()));
						break;
					case 1:
						rg.check(Integer.parseInt(bi.getBathExplain()));
						break;
					case 2:
						rg.check(Integer.parseInt(bi.getModificationExplain()));
						break;
					case 3:
						rg.check(Integer.parseInt(bi.getDressingExplain()));
						break;
					case 4:
						rg.check(Integer.parseInt(bi.getStoolControlExplain()));
						break;
					case 5:
						rg.check(Integer.parseInt(bi.getUrineControlExplain()));
						break;
					case 6:
						rg.check(Integer.parseInt(bi.getGoToTheToiletExplain()));
						break;
					case 7:
						rg.check(Integer.parseInt(bi.getBedChairTransferExplain()));
						break;
					case 8:
						rg.check(Integer.parseInt(bi.getFlatWalkingExplain()));
						break;
					case 9:
						rg.check(Integer.parseInt(bi.getUpAndDownStairsExplain()));
						break;
					default:
						break;
					}
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		dbUtils.close();
		score = Integer.parseInt(et_SumScore.getText().toString());
	}

	@OnClick({ R.id.bt_save, R.id.bt_back })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:
			DbUtils dbUtils = DbUtils.create(this);
			AI_ActivityofDailyLliving bi = null;
			bi = new AI_ActivityofDailyLliving();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setSumScore(et_SumScore.getText().toString());
			bi.setDailyActivityLevel(rg_DailyActivityLevel.getCheckedRadioButtonId() + "");
			for (int i = 0; i < array_rcshnl.length; i++) {
				RadioGroup rg = list_rg.get(i);
				int checkedItem = rg.getCheckedRadioButtonId();
				String scoreItem = getScore(childs[i].length, checkedItem) + "";
				switch (i) {
				case 0:
					bi.setEatExplain(checkedItem + "");
					bi.setEatScore(scoreItem);
					break;
				case 1:
					bi.setBathExplain(checkedItem + "");
					bi.setBathScore(scoreItem);
					break;
				case 2:
					bi.setModificationExplain(checkedItem + "");
					bi.setModificationScore(scoreItem);
					break;
				case 3:
					bi.setDressingExplain(checkedItem + "");
					bi.setDressingScore(scoreItem);
					break;
				case 4:
					bi.setStoolControlExplain(checkedItem + "");
					bi.setStoolControlScore(scoreItem);
					break;
				case 5:
					bi.setUrineControlExplain(checkedItem + "");
					bi.setUrineControlScore(scoreItem);
					break;
				case 6:
					bi.setGoToTheToiletExplain(checkedItem + "");
					bi.setGoToTheToiletScore(scoreItem);
					break;
				case 7:
					bi.setBedChairTransferExplain(checkedItem + "");
					bi.setBedChairTransferScore(scoreItem);
					break;
				case 8:
					bi.setFlatWalkingExplain(checkedItem + "");
					bi.setFlatWalkingScore(scoreItem);
					break;
				case 9:
					bi.setUpAndDownStairsExplain(checkedItem + "");
					bi.setUpAndDownStairsScore(scoreItem);
					break;

				default:
					break;
				}
			}

			try {
				if (dbUtils.findFirst(Selector.from(AI_ActivityofDailyLliving.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_ActivityofDailyLliving.class).where("ReportId", "=", reportId));
					bi.setSumScore(et_SumScore.getText().toString());
					bi.setDailyActivityLevel(rg_DailyActivityLevel.getCheckedRadioButtonId() + "");
					for (int i = 0; i < array_rcshnl.length; i++) {
						RadioGroup rg = list_rg.get(i);
						int checkedItem = rg.getCheckedRadioButtonId();
						String scoreItem = getScore(childs[i].length, checkedItem) + "";
						switch (i) {
						case 0:
							bi.setEatExplain(checkedItem + "");
							bi.setEatScore(scoreItem);
							break;
						case 1:
							bi.setBathExplain(checkedItem + "");
							bi.setBathScore(scoreItem);
							break;
						case 2:
							bi.setModificationExplain(checkedItem + "");
							bi.setModificationScore(scoreItem);
							break;
						case 3:
							bi.setDressingExplain(checkedItem + "");
							bi.setDressingScore(scoreItem);
							break;
						case 4:
							bi.setStoolControlExplain(checkedItem + "");
							bi.setStoolControlScore(scoreItem);
							break;
						case 5:
							bi.setUrineControlExplain(checkedItem + "");
							bi.setUrineControlScore(scoreItem);
							break;
						case 6:
							bi.setGoToTheToiletExplain(checkedItem + "");
							bi.setGoToTheToiletScore(scoreItem);
							break;
						case 7:
							bi.setBedChairTransferExplain(checkedItem + "");
							bi.setBedChairTransferScore(scoreItem);
							break;
						case 8:
							bi.setFlatWalkingExplain(checkedItem + "");
							bi.setFlatWalkingScore(scoreItem);
							break;
						case 9:
							bi.setUpAndDownStairsExplain(checkedItem + "");
							bi.setUpAndDownStairsScore(scoreItem);
							break;

						default:
							break;
						}
					}
					dbUtils.update(bi);
					Toast.makeText(this, getResources().getString(R.string.success_update), Toast.LENGTH_SHORT).show();
				} else {
					dbUtils.save(bi);
					Toast.makeText(this, getResources().getString(R.string.success_save), Toast.LENGTH_SHORT).show();
				}
			} catch (DbException e) {
				e.printStackTrace();
			}
			dbUtils.close();
			break;
		default:
			break;
		}
	}

	private int getScore(int items, int checkedItem) {
		int n = 0;
		switch (items - checkedItem) {
		case 4:
			n = 15;
			break;
		case 3:
			n = 10;
			break;
		case 2:
			n = 5;
			break;
		case 1:
			n = 0;
			break;
		default:
			break;
		}
		return n;
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_rcshnl");
			break;
		case R.id.tv_third_title0:
			intent.putExtra("guid", "ai_js");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "ai_xz");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "ai_xs");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "ai_cy");
			break;
		case R.id.tv_third_title7:
			intent.putExtra("guid", "ai_cyzy");
			break;
		case R.id.tv_third_title10:
			intent.putExtra("guid", "ai_rcshzf");
			break;
		case R.id.tv_third_title11:
			intent.putExtra("guid", "ai_rcshfj");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}

}