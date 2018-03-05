package com.pinetree.mobile.bean;

import java.util.List;

/**
 * 客户经纬度和周边标识经纬度
 * 
 * @author Administrator
 * 
 */
public class CustomerMap {
	private CustomerLocation resultData;
	private String success;

	public CustomerLocation getResultData() {
		return resultData;
	}

	public void setResultData(CustomerLocation resultData) {
		this.resultData = resultData;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public class CustomerLocation {
		private String custName;
		private String latitude;
		private String longitude;
		private List<NarbyMark> markList;

		public String getCustName() {
			return custName;
		}

		public void setCustName(String custName) {
			this.custName = custName;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public List<NarbyMark> getMarkList() {
			return markList;
		}

		public void setMarkList(List<NarbyMark> markList) {
			this.markList = markList;
		}

		public class NarbyMark {
			private String latitude;
			private String longitude;
			private String mapMark;

			public String getLatitude() {
				return latitude;
			}

			public void setLatitude(String latitude) {
				this.latitude = latitude;
			}

			public String getLongitude() {
				return longitude;
			}

			public void setLongitude(String longitude) {
				this.longitude = longitude;
			}

			public String getMapMark() {
				return mapMark;
			}

			public void setMapMark(String mapMark) {
				this.mapMark = mapMark;
			}

		}

	}

}
