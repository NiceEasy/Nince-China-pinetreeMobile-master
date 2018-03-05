package com.pinetree.mobile.activity;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.BrainStateActivity.editTextCheckoutListener;
import com.pinetree.mobile.bean.BasicInformation;
import com.pinetree.mobile.bean.BasicInformationBase;
import com.pinetree.mobile.bean.Copm;
import com.pinetree.mobile.bean.CopmBase;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.PlanSubList;
import com.pinetree.mobile.bean.RecordSubmitState;
import com.pinetree.mobile.bean.ServerRecord;
import com.pinetree.mobile.bean.ServerRecordBase;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.FilesUtils;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CopmActivity extends Activity implements OnClickListener{

	/**
	 * 顶部返回按钮图片
	 */
	@ViewInject(R.id.copm_imageView)
	private ImageView copmImageView;
	/**
	 * 现状评分
	 */
	@ViewInject(R.id.xian_point_textView)
	private TextView xianPointTextView;
	/**
	 * 满意度评分
	 */
	@ViewInject(R.id.man_point_textView)
	private TextView manPointTextView;
	/**
	 * 作业活动表现变化
	 */
	@ViewInject(R.id.zuoye_change_textView)
	private TextView zuoyeChangeTextView;
	/**
	 * 满意度变化
	 */
	@ViewInject(R.id.man_change_textView)
	private TextView manChangeTextView;
	/**
	 * 历史LinearLayout
	 */
	@ViewInject(R.id.copm_history_linearLayout)
	private LinearLayout copmHistoryLinearLayout;
	/**
	 * 评估师特殊交代
	 */
	@ViewInject(R.id.specialAccount_editText)
	private EditText specialAccountEditText;
	/**
	 * 提交按钮
	 */
	@ViewInject(R.id.copm_submit_button)
	private Button copmSubmitButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.copm_save_button)
	private Button copmSaveButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.copm_add_button)
	private Button copmAddButton;
	
	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;
	
	private ViewGroup.LayoutParams lp_ViewGroup3 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	private ViewGroup.LayoutParams lp_ViewGroup4 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	private ArrayList<Copm> list = new ArrayList<Copm>();
	private List<String> listAdd = new ArrayList<String>();
	private ArrayList<Copm> custAppealList = new ArrayList<Copm>();
	private ArrayList<Copm> copmlist = new ArrayList<Copm>();
	private ArrayList<Copm> specialList = new ArrayList<Copm>();//目前需求没有时，只有评估师特殊要求
	private ArrayList<String> deleteList = new ArrayList<String>();
	private ArrayList<String> historySatisfList = new ArrayList<String>();//历史满意度
	private ArrayList<String> historySituationList = new ArrayList<String>();//历史现状
	private ArrayList<String> satisfList = new ArrayList<String>();//新增满意度
	private ArrayList<String> situationList = new ArrayList<String>();//新增现状
	
	private String isLast = "";
	private float xianPointStr = 0;//现状评分
	private float manPointStr = 0;//满意度评分
	private float zuoyeChangeStr = 0;//作业活动表现变化
	private float manChangeStr = 0;//满意度变化
	private String custAppealStr = "";
	private String situationTotal = "";//现状总分
	private String satisfTotal= "";//满意度总分
	private String situationHistoryTotal = "0";//历史总分
	private String satisfHistoryTotal= "0";//历史满意度总分
	private String specialAccount = "";//评估师特殊交代
	
	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				copmlist.clear();
				copmlist =  (ArrayList<Copm>) msg.obj;
				initData(copmlist);
				break;
			case INITDATANET:// 回写网络
				Copm copmNet = (Copm) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(CopmActivity.this);
				pinetreeDB.deleteCopmByScheID(customer.getId());
				copmNet.setScheID(customer.getId());
				copmNet.setCustID(customer.getCustID());
				break;
			default:
				break;
			}
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_copm);
		
		ViewUtils.inject(this);
		
		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		
		dialog = new Dialog(CopmActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		
		//判断 如果已经提交过 不可再提交
		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus())) {
			copmSaveButton.setVisibility(View.GONE);
			copmSubmitButton.setVisibility(View.GONE);
		}
		
		copmImageView.setOnClickListener(this);
		copmAddButton.setOnClickListener(this);
		copmSaveButton.setOnClickListener(this);
		copmSubmitButton.setOnClickListener(this);
		
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(CopmActivity.this);
		if (!"1".equals(customer.getProdType())) {
			//不为评估产品时
			copmSubmitButton.setVisibility(View.GONE);
			copmSaveButton.setVisibility(View.GONE);
		}
		if (NetUtil.checkNetWork(CopmActivity.this)) {
			dialog.show();
			RequestParams params = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("prodType", customer.getProdType());
			HttpUtil.post("copmAddView.action", params, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					dialog.hide();
					CopmBase copmBase = GsonUtils.json2bean(responseInfo.result, CopmBase.class);
					ArrayList<Copm> copmList1 = new ArrayList<Copm>();
					dialog.hide();
					if (!"".equals(copmBase.getSuccess()) && Boolean.valueOf(copmBase.getSuccess())) {
						List<Copm> copmNetList = pinetreeDB.getCopmByScheID(customer.getId());
						if (copmNetList.size() > 0 && !"1".equals(copmNetList.get(copmNetList.size()-1).getSubmitState())) {
							copmList1.addAll(copmNetList);
							if (!"".equals(copmNetList.get(0).getSituationSum()) && null != copmNetList.get(0).getSituationSum()) {
								situationHistoryTotal = copmNetList.get(0).getSituationSum();
							}
							if (!"".equals(copmNetList.get(0).getSatisfSum()) && null != copmNetList.get(0).getSatisfSum()) {
								satisfHistoryTotal = copmNetList.get(0).getSatisfSum();
							}
						}else {
							if (copmBase.getResultData().size() > 0  && null !=  copmBase.getResultData()) {
								copmList1.addAll(copmBase.getResultData());
								if (!"".equals(copmBase.getResultData().get(0).getSituationSum()) && null != copmBase.getResultData().get(0).getSituationSum()) {
									situationHistoryTotal = copmBase.getResultData().get(0).getSituationSum();
								}
								if (!"".equals(copmBase.getResultData().get(0).getSatisfSum()) && null != copmBase.getResultData().get(0).getSatisfSum()) {
									satisfHistoryTotal = copmBase.getResultData().get(0).getSatisfSum();
								}
							}
						}
						
						
						if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "0".equals(customer.getReportStatus())) {
							List<Copm> copmDbList = PinetreeDB.getInstance(CopmActivity.this).getCopmAddByScheID(customer.getId());
							if (null!=copmDbList && copmDbList.size()>0) {
								if (!"1".equals(copmDbList.get(copmDbList.size()-1).getSubmitState())) {
									copmList1.addAll(copmDbList);
									for (int i = 0; i < copmDbList.size(); i++) {
										listAdd.add(i+"");
									}
								}
							}
						}
						Message message = Message.obtain();
						message.obj = copmList1;
						message.what = INITDATADB;
						handler.sendMessage(message);
					}else {
						List<Copm> copmDb = PinetreeDB.getInstance(CopmActivity.this).getCopmAddByScheID(customer.getId());
						if (null!=copmDb && copmDb.size()>0) {
							copmList1.addAll(copmDb);
							situationHistoryTotal = "0";
							satisfHistoryTotal = "0";
							for (int i = 0; i < copmDb.size(); i++) {
								listAdd.add(i+"");
							}
						}
						Message message = Message.obtain();
						message.obj = copmList1;
						message.what = INITDATADB;
						handler.sendMessage(message);
					}
				}
						
				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
					
				}
			});		
			
		}else {
			dialog.hide();
			ArrayList<Copm> copmList2 = new ArrayList<Copm>();
			List<Copm> copmList3 = PinetreeDB.getInstance(CopmActivity.this).getCopmByScheID(customer.getId());//添加从网络获取的数据
			List<Copm> copmList4 = PinetreeDB.getInstance(CopmActivity.this).getCopmAddByScheID(customer.getId());//添加本地添加的数据
			if (null!=copmList3 && copmList3.size()>0) {
				copmList2.addAll(copmList3);
				if (!"".equals(copmList3.get(0).getSituationSum()) && null != copmList3.get(0).getSituationSum()) {
					situationHistoryTotal = copmList3.get(0).getSituationSum();
				}
				if (!"".equals(copmList3.get(0).getSatisfSum()) && null != copmList3.get(0).getSatisfSum()) {
					satisfHistoryTotal = copmList3.get(0).getSatisfSum();
				}
			}else {
				situationHistoryTotal = "0";
				satisfHistoryTotal = "0";
			}
			if (null!=copmList4 && copmList4.size()>0) {
				copmList2.addAll(copmList4);
				for (int i = 0; i < copmList4.size(); i++) {
					listAdd.add(i+"");
				}
			}
			Message message = Message.obtain();
			message.obj = copmList2;
			message.what = INITDATADB;
			handler.sendMessage(message);
		}  
						
	}
	
	
	
	private void initData(ArrayList<Copm> copmList) {
		
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(CopmActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getCopmSubmitState() && "1".equals(recordSubmitState2.getCopmSubmitState())) {
				copmSaveButton.setVisibility(View.GONE);
				copmSubmitButton.setVisibility(View.GONE);
			}
		}
		if (copmList.size() > 0) {
			specialAccount = copmList.get(0).getSpecialAccount();
			specialAccountEditText.setText(specialAccount);
			
			 LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
		     LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		     params2.weight = 1;
		     LayoutParams params3 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		     params3.weight = (float) 2.5;
		     LayoutParams params4 = new LayoutParams(2, LayoutParams.MATCH_PARENT);
		     LayoutParams params5 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		     params5.weight = 1;
		     LayoutParams params6 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		     params6.weight = 1;
		     
		     
		     list.addAll(copmList);
		     int contentNumber = list.size();
		     if (contentNumber > 0) {
				for (int i = 0; i < list.size(); i++) {
					
					if (!"".equals(list.get(i).getContent()) && null != list.get(i).getContent()) {
						 LinearLayout linearLayout1 = new LinearLayout(this);
					     linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
					     linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup3));
					     linearLayout1.setGravity(Gravity.CENTER);
					   
					     Button  deleteBtn = new Button(this);
					    
					     if (!"".equals(list.get(i).getIsLast()) && null != list.get(i).getIsLast()) {
					    	 if ("0".equals(list.get(i).getIsLast())) {
						    	 TextView  tv = new TextView(this);
							     tv.setText("");
							     tv.setLayoutParams(params2);
							     tv.setGravity(Gravity.CENTER);	
							     tv.setId(i);
							     linearLayout1.addView(tv);
							}else {
							     deleteBtn.setText("删除");
							     deleteBtn.setLayoutParams(params2);
							     deleteBtn.setGravity(Gravity.CENTER);	
							     deleteBtn.setId(i);
							     linearLayout1.addView(deleteBtn);
							}
						}else {
						     deleteBtn.setText("删除");
						     deleteBtn.setLayoutParams(params2);
						     deleteBtn.setGravity(Gravity.CENTER);	
						     deleteBtn.setId(i);
						     linearLayout1.addView(deleteBtn);
						}
					    
					     LinearLayout lin1 = new LinearLayout(this);				     
					     lin1.setLayoutParams(params4);
					     lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
				         linearLayout1.addView(lin1);
				         	
				    	 if ("0".equals(list.get(i).getIsLast())) {
				    		 TextView  tv1 = new TextView(this);
						     tv1.setText(list.get(i).getContent());
						     tv1.setLayoutParams(params3);
						     tv1.setGravity(Gravity.CENTER);	
						     tv1.setId(4*i + 1);
						     historySatisfList.add(list.get(i).getSatisf());
						     historySituationList.add(list.get(i).getSituation());
						     linearLayout1.addView(tv1);
						}else if("1".equals(list.get(i).getIsLast())){
							EditText et = new EditText(this);
					         et.setLayoutParams(params3);
					    	 et.setText(list.get(i).getContent());
					    	 et.setGravity(Gravity.CENTER);
					    	 et.setId(4*i + 1);
					    	 satisfList.add(list.get(i).getSatisf());
					    	 situationList.add(list.get(i).getSituation());
					    	 linearLayout1.addView(et);
						}
				    	 LinearLayout lin2 = new LinearLayout(this);
					     lin2.setLayoutParams(params4);
					     lin2.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
				         linearLayout1.addView(lin2);
				         
				         EditText et1 = new EditText(this);
				         if (!"".equals(list.get(i).getContent()) && null != list.get(i).getContent()) {
				        	 et1.setText(list.get(i).getSituation());
						}else {
							et1.setText("");
						}
				    	 et1.setLayoutParams(params5);
				    	 et1.setGravity(Gravity.CENTER);	
				    	 et1.setId(4*i + 2);
				    	 et1.setInputType(InputType.TYPE_CLASS_NUMBER);
				    	 setEditTextListener(et1);
				    	 linearLayout1.addView(et1);
				    	 LinearLayout lin3 = new LinearLayout(this);
					     lin3.setLayoutParams(params4);
					     lin3.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
				         linearLayout1.addView(lin3);
				         
				         EditText et2 = new EditText(this);
				         if (!"".equals(list.get(i).getContent()) && null != list.get(i).getContent()) {
				        	 et2.setText(list.get(i).getSatisf());
						}else {
							et2.setText("");
						}
				    	 et2.setLayoutParams(params2);
				    	 et2.setGravity(Gravity.CENTER);
				    	 et2.setId(4*i + 3);
				    	 et2.setInputType(InputType.TYPE_CLASS_NUMBER);
				    	 setEditTextListener(et2);
				    	 linearLayout1.addView(et2);
				    	 
				    	 LinearLayout lin4 = new LinearLayout(this);
					     lin4.setLayoutParams(params1);
					     lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
				         setButtonListener(deleteBtn, copmHistoryLinearLayout, linearLayout1,lin4,i);
				         copmHistoryLinearLayout.addView(linearLayout1);   
				         copmHistoryLinearLayout.addView(lin4);	
				         
					}			
				}
				float manPoint1 = 0;
				float xianPoint1 = 0;
				float manChange1 = 0;
				float zuoyeChange1 = 0;
				float manHistoryTotal1 = 0;
				float xianHistoryTotal1 = 0;
				float manTotal1 = 0;
				float xianTotal1 = 0;
				if (historySatisfList.size() > 0) {
					for (int j = 0; j < historySatisfList.size(); j++) {
						manHistoryTotal1 += Float.parseFloat(historySatisfList.get(j)) ;
					}
				}
				if (historySituationList.size() > 0) {
					for (int z = 0; z < historySituationList.size(); z++) {
						xianHistoryTotal1 += Float.parseFloat(historySituationList.get(z));
					}
				}
				if (satisfList.size() > 0) {
					for (int j = 0; j < satisfList.size(); j++) {
						manTotal1 += Float.parseFloat(satisfList.get(j)); 
					}
				}
				if (situationList.size() > 0) {
					for (int z = 0; z < situationList.size(); z++) {
						xianTotal1 += Float.parseFloat(situationList.get(z));
					}
				}
				manChangeTextView.setText((float)(Math.round((manHistoryTotal1-Float.parseFloat(satisfHistoryTotal))/historySatisfList.size()*100))/100 + "");
				manPointTextView.setText((float)(Math.round((manHistoryTotal1 + manTotal1)/(satisfList.size() + historySatisfList.size())*100))/100 + "");
				xianPointTextView.setText((float)(Math.round((xianTotal1 + xianHistoryTotal1)/(situationList.size() + historySituationList.size())*100))/100 + "");
				zuoyeChangeTextView.setText((float)(Math.round((xianHistoryTotal1-Float.parseFloat(situationHistoryTotal))/historySituationList.size()*100))/100 + "");
				
			}
		}	
	}
	
	//Button监听
	private void setButtonListener(Button btn,final LinearLayout mainLin,final LinearLayout lin,final LinearLayout lin1,final int i){
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mainLin.removeView(lin);
				mainLin.removeView(lin1);
				if (i < list.size()) {
					deleteList.add(i + "");
				}else {
					listAdd.remove(0);
				}
				
			}
		});
	}
	
	//EditText监听
	private void setEditTextListener(EditText et){
		
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				getElementData();
				float satisfHistoryTotal1 = 0;
				float situationHistoryTotal1 = 0; 
				int satisfAddTotal = 0;
				int situationAddTotal = 0; 
				if (historySatisfList.size() > 0) {
					for (int i = 0; i < historySatisfList.size(); i++) {
						satisfHistoryTotal1 += Integer.parseInt(historySatisfList.get(i));
					}
					manChangeStr = (float)(Math.round((satisfHistoryTotal1-Float.parseFloat(satisfHistoryTotal))/historySatisfList.size()*100))/100;
				}
				manChangeTextView.setText(manChangeStr + "");
				
				if (historySituationList.size() > 0) {
					for (int i = 0; i < historySituationList.size(); i++) {
						situationHistoryTotal1 += Integer.parseInt(historySituationList.get(i));
					}
					zuoyeChangeStr = (float)(Math.round((situationHistoryTotal1-Float.parseFloat(situationHistoryTotal))/historySituationList.size()*100))/100;
				}
				zuoyeChangeTextView.setText(zuoyeChangeStr + "");
				
				if (satisfList.size() > 0) {
					for (int i = 0; i < satisfList.size(); i++) {
						satisfAddTotal += Integer.parseInt(satisfList.get(i));
					}
					manPointStr = (float)(Math.round((satisfAddTotal + satisfHistoryTotal1)/(satisfList.size()+ historySatisfList.size())*100))/100;
				}
				manPointTextView.setText(manPointStr+ "");
				
				if (situationList.size() > 0) {
					for (int i = 0; i < situationList.size(); i++) {
						situationAddTotal += Integer.parseInt(situationList.get(i));
					}
					xianPointStr = (float)(Math.round((situationAddTotal + situationHistoryTotal1)/(situationList.size() + historySituationList.size())*100))/100;
				}
				xianPointTextView.setText(xianPointStr + "");
				
			}
		});
	}
	
	private void createAddContent(){
		 LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
	     LayoutParams params2 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params2.weight = 1;
	     LayoutParams params3 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params3.weight = (float) 2.5;
	     LayoutParams params4 = new LayoutParams(2, LayoutParams.MATCH_PARENT);
	     LayoutParams params5 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params5.weight = 1;
	     LayoutParams params6 = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
	     params6.weight = 1;
	     
	     int contentNumber = list.size();
	     LinearLayout linearLayout1 = new LinearLayout(this);
	     linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
	     linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(lp_ViewGroup3));
	     linearLayout1.setGravity(Gravity.CENTER);
			     
	     Button  deleteBtn = new Button(this);
	     deleteBtn.setText("删除");
	     deleteBtn.setLayoutParams(params2);
	     deleteBtn.setGravity(Gravity.CENTER);	
	     deleteBtn.setId(4*contentNumber);
	     linearLayout1.addView(deleteBtn);
	    
	     
	     LinearLayout lin1 = new LinearLayout(this);				     
	     lin1.setLayoutParams(params4);
	     lin1.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
	     linearLayout1.addView(lin1);
		         			         
	     EditText et = new EditText(this);
	     et.setLayoutParams(params3);
	     et.setText("");	
	     et.setGravity(Gravity.CENTER);
	     et.setId(4*contentNumber+1);
	     linearLayout1.addView(et);
	     LinearLayout lin2 = new LinearLayout(this);
	     lin2.setLayoutParams(params4);
	     lin2.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
	     linearLayout1.addView(lin2);
		         
	     EditText et1 = new EditText(this);
	     et1.setText("0");
	     et1.setLayoutParams(params5);
	     et1.setGravity(Gravity.CENTER);	
	     et1.setId(4*contentNumber + 2);
	     et1.setInputType(InputType.TYPE_CLASS_NUMBER);
	     setEditTextListener(et1);
	     linearLayout1.addView(et1);
	     LinearLayout lin3 = new LinearLayout(this);
	     lin3.setLayoutParams(params4);
	     lin3.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
	     linearLayout1.addView(lin3);
		         
	     EditText et2 = new EditText(this);
	     et2.setText("0");
	     et2.setLayoutParams(params2);
	     et2.setGravity(Gravity.CENTER);
	     et2.setId(4*contentNumber + 3);
	     et2.setInputType(InputType.TYPE_CLASS_NUMBER);
	     setEditTextListener(et2);
	     linearLayout1.addView(et2);
		    	 
	     LinearLayout lin4 = new LinearLayout(this);
	     lin4.setLayoutParams(params1);
	     lin4.setBackgroundColor(Color.parseColor("#e6e4e5"));						     
	     
	     copmHistoryLinearLayout.addView(linearLayout1);   
	     copmHistoryLinearLayout.addView(lin4);	
	     
	     Copm copm = new Copm();
	     copm.setIsLast("1");
	     list.add(copm);
	     listAdd.add(contentNumber + "");
	     setButtonListener(deleteBtn, copmHistoryLinearLayout, linearLayout1,lin4,contentNumber);
	     
	};
	/**
	 * 获取元素值
	 */
	private void getElementData() {
		historySatisfList.clear();
		historySituationList.clear();
		satisfList.clear();
		situationList.clear();
		custAppealStr = "";
		
		int contentAddNumber = listAdd.size()/4;
		int deleteListSize = deleteList.size();
		int contentNumber = list.size();
		if (contentNumber  != 0) {
			for (int i = 0; i < contentNumber  ; i++) {
				
				TextView tv;
				EditText et;
				String content = "";
				String situation = "";//现状评分
				String satisf = "";//满意度评分
				String isLast1  = "";//是否可更改状态
				Boolean b = false;
				if (deleteList.size() > 0) {
					for (int j = 0; j < deleteList.size(); j++) {
						if (i != Integer.parseInt(deleteList.get(j))) {
							b = true;
						}else {
							b = false;
						}
						if (b == false) {
							break;
						}
					}
				}else {
					b = true;
				}
				
				if (b == true) {
					if (!"".equals(list.get(i).getContent())) {
						if (!"".equals(list.get(i).getIsLast()) && null != list.get(i).getIsLast()) {
							if ("0".equals(list.get(i).getIsLast())) {
								tv = (TextView) findViewById(4*i+1);
								if (null != tv.getText().toString().trim() && !"".equals(tv.getText().toString().trim())) {
									content = tv.getText().toString().trim();
								}
							}else {
								et = (EditText) findViewById(4*i + 1);
								if (null != et.getText().toString().trim() && !"".equals(et.getText().toString().trim())) {
									content = et.getText().toString().trim();
								}
							}
						}
						
						EditText et1 = (EditText) findViewById(4*i + 2);
						EditText et2 = (EditText) findViewById(4*i + 3);
						
						if (null != et1.getText().toString().trim() && !"".equals(et1.getText().toString().trim())) {
							situation = et1.getText().toString().trim();
						}else   {
							situation = 0 + "";
						}
						
						if (null != et2.getText().toString().trim() && !"".equals(et2.getText().toString().trim())) {
							satisf = et2.getText().toString().trim();
						}else  {
							satisf = 0 + "";
						}
						if (!"".equals(list.get(i).getIsLast()) && null != list.get(i).getIsLast()) {
							isLast1 = list.get(i).getIsLast();
							if ("0".equals(isLast1)) {
								historySatisfList.add(satisf);
								historySituationList.add(situation);
							}else {
								satisfList.add(satisf);
								situationList.add(situation);
							}
						}else {
							isLast1 = "1";
							satisfList.add(satisf);
							situationList.add(situation);
						}
						if (!"".equals(content)) {
							custAppealStr += content + "^" + situation + "^" + satisf +"^" + isLast1 +"|";
						}
					}
				}
				
			}
			if (!"".equals(custAppealStr)) {
				custAppealStr = custAppealStr.substring(0, (custAppealStr.length()-1));
			}
			
		}
		if (!"".equals(specialAccountEditText.getText().toString())) {
			specialAccount = specialAccountEditText.getText().toString();
		}
	}

	private void setData(){
		custAppealList.clear();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		
		if (!"".equals(custAppealStr)) {
			if (custAppealStr.contains("|")) {
				String[] custAppealListArray =custAppealStr.split("\\|");
				for (int i = 0; i < custAppealListArray.length; i++) {
					String[] custAppealListArray1 = custAppealListArray[i].split("\\^");
					Copm copm = new Copm();
					copm.setCustID(customer.getCustID());
					copm.setScheID(customer.getId());
					copm.setContent(custAppealListArray1[0]);
					copm.setSituation(custAppealListArray1[1]);
					copm.setSatisf(custAppealListArray1[2]);
					copm.setIsLast(custAppealListArray1[3]);
					copm.setNumber(i+"");
					copm.setCreateTime(createDate + "T" + createTime);
					copm.setManPoint(manPointStr+"");
					copm.setXianPoint(xianPointStr+"");
					copm.setZuoyeChange(zuoyeChangeStr+"");
					copm.setManChange(manChangeStr+"");
					copm.setSatisfSum(satisfHistoryTotal);
					copm.setSituationSum(situationHistoryTotal);
					custAppealList.add(copm);
				}
			
			}else {
				Copm copm = new Copm();
				copm.setCustID(customer.getCustID());
				copm.setScheID(customer.getId());
				String[] custAppealListArray2 =custAppealStr.split("\\^");
				copm.setContent(custAppealListArray2[0]);
				copm.setSituation(custAppealListArray2[1]);
				copm.setSatisf(custAppealListArray2[2]);
				copm.setIsLast(custAppealListArray2[3]);
				copm.setNumber(1+"");
				copm.setCreateTime(createDate + "T" + createTime);
				copm.setManPoint(manPointStr+"");
				copm.setXianPoint(xianPointStr+"");
				copm.setZuoyeChange(zuoyeChangeStr+"");
				copm.setManChange(manChangeStr+"");
				custAppealList.add(copm);
			}
		}else {
			Copm copm = new Copm();
			copm.setCustID(customer.getCustID());
			copm.setScheID(customer.getId());
			copm.setNumber(1+"");
			copm.setCreateTime(createDate + "T" + createTime);
			copm.setSpecialAccount(specialAccount);
			specialList.add(copm);
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.copm_imageView:
			finish();
			break;
		case R.id.copm_save_button:
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(CopmActivity.this);
			pinetreeDB.deleteCopmByScheID(customer.getId());
			pinetreeDB.deleteCopmAddByScheID(customer.getId());
			getElementData();
			setData();
			boolean b = false;
			int listAddSize = listAdd.size();
			int custAppealListSize = custAppealList.size();
			if ((custAppealListSize-listAddSize) > 0) {
				for (int i = 0; i < custAppealListSize-listAddSize; i++) {
					Copm copm = custAppealList.get(i);
					copm.setSpecialAccount(specialAccount);
					pinetreeDB.saveCopm(copm);
				}
				for (int j = custAppealListSize-listAddSize; j < custAppealListSize; j++) {
					Copm copm = custAppealList.get(j);
					copm.setSpecialAccount(specialAccount);
					pinetreeDB.saveCopmAdd(copm);
				}
			}else {
				for (int j = 0; j < custAppealListSize; j++) {
					Copm copm = custAppealList.get(j);
					copm.setSpecialAccount(specialAccount);
					pinetreeDB.saveCopmAdd(copm);
				}
			}
			if (custAppealListSize == 0 && specialList.size() > 0) {
				Copm copm = specialList.get(0);
				pinetreeDB.saveCopmAdd(copm);
			}
			PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(CopmActivity.this);
			ArrayList<Copm> copmListDb1 = (ArrayList<Copm>) pinetreeDB1.getCopmByScheID(customer.getId());
			ArrayList<Copm> copmListDb2 = (ArrayList<Copm>) pinetreeDB1.getCopmAddByScheID(customer.getId());
			if (copmListDb1.size() == (custAppealListSize-listAddSize) && listAddSize == copmListDb2.size()){
				b = true;
			}else if (custAppealList.size() == copmListDb2.size()) {
				b = true;
			}else if (custAppealListSize == 0 && specialList.size() > 0) {
				b = true;
			}
			else {
				b = false;
			}
			
			if (b) {
				ToastUtils.showToast(CopmActivity.this, "保存成功");
			} else {
				ToastUtils.showToast(CopmActivity.this, "保存失败，请重试");
				pinetreeDB1.deleteCopmAddByScheID(customer.getId());
				pinetreeDB1.deleteCopmByScheID(customer.getId());
			}		
			
			break;
		case R.id.copm_submit_button:
			dialog.show();
			PinetreeDB pinetreeDB5 = PinetreeDB.getInstance(CopmActivity.this);
			RecordSubmitState recordSubmitStateSubmit = pinetreeDB5.getRecordSubmitState(customer.getId());
			if (null != recordSubmitStateSubmit &&  !"".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBasicSubmitState())) {
				if (NetUtil.checkNetWork(CopmActivity.this)) {
					getElementData();
					setData();
					RequestParams params = new RequestParams();
					params.addBodyParameter("scheID", customer.getId());			
					params.addBodyParameter("custID",customer.getCustID());
					params.addBodyParameter("custAppeal.custAppealStr", custAppealStr);
					if (null!=recordSubmitStateSubmit && !"".equals(recordSubmitStateSubmit)) {
						if ("1".equals(recordSubmitStateSubmit.getBrainSubmitState()) && "1".equals(recordSubmitStateSubmit.getEverydaySubmitState()) && "1".equals(recordSubmitStateSubmit.getDrugSubmitState()) && "1".equals(recordSubmitStateSubmit.getSportSubmitState())&& "1".equals(recordSubmitStateSubmit.getBasicSubmitState())) {
							params.addBodyParameter("custCustinfoDetail.status", "1");
						}else {
							params.addBodyParameter("custCustinfoDetail.status", "0");
						}
					}else {
						params.addBodyParameter("custCustinfoDetail.status", "0");
					}
					params.addBodyParameter("custAppeal.specialAccount", specialAccount);
					
					HttpUtil.post("copmSave.action", params, new RequestCallBack<String>() {
							
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							GlobalResult globalResult = GsonUtils.json2bean(responseInfo.result, GlobalResult.class);
							if (!"".equals(globalResult.getSuccess()) && Boolean.valueOf(globalResult.getSuccess())) {
								if ("1".equals(globalResult.getResultData().get(0).getIsServ())) {
									
									PinetreeDB pinetreeDB = PinetreeDB.getInstance(CopmActivity.this);
									pinetreeDB.deleteCopmByScheID(customer.getId());
									pinetreeDB.deleteCopmAddByScheID(customer.getId());
									
									for (int i = 0; i < custAppealList.size(); i++) {
										Copm copm = new Copm();
										copm.setContent(custAppealList.get(i).getContent());
										copm.setCustID(customer.getCustID());
										copm.setScheID(customer.getId());
										copm.setIsLast(custAppealList.get(i).getIsLast());
										copm.setSatisf(custAppealList.get(i).getSatisf());
										copm.setSituation(custAppealList.get(i).getSituation());
										copm.setManPoint(custAppealList.get(i).getManPoint());
										copm.setXianPoint(custAppealList.get(i).getXianPoint());
										copm.setZuoyeChange(custAppealList.get(i).getZuoyeChange());
										copm.setManChange(custAppealList.get(i).getManChange());
										copm.setSatisfSum(custAppealList.get(i).getSatisfSum());
										copm.setSituationSum(custAppealList.get(i).getSituationSum());
										copm.setSpecialAccount(specialAccount);
										copm.setSubmitState("1");
										pinetreeDB.saveCopm(copm);
									}
										
									if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
										pinetreeDB.updateCopmSubmitState(customer.getId());
									}else {
										RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
										recordSubmitStateDB.setScheID(customer.getId());
										recordSubmitStateDB.setCopmSubmitState("1");
										pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
									} 
										
									ToastUtils.showToast(CopmActivity.this, "提交成功");
									copmSaveButton.setVisibility(View.GONE);
									copmSubmitButton.setVisibility(View.GONE);
										
									dialog.hide();
									new Handler().postDelayed(
											new Runnable() {
												@Override
												public void run() {
													finish();
												}
											}, 1000);
								}else if ("0".equals(globalResult.getResultData().get(0).getIsServ())) {
									dialog.hide();
									ToastUtils.showToast(CopmActivity.this, "请先提交签字单！");
								}
							}else {
								dialog.hide();
								ToastUtils.showToast(CopmActivity.this, "提交失败，请重试！");
							}
								
						}
							
						@Override
						public void onFailure(HttpException error, String msg) {
							ToastUtils.showToast(CopmActivity.this, "提交失败，请重试！");
							dialog.hide();						
						}
					});
									
				}else {
					dialog.hide();
					ToastUtils.showToast(CopmActivity.this, "亲，没有网络哦");
				}
			}else {
				dialog.hide();	
				ToastUtils.showToast(CopmActivity.this, "亲，请先填写并提交基本情况表");
			}
			break;
		case R.id.copm_add_button:
			createAddContent();
			break;
		default:
			break;
		}
		
	}

}
