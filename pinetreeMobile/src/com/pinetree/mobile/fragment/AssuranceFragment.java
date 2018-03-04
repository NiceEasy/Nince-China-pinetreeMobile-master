package com.pinetree.mobile.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.Guaranty;
import com.pinetree.mobile.bean.GuarantyMoney;
import com.pinetree.mobile.db.PinetreeDB;
import com.pinetree.mobile.net.HttpUtil;
import com.pinetree.mobile.net.NetUtil;
import com.pinetree.mobile.utils.GsonUtils;
import com.pinetree.mobile.utils.ToastUtils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * 担保金额
 * 
 * @author Administrator
 * 
 */
public class AssuranceFragment extends Fragment implements OnClickListener {
	protected static final int ASSURE_RESULT = 0;// 从网络获取担保金额数据
	protected static final int ADD_ASSURE_DATA = 10;// 添加担保金额
	private static final int ASSURE_RESULT_DB = 20;// 从本地数据库查询担保金额数据
	@ViewInject(R.id.add_assurance_btn)
	private Button addAssuranceBtn;
	@ViewInject(R.id.listView_assurance)
	private ListView listViewAssurance;
	@ViewInject(R.id.linearLayout_assureMoney)
	private LinearLayout assureMoneyLinearLayout;
	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Bundle bundle;
	private Dialog dialog;
	private final Calendar mCalendar = Calendar.getInstance();
	// 获取当前系统时间
	private int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
	private int minute = mCalendar.get(Calendar.MINUTE);
	private int day = mCalendar.get(Calendar.DAY_OF_MONTH);
	private int month = mCalendar.get(Calendar.MONTH);
	private int year = mCalendar.get(Calendar.YEAR);
	private ArrayList<GuarantyMoney> assureList = new ArrayList<GuarantyMoney>();
	private MyAdapter adapter;

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ASSURE_RESULT:// 从网络拿取担保金额数据,设置数据适配器
				assureList = (ArrayList<GuarantyMoney>) msg.obj;
				listViewAssurance.setAdapter(adapter);
				break;
			case ADD_ASSURE_DATA:// 添加担保数据
				GuarantyMoney guarantyMoney = (GuarantyMoney) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
				boolean b = pinetreeDB.saveAddAssure(guarantyMoney);
				if (b) {
					assureList.add(guarantyMoney);
					listViewAssurance.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				} else {
					ToastUtils.showToast(getActivity(), "添加担保失败，请重试");
				}
				break;
			case ASSURE_RESULT_DB:
				assureList = (ArrayList<GuarantyMoney>) msg.obj;
				if (assureList != null && assureList.size() > 0) {
					dialog.hide();
					listViewAssurance.setAdapter(adapter);
				} else {
					dialog.hide();
					ToastUtils.showToast(getActivity(), "亲，没有担保数据");
				}

