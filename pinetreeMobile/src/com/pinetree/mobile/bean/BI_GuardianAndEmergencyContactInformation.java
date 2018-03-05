package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@�໤��/������ϵ����Ϣ BI_GuardianAndEmergencyContactInformation ID ID ���浥�� ReportId �໤������ GName �໤�������˹�ϵ GRelationship �໤�˾�ס��ַ GAddress �໤��סլ�绰 GHomePhone �໤���ƶ��绰 GMobile �໤���������� GPostalCode
 *      �໤�˵������� GEMail ������ϵ������ EName ������ϵ�������˹�ϵ ERelationship ������ϵ�˾�ס��ַ EAddress ������ϵ��סլ�绰 EHomePhone ������ϵ���ƶ��绰 EMobile ������ϵ���������� EPostalCode ������ϵ�˵������� EEMail ��ע Remark
 * @author wcm
 * @createDate 2015-9-1 ����10:13:03
 */
public class BI_GuardianAndEmergencyContactInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String GName;
	String GRelationship;
	String GAddress;
	String GHomePhone;
	String GMobile;
	String GPostalCode;
	String GEMail;
	String EName;
	String ERelationship;
	String EAddress;
	String EHomePhone;
	String EMobile;
	String EPostalCode;
	String EEMail;
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

	public String getGName() {
		return GName;
	}

	public void setGName(String gName) {
		GName = gName;
	}

	public String getGRelationship() {
		return GRelationship;
	}

	public void setGRelationship(String gRelationship) {
		GRelationship = gRelationship;
	}

	public String getGAddress() {
		return GAddress;
	}

	public void setGAddress(String gAddress) {
		GAddress = gAddress;
	}

	public String getGHomePhone() {
		return GHomePhone;
	}

	public void setGHomePhone(String gHomePhone) {
		GHomePhone = gHomePhone;
	}

	public String getGMobile() {
		return GMobile;
	}

	public void setGMobile(String gMobile) {
		GMobile = gMobile;
	}

	public String getGPostalCode() {
		return GPostalCode;
	}

	public void setGPostalCode(String gPostalCode) {
		GPostalCode = gPostalCode;
	}

	public String getGEMail() {
		return GEMail;
	}

	public void setGEMail(String gEMail) {
		GEMail = gEMail;
	}

	public String getEName() {
		return EName;
	}

	public void setEName(String eName) {
		EName = eName;
	}

	public String getERelationship() {
		return ERelationship;
	}

	public void setERelationship(String eRelationship) {
		ERelationship = eRelationship;
	}

	public String getEAddress() {
		return EAddress;
	}

	public void setEAddress(String eAddress) {
		EAddress = eAddress;
	}

	public String getEHomePhone() {
		return EHomePhone;
	}

	public void setEHomePhone(String eHomePhone) {
		EHomePhone = eHomePhone;
	}

	public String getEMobile() {
		return EMobile;
	}

	public void setEMobile(String eMobile) {
		EMobile = eMobile;
	}

	public String getEPostalCode() {
		return EPostalCode;
	}

	public void setEPostalCode(String ePostalCode) {
		EPostalCode = ePostalCode;
	}

	public String getEEMail() {
		return EEMail;
	}

	public void setEEMail(String eEMail) {
		EEMail = eEMail;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
