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
import com.pinetree.mobile.bean.DrugState;
import com.pinetree.mobile.bean.DrugStateBase;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 以前叫药品使用，现在又叫查体啦。。。
 */
public class DrugStateActivity extends Activity implements OnClickListener {
	
	protected static final int INITDATADB = 0;
	protected static final int INITDATANET = 10;
	
	@ViewInject(R.id.back_drug_imageView)
	private ImageView backDrugImageView;
	/**
	 * 饮食正常
	 */
	@ViewInject(R.id.ys_zc_checkBox)
	private CheckBox ysZcCheckBox;
	/**
	 * 饮食偏多
	 */
	@ViewInject(R.id.ys_pd_checkBox)
	private CheckBox ysPdCheckBox;
	/**
	 * 饮食偏少
	 */
	@ViewInject(R.id.ys_ps_checkBox)
	private CheckBox ysPsCheckBox;
	
	/**
	 * 饮食呛咳
	 */
	@ViewInject(R.id.ys_qk_checkBox)
	private CheckBox ysQkCheckBox;
	/**
	 * 饮食鼻饲
	 */
	@ViewInject(R.id.ys_bc_checkBox)
	private CheckBox ysBcCheckBox;
	/**
	 * 饮食喂食
	 */
	@ViewInject(R.id.ys_ws_checkBox)
	private CheckBox ysWsCheckBox;
	/**
	 * 饮食厌食
	 */
	@ViewInject(R.id.ys_ys_checkBox)
	private CheckBox ysYsCheckBox;
	/**
	 * 饮食 食欲不振
	 */
	@ViewInject(R.id.ys_sybz_checkBox)
	private CheckBox ysSybzCheckBox;
	/**
	 * 饮食 进食困难
	 */
	@ViewInject(R.id.ys_jskn_checkBox)
	private CheckBox ysJsknCheckBox;
	/**
	 * 饮食 主食
	 */
	@ViewInject(R.id.ys_zs_editText)
	private EditText ysZsEditText;
	/**
	 * 饮食 蔬菜
	 */
	@ViewInject(R.id.ys_sc_editText)
	private EditText ysScEditText;
	/**
	 * 饮食 蛋白质
	 */
	@ViewInject(R.id.ys_dbz_editText)
	private EditText ysDbzEditText;
	/**
	 * 饮食 饮水
	 */
	@ViewInject(R.id.ys_yis_editText)
	private EditText ysYisEditText;
	/**
	 * 睡眠 正常
	 */
	@ViewInject(R.id.sm_zc_checkBox)
	private CheckBox smZcCheckBox;
	/**
	 * 睡眠 失眠
	 */
	@ViewInject(R.id.sm_sm_checkBox)
	private CheckBox smSmCheckBox;
	/**
	 * 睡眠 多梦
	 */
	@ViewInject(R.id.sm_dm_checkBox)
	private CheckBox smDmCheckBox;
	/**
	 * 睡眠 黑白颠倒
	 */
	@ViewInject(R.id.sm_hbdd_checkBox)
	private CheckBox smHbddCheckBox;
	/**
	 * 睡眠 原因
	 */
	@ViewInject(R.id.sm_yy_editText)
	private EditText smYyEditText;
	/**
	 * 睡眠 是否治疗/用药
	 */
	@ViewInject(R.id.sm_sfzl_editText)
	private EditText smSfzlEditText;
	/**
	 * 睡眠 影响如何
	 */
	@ViewInject(R.id.sm_yx_editText)
	private EditText smYxEditText;
	/**
	 * 睡眠 其他
	 */
	@ViewInject(R.id.sm_smqt_editText)
	private EditText smQtEditText;
	/**
	 * 排便 情况表述
	 */
	@ViewInject(R.id.pb_qkbs_editText)
	private EditText pbQkbsEditText;
	/**
	 * 排便 原因
	 */
	@ViewInject(R.id.pb_yy_editText)
	private EditText pbYyEditText;
	/**
	 * 排便 是否治疗/用药/饮食调理
	 */
	@ViewInject(R.id.pb_sfzl_editText)
	private EditText pbSfzlEditText;
	/**
	 * 排便 便秘与腹泻交替
	 */
	@ViewInject(R.id.pb_bmfx_checkBox)
	private CheckBox pbBmfxCheckBox;
	/**
	 * 排尿 正常
	 */
	@ViewInject(R.id.pn_zc_checkBox)
	private CheckBox pnZcCheckBox;
	/**
	 * 排尿 不畅
	 */
	@ViewInject(R.id.pn_bc_checkBox)
	private CheckBox pnBcCheckBox;
	/**
	 * 排尿 失禁
	 */
	@ViewInject(R.id.pn_sj_checkBox)
	private CheckBox pnSjCheckBox;
	/**
	 * 排尿 疼痛
	 */
	@ViewInject(R.id.pn_tt_checkBox)
	private CheckBox pnTtCheckBox;
	/**
	 * 排尿 尿频
	 */
	@ViewInject(R.id.pn_np_checkBox)
	private CheckBox pnNpCheckBox;
	/**
	 * 排尿 留置导尿
	 */
	@ViewInject(R.id.pn_lzdn_checkBox)
	private CheckBox pnLzdnCheckBox;
	/**
	 * 排尿 尿潴留
	 */
	@ViewInject(R.id.pn_nzl_checkBox)
	private CheckBox pnNzlCheckBox;
	/**
	 * 排尿 压力性尿失禁
	 */
	@ViewInject(R.id.pn_ylxnsj_checkBox)
	private CheckBox pnYlxnsjCheckBox;
	/**
	 * 排尿 是否治疗
	 */
	@ViewInject(R.id.pn_sfzl_editText)
	private EditText pnSfzlEditText;
	/**
	 * 排尿 原因 
	 */
	@ViewInject(R.id.pn_yy_editText)
	private EditText pnYyEditText;
	/**
	 * 排尿 影响
	 */
	@ViewInject(R.id.pn_yx_editText)
	private EditText pnYxEditText;
	/**
	 * 视力 正常
	 */
	@ViewInject(R.id.sl_zc_checkBox)
	private CheckBox slZcCheckBox;
	/**
	 * 视力 老花
	 */
	@ViewInject(R.id.sl_lh_checkBox)
	private CheckBox slLhCheckBox;
	/**
	 * 视力 白内障
	 */
	@ViewInject(R.id.sl_bnz_checkBox)
	private CheckBox slBnzCheckBox;
	/**
	 * 视力 视物不清
	 */
	@ViewInject(R.id.sl_swbq_checkBox)
	private CheckBox slSwbqCheckBox;
	/**
	 * 视力 左失明
	 */
	@ViewInject(R.id.sl_zsm_checkBox)
	private CheckBox slZsmCheckBox;
	/**
	 * 视力 右失明
	 */
	@ViewInject(R.id.sl_ysm_checkBox)
	private CheckBox slYsmCheckBox;
	/**
	 * 视力 矫正后正常
	 */
	@ViewInject(R.id.sl_jzhzc_checkBox)
	private CheckBox slJzhzcCheckBox;
	/**
	 * 视力 左近视
	 */
	@ViewInject(R.id.sl_zjs_checkBox)
	private CheckBox slZjsCheckBox;
	/**
	 * 视力 右近视
	 */
	@ViewInject(R.id.sl_yjs_checkBox)
	private CheckBox slYjsCheckBox;
	/**
	 * 视力 原因
	 */
	@ViewInject(R.id.sl_yy_editText)
	private EditText slYyEditText;
	/**
	 * 视力 是否治疗
	 */
	@ViewInject(R.id.sl_sfzl_editText)
	private EditText slSfzlEditText;
	/**
	 * 视力 影响
	 */
	@ViewInject(R.id.sl_yx_editText)
	private EditText slYxEditText;
	/**
	 * 听力 正常
	 */
	@ViewInject(R.id.tl_zc_checkBox)
	private CheckBox tlZcCheckBox;
	/**
	 * 听力 耳背
	 */
	@ViewInject(R.id.tl_eb_checkBox)
	private CheckBox tlEbCheckBox;
	/**
	 * 听力 耳聋
	 */
	@ViewInject(R.id.tl_el_checkBox)
	private CheckBox tlElCheckBox;
	/**
	 * 听力 耳鸣
	 */
	@ViewInject(R.id.tl_em_checkBox)
	private CheckBox tlEmCheckBox;
	/**
	 * 听力 耳廓异物
	 */
	@ViewInject(R.id.tl_ekyw_checkBox)
	private CheckBox tlEkywCheckBox;
	/**
	 * 听力 耳垢
	 */
	@ViewInject(R.id.tl_eg_checkBox)
	private CheckBox tlEgCheckBox;
	/**
	 * 听力 左助听器
	 */
	@ViewInject(R.id.tl_zztq_checkBox)
	private CheckBox tlZztqCheckBox;
	/**
	 * 听力 右助听器
	 */
	@ViewInject(R.id.tl_yztq_checkBox)
	private CheckBox tlYztqCheckBox;
	/**
	 * 听力 原因
	 */
	@ViewInject(R.id.tl_yy_editText)
	private EditText tlYyEditText;
	/**
	 * 听力 是否治疗
	 */
	@ViewInject(R.id.tl_sfzl_editText)
	private EditText tlSfzlEditText;
	/**
	 * 听力 影响
	 */
	@ViewInject(R.id.tl_yx_editText)
	private EditText tlYxEditText;
	/**
	 * 语言 正常
	 */
	@ViewInject(R.id.yy_zc_checkBox)
	private CheckBox yyZcCheckBox;	
	/**
	 * 语言 不清
	 */
	@ViewInject(R.id.yy_bq_checkBox)
	private CheckBox yyBqCheckBox;
	/**
	 * 语言 失语
	 */
	@ViewInject(R.id.yy_sy_checkBox)
	private CheckBox yySyCheckBox;
	/**
	 * 语言 失音
	 */
	@ViewInject(R.id.yy_syin_checkBox)
	private CheckBox yySyinCheckBox;
	/**
	 * 语言 多语
	 */
	@ViewInject(R.id.yy_dy_checkBox)
	private CheckBox yyDyCheckBox;
	/**
	 * 语言 聋哑
	 */
	@ViewInject(R.id.yy_ly_checkBox)
	private CheckBox yyLyCheckBox;
	/**
	 * 语言 懒言
	 */
	@ViewInject(R.id.yy_lyan_checkBox)
	private CheckBox yyLyanCheckBox;
	/**
	 * 语言 原因
	 */
	@ViewInject(R.id.yy_yy_editText)
	private EditText yyYyEditText;
	/**
	 * 语言 是否治疗
	 */
	@ViewInject(R.id.yy_sfzl_editText)
	private EditText yySfzlEditText;
	/**
	 * 语言 影响
	 */
	@ViewInject(R.id.yy_yx_editText)
	private EditText yyYxEditText;	
	/**
	 * 意识 正常
	 */
	@ViewInject(R.id.yis_zc_checkBox)
	private CheckBox yisZcCheckBox;
	/**
	 * 意识 嗜睡
	 */
	@ViewInject(R.id.yis_ss_checkBox)
	private CheckBox yisSsCheckBox;
	/**
	 * 意识 谵妄
	 */
	@ViewInject(R.id.yis_zw_checkBox)
	private CheckBox yisZwCheckBox;
	/**
	 * 意识 淡漠
	 */
	@ViewInject(R.id.yis_dm_checkBox)
	private CheckBox yisDmCheckBox;
	/**
	 * 意识 幻觉
	 */
	@ViewInject(R.id.yis_hj_checkBox)
	private CheckBox yisHjCheckBox;
	/**
	 * 意识 意识模糊
	 */
	@ViewInject(R.id.yis_ysmh_checkBox)
	private CheckBox yisYsmhCheckBox;
	/**
	 * 意识 浅昏迷
	 */
	@ViewInject(R.id.yis_qhm_checkBox)
	private CheckBox yisQhmCheckBox;
	/**
	 * 意识 深昏迷
	 */
	@ViewInject(R.id.yis_shm_checkBox)
	private CheckBox yisShmCheckBox;
	/**
	 * 意识 植物人
	 */
	@ViewInject(R.id.yis_zwr_checkBox)
	private CheckBox yisZwrCheckBox;
	/**
	 * 意识 备注
	 */
	@ViewInject(R.id.yis_bz_editText)
	private EditText yisBzEditText;	
	/**
	 * 心理 正常
	 */
	@ViewInject(R.id.xli_zc_checkBox)
	private CheckBox xliZcCheckBox;
	/**
	 * 心理 焦虑
	 */
	@ViewInject(R.id.xli_jl_checkBox)
	private CheckBox xliJlCheckBox;
	/**
	 * 心理 抑郁
	 */
	@ViewInject(R.id.xli_yiy_checkBox)
	private CheckBox xliYiyCheckBox;
	/**
	 * 心理 无法评估
	 */
	@ViewInject(R.id.xli_wfpg_checkBox)
	private CheckBox xliWfpgCheckBox;
	/**
	 * 心理 多疑
	 */
	@ViewInject(R.id.xli_dy_checkBox)
	private CheckBox xliDyCheckBox;
	/**
	 * 心理 易激动
	 */
	@ViewInject(R.id.xli_yjd_checkBox)
	private CheckBox xliYjdCheckBox;
	/**
	 * 心理 原因
	 */
	@ViewInject(R.id.xli_yy_editText)
	private EditText xliYyEditText;
	/**
	 * 心理 是否治疗
	 */
	@ViewInject(R.id.xli_yongyao_editText)
	private EditText xliYongyaoEditText;
	/**
	 * 心理 影响
	 */
	@ViewInject(R.id.xli_yingxia_editText)
	private EditText xliYingxiaEditText;
	/**
	 * 个人卫生 正常
	 */
	@ViewInject(R.id.grws_zc_checkBox)
	private CheckBox grwsZcCheckBox;
	/**
	 * 个人卫生 头发脏
	 */
	@ViewInject(R.id.grws_tfz_checkBox)
	private CheckBox grwsTfzCheckBox;
	/**
	 * 个人卫生 手脏
	 */
	@ViewInject(R.id.grws_sz_checkBox)
	private CheckBox grwsSzCheckBox;
	/**
	 * 个人卫生 脚脏
	 */
	@ViewInject(R.id.grws_jz_checkBox)
	private CheckBox grwsJzCheckBox;
	/**
	 * 个人卫生 会阴脏
	 */
	@ViewInject(R.id.grws_hyz_checkBox)
	private CheckBox grwsHyzCheckBox;
	/**
	 * 个人卫生 皮肤脏
	 */
	@ViewInject(R.id.grws_pfz_checkBox)
	private CheckBox grwsPfzCheckBox;
	/**
	 * 个人卫生 指甲长
	 */
	@ViewInject(R.id.grws_zjc_checkBox)
	private CheckBox grwsZjcCheckBox;
	/**
	 * 生命体征 血糖 餐前
	 */
	@ViewInject(R.id.xt_zq_checkBox)
	private CheckBox xtZqCheckBox;
	/**
	 * 生命体征 血糖 餐后2H
	 */
	@ViewInject(R.id.xt_zh_checkBox)
	private CheckBox xtZhCheckBox;
	/**
	 * 生命体征 血糖 随机
	 */
	@ViewInject(R.id.xt_sj_checkBox)
	private CheckBox xtSjCheckBox;
	/**
	 * 生命体征 血糖 血糖值
	 */
	@ViewInject(R.id.xt_xtz_editText)
	private EditText xtXtzEditText;
	/**
	 * 生命体征 脉搏 心率正常
	 */
	@ViewInject(R.id.mb_xlzc_checkBox)
	private CheckBox mbXlzcCheckBox;
	/**
	 * 生命体征 脉搏 房颤
	 */
	@ViewInject(R.id.mb_fc_checkBox)
	private CheckBox mbFcCheckBox;
	/**
	 * 生命体征 脉搏 早搏
	 */
	@ViewInject(R.id.mb_zb_checkBox)
	private CheckBox mbZbCheckBox;
	/**
	 * 生命体征 脉搏 心动过缓
	 */
	@ViewInject(R.id.mb_xdgh_checkBox)
	private CheckBox mbXdghCheckBox;
	/**
	 * 生命体征 脉搏 心动过速
	 */
	@ViewInject(R.id.mb_xdgs_checkBox)
	private CheckBox mbXdgsCheckBox;
	/**
	 * 生命体征 脉搏 脉率值
	 */
	@ViewInject(R.id.mb_mbz_editText)
	private EditText mbMbzEditText;
	/**
	 * 生命体征 体温
	 */
	@ViewInject(R.id.tw_editText)
	private EditText twEditText;
	/**
	 * 呼吸 呼吸困难
	 */
	@ViewInject(R.id.hx_hykn_checkBox)
	private CheckBox hxHyknCheckBox;
	/**
	 * 呼吸 胸式呼吸
	 */
	@ViewInject(R.id.hx_xshx_checkBox)
	private CheckBox hxXshxCheckBox;
	/**
	 * 呼吸 腹式呼吸
	 */
	@ViewInject(R.id.hx_fshx_checkBox)
	private CheckBox hxFshxCheckBox;
	/**
	 * 呼吸 呼吸次数
	 */
	@ViewInject(R.id.hx_hxz_editText)
	private EditText hxHxzEditText;
	/**
	 * 呼吸 正常
	 */
	@ViewInject(R.id.hx_zc_checkBox)
	private CheckBox hxZcCheckBox;
	/**
	 * 呼吸 有痰易咳出
	 */
	@ViewInject(R.id.hx_ydkc_checkBox)
	private CheckBox hxYdkcCheckBox;
	/**
	 * 呼吸 有痰不易咳出
	 */
	@ViewInject(R.id.hx_ydbyk_checkBox)
	private CheckBox hxYdbykCheckBox;
	/**
	 * 呼吸 干咳
	 */
	@ViewInject(R.id.hx_gk_checkBox)
	private CheckBox hxGkCheckBox;
	/**
	 * 血压 右侧
	 */
	@ViewInject(R.id.xy_yc_checkBox)
	private CheckBox xyYcCheckBox;
	/**
	 * 血压 左侧
	 */
	@ViewInject(R.id.xy_zc_checkBox)
	private CheckBox xyZcCheckBox;
	/**
	 * 血压 立位
	 */
	@ViewInject(R.id.xy_lw_checkBox)
	private CheckBox xyLwCheckBox;
	/**
	 * 血压 坐位
	 */
	@ViewInject(R.id.xy_zw_checkBox)
	private CheckBox xyZwCheckBox;
	/**
	 * 血压 卧位
	 */
	@ViewInject(R.id.xy_ww_checkBox)
	private CheckBox xyWwCheckBox;
	/**
	 * 血压 收缩压
	 */
	@ViewInject(R.id.xy_ssyz_editText)
	private EditText xySsyzEditText;
	/**
	 * 血压 舒张压
	 */
	@ViewInject(R.id.xy_szyz_editText)
	private EditText xySzyzEditText;
	/**
	 * 面容 贫血貌
	 */
	@ViewInject(R.id.mr_pxm_checkBox)
	private CheckBox mrPxmCheckBox;
	/**
	 * 面容 慢性病
	 */
	@ViewInject(R.id.mr_mxb_checkBox)
	private CheckBox mrMxbCheckBox;
	/**
	 * 面容 正常
	 */
	@ViewInject(R.id.mr_zc_checkBox)
	private CheckBox mrZcCheckBox;	
	/**
	 * 水肿 其他部位
	 */
	@ViewInject(R.id.sz_qt_editText)
	private EditText szQtEditText;
	/**
	 * 水肿 手
	 */
	@ViewInject(R.id.sz_s_checkBox)
	private CheckBox szSCheckBox;
	/**
	 * 水肿 骶尾
	 */
	@ViewInject(R.id.sz_dw_checkBox)
	private CheckBox szDwCheckBox;
	/**
	 * 水肿 下肢
	 */
	@ViewInject(R.id.sz_xz_checkBox)
	private CheckBox szXzCheckBox;
	/**
	 * 水肿 足踝
	 */
	@ViewInject(R.id.sz_zl_checkBox)
	private CheckBox szZlCheckBox;
	/**
	 * 水肿 眼睑
	 */
	@ViewInject(R.id.sz_yj_checkBox)
	private CheckBox szYjCheckBox;
	/**
	 * 水肿 无
	 */
	@ViewInject(R.id.sz_w_checkBox)
	private CheckBox szWCheckBox;
	/**
	 * 压疮 有
	 */
	@ViewInject(R.id.yc_y_checkBox)
	private CheckBox ycYCheckBox;
	/**
	 * 压疮 无
	 */
	@ViewInject(R.id.yc_w_checkBox)
	private CheckBox ycWCheckBox;
	/**
	 * 压疮 渗出情况
	 */
	@ViewInject(R.id.yc_shen_editText)
	private EditText ycShenEditText;
	/**
	 * 压疮 大小
	 */
	@ViewInject(R.id.yc_dy_editText)
	private EditText ycDyEditText;
	/**
	 * 压疮 部位
	 */
	@ViewInject(R.id.yc_bw_editText)
	private EditText ycBwEditText;
	/**
	 * 压红 有
	 */
	@ViewInject(R.id.yh_y_checkBox)
	private CheckBox yhYCheckBox;
	/**
	 * 压红 无
	 */
	@ViewInject(R.id.yh_w_checkBox)
	private CheckBox yhWCheckBox;
	/**
	 * 压红 大小
	 */
	@ViewInject(R.id.yh_da_editText)
	private EditText yhDaEditText;
	/**
	 * 压红 部位
	 */
	@ViewInject(R.id.yh_bu_editText)
	private EditText yhBuEditText;
	/**
	 * 胸部 正常
	 */
	@ViewInject(R.id.xb_zc_checkBox)
	private CheckBox xbZcCheckBox;
	/**
	 * 胸部 变形
	 */
	@ViewInject(R.id.xb_bx_checkBox)
	private CheckBox xbBxCheckBox;
	/**
	 * 胸部 其他
	 */
	@ViewInject(R.id.xb_xbqt_editText)
	private EditText xbQtEditText;
	/**
	 * 腹部 正常
	 */
	@ViewInject(R.id.fb_zc_checkBox)
	private CheckBox fbZcCheckBox;
	/**
	 * 腹部 隆起
	 */
	@ViewInject(R.id.fb_lq_checkBox)
	private CheckBox fbLqCheckBox;
	/**
	 * 腹部 舟状腹
	 */
	@ViewInject(R.id.fb_zzf_checkBox)
	private CheckBox fbZzfCheckBox;
	/**
	 * 腹部 腹水
	 */
	@ViewInject(R.id.fb_fs_checkBox)
	private CheckBox fbFsCheckBox;
	/**
	 * 腹部 腹胀
	 */
	@ViewInject(R.id.fb_fz_checkBox)
	private CheckBox fbFzCheckBox;
	/**
	 * 腹部 腹痛
	 */
	@ViewInject(R.id.fb_ft_checkBox)
	private CheckBox fbFtCheckBox;
	/**
	 * 腹部 其它
	 */
	@ViewInject(R.id.fb_fbqt_editText)
	private EditText fbQtEditText;

