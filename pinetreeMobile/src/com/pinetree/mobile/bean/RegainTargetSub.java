package com.pinetree.mobile.bean;

import java.io.Serializable;

public class RegainTargetSub implements Serializable{
/**
 * 康复措施
 */
	private String targetID;//康复护理目标ID
	private String num;//序号
	private String content;//康复护理措施内容
	private String bigTitle;//大标题
	private String smallTitle;//小标题
	
	private String planID;//康复护理计划ID
	private String planNum;//计划数量
	private String realNum;//实际数量
	private String sortIndex;//排序号
	private int tvId;//某条措施的删除tv的ID
	
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
	public int getTvId() {
		return tvId;
	}
	public void setTvId(int tvId) {
		this.tvId = tvId;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
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
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getPlanNum() {
		return planNum;
	}
	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}
	public String getRealNum() {
		return realNum;
	}
	public void setRealNum(String realNum) {
		this.realNum = realNum;
	}
	public String getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}
	
	
}
