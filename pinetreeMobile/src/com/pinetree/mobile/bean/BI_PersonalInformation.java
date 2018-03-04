package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 基本信息@个人信息 BI_PersonalInformation ID ID 报告单号 ReportId 性别 Sex 出生日期 BirthDate 所属区 District 所属街道 Street 民族 Nation 宗教信仰 ReligiousBelief 婚姻状况 MaritalStatus 文化程度 CulturalDegree 籍贯 PlaceofOrigin 使用语言
 *      UsingLanguage 户籍地址 CensusRegisterAddress 居住地址 LiveAddress 住宅电话 HomePhone 移动电话 Mobile 邮政编码 PostalCode 电子邮箱 EMail 备注 Remark
 * @author wcm
 * @createDate 2015-9-1 上午10:10:21
 */
public class BI_PersonalInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String Sex;
	String BirthDate;
	String District;
	String Street;
	String Nation;
	String ReligiousBelief;
	String MaritalStatus;
	String CulturalDegree;
	String PlaceofOrigin;
	String UsingLanguage;
	String CensusRegisterAddress;
	String LiveAddress;
	String HomePhone;
	String Mobile;
	String PostalCode;
	String EMail;
	String Remark;
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

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public String getReligiousBelief() {
		return ReligiousBelief;
	}

	public void setReligiousBelief(String religiousBelief) {
		ReligiousBelief = religiousBelief;
	}

	public String getMaritalStatus() {
		return MaritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}

	public String getCulturalDegree() {
		return CulturalDegree;
	}

	public void setCulturalDegree(String culturalDegree) {
		CulturalDegree = culturalDegree;
	}

	public String getPlaceofOrigin() {
		return PlaceofOrigin;
	}

	public void setPlaceofOrigin(String placeofOrigin) {
		PlaceofOrigin = placeofOrigin;
	}

	public String getUsingLanguage() {
		return UsingLanguage;
	}

	public void setUsingLanguage(String usingLanguage) {
		UsingLanguage = usingLanguage;
	}

	public String getCensusRegisterAddress() {
		return CensusRegisterAddress;
	}

	public void setCensusRegisterAddress(String censusRegisterAddress) {
		CensusRegisterAddress = censusRegisterAddress;
	}

	public String getLiveAddress() {
		return LiveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		LiveAddress = liveAddress;
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

	public String getWhetherNew() {
		return whetherNew;
	}

	public void setWhetherNew(String whetherNew) {
		this.whetherNew = whetherNew;
	}

	
}
