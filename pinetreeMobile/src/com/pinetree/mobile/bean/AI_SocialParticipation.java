package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 评估信息@社会参与 AI_SocialParticipation
 * 
 *      Name Code ID ID 报告单号 ReportId 生活能力说明 LifeAbilityExplain 生活能力得分 LifeAbilityScore 工作能力得分 WorkingAbilityScore 工作能力说明 WorkingAbilityExplain 时间/空间定向说明 TimeOrSpatialOrientationExplain 时间/空间定向得分
 *      TimeOrSpatialOrientationScore 人物定向说明 PersonalOrientationExplain 人物定向得分 PersonalOrientationScore 社会交往能力得分 SocialIntercourseAbilityScore 社会交往能力说明 SocialIntercourseAbilityExplain 社会参与总分
 *      SPSumScore 社会参与分级 SPLevel 社会参与分级说明 SPLevelExplain 备注 Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 上午10:38:44
 */
public class AI_SocialParticipation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String LifeAbilityExplain;
	String LifeAbilityScore;
	String WorkingAbilityScore;
	String WorkingAbilityExplain;
	String TimeOrSpatialOrientationExplain;
	String TimeOrSpatialOrientationScore;
	String PersonalOrientationExplain;
	String PersonalOrientationScore;
	String SocialIntercourseAbilityScore;
	String SocialIntercourseAbilityExplain;
	String SPSumScore;
	String SPLevel;
	String SPLevelExplain;
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

	public String getLifeAbilityExplain() {
		return LifeAbilityExplain;
	}

	public void setLifeAbilityExplain(String lifeAbilityExplain) {
		LifeAbilityExplain = lifeAbilityExplain;
	}

	public String getLifeAbilityScore() {
		return LifeAbilityScore;
	}

	public void setLifeAbilityScore(String lifeAbilityScore) {
		LifeAbilityScore = lifeAbilityScore;
	}

	public String getWorkingAbilityScore() {
		return WorkingAbilityScore;
	}

	public void setWorkingAbilityScore(String workingAbilityScore) {
		WorkingAbilityScore = workingAbilityScore;
	}

	public String getWorkingAbilityExplain() {
		return WorkingAbilityExplain;
	}

	public void setWorkingAbilityExplain(String workingAbilityExplain) {
		WorkingAbilityExplain = workingAbilityExplain;
	}

	public String getTimeOrSpatialOrientationExplain() {
		return TimeOrSpatialOrientationExplain;
	}

	public void setTimeOrSpatialOrientationExplain(String timeOrSpatialOrientationExplain) {
		TimeOrSpatialOrientationExplain = timeOrSpatialOrientationExplain;
	}

	public String getTimeOrSpatialOrientationScore() {
		return TimeOrSpatialOrientationScore;
	}

	public void setTimeOrSpatialOrientationScore(String timeOrSpatialOrientationScore) {
		TimeOrSpatialOrientationScore = timeOrSpatialOrientationScore;
	}

	public String getPersonalOrientationExplain() {
		return PersonalOrientationExplain;
	}

	public void setPersonalOrientationExplain(String personalOrientationExplain) {
		PersonalOrientationExplain = personalOrientationExplain;
	}

	public String getPersonalOrientationScore() {
		return PersonalOrientationScore;
	}

	public void setPersonalOrientationScore(String personalOrientationScore) {
		PersonalOrientationScore = personalOrientationScore;
	}

	public String getSocialIntercourseAbilityScore() {
		return SocialIntercourseAbilityScore;
	}

	public void setSocialIntercourseAbilityScore(String socialIntercourseAbilityScore) {
		SocialIntercourseAbilityScore = socialIntercourseAbilityScore;
	}

	public String getSocialIntercourseAbilityExplain() {
		return SocialIntercourseAbilityExplain;
	}

	public void setSocialIntercourseAbilityExplain(String socialIntercourseAbilityExplain) {
		SocialIntercourseAbilityExplain = socialIntercourseAbilityExplain;
	}

	public String getSPSumScore() {
		return SPSumScore;
	}

	public void setSPSumScore(String sPSumScore) {
		SPSumScore = sPSumScore;
	}

	public String getSPLevel() {
		return SPLevel;
	}

	public void setSPLevel(String sPLevel) {
		SPLevel = sPLevel;
	}

	public String getSPLevelExplain() {
		return SPLevelExplain;
	}

	public void setSPLevelExplain(String sPLevelExplain) {
		SPLevelExplain = sPLevelExplain;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
