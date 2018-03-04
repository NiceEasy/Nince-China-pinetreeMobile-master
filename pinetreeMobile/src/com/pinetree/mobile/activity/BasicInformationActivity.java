package com.pinetree.mobile.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.BasicInformation;
import com.pinetree.mobile.bean.BasicInformationBase;
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
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 基本情况
 */

public class BasicInformationActivity extends Activity implements
		OnClickListener {

	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	
	@ViewInject(R.id.back_basicInformation_imageView)
	private ImageView backBasicInformationImageView;
	/**
	 * 客户姓名
	 */
	@ViewInject(R.id.custName_textView)
	private TextView custNameTextView;
	/**
	 * 客户年龄
	 */
	@ViewInject(R.id.cust_age_textView)
	private TextView custAgeTextView;
	/**
	 * 客户电话
	 */
	@ViewInject(R.id.custPhone_textView)
	private TextView custPhoneTextView;
	/**
	 * 客户地址
	 */
	@ViewInject(R.id.custAddress_textView)
	private TextView custAddressTextView;
	/**
	 * 婚姻状况
	 */
	@ViewInject(R.id.marriage_radioGroup)
	private RadioGroup marriageRadioGroup;
	/**
	 * 婚姻 未婚
	 */
	@ViewInject(R.id.wh_radioButton)
	private RadioButton whRadioButton;
	/**
	 * 婚姻 已婚
	 */
	@ViewInject(R.id.yh_radioButton)
	private RadioButton yhRadioButton;
	/**
	 * 婚姻 再婚
	 */
	@ViewInject(R.id.zh_radioButton)
	private RadioButton zhRadioButton;
	/**
	 * 婚姻 丧偶
	 */
	@ViewInject(R.id.so_radioButton)
	private RadioButton soRadioButton;
	/**
	 * 婚姻 离异
	 */
	@ViewInject(R.id.ly_radioButton)
	private RadioButton lyRadioButton;
	/**
	 * 退休前职业
	 */
	@ViewInject(R.id.occupational_radioGroup)
	private RadioGroup occupationalRadioGroup;
	/**
	 * 退休前职业 干部
	 */
	@ViewInject(R.id.gb_radioButton)
	private RadioButton gbRadioButton;
	/**
	 * 退休前职业 工人
	 */
	@ViewInject(R.id.gr_radioButton)
	private RadioButton grRadioButton;
	/**
	 * 退休前职业 农民
	 */
	@ViewInject(R.id.nm_radioButton)
	private RadioButton nmRadioButton;
	/**
	 * 退休前职业 文化工作者
	 */
	@ViewInject(R.id.whgz_radioButton)
	private RadioButton whgzRadioButton;
	/**
	 * 退休前职业 其他
	 */
	@ViewInject(R.id.occupational_qt_editText)
	private EditText occupationalQtEditText;
	/**
	 * 子女情况 无子女
	 */
	@ViewInject(R.id.child_wzn_checkBox)
	private CheckBox childWznCheckBox;
	/**
	 * 子女情况 有儿子
	 */
	@ViewInject(R.id.child_yez_checkBox)
	private CheckBox childYezCheckBox;
	/**
	 * 子女情况 有女儿
	 */
	@ViewInject(R.id.child_yne_checkBox)
	private CheckBox childYneCheckBox;
	/**
	 * 子女情况 领养
	 */
	@ViewInject(R.id.child_ly_checkBox)
	private CheckBox childLyCheckBox;
	/**
	 * 子女情况 未生育
	 */
	@ViewInject(R.id.child_wsy_checkBox)
	private CheckBox childWsyCheckBox;
	/**
	 * 陪护者 配偶
	 */
	@ViewInject(R.id.phz_po_checkBox)
	private CheckBox phzPoCheckBox;
	/**
	 * 陪护者 家政
	 */
	@ViewInject(R.id.phz_jz_checkBox)
	private CheckBox phzJzCheckBox;
	/**
	 * 陪护者 独居
	 */
	@ViewInject(R.id.phz_dj_checkBox)
	private CheckBox phzDjCheckBox;
	/**
	 * 陪护者 儿子
	 */
	@ViewInject(R.id.phz_ez_checkBox)
	private CheckBox phzEzCheckBox;
	/**
	 * 陪护者 女儿
	 */
	@ViewInject(R.id.phz_ne_checkBox)
	private CheckBox phzNeCheckBox;
	/**
	 * 陪护者 孙辈
	 */
	@ViewInject(R.id.phz_sb_checkBox)
	private CheckBox phSbCheckBox;
	/**
	 * 陪护者 父母
	 */
	@ViewInject(R.id.phz_fm_checkBox)
	private CheckBox phzFmCheckBox;
	/**
	 * 陪护者 其他
	 */
	@ViewInject(R.id.phz_qt_editText)
	private EditText phzQtEditText;
	/**
	 * 护理状况
	 */
	@ViewInject(R.id.nurse_editText)
	private EditText nurseEditText;
	/**
	 * 家庭经济情况
	 */
	@ViewInject(R.id.economy_editText)
	private EditText economyEditText;
	/**
	 * 家庭居住状况 采光
	 */
	@ViewInject(R.id.lighting_editText)
	private EditText lightingEditText;
	/**
	 * 家庭居住状况 空气
	 */
	@ViewInject(R.id.air_editText)
	private EditText airEditText;
	/**
	 * 家庭居住状况 干湿度
	 */
	@ViewInject(R.id.humidity_editText)
	private EditText humidityEditText;
	/**
	 * 家庭居住情况 温度
	 */
	@ViewInject(R.id.temperature_editText)
	private EditText temperatureEditText;
	/**
	 * 家庭居住情况 气味
	 */
	@ViewInject(R.id.odor_editText)
	private EditText odorEditText;
	/**
	 * 卫生间 有扶手
	 */
	@ViewInject(R.id.wc_yfs_checkBox)
	private CheckBox wcYfsCheckBox;
	/**
	 * 卫生间 无扶手
	 */
	@ViewInject(R.id.wc_wfs_checkBox)
	private CheckBox wcWfsCheckBox;
	/**
	 * 卫生间 地面不防滑
	 */
	@ViewInject(R.id.wc_dmbfh_checkBox)
	private CheckBox wcDmbfhCheckBox;
	/**
	 * 卫生间 狭窄
	 */
	@ViewInject(R.id.wc_xz_checkBox)
	private CheckBox wcXzsCheckBox;
	/**
	 * 卫生间 门口有台阶
	 */
	@ViewInject(R.id.wc_mkytj_checkBox)
	private CheckBox wcMkytjCheckBox;
	/**
	 * 卫生间 公共卫生间
	 */
	@ViewInject(R.id.wc_ggwsj_checkBox)
	private CheckBox wcGgwsjCheckBox;
	/**
	 * 卫生间 坐便器
	 */
	@ViewInject(R.id.wc_zbq_checkBox)
	private CheckBox wcZbqCheckBox;
	/**
	 * 床单位 高度不适
	 */
	@ViewInject(R.id.cdw_gdbs_checkBox)
	private CheckBox cdwGdbsCheckBox;
	/**
	 * 床单位 宽度不适
	 */
	@ViewInject(R.id.cdw_kdbs_checkBox)
	private CheckBox cdwKdbsCheckBox;
	/**
	 * 床单位 缺床档
	 */
	@ViewInject(R.id.cdw_qcw_checkBox)
	private CheckBox cdwQcwCheckBox;
	/**
	 * 床单位 不能半卧位
	 */
	@ViewInject(R.id.cdw_bnbww_checkBox)
	private CheckBox cdwBnbwwCheckBox;
	/**
	 * 床单位 医用床
	 */
	@ViewInject(R.id.cdw_yyc_checkBox)
	private CheckBox cdwYycCheckBox;
	/**
	 * 床单位 气垫床
	 */
	@ViewInject(R.id.cdw_qdc_checkBox)
	private CheckBox cdwQdcCheckBox;
	/**
	 * 床单位 整洁度
	 */
	@ViewInject(R.id.cdw_clean_radioGroup)
	private RadioGroup cdw_clean_radioGroup;
	/**
	 * 床单位  整洁
	 */
	@ViewInject(R.id.cdw_zj_radioButton)
	private RadioButton cdwZjRadioButton;
	/**
	 * 床单位  有异物
	 */
	@ViewInject(R.id.cdw_yyw_radioButton)
	private RadioButton cdwYywRadioButton;
	/**
	 * 床单位 被褥污染
	 */
	@ViewInject(R.id.cdw_brws_radioButton)
	private RadioButton cdwBrwsRadioButton;
	/**
	 * 床垫褥子 褥子软硬度
	 */
	@ViewInject(R.id.cdw_rz_radioGroup)
	private RadioGroup cdwRzRadioGroup;
	/**
	 * 床单位 褥子软
	 */
	@ViewInject(R.id.cdw_rzr_radioButton)
	private RadioButton cdwRzrRadioButton;
	/**
	 * 床单位 褥子硬
	 */
	@ViewInject(R.id.cdw_rzy_radioButton)
	private RadioButton cdwRzyRadioButton;
	
	/**
	 * 床单位 床垫
	 */
	@ViewInject(R.id.cdw_cd_radioGroup)
	private RadioGroup cdwCdRadioGroup;
	/**
	 * 床单位 床垫软
	 */
	@ViewInject(R.id.cdw_cdr_radioButton)
	private RadioButton cdwCdrRadioButton;
	/**
	 * 床单位 床垫硬
	 */
	@ViewInject(R.id.cdw_cdy_radioButton)
	private RadioButton cdwCdyRadioButton;
	/**
	 * 环境 家具是否有锐角
	 */
	@ViewInject(R.id.hj_jr_radioGroup)
	private RadioGroup hjJrRadioGroup;
	/**
	 * 环境 家具有锐角
	 */
	@ViewInject(R.id.hj_jyr_radioButton)
	private RadioButton hjJyrRadioButton;
	/**
	 * 环境 家具无锐角
	 */
	@ViewInject(R.id.hj_jwr_radioButton)
	private RadioButton hjJwrRadioButton;
	/**
	 * 环境 家具是否牢固
	 */
	@ViewInject(R.id.hj_lg_radioGroup)
	private RadioGroup hjLgRadioGroup;
	/**
	 * 环境 家具牢固
	 */
	@ViewInject(R.id.hj_lg_radioButton)
	private RadioButton hjLgRadioButton;
	/**
	 * 环境 家具不牢固
	 */
	@ViewInject(R.id.hj_blg_radioButton)
	private RadioButton hjBlgRadioButton;
	/**
	 * 环境 家具是否影响室内活动
	 */
	@ViewInject(R.id.hj_yxsnhd_radioGroup)
	private RadioGroup hjYxsnhdRadioGroup;
	/**
	 * 环境 家具影响室内活动
	 */
	@ViewInject(R.id.hj_yxsnhd_radioButton)
	private RadioButton hjYxsnhdRadioButton;
	/**
	 * 环境 家具否影响室内活动
	 */
	@ViewInject(R.id.hj_byxsnhd_radioButton)
	private RadioButton hjByxsnhdRadioButton;
	/**
	 * 环境 地面是否防滑
	 */
	@ViewInject(R.id.hj_dmfh_radioGroup)
	private RadioGroup hjDmfhRadioGroup;
	/**
	 * 环境 地面防滑
	 */
	@ViewInject(R.id.hj_dmfh_radioButton)
	private RadioButton hjDmfhRadioButton;
	/**
	 * 环境 地面不防滑
	 */
	@ViewInject(R.id.hj_dmbfh_radioButton)
	private RadioButton hjDmbfhRadioButton;
	/**
	 * 环境 地面有无障碍物
	 */
	@ViewInject(R.id.hj_yzaw_radioGroup)
	private RadioGroup hjYzawRadioGroup;
	/**
	 * 环境 地面有无障碍物
	 */
	@ViewInject(R.id.hj_yzaw_radioButton)
	private RadioButton hjYzawRadioButton;
	/**
	 * 环境 地面无障碍物
	 */
	@ViewInject(R.id.hj_wzaw_radioButton)
	private RadioButton hjWzawRadioButton;
	/**
	 * 环境 地面是否整洁
	 */
	@ViewInject(R.id.hj_zj_radioGroup)
	private RadioGroup hjZjRadioGroup;
	/**
	 * 环境 地面整洁
	 */
	@ViewInject(R.id.hj_zj_radioButton)
	private RadioButton hjZjRadioButton;
	/**
	 * 环境 地面不整洁
	 */
	@ViewInject(R.id.hj_bzj_radioButton)
	private RadioButton hjBzjRadioButton;
	/**
	 * 环境 过道杂物
	 */
	@ViewInject(R.id.hj_gdyzw_radioGroup)
	private RadioGroup hjGdyzwRadioGroup;
	/**
	 * 环境 过道有杂物
	 */
	@ViewInject(R.id.hj_gdyzw_radioButton)
	private RadioButton hjGdyzwRadioButton;
	/**
	 * 环境 过道无杂物
	 */
	@ViewInject(R.id.hj_gdwzw_radioButton)
	private RadioButton hjGdwzwRadioButton;
	/**
	 * 环境 过道门槛
	 */
	@ViewInject(R.id.hj_ymk_radioGroup)
	private RadioGroup hjYmkRadioGroup;
	/**
	 * 环境 过道有门槛
	 */
	@ViewInject(R.id.hj_ymk_radioButton)
	private RadioButton hjYmkRadioButton;
	/**
	 * 环境 过道无门槛
	 */
	@ViewInject(R.id.hj_wmk_radioButton)
	private RadioButton hjWmkRadioButton;
	/**
	 * 环境 其他
	 */
	@ViewInject(R.id.hj_qt_editText)
	private EditText hjQtEditText;
	/**
	 * 着装 清洁
	 */
	@ViewInject(R.id.zz_qj_radioGroup)
	private RadioGroup zzQjRadioGroup;
	/**
	 * 着装 清洁
	 */
	@ViewInject(R.id.zz_qj_radioButton)
	private RadioButton zzQjRadioButton;
	/**
	 * 着装 不清洁
	 */
	@ViewInject(R.id.zz_bqj_radioButton)
	private RadioButton zzBqjRadioButton;
	/**
	 * 着装 合身
	 */
	@ViewInject(R.id.zz_hs_radioGroup)
	private RadioGroup zzHsRadioGroup;
	/**
	 * 着装 合身
	 */
	@ViewInject(R.id.zz_hs_radioButton)
	private RadioButton zzHsRadioButton;
	/**
	 * 着装 不合身
	 */
	@ViewInject(R.id.zz_bhs_radioButton)
	private RadioButton zzBhsRadioButton;
	/**
	 * 着装 保暖
	 */
	@ViewInject(R.id.zz_bn_radioGroup)
	private RadioGroup zzBnRadioGroup;
	/**
	 * 着装 保暖
	 */
	@ViewInject(R.id.zz_bn_radioButton)
	private RadioButton zzBnRadioButton;
	/**
	 * 着装 不保暖
	 */
	@ViewInject(R.id.zz_bbn_radioButton)
	private RadioButton zzBbnRadioButton;
	/**
	 * 起夜 光线不够
	 */
	@ViewInject(R.id.qy_gxbg_checkBox)
	private CheckBox qyGxbgCheckBox;
	/**
	 * 起夜 便器不合适
	 */
	@ViewInject(R.id.qy_bqbhs_checkBox)
	private CheckBox qyBqbhsCheckBox;
	/**
	 * 起夜 手电
	 */
	@ViewInject(R.id.qy_sd_checkBox)
	private CheckBox qySdCheckBox;
	/**
	 * 起夜 辅助工具
	 */
	@ViewInject(R.id.qy_fzgj_checkBox)
	private CheckBox qyFzgjCheckBox;
	/**
	 * 起夜 呼叫无效
	 */
	@ViewInject(R.id.qy_hjwx_checkBox)
	private CheckBox qyHjwxCheckBox;
	/**
	 * 起夜 呼叫安全
	 */
	@ViewInject(R.id.qy_hjaq_checkBox)
	private CheckBox qyHjaqCheckBox;
	/**
	 * 起夜 独自起夜
	 */
	@ViewInject(R.id.qy_dzqy_checkBox)
	private CheckBox qyDzqyCheckBox;
	/**
	 * 起夜 其他
	 */
	@ViewInject(R.id.qy_qybz_editText)
	private EditText qyQybzEditText;
	/**
	 * 辅具助行类 助行器
	 */
	@ViewInject(R.id.fzzxl_zxq_checkBox)
	private CheckBox fzzxlZxqCheckBox;
	/**
	 * 辅具助行类 四点杖
	 */
	@ViewInject(R.id.fzzxl_sdz_checkBox)
	private CheckBox fzzxlSdzCheckBox;
	/**
	 * 辅具助行类 轮椅
	 */
	@ViewInject(R.id.fzzxl_ly_checkBox)
	private CheckBox fzzxlLyCheckBox;
	/**
	 * 辅具助行类 电动轮椅
	 */
	@ViewInject(R.id.fzzxl_ddly_checkBox)
	private CheckBox fzzxlDdlyCheckBox;
	/**
	 * 辅具助行类 足踝矫形器
	 */
	@ViewInject(R.id.fzzxl_zhjxq_checkBox)
	private CheckBox fzzxlZhjxqCheckBox;
	/**
	 * 辅具助行类 脊柱矫形器
	 */
	@ViewInject(R.id.fzzxl_jzqxq_checkBox)
	private CheckBox fzzxlJzqxqCheckBox;

	/**
	 * 辅具助行类 其他类
	 */
	@ViewInject(R.id.fzzxl_qtl_radioGroup)
	private RadioGroup fzzxlQtlRadioGroup;
	/**
	 * 辅具助行类 适合
	 */
	@ViewInject(R.id.fzzxl_sy_radioButton)
	private RadioButton fzzxlSyRadioButton;
	/**
	 * 辅具助行类 不适合
	 */
	@ViewInject(R.id.fzzxl_bsy_radioButton)
	private RadioButton fzzxlBsyRadioButton;
	/**
	 * 辅具助行类 其它工具
	 */
	@ViewInject(R.id.fzzxl_qtgj_editText)
	private EditText fzzxlQtgjEditText;
	/**
	 * 辅具需求
	 */
	@ViewInject(R.id.assist_editText)
	private EditText assistEditText;
	/**
	 * 病史记录 
	 */
	@ViewInject(R.id.history_linearLayout)
	private LinearLayout historyLinearLayout;
	/**
	 * 病史记录  添加
	 */
	@ViewInject(R.id.history_add_textView)
	private TextView historyAddTextView;
	/**
	 * 病史记录  删除
	 */
	@ViewInject(R.id.history_subtract_textView)
	private TextView historySubtractTextView;
	/**
	 * 药品使用
	 */
	@ViewInject(R.id.drug_editText)
	private EditText drugEditText;

	/**
	 * 提交表单
	 */
	@ViewInject(R.id.submit_basicInfo_button)
	private Button submitBasicInfoButton;
	/**
	 * 保存表单
	 */
	@ViewInject(R.id.save_basicInfo_button)
	private Button saveBasicInfoButton;
	/**
	 * 客户性别 男
	 */
	@ViewInject(R.id.sex_cust_nan)
	private RadioButton sexCustNan;
	/**
	 * 客户性别 女
	 */
	@ViewInject(R.id.sex_cust_nv)
	private RadioButton sexCustNv;

	private String marriage = "";// 婚姻状况
	private String occupational = "";// 退休前职业
	private String child = "";// 子女情况
	private String phz = "";// 陪护者
	private String nurse = "";// 护理情况
	private String economy = "";// 家庭经济情况
	private String lighting = "";// 家庭居住状况 采光
	private String air = "";// 家庭居住状况 空气
	private String humidity = "";// 家庭居住状况 干湿度
	private String temperature = "";// 家庭居住状况 干湿度
	private String odor = "";// 家庭居住状况 气味
	private String wc = "";// 卫生间
	private String cdw = "";// 床单位
	private String hj = "";// 环境
	private String zz = "";// 着装
	private String qy = "";// 起夜
	private String fjzxl = "";// 辅具助行类
	private String assist= "";// 辅具需求
	private String history= "";// 病史记录
	private String drug = "";// 药品使用情况

	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;
	private String prodType; //产品类型（0：护理，1：评估，2：体验，3：其他）
	
	private List<String> list = new ArrayList<String>();//病史记录 输入框ID
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				BasicInformation basicInformationDB = (BasicInformation) msg.obj;
				if (!"1".equals(prodType)) {
					//prodType不为1时，只是显示报告，不需页面里的保存和提交功能				
					submitBasicInfoButton.setVisibility(View.GONE);
					saveBasicInfoButton.setVisibility(View.GONE);
				}
				initData(basicInformationDB);
				break;
			case INITDATANET:// 回写网络
				BasicInformation basicInformationNet = (BasicInformation) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
				pinetreeDB.deleteBasicInformationByScheID(customer.getId());
				basicInformationNet.setScheID(customer.getId());
				pinetreeDB.saveBasicInformationByScheID(basicInformationNet);
				if (!"1".equals(prodType)) {
					submitBasicInfoButton.setVisibility(View.GONE);
					saveBasicInfoButton.setVisibility(View.GONE);
				}
				initData(basicInformationNet);
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
		setContentView(R.layout.activity_basic_information);
		ViewUtils.inject(this);

		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		prodType = bundle.getString("prodType");
		
		dialog = new Dialog(BasicInformationActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);

		if (!"".equals(customer.getCustName())) {
			custNameTextView.setText(customer.getCustName());
		}
		if (!"".equals(customer.getCustAddress())) {
			custAddressTextView.setText(customer.getCustAddress());
		}
		if (!"".equals(customer.getCustPhone())) {
			custPhoneTextView.setText(customer.getCustPhone());
		}
		if (!"".equals(customer.getSex())) {
			if ("1".equals(customer.getSex())) {
				//男
				sexCustNan.setChecked(true);
			}else {
				//女
				sexCustNv.setChecked(true);
			}
		}
		if (!"".equals(customer.getAge())) {
			custAgeTextView.setText(customer.getAge());
		}

		//判断 如果已经提交过 不可再提交
		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus()) || !"1".equals(prodType)) {
			submitBasicInfoButton.setVisibility(View.GONE);
			saveBasicInfoButton.setVisibility(View.GONE);
		}
		
		selectListener();
		
		historyAddTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		historySubtractTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
		deleteOldThings();
		
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
		if (!"1".equals(prodType)) {
			//不为评估产品时
			submitBasicInfoButton.setVisibility(View.GONE);
			saveBasicInfoButton.setVisibility(View.GONE);
		}	
		if (NetUtil.checkNetWork(BasicInformationActivity.this)) {
			dialog.show();
			/**
			 *  有网络解析数据，拿到操作时间
			 *  查询本地数据库，如果有数据，获取插入操作时间
			 *  判断网络数据时间和本地时间回写最新的数据
			 */
			RequestParams params = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("prodType", prodType);
			params.addBodyParameter("num", "0");
			System.out.println("wwwwwwwwwwwwwwwwww   scheID:"+customer.getId()+"   custID:"+customer.getCustID());
			HttpUtil.post("baseAndTestView.action", params, new RequestCallBack<String>() {
					
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					dialog.hide();
					BasicInformationBase basicInformationBase = GsonUtils.json2bean(responseInfo.result, BasicInformationBase.class);
					Log.v("TAG", "基本情况表  responseInfo.result ：    " + responseInfo.result.toString());
					BasicInformation basicInformationDB = pinetreeDB.getBasicInformationByScheID(customer.getId());
					Message message = Message.obtain();
					if (!"".equals(basicInformationBase.getSuccess()) && Boolean.valueOf(basicInformationBase.getSuccess())) {
						if (!"".equals(basicInformationDB) && null != basicInformationDB) {
							if (!"".equals(basicInformationDB.getCreateTime()) && !"".equals(basicInformationBase.getResultData().get(0).getCreateTime())) {
									
								try {
									String createTimeDB = basicInformationDB.getCreateTime();
									String createTimeNet = basicInformationBase.getResultData().get(0).getCreateTime();
									String timeDB = createTimeDB.substring(0, 10)+ " "+ createTimeDB.substring(11, 19);
									String timeNet = createTimeNet.substring(0, 10)+ " "+ createTimeNet.substring(11, 19);
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date dbDate = format.parse(timeDB);
									Date netDate = format.parse(timeNet);
									System.out.println("数据库的时间值："+dbDate+"      网络的时间值："+netDate);
									if (dbDate.compareTo(netDate) > 0 ) {
										message.obj = basicInformationDB;
										message.what = INITDATADB;
										handler.sendMessage(message);
									} else {
										message.obj = basicInformationBase
													.getResultData().get(0);
										message.what = INITDATANET;
										handler.sendMessage(message);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
								
							}else if ("".equals(basicInformationBase.getResultData().get(0).getCreateTime())) {
								message.obj = basicInformationDB;
								message.what = INITDATADB;
								handler.sendMessage(message);
							}else if ("".equals(basicInformationDB.getCreateTime())) {
								message.obj = basicInformationBase
											.getResultData().get(0);
								message.what = INITDATANET;
								handler.sendMessage(message);
							}
							
						}else {
							message.obj = basicInformationBase
										.getResultData().get(0);
							message.what = INITDATANET;
							handler.sendMessage(message);
							Log.v("TAG", "如果数据库为NULL  回显网络");
						}
							
					}else {
						if (!"".equals(basicInformationDB) && null != basicInformationDB) {
							initData(basicInformationDB);
						}		
					}																																																																																														
				}
					
				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
						
				}
			});
																																													
		} else {
			BasicInformation basicInformationDB = pinetreeDB.getBasicInformationByScheID(customer.getId());
			if (!"".equals(basicInformationDB) && null != basicInformationDB) {
				initData(basicInformationDB);
			}
		}
		
	}
	
	/**
	 * 监听器
	 */
	private void selectListener(){
		backBasicInformationImageView.setOnClickListener(this);
		submitBasicInfoButton.setOnClickListener(this);
		saveBasicInfoButton.setOnClickListener(this);
		historyAddTextView.setOnClickListener(this);
		historySubtractTextView.setOnClickListener(this);
		
		gbRadioButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				occupationalRadioGroup.clearCheck();
				gbRadioButton.setChecked(true);
				occupationalQtEditText.setText("");
			}
		});
		grRadioButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View arg0) {
				
				occupationalRadioGroup.clearCheck();
				grRadioButton.setChecked(true);
				occupationalQtEditText.setText("");
			}
		});
		nmRadioButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				occupationalRadioGroup.clearCheck();
				nmRadioButton.setChecked(true);
				occupationalQtEditText.setText("");
			}
		});
		whgzRadioButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				occupationalRadioGroup.clearCheck();
				whgzRadioButton.setChecked(true);
				occupationalQtEditText.setText("");
			}
		});
		
		occupationalQtEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				if (!"".equals(occupationalQtEditText.getText().toString().trim())) {
					
					if (gbRadioButton.isChecked()==true){
						gbRadioButton.setChecked(false);
					}else if (grRadioButton.isChecked()==true) {
						grRadioButton.setChecked(false);
					}else if (nmRadioButton.isChecked()==true) {
						nmRadioButton.setChecked(false);
					}else if (whgzRadioButton.isChecked()==true) {
						whgzRadioButton.setChecked(false);
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				
				}
		});
		
		
	}
	
	/**
	 * 删除    当前时间-14    之前的数据
	 */
	private void deleteOldThings() {
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
		List<BasicInformation> list = pinetreeDB.getAllBasicInformation();
		if (list.size() > 0) {
			Date time = new Date(System.currentTimeMillis()  - 14*24*60*60*1000);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(time);
			int timeHis = Integer.parseInt(dateStr);
			for (int i = 0; i < list.size(); i++) {
				String scheID = list.get(i).getScheID();
				Customer customer = pinetreeDB.getCustomer(scheID);
				String createTime = customer.getBeginTime();
				if (!"".equals(createTime) && null != createTime) {
					String timeDB = createTime.substring(0, 4)+ createTime.substring(5, 7)+ createTime.substring(8, 10);
					int timeDb = Integer.parseInt(timeDB);
					if (timeHis > timeDb) {
						pinetreeDB.deleteBasicInformationByScheID(scheID);
						pinetreeDB.deleteBrainScoreByscheID(scheID);
						pinetreeDB.deleteEverydayStateByScheID(scheID);
						pinetreeDB.deleteDrugStateByScheID(scheID);
						pinetreeDB.deleteSportAssessByScheID(scheID);
						pinetreeDB.deleteCopmAddByScheID(scheID);
						pinetreeDB.deleteCopmByScheID(scheID);
					}
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_basicInformation_imageView:
			finish();
			break;
		case R.id.submit_basicInfo_button:
			dialog.show();
			if (NetUtil.checkNetWork(BasicInformationActivity.this)) {
				getElementData();
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
				RecordSubmitState recordSubmitStateSubmit = pinetreeDB.getRecordSubmitState(customer.getId());
				RequestParams params = new RequestParams();
				params.addBodyParameter("empID",employeeId);
				params.addBodyParameter("scheID", customer.getId());			
				params.addBodyParameter("custID",customer.getCustID());
				params.addBodyParameter("custCustinfoDetail.marriage", marriage);
				params.addBodyParameter("custCustinfoDetail.occupational", occupational);
				params.addBodyParameter("dictionary.child", child);
				params.addBodyParameter("dictionary.phz", phz);
				params.addBodyParameter("custCustinfoDetail.nurse", nurse);
				params.addBodyParameter("custCustinfoDetail.lighting", lighting);
				params.addBodyParameter("custCustinfoDetail.air", air);
				params.addBodyParameter("custCustinfoDetail.humidity", humidity);
				params.addBodyParameter("custCustinfoDetail.temperature", temperature);
				params.addBodyParameter("custCustinfoDetail.odor", odor);
				params.addBodyParameter("custCustinfoDetail.economy", economy);
				params.addBodyParameter("dictionary.wc", wc);
				params.addBodyParameter("dictionary.cdw", cdw);
				params.addBodyParameter("dictionary.hj", hj);
				params.addBodyParameter("dictionary.zz", zz);				
				params.addBodyParameter("dictionary.qy", qy);
				params.addBodyParameter("dictionary.fjzxl", fjzxl);
				params.addBodyParameter("custCustinfoDetail.assist", assist);
				params.addBodyParameter("custCustinfoDetail.history", history);	
				params.addBodyParameter("custCustinfoDetail.drug", drug);
				params.addBodyParameter("num", "0");
				params.addBodyParameter("custCustinfoDetail.status", "0");
				
				
				HttpUtil.post("baseReportSave.action", params, new RequestCallBack<String>() {
					
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						GlobalResult globalResult = GsonUtils.json2bean(responseInfo.result, GlobalResult.class);
						if (!"".equals(globalResult.getSuccess())&&Boolean.valueOf(globalResult.getSuccess())) {
							PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
							pinetreeDB.deleteBasicInformationByScheID(customer.getId());
							BasicInformation basicInformation = setData();
							pinetreeDB.saveBasicInformationByScheID(basicInformation);
							
							if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
								pinetreeDB.updateRecordSubmitStateBasic(customer.getId());
							}else {
								RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
								recordSubmitStateDB.setScheID(customer.getId());
								recordSubmitStateDB.setBasicSubmitState("1");
								pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
							} 
							
							ToastUtils.showToast(BasicInformationActivity.this, "提交成功");
							submitBasicInfoButton.setVisibility(View.GONE);
							saveBasicInfoButton.setVisibility(View.GONE);
							
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
							ToastUtils.showToast(BasicInformationActivity.this, "提交失败，请重试！");
						}
						
					}
					
					@Override
					public void onFailure(HttpException error, String msg) {
						clearVariable();
						ToastUtils.showToast(BasicInformationActivity.this, "提交失败，请重试！");
						dialog.hide();						
					}
				});
							
			}else {
				dialog.hide();
				ToastUtils.showToast(BasicInformationActivity.this, "亲，没有网络哦");
			}
			break;
		case R.id.save_basicInfo_button://将基本情况表保存到本地
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
			pinetreeDB.deleteBasicInformationByScheID(customer.getId());
			getElementData();
			BasicInformation basicInformation = setData();
			boolean b = pinetreeDB.saveBasicInformationByScheID(basicInformation);
			if (b) {
				ToastUtils.showToast(BasicInformationActivity.this, "保存成功");
			} else {
				ToastUtils.showToast(BasicInformationActivity.this, "保存失败，请重试");
			}		
			break;
		case R.id.history_add_textView://病史记录  添加输入框按钮
			EditText et = new EditText(this);
			LayoutParams params= new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        et.setLayoutParams(params);	        
	    	et.setId(list.size()+1);
	    	list.add("" + (list.size()+1));
	    	historyLinearLayout.addView(et);
			break;
		case R.id.history_subtract_textView://病史记录  删除输入框按钮
			if (list.size() > 0) {
				int id = Integer.parseInt(list.get(list.size()-1));
				EditText et1 = (EditText) findViewById(id);
				list.remove(list.size()-1);
				historyLinearLayout.removeView(et1);
			}
			break;
		default:
			break;
		}

	}

	/**
	 * 设置数据
	 */
	private BasicInformation setData() {
		BasicInformation basicInformation = new BasicInformation();
		basicInformation.setScheID(customer.getId());
		basicInformation.setCustID(customer.getCustID());
		basicInformation.setMarriage(marriage);
		basicInformation.setOccupational(occupational);
		basicInformation.setChild(child);
		basicInformation.setPhz(phz);
		basicInformation.setNurse(nurse);
		basicInformation.setEconomy(economy);
		basicInformation.setLighting(lighting);
		basicInformation.setAir(air);
		basicInformation.setHumidity(humidity);
		basicInformation.setTemperature(temperature);
		basicInformation.setOdor(odor);
		basicInformation.setWc(wc);
		basicInformation.setCdw(cdw);
		basicInformation.setHj(hj);
		basicInformation.setZz(zz);
		basicInformation.setQy(qy);
		basicInformation.setFjzxl(fjzxl);
		basicInformation.setAssist(assist);
		
		
		basicInformation.setHistory(history);
		basicInformation.setDrug(drug);
		
		// 获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		basicInformation.setCreateTime(createDate + "T" + createTime);
		return basicInformation;
	}

	/**
	 * 获取元素值
	 */
	private void getElementData() {
		clearVariable();
		// 婚姻
		RadioButton hyRadioButton = (RadioButton) findViewById(marriageRadioGroup
				.getCheckedRadioButtonId());
		String marriageText = hyRadioButton.getText().toString().trim();
		if (!"".equals(marriageText)) {
			if ("未婚".equals(marriageText)) {
				marriage = "wh";
			} else if ("已婚".equals(marriageText)) {
				marriage = "yh";
			} else if ("再婚".equals(marriageText)) {
				marriage = "zh";
			} else if ("丧偶".equals(marriageText)) {
				marriage = "so";
			} else if ("离异".equals(marriageText)) {
				marriage = "ly";
			}
		}

		if (gbRadioButton.isChecked() == true) {
			occupational = "gb";
		}else if (grRadioButton.isChecked() == true) {
			occupational = "gr";
		}else if (nmRadioButton.isChecked() == true) {
			occupational = "nm";
		}else if (whgzRadioButton.isChecked() == true) {
			occupational = "whgz";
		}else {
			occupational = occupationalQtEditText.getText().toString().trim();
		}

		// 子女情况
		if (childWznCheckBox.isChecked()) {
			child += "wzn|";
		}
		if (childYezCheckBox.isChecked()) {
			child += "yez|";
		}
		if (childYneCheckBox.isChecked()) {
			child += "yne|";
		}
		if (childLyCheckBox.isChecked()) {
			child += "ly|";
		}
		if (childWsyCheckBox.isChecked()) {
			child += "wsy|";
		}
		if (!"".equals(child)) {
			child = child.substring(0, child.length() - 1);
		}

		// 陪护者
		if (phzPoCheckBox.isChecked()) {
			phz += "po|";
		}
		if (phzJzCheckBox.isChecked()) {
			phz += "jz|";
		}
		if (phzDjCheckBox.isChecked()) {
			phz += "dj|";
		}
		if (phzEzCheckBox.isChecked()) {
			phz+= "ez|";
		}
		if (phzNeCheckBox.isChecked()) {
			phz += "ne|";
		}
		if (phSbCheckBox.isChecked()) {
			phz+= "sb|";
		}
		if (phzFmCheckBox.isChecked()) {
			phz+= "fm|";
		}
		if (!"".equals(phzQtEditText.getText().toString().trim())) {
			phz+= "phzqt:" + phzQtEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(phz)) {
			phz = phz.substring(0, phz.length() - 1);
		}

		// 护理状况
		if (!"".equals(nurseEditText.getText().toString().trim())) {
			nurse = nurseEditText.getText().toString().trim();
		}
		// 家庭经济状况
		if (!"".equals(economyEditText.getText().toString().trim())) {
			economy = economyEditText.getText().toString().trim();
		}
		// 采光
		if (!"".equals(lightingEditText.getText().toString().trim())) {
			lighting = lightingEditText.getText().toString().trim();
		}
		// 空气
		if (!"".equals(airEditText.getText().toString().trim())) {
			air = airEditText.getText().toString().trim();
		}
		// 干湿度
		if (!"".equals(humidityEditText.getText().toString().trim())) {
			humidity = humidityEditText.getText().toString().trim();
		}
		// 温度
		if (!"".equals(temperatureEditText.getText().toString().trim())) {
			temperature = temperatureEditText.getText().toString().trim();
		}
		// 气味
		if (!"".equals(odorEditText.getText().toString().trim())) {
			odor = odorEditText.getText().toString().trim();
		}

		// 卫生间
		if (wcYfsCheckBox.isChecked()) {
			wc += "yfs|";
		}
		if (wcWfsCheckBox.isChecked()) {
			wc += "wfs|";
		}
		if (wcDmbfhCheckBox.isChecked()) {
			wc += "dmbfh|";
		}
		if (wcXzsCheckBox.isChecked()) {
			wc += "xz|";
		}
		if (wcMkytjCheckBox.isChecked()) {
			wc += "mkytj|";
		}
		if (wcGgwsjCheckBox.isChecked()) {
			wc += "ggwc|";//ggwc
		}
		if (wcZbqCheckBox.isChecked()) {
			wc += "zbq|";
		}
		if (!"".equals(wc)) {
			wc = wc.substring(0, wc.length() - 1);
		}

		// 床单位
		if (cdwGdbsCheckBox.isChecked()) {
			cdw += "gdbs|";
		}
		if (cdwKdbsCheckBox.isChecked()) {
			cdw += "kdbs|";
		}
		if (cdwQcwCheckBox.isChecked()) {
			cdw += "qcd|";//qcd
		}
		if (cdwBnbwwCheckBox.isChecked()) {
			cdw += "bnbww|";
		}
		if (cdwYycCheckBox.isChecked()) {
			cdw += "yyc|";
		}
		if (cdwQdcCheckBox.isChecked()) {
			cdw += "qdc|";
		}
		RadioButton zjdRadioButton = (RadioButton) findViewById(cdw_clean_radioGroup
				.getCheckedRadioButtonId());
		String zjdRadioButtonText = zjdRadioButton.getText().toString().trim();
		if (!"".equals(zjdRadioButtonText)) {
			if ("整洁".equals(zjdRadioButtonText)) {
				cdw += "zj|";
			} else if ("有异物".equals(zjdRadioButtonText)) {
				cdw += "yyw|";
			} else if ("被褥污损".equals(zjdRadioButtonText)) {
				cdw += "brws|";
			}
		}
		RadioButton rzRadioButton = (RadioButton) findViewById(cdwRzRadioGroup
				.getCheckedRadioButtonId());
		String rzRadioButtonText = rzRadioButton.getText().toString().trim();
		if (!"".equals(rzRadioButtonText)) {
			if ("软".equals(rzRadioButtonText)) {
				cdw += "rzr|";
			} else if ("硬".equals(rzRadioButtonText)) {
				cdw += "rzy|";
			}
		}
		RadioButton cdRadioButton = (RadioButton) findViewById(cdwCdRadioGroup
				.getCheckedRadioButtonId());
		String cdRadioButtonText = cdRadioButton.getText().toString().trim();
		if (!"".equals(cdRadioButtonText)) {
			if ("软".equals(cdRadioButtonText)) {
				cdw += "cdr|";
			} else if ("硬".equals(cdRadioButtonText)) {
				cdw += "cdy|";
			}
		}
		if (!"".equals(cdw)) {
			cdw = cdw.substring(0, cdw.length() - 1);
		}

		// 环境 家具是否有锐角
		RadioButton hjJrRadioButton = (RadioButton) findViewById(hjJrRadioGroup
				.getCheckedRadioButtonId());
		String hjJrText = hjJrRadioButton.getText().toString().trim();
		if (!"".equals(hjJrText)) {
			if ("是".equals(hjJrText)) {
				hj += "jyr|";
			} else if ("否".equals(hjJrText)) {
				hj += "jwr|";
			}
		}

		// 环境 家具是否牢固
		RadioButton hjLgRadioButton = (RadioButton) findViewById(hjLgRadioGroup
				.getCheckedRadioButtonId());
		String hjLgText = hjLgRadioButton.getText().toString().trim();
		if (!"".equals(hjLgText)) {
			if ("是".equals(hjLgText)) {
				hj+= "lg|";
			} else if ("否".equals(hjLgText)) {
				hj += "blg|";
			}
		}

		// 环境 家具是否影响室内活动
		RadioButton hjYxsnhdRadioButton = (RadioButton) findViewById(hjYxsnhdRadioGroup
				.getCheckedRadioButtonId());
		String hjYxsnhdText = hjYxsnhdRadioButton.getText().toString().trim();
		if (!"".equals(hjYxsnhdText)) {
			if ("是".equals(hjYxsnhdText)) {
				hj += "yxsnhd|";
			} else if ("否".equals(hjYxsnhdText)) {
				hj += "byxsnhd|";
			}
		}
		// 环境 地面是否防滑
		RadioButton hjDmfhRadioButton = (RadioButton) findViewById(hjDmfhRadioGroup
				.getCheckedRadioButtonId());
		String hjDmfhText = hjDmfhRadioButton.getText().toString().trim();
		if (!"".equals(hjDmfhText)) {
			if ("是".equals(hjDmfhText)) {
				hj += "dmfh|";
			} else if ("否".equals(hjDmfhText)) {
				hj += "dmbfh|";
			}
		}
		// 环境 地面有无障碍物
		RadioButton hjYzawRadioButton = (RadioButton) findViewById(hjYzawRadioGroup
				.getCheckedRadioButtonId());
		String hjYzawText = hjYzawRadioButton.getText().toString().trim();
		if (!"".equals(hjYzawText)) {
			if ("是".equals(hjYzawText)) {
				hj += "yzaw|";
			} else if ("否".equals(hjYzawText)) {
				hj += "wzaw|";
			}
		}
		// 环境 地面是否整洁
		RadioButton hjZjRadioButton = (RadioButton) findViewById(hjZjRadioGroup
				.getCheckedRadioButtonId());
		String hjZjText = hjZjRadioButton.getText().toString().trim();
		if (!"".equals(hjZjText)) {
			if ("是".equals(hjZjText)) {
				hj += "zj|";
			} else if ("否".equals(hjZjText)) {
				hj += "bzj|";
			}
		}
		// 环境 过道杂物堆放
		RadioButton hjGdyzwRadioButton = (RadioButton) findViewById(hjGdyzwRadioGroup
				.getCheckedRadioButtonId());
		String hjGdyzwText = hjGdyzwRadioButton.getText().toString().trim();
		if (!"".equals(hjGdyzwText)) {
			if ("是".equals(hjGdyzwText)) {
				hj += "gdyzw|";
			} else if ("否".equals(hjGdyzwText)) {
				hj += "gdwzw|";
			}
		}

		// 环境 过道有门槛
		RadioButton hjYmkRadioButton = (RadioButton) findViewById(hjYmkRadioGroup
				.getCheckedRadioButtonId());
		String hjYmkText = hjYmkRadioButton.getText().toString().trim();
		if (!"".equals(hjYmkText)) {
			if ("是".equals(hjYmkText)) {
				hj += "ymk|";
			} else if ("否".equals(hjYmkText)) {
				hj += "wmk|";
			}
		}
		if (!"".equals(hjQtEditText.getText().toString().trim())) {
			hj += "hjqt:" + hjQtEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(hj)) {
			hj = hj.substring(0, hj.length() - 1);
		}

		// 着装 清洁
		RadioButton zzQjRadioButton = (RadioButton) findViewById(zzQjRadioGroup
				.getCheckedRadioButtonId());
		String zzQjText = zzQjRadioButton.getText().toString().trim();
		if (!"".equals(zzQjText)) {
			if ("是".equals(zzQjText)) {
				zz += "qj|";
			} else if ("否".equals(zzQjText)) {
				zz += "bqj|";
			}
		}
		// 着装 合身
		RadioButton zzHsRadioButton = (RadioButton) findViewById(zzHsRadioGroup
				.getCheckedRadioButtonId());
		String zzHsText = zzHsRadioButton.getText().toString().trim();
		if (!"".equals(zzHsText)) {
			if ("是".equals(zzHsText)) {
				zz += "hs|";
			} else if ("否".equals(zzHsText)) {
				zz += "bhs|";
			}
		}
		// 着装 保暖
		RadioButton zzBnRadioButton = (RadioButton) findViewById(zzBnRadioGroup
				.getCheckedRadioButtonId());
		String zzBnText = zzBnRadioButton.getText().toString().trim();
		if (!"".equals(zzBnText)) {
			if ("是".equals(zzBnText)) {
				zz += "bn|";
			} else if ("否".equals(zzBnText)) {
				zz += "bbn|";
			}
		}
		if (!"".equals(zz)) {
			zz = zz.substring(0, zz.length() - 1);
		}

		// 起夜
		if (qyGxbgCheckBox.isChecked()) {
			qy += "gxbg|";
		}
		if (qyBqbhsCheckBox.isChecked()) {
			qy += "bqbhs|";
		}
		if (qyFzgjCheckBox.isChecked()) {
			qy += "fzgj|";
		}
		if (qyHjwxCheckBox.isChecked()) {
			qy += "hjwh|";
		}
		if (qyHjaqCheckBox.isChecked()) {
			qy += "hjaq|";
		}
		if (qySdCheckBox.isChecked()) {
			qy += "sd|";
		}
		if (qyDzqyCheckBox.isChecked()) {
			qy += "dzqy|";
		}
		if (!"".equals(qyQybzEditText.getText().toString().trim())) {
			qy += "qybz:" + qyQybzEditText.getText().toString().trim()+ "|";
		}
		if (!"".equals(qy)) {
			qy = qy.substring(0, qy.length() - 1);
		}

		// 辅具助行类
		if (fzzxlZxqCheckBox.isChecked()) {
			fjzxl += "zxq|";
		}
		if (fzzxlSdzCheckBox.isChecked()) {
			fjzxl += "sdz|";
		}
		if (fzzxlLyCheckBox.isChecked()) {
			fjzxl += "ly|";
		}
		if (fzzxlDdlyCheckBox.isChecked()) {
			fjzxl += "ddly|";
		}
		if (fzzxlZhjxqCheckBox.isChecked()) {
			fjzxl += "zhjxq|";
		}
		if (fzzxlJzqxqCheckBox.isChecked()) {
			fjzxl+= "jzjxq|";
		}
		RadioButton fzzxlQtlRadioButton = (RadioButton) findViewById(fzzxlQtlRadioGroup
				.getCheckedRadioButtonId());
		String fzzxlQtlText = fzzxlQtlRadioButton.getText().toString().trim();
		if (!"".equals(fzzxlQtlText)) {
			if ("适合".equals(fzzxlQtlText)) {
				fjzxl += "sh|";
			} else if ("不适合".equals(fzzxlQtlText)) {
				fjzxl += "bs|";
			}
		}
		if (!"".equals(fzzxlQtgjEditText.getText().toString().trim())) {
			fjzxl +="qtgj:"+fzzxlQtgjEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(fjzxl)) {
			fjzxl = fjzxl.substring(0, fjzxl.length() - 1);
		}

		// 辅具需求
		if (!"".equals(assistEditText.getText().toString().trim())) {
			assist = assistEditText.getText().toString().trim();
		}
		
		// 病史记录	
		if (list.size() > 0) {		
			for (int i = 0; i < list.size(); i++) {
				int id = Integer.parseInt(list.get(i));
				EditText et = (EditText) findViewById(id);
				if (!"".equals(et.getText().toString().trim()) && null != et.getText().toString().trim()) {
					history += et.getText().toString().trim() + "|";
				}
			}
		}
		if (!"".equals(history)) {
			history = history.substring(0, history.length() - 1);
		}

		// 药品使用
		if (!"".equals(drugEditText.getText().toString().trim())) {
			drug = drugEditText.getText().toString().trim();
		}
	}

	/**
	 * 清空变量
	 */
	public void clearVariable() {
		marriage = "";// 婚姻状况
		occupational = "";// 退休前职业
		child = "";// 子女情况
		phz = "";// 陪护者
		nurse = "";// 护理情况
		economy = "";// 家庭经济情况
		lighting = "";// 家庭居住状况 采光
		air = "";// 家庭居住状况 空气
		humidity = "";// 家庭居住状况 干湿度
		temperature = "";// 家庭居住状况 干湿度
		odor = "";// 家庭居住状况 气味
		wc = "";// 卫生间
		cdw = "";// 床单位
		hj = "";// 环境
		zz = "";// 着装
		qy = "";// 起夜
		fjzxl = "";// 辅具助行类
		assist = "";// 辅具需求
		history = "";// 病史记录
		drug = "";// 药品使用情况
	}
	
	
	/**
	 * 回写数据
	 */
	private void  initData(BasicInformation basicInformation){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(BasicInformationActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getBasicSubmitState() && "1".equals(recordSubmitState2.getBasicSubmitState())) {
				submitBasicInfoButton.setVisibility(View.GONE);
				saveBasicInfoButton.setVisibility(View.GONE);
			}
		}
		
		if (!"".equals(basicInformation) && null != basicInformation) {
			
			//婚姻
			if (!"".equals(basicInformation.getMarriage())&& null != basicInformation.getMarriage()) {			
				if ("wh".equals(basicInformation.getMarriage())) {
					whRadioButton.setChecked(true);
				}else if ("yh".equals(basicInformation.getMarriage())) {
					yhRadioButton.setChecked(true);
				}else if ("zh".equals(basicInformation.getMarriage())) {
					zhRadioButton.setChecked(true);
				}else if ("so".equals(basicInformation.getMarriage())) {
					soRadioButton.setChecked(true);
				}else if ("ly".equals(basicInformation.getMarriage())) {
					lyRadioButton.setChecked(true);
				}
			}
			//退休职业
			if (!"".equals(basicInformation.getOccupational()) && null != basicInformation.getOccupational()) {
				if ("gb".equals(basicInformation.getOccupational())) {
					gbRadioButton.setChecked(true);
				}else if ("gr".equals(basicInformation.getOccupational())) {
					grRadioButton.setChecked(true);
				}else if ("nm".equals(basicInformation.getOccupational())) {
					nmRadioButton.setChecked(true);
				}else if ("whgz".equals(basicInformation.getOccupational())) {
					whgzRadioButton.setChecked(true);
				}else {
					occupationalQtEditText.setText(basicInformation.getOccupational());
				}
			}
			//子女情况
			if (!"".equals(basicInformation.getChild()) && null != basicInformation.getChild()) {
				if (basicInformation.getChild().contains("|")) {
					String[] childArray = basicInformation.getChild().split("\\|");
					for (String child : childArray) {
						setChildString(child);
					}
				}else {
					setChildString(basicInformation.getChild());
				}	
			}		
			//陪护者
			if (!"".equals(basicInformation.getPhz()) && null != basicInformation.getPhz()) {
				if (basicInformation.getPhz().contains("|")) {
					String[] phzArray = basicInformation.getPhz().split("\\|");
					for (String phz : phzArray) {
						setPhzString(phz);
					}
				}else {
					setPhzString(basicInformation.getPhz());
				}
				
			}
			//护理状况
			if (!"".equals(basicInformation.getNurse())) {
				nurseEditText.setText(basicInformation.getNurse());
			}
			//家庭经济状况
			if (!"".equals(basicInformation.getEconomy())) {
				economyEditText.setText(basicInformation.getEconomy());
			}
			//采光
			if (!"".equals(basicInformation.getLighting())) {
				lightingEditText.setText(basicInformation.getLighting());
			}
			//空气
			if (!"".equals(basicInformation.getAir())) {
				airEditText.setText(basicInformation.getAir());
			}
			//干湿度
			if (!"".equals(basicInformation.getHumidity())) {
				humidityEditText.setText(basicInformation.getHumidity());
			}
			//温度
			if (!"".equals(basicInformation.getTemperature())) {
				temperatureEditText.setText(basicInformation.getTemperature());
			}
			//气度
			if (!"".equals(basicInformation.getOdor())) {
				odorEditText.setText(basicInformation.getOdor());
			}
			//卫生间
			if (!"".equals(basicInformation.getWc()) && null != basicInformation.getWc()) {
				if (basicInformation.getWc().contains("|")) {
					String[] wcArray = basicInformation.getWc().split("\\|");
					for (String wc : wcArray) {
						setWcString(wc);
					}
				}else {
					setWcString(basicInformation.getWc());
				}			
			}
			//床单位
			if (!"".equals(basicInformation.getCdw()) && null != basicInformation.getCdw()) {
				if (basicInformation.getCdw().contains("|")) {
					String[] cdwArray = basicInformation.getCdw().split("\\|");
					for (String cdw : cdwArray) {
						setCdwString(cdw);
					}
				}else {
					setCdwString(basicInformation.getCdw());
				}				
			}
			//环境 
			if (!"".equals(basicInformation.getHj()) && null != basicInformation.getHj()) {
				if (basicInformation.getHj().contains("|")) {
					String[] hjArray = basicInformation.getHj().split("\\|");
					for (String hj : hjArray) {
						setHjString(hj);
					}
				}else {
					setHjString(basicInformation.getHj());
				}				
			}
			//着装
			if (!"".equals(basicInformation.getZz()) && null != basicInformation.getZz()) {
				if (basicInformation.getZz().contains("|")) {
					String[] zzArray = basicInformation.getZz().split("\\|");
					for (String zz : zzArray) {
						setZzString(zz);
					}
				}else {
					setZzString(basicInformation.getZz());
				}			
			}
			//起夜
			if (!"".equals(basicInformation.getQy()) && null != basicInformation.getQy()) {
				if (basicInformation.getQy().contains("|")) {
					String[] qyArray = basicInformation.getQy().split("\\|");
					for (String qy : qyArray) {
						setQyString(qy);
					}					
				}else {
					setQyString(basicInformation.getQy());
				}				
			}
			//辅具助行类
			if (!"".equals(basicInformation.getFjzxl()) && null != basicInformation.getFjzxl()) {
				if (basicInformation.getFjzxl().contains("|")) {
					String[] fjzxlArray = basicInformation.getFjzxl().split("\\|");
					for (String fjzxl : fjzxlArray) {
						setFjzxlString(fjzxl);
					}
				}else {
					setFjzxlString(basicInformation.getFjzxl());
				}
				
			}
			//辅具需求
			if (!"".equals(basicInformation.getAssist())) {
				assistEditText.setText(basicInformation.getAssist());
			}
			//病史记录
			if (!"".equals(basicInformation.getHistory())&& null != basicInformation.getHistory()) {
				LayoutParams params= new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				if (basicInformation.getHistory().contains("|")) {
					String[] historyArray = basicInformation.getHistory().split("\\|");					
					for (int i = 0; i < historyArray.length; i++) {
						EditText et = new EditText(this);
				        et.setLayoutParams(params);
				    	et.setText(historyArray[i]);
				    	et.setId(i+1);
				    	list.add("" + (i+1));
				    	et.setGravity(Gravity.CENTER);	
				    	historyLinearLayout.addView(et);
					}
				}else {
					EditText et = new EditText(this);
			        et.setLayoutParams(params);
			    	et.setText(basicInformation.getHistory());
			    	et.setGravity(Gravity.CENTER);
			    	et.setId(1);
			    	list.add("" + 1);
			    	historyLinearLayout.addView(et);
				}
				
			}
			//药品使用
			if (!"".equals(basicInformation.getDrug())) {
				drugEditText.setText(basicInformation.getDrug());
			}
		}
	}
	
		/**
		 * 子女情况  判断
		 */
		private void setChildString(String child){
			if ("wzn".equals(child)) {
				childWznCheckBox.setChecked(true);
			}else if ("yez".equals(child)) {
				childYezCheckBox.setChecked(true);
			}else if ("yne".equals(child)) {
				childYneCheckBox.setChecked(true);
			}else if ("ly".equals(child)) {
				childLyCheckBox.setChecked(true);
			}else if ("wsy".equals(child)) {
				childWsyCheckBox.setChecked(true);
			}
		}
		
		/**
		 * 陪护者 判断
		 */
		private void setPhzString(String phz){
			if ("po".equals(phz)) {
				phzPoCheckBox.setChecked(true);
			}else if ("jz".equals(phz)) {
				phzJzCheckBox.setChecked(true);
			}else if ("dj".equals(phz)) {
				phzDjCheckBox.setChecked(true);
			}else if ("ez".equals(phz)) {
				phzEzCheckBox.setChecked(true);
			}else if ("ne".equals(phz)) {
				phzNeCheckBox.setChecked(true);
			}else if ("sb".equals(phz)) {
				phSbCheckBox.setChecked(true);
			}else if ("fm".equals(phz)) {
				phzFmCheckBox.setChecked(true);
			}else if (!"".equals(phz)&&phz.contains(":")) {
				phzQtEditText.setText(phz.split(":")[1]);
			}
		}
		
		/** 
		 * 卫生间 判断
		 */
		private void setWcString(String wc){
			if ("yfs".equals(wc)) {
				wcYfsCheckBox.setChecked(true);
			}else if ("wfs".equals(wc)) {
				wcWfsCheckBox.setChecked(true);
			}else if ("dmbfh".equals(wc)) {
				wcDmbfhCheckBox.setChecked(true);
			}else if ("xz".equals(wc)) {
				wcXzsCheckBox.setChecked(true);
			}else if ("mkytj".equals(wc)) {
				wcMkytjCheckBox.setChecked(true);
			}else if ("ggwc".equals(wc)) {
				wcGgwsjCheckBox.setChecked(true);
			}else if ("zbq".equals(wc)) {
				wcZbqCheckBox.setChecked(true);
			}
		}
		/**
		 * 起夜 判断
		 */
		private void setQyString(String qy){
			if ("gxbg".equals(qy)) {
				qyGxbgCheckBox.setChecked(true);
			}else if ("bqbhs".equals(qy)) {
				qyBqbhsCheckBox.setChecked(true);
			}else if ("fzgj".equals(qy)) {
				qyFzgjCheckBox.setChecked(true);
			}else if ("hjwh".equals(qy)) {
				qyHjwxCheckBox.setChecked(true);
			}else if ("hjaq".equals(qy)) {
				qyHjaqCheckBox.setChecked(true);
			}else if ("sd".equals(qy)) {
				qySdCheckBox.setChecked(true);
			}else if ("dzqy".equals(qy)) {
				qyDzqyCheckBox.setChecked(true);
			}else if (!"".equals(qy) && qy.contains(":")) {
				qyQybzEditText.setText(qy.split(":")[1]);
			}
		}
		/**
		 * 辅具助行类  判断
		 */
		private void setFjzxlString(String fjzxl){
			if ("zxq".equals(fjzxl)) {
				fzzxlZxqCheckBox.setChecked(true);
			}else if ("sdz".equals(fjzxl)) {
				fzzxlSdzCheckBox.setChecked(true);
			}else if ("ly".equals(fjzxl)) {
				fzzxlLyCheckBox.setChecked(true);
			}else if ("ddly".equals(fjzxl)) {
				fzzxlDdlyCheckBox.setChecked(true);
			}else if ("zhjxq".equals(fjzxl)) {
				fzzxlZhjxqCheckBox.setChecked(true);
			}else if ("jzjxq".equals(fjzxl)) {
				fzzxlJzqxqCheckBox.setChecked(true);
			}else if ("sh".equals(fjzxl)) {
				fzzxlSyRadioButton.setChecked(true);
			}else if ("bsh".equals(fjzxl)) {
				fzzxlBsyRadioButton.setChecked(true);
			}else if (!"".equals(fjzxl) && fjzxl.contains(":")) {
				fzzxlQtgjEditText.setText(fjzxl.split(":")[1]);
			}
		}
		
		/**
		 * 床单位  判断
		 */
		private void setCdwString(String cdw){
			if ("gdbs".equals(cdw)) {
				cdwGdbsCheckBox.setChecked(true);
			}else if ("kdbs".equals(cdw)) {
				cdwKdbsCheckBox.setChecked(true);
			}else if ("qcd".equals(cdw)) {
				cdwQcwCheckBox.setChecked(true);
			}else if ("bnbww".equals(cdw)) {
				cdwBnbwwCheckBox.setChecked(true);
			}else if ("yyc".equals(cdw)) {
				cdwYycCheckBox.setChecked(true);
			}else if ("qdc".equals(cdw)) {
				cdwQdcCheckBox.setChecked(true);
			}else if ("zj".equals(cdw)) {
				cdwZjRadioButton.setChecked(true);
			}else if ("yyw".equals(cdw)) {
				cdwYywRadioButton.setChecked(true);
			}else if ("brws".equals(cdw)) {
				cdwBrwsRadioButton.setChecked(true);
			}else if ("rzr".equals(cdw)) {
				cdwRzrRadioButton.setChecked(true);
			}else if ("rzy".equals(cdw)) {
				cdwRzyRadioButton.setChecked(true);
			}else if ("cdr".equals(cdw)) {
				cdwCdrRadioButton.setChecked(true);
			}else if ("cdy".equals(cdw)) {
				cdwCdyRadioButton.setChecked(true);
			}
		}
		/**
		 * 环境 判断
		 */
		private void setHjString(String hj){
			if ("jyr".equals(hj)) {
				hjJyrRadioButton.setChecked(true);
			}else if ("jwr".equals(hj)) {
				hjJwrRadioButton.setChecked(true);
			}else if ("lg".equals(hj)) {
				hjLgRadioButton.setChecked(true);
			}else if ("blg".equals(hj)) {
				hjBlgRadioButton.setChecked(true);
			}else if ("yxsnhd".equals(hj)) {
				hjYxsnhdRadioButton.setChecked(true);
			}else if ("byxsnhd".equals(hj)) {
				hjByxsnhdRadioButton.setChecked(true);
			}else if ("dmfh".equals(hj)) {
				hjDmfhRadioButton.setChecked(true);
			}else if ("dmbfh".equals(hj)) {
				hjDmbfhRadioButton.setChecked(true);
			}else if ("yzaw".equals(hj)) {
				hjYzawRadioButton.setChecked(true);
			}else if ("wzaw".equals(hj)) {
				hjWzawRadioButton.setChecked(true);
			}else if ("zj".equals(hj)) {
				hjZjRadioButton.setChecked(true);
			}else if ("bzj".equals(hj)) {
				hjBzjRadioButton.setChecked(true);
			}else if ("gdyzw".equals(hj)) {
				hjGdyzwRadioButton.setChecked(true);
			}else if ("gdwzw".equals(hj)) {
				hjGdwzwRadioButton.setChecked(true);
			}else if ("ymk".equals(hj)) {
				hjYmkRadioButton.setChecked(true);
			}else if ("wmk".equals(hj)) {
				hjWmkRadioButton.setChecked(true);
			}else if (!"".equals(hj)&&hj.contains(":")) {
				hjQtEditText.setText(hj.split(":")[1]);
			}
		}
		/**
		 * 着装  判断
		 */
		private void setZzString(String zz){
			if ("qj".equals(zz)) {
				zzQjRadioButton.setChecked(true);
			}else if ("bqj".equals(zz)) {
				zzBqjRadioButton.setChecked(true);
			}else if ("hs".equals(zz)) {
				zzHsRadioButton.setChecked(true);
			}else if("bhs".equals(zz)){
				zzBhsRadioButton.setChecked(true);
			}else if ("bn".equals(zz)) {
				zzBnRadioButton.setChecked(true);
			}else if ("bbn".equals(zz)) {
				zzBbnRadioButton.setChecked(true);
			}
		}
}
