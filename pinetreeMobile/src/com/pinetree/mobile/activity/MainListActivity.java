package com.pinetree.mobile.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.LiveFolders;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.question.adapter.AdapterMainList;
import com.example.question.tab.MoreTabActivity;
import com.fr.android.app.activity.IFAboutActivity4Pad;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import com.pinetree.mobile.bean.AreaEntity;
import com.pinetree.mobile.bean.BI_IDInformation;
import com.pinetree.mobile.bean.BI_PersonalInformation;
import com.pinetree.mobile.bean.Copm;
import com.pinetree.mobile.bean.CopmInfo;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.SF_COPM;
import com.pinetree.mobile.bean.TwoEntity;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;
import com.pinetree.mobile.db.AreaOperationDB;
import com.pinetree.mobile.db.AssetHelper;
import com.pinetree.mobile.utils.Request;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

/**
 * 
 *  @类描述	应用主列表，展示所有本地存储的评估报告
 * @author wcm 
 * @createDate 2015-10-13 上午9:24:13
 */
@ContentView(R.layout.activity_main_list)
public class MainListActivity extends Activity {
	@ViewInject(R.id.button)
	private Button button;
	@ViewInject(R.id.bt_add)
	private Button btAdd;
	@ViewInject(R.id.tv_title)
	private TextView tvTitle;
	@ViewInject(R.id.lv_main)
	private ListView lvMain;
	private AdapterMainList adapterMainList;
	private List<YangLaoServiceAssessmentReport> questionList;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	@ViewInject(R.id.et_name)
	EditText et_sex;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	@ResInject(id = R.array.array_area, type = ResType.StringArray)
	private String[] array_area;
	@ResInject(id = R.array.array_street, type = ResType.StringArray)
	private String[] array_street;
	private ArrayAdapter<?> adapter_area, adapter_street;

	/** 一级集合 */
	private List<String> oneList = new ArrayList<String>();
	/** 二级集合 */
	private List<TwoEntity> twoList = new ArrayList<TwoEntity>();
	/** 默认集合 */
	private List<String> indexList;
	/** 适配器 */
	private ArrayAdapter<String> adapter;
	/** 一级名称 */
	private String oneName;
	/** 二级名称 */
	private String twoName;
	AreaOperationDB areaOperationDB;
	private List<AreaEntity> provinceList = new ArrayList<AreaEntity>();
	private List<AreaEntity> cityList = new ArrayList<AreaEntity>();
	private List<String> reportIds = new ArrayList<String>();
	Request request;
	private String scheduleId = "";
	private String projectId = "";
	private String prodID = "";
	private String employeeID = "";
	private String employeeName = "";
	private Customer customer;
	
