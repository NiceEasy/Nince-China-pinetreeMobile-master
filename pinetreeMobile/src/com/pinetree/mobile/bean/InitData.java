package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@�ճ��������� AI_ActivityofDailyLliving
 * 
 *      Name Code ID ID ���浥�� ReportId ��ʳ˵�� EatExplain ��ʳ�÷� EatScore ϴ��÷� BathScore ϴ��˵�� BathExplain ����˵�� ModificationExplain ���ε÷� ModificationScore ����˵�� DressingExplain ���µ÷� DressingScore �����Ƶ÷�
 *      StoolControlScore ������˵�� StoolControlExplain С�����˵�� UrineControlExplain С����Ƶ÷� UrineControlScore ���˵�� GoToTheToiletExplain ��޵÷� GoToTheToiletScore ����ת��˵�� BedChairTransferExplain ����ת�Ƶ÷�
 *      BedChairTransferScore ƽ������˵�� FlatWalkingExplain ƽ�����ߵ÷� FlatWalkingScore ����¥��˵�� UpAndDownStairsExplain ����¥�ݵ÷� UpAndDownStairsScore �ճ������ܷ� SumScore �ճ������ּ� DailyActivityLevel �ճ������ּ�˵��
 *      DailyActivityLevelExplain ��ע Remark �Ƿ�������״̬ 0��������1���ճ̶�Ӧ���棩 whetherNew
 * 
 * @author wcm
 * @createDate 2015-9-2 ����10:32:54
 */
public class InitData implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String age;
	String beginTime;
	String custAddress;
	String custID;
	String custName;
	String custPhone;
	String empNum;
	String employeeID;
	String employeeName;
	String endTime;
	String scheduleId;
	String lat;
	String lng;
	String sex;
	String projectId;
	String prodID;
	String whetherNew;
	String Remark;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
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

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
