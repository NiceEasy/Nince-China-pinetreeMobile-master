package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@��ȷ��ļ��� BI_DiagnosedDisease ID ID ���浥�� ReportId ��Ⱦ������ƴ���� InfectiousDiseases ���ո�Σ������ƴ���� HighRiskDisease ��ʳ�����Լ�����ƴ���� DietaryRestrictionDisease ����������ƴ���� OtherDisease ��ע Remark ���������е�������Ϣ1
 *      ���������е�������Ϣ2
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:16:21
 */
public class BI_DiagnosedDisease implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String InfectiousDiseases;
	String HighRiskDisease;
	String DietaryRestrictionDisease;
	String OtherDisease;
	String OtherInfoOne;
	String OtherInfoTwo;
	String Remark;

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

	public String getInfectiousDiseases() {
		return InfectiousDiseases;
	}

	public void setInfectiousDiseases(String infectiousDiseases) {
		InfectiousDiseases = infectiousDiseases;
	}

	public String getHighRiskDisease() {
		return HighRiskDisease;
	}

	public void setHighRiskDisease(String highRiskDisease) {
		HighRiskDisease = highRiskDisease;
	}

	public String getDietaryRestrictionDisease() {
		return DietaryRestrictionDisease;
	}

	public void setDietaryRestrictionDisease(String dietaryRestrictionDisease) {
		DietaryRestrictionDisease = dietaryRestrictionDisease;
	}

	public String getOtherDisease() {
		return OtherDisease;
	}

	public void setOtherDisease(String otherDisease) {
		OtherDisease = otherDisease;
	}

	public String getOtherInfoOne() {
		return OtherInfoOne;
	}

	public void setOtherInfoOne(String otherInfoOne) {
		OtherInfoOne = otherInfoOne;
	}

	public String getOtherInfoTwo() {
		return OtherInfoTwo;
	}

	public void setOtherInfoTwo(String otherInfoTwo) {
		OtherInfoTwo = otherInfoTwo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
