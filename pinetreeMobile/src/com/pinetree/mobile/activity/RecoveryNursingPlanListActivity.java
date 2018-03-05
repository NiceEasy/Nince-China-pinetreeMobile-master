package com.pinetree.mobile.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fr.android.app.activity.IFAboutActivity4Pad;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.ChooseAssessmentReportActivity.MyAdapter;
import com.pinetree.mobile.activity.ChooseAssessmentReportActivity.ViewHolder;
import com.pinetree.mobile.bean.RecoveryMeasuresBase;
import com.pinetree.mobile.bean.RecoveryNursingPlan;
import com.pinetree.mobile.bean.RecoveryNursingPlanBase;
import com.pinetree.mobile.bean.RegainHistoryRecords;
import com.pinetree.mobile.bean.RegainPublicize;
import com.pinetree.mobile.bean.RegainQuestion;
import com.pinetree.mobile.bean.RegainTarget;
import com.pinetree.mobile.bean.RegainTargetSub;
import com.pinetree.mobile.bean.RehabilitationMeasures;
import com.pinetree.mobile.bean.ServerRecord;
import com.pinetree.mobile.bean.ServerRecordBase;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.FilesUtils;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 护理人员写的康复护理计划列表
 */
public class RecoveryNursingPlanListActivity extends Activity implements OnClickListener{

	/**
	 * 返回
	 */
	@ViewInject(R.id.back_nursingPlanList_imageView)
	private ImageView backNursingPlanListImageView;
	/**
	 * 新增康复护理计划
	 */
	@ViewInject(R.id.addPlan_textView)
	private TextView addPlanTextView;
	/**
	 * 更新康复措施
	 */
	@ViewInject(R.id.update_measures_button)
	private Button updateMeasuresButton;
	/**
	 * 更新健康宣教
	 */
	@ViewInject(R.id.update_health_button)
	private Button updateHealthButton;
	/**
	 * 康复计划列表
	 */
	@ViewInject(R.id.planList_listView)
	private ListView planListListView;
	/**
	 * 刷新当前页面
	 */
	@ViewInject(R.id.refresh_button)
	private Button refreshButton;
	
	private MyAdapter adapter;
	private List<String> list = new ArrayList<String>();
	private List<RecoveryNursingPlan> allList = new ArrayList<RecoveryNursingPlan>();
	private Dialog dialog;
	private String employeeId = "";
	private String employeeName = "";
	
	private static final int ADD_REPORT = 4;//进入详情页后添加一条
	private static final int PLANDATANET = 0;//康复计划列表
	private static final int REFRESHPLANDATANET = 1;//刷新康复计划列表
	private static final int UPDATEMEASURE = 2;//更新康复措施数据
	private static final int UPDATEHEALTH = 3;//更新健康宣教数据
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PLANDATANET:
				allList = (List<RecoveryNursingPlan>) msg.obj;
				Log.v("TAG", "allList : " + allList.toString());
				planListListView.setAdapter(adapter);
				break;
			case REFRESHPLANDATANET:
				allList.clear();
				String cacheInfoNew = FilesUtils.getCacheInfo(employeeId, RecoveryNursingPlanListActivity.this);
				List<RecoveryNursingPlan> netList = null; 
				PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
				if (!"".equals(cacheInfoNew)&&null!=cacheInfoNew) {
					RecoveryNursingPlanBase recoveryNursingPlanBase = GsonUtils.json2bean(cacheInfoNew, RecoveryNursingPlanBase.class);
					netList = recoveryNursingPlanBase.getResultData();
					for (int i = 0; i < netList.size(); i++) {
						if ("1".equals(netList.get(i).getComitStatus())) {
							String planID = netList.get(i).getId();
							RecoveryNursingPlan recoveryNursingPlan = pinetreeDB1.getRecoveryNursingPlan(planID);
							if (recoveryNursingPlan != null) {
								pinetreeDB1.deleteRecoveryNursingPlan(planID);
								pinetreeDB1.deleteRegainQuestion(planID, "0");
								pinetreeDB1.deleteRegainQuestion(planID, "1");
								pinetreeDB1.deleteRegainTarget(planID);
								pinetreeDB1.deleteRegainTargetSub(planID);
								pinetreeDB1.deleteRegainPublicize(planID);
							}
						}
						
					}
				}
				List<RecoveryNursingPlan> dbList = pinetreeDB1.getAllRecoveryNursingPlan();
				for (int i = 0; i < dbList.size(); i++) {
					String id = dbList.get(i).getPlanID();
					dbList.get(i).setRegainHistoryRecordsList(pinetreeDB1.getHistoryRecords(id));
					dbList.get(i).setCurrQuestionList(pinetreeDB1.getRegainQuestion(id, "0"));
					dbList.get(i).setSolveQuestionList(pinetreeDB1.getRegainQuestion(id, "1"));
					dbList.get(i).setRegainTargetList(pinetreeDB1.getRegainTarget(id));
					dbList.get(i).setRegainTargetSubList(pinetreeDB1.getRegainTargetSub(id));
					dbList.get(i).setRegainPublicizeList(pinetreeDB1.getRegainPublicize(id));
				}
				List<RecoveryNursingPlan> newList1 = CompareData(netList, dbList);
				if (newList1 != null) {
					allList.addAll(newList1);
				}
				planListListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				break;
			case UPDATEMEASURE:
				String cacheInfo = FilesUtils.getCacheInfo("康复措施", RecoveryNursingPlanListActivity.this);
				Log.v("TAG", "康复措施：cacheInfo :  "+cacheInfo.toString());
				RecoveryMeasuresBase recoveryMeasuresBase = GsonUtils.json2bean(cacheInfo, RecoveryMeasuresBase.class);
				RehabilitationMeasures rehabilitationMeasures = new RehabilitationMeasures();
				rehabilitationMeasures.setTitle("请选择");
				rehabilitationMeasures.setType("0");
				List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
				list.add(rehabilitationMeasures);
				list.addAll(recoveryMeasuresBase.getResultData());
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
				pinetreeDB.deleteRehabilitationMeasures();
				Log.v("TAG", "UPDATEMEASURE list.size(): " + list.size());
				for (int i = 0; i < list.size(); i++) {
					pinetreeDB.saveRehabilitationMeasure(list.get(i));
					
				}
				
