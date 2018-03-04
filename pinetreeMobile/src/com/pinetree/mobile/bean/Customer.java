package com.pinetree.mobile.bean;

import java.io.Serializable;
import com.pinetree.mobile.utils.StringDispose;

public class Customer implements Serializable {
	private String sex;
	private String age;
	private String beginTime;
	private String beginTimeSub;
	private String custAddress;//详细地址
	private String custID;
	private String custName;
	private String custPhone;
	private String endTime;
	private String endTimeSub;
	private String id;
	private String isResign;
	private String isVouchRec;//是否还款（0：否，1：是）
	private String vouchAmount;//担保还款金额
	private String renewalAmount;//续费金额
	private String isSign;//是否续约（0：否，1：是）
	private String haveSign;
	private String prodName;
	private String loadDataTag;// 加载数据tag
	private String signResignTag;// 签到签退tag
	private String lat;//纬度
	private String lng;//经度
	private String reimbursement;//是否显示报销路费
	private String prodID;//ec996f1598b2429bb5042f7b5add7338 此ID 为政府做评估的产品ID
	private String prodType;//判断是否可评估  产品类型（0：护理，1：评估，2：体验，3：其他）
	private String status;
	private String reportStatus;//提交状态  1为所有报告都提交了 
	private String isServ;//签字单是否提交 0：未签字 1：已签字
    private String empNum;//服务人员编号
    private String projectId;//项目ID
    
    private String beforeName;//曾用名
    private String idNum;//身份证号
    private String healthCard;//医保卡号
    private String disabledSoldierPaper;//残疾军人证
    private String disabledPaper;//残疾人证
    private String birth;//出生日期
    private String familyName;//民族
    private String placeOrigin;//籍贯
    private String cityAddress;//省市区地址 
    private String linkPhone2;//移动电话
    private String postCode;//邮政编号
    private String custEmail;//电子邮箱
    private String categoryID;//产品分类ID
    
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getBeforeName() {
		return beforeName;
	}
	public void setBeforeName(String beforeName) {
		this.beforeName = beforeName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getHealthCard() {
		return healthCard;
	}
	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}
	public String getDisabledSoldierPaper() {
		return disabledSoldierPaper;
	}
	public void setDisabledSoldierPaper(String disabledSoldierPaper) {
		this.disabledSoldierPaper = disabledSoldierPaper;
	}
	public String getDisabledPaper() {
		return disabledPaper;
	}
	public void setDisabledPaper(String disabledPaper) {
		this.disabledPaper = disabledPaper;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getPlaceOrigin() {
		return placeOrigin;
	}
	public void setPlaceOrigin(String placeOrigin) {
		this.placeOrigin = placeOrigin;
	}
	public String getCityAddress() {
		return cityAddress;
	}
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}
	public String getLinkPhone2() {
		return linkPhone2;
	}
	public void setLinkPhone2(String linkPhone2) {
		this.linkPhone2 = linkPhone2;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getIsServ() {
		return isServ;
	}
	public void setIsServ(String isServ) {
		this.isServ = isServ;
	}
	public String getIsVouchRec() {
		return isVouchRec;
	}
	public void setIsVouchRec(String isVouchRec) {
		this.isVouchRec = isVouchRec;
	}
	public String getVouchAmount() {
		return vouchAmount;
	}
	public void setVouchAmount(String vouchAmount) {
		this.vouchAmount = vouchAmount;
	}
	public String getRenewalAmount() {
		return renewalAmount;
	}
	public void setRenewalAmount(String renewalAmount) {
		this.renewalAmount = renewalAmount;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProdID() {
		return prodID;
	}
	public void setProdID(String prodID) {
		this.prodID = prodID;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getLng() {
		return StringDispose.getNotNullStr(lng);
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return StringDispose.getNotNullStr(lat);
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getReimbursement() {
		return StringDispose.getNotNullStr(reimbursement);
	}
	public void setReimbursement(String reimbursement) {
		this.reimbursement = reimbursement;
	}
	public String getBeginTime() {
		return StringDispose.getNotNullStr(beginTime);
	}
	public String getBeginTimeSub() {
		return StringDispose.getNotNullStr(beginTimeSub);
	}
	public String getCustAddress() {
		return StringDispose.getNotNullStr(custAddress);
	}
	public String getCustID() {
		return StringDispose.getNotNullStr(custID);
	}
	public String getCustName() {
		return StringDispose.getNotNullStr(custName);
	}
	public String getCustPhone() {
		return StringDispose.getNotNullStr(custPhone);
	}
	public String getEndTime() {
		return StringDispose.getNotNullStr(endTime);
	}
	public String getEndTimeSub() {
		return StringDispose.getNotNullStr(endTimeSub);
	}
	public String getId() {
		return StringDispose.getNotNullStr(id);
	}
	public String getIsResign() {
		return StringDispose.getNotNullStr(isResign);
	}
	public String getIsSign() {
		return StringDispose.getNotNullStr(isSign);
	}
	public String getHaveSign() {
		return StringDispose.getNotNullStr(haveSign);
	}
	public String getProdName() {
		return StringDispose.getNotNullStr(prodName);
	}
	public String getLoadDataTag() {
		return StringDispose.getNotNullStr(loadDataTag);
	}
	public String getSignResignTag() {
		return StringDispose.getNotNullStr(signResignTag);
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public void setBeginTimeSub(String beginTimeSub) {
		this.beginTimeSub = beginTimeSub;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setEndTimeSub(String endTimeSub) {
		this.endTimeSub = endTimeSub;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIsResign(String isResign) {
		this.isResign = isResign;
	}
	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	public void setHaveSign(String haveSign) {
		this.haveSign = haveSign;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public void setLoadDataTag(String loadDataTag) {
		this.loadDataTag = loadDataTag;
	}
	public void setSignResignTag(String signResignTag) {
		this.signResignTag = signResignTag;
	}

	
	
}
