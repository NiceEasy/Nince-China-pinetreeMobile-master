package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.pinetree.mobile.utils.StringDispose;

/**
 * 基本情况
 */
public class BasicInformation implements Serializable{

	private String scheID;//日程ID
	private String custID ;//客户ID
	private String age;//客户年龄
	private String marriage;//婚姻
	private String occupational;//退休前职业
	private String child;//子女情况
	private String phz;//陪护者
	private String nurse;//护理情况
	private String lighting;//采光
	private String air;//空气
	private String humidity;//干湿度
	private String temperature;//温度
	private String odor;//气味
	private String economy;//气家庭经济情况
	private String wc;//卫生间
	private String cdw;//床单位
	private String hj;//环境
	private String zz;//着装
	private String qy;//起夜
	private String fjzxl;//辅具助行类	
	private String assist;//辅具需求
	private String history;//病史记录
	private String drug;//药品使用情况
	private String createTime;//创建时间
	private String isNew;//0:最新评估报告
	
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	public String getOccupational() {
		return occupational;
	}
	public void setOccupational(String occupational) {
		this.occupational = occupational;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public String getPhz() {
		return phz;
	}
	public void setPhz(String phz) {
		this.phz = phz;
	}
	public String getNurse() {
		return nurse;
	}
	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
	public String getLighting() {
		return lighting;
	}
	public void setLighting(String lighting) {
		this.lighting = lighting;
	}
	public String getAir() {
		return air;
	}
	public void setAir(String air) {
		this.air = air;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getOdor() {
		return odor;
	}
	public void setOdor(String odor) {
		this.odor = odor;
	}
	public String getEconomy() {
		return economy;
	}
	public void setEconomy(String economy) {
		this.economy = economy;
	}
	public String getWc() {
		return wc;
	}
	public void setWc(String wc) {
		this.wc = wc;
	}
	public String getCdw() {
		return cdw;
	}
	public void setCdw(String cdw) {
		this.cdw = cdw;
	}
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getQy() {
		return qy;
	}
	public void setQy(String qy) {
		this.qy = qy;
	}
	public String getFjzxl() {
		return fjzxl;
	}
	public void setFjzxl(String fjzxl) {
		this.fjzxl = fjzxl;
	}
	public String getAssist() {
		return assist;
	}
	public void setAssist(String assist) {
		this.assist = assist;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	
	
	
	
}
