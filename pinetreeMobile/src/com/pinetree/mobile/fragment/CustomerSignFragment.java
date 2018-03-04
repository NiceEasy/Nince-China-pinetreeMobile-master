package com.pinetree.mobile.fragment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.activity.SpecialCircumstancesActivity;
import com.pinetree.mobile.activity.WritePadActivity;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.CustomerSign;
import com.pinetree.mobile.bean.SignResult;
import com.pinetree.mobile.bean.SpecialCircumstances;
import com.pinetree.mobile.bean.SpecialCircumstancesBase;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.SDPath;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * 客户签字
 * 
 * @author Administrator
 * 
 */
public class CustomerSignFragment extends Fragment implements OnClickListener {
	@ViewInject(R.id.name_text)
	private TextView nameText;
	@ViewInject(R.id.customer_name_text)
	private TextView customerNameText;
	@ViewInject(R.id.server_name_text)
	private TextView serverNameText;
	@ViewInject(R.id.date_text)
	private TextView dateText;
	@ViewInject(R.id.time_text)
	private TextView timeText;
	@ViewInject(R.id.sign_imageView)
	private ImageView signImageView;//客户签字显示图片
	@ViewInject(R.id.write_text)
	private TextView writeText;
	@ViewInject(R.id.photo_text)
	private TextView photoText;
	@ViewInject(R.id.update_textView)
	private TextView updateImage;
	@ViewInject(R.id.save_CustomerSign_btn)
	private Button saveCustomerSignBtn;
	@ViewInject(R.id.reimbursementAmount_editText)
	private EditText reimbursementAmountEditText;
	@ViewInject(R.id.specialCircumstances_editText)
	private EditText specialCircumstancesEditText;
	@ViewInject(R.id.relation_editText)
	private EditText relationEditText;//付款人姓名
	@ViewInject(R.id.sign_relation_spinner)
	private Spinner signRelationSpinner;//付款人与客户关系
	@ViewInject(R.id.custSign_relation_spinner)
	private Spinner custSignRelationSpinner;//签字人与客户关系
	@ViewInject(R.id.submit_CustomerSign_btn)
	private Button submitCustomerSignBtn;
	@ViewInject(R.id.money_linearLayout)
	private LinearLayout moneyLinearLayout;
	@ViewInject(R.id.money_view)
	private View moneyView;
	@ViewInject(R.id.special_customerSign_btn)
	private Button specialCircumstancesBtn;//跳到特殊情况
	
	/**
	 * 续约布局
	 */
	@ViewInject(R.id.isSign_linearLayout)
	private LinearLayout isSignLinearLayout;
	/**
	 * 续约 RadioGroup
	 */
	@ViewInject(R.id.isSign_radioGroup)
	private RadioGroup isSignRadioGroup;
	/**
	 * 续约 RadioButton  0元
	 */
	@ViewInject(R.id.isSign_zero_radioButton)
	private RadioButton isSignZeroRadioButton;
	/**
	 * 续约 RadioButton  其他钱
	 */
	@ViewInject(R.id.isSign_more_radioButton)
	private RadioButton isSignMoreRadioButton;
	/**
	 * 还款布局
	 */
	@ViewInject(R.id.isVouchRec_linearLayout)
	private LinearLayout isVouchRecLinearLayout;
	/**
	 * 还款 RadioGroup
	 */
	@ViewInject(R.id.isVouchRec_radioGroup)
	private RadioGroup isVouchRecRadioGroup;
	/**
	 * 还款 RadioButton  0元
	 */
	@ViewInject(R.id.isVouchRec_zero_radioButton)
	private RadioButton isVouchRecZeroRadioButton;
	/**
	 * 还款 RadioButton  其他钱
	 */
	@ViewInject(R.id.isVouchRec_more_radioButton)
	private RadioButton isVouchRecMoreRadioButton;
	
