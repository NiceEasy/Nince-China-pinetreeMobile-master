package com.pinetree.mobile.bean;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 
 * @类描述 评估信息@感知觉与沟通 AI_SensoryPerceptionAndCommunication
 * 
 *      Name Code ID ID 报告单号 ReportId 意识水平说明 ConsciousnessLevelExplain 意识水平得分 ConsciousnessLevelScore 视力得分 VisionScore 视力说明 VisionExplain 听力说明 ListeningExplain 听力得分 ListeningScore 沟通交流说明
 *      CommunicationExplain 沟通交流得分 CommunicationScore 感知觉与沟通总分 SPCSumScore 感知觉与沟通分级 SPCLevel 感知觉与沟通分级说明 SPCExplain 备注 Remark
 * 
 * @author wcm
 * @createDate 2015-9-2 上午10:37:00
 */
public class AI_SensoryPerceptionAndCommunication implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int ID;
	String ReportId;
	String ConsciousnessLevelExplain;
	String ConsciousnessLevelScore;
	String VisionScore;
	String VisionExplain;
	String ListeningExplain;
	String ListeningScore;
	String CommunicationExplain;
	String CommunicationScore;
	String SPCSumScore;
	String SPCLevel;
	String SPCExplain;
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

	public String getConsciousnessLevelExplain() {
		return ConsciousnessLevelExplain;
	}

	public void setConsciousnessLevelExplain(String consciousnessLevelExplain) {
		ConsciousnessLevelExplain = consciousnessLevelExplain;
	}

	public String getConsciousnessLevelScore() {
		return ConsciousnessLevelScore;
	}

	public void setConsciousnessLevelScore(String consciousnessLevelScore) {
		ConsciousnessLevelScore = consciousnessLevelScore;
	}

	public String getVisionScore() {
		return VisionScore;
	}

	public void setVisionScore(String visionScore) {
		VisionScore = visionScore;
	}

	public String getVisionExplain() {
		return VisionExplain;
	}

	public void setVisionExplain(String visionExplain) {
		VisionExplain = visionExplain;
	}

	public String getListeningExplain() {
		return ListeningExplain;
	}

	public void setListeningExplain(String listeningExplain) {
		ListeningExplain = listeningExplain;
	}

	public String getListeningScore() {
		return ListeningScore;
	}

	public void setListeningScore(String listeningScore) {
		ListeningScore = listeningScore;
	}

	public String getCommunicationExplain() {
		return CommunicationExplain;
	}

	public void setCommunicationExplain(String communicationExplain) {
		CommunicationExplain = communicationExplain;
	}

	public String getCommunicationScore() {
		return CommunicationScore;
	}

	public void setCommunicationScore(String communicationScore) {
		CommunicationScore = communicationScore;
	}

	public String getSPCSumScore() {
		return SPCSumScore;
	}

	public void setSPCSumScore(String sPCSumScore) {
		SPCSumScore = sPCSumScore;
	}

	public String getSPCLevel() {
		return SPCLevel;
	}

	public void setSPCLevel(String sPCLevel) {
		SPCLevel = sPCLevel;
	}

	public String getSPCExplain() {
		return SPCExplain;
	}

	public void setSPCExplain(String sPCExplain) {
		SPCExplain = sPCExplain;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
