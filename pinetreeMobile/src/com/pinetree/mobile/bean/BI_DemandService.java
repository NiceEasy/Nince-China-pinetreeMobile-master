package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @������ ������Ϣ@������� BI_DemandService ID ID ���浥�� ReportId ���������� ServiceDemandCode ������������ ServiceDemandName ����Ƶ�ʱ�� ServiceFrequencyCode ����Ƶ������ ServiceFrequencyName ����Ƶ������˵�� ServiceFrequencyOtherDesc ��ע
 *      Remark
 * 
 * @author wcm
 * @createDate 2015-9-1 ����10:32:04
 */
public class BI_DemandService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String ServiceDemandCode;
	String ServiceDemandName;
	String ServiceFrequencyCode;
	String ServiceFrequencyName;
	String ServiceFrequencyOtherDesc;
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

	public String getServiceDemandCode() {
		return ServiceDemandCode;
	}

	public void setServiceDemandCode(String serviceDemandCode) {
		ServiceDemandCode = serviceDemandCode;
	}

	public String getServiceDemandName() {
		return ServiceDemandName;
	}

	public void setServiceDemandName(String serviceDemandName) {
		ServiceDemandName = serviceDemandName;
	}

	public String getServiceFrequencyCode() {
		return ServiceFrequencyCode;
	}

	public void setServiceFrequencyCode(String serviceFrequencyCode) {
		ServiceFrequencyCode = serviceFrequencyCode;
	}

	public String getServiceFrequencyName() {
		return ServiceFrequencyName;
	}

	public void setServiceFrequencyName(String serviceFrequencyName) {
		ServiceFrequencyName = serviceFrequencyName;
	}

	public String getServiceFrequencyOtherDesc() {
		return ServiceFrequencyOtherDesc;
	}

	public void setServiceFrequencyOtherDesc(String serviceFrequencyOtherDesc) {
		ServiceFrequencyOtherDesc = serviceFrequencyOtherDesc;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
