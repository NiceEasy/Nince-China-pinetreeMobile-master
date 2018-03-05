package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 基本情况 回显
 *
 */
public class BasicInformationBase implements Serializable{

	private String message;
	private String success;
	private List<BasicInformation> resultData;
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
	public List<BasicInformation> getResultData() {
		return resultData;
	}
	public void setResultData(List<BasicInformation> resultData) {
		this.resultData = resultData;
	}
	
	
}
