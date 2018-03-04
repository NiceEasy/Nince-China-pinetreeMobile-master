package com.pinetree.mobile.bean;

import java.util.List;

public class SignResult {
	private String message;
	private String success;
	private List<Customer> resultData;
	
	public List<Customer> getResultData() {
		return resultData;
	}
	public void setResultData(List<Customer> resultData) {
		this.resultData = resultData;
	}
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
	
	
}
