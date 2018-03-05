package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

public class SF_PersonalSupplement implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String OccupationPreRetirement;
	String NursingStatus;
	String ChildrenStatus;
	String HomeLighting;
	String HomeAir;
	String HomeDryHumidity;
	String HomeTemperature;
	String HomeSmell;
	String RoomToilet;
	String RoomBed;
	String RoomEnviroment;
	String RoomOther;
	String RoomDressing;
	String RoomGetupIntheNight;
	String RoomRemark;
	String AssistantTool;
	String AssistantToolOther;
	String AssistantToolDemand;
	String DrugUseCondition;
	String DietCondition;
	String DCDiet;
	String DietDrinkingWater;
	String DietVegetables;
	String DietProtein;
	String SleepCondition;
	String SleepReason;
	String SleepDrugUse;
	String SleepInfluence;
	String SleepOther;
	String DefecationCondition;
	String DefecationReason;
	String DefecationDrugUse;
	String DefecationConstipationAndDiarrhea;
	String UrinationCondition;
	String UrinationReason;
	String UrinationDrugUse;
	String UrinationInfluence;
	String PsychologyCondition;
	String PsychologyReason;
	String PsychologyDrugUse;
	String PsychologyInfluence;
	String PersonalHygiene;
	String VitalSignsBloodGlucoseMeasurementTime;
	String VitalSignsBloodGlucoseMeasurementValue;
	String VitalSignsPulseConfition;
	String VitalSignsPulseTimes;
	String VitalSignsBreathingCondition;
	String VitalSignsBreathingTimes;
	String VitalSignsSystolicPressure;
	String VitalSignsDiastolicBloodPressure;
	String VitalSignsBodyTemperature;
	String PersonsFace;
	String SkinEdima;
	String SkinOtherPosition;
	String SkinPressureSoreCondition;
	String SkinPressureSorePosition;
	String SkinPressureSoreSize;
	String SkinPressureSoreExudationCondition;
	String SkinPresstheRedPosition;
	String SkinPresstheRedSize;
	String ThoraxChest;
	String ThoraxChestOther;
	String Abdomen;
	String AbdomenOther;
	String SkinPresstheRedCondition;
	String MFASittingBalance;
	String MFAPositionBalance;
	String MFAHoldenWalkingAbility;
	String MFAMuscleForce;
	String MFAMuscularTension;
	String MFAPainScore;
	String RiskLevel;
	String Remark;

	// 2015.10.03����
	String OccupationPreRetirementOther;
	String RoomBedMattressSoftness;
	String RoomBedMattress;
	String RoomBedTidy;
	String RoomEnviromentAcuteAngle;
	String RoomEnviromentStrong;
	String RoomEnviromentAction;
	String RoomEnviromentAntiskid;
	String RoomEnviromentBarrier;
	String RoomEnviromentTidy;
	String RoomEnviromentSundries;
	String RoomEnviromentDoorsill;
	String RoomEnviromentOther;
	String RoomEnviromentOtherInfo;
	String RoomDressingClean;
	String RoomDressingFit;
	String RoomDressingWarm;
	String RoomGetupIntheNightRemark;
	String AssistantToolAction;
	String AssistantToolOrthopedic;
	String VitalSignsBreathingCough;
	String VitalSignsPressureLocation;
	
	//2015.10.08�������������������ߣ�������Ϣ��
	String AssistantToolOtherInfo;

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

	public String getOccupationPreRetirement() {
		return OccupationPreRetirement;
	}

	public void setOccupationPreRetirement(String occupationPreRetirement) {
		OccupationPreRetirement = occupationPreRetirement;
	}

	public String getNursingStatus() {
		return NursingStatus;
	}

	public void setNursingStatus(String nursingStatus) {
		NursingStatus = nursingStatus;
	}

	public String getChildrenStatus() {
		return ChildrenStatus;
	}

	public void setChildrenStatus(String childrenStatus) {
		ChildrenStatus = childrenStatus;
	}

	public String getHomeLighting() {
		return HomeLighting;
	}

	public void setHomeLighting(String homeLighting) {
		HomeLighting = homeLighting;
	}

	public String getHomeAir() {
		return HomeAir;
	}

	public void setHomeAir(String homeAir) {
		HomeAir = homeAir;
	}

	public String getHomeDryHumidity() {
		return HomeDryHumidity;
	}

	public void setHomeDryHumidity(String homeDryHumidity) {
		HomeDryHumidity = homeDryHumidity;
	}

	public String getHomeTemperature() {
		return HomeTemperature;
	}

	public void setHomeTemperature(String homeTemperature) {
		HomeTemperature = homeTemperature;
	}

	public String getHomeSmell() {
		return HomeSmell;
	}

	public void setHomeSmell(String homeSmell) {
		HomeSmell = homeSmell;
	}

	public String getRoomToilet() {
		return RoomToilet;
	}

	public void setRoomToilet(String roomToilet) {
		RoomToilet = roomToilet;
	}

	public String getRoomBed() {
		return RoomBed;
	}

	public void setRoomBed(String roomBed) {
		RoomBed = roomBed;
	}

	public String getRoomEnviroment() {
		return RoomEnviroment;
	}

	public void setRoomEnviroment(String roomEnviroment) {
		RoomEnviroment = roomEnviroment;
	}

	public String getRoomOther() {
		return RoomOther;
	}

	public void setRoomOther(String roomOther) {
		RoomOther = roomOther;
	}

	public String getRoomDressing() {
		return RoomDressing;
	}

	public void setRoomDressing(String roomDressing) {
		RoomDressing = roomDressing;
	}

	public String getRoomGetupIntheNight() {
		return RoomGetupIntheNight;
	}

	public void setRoomGetupIntheNight(String roomGetupIntheNight) {
		RoomGetupIntheNight = roomGetupIntheNight;
	}

	public String getRoomRemark() {
		return RoomRemark;
	}

	public void setRoomRemark(String roomRemark) {
		RoomRemark = roomRemark;
	}

	public String getAssistantTool() {
		return AssistantTool;
	}

	public void setAssistantTool(String assistantTool) {
		AssistantTool = assistantTool;
	}

	public String getAssistantToolOther() {
		return AssistantToolOther;
	}

	public void setAssistantToolOther(String assistantToolOther) {
		AssistantToolOther = assistantToolOther;
	}

	public String getAssistantToolDemand() {
		return AssistantToolDemand;
	}

	public void setAssistantToolDemand(String assistantToolDemand) {
		AssistantToolDemand = assistantToolDemand;
	}

	public String getDrugUseCondition() {
		return DrugUseCondition;
	}

	public void setDrugUseCondition(String drugUseCondition) {
		DrugUseCondition = drugUseCondition;
	}

	public String getDietCondition() {
		return DietCondition;
	}

	public void setDietCondition(String dietCondition) {
		DietCondition = dietCondition;
	}

	public String getDCDiet() {
		return DCDiet;
	}

	public void setDCDiet(String dCDiet) {
		DCDiet = dCDiet;
	}

	public String getDietDrinkingWater() {
		return DietDrinkingWater;
	}

	public void setDietDrinkingWater(String dietDrinkingWater) {
		DietDrinkingWater = dietDrinkingWater;
	}

	public String getDietVegetables() {
		return DietVegetables;
	}

	public void setDietVegetables(String dietVegetables) {
		DietVegetables = dietVegetables;
	}

	public String getDietProtein() {
		return DietProtein;
	}

	public void setDietProtein(String dietProtein) {
		DietProtein = dietProtein;
	}

	public String getSleepCondition() {
		return SleepCondition;
	}

	public void setSleepCondition(String sleepCondition) {
		SleepCondition = sleepCondition;
	}

	public String getSleepReason() {
		return SleepReason;
	}

	public void setSleepReason(String sleepReason) {
		SleepReason = sleepReason;
	}

	public String getSleepDrugUse() {
		return SleepDrugUse;
	}

	public void setSleepDrugUse(String sleepDrugUse) {
		SleepDrugUse = sleepDrugUse;
	}

	public String getSleepInfluence() {
		return SleepInfluence;
	}

	public void setSleepInfluence(String sleepInfluence) {
		SleepInfluence = sleepInfluence;
	}

	public String getSleepOther() {
		return SleepOther;
	}

	public void setSleepOther(String sleepOther) {
		SleepOther = sleepOther;
	}

	public String getDefecationCondition() {
		return DefecationCondition;
	}

	public void setDefecationCondition(String defecationCondition) {
		DefecationCondition = defecationCondition;
	}

	public String getDefecationReason() {
		return DefecationReason;
	}

	public void setDefecationReason(String defecationReason) {
		DefecationReason = defecationReason;
	}

	public String getDefecationDrugUse() {
		return DefecationDrugUse;
	}

	public void setDefecationDrugUse(String defecationDrugUse) {
		DefecationDrugUse = defecationDrugUse;
	}

	public String getDefecationConstipationAndDiarrhea() {
		return DefecationConstipationAndDiarrhea;
	}

	public void setDefecationConstipationAndDiarrhea(String defecationConstipationAndDiarrhea) {
		DefecationConstipationAndDiarrhea = defecationConstipationAndDiarrhea;
	}

	public String getUrinationCondition() {
		return UrinationCondition;
	}

	public void setUrinationCondition(String urinationCondition) {
		UrinationCondition = urinationCondition;
	}

	public String getUrinationReason() {
		return UrinationReason;
	}

	public void setUrinationReason(String urinationReason) {
		UrinationReason = urinationReason;
	}

	public String getUrinationDrugUse() {
		return UrinationDrugUse;
	}

	public void setUrinationDrugUse(String urinationDrugUse) {
		UrinationDrugUse = urinationDrugUse;
	}

	public String getUrinationInfluence() {
		return UrinationInfluence;
	}

	public void setUrinationInfluence(String urinationInfluence) {
		UrinationInfluence = urinationInfluence;
	}

	public String getPsychologyCondition() {
		return PsychologyCondition;
	}

	public void setPsychologyCondition(String psychologyCondition) {
		PsychologyCondition = psychologyCondition;
	}

	public String getPsychologyReason() {
		return PsychologyReason;
	}

	public void setPsychologyReason(String psychologyReason) {
		PsychologyReason = psychologyReason;
	}

	public String getPsychologyDrugUse() {
		return PsychologyDrugUse;
	}

	public void setPsychologyDrugUse(String psychologyDrugUse) {
		PsychologyDrugUse = psychologyDrugUse;
	}

	public String getPsychologyInfluence() {
		return PsychologyInfluence;
	}

	public void setPsychologyInfluence(String psychologyInfluence) {
		PsychologyInfluence = psychologyInfluence;
	}

	public String getPersonalHygiene() {
		return PersonalHygiene;
	}

	public void setPersonalHygiene(String personalHygiene) {
		PersonalHygiene = personalHygiene;
	}

	public String getVitalSignsBloodGlucoseMeasurementTime() {
		return VitalSignsBloodGlucoseMeasurementTime;
	}

	public void setVitalSignsBloodGlucoseMeasurementTime(String vitalSignsBloodGlucoseMeasurementTime) {
		VitalSignsBloodGlucoseMeasurementTime = vitalSignsBloodGlucoseMeasurementTime;
	}

	public String getVitalSignsBloodGlucoseMeasurementValue() {
		return VitalSignsBloodGlucoseMeasurementValue;
	}

	public void setVitalSignsBloodGlucoseMeasurementValue(String vitalSignsBloodGlucoseMeasurementValue) {
		VitalSignsBloodGlucoseMeasurementValue = vitalSignsBloodGlucoseMeasurementValue;
	}

	public String getVitalSignsPulseConfition() {
		return VitalSignsPulseConfition;
	}

	public void setVitalSignsPulseConfition(String vitalSignsPulseConfition) {
		VitalSignsPulseConfition = vitalSignsPulseConfition;
	}

	public String getVitalSignsPulseTimes() {
		return VitalSignsPulseTimes;
	}

	public void setVitalSignsPulseTimes(String vitalSignsPulseTimes) {
		VitalSignsPulseTimes = vitalSignsPulseTimes;
	}

	public String getVitalSignsBreathingCondition() {
		return VitalSignsBreathingCondition;
	}

	public void setVitalSignsBreathingCondition(String vitalSignsBreathingCondition) {
		VitalSignsBreathingCondition = vitalSignsBreathingCondition;
	}

	public String getVitalSignsBreathingTimes() {
		return VitalSignsBreathingTimes;
	}

	public void setVitalSignsBreathingTimes(String vitalSignsBreathingTimes) {
		VitalSignsBreathingTimes = vitalSignsBreathingTimes;
	}

	public String getVitalSignsSystolicPressure() {
		return VitalSignsSystolicPressure;
	}

	public void setVitalSignsSystolicPressure(String vitalSignsSystolicPressure) {
		VitalSignsSystolicPressure = vitalSignsSystolicPressure;
	}

	public String getVitalSignsDiastolicBloodPressure() {
		return VitalSignsDiastolicBloodPressure;
	}

	public void setVitalSignsDiastolicBloodPressure(String vitalSignsDiastolicBloodPressure) {
		VitalSignsDiastolicBloodPressure = vitalSignsDiastolicBloodPressure;
	}

	public String getVitalSignsBodyTemperature() {
		return VitalSignsBodyTemperature;
	}

	public void setVitalSignsBodyTemperature(String vitalSignsBodyTemperature) {
		VitalSignsBodyTemperature = vitalSignsBodyTemperature;
	}

	public String getPersonsFace() {
		return PersonsFace;
	}

	public void setPersonsFace(String personsFace) {
		PersonsFace = personsFace;
	}

	public String getSkinEdima() {
		return SkinEdima;
	}

	public void setSkinEdima(String skinEdima) {
		SkinEdima = skinEdima;
	}

	public String getSkinOtherPosition() {
		return SkinOtherPosition;
	}

	public void setSkinOtherPosition(String skinOtherPosition) {
		SkinOtherPosition = skinOtherPosition;
	}

	public String getSkinPressureSoreCondition() {
		return SkinPressureSoreCondition;
	}

	public void setSkinPressureSoreCondition(String skinPressureSoreCondition) {
		SkinPressureSoreCondition = skinPressureSoreCondition;
	}

	public String getSkinPressureSorePosition() {
		return SkinPressureSorePosition;
	}

	public void setSkinPressureSorePosition(String skinPressureSorePosition) {
		SkinPressureSorePosition = skinPressureSorePosition;
	}

	public String getSkinPressureSoreSize() {
		return SkinPressureSoreSize;
	}

	public void setSkinPressureSoreSize(String skinPressureSoreSize) {
		SkinPressureSoreSize = skinPressureSoreSize;
	}

	public String getSkinPressureSoreExudationCondition() {
		return SkinPressureSoreExudationCondition;
	}

	public void setSkinPressureSoreExudationCondition(String skinPressureSoreExudationCondition) {
		SkinPressureSoreExudationCondition = skinPressureSoreExudationCondition;
	}

	public String getSkinPresstheRedPosition() {
		return SkinPresstheRedPosition;
	}

	public void setSkinPresstheRedPosition(String skinPresstheRedPosition) {
		SkinPresstheRedPosition = skinPresstheRedPosition;
	}

	public String getSkinPresstheRedSize() {
		return SkinPresstheRedSize;
	}

	public void setSkinPresstheRedSize(String skinPresstheRedSize) {
		SkinPresstheRedSize = skinPresstheRedSize;
	}

	public String getThoraxChest() {
		return ThoraxChest;
	}

	public void setThoraxChest(String thoraxChest) {
		ThoraxChest = thoraxChest;
	}

	public String getThoraxChestOther() {
		return ThoraxChestOther;
	}

	public void setThoraxChestOther(String thoraxChestOther) {
		ThoraxChestOther = thoraxChestOther;
	}

	public String getAbdomen() {
		return Abdomen;
	}

	public void setAbdomen(String abdomen) {
		Abdomen = abdomen;
	}

	public String getAbdomenOther() {
		return AbdomenOther;
	}

	public void setAbdomenOther(String abdomenOther) {
		AbdomenOther = abdomenOther;
	}

	public String getSkinPresstheRedCondition() {
		return SkinPresstheRedCondition;
	}

	public void setSkinPresstheRedCondition(String skinPresstheRedCondition) {
		SkinPresstheRedCondition = skinPresstheRedCondition;
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

	public String getOccupationPreRetirementOther() {
		return OccupationPreRetirementOther;
	}

	public void setOccupationPreRetirementOther(String occupationPreRetirementOther) {
		OccupationPreRetirementOther = occupationPreRetirementOther;
	}

	public String getRoomBedMattressSoftness() {
		return RoomBedMattressSoftness;
	}

	public void setRoomBedMattressSoftness(String roomBedMattressSoftness) {
		RoomBedMattressSoftness = roomBedMattressSoftness;
	}

	public String getRoomBedMattress() {
		return RoomBedMattress;
	}

	public void setRoomBedMattress(String roomBedMattress) {
		RoomBedMattress = roomBedMattress;
	}

	public String getRoomBedTidy() {
		return RoomBedTidy;
	}

	public void setRoomBedTidy(String roomBedTidy) {
		RoomBedTidy = roomBedTidy;
	}

	public String getRoomEnviromentAcuteAngle() {
		return RoomEnviromentAcuteAngle;
	}

	public void setRoomEnviromentAcuteAngle(String roomEnviromentAcuteAngle) {
		RoomEnviromentAcuteAngle = roomEnviromentAcuteAngle;
	}

	public String getRoomEnviromentStrong() {
		return RoomEnviromentStrong;
	}

	public void setRoomEnviromentStrong(String roomEnviromentStrong) {
		RoomEnviromentStrong = roomEnviromentStrong;
	}

	public String getRoomEnviromentAction() {
		return RoomEnviromentAction;
	}

	public void setRoomEnviromentAction(String roomEnviromentAction) {
		RoomEnviromentAction = roomEnviromentAction;
	}

	public String getRoomEnviromentAntiskid() {
		return RoomEnviromentAntiskid;
	}

	public void setRoomEnviromentAntiskid(String roomEnviromentAntiskid) {
		RoomEnviromentAntiskid = roomEnviromentAntiskid;
	}

	public String getRoomEnviromentBarrier() {
		return RoomEnviromentBarrier;
	}

	public void setRoomEnviromentBarrier(String roomEnviromentBarrier) {
		RoomEnviromentBarrier = roomEnviromentBarrier;
	}

	public String getRoomEnviromentTidy() {
		return RoomEnviromentTidy;
	}

	public void setRoomEnviromentTidy(String roomEnviromentTidy) {
		RoomEnviromentTidy = roomEnviromentTidy;
	}

	public String getRoomEnviromentSundries() {
		return RoomEnviromentSundries;
	}

	public void setRoomEnviromentSundries(String roomEnviromentSundries) {
		RoomEnviromentSundries = roomEnviromentSundries;
	}

	public String getRoomEnviromentDoorsill() {
		return RoomEnviromentDoorsill;
	}

	public void setRoomEnviromentDoorsill(String roomEnviromentDoorsill) {
		RoomEnviromentDoorsill = roomEnviromentDoorsill;
	}

	public String getRoomEnviromentOther() {
		return RoomEnviromentOther;
	}

	public void setRoomEnviromentOther(String roomEnviromentOther) {
		RoomEnviromentOther = roomEnviromentOther;
	}

	public String getRoomEnviromentOtherInfo() {
		return RoomEnviromentOtherInfo;
	}

	public void setRoomEnviromentOtherInfo(String roomEnviromentOtherInfo) {
		RoomEnviromentOtherInfo = roomEnviromentOtherInfo;
	}

	public String getRoomDressingClean() {
		return RoomDressingClean;
	}

	public void setRoomDressingClean(String roomDressingClean) {
		RoomDressingClean = roomDressingClean;
	}

	public String getRoomDressingFit() {
		return RoomDressingFit;
	}

	public void setRoomDressingFit(String roomDressingFit) {
		RoomDressingFit = roomDressingFit;
	}

	public String getRoomDressingWarm() {
		return RoomDressingWarm;
	}

	public void setRoomDressingWarm(String roomDressingWarm) {
		RoomDressingWarm = roomDressingWarm;
	}

	public String getRoomGetupIntheNightRemark() {
		return RoomGetupIntheNightRemark;
	}

	public void setRoomGetupIntheNightRemark(String roomGetupIntheNightRemark) {
		RoomGetupIntheNightRemark = roomGetupIntheNightRemark;
	}

	public String getAssistantToolAction() {
		return AssistantToolAction;
	}

	public void setAssistantToolAction(String assistantToolAction) {
		AssistantToolAction = assistantToolAction;
	}

	public String getAssistantToolOrthopedic() {
		return AssistantToolOrthopedic;
	}

	public void setAssistantToolOrthopedic(String assistantToolOrthopedic) {
		AssistantToolOrthopedic = assistantToolOrthopedic;
	}

	public String getVitalSignsBreathingCough() {
		return VitalSignsBreathingCough;
	}

	public void setVitalSignsBreathingCough(String vitalSignsBreathingCough) {
		VitalSignsBreathingCough = vitalSignsBreathingCough;
	}

	public String getVitalSignsPressureLocation() {
		return VitalSignsPressureLocation;
	}

	public void setVitalSignsPressureLocation(String vitalSignsPressureLocation) {
		VitalSignsPressureLocation = vitalSignsPressureLocation;
	}

	public String getAssistantToolOtherInfo() {
		return AssistantToolOtherInfo;
	}

	public void setAssistantToolOtherInfo(String assistantToolOtherInfo) {
		AssistantToolOtherInfo = assistantToolOtherInfo;
	}

}
