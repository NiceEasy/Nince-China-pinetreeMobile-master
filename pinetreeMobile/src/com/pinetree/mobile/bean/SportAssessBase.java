package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 查体-运动功能评定 回显
 *
 */
public class SportAssessBase implements Serializable{

	private String message;
	private String success;
	private List<SportAssess> resultData;
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
	public List<SportAssess> getResultData() {
		return resultData;
	}
	public void setResultData(List<SportAssess> resultData) {
		this.resultData = resultData;
	}
	
	
}
