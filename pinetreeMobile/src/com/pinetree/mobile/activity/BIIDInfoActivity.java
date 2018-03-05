package com.pinetree.mobile.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BI_IDInformation;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.YangLaoServiceAssessmentReport;
import com.pinetree.mobile.utils.SharedPreferencesUtil;
import com.pinetree.mobile.utils.Tools;

@ContentView(R.layout.activity_bi_info)
public class BIIDInfoActivity extends Activity implements MyBaseInterface,
	OnLongClickListener{
	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	@ViewInject(R.id.tv_IDnumber)
	private TextView tv_IDnumber;
	@ViewInject(R.id.tv_medicarecardnumber)
	private TextView tv_medicarecardnumber;
	@ViewInject(R.id.tv_disabledsoldiercertificate)
	private TextView tv_disabledsoldiercertificate;
	@ViewInject(R.id.tv_disabilitycard)
	private TextView tv_disabilitycard;
	@ViewInject(R.id.et_name)
	EditText etName;
	@ViewInject(R.id.et_nameusedbefore)
	EditText etNameUsedBefore;
	@ViewInject(R.id.et_IDnumber)
	EditText etIDNumber;
	@ViewInject(R.id.et_medicarecardnumber)
	EditText etMedicareCardNumber;
	@ViewInject(R.id.et_disabledsoldiercertificate)
	EditText etDisabledSoldierCertificate;
	@ViewInject(R.id.et_disabilitycard)
	EditText etDisabilityCard;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private String bI_IDInformationList;
	private SharedPreferencesUtil sharedPreferencesUtil;
	private Bundle bundle;
	private Customer customer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
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
		
		customer = (Customer) getIntent().getExtras().getSerializable("customer");
		
		tv_name.setOnLongClickListener(this);
		tv_IDnumber.setOnLongClickListener(this);
		tv_medicarecardnumber.setOnLongClickListener(this);
		tv_disabledsoldiercertificate.setOnLongClickListener(this);
		tv_disabilitycard.setOnLongClickListener(this);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			BI_IDInformation bi = dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				etName.setText(bi.getName());
				etNameUsedBefore.setText(bi.getNameUsedBefore());
				etIDNumber.setText(bi.getIDNumber());
				etMedicareCardNumber.setText(bi.getMedicareCardNumber());
				etDisabledSoldierCertificate.setText(bi.getDisabledSoldierCertificate());
				etDisabilityCard.setText(bi.getDisabilityCard());
				if(bi.getWhetherNew()!=null&&bi.getWhetherNew().equals("1")){
					etName.setEnabled(false);
				}
			}
		} catch (DbException e) {
			
			e.printStackTrace();
		}
		dbUtils.close();
	}

	@OnClick({ R.id.bt_save, R.id.bt_back })
	public void clickMethod(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_save:
			if (isMatch()) {
				DbUtils dbUtils = DbUtils.create(this);
				BI_IDInformation bi = null;
				bi = new BI_IDInformation();
				bi.setID(Integer.parseInt(reportId));
				bi.setReportId(reportId);
				bi.setCustID(reportId);
				bi.setName(etName.getText().toString());
				bi.setNameUsedBefore(etNameUsedBefore.getText().toString());
				bi.setIDNumber(etIDNumber.getText().toString());
				bi.setMedicareCardNumber(etMedicareCardNumber.getText().toString());
				bi.setDisabledSoldierCertificate(etDisabledSoldierCertificate.getText().toString());
				bi.setDisabilityCard(etDisabilityCard.getText().toString());
				bi.setWhetherNew("0");
				try {
					if (dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId)) != null) {
						bi = dbUtils.findFirst(Selector.from(BI_IDInformation.class).where("ReportId", "=", reportId));
						bi.setName(etName.getText().toString());
						bi.setNameUsedBefore(etNameUsedBefore.getText().toString());
						bi.setIDNumber(etIDNumber.getText().toString());
						bi.setMedicareCardNumber(etMedicareCardNumber.getText().toString());
						bi.setDisabledSoldierCertificate(etDisabledSoldierCertificate.getText().toString());
						bi.setDisabilityCard(etDisabilityCard.getText().toString());
						dbUtils.update(bi);
						Toast.makeText(this, getResources().getString(R.string.success_update), Toast.LENGTH_SHORT).show();
					} else {
						dbUtils.save(bi);
						Toast.makeText(this, getResources().getString(R.string.success_save), Toast.LENGTH_SHORT).show();
					}
				} catch (DbException e) {
					e.printStackTrace();
				}
				YangLaoServiceAssessmentReport yangLaoServiceAssessmentReport;
				try {
					if (dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId)) != null) {
						
						yangLaoServiceAssessmentReport=dbUtils.findFirst(Selector.from(YangLaoServiceAssessmentReport.class).where("ReportId", "=", reportId)) ;
						yangLaoServiceAssessmentReport.setCustName(etName.getText().toString());
						dbUtils.update(yangLaoServiceAssessmentReport);
					} else {
						yangLaoServiceAssessmentReport = new YangLaoServiceAssessmentReport();
						yangLaoServiceAssessmentReport.setCustName(etName.getText().toString());
						yangLaoServiceAssessmentReport.setCustID(reportId);
						yangLaoServiceAssessmentReport.setReportId(reportId);
						dbUtils.save(yangLaoServiceAssessmentReport);
					}
				} catch (DbException e) {
					e.printStackTrace();
				}
				dbUtils.close();
			}
			break;

		default:
			break;
		}

	}

	private boolean isMatch() {
		if (!"".equals(etName.getText().toString().trim())) { 
			return true;
		} else {
			Tools.showToast(mContext, getResources().getString(R.string.str_name_noempty));
		}
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		
		Intent intent = new Intent(BIIDInfoActivity.this, NoteActivity.class);
		switch (v.getId()) {
		case R.id.tv_name:

			intent.putExtra("guid", "bi_id_name");

			break;
		case R.id.tv_IDnumber:
			intent.putExtra("guid", "bi_id_id");
			break;
		case R.id.tv_medicarecardnumber:
		case R.id.tv_disabledsoldiercertificate:
		case R.id.tv_disabilitycard:
			intent.putExtra("guid", "bi_id_ebkh");
			break;
		default:
			break;
		}
		startActivity(intent);
		return true;
	}
	
}
