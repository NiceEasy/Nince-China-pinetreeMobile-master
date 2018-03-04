package com.example.question.tab;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.MyBaseInterface;
import com.pinetree.mobile.activity.WritePadActivity;
import com.pinetree.mobile.bean.AI_AbilityAssessmentConclusion;
import com.pinetree.mobile.bean.AI_ActivityofDailyLliving;
import com.pinetree.mobile.bean.AI_AssessmentBasisInformation;
import com.pinetree.mobile.bean.AI_AssessmentSupplementaryInformation;
import com.pinetree.mobile.bean.AI_MainAssessorInformation;
import com.pinetree.mobile.bean.AI_MentalState;
import com.pinetree.mobile.bean.AI_SensoryPerceptionAndCommunication;
import com.pinetree.mobile.bean.AI_SocialParticipation;
import com.pinetree.mobile.bean.AI_SupplementaryAssessmentInformation;
import com.pinetree.mobile.bean.BI_DemandService;
import com.pinetree.mobile.bean.BI_DiagnosedDisease;
import com.pinetree.mobile.bean.BI_EarlyResults;
import com.pinetree.mobile.bean.BI_ExternalProfessionalService;
import com.pinetree.mobile.bean.BI_GuardianAndEmergencyContactInformation;
import com.pinetree.mobile.bean.BI_HomePrimaryCargiversInformation;
import com.pinetree.mobile.bean.BI_IDInformation;
import com.pinetree.mobile.bean.BI_ManagementInformation;
import com.pinetree.mobile.bean.BI_NowLivingCondition;
import com.pinetree.mobile.bean.BI_PersonalInformation;
import com.pinetree.mobile.bean.BI_SupplementaryInformation;
import com.pinetree.mobile.bean.SF_COPM;
import com.pinetree.mobile.bean.SF_MFA;
import com.pinetree.mobile.bean.SF_MentalStateAssessment;
import com.pinetree.mobile.bean.SF_PersonalSupplement;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReportAll;
import com.pinetree.mobile.utils.MyDatePickerDialog;
import com.pinetree.mobile.utils.MyTimePickerDialog;
import com.pinetree.mobile.utils.Request;
import com.pinetree.mobile.utils.SDPath;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;
import com.shizhefei.fragment.LazyFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @类描述	第一个tab页面内容
 * @author wcm 
 * @createDate 2015-10-13 上午9:22:07
 */
@ContentView(R.layout.fragment_first)
public class FirstFragment extends LazyFragment implements MyBaseInterface, OnTouchListener, OnClickListener {
	private ProgressBar progressBar;
	private TextView textView;
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	@ViewInject(R.id.etReportId)
	private EditText etReportId;
	@ViewInject(R.id.customerSign_imageView)
	private ImageView customerSignImageView;//客户签字
	@ViewInject(R.id.etReportDate)
	private EditText etReportDate;
	@ViewInject(R.id.et_beginTime)
	private EditText et_beginTime;
	@ViewInject(R.id.et_endTime)
	private EditText et_endTime;
	@ViewInject(R.id.cbPromiseDesc1)
	private CheckBox cbPromiseDesc1;
	@ViewInject(R.id.cbPromiseDesc2)
	private CheckBox cbPromiseDesc2;
	@ViewInject(R.id.cbPromiseDesc3)
	private CheckBox cbPromiseDesc3;
	@ViewInject(R.id.sp_relation)
	private Spinner sp_relation;
	@ResInject(id = R.array.array_relation, type = ResType.StringArray)
	private String[] array_relation;
	private ArrayAdapter<?> adapter_relation;
	@ViewInject(R.id.bt_back)
	private Button btBack;
	@ViewInject(R.id.bt_save)
	private Button btSave;
	@ViewInject(R.id.bt_save_upload)
	private Button bt_save_upload;
	private Context mContext;
	private String reportId;
	private String scheduleId = "";
	private String employeeID = "";
	private String employeeName = "";
	private String prodID = "";
	private String projectId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	Calendar c = Calendar.getInstance();
	Request request;
	