	private final Calendar mCalendar = Calendar.getInstance();
	// 获取当前系统时间
	private int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);

	private int minute = mCalendar.get(Calendar.MINUTE);

	private int day = mCalendar.get(Calendar.DAY_OF_MONTH);

	private int month = mCalendar.get(Calendar.MONTH);

	private int year = mCalendar.get(Calendar.YEAR);

	private static final int TAKE_PHOTO = 0;
	private static final int WRITE_RESULT = 2;
	private static final int CUT_PHOTO = 4;//截图
	private File photoPath;
	private File cutFile;
	private Uri cutUri;
	private File cutFile1;
	private File compressFile;
	private Customer customer;
	private String employeeId;
	private Dialog dialog;
	private String writePath = "";
	private String photoPathStr = "";
	private Bitmap compressBitmap;//压缩的图片
	private List<String> photoList = new ArrayList<String>();//所有签字图片地址
	private String id = UUID.randomUUID().toString().replaceAll("-", "");//签字单ID
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
		customer = (Customer) bundle.getSerializable("customer");
		String employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		View customerSignLayout = inflater.inflate(R.layout.fragment_customer_sign, null, false);
		ViewUtils.inject(this, customerSignLayout);

		dialog = new Dialog(getActivity(), R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);

		if ("1".equals(customer.getIsServ())) {
			saveCustomerSignBtn.setVisibility(View.GONE);
			submitCustomerSignBtn.setVisibility(View.GONE);
		}
		
		/**
		 * 根据customer.getIsVouchRec customer.getIsSign 判断下面的布局是否显示
		 */
		if (null!=customer.getIsVouchRec()&&!"".equals(customer.getIsVouchRec())&&customer.getIsVouchRec().equals("1")) {
			isVouchRecLinearLayout.setVisibility(View.VISIBLE);
			if (!"".equals(customer.getVouchAmount())&&null!=customer.getVouchAmount()) {
				isVouchRecMoreRadioButton.setText(customer.getVouchAmount());
			}
		}else {
			isVouchRecLinearLayout.setVisibility(View.GONE);
		}

		if (null!=customer.getIsSign()&&!"".equals(customer.getIsSign())&&customer.getIsSign().equals("1")) {
			isSignLinearLayout.setVisibility(View.VISIBLE);
			if (!"".equals(customer.getRenewalAmount())&&null!=customer.getRenewalAmount()) {
				isSignMoreRadioButton.setText(customer.getRenewalAmount());
			}
		}else {
			isSignLinearLayout.setVisibility(View.GONE);
		}

		nameText.setText(customer.getCustName() + "的康复护理签字单");
		customerNameText.setText(customer.getCustName());
		serverNameText.setText(employeeName);
		updateImage.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

		resetTime();
		resetDate();

		timeText.setOnClickListener(this);
		dateText.setOnClickListener(this);
		writeText.setOnClickListener(this);// 手写板
		photoText.setOnClickListener(this);// 拍照
		updateImage.setOnClickListener(this);// 修改手写板和拍照都能点击
		saveCustomerSignBtn.setOnClickListener(this);// 保存客户签字到本地数据库
		submitCustomerSignBtn.setOnClickListener(this);
		specialCircumstancesBtn.setOnClickListener(this);
		
		CustomerSign customerSign = PinetreeDB.getInstance(getActivity()).getCustomerSign(customer.getId());
		if (customerSign != null) {
			dateText.setText(customerSign.getServerDate().substring(0, 10));
			timeText.setText(customerSign.getServerDate().substring(11, 16));
			serverNameText.setText(customerSign.getServerName());
			if (null !=customerSign.getRenewalAmount() &&  !"".equals(customerSign.getRenewalAmount()) && !"0".equals(customerSign.getRenewalAmount())) {
				isSignMoreRadioButton.setText(customerSign.getRenewalAmount());
				isSignMoreRadioButton.setChecked(true);
			}
			if (null !=customerSign.getVouchAmount() &&  !"".equals(customerSign.getVouchAmount()) && !"0".equals(customerSign.getVouchAmount())) {
				isVouchRecMoreRadioButton.setText(customerSign.getVouchAmount());
				isVouchRecMoreRadioButton.setChecked(true);
			}
			
			reimbursementAmountEditText.setText(customerSign.getReimbursementAmount());
			specialCircumstancesEditText.setText(customerSign.getSpecialCircumstances());
			if (!"".equals(customerSign.getSignImageUri())) {
				writePath = customerSign.getSignImageUri();
				photoList.add(customerSign.getSignImageUri());
				File file1 = new File(customerSign.getSignImageUri());
				if (file1.exists()) {
					Bitmap bit1 = BitmapFactory.decodeFile(file1.getPath()).copy(Config.ARGB_8888, true);
					signImageView.setImageBitmap(bit1);
				}

			}
			if (!"".equals(customerSign.getPhotoImageUri())) {
				photoPathStr = customerSign.getPhotoImageUri();
				photoList.add(customerSign.getPhotoImageUri());
				File file2 = new File(customerSign.getPhotoImageUri());
				if (file2.exists()) {
					Bitmap bit2 = BitmapFactory.decodeFile(file2.getPath()).copy(Config.ARGB_8888, true);
					signImageView.setImageBitmap(bit2);
				}
			}
			
			relationEditText.setText(customerSign.getPayerName());
			if ("0".equals(customerSign.getSubmitState())) {
				submitCustomerSignBtn.setVisibility(View.GONE);
				saveCustomerSignBtn.setVisibility(View.GONE);
			}
			String relation = customerSign.getRelation();
			if (!"".equals(relation)&&null!=relation) {
				if ("1".equals(relation)) {
					signRelationSpinner.setSelection(0);
				}else if ("2".equals(relation)) {
					signRelationSpinner.setSelection(1);
				}else if ("3".equals(relation)) {
					signRelationSpinner.setSelection(2);
				}else if ("4".equals(relation)) {
					signRelationSpinner.setSelection(3);
				}else if ("5".equals(relation)) {
					signRelationSpinner.setSelection(4);
				}else if ("6".equals(relation)) {
					signRelationSpinner.setSelection(5);
				}
			}else {
				signRelationSpinner.setSelection(0);
			}
			
			
			String signRelation = customerSign.getSignRelation();
			Log.v("TAG", "签字人与客户关系 set " + signRelation);
			if (!"".equals(signRelation)&&null!=signRelation) {
				if ("1".equals(signRelation)) {
					custSignRelationSpinner.setSelection(0);
				}else if ("2".equals(signRelation)) {
					custSignRelationSpinner.setSelection(1);
				}else if ("3".equals(signRelation)) {
					custSignRelationSpinner.setSelection(2);
				}else if ("4".equals(signRelation)) {
					custSignRelationSpinner.setSelection(3);
				}else if ("5".equals(signRelation)) {
					custSignRelationSpinner.setSelection(4);
				}else if ("6".equals(signRelation)) {
					custSignRelationSpinner.setSelection(5);
				}else if ("7".equals(signRelation)) {
					custSignRelationSpinner.setSelection(6);
				}
			}else {
				custSignRelationSpinner.setSelection(0);
			}
		}
		
		deleteCustomerSignData();
		
		return customerSignLayout;
		
	}
	
	/**
	 * 删除15天之前的签字表的数据CUSTOMERSIGN
	 */
	private void deleteCustomerSignData(){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
		List<CustomerSign> list = pinetreeDB.selectAllCustomerSign();
		if (list.size() > 0) {
			Date time = new Date(System.currentTimeMillis()  - 16*24*60*60*1000);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(time);
			int timeHis = Integer.parseInt(dateStr);
			for (int i = 0; i < list.size(); i++) {
				String createTime = list.get(i).getServerDate();
				if (!"".equals(createTime) && null != createTime) {
					String timeDB = createTime.substring(0, 4)+ createTime.substring(5, 7)+ createTime.substring(8, 10);
					int timeDb = Integer.parseInt(timeDB);
					if (timeHis > timeDb) {
						String scheID = list.get(i).getScheduleId();
						String signPath = list.get(i).getSignImageUri();
						String photoPath = list.get(i).getPhotoImageUri();
						if (!"".equals(photoPath)) {
							File file = new File(photoPath);
							if (file.exists()) {
								file.delete();
							}
							String cutPhotoPath = photoPath.substring(0, photoPath.length()-4)+"c.png";
							if (!"".equals(cutPhotoPath)) {
								File file1 = new File(cutPhotoPath);
								if (file1.exists()) {
									file1.delete();
								}
							}
						}
						if (!"".equals(signPath)) {
							File file = new File(signPath);
							if (file.exists()) {
								file.delete();
							}
							String cutSignPath = signPath.substring(0, signPath.length()-4)+"c.png";
							if (!"".equals(cutSignPath)) {
								File file1 = new File(cutSignPath);
								if (file1.exists()) {
									file1.delete();
								}
							}
						}
						pinetreeDB.deleteCustomerSign(scheID);
					}
				}
			}
		}
	}
	

	/**
	 * 设置系统时间
	 */
	private void resetTime() {
		timeText.setText(new StringBuilder().append(pad(hourOfDay)).append(":")
				.append(pad(minute)));
	}

	/**
	 * 设置系统日期
	 */
	private void resetDate() {
		dateText.setText(new StringBuffer().append(pad(year)).append("-")
				.append(pad(month + 1)).append("-").append(pad(day)));
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.date_text:// 日期
			new DatePickerDialog(getActivity(), new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					dateText.setText(new StringBuffer().append(pad(year))
							.append("-").append(pad(monthOfYear + 1))
							.append("-").append(pad(dayOfMonth)));
				}
			}, year, month, day).show();
			break;
		case R.id.time_text:// 时间
			new TimePickerDialog(getActivity(), new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					timeText.setText(new StringBuilder().append(pad(hourOfDay))
							.append(":").append(pad(minute)));
				}
			}, hourOfDay, minute, true).show();
			break;
		case R.id.write_text:// 手写板
			File pathWrite = SDPath.getSDPath(getActivity());
			if (!pathWrite.exists()) {
				pathWrite.mkdirs();
			}
			
			CustomerSign customerSignSave = PinetreeDB.getInstance(getActivity()).getCustomerSign(customer.getId());
			Intent intent_write = new Intent(getActivity(),
					WritePadActivity.class);
			
			String name = customer.getId().toString() + photoList.size() + "w.png";
			writePath = pathWrite.getPath() + "/" + name;
			intent_write.putExtra("writePath", writePath);
			startActivityForResult(intent_write, WRITE_RESULT);
			break;
		case R.id.photo_text:// 拍照保存图片
			File path = SDPath.getSDPath(getActivity());
			if (!path.exists()) {
				path.mkdirs();
			}
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			
			photoPathStr = path.getPath() + "/" + customer.getId().toString() + photoList.size() + "p.png";
			photoPath = new File(photoPathStr);	
			Uri photoUri = Uri.fromFile(photoPath);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(intent, TAKE_PHOTO);
			break;
		case R.id.update_textView:// 点击让手写板和拍照签字都可点击
			photoText.setClickable(true);
			writeText.setClickable(true);
			String write = writeText.getText().toString().trim();
			String photo = photoText.getText().toString().trim();
			File path1 = SDPath.getSDPath(getActivity());
			if (!path1.exists()) {
				path1.mkdirs();
			}
			if (!"签字".equals(write)) {
				File writeFile = new File(path1, customer.getId().toString() + "w.png");
				File cutWriteFile = new File(path1, customer.getId().toString() + "wc.png");
				writeFile.delete();
				cutWriteFile.delete();
			}
			if (!"拍照".equals(write)) {
				File photoFile = new File(path1, customer.getId().toString() + "p.png");
				File cutPhotoFile = new File(path1, customer.getId().toString() + "pc.png");
				photoFile.delete();
				cutPhotoFile.delete();
			}
			writeText.setText("签字");
			photoText.setText("拍照");
			break;
		case R.id.save_CustomerSign_btn:// 保存客户签字到本地数据库
			dialog.show();
			String writeImageUri = writePath;
			String photoImageUri = photoPathStr;
			if (!"".equals(writeImageUri) || !"".equals(photoImageUri)) {
				File writeFile1 = new File(writeImageUri);
				File photoFile1 = new File(photoImageUri);
				if (writeFile1.length() > 0 || photoFile1.length() > 0) {//判断图片是否存在再保存
					
					CustomerSign customerSign = setData(writeImageUri,photoImageUri);
					PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
					pinetreeDB.deleteCustomerSign(customer.getId());
					boolean b = pinetreeDB.saveCustomerSign(customerSign);
					if (b) {
						ToastUtils.showToast(getActivity(), "保存成功");
						pinetreeDB.updateSaveCustomerSign(customer.getId());
						if (photoList.size() > 0) {
							for (int i = 0; i < photoList.size(); i++) {
								if (!"".equals(writeImageUri) && !photoList.get(i).equals(writeImageUri)) {
									File file = new File(photoList.get(i));
									if (file.exists()) {
										file.delete();	
									}
								}else if (!"".equals(photoImageUri) && !photoList.get(i).equals(photoImageUri)) {
									File file = new File(photoList.get(i));
									if (file.exists()) {
										file.delete();	
									}
								}
								
							}
						}
						dialog.hide();
					} else {
						ToastUtils.showToast(getActivity(), "保存失败");
						dialog.hide();
					}
				}else {
					ToastUtils.showToast(getActivity(), "签字不存在，请重新签字");
					dialog.hide();
				}
			} else {
				ToastUtils.showToast(getActivity(), "客户签字不能为空");
				dialog.hide();
			}
			break;
		case R.id.submit_CustomerSign_btn:// 向服务器提交数据
			submitCustomerSignBtn.setClickable(false);
			dialog.show();
			if (NetUtil.checkNetWork(getActivity())) {
					
				String scheduleTime = customer.getBeginTime().substring(0, 4) + customer.getBeginTime().substring(5, 7) + customer.getBeginTime().substring(8, 10);
				int scheduleTimeNum = Integer.parseInt(scheduleTime);
				Date currentTime = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateStr = sdf.format(currentTime);
				int currentTimeNum = Integer.parseInt(dateStr);
				if (scheduleTimeNum <= currentTimeNum) {
						
					final String writeImageUri_ = writePath;
					final String photoImageUri_ = photoPathStr;
					if (!"".equals(writeImageUri_) || !"".equals(photoImageUri_)) {
							
						File subWriteFile = new File(writeImageUri_);
						File subPhotoFile = new File(photoImageUri_);
						if (subWriteFile.length() > 0 || subPhotoFile.length() > 0) { //判断签字是否存在
							
							RequestParams params = new RequestParams();
							params.addBodyParameter("signInfo.serviceID",customer.getId());// 日程id，服务id
							String date = dateText.getText().toString().trim();
							String time = timeText.getText().toString().trim();
							params.addBodyParameter("signInfo.serDate", date + "T"+ time + ":00");// 服务时间
							params.addBodyParameter("signInfo.custID",customer.getCustID());// 客户id
							params.addBodyParameter("signInfo.custName", customer.getCustName());
							String spinner = signRelationSpinner.getSelectedItem().toString().trim();
							if ("本人".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","1");
							}else if ("子女".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","2");
							}else if ("政府".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","3");
							}else if ("残联".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","4");
							}else if ("青松赠送".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","5");
							}else if ("其他".equals(spinner)) {
								params.addBodyParameter("signInfo.relation","6");
							}
							String signSpinner = custSignRelationSpinner.getSelectedItem().toString().trim();
							if ("本人".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","1");
							}else if ("配偶".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","2");
							}else if ("子女".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","3");
							}else if ("父母".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","4");
							}else if ("亲属".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","5");
							}else if ("家政".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","6");
							}else if ("其他".equals(signSpinner)) {
								params.addBodyParameter("signInfo.signRelation","7");
							}
							params.addBodyParameter("signInfo.employeeID", employeeId);// 员工id
							params.addBodyParameter("empID", employeeId);// 员工id
							params.addBodyParameter("signInfo.custAddress", customer.getCustAddress());
							params.addBodyParameter("signInfo.lat", customer.getLat());
							params.addBodyParameter("signInfo.lng", customer.getLng());
							params.addBodyParameter("signInfo.prodType",customer.getProdType());//产品类型
							Log.v("TAG", "提交时     prodType：        " + customer.getProdType());
							params.addBodyParameter("signInfo.special",specialCircumstancesEditText.getText().toString().trim());// 备注，特殊情况
							if (subWriteFile.length() > 0){
								params.addBodyParameter("signInfo.pic", new File(writePath));// 签字图片
								getimage(writePath);
								params.addBodyParameter("signInfo.thumbPic", compressFile);
							} else if (subPhotoFile.length() > 0) {
								params.addBodyParameter("signInfo.pic", new File(photoPathStr));// 拍照图片
							}
							params.addBodyParameter("signInfo.offAmount",reimbursementAmountEditText.getText().toString().trim());// 路费
							if (null!=customer.getIsSign()&&!"".equals(customer.getIsSign())&&"1".equals(customer.getIsSign())) {
								if (!isSignMoreRadioButton.getText().toString().trim().equals("无")) {
									if (isSignZeroRadioButton.isChecked()) {
										params.addBodyParameter("signInfo.renewalAmount","0");// 续费金额为0
									}else if (isSignMoreRadioButton.isChecked()) {
										params.addBodyParameter("signInfo.renewalAmount",isSignMoreRadioButton.getText().toString().trim());// 续费金额
									}
								}else {
									params.addBodyParameter("signInfo.renewalAmount","0");// 续费金额为0
								}
							}else {
								params.addBodyParameter("signInfo.renewalAmount","0");// 续费金额为0
							}
							
							if (null!=customer.getIsVouchRec()&&!"".equals(customer.getIsVouchRec())&&"1".equals(customer.getIsVouchRec())) {
								if (!"无".equals(isVouchRecMoreRadioButton.getText().toString().trim())) {
									if (isVouchRecZeroRadioButton.isChecked()) {
										params.addBodyParameter("signInfo.vouchAmount","0");// 还款金额为0
									}else if (isVouchRecMoreRadioButton.isChecked()) {
										params.addBodyParameter("signInfo.vouchAmount",isVouchRecMoreRadioButton.getText().toString().trim());// 还款金额
									}
								}else {
									params.addBodyParameter("signInfo.vouchAmount","0");// 还款金额为0
								}
							}else {
								params.addBodyParameter("signInfo.vouchAmount","0");// 还款金额为0
							}
						
							params.addBodyParameter("signInfo.isSign",customer.getIsSign());// 是否续费
							params.addBodyParameter("signInfo.isVouchRec",customer.getIsVouchRec());// 是否还款
							if (!"".equals(relationEditText.getText().toString().trim()) && null != relationEditText.getText().toString().trim()) {
								params.addBodyParameter("signInfo.payerName", relationEditText.getText().toString().trim());//付款人姓名
							}
							params.addBodyParameter("signInfo.id",id);// 签字单ID
							HttpUtil.post("custSign.action",params,new RequestCallBack<String>() {
										@Override
										public void onSuccess(ResponseInfo<String> responseInfo) {
											SignResult signResult = GsonUtils.json2bean(responseInfo.result,SignResult.class);
											if (Boolean.parseBoolean(signResult.getSuccess())) {
												if ("0".equals(signResult.getResultData().get(0).getIsServ())) {
													PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
													pinetreeDB.deleteCustomerSign(customer.getId());
													pinetreeDB.saveCustomerSign(setData(writeImageUri_, photoImageUri_));
													pinetreeDB.updateCustomerSign(customer.getId());
													ToastUtils.showToast(getActivity(),"提交成功");
													dialog.hide();
													new Handler().postDelayed(
															new Runnable() {
																@Override
																public void run() {
																	getActivity().finish();
																}
															}, 1000);
												}else if ("1".equals(signResult.getResultData().get(0).getIsServ())) {
													PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
													pinetreeDB.deleteCustomerSign(customer.getId());
													pinetreeDB.saveCustomerSign(setData(writeImageUri_, photoImageUri_));
													pinetreeDB.updateCustomerSign(customer.getId());
													ToastUtils.showToast(getActivity(),"客户签字已经提交过，请勿重复提交！");
													dialog.hide();
													saveCustomerSignBtn.setVisibility(View.GONE);
													submitCustomerSignBtn.setVisibility(View.GONE);
												}
											} else {
												ToastUtils.showToast(getActivity(),signResult.getMessage());
												submitCustomerSignBtn.setClickable(true);
												if ("不能重复提交".equals(signResult.getMessage())) {
													submitCustomerSignBtn.setClickable(false);
												}
												dialog.hide();
											}
												
										}
		
										@Override
										public void onFailure(HttpException error,
												String msg) {
											ToastUtils.showToast(getActivity(),"提交客户签字失败，请重试");
											submitCustomerSignBtn.setClickable(true);
											dialog.hide();
										}
									});
						}else {
							ToastUtils.showToast(getActivity(), "签字不存在，请重新签字");
							submitCustomerSignBtn.setClickable(true);
							dialog.hide();
						}
					} else {
						dialog.hide();
						ToastUtils.showToast(getActivity(), "客户签字不能为空");
						submitCustomerSignBtn.setClickable(true);
						
					}
				}else {
					ToastUtils.showToast(getActivity(), "时间还没到，请勿提交");
					submitCustomerSignBtn.setClickable(true);
					dialog.hide();
				}
			} else {
				ToastUtils.showToast(getActivity(), "亲，请检查网络！");
				submitCustomerSignBtn.setClickable(true);
				dialog.hide();
			}

			break;
		case R.id.special_customerSign_btn://特殊情况
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
			if (NetUtil.checkNetWork(getActivity())) {
				dialog.show();
				RequestParams params = new RequestParams();
				params.addBodyParameter("custID", customer.getCustID());
				HttpUtil.post("querySpecial.action", params, new RequestCallBack<String>() {
					
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						dialog.hide();
						pinetreeDB.deleteSpecialCircumstances(customer.getCustID());
						SpecialCircumstancesBase specialCircumstancesBase = GsonUtils.json2bean(responseInfo.result, SpecialCircumstancesBase.class);
						SpecialCircumstances specialCircumstances = new SpecialCircumstances();
						for (int i = 0; i < specialCircumstancesBase.getResultData().size(); i++) {
							String  special= specialCircumstancesBase.getResultData().get(i).getSpecial();
							specialCircumstances.setSpecial(special);
							specialCircumstances.setCustID(customer.getCustID());
							pinetreeDB.saveSpecialCircumstances(specialCircumstances);
						}
						
						Intent in = new Intent(getActivity(),SpecialCircumstancesActivity.class);
						in.putExtra("custID", customer.getCustID());
						startActivity(in);
						
					}
					@Override
					public void onFailure(HttpException error, String msg) {
						dialog.hide();
						
					}
				});
																																													
			} else {
				Intent in = new Intent(getActivity(),SpecialCircumstancesActivity.class);
				in.putExtra("custID", customer.getCustID());
				startActivity(in);
			}
			
			
			break;
		default:
			break;
		}

	}

	/**
	 * 获取输入框的值设置给对象
	 */
	private CustomerSign setData(String writeImageUri, String photoImageUri) {
		CustomerSign customerSign = new CustomerSign();
		customerSign.setCustomerName(customerNameText.getText().toString().trim());// 客户姓名
		String date = dateText.getText().toString().trim();
		String time = timeText.getText().toString().trim();
		customerSign.setServerDate(date + " " + time);// 服务时间
		customerSign.setServerName(serverNameText.getText().toString().trim());// 服务人员
		customerSign.setReimbursementAmount(reimbursementAmountEditText.getText().toString().trim());// 路费
		customerSign.setSpecialCircumstances(specialCircumstancesEditText.getText().toString().trim());// 特殊情况
		customerSign.setSignImageUri(writeImageUri);
		customerSign.setPhotoImageUri(photoImageUri);
		customerSign.setPayerName(relationEditText.getText().toString().trim());//付款人姓名
		customerSign.setScheduleId(customer.getId());
		if (isSignZeroRadioButton.isChecked()) {//续约
			customerSign.setRenewalAmount("0");
		}else if (isSignMoreRadioButton.isChecked()) {
			if (!"无".equals(isSignMoreRadioButton.getText().toString().trim())) {
				customerSign.setRenewalAmount(isSignMoreRadioButton.getText().toString().trim());
			}else {
				customerSign.setRenewalAmount("0");
			}
		}
		if (isVouchRecZeroRadioButton.isChecked()) {//还款
			customerSign.setVouchAmount("0");
		}else if (isVouchRecMoreRadioButton.isChecked()) {
			if (!"无".equals(isVouchRecMoreRadioButton.getText().toString().trim())) {
				customerSign.setVouchAmount(isVouchRecMoreRadioButton.getText().toString().trim());
			}else {
				customerSign.setVouchAmount("0");
			}
		}
		
		String spinner = signRelationSpinner.getSelectedItem().toString().trim();
		if ("本人".equals(spinner)) {
			customerSign.setRelation("1");
		}else if ("子女".equals(spinner)) {
			customerSign.setRelation("2");
		}else if ("政府".equals(spinner)) {
			customerSign.setRelation("3");
		}else if ("残联".equals(spinner)) {
			customerSign.setRelation("4");
		}else if ("青松赠送".equals(spinner)) {
			customerSign.setRelation("5");
		}else if ("其他".equals(spinner)) {
			customerSign.setRelation("6");
		}
		
		String signSpinner = custSignRelationSpinner.getSelectedItem().toString().trim();
		if ("本人".equals(signSpinner)) {
			customerSign.setSignRelation("1");
		}else if ("配偶".equals(signSpinner)) {
			customerSign.setSignRelation("2");
		}else if ("子女".equals(signSpinner)) {
			customerSign.setSignRelation("3");
		}else if ("父母".equals(signSpinner)) {
			customerSign.setSignRelation("4");
		}else if ("亲属".equals(signSpinner)) {
			customerSign.setSignRelation("5");
		}else if ("家政".equals(signSpinner)) {
			customerSign.setSignRelation("6");
		}else if ("其他".equals(signSpinner)) {
			customerSign.setSignRelation("7");
		}
		return customerSign;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO:
			if (photoPath.exists()) {
				startPhotoZoom(Uri.fromFile(photoPath),150);
			}
			break;
		case WRITE_RESULT:
			File file = new File(writePath);
			if (file.exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(file.getPath()).copy(Config.ARGB_8888, true);
				signImageView.setImageBitmap(bitmap);
				photoList.add(writePath);
				photoPathStr = "";
			}
			break;
		case CUT_PHOTO:
                if (cutFile1.exists()) {
                	photoPath.delete();
                	Bitmap bitmap = BitmapFactory.decodeFile(cutFile1.getPath()).copy(Config.ARGB_8888, true);
    				signImageView.setImageBitmap(bitmap);
    				photoList.add(writePath);
                	writePath = "";
                	photoPathStr = cutFile1.getPath();
				}else {
					if (!"".equals(photoPath.getPath())) {
						if (photoPath.exists()) {
							Bitmap bitmap = BitmapFactory.decodeFile(photoPath.getPath()).copy(Config.ARGB_8888, true);
		    				signImageView.setImageBitmap(bitmap);
		    				photoList.add(photoPath.getPath());
		    				writePath = "";
						}
					}
				}
                
			break;
		default:
			break;
		}
	}
	/**
     * 跳转至系统截图界面进行截图
     * 
     */ 
    private void startPhotoZoom(Uri uri, int size) { 
        Intent intent = new Intent("com.android.camera.action.CROP"); 
        intent.setDataAndType(uri, "image/*"); 
        intent.putExtra("crop", "true"); 
        intent.putExtra("aspectX", 3); 
        intent.putExtra("aspectY", 2); 
        intent.putExtra("outputX", size); 
        intent.putExtra("outputY", (size-50)); 

        File path = SDPath.getSDPath(getActivity());
		if (!path.exists()) {
			path.mkdirs();
		}
		cutFile1 = new File(path,customer.getId() + photoList.size() + "pc.png");
		cutUri = Uri.fromFile(cutFile1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cutUri);  
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString()); //JPEG.toString() 
        		 
        startActivityForResult(intent, CUT_PHOTO); 
    }  
    
    private Bitmap decodeUriAsBitmap(Uri uri){
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

 	
 	//压缩图片
 	private void getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 72f;
        float ww = 128f;
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        compressBitmap = bitmap;
        String cutPhotoPath =  srcPath.substring(0, srcPath.length()-4)+ "c.png";
		File path = SDPath.getSDPath(getActivity());
		if (!path.exists()) {
			path.mkdirs();
		}
		compressFile = new File(cutPhotoPath);
        try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(compressFile));
                compressBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                bos.flush();
                bos.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
 	
}
