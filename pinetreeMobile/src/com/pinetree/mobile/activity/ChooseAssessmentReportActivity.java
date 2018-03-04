package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.protocol.RequestExpectContinue;

import com.baidu.mapapi.map.Text;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.ChooseAssessmentReport;
import com.pinetree.mobile.bean.ChooseAssessmentReportBase;
import com.pinetree.mobile.bean.CurrQuestionBase;
import com.pinetree.mobile.bean.RecoveryNursingPlan;
import com.pinetree.mobile.bean.RecoveryNursingPlanBase;
import com.pinetree.mobile.bean.RegainPublicize;
import com.pinetree.mobile.bean.RegainQuestion;
import com.pinetree.mobile.bean.RegainTarget;
import com.pinetree.mobile.bean.RegainTargetSub;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.FilesUtils;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.SexUtil;
import com.pinetree.mobile.utils.ToastUtils;
import com.pinetree.mobile.view.RefreshableView;
import com.pinetree.mobile.view.RefreshableView.PullToRefreshListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 选择要新增的某个评估报告的康复护理计划类
 */
public class ChooseAssessmentReportActivity extends Activity implements OnClickListener{
	
	/**
	 * 返回按钮
	 */
	@ViewInject(R.id.back_choose_report_imageView)
	private ImageView backChooseReportImageView;
	/**
	 * 输入查询的客户名字
	 */
	@ViewInject(R.id.input_editText)
	private EditText inputEditText;
	/**
	 * 查询按钮
	 */
	@ViewInject(R.id.query_button)
	private Button queryButton;
	/**
	 * 刷新
	 */
	@ViewInject(R.id.refresh_button)
	private Button refreshButton;
	/**
	 * 下拉刷新view
	 */
	@ViewInject(R.id.refreshableview)
	private RefreshableView refreshableView;
	/**
	 * listView
	 */
	@ViewInject(R.id.listView)
	private ListView chooseListView;
	/**
	 * 首页
	 */
	@ViewInject(R.id.firstPage_button)
	private Button firstPageButton;
	/**
	 * 上一页
	 */
	@ViewInject(R.id.lastPage_button)
	private Button lastPageButton;
	/**
	 * 页数 输入框
	 */
	@ViewInject(R.id.page_editText)
	private EditText pageEditText;
	/**
	 * 下一页
	 */
	@ViewInject(R.id.nextPage_button)
	private Button nextPageButton;
	/**
	 * 末页
	 */
	@ViewInject(R.id.endPage_button)
	private Button endPageButton;
	private List<String> list = new ArrayList<String>();
	private MyAdapter adapter;
	private int index = 1;//页数
	private int count = 20;//某页显示的项数
	private String custName="";
	private List<ChooseAssessmentReport> dataList = new ArrayList<ChooseAssessmentReport>();
	private List<ChooseAssessmentReport> allDataList;
	private Dialog dialog;
	private int total = 0;
	private String employeeId = "";
	private String employeeName = "";
	private Bundle bundle;
	private static final int NETDATA = 0;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case NETDATA:
				allDataList = (List<ChooseAssessmentReport>) msg.obj;
				adapter.notifyDataSetChanged();
				chooseListView.setAdapter(adapter);
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
		setContentView(R.layout.activity_choose_assessment_report);
		
		ViewUtils.inject(this);
		bundle = getIntent().getExtras();
		
