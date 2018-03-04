package com.pinetree.mobile.fragment;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.EverydayRecordSheetActivity;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.PlanList;
import com.pinetree.mobile.bean.PlanSubList;
import com.pinetree.mobile.bean.ServerRecord;
import com.pinetree.mobile.bean.ServerRecordBase;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.DateTimeUtil;
import com.pinetree.mobile.utils.FilesUtils;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 服务记录(每日情况记录单)
 * 
 * @author Administrator
 * 
 */
public class ServerRecordFragment extends Fragment {

	@ViewInject(R.id.server_record_listView)
	private ListView serverRecordListView;
	@ViewInject(R.id.add_server_record_button)
	private Button addServerRecordButton;
	protected static final int SERVICERECORDNET = 0;
	private static final int ADD_SERVER_RECORD = 1;
	private static final int ONACTIVITYRESULT=2;

	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Bundle bundle;
	private Dialog dialog;
	private String custID;
	private List<ServerRecord> serverRecordList = new ArrayList<ServerRecord>();

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SERVICERECORDNET:
				serverRecordList = (List<ServerRecord>) msg.obj;
				serverRecordListView.setAdapter(myAdapter);
				break;
			case ONACTIVITYRESULT:
				serverRecordList.clear();
				String cacheInfo = FilesUtils.getCacheInfo(customer.getCustID(), getActivity());
				if (!"".equals(cacheInfo)&&null!=cacheInfo) {
					ServerRecordBase serverRecordBase = GsonUtils.json2bean(cacheInfo, ServerRecordBase.class);
					serverRecordList.addAll(serverRecordBase.getResultData());
				}
				if (!"".equals(custID) && null != custID) {
					List<ServerRecord> serScore = PinetreeDB.getInstance(getActivity()).getSerScore(custID);
					if (null!=serScore&&serScore.size()>0) {
						serverRecordList.addAll(serScore);
					}
				}
				
				serverRecordListView.setAdapter(myAdapter);
				myAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	private myAdapter myAdapter;
	private String scheID = "";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		bundle = this.getArguments();
		customer = (Customer) bundle.getSerializable("customer");
		custID = customer.getCustID();
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");

		View serverRecordLayout = inflater.inflate(
				R.layout.fragment_server_record, container, false);
		ViewUtils.inject(this, serverRecordLayout);
		dialog = new Dialog(getActivity(), R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		
		myAdapter = new myAdapter();
		scheID = customer.getId();
		
		if (NetUtil.checkNetWork(getActivity())) {
			RequestParams params = new RequestParams();
			params.addBodyParameter("custID",customer.getCustID());
			HttpUtil.post("queryServiceRecord.action", params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							ServerRecordBase serverRecordBase = GsonUtils
									.json2bean(responseInfo.result,
											ServerRecordBase.class);
							ArrayList<ServerRecord> dapterList = new ArrayList<ServerRecord>();
							dialog.hide();
							if (!"".equals(serverRecordBase.getSuccess())&& Boolean.valueOf(serverRecordBase.getSuccess())) {
								dapterList.addAll(serverRecordBase.getResultData());
								FilesUtils.setCacheInfo(customer.getCustID(), responseInfo.result, getActivity());
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
								pinetreeDB.deleteServerRecord();
								List<ServerRecord> serScore = PinetreeDB.getInstance(getActivity()).getSerScore(customer.getCustID());
								if (null!=serScore&&serScore.size()>0) {
									dapterList.addAll(serScore);
								}
								Message message = Message.obtain();
								message.obj = dapterList;
								message.what = SERVICERECORDNET;
								handler.sendMessage(message);
							}else {
								List<ServerRecord> serScore = PinetreeDB.getInstance(getActivity()).getSerScore(customer.getCustID());
								if (null!=serScore&&serScore.size()>0) {
									dapterList.addAll(serScore);
								}
								Message message = Message.obtain();
								message.obj = dapterList;
								message.what = SERVICERECORDNET;
								handler.sendMessage(message);
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
							ToastUtils.showToast(getActivity(), "请求网络失败,请重试");
						}
					});
		} else {
			dialog.hide();
			ArrayList<ServerRecord> adpterList = new ArrayList<ServerRecord>();
			String cacheInfo = FilesUtils.getCacheInfo(customer.getCustID(), getActivity());
			if (!"".equals(cacheInfo)&&null!=cacheInfo) {
				ServerRecordBase serverRecordBase = GsonUtils.json2bean(cacheInfo, ServerRecordBase.class);//缓存
				adpterList.addAll(serverRecordBase.getResultData());
			}
			List<ServerRecord> serScore = PinetreeDB.getInstance(getActivity()).getSerScore(customer.getCustID());//添加
			if (null!=serScore&&serScore.size()>0) {
				adpterList.addAll(serScore);
			}
			Message message = Message.obtain();
			message.obj = adpterList;
			message.what = SERVICERECORDNET;
			handler.sendMessage(message);
		}  

