package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class RecoveryNursingPlan implements Serializable{

	private String id;//康复护理计划ID
	private String custID;//客户id
	private String cudeID;//评估报告ID
	private String custName;//客户名称
	private String createUser;
	private String sex;//性别
	private String age;//年龄 
	private String illhis;//病史记录(不用了)
	private String checkbody;//查体
	private String adlscore;//ADL评分 
	private String mmsescore;//MMSE评分
	private String druguse;//目前用药
	private String notice;//注意事项
	private String comitStatus;//状态（0：暂存，1：提交，2：保存）
	private String signinDate;//提交日期
	private String createTime;//创建时间
	private String version;
	private String serDate;//服务日期
	//非数据库字段
	private String scheId;//排班id
	private String content;//查询健康宣教
	private String submitState;
	private String saveState;
	private String planID;
	private String empName;
	
	private List<RegainHistoryRecords> regainHistoryRecordsList;//病史记录
	private List<RegainQuestion> currQuestionList;//现存问题
	private List<RegainQuestion> solveQuestionList;//拟解决问题
	private List<RegainTarget> regainTargetList;//康复目标
	private List<RehabilitationMeasures> regainTargetSubList;//康复措施
	private List<RehabilitationMeasures> regainPublicizeList;//健康宣教
	
	
	
	public String getSerDate() {
		return serDate;
	}
	public void setSerDate(String serDate) {
		this.serDate = serDate;
	}
	public List<RegainHistoryRecords> getRegainHistoryRecordsList() {
		return regainHistoryRecordsList;
	}
	public void setRegainHistoryRecordsList(
			List<RegainHistoryRecords> regainHistoryRecordsList) {
		this.regainHistoryRecordsList = regainHistoryRecordsList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCudeID() {
		return cudeID;
	}
	public void setCudeID(String cudeID) {
		this.cudeID = cudeID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getIllhis() {
		return illhis;
	}
	public void setIllhis(String illhis) {
		this.illhis = illhis;
	}
	public String getCheckbody() {
		return checkbody;
	}
	public void setCheckbody(String checkbody) {
		this.checkbody = checkbody;
	}
	public String getAdlscore() {
		return adlscore;
	}
	public void setAdlscore(String adlscore) {
		this.adlscore = adlscore;
	}
	public String getMmsescore() {
		return mmsescore;
	}
	public void setMmsescore(String mmsescore) {
		this.mmsescore = mmsescore;
	}
	public String getDruguse() {
		return druguse;
	}
	public void setDruguse(String druguse) {
		this.druguse = druguse;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getComitStatus() {
		return comitStatus;
	}
	public void setComitStatus(String comitStatus) {
		this.comitStatus = comitStatus;
	}
	public String getSigninDate() {
		return signinDate;
	}
	public void setSigninDate(String signinDate) {
		this.signinDate = signinDate;
	}
	public String getScheId() {
		return scheId;
	}
	public void setScheId(String scheId) {
		this.scheId = scheId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubmitState() {
		return submitState;
	}
	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}
	public String getSaveState() {
		return saveState;
	}
	public void setSaveState(String saveState) {
		this.saveState = saveState;
	}
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public List<RegainQuestion> getCurrQuestionList() {
		return currQuestionList;
	}
	public void setCurrQuestionList(List<RegainQuestion> currQuestionList) {
		this.currQuestionList = currQuestionList;
	}
	public List<RegainQuestion> getSolveQuestionList() {
		return solveQuestionList;
	}
	public void setSolveQuestionList(List<RegainQuestion> solveQuestionList) {
		this.solveQuestionList = solveQuestionList;
	}
	public List<RegainTarget> getRegainTargetList() {
		return regainTargetList;
	}
	public void setRegainTargetList(List<RegainTarget> regainTargetList) {
		this.regainTargetList = regainTargetList;
	}
	public List<RehabilitationMeasures> getRegainPublicizeList() {
		return regainPublicizeList;
	}
	public void setRegainPublicizeList(List<RehabilitationMeasures> regainPublicizeList) {
		this.regainPublicizeList = regainPublicizeList;
	}
	public List<RehabilitationMeasures> getRegainTargetSubList() {
		return regainTargetSubList;
	}
	public void setRegainTargetSubList(List<RehabilitationMeasures> regainTargetSubList) {
		this.regainTargetSubList = regainTargetSubList;
	}
	
	
}
