package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@��ͥ��Ҫ�ջ�����Ϣ BI_HomePrimaryCargiversInformation ID ID ���浥�� ReportId ���� Name ���� Age �Ա� Sex �����˹�ϵ Relationship ��ס��ַ Address �������� PostalCode ������ͬס LiveWithOldPeople סլ�绰 HomePhone �ƶ��绰 Mobile
 *      �������� EMail ��ͥ�ջ����ṩ���ջ����ݣ�ƴ���� HomeCargiversServices ��ͥ��Ա�Ӽ��ջ�Сʱ����5�칤���գ� 5WorkingDays ��ͥ��Ա�Ӽ��ջ�Сʱ����2����ĩ�� 2Weekend ��ͥ�ջ���Ա״̬��ƴ���� HomeCaregiversStatus ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:24:57
 */
public class BI_HomePrimaryCargiversInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Name;
	String Age;
	String Sex;
	String Relationship;
	String Address;
	String PostalCode;
	String LiveWithOldPeople;
	String HomePhone;
	String Mobile;
	String EMail;
	String HomeCargiversServices;
	String WorkingDays;
	String Weekend;
	String HomeCaregiversStatus;
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

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getRelationship() {
		return Relationship;
	}

	public void setRelationship(String relationship) {
		Relationship = relationship;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getLiveWithOldPeople() {
		return LiveWithOldPeople;
	}

	public void setLiveWithOldPeople(String liveWithOldPeople) {
		LiveWithOldPeople = liveWithOldPeople;
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

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getHomeCargiversServices() {
		return HomeCargiversServices;
	}

	public void setHomeCargiversServices(String homeCargiversServices) {
		HomeCargiversServices = homeCargiversServices;
	}

	public String getWorkingDays() {
		return WorkingDays;
	}

	public void setWorkingDays(String workingDays) {
		WorkingDays = workingDays;
	}

	public String getWeekend() {
		return Weekend;
	}

	public void setWeekend(String weekend) {
		Weekend = weekend;
	}

	public String getHomeCaregiversStatus() {
		return HomeCaregiversStatus;
	}

	public void setHomeCaregiversStatus(String homeCaregiversStatus) {
		HomeCaregiversStatus = homeCaregiversStatus;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
