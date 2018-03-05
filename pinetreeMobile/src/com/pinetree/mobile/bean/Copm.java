package com.pinetree.mobile.bean;

public class Copm {

	private static final long serialVersionUID = 1L;
	private String scheID;
	private String custID;
	private String content;//内容
    private String situation;//现状
    private String satisf;//满意度
    private String xianPoint;//现状评分
    private String zuoyeChange;//作业活动表现变化
    private String manPoint;//满意度评分
    private String manChange;//满意度变化
    private String number;//数据第几条
    private String createTime;
    private String unmodifiableState;
    private String isLast;//是否上次带出（1：否，0：是） 0表示不可更改数据
    private String saveState;
    private String submitState;
    private String situationSum;// 历史现状总分
    private String satisfSum;//历史满意度总分
    private String isNew;//0:最新评估报告
    private String specialAccount;//评估师特殊交代

    
	public String getSpecialAccount() {
		return specialAccount;
	}
	public void setSpecialAccount(String specialAccount) {
		this.specialAccount = specialAccount;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getSituationSum() {
		return situationSum;
	}
	public void setSituationSum(String situationSum) {
		this.situationSum = situationSum;
	}
	public String getSatisfSum() {
		return satisfSum;
	}
	public void setSatisfSum(String satisfSum) {
		this.satisfSum = satisfSum;
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
	public String getIsLast() {
		return isLast;
	}
	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}
	public String getUnmodifiableState() {
		return unmodifiableState;
	}
	public void setUnmodifiableState(String unmodifiableState) {
		this.unmodifiableState = unmodifiableState;
	}
	public String getScheID() {
		return scheID;
	}
	public void setScheID(String scheID) {
		this.scheID = scheID;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getXianPoint() {
		return xianPoint;
	}
	public void setXianPoint(String xianPoint) {
		this.xianPoint = xianPoint;
	}
	public String getZuoyeChange() {
		return zuoyeChange;
	}
	public void setZuoyeChange(String zuoyeChange) {
		this.zuoyeChange = zuoyeChange;
	}
	public String getManPoint() {
		return manPoint;
	}
	public void setManPoint(String manPoint) {
		this.manPoint = manPoint;
	}
	public String getManChange() {
		return manChange;
	}
	public void setManChange(String manChange) {
		this.manChange = manChange;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getSatisf() {
		return satisf;
	}
	public void setSatisf(String satisf) {
		this.satisf = satisf;
	}
    
    

}
