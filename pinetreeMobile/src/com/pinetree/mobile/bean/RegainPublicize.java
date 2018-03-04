package com.pinetree.mobile.bean;

import java.io.Serializable;

public class RegainPublicize implements Serializable{
	/**
	 * 健康宣教
	 */
	private String planID;//康复护理计划ID
	private String num;//序号
	private String bigTitle;//健康宣教大标题
	private String smallTitle;//健康宣教大标题
	private String content;//健康宣教内容
	
	private String id;
	private String fatherid;//父级ID
	private String type;//类型（0：大标题，1：小标题，2：内容）
	private String title;//康复措施（健康宣教）标题
	private String bigTitleId;//大标题id
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBigTitleId() {
		return bigTitleId;
	}
	public void setBigTitleId(String bigTitleId) {
		this.bigTitleId = bigTitleId;
	}
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
	public String getBigTitle() {
		return bigTitle;
	}
	public void setBigTitle(String bigTitle) {
		this.bigTitle = bigTitle;
	}
	public String getSmallTitle() {
		return smallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
