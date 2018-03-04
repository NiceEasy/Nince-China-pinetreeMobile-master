package com.pinetree.mobile.bean;

import com.pinetree.mobile.utils.StringDispose;

/**
 * 
 * 日常生活自理能力  实体类
 *
 */
public class EverydayState {
	private String scheID;//日程ID
	private String custID;//客户ID
	private String jc;//进餐
	private String xz;//洗澡
	private String zs;//装饰
	private String cy;//穿衣
	private String db;//大便
	private String xb;//小便
	private String yc;//用厕
	private String qyzy;//床椅转移
	private String pdz;//平底走
	private String sxlt;//上下楼梯
	private String assess;//评估总分
	private String result;//评估结果
	private String note;//注释
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
	public String getJc() {
		return jc;
	}
	public void setJc(String jc) {
		this.jc = jc;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public String getCy() {
		return cy;
	}
	public void setCy(String cy) {
		this.cy = cy;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getYc() {
		return yc;
	}
	public void setYc(String yc) {
		this.yc = yc;
	}
	public String getQyzy() {
		return qyzy;
	}
	public void setQyzy(String qyzy) {
		this.qyzy = qyzy;
	}
	public String getPdz() {
		return pdz;
	}
	public void setPdz(String pdz) {
		this.pdz = pdz;
	}
	public String getSxlt() {
		return sxlt;
	}
	public void setSxlt(String sxlt) {
		this.sxlt = sxlt;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateTime() {
		return StringDispose.getNotNullStr(createTime);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
