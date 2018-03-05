package com.pinetree.mobile.activity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.EverydayState;
import com.pinetree.mobile.bean.EverydayStateBase;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.RecordSubmitState;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 日常生活自理能力表（ADL)评估
 */
public class EverydayStateActivity extends Activity implements OnClickListener {

	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	
	@ViewInject(R.id.back_everydayState_imageView)
	private ImageView backEverydayStateImageView;
	/**
	 * 进餐
	 * */
	@ViewInject(R.id.jc_editText)
	private EditText jcEditText;
	/**
	 * 洗澡
	 * */
	@ViewInject(R.id.xz_editText)
	private EditText xzEditText;
	/**
	 * 修饰
	 * */
	@ViewInject(R.id.zs_editText)
	private EditText zsEditText;
	/**
	 * 穿衣
	 * */
	@ViewInject(R.id.cy_editText)
	private EditText cyEditText;
	/**
	 * 大便
	 * */
	@ViewInject(R.id.db_editText)
	private EditText dbEditText;
	/**
	 * 小便
	 * */
	@ViewInject(R.id.xb_editText)
	private EditText xbEditText;
	/**
	 * 用厕
	 * */
	@ViewInject(R.id.yc_editText)
	private EditText ycEditText;
	/**
	 * 床椅转移
	 * */
	@ViewInject(R.id.qyzy_editText)
	private EditText qyzyEditText;
	/**
	 * 平地走
	 * */
	@ViewInject(R.id.pdz_editText)
	private EditText pdzEditText;
	/**
	 * 上下楼梯
	 * */
	@ViewInject(R.id.sxlt_editText)
	private EditText sxltEditText;
	/**
	 * 评估结果
	 * */
	@ViewInject(R.id.result_textView)
	private TextView resultTextView;
	/**
	 * 注释
	 * */
	@ViewInject(R.id.note_editText)
	private EditText noteEditText;
	/**
	 * 计算总评分
	 * */
	@ViewInject(R.id.totalPoints_textView)
	private TextView totalPointsTextView;
	/**
	 * 提交按钮
	 */
	@ViewInject(R.id.submit_everyday_button)
	private Button submitEverydayButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.save_everyday_button)
	private Button saveEverydayButton;
	
	private String jc = "";
	private String xz = "";
	private String zs = "";
	private String cy = "";
	private String db = "";
	private String xb = "";
	private String yc = "";
	private String qyzy = "";
	private String pdz = "";
	private String sxlt = "";
	private String assess = "";
	private String result = "";
	private String note = "";

	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				EverydayState everydayStateDB = (EverydayState) msg.obj;
				initData(everydayStateDB);
				break;
			case INITDATANET:// 回写网络
				EverydayState everydayStateNet = (EverydayState) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayStateActivity.this);
				pinetreeDB.deleteEverydayStateByScheID(customer.getId());
				everydayStateNet.setScheID(customer.getId());
				pinetreeDB.saveEverydayStateByScheID(everydayStateNet);
				initData(everydayStateNet);
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
		setContentView(R.layout.activity_everyday_state);

		ViewUtils.inject(this);

		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		
		dialog = new Dialog(EverydayStateActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		
		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus())) {
			submitEverydayButton.setVisibility(View.GONE);
			saveEverydayButton.setVisibility(View.GONE);
		}
		
		
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayStateActivity.this);
		if (!"1".equals(customer.getProdType())) {
			//不为评估产品时
			submitEverydayButton.setVisibility(View.GONE);
			saveEverydayButton.setVisibility(View.GONE);
		}
		if (NetUtil.checkNetWork(EverydayStateActivity.this)) {
			dialog.show();
			RequestParams params = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("assType", "1");
			params.addBodyParameter("prodType", customer.getProdType());
			HttpUtil.post("witStatusAndDailyLifeReportView.action", params, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					dialog.hide();
					EverydayStateBase everydayStateBase = 	GsonUtils.json2bean(responseInfo.result, EverydayStateBase.class);
					EverydayState everydayStateDB = pinetreeDB.getEverydayStateByScheID(customer.getId());
					Message message = Message.obtain();
					if (!"".equals(everydayStateBase.getSuccess()) && Boolean.valueOf(everydayStateBase.getSuccess())) {
						if (!"".equals(everydayStateDB) && null != everydayStateDB) {
							if (!"".equals(everydayStateDB.getCreateTime()) && !"".equals(everydayStateBase.getResultData().get(0).getCreateTime())) {
								
								try {
									String createTimeDB = everydayStateDB.getCreateTime();
									String createTimeNet = everydayStateBase.getResultData().get(0).getCreateTime();
									String timeDB = createTimeDB.substring(0, 10)+ " "+ createTimeDB.substring(11, 19);
									String timeNet = createTimeNet.substring(0, 10)+ " "+ createTimeNet.substring(11, 19);
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date dbDate = format.parse(timeDB);
									Date netDate = format.parse(timeNet);
									if (dbDate.compareTo(netDate) > 0) {
										message.obj = everydayStateDB;
										message.what = INITDATADB;
										handler.sendMessage(message);
									} else {
										message.obj = everydayStateBase
												.getResultData().get(0);
										message.what = INITDATANET;
										handler.sendMessage(message);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}							
								
							}else if ("".equals(everydayStateDB.getCreateTime())) {
								message.obj = everydayStateBase
										.getResultData().get(0);
								message.what = INITDATANET;
								handler.sendMessage(message);
							}else if ("".equals(everydayStateBase.getResultData().get(0).getCreateTime())) {
								message.obj = everydayStateDB;
								message.what = INITDATADB;
								handler.sendMessage(message);
							}
																												
						}else {
							message.obj = everydayStateBase
									.getResultData().get(0);
							message.what = INITDATANET;
							handler.sendMessage(message);
						}
																																								
					}else {
						if (!"".equals(everydayStateDB)
								&& null != everydayStateDB) {
							message.obj = everydayStateDB;
							message.what = INITDATADB;
							initData(everydayStateDB);
						}
					}
																																																						
				}
				
				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
					
				}
			});

		}else {
			EverydayState everydayState = pinetreeDB.getEverydayStateByScheID(customer.getId());
			if (everydayState != null &&  ! "".equals(everydayState)) {
				initData(everydayState);
			}
		}
								
		backEverydayStateImageView.setOnClickListener(this);
		submitEverydayButton.setOnClickListener(this);
		saveEverydayButton.setOnClickListener(this);
		
		jcEditText.addTextChangedListener(new MyTextWatcher(jcEditText));
		xzEditText.addTextChangedListener(new MyTextWatcher(xzEditText));
		zsEditText.addTextChangedListener(new MyTextWatcher(zsEditText));
		cyEditText.addTextChangedListener(new MyTextWatcher(cyEditText));
		dbEditText.addTextChangedListener(new MyTextWatcher(dbEditText));
		xbEditText.addTextChangedListener(new MyTextWatcher(xbEditText));
		ycEditText.addTextChangedListener(new MyTextWatcher(ycEditText));
		qyzyEditText.addTextChangedListener(new MyTextWatcher(qyzyEditText));
		pdzEditText.addTextChangedListener(new MyTextWatcher(pdzEditText));
		sxltEditText.addTextChangedListener(new MyTextWatcher(sxltEditText));

		
	}
	
	/**
	 * 获取元素的值
	 * */
	public void gainValue() {
		jc = jcEditText.getText().toString().trim();// 进餐得分
		xz = xzEditText.getText().toString().trim();// 洗澡得分
		zs = zsEditText.getText().toString().trim();// 装饰得分
		cy = cyEditText.getText().toString().trim();// 穿衣得分
		db = dbEditText.getText().toString().trim();// 大便得分
		xb = xbEditText.getText().toString().trim();// 小便得分
		yc = ycEditText.getText().toString().trim();// 用厕得分
		qyzy = qyzyEditText.getText().toString().trim();// 床椅转移得分
		pdz = pdzEditText.getText().toString().trim();// 平地走得分
		sxlt = sxltEditText.getText().toString().trim();// 上下楼梯得分
		assess = totalPointsTextView.getText().toString().trim();//总分
		result = resultTextView.getText().toString().trim();//评估结果
		note = noteEditText.getText().toString().trim();//注释
		
	}

	/** 
	 * 数据回显
	 */
	private void initData(EverydayState everydayState){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayStateActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getEverydaySubmitState() && "1".equals(recordSubmitState2.getEverydaySubmitState())) {
				submitEverydayButton.setVisibility(View.GONE);
				saveEverydayButton.setVisibility(View.GONE);
			}
		}
		
		if (!"".equals(everydayState) && null != everydayState) {
			jcEditText.setText(everydayState.getJc());
			xzEditText.setText(everydayState.getXz());
			zsEditText.setText(everydayState.getZs());
			cyEditText.setText(everydayState.getCy());
			dbEditText.setText(everydayState.getDb());
			xbEditText.setText(everydayState.getXb());
			ycEditText.setText(everydayState.getYc());
			qyzyEditText.setText(everydayState.getQyzy());
			pdzEditText.setText(everydayState.getPdz());
			sxltEditText.setText(everydayState.getSxlt());
			resultTextView.setText(everydayState.getResult());
			noteEditText.setText(everydayState.getNote());
			totalPointsTextView.setText(everydayState.getAssess());		
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_everydayState_imageView:
			finish();
			break;
		case R.id.submit_everyday_button:
			dialog.show();
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayStateActivity.this);
			RecordSubmitState recordSubmitStateSubmit = pinetreeDB.getRecordSubmitState(customer.getId());
			if (null != recordSubmitStateSubmit &&  !"".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBasicSubmitState())){
				if (NetUtil.checkNetWork(EverydayStateActivity.this)) {
					gainValue();
					RequestParams params = new RequestParams();
					params.addBodyParameter("scheID", customer.getId());			
					params.addBodyParameter("custID",customer.getCustID());
					params.addBodyParameter("empID",employeeId);
					params.addBodyParameter("custAssessDetail.jc", jc);
					params.addBodyParameter("custAssessDetail.xz", xz);
					params.addBodyParameter("custAssessDetail.zs", zs);
					params.addBodyParameter("custAssessDetail.cy", cy);
					params.addBodyParameter("custAssessDetail.db", db);
					params.addBodyParameter("custAssessDetail.xb", xb);
					params.addBodyParameter("custAssessDetail.yc", yc);
					params.addBodyParameter("custAssessDetail.qyzy", qyzy);
					params.addBodyParameter("custAssessDetail.pdz", pdz);
					params.addBodyParameter("custAssessDetail.sxlt", sxlt);
					params.addBodyParameter("custAssessResult.assess", assess);
					params.addBodyParameter("custAssessResult.result", result);
					params.addBodyParameter("custAssessResult.note", note);
					params.addBodyParameter("assType", "1");
					
					if (null!=recordSubmitStateSubmit&&!"".equals(recordSubmitStateSubmit)) {
						if ("1".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBrainSubmitState()) && "1".equals(recordSubmitStateSubmit.getDrugSubmitState()) && "1".equals(recordSubmitStateSubmit.getSportSubmitState()) && "1".equals(recordSubmitStateSubmit.getCopmSubmitState())) {
							params.addBodyParameter("custCustinfoDetail.status", "1");
						}else {
							params.addBodyParameter("custCustinfoDetail.status", "0");
						}
					}else {
						params.addBodyParameter("custCustinfoDetail.status", "0");
					}
					
					HttpUtil.post("dailyLifeReportSave.action", params, new RequestCallBack<String>() {
						
						@Override  
						public void onSuccess(ResponseInfo<String> responseInfo) {
							
							GlobalResult globalResult = GsonUtils.json2bean(responseInfo.result, GlobalResult.class);
							if (!"".equals(globalResult.getSuccess())&&Boolean.valueOf(globalResult.getSuccess())) {
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(EverydayStateActivity.this);
								pinetreeDB.deleteEverydayStateByScheID(customer.getId());
								EverydayState everydayState = setData();
								pinetreeDB.saveEverydayStateByScheID(everydayState);
								
								if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
									pinetreeDB.updateRecordSubmitStateEveryday(customer.getId());
								}else {
									RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
									recordSubmitStateDB.setScheID(customer.getId());
									recordSubmitStateDB.setEverydaySubmitState("1");
									pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
								} 
								
								ToastUtils.showToast(EverydayStateActivity.this, "提交成功");
								submitEverydayButton.setVisibility(View.GONE);
								saveEverydayButton.setVisibility(View.GONE);
								
								dialog.hide();
								new Handler().postDelayed(
										new Runnable() {
											@Override
											public void run() {
												finish();
											}
										}, 1000);
							}else {
								clearVariable();
								dialog.hide();
								ToastUtils.showToast(EverydayStateActivity.this, "提交失败，请重试！");
							}
							
						}
						
						@Override
						public void onFailure(HttpException error, String msg) {
							clearVariable();
							ToastUtils.showToast(EverydayStateActivity.this, "提交失败，请重试！");
							dialog.hide();						
						}
					});
								
				}else {
					dialog.hide();
					ToastUtils.showToast(EverydayStateActivity.this, "亲，没有网络哦");
				}
			}else {
				dialog.hide();	
				ToastUtils.showToast(EverydayStateActivity.this, "亲，请先填写并提交基本情况表");
			}
			break;
		case R.id.save_everyday_button://将填写的日常生活自理能力表（ADL)评估保存到本地
			PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(EverydayStateActivity.this);
			pinetreeDB1.deleteEverydayStateByScheID(customer.getId());
			gainValue();
			EverydayState everydayState = setData();
			boolean b = pinetreeDB1.saveEverydayStateByScheID(everydayState);
			if (b) {
				ToastUtils.showToast(EverydayStateActivity.this, "保存成功");
			}else {
				ToastUtils.showToast(EverydayStateActivity.this, "保存失败，请重试");
			}
			break;
		default:
			break;
		}
	}

	private EverydayState setData() {
		EverydayState everydayState = new EverydayState();
		everydayState.setCustID(customer.getCustID());
		everydayState.setScheID(customer.getId());
		everydayState.setJc(jc);
		everydayState.setXz(xz);
		everydayState.setZs(zs);
		everydayState.setCy(cy);
		everydayState.setDb(db);
		everydayState.setXb(xb);
		everydayState.setYc(yc);
		everydayState.setQyzy(qyzy);
		everydayState.setPdz(pdz);
		everydayState.setSxlt(sxlt);
		everydayState.setAssess(assess);
		everydayState.setResult(result);
		everydayState.setNote(note);
		// 获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		everydayState.setCreateTime(createDate + "T" + createTime);
		return everydayState;
	}

	public class MyTextWatcher implements TextWatcher {

		private EditText etID = null;

		public MyTextWatcher(EditText id) {
			etID = id;
		}

		@Override
		public void afterTextChanged(Editable arg0) {
			DefineConstant de = new DefineConstant();
			if (arg0 != null) {
				if (etID == jcEditText) {
					de.checkout(arg0.toString(), 0, 10, jcEditText);
					de.total();
				} else if (etID == xzEditText) {
					de.checkout(arg0.toString(), 0, 5, xzEditText);
					de.total();
				} else if (etID == zsEditText) {
					de.checkout(arg0.toString(), 0, 5, zsEditText);
					de.total();
				} else if (etID == cyEditText) {
					de.checkout(arg0.toString(), 0, 10, cyEditText);
					de.total();
				} else if (etID == dbEditText) {
					de.checkout(arg0.toString(), 0, 10, dbEditText);
					de.total();
				} else if (etID == xbEditText) {
					de.checkout(arg0.toString(), 0, 10, xbEditText);
					de.total();
				} else if (etID == ycEditText) {
					de.checkout(arg0.toString(), 0, 10, ycEditText);
					de.total();
				} else if (etID == qyzyEditText) {
					de.checkout(arg0.toString(), 0, 15, qyzyEditText);
					de.total();
				} else if (etID == pdzEditText) {
					de.checkout(arg0.toString(), 0, 15, pdzEditText);
					de.total();
				} else if (etID == sxltEditText) {
					de.checkout(arg0.toString(), 0, 10, sxltEditText);
					de.total();
				}

			}

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

	}

	public class DefineConstant {

		// 校验输入框的分数是否符合规则
		public void checkout(String str, int min, int max,
				EditText et) {
			if (null!=str && !"".equals(str)) {
				int number = Integer.parseInt(str);
				if (number >= min && number <= max) {

				} else {
					et.setText("");
				}
			} 
		}

		public void total() {
			gainValue();		
			int total = 0;
			if (!"".equals(jc)) {
				total += Integer.valueOf(jc);
			}
			if (!"".equals(xz)) {
				total += Integer.valueOf(xz);
			}
			if (!"".equals(zs)) {
				total += Integer.valueOf(zs);
			}
			if (!"".equals(cy)) {
				total += Integer.valueOf(cy);
			}
			if (!"".equals(db)) {
				total += Integer.valueOf(db);
			}
			if (!"".equals(xb)) {
				total += Integer.valueOf(xb);
			}
			if (!"".equals(yc)) {
				total += Integer.valueOf(yc);
			}
			if (!"".equals(qyzy)) {
				total += Integer.valueOf(qyzy);
			}
			if (!"".equals(pdz)) {
				total += Integer.valueOf(pdz);
			}
			if (!"".equals(sxlt)) {
				total += Integer.valueOf(sxlt);
			}

			totalPointsTextView.setText(total + "");

			if (!"".equals(total)) {
				if (total >= 0 && total <= 20) {
					resultTextView.setText("完全依赖");
				} else if (total >= 25 && total <= 45) {
					resultTextView.setText("重度依赖");
				} else if (total >= 50 && total <= 70) {
					resultTextView.setText("中度依赖");
				} else if (total >= 75 && total <= 95) {
					resultTextView.setText("轻度依赖");
				} else if (total == 100) {
					resultTextView.setText("独立");
				}else {
					resultTextView.setText("");
				}
			}

		}

	}
	
	/**
	 * 清空变量
	 */
	public void clearVariable(){
		jc = "";
		xz = "";
		zs = "";
		cy = "";
		db = "";
		xb = "";
		yc = "";
		qyzy = "";
		pdz = "";
		sxlt = "";
		assess = "";
		result = "";
		note = "";
	}
}
