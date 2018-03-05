package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@����������Ϣ AI_AssessmentBasisInformation Name Code ID ID ���浥�� ReportId ��Ϣ�ṩ���Ƿ��� isSelf ���� Name ���� Age �����˹�ϵ RelationWithOldPeople ��ϵ�绰 Telephone ����ԭ�� AssessmentReason ɸ�� Screening ����ԭ���������Ϣ
 *      AssessmentReasonOther ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 ����10:30:20
 */
public class AI_AssessmentBasisInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String isSelf;
	String Name;
	String Age;
	String RelationWithOldPeople;
	String Telephone;
	String AssessmentReason;
	String Screening;
	String AssessmentReasonOther;
	String Remark;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getReportId() {
		return ReportId;
	}

	public void setReportId(String reportId) {
		ReportId = reportId;
	}

	public String getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getRelationWithOldPeople() {
		return RelationWithOldPeople;
	}

	public void setRelationWithOldPeople(String relationWithOldPeople) {
		RelationWithOldPeople = relationWithOldPeople;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getAssessmentReason() {
		return AssessmentReason;
	}

	public void setAssessmentReason(String assessmentReason) {
		AssessmentReason = assessmentReason;
	}

	public String getScreening() {
		return Screening;
	}

	public void setScreening(String screening) {
		Screening = screening;
	}

	public String getAssessmentReasonOther() {
		return AssessmentReasonOther;
	}

	public void setAssessmentReasonOther(String assessmentReasonOther) {
		AssessmentReasonOther = assessmentReasonOther;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
