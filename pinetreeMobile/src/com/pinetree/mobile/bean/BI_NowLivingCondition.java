package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 基本信息@目前生活状况 BI_NowLivingCondition ID ID 报告单号 ReportId 居住情况 LivingConditions 经济来源 EconomicSources 就医方式 MedicalTreatment 资金困难 FinancialDifficulties 同住配偶健康状况 LiveSpouseHealth 居住环境
 *      LivingEnvironment 医疗支付 MedicalPayment 其他状况 OtherConditions 备注 Remark 居住情况的其他信息 LivingConditionsOther 经济来源的其他信息 EconomicSourcesOther 同住配偶健康状况的其他信息 LiveSpouseHealthOther 医疗支付的其他信息
 *      MedicalPaymentOther
 * 
 * @author wcm
 * @createDate 2015-9-1 上午10:14:56
 */
public class BI_NowLivingCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String HouseConditions;
	String LivingConditions;
	String EconomicSources;
	String IncomeConditions;
	String MedicalTreatment;
	String FinancialDifficulties;
	String LiveSpouseHealth;
	String LivingEnvironment;
	String MedicalPayment;
	String OtherConditions;
	String HouseConditionsOther;
	String LivingConditionsOther;
	String EconomicSourcesOther;
	String IncomeConditionsOther;
	String LiveSpouseHealthOther;
	String MedicalPaymentOther;
	String Remark;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getReportId() {
		return ReportId;
	}

	public void setReportId(String reportId) {
		ReportId = reportId;
	}

	public String getHouseConditions() {
		return HouseConditions;
	}

	public void setHouseConditions(String houseConditions) {
		HouseConditions = houseConditions;
	}

	public String getLivingConditions() {
		return LivingConditions;
	}

	public void setLivingConditions(String livingConditions) {
		LivingConditions = livingConditions;
	}

	public String getEconomicSources() {
		return EconomicSources;
	}

	public void setEconomicSources(String economicSources) {
		EconomicSources = economicSources;
	}

	public String getIncomeConditions() {
		return IncomeConditions;
	}

	public void setIncomeConditions(String incomeConditions) {
		IncomeConditions = incomeConditions;
	}

	public String getMedicalTreatment() {
		return MedicalTreatment;
	}

	public void setMedicalTreatment(String medicalTreatment) {
		MedicalTreatment = medicalTreatment;
	}

	public String getFinancialDifficulties() {
		return FinancialDifficulties;
	}

	public void setFinancialDifficulties(String financialDifficulties) {
		FinancialDifficulties = financialDifficulties;
	}

	public String getLiveSpouseHealth() {
		return LiveSpouseHealth;
	}

	public void setLiveSpouseHealth(String liveSpouseHealth) {
		LiveSpouseHealth = liveSpouseHealth;
	}

	public String getLivingEnvironment() {
		return LivingEnvironment;
	}

	public void setLivingEnvironment(String livingEnvironment) {
		LivingEnvironment = livingEnvironment;
	}

	public String getMedicalPayment() {
		return MedicalPayment;
	}

	public void setMedicalPayment(String medicalPayment) {
		MedicalPayment = medicalPayment;
	}

	public String getOtherConditions() {
		return OtherConditions;
	}

	public void setOtherConditions(String otherConditions) {
		OtherConditions = otherConditions;
	}

	public String getLivingConditionsOther() {
		return LivingConditionsOther;
	}

	public void setLivingConditionsOther(String livingConditionsOther) {
		LivingConditionsOther = livingConditionsOther;
	}

	public String getHouseConditionsOther() {
		return HouseConditionsOther;
	}

	public void setHouseConditionsOther(String houseConditionsOther) {
		HouseConditionsOther = houseConditionsOther;
	}

	public String getEconomicSourcesOther() {
		return EconomicSourcesOther;
	}

	public void setEconomicSourcesOther(String economicSourcesOther) {
		EconomicSourcesOther = economicSourcesOther;
	}

	public String getIncomeConditionsOther() {
		return IncomeConditionsOther;
	}

	public void setIncomeConditionsOther(String incomeConditionsOther) {
		IncomeConditionsOther = incomeConditionsOther;
	}

	public String getLiveSpouseHealthOther() {
		return LiveSpouseHealthOther;
	}

	public void setLiveSpouseHealthOther(String liveSpouseHealthOther) {
		LiveSpouseHealthOther = liveSpouseHealthOther;
	}

	public String getMedicalPaymentOther() {
		return MedicalPaymentOther;
	}

	public void setMedicalPaymentOther(String medicalPaymentOther) {
		MedicalPaymentOther = medicalPaymentOther;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
