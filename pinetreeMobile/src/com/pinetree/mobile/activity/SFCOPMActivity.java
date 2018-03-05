package com.pinetree.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import com.pinetree.mobile.bean.Copm;
import com.pinetree.mobile.bean.CopmInfo;
import com.pinetree.mobile.bean.SF_COPM;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;
import com.pinetree.mobile.utils.Request;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_sf_copm)
public class SFCOPMActivity extends Activity implements MyBaseInterface {
	@ViewInject(R.id.tl_copm)
	TableLayout tl_copm;
	@ViewInject(R.id.bt_add_copm)
	Button btAddCopm;
	@ViewInject(R.id.tv_xianPoint)
	TextView tv_xianPoint;
	@ViewInject(R.id.tv_zuoyeChange)
	TextView tv_zuoyeChange;
	@ViewInject(R.id.tv_manPoint)
	TextView tv_manPoint;
	@ViewInject(R.id.tv_manChange)
	TextView tv_manChange;
	@ViewInject(R.id.et_specialAccount)
	TextView et_specialAccount;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	private List<Integer> listScore;
	private int score;
	private LayoutInflater inflater;
	private int rowNumOld = 0;
	private int rowNum = 0;
	private List<TableRow> listTr;
	private List<Integer> listSituation;
	private List<Integer> listSatisf;
	private List<TextView> listtvSituation;
	private List<TextView> listtvSatisf;
	Double sumSituation = 0.0, sumSatisf = 0.0;
	Double historySumSituationOld = 0.0, historySumSatisfOld = 0.0;
	Double historySumSituation = 0.0, historySumSatisf = 0.0;
	Double newSumSituation = 0.0, newSumSatisf = 0.0;
	int hasEmptyNum = 0;
	Request request;

