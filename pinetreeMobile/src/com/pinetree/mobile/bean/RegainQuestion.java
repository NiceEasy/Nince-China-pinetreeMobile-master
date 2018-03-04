package com.pinetree.mobile.bean;

import java.io.Serializable;

public class RegainQuestion implements Serializable{
/**
 * 现存问题
 */
	
	private String planID;//康复护理计划ID
	private String num;//序号
	private String question;//问题
	private String qtype;//问题类型(0:现存问题 1:拟解决问题)
	private String term;//期限（0：短期，1：中期，2：长期）
	private String sortIndex;//排序号
	
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}
	
	
	
}
