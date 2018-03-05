package com.pinetree.mobile.bean;

import java.util.List;

/**
 * 全局返回结果
 * @author Administrator
 *
 */
public class GlobalResult {
	private String message;
	private String success;
	private List<ServerRecord> resultData;
	
	
	public List<ServerRecord> getResultData() {
		return resultData;
	}
	public void setResultData(List<ServerRecord> resultData) {
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
