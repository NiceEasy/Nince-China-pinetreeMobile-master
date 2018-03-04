package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 智力评定
 */
public class BrainScoreBase implements Serializable {

	private String message;
	private String success;
	private List<BrainScore> resultData;

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

	public List<BrainScore> getResultData() {
		return resultData;
	}

	public void setResultData(List<BrainScore> resultData) {
		this.resultData = resultData;
	}

}