	public Handler Myhandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			Tools.stopProgressDialog(mContext);
			if (msg.arg1 == 101) {
				String str = msg.getData().getString("result");
				int flag = msg.getData().getInt("flag");
				if (flag == 1) {
					List<CopmInfo> copmInfos = Tools.getCopmInfoFromJson(str);
					List<Copm> copms = new ArrayList<Copm>();
					if (copmInfos != null && copmInfos.size() > 0) {
						for (int i = 0; i < copmInfos.size(); i++) {
							CopmInfo copmInfo = copmInfos.get(i);
							Copm copm = new Copm();
							copm.setContent(copmInfo.getContent());
							copm.setIsLast(copmInfo.getIsLast());
							copm.setSatisf(copmInfo.getSatisf());
							copm.setSituation(copmInfo.getSituation());
							copms.add(copm);
						}
					}
					if (copms.size() > 0) {
						SF_COPM sf_COPM = new SF_COPM();
						Gson gson = new Gson();
						String jsonData = gson.toJson(copms);
						sf_COPM.setCopm(jsonData);
						sf_COPM.setIsLast("0");
						sf_COPM.setReportId(reportId);
						sf_COPM.setSituationSum(copmInfos.get(0).getSituationSum());
						sf_COPM.setSatisfSum(copmInfos.get(0).getSatisfSum());
						DbUtils dbUtils = DbUtils.create(mContext);
						try {
							if (dbUtils.findFirst(Selector.from(SF_COPM.class).where("reportId", "=", reportId)) == null) {
								dbUtils.save(sf_COPM);
							} else {
							}
						} catch (DbException e) {
							e.printStackTrace();
						}
						dbUtils.close();
					}
				} else {
					Toast.makeText(getApplicationContext(), str, 1000).show();
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewUtils.inject(this);
		mContext = this;
		tvTitle.setText(getResources().getString(R.string.app_title));
		request = new Request(Myhandler);
		init();
		initView();
	}

	private void init() {
		File file = new File(AssetHelper.getPath(mContext, false) + "/" + getString(R.string.str_bj_db));
		if (file.exists()) {
			Log.i("UploadInfo", "呼市数据库已存在");
		} else {
			try {
				AssetHelper.CopyAsset(mContext, AssetHelper.getPath(mContext, false), getString(R.string.str_bj_db));
			} catch (IOException e) {
			}
			Log.i("UploadInfo", "呼市数据库不存在");
		}
		file = new File(AssetHelper.getPath(mContext, false) + "/" + getString(R.string.str_note_db));
			try {
				AssetHelper.CopyAsset(mContext, AssetHelper.getPath(mContext, false), getString(R.string.str_note_db));
			} catch (IOException e) {
			}
		customer = (Customer) getIntent().getExtras().getSerializable("initData");
		Log.v("TAG", "ID:" + customer.getId().toString());
		employeeName = getIntent().getExtras().getString("employeeName");
		employeeID = getIntent().getExtras().getString("employeeId");
		if (customer != null) {
			DbUtils dbUtils = DbUtils.create(this);
			scheduleId = customer.getId();
			projectId = customer.getProjectId();
			prodID = customer.getProdID();
			String custID = customer.getCustID();
			String sexStr = customer.getSex();
			String birth = "";
			if (customer.getBirth() != null && !"".equals(customer.getBirth())) {
				birth = customer.getBirth().toString();
			}
			String birthTime = "";
			if (birth != null && !"".equals(birth)) {
				birthTime = birth.substring(0, 10);
			}
			String cityAddress = "";
			String custAddress = "";
			if (customer.getCityAddress() != null && !"".equals(customer.getCityAddress())) {
				cityAddress = customer.getCityAddress().toString();
			}
			if (customer.getCustAddress() != null && !"".equals(customer.getCustAddress())) {
				custAddress = customer.getCustAddress().toString();
			}
			String live = cityAddress+custAddress;
			try {
				YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("scheduleId", "=", scheduleId)
								.and("whetherNew", "=", "1"));
				if (yangLaoServiceAssessmentReport == null) {
					reportId = Tools.getCurrentTimeMillis() + "";
					yangLaoServiceAssessmentReport = new YangLaoServiceAssessmentReport();
					yangLaoServiceAssessmentReport.setReportId(reportId);
					yangLaoServiceAssessmentReport.setAge(customer.getAge());
					yangLaoServiceAssessmentReport.setBeginTime(customer.getBeginTime());
					yangLaoServiceAssessmentReport.setCustAddress(customer.getCustAddress()+"");
					yangLaoServiceAssessmentReport.setCustID(custID);
					yangLaoServiceAssessmentReport.setCustName(customer.getCustName());
					yangLaoServiceAssessmentReport.setCustPhone(customer.getCustPhone()+"");
					yangLaoServiceAssessmentReport.setEmpNum(customer.getEmpNum());
					yangLaoServiceAssessmentReport.setEmployeeID(employeeID);
					yangLaoServiceAssessmentReport.setEmployeeName(employeeName);
					yangLaoServiceAssessmentReport.setEndTime(customer.getEndTime());
					yangLaoServiceAssessmentReport.setScheduleId(scheduleId);// 此处注意，传来的是id,但是我定义的实体类字段名为ScheduleId
					yangLaoServiceAssessmentReport.setLat(customer.getLat());
					yangLaoServiceAssessmentReport.setLng(customer.getLng());
					yangLaoServiceAssessmentReport.setSex(customer.getSex());
					yangLaoServiceAssessmentReport.setProjectId(customer.getProjectId());
					yangLaoServiceAssessmentReport.setProdID(customer.getProdID());
					yangLaoServiceAssessmentReport.setWhetherNew("1");
					yangLaoServiceAssessmentReport.setIsUpload("0");
					yangLaoServiceAssessmentReport.setReportDate(customer.getBeginTime().split("T")[0]);
					dbUtils.save(yangLaoServiceAssessmentReport);
					BI_IDInformation bi = new BI_IDInformation();
					bi.setCustID(custID);
					bi.setReportId(reportId);
					bi.setName(customer.getCustName());
					bi.setDisabilityCard(customer.getDisabledPaper());
					bi.setDisabledSoldierCertificate(customer.getDisabledSoldierPaper());
					bi.setIDNumber(customer.getIdNum());
					bi.setMedicareCardNumber(customer.getHealthCard());
					bi.setNameUsedBefore(customer.getBeforeName());
					bi.setWhetherNew("1");
					BI_IDInformation bi0 =dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
					if (bi0 != null) {
						bi.setCustID(custID);
						bi.setReportId(reportId);
						bi.setName(customer.getCustName());
						bi.setDisabilityCard(bi0.getDisabilityCard()+"");
						bi.setDisabledSoldierCertificate(bi0.getDisabledSoldierCertificate()+"");
						bi.setIDNumber(bi0.getIDNumber());
						bi.setMedicareCardNumber(bi0.getMedicareCardNumber()+"");
						bi.setNameUsedBefore(bi0.getNameUsedBefore()+"");
						bi.setWhetherNew("1");
						dbUtils.update(bi);
					}else {
						dbUtils.save(bi);
					}

					BI_PersonalInformation bi1 = null;
					bi1 = new BI_PersonalInformation();
					bi1.setReportId(reportId);
					if (sexStr.equals("1")) {
						bi1.setSex("1");
					} else {
						bi1.setSex("0");
					}
					bi1.setBirthDate(birthTime);
					bi1.setNation(customer.getFamilyName());
					bi1.setPlaceofOrigin(customer.getPlaceOrigin());
					bi1.setLiveAddress(live);
					bi1.setHomePhone(customer.getCustPhone());
					bi1.setMobile(customer.getLinkPhone2());
					bi1.setPostalCode(customer.getPostCode());
					bi1.setEMail(customer.getCustEmail());
					bi1.setWhetherNew("1");
					BI_PersonalInformation bi11 = dbUtils.findFirst(Selector.from(BI_PersonalInformation.class).
							where("ReportId", "=", reportId));
					if(bi11 != null){
						bi1.setReportId(reportId);
						if (sexStr.equals("1")) {
							bi1.setSex("1");
						} else {
							bi1.setSex("0");
						}
						bi1.setBirthDate(bi11.getBirthDate()+"");
						bi1.setNation(bi11.getNation()+"");
						
						bi1.setReligiousBelief(bi11.getReligiousBelief() + "");
						bi1.setMaritalStatus(bi11.getMaritalStatus() + "");
						bi1.setCulturalDegree(bi11.getCulturalDegree() + "");
						bi1.setPlaceofOrigin(bi11.getPlaceofOrigin()+"");
						bi1.setUsingLanguage(bi11.getUsingLanguage() + "");
						bi1.setCensusRegisterAddress(bi11.getCensusRegisterAddress()+"");
						bi1.setLiveAddress(bi11.getLiveAddress()+"");
						bi1.setHomePhone(bi11.getHomePhone()+"");
						bi1.setMobile(bi11.getMobile()+"");
						bi1.setPostalCode(bi11.getPostalCode()+"");
						bi1.setEMail(bi11.getEMail()+"");
						bi1.setWhetherNew("1");
						dbUtils.update(bi1);
					}else {
						dbUtils.save(bi1);
					}
					

					if (!scheduleId.equals("") && !custID.equals("")) {
						request.getCopmsRequest(scheduleId, custID);
					}
				} else {
					yangLaoServiceAssessmentReport.setAge(customer.getAge());
					yangLaoServiceAssessmentReport.setBeginTime(customer.getBeginTime());
					yangLaoServiceAssessmentReport.setCustAddress(customer.getCustAddress()+"");
					yangLaoServiceAssessmentReport.setCustID(custID);
					yangLaoServiceAssessmentReport.setCustName(customer.getCustName());
					yangLaoServiceAssessmentReport.setCustPhone(customer.getCustPhone());
					yangLaoServiceAssessmentReport.setEmpNum(customer.getEmpNum());
					yangLaoServiceAssessmentReport.setEmployeeID(employeeID);
					yangLaoServiceAssessmentReport.setEmployeeName(employeeName);
					yangLaoServiceAssessmentReport.setEndTime(customer.getEndTime());
					yangLaoServiceAssessmentReport.setScheduleId(scheduleId);// 此处注意，传来的是id,但是我定义的实体类字段名为ScheduleId
					yangLaoServiceAssessmentReport.setLat(customer.getLat());
					yangLaoServiceAssessmentReport.setLng(customer.getLng());
					yangLaoServiceAssessmentReport.setSex(customer.getSex());
					yangLaoServiceAssessmentReport.setProjectId(customer.getProjectId());
					yangLaoServiceAssessmentReport.setProdID(customer.getProdID());
					yangLaoServiceAssessmentReport.setWhetherNew("1");
					yangLaoServiceAssessmentReport.setIsUpload("0");
					yangLaoServiceAssessmentReport.setReportDate(customer.getBeginTime().split("T")[0]);
					dbUtils.update(yangLaoServiceAssessmentReport);

					BI_IDInformation bi = new BI_IDInformation();
					BI_IDInformation bi0 =dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
					if (bi0 != null) {
						bi.setCustID(custID);
						bi.setReportId(reportId);
						bi.setName(customer.getCustName());
						bi.setDisabilityCard(bi0.getDisabilityCard()+"");
						bi.setDisabledSoldierCertificate(bi0.getDisabledSoldierCertificate()+"");
						bi.setIDNumber(bi0.getIDNumber());
						bi.setMedicareCardNumber(bi0.getMedicareCardNumber()+"");
						bi.setNameUsedBefore(bi0.getNameUsedBefore()+"");
						bi.setWhetherNew("1");
						dbUtils.update(bi);
					}else {
						bi.setCustID(custID);
						bi.setReportId(reportId);
						bi.setName(customer.getCustName());
						bi.setDisabilityCard(customer.getDisabledPaper());
						bi.setDisabledSoldierCertificate(customer.getDisabledSoldierPaper());
						bi.setIDNumber(customer.getIdNum());
						bi.setMedicareCardNumber(customer.getHealthCard());
						bi.setNameUsedBefore(customer.getBeforeName());
						dbUtils.save(bi);
					}

					BI_PersonalInformation bi1 = new BI_PersonalInformation();
					BI_PersonalInformation bi11 = dbUtils.findFirst(Selector.from(BI_PersonalInformation.class).
							where("ReportId", "=", reportId));
					if(bi11 != null){
						bi1.setReportId(reportId);
						if (sexStr.equals("1")) {
							bi1.setSex("1");
						} else {
							bi1.setSex("0");
						}
						bi1.setBirthDate(bi11.getBirthDate()+"");
						bi1.setNation(bi11.getNation()+"");
						bi1.setReligiousBelief(bi11.getReligiousBelief() + "");
						bi1.setMaritalStatus(bi11.getMaritalStatus() + "");
						bi1.setCulturalDegree(bi11.getCulturalDegree() + "");
						bi1.setPlaceofOrigin(bi11.getPlaceofOrigin()+"");
						bi1.setUsingLanguage(bi11.getUsingLanguage() + "");
						bi1.setCensusRegisterAddress(bi11.getCensusRegisterAddress()+"");
						bi1.setLiveAddress(bi11.getLiveAddress()+"");
						bi1.setHomePhone(bi11.getHomePhone()+"");
						bi1.setMobile(bi11.getMobile()+"");
						bi1.setPostalCode(bi11.getPostalCode()+"");
						bi1.setEMail(bi11.getEMail()+"");
						bi1.setWhetherNew("1");
						dbUtils.update(bi1);
					}else {
						bi1.setReportId(reportId);
						if (sexStr.equals("1")) {
							bi1.setSex("1");
						} else {
							bi1.setSex("0");
						}
						bi1.setBirthDate(customer.getBirth());
						bi1.setNation(customer.getFamilyName());
						bi1.setPlaceofOrigin(customer.getPlaceOrigin());
						bi1.setLiveAddress(live);
						bi1.setHomePhone(customer.getCustPhone());
						bi1.setMobile(customer.getLinkPhone2());
						bi1.setPostalCode(customer.getPostCode());
						bi1.setEMail(customer.getCustEmail());
						dbUtils.save(bi1);
					}

				}
			} catch (DbException e) {
				e.printStackTrace();
			}
			dbUtils.close();
		}
		
		areaOperationDB = new AreaOperationDB(mContext);

	}

