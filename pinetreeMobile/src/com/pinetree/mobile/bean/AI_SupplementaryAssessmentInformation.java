package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@����������Ϣ AI_SupplementaryAssessmentInformation
 * 
 *      Name Code ID ID ���浥�� ReportId �� ��˵�� DementiaExplain �մ���� DementiaCode ���񼲲���� MentalSicknessCode ���񼲲�˵�� MentalSicknessExplain ����˵�� FallExplain ������� FallCode ҭʳ˵�� ChokeExplain ҭʳ��� ChokeCode
 *      ��ʧ��� BelostCode ��ʧ˵�� BelostExplain ��ɱ��� IdioctoniaCode ��ɱ˵�� IdioctoniaExplain ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 ����10:40:19
 */
public class AI_SupplementaryAssessmentInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String DementiaExplain;
	String DementiaCode;
	String MentalSicknessCode;
	String MentalSicknessExplain;
	String FallExplain;
	String FallCode;
	String ChokeExplain;
	String ChokeCode;
	String BelostCode;
	String BelostExplain;
	String IdioctoniaCode;
	String IdioctoniaExplain;
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

	public String getDementiaExplain() {
		return DementiaExplain;
	}

	public void setDementiaExplain(String dementiaExplain) {
		DementiaExplain = dementiaExplain;
	}

	public String getDementiaCode() {
		return DementiaCode;
	}

	public void setDementiaCode(String dementiaCode) {
		DementiaCode = dementiaCode;
	}

	public String getMentalSicknessCode() {
		return MentalSicknessCode;
	}

	public void setMentalSicknessCode(String mentalSicknessCode) {
		MentalSicknessCode = mentalSicknessCode;
	}

	public String getMentalSicknessExplain() {
		return MentalSicknessExplain;
	}

	public void setMentalSicknessExplain(String mentalSicknessExplain) {
		MentalSicknessExplain = mentalSicknessExplain;
	}

	public String getFallExplain() {
		return FallExplain;
	}

	public void setFallExplain(String fallExplain) {
		FallExplain = fallExplain;
	}

	public String getFallCode() {
		return FallCode;
	}

	public void setFallCode(String fallCode) {
		FallCode = fallCode;
	}

	public String getChokeExplain() {
		return ChokeExplain;
	}

	public void setChokeExplain(String chokeExplain) {
		ChokeExplain = chokeExplain;
	}

	public String getChokeCode() {
		return ChokeCode;
	}

	public void setChokeCode(String chokeCode) {
		ChokeCode = chokeCode;
	}

	public String getBelostCode() {
		return BelostCode;
	}

	public void setBelostCode(String belostCode) {
		BelostCode = belostCode;
	}

	public String getBelostExplain() {
		return BelostExplain;
	}

	public void setBelostExplain(String belostExplain) {
		BelostExplain = belostExplain;
	}

	public String getIdioctoniaCode() {
		return IdioctoniaCode;
	}

	public void setIdioctoniaCode(String idioctoniaCode) {
		IdioctoniaCode = idioctoniaCode;
	}

	public String getIdioctoniaExplain() {
		return IdioctoniaExplain;
	}

	public void setIdioctoniaExplain(String idioctoniaExplain) {
		IdioctoniaExplain = idioctoniaExplain;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
