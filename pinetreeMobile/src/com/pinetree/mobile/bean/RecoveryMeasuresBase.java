package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class RecoveryMeasuresBase implements Serializable{

	private String message;
	private String success;
	private List<RehabilitationMeasures> resultData;
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
	public List<RehabilitationMeasures> getResultData() {
		return resultData;
	}
	public void setResultData(List<RehabilitationMeasures> resultData) {
		this.resultData = resultData;
	}
	
	
}
