package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 查体-基本 回显
 *
 */
public class DrugStateBase implements Serializable{

	private String message;
	private String success;
	private List<DrugState> resultData;
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
	public List<DrugState> getResultData() {
		return resultData;
	}
	public void setResultData(List<DrugState> resultData) {
		this.resultData = resultData;
	}
	
	
	
}
