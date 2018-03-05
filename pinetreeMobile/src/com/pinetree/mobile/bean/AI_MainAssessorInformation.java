package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@��������Ա��Ϣ AI_MainAssessorInformation
 * 
 *      Name Code ID ID ���浥�� ReportId ���� Name �������� BelongInstitution ��ס��ַ HomeAddress סլ�绰 HomePhone �ƶ��绰 Mobile �������� PostalCode �������� EMail ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 ����10:43:25
 */
public class AI_MainAssessorInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Name;
	String BelongInstitution;
	String HomeAddress;
	String HomePhone;
	String Mobile;
	String PostalCode;
	String EMail;
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBelongInstitution() {
		return BelongInstitution;
	}

	public void setBelongInstitution(String belongInstitution) {
		BelongInstitution = belongInstitution;
	}

	public String getHomeAddress() {
		return HomeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		HomeAddress = homeAddress;
	}

	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
