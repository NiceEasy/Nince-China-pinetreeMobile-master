package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class ChooseAssessmentReport implements Serializable{
	
	private String id;//康复护理计划ID
    private String age;
    private String comitStatus;
    private String cudeID;
    private String custID;
    private String custName;
    private String custNum;
    private String scheId;
    private String serDate;
    private String sex;
    private String checkbody;//查体
	private String adlscore;//ADL评分 
	private String mmsescore;//MMSE评分
	private String druguse;//目前用药
	private String notice;//注意事项
	private List<RegainHistoryRecords> regainHistoryRecordsList;//病史记录
	
	public List<RegainHistoryRecords> getRegainHistoryRecordsList() {
		return regainHistoryRecordsList;
	}
	public void setRegainHistoryRecordsList(
			List<RegainHistoryRecords> regainHistoryRecordsList) {
		this.regainHistoryRecordsList = regainHistoryRecordsList;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getComitStatus() {
		return comitStatus;
	}
	public void setComitStatus(String comitStatus) {
		this.comitStatus = comitStatus;
	}
	public String getCudeID() {
		return cudeID;
	}
	public void setCudeID(String cudeID) {
		this.cudeID = cudeID;
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
	public String getCustNum() {
		return custNum;
	}
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public String getScheId() {
		return scheId;
	}
	public void setScheId(String scheId) {
		this.scheId = scheId;
	}
	public String getSerDate() {
		return serDate;
	}
	public void setSerDate(String serDate) {
		this.serDate = serDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

    
}
