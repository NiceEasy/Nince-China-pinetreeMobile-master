package com.pinetree.mobile.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.pinetree.mobile.bean.AI_MentalState;
import com.pinetree.mobile.utils.SDPath;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_ai_mental_state)
public class AIMentalStateActivity extends Activity implements MyBaseInterface, 
	OnCheckedChangeListener,OnLongClickListener {

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
	@ViewInject(R.id.tv_third_title4)
	private TextView tv_third_title4;
	@ViewInject(R.id.tv_fourth_title0)
	private TextView tv_fourth_title0;
	@ViewInject(R.id.tv_fourth_title1)
	private TextView tv_fourth_title1;
	@ViewInject(R.id.tv_fourth_title2)
	private TextView tv_fourth_title2;
	@ViewInject(R.id.rg_CognitiveFunction)
	private RadioGroup rg_CognitiveFunction;
	@ViewInject(R.id.rg_AggressiveBehavior)
	private RadioGroup rg_AggressiveBehavior;
	@ViewInject(R.id.rg_DepressiveSymptoms)
	private RadioGroup rg_DepressiveSymptoms;
	@ViewInject(R.id.et_CognitiveFunction0)
	private EditText et_CognitiveFunction0;
	@ViewInject(R.id.et_CognitiveFunction1)
	private EditText et_CognitiveFunction1;
	@ViewInject(R.id.et_CognitiveFunction2)
	private EditText et_CognitiveFunction2;
	@ViewInject(R.id.huazhong_imageView)
	private ImageView huazhong_imageView;

	@ResInject(id = R.array.array_rzgn, type = ResType.StringArray)
	private String[] array_rzgn;
	@ResInject(id = R.array.array_gjxw, type = ResType.StringArray)
	private String[] array_gjxw;
	@ResInject(id = R.array.array_yyzz, type = ResType.StringArray)
	private String[] array_yyzz;
	@ResInject(id = R.array.array_jsztfj, type = ResType.StringArray)
	private String[] array_jsztfj;
	@ViewInject(R.id.rg_MSLevel)
	RadioGroup rg_MSLevel;
	@ViewInject(R.id.et_MSSumScore)
	EditText et_MSSumScore;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private int score = 0;
	private List<Integer> maps;
	private static final int WRITE_RESULT = 2;
	private String writePath = "";
	private String path = "";//数据库中存的签字
	private List<String> list = new ArrayList<String>();//存历史的签字图片地址

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
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
		maps = new ArrayList<Integer>();
		Tools.addRadioButtonV(mContext, array_rzgn, rg_CognitiveFunction);
		Tools.addRadioButtonV(mContext, array_gjxw, rg_AggressiveBehavior);
		Tools.addRadioButtonV(mContext, array_yyzz, rg_DepressiveSymptoms);
		Tools.addRadioButtonV(mContext, array_jsztfj, rg_MSLevel);
		for (int i = 0; i < 3; i++) {
			maps.add(i, 0);
		}
		et_MSSumScore.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				score = Integer.parseInt(et_MSSumScore.getText().toString());
				int n = 0;
				if (score == 0) {
					n = 0;
				} else if (score == 1) {
					n = 1;
				} else if (2 <= score && score <= 3) {
					n = 2;
				} else {
					n = 3;
				}
				rg_MSLevel.check(n);
			}
		});
		et_MSSumScore.setText(score + "");
		rg_CognitiveFunction.setOnCheckedChangeListener(this);
		rg_AggressiveBehavior.setOnCheckedChangeListener(this);
		rg_DepressiveSymptoms.setOnCheckedChangeListener(this);
		
		tv_second_title.setOnLongClickListener(this);
		tv_third_title0.setOnLongClickListener(this);
		tv_third_title1.setOnLongClickListener(this);
		tv_third_title2.setOnLongClickListener(this);
		tv_third_title3.setOnLongClickListener(this);
		tv_third_title4.setOnLongClickListener(this);
		tv_fourth_title0.setOnLongClickListener(this);
		tv_fourth_title1.setOnLongClickListener(this);
		tv_fourth_title2.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_MentalState bi = dbUtils.findFirst(Selector.from(AI_MentalState.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				rg_CognitiveFunction.check(Integer.parseInt(bi.getCognitiveFunctionExplain()));
				rg_AggressiveBehavior.check(Integer.parseInt(bi.getAggressiveBehaviorExplain()));
				rg_DepressiveSymptoms.check(Integer.parseInt(bi.getDepressiveSymptomsExplain()));
				if (bi.getCognitiveFunctionTestAnswer().contains("-")) {
					String[] array_t = bi.getCognitiveFunctionTestAnswer().split("-");
					if (array_t.length > 2) {
						et_CognitiveFunction0.setText(array_t[0]);
						et_CognitiveFunction1.setText(array_t[1]);
						et_CognitiveFunction2.setText(array_t[2]);
					}
					else if(array_t.length > 1)
					{
						et_CognitiveFunction0.setText(array_t[0]);
						et_CognitiveFunction1.setText(array_t[1]);
					}
					else if(array_t.length > 0)
					{
						et_CognitiveFunction0.setText(array_t[0]);
					}
				}
				
				File path = SDPath.getSDPath(this);
				String name = reportId.toString()+ "0z.png";
				list.add(path.getPath() + "/" + name);
				File file = new File(path.getPath() + "/" + name);
				if (file.exists()) {
					Bitmap bit = BitmapFactory.decodeFile(file.getPath()).copy(Bitmap.Config.ARGB_8888, true);
					huazhong_imageView.setImageBitmap(bit);
				}
			}
		} catch (DbException e) {
			
			e.printStackTrace();
		}
		dbUtils.close();
	}

	@OnClick({ R.id.bt_save, R.id.bt_back,R.id.huazhong_imageView })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:
			DbUtils dbUtils = DbUtils.create(this);
			AI_MentalState bi = null;
			bi = new AI_MentalState();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setCognitiveFunctionTestAnswer(et_CognitiveFunction0.getText().toString() + "-" + et_CognitiveFunction1.getText().toString() + "-" + et_CognitiveFunction2.getText().toString());
			bi.setCognitiveFunctionExplain(rg_CognitiveFunction.getCheckedRadioButtonId() + "");
			bi.setAggressiveBehaviorExplain(rg_AggressiveBehavior.getCheckedRadioButtonId() + "");
			bi.setDepressiveSymptomsExplain(rg_DepressiveSymptoms.getCheckedRadioButtonId() + "");
			bi.setMSLevel(rg_MSLevel.getCheckedRadioButtonId() + "");
			bi.setMSSumScore(et_MSSumScore.getText().toString());

			try {
				if (dbUtils.findFirst(Selector.from(AI_MentalState.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_MentalState.class).where("ReportId", "=", reportId));
					bi.setCognitiveFunctionTestAnswer(et_CognitiveFunction0.getText().toString() + "-" + et_CognitiveFunction1.getText().toString() + "-" + et_CognitiveFunction2.getText().toString());
					bi.setCognitiveFunctionExplain(rg_CognitiveFunction.getCheckedRadioButtonId() + "");
					bi.setAggressiveBehaviorExplain(rg_AggressiveBehavior.getCheckedRadioButtonId() + "");
					bi.setDepressiveSymptomsExplain(rg_DepressiveSymptoms.getCheckedRadioButtonId() + "");
					bi.setMSLevel(rg_MSLevel.getCheckedRadioButtonId() + "");
					bi.setMSSumScore(et_MSSumScore.getText().toString());
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
		    case R.id.huazhong_imageView://点击画钟
			Intent intent_write = new Intent(this,
					WritePadActivity.class);
			File path = SDPath.getSDPath(this);
			if (!path.exists()) {
				path.mkdirs();
			}
			String name = reportId.toString()+"0z.png";
			writePath = path.getPath() + "/" + name;
			Log.v("TAG", "FirstFragment writePath:" + writePath);
			intent_write.putExtra("writePath", writePath);
			startActivityForResult(intent_write, WRITE_RESULT);
			
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case WRITE_RESULT:
			File file = new File(writePath);
			if (file.exists()) {
				Bitmap bm = BitmapFactory.decodeFile(file.getPath()).copy(Bitmap.Config.ARGB_8888, true);
				Log.v("TAG", "bm : " + bm.getByteCount());
				huazhong_imageView.setImageBitmap(bm);
				list.add(writePath);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		
		switch (arg0.getId()) {
		case R.id.rg_CognitiveFunction:
			maps.set(0, checkedId);
			break;
		case R.id.rg_AggressiveBehavior:
			maps.set(1, checkedId);
			break;
		case R.id.rg_DepressiveSymptoms:
			maps.set(2, checkedId);
			break;

		default:
			break;
		}
		score = 0;
		for (int k = 0; k < 3; k++) {
			score += maps.get(k);
		}
		et_MSSumScore.setText(score + "");
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(mContext, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_second_title:
			intent.putExtra("guid", "ai_jszt");
			break;
		case R.id.tv_fourth_title0:
			intent.putExtra("guid", "ai_hzcy");
			break;
		case R.id.tv_fourth_title1:
			intent.putExtra("guid", "ai_hzcy");
			break;
		case R.id.tv_fourth_title2:
			intent.putExtra("guid", "ai_hycy");
			break;
		case R.id.tv_third_title0:
			intent.putExtra("guid", "ai_rzgn");
			break;
		case R.id.tv_third_title1:
			intent.putExtra("guid", "ai_gjxw");
			break;
		case R.id.tv_third_title2:
			intent.putExtra("guid", "ai_yyzz");
			break;
		case R.id.tv_third_title3:
			intent.putExtra("guid", "ai_jsztzf");
			break;
		case R.id.tv_third_title4:
			intent.putExtra("guid", "ai_jsztfj");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
}
