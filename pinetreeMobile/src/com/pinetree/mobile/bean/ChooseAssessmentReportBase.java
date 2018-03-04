package com.pinetree.mobile.bean;

import java.io.Serializable;

public class ChooseAssessmentReportBase implements Serializable{

	private String message;
	private String success;
	private ChooseAssessmentReportData resultData;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public ChooseAssessmentReportData getResultData() {
		return resultData;
	}
	public void setResultData(ChooseAssessmentReportData resultData) {
		this.resultData = resultData;
	}
	
	
}
