package com.pinetree.mobile.bean;

import java.io.Serializable;

public class RegainHistoryRecords implements Serializable{

	private String planID;//康复护理计划ID
	private String num;//序号
	private String hisRecName;//病史名称
	private String description;//病史描述
	
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHisRecName() {
		return hisRecName;
	}
	public void setHisRecName(String hisRecName) {
		this.hisRecName = hisRecName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