	@Override
	protected void onResume() {
		
		super.onResume();
		initView();
		if(et_sex != null)
		{
			et_sex.setText("");
		}
		
		initData();
	}

	@OnClick({ R.id.bt_add, R.id.bt_back})
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_add:
			Intent intent = new Intent(MainListActivity.this, MoreTabActivity.class);
			intent.putExtra("isNew", true);
			intent.putExtra("scheduleId", scheduleId);
			intent.putExtra("employeeID", employeeID);
			intent.putExtra("employeeName", employeeName);
			intent.putExtra("prodID", prodID);
			intent.putExtra("projectId", projectId);
			startActivity(intent);
			break;
		
		default:
			break;
		}
	}

	private void initView() {
		questionList = new ArrayList<YangLaoServiceAssessmentReport>();
		adapterMainList = new AdapterMainList(mContext, questionList);
		lvMain.setAdapter(adapterMainList);
		lvMain.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(MainListActivity.this, MoreTabActivity.class);
				intent.putExtra("isNew", false);
				intent.putExtra("reportId", questionList.get(position).getReportId());
				intent.putExtra("scheduleId", scheduleId);
				intent.putExtra("prodID", prodID);
				intent.putExtra("projectId", projectId);
				intent.putExtra("employeeID", employeeID);
				intent.putExtra("employeeName", employeeName);
				Bundle bundle = new Bundle();
				bundle.putSerializable("customer", customer);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}

	private void initData() {
		DbUtils db = DbUtils.create(this);
		try {
			if (!scheduleId.equals("")) {
				questionList = db.findAll(Selector.from(YangLaoServiceAssessmentReport.class).where("scheduleId", "=", scheduleId));
				if (questionList != null && questionList.size() > 0) {
					adapterMainList = new AdapterMainList(mContext, questionList);
					lvMain.setAdapter(adapterMainList);
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onContextItemSelected(item);
	}
}
