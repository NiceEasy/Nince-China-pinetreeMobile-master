package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 *  @类描述 评估信息@日常生活能力 AI_ActivityofDailyLliving
 * 
 *      Name Code ID ID 报告单号 ReportId 进食说明 EatExplain 进食得分 EatScore 洗澡得分 BathScore 洗澡说明 BathExplain 修饰说明 ModificationExplain 修饰得分 ModificationScore 穿衣说明 DressingExplain 穿衣得分 DressingScore 大便控制得分
 *      StoolControlScore 大便控制说明 StoolControlExplain 小便控制说明 UrineControlExplain 小便控制得分 UrineControlScore 如厕说明 GoToTheToiletExplain 如厕得分 GoToTheToiletScore 床椅转移说明 BedChairTransferExplain 床椅转移得分
 *      BedChairTransferScore 平地行走说明 FlatWalkingExplain 平地行走得分 FlatWalkingScore 上下楼梯说明 UpAndDownStairsExplain 上下楼梯得分 UpAndDownStairsScore 日常生活活动总分 SumScore 日常生活活动分级 DailyActivityLevel 日常生活活动分级说明
 *      DailyActivityLevelExplain 备注 Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 上午10:32:54
 */
public class AI_ActivityofDailyLliving implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String EatExplain;
	String EatScore;
	String BathScore;
	String BathExplain;
	String ModificationExplain;
	String ModificationScore;
	String DressingExplain;
	String DressingScore;
	String StoolControlScore;
	String StoolControlExplain;
	String UrineControlExplain;
	String UrineControlScore;
	String GoToTheToiletExplain;
	String GoToTheToiletScore;
	String BedChairTransferExplain;
	String BedChairTransferScore;
	String FlatWalkingExplain;
	String FlatWalkingScore;
	String UpAndDownStairsExplain;
	String UpAndDownStairsScore;
	String SumScore;
	String DailyActivityLevel;
	String DailyActivityLevelExplain;
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

	public String getEatExplain() {
		return EatExplain;
	}

	public void setEatExplain(String eatExplain) {
		EatExplain = eatExplain;
	}

	public String getEatScore() {
		return EatScore;
	}

	public void setEatScore(String eatScore) {
		EatScore = eatScore;
	}

	public String getBathScore() {
		return BathScore;
	}

	public void setBathScore(String bathScore) {
		BathScore = bathScore;
	}

	public String getBathExplain() {
		return BathExplain;
	}

	public void setBathExplain(String bathExplain) {
		BathExplain = bathExplain;
	}

	public String getModificationExplain() {
		return ModificationExplain;
	}

	public void setModificationExplain(String modificationExplain) {
		ModificationExplain = modificationExplain;
	}

	public String getModificationScore() {
		return ModificationScore;
	}

	public void setModificationScore(String modificationScore) {
		ModificationScore = modificationScore;
	}

	public String getDressingExplain() {
		return DressingExplain;
	}

	public void setDressingExplain(String dressingExplain) {
		DressingExplain = dressingExplain;
	}

	public String getDressingScore() {
		return DressingScore;
	}

	public void setDressingScore(String dressingScore) {
		DressingScore = dressingScore;
	}

	public String getStoolControlScore() {
		return StoolControlScore;
	}

	public void setStoolControlScore(String stoolControlScore) {
		StoolControlScore = stoolControlScore;
	}

	public String getStoolControlExplain() {
		return StoolControlExplain;
	}

	public void setStoolControlExplain(String stoolControlExplain) {
		StoolControlExplain = stoolControlExplain;
	}

	public String getUrineControlExplain() {
		return UrineControlExplain;
	}

	public void setUrineControlExplain(String urineControlExplain) {
		UrineControlExplain = urineControlExplain;
	}

	public String getUrineControlScore() {
		return UrineControlScore;
	}

	public void setUrineControlScore(String urineControlScore) {
		UrineControlScore = urineControlScore;
	}

	public String getGoToTheToiletExplain() {
		return GoToTheToiletExplain;
	}

	public void setGoToTheToiletExplain(String goToTheToiletExplain) {
		GoToTheToiletExplain = goToTheToiletExplain;
	}

	public String getGoToTheToiletScore() {
		return GoToTheToiletScore;
	}

	public void setGoToTheToiletScore(String goToTheToiletScore) {
		GoToTheToiletScore = goToTheToiletScore;
	}

	public String getBedChairTransferExplain() {
		return BedChairTransferExplain;
	}

	public void setBedChairTransferExplain(String bedChairTransferExplain) {
		BedChairTransferExplain = bedChairTransferExplain;
	}

	public String getBedChairTransferScore() {
		return BedChairTransferScore;
	}

	public void setBedChairTransferScore(String bedChairTransferScore) {
		BedChairTransferScore = bedChairTransferScore;
	}

	public String getFlatWalkingExplain() {
		return FlatWalkingExplain;
	}

	public void setFlatWalkingExplain(String flatWalkingExplain) {
		FlatWalkingExplain = flatWalkingExplain;
	}

	public String getFlatWalkingScore() {
		return FlatWalkingScore;
	}

	public void setFlatWalkingScore(String flatWalkingScore) {
		FlatWalkingScore = flatWalkingScore;
	}

	public String getUpAndDownStairsExplain() {
		return UpAndDownStairsExplain;
	}

	public void setUpAndDownStairsExplain(String upAndDownStairsExplain) {
		UpAndDownStairsExplain = upAndDownStairsExplain;
	}

	public String getUpAndDownStairsScore() {
		return UpAndDownStairsScore;
	}

	public void setUpAndDownStairsScore(String upAndDownStairsScore) {
		UpAndDownStairsScore = upAndDownStairsScore;
	}

	public String getSumScore() {
		return SumScore;
	}

	public void setSumScore(String sumScore) {
		SumScore = sumScore;
	}

	public String getDailyActivityLevel() {
		return DailyActivityLevel;
	}

	public void setDailyActivityLevel(String dailyActivityLevel) {
		DailyActivityLevel = dailyActivityLevel;
	}

	public String getDailyActivityLevelExplain() {
		return DailyActivityLevelExplain;
	}

	public void setDailyActivityLevelExplain(String dailyActivityLevelExplain) {
		DailyActivityLevelExplain = dailyActivityLevelExplain;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
