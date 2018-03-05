package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 日常生活自理能力表（ADL)评估
 *
 */

public class EverydayStateBase implements Serializable{

	private String message;
	private String success;
	private List<EverydayState> resultData;
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
	public List<EverydayState> getResultData() {
		return resultData;
	}
	public void setResultData(List<EverydayState> resultData) {
		this.resultData = resultData;
	}
	
	
	
}
