package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class RecoveryNursingPlanBase  implements Serializable{

	private String message;
	private String success;
	private List<RecoveryNursingPlan> resultData;
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
	public List<RecoveryNursingPlan> getResultData() {
		return resultData;
	}
	public void setResultData(List<RecoveryNursingPlan> resultData) {
		this.resultData = resultData;
	}
	
	
}
