package com.pinetree.mobile.bean;

/**
 * 客户签字
 * 
 * @author Administrator
 * 
 */
public class CustomerSign {
	private String scheduleId;
	private String saveState;
	private String submitState;
	private String payerName;
	private String signRelation;//签字人
	
	
	public String getSignRelation() {
		return signRelation;
	}

	public void setSignRelation(String signRelation) {
		this.signRelation = signRelation;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getSaveState() {
		return saveState;
	}

	public void setSaveState(String saveState) {
		this.saveState = saveState;
	}

	public String getSubmitState() {
		return submitState;
	}

	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	private String customerName;// 客户姓名
	private String serverDate;// 服务时间
	private String serverName;// 服务人员
	private String signContract;// 签约
	private String contractExtension;// 续约
	private String signMoney;//签约续约金额

	public String getSignMoney() {
		return signMoney;
	}

	public void setSignMoney(String signMoney) {
		this.signMoney = signMoney;
	}
	
	private String isVouchRec;//是否还款（0：否，1：是）
	private String vouchAmount;//担保还款金额
	private String renewalAmount;//续费金额
	private String isSign;//是否续约（0：否，1：是）
	
	public String getIsVouchRec() {
		return isVouchRec;
	}

	public void setIsVouchRec(String isVouchRec) {
		this.isVouchRec = isVouchRec;
	}

	public String getVouchAmount() {
		return vouchAmount;
	}

	public void setVouchAmount(String vouchAmount) {
		this.vouchAmount = vouchAmount;
	}

	public String getRenewalAmount() {
		return renewalAmount;
	}

	public void setRenewalAmount(String renewalAmount) {
		this.renewalAmount = renewalAmount;
	}

	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	private String reimbursementAmount;// 报销金额
	private String specialCircumstances;// 特殊情况
	private String signImageUri;// 签字或
	private String photoImageUri;//拍照图片
	private String relation;// 付款人和服务对象的关系
	
	public String getPhotoImageUri() {
		return photoImageUri;
	}

	public void setPhotoImageUri(String photoImageUri) {
		this.photoImageUri = photoImageUri;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getServerDate() {
		return serverDate;
	}

	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSignContract() {
		return signContract;
	}

	public void setSignContract(String signContract) {
		this.signContract = signContract;
	}

	public String getContractExtension() {
		return contractExtension;
	}

	public void setContractExtension(String contractExtension) {
		this.contractExtension = contractExtension;
	}

	public String getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(String reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getSpecialCircumstances() {
		return specialCircumstances;
	}

	public void setSpecialCircumstances(String specialCircumstances) {
		this.specialCircumstances = specialCircumstances;
	}

	public String getSignImageUri() {
		return signImageUri;
	}

	public void setSignImageUri(String signImageUri) {
		this.signImageUri = signImageUri;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
