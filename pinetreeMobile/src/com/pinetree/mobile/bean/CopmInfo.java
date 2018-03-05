package com.pinetree.mobile.bean;

import java.io.Serializable;

public class CopmInfo implements Serializable {
	private static final long serialVersionUID = 1L;
      private String createTime;//�ճ�ID
      private String id;//�ճ�ID
	private String num;//�ճ�ID
	private String pgID;//�ճ�ID
	private String signDate;//�ճ�ID
	private String scheId;//�ճ�ID
	private String custId;//�ͻ�ID
	private String content;//����(Ŀǰ������)
	private String situation;//��״
	private String satisf;//�����
	private String isLast;//�Ƿ��ϴδ�����1����0���ǣ� 0��ʾ���ɸ�������
	private String situationSum;// ��ʷ��״�ܷ�
	private String satisfSum;//��ʷ������ܷ�
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPgID() {
		return pgID;
	}
	public void setPgID(String pgID) {
		this.pgID = pgID;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getScheId() {
		return scheId;
	}
	public void setScheId(String scheId) {
		this.scheId = scheId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
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
	public String getIsLast() {
		return isLast;
	}
	public void setIsLast(String isLast) {
		this.isLast = isLast;
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
	
	
	
}
