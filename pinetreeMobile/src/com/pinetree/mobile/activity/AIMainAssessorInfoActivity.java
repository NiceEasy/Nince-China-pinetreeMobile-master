package com.pinetree.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.pinetree.mobile.bean.AI_MainAssessorInformation;
import com.pinetree.mobile.utils.SharedPreferencesUtil;

@ContentView(R.layout.activity_bi_management_info)
public class AIMainAssessorInfoActivity extends Activity implements MyBaseInterface {
	@ViewInject(R.id.et_Name)
	private EditText et_Name;
	@ViewInject(R.id.et_Organ)
	private EditText et_Organ;
	@ViewInject(R.id.et_Address)
	private EditText et_Address;
	@ViewInject(R.id.et_HomePhone)
	private EditText et_HomePhone;
	@ViewInject(R.id.et_Mobile)
	private EditText et_Mobile;
	@ViewInject(R.id.et_PostalCode)
	private EditText et_PostalCode;
	@ViewInject(R.id.et_EMail)
	private EditText et_EMail;
	@ViewInject(R.id.tv_second_title)
	private TextView tv_second_title;
	@ViewInject(R.id.bt_save)
	Button btSave;
	@ViewInject(R.id.bt_back)
	Button btBack;
	private Context mContext;
	private String reportId = "";
	private SharedPreferencesUtil sharedPreferencesUtil;
	@ResInject(id = R.string.str_MainAssessorInformation, type = ResType.String)
	private String str_MainAssessorInformation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	@Override
	public void initView() {
		
		reportId = sharedPreferencesUtil.get("reportId");
		tv_second_title.setText(str_MainAssessorInformation);
	}

	@Override
	public void initData() {
		
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			AI_MainAssessorInformation bi = dbUtils.findFirst(Selector.from(AI_MainAssessorInformation.class).where("ReportId", "=", reportId));
			if (bi != null && bi.getID() > 0) {
				et_Name.setText(bi.getName());

				//et_Organ.setText(bi.getBelongInstitution());
				et_Organ.setText("青松老年看护服务（北京）有限公司");
				//et_Address.setText(bi.getHomeAddress());
				et_Address.setText("北京市朝阳区东三环北路乙2号海南航空大厦B座7层");
				//et_HomePhone.setText(bi.getHomePhone());
				et_HomePhone.setText("010-60845225");
				et_Mobile.setText(bi.getMobile());
				//et_PostalCode.setText(bi.getPostalCode());
				et_PostalCode.setText("100020");
				et_EMail.setText(bi.getEMail());
			}
			else
			{
				et_Organ.setText("青松老年看护服务（北京）有限公司");
				et_Address.setText("北京市朝阳区东三环北路乙2号海南航空大厦B座7层");
				et_HomePhone.setText("010-60845225");
				et_PostalCode.setText("100020");
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
			DbUtils dbUtils = DbUtils.create(this);
			AI_MainAssessorInformation bi = null;
			bi = new AI_MainAssessorInformation();
			bi.setID(Integer.parseInt(reportId));
			bi.setReportId(reportId);
			bi.setName(et_Name.getText().toString());
			bi.setBelongInstitution(et_Organ.getText().toString());
			bi.setHomeAddress(et_Address.getText().toString());
			bi.setHomePhone(et_HomePhone.getText().toString());
			bi.setMobile(et_Mobile.getText().toString());
			bi.setPostalCode(et_PostalCode.getText().toString());
			bi.setEMail(et_EMail.getText().toString());
			try {
				if (dbUtils.findFirst(Selector.from(AI_MainAssessorInformation.class).where("ReportId", "=", reportId)) != null) {
					bi = dbUtils.findFirst(Selector.from(AI_MainAssessorInformation.class).where("ReportId", "=", reportId));
					bi.setName(et_Name.getText().toString());
					bi.setBelongInstitution(et_Organ.getText().toString());
					bi.setHomeAddress(et_Address.getText().toString());
					bi.setHomePhone(et_HomePhone.getText().toString());
					bi.setMobile(et_Mobile.getText().toString());
					bi.setPostalCode(et_PostalCode.getText().toString());
					bi.setEMail(et_EMail.getText().toString());
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
			break;

		default:
			break;
		}

	}
}
