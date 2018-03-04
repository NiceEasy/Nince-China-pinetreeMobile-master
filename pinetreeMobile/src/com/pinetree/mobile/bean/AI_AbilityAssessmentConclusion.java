package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@������������ AI_AbilityAssessmentConclusion
 * Name Code ID ID 报告单号 ReportId 功能等级评定结果-日常生活分级 DailyLevel 功能等级评定结果-日常生活分级说明DailyLevelExplain 
 * 功能等级评定结果-精神状态分级 MentalLevel 功能等级评定结果-精神状态分级说明 MentalLevelExplain 
 * 功能等级评定结果-感知觉与沟通分级 SensoryLevel功能等级评定结果-感知觉与沟通分级说明 SensoryLevelExplain 
 * 功能等级评定结果-社会参与分级 SociatyLevel 功能等级评定结果-社会参与分级说明SociatyLevelExplain 
 * 能力等级评定结果-能力等级初评结果 InitialLevel 能力等级评定结果-能力等级初评结果说明 InitialLevelExplain 
 * 等级变更依据ChangeBasis 能力最终等级 LastLevel 评估人一姓名 FirstName 评估人一联系电FirstHomePhone 
 * 评估人二姓名 SecondName 评估人二联系电话 SecondHomePhone 审核人姓名 VerifyName 审核人联系电话VerifyMobile 
 * 评估日期 AssessDate 审核日期 VerifyDate 备注 Remark
 * @author wcm
 * @createDate 2015-9-2 ����10:41:46
 */
public class AI_AbilityAssessmentConclusion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String DailyLevel;
	String DailyLevelExplain;
	String MentalLevel;
	String MentalLevelExplain;
	String SensoryLevel;
	String SensoryLevelExplain;
	String SociatyLevel;
	String SociatyLevelExplain;
	String InitialLevel;
	String InitialLevelExplain;
	String ChangeBasis;
	String LastLevel;
	String FirstName;
	String FirstHomePhone;
	String SecondName;
	String SecondHomePhone;
	String VerifyName;
	String VerifyMobile;
	String AssessDate;
	String VerifyDate;
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

	public String getDailyLevel() {
		return DailyLevel;
	}

	public void setDailyLevel(String dailyLevel) {
		DailyLevel = dailyLevel;
	}

	public String getDailyLevelExplain() {
		return DailyLevelExplain;
	}

	public void setDailyLevelExplain(String dailyLevelExplain) {
		DailyLevelExplain = dailyLevelExplain;
	}

	public String getMentalLevel() {
		return MentalLevel;
	}

	public void setMentalLevel(String mentalLevel) {
		MentalLevel = mentalLevel;
	}

	public String getMentalLevelExplain() {
		return MentalLevelExplain;
	}

	public void setMentalLevelExplain(String mentalLevelExplain) {
		MentalLevelExplain = mentalLevelExplain;
	}

	public String getSensoryLevel() {
		return SensoryLevel;
	}

	public void setSensoryLevel(String sensoryLevel) {
		SensoryLevel = sensoryLevel;
	}

	public String getSensoryLevelExplain() {
		return SensoryLevelExplain;
	}

	public void setSensoryLevelExplain(String sensoryLevelExplain) {
		SensoryLevelExplain = sensoryLevelExplain;
	}

	public String getSociatyLevel() {
		return SociatyLevel;
	}

	public void setSociatyLevel(String sociatyLevel) {
		SociatyLevel = sociatyLevel;
	}

	public String getSociatyLevelExplain() {
		return SociatyLevelExplain;
	}

	public void setSociatyLevelExplain(String sociatyLevelExplain) {
		SociatyLevelExplain = sociatyLevelExplain;
	}

	public String getInitialLevel() {
		return InitialLevel;
	}

	public void setInitialLevel(String initialLevel) {
		InitialLevel = initialLevel;
	}

	public String getInitialLevelExplain() {
		return InitialLevelExplain;
	}

	public void setInitialLevelExplain(String initialLevelExplain) {
		InitialLevelExplain = initialLevelExplain;
	}

	public String getChangeBasis() {
		return ChangeBasis;
	}

	public void setChangeBasis(String changeBasis) {
		ChangeBasis = changeBasis;
	}

	public String getLastLevel() {
		return LastLevel;
	}

	public void setLastLevel(String lastLevel) {
		LastLevel = lastLevel;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getFirstHomePhone() {
		return FirstHomePhone;
	}

	public void setFirstHomePhone(String firstHomePhone) {
		FirstHomePhone = firstHomePhone;
	}

	public String getSecondName() {
		return SecondName;
	}

	public void setSecondName(String secondName) {
		SecondName = secondName;
	}

	public String getSecondHomePhone() {
		return SecondHomePhone;
	}

	public void setSecondHomePhone(String secondHomePhone) {
		SecondHomePhone = secondHomePhone;
	}

	public String getVerifyName() {
		return VerifyName;
	}

	public void setVerifyName(String verifyName) {
		VerifyName = verifyName;
	}

	public String getVerifyMobile() {
		return VerifyMobile;
	}

	public void setVerifyMobile(String verifyMobile) {
		VerifyMobile = verifyMobile;
	}

	public String getAssessDate() {
		return AssessDate;
	}

	public void setAssessDate(String assessDate) {
		AssessDate = assessDate;
	}

	public String getVerifyDate() {
		return VerifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		VerifyDate = verifyDate;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