		//条目点击事件
		serverRecordListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ServerRecord serverRecord = serverRecordList.get(position);
				Intent intent = new Intent(getActivity(),
						EverydayRecordSheetActivity.class);
				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("serverRecord", serverRecord);
				bundle2.putString("scheduleId", customer.getId());
				bundle2.putString("custID", customer.getCustID());
				intent.putExtras(bundle2);
				startActivityForResult(intent, ADD_SERVER_RECORD);
			}
		});
		
		
		
		//添加一个新的服务记录
		addServerRecordButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();
				List<ServerRecord> serScore1 = PinetreeDB.getInstance(getActivity()).getSerScoreByScheduleId(customer.getId());
				if (serScore1.size() > 0) {
					dialog.hide();
					ToastUtils.showToast(getActivity(),"每日记录已经新增过，请勿重复填写！");
				}else{
					if (NetUtil.checkNetWork(getActivity())) {
						RequestParams params = new RequestParams();
						params.addBodyParameter("custID",customer.getCustID());
						if (!"".equals(scheID) && null != scheID ) {
							params.addBodyParameter("scheID", customer.getId());
						}
						
						HttpUtil.post("serviceRecordAddView.action", params, new RequestCallBack<String>() {
							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								ServerRecordBase serverRecordBase = GsonUtils.json2bean(responseInfo.result, ServerRecordBase.class);
								if (!"".equals(serverRecordBase.getSuccess())&&Boolean.valueOf(serverRecordBase.getSuccess())) {
									FilesUtils.setCacheInfo(customer.getCustID()+"add", responseInfo.result, getActivity());
									ServerRecord serverRecord = serverRecordBase.getResultData().get(0);
									Intent intent = new Intent(getActivity(),EverydayRecordSheetActivity.class);
									Bundle bundle3 = new Bundle();
									serverRecord.setSubmitState("0");
									bundle3.putSerializable("serverRecord", serverRecord);
									bundle3.putString("scheduleId", customer.getId());
									bundle3.putString("custID", customer.getCustID());
									intent.putExtras(bundle3);
									dialog.hide();
									startActivityForResult(intent, ADD_SERVER_RECORD);
								}else {
									dialog.hide();
									ToastUtils.showToast(getActivity(), "没有每日记录模板");
								}
							}
							
							@Override
							public void onFailure(HttpException error, String msg) {
								dialog.hide();
								ToastUtils.showToast(getActivity(), "请求服务器失败，请重试");
							}
						});
					}else {
						String addCacheInfo = FilesUtils.getCacheInfo(customer.getCustID()+"add", getActivity());
						if (!"".equals(addCacheInfo)&&null!=addCacheInfo) {
							ServerRecordBase serverRecordBase = GsonUtils.json2bean(addCacheInfo, ServerRecordBase.class);
							if (!"".equals(serverRecordBase.getSuccess())
									&& Boolean.valueOf(serverRecordBase.getSuccess())) {
								ServerRecord serverRecord = serverRecordBase.getResultData().get(0);
								Intent intent = new Intent(getActivity(),EverydayRecordSheetActivity.class);
								Bundle bundle3 = new Bundle();
								bundle3.putSerializable("serverRecord", serverRecord);
								bundle3.putString("scheduleId", customer.getId());
								bundle3.putString("custID", customer.getCustID());
								intent.putExtras(bundle3);
								dialog.hide();
								startActivityForResult(intent, ADD_SERVER_RECORD);
							}
						}else {
							dialog.hide();
							ToastUtils.showToast(getActivity(), "没有缓存模板哦");
						}
					}
				}
			}
		});
		
		return serverRecordLayout;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ADD_SERVER_RECORD:
			System.out.println("onActivityResult-------");
			Message message = Message.obtain();
			message.what = ONACTIVITYRESULT;
			handler.sendMessage(message);
			break;

		default:
			break;
		}
	}
	

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return serverRecordList.size();
		}

		@Override
		public Object getItem(int position) {
			return serverRecordList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.listview_item_server_record, null);
				new ViewHolder(convertView);
			}
			final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			final ServerRecord serverRecord = serverRecordList.get(position);
			viewHolder.serverRecordDateTextView.setText(DateTimeUtil.getMSDateTiem(serverRecord.getCreateTime()));
			viewHolder.serverRecordEmpNameTextView.setText(serverRecord.getEmpName());
			String submitState = serverRecord.getSubmitState();
			if ("1".equals(submitState)) {
				viewHolder.serverRecordSubmitButton.setVisibility(View.VISIBLE);
			}else if("0".equals(submitState)){
				viewHolder.serverRecordSubmitButton.setVisibility(View.INVISIBLE);
			}
				viewHolder.serverRecordSubmitButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.show();
						if (NetUtil.checkNetWork(getActivity())) {
							RequestParams params = new RequestParams();
							params.addBodyParameter("recordDay.empID",employeeId);
							params.addBodyParameter("recordDay.servID",serverRecord.getScheduleId());
							params.addBodyParameter("recordDay.custID",serverRecord.getCustID());
							params.addBodyParameter("recordDay.custName", serverRecord.getCustName());
							params.addBodyParameter("recordDay.empName", employeeName);
							params.addBodyParameter("recordDay.age1", serverRecord.getAge());
							params.addBodyParameter("recordDay.sex1", serverRecord.getSex());
							params.addBodyParameter("recordDay.bloodsugar", serverRecord.getBloodsugar());
							params.addBodyParameter("recordDay.breath", serverRecord.getBreath());
							params.addBodyParameter("recordDay.highpressure", serverRecord.getHighpressure());
							params.addBodyParameter("recordDay.lowpressure", serverRecord.getLowpressure());
							params.addBodyParameter("recordDay.pulse", serverRecord.getPulse());
							params.addBodyParameter("recordDay.content", serverRecord.getContent());
							params.addBodyParameter("recordDay.otherSub",serverRecord.getOtherSub());
							params.addBodyParameter("recordDay.startDate1",serverRecord.getStartDate());
							params.addBodyParameter("recordDay.endDate1",serverRecord.getEndDate());
							params.addBodyParameter("recordDay.targetDate",serverRecord.getTargetDate());
							params.addBodyParameter("recordDay.planID",serverRecord.getPlanID());
							params.addBodyParameter("recordDay.temperature",serverRecord.getTemperature());
							List<PlanList> planList = serverRecord.getPlanList();
							StringBuffer planSb=new StringBuffer();
							for (int i = 0; i < planList.size(); i++) {
								if (i!=planList.size()-1) {
									planSb.append(planList.get(i).getContent()+"|");
								}else {
									planSb.append(planList.get(i).getContent());
								}
							}
							List<PlanSubList> planSubList = serverRecord.getPlanSubList();
							StringBuffer planSubSb = new StringBuffer();
							for (int i = 0; i < planSubList.size(); i++) {
								PlanSubList planSubList2 = planSubList.get(i);
								if (i!=planSubList.size()-1) {
									planSubSb.append(planSubList2.getBigTitle()+"^"+planSubList2.getContent()+"^"+planSubList2.getPlanNum()+"^"+planSubList2.getRealNum()+"|");
								}else {
									planSubSb.append(planSubList2.getBigTitle()+"^"+planSubList2.getContent()+"^"+planSubList2.getPlanNum()+"^"+planSubList2.getRealNum());
								}
							}
						
							params.addBodyParameter("recordDayPlanStr", planSb.toString());
							params.addBodyParameter("recordDaySubStr", planSubSb.toString());
							HttpUtil.post("serviceRecordSave.action", params, new RequestCallBack<String>() {
								@Override
								public void onSuccess(ResponseInfo<String> responseInfo) {
									GlobalResult globalResult = GsonUtils.json2bean(responseInfo.result, GlobalResult.class);
									dialog.hide();
									if (!"".equals(globalResult.getSuccess()) && Boolean.valueOf(globalResult.getSuccess())) {
										if ("1".equals(globalResult.getResultData().get(0).getIsServ())) {
											if ("0".equals(globalResult.getResultData().get(0).getIsRecord())) {
												PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
												pinetreeDB.updateServerRecord(serverRecord.getScheduleId());
												pinetreeDB.updateServerRecordButton(serverRecord.getScheduleId());
												serverRecord.setSubmitState("0");
												serverRecord.setSaveState("0");
												myAdapter.notifyDataSetChanged();
												viewHolder.serverRecordSubmitButton.setVisibility(View.INVISIBLE);
												ToastUtils.showToast(getActivity(), "提交成功");
											}else if ("1".equals(globalResult.getResultData().get(0).getIsRecord())) {
												PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
												pinetreeDB.deleteServerRecordByScheID(customer.getId());
												ToastUtils.showToast(getActivity(),"每日记录已经提交过，请勿重复提交！");
												dialog.hide();
												myAdapter.notifyDataSetChanged();
											}
										}else if ("0".equals(globalResult.getResultData().get(0).getIsServ())) {
											ToastUtils.showToast(getActivity(),"请先提交签字单，再提交每日记录！");
											dialog.hide();
										}
									}else {
										viewHolder.serverRecordSubmitButton.setVisibility(View.INVISIBLE);
										ToastUtils.showToast(getActivity(), "提交失败，请重试");
									}
									
								}
								
								@Override
								public void onFailure(HttpException error, String msg) {
									ToastUtils.showToast(getActivity(), "提交失败，请重试！");
									dialog.hide();						
								}
							});
										
						}else {
							dialog.hide();
							ToastUtils.showToast(getActivity(), "亲，没有网络哦");
						}
						
					}
				});

			return convertView;
		}

	}

	class ViewHolder {
		private TextView serverRecordDateTextView;
		private TextView serverRecordEmpNameTextView;
		private Button serverRecordSubmitButton;

		public ViewHolder(View view) {
			serverRecordDateTextView = (TextView) view
					.findViewById(R.id.server_record_date_textView);
			serverRecordEmpNameTextView = (TextView) view
					.findViewById(R.id.server_record_empName_textView);
			serverRecordSubmitButton=(Button) view.findViewById(R.id.server_record_submit_button);
			view.setTag(this);
		}
	}
}