	public Handler Myhandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			Tools.stopProgressDialog(mContext);
			if (msg.arg1 == 101) {
				String str = msg.getData().getString("result");
				int flag = msg.getData().getInt("flag");
				if (flag == 1) {
					Log.e("test", "text:" + str);
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
					DbUtils dbUtils = DbUtils.create(mContext);
					YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport;
					try {
						yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("reportId", "=", reportId));
						SF_COPM sf_COPM = dbUtils.findFirst(Selector.from(SF_COPM.class).where("reportId", "=", reportId));
						if (copms.size() > 0) {

							if (yangLaoServiceAssessmentReport != null && yangLaoServiceAssessmentReport.getIsUpload() != null && yangLaoServiceAssessmentReport.getIsUpload().equals("1")) {// ���ϱ�
								Gson gson = new Gson();
								String jsonData = gson.toJson(copms);

								try {
									if (sf_COPM == null) {
										sf_COPM = new SF_COPM();
										sf_COPM.setCopm(jsonData);
										sf_COPM.setReportId(reportId);
										sf_COPM.setSituationSum(copmInfos.get(0).getSituationSum());
										sf_COPM.setSatisfSum(copmInfos.get(0).getSatisfSum());
										dbUtils.save(sf_COPM);
									} else {
										sf_COPM.setCopm(jsonData);
										sf_COPM.setSituationSum(copmInfos.get(0).getSituationSum());
										sf_COPM.setSatisfSum(copmInfos.get(0).getSatisfSum());
										dbUtils.update(sf_COPM);
									}
								} catch (DbException e) {
									e.printStackTrace();
								}
								dbUtils.close();
								for (int i = 0; i < copms.size(); i++) {
									addRow();
								}
								if (copmInfos.get(0).getSituationSum() != null && !copmInfos.get(0).getSituationSum().equals(""))
									historySumSituationOld = Double.parseDouble(copmInfos.get(0).getSituationSum());
								if (copmInfos.get(0).getSatisfSum() != null && !copmInfos.get(0).getSatisfSum().equals(""))
									historySumSatisfOld = Double.parseDouble(copmInfos.get(0).getSatisfSum());
								setNowCopmsDisable(copms);
								setSum();

							} else {// δ�ϱ�
								if (sf_COPM == null) {
									sf_COPM = new SF_COPM();
									Gson gson = new Gson();
									String jsonData = gson.toJson(copms);
									sf_COPM.setCopm(jsonData);
									sf_COPM.setReportId(reportId);
									sf_COPM.setSituationSum(copmInfos.get(0).getSituationSum());
									sf_COPM.setSatisfSum(copmInfos.get(0).getSatisfSum());
									dbUtils.save(sf_COPM);
								} else {
									List<Copm> copmsHasSave = Tools.getCopmFromJson(sf_COPM.getCopm());
									for (int i = 0; i < copmsHasSave.size(); i++) {
										Copm copm = copmsHasSave.get(i);
										if (copm.getIsLast().equals("1")) {
											copms.add(copm);// �Լ�����������
										}
									}
									Gson gson = new Gson();
									String jsonData = gson.toJson(copms);
									sf_COPM.setCopm(jsonData);// �浽����
									dbUtils.update(sf_COPM);

								}
								dbUtils.close();
								for (int i = 0; i < copms.size(); i++) {
									addRow();
								}
								if (copmInfos.get(0).getSituationSum() != null && !copmInfos.get(0).getSituationSum().equals(""))
									historySumSituationOld = Double.parseDouble(copmInfos.get(0).getSituationSum());
								if (copmInfos.get(0).getSatisfSum() != null && !copmInfos.get(0).getSatisfSum().equals(""))
									historySumSatisfOld = Double.parseDouble(copmInfos.get(0).getSatisfSum());
								setNowCopms(copms);
								setSum();
							}

						} else {
							if (sf_COPM != null && sf_COPM.getID() > 0) {
								copms = Tools.getCopmFromJson(sf_COPM.getCopm());
								if (copms.size() > 0) {
									for (int i = 0; i < copms.size(); i++) {
										addRow();
									}
									if (sf_COPM.getSituationSum() != null && !sf_COPM.getSituationSum().equals(""))
										historySumSituationOld = Double.parseDouble(sf_COPM.getSituationSum());
									if (sf_COPM.getSatisfSum() != null && !sf_COPM.getSatisfSum().equals(""))
										historySumSatisfOld = Double.parseDouble(sf_COPM.getSatisfSum());
									if (yangLaoServiceAssessmentReport != null && yangLaoServiceAssessmentReport.getIsUpload() != null && yangLaoServiceAssessmentReport.getIsUpload().equals("1")) {
										setNowCopmsDisable(copms);
									} else {
										setNowCopms(copms);
									}
									setSum();
								}
							}
						}
					} catch (DbException e1) {
						
						e1.printStackTrace();
					}

				} else {
					DbUtils dbUtils = DbUtils.create(mContext);
					YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport;
					try {
						yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("reportId", "=", reportId));
						SF_COPM sf_COPM = dbUtils.findFirst(Selector.from(SF_COPM.class).where("reportId", "=", reportId));
						if (sf_COPM != null && sf_COPM.getID() > 0) {
							List<Copm> copms = Tools.getCopmFromJson(sf_COPM.getCopm());
							if (copms.size() > 0) {
								for (int i = 0; i < copms.size(); i++) {
									addRow();
								}
								if (sf_COPM.getSituationSum() != null && !sf_COPM.getSituationSum().equals(""))
									historySumSituationOld = Double.parseDouble(sf_COPM.getSituationSum());
								if (sf_COPM.getSatisfSum() != null && !sf_COPM.getSatisfSum().equals(""))
									historySumSatisfOld = Double.parseDouble(sf_COPM.getSatisfSum());
								if (yangLaoServiceAssessmentReport != null && yangLaoServiceAssessmentReport.getIsUpload() != null && yangLaoServiceAssessmentReport.getIsUpload().equals("1")) {
									setNowCopmsDisable(copms);
								} else {
									setNowCopms(copms);
								}
								setSum();
							}
						}
					} catch (DbException e) {
						
						e.printStackTrace();
					}
				}
				Tools.stopProgressDialog(mContext);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewUtils.inject(this);
		mContext = this;
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		reportId = sharedPreferencesUtil.get("reportId");
		inflater = getLayoutInflater();
		listTr = new ArrayList<TableRow>();
		listSituation = new ArrayList<Integer>();
		listSatisf = new ArrayList<Integer>();
		request = new Request(Myhandler);
	}

	@Override
	public void initData() {
		

		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport = dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId));
			SF_COPM sf_COPM = dbUtils.findFirst(Selector.from(SF_COPM.class).where("reportId", "=", reportId));
			boolean hasRemoteCopm = false;
			if (sf_COPM != null && sf_COPM.getCopm() != null) {
				List<Copm> copmsHasSave = Tools.getCopmFromJson(sf_COPM.getCopm());

				for (int i = 0; i < copmsHasSave.size(); i++) {
					Copm copm = copmsHasSave.get(i);
					if (copm.getIsLast().equals("0")) {
						hasRemoteCopm = true;
					}
				}
			}
			if (yangLaoServiceAssessmentReport != null && yangLaoServiceAssessmentReport.getID() > 0) {
				if (yangLaoServiceAssessmentReport.getIsUpload().equals("0") && hasRemoteCopm) {
					List<Copm> copms = Tools.getCopmFromJson(sf_COPM.getCopm());
					if (copms.size() > 0) {
						for (int i = 0; i < copms.size(); i++) {
							addRow();
						}
						if (sf_COPM.getSituationSum() != null && !sf_COPM.getSituationSum().equals(""))
							historySumSituationOld = Double.parseDouble(sf_COPM.getSituationSum());
						if (sf_COPM.getSatisfSum() != null && !sf_COPM.getSatisfSum().equals(""))
							historySumSatisfOld = Double.parseDouble(sf_COPM.getSatisfSum());
						setNowCopms(copms);
						setSum();
					}
				} else {
					if (yangLaoServiceAssessmentReport.getScheduleId() != null && yangLaoServiceAssessmentReport.getCustID() != null && !yangLaoServiceAssessmentReport.getScheduleId().equals("")
							&& !yangLaoServiceAssessmentReport.getCustID().equals("")) {
						Tools.startProgressDialog(mContext, "���ڼ���...");
						request.getCopmsRequest(yangLaoServiceAssessmentReport.getScheduleId(), yangLaoServiceAssessmentReport.getCustID());
					}else{
						if(sf_COPM != null && sf_COPM.getCopm() != null){
							List<Copm> copms = Tools.getCopmFromJson(sf_COPM.getCopm());
							if (copms.size() > 0) {
								for (int i = 0; i < copms.size(); i++) {
									addRow();
								}
								if (sf_COPM.getSituationSum() != null && !sf_COPM.getSituationSum().equals(""))
									historySumSituationOld = Double.parseDouble(sf_COPM.getSituationSum());
								if (sf_COPM.getSatisfSum() != null && !sf_COPM.getSatisfSum().equals(""))
									historySumSatisfOld = Double.parseDouble(sf_COPM.getSatisfSum());
								setNowCopms(copms);
								setSum();
							}
							}
					}
				}

			}else{
				if(sf_COPM != null && sf_COPM.getCopm() != null){
				List<Copm> copms = Tools.getCopmFromJson(sf_COPM.getCopm());
				if (copms.size() > 0) {
					for (int i = 0; i < copms.size(); i++) {
						addRow();
					}
					if (sf_COPM.getSituationSum() != null && !sf_COPM.getSituationSum().equals(""))
						historySumSituationOld = Double.parseDouble(sf_COPM.getSituationSum());
					if (sf_COPM.getSatisfSum() != null && !sf_COPM.getSatisfSum().equals(""))
						historySumSatisfOld = Double.parseDouble(sf_COPM.getSatisfSum());
					setNowCopms(copms);
					setSum();
				}
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		dbUtils.close();

	}

	@OnClick({ R.id.bt_save, R.id.bt_back, R.id.bt_add_copm })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:

			DbUtils dbUtils = DbUtils.create(this);
			SF_COPM bi = null;
			bi = new SF_COPM();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			Gson gson = new Gson();
			String jsonData = gson.toJson(getNowCopms());
			if (hasEmptyNum > 0) {
				Toast.makeText(this, getResources().getString(R.string.str_hasEmptyContent), Toast.LENGTH_SHORT).show();

			} else {
				bi.setCopm(jsonData);
				bi.setXianPoint(tv_xianPoint.getText().toString());
				bi.setZuoyeChange(tv_zuoyeChange.getText().toString());
				bi.setManPoint(tv_manPoint.getText().toString());
				bi.setManChange(tv_manChange.getText().toString());
				bi.setSpecialAccount(et_specialAccount.getText().toString());

				try {
					if (dbUtils.findFirst(Selector.from(SF_COPM.class).where("ReportId", "=", reportId)) != null) {

						bi = dbUtils.findFirst(Selector.from(SF_COPM.class).where("ReportId", "=", reportId));
						bi.setCopm(jsonData);
						bi.setXianPoint(tv_xianPoint.getText().toString());
						bi.setZuoyeChange(tv_zuoyeChange.getText().toString());
						bi.setManPoint(tv_manPoint.getText().toString());
						bi.setManChange(tv_manChange.getText().toString());
						bi.setSpecialAccount(et_specialAccount.getText().toString());
						dbUtils.update(bi);
						Toast.makeText(this, getResources().getString(R.string.success_update), Toast.LENGTH_SHORT).show();
					} else {
						dbUtils.save(bi);
						Toast.makeText(this, getResources().getString(R.string.success_save), Toast.LENGTH_SHORT).show();
					}
				} catch (DbException e) {
					e.printStackTrace();
				}
				dbUtils.close();
			}
			break;

		case R.id.bt_add_copm:
			addRow();
			break;
		default:
			break;
		}

	}

	private void addRow() {
		TableRow tr = (TableRow) inflater.inflate(R.layout.item_copm, null);
		tr.setId(rowNum + (int) SystemClock.currentThreadTimeMillis() / 1000);
		listTr.add(tr);
		rowNum++;
		setSum();
		Button bt_delete = (Button) tr.findViewById(R.id.bt_delete_copm);
		bt_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				TableRow tableRow = (TableRow) v.getParent();
				tl_copm.removeView(tableRow);
				listTr.remove(tableRow);
				rowNum--;
				setSum();
			}
		});
		EditText et_situation = (EditText) tr.findViewById(R.id.et2);
		et_situation.setText("0.0");
		et_situation.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				setSum();
			}
		});
		EditText et_satisf = (EditText) tr.findViewById(R.id.et3);
		et_satisf.setText("0.0");
		et_satisf.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				setSum();
			}
		});

		tl_copm.addView(tr);
	}

	private List<Copm> getNowCopms() {
		hasEmptyNum = 0;
		List<Copm> copms = new ArrayList<Copm>();
		sumSituation = 0.0;
		sumSatisf = 0.0;
		for (int i = 0; i < rowNum; i++) {
			Copm co = new Copm();
			EditText et1 = (EditText) listTr.get(i).findViewById(R.id.et1);
			EditText et2 = (EditText) listTr.get(i).findViewById(R.id.et2);
			EditText et3 = (EditText) listTr.get(i).findViewById(R.id.et3);
			Button bt_delete_copm = (Button) listTr.get(i).findViewById(R.id.bt_delete_copm);
			String content = et1.getText().toString();
			if (!content.trim().equals("")) {
				co.setContent(content);
				String situation = et2.getText().toString();
				String satisf = et3.getText().toString();
				if (!situation.trim().equals("")) {
					if (situation.lastIndexOf(".") == situation.length() - 1) {
						situation = situation.substring(0, situation.length() - 1);
					}
				}
				if (!satisf.trim().equals("")) {
					if (satisf.lastIndexOf(".") == satisf.length() - 1) {
						satisf = satisf.substring(0, satisf.length() - 1);
					}
				}
				co.setSituation(situation);
				co.setSatisf(satisf);
				if (bt_delete_copm.getVisibility() == View.INVISIBLE) {
					co.setIsLast("0");
				} else {
					co.setIsLast("1");
				}
				copms.add(co);
			} else {
				hasEmptyNum++;
			}
		}
		return copms;
	}

	private void setSum() {
		sumSituation = 0.0;
		sumSatisf = 0.0;
		historySumSituation = 0.0;
		historySumSatisf = 0.0;
		newSumSituation = 0.0;
		newSumSatisf = 0.0;
		for (int i = 0; i < rowNum; i++) {
			EditText et1 = (EditText) listTr.get(i).findViewById(R.id.et1);
			EditText et2 = (EditText) listTr.get(i).findViewById(R.id.et2);
			EditText et3 = (EditText) listTr.get(i).findViewById(R.id.et3);
			Button bt_delete_copm = (Button) listTr.get(i).findViewById(R.id.bt_delete_copm);
			String content = et1.getText().toString();
			String situation = et2.getText().toString();
			String satisf = et3.getText().toString();
			if (!situation.trim().equals("")) {
				if (situation.lastIndexOf(".") == situation.length() - 1) {
					situation = situation.substring(0, situation.length() - 1);
				}
				sumSituation = sumSituation + Double.parseDouble(situation);
				if (bt_delete_copm.getVisibility() == View.INVISIBLE) {
					historySumSituation = historySumSituation + Double.parseDouble(situation);
				} else {
					newSumSituation = newSumSituation + Double.parseDouble(situation);
				}
			}
			if (!satisf.trim().equals("")) {
				if (satisf.lastIndexOf(".") == satisf.length() - 1) {
					satisf = satisf.substring(0, satisf.length() - 1);
				}
				sumSatisf = sumSatisf + Double.parseDouble(satisf);
				if (bt_delete_copm.getVisibility() == View.INVISIBLE) {
					historySumSatisf = historySumSatisf + Double.parseDouble(satisf);
				} else {
					newSumSatisf = newSumSatisf + Double.parseDouble(satisf);
				}
			}
		}
		tv_xianPoint.setText((double) (Math.round((sumSituation / rowNum) * 10) / 10.0) + "");
		tv_manPoint.setText((double) (Math.round((sumSatisf / rowNum) * 10) / 10.0) + "");
		if (historySumSituation == 0.0) {
			tv_zuoyeChange.setText("0.0");
		} else {
			tv_zuoyeChange.setText((double) (Math.round(((historySumSituation - historySumSituationOld) / rowNumOld) * 10) / 10.0) + "");
		}
		if (historySumSatisf == 0.0) {
			tv_manChange.setText("0.0");
		} else {
			tv_manChange.setText((double) (Math.round(((historySumSatisf - historySumSatisfOld) / rowNumOld) * 10) / 10.0) + "");
		}

	}

	private void setNowCopms(List<Copm> copms) {

		rowNumOld = 0;
		for (int i = 0; i < copms.size(); i++) {
			Copm co = copms.get(i);
			EditText et1 = (EditText) listTr.get(i).findViewById(R.id.et1);
			EditText et2 = (EditText) listTr.get(i).findViewById(R.id.et2);
			EditText et3 = (EditText) listTr.get(i).findViewById(R.id.et3);
			Button bt_delete_copm = (Button) listTr.get(i).findViewById(R.id.bt_delete_copm);
			et1.setText(co.getContent());
			et2.setText(co.getSituation());
			et3.setText(co.getSatisf());
			if (co.getIsLast().equals("0")) {
				bt_delete_copm.setVisibility(View.INVISIBLE);
				et1.setEnabled(false);
				rowNumOld++;
			}
		}
	}

	private void setNowCopmsDisable(List<Copm> copms) {

		rowNumOld = 0;
		for (int i = 0; i < copms.size(); i++) {
			Copm co = copms.get(i);
			EditText et1 = (EditText) listTr.get(i).findViewById(R.id.et1);
			EditText et2 = (EditText) listTr.get(i).findViewById(R.id.et2);
			EditText et3 = (EditText) listTr.get(i).findViewById(R.id.et3);
			Button bt_delete_copm = (Button) listTr.get(i).findViewById(R.id.bt_delete_copm);
			et1.setText(co.getContent());
			et2.setText(co.getSituation());
			et3.setText(co.getSatisf());
			if (co.getIsLast().equals("0")) {
				bt_delete_copm.setVisibility(View.INVISIBLE);
				et1.setEnabled(false);
				et2.setEnabled(false);
				et3.setEnabled(false);
				rowNumOld++;
			}
		}
	}
}