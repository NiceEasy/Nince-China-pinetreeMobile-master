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
import com.pinetree.mobile.bean.GlobalResult;
import com.pinetree.mobile.bean.RecordSubmitState;
import com.pinetree.mobile.bean.SportAssess;
import com.pinetree.mobile.bean.SportAssessBase;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Spinner;

/**
 * 查体--运动功能评定
 */
public class SportAssessActivity extends Activity implements OnClickListener,
		OnItemSelectedListener,OnCheckedChangeListener,android.widget.CompoundButton.OnCheckedChangeListener {

	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	/**
	 * 顶部返回按钮图片
	 */
	@ViewInject(R.id.back_sport_imageView)
	private ImageView backSportImageView;

	/**
	 * 肌力 正常异常单选RadioGroup
	 */
	@ViewInject(R.id.strength_radioGroup)
	private RadioGroup strengthRadioGroup;
	/**
	 * 肌力 正常单选按钮
	 */
	@ViewInject(R.id.strength_normal_radioButton)
	private RadioButton strengthNormalRadioButton;
	/**
	 * 肌力 异常单选按钮
	 */
	@ViewInject(R.id.strength_exception_radioButton)
	private RadioButton strengthExceptionRadioButton;
	/**
	 * 肌力 隐藏的评分布局
	 */
	@ViewInject(R.id.strength_linearLayout)
	private LinearLayout strengthLinearLayout;

	/**
	 * 肌张力 正常异常单选RadioGroup
	 */
	@ViewInject(R.id.muscleTension_radioGroup)
	private RadioGroup muscleTensionRadioGroup;
	/**
	 * 肌张力 正常单选按钮
	 */
	@ViewInject(R.id.muscleTension_normal_radioButton)
	private RadioButton muscleTensionNormalRadioButton;
	/**
	 * 肌张力 异常单选按钮
	 */
	@ViewInject(R.id.muscleTension_exception_radioButton)
	private RadioButton muscleTensionExceptionRadioButton;
	/**
	 * 肌张力 隐藏的评分布局
	 */
	@ViewInject(R.id.muscleTension_linearLayout)
	private LinearLayout muscleTensionLinearLayout;
	/**
	 * 疼痛评分 无疼痛有疼痛单选RadioGroup
	 */
	@ViewInject(R.id.pain_radioGroup)
	private RadioGroup painRadioGroup;
	/**
	 * 疼痛评分 无疼痛单选按钮
	 */
	@ViewInject(R.id.pain_normal_radioButton)
	private RadioButton painNormalRadioButton;
	/**
	 * 疼痛评分 有疼痛单选按钮
	 */
	@ViewInject(R.id.pain_exception_radioButton)
	private RadioButton painExceptionRadioButton;
	/**
	 * 疼痛评分 隐藏的评分布局
	 */
	@ViewInject(R.id.pain_linearLayout)
	private LinearLayout painLinearLayout;
	/**
	 * 坐位平衡不适用原因
	 */
	@ViewInject(R.id.zwph_bsyyy_editText)
	private EditText zwphBsyyyEditText;
	/**
	 * 坐位平衡 下拉框
	 */
	@ViewInject(R.id.zwph_spinner)
	private Spinner zwphSpinner;
	/**
	 * 立位平衡 不适用原因
	 */
	@ViewInject(R.id.lwph_bsyyy_editText)
	private EditText lwphBsyyyEditText;
	/**
	 * 立位平衡 下拉框
	 */
	@ViewInject(R.id.lwph_spinner)
	private Spinner lwphSpinner;
	/**
	 * Holden步行能力评定 不适用原因
	 */
	@ViewInject(R.id.bxnl_bsyyy_editText)
	private EditText bxnlBsyyyEditText;
	/**
	 * Holden步行能力评定 下拉框
	 */
	@ViewInject(R.id.bxnl_spinner)
	private Spinner bxnlSpinner;
	/**
	 * 肌力 异常 左侧
	 */
	@ViewInject(R.id.jl_yc_zc_checkBox)
	private CheckBox jlYcZcCheckBox;
	/**
	 * 肌力 异常 左侧 手
	 */
	@ViewInject(R.id.jl_yc_zc_shou_checkBox)
	private CheckBox jlYcZcShouCheckBox;
	/**
	 * 肌力 异常 左侧 手得分下拉框
	 */
	@ViewInject(R.id.jl_yc_zc_shou_spinner)
	private Spinner jlYcZcShouSpinner;
	/**
	 * 肌力 异常 左侧 上肢
	 */
	@ViewInject(R.id.jl_yc_zc_sz_checkBox)
	private CheckBox jlYcZcSzCheckBox;
	/**
	 * 肌力 异常 左侧 上肢得分下拉框
	 */
	@ViewInject(R.id.jl_yc_zc_sz_spinner)
	private Spinner jlYcZcSzSpinner;
	/**
	 * 肌力 异常 左侧 下肢
	 */
	@ViewInject(R.id.jl_yc_zc_xz_checkBox)
	private CheckBox jlYcZcXzCheckBox;
	/**
	 * 肌力 异常 左侧 下肢得分下拉框
	 */
	@ViewInject(R.id.jl_yc_zc_xz_spinner)
	private Spinner jlYcZcXzSpinner;
	/**
	 * 肌力 异常 左侧 其他输入框
	 */
	@ViewInject(R.id.jl_yc_zc_qt_editText)
	private EditText jlYcZcQtEditText;
	/**
	 * 肌力 异常 左侧 其他得分
	 */
	@ViewInject(R.id.jl_yc_zc_qt_spinner)
	private Spinner jlYcZcQtSpinner;
	/**
	 * 肌力 异常 躯干
	 */
	@ViewInject(R.id.jl_yc_qg_checkBox)
	private CheckBox jlYcQgCheckBox;
	/**
	 * 肌力 异常 躯干 背肌
	 */
	@ViewInject(R.id.jl_yc_qg_bj_checkBox)
	private CheckBox jlYcQgBjCheckBox;
	/**
	 * 肌力 异常 躯干 背肌得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_qg_bj_spinner)
	private Spinner jlYcQgBjSpinner;
	/**
	 * 肌力 异常 躯干 腰肌
	 */
	@ViewInject(R.id.jl_yc_qg_yj_checkBox)
	private CheckBox jlYcQgYjCheckBox;
	/**
	 * 肌力 异常 躯干 腰肌得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_qg_yj_spinner)
	private Spinner jlYcQgYjSpinner;
	/**
	 * 肌力 异常 躯干 臀肌
	 */
	@ViewInject(R.id.jl_yc_qg_tj_checkBox)
	private CheckBox jlYcQgTjCheckBox;
	/**
	 * 肌力 异常 躯干 臀肌得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_qg_tj_spinner)
	private Spinner jlYcQgTjSpinner;
	/**
	 * 肌力 异常 躯干 胸肌
	 */
	@ViewInject(R.id.jl_yc_qg_xj_checkBox)
	private CheckBox jlYcQgXjCheckBox;
	/**
	 * 肌力 异常 躯干 胸肌得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_qg_xj_spinner)
	private Spinner jlYcQgXjSpinner;
	/**
	 * 肌力 异常 躯干 腹肌
	 */
	@ViewInject(R.id.jl_yc_qg_fj_checkBox)
	private CheckBox jlYcQgFjCheckBox;
	/**
	 * 肌力 异常 躯干 腹肌得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_qg_fj_spinner)
	private Spinner jlYcQgFjSpinner;
	/**
	 * 肌力 异常 躯干 其他
	 */
	@ViewInject(R.id.jl_yc_qg_qt_checkBox)
	private TextView jlYcQgQtCheckBox;
	/**
	 * 肌力 异常 躯干 其他输入框
	 */
	@ViewInject(R.id.jl_yc_qg_qt_editText)
	private EditText jlYcQgQtEditText;
	/**
	 * 肌力 异常 躯干 其他得分
	 */
	@ViewInject(R.id.jl_yc_qg_qt_spinner)
	private Spinner jlYcQgQtSpinner;
	/**
	 * 肌力 异常 右侧
	 */
	@ViewInject(R.id.jl_yc_rc_checkBox)
	private CheckBox jlYcRcCheckBox;
	/**
	 * 肌力 异常 右侧 上肢
	 */
	@ViewInject(R.id.jl_yc_rc_sz_checkBox)
	private CheckBox jlYcRcSzCheckBox;
	/**
	 * 肌力 异常 右侧 上肢得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_rc_sz_spinner)
	private Spinner jlYcRcSzSpinner;
	/**
	 * 肌力 异常 右侧 下肢
	 */
	@ViewInject(R.id.jl_yc_rc_xz_checkBox)
	private CheckBox jlYcRcXzCheckBox;
	/**
	 * 肌力 异常 右侧 下肢得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_rc_xz_spinner)
	private Spinner jlYcRcXzSpinner;
	/**
	 * 肌力 异常 右侧 脚
	 */
	@ViewInject(R.id.jl_yc_rc_jiao_checkBox)
	private CheckBox jlYcRcJiaoCheckBox;
	/**
	 * 肌力 异常 右侧 脚得分 Spinner
	 */
	@ViewInject(R.id.jl_yc_rc_jiao_spinner)
	private Spinner jlYcRcJiaoSpinner;
	/**
	 * 肌力 异常 右侧 其他
	 */
	@ViewInject(R.id.jl_yc_rc_qt_checkBox)
	private TextView jlYcRcQtCheckBox;
	/**
	 * 肌力 异常 右侧 其他输入框
	 */
	@ViewInject(R.id.jl_yc_rc_qt_editText)
	private EditText jlYcRcQtEditText;
	/**
	 * 肌力 异常 右侧 其他得分
	 */
	@ViewInject(R.id.jl_yc_rc_qt_spinner)
	private Spinner jlYcRcQtSpinner;
	/**
	 * 肌张力 异常 左侧
	 */
	@ViewInject(R.id.jzl_yc_zc_checkBox)
	private CheckBox jzlYcZcCheckBox;
	/**
	 * 肌张力 异常 左侧 手
	 */
	@ViewInject(R.id.jzl_yc_zc_shou_checkBox)
	private CheckBox jzlYcZcShouCheckBox;
	/**
	 * 肌张力 异常 左侧 手得分下拉框
	 */
	@ViewInject(R.id.jzl_yc_zc_shou_spinner)
	private Spinner jzlYcZcShouSpinner;
	/**
	 * 肌张力 异常 左侧 上肢
	 */
	@ViewInject(R.id.jzl_yc_zc_sz_checkBox)
	private CheckBox jzlYcZcSzCheckBox;
	/**
	 * 肌张力 异常 左侧 上肢得分下拉框
	 */
	@ViewInject(R.id.jzl_yc_zc_sz_spinner)
	private Spinner jzlYcZcSzSpinner;
	/**
	 * 肌张力 异常 左侧 下肢
	 */
	@ViewInject(R.id.jzl_yc_zc_xz_checkBox)
	private CheckBox jzlYcZcXzCheckBox;
	/**
	 * 肌张力 异常 左侧 下肢得分下拉框
	 */
	@ViewInject(R.id.jzl_yc_zc_xz_spinner)
	private Spinner jzlYcZcXzSpinner;
	/**
	 * 肌张力 异常 左侧 其他
	 */
	@ViewInject(R.id.jzl_yc_zc_qt_checkBox)
	private TextView jzlYcZcQtCheckBox;
	/**
	 * 肌张力 异常 左侧 其他输入框
	 */
	@ViewInject(R.id.jzl_yc_zc_qt_editText)
	private EditText jzlYcZcQtEditText;
	/**
	 * 肌张力 异常 左侧 其他得分
	 */
	@ViewInject(R.id.jzl_yc_zc_qt_spinner)
	private Spinner jzlYcZcQtSpinner;
	/**
	 * 肌张力 异常 躯干
	 */
	@ViewInject(R.id.jzl_yc_qg_checkBox)
	private CheckBox jzlYcQgCheckBox;
	/**
	 * 肌张力 异常 躯干 背肌
	 */
	@ViewInject(R.id.jzl_yc_qg_bj_checkBox)
	private CheckBox jzlYcQgBjCheckBox;
	/**
	 * 肌张力 异常 躯干 背肌得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_qg_bj_spinner)
	private Spinner jzlYcQgBjSpinner;
	/**
	 * 肌张力 异常 躯干 腰肌
	 */
	@ViewInject(R.id.jzl_yc_qg_yj_checkBox)
	private CheckBox jzlYcQgYjCheckBox;
	/**
	 * 肌张力 异常 躯干 腰肌得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_qg_yj_spinner)
	private Spinner jzlYcQgYjSpinner;
	/**
	 * 肌张力 异常 躯干 臀肌
	 */
	@ViewInject(R.id.jzl_yc_qg_tj_checkBox)
	private CheckBox jzlYcQgTjCheckBox;
	/**
	 * 肌张力 异常 躯干 臀肌得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_qg_tj_spinner)
	private Spinner jzlYcQgTjSpinner;
	/**
	 * 肌张力 异常 躯干 胸肌
	 */
	@ViewInject(R.id.jzl_yc_qg_xj_checkBox)
	private CheckBox jzlYcQgXjCheckBox;
	/**
	 * 肌张力 异常 躯干 胸肌得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_qg_xj_spinner)
	private Spinner jzlYcQgXjSpinner;
	/**
	 * 肌张力 异常 躯干 腹肌
	 */
	@ViewInject(R.id.jzl_yc_qg_fj_checkBox)
	private CheckBox jzlYcQgFjCheckBox;
	/**
	 * 肌张力 异常 躯干 腹肌得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_qg_fj_spinner)
	private Spinner jzlYcQgFjSpinner;
	/**
	 * 肌张力 异常 躯干 其他
	 */
	@ViewInject(R.id.jzl_yc_qg_qt_checkBox)
	private TextView jzlYcQgQtCheckBox;
	/**
	 * 肌张力 异常 躯干 其他输入框
	 */
	@ViewInject(R.id.jzl_yc_qg_qt_editText)
	private EditText jzlYcQgQtEditText;
	/**
	 * 肌张力 异常 躯干 其他得分
	 */
	@ViewInject(R.id.jzl_yc_qg_qt_spinner)
	private Spinner jzlYcQgQtSpinner;
	/**
	 * 肌张力 异常 右侧
	 */
	@ViewInject(R.id.jzl_yc_rc_checkBox)
	private CheckBox jzlYcRcCheckBox;
	/**
	 * 肌张力 异常 右侧 上肢
	 */
	@ViewInject(R.id.jzl_yc_rc_sz_checkBox)
	private CheckBox jzlYcRcSzCheckBox;
	/**
	 * 肌张力 异常 右侧 上肢得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_rc_sz_spinner)
	private Spinner jzlYcRcSzSpinner;
	/**
	 * 肌张力 异常 右侧 下肢
	 */
	@ViewInject(R.id.jzl_yc_rc_xz_checkBox)
	private CheckBox jzlYcRcXzCheckBox;
	/**
	 * 肌张力 异常 右侧 下肢得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_rc_xz_spinner)
	private Spinner jzlYcRcXzSpinner;
	/**
	 * 肌张力 异常 右侧 脚
	 */
	@ViewInject(R.id.jzl_yc_rc_jiao_checkBox)
	private CheckBox jzlYcRcJiaoCheckBox;
	/**
	 * 肌张力 异常 右侧 脚得分 Spinner
	 */
	@ViewInject(R.id.jzl_yc_rc_jiao_spinner)
	private Spinner jzlYcRcJiaoSpinner;
	/**
	 * 肌张力 异常 右侧 其他
	 */
	@ViewInject(R.id.jzl_yc_rc_qt_checkBox)
	private TextView jzlYcRcQtCheckBox;
	/**
	 * 肌张力 异常 右侧 其他输入框
	 */
	@ViewInject(R.id.jzl_yc_rc_qt_editText)
	private EditText jzlYcRcQtEditText;
	/**
	 * 肌张力 异常 右侧 其他得分
	 */
	@ViewInject(R.id.jzl_yc_rc_qt_spinner)
	private Spinner jzlYcRcQtSpinner;
	/**
	 * 疼痛评分 有疼痛 左侧
	 */
	@ViewInject(R.id.ttpf_yc_zc_checkBox)
	private CheckBox ttpfYcZcCheckBox;
	/**
	 * 疼痛评分 有疼痛 左侧 手
	 */
	@ViewInject(R.id.ttpf_yc_zc_shou_checkBox)
	private CheckBox ttpfYcZcShouCheckBox;
	/**
	 * 疼痛评分 有疼痛 左侧 手得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_zc_shou_spinner)
	private Spinner ttpfYcZcShouSpinner;
	/**
	 * 疼痛评分 有疼痛 左侧 上肢
	 */
	@ViewInject(R.id.ttpf_yc_zc_sz_checkBox)
	private CheckBox ttpfYcZcSzCheckBox;
	/**
	 * 疼痛评分 有疼痛 左侧 上肢得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_zc_sz_spinner)
	private Spinner ttpfYcZcSzSpinner;
	/**
	 * 疼痛评分 有疼痛 左侧 下肢
	 */
	@ViewInject(R.id.ttpf_yc_zc_xz_checkBox)
	private CheckBox ttpfYcZcXzCheckBox;
	/**
	 * 疼痛评分 有疼痛 左侧 下肢得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_zc_xz_spinner)
	private Spinner ttpfYcZcXzSpinner;
	/**
	 * 疼痛评分 有疼痛 左侧 其他
	 */
	@ViewInject(R.id.ttpf_yc_zc_qt_checkBox)
	private TextView ttpfYcZcQtCheckBox;
	/**
	 * 疼痛评分 有疼痛 左侧 其他输入框
	 */
	@ViewInject(R.id.ttpf_yc_zc_qt_editText)
	private EditText ttpfYcZcQtEditText;
	/**
	 * 疼痛评分 有疼痛 左侧 其他得分
	 */
	@ViewInject(R.id.ttpf_yc_zc_qt_spinner)
	private Spinner ttpfYcZcQtSpinner;
	/**
	 * 疼痛评分 有疼痛 躯干
	 */
	@ViewInject(R.id.ttpf_yc_qg_checkBox)
	private CheckBox ttpfYcQgCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 背肌
	 */
	@ViewInject(R.id.ttpf_yc_qg_bj_checkBox)
	private CheckBox ttpfYcQgBjCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 背肌得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_qg_bj_spinner)
	private Spinner ttpfYcQgBjSpinner;
	/**
	 * 疼痛评分 有疼痛 躯干 腰肌
	 */
	@ViewInject(R.id.ttpf_yc_qg_yj_checkBox)
	private CheckBox ttpfYcQgYjCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 腰肌得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_qg_yj_spinner)
	private Spinner ttpfYcQgYjSpinner;
	/**
	 * 疼痛评分 有疼痛 躯干 臀肌
	 */
	@ViewInject(R.id.ttpf_yc_qg_tj_checkBox)
	private CheckBox ttpfYcQgTjCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 臀肌得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_qg_tj_spinner)
	private Spinner ttpfYcQgTjSpinner;
	/**
	 * 疼痛评分 有疼痛 躯干 胸肌
	 */
	@ViewInject(R.id.ttpf_yc_qg_xj_checkBox)
	private CheckBox ttpfYcQgXjCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 胸肌得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_qg_xj_spinner)
	private Spinner ttpfYcQgXjSpinner;
	
	/**
	 * 疼痛评分 有疼痛 躯干 腹肌
	 */
	@ViewInject(R.id.ttpf_yc_qg_fj_checkBox)
	private CheckBox ttpfYcQgFjCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 腹肌得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_qg_fj_spinner)
	private Spinner ttpfYcQgFjSpinner;
	/**
	 * 疼痛评分 有疼痛 躯干 其他
	 */
	@ViewInject(R.id.ttpf_yc_qg_qt_checkBox)
	private TextView ttpfYcQgQtCheckBox;
	/**
	 * 疼痛评分 有疼痛 躯干 其他输入框
	 */
	@ViewInject(R.id.ttpf_yc_qg_qt_editText)
	private EditText ttpfYcQgQtEditText;
	/**
	 * 疼痛评分 有疼痛 躯干 其他得分
	 */
	@ViewInject(R.id.ttpf_yc_qg_qt_spinner)
	private Spinner ttpfYcQgQtSpinner;
	/**
	 * 疼痛评分 有疼痛 右侧
	 */
	@ViewInject(R.id.ttpf_yc_rc_checkBox)
	private CheckBox ttpfYcRcCheckBox;
	/**
	 * 疼痛评分 有疼痛 右侧 上肢
	 */
	@ViewInject(R.id.ttpf_yc_rc_sz_checkBox)
	private CheckBox ttpfYcRcSzCheckBox;
	/**
	 * 疼痛评分 有疼痛 右侧 上肢得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_rc_sz_spinner)
	private Spinner ttpfYcRcSzSpinner;
	/**
	 * 疼痛评分 有疼痛 右侧 下肢
	 */
	@ViewInject(R.id.ttpf_yc_rc_xz_checkBox)
	private CheckBox ttpfYcRcXzCheckBox;
	/**
	 * 疼痛评分 有疼痛 右侧 下肢得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_rc_xz_spinner)
	private Spinner ttpfYcRcXzSpinner;
	/**
	 * 疼痛评分 有疼痛 右侧 脚
	 */
	@ViewInject(R.id.ttpf_yc_rc_jiao_checkBox)
	private CheckBox ttpfYcRcJiaoCheckBox;
	/**
	 * 疼痛评分 有疼痛 右侧 脚得分 Spinner
	 */
	@ViewInject(R.id.ttpf_yc_rc_jiao_spinner)
	private Spinner ttpfYcRcJiaoSpinner;
	/**
	 * 疼痛评分 有疼痛 右侧 其他
	 */
	@ViewInject(R.id.ttpf_yc_rc_qt_checkBox)
	private TextView ttpfYcRcQtCheckBox;
	/**
	 * 疼痛评分 有疼痛 右侧 其他输入框
	 */
	@ViewInject(R.id.ttpf_yc_rc_qt_editText)
	private EditText ttpfYcRcQtEditText;
	/**
	 * 疼痛评分 有疼痛 右侧 其他得分
	 */
	@ViewInject(R.id.ttpf_yc_rc_qt_spinner)
	private Spinner ttpfYcRcQtSpinner;
	/**
	 * 风险等级
	 */
	@ViewInject(R.id.fxdj_radioGroup)
	private RadioGroup fxdjRadioGroup;
	/**
	 * 风险等级 低危
	 */
	@ViewInject(R.id.fxdj_dw_radioButton)
	private RadioButton fxdjDwRadioButton;
	/**
	 * 风险等级 中危
	 */
	@ViewInject(R.id.fxdj_zw_radioButton)
	private RadioButton fxdjZwRadioButton;
	/**
	 * 风险等级 高危
	 */
	@ViewInject(R.id.fxdj_gw_radioButton)
	private RadioButton fxdjGwRadioButton;
	/**
	 * 风险等级 极高危
	 */
	@ViewInject(R.id.fxdj_jgw_radioButton)
	private RadioButton fxdjJgwRadioButton;
	/**
	 * 保存
	 */
	@ViewInject(R.id.sport_save_button)
	private Button sportSaveButton;
	/**
	 * 提交表单
	 */
	@ViewInject(R.id.sport_submit_button)
	private Button sportSubmitButton;

	
	private String zwph = "";// 坐位平衡
	private String lwph = "";// 立位平衡
	private String bxnl = "";// Holden步行能力评定
	
	private String jl = "";// 肌力 （异常 正常）
	private String jl_yc = "";// 肌力异常（左侧，躯干，右侧）
	private String jl_yc_zc = "";// 肌力异常左侧
	private String jl_yc_qg = "";// 肌力异常躯干
	private String jl_yc_rc = "";// 肌力异常右侧	
	private String jl_yc_zc_shou = "";//肌力 异常 左侧 手得分
	private String jl_yc_zc_sz = "";//肌力 异常 左侧 上肢得分
	private String jl_yc_zc_xz = "";//肌力 异常 左侧 下肢得分
	private String jl_yc_zc_qt = "";//肌力 异常 左侧 其他得分
	private String jl_yc_qg_bj = "";//肌力 异常 躯干 背肌得分
	private String jl_yc_qg_yj = "";//肌力 异常 躯干 腰肌得分
	private String jl_yc_qg_tj = "";//肌力 异常 躯干 臀肌得分
	private String jl_yc_qg_xj = "";//肌力 异常 躯干 胸肌得分
	private String jl_yc_qg_fj = "";//肌力 异常 躯干 腹肌得分
	private String jl_yc_qg_qt = "";//肌力 异常 躯干 其他得分	
	private String jl_yc_rc_sz = "";//肌力 异常 右侧 上肢得分
	private String jl_yc_rc_xz = "";//肌力 异常 右侧 下肢得分
	private String jl_yc_rc_jiao = "";//肌力 异常 右侧 脚得分
	private String jl_yc_rc_qt = "";//肌力 异常 右侧 其他得分
	
	private String jzl = "";// 肌张力（异常 正常）	
	private String jzl_yc = "";// 肌张力异常(左侧,躯干,右侧)
	private String jzl_yc_zc = "";// 肌张力异常左侧
	private String jzl_yc_qg = "";// 肌张力异常躯干
	private String jzl_yc_rc = "";// 肌张力异常右侧
	private String jzl_yc_zc_shou = "";//肌张力 异常 左侧 手得分
	private String jzl_yc_zc_sz = "";//肌张力 异常 左侧 上肢得分
	private String jzl_yc_zc_xz = "";//肌张力 异常 左侧 下肢得分
	private String jzl_yc_zc_qt = "";//肌张力 异常 左侧 其他得分
	private String jzl_yc_qg_bj = "";//肌张力 异常 躯干 背肌得分
	private String jzl_yc_qg_yj = "";//肌张力 异常 躯干 腰肌得分
	private String jzl_yc_qg_tj = "";//肌张力 异常 躯干 臀肌得分
	private String jzl_yc_qg_xj = "";//肌张力 异常 躯干 胸肌得分
	private String jzl_yc_qg_fj = "";//肌张力 异常 躯干 腹肌得分
	private String jzl_yc_qg_qt = "";//肌张力 异常 躯干 其他得分	
	private String jzl_yc_rc_sz = "";//肌张力 异常 右侧 上肢得分
	private String jzl_yc_rc_xz = "";//肌张力 异常 右侧 下肢得分
	private String jzl_yc_rc_jiao = "";//肌张力 异常 右侧 脚得分
	private String jzl_yc_rc_qt = "";//肌张力 异常 右侧 其他得分
	
	private String ttpf = "";// 疼痛评分（异常 正常）
	private String ttpf_yc = "";// 疼痛 异常（左侧，躯干，右侧）
	private String ttpf_yc_zc = "";//疼痛评分  有疼痛 左侧
	private String ttpf_yc_qg = "";//疼痛评分  有疼痛 躯干
	private String ttpf_yc_rc = "";//疼痛评分  有疼痛 右侧
	private String ttpf_yc_zc_shou = "";//疼痛评分 有疼痛 左侧 手得分
	private String ttpf_yc_zc_sz = "";//疼痛评分 有疼痛 左侧 上肢得分
	private String ttpf_yc_zc_xz = "";//疼痛评分 有疼痛 左侧 下肢得分
	private String ttpf_yc_zc_qt = "";//疼痛评分 有疼痛 左侧 其他得分
	private String ttpf_yc_qg_bj = "";//疼痛评分 有疼痛 躯干 背肌得分
	private String ttpf_yc_qg_yj = "";//疼痛评分 有疼痛 躯干 腰肌得分
	private String ttpf_yc_qg_tj = "";//疼痛评分 有疼痛 躯干 臀肌得分
	private String ttpf_yc_qg_xj = "";//疼痛评分 有疼痛 躯干 胸肌得分
	private String ttpf_yc_qg_fj = "";//疼痛评分 有疼痛 躯干 腹肌得分
	private String ttpf_yc_qg_qt = "";//疼痛评分 有疼痛 躯干 其他得分	
	private String ttpf_yc_rc_sz = "";//疼痛评分 有疼痛 右侧 上肢得分
	private String ttpf_yc_rc_xz = "";//疼痛评分 有疼痛 右侧 下肢得分
	private String ttpf_yc_rc_jiao = "";//疼痛评分 有疼痛 右侧 脚得分
	private String ttpf_yc_rc_qt = "";//疼痛评分 有疼痛 右侧 其他得分
	private String fxdj = "";//风险等级
	
	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				SportAssess sportAssessDB = (SportAssess) msg.obj;
				initData(sportAssessDB);
				break;
			case INITDATANET:// 回写网络
				SportAssess sportAssessNet = (SportAssess) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(SportAssessActivity.this);
				pinetreeDB.deleteSportAssessByScheID(customer.getId());
				sportAssessNet.setScheID(customer.getId());
				pinetreeDB.saveSportAssessByScheID(sportAssessNet);
				initData(sportAssessNet);
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
		setContentView(R.layout.activity_sport_assess);

		ViewUtils.inject(this);

		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");

		dialog = new Dialog(SportAssessActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);

		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus())) {
			sportSubmitButton.setVisibility(View.GONE);
			sportSaveButton.setVisibility(View.GONE);
		}
		
		backSportImageView.setOnClickListener(this);
		sportSaveButton.setOnClickListener(this);
		sportSubmitButton.setOnClickListener(this);
		
		selectListener();
		
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(SportAssessActivity.this);
		if (!"1".equals(customer.getProdType())) {
			//不为评估产品时
			sportSaveButton.setVisibility(View.GONE);
			sportSubmitButton.setVisibility(View.GONE);
		}
		if (NetUtil.checkNetWork(SportAssessActivity.this)) {
			dialog.show();
			RequestParams params = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("num", "2");
			params.addBodyParameter("prodType", customer.getProdType());
			HttpUtil.post("baseAndTestView.action", params, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					dialog.hide();	
					SportAssess sportAssessDB = pinetreeDB.getSportAssessByScheID(customer.getId());
					SportAssessBase sportAssessBase = GsonUtils.json2bean(responseInfo.result, SportAssessBase.class);
					Message message = Message.obtain();
					
					if (!"".equals(sportAssessBase.getSuccess()) && Boolean.valueOf(sportAssessBase.getSuccess())) {
						if (!"".equals(sportAssessDB) && null != sportAssessDB) {
							if (!"".equals(sportAssessDB.getCreateTime()) && !"".equals(sportAssessBase.getResultData().get(0).getCreateTime())) {
								try {
									String createTimeDB = sportAssessDB.getCreateTime();
									String createTimeNet = sportAssessBase.getResultData().get(0).getCreateTime();
									String timeDB = createTimeDB.substring(0, 10)+ " "+ createTimeDB.substring(11, 19);
									String timeNet = createTimeNet.substring(0, 10)+ " "+ createTimeNet.substring(11, 19);
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date dbDate = format.parse(timeDB);
									Date netDate = format.parse(timeNet);
									
									if (dbDate.compareTo(netDate) > 0) {
										message.obj = sportAssessDB;
										message.what = INITDATADB;
										handler.sendMessage(message);
									} else {
										message.obj = sportAssessBase
												.getResultData().get(0);
										message.what = INITDATANET;
										handler.sendMessage(message);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
							
							}else if ("".equals(sportAssessBase.getResultData().get(0).getCreateTime())) {
								message.obj = sportAssessDB;
								message.what = INITDATADB;
								handler.sendMessage(message);
							}else if ("".equals(sportAssessDB.getCreateTime())) {
								message.obj = sportAssessBase
										.getResultData().get(0);
								message.what = INITDATANET;
								handler.sendMessage(message);
							}
						}else {
							message.obj = sportAssessBase
									.getResultData().get(0);
							message.what = INITDATANET;
							handler.sendMessage(message);
						}					
					}else {
						if (!"".equals(sportAssessDB) && null != sportAssessDB) {
							initData(sportAssessDB);
						}		
					}
					
				}
				
				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();					
				}
			});
			
			
		}else {
			SportAssess sportAssessDB = pinetreeDB.getSportAssessByScheID(customer.getId());
			if (!"".equals(sportAssessDB) && null != sportAssessDB) {
				initData(sportAssessDB);
			}
		}
	}

	/**
	 * 给spinner 绑定监听器
	 */
	private void selectListener() {
		
		strengthRadioGroup.setOnCheckedChangeListener(this);//肌力 正常 异常单选按钮
		muscleTensionRadioGroup.setOnCheckedChangeListener(this);//肌张力 正常 异常单选按钮
		painRadioGroup.setOnCheckedChangeListener(this);//疼痛评分 正常 异常单选按钮
		
		jlYcZcSzCheckBox.setOnCheckedChangeListener(this);//肌力 左侧 上肢
		jlYcZcXzCheckBox.setOnCheckedChangeListener(this);//肌力 左侧 下肢
		jlYcZcShouCheckBox.setOnCheckedChangeListener(this);//肌力 左侧 脚
		jlYcZcCheckBox.setOnCheckedChangeListener(this);//肌力 左侧 		
		jlYcQgBjCheckBox.setOnCheckedChangeListener(this);//肌力 躯干 背肌
		jlYcQgYjCheckBox.setOnCheckedChangeListener(this);//肌力 躯干 腰肌
		jlYcQgTjCheckBox.setOnCheckedChangeListener(this);//肌力 躯干 臀肌
		jlYcQgXjCheckBox.setOnCheckedChangeListener(this);//肌力 躯干 胸肌
		jlYcQgFjCheckBox.setOnCheckedChangeListener(this);//肌力 躯干 腹肌
		jlYcQgCheckBox.setOnCheckedChangeListener(this);//肌力 躯干
		jlYcRcSzCheckBox.setOnCheckedChangeListener(this);//肌力 右侧 上肢
		jlYcRcXzCheckBox.setOnCheckedChangeListener(this);//肌力 右侧 下肢
		jlYcRcJiaoCheckBox.setOnCheckedChangeListener(this);//肌力 右侧 脚
		jlYcRcCheckBox.setOnCheckedChangeListener(this);//肌力 右侧 
		
		jzlYcZcSzCheckBox.setOnCheckedChangeListener(this);//肌张力 左侧 上肢
		jzlYcZcXzCheckBox.setOnCheckedChangeListener(this);//肌张力 左侧 下肢
		jzlYcZcShouCheckBox.setOnCheckedChangeListener(this);//肌张力 左侧 脚
		jzlYcZcCheckBox.setOnCheckedChangeListener(this);//肌张力 左侧 		
		jzlYcQgBjCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干 背肌
		jzlYcQgYjCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干 腰肌
		jzlYcQgTjCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干 臀肌
		jzlYcQgXjCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干 胸肌
		jzlYcQgFjCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干 腹肌
		jzlYcQgCheckBox.setOnCheckedChangeListener(this);//肌张力 躯干
		jzlYcRcSzCheckBox.setOnCheckedChangeListener(this);//肌张力 右侧 上肢
		jzlYcRcXzCheckBox.setOnCheckedChangeListener(this);//肌张力 右侧 下肢
		jzlYcRcJiaoCheckBox.setOnCheckedChangeListener(this);//肌张力 右侧 脚
		jzlYcRcCheckBox.setOnCheckedChangeListener(this);//肌张力 右侧 
		
		ttpfYcZcSzCheckBox.setOnCheckedChangeListener(this);//疼痛评分 左侧 上肢
		ttpfYcZcXzCheckBox.setOnCheckedChangeListener(this);//疼痛评分 左侧 下肢
		ttpfYcZcShouCheckBox.setOnCheckedChangeListener(this);//疼痛评分 左侧 脚
		ttpfYcZcCheckBox.setOnCheckedChangeListener(this);//疼痛评分 左侧 		
		ttpfYcQgBjCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干 背肌
		ttpfYcQgYjCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干 腰肌
		ttpfYcQgTjCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干 臀肌
		ttpfYcQgXjCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干 胸肌
		ttpfYcQgFjCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干 腹肌
		ttpfYcQgCheckBox.setOnCheckedChangeListener(this);//疼痛评分 躯干
		ttpfYcRcSzCheckBox.setOnCheckedChangeListener(this);//疼痛评分 右侧 上肢
		ttpfYcRcXzCheckBox.setOnCheckedChangeListener(this);//疼痛评分 右侧 下肢
		ttpfYcRcJiaoCheckBox.setOnCheckedChangeListener(this);//疼痛评分 右侧 脚
		ttpfYcRcCheckBox.setOnCheckedChangeListener(this);//疼痛评分 右侧 
		
		zwphSpinner.setOnItemSelectedListener(this);//坐位平衡 选级
		lwphSpinner.setOnItemSelectedListener(this);//立位平衡 选级
		bxnlSpinner.setOnItemSelectedListener(this);//步行能力评定 选级
		
		jlYcZcShouSpinner.setOnItemSelectedListener(this);// 肌力 左侧 手得分
		jlYcZcSzSpinner.setOnItemSelectedListener(this);// 肌力 左侧 上肢得分
		jlYcZcXzSpinner.setOnItemSelectedListener(this);// 肌力 左侧 下肢得分
		jlYcZcQtSpinner.setOnItemSelectedListener(this);// 肌力 左侧 其他得分
		jlYcQgBjSpinner.setOnItemSelectedListener(this);// 肌力 躯干 背肌得分
		jlYcQgYjSpinner.setOnItemSelectedListener(this);// 肌力 躯干 腰肌得分
		jlYcQgTjSpinner.setOnItemSelectedListener(this);// 肌力 躯干 臀肌得分
		jlYcQgXjSpinner.setOnItemSelectedListener(this);// 肌力 躯干 胸肌得分
		jlYcQgFjSpinner.setOnItemSelectedListener(this);// 肌力 躯干 腹肌得分
		jlYcQgQtSpinner.setOnItemSelectedListener(this);// 肌力 躯干 其他得分
		jlYcRcXzSpinner.setOnItemSelectedListener(this);// 肌力 右侧 下肢得分
		jlYcRcSzSpinner.setOnItemSelectedListener(this);// 肌力 右侧 上肢得分
		jlYcRcJiaoSpinner.setOnItemSelectedListener(this);// 肌力 右侧 脚得分
		jlYcRcQtSpinner.setOnItemSelectedListener(this);// 肌力 右侧 其他得分
			
		jzlYcZcShouSpinner.setOnItemSelectedListener(this);// 肌张力 左侧 手得分
		jzlYcZcSzSpinner.setOnItemSelectedListener(this);// 肌张力 左侧 上肢得分
		jzlYcZcXzSpinner.setOnItemSelectedListener(this);// 肌张力 左侧 下肢得分
		jzlYcZcQtSpinner.setOnItemSelectedListener(this);// 肌张力 左侧 其他得分
		jzlYcQgBjSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 背肌得分
		jzlYcQgYjSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 腰肌得分
		jzlYcQgTjSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 臀肌得分
		jzlYcQgXjSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 胸肌得分
		jzlYcQgFjSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 腹肌得分
		jzlYcQgQtSpinner.setOnItemSelectedListener(this);// 肌张力 躯干 其他得分
		jzlYcRcXzSpinner.setOnItemSelectedListener(this);// 肌张力 右侧 下肢得分
		jzlYcRcSzSpinner.setOnItemSelectedListener(this);// 肌张力 右侧 上肢得分
		jzlYcRcJiaoSpinner.setOnItemSelectedListener(this);// 肌张力 右侧 脚得分
		jzlYcRcQtSpinner.setOnItemSelectedListener(this);// 肌张力 右侧 其他得分
		
		ttpfYcZcShouSpinner.setOnItemSelectedListener(this);// 疼痛评分 左侧 手得分
		ttpfYcZcSzSpinner.setOnItemSelectedListener(this);// 疼痛评分 左侧 上肢得分
		ttpfYcZcXzSpinner.setOnItemSelectedListener(this);// 疼痛评分 左侧 下肢得分
		ttpfYcZcQtSpinner.setOnItemSelectedListener(this);// 疼痛评分 左侧 其他得分
		ttpfYcQgBjSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 背肌得分
		ttpfYcQgYjSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 腰肌得分
		ttpfYcQgTjSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 臀肌得分
		ttpfYcQgXjSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 胸肌得分
		ttpfYcQgFjSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 腹肌得分
		ttpfYcQgQtSpinner.setOnItemSelectedListener(this);// 疼痛评分 躯干 其他得分
		ttpfYcRcXzSpinner.setOnItemSelectedListener(this);// 疼痛评分 右侧 下肢得分
		ttpfYcRcSzSpinner.setOnItemSelectedListener(this);// 疼痛评分 右侧 上肢得分
		ttpfYcRcJiaoSpinner.setOnItemSelectedListener(this);// 疼痛评分 右侧 脚得分
		ttpfYcRcQtSpinner.setOnItemSelectedListener(this);// 疼痛评分 右侧 其他得分
		
	}

	/**
	 * 清空变量
	 */
	public void clearVariable(){
		
		zwph="";//坐位平衡
		lwph="";//立位平衡
		bxnl="";//Holden步行能力评定
		jl="";//肌力 （异常 正常）
		jzl="";//肌张力（异常 正常）
		ttpf="";//疼痛评分（异常 正常）
		jl_yc = "";//肌力异常（左侧，躯干，右侧）
		jzl_yc = "";//肌张力异常(左侧,躯干,右侧)
		ttpf_yc = "";//疼痛 异常（左侧，躯干，右侧）
		jzl_yc_zc = "";//肌张力异常左侧
		jzl_yc_qg="";//肌张力异常躯干
		jzl_yc_rc="";//肌张力异常右侧
		jl_yc_zc="";//肌力异常左侧
		jl_yc_qg="";//肌力异常躯干
		jl_yc_rc="";//肌力异常右侧
		ttpf_yc_zc = "";//疼痛评分  有疼痛 左侧
		ttpf_yc_qg = "";//疼痛评分  有疼痛 躯干
		ttpf_yc_rc = "";//疼痛评分  有疼痛 右侧
		jl_yc_zc_shou = "";//肌力 异常 左侧 手得分
		jl_yc_zc_sz = "";//肌力 异常 左侧 上肢得分	
		jl_yc_zc_xz="";//肌力 异常 左侧 下肢得分
		jl_yc_zc_qt="";//肌力 异常 左侧 其他得分	
		jl_yc_qg_bj="";//肌力 异常 躯干 背肌得分
		jl_yc_qg_yj = "";//肌力 异常 躯干 腰肌得分
		jl_yc_qg_tj = "";//肌力 异常 躯干 臀肌得分
		jl_yc_qg_xj = "";//肌力 异常 躯干 胸肌得分
		jl_yc_qg_fj = "";//肌力 异常 躯干 腹肌得分
		jl_yc_qg_qt = "";//肌力 异常 躯干 其他得分		
		jl_yc_rc_sz="";//肌力 异常 右侧 上肢得分
		jl_yc_rc_xz="";//肌力 异常 右侧 下肢得分
		jl_yc_rc_jiao="";//肌力 异常 右侧 脚得分
		jl_yc_rc_qt="";//肌力 异常 右侧 其他得分
		
		jzl_yc_zc_shou = "";//肌张力 异常 左侧 手得分
		jzl_yc_zc_sz = "";//肌张力 异常 左侧 上肢得分	
		jzl_yc_zc_xz="";//肌张力 异常 左侧 下肢得分
		jzl_yc_zc_qt="";//肌张力 异常 左侧 其他得分	
		jzl_yc_qg_bj="";//肌张力 异常 躯干 背肌得分
		jzl_yc_qg_yj = "";//肌张力 异常 躯干 腰肌得分
		jzl_yc_qg_tj = "";//肌张力 异常 躯干 臀肌得分
		jzl_yc_qg_xj = "";//肌张力 异常 躯干 胸肌得分
		jzl_yc_qg_fj = "";//肌张力 异常 躯干 腹肌得分
		jzl_yc_qg_qt = "";//肌张力 异常 躯干 其他得分		
		jzl_yc_rc_sz="";//肌张力 异常 右侧 上肢得分
		jzl_yc_rc_xz="";//肌张力 异常 右侧 下肢得分
		jzl_yc_rc_jiao="";//肌张力 异常 右侧 脚得分
		jzl_yc_rc_qt="";//肌张力 异常 右侧 其他得分
		
		ttpf_yc_zc_shou = "";//疼痛评分  有疼痛  左侧 手得分
		ttpf_yc_zc_sz = "";//疼痛评分  有疼痛 左侧 上肢得分	
		ttpf_yc_zc_xz="";//疼痛评分  有疼痛 左侧 下肢得分
		ttpf_yc_zc_qt="";//疼痛评分  有疼痛 左侧 其他得分	
		ttpf_yc_qg_bj="";//疼痛评分  有疼痛 躯干 背肌得分
		ttpf_yc_qg_yj = "";//疼痛评分  有疼痛 躯干 腰肌得分
		ttpf_yc_qg_tj = "";//疼痛评分  有疼痛 躯干 臀肌得分
		ttpf_yc_qg_xj = "";//疼痛评分  有疼痛 躯干 胸肌得分
		ttpf_yc_qg_fj = "";//疼痛评分  有疼痛 躯干 腹肌得分
		ttpf_yc_qg_qt = "";//疼痛评分  有疼痛 躯干 其他得分		
		ttpf_yc_rc_sz="";//疼痛评分  有疼痛 右侧 上肢得分
		ttpf_yc_rc_xz="";//疼痛评分  有疼痛 右侧 下肢得分
		ttpf_yc_rc_jiao="";//疼痛评分  有疼痛 右侧 脚得分
		ttpf_yc_rc_qt="";//疼痛评分  有疼痛 右侧 其他得分
		fxdj = "";//风险等级
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_sport_imageView:
			finish();
			break;
		case R.id.sport_submit_button:// 提交按钮
			dialog.show();
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(SportAssessActivity.this);
			RecordSubmitState recordSubmitStateSubmit = pinetreeDB.getRecordSubmitState(customer.getId());
			if (null != recordSubmitStateSubmit &&  !"".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBasicSubmitState())) {
				if (NetUtil.checkNetWork(SportAssessActivity.this)) {
					getElementData();
					RequestParams params = new RequestParams();
					params.addBodyParameter("empID",employeeId);
					params.addBodyParameter("scheID", customer.getId());			
					params.addBodyParameter("custID",customer.getCustID());
					params.addBodyParameter("dictionary.zwph", zwph);
					params.addBodyParameter("dictionary.lwph", lwph);
					params.addBodyParameter("dictionary.bxnl", bxnl);
					params.addBodyParameter("dictionary.jl", jl);
					params.addBodyParameter("dictionary.jzl", jzl);
					params.addBodyParameter("dictionary.ttpf", ttpf);
					params.addBodyParameter("dictionary.jl_yc", jl_yc);
					params.addBodyParameter("dictionary.jzl_yc", jzl_yc);
					params.addBodyParameter("dictionary.ttpf_yc", ttpf_yc);
					params.addBodyParameter("dictionary.jzl_yc_zc", jzl_yc_zc);
					params.addBodyParameter("dictionary.jzl_yc_qg", jzl_yc_qg);
					params.addBodyParameter("dictionary.jzl_yc_rc", jzl_yc_rc);
					params.addBodyParameter("dictionary.jl_yc_zc", jl_yc_zc);
					params.addBodyParameter("dictionary.jl_yc_qg", jl_yc_qg);
					params.addBodyParameter("dictionary.jl_yc_rc", jl_yc_rc);				
					params.addBodyParameter("dictionary.ttpf_yc_zc", ttpf_yc_zc);
					params.addBodyParameter("dictionary.ttpf_yc_qg", ttpf_yc_qg);
					params.addBodyParameter("dictionary.ttpf_yc_rc", ttpf_yc_rc);
					params.addBodyParameter("dictionary.jl_yc_zc_shou", jl_yc_zc_shou);				
					params.addBodyParameter("dictionary.jl_yc_zc_sz", jl_yc_zc_sz);
					params.addBodyParameter("dictionary.jl_yc_zc_xz", jl_yc_zc_xz);				
					params.addBodyParameter("dictionary.jl_yc_zc_qt", jl_yc_zc_qt);																				
					params.addBodyParameter("dictionary.jl_yc_qg_bj", jl_yc_qg_bj);
					params.addBodyParameter("dictionary.jl_yc_qg_yj", jl_yc_qg_yj);
					params.addBodyParameter("dictionary.jl_yc_qg_tj", jl_yc_qg_tj);				
					params.addBodyParameter("dictionary.jl_yc_qg_xj", jl_yc_qg_xj);
					params.addBodyParameter("dictionary.jl_yc_qg_fj", jl_yc_qg_fj);
					params.addBodyParameter("dictionary.jl_yc_qg_qt", jl_yc_qg_qt);
					params.addBodyParameter("dictionary.jl_yc_rc_sz", jl_yc_rc_sz);				
					params.addBodyParameter("dictionary.jl_yc_rc_xz", jl_yc_rc_xz);
					params.addBodyParameter("dictionary.jl_yc_rc_jiao", jl_yc_rc_jiao);				
					params.addBodyParameter("dictionary.jl_yc_rc_qt", jl_yc_rc_qt);
					params.addBodyParameter("dictionary.jzl_yc_zc_shou", jzl_yc_zc_shou);				
					params.addBodyParameter("dictionary.jzl_yc_zc_sz", jzl_yc_zc_sz);
					params.addBodyParameter("dictionary.jzl_yc_zc_xz", jzl_yc_zc_xz);				
					params.addBodyParameter("dictionary.jzl_yc_zc_qt", jzl_yc_zc_qt);																				
					params.addBodyParameter("dictionary.jzl_yc_qg_bj", jzl_yc_qg_bj);
					params.addBodyParameter("dictionary.jzl_yc_qg_yj", jzl_yc_qg_yj);
					params.addBodyParameter("dictionary.jzl_yc_qg_tj", jzl_yc_qg_tj);				
					params.addBodyParameter("dictionary.jzl_yc_qg_xj", jzl_yc_qg_xj);
					params.addBodyParameter("dictionary.jzl_yc_qg_fj", jzl_yc_qg_fj);
					params.addBodyParameter("dictionary.jzl_yc_qg_qt", jzl_yc_qg_qt);
					params.addBodyParameter("dictionary.jzl_yc_rc_sz", jzl_yc_rc_sz);				
					params.addBodyParameter("dictionary.jzl_yc_rc_xz", jzl_yc_rc_xz);
					params.addBodyParameter("dictionary.jzl_yc_rc_jiao", jzl_yc_rc_jiao);				
					params.addBodyParameter("dictionary.jzl_yc_rc_qt", jzl_yc_rc_qt);			
					params.addBodyParameter("dictionary.ttpf_yc_zc_shou", ttpf_yc_zc_shou);				
					params.addBodyParameter("dictionary.ttpf_yc_zc_sz", ttpf_yc_zc_sz);
					params.addBodyParameter("dictionary.ttpf_yc_zc_xz", ttpf_yc_zc_xz);				
					params.addBodyParameter("dictionary.ttpf_yc_zc_qt", ttpf_yc_zc_qt);																				
					params.addBodyParameter("dictionary.ttpf_yc_qg_bj", ttpf_yc_qg_bj);
					params.addBodyParameter("dictionary.ttpf_yc_qg_yj", ttpf_yc_qg_yj);
					params.addBodyParameter("dictionary.ttpf_yc_qg_tj", ttpf_yc_qg_tj);				
					params.addBodyParameter("dictionary.ttpf_yc_qg_xj", ttpf_yc_qg_xj);
					params.addBodyParameter("dictionary.ttpf_yc_qg_fj", ttpf_yc_qg_fj);
					params.addBodyParameter("dictionary.ttpf_yc_qg_qt", ttpf_yc_qg_qt);
					params.addBodyParameter("dictionary.ttpf_yc_rc_sz", ttpf_yc_rc_sz);				
					params.addBodyParameter("dictionary.ttpf_yc_rc_xz", ttpf_yc_rc_xz);
					params.addBodyParameter("dictionary.ttpf_yc_rc_jiao", ttpf_yc_rc_jiao);				
					params.addBodyParameter("dictionary.ttpf_yc_rc_qt", ttpf_yc_rc_qt);
					params.addBodyParameter("dictionary.fxdj", fxdj);			
					params.addBodyParameter("num", "2");
					
					if (null!=recordSubmitStateSubmit&&!"".equals(recordSubmitStateSubmit)) {
						if ("1".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getEverydaySubmitState()) && "1".equals(recordSubmitStateSubmit.getDrugSubmitState()) && "1".equals(recordSubmitStateSubmit.getBrainSubmitState()) && "1".equals(recordSubmitStateSubmit.getCopmSubmitState())) {
							params.addBodyParameter("custCustinfoDetail.status", "1");
						}else {
							params.addBodyParameter("custCustinfoDetail.status", "0");
						}
					}else {
						params.addBodyParameter("custCustinfoDetail.status", "0");
					}
					
					HttpUtil.post("drugUseReportSave.action", params, new RequestCallBack<String>() {
						
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							GlobalResult globalResult = GsonUtils.json2bean(responseInfo.result, GlobalResult.class);	
							if (!"".equals(globalResult.getSuccess())&&Boolean.valueOf(globalResult.getSuccess())) {
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(SportAssessActivity.this);
								pinetreeDB.deleteSportAssessByScheID(customer.getId());
								SportAssess sportAssess = setData();
								pinetreeDB.saveSportAssessByScheID(sportAssess);
								
								if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
									pinetreeDB.updateRecordSubmitStateSport(customer.getId());
								}else {
									RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
									recordSubmitStateDB.setScheID(customer.getId());
									recordSubmitStateDB.setSportSubmitState("1");
									pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
								} 
							
								ToastUtils.showToast(SportAssessActivity.this, "提交成功");
								sportSubmitButton.setVisibility(View.GONE);
								sportSaveButton.setVisibility(View.GONE);
								
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
								ToastUtils.showToast(SportAssessActivity.this, "提交失败，请重试！");
							}
							
						}
						
						@Override
						public void onFailure(HttpException error, String msg) {
							clearVariable();
							ToastUtils.showToast(SportAssessActivity.this, "提交失败，请重试！");
							dialog.hide();						
						}
					});
								
				}else {
					dialog.hide();
					ToastUtils.showToast(SportAssessActivity.this, "亲，没有网络哦");
				}
			}else {
				dialog.hide();	
				ToastUtils.showToast(SportAssessActivity.this, "亲，请先填写并提交基本情况表");
			}
			break;
		case R.id.sport_save_button:// 保存按钮
			PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(SportAssessActivity.this);
			pinetreeDB1.deleteSportAssessByScheID(customer.getId());
			getElementData();
			SportAssess sportAssess = setData();
			boolean b = pinetreeDB1.saveSportAssessByScheID(sportAssess);
			if (b) {
				ToastUtils.showToast(SportAssessActivity.this, "保存成功");
			} else {
				ToastUtils.showToast(SportAssessActivity.this, "保存失败，请重试");
			}			
			
			break;
		default:
			break;
		}

	}

	private SportAssess setData() {
		SportAssess sportAssess = new SportAssess();
		sportAssess.setScheID(customer.getId());
		sportAssess.setCustID(customer.getCustID());						
		sportAssess.setZwph(zwph);
		sportAssess.setLwph(lwph);				
		sportAssess.setBxnl(bxnl);
		
		sportAssess.setJl(jl);						
		sportAssess.setJl_yc(jl_yc);
		sportAssess.setJl_yc_zc(jl_yc_zc);
		sportAssess.setJl_yc_qg(jl_yc_qg);
		sportAssess.setJl_yc_rc(jl_yc_rc);
		sportAssess.setJl_yc_zc_shou(jl_yc_zc_shou);
		sportAssess.setJl_yc_zc_sz(jl_yc_zc_sz);		
		sportAssess.setJl_yc_zc_xz(jl_yc_zc_xz);
		sportAssess.setJl_yc_zc_qt(jl_yc_zc_qt);						
		sportAssess.setJl_yc_qg_bj(jl_yc_qg_bj);
		sportAssess.setJl_yc_qg_yj(jl_yc_qg_yj);				
		sportAssess.setJl_yc_qg_tj(jl_yc_qg_tj);
		sportAssess.setJl_yc_qg_xj(jl_yc_qg_xj);						
		sportAssess.setJl_yc_qg_fj(jl_yc_qg_fj);
		sportAssess.setJl_yc_qg_qt(jl_yc_qg_qt);
		sportAssess.setJl_yc_rc_sz(jl_yc_rc_sz);										
		sportAssess.setJl_yc_rc_xz(jl_yc_rc_xz);
		sportAssess.setJl_yc_rc_jiao(jl_yc_rc_jiao);
		sportAssess.setJl_yc_rc_qt(jl_yc_rc_qt);	
		
		sportAssess.setJzl(jzl);
		sportAssess.setJzl_yc(jzl_yc);								
		sportAssess.setJzl_yc_zc(jzl_yc_zc);				
		sportAssess.setJzl_yc_qg(jzl_yc_qg);
		sportAssess.setJzl_yc_rc(jzl_yc_rc);	
		sportAssess.setJzl_yc_zc_shou(jzl_yc_zc_shou);
		sportAssess.setJzl_yc_zc_sz(jzl_yc_zc_sz);		
		sportAssess.setJzl_yc_zc_xz(jzl_yc_zc_xz);
		sportAssess.setJzl_yc_zc_qt(jzl_yc_zc_qt);						
		sportAssess.setJzl_yc_qg_bj(jzl_yc_qg_bj);
		sportAssess.setJzl_yc_qg_yj(jzl_yc_qg_yj);				
		sportAssess.setJzl_yc_qg_tj(jzl_yc_qg_tj);
		sportAssess.setJzl_yc_qg_xj(jzl_yc_qg_xj);						
		sportAssess.setJzl_yc_qg_fj(jzl_yc_qg_fj);
		sportAssess.setJzl_yc_qg_qt(jzl_yc_qg_qt);
		sportAssess.setJzl_yc_rc_sz(jzl_yc_rc_sz);										
		sportAssess.setJzl_yc_rc_xz(jzl_yc_rc_xz);
		sportAssess.setJzl_yc_rc_jiao(jzl_yc_rc_jiao);
		sportAssess.setJzl_yc_rc_qt(jzl_yc_rc_qt);		
		
		sportAssess.setTtpf(ttpf);	
		sportAssess.setTtpf_yc(ttpf_yc);
		sportAssess.setTtpf_yc_zc(ttpf_yc_zc);
		sportAssess.setTtpf_yc_qg(ttpf_yc_qg);
		sportAssess.setTtpf_yc_rc(ttpf_yc_rc);
		sportAssess.setTtpf_yc_zc_shou(ttpf_yc_zc_shou);
		sportAssess.setTtpf_yc_zc_sz(ttpf_yc_zc_sz);		
		sportAssess.setTtpf_yc_zc_xz(ttpf_yc_zc_xz);
		sportAssess.setTtpf_yc_zc_qt(ttpf_yc_zc_qt);						
		sportAssess.setTtpf_yc_qg_bj(ttpf_yc_qg_bj);
		sportAssess.setTtpf_yc_qg_yj(ttpf_yc_qg_yj);				
		sportAssess.setTtpf_yc_qg_tj(ttpf_yc_qg_tj);
		sportAssess.setTtpf_yc_qg_xj(ttpf_yc_qg_xj);						
		sportAssess.setTtpf_yc_qg_fj(ttpf_yc_qg_fj);
		sportAssess.setTtpf_yc_qg_qt(ttpf_yc_qg_qt);
		sportAssess.setTtpf_yc_rc_sz(ttpf_yc_rc_sz);										
		sportAssess.setTtpf_yc_rc_xz(ttpf_yc_rc_xz);
		sportAssess.setTtpf_yc_rc_jiao(ttpf_yc_rc_jiao);
		sportAssess.setTtpf_yc_rc_qt(ttpf_yc_rc_qt);
		sportAssess.setFxdj(fxdj);
		
		// 获取系统时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		sportAssess.setCreateTime(createDate + "T" + createTime);
		return sportAssess;
	}

	/**
	 * 获取元素的值
	 */
	private void getElementData() {
		// 坐位平衡
		if (!"请选择".equals(zwphSpinner.getSelectedItem().toString().trim())) {
			if (!"bsy".equals(getPhScore(zwphSpinner.getSelectedItem().toString().trim()))) {
				zwph = getPhScore(zwphSpinner.getSelectedItem().toString().trim()) + "|";
			}else {
				zwph += getPhScore(zwphSpinner.getSelectedItem().toString().trim()) + "|";
				if (!"".equals(zwphBsyyyEditText.getText().toString().trim())) {
					zwph += "zwphbsyyy:" + zwphBsyyyEditText.getText().toString().trim()
							+ "|";
				}
			}			
		}		
		if (!"".equals(zwph)) {
			zwph = zwph.substring(0, zwph.length() - 1);
		}
		// 立位平衡
		if (!"请选择".equals(lwphSpinner.getSelectedItem().toString().trim())) {
			if (!"bsy".equals(getPhScore(lwphSpinner.getSelectedItem().toString().trim()))) {
				lwph = getPhScore(lwphSpinner.getSelectedItem().toString().trim()) + "|";
			}else {
				lwph += getPhScore(lwphSpinner.getSelectedItem().toString().trim()) + "|";
				if (!"".equals(lwphBsyyyEditText.getText().toString().trim())) {
					lwph += "lwphbsyyy:" + lwphBsyyyEditText.getText().toString().trim()
							+ "|";
				}
			}					
		}
		if (!"".equals(lwph)) {
			lwph = lwph.substring(0, lwph.length() - 1);
		}
		// Holden步行能力评定
		if (!"请选择".equals(bxnlSpinner.getSelectedItem().toString().trim())) {
			if (!"bsy".equals(getPhScore(bxnlSpinner.getSelectedItem().toString().trim()))) {
			bxnl = getBxnlScore(bxnlSpinner.getSelectedItem().toString().trim()) + "|";
			}else {
				bxnl += getBxnlScore(bxnlSpinner.getSelectedItem().toString().trim()) + "|";
				if (!"".equals(bxnlBsyyyEditText.getText().toString().trim())) {
					bxnl += "bxnlbsyyy:" + bxnlBsyyyEditText.getText().toString().trim()
							+ "|";
				}
			}
		}
		if (!"".equals(bxnl)) {
			bxnl = bxnl.substring(0, bxnl.length() - 1);
		}
		// 肌力 单选按钮（正常，异常）
		RadioButton jlRadioButton = (RadioButton) findViewById(strengthRadioGroup
				.getCheckedRadioButtonId());
		if ("正常".equals(jlRadioButton.getText().toString().trim())) {
			jl = "zc";
		} else {
			jl = "yc";
		}
		// 肌张力 单选按钮（正常，异常）
		RadioButton jzlRadioButton = (RadioButton) findViewById(muscleTensionRadioGroup
				.getCheckedRadioButtonId());
		if ("正常".equals(jzlRadioButton.getText().toString().trim())) {
			jzl = "zc";
		} else {
			jzl = "yc";
		}
		// 疼痛评分
		RadioButton ttpfRadioButton = (RadioButton) findViewById(painRadioGroup
				.getCheckedRadioButtonId());
		if ("无疼痛".equals(ttpfRadioButton.getText().toString().trim())) {
			ttpf = "zc";
		} else {
			ttpf = "yc";
		}
		
		// 肌力 （左侧，躯干，右侧）
		if (jlYcZcCheckBox.isChecked()) {
			jl_yc += "zc|";
		}
		if (jlYcQgCheckBox.isChecked()) {
			jl_yc += "qg|";
		}
		if (jlYcRcCheckBox.isChecked()) {
			jl_yc += "rc|";
		}
		if (!"".equals(jl_yc)) {
			jl_yc = jl_yc.substring(0, jl_yc.length() - 1);
		}
		// 肌力 异常 左侧 jl_yc_zc
		if (jlYcZcShouCheckBox.isChecked()) {
			jl_yc_zc += "shou|";
		}
		if (jlYcZcSzCheckBox.isChecked()) {
			jl_yc_zc += "sz|";
		}
		if (jlYcZcXzCheckBox.isChecked()) {
			jl_yc_zc += "xz|";
		}
		if (!"".equals(jlYcZcQtEditText.getText().toString().trim())) {
			jl_yc_zc += "jlyczcqt:" + jlYcZcQtEditText.getText().toString().trim() +"|";
		}
		if (!"".equals(jl_yc_zc)) {
			jl_yc_zc = jl_yc_zc.substring(0, jl_yc_zc.length() - 1);
		}
		// 肌力 异常 躯干 jzl_yc_qg
		if (jlYcQgBjCheckBox.isChecked()) {
			jl_yc_qg += "bj|";
		}
		if (jlYcQgYjCheckBox.isChecked()) {
			jl_yc_qg += "yj|";
		}
		if (jlYcQgTjCheckBox.isChecked()) {
			jl_yc_qg += "tj|";
		}
		if (jlYcQgXjCheckBox.isChecked()) {
			jl_yc_qg += "xj|";
		}
		if (jlYcQgFjCheckBox.isChecked()) {
			jl_yc_qg += "fj|";
		}
		if (!"".equals(jlYcQgQtEditText.getText().toString().trim())) {
			jl_yc_qg += "jlycqgqt:" + jlYcQgQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(jl_yc_qg)) {
			jl_yc_qg.substring(0, jl_yc_qg.length() - 1);
		}
		// 肌力异常 右侧 jl_yc_rc
		if (jlYcRcSzCheckBox.isChecked()) {
			jl_yc_rc += "sz|";
		}
		if (jlYcRcXzCheckBox.isChecked()) {
			jl_yc_rc += "xz|";
		}
		if (jlYcRcJiaoCheckBox.isChecked()) {
			jl_yc_rc += "jiao|";
		}
		if (!"".equals(jlYcRcQtEditText.getText().toString().trim())) {
			jl_yc_rc += "jlycrcqt:" + jlYcRcQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(jl_yc_rc)) {
			jl_yc_rc = jl_yc_rc.substring(0, jl_yc_rc.length() - 1);
		}

		// 肌张力 jzl_yc
		if (jzlYcZcCheckBox.isChecked()) {
			jzl_yc += "zc|";
		}
		if (jzlYcQgCheckBox.isChecked()) {
			jzl_yc += "qg|";
		}
		if (jzlYcRcCheckBox.isChecked()) {
			jzl_yc += "rc|";
		}
		if (!"".equals(jzl_yc)) {
			jzl_yc = jzl_yc.substring(0, jzl_yc.length() - 1);
		}
		// 肌张力 异常 左侧
		if (jzlYcZcShouCheckBox.isChecked()) {
			jzl_yc_zc += "shou|";
		}
		if (jzlYcZcSzCheckBox.isChecked()) {
			jzl_yc_zc += "sz|";
		}
		if (jzlYcZcXzCheckBox.isChecked()) {
			jzl_yc_zc += "xz|";
		}
		if (!"".equals(jzlYcZcQtEditText.getText().toString().trim())) {
			jzl_yc_zc += "jzlyczcqt:" + jzlYcZcQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(jzl_yc_zc)) {
			jzl_yc_zc = jzl_yc_zc.substring(0, jzl_yc_zc.length() - 1);
		}
		// 肌张力 异常 躯干
		if (jzlYcQgBjCheckBox.isChecked()) {
			jzl_yc_qg += "bj|";
		}
		if (jzlYcQgYjCheckBox.isChecked()) {
			jzl_yc_qg += "yj|";
		}
		if (jzlYcQgTjCheckBox.isChecked()) {
			jzl_yc_qg += "tj|";
		}
		if (jzlYcQgXjCheckBox.isChecked()) {
			jzl_yc_qg += "xj|";
		}
		if (jzlYcQgFjCheckBox.isChecked()) {
			jzl_yc_qg += "fj|";
		}
		if (!"".equals(jzlYcQgQtEditText.getText().toString().trim())) {
			jzl_yc_qg += "jzlycqgqt:" + jzlYcQgQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(jzl_yc_qg)) {
			jzl_yc_qg = jzl_yc_qg.substring(0, jzl_yc_qg.length() - 1);
		}
		// 肌张力 异常 右侧
		if (jzlYcRcSzCheckBox.isChecked()) {
			jzl_yc_rc += "sz|";
		}
		if (jzlYcRcXzCheckBox.isChecked()) {
			jzl_yc_rc += "xz|";
		}
		if (jzlYcRcJiaoCheckBox.isChecked()) {
			jzl_yc_rc += "jiao|";
		}
		if (!"".equals(jzlYcRcQtEditText.getText().toString().trim())) {
			jzl_yc_rc += "jzlycrcqt:" + jzlYcRcQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(jzl_yc_rc)) {
			jzl_yc_rc = jzl_yc_rc.substring(0, jzl_yc_rc.length() - 1);
		}
		
		// 疼痛 ttpf
		if (ttpfYcZcCheckBox.isChecked()) {
			ttpf_yc += "zc|";
		}
		if (ttpfYcQgCheckBox.isChecked()) {
			ttpf_yc += "qg|";
		}
		if (ttpfYcRcCheckBox.isChecked()) {
			ttpf_yc += "rc|";
		}
		if (!"".equals(ttpf_yc)) {
			ttpf_yc = ttpf_yc.substring(0, ttpf_yc.length() - 1);
		}		
		//疼痛评分 有疼痛 左侧 ttpf_yc_zc
		if (ttpfYcZcShouCheckBox.isChecked()) {
			ttpf_yc_zc += "shou|";
		}
		if (ttpfYcZcSzCheckBox.isChecked()) {
			ttpf_yc_zc += "sz|";
		}
		if (ttpfYcZcXzCheckBox.isChecked()) {
			ttpf_yc_zc += "xz|";
		}
		if (!"".equals(ttpfYcZcQtEditText.getText().toString().trim())) {
			ttpf_yc_zc += "ttpfyczcqt:" + ttpfYcZcQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(ttpf_yc_zc)) {
			ttpf_yc_zc = ttpf_yc_zc.substring(0, ttpf_yc_zc.length() - 1);
		}
		// 疼痛评分 有疼痛 躯干 ttpf_yc_qg
		if (ttpfYcQgBjCheckBox.isChecked()) {
			ttpf_yc_qg += "bj|";
		}
		if (ttpfYcQgYjCheckBox.isChecked()) {
			ttpf_yc_qg += "yj|";
		}
		if (ttpfYcQgTjCheckBox.isChecked()) {
			ttpf_yc_qg += "tj|";
		}
		if (ttpfYcQgXjCheckBox.isChecked()) {
			ttpf_yc_qg += "xj|";
		}
		if (ttpfYcQgFjCheckBox.isChecked()) {
			ttpf_yc_qg += "fj|";
		}
		if (!"".equals(ttpfYcQgQtEditText.getText().toString().trim())) {
			ttpf_yc_qg += "ttpfycqgqt:" + ttpfYcQgQtEditText.getText().toString().trim()
							+ "|";
		}
		if (!"".equals(ttpf_yc_qg)) {
			ttpf_yc_qg.substring(0, ttpf_yc_qg.length() - 1);
		}
		// 疼痛评分 有疼痛  右侧 ttpf_yc_rc
		if (ttpfYcRcSzCheckBox.isChecked()) {
			ttpf_yc_rc += "sz|";
		}
		if (ttpfYcRcXzCheckBox.isChecked()) {
			ttpf_yc_rc += "xz|";
		}
		if (ttpfYcRcJiaoCheckBox.isChecked()) {
			ttpf_yc_rc += "jiao|";
		}
		if (!"".equals(ttpfYcRcQtEditText.getText().toString().trim())) {
			ttpf_yc_rc += "ttpfycrcqt:"  + ttpfYcRcQtEditText.getText().toString().trim()
					+ "|";
		}
		if (!"".equals(ttpf_yc_rc)) {
			ttpf_yc_rc = ttpf_yc_rc.substring(0, ttpf_yc_rc.length() - 1);
		}
		//肌力 异常 左侧  手得分jl_yc_zc_shou
		if (!"".equals(jlYcZcShouSpinner.getSelectedItem().toString().trim())) {
			jl_yc_zc_shou = getJlScore(jlYcZcShouSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 左侧  上肢得分jl_yc_zc_sz
		if (!"".equals(jlYcZcSzSpinner.getSelectedItem().toString().trim())) {
			jl_yc_zc_sz = getJlScore(jlYcZcSzSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 左侧  下肢得分jl_yc_zc_xz
		if (!"".equals(jlYcZcXzSpinner.getSelectedItem().toString().trim())) {
			jl_yc_zc_xz = getJlScore(jlYcZcXzSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 左侧  其他得分jl_yc_zc_qt
		if (!"".equals(jlYcZcQtSpinner.getSelectedItem().toString().trim())) {
			jl_yc_zc_qt = getJlQtScore(jlYcZcQtSpinner.getSelectedItem().toString().trim(),jlYcZcQtEditText);
		}						
		//肌力 异常 躯干  背肌得分jl_yc_qg_bj
		if (!"".equals(jlYcQgBjSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_bj = getJlScore(jlYcQgBjSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 躯干  腰肌得分jl_yc_qg_yj
		if (!"".equals(jlYcQgYjSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_yj = getJlScore(jlYcQgYjSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 躯干  臀肌得分jl_yc_qg_tj
		if (!"".equals(jlYcQgTjSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_tj = getJlScore(jlYcQgTjSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 躯干  胸肌得分jl_yc_qg_xj
		if (!"".equals(jlYcQgXjSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_xj = getJlScore(jlYcQgXjSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 躯干  腹肌得分jl_yc_qg_fj
		if (!"".equals(jlYcQgFjSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_fj = getJlScore(jlYcQgFjSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 躯干  其他得分jl_yc_qg_qt
		if (!"".equals(jlYcQgQtSpinner.getSelectedItem().toString().trim())) {
			jl_yc_qg_qt = getJlQtScore(jlYcQgQtSpinner.getSelectedItem().toString().trim(),jlYcQgQtEditText);
		}											
		//肌力 异常 右侧  上肢得分jl_yc_rc_sz
		if (!"".equals(jlYcRcSzSpinner.getSelectedItem().toString().trim())) {
			jl_yc_rc_sz = getJlScore(jlYcRcSzSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 右侧  下肢得分jl_yc_rc_xz
		if (!"".equals(jlYcRcXzSpinner.getSelectedItem().toString().trim())) {
			jl_yc_rc_xz = getJlScore(jlYcRcXzSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 右侧  脚得分jl_yc_rc_sz
		if (!"".equals(jlYcRcJiaoSpinner.getSelectedItem().toString().trim())) {
			jl_yc_rc_jiao = getJlScore(jlYcRcJiaoSpinner.getSelectedItem().toString().trim());
		}
		//肌力 异常 右侧  其他得分jl_yc_rc_qt
		if (!"".equals(jlYcRcQtSpinner.getSelectedItem().toString().trim())) {
			jl_yc_rc_qt = getJlQtScore(jlYcRcQtSpinner.getSelectedItem().toString().trim(),jlYcRcQtEditText);
		}
		//肌张力 异常 左侧  手得分jl_yc_zc_shou
		if (!"".equals(jzlYcZcShouSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_zc_shou = getJzlScore(jzlYcZcShouSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 左侧  上肢得分jl_yc_zc_sz
		if (!"".equals(jzlYcZcSzSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_zc_sz = getJzlScore(jzlYcZcSzSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 左侧  下肢得分jl_yc_zc_xz
		if (!"".equals(jzlYcZcXzSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_zc_xz = getJzlScore(jzlYcZcXzSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 左侧  其他得分jl_yc_zc_qt
		if (!"".equals(jzlYcZcQtSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_zc_qt = getJzlQtScore(jzlYcZcQtSpinner.getSelectedItem().toString().trim(),jzlYcZcQtEditText);
		}		
		//肌张力 异常 躯干  背肌得分jl_yc_qg_bj
		if (!"".equals(jzlYcQgBjSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_bj = getJzlScore(jzlYcQgBjSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 躯干  腰肌得分jl_yc_qg_yj
		if (!"".equals(jzlYcQgYjSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_yj =getJzlScore(jzlYcQgYjSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 躯干  臀肌得分jl_yc_qg_tj
		if (!"".equals(jzlYcQgTjSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_tj = getJzlScore(jzlYcQgTjSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 躯干  胸肌得分jl_yc_qg_xj
		if (!"".equals(jzlYcQgXjSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_xj = getJzlScore(jzlYcQgXjSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 躯干  腹肌得分jl_yc_qg_fj
		if (!"".equals(jzlYcQgFjSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_fj = getJzlScore(jzlYcQgFjSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 躯干  其他得分jl_yc_qg_qt
		if (!"".equals(jzlYcQgQtSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_qg_qt = getJzlQtScore(jzlYcQgQtSpinner.getSelectedItem().toString().trim(),jzlYcQgQtEditText);
		}				
		//肌张力 异常 右侧  上肢得分jl_yc_rc_sz
		if (!"".equals(jzlYcRcSzSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_rc_sz = getJzlScore(jzlYcRcSzSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 右侧  下肢得分jl_yc_rc_xz
		if (!"".equals(jzlYcRcXzSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_rc_xz = getJzlScore(jzlYcRcXzSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 右侧  脚得分jl_yc_rc_sz
		if (!"".equals(jzlYcRcJiaoSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_rc_jiao = getJzlScore(jzlYcRcJiaoSpinner.getSelectedItem().toString().trim());
		}
		//肌张力 异常 右侧  其他得分jl_yc_rc_qt
		if (!"".equals(jzlYcRcQtSpinner.getSelectedItem().toString().trim())) {
			jzl_yc_rc_qt = getJzlQtScore(jzlYcRcQtSpinner.getSelectedItem().toString().trim(),jzlYcRcQtEditText);
		}
		//疼痛评分 有疼痛 左侧  手得分ttpf_yc_zc_shou
		if (!"".equals(ttpfYcZcShouSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_zc_shou = getTtpfScore(ttpfYcZcShouSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 左侧  上肢得分ttpf_yc_zc_sz
		if (!"".equals(ttpfYcZcSzSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_zc_sz = getTtpfScore(ttpfYcZcSzSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 左侧  下肢得分ttpf_yc_zc_xz
		if (!"".equals(ttpfYcZcXzSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_zc_xz = getTtpfScore(ttpfYcZcXzSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 左侧  其他得分ttpf_yc_zc_qt
		if (!"".equals(ttpfYcZcQtSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_zc_qt = getTtpfQtScore(ttpfYcZcQtSpinner.getSelectedItem().toString().trim(),ttpfYcZcQtEditText);
		}				
		//疼痛评分 有疼痛 躯干  背肌得分ttpf_yc_qg_bj
		if (!"".equals(ttpfYcQgBjSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_bj = getTtpfScore(ttpfYcQgBjSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 躯干  腰肌得分ttpf_yc_qg_yj
		if (!"".equals(ttpfYcQgYjSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_yj = getTtpfScore(ttpfYcQgYjSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 躯干  臀肌得分ttpf_yc_qg_tj
		if (!"".equals(ttpfYcQgTjSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_tj = getTtpfScore(ttpfYcQgTjSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 躯干  胸肌得分ttpf_yc_qg_xj
		if (!"".equals(ttpfYcQgXjSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_xj = getTtpfScore(ttpfYcQgXjSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 躯干  腹肌得分ttpf_yc_qg_fj
		if (!"".equals(ttpfYcQgFjSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_fj = getTtpfScore(ttpfYcQgFjSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛  躯干  其他得分ttpf_yc_qg_qt
		if (!"".equals(ttpfYcQgQtSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_qg_qt = getTtpfQtScore(ttpfYcQgQtSpinner.getSelectedItem().toString().trim(),ttpfYcQgQtEditText);
		}				
		//疼痛评分 有疼痛 右侧  上肢得分ttpf_yc_rc_sz
		if (!"".equals(ttpfYcRcSzSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_rc_sz = getTtpfScore(ttpfYcRcSzSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 右侧  下肢得分ttpf_yc_rc_xz
		if (!"".equals(ttpfYcRcXzSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_rc_xz = getTtpfScore(ttpfYcRcXzSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 右侧  脚得分ttpf_yc_rc_jiao
		if (!"".equals(ttpfYcRcJiaoSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_rc_jiao = getTtpfScore(ttpfYcRcJiaoSpinner.getSelectedItem().toString().trim());
		}
		//疼痛评分 有疼痛 右侧  其他得分ttpf_yc_rc_qt
		if (!"".equals(ttpfYcRcQtSpinner.getSelectedItem().toString().trim())) {
			ttpf_yc_rc_qt = getTtpfQtScore(ttpfYcRcQtSpinner.getSelectedItem().toString().trim(),ttpfYcRcQtEditText);
		}
		//风险等级  低级
		RadioButton fxdjRadioButton = (RadioButton) findViewById(fxdjRadioGroup.getCheckedRadioButtonId());
		if ("低危".equals(fxdjRadioButton.getText().toString().trim())) {
			fxdj = "dw";
		}else if ("中危".equals(fxdjRadioButton.getText().toString().trim())) {
			fxdj = "zw";
		}else if ("高危".equals(fxdjRadioButton.getText().toString().trim())) {
			fxdj = "gw";
		}else if ("极高危".equals(fxdjRadioButton.getText().toString().trim())) {
			fxdj = "jgw";
		}
		
	}

	/**
	 * 平衡 选级公用
	 */
	private String getPhScore(String str) {
		if (!"".equals(str) && !"请选择".equals(str)) {
			if ("1级".equals(str)) {
				return "yiji";
			} else if ("2级".equals(str)) {
				return "erji";
			} else if ("3级".equals(str)) {
				return "sanji";
			} else if ("不适用".equals(str)) {
				return "bsy";
			}
		}
		return "";
	}
	
	/**
	 * 步行能力评定 选级公用
	 */
	private String getBxnlScore(String str) {
		if (!"".equals(str) && !"请选择".equals(str)) {
			if ("1级".equals(str)) {
				return "yiji";
			} else if ("2级".equals(str)) {
				return "erji";
			} else if ("3级".equals(str)) {
				return "sanji";
			} else if ("4级".equals(str)) {
				return "siji";
			} else if ("5级".equals(str)) {
				return "wuji";
			}else if ("0级".equals(str)) {
				return "lingji";
			} else if ("不适用".equals(str)) {
				return "bsy";
			}
		}
		return "";
	}
	
	/**
	 * 肌力评分公用
	 */
	private String getJlScore(String str) {
		if (!"".equals(str) && !"请选择".equals(str)) {
			if ("0".equals(str)) {
				return "zero";
			} else if ("1".equals(str)) {
				return "one";
			} else if ("2".equals(str)) {
				return "two";
			} else if ("3".equals(str)) {
				return "three";
			} else if ("4".equals(str)) {
				return "four";
			} else if ("5".equals(str)) {
				return "five";
			}
		}
		return "";
	}
	/**
	 * 肌力其他评分公用
	 */
	private String getJlQtScore(String str,EditText et) {
		if (!"".equals(et.getText().toString().trim())) {
			if (!"".equals(str) && !"请选择".equals(str)) {
				if ("0".equals(str)) {
					return "zero";
				} else if ("1".equals(str)) {
					return "one";
				} else if ("2".equals(str)) {
					return "two";
				} else if ("3".equals(str)) {
					return "three";
				} else if ("4".equals(str)) {
					return "four";
				} else if ("5".equals(str)) {
					return "five";
				}
			}
		}
		return "";
	}
	/**
	 * 肌张力评分公用
	 */
	private String getJzlScore(String str) {
		if (!"".equals(str) && !"请选择".equals(str)) {
			if ("1".equals(str)) {
				return "one";
			} else if ("2".equals(str)) {
				return "two";
			} else if ("3".equals(str)) {
				return "three";
			} else if ("4".equals(str)) {
				return "four";
			} else if ("1+".equals(str)) {
				return "oneadd";
			}
		}
		return "";
	}
	/**
	 * 肌张力其他评分公用
	 */
	private String getJzlQtScore(String str,EditText et) {
		if (!"".equals(et.getText().toString().trim())) {
			if (!"".equals(str) && !"请选择".equals(str)) {
				if ("1".equals(str)) {
					return "one";
				} else if ("2".equals(str)) {
					return "two";
				} else if ("3".equals(str)) {
					return "three";
				} else if ("4".equals(str)) {
					return "four";
				} else if ("1+".equals(str)) {
					return "oneadd";
				}
			}
		}
		
		return "";
	}
	/**
	 * 有疼痛评分公用
	 */
	private String getTtpfScore(String str) {
		if (!"".equals(str) && !"请选择".equals(str)) {
			if ("0".equals(str)) {
				return "zero";
			} else if ("1".equals(str)) {
				return "one";
			} else if ("2".equals(str)) {
				return "two";
			} else if ("3".equals(str)) {
				return "three";
			} else if ("4".equals(str)) {
				return "four";
			} else if ("5".equals(str)) {
				return "five";
			} else if ("6".equals(str)) {
				return "six";
			} else if ("7".equals(str)) {
				return "seven";
			} else if ("8".equals(str)) {
				return "eight";
			} else if ("9".equals(str)) {
				return "nine";
			} else if ("10".equals(str)) {
				return "ten";
			}
		}
		return "";
	}
	/**
	 * 有疼痛其他评分公用
	 */
	private String getTtpfQtScore(String str,EditText et) {
		if (!"".equals(et.getText().toString().trim())) {
			if (!"".equals(str) && !"请选择".equals(str)) {
				if ("0".equals(str)) {
					return "zero";
				} else if ("1".equals(str)) {
					return "one";
				} else if ("2".equals(str)) {
					return "two";
				} else if ("3".equals(str)) {
					return "three";
				} else if ("4".equals(str)) {
					return "four";
				} else if ("5".equals(str)) {
					return "five";
				} else if ("6".equals(str)) {
					return "six";
				} else if ("7".equals(str)) {
					return "seven";
				} else if ("8".equals(str)) {
					return "eight";
				} else if ("9".equals(str)) {
					return "nine";
				} else if ("10".equals(str)) {
					return "ten";
				}
			}
		}
		
		return "";
	}
	/**
	 * 回显数据
	 */
	private void initData(SportAssess sportAssess){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(SportAssessActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getSportSubmitState() && "1".equals(recordSubmitState2.getSportSubmitState())) {
				sportSubmitButton.setVisibility(View.GONE);
				sportSaveButton.setVisibility(View.GONE);
			}
		}
		
		if (!"".equals(sportAssess) && null != sportAssess) {
			//坐位平衡
			if (!"".equals(sportAssess.getZwph()) && null != sportAssess.getZwph()) {
				if (sportAssess.getZwph().contains("|")) {
					String[] zwphArray = sportAssess.getZwph().split("\\|");
					for (String zwph : zwphArray) {
						setPhBshString(zwphSpinner,zwph,zwphBsyyyEditText);
					}
				}else {
					setPhString(zwphSpinner,sportAssess.getZwph());
				}	
			}		
			//立位平衡
			if (!"".equals(sportAssess.getLwph()) && null != sportAssess.getLwph()) {
				if (sportAssess.getLwph().contains("|")) {
					String[] lwphArray = sportAssess.getLwph().split("\\|");
					for (String lwph : lwphArray) {
						setPhBshString(lwphSpinner,lwph,lwphBsyyyEditText);
					}
				}else {
					setPhString(lwphSpinner,sportAssess.getLwph());
				}	
			}		
			//步行能力评定
			if (!"".equals(sportAssess.getBxnl()) && null != sportAssess.getBxnl()) {
				if (sportAssess.getBxnl().contains("|")) {
					String[] bxnlArray = sportAssess.getBxnl().split("\\|");
					for (String bxnl : bxnlArray) {
						setBxnlBshString(bxnlSpinner,bxnl,bxnlBsyyyEditText);
					}
				}else {
					setBxnlString(bxnlSpinner,sportAssess.getBxnl());
				}	
			}			
			//肌力 （正常 异常）
			if (!"".equals(sportAssess.getJl()) && null != sportAssess.getJl()) {
				if ("zc".equals(sportAssess.getJl())) {
					strengthNormalRadioButton.setChecked(true);
				}else if ("yc".equals(sportAssess.getJl())) {
					strengthExceptionRadioButton.setChecked(true);
				}
			}
			//肌力 异常（左侧 躯干 右侧）
			if (!"".equals(sportAssess.getJl_yc()) && null != sportAssess.getJl_yc()) {
				if (sportAssess.getJl_yc().contains("|")) {
					String[] jlYcArray = sportAssess.getJl_yc().split("\\|");
					for (String jl_yc : jlYcArray) {
						setYcString(jl_yc,jlYcZcCheckBox,jlYcQgCheckBox,jlYcRcCheckBox);
					}
				}else {
					setYcString(sportAssess.getJl_yc(),jlYcZcCheckBox,jlYcQgCheckBox,jlYcRcCheckBox);
				}	
			}		
			//肌力 异常 左侧 (手 上肢 下肢 其他)
			if (!"".equals(sportAssess.getJl_yc_zc()) && null != sportAssess.getJl_yc_zc()) {
				if (sportAssess.getJl_yc_zc().contains("|")) {
					String[] jlYcZcArray = sportAssess.getJl_yc_zc().split("\\|");
					for (String jl_yc_zc : jlYcZcArray) {
						setYcZcString(jl_yc_zc,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzCheckBox,jlYcZcQtEditText);
					}
				}else {
					setYcZcString(sportAssess.getJl_yc_zc(),jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzCheckBox,jlYcZcQtEditText);
				}	
			}		
			//肌力 异常 躯干 (背肌：bj腰肌：yj臀肌：tj 胸肌：xj腹肌：fj其他：qt)
			if (!"".equals(sportAssess.getJl_yc_qg()) && null != sportAssess.getJl_yc_qg()) {
				if (sportAssess.getJl_yc_qg().contains("|")) {
					String[] jlYcQgArray = sportAssess.getJl_yc_qg().split("\\|");
					for (String jl_yc_qg : jlYcQgArray) {
						setYcQgString(jl_yc_qg,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
					}
				}else {
					setYcQgString(sportAssess.getJl_yc_qg(),jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
				}	
			}	
			//肌力 异常 右侧 (上肢 下肢 脚 其他)
			if (!"".equals(sportAssess.getJl_yc_rc()) && null != sportAssess.getJl_yc_rc()) {
				if (sportAssess.getJl_yc_rc().contains("|")) {
					String[] jlYcRcArray = sportAssess.getJl_yc_rc().split("\\|");
					for (String jl_yc_rc : jlYcRcArray) {
						setYcRcString(jl_yc_rc,jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoCheckBox,jlYcRcQtEditText);
					}
				}else {
					setYcRcString(sportAssess.getJl_yc_rc(),jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoCheckBox,jlYcRcQtEditText);
				}	
			}
			//肌力 异常 左侧 手得分
			if (!"".equals(sportAssess.getJl_yc_zc_shou()) && null != sportAssess.getJl_yc_zc_shou()) {
				String JlYcZcShou = sportAssess.getJl_yc_zc_shou();
				setJlYcScore(jlYcZcShouSpinner,JlYcZcShou);
			}
			//肌力 异常 左侧 上肢得分
			if (!"".equals(sportAssess.getJl_yc_zc_sz()) && null != sportAssess.getJl_yc_zc_sz()) {
				String JlYcZcSz = sportAssess.getJl_yc_zc_sz();
				setJlYcScore(jlYcZcSzSpinner,JlYcZcSz);
			}
			//肌力 异常 左侧 下肢得分
			if (!"".equals(sportAssess.getJl_yc_zc_xz()) && null != sportAssess.getJl_yc_zc_xz()) {
				String JlYcZcXz = sportAssess.getJl_yc_zc_xz();
				setJlYcScore(jlYcZcXzSpinner,JlYcZcXz);
			}
			//肌力 异常 左侧 其他得分
			if (!"".equals(sportAssess.getJl_yc_zc_qt()) && null != sportAssess.getJl_yc_zc_qt()) {
				String JlYcZcQt = sportAssess.getJl_yc_zc_qt();
//				Log.v("TAG", "肌力 异常 左侧 其他得分 ");
				setJlYcScore(jlYcZcQtSpinner,JlYcZcQt);
			}
			//肌力 异常 躯干 背肌得分
			if (!"".equals(sportAssess.getJl_yc_qg_bj()) && null != sportAssess.getJl_yc_qg_bj()) {
				String JlYcQgBj = sportAssess.getJl_yc_qg_bj();
				setJlYcScore(jlYcQgBjSpinner,JlYcQgBj);
			}
			//肌力 异常 躯干 腰肌得分
			if (!"".equals(sportAssess.getJl_yc_qg_yj()) && null != sportAssess.getJl_yc_qg_yj()) {
				String JlYcQgYj = sportAssess.getJl_yc_qg_yj();
				setJlYcScore(jlYcQgYjSpinner,JlYcQgYj);
			}
			//肌力 异常 躯干 臀肌得分
			if (!"".equals(sportAssess.getJl_yc_qg_tj()) && null != sportAssess.getJl_yc_qg_tj()) {
				String JlYcQgTj = sportAssess.getJl_yc_qg_tj();
				setJlYcScore(jlYcQgTjSpinner,JlYcQgTj);
			}
			//肌力 异常 躯干 胸肌得分
			if (!"".equals(sportAssess.getJl_yc_qg_xj()) && null != sportAssess.getJl_yc_qg_xj()) {
				String JlYcQgXj = sportAssess.getJl_yc_qg_xj();
//				Log.v("TAG", "肌力 异常 躯干 胸肌得分 ");
				setJlYcScore(jlYcQgXjSpinner,JlYcQgXj);
			}
			//肌力 异常 躯干 腹肌得分
			if (!"".equals(sportAssess.getJl_yc_qg_fj()) && null != sportAssess.getJl_yc_qg_fj()) {
				String JlYcQgFj = sportAssess.getJl_yc_qg_fj();
//				Log.v("TAG", "肌力 异常 躯干 腹肌得分 ");
				setJlYcScore(jlYcQgFjSpinner,JlYcQgFj);
			}			
			//肌力 异常 躯干 其他得分
			if (!"".equals(sportAssess.getJl_yc_qg_qt()) && null != sportAssess.getJl_yc_qg_qt()) {
				String JlYcQgQt = sportAssess.getJl_yc_qg_qt();
				setJlYcScore(jlYcQgQtSpinner,JlYcQgQt);
			}			
			//肌力 异常 右侧 上肢得分
			if (!"".equals(sportAssess.getJl_yc_rc_sz()) && null != sportAssess.getJl_yc_rc_sz()) {
				String JlYcRcSz = sportAssess.getJl_yc_rc_sz();
				setJlYcScore(jlYcRcSzSpinner,JlYcRcSz);
			}
			//肌力 异常 右侧 下肢得分
			if (!"".equals(sportAssess.getJl_yc_rc_xz()) && null != sportAssess.getJl_yc_rc_xz()) {
				String JlYcRcXz = sportAssess.getJl_yc_rc_xz();
				setJlYcScore(jlYcRcXzSpinner,JlYcRcXz);
			}
			//肌力 异常 右侧 脚得分
			if (!"".equals(sportAssess.getJl_yc_rc_jiao()) && null != sportAssess.getJl_yc_rc_jiao()) {
				String JlYcRcJiao = sportAssess.getJl_yc_rc_jiao();
				setJlYcScore(jlYcRcJiaoSpinner,JlYcRcJiao);
			}
			//肌力 异常 右侧 其他得分
			if (!"".equals(sportAssess.getJl_yc_rc_qt()) && null != sportAssess.getJl_yc_rc_qt()) {
				String JlYcRcQt = sportAssess.getJl_yc_rc_qt();
//				Log.v("TAG", "肌力 异常 右侧 其他得分");
				setJlYcScore(jlYcRcQtSpinner,JlYcRcQt);
			}		
			//肌张力 （正常 异常）
			if (!"".equals(sportAssess.getJzl()) && null != sportAssess.getJzl()) {
				if ("zc".equals(sportAssess.getJzl())) {
					muscleTensionNormalRadioButton.setChecked(true);
				}else if ("yc".equals(sportAssess.getJzl())) {
					muscleTensionExceptionRadioButton.setChecked(true);
				}
			}
			//肌张力 异常（左侧 躯干 右侧）
			if (!"".equals(sportAssess.getJzl_yc()) && null != sportAssess.getJzl_yc()) {
				if (sportAssess.getJzl_yc().contains("|")) {
					String[] jzlYcArray = sportAssess.getJzl_yc().split("\\|");
					for (String jzl_yc : jzlYcArray) {
						setYcString(jzl_yc,jzlYcZcCheckBox,jzlYcQgCheckBox,jzlYcRcCheckBox);
					}
				}else {
					setYcString(jzl_yc,jzlYcZcCheckBox,jzlYcQgCheckBox,jzlYcRcCheckBox);
				}	
			}		
			//肌张力 异常 左侧 (手 上肢 下肢 其他)
			if (!"".equals(sportAssess.getJzl_yc_zc()) && null != sportAssess.getJzl_yc_zc()) {
				if (sportAssess.getJzl_yc_zc().contains("|")) {
					String[] jzlYcZcArray = sportAssess.getJzl_yc_zc().split("\\|");
					for (String jzl_yc_zc : jzlYcZcArray) {
						setYcZcString(jzl_yc_zc,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzCheckBox,jzlYcZcQtEditText);
					}
				}else {
					setYcZcString(jzl_yc_zc,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzCheckBox,jzlYcZcQtEditText);
				}	
			}		
			//肌张力 异常 躯干 (背肌：bj腰肌：yj臀肌：tj 胸肌：xj腹肌：fj其他：qt)
			if (!"".equals(sportAssess.getJzl_yc_qg()) && null != sportAssess.getJzl_yc_qg()) {
				if (sportAssess.getJzl_yc_qg().contains("|")) {
					String[] jzlYcQgArray = sportAssess.getJzl_yc_qg().split("\\|");
					for (String jzl_yc_qg : jzlYcQgArray) {
						setYcQgString(jzl_yc_qg,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
					}
				}else {
					setYcQgString(sportAssess.getJzl_yc_qg(),jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
				}	
			}	
			//肌张力 异常 右侧 (上肢 下肢 脚 其他)
			if (!"".equals(sportAssess.getJzl_yc_rc()) && null != sportAssess.getJzl_yc_rc()) {
				if (sportAssess.getJzl_yc_rc().contains("|")) {
					String[] jzlYcRcArray = sportAssess.getJzl_yc_rc().split("\\|");
					for (String jzl_yc_rc : jzlYcRcArray) {
						setYcRcString(jzl_yc_rc,jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcQtEditText);
					}
				}else {
					setYcRcString(sportAssess.getJzl_yc_rc(),jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcQtEditText);
				}	
			}
			//肌张力 异常 左侧 手得分
			if (!"".equals(sportAssess.getJzl_yc_zc_shou()) && null != sportAssess.getJzl_yc_zc_shou()) {
				String JzlYcZcShou = sportAssess.getJzl_yc_zc_shou();
				setJzlYcScore(jzlYcZcShouSpinner,JzlYcZcShou);
			}
			//肌张力 异常 左侧 上肢得分
			if (!"".equals(sportAssess.getJzl_yc_zc_sz()) && null != sportAssess.getJzl_yc_zc_sz()) {
				String JzlYcZcSz = sportAssess.getJzl_yc_zc_sz();
				setJzlYcScore(jzlYcZcSzSpinner,JzlYcZcSz);
			}
			//肌张力 异常 左侧 下肢得分
			if (!"".equals(sportAssess.getJzl_yc_zc_xz()) && null != sportAssess.getJzl_yc_zc_xz()) {
				String JzlYcZcXz = sportAssess.getJzl_yc_zc_xz();
				setJzlYcScore(jzlYcZcXzSpinner,JzlYcZcXz);
			}
			//肌张力 异常 左侧 其他得分
			if (!"".equals(sportAssess.getJzl_yc_zc_qt()) && null != sportAssess.getJzl_yc_zc_qt()) {
				String JzlYcZcQt = sportAssess.getJzl_yc_zc_qt();
//				Log.v("TAG", "肌张力 异常 左侧 其他得分");
				setJzlYcScore(jzlYcZcQtSpinner,JzlYcZcQt);
			}
			//肌张力 异常 躯干 背肌得分
			if (!"".equals(sportAssess.getJzl_yc_qg_bj()) && null != sportAssess.getJzl_yc_qg_bj()) {
				String JzlYcQgBj = sportAssess.getJzl_yc_qg_bj();
				setJzlYcScore(jzlYcQgBjSpinner,JzlYcQgBj);
			}
			//肌张力 异常 躯干 腰肌得分
			if (!"".equals(sportAssess.getJzl_yc_qg_yj()) && null != sportAssess.getJzl_yc_qg_yj()) {
				String JzlYcQgYj = sportAssess.getJzl_yc_qg_yj();
				setJzlYcScore(jzlYcQgYjSpinner,JzlYcQgYj);
			}
			//肌张力 异常 躯干 臀肌得分
			if (!"".equals(sportAssess.getJzl_yc_qg_tj()) && null != sportAssess.getJzl_yc_qg_tj()) {
				String JzlYcQgTj = sportAssess.getJzl_yc_qg_tj();
				setJzlYcScore(jzlYcQgTjSpinner,JzlYcQgTj);
			}
			//肌张力 异常 躯干 胸肌得分
			if (!"".equals(sportAssess.getJzl_yc_qg_xj()) && null != sportAssess.getJzl_yc_qg_xj()) {
				String JzlYcQgXj = sportAssess.getJzl_yc_qg_xj();
//				Log.v("TAG", "肌张力 异常 躯干 胸肌得分");
				setJzlYcScore(jzlYcQgXjSpinner,JzlYcQgXj);
			}
			//肌张力 异常 躯干 腹肌得分
			if (!"".equals(sportAssess.getJzl_yc_qg_fj()) && null != sportAssess.getJzl_yc_qg_fj()) {
				String JzlYcQgFj = sportAssess.getJzl_yc_qg_fj();
//				Log.v("TAG", "肌张力 异常 躯干 腹肌得分");
				setJzlYcScore(jzlYcQgFjSpinner,JzlYcQgFj);
			}
			//肌张力 异常 躯干 其他得分
			if (!"".equals(sportAssess.getJzl_yc_qg_qt()) && null != sportAssess.getJzl_yc_qg_qt()) {
				String JzlYcQgQt = sportAssess.getJzl_yc_qg_qt();
				setJzlYcScore(jzlYcQgQtSpinner,JzlYcQgQt);
			}			
			//肌张力 异常 右侧 上肢得分
			if (!"".equals(sportAssess.getJzl_yc_rc_sz()) && null != sportAssess.getJzl_yc_rc_sz()) {
				String JzlYcRcSz = sportAssess.getJzl_yc_rc_sz();
				setJzlYcScore(jzlYcRcSzSpinner,JzlYcRcSz);
			}
			//肌张力 异常 右侧 下肢得分
			if (!"".equals(sportAssess.getJzl_yc_rc_xz()) && null != sportAssess.getJzl_yc_rc_xz()) {
				String JzlYcRcXz = sportAssess.getJzl_yc_rc_xz();
				setJzlYcScore(jzlYcRcXzSpinner,JzlYcRcXz);
			}
			//肌张力 异常 右侧 脚得分
			if (!"".equals(sportAssess.getJzl_yc_rc_jiao()) && null != sportAssess.getJzl_yc_rc_jiao()) {
				String JzlYcRcJiao = sportAssess.getJzl_yc_rc_jiao();
				setJzlYcScore(jzlYcRcJiaoSpinner,JzlYcRcJiao);
			}
			//肌张力 异常 右侧 其他得分
			if (!"".equals(sportAssess.getJzl_yc_rc_qt()) && null != sportAssess.getJzl_yc_rc_qt()) {
				String JzlYcRcQt = sportAssess.getJzl_yc_rc_qt();
//				Log.v("TAG", "肌张力 异常 右侧 其他得分");
				setJzlYcScore(jzlYcRcQtSpinner,JzlYcRcQt);
			}
			//疼痛评分 （无疼痛 有疼痛）
			if (!"".equals(sportAssess.getTtpf()) && null != sportAssess.getTtpf()) {
				if ("zc".equals(sportAssess.getTtpf())) {
					painNormalRadioButton.setChecked(true);
				}else if ("yc".equals(sportAssess.getTtpf())) {
					painExceptionRadioButton.setChecked(true);
				}
			}
			//疼痛评分 有疼痛（左侧 躯干 右侧）
			if (!"".equals(sportAssess.getTtpf_yc()) && null != sportAssess.getTtpf_yc()) {
				if (sportAssess.getTtpf_yc().contains("|")) {
					String[] ttpfYcArray = sportAssess.getTtpf_yc().split("\\|");
					for (String ttpf_yc : ttpfYcArray) {
						setYcString(ttpf_yc,ttpfYcZcCheckBox,ttpfYcQgCheckBox,ttpfYcRcCheckBox);
					}
				}else {
					setYcString(ttpf_yc,ttpfYcZcCheckBox,ttpfYcQgCheckBox,ttpfYcRcCheckBox);
				}	
			}		
			//疼痛评分 有疼痛 左侧 (手 上肢 下肢 其他)
			if (!"".equals(sportAssess.getTtpf_yc_zc()) && null != sportAssess.getTtpf_yc_zc()) {
				if (sportAssess.getTtpf_yc_zc().contains("|")) {
					String[] ttpfYcZcArray = sportAssess.getTtpf_yc_zc().split("\\|");
					for (String ttpf_yc_zc : ttpfYcZcArray) {
						setYcZcString(ttpf_yc_zc,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcQtEditText);
					}
				}else {
					setYcZcString(ttpf_yc_zc,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcQtEditText);
				}	
			}		
			//疼痛评分 有疼痛 躯干 (背肌：bj腰肌：yj臀肌：tj 胸肌：xj腹肌：fj其他：qt)
			if (!"".equals(sportAssess.getTtpf_yc_qg()) && null != sportAssess.getTtpf_yc_qg()) {
				if (sportAssess.getTtpf_yc_qg().contains("|")) {
					String[] ttpfYcQgArray = sportAssess.getTtpf_yc_qg().split("\\|");
					for (String ttpf_yc_qg : ttpfYcQgArray) {
						setYcQgString(ttpf_yc_qg,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
					}
				}else {
					setYcQgString(sportAssess.getTtpf_yc_qg(),ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
				}	
			}	
			//疼痛评分 有疼痛 右侧 (上肢 下肢 脚 其他)
			if (!"".equals(sportAssess.getTtpf_yc_rc()) && null != sportAssess.getTtpf_yc_rc()) {
				if (sportAssess.getTtpf_yc_rc().contains("|")) {
					String[] ttpfYcRcArray = sportAssess.getTtpf_yc_rc().split("\\|");
					for (String ttpf_yc_rc : ttpfYcRcArray) {
						setYcRcString(ttpf_yc_rc,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcQtEditText);
					}
				}else {
					setYcRcString(sportAssess.getTtpf_yc_rc(),ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcQtEditText);
				}	
			}
			//疼痛评分 有疼痛 左侧 手得分
			if (!"".equals(sportAssess.getTtpf_yc_zc_shou()) && null != sportAssess.getTtpf_yc_zc_shou()) {
				String ttpfYcZcShou = sportAssess.getTtpf_yc_zc_shou();
				setTtpfYcScore(ttpfYcZcShouSpinner,ttpfYcZcShou);
			}
			//疼痛评分 有疼痛 左侧 上肢得分
			if (!"".equals(sportAssess.getTtpf_yc_zc_sz()) && null != sportAssess.getTtpf_yc_zc_sz()) {
				String ttpfYcZcSz = sportAssess.getTtpf_yc_zc_sz();
				setTtpfYcScore(ttpfYcZcSzSpinner,ttpfYcZcSz);
			}
			//疼痛评分 有疼痛 左侧 下肢得分
			if (!"".equals(sportAssess.getTtpf_yc_zc_xz()) && null != sportAssess.getTtpf_yc_zc_xz()) {
				String ttpfYcZcXz = sportAssess.getTtpf_yc_zc_xz();
				setTtpfYcScore(ttpfYcZcXzSpinner,ttpfYcZcXz);
			}
			//疼痛评分 有疼痛 左侧 其他得分
			if (!"".equals(sportAssess.getTtpf_yc_zc_qt()) && null != sportAssess.getTtpf_yc_zc_qt()) {
				String ttpfYcZcQt = sportAssess.getTtpf_yc_zc_qt();
//				Log.v("TAG", "疼痛评分 有疼痛 左侧 其他得分 ttpfYcZcQt " + ttpfYcZcQt);
				setTtpfYcScore(ttpfYcZcQtSpinner,ttpfYcZcQt);
//				Log.v("TAG", "疼痛评分 有疼痛 左侧 其他得分");
			}
			//疼痛评分 有疼痛 躯干 背肌得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_bj()) && null != sportAssess.getTtpf_yc_qg_bj()) {
				String ttpfYcQgBj = sportAssess.getTtpf_yc_qg_bj();
				setTtpfYcScore(ttpfYcQgBjSpinner,ttpfYcQgBj);
			}
			//疼痛评分 有疼痛 躯干 腰肌得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_yj()) && null != sportAssess.getTtpf_yc_qg_yj()) {
				String ttpfYcQgYj = sportAssess.getTtpf_yc_qg_yj();
				setTtpfYcScore(ttpfYcQgYjSpinner,ttpfYcQgYj);
			}
			//疼痛评分 有疼痛 躯干 臀肌得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_tj()) && null != sportAssess.getTtpf_yc_qg_tj()) {
				String ttpfYcQgTj = sportAssess.getTtpf_yc_qg_tj();
				setTtpfYcScore(ttpfYcQgTjSpinner,ttpfYcQgTj);
			}
			//疼痛评分 有疼痛 躯干 胸肌得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_xj()) && null != sportAssess.getTtpf_yc_qg_xj()) {
				String ttpfYcQgXj = sportAssess.getTtpf_yc_qg_xj();
				setTtpfYcScore(ttpfYcQgXjSpinner,ttpfYcQgXj);
//				Log.v("TAG", "疼痛评分 有疼痛 躯干 胸肌得分 ttpfYcQgXj   " + ttpfYcQgXj);
			}
			//疼痛评分 有疼痛 躯干 腹肌得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_fj()) && null != sportAssess.getTtpf_yc_qg_fj()) {
				String ttpfYcQgFj = sportAssess.getTtpf_yc_qg_fj();
				setTtpfYcScore(ttpfYcQgFjSpinner,ttpfYcQgFj);
//				Log.v("TAG", "疼痛评分 有疼痛 躯干 腹肌得分  ttpfYcQgFj  " + ttpfYcQgFj);
			}
			//疼痛评分 有疼痛 躯干 其他得分
			if (!"".equals(sportAssess.getTtpf_yc_qg_qt()) && null != sportAssess.getTtpf_yc_qg_qt()) {
				String ttpfYcQgQt = sportAssess.getTtpf_yc_qg_qt();
				setTtpfYcScore(ttpfYcQgQtSpinner,ttpfYcQgQt);
			}			
			//疼痛评分 有疼痛 右侧 上肢得分
			if (!"".equals(sportAssess.getTtpf_yc_rc_sz()) && null != sportAssess.getTtpf_yc_rc_sz()) {
				String ttpfYcRcSz = sportAssess.getTtpf_yc_rc_sz();
				setTtpfYcScore(ttpfYcRcSzSpinner,ttpfYcRcSz);
			}
			//疼痛评分 有疼痛 右侧 下肢得分
			if (!"".equals(sportAssess.getTtpf_yc_rc_xz()) && null != sportAssess.getTtpf_yc_rc_xz()) {
				String ttpfYcRcXz = sportAssess.getTtpf_yc_rc_xz();
				setTtpfYcScore(ttpfYcRcXzSpinner,ttpfYcRcXz);
			}
			//疼痛评分 有疼痛 异常 右侧 脚得分
			if (!"".equals(sportAssess.getTtpf_yc_rc_jiao()) && null != sportAssess.getTtpf_yc_rc_jiao()) {
				String ttpfYcRcJiao = sportAssess.getTtpf_yc_rc_jiao();
				setTtpfYcScore(ttpfYcRcJiaoSpinner,ttpfYcRcJiao);
			}
			//疼痛评分 有疼痛 异常 右侧 其他得分
			if (!"".equals(sportAssess.getTtpf_yc_rc_qt()) && null != sportAssess.getTtpf_yc_rc_qt()) {
				String ttpfYcRcQt = sportAssess.getTtpf_yc_rc_qt();
				setTtpfYcScore(ttpfYcRcQtSpinner,ttpfYcRcQt);
//				Log.v("TAG", "疼痛评分 有疼痛 异常 右侧 其他得分 ttpfYcRcQt  " + ttpfYcRcQt);
			}
			//风险等级
			if (!"".equals(sportAssess.getFxdj()) && null != sportAssess.getFxdj()) {
				if ("dw".equals(sportAssess.getFxdj())) {
					fxdjDwRadioButton.setChecked(true);
				}else if ("zw".equals(sportAssess.getFxdj())) {
					fxdjZwRadioButton.setChecked(true);
				}else if ("gw".equals(sportAssess.getFxdj())) {
					fxdjGwRadioButton.setChecked(true);
				}else if ("jgw".equals(sportAssess.getFxdj())) {
					fxdjJgwRadioButton.setChecked(true);
				}
			}			
		}				
	}
	
	
	/**
	 * 平衡  设置
	 */
	private String phStr = null;
	private void setPhString(Spinner spin,String phStr){
		if ("yiji".equals(phStr)) {
			spin.setSelection(1);
		}else if ("erji".equals(phStr)) {
			spin.setSelection(2);
		}else if ("sanji".equals(phStr)) {
			spin.setSelection(3);
		}else if ("bsy".equals(phStr)) {
			spin.setSelection(4);			
		}else {
			spin.setSelection(0);
		}
	}
	/**
	 * 平衡  不适合 设置
	 */
	private String phBshStr = null;
	private void setPhBshString(Spinner spin,String phBshStr,EditText et){
		if ("bsy".equals(phBshStr)) {
			spin.setSelection(4);
		}else if (!"".equals(phBshStr)&&phBshStr.contains(":")) {
			et.setText(phBshStr.split(":")[1]);
		}
	}
	/**
	 * 步行能力评定 设置
	 */
	private String bxnlStr = null;
	private void setBxnlString(Spinner spin,String bxnlStr){
		if ("lingji".equals(bxnlStr)) {
			spin.setSelection(1);
		}else if ("yiji".equals(bxnlStr)) {
			spin.setSelection(2);
		}else if ("erji".equals(bxnlStr)) {
			spin.setSelection(3);
		}else if ("sanji".equals(bxnlStr)) {
			spin.setSelection(4);
		}else if ("siji".equals(bxnlStr)) {
			spin.setSelection(5);
		}else if ("wuji".equals(bxnlStr)) {
			spin.setSelection(6);
		}else if ("bsy".equals(bxnlStr)) {
			spin.setSelection(7);	
		}else {
			spin.setSelection(0);
		}
	}
	/**
	 * 步行能力评定  不适合 设置
	 */
	private String bxnlBshStr = null;
	private void setBxnlBshString(Spinner spin,String bxnlBshStr,EditText et){
		if ("bsy".equals(bxnlBshStr)) {
			spin.setSelection(6);
		}else if (!"".equals(bxnlBshStr)&&bxnlBshStr.contains(":")) {
			et.setText(bxnlBshStr.split(":")[1]);
		}
	}
	/**
	 * 异常  判断
	 */
	private void setYcString(String yc,CheckBox cb1,CheckBox cb2,CheckBox cb3){
		if ("zc".equals(yc)) {
			cb1.setChecked(true);
		}else if ("qg".equals(yc)) {
			cb2.setChecked(true);
		}else if ("rc".equals(yc)) {
			cb3.setChecked(true);
		}
	}
	
	/**
	 * 异常 左侧 判断
	 */
	private void setYcZcString(String yc_zc,CheckBox cb1,CheckBox cb2,CheckBox cb3,EditText et){
		if ("shou".equals(yc_zc)) {
			cb1.setChecked(true);
		}else if ("sz".equals(yc_zc)) {
			cb2.setChecked(true);
		}else if ("xz".equals(yc_zc)) {
			cb3.setChecked(true);
		}else if (!"".equals(yc_zc)&&yc_zc.contains(":")) {
			et.setText(yc_zc.split(":")[1]);
		}
	}

	/**
	 * 异常 躯干 判断
	 */
	private void setYcQgString(String yc_qg,CheckBox cb1,CheckBox cb2,CheckBox cb3,CheckBox cb4,CheckBox cb5,EditText et){
		if ("bj".equals(yc_qg)) {
			cb1.setChecked(true);
		}else if ("yj".equals(yc_qg)) {
			cb2.setChecked(true);
		}else if ("tj".equals(yc_qg)) {
			cb3.setChecked(true);
		}else if ("xj".equals(yc_qg)) {
			cb4.setChecked(true);
		}else if ("fj".equals(yc_qg)) {
			cb5.setChecked(true);
		}else if (!"".equals(yc_qg)&&yc_qg.contains(":")) {
			et.setText(yc_qg.split(":")[1]);
		}
	}

	/**
	 *异常 右侧 判断
	 */
	private void setYcRcString(String yc_rc,CheckBox cb1,CheckBox cb2,CheckBox cb3,EditText et){
		if ("sz".equals(yc_rc)) {
			cb1.setChecked(true);
		}else if ("xz".equals(yc_rc)) {
			cb2.setChecked(true);
		}else if ("jiao".equals(yc_rc)) {
			cb3.setChecked(true);
		}else if (!"".equals(yc_rc)&&yc_rc.contains(":")) {
			et.setText(yc_rc.split(":")[1]);
		}
	}

	/**
	 * 肌力 得分 设置
	 */
	private String jlStr = null;
	private void setJlYcScore(Spinner spin,String jlStr){
		if ("zero".equals(jlStr)) {
			spin.setSelection(1);
		}else if ("one".equals(jlStr)) {
			spin.setSelection(2);
		}else if ("two".equals(jlStr)) {
			spin.setSelection(3);
		}else if ("three".equals(jlStr)) {
			spin.setSelection(4);
		}else if ("four".equals(jlStr)) {
			spin.setSelection(5);
		}else if ("five".equals(jlStr)) {
			spin.setSelection(6);
		}else {
			spin.setSelection(0);
		}
	}
	
	/**
	 * 肌张力 得分 设置
	 */
	private String jzlStr = null;
	private void setJzlYcScore(Spinner spin,String jzlStr){
		if ("one".equals(jzlStr)) {
			spin.setSelection(1);
		}else if ("oneadd".equals(jzlStr)) {
			spin.setSelection(2);
		}else if ("two".equals(jzlStr)) {
			spin.setSelection(3);
		}else if ("three".equals(jzlStr)) {
			spin.setSelection(4);
		}else if ("four".equals(jzlStr)) {
			spin.setSelection(5);
		}else {
			spin.setSelection(0);
		}
	}
	
	/**
	 * 疼痛评分 得分 设置
	 */
	private String ttpfStr = null;
	private void setTtpfYcScore(Spinner spin,String ttpfStr){
		if ("zero".equals(ttpfStr)) {
			spin.setSelection(1);
		}else if ("one".equals(ttpfStr)) {
			spin.setSelection(2);
		}else if ("two".equals(ttpfStr)) {
			spin.setSelection(3);
		}else if ("three".equals(ttpfStr)) {
			spin.setSelection(4);
		}else if ("four".equals(ttpfStr)) {
			spin.setSelection(5);
		}else if ("five".equals(ttpfStr)) {
			spin.setSelection(6);
		}else if ("six".equals(ttpfStr)) {
			spin.setSelection(7);
		}else if ("seven".equals(ttpfStr)) {
			spin.setSelection(8);
		}else if ("eight".equals(ttpfStr)) {
			spin.setSelection(9);
		}else if ("nine".equals(ttpfStr)) {
			spin.setSelection(10);
		}else if ("ten".equals(ttpfStr)) {
			spin.setSelection(11);
		}else {
			spin.setSelection(0);
		}
	}
		
	
	public void onItemSelected(AdapterView<?> spin, View view, int position,
			long arg3) {
		switch (spin.getId()) {
		case R.id.zwph_spinner://坐位平衡
			String zwphNumber = SportAssessActivity.this.getResources().getStringArray(R.array.balanceSpinner)[position];
//			Log.v("TAG", "zwphNumber : " + zwphNumber);
			if (!"不适用".equals(zwphNumber)) {
				zwphBsyyyEditText.setText("");
			}
			break;
		case R.id.lwph_spinner://立位平衡
			String lwphNumber = SportAssessActivity.this.getResources().getStringArray(R.array.balanceSpinner)[position];
			if (!"不适用".equals(lwphNumber)) {
				lwphBsyyyEditText.setText("");
			}
			break;
		case R.id.bxnl_spinner://立位平衡
			String bxnlNumber = SportAssessActivity.this.getResources().getStringArray(R.array.walkSpinner)[position];
			if (!"不适用".equals(bxnlNumber)) {
				bxnlBsyyyEditText.setText("");
			}
			break;
		case R.id.jl_yc_zc_shou_spinner:// 肌力 左侧 手得分
			String jlYcZcShouNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcZcShouNumber,jlYcZcCheckBox,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzCheckBox,jlYcZcQtEditText);
			break;		
		case R.id.jl_yc_zc_sz_spinner:// 肌力 左侧 上肢得分
			String jlYcZcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcZcSzNumber,jlYcZcCheckBox,jlYcZcSzCheckBox,jlYcZcShouCheckBox,jlYcZcXzCheckBox,jlYcZcQtEditText);
			break;
		case R.id.jl_yc_zc_xz_spinner:// 肌力 左侧 下肢得分
			String jlYcZcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcZcXzNumber,jlYcZcCheckBox,jlYcZcXzCheckBox,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcQtEditText);
			break;
		case R.id.jl_yc_zc_qt_spinner:// 肌力 左侧 其他得分
			String jlYcZcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qtLinkageState(jlYcZcQtNumber, jlYcZcCheckBox, jlYcZcShouCheckBox,jlYcZcSzCheckBox, jlYcZcXzCheckBox, jlYcZcQtEditText);
			break;
		case R.id.jl_yc_qg_bj_spinner://肌力 躯干 背肌得分
			String jlYcQgBjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgLinkageState(jlYcQgBjNumber,jlYcQgCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
			break;
		case R.id.jl_yc_qg_yj_spinner://肌力 躯干 腰肌得分
			String jlYcQgYjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgLinkageState(jlYcQgYjNumber,jlYcQgCheckBox,jlYcQgYjCheckBox,jlYcQgBjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
			break;
		case R.id.jl_yc_qg_tj_spinner://肌力 躯干 臀肌得分
			String jlYcQgTjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgLinkageState(jlYcQgTjNumber,jlYcQgCheckBox,jlYcQgTjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
			break;
		case R.id.jl_yc_qg_xj_spinner://肌力 躯干 胸肌得分
			String jlYcQgXjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgLinkageState(jlYcQgXjNumber,jlYcQgCheckBox,jlYcQgXjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgFjCheckBox,jlYcQgQtEditText);
			break;
		case R.id.jl_yc_qg_fj_spinner://肌力 躯干 腹肌得分
			String jlYcQgFjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgLinkageState(jlYcQgFjNumber,jlYcQgCheckBox,jlYcQgFjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgQtEditText);
			break;
		case R.id.jl_yc_qg_qt_spinner://肌力 躯干 其他得分
			String jlYcQgQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qgQtLinkageState(jlYcQgQtNumber,jlYcQgCheckBox, jlYcQgBjCheckBox, jlYcQgYjCheckBox, jlYcQgTjCheckBox, jlYcQgXjCheckBox, jlYcQgFjCheckBox, jlYcQgQtEditText);
			break;
		case R.id.jl_yc_rc_sz_spinner:// 肌力 右侧 上肢得分
			String jlYcRcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcRcSzNumber, jlYcRcCheckBox, jlYcRcSzCheckBox, jlYcRcXzCheckBox, jlYcRcJiaoCheckBox, jlYcRcQtEditText);
			break;		
		case R.id.jl_yc_rc_xz_spinner:// 肌力 右侧 下肢得分
			String jlYcRcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcRcXzNumber, jlYcRcCheckBox, jlYcRcXzCheckBox, jlYcRcSzCheckBox, jlYcRcJiaoCheckBox, jlYcRcQtEditText);
			break;
		case R.id.jl_yc_rc_jiao_spinner:// 肌力 右侧 脚得分
			String jlYcRcJiaoNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			linkageState(jlYcRcJiaoNumber,jlYcRcCheckBox, jlYcRcJiaoCheckBox, jlYcRcSzCheckBox, jlYcRcXzCheckBox, jlYcRcQtEditText);
			break;
		case R.id.jl_yc_rc_qt_spinner:// 肌力 右侧 其他得分
			String jlYcRcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.strengthSpinner)[position];
			qtLinkageState(jlYcRcQtNumber,jlYcRcCheckBox,jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoCheckBox,jlYcRcQtEditText);
			break;	
		case R.id.jzl_yc_zc_shou_spinner:// 肌张力 左侧 手得分
			String jzlYcZcShouNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcZcShouNumber,jzlYcZcCheckBox,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzCheckBox,jzlYcZcQtEditText);
			break;		
		case R.id.jzl_yc_zc_sz_spinner:// 肌张力 左侧 上肢得分
			String jzlYcZcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcZcSzNumber,jzlYcZcCheckBox,jzlYcZcSzCheckBox,jzlYcZcShouCheckBox,jzlYcZcXzCheckBox,jzlYcZcQtEditText);
			break;
		case R.id.jzl_yc_zc_xz_spinner:// 肌张力 左侧 下肢得分
			String jzlYcZcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcZcXzNumber,jzlYcZcCheckBox,jzlYcZcXzCheckBox,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcQtEditText);
			break;
		case R.id.jzl_yc_zc_qt_spinner:// 肌张力 左侧 其他得分
			String jzlYcZcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qtLinkageState(jzlYcZcQtNumber, jzlYcZcCheckBox, jzlYcZcSzCheckBox, jzlYcZcXzCheckBox, jzlYcZcShouCheckBox, jzlYcZcQtEditText);
			break;
		case R.id.jzl_yc_qg_bj_spinner://肌张力 躯干 背肌得分
			String jzlYcQgBjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgLinkageState(jzlYcQgBjNumber,jzlYcQgCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_qg_yj_spinner://肌张力 躯干 腰肌得分
			String jzlYcQgYjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgLinkageState(jzlYcQgYjNumber,jzlYcQgCheckBox,jzlYcQgYjCheckBox,jzlYcQgBjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_qg_tj_spinner://肌张力 躯干 臀肌得分
			String jzlYcQgTjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgLinkageState(jzlYcQgTjNumber,jzlYcQgCheckBox,jzlYcQgTjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_qg_xj_spinner://肌张力 躯干 胸肌得分
			String jzlYcQgXjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgLinkageState(jzlYcQgXjNumber,jzlYcQgCheckBox,jzlYcQgXjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgFjCheckBox,jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_qg_fj_spinner://肌张力 躯干 腹肌得分
			String jzlYcQgFjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgLinkageState(jzlYcQgFjNumber,jzlYcQgCheckBox,jzlYcQgFjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_qg_qt_spinner://肌张力 躯干 其他得分
			String jzlYcQgQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qgQtLinkageState(jzlYcQgQtNumber,jzlYcQgCheckBox, jzlYcQgBjCheckBox, jzlYcQgYjCheckBox, jzlYcQgTjCheckBox, jzlYcQgXjCheckBox, jzlYcQgFjCheckBox, jzlYcQgQtEditText);
			break;
		case R.id.jzl_yc_rc_sz_spinner:// 肌张力 右侧 上肢得分
			String jzlYcRcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcRcSzNumber, jzlYcRcCheckBox, jzlYcRcSzCheckBox, jzlYcRcXzCheckBox, jzlYcRcJiaoCheckBox, jzlYcRcQtEditText);
			break;		
		case R.id.jzl_yc_rc_xz_spinner:// 肌张力 右侧 下肢得分
			String jzlYcRcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcRcXzNumber, jzlYcRcCheckBox, jzlYcRcXzCheckBox, jzlYcRcSzCheckBox, jzlYcRcJiaoCheckBox, jzlYcRcQtEditText);
			break;
		case R.id.jzl_yc_rc_jiao_spinner:// 肌张力 右侧 脚得分
			String jzlYcRcJiaoNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			linkageState(jzlYcRcJiaoNumber,jzlYcRcCheckBox, jzlYcRcJiaoCheckBox, jzlYcRcSzCheckBox, jzlYcRcXzCheckBox, jzlYcRcQtEditText);
			break;
		case R.id.jzl_yc_rc_qt_spinner:// 肌张力 右侧 其他得分
			String jzlYcRcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.muscleTensionSpinner)[position];
			qtLinkageState(jzlYcRcQtNumber,jzlYcRcCheckBox,jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcQtEditText);
			break;	
		case R.id.ttpf_yc_zc_shou_spinner:// 疼痛评分 左侧 手得分
			String ttpfYcZcShouNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcZcShouNumber,ttpfYcZcCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcQtEditText);
			break;		
		case R.id.ttpf_yc_zc_sz_spinner:// 疼痛评分 左侧 上肢得分
			String ttpfYcZcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcZcSzNumber,ttpfYcZcCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcQtEditText);
			break;
		case R.id.ttpf_yc_zc_xz_spinner:// 疼痛评分 左侧 下肢得分
			String ttpfYcZcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcZcXzNumber,ttpfYcZcCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcQtEditText);
			break;
		case R.id.ttpf_yc_zc_qt_spinner:// 疼痛评分 左侧 其他得分
			String ttpfYcZcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qtLinkageState(ttpfYcZcQtNumber, ttpfYcZcCheckBox, ttpfYcZcSzCheckBox, ttpfYcZcXzCheckBox, ttpfYcZcShouCheckBox, ttpfYcZcQtEditText);
			break;
		case R.id.ttpf_yc_qg_bj_spinner://疼痛评分 躯干 背肌得分
			String ttpfYcQgBjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgLinkageState(ttpfYcQgBjNumber,ttpfYcQgCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_qg_yj_spinner://疼痛评分 躯干 腰肌得分
			String ttpfYcQgYjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgLinkageState(ttpfYcQgYjNumber,ttpfYcQgCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_qg_tj_spinner://疼痛评分 躯干 臀肌得分
			String ttpfYcQgTjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgLinkageState(ttpfYcQgTjNumber,ttpfYcQgCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_qg_xj_spinner://疼痛评分 躯干 胸肌得分
			String ttpfYcQgXjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgLinkageState(ttpfYcQgXjNumber,ttpfYcQgCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_qg_fj_spinner://疼痛评分 躯干 腹肌得分
			String ttpfYcQgFjNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgLinkageState(ttpfYcQgFjNumber,ttpfYcQgCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_qg_qt_spinner://疼痛评分 躯干 其他得分
			String ttpfYcQgQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qgQtLinkageState(ttpfYcQgQtNumber, ttpfYcQgCheckBox, ttpfYcQgBjCheckBox, ttpfYcQgYjCheckBox, ttpfYcQgTjCheckBox, ttpfYcQgXjCheckBox, ttpfYcQgFjCheckBox, ttpfYcQgQtEditText);
			break;
		case R.id.ttpf_yc_rc_sz_spinner:// 疼痛评分 右侧 上肢得分
			String ttpfYcRcSzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcRcSzNumber,ttpfYcRcCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcQtEditText);
			break;		
		case R.id.ttpf_yc_rc_xz_spinner:// 疼痛评分 右侧 下肢得分
			String ttpfYcRcXzNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcRcXzNumber, ttpfYcRcCheckBox, ttpfYcRcXzCheckBox, ttpfYcRcSzCheckBox, ttpfYcRcJiaoCheckBox, ttpfYcRcQtEditText);
			break;
		case R.id.ttpf_yc_rc_jiao_spinner:// 疼痛评分 右侧 脚得分
			String ttpfYcRcJiaoNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			linkageState(ttpfYcRcJiaoNumber,ttpfYcRcCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcQtEditText);
			break;
		case R.id.ttpf_yc_rc_qt_spinner:// 疼痛评分 右侧 其他得分
			String ttpfYcRcQtNumber = SportAssessActivity.this.getResources().getStringArray(R.array.painSpinner)[position];
			qtLinkageState(ttpfYcRcQtNumber,ttpfYcRcCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcQtEditText);
			break;		
		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	/**
	 * 左右侧Spinner （除了其他）联动状态 
	 */
	private void linkageState(String str,CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,EditText qtEt){
		if (!"".equals(str)&& !"请选择".equals(str)) {
			cb.setChecked(true);
			cb1.setChecked(true);
		}
		else if ("请选择".equals(str)) {
			cb1.setChecked(false);
			if (cb2.isChecked()|| cb3.isChecked()|| !"".equals(qtEt.getText().toString().trim())) {
				cb.setChecked(true);
			} else {
				cb.setChecked(false);
			}
		}
	}
	/**
	 * 左右侧其他Spinner 联动状态 
	 */
	private void qtLinkageState(String str,CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,EditText qtEt){
		if (!"".equals(str)&& !"请选择".equals(str)|| !"".equals(qtEt.getText().toString().trim())) {
			cb.setChecked(true);
		}else if ("请选择".equals(str)) {
			if (cb1.isChecked()|| cb2.isChecked()|| cb3.isChecked()) {
				cb.setChecked(true);
			} else {
				cb.setChecked(false);
			}
		}
	}
	/**
	 * 躯干Spinner （除了其他）联动状态 
	 */
	private void qgLinkageState(String str,CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,CheckBox cb4,CheckBox cb5,EditText qtEt){
		if (!"".equals(str) && !"请选择".equals(str)) {
			cb.setChecked(true);
			cb1.setChecked(true);
		}else if ("请选择".equals(str)) {
			cb1.setChecked(false);
			if (cb2.isChecked() || cb3.isChecked() || cb4.isChecked() ||cb5.isChecked()  || !"".equals(qtEt.getText().toString().trim())) {
				cb.setChecked(true);
			}else {
				cb.setChecked(false);
			}
		}
	}
	/**
	 * 躯干其他Spinner 联动状态 
	 */
	private void qgQtLinkageState(String str,CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,CheckBox cb4,CheckBox cb5,EditText qtEt){
		if (!"".equals(str) && !"请选择".equals(str) || !"".equals(qtEt.getText().toString().trim())) {
			cb.setChecked(true);
		}else if ("请选择".equals(str)) {
			if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()|| cb4.isChecked() || cb5.isChecked()) {
				cb.setChecked(true);
			}else {
				cb.setChecked(false);
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg0.getId()) {
		case R.id.strength_radioGroup://肌力 正常 异常选择
			if (arg1 == strengthExceptionRadioButton.getId()) {
				strengthLinearLayout.setVisibility(LinearLayout.VISIBLE);
			} else if (arg1 == strengthNormalRadioButton.getId()) {
				strengthLinearLayout.setVisibility(LinearLayout.GONE);
				clearJlControls();
			}
			break;
		case R.id.muscleTension_radioGroup://肌张力 正常 异常选择
			if (arg1 == muscleTensionExceptionRadioButton.getId()) {
				muscleTensionLinearLayout.setVisibility(LinearLayout.VISIBLE);
			} else if (arg1 == muscleTensionNormalRadioButton
					.getId()) {
				muscleTensionLinearLayout.setVisibility(LinearLayout.GONE);
				clearJzlControls();
			}
			break;
		case R.id.pain_radioGroup://疼痛评分 正常 异常选择
			if (arg1 == painExceptionRadioButton.getId()) {
				painLinearLayout.setVisibility(LinearLayout.VISIBLE);
			} else if (arg1 == painNormalRadioButton.getId()) {
				painLinearLayout.setVisibility(LinearLayout.GONE);
				clearTtpfControls();
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 肌力正常  清空
	 */
	private void clearJlControls(){
		jlYcZcCheckBox.setChecked(false);
		jlYcZcShouCheckBox.setChecked(false);
		jlYcZcSzCheckBox.setChecked(false);
		jlYcZcXzCheckBox.setChecked(false);
		jlYcZcQtEditText.setText("");
		jlYcZcShouSpinner.setSelection(0);
		jlYcZcSzSpinner.setSelection(0);
		jlYcZcXzSpinner.setSelection(0);
		jlYcZcQtSpinner.setSelection(0);	
		jlYcQgBjCheckBox.setChecked(false);
		jlYcQgYjCheckBox.setChecked(false);
		jlYcQgTjCheckBox.setChecked(false);
		jlYcQgXjCheckBox.setChecked(false);
		jlYcQgFjCheckBox.setChecked(false);
		jlYcQgQtEditText.setText("");
		jlYcQgBjSpinner.setSelection(0);
		jlYcQgYjSpinner.setSelection(0);
		jlYcQgTjSpinner.setSelection(0);
		jlYcQgXjSpinner.setSelection(0);
		jlYcQgFjSpinner.setSelection(0);
		jlYcQgQtSpinner.setSelection(0);	
		jlYcRcSzCheckBox.setChecked(false);
		jlYcRcXzCheckBox.setChecked(false);
		jlYcRcJiaoCheckBox.setChecked(false);
		jlYcRcQtEditText.setText("");
		jlYcRcSzSpinner.setSelection(0);
		jlYcRcXzSpinner.setSelection(0);
		jlYcRcJiaoSpinner.setSelection(0);
		jlYcRcQtSpinner.setSelection(0);
		
	}

	/**
	 * 肌张力正常  清空
	 */
	private void clearJzlControls(){
		jzlYcZcCheckBox.setChecked(false);
		jzlYcZcShouCheckBox.setChecked(false);
		jzlYcZcSzCheckBox.setChecked(false);
		jzlYcZcXzCheckBox.setChecked(false);
		jzlYcZcQtEditText.setText("");
		jzlYcZcShouSpinner.setSelection(0);
		jzlYcZcSzSpinner.setSelection(0);
		jzlYcZcXzSpinner.setSelection(0);
		jzlYcZcQtSpinner.setSelection(0);	
		jzlYcQgBjCheckBox.setChecked(false);
		jzlYcQgYjCheckBox.setChecked(false);
		jzlYcQgTjCheckBox.setChecked(false);
		jzlYcQgXjCheckBox.setChecked(false);
		jzlYcQgFjCheckBox.setChecked(false);
		jzlYcQgQtEditText.setText("");
		jzlYcQgBjSpinner.setSelection(0);
		jzlYcQgYjSpinner.setSelection(0);
		jzlYcQgTjSpinner.setSelection(0);
		jzlYcQgXjSpinner.setSelection(0);
		jzlYcQgFjSpinner.setSelection(0);
		jzlYcQgQtSpinner.setSelection(0);	
		jzlYcRcSzCheckBox.setChecked(false);
		jzlYcRcXzCheckBox.setChecked(false);
		jzlYcRcJiaoCheckBox.setChecked(false);
		jzlYcRcQtEditText.setText("");
		jzlYcRcSzSpinner.setSelection(0);
		jzlYcRcXzSpinner.setSelection(0);
		jzlYcRcJiaoSpinner.setSelection(0);
		jzlYcRcQtSpinner.setSelection(0);
		
	}
	
	/**
	 * 疼痛评分 无疼痛  清空
	 */
	private void clearTtpfControls(){
		ttpfYcZcCheckBox.setChecked(false);
		ttpfYcZcShouCheckBox.setChecked(false);
		ttpfYcZcSzCheckBox.setChecked(false);
		ttpfYcZcXzCheckBox.setChecked(false);
		ttpfYcZcQtEditText.setText("");
		ttpfYcZcShouSpinner.setSelection(0);
		ttpfYcZcSzSpinner.setSelection(0);
		ttpfYcZcXzSpinner.setSelection(0);
		ttpfYcZcQtSpinner.setSelection(0);	
		ttpfYcQgBjCheckBox.setChecked(false);
		ttpfYcQgYjCheckBox.setChecked(false);
		ttpfYcQgTjCheckBox.setChecked(false);
		ttpfYcQgXjCheckBox.setChecked(false);
		ttpfYcQgFjCheckBox.setChecked(false);
		ttpfYcQgQtEditText.setText("");
		ttpfYcQgBjSpinner.setSelection(0);
		ttpfYcQgYjSpinner.setSelection(0);
		ttpfYcQgTjSpinner.setSelection(0);
		ttpfYcQgXjSpinner.setSelection(0);
		ttpfYcQgFjSpinner.setSelection(0);
		ttpfYcQgQtSpinner.setSelection(0);	
		ttpfYcRcSzCheckBox.setChecked(false);
		ttpfYcRcXzCheckBox.setChecked(false);
		ttpfYcRcJiaoCheckBox.setChecked(false);
		ttpfYcRcQtEditText.setText("");
		ttpfYcRcSzSpinner.setSelection(0);
		ttpfYcRcXzSpinner.setSelection(0);
		ttpfYcRcJiaoSpinner.setSelection(0);
		ttpfYcRcQtSpinner.setSelection(0);
		
	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		switch (arg0.getId()) {
			case R.id.jl_yc_zc_checkBox://肌力 左侧 
				chooseSideCheckBox(jlYcZcCheckBox,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzCheckBox,jlYcZcShouSpinner,jlYcZcSzSpinner,jlYcZcXzSpinner,jlYcZcQtSpinner,jlYcZcQtEditText);
				break;
			case R.id.jl_yc_zc_shou_checkBox://肌力 左侧 手 
				chooseCheckBox(jlYcZcCheckBox,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzCheckBox,jlYcZcShouSpinner,jlYcZcSzSpinner,jlYcZcXzSpinner,jlYcZcQtSpinner,jlYcZcQtEditText);			
				break;
			case R.id.jl_yc_zc_sz_checkBox://肌力 左侧 上肢 
				chooseCheckBox(jlYcZcCheckBox,jlYcZcSzCheckBox,jlYcZcShouCheckBox,jlYcZcXzCheckBox,jlYcZcSzSpinner,jlYcZcXzSpinner,jlYcZcShouSpinner,jlYcZcQtSpinner,jlYcZcQtEditText);		
				break;
			case R.id.jl_yc_zc_xz_checkBox://肌力 左侧 下肢  
				chooseCheckBox(jlYcZcCheckBox,jlYcZcXzCheckBox,jlYcZcShouCheckBox,jlYcZcSzCheckBox,jlYcZcXzSpinner,jlYcZcSzSpinner,jlYcZcShouSpinner,jlYcZcQtSpinner,jlYcZcQtEditText);	
				break;	
			case R.id.jl_yc_qg_checkBox://肌力 躯干
				chooseQgSideCheckBox(jlYcQgCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,
						jlYcQgBjSpinner,jlYcQgYjSpinner,jlYcQgTjSpinner,jlYcQgXjSpinner,jlYcQgFjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);
				break;
			case R.id.jl_yc_qg_bj_checkBox://肌力 躯干 背肌  
				chooseQgCheckBox(jlYcQgCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,
						jlYcQgBjSpinner,jlYcQgYjSpinner,jlYcQgTjSpinner,jlYcQgXjSpinner,jlYcQgFjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);	
				break;			
			case R.id.jl_yc_qg_yj_checkBox://肌力 躯干 腰肌  
				chooseQgCheckBox(jlYcQgCheckBox,jlYcQgYjCheckBox,jlYcQgBjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,
						jlYcQgYjSpinner,jlYcQgBjSpinner,jlYcQgTjSpinner,jlYcQgXjSpinner,jlYcQgFjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);	
				break;
			case R.id.jl_yc_qg_tj_checkBox://肌力 躯干 臀肌  
				chooseQgCheckBox(jlYcQgCheckBox,jlYcQgTjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgXjCheckBox,jlYcQgFjCheckBox,
						jlYcQgTjSpinner,jlYcQgBjSpinner,jlYcQgYjSpinner,jlYcQgXjSpinner,jlYcQgFjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);	
				break;
			case R.id.jl_yc_qg_xj_checkBox://肌力 躯干 胸肌  
				chooseQgCheckBox(jlYcQgCheckBox,jlYcQgXjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgFjCheckBox,
						jlYcQgXjSpinner,jlYcQgBjSpinner,jlYcQgYjSpinner,jlYcQgTjSpinner,jlYcQgFjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);	
				break;
			case R.id.jl_yc_qg_fj_checkBox://肌力 躯干 腹肌  
				chooseQgCheckBox(jlYcQgCheckBox,jlYcQgFjCheckBox,jlYcQgBjCheckBox,jlYcQgYjCheckBox,jlYcQgTjCheckBox,jlYcQgXjCheckBox,
						jlYcQgFjSpinner,jlYcQgBjSpinner,jlYcQgYjSpinner,jlYcQgTjSpinner,jlYcQgXjSpinner,jlYcQgQtSpinner,jlYcQgQtEditText);	
				break;			
			case R.id.jl_yc_rc_checkBox://肌力 右侧
				chooseSideCheckBox(jlYcRcCheckBox,jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoCheckBox,jlYcRcSzSpinner,jlYcRcXzSpinner,jlYcRcJiaoSpinner,jlYcRcQtSpinner,jlYcRcQtEditText);
				break;
			case R.id.jl_yc_rc_sz_checkBox://肌力 右侧 上肢 
				chooseCheckBox(jlYcRcCheckBox,jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoCheckBox,jlYcRcSzSpinner,jlYcRcXzSpinner,jlYcRcJiaoSpinner,jlYcRcQtSpinner,jlYcRcQtEditText);			
				break;
			case R.id.jl_yc_rc_xz_checkBox://肌力 右侧 下肢 
				chooseCheckBox(jlYcRcCheckBox,jlYcRcXzCheckBox,jlYcRcSzCheckBox,jlYcRcJiaoCheckBox,jlYcRcXzSpinner,jlYcRcSzSpinner,jlYcRcJiaoSpinner,jlYcRcQtSpinner,jlYcRcQtEditText);		
				break;
			case R.id.jl_yc_rc_jiao_checkBox://肌力 右侧 脚 
				chooseCheckBox(jlYcRcCheckBox,jlYcRcJiaoCheckBox,jlYcRcSzCheckBox,jlYcRcXzCheckBox,jlYcRcJiaoSpinner,jlYcRcSzSpinner,jlYcRcXzSpinner,jlYcRcQtSpinner,jlYcRcQtEditText);	
				break;	
		
			case R.id.jzl_yc_zc_checkBox://肌张力 左侧 
				chooseSideCheckBox(jzlYcZcCheckBox,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzCheckBox,jzlYcZcShouSpinner,jzlYcZcSzSpinner,jzlYcZcXzSpinner,jzlYcZcQtSpinner,jzlYcZcQtEditText);
				break;
			case R.id.jzl_yc_zc_shou_checkBox://肌张力 左侧 手 
				chooseCheckBox(jzlYcZcCheckBox,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzCheckBox,jzlYcZcShouSpinner,jzlYcZcSzSpinner,jzlYcZcXzSpinner,jzlYcZcQtSpinner,jzlYcZcQtEditText);			
				break;
			case R.id.jzl_yc_zc_sz_checkBox://肌张力 左侧 上肢 
				chooseCheckBox(jzlYcZcCheckBox,jzlYcZcSzCheckBox,jzlYcZcShouCheckBox,jzlYcZcXzCheckBox,jzlYcZcSzSpinner,jzlYcZcXzSpinner,jzlYcZcShouSpinner,jzlYcZcQtSpinner,jzlYcZcQtEditText);		
				break;
			case R.id.jzl_yc_zc_xz_checkBox://肌张力 左侧 下肢  
				chooseCheckBox(jzlYcZcCheckBox,jzlYcZcXzCheckBox,jzlYcZcShouCheckBox,jzlYcZcSzCheckBox,jzlYcZcXzSpinner,jzlYcZcSzSpinner,jzlYcZcShouSpinner,jzlYcZcQtSpinner,jzlYcZcQtEditText);	
				break;	
			case R.id.jzl_yc_qg_checkBox://肌张力 躯干
				chooseQgSideCheckBox(jzlYcQgCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,
						jzlYcQgBjSpinner,jzlYcQgYjSpinner,jzlYcQgTjSpinner,jzlYcQgXjSpinner,jzlYcQgFjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);
				break;
			case R.id.jzl_yc_qg_bj_checkBox://肌张力 躯干 背肌  
				chooseQgCheckBox(jzlYcQgCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,
						jzlYcQgBjSpinner,jzlYcQgYjSpinner,jzlYcQgTjSpinner,jzlYcQgXjSpinner,jzlYcQgFjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);	
				break;			
			case R.id.jzl_yc_qg_yj_checkBox://肌张力 躯干 腰肌  
				chooseQgCheckBox(jzlYcQgCheckBox,jzlYcQgYjCheckBox,jzlYcQgBjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,
						jzlYcQgYjSpinner,jzlYcQgBjSpinner,jzlYcQgTjSpinner,jzlYcQgXjSpinner,jzlYcQgFjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);	
				break;
			case R.id.jzl_yc_qg_tj_checkBox://肌张力 躯干 臀肌  
				chooseQgCheckBox(jzlYcQgCheckBox,jzlYcQgTjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgXjCheckBox,jzlYcQgFjCheckBox,
						jzlYcQgTjSpinner,jzlYcQgBjSpinner,jzlYcQgYjSpinner,jzlYcQgXjSpinner,jzlYcQgFjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);	
				break;
			case R.id.jzl_yc_qg_xj_checkBox://肌张力 躯干 胸肌  
				chooseQgCheckBox(jzlYcQgCheckBox,jzlYcQgXjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgFjCheckBox,
						jzlYcQgXjSpinner,jzlYcQgBjSpinner,jzlYcQgYjSpinner,jzlYcQgTjSpinner,jzlYcQgFjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);	
				break;
			case R.id.jzl_yc_qg_fj_checkBox://肌张力 躯干 腹肌  
				chooseQgCheckBox(jzlYcQgCheckBox,jzlYcQgFjCheckBox,jzlYcQgBjCheckBox,jzlYcQgYjCheckBox,jzlYcQgTjCheckBox,jzlYcQgXjCheckBox,
						jzlYcQgFjSpinner,jzlYcQgBjSpinner,jzlYcQgYjSpinner,jzlYcQgTjSpinner,jzlYcQgXjSpinner,jzlYcQgQtSpinner,jzlYcQgQtEditText);	
				break;			
			case R.id.jzl_yc_rc_checkBox://肌张力 右侧
				chooseSideCheckBox(jzlYcRcCheckBox,jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcSzSpinner,jzlYcRcXzSpinner,jzlYcRcJiaoSpinner,jzlYcRcQtSpinner,jzlYcRcQtEditText);
				break;
			case R.id.jzl_yc_rc_sz_checkBox://肌张力 右侧 上肢 
				chooseCheckBox(jzlYcRcCheckBox,jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcSzSpinner,jzlYcRcXzSpinner,jzlYcRcJiaoSpinner,jzlYcRcQtSpinner,jzlYcRcQtEditText);			
				break;
			case R.id.jzl_yc_rc_xz_checkBox://肌张力 右侧 下肢 
				chooseCheckBox(jzlYcRcCheckBox,jzlYcRcXzCheckBox,jzlYcRcSzCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcXzSpinner,jzlYcRcSzSpinner,jzlYcRcJiaoSpinner,jzlYcRcQtSpinner,jzlYcRcQtEditText);		
				break;
			case R.id.jzl_yc_rc_jiao_checkBox://肌张力 右侧 脚 
				chooseCheckBox(jzlYcRcCheckBox,jzlYcRcJiaoCheckBox,jzlYcRcSzCheckBox,jzlYcRcXzCheckBox,jzlYcRcJiaoSpinner,jzlYcRcSzSpinner,jzlYcRcXzSpinner,jzlYcRcQtSpinner,jzlYcRcQtEditText);	
				break;	
		
			case R.id.ttpf_yc_zc_checkBox://疼痛评分 左侧 
				chooseSideCheckBox(ttpfYcZcCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcShouSpinner,ttpfYcZcSzSpinner,ttpfYcZcXzSpinner,ttpfYcZcQtSpinner,ttpfYcZcQtEditText);
				break;
			case R.id.ttpf_yc_zc_shou_checkBox://疼痛评分 左侧 手 
				chooseCheckBox(ttpfYcZcCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcShouSpinner,ttpfYcZcSzSpinner,ttpfYcZcXzSpinner,ttpfYcZcQtSpinner,ttpfYcZcQtEditText);			
				break;
			case R.id.ttpf_yc_zc_sz_checkBox://疼痛评分 左侧 上肢 
				chooseCheckBox(ttpfYcZcCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcSzSpinner,ttpfYcZcXzSpinner,ttpfYcZcShouSpinner,ttpfYcZcQtSpinner,ttpfYcZcQtEditText);		
				break;
			case R.id.ttpf_yc_zc_xz_checkBox://疼痛评分 左侧 下肢  
				chooseCheckBox(ttpfYcZcCheckBox,ttpfYcZcXzCheckBox,ttpfYcZcShouCheckBox,ttpfYcZcSzCheckBox,ttpfYcZcXzSpinner,ttpfYcZcSzSpinner,ttpfYcZcShouSpinner,ttpfYcZcQtSpinner,ttpfYcZcQtEditText);	
				break;
			case R.id.ttpf_yc_qg_checkBox://疼痛评分 躯干
				chooseQgSideCheckBox(ttpfYcQgCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,
						ttpfYcQgBjSpinner,ttpfYcQgYjSpinner,ttpfYcQgTjSpinner,ttpfYcQgXjSpinner,ttpfYcQgFjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);
				break;
			case R.id.ttpf_yc_qg_bj_checkBox://疼痛评分 躯干 背肌  
				chooseQgCheckBox(ttpfYcQgCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,
						ttpfYcQgBjSpinner,ttpfYcQgYjSpinner,ttpfYcQgTjSpinner,ttpfYcQgXjSpinner,ttpfYcQgFjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);	
				break;			
			case R.id.ttpf_yc_qg_yj_checkBox://疼痛评分 躯干 腰肌  
				chooseQgCheckBox(ttpfYcQgCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,
						ttpfYcQgYjSpinner,ttpfYcQgBjSpinner,ttpfYcQgTjSpinner,ttpfYcQgXjSpinner,ttpfYcQgFjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);	
				break;
			case R.id.ttpf_yc_qg_tj_checkBox://疼痛评分 躯干 臀肌  
				chooseQgCheckBox(ttpfYcQgCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgFjCheckBox,
						ttpfYcQgTjSpinner,ttpfYcQgBjSpinner,ttpfYcQgYjSpinner,ttpfYcQgXjSpinner,ttpfYcQgFjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);	
				break;
			case R.id.ttpf_yc_qg_xj_checkBox://疼痛评分 躯干 胸肌  
				chooseQgCheckBox(ttpfYcQgCheckBox,ttpfYcQgXjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgFjCheckBox,
						ttpfYcQgXjSpinner,ttpfYcQgBjSpinner,ttpfYcQgYjSpinner,ttpfYcQgTjSpinner,ttpfYcQgFjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);	
				break;
			case R.id.ttpf_yc_qg_fj_checkBox://疼痛评分 躯干 腹肌  
				chooseQgCheckBox(ttpfYcQgCheckBox,ttpfYcQgFjCheckBox,ttpfYcQgBjCheckBox,ttpfYcQgYjCheckBox,ttpfYcQgTjCheckBox,ttpfYcQgXjCheckBox,
						ttpfYcQgFjSpinner,ttpfYcQgBjSpinner,ttpfYcQgYjSpinner,ttpfYcQgTjSpinner,ttpfYcQgXjSpinner,ttpfYcQgQtSpinner,ttpfYcQgQtEditText);	
				break;		
			case R.id.ttpf_yc_rc_checkBox://疼痛评分 右侧
				chooseSideCheckBox(ttpfYcRcCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcSzSpinner,ttpfYcRcXzSpinner,ttpfYcRcJiaoSpinner,ttpfYcRcQtSpinner,ttpfYcRcQtEditText);
				break;
			case R.id.ttpf_yc_rc_sz_checkBox://疼痛评分 右侧 上肢 
				chooseCheckBox(ttpfYcRcCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcSzSpinner,ttpfYcRcXzSpinner,ttpfYcRcJiaoSpinner,ttpfYcRcQtSpinner,ttpfYcRcQtEditText);			
				break;
			case R.id.ttpf_yc_rc_xz_checkBox://疼痛评分 右侧 下肢 
				chooseCheckBox(ttpfYcRcCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcXzSpinner,ttpfYcRcSzSpinner,ttpfYcRcJiaoSpinner,ttpfYcRcQtSpinner,ttpfYcRcQtEditText);		
				break;
			case R.id.ttpf_yc_rc_jiao_checkBox://疼痛评分 右侧 脚 
				chooseCheckBox(ttpfYcRcCheckBox,ttpfYcRcJiaoCheckBox,ttpfYcRcSzCheckBox,ttpfYcRcXzCheckBox,ttpfYcRcJiaoSpinner,ttpfYcRcSzSpinner,ttpfYcRcXzSpinner,ttpfYcRcQtSpinner,ttpfYcRcQtEditText);	
				break;	
			default:
				break;
		}
	}
	
	/**
	 * 异常 左右侧（上肢 下肢 脚 手 其他） checkbox选择
	 */
	private void chooseCheckBox(CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,Spinner s1,Spinner s2,Spinner s3,Spinner s4,EditText et){
		if (cb1.isChecked() == true ) {
			cb.setChecked(true);
		}else if(cb1.isChecked() == false){
			s1.setSelection(0);
			if (cb2.isChecked() == true || cb3.isChecked() == true || !"请选择".equals(s4.getSelectedItem().toString().trim()) || !"".equals(et.getText().toString().trim())) {
				 cb.setChecked(true);
			}else{
				cb.setChecked(false);
				s1.setSelection(0);
				s2.setSelection(0);
				s3.setSelection(0);
				s4.setSelection(0);
				et.setText("");
			}
		}		
	}
	/**
	 * 异常 躯干(背肌 腰肌 臀肌 胸肌 腹肌 其他 ) checkbox选择
	 */
	private void chooseQgCheckBox(CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,CheckBox cb4,CheckBox cb5,Spinner s1,Spinner s2,Spinner s3,Spinner s4,Spinner s5,Spinner s6,EditText et){
		if (cb1.isChecked() == true ) {
			cb.setChecked(true);
		}else if(cb1.isChecked() == false){
			s1.setSelection(0);
			if (cb2.isChecked() == true || cb3.isChecked() == true || cb4.isChecked() == true || cb5.isChecked() == true || !"请选择".equals(s6.getSelectedItem().toString().trim()) || !"".equals(et.getText().toString().trim())) {
				 cb.setChecked(true);
			}else{
				cb.setChecked(false);
				s1.setSelection(0);
				s2.setSelection(0);
				s3.setSelection(0);
				s4.setSelection(0);
				s5.setSelection(0);
				s6.setSelection(0);
				et.setText("");
			}
		}		
	}
	/**
	 * 左右侧 checkbox选择
	 */
	private void chooseSideCheckBox(CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,Spinner s1,Spinner s2,Spinner s3,Spinner s4,EditText et){
		if (cb.isChecked() == false) {
			cb1.setChecked(false);
			cb2.setChecked(false);
			cb3.setChecked(false);
			s1.setSelection(0);
			s2.setSelection(0);
			s3.setSelection(0);
			s4.setSelection(0);
			et.setText("");
		}	
	}
	/**
	 * 躯干 checkbox选择
	 */
	private void chooseQgSideCheckBox(CheckBox cb,CheckBox cb1,CheckBox cb2,CheckBox cb3,CheckBox cb4,CheckBox cb5,Spinner s1,Spinner s2,Spinner s3,Spinner s4,Spinner s5,Spinner s6,EditText et){
		if (cb.isChecked() == false) {
			cb1.setChecked(false);
			cb2.setChecked(false);
			cb3.setChecked(false);
			cb4.setChecked(false);
			cb5.setChecked(false);
			s1.setSelection(0);
			s2.setSelection(0);
			s3.setSelection(0);
			s4.setSelection(0);
			s5.setSelection(0);
			s6.setSelection(0);
			et.setText("");
		}	
	}
	
}
