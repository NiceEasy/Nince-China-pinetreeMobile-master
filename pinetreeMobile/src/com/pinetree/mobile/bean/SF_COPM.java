package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

public class SF_COPM implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Copm;
	String xianPoint;
	String zuoyeChange;
	String manPoint;
	String manChange;
	String isLast;//是否上次带出（1：否，0：是） 0表示不可更改数据
	String situationSum;
	String satisfSum;
	String specialAccount;//评估师特殊交代
	String remark;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getReportId() {
		return ReportId;
	}

	public void setReportId(String reportId) {
		ReportId = reportId;
	}

	public String getCopm() {
		return Copm;
	}

	public void setCopm(String copm) {
		this.Copm = copm;
	}

	public String getXianPoint() {
		return xianPoint;
	}

	public void setXianPoint(String xianPoint) {
		this.xianPoint = xianPoint;
	}

	public String getZuoyeChange() {
		return zuoyeChange;
	}

	public void setZuoyeChange(String zuoyeChange) {
		this.zuoyeChange = zuoyeChange;
	}

	public String getManPoint() {
		return manPoint;
	}

	public void setManPoint(String manPoint) {
		this.manPoint = manPoint;
	}

	public String getManChange() {
		return manChange;
	}

	public void setManChange(String manChange) {
		this.manChange = manChange;
	}

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public String getSituationSum() {
		return situationSum;
	}

	public void setSituationSum(String situationSum) {
		this.situationSum = situationSum;
	}

	public String getSatisfSum() {
		return satisfSum;
	}

	public void setSatisfSum(String satisfSum) {
		this.satisfSum = satisfSum;
	}

	public String getSpecialAccount() {
		return specialAccount;
	}

	public void setSpecialAccount(String specialAccount) {
		this.specialAccount = specialAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
