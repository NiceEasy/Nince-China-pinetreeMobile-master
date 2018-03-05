package com.pinetree.mobile.bean;

public class SignInOut {
	private String taskId;
	private String isSignInOut;// (签退0，签到1，完成2)
	private String date;
	private String state;
	private String empNum;//服务人员员工编号
	
	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getIsSignInOut() {
		return isSignInOut;
	}

	public void setIsSignInOut(String isSignInOut) {
		this.isSignInOut = isSignInOut;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
