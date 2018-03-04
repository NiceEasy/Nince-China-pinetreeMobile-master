package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.pinetree.mobile.utils.StringDispose;

public class DrugState implements Serializable{

	private String scheID;//日程id
	private String custID;//客户id
	private String ys;//饮食
	private String sm;//睡眠	
	private String pb;//排便
	private String pn;//排尿
	private String sl;//视力
	private String tl;//听力
	private String yy;//语言
	private String yis;//意识
	private String xli;//心理	
	private String grws;//个人卫生
	private String xt;//血糖	
	private String mb;//脉搏
	private String hx;//呼吸
	private String xy;//血压	
	private String tww;//体温
	private String mr;//面 容
	private String sz;//水肿	
	private String yc;//压疮
	private String yh;//压红
	private String xb;//胸部	
	private String fb;//腹部
	private String tw;//体位	
	private String hd;//活动	
	private String wx;//外形
	private String createTime;//创建时间
	private String isNew;//0:最新评估报告
	
	
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getTww() {
		return tww;
	}
	public void setTww(String tww) {
		this.tww = tww;
	}
	public String getXt() {
		return xt;
	}
	public void setXt(String xt) {
		this.xt = xt;
	}
	public String getMb() {
		return mb;
	}
	public void setMb(String mb) {
		this.mb = mb;
	}
	public String getHx() {
		return hx;
	}
	public void setHx(String hx) {
		this.hx = hx;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getMr() {
		return mr;
	}
	public void setMr(String mr) {
		this.mr = mr;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public String getYc() {
		return yc;
	}
	public void setYc(String yc) {
		this.yc = yc;
	}
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getFb() {
		return fb;
	}
	public void setFb(String fb) {
		this.fb = fb;
	}
	public String getTw() {
		return tw;
	}
	public void setTw(String tw) {
		this.tw = tw;
	}
	public String getHd() {
		return hd;
	}
	public void setHd(String hd) {
		this.hd = hd;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
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
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getPb() {
		return pb;
	}
	public void setPb(String pb) {
		this.pb = pb;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getTl() {
		return tl;
	}
	public void setTl(String tl) {
		this.tl = tl;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getYis() {
		return yis;
	}
	public void setYis(String yis) {
		this.yis = yis;
	}
	public String getXli() {
		return xli;
	}
	public void setXli(String xli) {
		this.xli = xli;
	}
	public String getGrws() {
		return grws;
	}
	public void setGrws(String grws) {
		this.grws = grws;
	}
	public String getCreateTime() {
		return StringDispose.getNotNullStr(createTime);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
