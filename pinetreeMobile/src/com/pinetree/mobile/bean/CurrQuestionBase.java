package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class CurrQuestionBase implements Serializable{

	private String message;
	private String success;
	private List<RegainQuestion> resultData;
	
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
	public List<RegainQuestion> getResultData() {
		return resultData;
	}
	public void setResultData(List<RegainQuestion> resultData) {
		this.resultData = resultData;
	}
	
	
}
