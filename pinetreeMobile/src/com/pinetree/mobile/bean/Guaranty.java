package com.pinetree.mobile.bean;

import java.util.List;

/**
 * 担保
 * @author Administrator
 *
 */
public class Guaranty {
	private String message;
	private String success;
	private List<GuarantyMoney> resultData;
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
	public List<GuarantyMoney> getResultData() {
		return resultData;
	}
	public void setResultData(List<GuarantyMoney> resultData) {
		this.resultData = resultData;
	}
	
	
}
