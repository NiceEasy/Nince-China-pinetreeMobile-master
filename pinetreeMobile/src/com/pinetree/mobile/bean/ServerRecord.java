package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;


/**
 * 服务记录
 */
public class ServerRecord implements Serializable{
	private String age;
	private String bloodsugar;
	private String breath;
	private String content;
	private String createTime;
	private String custID;
	private String custName;
	private String empName;
	private String endDate;
	private String highpressure;
	private String id;
	private String lowpressure;
	private String temperature;
	private String otherSub;
	private String planID;
	private List<PlanList> planList;
	private List<PlanSubList> planSubList;
	private String pulse;
	private String sex;
	private String recorddate;
	private String startDate;
	private String targetDate;
	private String saveState;
	private String submitState;
	private String isRecord;//判断重复提交
	private String scheduleId;
	private String isServ;//签字单是否提交 0：未签字 1：已签字
	
	public String getIsServ() {
		return isServ;
	}
	public void setIsServ(String isServ) {
		this.isServ = isServ;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getIsRecord() {
		return isRecord;
	}
	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}
	public String getSaveState() {
		return saveState;
	}
	public void setSaveState(String saveState) {
		this.saveState = saveState;
	}
	public String getSubmitState() {
		return submitState;
	}
	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}
	public String getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBloodsugar() {
		return bloodsugar;
	}
	public void setBloodsugar(String bloodsugar) {
		this.bloodsugar = bloodsugar;
	}
	public String getBreath() {
		return breath;
	}
	public void setBreath(String breath) {
		this.breath = breath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHighpressure() {
		return highpressure;
	}
	public void setHighpressure(String highpressure) {
		this.highpressure = highpressure;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLowpressure() {
		return lowpressure;
	}
	public void setLowpressure(String lowpressure) {
		this.lowpressure = lowpressure;
	}
	public String getOtherSub() {
		return otherSub;
	}
	public void setOtherSub(String otherSub) {
		this.otherSub = otherSub;
	}
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public List<PlanList> getPlanList() {
		return planList;
	}
	public void setPlanList(List<PlanList> planList) {
		this.planList = planList;
	}
	public List<PlanSubList> getPlanSubList() {
		return planSubList;
	}
	public void setPlanSubList(List<PlanSubList> planSubList) {
		this.planSubList = planSubList;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	
}
