package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@�Ӽ��ջ�����Ա��Ϣ BI_ManagementInformation ID ID ���浥�� ReportId ���� Name �������� Organ ��ס��ַ Address סլ�绰 HomePhone �ƶ��绰 Mobile �������� PostalCode �������� EMail ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:30:28
 */
public class BI_ManagementInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Name;
	String Organ;
	String Address;
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

	public String getOrgan() {
		return Organ;
	}

	public void setOrgan(String organ) {
		Organ = organ;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
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
