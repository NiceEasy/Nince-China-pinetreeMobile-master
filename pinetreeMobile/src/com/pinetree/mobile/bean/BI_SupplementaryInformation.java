package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@������Ϣ BI_SupplementaryInformation ID ID ���浥�� ReportId ������Ϣ SupplementaryInfo ��ע Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:33:52
 */
public class BI_SupplementaryInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String SupplementaryInfo;
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

	public String getSupplementaryInfo() {
		return SupplementaryInfo;
	}

	public void setSupplementaryInfo(String supplementaryInfo) {
		SupplementaryInfo = supplementaryInfo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
