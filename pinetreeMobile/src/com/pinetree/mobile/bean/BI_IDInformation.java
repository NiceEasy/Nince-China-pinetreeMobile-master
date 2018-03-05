package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 基本信息@身份信息 BI_IDInformation ID ID 报告单号 ReportId 姓名 Name 曾用名 NameUsedBefore 身份证件号 IDNumber 医保卡号 MedicareCardNumber 残疾军人证 DisabledSoldierCertificate 残疾人证 DisabilityCard 备注 Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 上午10:07:52
 */
public class BI_IDInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Name;
	String NameUsedBefore;
	String IDNumber;
	String MedicareCardNumber;
	String DisabledSoldierCertificate;
	String DisabilityCard;
	String Remark;
	String custID;
	String whetherNew;

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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNameUsedBefore() {
		return NameUsedBefore;
	}

	public void setNameUsedBefore(String nameUsedBefore) {
		NameUsedBefore = nameUsedBefore;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getMedicareCardNumber() {
		return MedicareCardNumber;
	}

	public void setMedicareCardNumber(String medicareCardNumber) {
		MedicareCardNumber = medicareCardNumber;
	}

	public String getDisabledSoldierCertificate() {
		return DisabledSoldierCertificate;
	}

	public void setDisabledSoldierCertificate(String disabledSoldierCertificate) {
		DisabledSoldierCertificate = disabledSoldierCertificate;
	}

	public String getDisabilityCard() {
		return DisabilityCard;
	}

	public void setDisabilityCard(String disabilityCard) {
		DisabilityCard = disabilityCard;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getWhetherNew() {
		return whetherNew;
	}

	public void setWhetherNew(String whetherNew) {
		this.whetherNew = whetherNew;
	}

}
