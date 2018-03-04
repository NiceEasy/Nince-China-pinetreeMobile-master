package com.pinetree.mobile.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BasicInformation;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.PlanList;
import com.pinetree.mobile.bean.PlanSubList;
import com.pinetree.mobile.bean.ServerRecord;
import com.pinetree.mobile.bean.ServerRecordBase;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.SexUtil;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 * 服务记录 （服务记录详情页）
 * 
 */
public class EverydayRecordSheetActivity extends Activity implements
		OnClickListener {
	/**
	 * 客户姓名
	 */
	@ViewInject(R.id.custName_textView)
	private TextView custNameTextView;
	/**
	 * 客户的性别
	 */
	@ViewInject(R.id.custSex_textView)
	private TextView custSexTextView;
	/**
	 * 客户的年龄
	 */
	@ViewInject(R.id.custAge_textView)
	private TextView custAgeTextView;
	/**
	 * 高压
	 */
	@ViewInject(R.id.highpressure_editText)
	private EditText highpressureEditText;
	/**
	 * 低压
	 */
	@ViewInject(R.id.lowpressure_editText)
	private EditText lowpressureEditText;
	/**
	 * 脉搏
	 */
	@ViewInject(R.id.pulse_editText)
	private EditText pulseEditText;
	/**
	 * 呼吸
	 */
	@ViewInject(R.id.breath_editText)
	private EditText breathEditText;
	/**
	 * 血糖
	 */
	@ViewInject(R.id.bloodsugar_editText)
	private EditText bloodsugarEditText;
	/**
	 * 体温
	 */
	@ViewInject(R.id.temperature_editText)
	private EditText temperatureEditText;
	/**
	 * 其他
	 */
	@ViewInject(R.id.otherSub_editText)
	private EditText otherSubEditText;
	/**
	 * 健康宣教
	 */
	@ViewInject(R.id.content_textView)
	private TextView contentTextView;
	
	/**
	 * 时间区间
	 */
	@ViewInject(R.id.targetDate_textView)
	private TextView targetDateTextView;
	
	/**
	 * 顶部返回按钮图片
	 */
	@ViewInject(R.id.back_everydayRecordSheet_imageView)
	private ImageView backEverydayRecordSheetImageView;
	private ServerRecord serverRecord;//传递过来的数据对象
	
	/**
	 * 康复护理目标title
	 */
	@ViewInject(R.id.health_goal_title_linearLayout)
	private LinearLayout healthGoalTitleLinearLayout;
	/**
	 * 康复护理目标
	 */
	@ViewInject(R.id.health_goal_linearLayout)
	private LinearLayout healthGoalLinearLayout;
	/**
	 * 康复护理目标View
	 */
	@ViewInject(R.id.health_goal_view)
	private View healthGoalView;
	/**
	 * 康复护理措施title
	 */
	@ViewInject(R.id.health_measure_title_linearLayout)
	private LinearLayout healthMeasureTitleLinearLayout;
	/**
	 * 康复护理措施
	 */
	@ViewInject(R.id.health_measure_linearLayout)
	private LinearLayout healthMeasureLinearLayout;
	/**
	 * 康复护理措施View
	 */
	@ViewInject(R.id.health_measure_view)
	private View healthMeasureView;
	
	/**
	 *提交按钮
	 */
	@ViewInject(R.id.submit_serverRecord_button)
	private Button submitServerRecordButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.save_serverRecord_button)
	private Button saveServerRecordButton;
	
	private ViewGroup.LayoutParams lp_ViewGroup2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	private ViewGroup.LayoutParams lp_ViewGroup3 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	 
	private String custName = "";
	private String empName = "";
	private String age = "";
	private String bloodsugar = "";
	private String breath = "";
	private String content = "";
	private String createTime ="";
	private String highpressure = "";
	private String lowpressure = "";
	private String temperature = "";
	private String otherSub = "";
	private String planListStr = "";
	private String planSubListStr = "";
	private String pulse = "";
	private String sex = "";
	private String saveState = "";
	private String submitState = "";
	private String custID = "";
	private String scheID = "";
	private String timeEnd1 = "";
	private String timeStart1 = "";
	private String time = "";
	private String planID = "";
	
	private Dialog dialog;
	private String employeeId;
	private String employeeName;
	private String scheduleId;//日程id
	private String custID2;//传过来的客户id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		Bundle bundle = getIntent().getExtras();
		serverRecord = (ServerRecord) bundle
				.getSerializable("serverRecord");
		scheduleId = bundle.getString("scheduleId");
		custID2 = bundle.getString("custID");
		setContentView(R.layout.activity_everyday_record_sheet);
		ViewUtils.inject(this);
		
		SharedPreferences sp = getSharedPreferences("UserInfo",
				MODE_PRIVATE);
		employeeId = sp.getString("employeeId", "");
		employeeName = sp.getString("employeeName", "");
		
		dialog = new Dialog(EverydayRecordSheetActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		if ("0".equals(serverRecord.getSaveState()) && "0".equals(serverRecord.getSubmitState())) {
			saveServerRecordButton.setVisibility(View.GONE);
		}
		initData(serverRecord);
		
		backEverydayRecordSheetImageView.setOnClickListener(this);
		submitServerRecordButton.setOnClickListener(this);
		saveServerRecordButton.setOnClickListener(this);
		
	}

	private void initData(ServerRecord serverRecord2) {
		if (!"".equals(serverRecord2)&&null!=serverRecord2) {
			if (!"".equals(serverRecord2.getCustName())) {
				custNameTextView.setText(serverRecord2.getCustName());
			}
			if (!"".equals(SexUtil.getSex(serverRecord2.getSex()))) {
				custSexTextView.setText(SexUtil.getSex(serverRecord2.getSex()));
			}
			if (!"".equals(serverRecord2.getAge())) {
				custAgeTextView.setText(serverRecord2.getAge());
			}
			if (null != serverRecord2.getHighpressure()) {
				highpressureEditText.setText(serverRecord2.getHighpressure());
			}
			if (null != serverRecord2.getLowpressure()) {
				lowpressureEditText.setText(serverRecord2.getLowpressure());
			}
			if (null != serverRecord2.getPulse()) {
				pulseEditText.setText(serverRecord2.getPulse());
			}
			if (null != serverRecord2.getBreath()) {
				breathEditText.setText(serverRecord2.getBreath());
			}
			if (null != serverRecord2.getBloodsugar()) {
				bloodsugarEditText.setText(serverRecord2.getBloodsugar());
			}
			if (null != serverRecord2.getTemperature()) {
				temperatureEditText.setText(serverRecord2.getTemperature());
			}
			if (null != serverRecord2.getOtherSub()) {
				otherSubEditText.setText(serverRecord2.getOtherSub());
			}		
			if (null != serverRecord2.getStartDate() && !"".equals(serverRecord2.getStartDate()) && null != serverRecord2.getEndDate()) {
				if (!serverRecord2.getStartDate().equals(serverRecord2.getEndDate())  ) {
					if (null != serverRecord2.getTargetDate() && !"".equals(serverRecord2.getTargetDate())) {
						targetDateTextView.setText(serverRecord2.getTargetDate());
					}				
				}else {
					String startTime = serverRecord2.getStartDate();
					String endTime = serverRecord2.getEndDate();
					String timeStart = startTime.substring(0, 4)+ "年"+ startTime.substring(6, 7)+ "月" + startTime.substring(8, 10)+ "日" ;
					int timeEndMouth = Integer.parseInt(startTime.substring(6, 7)) + 3;
					String timeEndMouth1 = "";
					int timeEndYear = 0 ;
					if (timeEndMouth >=13) {
						timeEndMouth = timeEndMouth-12;
						timeEndMouth1 = "0" + timeEndMouth;
						timeEndYear = Integer.parseInt(startTime.substring(0, 4)) + 1;
					}else {
						timeEndMouth1 = "0" + timeEndMouth;
						timeEndYear = Integer.parseInt(startTime.substring(0, 4));
					}
					String timeEnd = timeEndYear+ "年"+ timeEndMouth+ "月" + startTime.substring(8, 10)+ "日";
					timeEnd1 = timeEndYear + "-" + timeEndMouth1 + endTime.substring(7, 19);
					time = timeStart + "~" + timeEnd;
					targetDateTextView.setText(time);
				}
			}
			
			
			if (null != serverRecord2.getContent()) {
				contentTextView.setText(serverRecord2.getContent());
			}
			
			
			if (serverRecord2.getSubmitState().equals("0")) {
				submitServerRecordButton.setVisibility(View.GONE);
			}
			
			createPlanListObject(serverRecord2);
			createPlanSubListObject(serverRecord2);
			
		}
	}
	/**
	 * 康复护理目标 赋值
	 */
	private void createPlanListObject(ServerRecord serverRecord){ 
		if (!"".equals(serverRecord) && null!=serverRecord) {
			for (int i = 0; i < serverRecord.getPlanList().size(); i++) {
				if (!"".equals(serverRecord.getPlanList()) && null!=serverRecord.getPlanList()) {
					 EditText et = new EditText(this);
			    	 et.setText(serverRecord.getPlanList().get(i).getContent().toString().trim());
			    	 et.setId(i);
			    	 et.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup2));
			    	 healthGoalLinearLayout.addView(et);	
				}
			}
			if (serverRecord.getPlanList().size() == 0) {
				healthGoalTitleLinearLayout.setVisibility(View.GONE);
				healthGoalView.setVisibility(View.GONE);
			}
		}				
	}
	/**
	 * 措施
	 */
	private void createPlanSubListObject(ServerRecord serverRecord){
		 LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
	     LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params2.weight = 1;
	     LayoutParams params3 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params3.weight = 2;
	     LayoutParams params4 = new LayoutParams(2, LayoutParams.MATCH_PARENT);
	    
	     int planListNumber = serverRecord.getPlanList().size();
	     int planSubListNumber = serverRecord.getPlanSubList().size();
	     if (!"".equals(serverRecord) && null!=serverRecord) {
			for (int i = 0; i < serverRecord.getPlanSubList().size(); i++) {
				if (!"".equals(serverRecord.getPlanSubList()) && null!=serverRecord.getPlanSubList()) {
					 LinearLayout linearLayout1 = new LinearLayout(this);
				     linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
				     linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup3));
				     linearLayout1.setGravity(Gravity.CENTER);
				     
				     TextView  tv = new TextView(this);
				     tv.setText(serverRecord.getPlanSubList().get(i).getBigTitle().toString());
				     tv.setLayoutParams(params2);
				     tv.setGravity(Gravity.CENTER);	
				     tv.setId(planListNumber +i);
				     linearLayout1.addView(tv);
				     LinearLayout lin1 = new LinearLayout(this);				     
				     lin1.setLayoutParams(params4);
				     lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
			         linearLayout1.addView(lin1);
			         			         
			         EditText et = new EditText(this);
			         et.setLayoutParams(params3);
			    	 et.setText(serverRecord.getPlanSubList().get(i).getContent().toString());	 
			    	 et.setGravity(Gravity.CENTER);
			    	 et.setId(planListNumber + planSubListNumber + i);
			    	 linearLayout1.addView(et);
			    	 LinearLayout lin2 = new LinearLayout(this);
				     lin2.setLayoutParams(params4);
				     lin2.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
			         linearLayout1.addView(lin2);
			         
			         
			         EditText et1 = new EditText(this);
			    	 et1.setText(serverRecord.getPlanSubList().get(i).getPlanNum().toString());			    
			    	 et1.setLayoutParams(params2);
			    	 et1.setGravity(Gravity.CENTER);	
			    	 et1.setId(planListNumber + 2*planSubListNumber + i);
			    	 linearLayout1.addView(et1);
			    	 LinearLayout lin3 = new LinearLayout(this);
				     lin3.setLayoutParams(params4);
				     lin3.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
			         linearLayout1.addView(lin3);
			         
			         EditText et2 = new EditText(this);
			    	 et2.setText(serverRecord.getPlanSubList().get(i).getRealNum().toString());	
			    	 et2.setLayoutParams(params2);
			    	 et2.setGravity(Gravity.CENTER);
			    	 et2.setId(planListNumber + 3*planSubListNumber + i);
			    	 linearLayout1.addView(et2);
			    	 
			    	 LinearLayout lin4 = new LinearLayout(this);
				     lin4.setLayoutParams(params1);
				     lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
			         
			         healthMeasureLinearLayout.addView(linearLayout1);   
			         healthMeasureLinearLayout.addView(lin4);								     
				}			
			}
			if (serverRecord.getPlanSubList().size() == 0) {
				healthMeasureTitleLinearLayout.setVisibility(View.GONE);
				healthMeasureView.setVisibility(View.GONE);
			}
		}
	};

	/**
	 * 获取元素值
	 */
	private void getElementData() {
		//客户姓名
		if (!"".equals(custNameTextView.getText().toString().trim())) {
			custName = custNameTextView.getText().toString().trim();
		}
		//客户性别
		if (!"".equals(custSexTextView.getText().toString().trim())) {
			if ("男".equals(custSexTextView.getText().toString().trim())) {
				sex = "1";
			}else if ("女".equals(custSexTextView.getText().toString().trim())) {
				sex = "0";
			}
		}
		//客户年龄
		if (!"".equals(custAgeTextView.getText().toString().trim())) {
			age = custAgeTextView.getText().toString().trim();
		}
		//高压
		if (!"".equals(highpressureEditText.getText().toString().trim())) {
			highpressure = highpressureEditText.getText().toString().trim();
		}
		//低压
		if (!"".equals(lowpressureEditText.getText().toString().trim())) {
			lowpressure = lowpressureEditText.getText().toString().trim();
		}
		//脉搏
		if (!"".equals(pulseEditText.getText().toString().trim())) {
			pulse = pulseEditText.getText().toString().trim();
		}
		//呼吸
		if (!"".equals(breathEditText.getText().toString().trim())) {
			breath = breathEditText.getText().toString().trim();
		}
		//血糖
		if (!"".equals(bloodsugarEditText.getText().toString().trim())) {
			bloodsugar = bloodsugarEditText.getText().toString().trim();
		}
		//体温
		if (!"".equals(temperatureEditText.getText().toString().trim())) {
			temperature = temperatureEditText.getText().toString().trim();
		}
		//其他
		if (!"".equals(otherSubEditText.getText().toString().trim())) {
			otherSub = otherSubEditText.getText().toString().trim();
		}
		//健康宣教
		if (!"".equals(contentTextView.getText().toString().trim())) {
			content = contentTextView.getText().toString().trim();
		}
		if (!"".equals(targetDateTextView.getText().toString().trim())) {
			time = targetDateTextView.getText().toString().trim();
		}
		timeStart1 = serverRecord.getStartDate();
		timeEnd1 = timeEnd1;
		//服务人员姓名
		empName = employeeName;
		scheID = scheduleId;
		custID = serverRecord.getCustID();
		planID = serverRecord.getId();
		Log.v("TAG", "get  empName:  " + empName);
		if (!"".equals(serverRecord) && null != serverRecord) {
			int planListNumber = 0;
			int planSubListNumber= 0 ;
			if (!"".equals(serverRecord.getPlanList()) && null != serverRecord.getPlanList()) {
				planListNumber = serverRecord.getPlanList().size();
			}
			if (!"".equals(serverRecord.getPlanSubList()) && null != serverRecord.getPlanSubList()) {
				planSubListNumber = serverRecord.getPlanSubList().size();
			}		    
			//康复护理目标
			if (planListNumber > 0) {
				for (int i = 0; i < planListNumber; i++) {
					EditText et = (EditText) findViewById(i);
					if (null != et.getText().toString().trim()) {
						planListStr += et.getText().toString().trim() + "|";
					}
				}
			}
			if (!"".equals(planListStr)) {
				planListStr = planListStr.substring(0, (planListStr.length()-1));
			}
			//措施
			if (planSubListNumber > 0) {
				for (int i = 0; i < planSubListNumber; i++) {
					TextView tv = (TextView) findViewById(planListNumber + i);
					EditText et = (EditText) findViewById(planListNumber + planSubListNumber + i);
					EditText et1 = (EditText) findViewById(planListNumber + (2*planSubListNumber) + i);
					EditText et2 = (EditText) findViewById(planListNumber + (3*planSubListNumber) + i);
					String bigTitle = "";
					String contentStr = "";
					String planNum = "";
					String realNum = "";
					if (null != tv.getText().toString().trim() && !"".equals(tv.getText().toString().trim())) {
						bigTitle = tv.getText().toString().trim();
					}
					if (null != et.getText().toString().trim() && !"".equals(et.getText().toString().trim())) {
						contentStr = et.getText().toString().trim();
					}
					if (null != et1.getText().toString().trim() && !"".equals(et1.getText().toString().trim())) {
						planNum = et1.getText().toString().trim();
					}else   {
						planNum = 0 + "";
					}
					if (null != et2.getText().toString().trim() && !"".equals(et2.getText().toString().trim())) {
						realNum = et2.getText().toString().trim();
					}else  {
						realNum = 0 + "";
					}
					planSubListStr += bigTitle + "^" + contentStr + "^" + planNum + "^" + realNum + "|";
				}
			}		
			if (!"".equals(planSubListStr)) {
				planSubListStr = planSubListStr.substring(0, (planSubListStr.length()-1));
			}
		}
		
	}

	
	/**
	 * 设置数据
	 */
	private ServerRecord setData() {
		serverRecord.setCustName(custName);
		serverRecord.setSex(sex);
		serverRecord.setAge(age);
		serverRecord.setBloodsugar(bloodsugar);
		serverRecord.setBreath(breath);
		serverRecord.setHighpressure(highpressure);
		serverRecord.setLowpressure(lowpressure);
		serverRecord.setTemperature(temperature);
		serverRecord.setPulse(pulse);
		serverRecord.setContent(content);
		serverRecord.setOtherSub(otherSub);
		serverRecord.setCustID(custID);//设置传过来的客户id
		serverRecord.setStartDate(timeStart1);
		serverRecord.setEndDate(timeEnd1);		
		serverRecord.setTargetDate(time);
		serverRecord.setPlanID(planID);
		serverRecord.setScheduleId(scheduleId);
		System.out.println("setDATE================"+highpressure+";;;;"+"lowpressure");
		ArrayList<PlanList> planList = new ArrayList<PlanList>();
		if (!"".equals(planListStr)) {
			if (planListStr.contains("|")) {
				String[] planListArray =planListStr.split("\\|");
				for (String planListStr1 : planListArray) {
					PlanList planListObj = new PlanList();
					planListObj.setContent(planListStr1);
					planList.add(planListObj);
				}
			}else {
				PlanList planListObj = new PlanList();
				planListObj.setContent(planListStr);
				planList.add(planListObj);
			}
		}
		serverRecord.setPlanList(planList);
		
		ArrayList<PlanSubList> planSubList = new ArrayList<PlanSubList>();
		if (!"".equals(planSubListStr)) {
			if (planSubListStr.contains("|")) {
				String[] planSubListArray =planSubListStr.split("\\|");
				for (int i = 0; i < planSubListArray.length; i++) {
					String[] planSubListArray1 = planSubListArray[i].split("\\^");
					PlanSubList planSubListObj = new PlanSubList();
					planSubListObj.setBigTitle(planSubListArray1[0]);
					planSubListObj.setContent(planSubListArray1[1]);
					planSubListObj.setPlanNum(planSubListArray1[2]);
					planSubListObj.setRealNum(planSubListArray1[3]);
					planSubList.add(planSubListObj);
				}
			}else {
				String[] planSubListArray2 =planSubListStr.split("\\^");
				PlanSubList planSubListObj = new PlanSubList();
				planSubListObj.setBigTitle(planSubListArray2[0]);
				planSubListObj.setContent(planSubListArray2[1]);
				planSubListObj.setPlanNum(planSubListArray2[2]);
				planSubListObj.setRealNum(planSubListArray2[3]);
				planSubList.add(planSubListObj);
			}
		}
		serverRecord.setPlanSubList(planSubList);
		
		return serverRecord;
	}
	
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_everydayRecordSheet_imageView:
			finish();
			break;
		case R.id.save_serverRecord_button://保存
			dialog.show();
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayRecordSheetActivity.this);
			pinetreeDB.deleteServerRecordByScheID(scheduleId);
			getElementData();
			ServerRecord serverRecord = setData();
			serverRecord.setSubmitState("1");
			serverRecord.setSaveState("0");
			serverRecord.setEmpName(employeeName);
			boolean b = pinetreeDB.saveServerRecord(serverRecord);
			if (b) {
				dialog.hide();
				ToastUtils.showToast(EverydayRecordSheetActivity.this, "保存成功");
				new Handler().postDelayed(
						new Runnable() {
							@Override
							public void run() {
								finish();
							}
						}, 1000);
			} else {
				dialog.hide();
				clearVariable();
				ToastUtils.showToast(EverydayRecordSheetActivity.this, "保存失败，请重试");
			}		
			break;
		default:
			break;
		}

	}
	
	private void clearVariable() {
		custName = "";
		empName = "";
		age = "";
		bloodsugar = "";
		breath = "";
		content = "";
		highpressure = "";
		lowpressure = "";
		temperature = "";
		otherSub = "";
		planListStr = "";
		planSubListStr = "";
		pulse = "";
		sex = "";
		time = "";
		timeStart1 = "";
		timeEnd1 = "";
	}
}
