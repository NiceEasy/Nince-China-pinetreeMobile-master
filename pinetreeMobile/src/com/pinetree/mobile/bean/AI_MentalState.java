package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 *@类描述 评估信息@精神状态 AI_MentalState
 * 
 *      Name Code ID ID 报告单号 ReportId 认知功能测试问题答案 CognitiveFunctionTestAnswer 认知功能说明 CognitiveFunctionExplain 认知功能得分 CognitiveFunctionScore 攻击行为说明 AggressiveBehaviorExplain 攻击行为得分
 *      AggressiveBehaviorScore 抑郁症状说明 DepressiveSymptomsExplain 抑郁症状得分 DepressiveSymptomsScore 精神状态总得分 MSSumScore 精神状态分级 MSLevel 精神状态分级说明 MSLevelExplain 备注 Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 上午10:35:10
 */
public class AI_MentalState implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String CognitiveFunctionTestAnswer;
	String CognitiveFunctionExplain;
	String CognitiveFunctionScore;
	String AggressiveBehaviorExplain;
	String AggressiveBehaviorScore;
	String DepressiveSymptomsExplain;
	String DepressiveSymptomsScore;
	String MSSumScore;
	String MSLevel;
	String MSLevelExplain;
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

	public String getCognitiveFunctionTestAnswer() {
		return CognitiveFunctionTestAnswer;
	}

	public void setCognitiveFunctionTestAnswer(String cognitiveFunctionTestAnswer) {
		CognitiveFunctionTestAnswer = cognitiveFunctionTestAnswer;
	}

	public String getCognitiveFunctionExplain() {
		return CognitiveFunctionExplain;
	}

	public void setCognitiveFunctionExplain(String cognitiveFunctionExplain) {
		CognitiveFunctionExplain = cognitiveFunctionExplain;
	}

	public String getCognitiveFunctionScore() {
		return CognitiveFunctionScore;
	}

	public void setCognitiveFunctionScore(String cognitiveFunctionScore) {
		CognitiveFunctionScore = cognitiveFunctionScore;
	}

	public String getAggressiveBehaviorExplain() {
		return AggressiveBehaviorExplain;
	}

	public void setAggressiveBehaviorExplain(String aggressiveBehaviorExplain) {
		AggressiveBehaviorExplain = aggressiveBehaviorExplain;
	}

	public String getAggressiveBehaviorScore() {
		return AggressiveBehaviorScore;
	}

	public void setAggressiveBehaviorScore(String aggressiveBehaviorScore) {
		AggressiveBehaviorScore = aggressiveBehaviorScore;
	}

	public String getDepressiveSymptomsExplain() {
		return DepressiveSymptomsExplain;
	}

	public void setDepressiveSymptomsExplain(String depressiveSymptomsExplain) {
		DepressiveSymptomsExplain = depressiveSymptomsExplain;
	}

	public String getDepressiveSymptomsScore() {
		return DepressiveSymptomsScore;
	}

	public void setDepressiveSymptomsScore(String depressiveSymptomsScore) {
		DepressiveSymptomsScore = depressiveSymptomsScore;
	}

	public String getMSSumScore() {
		return MSSumScore;
	}

	public void setMSSumScore(String mSSumScore) {
		MSSumScore = mSSumScore;
	}

	public String getMSLevel() {
		return MSLevel;
	}

	public void setMSLevel(String mSLevel) {
		MSLevel = mSLevel;
	}

	public String getMSLevelExplain() {
		return MSLevelExplain;
	}

	public void setMSLevelExplain(String mSLevelExplain) {
		MSLevelExplain = mSLevelExplain;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