		dialog = new Dialog(ChooseAssessmentReportActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		
		adapter = new MyAdapter();
		setLinstener();
		initData();
		
		
		chooseListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				dialog.show();
				final ChooseAssessmentReport chooseAssessmentReport = allDataList.get(position);
				if (NetUtil.checkNetWork(ChooseAssessmentReportActivity.this)) {
					//有网时
					RequestParams params = new RequestParams();
					params.addBodyParameter("custID",chooseAssessmentReport.getCustID());
					params.addBodyParameter("scheID",chooseAssessmentReport.getScheId());
					HttpUtil.post("queryCurrQuestion.action", params,new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							CurrQuestionBase currQuestionBase = GsonUtils.json2bean(responseInfo.result,
									CurrQuestionBase.class);
							Log.v("TAG", "网上数据  responseInfo.result " + responseInfo.result.toString());
							dialog.hide();
							if (!"".equals(currQuestionBase.getSuccess())&& Boolean.valueOf(currQuestionBase.getSuccess())) {
								RecoveryNursingPlan recoveryNursingPlan = new RecoveryNursingPlan();
								recoveryNursingPlan.setAge(chooseAssessmentReport.getAge());
								recoveryNursingPlan.setComitStatus(chooseAssessmentReport.getComitStatus());
								recoveryNursingPlan.setCustID(chooseAssessmentReport.getCustID());
								recoveryNursingPlan.setCustName(chooseAssessmentReport.getCustName());
								recoveryNursingPlan.setCudeID(chooseAssessmentReport.getCudeID());
								recoveryNursingPlan.setScheId(chooseAssessmentReport.getScheId());
								recoveryNursingPlan.setSex(chooseAssessmentReport.getSex());
								recoveryNursingPlan.setSerDate(chooseAssessmentReport.getSerDate());
								recoveryNursingPlan.setAdlscore(chooseAssessmentReport.getAdlscore());
								recoveryNursingPlan.setMmsescore(chooseAssessmentReport.getMmsescore());
								recoveryNursingPlan.setDruguse(chooseAssessmentReport.getDruguse());
								recoveryNursingPlan.setCheckbody(chooseAssessmentReport.getCheckbody());
								recoveryNursingPlan.setNotice(chooseAssessmentReport.getNotice());
								recoveryNursingPlan.setCurrQuestionList(currQuestionBase.getResultData());
								recoveryNursingPlan.setRegainHistoryRecordsList(chooseAssessmentReport.getRegainHistoryRecordsList());
								recoveryNursingPlan.setPlanID(UUID.randomUUID().toString().replace("-", ""));
								Intent intent = new Intent(ChooseAssessmentReportActivity.this, RecoveryNursingPlanActivity.class);
								Bundle bundle1 = new Bundle();
								bundle1.putSerializable("RecoveryNursingPlan", recoveryNursingPlan);
								
								intent.putExtras(bundle);
								intent.putExtras(bundle1);
								startActivity(intent);
								finish();
							}else {
								ToastUtils.showToast(ChooseAssessmentReportActivity.this, "没有此客户报告");
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
							ToastUtils.showToast(ChooseAssessmentReportActivity.this, "请求网络失败,请重试");
						}
					});
				} else {
					dialog.hide();
					ToastUtils.showToast(ChooseAssessmentReportActivity.this, "没有网络,请在有网时查询");
				} 
				
			}
		});
		if (index == 1) {
			lastPageButton.setEnabled(false);
		}
	}

	private void initData() {
		
		if (NetUtil.checkNetWork(ChooseAssessmentReportActivity.this)) {
			dialog.show();
			Log.v("TAG", "initData index:  " + index);
			Log.v("TAG", "initData custName:  " + custName);
			RequestParams params = new RequestParams();
			params.addBodyParameter("offset",index+"");
			params.addBodyParameter("custName",custName);
			HttpUtil.post("queryAssess.action", params,new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					ChooseAssessmentReportBase chooseAssessmentReportBase = GsonUtils.json2bean(responseInfo.result,
							ChooseAssessmentReportBase.class);
					Log.v("TAG", "网上数据选择客户  responseInfo.result " + responseInfo.result.toString());
					dialog.hide();
					if (!"".equals(chooseAssessmentReportBase.getSuccess())&& Boolean.valueOf(chooseAssessmentReportBase.getSuccess())) {
						dataList.clear();
						dataList.addAll(chooseAssessmentReportBase.getResultData().getData());
						total = Integer.parseInt(chooseAssessmentReportBase.getResultData().getTotal());
						Message message = Message.obtain();
						message.obj = dataList;
						message.what = NETDATA;
						handler.sendMessage(message);
					}else {
						ToastUtils.showToast(ChooseAssessmentReportActivity.this, "没有此客户报告");
					}
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
					ToastUtils.showToast(ChooseAssessmentReportActivity.this, "请求网络失败,请重试");
				}
			});
		} else {
			dialog.hide();
			ToastUtils.showToast(ChooseAssessmentReportActivity.this, "没有网络,请在有网时查询");
		} 
	}

	private void setLinstener() {
		
		backChooseReportImageView.setOnClickListener(this);
		queryButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		firstPageButton.setOnClickListener(this);
		lastPageButton.setOnClickListener(this);
		nextPageButton.setOnClickListener(this);
		endPageButton.setOnClickListener(this);
		pageEditText.setEnabled(false);
		
	}

	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			if (allDataList.size()< count) {
				return allDataList.size();
			} else {
				return count;
			}
		}

		@Override
		public Object getItem(int position) {
			
			return allDataList.get(position);
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
				convertView = LayoutInflater.from(ChooseAssessmentReportActivity.this).inflate(
						R.layout.choose_listview_item, null);
				holder.serialNumberTextView = (TextView) convertView.findViewById(R.id.serialNumber_textView);
				holder.numberTextView = (TextView) convertView.findViewById(R.id.number_textView);
				holder.ageTextView = (TextView) convertView.findViewById(R.id.age_textView);
				holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
				holder.sexTextView = (TextView) convertView.findViewById(R.id.sex_textView);
				holder.timeTextView = (TextView) convertView.findViewById(R.id.time_textView);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.serialNumberTextView.setText((position+1)+"");
			holder.ageTextView.setText(allDataList.get(position).getAge());
			holder.nameTextView.setText(allDataList.get(position).getCustName());
			holder.sexTextView.setText(SexUtil.getSex(allDataList.get(position).getSex()));
			String serTime = allDataList.get(position).getSerDate();
			if (!"".equals(serTime) && null != serTime) {
				String time = serTime.substring(0, 10) + " " + serTime.substring(11, 19);
				holder.timeTextView.setText(time);
			}
			
			return convertView;
		}
		
	}
	public class ViewHolder{
		public TextView serialNumberTextView;//序号
		public TextView numberTextView;//编号
		public TextView ageTextView;
		public TextView nameTextView;
		public TextView sexTextView;
		public TextView timeTextView;
	}
	
	
	
	
	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.back_choose_report_imageView:
			finish();
			break;
		case R.id.query_button:
			index = 1;
			custName = inputEditText.getText().toString();
			if (!"".equals(custName)) {
				initData();
			}
			break;
		case R.id.refresh_button:
			initData();
			break;
		case R.id.firstPage_button:
			index = 1;
			custName = "";
			initData();
			pageEditText.setText(index+"");
			lastPageButton.setEnabled(false);
			nextPageButton.setEnabled(true);
			break;
		case R.id.lastPage_button:
			index--;
			custName = "";
			initData();
			checkButton();
			break;
		case R.id.nextPage_button:
			index++;
			custName = "";
			adapter.notifyDataSetChanged();
			initData();
			checkButton();
			break;
		case R.id.endPage_button:
			index = total;
			custName = "";
			initData();
			pageEditText.setText(total+"");
			nextPageButton.setEnabled(false);
			lastPageButton.setEnabled(true);
			break;
			
		default:
			break;
		}
	}

	public void checkButton() {
		if (index <= 1) {
		lastPageButton.setEnabled(false);
		} else if (index == total) {
		nextPageButton.setEnabled(false);
		} else {
			lastPageButton.setEnabled(true);
			nextPageButton.setEnabled(true);
		}
		pageEditText.setText(index+"");
	}
	
}
