package com.pinetree.mobile.bean;

import java.util.List;

/**
 * 客户周围的地图标签
 */
public class CustomerNearbyMarker {

	public String message;
	public String success;
	public List<CustomerNearbyMarkerItem> resultData;
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
	public List<CustomerNearbyMarkerItem> getResultData() {
		return resultData;
	}
	public void setResultData(List<CustomerNearbyMarkerItem> resultData) {
		this.resultData = resultData;
	}

}
