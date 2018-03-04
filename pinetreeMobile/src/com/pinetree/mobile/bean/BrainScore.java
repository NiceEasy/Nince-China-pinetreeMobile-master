package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.pinetree.mobile.utils.StringDispose;

/**
 * 智力得分实体
 */
public class BrainScore implements Serializable{
	private String scheID;//日程id
	private String custID;//客户id
	private String sjdx;//时间定向
	private String dddx;//地点定向
	private String jy;//记忆得分
	private String zyyjs;//主意与计算
	private String hy;//回忆
	private String mm;//命名
	private String fsjz;//复述句子
	private String zxml;//执行命令
	private String ydlj;//阅读理解
	private String sx;//书写
	private String gtnl;//构图能力
	private String education;//文化程度
	private String assess;//合计得分
	private String result;//评估结果
	private String createTime;//创建时间
	private String isNew;//0:最新评估报告
	
	
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getCreateTime() {
		return StringDispose.getNotNullStr(createTime);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getSjdx() {
		return sjdx;
	}
	public void setSjdx(String sjdx) {
		this.sjdx = sjdx;
	}
	public String getDddx() {
		return dddx;
	}
	public void setDddx(String dddx) {
		this.dddx = dddx;
	}
	public String getJy() {
		return jy;
	}
	public void setJy(String jy) {
		this.jy = jy;
	}
	public String getZyyjs() {
		return zyyjs;
	}
	public void setZyyjs(String zyyjs) {
		this.zyyjs = zyyjs;
	}
	public String getHy() {
		return hy;
	}
	public void setHy(String hy) {
		this.hy = hy;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getFsjz() {
		return fsjz;
	}
	public void setFsjz(String fsjz) {
		this.fsjz = fsjz;
	}
	public String getZxml() {
		return zxml;
	}
	public void setZxml(String zxml) {
		this.zxml = zxml;
	}
	public String getYdlj() {
		return ydlj;
	}
	public void setYdlj(String ydlj) {
		this.ydlj = ydlj;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public String getGtnl() {
		return gtnl;
	}
	public void setGtnl(String gtnl) {
		this.gtnl = gtnl;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
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
	
}
