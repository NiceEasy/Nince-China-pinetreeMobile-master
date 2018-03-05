package com.pinetree.mobile.bean;

/**
 * 
 * 运动功能评定 实体类
 *
 */
public class SportAssess {

	private String scheID;//日程ID
	private String custID;//客户ID 
	private String zwph;//坐位平衡
	private String lwph;//立位平衡
	private String bxnl;//Holden步行能力评定
	private String jl;// 肌力 （异常 正常）
	private String jzl;// 肌张力（异常 正常）
	private String ttpf ;// 疼痛评分（异常 正常）
	private String jl_yc;// 肌力异常（左侧，躯干，右侧）
	private String jzl_yc;// 肌张力异常(左侧,躯干,右侧)
	private String ttpf_yc;// 疼痛 异常（左侧，躯干，右侧）
	private String jzl_yc_zc;// 肌张力异常左侧
	private String jzl_yc_qg;// 肌张力异常躯干
	private String jzl_yc_rc;// 肌张力异常右侧
	private String jl_yc_zc;// 肌力异常左侧
	private String jl_yc_qg;// 肌力异常躯干
	private String jl_yc_rc;// 肌力异常右侧
	private String ttpf_yc_zc;//疼痛评分  有疼痛 左侧
	private String ttpf_yc_qg;//疼痛评分  有疼痛 躯干
	private String ttpf_yc_rc;//疼痛评分  有疼痛 右侧
	private String jl_yc_zc_shou;//肌力 异常 左侧 手得分
	private String jl_yc_zc_sz;//肌力 异常 左侧 上肢得分
	private String jl_yc_zc_xz;//肌力 异常 左侧 下肢得分
	private String jl_yc_zc_qt;//肌力 异常 左侧 其他得分
	private String jl_yc_qg_bj;//肌力 异常 躯干 背肌得分
	private String jl_yc_qg_yj;//肌力 异常 躯干 腰肌得分
	private String jl_yc_qg_tj;//肌力 异常 躯干 臀肌得分
	private String jl_yc_qg_xj;//肌力 异常 躯干 胸肌得分
	private String jl_yc_qg_fj;//肌力 异常 躯干 腹肌得分
	private String jl_yc_qg_qt;//肌力 异常 躯干 其他得分	
	private String jl_yc_rc_sz;//肌力 异常 右侧 上肢得分
	private String jl_yc_rc_xz;//肌力 异常 右侧 下肢得分
	private String jl_yc_rc_jiao;//肌力 异常 右侧 脚得分
	private String jl_yc_rc_qt;//肌力 异常 右侧 其他得分
	private String jzl_yc_zc_shou;//肌张力 异常 左侧 手得分
	private String jzl_yc_zc_sz;//肌张力 异常 左侧 上肢得分
	private String jzl_yc_zc_xz;//肌张力 异常 左侧 下肢得分
	private String jzl_yc_zc_qt;//肌张力 异常 左侧 其他得分
	private String jzl_yc_qg_bj;//肌张力 异常 躯干 背肌得分
	private String jzl_yc_qg_yj;//肌张力 异常 躯干 腰肌得分
	private String jzl_yc_qg_tj;//肌张力 异常 躯干 臀肌得分
	private String jzl_yc_qg_xj;//肌张力 异常 躯干 胸肌得分
	private String jzl_yc_qg_fj;//肌张力 异常 躯干 腹肌得分
	private String jzl_yc_qg_qt;//肌张力 异常 躯干 其他得分	
	private String jzl_yc_rc_sz;//肌张力 异常 右侧 上肢得分
	private String jzl_yc_rc_xz;//肌张力 异常 右侧 下肢得分
	private String jzl_yc_rc_jiao;//肌张力 异常 右侧 脚得分
	private String jzl_yc_rc_qt;//肌张力 异常 右侧 其他得分
	private String ttpf_yc_zc_shou;//疼痛评分 有疼痛 左侧 手得分
	private String ttpf_yc_zc_sz;//疼痛评分 有疼痛 左侧 上肢得分
	private String ttpf_yc_zc_xz;//疼痛评分 有疼痛 左侧 下肢得分
	private String ttpf_yc_zc_qt;//疼痛评分 有疼痛 左侧 其他得分
	private String ttpf_yc_qg_bj;//疼痛评分 有疼痛 躯干 背肌得分
	private String ttpf_yc_qg_yj;//疼痛评分 有疼痛 躯干 腰肌得分
	private String ttpf_yc_qg_tj;//疼痛评分 有疼痛 躯干 臀肌得分
	private String ttpf_yc_qg_xj;//疼痛评分 有疼痛 躯干 胸肌得分
	private String ttpf_yc_qg_fj;//疼痛评分 有疼痛 躯干 腹肌得分
	private String ttpf_yc_qg_qt;//疼痛评分 有疼痛 躯干 其他得分	
	private String ttpf_yc_rc_sz;//疼痛评分 有疼痛 右侧 上肢得分
	private String ttpf_yc_rc_xz;//疼痛评分 有疼痛 右侧 下肢得分
	private String ttpf_yc_rc_jiao;//疼痛评分 有疼痛 右侧 脚得分
	private String ttpf_yc_rc_qt;//疼痛评分 有疼痛 右侧 其他得分
	private String fxdj;//风险等级
	private String createTime;//创建时间
	private String isNew;//0:最新评估报告
	
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getScheID() {
		return scheID;
	}
	public void setScheID(String scheID) {
		this.scheID = scheID;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getZwph() {
		return zwph;
	}
	public void setZwph(String zwph) {
		this.zwph = zwph;
	}
	public String getLwph() {
		return lwph;
	}
	public void setLwph(String lwph) {
		this.lwph = lwph;
	}
	public String getBxnl() {
		return bxnl;
	}
	public void setBxnl(String bxnl) {
		this.bxnl = bxnl;
	}
	public String getJl() {
		return jl;
	}
	public void setJl(String jl) {
		this.jl = jl;
	}
	public String getJzl() {
		return jzl;
	}
	public void setJzl(String jzl) {
		this.jzl = jzl;
	}
	public String getTtpf() {
		return ttpf;
	}
	public void setTtpf(String ttpf) {
		this.ttpf = ttpf;
	}
	public String getJl_yc() {
		return jl_yc;
	}
	public void setJl_yc(String jl_yc) {
		this.jl_yc = jl_yc;
	}
	public String getJzl_yc() {
		return jzl_yc;
	}
	public void setJzl_yc(String jzl_yc) {
		this.jzl_yc = jzl_yc;
	}
	public String getTtpf_yc() {
		return ttpf_yc;
	}
	public void setTtpf_yc(String ttpf_yc) {
		this.ttpf_yc = ttpf_yc;
	}
	public String getJzl_yc_zc() {
		return jzl_yc_zc;
	}
	public void setJzl_yc_zc(String jzl_yc_zc) {
		this.jzl_yc_zc = jzl_yc_zc;
	}
	public String getJzl_yc_qg() {
		return jzl_yc_qg;
	}
	public void setJzl_yc_qg(String jzl_yc_qg) {
		this.jzl_yc_qg = jzl_yc_qg;
	}
	public String getJzl_yc_rc() {
		return jzl_yc_rc;
	}
	public void setJzl_yc_rc(String jzl_yc_rc) {
		this.jzl_yc_rc = jzl_yc_rc;
	}
	public String getJl_yc_zc() {
		return jl_yc_zc;
	}
	public void setJl_yc_zc(String jl_yc_zc) {
		this.jl_yc_zc = jl_yc_zc;
	}
	public String getJl_yc_qg() {
		return jl_yc_qg;
	}
	public void setJl_yc_qg(String jl_yc_qg) {
		this.jl_yc_qg = jl_yc_qg;
	}
	public String getJl_yc_rc() {
		return jl_yc_rc;
	}
	public void setJl_yc_rc(String jl_yc_rc) {
		this.jl_yc_rc = jl_yc_rc;
	}
	public String getTtpf_yc_zc() {
		return ttpf_yc_zc;
	}
	public void setTtpf_yc_zc(String ttpf_yc_zc) {
		this.ttpf_yc_zc = ttpf_yc_zc;
	}
	public String getTtpf_yc_qg() {
		return ttpf_yc_qg;
	}
	public void setTtpf_yc_qg(String ttpf_yc_qg) {
		this.ttpf_yc_qg = ttpf_yc_qg;
	}
	public String getTtpf_yc_rc() {
		return ttpf_yc_rc;
	}
	public void setTtpf_yc_rc(String ttpf_yc_rc) {
		this.ttpf_yc_rc = ttpf_yc_rc;
	}
	public String getJl_yc_zc_shou() {
		return jl_yc_zc_shou;
	}
	public void setJl_yc_zc_shou(String jl_yc_zc_shou) {
		this.jl_yc_zc_shou = jl_yc_zc_shou;
	}
	public String getJl_yc_zc_sz() {
		return jl_yc_zc_sz;
	}
	public void setJl_yc_zc_sz(String jl_yc_zc_sz) {
		this.jl_yc_zc_sz = jl_yc_zc_sz;
	}
	public String getJl_yc_zc_xz() {
		return jl_yc_zc_xz;
	}
	public void setJl_yc_zc_xz(String jl_yc_zc_xz) {
		this.jl_yc_zc_xz = jl_yc_zc_xz;
	}
	public String getJl_yc_zc_qt() {
		return jl_yc_zc_qt;
	}
	public void setJl_yc_zc_qt(String jl_yc_zc_qt) {
		this.jl_yc_zc_qt = jl_yc_zc_qt;
	}
	public String getJl_yc_qg_bj() {
		return jl_yc_qg_bj;
	}
	public void setJl_yc_qg_bj(String jl_yc_qg_bj) {
		this.jl_yc_qg_bj = jl_yc_qg_bj;
	}
	public String getJl_yc_qg_yj() {
		return jl_yc_qg_yj;
	}
	public void setJl_yc_qg_yj(String jl_yc_qg_yj) {
		this.jl_yc_qg_yj = jl_yc_qg_yj;
	}
	public String getJl_yc_qg_tj() {
		return jl_yc_qg_tj;
	}
	public void setJl_yc_qg_tj(String jl_yc_qg_tj) {
		this.jl_yc_qg_tj = jl_yc_qg_tj;
	}
	public String getJl_yc_qg_xj() {
		return jl_yc_qg_xj;
	}
	public void setJl_yc_qg_xj(String jl_yc_qg_xj) {
		this.jl_yc_qg_xj = jl_yc_qg_xj;
	}
	public String getJl_yc_qg_fj() {
		return jl_yc_qg_fj;
	}
	public void setJl_yc_qg_fj(String jl_yc_qg_fj) {
		this.jl_yc_qg_fj = jl_yc_qg_fj;
	}
	public String getJl_yc_qg_qt() {
		return jl_yc_qg_qt;
	}
	public void setJl_yc_qg_qt(String jl_yc_qg_qt) {
		this.jl_yc_qg_qt = jl_yc_qg_qt;
	}
	public String getJl_yc_rc_sz() {
		return jl_yc_rc_sz;
	}
	public void setJl_yc_rc_sz(String jl_yc_rc_sz) {
		this.jl_yc_rc_sz = jl_yc_rc_sz;
	}
	public String getJl_yc_rc_xz() {
		return jl_yc_rc_xz;
	}
	public void setJl_yc_rc_xz(String jl_yc_rc_xz) {
		this.jl_yc_rc_xz = jl_yc_rc_xz;
	}
	public String getJl_yc_rc_jiao() {
		return jl_yc_rc_jiao;
	}
	public void setJl_yc_rc_jiao(String jl_yc_rc_jiao) {
		this.jl_yc_rc_jiao = jl_yc_rc_jiao;
	}
	public String getJl_yc_rc_qt() {
		return jl_yc_rc_qt;
	}
	public void setJl_yc_rc_qt(String jl_yc_rc_qt) {
		this.jl_yc_rc_qt = jl_yc_rc_qt;
	}
	public String getJzl_yc_zc_shou() {
		return jzl_yc_zc_shou;
	}
	public void setJzl_yc_zc_shou(String jzl_yc_zc_shou) {
		this.jzl_yc_zc_shou = jzl_yc_zc_shou;
	}
	public String getJzl_yc_zc_sz() {
		return jzl_yc_zc_sz;
	}
	public void setJzl_yc_zc_sz(String jzl_yc_zc_sz) {
		this.jzl_yc_zc_sz = jzl_yc_zc_sz;
	}
	public String getJzl_yc_zc_xz() {
		return jzl_yc_zc_xz;
	}
	public void setJzl_yc_zc_xz(String jzl_yc_zc_xz) {
		this.jzl_yc_zc_xz = jzl_yc_zc_xz;
	}
	public String getJzl_yc_zc_qt() {
		return jzl_yc_zc_qt;
	}
	public void setJzl_yc_zc_qt(String jzl_yc_zc_qt) {
		this.jzl_yc_zc_qt = jzl_yc_zc_qt;
	}
	public String getJzl_yc_qg_bj() {
		return jzl_yc_qg_bj;
	}
	public void setJzl_yc_qg_bj(String jzl_yc_qg_bj) {
		this.jzl_yc_qg_bj = jzl_yc_qg_bj;
	}
	public String getJzl_yc_qg_yj() {
		return jzl_yc_qg_yj;
	}
	public void setJzl_yc_qg_yj(String jzl_yc_qg_yj) {
		this.jzl_yc_qg_yj = jzl_yc_qg_yj;
	}
	public String getJzl_yc_qg_tj() {
		return jzl_yc_qg_tj;
	}
	public void setJzl_yc_qg_tj(String jzl_yc_qg_tj) {
		this.jzl_yc_qg_tj = jzl_yc_qg_tj;
	}
	public String getJzl_yc_qg_xj() {
		return jzl_yc_qg_xj;
	}
	public void setJzl_yc_qg_xj(String jzl_yc_qg_xj) {
		this.jzl_yc_qg_xj = jzl_yc_qg_xj;
	}
	public String getJzl_yc_qg_fj() {
		return jzl_yc_qg_fj;
	}
	public void setJzl_yc_qg_fj(String jzl_yc_qg_fj) {
		this.jzl_yc_qg_fj = jzl_yc_qg_fj;
	}
	public String getJzl_yc_qg_qt() {
		return jzl_yc_qg_qt;
	}
	public void setJzl_yc_qg_qt(String jzl_yc_qg_qt) {
		this.jzl_yc_qg_qt = jzl_yc_qg_qt;
	}
	public String getJzl_yc_rc_sz() {
		return jzl_yc_rc_sz;
	}
	public void setJzl_yc_rc_sz(String jzl_yc_rc_sz) {
		this.jzl_yc_rc_sz = jzl_yc_rc_sz;
	}
	public String getJzl_yc_rc_xz() {
		return jzl_yc_rc_xz;
	}
	public void setJzl_yc_rc_xz(String jzl_yc_rc_xz) {
		this.jzl_yc_rc_xz = jzl_yc_rc_xz;
	}
	public String getJzl_yc_rc_jiao() {
		return jzl_yc_rc_jiao;
	}
	public void setJzl_yc_rc_jiao(String jzl_yc_rc_jiao) {
		this.jzl_yc_rc_jiao = jzl_yc_rc_jiao;
	}
	public String getJzl_yc_rc_qt() {
		return jzl_yc_rc_qt;
	}
	public void setJzl_yc_rc_qt(String jzl_yc_rc_qt) {
		this.jzl_yc_rc_qt = jzl_yc_rc_qt;
	}
	public String getTtpf_yc_zc_shou() {
		return ttpf_yc_zc_shou;
	}
	public void setTtpf_yc_zc_shou(String ttpf_yc_zc_shou) {
		this.ttpf_yc_zc_shou = ttpf_yc_zc_shou;
	}
	public String getTtpf_yc_zc_sz() {
		return ttpf_yc_zc_sz;
	}
	public void setTtpf_yc_zc_sz(String ttpf_yc_zc_sz) {
		this.ttpf_yc_zc_sz = ttpf_yc_zc_sz;
	}
	public String getTtpf_yc_zc_xz() {
		return ttpf_yc_zc_xz;
	}
	public void setTtpf_yc_zc_xz(String ttpf_yc_zc_xz) {
		this.ttpf_yc_zc_xz = ttpf_yc_zc_xz;
	}
	public String getTtpf_yc_zc_qt() {
		return ttpf_yc_zc_qt;
	}
	public void setTtpf_yc_zc_qt(String ttpf_yc_zc_qt) {
		this.ttpf_yc_zc_qt = ttpf_yc_zc_qt;
	}
	public String getTtpf_yc_qg_bj() {
		return ttpf_yc_qg_bj;
	}
	public void setTtpf_yc_qg_bj(String ttpf_yc_qg_bj) {
		this.ttpf_yc_qg_bj = ttpf_yc_qg_bj;
	}
	public String getTtpf_yc_qg_yj() {
		return ttpf_yc_qg_yj;
	}
	public void setTtpf_yc_qg_yj(String ttpf_yc_qg_yj) {
		this.ttpf_yc_qg_yj = ttpf_yc_qg_yj;
	}
	public String getTtpf_yc_qg_tj() {
		return ttpf_yc_qg_tj;
	}
	public void setTtpf_yc_qg_tj(String ttpf_yc_qg_tj) {
		this.ttpf_yc_qg_tj = ttpf_yc_qg_tj;
	}
	public String getTtpf_yc_qg_xj() {
		return ttpf_yc_qg_xj;
	}
	public void setTtpf_yc_qg_xj(String ttpf_yc_qg_xj) {
		this.ttpf_yc_qg_xj = ttpf_yc_qg_xj;
	}
	public String getTtpf_yc_qg_fj() {
		return ttpf_yc_qg_fj;
	}
	public void setTtpf_yc_qg_fj(String ttpf_yc_qg_fj) {
		this.ttpf_yc_qg_fj = ttpf_yc_qg_fj;
	}
	public String getTtpf_yc_qg_qt() {
		return ttpf_yc_qg_qt;
	}
	public void setTtpf_yc_qg_qt(String ttpf_yc_qg_qt) {
		this.ttpf_yc_qg_qt = ttpf_yc_qg_qt;
	}
	public String getTtpf_yc_rc_sz() {
		return ttpf_yc_rc_sz;
	}
	public void setTtpf_yc_rc_sz(String ttpf_yc_rc_sz) {
		this.ttpf_yc_rc_sz = ttpf_yc_rc_sz;
	}
	public String getTtpf_yc_rc_xz() {
		return ttpf_yc_rc_xz;
	}
	public void setTtpf_yc_rc_xz(String ttpf_yc_rc_xz) {
		this.ttpf_yc_rc_xz = ttpf_yc_rc_xz;
	}
	public String getTtpf_yc_rc_jiao() {
		return ttpf_yc_rc_jiao;
	}
	public void setTtpf_yc_rc_jiao(String ttpf_yc_rc_jiao) {
		this.ttpf_yc_rc_jiao = ttpf_yc_rc_jiao;
	}
	public String getTtpf_yc_rc_qt() {
		return ttpf_yc_rc_qt;
	}
	public void setTtpf_yc_rc_qt(String ttpf_yc_rc_qt) {
		this.ttpf_yc_rc_qt = ttpf_yc_rc_qt;
	}
	public String getFxdj() {
		return fxdj;
	}
	public void setFxdj(String fxdj) {
		this.fxdj = fxdj;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
