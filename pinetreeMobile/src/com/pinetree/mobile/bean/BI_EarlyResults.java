package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * ������Ϣ@��Ϣ�ɼ�������� BI_EarlyResults ID ID ���浥�� ReportId ����ӡ���ţ�ƴ���� FirstImpressionCodes ����ӡ�����ݣ�ƴ���� FirstImpressionContents �������飨ƴ���� FirstAdvices ��ע Remark ����ӡ���������Ϣ1 FirstImpressionOtherOne ����ӡ���������Ϣ2
 * FirstImpressionOtherTwo ��������������Ϣ FirstAdvicesOther
 * 
 * @������
 * @author wcm
 * @createDate 2015-9-1 ����10:27:42
 */
public class BI_EarlyResults implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String FirstImpressionCodes;
	String FirstImpressionContents;
	String FirstAdvices;
	String FirstImpressionOtherOne;
	String FirstImpressionOtherTwo;
	String FirstAdvicesOther;

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

	public String getFirstImpressionCodes() {
		return FirstImpressionCodes;
	}

	public void setFirstImpressionCodes(String firstImpressionCodes) {
		FirstImpressionCodes = firstImpressionCodes;
	}

	public String getFirstImpressionContents() {
		return FirstImpressionContents;
	}

	public void setFirstImpressionContents(String firstImpressionContents) {
		FirstImpressionContents = firstImpressionContents;
	}

	public String getFirstAdvices() {
		return FirstAdvices;
	}

	public void setFirstAdvices(String firstAdvices) {
		FirstAdvices = firstAdvices;
	}

	public String getFirstImpressionOtherOne() {
		return FirstImpressionOtherOne;
	}

	public void setFirstImpressionOtherOne(String firstImpressionOtherOne) {
		FirstImpressionOtherOne = firstImpressionOtherOne;
	}

	public String getFirstImpressionOtherTwo() {
		return FirstImpressionOtherTwo;
	}

	public void setFirstImpressionOtherTwo(String firstImpressionOtherTwo) {
		FirstImpressionOtherTwo = firstImpressionOtherTwo;
	}

	public String getFirstAdvicesOther() {
		return FirstAdvicesOther;
	}

	public void setFirstAdvicesOther(String firstAdvicesOther) {
		FirstAdvicesOther = firstAdvicesOther;
	}


	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