	/**
	 * 提交按钮
	 */
	@ViewInject(R.id.drug_submit_button)
	private Button drugSubmitButton;
	/**
	 * 保存按钮
	 */
	@ViewInject(R.id.drug_save_button)
	private Button drugSaveButton;
	
	private String ys="";//饮食
	private String sm="";//睡眠
	private String pb="";//排便
	private String pn="";//排尿
	private String sl="";//视力
	private String tl="";//听力
	private String yy = "";//语言
	private String yis = "";//意识
	private String xli = "";//心理
	private String grws = "";//个人卫生
	private String xt="";//生命体征   血糖
	private String mb="";//生命体征   脉搏
	private String hx="";//生命体征 呼吸
	private String xy="";//生命体征 血压
	private String twz = "";//生命体征 体温
	private String mr="";//面容
	private String sz = "";//水肿
	private String yc = "";//压疮
	private String yh = "";//压红
	private String xb = "";//胸部
	private String fb = "";//腹部

	private Customer customer;
	private String employeeName;
	private String employeeId;
	private Dialog dialog;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INITDATADB:// 回写本地数据
				DrugState drugStateDB = (DrugState) msg.obj;
				initData(drugStateDB);
				break;
			case INITDATANET:// 回写网络
				DrugState drugStateNet = (DrugState) msg.obj;
				PinetreeDB pinetreeDB = PinetreeDB.getInstance(DrugStateActivity.this);
				pinetreeDB.deleteDrugStateByScheID(customer.getId());
				drugStateNet.setScheID(customer.getId());
				pinetreeDB.saveDrugStateByScheID(drugStateNet);
				initData(drugStateNet);
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
		setContentView(R.layout.activity_drug_state);

