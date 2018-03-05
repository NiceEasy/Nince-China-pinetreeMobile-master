package com.pinetree.mobile.bean;

import java.io.Serializable;

/**
 * 账号信息
 * @author Administrator
 *
 */
public class User implements Serializable{
	private String message;
	private String success;
	private UserInfo resultData;
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
	
	
	public UserInfo getResultData() {
		return resultData;
	}
	public void setResultData(UserInfo resultData) {
		this.resultData = resultData;
	}


	public class UserInfo implements Serializable{
		private String departmentId;
		private String documentNo;
		private String employeeId;
		private String employeeName;
		private String id;
		private String sex;
		private String subsidiaryId;
		private String tel;
		private String url1;
		public String getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(String departmentId) {
			this.departmentId = departmentId;
		}
		public String getDocumentNo() {
			return documentNo;
		}
		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getSubsidiaryId() {
			return subsidiaryId;
		}
		public void setSubsidiaryId(String subsidiaryId) {
			this.subsidiaryId = subsidiaryId;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getUrl1() {
			return url1;
		}
		public void setUrl1(String url1) {
			this.url1 = url1;
		}
		
		
	}
}
