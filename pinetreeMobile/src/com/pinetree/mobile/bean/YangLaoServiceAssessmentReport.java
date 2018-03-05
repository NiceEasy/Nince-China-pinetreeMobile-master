package com.pinetree.mobile.bean;

import java.io.File;
import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 养老服务信息表 YangLaoServiceAssessmentReport ID ID 报告单号（评估编号） ReportId 申请人 Applicant 填表日期 ReportDate 承诺声明1 PromiseDesc1 承诺声明2 PromiseDesc2 承诺声明3 PromiseDesc3 备注 Remark 产品ID prodID 是否新增（状态
 *      0：新增；1：日程对应报告） whetherNew
 * @author wcm
 * @createDate 2015-9-1 上午9:45:45
 */
public class YangLaoServiceAssessmentReport implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	File Applicant;
	File HuaZhong;
	String ApplicantStr;
	String huazhongStr;
	String ReportDate;
	String PromiseDesc1;
	String PromiseDesc2;
	String PromiseDesc3;
	String Remark;
	String beginTime;
	String endTime;
	String projectId;
	String scheduleId;
	String lat;
	String lng;
	String employeeID;
	String employeeName;
	String empNum;
	String relation;
	String prodID;
	String whetherNew;
	String isUpload;
	
	String age;
	String custAddress;
	String custID;
	String custName;
	String custPhone;
	String sex;

	public String getApplicantStr() {
		return ApplicantStr;
	}

	public void setApplicantStr(String applicantStr) {
		ApplicantStr = applicantStr;
	}

	public File getHuaZhong() {
		return HuaZhong;
	}

	public void setHuaZhong(File huaZhong) {
		HuaZhong = huaZhong;
	}

	public String getHuazhongStr() {
		return huazhongStr;
	}

	public void setHuazhongStr(String huazhongStr) {
		this.huazhongStr = huazhongStr;
	}

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

	public File getApplicant() {
		return Applicant;
	}

	public void setApplicant(File applicant) {
		Applicant = applicant;
	}

	public String getReportDate() {
		return ReportDate;
	}

	public void setReportDate(String reportDate) {
		ReportDate = reportDate;
	}

	public String getPromiseDesc1() {
		return PromiseDesc1;
	}

	public void setPromiseDesc1(String promiseDesc1) {
		PromiseDesc1 = promiseDesc1;
	}

	public String getPromiseDesc2() {
		return PromiseDesc2;
	}

	public void setPromiseDesc2(String promiseDesc2) {
		PromiseDesc2 = promiseDesc2;
	}

	public String getPromiseDesc3() {
		return PromiseDesc3;
	}

	public void setPromiseDesc3(String promiseDesc3) {
		PromiseDesc3 = promiseDesc3;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getProdID() {
		return prodID;
	}

	public void setProdID(String prodID) {
		this.prodID = prodID;
	}

	public String getWhetherNew() {
		return whetherNew;
	}

	public void setWhetherNew(String whetherNew) {
		this.whetherNew = whetherNew;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
}
