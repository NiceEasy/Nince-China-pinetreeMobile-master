package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@��������˵�� AI_AssessmentSupplementaryInformation
 * 
 *      Name Code ID ID ���浥�� ReportId ������Ϣ AssessmentSupplementaryInfo ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 ����10:44:29
 */
public class AI_AssessmentSupplementaryInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String AssessmentSupplementaryInfo;
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

	public String getAssessmentSupplementaryInfo() {
		return AssessmentSupplementaryInfo;
	}

	public void setAssessmentSupplementaryInfo(String assessmentSupplementaryInfo) {
		AssessmentSupplementaryInfo = assessmentSupplementaryInfo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