	private static final int WRITE_RESULT = 2;
	private String writePath = "";
	private String path = "";//数据库中存的签字
	private List<String> list = new ArrayList<String>();//存历史的签字图片地址
	private String huazhongPath = "";
	

	public Handler Myhandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			Tools.stopProgressDialog(mContext);
			if (msg.arg1 == 100) {
				String str = msg.getData().getString("result");
				int flag = msg.getData().getInt("flag");
				if (flag == 1) {
					if (str.equals("true")) {
						saveorUpdate("1", "1");
						Toast.makeText(getApplicationContext(), "上传并保存成功！", Toast.LENGTH_LONG).show();
					} else {
						saveorUpdate("0", "1");
						Toast.makeText(getApplicationContext(), str, 1000).show();
					}
				} else {
					saveorUpdate("0", "1");
					Toast.makeText(getApplicationContext(), str, 1000).show();
				}
			}
		}
	};

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_first);
		ViewUtils.inject(this, getContentView());
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		reportId = sharedPreferencesUtil.get("reportId");
		scheduleId = sharedPreferencesUtil.get("scheduleId");
		Log.v("TAG", "FirstFragment  scheduleId:  " + scheduleId);
		employeeID = sharedPreferencesUtil.get("employeeID");
		employeeName = sharedPreferencesUtil.get("employeeName");
		prodID = sharedPreferencesUtil.get("prodID");
		Log.v("TAG", "FirstFragment  prodID:  " + prodID);
		projectId = sharedPreferencesUtil.get("projectId");
		Log.v("TAG", "FirstFragment  projectId:  " + projectId);
		request = new Request(Myhandler);
		initView();
		initData();
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	@Override
	public void initView() {
		

		etReportId = (EditText) findViewById(R.id.etReportId);
		etReportDate = (EditText) findViewById(R.id.etReportDate);
		et_beginTime = (EditText) findViewById(R.id.et_beginTime);
		et_endTime = (EditText) findViewById(R.id.et_endTime);
		customerSignImageView = (ImageView) findViewById(R.id.customerSign_imageView);
		cbPromiseDesc1 = (CheckBox) findViewById(R.id.cbPromiseDesc1);
		cbPromiseDesc2 = (CheckBox) findViewById(R.id.cbPromiseDesc2);
		cbPromiseDesc3 = (CheckBox) findViewById(R.id.cbPromiseDesc3);
		sp_relation = (Spinner) findViewById(R.id.sp_relation);
		btBack = (Button) findViewById(R.id.bt_back);
		btSave = (Button) findViewById(R.id.bt_save);
		bt_save_upload = (Button) findViewById(R.id.bt_save_upload);
		btSave.setOnClickListener(this);
		bt_save_upload.setOnClickListener(this);
		customerSignImageView.setOnClickListener(this);
		etReportId.setText(reportId);
		etReportDate.setText(Tools.getCurrDate() + "");
		et_beginTime.setText(Tools.getCurrTime() + "");
		et_endTime.setText(Tools.getCurrTime() + "");
		etReportId.setEnabled(false);
		cbPromiseDesc1.setChecked(true);
		cbPromiseDesc2.setChecked(true);
		cbPromiseDesc3.setChecked(true);
		etReportDate.setOnTouchListener(this);
		et_beginTime.setOnTouchListener(this);
		et_endTime.setOnTouchListener(this);
		adapter_relation = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, array_relation);
		adapter_relation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_relation.setAdapter(adapter_relation);

	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId));
			if (yangLaoServiceAssessmentReport != null && yangLaoServiceAssessmentReport.getID() > 0) {
				
				if (!"".equals(yangLaoServiceAssessmentReport.getApplicantStr()) && yangLaoServiceAssessmentReport.getApplicantStr() != null) {
					path = yangLaoServiceAssessmentReport.getApplicantStr();
					list.add(path);
					writePath = path;
					File file = new File(path);
					if (file.exists()) {
						Bitmap bit = BitmapFactory.decodeFile(file.getPath()).copy(Bitmap.Config.ARGB_8888, true);
						customerSignImageView.setImageBitmap(bit);
					}
				}
				
				etReportId.setText(yangLaoServiceAssessmentReport.getReportId());
				etReportDate.setText(yangLaoServiceAssessmentReport.getReportDate());
				String beginTime = yangLaoServiceAssessmentReport.getBeginTime();
				String endTime = yangLaoServiceAssessmentReport.getEndTime();
				et_beginTime.setText(beginTime.substring(beginTime.lastIndexOf("T") + 1, beginTime.lastIndexOf(":")));
				et_endTime.setText(endTime.substring(beginTime.lastIndexOf("T") + 1, endTime.lastIndexOf(":")));
				cbPromiseDesc1.setChecked(Boolean.parseBoolean(yangLaoServiceAssessmentReport.getPromiseDesc1()));
				cbPromiseDesc2.setChecked(Boolean.parseBoolean(yangLaoServiceAssessmentReport.getPromiseDesc2()));
				cbPromiseDesc3.setChecked(Boolean.parseBoolean(yangLaoServiceAssessmentReport.getPromiseDesc3()));
				if (yangLaoServiceAssessmentReport.getRelation() != null && !yangLaoServiceAssessmentReport.getRelation().equals("")) {
					sp_relation.setSelection(Integer.parseInt(yangLaoServiceAssessmentReport.getRelation()), true);
				}
				if(yangLaoServiceAssessmentReport.getWhetherNew()!=null&&yangLaoServiceAssessmentReport.getWhetherNew().equals("1")){
					etReportDate.setEnabled(false);
					et_beginTime.setEnabled(false);
					et_endTime.setEnabled(false);
				}
			}
		} catch (DbException e) {
			
			e.printStackTrace();
		}
		dbUtils.close();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case WRITE_RESULT:
			File file = new File(writePath);
			if (file.exists()) {
				Bitmap bm = BitmapFactory.decodeFile(file.getPath()).copy(Bitmap.Config.ARGB_8888, true);
				Log.v("TAG", "bm : " + bm.getByteCount());
				customerSignImageView.setImageBitmap(bm);
				list.add(writePath);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bt_save:
			if (!"".equals(writePath)) {
				File file = new File(writePath);
				if (file.exists()) {
					saveorUpdate("0", "0");
				}else {
					Toast.makeText(mContext, "签字不能为空，请先签字！", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(mContext, "签字不能为空，请先签字！！", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.bt_back:
			getActivity().finish();
			break;
		case R.id.customerSign_imageView://点击签字
			Intent intent_write = new Intent(getActivity(),
					WritePadActivity.class);
			File path = SDPath.getSDPath(getActivity());
			if (!path.exists()) {
				path.mkdirs();
			}
			String name = reportId.toString()+ list.size() + ".png";
			writePath = path.getPath() + "/" + name;
			Log.v("TAG", "FirstFragment writePath:" + writePath);
			intent_write.putExtra("writePath", writePath);
			startActivityForResult(intent_write, WRITE_RESULT);
			
			break;
		case R.id.bt_save_upload:
			DbUtils dbUtils2 = DbUtils.create(mContext);
			YangLaoServiceAssessmentReport bi = getData();
			if (bi.getApplicantStr() != null && !"".equals(bi.getApplicantStr())) {
				
				if(bi.getIsUpload().equals("1")){
					Toast.makeText(mContext, "已上报的数据", Toast.LENGTH_SHORT).show();
				}else{
				Tools.startProgressDialog(mContext, "正在上传，请稍后");
				YangLaoServiceAssessmentReportAll yangLao = new YangLaoServiceAssessmentReportAll();
				
					BI_IDInformation bi0;
					BI_PersonalInformation bi1;
					/* 基本信息@身份信息 */
					BI_IDInformation BI_IDInformation;
					/* 基本信息@个人信息 */
					BI_PersonalInformation BI_PersonalInformation;
					/* 基本信息@监护人/紧急联系人信息 */
					BI_GuardianAndEmergencyContactInformation BI_GuardianAndEmergencyContactInformation;
					/* 基本信息@目前生活状况 */
					BI_NowLivingCondition BI_NowLivingCondition;
					/* 基本信息@已确诊的疾病 */
					BI_DiagnosedDisease BI_DiagnosedDisease;
					/* 基本信息@家庭主要照护者信息 */
					BI_HomePrimaryCargiversInformation BI_HomePrimaryCargiversInformation;
					/* 基本信息@外部提供的专业看护服务（最近7 天） */
					BI_ExternalProfessionalService BI_ExternalProfessionalService;
					/* 基本信息@信息采集初步结果 */
					BI_EarlyResults BI_EarlyResults;
					/* 基本信息@居家照护管理员信息 */
					BI_ManagementInformation BI_ManagementInformation;
					/* 基本信息@需求服务 */
					BI_DemandService BI_DemandService;
					/* 基本信息@补充信息 */
					BI_SupplementaryInformation BI_SupplementaryInformation;
		
					/* 评估信息@评估基本信息 */
					AI_AssessmentBasisInformation AI_AssessmentBasisInformation;
					/* 评估信息@日常生活能力 */
					AI_ActivityofDailyLliving AI_ActivityofDailyLliving;
					/* 评估信息@精神状态 */
					AI_MentalState AI_MentalState;
					/* 评估信息@感知觉与沟通 */
					AI_SensoryPerceptionAndCommunication AI_SensoryPerceptionAndCommunication;
					/* 评估信息@社会参与 */
					AI_SocialParticipation AI_SocialParticipation;
					/* 评估信息@补充评估信息 */
					AI_SupplementaryAssessmentInformation AI_SupplementaryAssessmentInformation;
					/* 评估信息@能力评估结论 */
					AI_AbilityAssessmentConclusion AI_AbilityAssessmentConclusion;
					/* 评估信息@主责评估员信息 */
					AI_MainAssessorInformation AI_MainAssessorInformation;
					/* 评估信息@评估补充说明 */
					AI_AssessmentSupplementaryInformation AI_AssessmentSupplementaryInformation;
		
					/* 补充模块@个人补充信息 */
					SF_PersonalSupplement SF_PersonalSupplement;
					/* 补充模块@智力状态检查评估 */
					SF_MentalStateAssessment SF_MentalStateAssessment;
					
					/*补充模块@运动功能评定*/
					SF_MFA SF_MFA;
					/*补充模块@COPM*/
					SF_COPM SF_COPM;
		
					try {
						yangLao.setID(Integer.parseInt(etReportId.getText().toString()));
						yangLao.setReportId(bi.getReportId());
						yangLao.setReportDate(bi.getReportDate());
						yangLao.setBeginTime(bi.getBeginTime());
						yangLao.setEndTime(bi.getEndTime());
						yangLao.setPromiseDesc1(bi.getPromiseDesc1());
						yangLao.setPromiseDesc2(bi.getPromiseDesc2());
						yangLao.setPromiseDesc3(bi.getPromiseDesc3());
						yangLao.setRelation(bi.getRelation());
						yangLao.setCustID(bi.getCustID());
						
						yangLao.setProjectId(bi.getProjectId());
						yangLao.setScheduleId(bi.getScheduleId());
						yangLao.setLat(bi.getLat());
						yangLao.setLng(bi.getLng());
						yangLao.setEmployeeID(bi.getEmployeeID());
						yangLao.setEmployeeName(bi.getEmployeeName());
						yangLao.setEmpNum(bi.getEmpNum());
						yangLao.setProdID(bi.getProdID());
						yangLao.setWhetherNew(bi.getWhetherNew());
		
						bi0 = dbUtils2.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
						if (bi0 != null && bi0.getID() > 0) {
							yangLao.setBI_IDInformation(bi0);
						}
						bi1 = dbUtils2.findFirst(Selector.from(BI_PersonalInformation.class).where("ReportId", "=", reportId));
						if (bi1 != null && bi1.getID() > 0) {
							yangLao.setBI_PersonalInformation(bi1);
						}
		
						BI_GuardianAndEmergencyContactInformation = dbUtils2.findFirst(Selector.from(BI_GuardianAndEmergencyContactInformation.class).where("ReportId", "=", reportId));
						if (BI_GuardianAndEmergencyContactInformation != null && BI_GuardianAndEmergencyContactInformation.getID() > 0) {
							yangLao.setBI_GuardianAndEmergencyContactInformation(BI_GuardianAndEmergencyContactInformation);
						}
						BI_NowLivingCondition = dbUtils2.findFirst(Selector.from(BI_NowLivingCondition.class).where("ReportId", "=", reportId));
						if (BI_NowLivingCondition != null && BI_NowLivingCondition.getID() > 0) {
							yangLao.setBI_NowLivingCondition(BI_NowLivingCondition);
						}
						BI_DiagnosedDisease = dbUtils2.findFirst(Selector.from(BI_DiagnosedDisease.class).where("ReportId", "=", reportId));
						if (BI_DiagnosedDisease != null && BI_DiagnosedDisease.getID() > 0) {
							yangLao.setBI_DiagnosedDisease(BI_DiagnosedDisease);
						}
						BI_HomePrimaryCargiversInformation = dbUtils2.findFirst(Selector.from(BI_HomePrimaryCargiversInformation.class).where("ReportId", "=", reportId));
						if (BI_HomePrimaryCargiversInformation != null && BI_HomePrimaryCargiversInformation.getID() > 0) {
							yangLao.setBI_HomePrimaryCargiversInformation(BI_HomePrimaryCargiversInformation);
						}
						BI_ExternalProfessionalService = dbUtils2.findFirst(Selector.from(BI_ExternalProfessionalService.class).where("ReportId", "=", reportId));
						if (BI_ExternalProfessionalService != null && BI_ExternalProfessionalService.getID() > 0) {
							yangLao.setBI_ExternalProfessionalService(BI_ExternalProfessionalService);
						}
						BI_EarlyResults = dbUtils2.findFirst(Selector.from(BI_EarlyResults.class).where("ReportId", "=", reportId));
						if (BI_EarlyResults != null && BI_EarlyResults.getID() > 0) {
							yangLao.setBI_EarlyResults(BI_EarlyResults);
						}
						BI_ManagementInformation = dbUtils2.findFirst(Selector.from(BI_ManagementInformation.class).where("ReportId", "=", reportId));
						if (BI_ManagementInformation != null && BI_ManagementInformation.getID() > 0) {
							yangLao.setBI_ManagementInformation(BI_ManagementInformation);
						}
						BI_DemandService = dbUtils2.findFirst(Selector.from(BI_DemandService.class).where("ReportId", "=", reportId));
						if (BI_DemandService != null && BI_DemandService.getID() > 0) {
							yangLao.setBI_DemandService(BI_DemandService);
						}
						BI_SupplementaryInformation = dbUtils2.findFirst(Selector.from(BI_SupplementaryInformation.class).where("ReportId", "=", reportId));
						if (BI_SupplementaryInformation != null && BI_SupplementaryInformation.getID() > 0) {
							yangLao.setBI_SupplementaryInformation(BI_SupplementaryInformation);
						}
		
						AI_AssessmentBasisInformation = dbUtils2.findFirst(Selector.from(AI_AssessmentBasisInformation.class).where("ReportId", "=", reportId));
						if (AI_AssessmentBasisInformation != null && AI_AssessmentBasisInformation.getID() > 0) {
							yangLao.setAI_AssessmentBasisInformation(AI_AssessmentBasisInformation);
						}
						AI_ActivityofDailyLliving = dbUtils2.findFirst(Selector.from(AI_ActivityofDailyLliving.class).where("ReportId", "=", reportId));
						if (AI_ActivityofDailyLliving != null && AI_ActivityofDailyLliving.getID() > 0) {
							yangLao.setAI_ActivityofDailyLliving(AI_ActivityofDailyLliving);
						}
						AI_MentalState = dbUtils2.findFirst(Selector.from(AI_MentalState.class).where("ReportId", "=", reportId));
						if (AI_MentalState != null && AI_MentalState.getID() > 0) {
							yangLao.setAI_MentalState(AI_MentalState);
						}
						AI_SensoryPerceptionAndCommunication = dbUtils2.findFirst(Selector.from(AI_SensoryPerceptionAndCommunication.class).where("ReportId", "=", reportId));
						if (AI_SensoryPerceptionAndCommunication != null && AI_SensoryPerceptionAndCommunication.getID() > 0) {
							yangLao.setAI_SensoryPerceptionAndCommunication(AI_SensoryPerceptionAndCommunication);
						}
						AI_SocialParticipation = dbUtils2.findFirst(Selector.from(AI_SocialParticipation.class).where("ReportId", "=", reportId));
						if (AI_SocialParticipation != null && AI_SocialParticipation.getID() > 0) {
							yangLao.setAI_SocialParticipation(AI_SocialParticipation);
						}
						AI_SupplementaryAssessmentInformation = dbUtils2.findFirst(Selector.from(AI_SupplementaryAssessmentInformation.class).where("ReportId", "=", reportId));
						if (AI_SupplementaryAssessmentInformation != null && AI_SupplementaryAssessmentInformation.getID() > 0) {
							yangLao.setAI_SupplementaryAssessmentInformation(AI_SupplementaryAssessmentInformation);
						}
						AI_AbilityAssessmentConclusion = dbUtils2.findFirst(Selector.from(AI_AbilityAssessmentConclusion.class).where("ReportId", "=", reportId));
						if (AI_AbilityAssessmentConclusion != null && AI_AbilityAssessmentConclusion.getID() > 0) {
							yangLao.setAI_AbilityAssessmentConclusion(AI_AbilityAssessmentConclusion);
						}
						AI_MainAssessorInformation = dbUtils2.findFirst(Selector.from(AI_MainAssessorInformation.class).where("ReportId", "=", reportId));
						if (AI_MainAssessorInformation != null && AI_MainAssessorInformation.getID() > 0) {
							yangLao.setAI_MainAssessorInformation(AI_MainAssessorInformation);
						}
						AI_AssessmentSupplementaryInformation = dbUtils2.findFirst(Selector.from(AI_AssessmentSupplementaryInformation.class).where("ReportId", "=", reportId));
						if (AI_AssessmentSupplementaryInformation != null && AI_AssessmentSupplementaryInformation.getID() > 0) {
							yangLao.setAI_AssessmentSupplementaryInformation(AI_AssessmentSupplementaryInformation);
						}
		
						SF_PersonalSupplement = dbUtils2.findFirst(Selector.from(SF_PersonalSupplement.class).where("ReportId", "=", reportId));
						if (SF_PersonalSupplement != null && SF_PersonalSupplement.getID() > 0) {
							yangLao.setSF_PersonalSupplement(SF_PersonalSupplement);
						}
						SF_MentalStateAssessment = dbUtils2.findFirst(Selector.from(SF_MentalStateAssessment.class).where("ReportId", "=", reportId));
						if (SF_MentalStateAssessment != null && SF_MentalStateAssessment.getID() > 0) {
							yangLao.setSF_MentalStateAssessment(SF_MentalStateAssessment);
						}
						SF_MFA = dbUtils2.findFirst(Selector.from(SF_MFA.class).where("ReportId", "=", reportId));
						if (SF_MFA != null && SF_MFA.getID() > 0) {
							yangLao.setSF_MFA(SF_MFA);
						}
						SF_COPM = dbUtils2.findFirst(Selector.from(SF_COPM.class).where("ReportId", "=", reportId));
								if (SF_COPM != null && SF_COPM.getID() > 0) {
					yangLao.setSF_COPM(SF_COPM);
						}
		
					} catch (DbException e) {
						
						e.printStackTrace();
					}
					dbUtils2.close();
					Gson gson = new Gson();
					String jsonData = gson.toJson(yangLao);
					Log.d("test", "test:" + jsonData);
					
					String huazhongName = reportId.toString() + "0z.png";
					File zhongPath = SDPath.getSDPath(getActivity());
					if (!zhongPath.exists()) {
						zhongPath.mkdirs();
					}
					huazhongPath = zhongPath.getPath() + "/" + huazhongName;
					
					request.uploadRequest(jsonData,writePath,huazhongPath);
				}
			}else {
				Toast.makeText(mContext, "请先填写签字", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return false;

		case MotionEvent.ACTION_UP:
			switch (arg0.getId()) {
			case R.id.etReportDate:
				new MyDatePickerDialog(mContext, etReportDate, c).showMyDatePickerDialog();
				break;
			case R.id.et_beginTime:
				new MyTimePickerDialog(mContext, et_beginTime, c).showMyTimePickerDialog();
				break;
			case R.id.et_endTime:
				new MyTimePickerDialog(mContext, et_endTime, c).showMyTimePickerDialog();
				break;

			default:
				break;
			}
			return true;
		}
		return false;
	}

	private void saveorUpdate(String hasUpload, String uploading) {
		DbUtils dbUtils = DbUtils.create(mContext);
		YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport = getData();
		Log.v("TAG", "yangLaoServiceAssessmentReport:  "+yangLaoServiceAssessmentReport.toString());

		try {
			if (dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId)) != null) {

				yangLaoServiceAssessmentReport.setIsUpload(hasUpload);
				dbUtils.update(yangLaoServiceAssessmentReport);
				if (!uploading.equals("1")) {
					Toast.makeText(mContext, "已更新", Toast.LENGTH_SHORT).show();
				}
			} else {
				yangLaoServiceAssessmentReport.setIsUpload(hasUpload);
				dbUtils.save(yangLaoServiceAssessmentReport);
				if (!uploading.equals("1")) {
					Toast.makeText(mContext, "已保存", Toast.LENGTH_SHORT).show();
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		dbUtils.close();
	}

	private YangLaoServiceAssessmentReport getData() {
		DbUtils dbUtils = DbUtils.create(mContext);
		YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport = new YangLaoServiceAssessmentReport();
		try {
			if (dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId)) != null) {
				yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId));
			} else {
				String tempId = Tools.getUUID();
				yangLaoServiceAssessmentReport.setProjectId(projectId);
				if(!scheduleId.equals("")){
					yangLaoServiceAssessmentReport.setScheduleId(scheduleId);
				}
				yangLaoServiceAssessmentReport.setEmployeeID(employeeID);
				yangLaoServiceAssessmentReport.setEmployeeName(employeeName);
				yangLaoServiceAssessmentReport.setProdID(prodID);
				yangLaoServiceAssessmentReport.setWhetherNew("0");

			}
			if (!"".equals(writePath)) {
				yangLaoServiceAssessmentReport.setApplicantStr(writePath);
				for (int i = 0; i < list.size(); i++) {
					if (!list.get(i).equals(writePath)) {
						File file = new File(list.get(i));
						if (file.exists()) {
							file.delete();
						}
					}
				}
			}
			if (!"".equals(huazhongPath)) {
				yangLaoServiceAssessmentReport.setHuazhongStr(huazhongPath);
				for (int i = 0; i < list.size(); i++) {
					if (!list.get(i).equals(huazhongPath)) {
						File file = new File(list.get(i));
						if (file.exists()) {
							file.delete();
						}
					}
				}
			}
		
			yangLaoServiceAssessmentReport.setReportId(etReportId.getText().toString());
			yangLaoServiceAssessmentReport.setReportDate(etReportDate.getText().toString());
			yangLaoServiceAssessmentReport.setBeginTime(etReportDate.getText().toString() + "T" + et_beginTime.getText().toString() + ":00");
			yangLaoServiceAssessmentReport.setEndTime(etReportDate.getText().toString() + "T" + et_endTime.getText().toString() + ":59");
			yangLaoServiceAssessmentReport.setPromiseDesc1(cbPromiseDesc1.isChecked() + "");
			yangLaoServiceAssessmentReport.setPromiseDesc2(cbPromiseDesc2.isChecked() + "");
			yangLaoServiceAssessmentReport.setPromiseDesc3(cbPromiseDesc3.isChecked() + "");
			yangLaoServiceAssessmentReport.setRelation(sp_relation.getSelectedItemPosition() + "");
		} catch (DbException e) {
			e.printStackTrace();
		}
		return yangLaoServiceAssessmentReport;
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
		Tools.stopProgressDialog(mContext);
	}
}
