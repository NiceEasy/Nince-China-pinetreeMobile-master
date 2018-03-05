package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 客户实体
 * 
 * @author Administrator
 * 
 */
public class Customers implements Serializable {
	private String message;
	private String success;
	private List<Customer> resultData;

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

	public List<Customer> getResultData() {
		return resultData;
	}

	public void setResultData(List<Customer> resultData) {
		this.resultData = resultData;
	}

	
}
