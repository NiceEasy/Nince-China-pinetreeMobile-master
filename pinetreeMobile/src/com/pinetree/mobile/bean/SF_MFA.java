package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

public class SF_MFA implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String MFASittingBalance;
	String MFAPositionBalance;
	String MFAHoldenWalkingAbility;
	String MFASittingBalanceOther;
	String MFAPositionBalanceOther;
	String MFAHoldenWalkingAbilityOther;
	String MFAMuscleForce;
	String MFAMuscularTension;
	String MFAPainScore;
	String RiskLevel;
	String Remark;

	String MFAMuscleForceLeftHand;
	String MFAMuscleForceLeftUpper;
	String MFAMuscleForceLeftLower;
	String MFAMuscleForceLeftOtherName;
	String MFAMuscleForceLeftOther;
	String MFAMuscleForceTrunkBack;
	String MFAMuscleForceTrunkWaist;
	String MFAMuscleForceTrunkButtocks;
	String MFAMuscleForceTrunkChest;
	String MFAMuscleForceTrunkAbdomen;
	String MFAMuscleForceTrunkOtherName;
	String MFAMuscleForceTrunkOther;
	String MFAMuscleForceRightFeet;
	String MFAMuscleForceRightUpper;
	String MFAMuscleForceRightLower;
	String MFAMuscleForceRightOtherName;
	String MFAMuscleForceRightOther;

	String MFAMuscularTensionLeftHand;
	String MFAMuscularTensionLeftUpper;
	String MFAMuscularTensionLeftLower;
	String MFAMuscularTensionLeftOtherName;
	String MFAMuscularTensionLeftOther;
	String MFAMuscularTensionTrunkBack;
	String MFAMuscularTensionTrunkWaist;
	String MFAMuscularTensionTrunkButtocks;
	String MFAMuscularTensionTrunkChest;
	String MFAMuscularTensionTrunkAbdomen;
	String MFAMuscularTensionTrunkOtherName;
	String MFAMuscularTensionTrunkOther;
	String MFAMuscularTensionRightFeet;
	String MFAMuscularTensionRightUpper;
	String MFAMuscularTensionRightLower;
	String MFAMuscularTensionRightOtherName;
	String MFAMuscularTensionRightOther;

	String MFAPainScoreLeftHand;
	String MFAPainScoreLeftUpper;
	String MFAPainScoreLeftLower;
	String MFAPainScoreLeftOtherName;
	String MFAPainScoreLeftOther;
	String MFAPainScoreTrunkBack;
	String MFAPainScoreTrunkWaist;
	String MFAPainScoreTrunkButtocks;
	String MFAPainScoreTrunkChest;
	String MFAPainScoreTrunkAbdomen;
	String MFAPainScoreTrunkOtherName;
	String MFAPainScoreTrunkOther;
	String MFAPainScoreRightFeet;
	String MFAPainScoreRightUpper;
	String MFAPainScoreRightLower;
	String MFAPainScoreRightOtherName;
	String MFAPainScoreRightOther;

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

	public String getMFASittingBalance() {
		return MFASittingBalance;
	}

	public void setMFASittingBalance(String mFASittingBalance) {
		MFASittingBalance = mFASittingBalance;
	}

	public String getMFAPositionBalance() {
		return MFAPositionBalance;
	}

	public void setMFAPositionBalance(String mFAPositionBalance) {
		MFAPositionBalance = mFAPositionBalance;
	}

	public String getMFAHoldenWalkingAbility() {
		return MFAHoldenWalkingAbility;
	}

	public void setMFAHoldenWalkingAbility(String mFAHoldenWalkingAbility) {
		MFAHoldenWalkingAbility = mFAHoldenWalkingAbility;
	}

	public String getMFASittingBalanceOther() {
		return MFASittingBalanceOther;
	}

	public void setMFASittingBalanceOther(String mFASittingBalanceOther) {
		MFASittingBalanceOther = mFASittingBalanceOther;
	}

	public String getMFAPositionBalanceOther() {
		return MFAPositionBalanceOther;
	}

	public void setMFAPositionBalanceOther(String mFAPositionBalanceOther) {
		MFAPositionBalanceOther = mFAPositionBalanceOther;
	}

	public String getMFAHoldenWalkingAbilityOther() {
		return MFAHoldenWalkingAbilityOther;
	}

	public void setMFAHoldenWalkingAbilityOther(String mFAHoldenWalkingAbilityOther) {
		MFAHoldenWalkingAbilityOther = mFAHoldenWalkingAbilityOther;
	}

	public String getMFAMuscleForce() {
		return MFAMuscleForce;
	}

	public void setMFAMuscleForce(String mFAMuscleForce) {
		MFAMuscleForce = mFAMuscleForce;
	}

	public String getMFAMuscularTension() {
		return MFAMuscularTension;
	}

	public void setMFAMuscularTension(String mFAMuscularTension) {
		MFAMuscularTension = mFAMuscularTension;
	}

	public String getMFAPainScore() {
		return MFAPainScore;
	}

	public void setMFAPainScore(String mFAPainScore) {
		MFAPainScore = mFAPainScore;
	}

	public String getRiskLevel() {
		return RiskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		RiskLevel = riskLevel;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getMFAMuscleForceLeftHand() {
		return MFAMuscleForceLeftHand;
	}

	public void setMFAMuscleForceLeftHand(String mFAMuscleForceLeftHand) {
		MFAMuscleForceLeftHand = mFAMuscleForceLeftHand;
	}

	public String getMFAMuscleForceLeftUpper() {
		return MFAMuscleForceLeftUpper;
	}

	public void setMFAMuscleForceLeftUpper(String mFAMuscleForceLeftUpper) {
		MFAMuscleForceLeftUpper = mFAMuscleForceLeftUpper;
	}

	public String getMFAMuscleForceLeftLower() {
		return MFAMuscleForceLeftLower;
	}

	public void setMFAMuscleForceLeftLower(String mFAMuscleForceLeftLower) {
		MFAMuscleForceLeftLower = mFAMuscleForceLeftLower;
	}

	public String getMFAMuscleForceLeftOtherName() {
		return MFAMuscleForceLeftOtherName;
	}

	public void setMFAMuscleForceLeftOtherName(String mFAMuscleForceLeftOtherName) {
		MFAMuscleForceLeftOtherName = mFAMuscleForceLeftOtherName;
	}

	public String getMFAMuscleForceLeftOther() {
		return MFAMuscleForceLeftOther;
	}

	public void setMFAMuscleForceLeftOther(String mFAMuscleForceLeftOther) {
		MFAMuscleForceLeftOther = mFAMuscleForceLeftOther;
	}

	public String getMFAMuscleForceTrunkBack() {
		return MFAMuscleForceTrunkBack;
	}

	public void setMFAMuscleForceTrunkBack(String mFAMuscleForceTrunkBack) {
		MFAMuscleForceTrunkBack = mFAMuscleForceTrunkBack;
	}

	public String getMFAMuscleForceTrunkWaist() {
		return MFAMuscleForceTrunkWaist;
	}

	public void setMFAMuscleForceTrunkWaist(String mFAMuscleForceTrunkWaist) {
		MFAMuscleForceTrunkWaist = mFAMuscleForceTrunkWaist;
	}

	public String getMFAMuscleForceTrunkButtocks() {
		return MFAMuscleForceTrunkButtocks;
	}

	public void setMFAMuscleForceTrunkButtocks(String mFAMuscleForceTrunkButtocks) {
		MFAMuscleForceTrunkButtocks = mFAMuscleForceTrunkButtocks;
	}

	public String getMFAMuscleForceTrunkChest() {
		return MFAMuscleForceTrunkChest;
	}

	public void setMFAMuscleForceTrunkChest(String mFAMuscleForceTrunkChest) {
		MFAMuscleForceTrunkChest = mFAMuscleForceTrunkChest;
	}

	public String getMFAMuscleForceTrunkAbdomen() {
		return MFAMuscleForceTrunkAbdomen;
	}

	public void setMFAMuscleForceTrunkAbdomen(String mFAMuscleForceTrunkAbdomen) {
		MFAMuscleForceTrunkAbdomen = mFAMuscleForceTrunkAbdomen;
	}

	public String getMFAMuscleForceTrunkOtherName() {
		return MFAMuscleForceTrunkOtherName;
	}

	public void setMFAMuscleForceTrunkOtherName(String mFAMuscleForceTrunkOtherName) {
		MFAMuscleForceTrunkOtherName = mFAMuscleForceTrunkOtherName;
	}

	public String getMFAMuscleForceTrunkOther() {
		return MFAMuscleForceTrunkOther;
	}

	public void setMFAMuscleForceTrunkOther(String mFAMuscleForceTrunkOther) {
		MFAMuscleForceTrunkOther = mFAMuscleForceTrunkOther;
	}

	public String getMFAMuscleForceRightFeet() {
		return MFAMuscleForceRightFeet;
	}

	public void setMFAMuscleForceRightFeet(String mFAMuscleForceRightFeet) {
		MFAMuscleForceRightFeet = mFAMuscleForceRightFeet;
	}

	public String getMFAMuscleForceRightUpper() {
		return MFAMuscleForceRightUpper;
	}

	public void setMFAMuscleForceRightUpper(String mFAMuscleForceRightUpper) {
		MFAMuscleForceRightUpper = mFAMuscleForceRightUpper;
	}

	public String getMFAMuscleForceRightLower() {
		return MFAMuscleForceRightLower;
	}

	public void setMFAMuscleForceRightLower(String mFAMuscleForceRightLower) {
		MFAMuscleForceRightLower = mFAMuscleForceRightLower;
	}

	public String getMFAMuscleForceRightOtherName() {
		return MFAMuscleForceRightOtherName;
	}

	public void setMFAMuscleForceRightOtherName(String mFAMuscleForceRightOtherName) {
		MFAMuscleForceRightOtherName = mFAMuscleForceRightOtherName;
	}

	public String getMFAMuscleForceRightOther() {
		return MFAMuscleForceRightOther;
	}

	public void setMFAMuscleForceRightOther(String mFAMuscleForceRightOther) {
		MFAMuscleForceRightOther = mFAMuscleForceRightOther;
	}

	public String getMFAMuscularTensionLeftHand() {
		return MFAMuscularTensionLeftHand;
	}

	public void setMFAMuscularTensionLeftHand(String mFAMuscularTensionLeftHand) {
		MFAMuscularTensionLeftHand = mFAMuscularTensionLeftHand;
	}

	public String getMFAMuscularTensionLeftUpper() {
		return MFAMuscularTensionLeftUpper;
	}

	public void setMFAMuscularTensionLeftUpper(String mFAMuscularTensionLeftUpper) {
		MFAMuscularTensionLeftUpper = mFAMuscularTensionLeftUpper;
	}

	public String getMFAMuscularTensionLeftLower() {
		return MFAMuscularTensionLeftLower;
	}

	public void setMFAMuscularTensionLeftLower(String mFAMuscularTensionLeftLower) {
		MFAMuscularTensionLeftLower = mFAMuscularTensionLeftLower;
	}

	public String getMFAMuscularTensionLeftOtherName() {
		return MFAMuscularTensionLeftOtherName;
	}

	public void setMFAMuscularTensionLeftOtherName(String mFAMuscularTensionLeftOtherName) {
		MFAMuscularTensionLeftOtherName = mFAMuscularTensionLeftOtherName;
	}

	public String getMFAMuscularTensionLeftOther() {
		return MFAMuscularTensionLeftOther;
	}

	public void setMFAMuscularTensionLeftOther(String mFAMuscularTensionLeftOther) {
		MFAMuscularTensionLeftOther = mFAMuscularTensionLeftOther;
	}

	public String getMFAMuscularTensionTrunkBack() {
		return MFAMuscularTensionTrunkBack;
	}

	public void setMFAMuscularTensionTrunkBack(String mFAMuscularTensionTrunkBack) {
		MFAMuscularTensionTrunkBack = mFAMuscularTensionTrunkBack;
	}

	public String getMFAMuscularTensionTrunkWaist() {
		return MFAMuscularTensionTrunkWaist;
	}

	public void setMFAMuscularTensionTrunkWaist(String mFAMuscularTensionTrunkWaist) {
		MFAMuscularTensionTrunkWaist = mFAMuscularTensionTrunkWaist;
	}

	public String getMFAMuscularTensionTrunkButtocks() {
		return MFAMuscularTensionTrunkButtocks;
	}

	public void setMFAMuscularTensionTrunkButtocks(String mFAMuscularTensionTrunkButtocks) {
		MFAMuscularTensionTrunkButtocks = mFAMuscularTensionTrunkButtocks;
	}

	public String getMFAMuscularTensionTrunkChest() {
		return MFAMuscularTensionTrunkChest;
	}

	public void setMFAMuscularTensionTrunkChest(String mFAMuscularTensionTrunkChest) {
		MFAMuscularTensionTrunkChest = mFAMuscularTensionTrunkChest;
	}

	public String getMFAMuscularTensionTrunkAbdomen() {
		return MFAMuscularTensionTrunkAbdomen;
	}

	public void setMFAMuscularTensionTrunkAbdomen(String mFAMuscularTensionTrunkAbdomen) {
		MFAMuscularTensionTrunkAbdomen = mFAMuscularTensionTrunkAbdomen;
	}

	public String getMFAMuscularTensionTrunkOtherName() {
		return MFAMuscularTensionTrunkOtherName;
	}

	public void setMFAMuscularTensionTrunkOtherName(String mFAMuscularTensionTrunkOtherName) {
		MFAMuscularTensionTrunkOtherName = mFAMuscularTensionTrunkOtherName;
	}

	public String getMFAMuscularTensionTrunkOther() {
		return MFAMuscularTensionTrunkOther;
	}

	public void setMFAMuscularTensionTrunkOther(String mFAMuscularTensionTrunkOther) {
		MFAMuscularTensionTrunkOther = mFAMuscularTensionTrunkOther;
	}

	public String getMFAMuscularTensionRightFeet() {
		return MFAMuscularTensionRightFeet;
	}

	public void setMFAMuscularTensionRightFeet(String mFAMuscularTensionRightFeet) {
		MFAMuscularTensionRightFeet = mFAMuscularTensionRightFeet;
	}

	public String getMFAMuscularTensionRightUpper() {
		return MFAMuscularTensionRightUpper;
	}

	public void setMFAMuscularTensionRightUpper(String mFAMuscularTensionRightUpper) {
		MFAMuscularTensionRightUpper = mFAMuscularTensionRightUpper;
	}

	public String getMFAMuscularTensionRightLower() {
		return MFAMuscularTensionRightLower;
	}

	public void setMFAMuscularTensionRightLower(String mFAMuscularTensionRightLower) {
		MFAMuscularTensionRightLower = mFAMuscularTensionRightLower;
	}

	public String getMFAMuscularTensionRightOtherName() {
		return MFAMuscularTensionRightOtherName;
	}

	public void setMFAMuscularTensionRightOtherName(String mFAMuscularTensionRightOtherName) {
		MFAMuscularTensionRightOtherName = mFAMuscularTensionRightOtherName;
	}

	public String getMFAMuscularTensionRightOther() {
		return MFAMuscularTensionRightOther;
	}

	public void setMFAMuscularTensionRightOther(String mFAMuscularTensionRightOther) {
		MFAMuscularTensionRightOther = mFAMuscularTensionRightOther;
	}

	public String getMFAPainScoreLeftHand() {
		return MFAPainScoreLeftHand;
	}

	public void setMFAPainScoreLeftHand(String mFAPainScoreLeftHand) {
		MFAPainScoreLeftHand = mFAPainScoreLeftHand;
	}

	public String getMFAPainScoreLeftUpper() {
		return MFAPainScoreLeftUpper;
	}

	public void setMFAPainScoreLeftUpper(String mFAPainScoreLeftUpper) {
		MFAPainScoreLeftUpper = mFAPainScoreLeftUpper;
	}

	public String getMFAPainScoreLeftLower() {
		return MFAPainScoreLeftLower;
	}

	public void setMFAPainScoreLeftLower(String mFAPainScoreLeftLower) {
		MFAPainScoreLeftLower = mFAPainScoreLeftLower;
	}

	public String getMFAPainScoreLeftOtherName() {
		return MFAPainScoreLeftOtherName;
	}

	public void setMFAPainScoreLeftOtherName(String mFAPainScoreLeftOtherName) {
		MFAPainScoreLeftOtherName = mFAPainScoreLeftOtherName;
	}

	public String getMFAPainScoreLeftOther() {
		return MFAPainScoreLeftOther;
	}

	public void setMFAPainScoreLeftOther(String mFAPainScoreLeftOther) {
		MFAPainScoreLeftOther = mFAPainScoreLeftOther;
	}

	public String getMFAPainScoreTrunkBack() {
		return MFAPainScoreTrunkBack;
	}

	public void setMFAPainScoreTrunkBack(String mFAPainScoreTrunkBack) {
		MFAPainScoreTrunkBack = mFAPainScoreTrunkBack;
	}

	public String getMFAPainScoreTrunkWaist() {
		return MFAPainScoreTrunkWaist;
	}

	public void setMFAPainScoreTrunkWaist(String mFAPainScoreTrunkWaist) {
		MFAPainScoreTrunkWaist = mFAPainScoreTrunkWaist;
	}

	public String getMFAPainScoreTrunkButtocks() {
		return MFAPainScoreTrunkButtocks;
	}

	public void setMFAPainScoreTrunkButtocks(String mFAPainScoreTrunkButtocks) {
		MFAPainScoreTrunkButtocks = mFAPainScoreTrunkButtocks;
	}

	public String getMFAPainScoreTrunkChest() {
		return MFAPainScoreTrunkChest;
	}

	public void setMFAPainScoreTrunkChest(String mFAPainScoreTrunkChest) {
		MFAPainScoreTrunkChest = mFAPainScoreTrunkChest;
	}

	public String getMFAPainScoreTrunkAbdomen() {
		return MFAPainScoreTrunkAbdomen;
	}

	public void setMFAPainScoreTrunkAbdomen(String mFAPainScoreTrunkAbdomen) {
		MFAPainScoreTrunkAbdomen = mFAPainScoreTrunkAbdomen;
	}

	public String getMFAPainScoreTrunkOtherName() {
		return MFAPainScoreTrunkOtherName;
	}

	public void setMFAPainScoreTrunkOtherName(String mFAPainScoreTrunkOtherName) {
		MFAPainScoreTrunkOtherName = mFAPainScoreTrunkOtherName;
	}

	public String getMFAPainScoreTrunkOther() {
		return MFAPainScoreTrunkOther;
	}

	public void setMFAPainScoreTrunkOther(String mFAPainScoreTrunkOther) {
		MFAPainScoreTrunkOther = mFAPainScoreTrunkOther;
	}

	public String getMFAPainScoreRightFeet() {
		return MFAPainScoreRightFeet;
	}

	public void setMFAPainScoreRightFeet(String mFAPainScoreRightFeet) {
		MFAPainScoreRightFeet = mFAPainScoreRightFeet;
	}

	public String getMFAPainScoreRightUpper() {
		return MFAPainScoreRightUpper;
	}

	public void setMFAPainScoreRightUpper(String mFAPainScoreRightUpper) {
		MFAPainScoreRightUpper = mFAPainScoreRightUpper;
	}

	public String getMFAPainScoreRightLower() {
		return MFAPainScoreRightLower;
	}

	public void setMFAPainScoreRightLower(String mFAPainScoreRightLower) {
		MFAPainScoreRightLower = mFAPainScoreRightLower;
	}

	public String getMFAPainScoreRightOtherName() {
		return MFAPainScoreRightOtherName;
	}

	public void setMFAPainScoreRightOtherName(String mFAPainScoreRightOtherName) {
		MFAPainScoreRightOtherName = mFAPainScoreRightOtherName;
	}

	public String getMFAPainScoreRightOther() {
		return MFAPainScoreRightOther;
	}

	public void setMFAPainScoreRightOther(String mFAPainScoreRightOther) {
		MFAPainScoreRightOther = mFAPainScoreRightOther;
	}

}
