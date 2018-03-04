package com.pinetree.mobile.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BrainScore;
import com.pinetree.mobile.bean.BrainScoreBase;
import com.pinetree.mobile.bean.Customer;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * 智力状态检查（MMSE)评估
 */
public class BrainStateActivity extends Activity implements OnClickListener {

	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	@ViewInject(R.id.back_brainsState_imageView)
	private ImageView backBrainsStateImageView;
	/**
	 * 时间定向
	 */
	@ViewInject(R.id.sjdx_editText)
	private EditText sjdxEditText;
	/**
	 * 地点定向
	 */
	@ViewInject(R.id.dddx_editText)
	private EditText dddxEditText;
	/**
	 * 记忆得分
	 */
	@ViewInject(R.id.jy_editText)
	private EditText jyEditText;
	/**
	 * 注意与计算
	 */
	@ViewInject(R.id.zyyjs_editText)
	private EditText zyyjsEditText;
	/**
	 * 回忆
	 */
	@ViewInject(R.id.hy_editText)
	private EditText hyditText;
	/**
	 * 命名
	 */
	@ViewInject(R.id.mm_editText)
	private EditText mmEditText;
	/**
	 * 复述句子
	 */
	@ViewInject(R.id.fsjz_editText)
	private EditText fsjzEditText;
	/**
	 * 执行命令
	 */
	@ViewInject(R.id.zxml_editText)
	private EditText zxmlEditText;
	/**
	 * 阅读理解
	 */
	@ViewInject(R.id.ydlj_editText)
	private EditText ydljEditText;
	/**
	 * 书写
	 */
	@ViewInject(R.id.sx_editText)
	private EditText sxEditText;
	/**
	 * 构图能力
	 */
	@ViewInject(R.id.gtnl_editText)
	private EditText gtnlEditText;
	/**
	 * 文化程度
	 */
	@ViewInject(R.id.whcd_radioGroup)
	private RadioGroup whcdRadioGroup;
	/**
	 * 文盲
	 */
	@ViewInject(R.id.illiteracy_radioButton)
	private RadioButton illiteracyRadioButton;
	/**
	 * 小学
	 */
	@ViewInject(R.id.primary_radionButton)
	private RadioButton primaryRadionButton;
	/**
	 * 中学及以上
	 */
	@ViewInject(R.id.above_primary_radionButton)
	private RadioButton abovePrimaryRadionButton;
	/**
	 * 合计得分
	 */
	@ViewInject(R.id.assess_textView)
	private TextView assessTextView;
	/**
	 * 评估结果
	 */
	@ViewInject(R.id.assess_result_textView)
	private TextView assessResultTextView;
	/**
	 * 提交按钮
	 */
	@ViewInject(R.id.submit_brains_button)
	private Button submitBrainsButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.save_brains_button)
	private Button saveBrainsButton;

	private String sjdx="";
	private String dddx="";
	private String jy="";
	private String zyyjs="";
	private String hy="";
	private String mm="";
	private String fsjz="";
	private String zxml="";
	private String ydlj="";
	private String sx="";
	private String gtnl="";
	// 单选按钮文本
	private String education="";
	private String assess="";
	private String result="";
	
	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				BrainScore brainScoreDB = (BrainScore) msg.obj;
				initData(brainScoreDB);
				break;
			case INITDATANET:// 回写网络
				BrainScore brainScoreNet = (BrainScore) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(BrainStateActivity.this);
				pinetreeDB.deleteBrainScoreByscheID(customer.getId());
				brainScoreNet.setScheID(customer.getId());
				pinetreeDB.saveBrainState(brainScoreNet);
				initData(brainScoreNet);
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
		setContentView(R.layout.activity_brains_state);
		ViewUtils.inject(this);

		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");

		dialog = new Dialog(BrainStateActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);

		//判断 如果已经提交过 不可再提交
		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus())) {
			submitBrainsButton.setVisibility(View.GONE);
			saveBrainsButton.setVisibility(View.GONE);
		}
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(BrainStateActivity.this);
		if (!"1".equals(customer.getProdType())) {
			//不为评估产品时
			submitBrainsButton.setVisibility(View.GONE);
			saveBrainsButton.setVisibility(View.GONE);
		}

		if (NetUtil.checkNetWork(BrainStateActivity.this)) {
			dialog.show();
			RequestParams params = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("assType", "0");
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("prodType", customer.getProdType());
			HttpUtil.post("witStatusAndDailyLifeReportView.action", params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							dialog.hide();
							BrainScoreBase brainScoreBase = GsonUtils.json2bean(responseInfo.result,BrainScoreBase.class);
							BrainScore brainScoreDB = pinetreeDB.getBrainScoreByScheID(customer.getId());
							Message message = Message.obtain();
							if (!"".equals(brainScoreBase.getSuccess())	&& Boolean.valueOf(brainScoreBase.getSuccess())) {
								if (!"".equals(brainScoreDB)&& null != brainScoreDB) {
									if (!"".equals(brainScoreDB.getCreateTime())&& !"".equals(brainScoreBase.getResultData().get(0).getCreateTime())) {
										try {
											String createTimeDB = brainScoreDB.getCreateTime();
											String createTimeNet = brainScoreBase.getResultData().get(0).getCreateTime();
											String timeDB = createTimeDB.substring(0, 10)+ " "+ createTimeDB.substring(11, 19);
											String timeNet = createTimeNet.substring(0, 10)+ " "+ createTimeNet.substring(11, 19);
											SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date dbDate = format.parse(timeDB);
											Date netDate = format.parse(timeNet);
											if (dbDate.compareTo(netDate) > 0 ) {
												message.obj = brainScoreDB;
												message.what = INITDATADB;
												handler.sendMessage(message);
											} else {
												message.obj = brainScoreBase
														.getResultData().get(0);
												message.what = INITDATANET;
												handler.sendMessage(message);
											}

										} catch (Exception e) {
											e.printStackTrace();
										}

									}else if ("".equals(brainScoreDB.getCreateTime())) {
										message.obj = brainScoreBase
												.getResultData().get(0);
										message.what = INITDATANET;
										handler.sendMessage(message);
									}else if ("".equals(brainScoreBase.getResultData().get(0).getCreateTime())) {
										message.obj = brainScoreDB;
										message.what = INITDATADB;
										handler.sendMessage(message);
									}
								}else{
									message.obj = brainScoreBase
											.getResultData().get(0);
									message.what = INITDATANET;
									handler.sendMessage(message);
								}
							} else {
								if (!"".equals(brainScoreDB)
										&& null != brainScoreDB) {
									initData(brainScoreDB);
								}
							}

						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
						}
					});
		} else {
			BrainScore brainScoreDB = pinetreeDB.getBrainScoreByScheID(customer.getId());
			if (!"".equals(brainScoreDB) && null != brainScoreDB) {
				initData(brainScoreDB);
			}
		}

		backBrainsStateImageView.setOnClickListener(this);
		submitBrainsButton.setOnClickListener(this);
		saveBrainsButton.setOnClickListener(this);

		sjdxEditText.addTextChangedListener(new watcher(sjdxEditText));
		dddxEditText.addTextChangedListener(new watcher(dddxEditText));
		jyEditText.addTextChangedListener(new watcher(jyEditText));
		zyyjsEditText.addTextChangedListener(new watcher(zyyjsEditText));
		hyditText.addTextChangedListener(new watcher(hyditText));
		mmEditText.addTextChangedListener(new watcher(mmEditText));
		fsjzEditText.addTextChangedListener(new watcher(fsjzEditText));
		zxmlEditText.addTextChangedListener(new watcher(zxmlEditText));
		ydljEditText.addTextChangedListener(new watcher(ydljEditText));
		sxEditText.addTextChangedListener(new watcher(sxEditText));
		gtnlEditText.addTextChangedListener(new watcher(gtnlEditText));

	}

	/**
	 * 获取元素的值
	 */
	public void getElementData() {
		sjdx = (String) sjdxEditText.getText().toString().trim();
		dddx = (String) dddxEditText.getText().toString().trim();
		jy = (String) jyEditText.getText().toString().trim();
		zyyjs = (String) zyyjsEditText.getText().toString().trim();
		hy = (String) hyditText.getText().toString().trim();
		mm = (String) mmEditText.getText().toString().trim();
		fsjz = (String) fsjzEditText.getText().toString().trim();
		zxml = (String) zxmlEditText.getText().toString().trim();
		ydlj = (String) ydljEditText.getText().toString().trim();
		sx = (String) sxEditText.getText().toString().trim();
		gtnl = (String) gtnlEditText.getText().toString().trim();

		RadioButton radioButton = (RadioButton) findViewById(whcdRadioGroup
				.getCheckedRadioButtonId());
		if (illiteracyRadioButton.isChecked() == true ||  primaryRadionButton .isChecked() == true || abovePrimaryRadionButton.isChecked() == true) {
			education = radioButton.getText().toString().trim();
		}
		
		result = assessResultTextView.getText().toString().trim();
		assess = assessTextView.getText().toString().trim();
	};

	/**
	 * 数据回显
	 */
	private void initData(BrainScore brainScore) {
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(BrainStateActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getBrainSubmitState() && "1".equals(recordSubmitState2.getBrainSubmitState())) {
				submitBrainsButton.setVisibility(View.GONE);
				saveBrainsButton.setVisibility(View.GONE);
			}
		}
		
		if (!"".equals(brainScore) && null != brainScore) {
			sjdxEditText.setText(brainScore.getSjdx());
			dddxEditText.setText(brainScore.getDddx());
			jyEditText.setText(brainScore.getJy());
			zyyjsEditText.setText(brainScore.getZyyjs());
			hyditText.setText(brainScore.getHy());
			mmEditText.setText(brainScore.getMm());
			fsjzEditText.setText(brainScore.getFsjz());
			zxmlEditText.setText(brainScore.getZxml());
			ydljEditText.setText(brainScore.getYdlj());
			sxEditText.setText(brainScore.getSx());
			gtnlEditText.setText(brainScore.getGtnl());
			if ("文盲".equals(brainScore.getEducation())) {
				illiteracyRadioButton.setChecked(true);
			}else if ("小学".equals(brainScore.getEducation())) {
				primaryRadionButton.setChecked(true);
			}else if ("中学及以上".equals(brainScore.getEducation())) {
				abovePrimaryRadionButton.setChecked(true);
			}
			assessTextView.setText(brainScore.getAssess());
			assessResultTextView.setText(brainScore.getResult());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_brainsState_imageView:
			finish();
			break;
		case R.id.submit_brains_button:// 提交表单数据
			dialog.show();
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(BrainStateActivity.this);
			RecordSubmitState recordSubmitStateSubmit = pinetreeDB.getRecordSubmitState(customer.getId());
			if (null != recordSubmitStateSubmit &&  !"".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBasicSubmitState())) {
				if (NetUtil.checkNetWork(BrainStateActivity.this)) {
					getElementData();
					RequestParams params = new RequestParams();
					params.addBodyParameter("empID",employeeId);
					params.addBodyParameter("scheID", customer.getId());
					params.addBodyParameter("custID", customer.getCustID());
					params.addBodyParameter("custAssessDetail.sjdx", sjdx);
					params.addBodyParameter("custAssessDetail.dddx", dddx);
					params.addBodyParameter("custAssessDetail.jy", jy);
					params.addBodyParameter("custAssessDetail.zyyjs", zyyjs);
					params.addBodyParameter("custAssessDetail.hy", hy);
					params.addBodyParameter("custAssessDetail.mm", mm);
					params.addBodyParameter("custAssessDetail.fsjz", fsjz);
					params.addBodyParameter("custAssessDetail.zxml", zxml);
					params.addBodyParameter("custAssessDetail.ydlj", ydlj);
					params.addBodyParameter("custAssessDetail.sx", sx);
					params.addBodyParameter("custAssessDetail.gtnl", gtnl);
					params.addBodyParameter("custAssessResult.education", education);
					params.addBodyParameter("custAssessResult.assess", assess);
					params.addBodyParameter("custAssessResult.result", result);
					params.addBodyParameter("assType", "0");

					if (null!=recordSubmitStateSubmit && !"".equals(recordSubmitStateSubmit)) {
						if ("1".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getEverydaySubmitState()) && "1".equals(recordSubmitStateSubmit.getDrugSubmitState()) && "1".equals(recordSubmitStateSubmit.getSportSubmitState()) && "1".equals(recordSubmitStateSubmit.getCopmSubmitState())) {
							params.addBodyParameter("custCustinfoDetail.status", "1");
						}else {
							params.addBodyParameter("custCustinfoDetail.status", "0");
						}
					}else {
						params.addBodyParameter("custCustinfoDetail.status", "0");
					}
					
					HttpUtil.post("witStatusReportSave.action", params,
							new RequestCallBack<String>() {
								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {
									GlobalResult globalResult = GsonUtils
											.json2bean(responseInfo.result,
													GlobalResult.class);
									if (!"".equals(globalResult.getSuccess())&& Boolean.valueOf(globalResult.getSuccess())) {
										PinetreeDB pinetreeDB = PinetreeDB.getInstance(BrainStateActivity.this);
										pinetreeDB.deleteBrainScoreByscheID(customer.getId());
										BrainScore brainScore = setData();
										pinetreeDB.saveBrainState(brainScore);
										
										if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
											pinetreeDB.updateRecordSubmitStateBrain(customer.getId());
										}else {
											RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
											recordSubmitStateDB.setScheID(customer.getId());
											recordSubmitStateDB.setBrainSubmitState("1");
											pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
										} 
									
										ToastUtils.showToast(BrainStateActivity.this, "提交成功");
										submitBrainsButton.setVisibility(View.GONE);
										saveBrainsButton.setVisibility(View.GONE);
										
										dialog.hide();
										new Handler().postDelayed(
												new Runnable() {
													@Override
													public void run() {
														finish();
													}
												}, 1000);
									} else {
										clearVariable();
										dialog.hide();
										ToastUtils.showToast(BrainStateActivity.this,"提交失败，请重试！");
									}
								}

								@Override
								public void onFailure(HttpException error,
										String msg) {
									clearVariable();
									ToastUtils.showToast(BrainStateActivity.this,"提交失败，请重试！");
									dialog.hide();
								}
							});
				} else {
					dialog.hide();
					ToastUtils.showToast(BrainStateActivity.this, "亲，没有网络哦");
				}
			}else {
				dialog.hide();	
				ToastUtils.showToast(BrainStateActivity.this, "亲，请先填写并提交基本情况表");
			}
			break;
		case R.id.save_brains_button:// 将填写的智力状态检查（MMSE)评估保存到本地
			PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(BrainStateActivity.this);
			pinetreeDB1.deleteBrainScoreByscheID(customer.getId());
			getElementData();
			BrainScore brainScore = setData();
			boolean b = pinetreeDB1.saveBrainState(brainScore);
			if (b) {
				ToastUtils.showToast(BrainStateActivity.this, "保存成功");
			} else {
				ToastUtils.showToast(BrainStateActivity.this, "保存失败，请重试");
			}
			break;
		default:
			break;
		}

	}

	private BrainScore setData() {
		BrainScore brainScore = new BrainScore();
		brainScore.setScheID(customer.getId());
		brainScore.setCustID(customer.getCustID());
		brainScore.setSjdx(sjdx);
		brainScore.setDddx(dddx);
		brainScore.setJy(jy);
		brainScore.setZyyjs(zyyjs);
		brainScore.setHy(hy);
		brainScore.setMm(mm);
		brainScore.setFsjz(fsjz);
		brainScore.setZxml(zxml);
		brainScore.setYdlj(ydlj);
		brainScore.setSx(sx);
		brainScore.setGtnl(gtnl);
		brainScore.setEducation(education);
		brainScore.setAssess(assess);
		brainScore.setResult(result);
		// 获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		brainScore.setCreateTime(createDate + "T" + createTime);
		return brainScore;
	}

	public class watcher implements TextWatcher {
		private EditText editTextId = null;

		public watcher(EditText id) {
			editTextId = id;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			editTextCheckoutListener checkoutListener = new editTextCheckoutListener();
			if (editTextId == sjdxEditText) {
				// 时间定向
				checkoutListener
						.inputCheckout(s.toString(), 0, 5, sjdxEditText);
				checkoutListener.total();
			} else if (editTextId == dddxEditText) {
				// 地点定向
				checkoutListener
						.inputCheckout(s.toString(), 0, 5, dddxEditText);
				checkoutListener.total();
			} else if (editTextId == jyEditText) {
				// 记忆
				checkoutListener.inputCheckout(s.toString(), 0, 3, jyEditText);
				checkoutListener.total();
			} else if (editTextId == zyyjsEditText) {
				// 注意与计算
				checkoutListener.inputCheckout(s.toString(), 0, 5,
						zyyjsEditText);
				checkoutListener.total();
			} else if (editTextId == hyditText) {
				// 回忆
				checkoutListener.inputCheckout(s.toString(), 0, 3, hyditText);
				checkoutListener.total();
			} else if (editTextId == mmEditText) {
				// 命名
				checkoutListener.inputCheckout(s.toString(), 0, 2, mmEditText);
				checkoutListener.total();
			} else if (editTextId == fsjzEditText) {
				// 复述句子
				checkoutListener
						.inputCheckout(s.toString(), 0, 1, fsjzEditText);
				checkoutListener.total();
			} else if (editTextId == zxmlEditText) {
				// 执行命令
				checkoutListener
						.inputCheckout(s.toString(), 0, 3, zxmlEditText);
				checkoutListener.total();
			} else if (editTextId == ydljEditText) {
				// 阅读理解
				checkoutListener
						.inputCheckout(s.toString(), 0, 1, ydljEditText);
				checkoutListener.total();
			} else if (editTextId == sxEditText) {
				// 书写
				checkoutListener.inputCheckout(s.toString(), 0, 1, sxEditText);
				checkoutListener.total();
			} else if (editTextId == gtnlEditText) {
				// 构图能力
				checkoutListener
						.inputCheckout(s.toString(), 0, 1, gtnlEditText);
				checkoutListener.total();
			}
		}
	}
	
	/*
	 * 输入框文本校验
	 */
	public class editTextCheckoutListener {

		public void inputCheckout(String s, int min, int max, EditText editText) {
			if (!"".equals(s) && null != s) {
				int text = Integer.valueOf(s);
				if (min <= text && text <= max) {

				} else {
					editText.setText("");
				}
			}
		}

		public void total() {
			getElementData();
			int total = 0;
			if (!"".equals(sjdx)) {
				total += Integer.valueOf(sjdx);
			}
			if (!"".equals(dddx)) {
				total += Integer.valueOf(dddx);
			}
			if (!"".equals(jy)) {
				total += Integer.valueOf(jy);
			}
			if (!"".equals(zyyjs)) {
				total += Integer.valueOf(zyyjs);
			}
			if (!"".equals(hy)) {
				total += Integer.valueOf(hy);
			}
			if (!"".equals(mm)) {
				total += Integer.valueOf(mm);
			}
			if (!"".equals(fsjz)) {
				total += Integer.valueOf(fsjz);
			}
			if (!"".equals(zxml)) {
				total += Integer.valueOf(zxml);
			}
			if (!"".equals(ydlj)) {
				total += Integer.valueOf(ydlj);
			}
			if (!"".equals(sx)) {
				total += Integer.valueOf(sx);
			}
			if (!"".equals(gtnl)) {
				total += Integer.valueOf(gtnl);
			}
			// 设置总得分
			assessTextView.setText(total + "");

			final int tempTotal = total;
			
			if (illiteracyRadioButton.isChecked() == true) {
				illiteracyJudge(tempTotal);
			}else if (primaryRadionButton.isChecked() == true) {
				primaryJudge(tempTotal);
			}else if (abovePrimaryRadionButton.isChecked() == true) {
				abovePrimaryJudge(tempTotal);
			}else if (illiteracyRadioButton.isChecked() == false && primaryRadionButton.isChecked() == false && abovePrimaryRadionButton.isChecked() == false) {
				cultureDegree(tempTotal);
			}
			
			whcdRadioGroup
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							switch (checkedId) {
							case R.id.illiteracy_radioButton:// 文盲
								illiteracyJudge(tempTotal);
								break;
							case R.id.primary_radionButton:// 小学
								primaryJudge(tempTotal);
								break;
							case R.id.above_primary_radionButton:// 中学及以上
								abovePrimaryJudge(tempTotal);
								break;

							default:
								break;
							}

						}
					});
		}
	}
	/**
	 * 文盲 判断
	 */
	private void illiteracyJudge(int sum){
		if (15 <= sum && sum <= 17) {
			assessResultTextView.setText("痴呆");
		} else if (sum < 15) {
			assessResultTextView.setText("严重痴呆");
		} else {
			assessResultTextView.setText("正常");
		}
	}
	/**
	 * 小学 判断
	 */
	private void primaryJudge(int sum){
		if (15 <= sum && sum <= 20) {
			assessResultTextView.setText("痴呆");
		} else if (sum < 15) {
			assessResultTextView.setText("严重痴呆");
		} else {
			assessResultTextView.setText("正常");
		}
	}
	/**
	 * 中学及以上 判断
	 */	
	private void abovePrimaryJudge(int sum){
		if (15 <= sum && sum <= 24) {
			assessResultTextView.setText("痴呆");
		} else if (sum < 15) {
			assessResultTextView.setText("严重痴呆");
		} else {
			assessResultTextView.setText("正常");
		}
	}
	/**
	 * 文化程度判断
	 */
	private void cultureDegree(int sum){
		  if (sum <= 15) {
			assessResultTextView.setText("严重痴呆");
		}else if (sum <= 22) {
			assessResultTextView.setText("痴呆");
		}else {
			assessResultTextView.setText("正常");
		}
	}
	/**
	 * 清空变量
	 */
	public void clearVariable() {
		sjdx="";
		dddx="";
		jy="";
		zyyjs="";
		hy="";
		mm="";
		fsjz="";
		zxml="";
		ydlj="";
		sx="";
		gtnl="";
		education="";
		assess="";
		result="";
	}
}
