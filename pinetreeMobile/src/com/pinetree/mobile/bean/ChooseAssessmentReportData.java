package com.pinetree.mobile.bean;

import java.io.Serializable;
import java.util.List;

public class ChooseAssessmentReportData implements Serializable{

	private String total;//总页数
	private List<ChooseAssessmentReport> data;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<ChooseAssessmentReport> getData() {
		return data;
	}
	public void setData(List<ChooseAssessmentReport> data) {
		this.data = data;
	}
	
	
}