				break;
			case UPDATEHEALTH:
				String cacheInfo1 = FilesUtils.getCacheInfo("健康宣教", RecoveryNursingPlanListActivity.this);
				Log.v("TAG", "健康宣教：cacheInfo :  "+cacheInfo1.toString());
				RecoveryMeasuresBase recoveryMeasuresBase1 = GsonUtils.json2bean(cacheInfo1, RecoveryMeasuresBase.class);
				RehabilitationMeasures rehabilitationMeasures1 = new RehabilitationMeasures();
				rehabilitationMeasures1.setTitle("请选择");
				rehabilitationMeasures1.setType("0");
				List<RehabilitationMeasures> list1 = new ArrayList<RehabilitationMeasures>();
				list1.add(rehabilitationMeasures1);
				list1.addAll(recoveryMeasuresBase1.getResultData());
				PinetreeDB pinetreeDB2 = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
				pinetreeDB2.deleteHealthEducation();
				Log.v("TAG", "UPDATEHEALTH list1.size(): " + list1.size());
				for (int i = 0; i < list1.size(); i++) {
					pinetreeDB2.saveHealthEducation(list1.get(i));
					
				}
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recovery_nursing_plan_list);
		
		employeeId = getIntent().getStringExtra("employeeId");
		employeeName = getIntent().getStringExtra("employeeName");
				
		ViewUtils.inject(this);
		
		dialog = new Dialog(RecoveryNursingPlanListActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		
		for (int i = 0; i < 10; i++) {
			list.add(i+"");
		}
		
		adapter = new MyAdapter();
		
		getDataNet(); 
		
		setListener();
		
	}

	/**
	 * 访问网络获取列表
	 */
	private void getDataNet() {
		dialog.show();
		if (NetUtil.checkNetWork(RecoveryNursingPlanListActivity.this)) {
			RequestParams params = new RequestParams();
			params.addBodyParameter("empID",employeeId);//"fcd7c18806264211b8e2250f5c224170"
			HttpUtil.post("regainPlanList.action", params,new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					RecoveryNursingPlanBase recoveryNursingPlanBase = GsonUtils.json2bean(responseInfo.result,
							RecoveryNursingPlanBase.class);
					Log.v("TAG", "网上数据  responseInfo.result " + responseInfo.result.toString());
					List<RecoveryNursingPlan> dapterList = new ArrayList<RecoveryNursingPlan>();
					dialog.hide();
					if (!"".equals(recoveryNursingPlanBase.getSuccess())&& Boolean.valueOf(recoveryNursingPlanBase.getSuccess())) {
						dapterList.addAll(recoveryNursingPlanBase.getResultData());
						FilesUtils.setCacheInfo(employeeId, responseInfo.result, RecoveryNursingPlanListActivity.this);
						PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
						
						if (dapterList.size() > 0) {
							for (int i = 0; i < dapterList.size(); i++) {
								if ("1".equals(dapterList.get(i).getComitStatus())) {
									String planID = dapterList.get(i).getId();
									RecoveryNursingPlan recoveryNursingPlan = pinetreeDB.getRecoveryNursingPlan(planID);
									if (recoveryNursingPlan != null) {
										pinetreeDB.deleteRecoveryNursingPlan(planID);
										pinetreeDB.deleteHistoryRecords(planID);
										pinetreeDB.deleteRegainQuestion(planID, "0");
										pinetreeDB.deleteRegainQuestion(planID, "1");
										pinetreeDB.deleteRegainTarget(planID);
										pinetreeDB.deleteRegainTargetSub(planID);
										pinetreeDB.deleteRegainPublicize(planID);
									}
								}
								
							}
						}
						
						List<RecoveryNursingPlan> recoveryNursingPlanList = pinetreeDB.getAllRecoveryNursingPlan();
						for (int i = 0; i < recoveryNursingPlanList.size(); i++) {
							String planID = recoveryNursingPlanList.get(i).getPlanID();
							List<RegainHistoryRecords> regainHistoryRecordsList = pinetreeDB.getHistoryRecords(planID);
							List<RegainQuestion> currQuestionList = pinetreeDB.getRegainQuestion(planID, "0");//现存问题
							List<RegainQuestion> solveQuestionList = pinetreeDB.getRegainQuestion(planID, "1");//拟解决问题
							List<RegainTarget> regainTargetList = pinetreeDB.getRegainTarget(planID);//康复目标
							List<RehabilitationMeasures> regainTargetSubList = pinetreeDB.getRegainTargetSub(planID);//康复措施
							List<RehabilitationMeasures> regainPublicizeList = pinetreeDB.getRegainPublicize(planID);//健康宣教
							recoveryNursingPlanList.get(i).setRegainHistoryRecordsList(regainHistoryRecordsList);
							recoveryNursingPlanList.get(i).setCurrQuestionList(currQuestionList);
							recoveryNursingPlanList.get(i).setSolveQuestionList(solveQuestionList);
							recoveryNursingPlanList.get(i).setRegainTargetList(regainTargetList);
							recoveryNursingPlanList.get(i).setRegainTargetSubList(regainTargetSubList);
							recoveryNursingPlanList.get(i).setRegainPublicizeList(regainPublicizeList);
							
						}
						
						List<RecoveryNursingPlan> list = CompareData(dapterList,recoveryNursingPlanList);
						
						
						Message message = Message.obtain();
						message.obj = list;
						message.what = PLANDATANET;
						handler.sendMessage(message);
					}else {
						PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
						List<RecoveryNursingPlan> recoveryNursingPlanList = pinetreeDB.getAllRecoveryNursingPlan();
						for (int i = 0; i < recoveryNursingPlanList.size(); i++) {
							String planID = recoveryNursingPlanList.get(i).getPlanID();
							List<RegainHistoryRecords> regainHistoryRecordsList = pinetreeDB.getHistoryRecords(planID);
							List<RegainQuestion> currQuestionList = pinetreeDB.getRegainQuestion(planID, "0");//现存问题
							List<RegainQuestion> solveQuestionList = pinetreeDB.getRegainQuestion(planID, "1");//拟解决问题
							List<RegainTarget> regainTargetList = pinetreeDB.getRegainTarget(planID);//康复目标
							List<RehabilitationMeasures> regainTargetSubList = pinetreeDB.getRegainTargetSub(planID);//康复措施
							List<RehabilitationMeasures> regainPublicizeList = pinetreeDB.getRegainPublicize(planID);//健康宣教
							recoveryNursingPlanList.get(i).setRegainHistoryRecordsList(regainHistoryRecordsList);
							recoveryNursingPlanList.get(i).setCurrQuestionList(currQuestionList);
							recoveryNursingPlanList.get(i).setSolveQuestionList(solveQuestionList);
							recoveryNursingPlanList.get(i).setRegainTargetList(regainTargetList);
							recoveryNursingPlanList.get(i).setRegainTargetSubList(regainTargetSubList);
							recoveryNursingPlanList.get(i).setRegainPublicizeList(regainPublicizeList);
							
						}
						if (null!=recoveryNursingPlanList&&recoveryNursingPlanList.size()>0) {
							dapterList.addAll(recoveryNursingPlanList);
						}
						Message message = Message.obtain();
						message.obj = dapterList;
						message.what = PLANDATANET;
						handler.sendMessage(message);
					}
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
					ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "请求网络失败,请重试");
				}
			});
		} else {
			dialog.hide();
			List<RecoveryNursingPlan> adpterList = new ArrayList<RecoveryNursingPlan>();
			String cacheInfo = FilesUtils.getCacheInfo(employeeId, RecoveryNursingPlanListActivity.this);
			if (!"".equals(cacheInfo)&&null!=cacheInfo) {
				RecoveryNursingPlanBase recoveryNursingPlanBase = GsonUtils.json2bean(cacheInfo, RecoveryNursingPlanBase.class);//缓存
				adpterList.addAll(recoveryNursingPlanBase.getResultData());
			}
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(RecoveryNursingPlanListActivity.this);
			List<RecoveryNursingPlan> recoveryNursingPlanList = pinetreeDB.getAllRecoveryNursingPlan();
			for (int i = 0; i < recoveryNursingPlanList.size(); i++) {
				String planID = recoveryNursingPlanList.get(i).getPlanID();
				List<RegainHistoryRecords> regainHistoryRecordsList = pinetreeDB.getHistoryRecords(planID);
				List<RegainQuestion> currQuestionList = pinetreeDB.getRegainQuestion(planID, "0");//现存问题
				List<RegainQuestion> solveQuestionList = pinetreeDB.getRegainQuestion(planID, "1");//拟解决问题
				List<RegainTarget> regainTargetList = pinetreeDB.getRegainTarget(planID);//康复目标
				List<RehabilitationMeasures> regainTargetSubList = pinetreeDB.getRegainTargetSub(planID);//康复措施
				List<RehabilitationMeasures> regainPublicizeList = pinetreeDB.getRegainPublicize(planID);//健康宣教
				recoveryNursingPlanList.get(i).setRegainHistoryRecordsList(regainHistoryRecordsList);
				recoveryNursingPlanList.get(i).setCurrQuestionList(currQuestionList);
				recoveryNursingPlanList.get(i).setSolveQuestionList(solveQuestionList);
				recoveryNursingPlanList.get(i).setRegainTargetList(regainTargetList);
				recoveryNursingPlanList.get(i).setRegainTargetSubList(regainTargetSubList);
				recoveryNursingPlanList.get(i).setRegainPublicizeList(regainPublicizeList);
				
			}
			
			List<RecoveryNursingPlan> list = CompareData(adpterList,recoveryNursingPlanList);
			Message message = Message.obtain();
			message.obj = list;
			message.what = PLANDATANET;
			handler.sendMessage(message);
		}
	}
	
	/**
	 * 比较有相同康复护理计划ID的数据，显示最新,只判断本条数据为暂存状态显示最新
	 */
	private List<RecoveryNursingPlan> CompareData(List<RecoveryNursingPlan> dapterList, List<RecoveryNursingPlan> recoveryNursingPlanList) {
		
		if (null != dapterList) {
			for (int i = 0; i < dapterList.size(); i++) {
				String planID = dapterList.get(i).getId();
				if (null != recoveryNursingPlanList) {
					for (int j = 0; j < recoveryNursingPlanList.size(); j++) {
						String id = recoveryNursingPlanList.get(j).getPlanID();
						if (planID.equals(id)) {
							try {
								String time = dapterList.get(i).getCreateTime();
								String dbTime = recoveryNursingPlanList.get(j).getCreateTime();
								String timeDB = dbTime.substring(0, 10)+ " "+ dbTime.substring(11, 19);
								String timeNet = time.substring(0, 10)+ " "+ time.substring(11, 19);
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date dbDate = format.parse(timeDB);
								Date netDate = format.parse(timeNet);
								if (dbDate.compareTo(netDate) >= 0 ) {
									dapterList.remove(i);
								} else {
									recoveryNursingPlanList.remove(j);
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
						}
					}
				}
			}
			
		}
		for (int i = 0; i < recoveryNursingPlanList.size(); i++) {
			dapterList.add(0,recoveryNursingPlanList.get(i)); 
		}
		
		return dapterList;
	}

	private void setListener() {
		
		backNursingPlanListImageView.setOnClickListener(this);
		addPlanTextView.setOnClickListener(this);
		updateMeasuresButton.setOnClickListener(this);
		updateHealthButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		
		planListListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				
				RecoveryNursingPlan recoveryNursingPlan = allList.get(position);
				Intent intent = new Intent(RecoveryNursingPlanListActivity.this, RecoveryNursingPlanActivity.class);
				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("RecoveryNursingPlan", recoveryNursingPlan);
				bundle2.putString("employeeId", employeeId);
				bundle2.putString("employeeName", employeeName);
				
				intent.putExtras(bundle2);
				startActivityForResult(intent, ADD_REPORT);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ADD_REPORT:
			Message message = Message.obtain();
			message.what = REFRESHPLANDATANET;
			handler.sendMessage(message);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.back_nursingPlanList_imageView:
			finish();
			break;
		case R.id.addPlan_textView:
			Intent intent = new Intent(RecoveryNursingPlanListActivity.this,ChooseAssessmentReportActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("employeeId", employeeId);
			bundle.putString("employeeName", employeeName);
			intent.putExtras(bundle);
			startActivityForResult(intent, ADD_REPORT);
			break;
		case R.id.update_measures_button:
			
			if (NetUtil.checkNetWork(RecoveryNursingPlanListActivity.this)) {
				dialog.show();
				HttpUtil.post("queryTagetSubDictionary.action", null, new RequestCallBack<String>() {
					
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						
						RecoveryMeasuresBase recoveryMeasuresBase = GsonUtils.json2bean(responseInfo.result,
								RecoveryMeasuresBase.class);
						
						if (!"".equals(recoveryMeasuresBase.getSuccess())&& Boolean.valueOf(recoveryMeasuresBase.getSuccess())) {
							FilesUtils.setCacheInfo("康复措施", responseInfo.result, RecoveryNursingPlanListActivity.this);
							
							Message message = Message.obtain();
							message.what = UPDATEMEASURE;
							handler.sendMessage(message);
							ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "更新康复措施成功");
							dialog.hide();
							
						}else {
							dialog.hide();
							ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "请重新更新");
						}
					}
					
					@Override
					public void onFailure(HttpException error, String msg) {
						
						dialog.hide();
						ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "更新康复措施失败，请重试");
					}
				});
			}else {
				ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "请联网后再更新");
			}
			break;
		case R.id.update_health_button:
			
			if (NetUtil.checkNetWork(RecoveryNursingPlanListActivity.this)) {
				dialog.show();
				HttpUtil.post("queryHealTeachDictionary.action", null, new RequestCallBack<String>() {
					
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						
						RecoveryMeasuresBase recoveryMeasuresBase = GsonUtils.json2bean(responseInfo.result,
								RecoveryMeasuresBase.class);
						
						if (!"".equals(recoveryMeasuresBase.getSuccess())&& Boolean.valueOf(recoveryMeasuresBase.getSuccess())) {
							FilesUtils.setCacheInfo("健康宣教", responseInfo.result, RecoveryNursingPlanListActivity.this);
							
							ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "更新健康宣教成功");
							
							Message message = Message.obtain();
							message.what = UPDATEHEALTH;
							handler.sendMessage(message);
							dialog.hide();
						}
					}
					
					@Override
					public void onFailure(HttpException error, String msg) {
						
						dialog.hide();
						ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "请重新更新");
					}
				});
			}else {
				ToastUtils.showToast(RecoveryNursingPlanListActivity.this, "请联网后再更新");
			}
			break;
		case R.id.refresh_button://刷新数据
			getDataNet();
			break;
		default:
			break;
		}
	}

	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			return allList.size();
		}

		@Override
		public Object getItem(int position) {
			
			return allList.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(RecoveryNursingPlanListActivity.this).inflate(
						R.layout.plan_list_item, null);
				holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
				holder.serverPeopleTextView = (TextView) convertView.findViewById(R.id.serverPeople_textView);
				holder.serverTimeTextView = (TextView) convertView.findViewById(R.id.serverTime_textView);
				holder.stateTextView = (TextView) convertView.findViewById(R.id.state_textView);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.nameTextView.setText(allList.get(position).getCustName());
			holder.serverPeopleTextView.setText(employeeName);
			String serTime = allList.get(position).getSerDate();
			String time = serTime.substring(0, 10) + " " + serTime.substring(11, 19);
			holder.serverTimeTextView.setText(time);
			Log.v("TAG", "allList.get(position).getComitStatus() :  " + allList.get(position).getComitStatus());
			if ("1".equals(allList.get(position).getComitStatus())) {
				holder.stateTextView.setText("已提交");
			}else if ("0".equals(allList.get(position).getComitStatus())) {
				holder.stateTextView.setText("已暂存");
			}else {
				holder.stateTextView.setText("已保存");
			}
			
			return convertView;
		}
		
	}
	public class ViewHolder{
		public TextView nameTextView;
		public TextView sexTextView;
		public TextView ageTextView;
		public TextView ADLTextView;
		public TextView MMSETextView;
		public TextView serverPeopleTextView;
		public TextView serverTimeTextView;
		public TextView stateTextView;
	}

}
