package com.pinetree.mobile.bean;

import java.io.Serializable;

public class RegainTarget implements Serializable{
/**
 * 康复护理目标
 */
	private String planID;//康复护理计划ID
	private String num;//序号
	private String content;//康复护理目标内容
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
