package com.pinetree.mobile.bean;

import java.io.File;
import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ���Ϸ�����Ϣ�ϱ�ʵ�� YangLaoServiceAssessmentReport ID ID ���浥�ţ�������ţ� ReportId ������ Applicant ������� ReportDate ��ŵ����1 PromiseDesc1 ��ŵ����2 PromiseDesc2 ��ŵ����3 PromiseDesc3 ��ע Remark
 *      BI_IDInformation��BI_PersonalInformation��Ϊ���ʵ�� ��ƷID prodID �Ƿ�������״̬ 0��������1���ճ̶�Ӧ���棩 whetherNew
 * @author wcm
 * @createDate 2015-9-1 ����9:45:45
 */
public class YangLaoServiceAssessmentReportAll implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String custID;
	String ReportId;
	File Applicant;
	String ReportDate;
	String PromiseDesc1;
	String PromiseDesc2;
	String PromiseDesc3;
	String Remark;
	String beginTime;
	String endTime;
	String projectId;
	String scheduleId;
	String lat;
	String lng;
	String employeeID;
	String employeeName;
	String empNum;
	String relation;
	String prodID;
	String whetherNew;
	String isUpload;

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	/* ������Ϣ@�����Ϣ */
	BI_IDInformation BI_IDInformation;
	/* ������Ϣ@������Ϣ */
	BI_PersonalInformation BI_PersonalInformation;
	/* ������Ϣ@�໤��/������ϵ����Ϣ */
	BI_GuardianAndEmergencyContactInformation BI_GuardianAndEmergencyContactInformation;
	/* ������Ϣ@Ŀǰ����״�� */
	BI_NowLivingCondition BI_NowLivingCondition;
	/* ������Ϣ@��ȷ��ļ��� */
	BI_DiagnosedDisease BI_DiagnosedDisease;
	/* ������Ϣ@��ͥ��Ҫ�ջ�����Ϣ */
	BI_HomePrimaryCargiversInformation BI_HomePrimaryCargiversInformation;
	/* ������Ϣ@�ⲿ�ṩ��רҵ�����������7 �죩 */
	BI_ExternalProfessionalService BI_ExternalProfessionalService;
	/* ������Ϣ@��Ϣ�ɼ�������� */
	BI_EarlyResults BI_EarlyResults;
	/* ������Ϣ@�Ӽ��ջ�����Ա��Ϣ */
	BI_ManagementInformation BI_ManagementInformation;
	/* ������Ϣ@������� */
	BI_DemandService BI_DemandService;
	/* ������Ϣ@������Ϣ */
	BI_SupplementaryInformation BI_SupplementaryInformation;

	/* ������Ϣ@����������Ϣ */
	AI_AssessmentBasisInformation AI_AssessmentBasisInformation;
	/* ������Ϣ@�ճ��������� */
	AI_ActivityofDailyLliving AI_ActivityofDailyLliving;
	/* ������Ϣ@����״̬ */
	AI_MentalState AI_MentalState;
	/* ������Ϣ@��֪���빵ͨ */
	AI_SensoryPerceptionAndCommunication AI_SensoryPerceptionAndCommunication;
	/* ������Ϣ@������ */
	AI_SocialParticipation AI_SocialParticipation;
	/* ������Ϣ@����������Ϣ */
	AI_SupplementaryAssessmentInformation AI_SupplementaryAssessmentInformation;
	/* ������Ϣ@������������ */
	AI_AbilityAssessmentConclusion AI_AbilityAssessmentConclusion;
	/* ������Ϣ@��������Ա��Ϣ */
	AI_MainAssessorInformation AI_MainAssessorInformation;
	/* ������Ϣ@��������˵�� */
	AI_AssessmentSupplementaryInformation AI_AssessmentSupplementaryInformation;

	/* ����ģ��@���˲�����Ϣ */
	SF_PersonalSupplement SF_PersonalSupplement;
	/* ����ģ��@����״̬������� */
	SF_MentalStateAssessment SF_MentalStateAssessment;
	/*����ģ��@�˶���������*/
	SF_MFA SF_MFA;
	/*����ģ��@COPM*/
	SF_COPM SF_COPM;

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

	public File getApplicant() {
		return Applicant;
	}

	public void setApplicant(File applicant) {
		Applicant = applicant;
	}

	public String getReportDate() {
		return ReportDate;
	}

	public void setReportDate(String reportDate) {
		ReportDate = reportDate;
	}

	public String getPromiseDesc1() {
		return PromiseDesc1;
	}

	public void setPromiseDesc1(String promiseDesc1) {
		PromiseDesc1 = promiseDesc1;
	}

	public String getPromiseDesc2() {
		return PromiseDesc2;
	}

	public void setPromiseDesc2(String promiseDesc2) {
		PromiseDesc2 = promiseDesc2;
	}

	public String getPromiseDesc3() {
		return PromiseDesc3;
	}

	public void setPromiseDesc3(String promiseDesc3) {
		PromiseDesc3 = promiseDesc3;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getProdID() {
		return prodID;
	}

	public void setProdID(String prodID) {
		this.prodID = prodID;
	}

	public String getWhetherNew() {
		return whetherNew;
	}

	public void setWhetherNew(String whetherNew) {
		this.whetherNew = whetherNew;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public BI_IDInformation getBI_IDInformation() {
		return BI_IDInformation;
	}

	public void setBI_IDInformation(BI_IDInformation bI_IDInformation) {
		BI_IDInformation = bI_IDInformation;
	}

	public BI_PersonalInformation getBI_PersonalInformation() {
		return BI_PersonalInformation;
	}

	public void setBI_PersonalInformation(BI_PersonalInformation bI_PersonalInformation) {
		BI_PersonalInformation = bI_PersonalInformation;
	}

	public BI_GuardianAndEmergencyContactInformation getBI_GuardianAndEmergencyContactInformation() {
		return BI_GuardianAndEmergencyContactInformation;
	}

	public void setBI_GuardianAndEmergencyContactInformation(BI_GuardianAndEmergencyContactInformation bI_GuardianAndEmergencyContactInformation) {
		BI_GuardianAndEmergencyContactInformation = bI_GuardianAndEmergencyContactInformation;
	}

	public BI_NowLivingCondition getBI_NowLivingCondition() {
		return BI_NowLivingCondition;
	}

	public void setBI_NowLivingCondition(BI_NowLivingCondition bI_NowLivingCondition) {
		BI_NowLivingCondition = bI_NowLivingCondition;
	}

	public BI_DiagnosedDisease getBI_DiagnosedDisease() {
		return BI_DiagnosedDisease;
	}

	public void setBI_DiagnosedDisease(BI_DiagnosedDisease bI_DiagnosedDisease) {
		BI_DiagnosedDisease = bI_DiagnosedDisease;
	}

	public BI_HomePrimaryCargiversInformation getBI_HomePrimaryCargiversInformation() {
		return BI_HomePrimaryCargiversInformation;
	}

	public void setBI_HomePrimaryCargiversInformation(BI_HomePrimaryCargiversInformation bI_HomePrimaryCargiversInformation) {
		BI_HomePrimaryCargiversInformation = bI_HomePrimaryCargiversInformation;
	}

	public BI_ExternalProfessionalService getBI_ExternalProfessionalService() {
		return BI_ExternalProfessionalService;
	}

	public void setBI_ExternalProfessionalService(BI_ExternalProfessionalService bI_ExternalProfessionalService) {
		BI_ExternalProfessionalService = bI_ExternalProfessionalService;
	}

	public BI_EarlyResults getBI_EarlyResults() {
		return BI_EarlyResults;
	}

	public void setBI_EarlyResults(BI_EarlyResults bI_EarlyResults) {
		BI_EarlyResults = bI_EarlyResults;
	}

	public BI_ManagementInformation getBI_ManagementInformation() {
		return BI_ManagementInformation;
	}

	public void setBI_ManagementInformation(BI_ManagementInformation bI_ManagementInformation) {
		BI_ManagementInformation = bI_ManagementInformation;
	}

	public BI_DemandService getBI_DemandService() {
		return BI_DemandService;
	}

	public void setBI_DemandService(BI_DemandService bI_DemandService) {
		BI_DemandService = bI_DemandService;
	}

	public BI_SupplementaryInformation getBI_SupplementaryInformation() {
		return BI_SupplementaryInformation;
	}

	public void setBI_SupplementaryInformation(BI_SupplementaryInformation bI_SupplementaryInformation) {
		BI_SupplementaryInformation = bI_SupplementaryInformation;
	}

	public AI_AssessmentBasisInformation getAI_AssessmentBasisInformation() {
		return AI_AssessmentBasisInformation;
	}

	public void setAI_AssessmentBasisInformation(AI_AssessmentBasisInformation aI_AssessmentBasisInformation) {
		AI_AssessmentBasisInformation = aI_AssessmentBasisInformation;
	}

	public AI_ActivityofDailyLliving getAI_ActivityofDailyLliving() {
		return AI_ActivityofDailyLliving;
	}

	public void setAI_ActivityofDailyLliving(AI_ActivityofDailyLliving aI_ActivityofDailyLliving) {
		AI_ActivityofDailyLliving = aI_ActivityofDailyLliving;
	}

	public AI_MentalState getAI_MentalState() {
		return AI_MentalState;
	}

	public void setAI_MentalState(AI_MentalState aI_MentalState) {
		AI_MentalState = aI_MentalState;
	}

	public AI_SensoryPerceptionAndCommunication getAI_SensoryPerceptionAndCommunication() {
		return AI_SensoryPerceptionAndCommunication;
	}

	public void setAI_SensoryPerceptionAndCommunication(AI_SensoryPerceptionAndCommunication aI_SensoryPerceptionAndCommunication) {
		AI_SensoryPerceptionAndCommunication = aI_SensoryPerceptionAndCommunication;
	}

	public AI_SocialParticipation getAI_SocialParticipation() {
		return AI_SocialParticipation;
	}

	public void setAI_SocialParticipation(AI_SocialParticipation aI_SocialParticipation) {
		AI_SocialParticipation = aI_SocialParticipation;
	}

	public AI_SupplementaryAssessmentInformation getAI_SupplementaryAssessmentInformation() {
		return AI_SupplementaryAssessmentInformation;
	}

	public void setAI_SupplementaryAssessmentInformation(AI_SupplementaryAssessmentInformation aI_SupplementaryAssessmentInformation) {
		AI_SupplementaryAssessmentInformation = aI_SupplementaryAssessmentInformation;
	}

	public AI_AbilityAssessmentConclusion getAI_AbilityAssessmentConclusion() {
		return AI_AbilityAssessmentConclusion;
	}

	public void setAI_AbilityAssessmentConclusion(AI_AbilityAssessmentConclusion aI_AbilityAssessmentConclusion) {
		AI_AbilityAssessmentConclusion = aI_AbilityAssessmentConclusion;
	}

	public AI_MainAssessorInformation getAI_MainAssessorInformation() {
		return AI_MainAssessorInformation;
	}

	public void setAI_MainAssessorInformation(AI_MainAssessorInformation aI_MainAssessorInformation) {
		AI_MainAssessorInformation = aI_MainAssessorInformation;
	}

	public AI_AssessmentSupplementaryInformation getAI_AssessmentSupplementaryInformation() {
		return AI_AssessmentSupplementaryInformation;
	}

	public void setAI_AssessmentSupplementaryInformation(AI_AssessmentSupplementaryInformation aI_AssessmentSupplementaryInformation) {
		AI_AssessmentSupplementaryInformation = aI_AssessmentSupplementaryInformation;
	}

	public SF_PersonalSupplement getSF_PersonalSupplement() {
		return SF_PersonalSupplement;
	}

	public void setSF_PersonalSupplement(SF_PersonalSupplement sF_PersonalSupplement) {
		SF_PersonalSupplement = sF_PersonalSupplement;
	}

	public SF_MentalStateAssessment getSF_MentalStateAssessment() {
		return SF_MentalStateAssessment;
	}

	public void setSF_MentalStateAssessment(SF_MentalStateAssessment sF_MentalStateAssessment) {
		SF_MentalStateAssessment = sF_MentalStateAssessment;
	}

	public SF_MFA getSF_MFA() {
		return SF_MFA;
	}

	public void setSF_MFA(SF_MFA sF_MFA) {
		SF_MFA = sF_MFA;
	}

	public SF_COPM getSF_COPM() {
		return SF_COPM;
	}

	public void setSF_COPM(SF_COPM sF_COPM) {
		SF_COPM = sF_COPM;
	}

}
