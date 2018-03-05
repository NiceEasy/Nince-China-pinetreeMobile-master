package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

public class SF_MentalStateAssessment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String TimeOrientaionScore;
	String TimeOrientaionInstruction;
	String AddressOrientaionScore;
	String AddressOrientaionInstruction;
	String MemoryScore;
	String MemoryInstruction;
	String AttentionAndCalculationScore;
	String AttentionAndCalculationInstruction;
	String RecollectScore;
	String RecollectInstruction;
	String NamedScore;
	String NamedInstruction;
	String RepeataSentenceScore;
	String RepeataSentenceInstruction;
	String ExecutiveOrderScore;
	String ExecutiveOrderInstruction;
	String ReadingComprehensionScore;
	String ReadingComprehensionInstruction;
	String WritingScore;
	String WritingInstruction;
	String CompositionAbilityScore;
	String CompostionAbilityInstruction;
	String CulturalDegree;
	String SumScore;
	String AssessmentResult;
	String COPMNowDemand;
	String COPMActuality;
	String COPMDegreeofSatisfaction;
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

	public String getTimeOrientaionScore() {
		return TimeOrientaionScore;
	}

	public void setTimeOrientaionScore(String timeOrientaionScore) {
		TimeOrientaionScore = timeOrientaionScore;
	}

	public String getTimeOrientaionInstruction() {
		return TimeOrientaionInstruction;
	}

	public void setTimeOrientaionInstruction(String timeOrientaionInstruction) {
		TimeOrientaionInstruction = timeOrientaionInstruction;
	}

	public String getAddressOrientaionScore() {
		return AddressOrientaionScore;
	}

	public void setAddressOrientaionScore(String addressOrientaionScore) {
		AddressOrientaionScore = addressOrientaionScore;
	}

	public String getAddressOrientaionInstruction() {
		return AddressOrientaionInstruction;
	}

	public void setAddressOrientaionInstruction(String addressOrientaionInstruction) {
		AddressOrientaionInstruction = addressOrientaionInstruction;
	}

	public String getMemoryScore() {
		return MemoryScore;
	}

	public void setMemoryScore(String memoryScore) {
		MemoryScore = memoryScore;
	}

	public String getMemoryInstruction() {
		return MemoryInstruction;
	}

	public void setMemoryInstruction(String memoryInstruction) {
		MemoryInstruction = memoryInstruction;
	}

	public String getAttentionAndCalculationScore() {
		return AttentionAndCalculationScore;
	}

	public void setAttentionAndCalculationScore(String attentionAndCalculationScore) {
		AttentionAndCalculationScore = attentionAndCalculationScore;
	}

	public String getAttentionAndCalculationInstruction() {
		return AttentionAndCalculationInstruction;
	}

	public void setAttentionAndCalculationInstruction(String attentionAndCalculationInstruction) {
		AttentionAndCalculationInstruction = attentionAndCalculationInstruction;
	}

	public String getRecollectScore() {
		return RecollectScore;
	}

	public void setRecollectScore(String recollectScore) {
		RecollectScore = recollectScore;
	}

	public String getRecollectInstruction() {
		return RecollectInstruction;
	}

	public void setRecollectInstruction(String recollectInstruction) {
		RecollectInstruction = recollectInstruction;
	}

	public String getNamedScore() {
		return NamedScore;
	}

	public void setNamedScore(String namedScore) {
		NamedScore = namedScore;
	}

	public String getNamedInstruction() {
		return NamedInstruction;
	}

	public void setNamedInstruction(String namedInstruction) {
		NamedInstruction = namedInstruction;
	}

	public String getRepeataSentenceScore() {
		return RepeataSentenceScore;
	}

	public void setRepeataSentenceScore(String repeataSentenceScore) {
		RepeataSentenceScore = repeataSentenceScore;
	}

	public String getRepeataSentenceInstruction() {
		return RepeataSentenceInstruction;
	}

	public void setRepeataSentenceInstruction(String repeataSentenceInstruction) {
		RepeataSentenceInstruction = repeataSentenceInstruction;
	}

	public String getExecutiveOrderScore() {
		return ExecutiveOrderScore;
	}

	public void setExecutiveOrderScore(String executiveOrderScore) {
		ExecutiveOrderScore = executiveOrderScore;
	}

	public String getExecutiveOrderInstruction() {
		return ExecutiveOrderInstruction;
	}

	public void setExecutiveOrderInstruction(String executiveOrderInstruction) {
		ExecutiveOrderInstruction = executiveOrderInstruction;
	}

	public String getReadingComprehensionScore() {
		return ReadingComprehensionScore;
	}

	public void setReadingComprehensionScore(String readingComprehensionScore) {
		ReadingComprehensionScore = readingComprehensionScore;
	}

	public String getReadingComprehensionInstruction() {
		return ReadingComprehensionInstruction;
	}

	public void setReadingComprehensionInstruction(String readingComprehensionInstruction) {
		ReadingComprehensionInstruction = readingComprehensionInstruction;
	}

	public String getWritingScore() {
		return WritingScore;
	}

	public void setWritingScore(String writingScore) {
		WritingScore = writingScore;
	}

	public String getWritingInstruction() {
		return WritingInstruction;
	}

	public void setWritingInstruction(String writingInstruction) {
		WritingInstruction = writingInstruction;
	}

	public String getCompositionAbilityScore() {
		return CompositionAbilityScore;
	}

	public void setCompositionAbilityScore(String compositionAbilityScore) {
		CompositionAbilityScore = compositionAbilityScore;
	}

	public String getCompostionAbilityInstruction() {
		return CompostionAbilityInstruction;
	}

	public void setCompostionAbilityInstruction(String compostionAbilityInstruction) {
		CompostionAbilityInstruction = compostionAbilityInstruction;
	}

	public String getCulturalDegree() {
		return CulturalDegree;
	}

	public void setCulturalDegree(String culturalDegree) {
		CulturalDegree = culturalDegree;
	}

	public String getSumScore() {
		return SumScore;
	}

	public void setSumScore(String sumScore) {
		SumScore = sumScore;
	}

	public String getAssessmentResult() {
		return AssessmentResult;
	}

	public void setAssessmentResult(String assessmentResult) {
		AssessmentResult = assessmentResult;
	}

	public String getCOPMNowDemand() {
		return COPMNowDemand;
	}

	public void setCOPMNowDemand(String cOPMNowDemand) {
		COPMNowDemand = cOPMNowDemand;
	}

	public String getCOPMActuality() {
		return COPMActuality;
	}

	public void setCOPMActuality(String cOPMActuality) {
		COPMActuality = cOPMActuality;
	}

	public String getCOPMDegreeofSatisfaction() {
		return COPMDegreeofSatisfaction;
	}

	public void setCOPMDegreeofSatisfaction(String cOPMDegreeofSatisfaction) {
		COPMDegreeofSatisfaction = cOPMDegreeofSatisfaction;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
