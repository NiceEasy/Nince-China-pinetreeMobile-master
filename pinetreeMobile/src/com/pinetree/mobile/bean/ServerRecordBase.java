package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 服务记录
 */
public class ServerRecordBase implements Serializable{
	private String message;
	private String success;
	private List<ServerRecord> resultData;
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
	public List<ServerRecord> getResultData() {
		return resultData;
	}
	public void setResultData(List<ServerRecord> resultData) {
		this.resultData = resultData;
	}
	
	
}
