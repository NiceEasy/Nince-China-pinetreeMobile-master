package com.pinetree.mobile.bean;

import java.util.List;

public class CopmBase {

	//COPM
	private String message;
	private String success;
	private List<Copm> resultData;
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
	public List<Copm> getResultData() {
		return resultData;
	}
	public void setResultData(List<Copm> resultData) {
		this.resultData = resultData;
	}
	
	
}