				break;
			default:
				break;
			}
		};
	};
	private TextView dateTextView;
	private TextView timeTextView;
	private AlertDialog addAssureDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		bundle = this.getArguments();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		View assuranceFragment = inflater.inflate(R.layout.fragment_assurance,
				null, false);
		ViewUtils.inject(this, assuranceFragment);

		dialog = new Dialog(getActivity(), R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();

		adapter = new MyAdapter();

		addAssuranceBtn.setOnClickListener(this);

		if (NetUtil.checkNetWork(getActivity())) {
			RequestParams params = new RequestParams();
			params.addBodyParameter("custID", customer.getCustID());
			HttpUtil.post("browsecurityCost.action", params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							Guaranty guaranty = GsonUtils.json2bean(
									responseInfo.result, Guaranty.class);
							dialog.hide();
							if (!"".equals(guaranty.getSuccess())
									&& Boolean.parseBoolean(guaranty
											.getSuccess())) {
								List<GuarantyMoney> assureMoneyList = guaranty
										.getResultData();
								PinetreeDB pinetreeDB = PinetreeDB
										.getInstance(getActivity());
								pinetreeDB.deleteAllGuarant(customer
										.getCustID());
								for (GuarantyMoney guarantyItem : assureMoneyList) {
									pinetreeDB.saveAssureCache(guarantyItem);
								}
								List<GuarantyMoney> selectAddAssure = pinetreeDB
										.selectAddAssureByCustId(customer
												.getCustID());
								if (selectAddAssure != null
										&& selectAddAssure.size() > 0) {
									assureMoneyList.addAll(selectAddAssure);
								}
								Message message = Message.obtain();
								message.obj = assureMoneyList;
								message.what = ASSURE_RESULT;
								handler.sendMessage(message);
							} else {
								PinetreeDB pinetreeDB = PinetreeDB
										.getInstance(getActivity());
								List<GuarantyMoney> assureMoneyList = pinetreeDB
										.selectAddAssureByCustId(customer
												.getCustID());
								if (assureMoneyList != null
										&& assureMoneyList.size() > 0) {
									Message message = Message.obtain();
									message.obj = assureMoneyList;
									message.what = ASSURE_RESULT_DB;
									handler.sendMessage(message);
								} else {
									ToastUtils.showToast(getActivity(),
											"亲，暂无担保数据！");
								}
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							dialog.hide();
						}
					});

		} else {
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(getActivity());
			ArrayList<GuarantyMoney> assureList = new ArrayList<GuarantyMoney>();
			List<GuarantyMoney> selectAssureCache = pinetreeDB
					.selectAssureCacheByCustId(customer.getCustID());// 缓存表中的数据
			List<GuarantyMoney> selectAddAssure = pinetreeDB
					.selectAddAssureByCustId(customer.getCustID());// 添加单表中的数据
			if (selectAssureCache != null && selectAssureCache.size() > 0) {
				assureList.addAll(selectAssureCache);
			}
			if (selectAddAssure != null && selectAddAssure.size() > 0) {
				assureList.addAll(selectAddAssure);
			}
			Message message = Message.obtain();
			message.obj = assureList;
			message.what = ASSURE_RESULT_DB;
			handler.sendMessage(message);
		}

		return assuranceFragment;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_assurance_btn:// 弹出一个添加担保的框
			String addState="";
			for (int i = 0; i < assureList.size(); i++) {
				String tempStatus = assureList.get(i).getStatus();
				if (tempStatus.equals("-1")||tempStatus.equals("0")||tempStatus.equals("1")) {
					addState="1";
				}
			}
			if ("".equals(addState)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("为客户 " + customer.getCustName() + " 添加担保：");
				final View view = getActivity().getLayoutInflater().inflate(
						R.layout.activity_add_assurance, null);
				dateTextView = (TextView) view.findViewById(R.id.date_textView);
				timeTextView = (TextView) view.findViewById(R.id.time_textView);
				dateTextView.setText(new StringBuffer().append(pad(year))
						.append("-").append(pad(month + 1)).append("-")
						.append(pad(day)));
				timeTextView.setText(new StringBuilder().append(pad(hourOfDay))
						.append(":").append(pad(minute)));
				dateTextView.setOnClickListener(this);
				timeTextView.setOnClickListener(this);
				builder.setView(view);
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Field field;
								try {
									field = addAssureDialog.getClass()
											.getSuperclass()
											.getDeclaredField("mShowing");
									field.setAccessible(true);
									field.setBoolean(addAssureDialog, true);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						});
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								try {
									Field field = addAssureDialog.getClass()
											.getSuperclass()
											.getDeclaredField("mShowing");
									field.setAccessible(true);
									field.setBoolean(addAssureDialog, false);
									EditText assureMoneyEditText = (EditText) view
											.findViewById(R.id.assure_money_editText);
									String assureMoney = assureMoneyEditText
											.getText().toString().trim();
									if ("".equals(assureMoney)) {
										ToastUtils.showToast(getActivity(),
												"担保金额不能为空");
									} else {
										Message message = Message.obtain();
										GuarantyMoney guarantyMoney = new GuarantyMoney();
										guarantyMoney.setVouchDate(dateTextView
												.getText().toString().trim()
												+ "T"
												+ timeTextView.getText().toString()
														.trim() + ":00");
										guarantyMoney.setCustID(customer
												.getCustID());
										guarantyMoney.setCustName(customer
												.getCustName());
										guarantyMoney.setEmployeeID(employeeId);
										guarantyMoney.setEmployeeName(employeeName);
										guarantyMoney.setAccount(assureMoney);
										guarantyMoney.setStatus("-1");
										message.obj = guarantyMoney;
										message.what = ADD_ASSURE_DATA;
										handler.sendMessage(message);
										field.setBoolean(addAssureDialog, true);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						});

				addAssureDialog = builder.create();
				addAssureDialog.show();
			}else {
				ToastUtils.showToast(getActivity(), "该客户不能再担保啦");
			}
			break;
		case R.id.date_textView:// 日期控件
			new DatePickerDialog(getActivity(), new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					dateTextView.setText(new StringBuffer().append(pad(year))
							.append("-").append(pad(monthOfYear + 1))
							.append("-").append(pad(dayOfMonth)));
				}
			}, year, month, day).show();
			break;
		case R.id.time_textView:// 时间控件
			new TimePickerDialog(getActivity(), new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					timeTextView.setText(new StringBuilder()
							.append(pad(hourOfDay)).append(":")
							.append(pad(minute)));
				}
			}, hourOfDay, minute, true).show();
			break;
		default:
			break;
		}
	}

	/**
	 * 数据适配器
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return assureList.size();
		}

		@Override
		public Object getItem(int position) {
			return assureList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.fragment_assurance_listview_item, null);
				new ViewHolder(convertView);
			}
			final ViewHolder holder = (ViewHolder) convertView.getTag();
			final GuarantyMoney guarantyMoney = assureList.get(position);
			holder.assureClient_text.setText(guarantyMoney.getCustName());
			holder.assureMoney_text.setText(guarantyMoney.getAccount());
			holder.servePeople_text.setText(guarantyMoney.getEmployeeName());
			// 截取时间
			String time = guarantyMoney.getVouchDate();
			if (!"".equals(time)) {
				String servetime = time.substring(0, 10) + " "
						+ time.substring(11, 16);
				holder.serveTime_text.setText(servetime);
			}

			String status = guarantyMoney.getStatus();
			if ("-1".equals(status)) {
				holder.commitAssuranceButton.setText("提交");
				holder.commitAssuranceButton
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.show();
								RequestParams params = new RequestParams();
								params.addBodyParameter("securityCoust.custID",
										guarantyMoney.getCustID());
								params.addBodyParameter(
										"securityCoust.vouchDate",
										guarantyMoney.getVouchDate());
								params.addBodyParameter(
										"securityCoust.account",
										guarantyMoney.getAccount());
								params.addBodyParameter(
										"securityCoust.employeeID",
										guarantyMoney.getEmployeeID());
								params.addBodyParameter("empID",guarantyMoney.getEmployeeID());
								HttpUtil.post("securityCostAdd.action", params,
										new RequestCallBack<String>() {
											@Override
											public void onSuccess(
													ResponseInfo<String> responseInfo) {
												GlobalResult result = GsonUtils
														.json2bean(
																responseInfo.result,
																GlobalResult.class);
												dialog.hide();
												if (!"".equals(result
														.getSuccess())
														&& Boolean.valueOf(result
																.getSuccess())) {
													holder.commitAssuranceButton
															.setText("待审核");
													holder.commitAssuranceButton
															.setClickable(false);
													PinetreeDB pinetreeDB = PinetreeDB
															.getInstance(getActivity());
													pinetreeDB.updateAddAssureNotShow(guarantyMoney.getCustID(),guarantyMoney.getVouchDate());
													guarantyMoney.setStatus("0");
													adapter.notifyDataSetChanged();
												} else {
													ToastUtils.showToast(getActivity(),"提交担保失败，请重试");
												}
											}

											@Override
											public void onFailure(HttpException error,String msg) {
												dialog.hide();
												ToastUtils.showToast(getActivity(),"提交担保失败，请重试");
											}
										});
							}
						});
			} else if ("0".equals(status)) {
				holder.commitAssuranceButton.setText("待审核");
				holder.commitAssuranceButton.setClickable(false);
			} else if ("1".equals(status)) {
				holder.commitAssuranceButton.setText("通过");
				holder.commitAssuranceButton.setClickable(false);
			} else if ("2".equals(status)) {
				holder.commitAssuranceButton.setText("未通过");
				holder.commitAssuranceButton.setClickable(false);
			} else if ("3".equals(status)) {
				holder.commitAssuranceButton.setText("完成");
				holder.commitAssuranceButton.setClickable(false);
			}
			return convertView;
		}
	}

	class ViewHolder {
		public TextView assureClient_text;
		public TextView assureMoney_text;
		public TextView servePeople_text;
		public TextView serveTime_text;
		public Button commitAssuranceButton;

		public ViewHolder(View view) {
			assureClient_text = (TextView) view
					.findViewById(R.id.custName_textView_listView);
			assureMoney_text = (TextView) view
					.findViewById(R.id.price_textView_listView);
			servePeople_text = (TextView) view
					.findViewById(R.id.empName_textView_listView);
			serveTime_text = (TextView) view
					.findViewById(R.id.date_textView_listView);
			commitAssuranceButton = (Button) view
					.findViewById(R.id.commit_Assurance_button);
			view.setTag(this);
		}
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
