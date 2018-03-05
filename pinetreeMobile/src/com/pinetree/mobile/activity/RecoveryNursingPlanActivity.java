package com.pinetree.mobile.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.RequestLine;

import com.baidu.platform.comapi.map.C;
import com.example.question.adapter.MyHealthAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.annotation.Check;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.ChooseAssessmentReportBase;
import com.pinetree.mobile.bean.CurrQuestionBase;
import com.pinetree.mobile.bean.RecoveryNursingPlan;
import com.pinetree.mobile.bean.RegainHistoryRecords;
import com.pinetree.mobile.bean.RegainPublicize;
import com.pinetree.mobile.bean.RegainQuestion;
import com.pinetree.mobile.bean.RegainTarget;
import com.pinetree.mobile.bean.RegainTargetSub;
import com.pinetree.mobile.bean.RehabilitationMeasures;
import com.pinetree.mobile.bean.SignResult;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.SexUtil;
import com.pinetree.mobile.utils.ToastUtils;

import android.R.bool;
import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 新增康复护理计划类(或历史护理计划内容展示)
 */
public class RecoveryNursingPlanActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.back_nursingPlan_imageView)
	private ImageView backNursingPlanImageView;
	/**
	 * 客户姓名
	 */
	@ViewInject(R.id.customerName_textView)
	private TextView customerNameTextView;
	/**
	 *客户性别
	 */
	@ViewInject(R.id.customerSex_textView)
	private TextView customerSexTextView;
	/**
	 *客户年龄
	 */
	@ViewInject(R.id.customerAge_textView)
	private TextView customerAgTxtView;
	/**
	 * 病史记录 LinearLayout
	 */
	@ViewInject(R.id.historyRecord_linearLayout)
	private LinearLayout historyRecordLinearLayout;
	/**
	 * 查体 EditText
	 */
	@ViewInject(R.id.check_editText)
	private EditText checkEditText;
	/**
	 * ADL评分 EditText
	 */
	@ViewInject(R.id.ADL_editText)
	private EditText aDLEditText;
	/**
	 * MMSE评分 EditText
	 */
	@ViewInject(R.id.MMSE_editText)
	private EditText mMSEEditText;
	/**
	 * 目前用药 EditText
	 */
	@ViewInject(R.id.nowMedicine_editTet)
	private EditText nowMedicineEditText;
	/**
	 * 现存问题
	 */
	@ViewInject(R.id.nowQuestion_linearLayout)
	private LinearLayout nowQuestionLinearLayout;
	/**
	 * 拟解决问题-短期
	 */
	@ViewInject(R.id.shortProblem_linearLayout)
	private LinearLayout shortProblemLinearLayout;
	/**
	 * 拟解决问题-中期
	 */
	@ViewInject(R.id.middleProblem_linearLayout)
	private LinearLayout middleProblemLinearLayout;
	/**
	 * 拟解决问题-长期
	 */
	@ViewInject(R.id.longProblem_linearLayout)
	private LinearLayout longProblemLinearLayout;
	/**
	 * 康复目标-短期
	 */
	@ViewInject(R.id.shortGoal_linearLayout)
	private LinearLayout shortGoalLinearLayout;
	/**
	 * 康复目标-中期
	 */
	@ViewInject(R.id.middleGoal_linearLayout)
	private LinearLayout middleGoalLinearLayout;
	/**
	 * 康复目标-长期
	 */
	@ViewInject(R.id.longGoal_linearLayout)
	private LinearLayout longGoalLinearLayout;
	/**
	 * 康复措施
	 */
	@ViewInject(R.id.healthMeasures_linearLayout)
	private LinearLayout healthMeasuresLinearLayout;
	/**
	 * 健康宣教-新增TextView
	 */
	@ViewInject(R.id.addHealth_textView)
	private TextView addHealthTextView;
	/**
	 * 健康宣教 LinearLayout
	 */
	@ViewInject(R.id.health_linearLayout)
	private LinearLayout healthLinearLayout;
	/**
	 * 注意事项
	 */
	@ViewInject(R.id.notes_editText)
	private EditText notesEditText;
	/**
	 * 提交
	 */
	@ViewInject(R.id.submit_button)
	private Button submitButton;
	/**
	 * 保存
	 */
	@ViewInject(R.id.save_button)
	private Button saveButton;
	@ViewInject(R.id.temporarySave_button)
	private Button temporarySaveButton;
	/**
	 * 病史记录-内容TextView
	 */
	@ViewInject(R.id.historyRecord_con)
	private TextView historyRecordConTextView;
	/**
	 * 现存问题-内容TextView
	 */
	@ViewInject(R.id.nowQuestion_con)
	private TextView nowQuestionConTextView;
	/**
	 * 拟解决问题短期-内容TextView
	 */
	@ViewInject(R.id.shortProblem_con)
	private TextView shortProblemConTextView;
	/**
	 * 拟解决问题中期-内容TextView
	 */
	@ViewInject(R.id.middleProblem_con)
	private TextView middleProblemConTextView;
	/**
	 * 拟解决问题长期-内容TextView
	 */
	@ViewInject(R.id.longProblem_con)
	private TextView longProblemConTextView;
	/**
	 * 康复目标短期-内容TextView
	 */
	@ViewInject(R.id.shortGoal_con)
	private TextView shortGoalConTextView;
	/**
	 * 康复目标中期-内容TextView
	 */
	@ViewInject(R.id.middleGoal_con)
	private TextView middleGoalConTextView;
	/**
	 * 康复目标长期-内容TextView
	 */
	@ViewInject(R.id.longGoal_con)
	private TextView longGoalConTextView;
	
	/**
	 * 康复措施-内容TextView
	 */
	@ViewInject(R.id.healthMeasures_con)
	private TextView healthMeasuresConTextView;
	/**
	 * 健康宣教-内容TextView
	 */
	@ViewInject(R.id.health_con)
	private TextView healthConTextView;
	/**
	 * 病史记录
	 */
	private List<RegainHistoryRecords> regainHistoryRecordsList = new ArrayList<RegainHistoryRecords>();
	/**
	 * 现存问题
	 */
	private List<RegainQuestion> list1 = new ArrayList<RegainQuestion>();
	/**
	 * 拟解决问题
	 */
	private List<RegainQuestion> solveQuestionList = new ArrayList<RegainQuestion>();
	/**
	 * 康复目标
	 */
	private List<RegainTarget> regainTargetList = new ArrayList<RegainTarget>();
	/**
	 * 康复措施
	 */
	private List<RehabilitationMeasures> regainTargetSubList = new ArrayList<RehabilitationMeasures>();
	/**
	 * 健康宣教
	 */
	private List<RehabilitationMeasures> regainPublicizeList = new ArrayList<RehabilitationMeasures>();
	private Map<Integer, Integer> healthMeasureTextViewMap = new HashMap<Integer,Integer>();//根据拟解决问题中每一行删除的id（Value值），存对应的问题的所有康复措施的id（key值）
	private Map<Integer, Integer> healthGoalTextViewMap= new HashMap<Integer, Integer>();//康复目标中的每条数据选择康复措施的id(key值),以及对应的进入拟解决问题TextView的id（Value值）
	private Map<Integer, Integer> problemTextViewMap = new HashMap<Integer, Integer>();//进入拟解决问题TextView的id（Value值）和拟解决问题布局中删除TextView的id（key值）
	private List<String> sumList = new ArrayList<String>();
	private List<Integer> checkBoxList = new ArrayList<Integer>();//选择大小标题中的checkbox的id值
	private List<RehabilitationMeasures> contentList = new ArrayList<RehabilitationMeasures>();//康复措施大小标题中选中的内容
	private List<RehabilitationMeasures> contentList1 = new ArrayList<RehabilitationMeasures>();//康复措施
	private List<RehabilitationMeasures> healthContentList = new ArrayList<RehabilitationMeasures>();//健康宣教大小标题中选中的内容
	private List<RehabilitationMeasures> contentList2;//健康宣教
	private List<RegainHistoryRecords> historyList = new ArrayList<RegainHistoryRecords>();//病史记录数据
	private String delete = "删除";
	private String choose = "选择康复措施";
	private String term = "";//期限
	private String decideTerm = "";
	private String notice = "";//注意事项
	private String checkboy = "";//查体
	private String aDLStr = "";//ADL评分
	private String mMSEStr = "";//MMSE评分
	private String nowMedicine = "";//目前用药
	private String state="2";//0为暂存，1为提交,2为保存
	private int saveState = -1;//判断保存是否成功   -1为失败     1为成功
	
	private RecoveryNursingPlan recoveryNursingPlan;
	private Dialog dialog;
	private String employeeId = "";
	private String employeeName = "";
	
	private static final int REFRESH = 0;
	private static final int MEASURES = 1;
	private static final int SAVE = 2;//保存数据
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH:
				nowQuestionLinearLayout.clearAnimation();
				nowQuestionLinearLayout.invalidate();
				nowQuestionLinearLayout.requestLayout();
				break;
			case MEASURES://康复措施适配
				
				break;
			case SAVE:
				saveToDB();
				if (saveState == 1) {
					ToastUtils.showToast(RecoveryNursingPlanActivity.this, "保存成功");
				}else {
					ToastUtils.showToast(RecoveryNursingPlanActivity.this, "保存失败");
				}
				break;
			default:
				break;
			}
		};
	};
	
	private ViewGroup.LayoutParams lp_ViewGroup = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recovery_nursing_plan);
	
		Bundle bundle = getIntent().getExtras();
		recoveryNursingPlan = (RecoveryNursingPlan) bundle.getSerializable("RecoveryNursingPlan");		
		employeeId = bundle.getString("employeeId");
		employeeName = bundle.getString("employeeName");
		
		dialog = new Dialog(RecoveryNursingPlanActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		ViewUtils.inject(this);
		initView();
		initData(recoveryNursingPlan);
		
		addHealthTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
	}

	
	private void initData(RecoveryNursingPlan recoveryNursingPlan2) {
		
		if (recoveryNursingPlan2 != null) {
			if ("1".equals(recoveryNursingPlan2.getComitStatus()) || "0".equals(recoveryNursingPlan2.getComitStatus())) {
				saveButton.setVisibility(View.GONE);
				temporarySaveButton.setVisibility(View.GONE);
				submitButton.setVisibility(View.GONE);
			}else {
				saveButton.setVisibility(View.VISIBLE);
				temporarySaveButton.setVisibility(View.VISIBLE);
				submitButton.setVisibility(View.VISIBLE);
			}
				
			customerNameTextView.setText(recoveryNursingPlan2.getCustName());
			customerSexTextView.setText(SexUtil.getSex(recoveryNursingPlan2.getSex()));
			customerAgTxtView.setText(recoveryNursingPlan2.getAge());
			checkEditText.setText(recoveryNursingPlan2.getCheckbody());
			aDLEditText.setText(recoveryNursingPlan2.getAdlscore());
			mMSEEditText.setText(recoveryNursingPlan2.getMmsescore());
			nowMedicineEditText.setText(recoveryNursingPlan2.getDruguse());
			notesEditText.setText(recoveryNursingPlan2.getNotice());
			
			if (null != recoveryNursingPlan2.getRegainHistoryRecordsList()) {
				regainHistoryRecordsList = recoveryNursingPlan2.getRegainHistoryRecordsList();
			}
			if (null != regainHistoryRecordsList) {
				if (regainHistoryRecordsList.size() > 0) {
					historyRecordConTextView.setVisibility(View.GONE);
				}
				for (int i = 0; i < regainHistoryRecordsList.size(); i++) {
					String content = regainHistoryRecordsList.get(i).getDescription();
					createHistoryLinearLayout(i,content);
				}
			}
			
			if (null != recoveryNursingPlan2.getCurrQuestionList()) {
				list1 = recoveryNursingPlan2.getCurrQuestionList();
			}	
			if (null != list1) {
				for (int i = 0; i < list1.size(); i++) {
					list1.get(i).setNum(i+"");
					createNowProblemLinearLayout(i);
				}
			}
			
			if (null != recoveryNursingPlan2.getSolveQuestionList()) {
				solveQuestionList = recoveryNursingPlan2.getSolveQuestionList();
			}	
			if (null != solveQuestionList) {
				for (int j = 0; j < solveQuestionList.size(); j++) {
					String term = solveQuestionList.get(j).getTerm();
					int num = Integer.parseInt(solveQuestionList.get(j).getNum());
					String content = solveQuestionList.get(j).getQuestion();
					TextView tv = (TextView) findViewById(num);
					tv.setVisibility(View.INVISIBLE);
					if ("0".equals(term)) {
						shortProblemConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("1",shortProblemLinearLayout,num,"0",content);
					}else if ("1".equals(term)) {
						middleProblemConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("1",middleProblemLinearLayout,num,"1",content);
					}else if ("2".equals(term)) {
						longProblemConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("1",longProblemLinearLayout,num,"2",content);
					}
				}
			}
			
			if (null != recoveryNursingPlan2.getRegainTargetList()) {
				regainTargetList = recoveryNursingPlan2.getRegainTargetList();
			}
			if (null != regainTargetList) {
				for (int i = 0; i < regainTargetList.size(); i++) {
					String term = regainTargetList.get(i).getTerm();
					int num = Integer.parseInt(regainTargetList.get(i).getNum());
					String content = regainTargetList.get(i).getContent();
					if ("0".equals(term)) {
						shortGoalConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("2",shortGoalLinearLayout,num,"0",content);
					}else if ("1".equals(term)) {
						middleGoalConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("2",middleGoalLinearLayout,num,"1",content);
					}else if ("2".equals(term)) {
						longGoalConTextView.setVisibility(View.GONE);
						createSolveProblemLinearLayout("2",longGoalLinearLayout,num,"2",content);
					}
				}
			}
			
			if (null != recoveryNursingPlan2.getRegainTargetSubList()) {
				regainTargetSubList = recoveryNursingPlan2.getRegainTargetSubList();
			}
			if (null != regainTargetSubList) {
				if (regainTargetSubList.size() > 0) {
					healthMeasuresConTextView.setVisibility(View.GONE);
				}
				for (int i = 0; i < regainTargetSubList.size(); i++) {

					int num = Integer.parseInt(regainTargetSubList.get(i).getNum());
					String content = regainTargetSubList.get(i).getContent();
					regainTargetSubList.get(i).setTvId(2*list1.size() + sumList.size()+2);
					createSolveProblemLinearLayout("3",healthMeasuresLinearLayout,num,"",content);
				}
			}
			
			if (null != recoveryNursingPlan2.getRegainPublicizeList()) {
				regainPublicizeList = recoveryNursingPlan2.getRegainPublicizeList();
			}	
			if (null != regainPublicizeList) {
				if (regainPublicizeList.size() > 0) {
					healthConTextView.setVisibility(View.GONE);
				}
				for (int i = 0; i < regainPublicizeList.size(); i++) {
					String content = regainPublicizeList.get(i).getContent();
					regainPublicizeList.get(i).setTvId(2*list1.size() + sumList.size()+2);
					createSolveProblemLinearLayout("4", healthLinearLayout, 0,"",content);
				}
			}
			
		}
	}


	private void initView() {
		backNursingPlanImageView.setOnClickListener(this);
		saveButton.setOnClickListener(this);
		submitButton.setOnClickListener(this);
		temporarySaveButton.setOnClickListener(this);
		addHealthTextView.setOnClickListener(this);
	}
	
	/**
	 * 病史记录布局
	 */
	private void createHistoryLinearLayout(int i,String content) {
		
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
		TextView  tv = new TextView(this);
		tv.setLayoutParams(lp_ViewGroup);
		tv.setText(content);
		tv.setGravity(Gravity.CENTER);	
		tv.setTextSize(18);
		historyRecordLinearLayout.addView(tv);
		
		if (i != regainHistoryRecordsList.size()-1 && regainHistoryRecordsList.size() > i) {
			LinearLayout lin4 = new LinearLayout(this);
			lin4.setLayoutParams(params1);
			lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));	
			historyRecordLinearLayout.addView(lin4);
		}
	}
	
	/**
	 * 现存问题布局    i
	 */
	private void createNowProblemLinearLayout(int i) {
		
		nowQuestionConTextView.setVisibility(View.GONE);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
		LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params2.weight = 1;
	    LayoutParams params3 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params3.weight = 5;
	    LayoutParams params4 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params4.weight = 2;
	    LayoutParams params5 = new LayoutParams(2, LayoutParams.MATCH_PARENT);
	    
	    LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
	    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup));
	    linearLayout.setGravity(Gravity.CENTER);
	    
	    TextView  tv = new TextView(this);
	    tv.setLayoutParams(params2);
	    tv.setText((i+1) +"");
	    tv.setGravity(Gravity.CENTER);	
	    tv.setTextSize(18);
	    linearLayout.addView(tv);
	    LinearLayout lin1 = new LinearLayout(this);				     
	    lin1.setLayoutParams(params5);
	    lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
        linearLayout.addView(lin1);
        
        int num = Integer.parseInt(list1.get(i).getNum());
        TextView  tv1 = new TextView(this);
	    tv1.setLayoutParams(params3);
	    tv1.setGravity(Gravity.CENTER);	
	    tv1.setText(list1.get(i).getQuestion());
	    tv1.setTextSize(18);
	    tv1.setId(list1.size()+num);
	    linearLayout.addView(tv1);
	    LinearLayout lin2 = new LinearLayout(this);				     
	    lin2.setLayoutParams(params5);
	    lin2.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
        linearLayout.addView(lin2);
        
        TextView  tv2 = new TextView(this);
	    tv2.setLayoutParams(params4);
	    tv2.setGravity(Gravity.CENTER);
	    tv2.setTextColor(Color.parseColor("#3333CC"));//#cc560f77
	    tv2.setText("进入拟解决问题");
	    tv2.setTextSize(18);
	    tv2.setId(num);
	    Log.v("TAG", "Id:" + i);
	    tv2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	    
	    linearLayout.addView(tv2);
	    setNowProblemClickListener(tv2);
	    
	    nowQuestionLinearLayout.addView(linearLayout);
	    if (i != list1.size()-1 && list1.size() > i) {
	    	LinearLayout lin4 = new LinearLayout(this);
		    lin4.setLayoutParams(params1);
		    lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));	
	    	nowQuestionLinearLayout.addView(lin4);
		}
	    
	}

	/**
	 * 设置点击进入拟解决问题textView的监听
	 */
	private void setNowProblemClickListener(final TextView tv) {
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				View  view=(LinearLayout) getLayoutInflater().inflate(R.layout.dialogview,null);         
				Builder builder = new AlertDialog.Builder(RecoveryNursingPlanActivity.this);
				builder.setTitle("请选择期限");
			  	builder.setView(view);
			   
			  	final TextView shortTextView = (TextView) view.findViewById(R.id.short_textView);
			  	final TextView middleTextView = (TextView) view.findViewById(R.id.middle_textView);
			  	final TextView longTextView = (TextView) view.findViewById(R.id.long_textView);
			  	shortTextView.setOnClickListener(new OnClickListener() {
				
					@Override
					public void onClick(View arg0) {
						
						shortTextView.setBackgroundColor(Color.parseColor("#cc560f77"));
						middleTextView.setBackgroundColor(Color.WHITE);
						longTextView.setBackgroundColor(Color.WHITE);
						term = "0";
					}
			  	});
			  	middleTextView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						
						term = "1";
						middleTextView.setBackgroundColor(Color.parseColor("#cc560f77"));
						shortTextView.setBackgroundColor(Color.WHITE);
						longTextView.setBackgroundColor(Color.WHITE);
					}
			  	});
			  	longTextView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						
						term = "2";
						longTextView.setBackgroundColor(Color.parseColor("#cc560f77"));
						shortTextView.setBackgroundColor(Color.WHITE);
						middleTextView.setBackgroundColor(Color.WHITE);
					}
			  	});
			   
			  	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						term = "";
					}
			  	});
			  	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						if (!"".equals(term)) {
							decideTerm = term;
							
							tv.setVisibility(View.INVISIBLE);
						    int i = tv.getId();
						    RegainQuestion regainQuestion = list1.get(i);
						    regainQuestion.setQtype("1");
						    regainQuestion.setPlanID(recoveryNursingPlan.getPlanID());
						    regainQuestion.setTerm(decideTerm);
							solveQuestionList.add(regainQuestion);
							RegainTarget regainTarget = new RegainTarget();
							regainTarget.setContent("");
							regainTarget.setPlanID(regainQuestion.getPlanID());
							regainTarget.setNum(regainQuestion.getNum());
							regainTarget.setTerm(decideTerm);
							regainTargetList.add(regainTarget);
						   
							if ("0".equals(decideTerm)) {
								shortProblemConTextView.setVisibility(View.GONE);
								shortGoalConTextView.setVisibility(View.GONE);
							    createSolveProblemLinearLayout("1",shortProblemLinearLayout,i,decideTerm,regainQuestion.getQuestion());
							    createSolveProblemLinearLayout("2",shortGoalLinearLayout,i,decideTerm,"");
							    term = "";
						    }else if ("1".equals(decideTerm)) {
						    	middleProblemConTextView.setVisibility(View.GONE);
								middleGoalConTextView.setVisibility(View.GONE);
						    	createSolveProblemLinearLayout("1",middleProblemLinearLayout,i,decideTerm,regainQuestion.getQuestion());
							    createSolveProblemLinearLayout("2",middleGoalLinearLayout,i,decideTerm,"");
							    term = "";
							}else if ("2".equals(decideTerm)) {
								longProblemConTextView.setVisibility(View.GONE);
								longGoalConTextView.setVisibility(View.GONE);
								createSolveProblemLinearLayout("1",longProblemLinearLayout,i,decideTerm,regainQuestion.getQuestion());
							    createSolveProblemLinearLayout("2",longGoalLinearLayout,i,decideTerm,"");
							    term = "";
							}
						    
						}
					}
			  	});
			  	builder.create().show();  
				
				
			}
		});
	}


	/**
	 * 添加拟解决问题布局或康复目标或康复措施 
	 */
	private void createSolveProblemLinearLayout(String str,LinearLayout AddLinearLayout,int i,String decideTerm,String content) {
		
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
		LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params2.weight = 5;
	    LayoutParams params3 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params3.weight = 2;
	    LayoutParams params5 = new LayoutParams(2, LayoutParams.MATCH_PARENT);
	    
	    int num = 2*list1.size() + sumList.size();
	    
	    LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
	    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup));
	    linearLayout.setGravity(Gravity.CENTER);
	    linearLayout.setId(num);
	    
	    EditText et = new EditText(this);
	    et.setLayoutParams(params2);
	    et.setText(content);
	    et.setId(num + 1);
	    et.setTextSize(18);
	    et.setGravity(Gravity.CENTER);
	    linearLayout.addView(et);
	    LinearLayout lin1 = new LinearLayout(this);				     
	    lin1.setLayoutParams(params5);
	    lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
        linearLayout.addView(lin1);
        
        TextView  tv1 = new TextView(this);
	    tv1.setLayoutParams(params3);
	    tv1.setGravity(Gravity.CENTER);	
	    tv1.setTextSize(18);
	    tv1.setTextColor(Color.parseColor("#3333CC"));//#cc560f77 
	    if ("2".equals(str)) {
	    	tv1.setText("选择康复措施");
		}else {
			tv1.setText("删除");
		}
	    tv1.setId(num+2);
	    tv1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	    linearLayout.addView(tv1);
        
	    LinearLayout lin4 = new LinearLayout(this);
	    lin4.setLayoutParams(params1);
	    lin4.setId(num+3);
	    lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));	
	    
	    
	    AddLinearLayout.addView(linearLayout);
	    AddLinearLayout.addView(lin4);
	    
	    
	    
	    if ("3".equals(str)) {
	    	healthMeasureTextViewMap.put(num+2, i);
		}else if ("1".equals(str)) {
			problemTextViewMap.put(num+2,i );
		}else if ("2".equals(str)) {
			healthGoalTextViewMap.put(num+2, i);
		}
	    
	    setSolveProblemClickListener(tv1,AddLinearLayout,linearLayout,lin4,num+2,str,decideTerm);
	    
	    sumList.add("0");
	    sumList.add("0");
        sumList.add("0");
        sumList.add("0");

	}


	/**
	 * 拟解决问题删除的监听
	 * tv:拟解决问题的删除按钮或康复目标的选择康复措施按钮或康复措施的删除按钮
	 * shortProblemLinearLayout：拟解决问题的LinearLayout或康复目标的LinearLayout或康复措施的LinearLayout
	 * linearLayout：拟解决问题的一行LinearLayout或康复目标的一行LinearLayout或康复措施的一行LinearLayout
	 * lin：动态添加一行分隔线
	 * id：被点击的tv的id
	 * str：1为当前是拟解决问题的删除按钮，2为当前是康复目标的选择康复措施按钮，3为当前是康复措施的删除按钮
	 * decideTerm：期限（短期，中期，长期）
	 */
	private void setSolveProblemClickListener(final TextView tv,final LinearLayout shortProblemLinearLayout,final LinearLayout  linearLayout,
			final LinearLayout lin,final int id,final String str,final String decideTerm) {
		
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (str.equals("1")) {//为拟解决问题删除时
					shortProblemLinearLayout.removeView(linearLayout);
					shortProblemLinearLayout.removeView(lin);
					
					int problemId = problemTextViewMap.get(id);
					if (null != solveQuestionList) {
						for (int i = 0; i < solveQuestionList.size(); i++) {
							if (problemId == Integer.parseInt(solveQuestionList.get(i).getNum())) {
								solveQuestionList.remove(i);
								if (solveQuestionList.size() > 0) {
									if (solveQuestionList.get(i).getTerm().equals("0")) {
										if (getContentList("0","1") == 0) {
											shortProblemConTextView.setVisibility(View.INVISIBLE);
										}
									}else if (solveQuestionList.get(i).getTerm().equals("1")) {
										if (getContentList("1","1") == 0) {
											middleProblemConTextView.setVisibility(View.INVISIBLE);
										}
									}else if (solveQuestionList.get(i).getTerm().equals("2")) {
										if (getContentList("2","1") == 0) {
											longProblemConTextView.setVisibility(View.INVISIBLE);
										}
									}
								}else {
									shortProblemConTextView.setVisibility(View.INVISIBLE);
									middleProblemConTextView.setVisibility(View.INVISIBLE);
									longProblemConTextView.setVisibility(View.INVISIBLE);
								}
								
							}
							break;
						}
					}
					
					List<Integer> healthGoalLinearLayoutIdList = getKey(healthGoalTextViewMap, problemId);//康复目标
					List<Integer> healthMeasuresTextViewIdList = getKey(healthMeasureTextViewMap, problemId);//康复措施
					if ("0".equals(decideTerm)) {
						for (int i = 0; i < healthGoalLinearLayoutIdList.size(); i++) {
							deleteHealth(healthGoalLinearLayoutIdList,shortGoalLinearLayout,i,problemId,"1");
							healthGoalTextViewMap.remove(healthGoalLinearLayoutIdList.get(i));
						}
						if (getContentList("0","2") == 0) {
							shortGoalConTextView.setVisibility(View.INVISIBLE);
						}
					}else if ("1".equals(decideTerm)) {
						for (int i = 0; i < healthGoalLinearLayoutIdList.size(); i++) {
							deleteHealth(healthGoalLinearLayoutIdList,middleGoalLinearLayout,i,problemId,"1");
							healthGoalTextViewMap.remove(healthGoalLinearLayoutIdList.get(i));
						}
						if (getContentList("1","2") == 0) {
							shortGoalConTextView.setVisibility(View.INVISIBLE);
						}
					}else if ("2".equals(decideTerm)) {
						for (int i = 0; i < healthGoalLinearLayoutIdList.size(); i++) {
							deleteHealth(healthGoalLinearLayoutIdList,longGoalLinearLayout,i,problemId,"1");
							healthGoalTextViewMap.remove(healthGoalLinearLayoutIdList.get(i));
						}
						if (getContentList("2","2") == 0) {
							shortGoalConTextView.setVisibility(View.INVISIBLE);
						}
					}
					
					for (int i = 0; i < healthMeasuresTextViewIdList.size(); i++) {
						deleteHealth(healthMeasuresTextViewIdList,healthMeasuresLinearLayout,i,problemId,"2");
						healthMeasureTextViewMap.remove(healthMeasuresTextViewIdList.get(i));
					}
					healthGoalLinearLayoutIdList.clear();
					healthMeasuresTextViewIdList.clear();
					if (healthMeasureTextViewMap.size() == 0) {
						healthMeasuresConTextView.setVisibility(View.INVISIBLE);
					}
					
					TextView tv1 = (TextView)findViewById(problemId);
					tv1.invalidate();
					tv1.setVisibility(View.VISIBLE);
					tv1.postInvalidate();
					problemTextViewMap.remove(id);
					
				}else if (str.equals("2")) {//为选择康复措施时
					final View  view=(LinearLayout) getLayoutInflater().inflate(R.layout.dialog_health_view,null);        
					Builder builder = new AlertDialog.Builder(RecoveryNursingPlanActivity.this);
					builder.setTitle("请选择康复措施");
					builder.setView(view);
					
					Spinner bigTitleSpin = (Spinner) view.findViewById(R.id.bigTitle_spinner);
					final Spinner smallTitleSpin = (Spinner) view.findViewById(R.id.smallTitle_spinner);
					final CheckBox checkAllCheckBox = (CheckBox) view.findViewById(R.id.checkAll_checkBox);
					final LinearLayout contentLinearLayout = (LinearLayout) view.findViewById(R.id.content_linearLayout);
					PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
					final List<RehabilitationMeasures> bigTitleList = pinetreeDB.getRehabilitationMeasuresOfBigTitle();//大标题list
					final List<RehabilitationMeasures> smallTitleList = new ArrayList<RehabilitationMeasures>();
					MyHealthAdapter adapter = new MyHealthAdapter(RecoveryNursingPlanActivity.this, bigTitleList);
					bigTitleSpin.setAdapter(adapter);
					
					bigTitleSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int position, long arg3) {
							
							smallTitleList.clear();
							contentList.clear();
							contentList1.clear();
							checkBoxList.clear();
							contentLinearLayout.removeAllViews();
							checkAllCheckBox.setChecked(false);
							if (!"请选择".equals(bigTitleList.get(position).getTitle())) {
								RehabilitationMeasures rehabilitationMeasures1 = new RehabilitationMeasures();
								rehabilitationMeasures1.setTitle("请选择");
								rehabilitationMeasures1.setType("1");
								smallTitleList.add(rehabilitationMeasures1);
								String id = bigTitleList.get(position).getId();
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
								if (!"".equals(id) && id != null) {
									smallTitleList.addAll(pinetreeDB.getRehabilitationMeasuresOfBigTitleoOfSmallTitle(id, "1"));
								}
								MyHealthAdapter adapter = new MyHealthAdapter(RecoveryNursingPlanActivity.this, smallTitleList);
								smallTitleSpin.setAdapter(adapter);
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							
							
						}
					});
					smallTitleSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int position, long arg3) {
							
							contentList.clear();
							checkBoxList.clear();
							contentList1.clear();
							contentLinearLayout.removeAllViews();
							checkAllCheckBox.setChecked(false);
							if (!"请选择".equals(smallTitleList.get(position).getTitle())) {
								String fatherid = smallTitleList.get(position).getId();
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
								if (!"".equals(fatherid) && fatherid != null) {
									contentList1 = pinetreeDB.getRehabilitationMeasuresOfBigTitleoOfSmallTitle(fatherid, "2");//内容list
								}
								for (int i = 0; i < contentList1.size(); i++) {
									createContentLayout(contentLinearLayout,contentList1.get(i).getContent(),checkAllCheckBox,i,"1",view);
								}
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							
							
						}
					});
					
					checkAllCheckBox.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							
							if (checkAllCheckBox.isChecked()) {
								contentList.clear();
								for (int i = 0; i < checkBoxList.size(); i++) {
									int id = checkBoxList.get(i);
									CheckBox box = (CheckBox) view.findViewById(id);
									box.setChecked(true);
									int tvId = checkBoxList.get(i) + 1;
									TextView tv = (TextView) view.findViewById(tvId);
								}
								if (contentList1 != null) {
									contentList.addAll(contentList1);
								}
							}else {
								for (int i = 0; i < checkBoxList.size(); i++) {
									int id = checkBoxList.get(i);
									CheckBox box = (CheckBox) view.findViewById(id);
									box.setChecked(false);
									contentList.clear();
								}
							}
						}
					});
					
					
					 builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							checkBoxList.clear();
							contentList.clear();
						}
						
					});
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							int problemId = healthGoalTextViewMap.get(id);
							for (int i = 0; i < contentList.size(); i++) {
								contentList.get(i).setTvId(2*list1.size()+sumList.size()+2);
								contentList.get(i).setNum(problemId+"");
								contentList.get(i).setPlanID(recoveryNursingPlan.getPlanID());
								createSolveProblemLinearLayout("3", healthMeasuresLinearLayout, problemId,"",contentList.get(i).getContent());
							}
							regainTargetSubList.addAll(contentList);
							if (regainTargetSubList.size() > 0) {
								healthMeasuresConTextView.setVisibility(View.GONE);
							}
							checkBoxList.clear();
							contentList.clear();
							tv.setTextColor(Color.parseColor("#cc560f77"));
						}
					})	;
					builder.create().show();  
					
				}else if (str.equals("3")) {//为康复措施删除
					shortProblemLinearLayout.removeView(linearLayout);
					shortProblemLinearLayout.removeView(lin);
					if (null != regainTargetSubList) {
						for (int i = 0; i < regainTargetSubList.size(); i++) {
							if (id == regainTargetSubList.get(i).getTvId()) {
								regainTargetSubList.remove(i);
							}
						}
					}
					healthMeasureTextViewMap.remove(id);
					healthMeasureTextViewMap.get(id);
					if (healthMeasureTextViewMap.size() == 0) {
						healthMeasuresConTextView.setVisibility(View.INVISIBLE);
					}
				}else if (str.equals("4")) {//为健康宣教删除
					shortProblemLinearLayout.removeView(linearLayout);
					shortProblemLinearLayout.removeView(lin);
					if (null != regainPublicizeList) {
						for (int i = 0; i < regainPublicizeList.size(); i++) {
							if (id == regainPublicizeList.get(i).getTvId()) {
								regainPublicizeList.remove(i);
							}
						}
					}
					if (regainPublicizeList.size() == 0) {
						healthConTextView.setVisibility(View.INVISIBLE);
					}
				}
			}

		});
	}
	
	/**
	 * 获取List里包含短期（或中期或长期）的个数
 	 */
	private int getContentList(String term,String type) {
		
		int size = 0;
		if ("1".equals(type)) {
			List<RegainQuestion> list11 = new ArrayList<RegainQuestion>();
			if (solveQuestionList.size() > 0) {
				for (int i = 0; i < solveQuestionList.size(); i++) {
					if (term.equals(solveQuestionList.get(i).getTerm())) {
						list11.add(solveQuestionList.get(i));
					}
				}
			}
			size = list11.size();
		}else if ("2".equals(type)) {
			List<RegainTarget> list11 = new ArrayList<RegainTarget>();
			if (regainTargetList.size() > 0) {
				for (int i = 0; i < regainTargetList.size(); i++) {
					if (term.equals(regainTargetList.get(i).getTerm())) {
						list11.add(regainTargetList.get(i));
					}
				}
			}
			size = list11.size();
		}
		
		return size;
		
	}

	/**
	 * 创建大小标题对应的新增的内容布局
	 */
	private void createContentLayout(LinearLayout contentLinearLayout,String content,CheckBox checkAllCheckBox,int i,String type,View view) {
		
		LayoutParams params1 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params1.weight = 1;
	    LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	    params2.weight = 8;
	    LayoutParams params3 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
	    LayoutParams params4 = new LayoutParams(1, LayoutParams.MATCH_PARENT);
	   
	    int num = 2*list1.size() + sumList.size();
	    
	    LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
	    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup));
	    linearLayout.setGravity(Gravity.CENTER);
	    
	    CheckBox box = new CheckBox(this);
	    box.setLayoutParams(params1);
	    box.setId(num);
	    checkBoxList.add(num);
	    box.setGravity(Gravity.CENTER);	
	    linearLayout.addView(box);
	    LinearLayout lin1 = new LinearLayout(this);				     
	    lin1.setLayoutParams(params4);
	    lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
        linearLayout.addView(lin1);
        
        TextView  tv1 = new TextView(this);
	    tv1.setLayoutParams(params2);
	    tv1.setText(content);
	    tv1.setId(num+1);
	    tv1.setGravity(Gravity.CENTER);	
	    linearLayout.addView(tv1);
        
	    LinearLayout lin4 = new LinearLayout(this);
	    lin4.setLayoutParams(params3);
	    lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));	
	    sumList.add("0");
	    sumList.add("0");
	    
	    contentLinearLayout.addView(linearLayout);
	    contentLinearLayout.addView(lin4);
	    
	    setCheckBoxLinstener(box,content,checkAllCheckBox,i,type,view);
	}
	
	/**
	 * 给大小标题的checkbox设置监听  
	 */
	private void setCheckBoxLinstener(final CheckBox box,final String content,final CheckBox checkAllCheckBox,final int i,final String type,final View view) {
		
		box.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (!"".equals(content)) {
					if (box.isChecked()) {
						if (getAllCheckBoxState(checkBoxList,view)) {
							checkAllCheckBox.setChecked(true);
						}else {
							checkAllCheckBox.setChecked(false);
						}
						if ("1".equals(type)) {
							contentList.add(contentList1.get(i));
						}else if ("2".equals(type)) {
							healthContentList.add(contentList1.get(i));
						}
						
					}else {
						checkAllCheckBox.setChecked(false);
						if ("1".equals(type)) {
							contentList.remove(i);
						}else if ("2".equals(type)) {
							healthContentList.remove(i);
						}
					}
				}
				
			}
		});
	}

	/**
	 * 判断checkbox是否全选中
	 */
	private boolean getAllCheckBoxState(List<Integer> checkBoxList,View view){
		boolean state = false;
		for (int i = 0; i < checkBoxList.size(); i++) {
			CheckBox check = (CheckBox) view.findViewById(checkBoxList.get(i));
			if (check.isChecked()) {
				state = true;
			}else {
				state = false;
				break;
			}
		}
		return state;
		
	}
	
	/**
	 * 删除拟解决问题对应康复目标某一行LinearLayout(或删除删除拟解决问题对应康复措施某一行LinearLayout)
	 */
	private void deleteHealth(List<Integer> healthList,LinearLayout healthLin,int i,int problemId,String type) {
		
		int healthGoalLinearLayoutId = healthList.get(i);
		int id = healthGoalLinearLayoutId-2;
		LinearLayout linear = (LinearLayout) findViewById(id);
		healthLin.removeView(linear);
		int linId = healthGoalLinearLayoutId+1;
		LinearLayout lin = (LinearLayout) findViewById(linId);
		healthLin.removeView(lin);
		String num = list1.get(problemId).getNum();
		if ("1".equals(type)) {
			for (int j = 0; j < regainTargetList.size(); j++) {
				if (num.equals(regainTargetList.get(j).getNum())) {
					regainTargetList.remove(j);
				}
			}
			
		}else if ("2".equals(type)) {
			for (int j = 0; j < regainTargetSubList.size(); j++) {
				if (num.equals(regainTargetSubList.get(j).getNum())) {
					regainTargetSubList.remove(j);
				}
			}
		}
	}
	
	/**
	 * 根据map中的values值获取其对应的key值
	 */
	public List<Integer> getKey(Map<Integer,Integer> map,int value) {
		  int o;
		  List<Integer> all=new ArrayList<Integer>();   //建一个数组用来存放符合条件的KEY值
		 Set set=map.entrySet();
		 Iterator it=set.iterator();
		 while(it.hasNext()) {
			 Map.Entry entry=(Map.Entry)it.next();
			 if(entry.getValue().equals(value)) {
				 o=(Integer) entry.getKey();
				 all.add(o);          
			 }
		  }
		  return all;
	}
	
	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.back_nursingPlan_imageView:
			finish();
			break;
		case R.id.save_button:
	
			getData();
			
			Message msg = Message.obtain();
			msg.what = SAVE;
			handler.sendMessage(msg);
			
			break;
		case R.id.temporarySave_button://暂存
			state = "0";
			getData();
			
			Gson gson = new Gson();
			String jsonData = gson.toJson(recoveryNursingPlan);
			if (NetUtil.checkNetWork(RecoveryNursingPlanActivity.this)) {
				dialog.show();
				RequestParams params = new RequestParams();
				params.addBodyParameter("regainPlanJson",jsonData);
				HttpUtil.post("saveRegainPlan.action", params,new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						SignResult signResult = GsonUtils.json2bean(responseInfo.result,
								SignResult.class);
						dialog.hide();
						if (!"".equals(signResult.getSuccess())&& Boolean.valueOf(signResult.getSuccess())) {
							saveToDB();
							ToastUtils.showToast(RecoveryNursingPlanActivity.this, "暂存数据成功");
							finish();
						}else {
							ToastUtils.showToast(RecoveryNursingPlanActivity.this, signResult.getMessage());
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						dialog.hide();
						ToastUtils.showToast(RecoveryNursingPlanActivity.this, "请求网络失败,请重试");
					}
				});
			} else {
				dialog.hide();
				ToastUtils.showToast(RecoveryNursingPlanActivity.this, "没有网络,请在有网时提交数据");
			 } 
			break;
		case R.id.submit_button:
			state = "1";
			getData();
			
			Gson gson1 = new Gson();
			String jsonData1 = gson1.toJson(recoveryNursingPlan);
			if (NetUtil.checkNetWork(RecoveryNursingPlanActivity.this)) {
				dialog.show();
				RequestParams params = new RequestParams();
				params.addBodyParameter("regainPlanJson",jsonData1);
				HttpUtil.post("saveRegainPlan.action", params,new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						SignResult signResult = GsonUtils.json2bean(responseInfo.result,
								SignResult.class);
						dialog.hide();
						if (!"".equals(signResult.getSuccess())&& Boolean.valueOf(signResult.getSuccess())) {
							PinetreeDB pin = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
							saveToDB();
							pin.updateRecoveryNursingPlan(recoveryNursingPlan.getPlanID());
							ToastUtils.showToast(RecoveryNursingPlanActivity.this, "提交数据成功");
							finish();
						}else {
							ToastUtils.showToast(RecoveryNursingPlanActivity.this, signResult.getMessage());
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						dialog.hide();
						ToastUtils.showToast(RecoveryNursingPlanActivity.this, "请求网络失败,请重试");
					}
				});
			} else {
				dialog.hide();
				ToastUtils.showToast(RecoveryNursingPlanActivity.this, "没有网络,请在有网时提交数据");
			} 
			break;
		case R.id.addHealth_textView://新增宣教
			final View  view=(LinearLayout) getLayoutInflater().inflate(R.layout.dialog_health_view,null);        
			Builder builder = new AlertDialog.Builder(RecoveryNursingPlanActivity.this);
			builder.setTitle("请选择健康宣教");
			builder.setView(view);
				
			Spinner bigTitleSpin = (Spinner) view.findViewById(R.id.bigTitle_spinner);
			final Spinner smallTitleSpin = (Spinner) view.findViewById(R.id.smallTitle_spinner);
			final CheckBox checkAllCheckBox = (CheckBox) view.findViewById(R.id.checkAll_checkBox);
			final LinearLayout contentLinearLayout = (LinearLayout) view.findViewById(R.id.content_linearLayout);
			
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
			final List<RehabilitationMeasures> bigTitleList = pinetreeDB.getHealthEducationsOfBigTitle();//大标题list
			final List<RehabilitationMeasures> smallTitleList = new ArrayList<RehabilitationMeasures>();
			
			MyHealthAdapter adapter = new MyHealthAdapter(RecoveryNursingPlanActivity.this, bigTitleList);
			bigTitleSpin.setAdapter(adapter);	
			
			bigTitleSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0,
							View arg1, int position, long arg3) {
					
					smallTitleList.clear();
					healthContentList.clear();
					checkBoxList.clear();
					contentLinearLayout.removeAllViews();
					checkAllCheckBox.setChecked(false);
					if (!"请选择".equals(bigTitleList.get(position).getTitle())) {
						RehabilitationMeasures rehabilitationMeasures1 = new RehabilitationMeasures();
						rehabilitationMeasures1.setTitle("请选择");
						rehabilitationMeasures1.setType("1");
						smallTitleList.add(rehabilitationMeasures1);
						String id = bigTitleList.get(position).getId();
						PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
						if (!"".equals(id) && id != null) {
							smallTitleList.addAll(pinetreeDB.getHealthEducationOfBigTitleoOfSmallTitle(id, "1"));
						}
						MyHealthAdapter adapter = new MyHealthAdapter(RecoveryNursingPlanActivity.this, smallTitleList);
						smallTitleSpin.setAdapter(adapter);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
						
						
				}
			});
			smallTitleSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0,
							View arg1, int position, long arg3) {
					
					healthContentList.clear();
					checkBoxList.clear();
					contentLinearLayout.removeAllViews();
					checkAllCheckBox.setChecked(false);
					if (!"请选择".equals(smallTitleList.get(position).getTitle())) {
						String fatherid = smallTitleList.get(position).getId();
						PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanActivity.this);
						if (!"".equals(fatherid) && fatherid != null) {
							contentList1 = pinetreeDB.getHealthEducationOfBigTitleoOfSmallTitle(fatherid, "2");//内容list
						}
						for (int i = 0; i < contentList1.size(); i++) {
							createContentLayout(contentLinearLayout,contentList1.get(i).getContent(),checkAllCheckBox,i,"2",view);
						}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
				
			checkAllCheckBox.setOnClickListener(new OnClickListener() {
					
				@Override
				public void onClick(View arg0) {
					
					if (checkAllCheckBox.isChecked()) {
						healthContentList.clear();
						for (int i = 0; i < checkBoxList.size(); i++) {
							int id = checkBoxList.get(i);
							CheckBox box = (CheckBox) view.findViewById(id);
							box.setChecked(true);
							int tvId = checkBoxList.get(i) + 1;
							TextView tv = (TextView) view.findViewById(tvId);
							if (contentList1 != null) {
								healthContentList.addAll(contentList1);
							}
						}
					}else {
						for (int i = 0; i < checkBoxList.size(); i++) {
							int id = checkBoxList.get(i);
							CheckBox box = (CheckBox) view.findViewById(id);
							box.setChecked(false);
							healthContentList.clear();
						}
					}
				}
			});
				
				
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
						
					checkBoxList.clear();
					healthContentList.clear();
				}
					
			});
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					for (int i = 0; i < healthContentList.size(); i++) {
						healthContentList.get(i).setTvId(2*list1.size()+sumList.size()+2);
						healthContentList.get(i).setPlanID(recoveryNursingPlan.getPlanID());
						createSolveProblemLinearLayout("4", healthLinearLayout, 0,"",healthContentList.get(i).getContent());
					}
					regainPublicizeList.addAll(healthContentList);
					if (regainPublicizeList.size() > 0) {
						healthConTextView.setVisibility(View.GONE);
					}
					checkBoxList.clear();
					healthContentList.clear();
				}
			})	;
			builder.create().show();  
			break;
		default:
			break;
		}
	}

	/**
	 * 把数据保存到数据库
	 */
	private void saveToDB() {
		
		PinetreeDB pine = PinetreeDB.getInstance(this);
		String planID = recoveryNursingPlan.getPlanID();
		pine.deleteRecoveryNursingPlan(planID);
		pine.deleteRegainQuestion(planID, "0");
		pine.deleteRegainQuestion(planID, "1");
		pine.deleteRegainTarget(planID);
		pine.deleteRegainTargetSub(planID);
		pine.deleteRegainPublicize(planID);
		int state1 = 1;
		int state2 = 1;
		int state3 = 1;
		int state4 = 1;
		int state5 = 1;
		int state6 = 1;
		int state7 = 1;
		
		state1 = pine.saveRecoveryNursingPlan(recoveryNursingPlan);
		
		if (null != regainHistoryRecordsList) {
			for (int i = 0; i < regainHistoryRecordsList.size(); i++) {
				int save = pine.saveHistoryRecords(regainHistoryRecordsList.get(i));
				if (save == -1) {
					state2 = -1;
				}
			}
		}
		
		if (null != list1) {
			for (int i = 0; i < list1.size(); i++) {
				list1.get(i).setPlanID(planID);
				list1.get(i).setQtype("0");
				int save = pine.saveRegainQuestion(list1.get(i));
				if (save == -1) {
					state3 = -1;
				}
			}
		}
		
		if (null != solveQuestionList) {
			for (int i = 0; i < solveQuestionList.size(); i++) {
				solveQuestionList.get(i).setQtype("1");
				int save = pine.saveRegainQuestion(solveQuestionList.get(i));
				if (save == -1) {
					state4 = -1;
				}
			}
		}
		
		if (null != regainTargetList) {
			for (int i = 0; i < regainTargetList.size(); i++) {
				int save = pine.saveRegainTarget(regainTargetList.get(i));
				if (save == -1) {
					state5 = -1;
				}
			}
		}
		
		if (null != regainTargetSubList) {
			for (int i = 0; i < regainTargetSubList.size(); i++) {
				int save = pine.saveRegainTargetSub(regainTargetSubList.get(i));
				if (save == -1) {
					state6 = -1;
				}
			}
		}
		
		if (null != regainPublicizeList) {
			for (int i = 0; i < regainPublicizeList.size(); i++) {
				int save = pine.saveRegainPublicize(regainPublicizeList.get(i));
				if (save == -1) {
					state7 = -1;
				}
			}
		}
		if (state1==1 && state2==1 && state3==1 && state4==1 && state5==1 && state6==1 && state7==1) {
			saveState = 1;
		}else {
			saveState = -1;
		}
		notice = "";//注意事项
		checkboy = "";//查体
		aDLStr = "";//ADL评分
		mMSEStr = "";//MMSE评分
		nowMedicine = "";//目前用药
		
	}


	/**
	 * 获取数据
	 */
	private void getData() {
		
		if (!"".equals(checkEditText.getText().toString()) && null != checkEditText.getText().toString()) {
			checkboy = checkEditText.getText().toString();
		}
		if (!"".equals(aDLEditText.getText().toString()) && null != aDLEditText.getText().toString()) {
			aDLStr = aDLEditText.getText().toString();
		}
		if (!"".equals(mMSEEditText.getText().toString()) && null != mMSEEditText.getText().toString()) {
			mMSEStr = mMSEEditText.getText().toString();
		}
		if (!"".equals(nowMedicineEditText.getText().toString()) && null != nowMedicineEditText.getText().toString()) {
			nowMedicine = nowMedicineEditText.getText().toString();
		}
		if (!"".equals(notesEditText.getText().toString()) && null != notesEditText.getText().toString()) {
			notice = notesEditText.getText().toString();
		}
		
		String planID = recoveryNursingPlan.getPlanID();
		recoveryNursingPlan.setId(planID);
		recoveryNursingPlan.setPlanID(planID);
		recoveryNursingPlan.setCreateUser(employeeId);
		recoveryNursingPlan.setCheckbody(checkboy);
		recoveryNursingPlan.setAdlscore(aDLStr);
		recoveryNursingPlan.setMmsescore(mMSEStr);
		recoveryNursingPlan.setDruguse(nowMedicine);
		recoveryNursingPlan.setNotice(notice);
		recoveryNursingPlan.setComitStatus(state);
		recoveryNursingPlan.setSubmitState(state);
		
		// 获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		recoveryNursingPlan.setCreateTime(createDate + "T" + createTime);
		
		if (null != regainHistoryRecordsList) {
			for (int i = 0; i < regainHistoryRecordsList.size(); i++) {
				regainHistoryRecordsList.get(i).setPlanID(planID);
			}
			recoveryNursingPlan.setRegainHistoryRecordsList(regainHistoryRecordsList);
		}
		
		if (null != list1) {
			for (int i = 0; i < list1.size(); i++) {
				list1.get(i).setPlanID(planID);
				list1.get(i).setQtype("0");
				list1.get(i).setSortIndex(i+"");
			}
			recoveryNursingPlan.setCurrQuestionList(list1);
		}
		
		
		if (null != solveQuestionList) {
			for (int i = 0; i < solveQuestionList.size(); i++) {
				int num = Integer.parseInt(solveQuestionList.get(i).getNum());
				List<Integer> problemList = getKey(problemTextViewMap, num);//拟解决问题
				if(problemList.size() > 0){
					EditText et = (EditText) findViewById(problemList.get(0)-1);
					solveQuestionList.get(i).setQuestion(et.getText().toString().trim());
					solveQuestionList.get(i).setSortIndex(i+"");
					solveQuestionList.get(i).setQtype("1");
					solveQuestionList.get(i).setPlanID(planID);
				}
			}
			recoveryNursingPlan.setSolveQuestionList(solveQuestionList);
		}
		
		if (null != regainTargetList) {
			for (int i = 0; i < regainTargetList.size(); i++) {
				int num = Integer.parseInt(regainTargetList.get(i).getNum());
				List<Integer> goalList = getKey(healthGoalTextViewMap, num);//目标
				for (int j = 0; j < goalList.size(); j++) {
					EditText et = (EditText) findViewById(goalList.get(j)-1);
					regainTargetList.get(i).setContent(et.getText().toString().trim());
					regainTargetList.get(i).setSortIndex(i+"");
					break;
				}
			}
			recoveryNursingPlan.setRegainTargetList(regainTargetList);
		}
		
		if (null != regainTargetSubList) {
			for (int i = 0; i < regainTargetSubList.size(); i++) {
					EditText et = (EditText) findViewById(regainTargetSubList.get(i).getTvId()-1);
					regainTargetSubList.get(i).setContent(et.getText().toString().trim());
					regainTargetSubList.get(i).setSortIndex(i+"");
			}
			recoveryNursingPlan.setRegainTargetSubList(regainTargetSubList);
		}
		
		if (null != regainPublicizeList) {
			for (int i = 0; i < regainPublicizeList.size(); i++) {
				EditText et = (EditText) findViewById(regainPublicizeList.get(i).getTvId()-1);
				regainPublicizeList.get(i).setContent(et.getText().toString().trim());
				regainPublicizeList.get(i).setSortIndex(i+"");
			}
			recoveryNursingPlan.setRegainPublicizeList(regainPublicizeList);
		}
	}
	

}
