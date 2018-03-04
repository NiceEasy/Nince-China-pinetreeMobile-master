package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@�ⲿ�ṩ��רҵ�����������7 �죩 BI_ExternalProfessionalService ID ID ���浥�� ReportId ���ʻ����֧��ʱ�� EmotionDuration ���������ջ�ʱ�� PersonalLifeDuration �Ӽ�����֧��ʱ�� HomeLifeDuration ����Ŀ�� NursingGoal ��������ı�
 *      NursingDemandChange ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:26:23
 */
public class BI_ExternalProfessionalService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String EmotionDuration;
	String PersonalLifeDuration;
	String HomeLifeDuration;
	String NursingGoal;
	String NursingDemandChange;
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

	public String getEmotionDuration() {
		return EmotionDuration;
	}

	public void setEmotionDuration(String emotionDuration) {
		EmotionDuration = emotionDuration;
	}

	public String getPersonalLifeDuration() {
		return PersonalLifeDuration;
	}

	public void setPersonalLifeDuration(String personalLifeDuration) {
		PersonalLifeDuration = personalLifeDuration;
	}

	public String getHomeLifeDuration() {
		return HomeLifeDuration;
	}

	public void setHomeLifeDuration(String homeLifeDuration) {
		HomeLifeDuration = homeLifeDuration;
	}

	public String getNursingGoal() {
		return NursingGoal;
	}

	public void setNursingGoal(String nursingGoal) {
		NursingGoal = nursingGoal;
	}

	public String getNursingDemandChange() {
		return NursingDemandChange;
	}

	public void setNursingDemandChange(String nursingDemandChange) {
		NursingDemandChange = nursingDemandChange;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