		ViewUtils.inject(this);
		
		Bundle bundle = getIntent().getExtras();
		customer = (Customer) bundle.getSerializable("customer");
		employeeName = bundle.getString("employeeName");
		employeeId = bundle.getString("employeeId");
		
		dialog = new Dialog(DrugStateActivity.this, R.style.dialog_fullscreen);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCanceledOnTouchOutside(false);
		
		//判断 如果已经提交过 不可再提交
		if (null != customer.getReportStatus() && !"".equals(customer.getReportStatus()) && "1".equals(customer.getReportStatus())) {
			drugSubmitButton.setVisibility(View.GONE);
			drugSaveButton.setVisibility(View.GONE);
		}
		
		
		backDrugImageView.setOnClickListener(this);
		drugSubmitButton.setOnClickListener(this);	
		drugSaveButton.setOnClickListener(this);
		
		final PinetreeDB pinetreeDB = PinetreeDB.getInstance(DrugStateActivity.this);
		if (!"1".equals(customer.getProdType())) {
			//不为评估产品时
			drugSubmitButton.setVisibility(View.GONE);
			drugSaveButton.setVisibility(View.GONE);
		}
		if (NetUtil.checkNetWork(DrugStateActivity.this)) {
			dialog.show();
			RequestParams params  = new RequestParams();
			params.addBodyParameter("scheID", customer.getId());
			params.addBodyParameter("custID", customer.getCustID());
			params.addBodyParameter("num", "1");
			params.addBodyParameter("prodType", customer.getProdType());
			HttpUtil.post("baseAndTestView.action", params, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					dialog.hide();
					
					DrugStateBase drugStateBase = GsonUtils.json2bean(responseInfo.result, DrugStateBase.class);
					DrugState drugStateDB = pinetreeDB.getDrugStateByScheID(customer.getId());
					Message message = Message.obtain();
					if (!"".equals(drugStateBase.getSuccess()) && Boolean.valueOf(drugStateBase.getSuccess())) {
						if (!"".equals(drugStateDB) && null != drugStateDB) {
							if (!"".equals(drugStateDB.getCreateTime()) && !"".equals(drugStateBase.getResultData().get(0).getCreateTime())) {
								
								try {
									String createTimeDB = drugStateDB.getCreateTime();
									String createTimeNet = drugStateBase.getResultData().get(0).getCreateTime();
									String timeDB = createTimeDB.substring(0, 10)+ " "+ createTimeDB.substring(11, 19);
									String timeNet = createTimeNet.substring(0, 10)+ " "+ createTimeNet.substring(11, 19);
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date dbDate = format.parse(timeDB);
									Date netDate = format.parse(timeNet);
									if (dbDate.compareTo(netDate) > 0 ) {
										message.obj = drugStateDB;
										message.what = INITDATADB;
										handler.sendMessage(message);
									} else {
										message.obj = drugStateBase
												.getResultData().get(0);
										message.what = INITDATANET;
										handler.sendMessage(message);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
															
							}else if ("".equals(drugStateBase.getResultData().get(0).getCreateTime())) {
								message.obj = drugStateDB;
								message.what = INITDATADB;
								handler.sendMessage(message);
							}else if ("".equals(drugStateDB.getCreateTime())) {
								message.obj = drugStateBase
										.getResultData().get(0);
								message.what = INITDATANET;
								handler.sendMessage(message);
							}
						}else {
							message.obj = drugStateBase
									.getResultData().get(0);
							message.what = INITDATANET;
							handler.sendMessage(message);
						}
						
					}else {
						if (!"".equals(drugStateDB) && null != drugStateDB) {
							initData(drugStateDB);
						}	
					}						
				}
				
				@Override
				public void onFailure(HttpException error, String msg) {
					dialog.hide();
					
				}
			});
				
		}else {
			DrugState drugStateDB = pinetreeDB.getDrugStateByScheID(customer.getId());
			if (!"".equals(drugStateDB) && null != drugStateDB) {
				initData(drugStateDB);
			}
		}
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_drug_imageView:
			finish();
			break;
		case R.id.drug_submit_button:	//提交					
			dialog.show();
			PinetreeDB pinetreeDB = PinetreeDB.getInstance(DrugStateActivity.this);
			RecordSubmitState recordSubmitStateSubmit = pinetreeDB.getRecordSubmitState(customer.getId());
			if (null != recordSubmitStateSubmit &&  !"".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getBasicSubmitState())) {
				if (NetUtil.checkNetWork(DrugStateActivity.this)) {
					getElementData();
					RequestParams params = new RequestParams();
					params.addBodyParameter("scheID", customer.getId());			
					params.addBodyParameter("custID",customer.getCustID());
					params.addBodyParameter("empID",employeeId);
					params.addBodyParameter("dictionary.ys", ys);
					params.addBodyParameter("dictionary.sm", sm);
					params.addBodyParameter("dictionary.pb", pb);
					params.addBodyParameter("dictionary.pn", pn);
					params.addBodyParameter("dictionary.sl", sl);
					params.addBodyParameter("dictionary.tl", tl);
					params.addBodyParameter("dictionary.yy", yy);
					params.addBodyParameter("dictionary.yis", yis);
					params.addBodyParameter("dictionary.xli", xli);
					params.addBodyParameter("dictionary.grws", grws);
					params.addBodyParameter("dictionary.xt", xt);
					params.addBodyParameter("dictionary.mb", mb);
					params.addBodyParameter("dictionary.hx", hx);
					params.addBodyParameter("dictionary.xy", xy);
					params.addBodyParameter("dictionary.mr", mr);				
					params.addBodyParameter("dictionary.sz", sz);
					params.addBodyParameter("dictionary.yc", yc);
					params.addBodyParameter("dictionary.yh", yh);
					params.addBodyParameter("dictionary.xb", xb);				
					params.addBodyParameter("dictionary.fb", fb);
					params.addBodyParameter("dictionary.tww", twz);
					params.addBodyParameter("num", "1");
					
					if (null!=recordSubmitStateSubmit&&!"".equals(recordSubmitStateSubmit)) {
						if ("1".equals(recordSubmitStateSubmit.getBasicSubmitState()) && "1".equals(recordSubmitStateSubmit.getEverydaySubmitState()) && "1".equals(recordSubmitStateSubmit.getBrainSubmitState()) && "1".equals(recordSubmitStateSubmit.getSportSubmitState()) && "1".equals(recordSubmitStateSubmit.getCopmSubmitState())) {
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
								PinetreeDB pinetreeDB = PinetreeDB.getInstance(DrugStateActivity.this);
								pinetreeDB.deleteDrugStateByScheID(customer.getId());
								DrugState drugState = setData();
								pinetreeDB.saveDrugStateByScheID(drugState);
								
								if (null != pinetreeDB.getRecordSubmitState(customer.getId())) {
									pinetreeDB.updateRecordSubmitStateDrug(customer.getId());
								}else {
									RecordSubmitState recordSubmitStateDB = new RecordSubmitState();
									recordSubmitStateDB.setScheID(customer.getId());
									recordSubmitStateDB.setDrugSubmitState("1");
									pinetreeDB.setRecordSubmitState(recordSubmitStateDB);
								} 
							
								ToastUtils.showToast(DrugStateActivity.this, "提交成功");
								drugSubmitButton.setVisibility(View.GONE);
								drugSaveButton.setVisibility(View.GONE);
								
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
								ToastUtils.showToast(DrugStateActivity.this, "提交失败，请重试！");
							}
							
						}
						
						@Override
						public void onFailure(HttpException error, String msg) {
							clearVariable();
							ToastUtils.showToast(DrugStateActivity.this, "提交失败，请重试！");
							dialog.hide();						
						}
					});
								
				}else {
					dialog.hide();
					ToastUtils.showToast(DrugStateActivity.this, "亲，没有网络哦");
				}
			}else {
				dialog.hide();	
				ToastUtils.showToast(DrugStateActivity.this, "亲，请先填写并提交基本情况表");
			}
			break;			
		case R.id.drug_save_button://保存按钮
			PinetreeDB pinetreeDB1 = PinetreeDB.getInstance(DrugStateActivity.this);
			pinetreeDB1.deleteDrugStateByScheID(customer.getId());
			getElementData();
			DrugState drugState = setData();
			boolean b = pinetreeDB1.saveDrugStateByScheID(drugState);
			if (b) {
				ToastUtils.showToast(DrugStateActivity.this, "保存成功");
			} else {
				ToastUtils.showToast(DrugStateActivity.this, "保存失败，请重试");
			}			
			
			break;
		default:
			break;
		}

	}


	private DrugState setData() {
		DrugState drugState = new DrugState();
		drugState.setScheID(customer.getId());
		drugState.setCustID(customer.getCustID());
		drugState.setYs(ys);
		drugState.setSm(sm);
		drugState.setPb(pb);
		drugState.setPn(pn);
		drugState.setSl(sl);
		drugState.setTl(tl);
		drugState.setYy(yy);
		drugState.setYis(yis);
		drugState.setXli(xli);
		drugState.setGrws(grws);
		drugState.setXt(xt);
		drugState.setMb(mb);
		drugState.setHx(hx);
		drugState.setXy(xy);
		drugState.setTww(twz);
		drugState.setMr(mr);
		drugState.setSz(sz);
		drugState.setYc(yc);
		drugState.setYh(yh);
		drugState.setXb(xb);
		drugState.setFb(fb);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String createDate = dateFormat.format(date);
		String createTime = timeFormat.format(date);
		drugState.setCreateTime(createDate + "T" + createTime);
		return drugState;
	}
	/**
	 * 获取元素组件值
	 */
	private void getElementData(){
		clearVariable();
		//饮食
		if (ysZcCheckBox.isChecked()) {
			ys += "zc|";
		}
		if (ysPdCheckBox.isChecked()) {
			ys += "pd|";
		}
		if (ysPsCheckBox.isChecked()) {
			ys += "ps|";
		}
		if (ysQkCheckBox.isChecked()) {
			ys += "qk|";
		}
		if (ysBcCheckBox.isChecked()) {
			ys += "bs|";
		}
		if (ysWsCheckBox.isChecked()) {
			ys += "ws|";
		}
		if (ysYsCheckBox.isChecked()) {
			ys += "ys|";
		}
		if (ysSybzCheckBox.isChecked()) {
			ys += "sybz|";
		}
		if (ysJsknCheckBox.isChecked()) {
			ys += "jskn|";
		}
		if (!"".equals(ysZsEditText.getText().toString().trim())) {
			ys += "zs:"+ysZsEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(ysScEditText.getText().toString().trim())) {
			ys += "sc:"+ysScEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(ysDbzEditText.getText().toString().trim())) {
			ys += "dbz:"+ysDbzEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(ysYisEditText.getText().toString().trim())) {
			ys += "yins:"+ysYisEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(ys)) {
			ys=ys.substring(0, ys.length() - 1);
			Log.v("TAG","getElementData   ys=     " + ys );
		}
		
		//睡眠
		if (smZcCheckBox.isChecked()) {
			sm += "zc|";
		}
		if (smSmCheckBox.isChecked()) {
			sm += "sm|";
		}
		if (smDmCheckBox.isChecked()) {
			sm += "dm|";
		}
		if (smHbddCheckBox.isChecked()) {
			sm += "hbdd|";
		}
		if (!"".equals(smYyEditText.getText().toString().trim())) {
			sm += "y:"+smYyEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(smSfzlEditText.getText().toString().trim())) {
			sm += "s:"+smSfzlEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(smYxEditText.getText().toString().trim())) {
			sm += "yi:"+smYxEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(smQtEditText.getText().toString().trim())) {
			sm += "smqt:"+smQtEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(sm)) {
			sm=sm.substring(0, sm.length() - 1);
			Log.v("TAG","getElementData   sm=     " + sm );
		}
		
		//排便
		if (!"".equals(pbQkbsEditText.getText().toString().trim())) {
			pb += "q:"+pbQkbsEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(pbYyEditText.getText().toString().trim())) {
			pb += "yu:"+pbYyEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(pbSfzlEditText.getText().toString().trim())) {
			pb += "yo:"+pbSfzlEditText.getText().toString().trim()+"|";
		}
		if (pbBmfxCheckBox.isChecked()) {
			pb += "bmyfxjt|";
		}
		if (!"".equals(pb)) {
			pb=pb.substring(0, pb.length() - 1);
			Log.v("TAG","getElementData   pb=     " + pb );
		}
		
		//排尿
		if (pnZcCheckBox.isChecked()) {
			pn += "zc|";
		}
		if (pnBcCheckBox.isChecked()) {
			pn += "bc|";
		}
		if (pnSjCheckBox.isChecked()) {
			pn += "sj|";
		}
		if (pnTtCheckBox.isChecked()) {
			pn += "tt|";
		}
		if (pnNpCheckBox.isChecked()) {
			pn += "np|";
		}
		if (pnLzdnCheckBox.isChecked()) {
			pn += "lzdn|";
		}
		if (pnNzlCheckBox.isChecked()) {
			pn += "nzl|";
		}
		if (pnYlxnsjCheckBox.isChecked()) {
			pn += "ylxnsj|";
		}
		if (!"".equals(pnYyEditText.getText().toString().trim())) {
			pn += "yua:"+pnYyEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(pnSfzlEditText.getText().toString())) {
			pn += "yon:"+pnSfzlEditText.getText().toString()+"|";
		}
		if (!"".equals(pnYxEditText.getText().toString().trim())) {
			pn += "yin:"+pnYxEditText.getText().toString().trim()+"|";
		}		
		if (!"".equals(pn)) {
			pn=pn.substring(0, pn.length() - 1);
			Log.v("TAG","getElementData   pn=     " + pn );
		}
		
		//视力
		if (slZcCheckBox.isChecked()) {
			sl += "zc|";
		}
		if (slLhCheckBox.isChecked()) {
			sl += "lh|";
		}
		if (slBnzCheckBox.isChecked()) {
			sl += "bnz|";
		}
		if (slSwbqCheckBox.isChecked()) {
			sl += "swbq|";
		}
		if (slZsmCheckBox.isChecked()) {
			sl += "zsm|";
		}
		if (slYsmCheckBox.isChecked()) {
			sl += "ysm|";
		}
		if (slJzhzcCheckBox.isChecked()) {
			sl += "jzhzc|";
		}
		if (slZjsCheckBox.isChecked()) {
			sl += "zjs|";
		}
		if (slYjsCheckBox.isChecked()) {
			sl += "yjs|";
		}
		if (!"".equals(slYyEditText.getText().toString().trim())) {
			sl += "yuan:"+slYyEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(slSfzlEditText.getText().toString().trim())) {
			sl += "yong:"+slSfzlEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(slYxEditText.getText().toString().trim())) {
			sl += "ying:"+slYxEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(sl)) {
			sl=sl.substring(0, sl.length() - 1);
			Log.v("TAG","getElementData   sl=     " + sl );
		}
		
		//听力
		if (tlZcCheckBox.isChecked()) {
			tl += "zc|";
		}
		if (tlEbCheckBox.isChecked()) {
			tl += "eb|";
		}
		if (tlElCheckBox.isChecked()) {
			tl += "el|";
		}
		if (tlEmCheckBox.isChecked()) {
			tl += "em|";
		}
		if (tlEkywCheckBox.isChecked()) {
			tl += "ekyw|";
		}
		if (tlEgCheckBox.isChecked()) {
			tl += "eg|";
		}
		if (tlZztqCheckBox.isChecked()) {
			tl += "zztq|";
		}
		if (tlYztqCheckBox.isChecked()) {
			tl += "yztq|";
		}
		if (!"".equals(tlYyEditText.getText().toString().trim())) {
			tl += "yuany:"+tlYyEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(tlSfzlEditText.getText().toString().trim())) {
			tl += "yongy:"+tlSfzlEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(tlYxEditText.getText().toString().trim())) {
			tl += "yingx:"+tlYxEditText.getText().toString().trim()+"|";
		}
		if (!"".equals(tl)) {
			tl=tl.substring(0, tl.length() - 1);
			Log.v("TAG","getElementData   tl=     " + tl );
		}
				
		//语言
		if (yyZcCheckBox.isChecked()) {
			yy += "zc|";
		}
		if (yyBqCheckBox.isChecked()) {
			yy += "bq|";
		}
		if (yySyCheckBox.isChecked()) {
			yy += "sy|";
		}
		if (yySyinCheckBox.isChecked()) {
			yy += "syin|";
		}
		if (yyDyCheckBox.isChecked()) {
			yy += "dy|";
		}
		if (yyLyCheckBox.isChecked()) {
			yy += "ly|";
		}
		if (yyLyanCheckBox.isChecked()){
			yy += "lyan|";
		}
		if (!"".equals(yyYyEditText.getText().toString().trim())) {
			yy += "yuanyi:"+yyYyEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yySfzlEditText.getText().toString().trim())) {
			yy += "yongya:" + yySfzlEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yyYxEditText.getText().toString().trim())) {
			yy += "yingxi:" + yyYxEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yy)) {
			yy = yy.substring(0, yy.length() - 1);
			Log.v("TAG","getElementData   yy=     " + yy );
		}
				
		//意识
		if (yisZcCheckBox.isChecked()) {
			yis += "zc|";
		}
		if (yisSsCheckBox.isChecked()) {
			yis += "ss|";
		}
		if (yisZwCheckBox.isChecked()) {
			yis += "zw|";
		}
		if (yisDmCheckBox.isChecked()) {
			yis += "dm|";
		}
		if (yisHjCheckBox.isChecked()) {
			yis += "hj|";
		}
		if (yisYsmhCheckBox.isChecked()) {
			yis += "ysmh|";
		}
		if (yisQhmCheckBox.isChecked()) {
			yis += "qhm|";
		}
		if (yisShmCheckBox.isChecked()) {
			yis += "shmi|";
		}
		if (yisZwrCheckBox.isChecked()){
			yis += "zwr|";
		}
		if (!"".equals(yisBzEditText.getText().toString().trim())) {
			yis += "bz:" + yisBzEditText.getText().toString().trim()  + "|";
		}
		if (!"".equals(yis)) {
			yis = yis.substring(0, yis.length() - 1);
			Log.v("TAG","getElementData   yis=     " + yis );
		}
				
		//心理
		if (xliZcCheckBox.isChecked()) {
			xli += "zc|";
		}
		if (xliJlCheckBox.isChecked()) {
			xli += "jl|";
		}
		if (xliYiyCheckBox.isChecked()) {
			xli += "yiy|";
		}
		if (xliWfpgCheckBox.isChecked()) {
			xli += "wfpg|";
		}
		if (xliDyCheckBox.isChecked()) {
			xli += "dy|";
		}
		if (xliYjdCheckBox.isChecked()) {
			xli += "yjd|";
		}
		if (!"".equals(xliYyEditText.getText().toString().trim())) {
			xli += "yuanyin:" + xliYyEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xliYongyaoEditText.getText().toString().trim())) {
			xli += "yongyao:" + xliYongyaoEditText.getText().toString().trim()  + "|";
		}
		if (!"".equals(xliYingxiaEditText.getText().toString().trim())) {
			xli += "yingxia:" + xliYingxiaEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xli)) {
			xli = xli.substring(0, xli.length() - 1);
			Log.v("TAG","getElementData   xli=     " + xli );
		}
		
		//个人卫生
		if (grwsZcCheckBox.isChecked()) {
			grws += "zc|";
		}
		if (grwsTfzCheckBox.isChecked()) {
			grws += "tfz|";
		}
		if (grwsSzCheckBox.isChecked()) {
			grws += "sz|";
		}
		if (grwsJzCheckBox.isChecked()) {
			grws += "jz|";
		}
		if (grwsHyzCheckBox.isChecked()) {
			grws += "hyz|";
		}
		if (grwsPfzCheckBox.isChecked()) {
			grws += "pfz|";
		}
		if (grwsZjcCheckBox.isChecked()) {
			grws += "zjc|";
		}
		if (!"".equals(grws)) {
			grws = grws.substring(0, grws.length() - 1);
			Log.v("TAG","getElementData   grws=     " + grws );
		}
		
		//11  生命体征  血糖
		if (xtZqCheckBox.isChecked()) {
			xt += "zq|";
		}
		if (xtZhCheckBox.isChecked()) {
			xt += "zh|";
		}
		if (xtSjCheckBox.isChecked()) {
			xt += "sj|";
		}
		if (!"".equals(xtXtzEditText.getText().toString().trim())) {
			xt += "xtz:" + xtXtzEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xt)) {
			xt=xt.substring(0, xt.length() - 1);
			Log.v("TAG","getElementData   xt=     " + xt );
		}
		
		//11 生命体征 脉搏
		if (mbXlzcCheckBox.isChecked()) {
			mb += "xlzc|";
		}
		if (mbFcCheckBox.isChecked()) {
			mb += "fc|";
		}
		if (mbZbCheckBox.isChecked()) {
			mb += "zb|";
		}
		if (mbXdghCheckBox.isChecked()) {
			mb += "xdgh|";
		}
		if (mbXdgsCheckBox.isChecked()) {
			mb += "xdgs|";
		}
		if (!"".equals(mbMbzEditText.getText().toString().trim())) {
			mb += "mbz:" + mbMbzEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(mb)) {
			mb=mb.substring(0, mb.length() - 1);
			Log.v("TAG","getElementData   mb=     " + mb );
		}
		
		//生命体征  呼吸
		if (hxHyknCheckBox.isChecked()) {
			hx += "hykn|";
		}
		if (hxXshxCheckBox.isChecked()) {
			hx += "xshx|";
		}
		if (hxFshxCheckBox.isChecked()) {
			hx += "fshx|";
		}
		if (hxZcCheckBox.isChecked()) {
			hx += "zc|";
		}
		if (hxYdkcCheckBox.isChecked()) {
			hx += "ydkc|";
		}
		if (hxYdbykCheckBox.isChecked()) {
			hx += "ydbyk|";
		}
		if (hxGkCheckBox.isChecked()) {
			hx += "gk|";
		}
		if (!"".equals(hxHxzEditText.getText().toString().trim())) {
			hx += "hxz:" + hxHxzEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(hx)) {
			hx=hx.substring(0, hx.length() - 1);
			Log.v("TAG","getElementData   hx=     " + hx );
		}
		
		//生命体征  血压
		if (xyYcCheckBox.isChecked()) {
			xy += "yc|";
		}
		if (xyZcCheckBox.isChecked()) {
			xy += "zc|";
		}
		if (xyLwCheckBox.isChecked()) {
			xy += "lw|";
		}
		if (xyZwCheckBox.isChecked()) {
			xy += "zw|";
		}
		if (xyWwCheckBox.isChecked()) {
			xy += "ww|";
		}
		if (!"".equals(xySsyzEditText.getText().toString().trim())) {
			xy += "ssyz:" + xySsyzEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xySzyzEditText.getText().toString().trim())) {
			xy += "szyz:" + xySzyzEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xy)) {
			xy=xy.substring(0, xy.length() - 1);
			Log.v("TAG","getElementData   xy=     " + xy );
		}
		
		//生命体征  体温
		if (!"".equals(twEditText.getText().toString().trim())) {
			twz = "twwz:" + twEditText.getText().toString().trim();
		}
		
		//面容
		if (mrPxmCheckBox.isChecked()) {
			mr += "pxm|";
		}
		if (mrMxbCheckBox.isChecked()) {
			mr += "mxb|";
		}
		if (mrZcCheckBox.isChecked()) {
			mr += "zc|";
		}
		if (!"".equals(mr)) {
			mr=mr.substring(0, mr.length() - 1);
		}
		//水肿
		if (szWCheckBox.isChecked()) {
			sz += "w|";
		}
		if (szDwCheckBox.isChecked()) {
			sz += "dw|";
		}
		if (szXzCheckBox.isChecked()) {
			sz += "xz|";
		}
		if (szZlCheckBox.isChecked()) {
			sz += "zl|";
		}
		if (szYjCheckBox.isChecked()) {
			sz += "yj|";
		}
		if (szSCheckBox.isChecked()) {
			sz += "s|";
		}
		if (!"".equals(szQtEditText.getText().toString().trim())) {
			sz += "qt:" + szQtEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(sz)) {
			sz = sz.substring(0, sz.length() - 1);
			Log.v("TAG","getElementData   sz=     " + sz );
		}
		
		//压疮
		if (ycWCheckBox.isChecked()) {
			yc += "w|";
		}
		if (ycYCheckBox.isChecked()) {
			yc += "y|";
		}
		if (!"".equals(ycBwEditText.getText().toString().trim())) {
			yc += "bw:" + ycBwEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(ycDyEditText.getText().toString().trim())) {
			yc += "dy:" + ycDyEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(ycShenEditText.getText().toString().trim())) {
			yc += "shen:" + ycShenEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yc)) {
			yc = yc.substring(0, yc.length() - 1);
			Log.v("TAG","getElementData   yc=     " + yc );
		}
	
		//压红
		if (yhWCheckBox.isChecked()) {
			yh += "w|";
		}
		if (yhYCheckBox.isChecked()) {
			yh += "y|";
		}
		if (!"".equals(yhBuEditText.getText().toString().trim())) {
			yh += "bu:" + yhBuEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yhDaEditText.getText().toString().trim())) {
			yh += "da:" + yhDaEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(yh)) {
			yh = yh.substring(0, yh.length() - 1);
			Log.v("TAG","getElementData   yh=     " + yh );
		}
				
		//胸部（胸廓）
		if (xbZcCheckBox.isChecked()) {
			xb += "zc|";
		}
		if (xbBxCheckBox.isChecked()) {
			xb += "bx|";
		}
		if (!"".equals(xbQtEditText.getText().toString().trim())) {
			xb += "xbqt:" + xbQtEditText.getText().toString().trim() + "|";
		}
		if (!"".equals(xb)) {
			xb = xb.substring(0, xb.length() - 1);
			Log.v("TAG","getElementData   xb=     " + xb );
		}
		
		//腹部
		if (fbZcCheckBox.isChecked()) {
			fb += "zc|";
		}
		if (fbLqCheckBox.isChecked()) {
			fb += "lq|";
		}
		if (fbZzfCheckBox.isChecked()) {
			fb += "zzf|";
		}
		if (fbFsCheckBox.isChecked()) {
			fb += "fs|";
		}
		if (fbFzCheckBox.isChecked()) {
			fb += "fz|";
		}
		if (fbFtCheckBox.isChecked()) {
			fb += "ft|";
		}
		if (!"".equals(fbQtEditText.getText().toString().trim())) {
			fb += "fbqt:" + fbQtEditText.getText().toString().trim() +"|";
		}
		if (!"".equals(fb)) {
			fb = fb.substring(0, fb.length() - 1);
			Log.v("TAG","getElementData   fb=     " + fb );
		}
	}
	
	/**
	 * 回显数据
	 */
	private void initData(DrugState drugState){
		PinetreeDB pinetreeDB = PinetreeDB.getInstance(DrugStateActivity.this);
		RecordSubmitState recordSubmitState2 = pinetreeDB.getRecordSubmitState(customer.getId());
		if (null != recordSubmitState2 && !"".equals(recordSubmitState2)) {
			if (null != recordSubmitState2.getDrugSubmitState() && "1".equals(recordSubmitState2.getDrugSubmitState())) {
				drugSubmitButton.setVisibility(View.GONE);
				drugSaveButton.setVisibility(View.GONE);
			}
		}
		Log.v("TAG","initData   drugState=     " + drugState.toString() );
		if (!"".equals(drugState) && null != drugState) {
			//饮食
			if (!"".equals(drugState.getYs()) && null != drugState.getYs()) {
				if (drugState.getYs().contains("|")) {
					String[] ysArray = drugState.getYs().split("\\|");
					for (String ys : ysArray) {
						setYsString(ys);
					}
				}else {
					setYsString(drugState.getYs());
				}				
			}
			//睡眠
			if (!"".equals(drugState.getSm()) && null != drugState.getSm()) {
				if (drugState.getSm().contains("|")) {
					String[] smArray = drugState.getSm().split("\\|");
					for (String sm : smArray) {
						setSmString(sm);
					}
				}else {
					setSmString(drugState.getSm());
				}				
			}
			//排便
			if (!"".equals(drugState.getPb()) && null != drugState.getPb()) {
				if (drugState.getPb().contains("|")) {
					String[] pbArray = drugState.getPb().split("\\|");
					for (String pb : pbArray) {
						setPbString(pb);
					}
				}else {
					setPbString(drugState.getPb());
				}
			}
			//排尿
			if (!"".equals(drugState.getPn()) && null != drugState.getPn()) {
				if (drugState.getPn().contains("|")) {
					String[] pnArray = drugState.getPn().split("\\|");
					for (String pn : pnArray) {
						setPnString(pn);
					}
				}else {
					setPnString(drugState.getPn());
				}
			}
			//视力
			if (!"".equals(drugState.getSl()) && null != drugState.getSl()) {
				if (drugState.getSl().contains("|")) {
					String[] slArray = drugState.getSl().split("\\|");
					for (String sl : slArray) {
						setSlString(sl);
					}
				}else {
					setSlString(drugState.getSl());
				}
			}
			//听力
			if (!"".equals(drugState.getTl()) && null != drugState.getTl()) {
				if (drugState.getTl().contains("|")) {
					String[] tlArray = drugState.getTl().split("\\|");
					for (String tl : tlArray) {
						setTlString(tl);
					}
				}else {
					setTlString(drugState.getTl());
				}
			}
			//语言
			if (!"".equals(drugState.getYy()) && null != drugState.getYy()) {
				if (drugState.getYy().contains("|")) {
					String[] yyArray = drugState.getYy().split("\\|");
					for (String yy : yyArray) {
						setYyString(yy);
					}
				}else {
					setYyString(drugState.getYy());
				}
			}
			//意识
			if (!"".equals(drugState.getYis()) && null != drugState.getYis()) {
				if (drugState.getYis().contains("|")) {
					String[] yisArray = drugState.getYis().split("\\|");
					for (String yis : yisArray) {
						setYisString(yis);
					}
				}else {
					setYisString(drugState.getYis());
				}
			}
			//心理
			if (!"".equals(drugState.getXli()) && null != drugState.getXli()) {
				if (drugState.getXli().contains("|")) {
					String[] xliArray = drugState.getXli().split("\\|");
					for (String xli : xliArray) {
						setXliString(xli);
					}
				}else {
					setXliString(drugState.getXli());
				}
			}
			//个人卫生
			if (!"".equals(drugState.getGrws()) && null != drugState.getGrws()) {
				if (drugState.getGrws().contains("|")) {
					String[] grwsArray = drugState.getGrws().split("\\|");
					for (String grws : grwsArray) {
						setGrwsString(grws);
					}
				}else {
					setGrwsString(drugState.getGrws());
				}
			}
			//血糖
			if (!"".equals(drugState.getXt()) && null != drugState.getXt()) {
				if (drugState.getXt().contains("|")) {
					String[] xtArray = drugState.getXt().split("\\|");
					for (String xt : xtArray) {
						setXtString(xt);
					}
				}else {
					setXtString(drugState.getXt());
				}
			}
			//脉搏
			if (!"".equals(drugState.getMb()) && null != drugState.getMb()) {
				if (drugState.getMb().contains("|")) {
					String[] mbArray = drugState.getMb().split("\\|");
					for (String mb : mbArray) {
						setMbString(mb);
					}
				}else {
					setMbString(drugState.getMb());
				}
			}
			//呼吸
			if (!"".equals(drugState.getHx()) && null != drugState.getHx()) {
				if (drugState.getHx().contains("|")) {
					String[] hxArray = drugState.getHx().split("\\|");
					for (String hx : hxArray) {
						setHxString(hx);
					}
				}else {
					setHxString(drugState.getHx());
				}
			}
			//血压
			if (!"".equals(drugState.getXy()) && null != drugState.getXy()) {
				if (drugState.getXy().contains("|")) {
					String[] xyArray = drugState.getXy().split("\\|");
					for (String xy : xyArray) {
						setXyString(xy);
					}
				}else {
					setXyString(drugState.getXy());
				}
			}
			//面容
			if (!"".equals(drugState.getMr()) && null != drugState.getMr()) {
				if (drugState.getMr().contains("|")) {
					String[] mrArray = drugState.getMr().split("\\|");
					for (String mr : mrArray) {
						setMrString(mr);
					}
				}else {
					setMrString(drugState.getMr());
				}
			}
			//体温
			if (!"".equals(drugState.getTww()) && null != drugState.getTww()) {
				String tw = drugState.getTww();
				if (!"".equals(tw) && tw.contains(":")) {
					twEditText.setText(tw.split(":")[1]);
				}
			}
			//水肿
			if (!"".equals(drugState.getSz()) && null != drugState.getSz()) {
				if (drugState.getSz().contains("|")) {
					String[] szArray = drugState.getSz().split("\\|");
					for (String sz : szArray) {
						setSzString(sz);
					}
				}else {
					setSzString(drugState.getSz());
				}
			}
			//压疮
			if (!"".equals(drugState.getYc()) && null != drugState.getYc()) {
				if (drugState.getYc().contains("|")) {
					String[] ycArray = drugState.getYc().split("\\|");
					for (String yc : ycArray) {
						setYcString(yc);
					}
				}else {
					setYcString(drugState.getYc());
				}
			}
			//压红
			if (!"".equals(drugState.getYh()) && null != drugState.getYh()) {
				if (drugState.getYh().contains("|")) {
					String[] yhArray = drugState.getYh().split("\\|");
					for (String yh : yhArray) {
						setYhString(yh);
					}
				}else {
					setYhString(drugState.getYh());
				}
			}
			//胸部
			if (!"".equals(drugState.getXb()) && null != drugState.getXb()) {
				if (drugState.getXb().contains("|")) {
					String[] xbArray = drugState.getXb().split("\\|");
					for (String xb : xbArray) {
						setXbString(xb);
					}
				}else {
					setXbString(drugState.getXb());
				}
			}
			//腹部
			if (!"".equals(drugState.getFb()) && null != drugState.getFb()) {
				if (drugState.getFb().contains("|")) {
					String[] fbArray = drugState.getFb().split("\\|");
					for (String fb : fbArray) {
						setFbString(fb);
					}
				}else {
					setFbString(drugState.getFb());
				}
			}
		}
	}
	
	
	/**
	 * 饮食 判断
	 */
	private void setYsString(String ys){
		if ("zc".equals(ys)) {
			ysZcCheckBox.setChecked(true);
		}else if ("pd".equals(ys)) {
			ysPdCheckBox.setChecked(true);
		}else if ("ps".equals(ys)) {
			ysPsCheckBox.setChecked(true);
		}else if ("qk".equals(ys)) {
			ysQkCheckBox.setChecked(true);
		}else if ("bs".equals(ys)) {
			ysBcCheckBox.setChecked(true);
		}else if ("jskn".equals(ys)) {
			ysJsknCheckBox.setChecked(true);
		}else if ("ws".equals(ys)) {
			ysWsCheckBox.setChecked(true);
		}else if ("ys".equals(ys)) {
			ysYsCheckBox.setChecked(true);
		}else if ("sybz".equals(ys)) {
			ysSybzCheckBox.setChecked(true);
		}else if (!"".equals(ys)&&ys.contains(":")) {
			if ("zs".equals(ys.split(":")[0])) {
				ysZsEditText.setText(ys.split(":")[1]);
			}else if ("sc".equals(ys.split(":")[0])) {
				ysScEditText.setText(ys.split(":")[1]);
			}else if ("dbz".equals(ys.split(":")[0])) {
				ysDbzEditText.setText(ys.split(":")[1]);
			}else if ("yins".equals(ys.split(":")[0])) {
				ysYisEditText.setText(ys.split(":")[1]);
			}			
		}
	}
	/**
	 * 睡眠 判断
	 */
	private void setSmString(String sm){
		if ("zc".equals(sm)) {
			smZcCheckBox.setChecked(true);
		}else if ("sm".equals(sm)) {
			smSmCheckBox.setChecked(true);
		}else if ("dm".equals(sm)) {
			smDmCheckBox.setChecked(true);
		}else if ("hbdd".equals(sm)) {
			smHbddCheckBox.setChecked(true);
		}else if (!"".equals(sm)&&sm.contains(":")) {
			if ("y".equals(sm.split(":")[0])) {
				smYyEditText.setText(sm.split(":")[1]);
			}else if ("s".equals(sm.split(":")[0])) {
				smSfzlEditText.setText(sm.split(":")[1]);
			}else if ("yi".equals(sm.split(":")[0])) {
				smYxEditText.setText(sm.split(":")[1]);
			}else if ("smqt".equals(sm.split(":")[0])) {
				smQtEditText.setText(sm.split(":")[1]);
			}			
		}	
	}
	/**
	 * 排便 判断
	 */
	private void setPbString(String pb){
		if (!"".equals(pb)&&pb.contains(":")) {
			if ("q".equals(pb.split(":")[0])) {
				pbQkbsEditText.setText(pb.split(":")[1]);
			}else if ("yu".equals(pb.split(":")[0])) {
				pbYyEditText.setText(pb.split(":")[1]);
			}else if ("yo".equals(pb.split(":")[0])) {
				pbSfzlEditText.setText(pb.split(":")[1]);
			}				
		}else if ("bmyfxjt".equals(pb)) {
			pbBmfxCheckBox.setChecked(true);
		}
	}
	/**
	 * 排尿 判断
	 */
	private void setPnString(String pn){
		if ("zc".equals(pn)) {
			pnZcCheckBox.setChecked(true);
		}else if ("bc".equals(pn)) {
			pnBcCheckBox.setChecked(true);
		}else if ("sj".equals(pn)) {
			pnSjCheckBox.setChecked(true);
		}else if ("tt".equals(pn)) {
			pnTtCheckBox.setChecked(true);
		}else if ("np".equals(pn)) {
			pnNpCheckBox.setChecked(true);
		}else if ("lzdn".equals(pn)) {
			pnLzdnCheckBox.setChecked(true);
		}else if ("nzl".equals(pn)) {
			pnNzlCheckBox.setChecked(true);
		}else if ("ylxnsj".equals(pn)) {
			pnYlxnsjCheckBox.setChecked(true);
		}else if (!"".equals(pn)&&pn.contains(":")) {
			if ("yua".equals(pn.split(":")[0])) {
				pnYyEditText.setText(pn.split(":")[1]);
			}else if ("yon".equals(pn.split(":")[0])) {
				pnSfzlEditText.setText(pn.split(":")[1]);
			}else if ("yin".equals(pn.split(":")[0])) {
				pnYxEditText.setText(pn.split(":")[1]);
			}		
		}
	}
	/**
	 * 视力 判断
	 */
	private void setSlString(String sl){
		if ("zc".equals(sl)) {
			slZcCheckBox.setChecked(true);
		}else if ("lh".equals(sl)) {
			slLhCheckBox.setChecked(true);
		}else if ("bnz".equals(sl)) {
			slBnzCheckBox.setChecked(true);
		}else if ("swbq".equals(sl)) {
			slSwbqCheckBox.setChecked(true);
		}else if ("zsm".equals(sl)) {
			slZsmCheckBox.setChecked(true);
		}else if ("ysm".equals(sl)) {
			slYsmCheckBox.setChecked(true);
		}else if ("jzhzc".equals(sl)) {
			slJzhzcCheckBox.setChecked(true);
		}else if ("zjs".equals(sl)) {
			slZjsCheckBox.setChecked(true);
		}else if ("yjs".equals(sl)) {
			slYjsCheckBox.setChecked(true);
		}else if (!"".equals(sl)&&sl.contains(":")) {
			if ("yuan".equals(sl.split(":")[0])) {
				slYyEditText.setText(sl.split(":")[1]);
			}else if ("yong".equals(sl.split(":")[0])) {
				slSfzlEditText.setText(sl.split(":")[1]);
			}else if ("ying".equals(sl.split(":")[0])) {
				slYxEditText.setText(sl.split(":")[1]);
			}			
		}
	}
	/**
	 * 听力 判断
	 */
	private void setTlString(String tl){
		if ("zc".equals(tl)) {
			tlZcCheckBox.setChecked(true);
		}else if ("eb".equals(tl)) {
			tlEbCheckBox.setChecked(true);
		}else if ("el".equals(tl)) {
			tlElCheckBox.setChecked(true);
		}else if ("em".equals(tl)) {
			tlEmCheckBox.setChecked(true);
		}else if ("eg".equals(tl)) {
			tlEgCheckBox.setChecked(true);
		}else if ("ekyw".equals(tl)) {
			tlEkywCheckBox.setChecked(true);
		}else if ("zztq".equals(tl)) {
			tlZztqCheckBox.setChecked(true);
		}else if ("yztq".equals(tl)) {
			tlYztqCheckBox.setChecked(true);
		}else if (!"".equals(tl)&&tl.contains(":")) {
			if ("yuany".equals(tl.split(":")[0])) {
				tlYyEditText.setText(tl.split(":")[1]);
			}else if ("yongy".equals(tl.split(":")[0])) {
				tlSfzlEditText.setText(tl.split(":")[1]);
			}else if ("yingx".equals(tl.split(":")[0])) {
				tlYxEditText.setText(tl.split(":")[1]);
			}		
		}
	}
	/**
	 * 语言 判断
	 */
	private void setYyString(String yy){
		if ("zc".equals(yy)) {
			yyZcCheckBox.setChecked(true);
		}else if ("bq".equals(yy)) {
			yyBqCheckBox.setChecked(true);
		}else if ("sy".equals(yy)) {
			yySyCheckBox.setChecked(true);
		}else if ("syin".equals(yy)) {
			yySyinCheckBox.setChecked(true);
		}else if ("dy".equals(yy)) {
			yyDyCheckBox.setChecked(true);
		}else if ("ly".equals(yy)) {
			yyLyCheckBox.setChecked(true);
		}else if ("lyan".equals(yy)) {
			yyLyanCheckBox.setChecked(true);
		}else if (!"".equals(yy)&&yy.contains(":")) {
			if ("yuanyi".equals(yy.split(":")[0])) {
				yyYyEditText.setText(yy.split(":")[1]);
			}else if ("yongya".equals(yy.split(":")[0])) {
				yySfzlEditText.setText(yy.split(":")[1]);
			}else if ("yingxi".equals(yy.split(":")[0])) {
				yyYxEditText.setText(yy.split(":")[1]);
			}		
		}
	}
	/**
	 * 意识 判断
	 */
	private void setYisString(String yis){
		if ("zc".equals(yis)) {
			yisZcCheckBox.setChecked(true);
		}else if ("ss".equals(yis)) {
			yisSsCheckBox.setChecked(true);
		}else if ("zw".equals(yis)) {
			yisZwCheckBox.setChecked(true);
		}else if ("dm".equals(yis)) {
			yisDmCheckBox.setChecked(true);
		}else if ("hj".equals(yis)) {
			yisHjCheckBox.setChecked(true);
		}else if ("ysmh".equals(yis)) {
			yisYsmhCheckBox.setChecked(true);
		}else if ("qhm".equals(yis)) {
			yisQhmCheckBox.setChecked(true);
		}else if ("shmi".equals(yis)) {
			yisShmCheckBox.setChecked(true);
		}else if ("zwr".equals(yis)) {
			yisZwrCheckBox.setChecked(true);
		}else if (!"".equals(yis)&&yis.contains(":")) {
			yisBzEditText.setText(yis.split(":")[1]);				
		}
	}
	/**
	 * 心理 判断
	 */
	private void setXliString(String xli){
		if ("zc".equals(xli)) {
			xliZcCheckBox.setChecked(true);
		}else if ("jl".equals(xli)) {
			xliJlCheckBox.setChecked(true);
		}else if ("yiy".equals(xli)) {
			xliYiyCheckBox.setChecked(true);
		}else if ("dy".equals(xli)) {
			xliDyCheckBox.setChecked(true);
		}else if ("wfpg".equals(xli)) {
			xliWfpgCheckBox.setChecked(true);
		}else if ("yjd".equals(xli)) {
			xliYjdCheckBox.setChecked(true);
		}else if (!"".equals(xli)&&xli.contains(":")) {
			if ("yuanyin".equals(xli.split(":")[0])) {
				xliYyEditText.setText(xli.split(":")[1]);
			}else if ("yongyao".equals(xli.split(":")[0])) {
				xliYongyaoEditText.setText(xli.split(":")[1]);
			}else if ("yingxia".equals(xli.split(":")[0])) {
				xliYingxiaEditText.setText(xli.split(":")[1]);
			}		
		}
	}
	/**
	 * 个人卫生 判断
	 */
	private void setGrwsString(String grws){
		if ("zc".equals(grws)) {
			grwsZcCheckBox.setChecked(true);
		}else if ("tfz".equals(grws)) {
			grwsTfzCheckBox.setChecked(true);
		}else if ("sz".equals(grws)) {
			grwsSzCheckBox.setChecked(true);
		}else if ("jz".equals(grws)) {
			grwsJzCheckBox.setChecked(true);
		}else if ("hyz".equals(grws)) {
			grwsHyzCheckBox.setChecked(true);
		}else if ("pfz".equals(grws)) {
			grwsPfzCheckBox.setChecked(true);
		}else if ("zjc".equals(grws)) {
			grwsZjcCheckBox.setChecked(true);
		}
	}
	/**
	 * 血糖 判断
	 */
	private void setXtString(String xt){
		if ("zq".equals(xt)) {
			xtZqCheckBox.setChecked(true);
		}else if ("zh".equals(xt)) {
			xtZhCheckBox.setChecked(true);
		}else if ("sj".equals(xt)) {
			xtSjCheckBox.setChecked(true);
		}else if (!"".equals(xt)&&xt.contains(":")) {
			xtXtzEditText.setText(xt.split(":")[1]);
		}
	}
	/**
	 * 脉搏 判断
	 */
	private void setMbString(String mb){
		if ("xlzc".equals(mb)) {
			mbXlzcCheckBox.setChecked(true);
		}else if ("fc".equals(mb)) {
			mbFcCheckBox.setChecked(true);
		}else if ("zb".equals(mb)) {
			mbZbCheckBox.setChecked(true);
		}else if ("xdgs".equals(mb)) {
			mbXdgsCheckBox.setChecked(true);
		}else if ("xdgh".equals(mb)) {
			mbXdghCheckBox.setChecked(true);
		}else if (!"".equals(mb)&&mb.contains(":")) {
			mbMbzEditText.setText(mb.split(":")[1]);				
		}
	}
	/**
	 * 呼吸 判断
	 */
	private void setHxString(String hx){
		if ("hykn".equals(hx)) {
			hxHyknCheckBox.setChecked(true);
		}else if ("xshx".equals(hx)) {
			hxXshxCheckBox.setChecked(true);
		}else if ("fshx".equals(hx)) {
			hxFshxCheckBox.setChecked(true);
		}else if ("zc".equals(hx)) {
			hxZcCheckBox.setChecked(true);
		}else if ("ydkc".equals(hx)) {
			hxYdkcCheckBox.setChecked(true);
		}else if ("ydbyk".equals(hx)) {
			hxYdbykCheckBox.setChecked(true);
		}else if ("gk".equals(hx)) {
			hxGkCheckBox.setChecked(true);
		}else if (!"".equals(hx)&&hx.contains(":")) {
			hxHxzEditText.setText(hx.split(":")[1]);				
		}
	}
	/**
	 * 血压 判断
	 */
	private void setXyString(String xy){
		if ("ww".equals(xy)) {
			xyWwCheckBox.setChecked(true);
		}else if ("lw".equals(xy)) {
			xyLwCheckBox.setChecked(true);
		}else if ("zw".equals(xy)) {
			xyZwCheckBox.setChecked(true);
		}else if ("zc".equals(xy)) {
			xyZcCheckBox.setChecked(true);
		}else if ("yc".equals(xy)) {
			xyYcCheckBox.setChecked(true);
		}else if (!"".equals(xy)&&xy.contains(":")) {
			if ("ssyz".equals(xy.split(":")[0])) {
				xySsyzEditText.setText(xy.split(":")[1]);
			}else if ("szyz".equals(xy.split(":")[0])) {
				xySzyzEditText.setText(xy.split(":")[1]);
			}	
		}
	}
	/**
	 * 面容 判断
	 */
	private void setMrString(String mr){
		if ("zc".equals(mr)) {
			mrZcCheckBox.setChecked(true);
		}else if ("mxb".equals(mr)) {
			mrMxbCheckBox.setChecked(true);
		}else if ("pxm".equals(mr)) {
			mrPxmCheckBox.setChecked(true);
		}
	}
	/**
	 * 意识 判断
	 */
	private void setSzString(String sz){
		if ("w".equals(sz)) {
			szWCheckBox.setChecked(true);
		}else if ("dw".equals(sz)) {
			szDwCheckBox.setChecked(true);
		}else if ("xz".equals(sz)) {
			szXzCheckBox.setChecked(true);
		}else if ("zl".equals(sz)) {
			szZlCheckBox.setChecked(true);
		}else if ("yj".equals(sz)) {
			szYjCheckBox.setChecked(true);
		}else if ("s".equals(sz)) {
			szSCheckBox.setChecked(true);
		}else if (!"".equals(sz)&&sz.contains(":")) {
			szQtEditText.setText(sz.split(":")[1]);				
		}
	}
	/**
	 * 压疮 判断
	 */
	private void setYcString(String yc){
		if ("w".equals(yc)) {
			ycWCheckBox.setChecked(true);
		}else if ("y".equals(yc)) {
			ycYCheckBox.setChecked(true);
		}else if (!"".equals(yc)&&yc.contains(":")) {
			if ("bw".equals(yc.split(":")[0])) {
				ycBwEditText.setText(yc.split(":")[1]);
			}else if ("dy".equals(yc.split(":")[0])) {
				ycDyEditText.setText(yc.split(":")[1]);
			}else if ("shen".equals(yc.split(":")[0])) {
				ycShenEditText.setText(yc.split(":")[1]);
			}		
		}
	}
	/**
	 * 压红 判断
	 */
	private void setYhString(String yh){
		if ("w".equals(yh)) {
			yhWCheckBox.setChecked(true);
		}else if ("y".equals(yh)) {
			yhYCheckBox.setChecked(true);
		}else if (!"".equals(yh)&&yh.contains(":")) {
			if ("bu".equals(yh.split(":")[0])) {
				yhBuEditText.setText(yh.split(":")[1]);
			}else if ("da".equals(yh.split(":")[0])) {
				yhDaEditText.setText(yh.split(":")[1]);
			}	
		}
	}
	/**
	 * 胸部 判断
	 */
	private void setXbString(String xb){
		if ("zc".equals(xb)) {
			xbZcCheckBox.setChecked(true);
		}else if ("bx".equals(xb)) {
			xbBxCheckBox.setChecked(true);
		}else if (!"".equals(xb)&&xb.contains(":")) {
			xbQtEditText.setText(xb.split(":")[1]);
		}
	}
	/**
	 * 腹部 判断
	 */
	private void setFbString(String fb){
		if ("zc".equals(fb)) {
			fbZcCheckBox.setChecked(true);
		}else if ("lq".equals(fb)) {
			fbLqCheckBox.setChecked(true);
		}else if ("fz".equals(fb)) {
			fbFzCheckBox.setChecked(true);
		}else if ("fs".equals(fb)) {
			fbFsCheckBox.setChecked(true);
		}else if ("zzf".equals(fb)) {
			fbZzfCheckBox.setChecked(true);
		}else if ("ft".equals(fb)) {
			fbFtCheckBox.setChecked(true);
		}else if (!"".equals(fb)&&fb.contains(":")) {
			fbQtEditText.setText(fb.split(":")[1]);
		}
	}
	
	/**
	 * 清空变量
	 */
	public void clearVariable(){
		ys="";//饮食
		sm="";//睡眠
		pb="";//排便
		pn="";//排尿
		sl="";//视力
		tl="";//听力
		yy = "";//语言
		yis = "";//意识
		xli = "";//心理
		grws = "";//个人卫生
		xt="";//生命体征   血糖
		mb="";//生命体征   脉搏
		hx="";//生命体征 呼吸
		xy="";//生命体征 血压
		twz="";//生命体征 体温
		mr="";//面容
		sz = "";//水肿
		yc = "";//压疮
		yh = "";//压红
		xb = "";//胸部
		fb = "";//腹部
	}
}
