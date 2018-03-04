package com.pinetree.mobile.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.pinetree.mobile.bean.SignResult;
import com.pinetree.mobile.net.HttpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @类描述	请求方法类
 * @author wcm 
 * @createDate 2015-10-13 上午9:16:17
 */
public class Request {
	String ip = "";
	String port = "";
	String path = "";
	String path_copm="";
	String result = null;
	private Handler handler;

	public Request(Handler handler) {
		this.handler = handler;
		// 内网地址ַ
		// path = "http://192.168.12.186:8080/txbss/mobileInterface/product_saveReport.action";
		// 外网地址
		
		
		
		//正式地址ַ
		//path = "http://bss.qskh.cn/txbss/mobileInterface/product_saveReport.action";
		//path_copm = "http://bss.qskh.cn/txbss/mobileInterface/product_copmAddView.action";
		//测试地址
		path = "http://218.241.153.216:9090/txbss/mobileInterface/product_saveReport.action";
		path_copm = "http://218.241.153.216:9090/txbss/mobileInterface/product_copmAddView.action";
		
		
		
		// 内网地址ַ2
//		path = "http://111.207.246.47:8080/txbss/mobileInterface/product_saveReport.action";
//		path_copm = "http://111.207.246.47:8080/txbss/mobileInterface/product_copmAddView.action";
		
		
//		path = "http://192.168.12.67:8080/txbss/mobileInterface/product_saveReport.action";
//		path_copm = "http://192.168.12.67:8080/txbss/mobileInterface/product_copmAddView.action";
		
//		path = "http://192.168.1.21:8080/txbss/mobileInterface/product_saveReport.action";
//		path_copm = "http://192.168.1.21:8080/txbss/mobileInterface/product_copmAddView.action";
	}

	public void uploadRequest(final String AssessmentReportData,String applicantPhoto,String huazhongPhoto) {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				HttpPost httpRequest = new HttpPost(path);
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair("AssessmentReportData", AssessmentReportData));
//				// params.add(new BasicNameValuePair("AssessmentReportData",
//				// "{\"AI_ActivityofDailyLliving\":{\"BathExplain\":\"0\",\"BathScore\":\"5\",\"BedChairTransferExplain\":\"0\",\"BedChairTransferScore\":\"15\",\"DailyActivityLevel\":\"1\",\"DressingExplain\":\"1\",\"DressingScore\":\"5\",\"EatExplain\":\"1\",\"EatScore\":\"5\",\"FlatWalkingExplain\":\"2\",\"FlatWalkingScore\":\"5\",\"GoToTheToiletExplain\":\"0\",\"GoToTheToiletScore\":\"10\",\"UrineControlScore\":\"5\",\"ModificationExplain\":\"0\",\"ModificationScore\":\"5\",\"ReportId\":\"1442316614\",\"StoolControlExplain\":\"0\",\"StoolControlScore\":\"10\",\"SumScore\":\"75\",\"UpAndDownStairsExplain\":\"0\",\"UpAndDownStairsScore\":\"10\",\"UrineControlExplain\":\"1\",\"ID\":2},\"AI_AssessmentBasisInformation\":{\"Age\":\"15\",\"AssessmentReason\":\"2\",\"isSelf\":\"0\",\"Name\":\"fhv\",\"RelationWithOldPeople\":\"1\",\"ReportId\":\"1442316614\",\"Screening\":\"cg\",\"Telephone\":\"1526256322\",\"ID\":1},\"AI_AssessmentSupplementaryInformation\":{\"AssessmentSupplementaryInfo\":\"gvhjjh,\u0027һ�����������������������������������Һò�������\",\"ReportId\":\"1442316614\",\"ID\":1},\"AI_MainAssessorInformation\":{\"BelongInstitution\":\"����\",\"EMail\":\"dt@cg.com\",\"HomeAddress\":\"������\",\"HomePhone\":\"52632563\",\"ReportId\":\"1442316614\",\"Mobile\":\"1524852625\",\"Name\":\"��С��\",\"PostalCode\":\"100032\",\"ID\":1},\"AI_MentalState\":{\"AggressiveBehaviorExplain\":\"0\",\"CognitiveFunctionExplain\":\"1\",\"CognitiveFunctionTestAnswer\":\"�ֱ�-ƻ��-����\",\"DepressiveSymptomsExplain\":\"0\",\"ReportId\":\"1442316614\",\"MSLevel\":\"1\",\"MSSumScore\":\"1\",\"ID\":1},\"AI_SensoryPerceptionAndCommunication\":{\"CommunicationExplain\":\"1\",\"ConsciousnessLevelExplain\":\"2\",\"ListeningExplain\":\"0\",\"ReportId\":\"1442316614\",\"SPCExplain\":\"1\",\"SPCSumScore\":\"3\",\"VisionExplain\":\"0\",\"ID\":1},\"Applicant\":\"�ſ�\",\"BI_DemandService\":{\"ReportId\":\"1442316614\",\"ServiceFrequencyCode\":\"2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,5\",\"ServiceFrequencyName\":\"��ĩ,������,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,һ����,����\",\"ID\":1},\"BI_DiagnosedDisease\":{\"DietaryRestrictionDisease\":\"0\",\"HighRiskDisease\":\"0\",\"ReportId\":\"1442316614\",\"InfectiousDiseases\":\"0\",\"OtherDisease\":\"0\",\"ID\":2},\"BI_EarlyResults\":{\"FirstAdvices\":\"1,2\",\"FirstImpressionCodes\":\"2,4,6\",\"FirstImpressionContents\":\"�ͱ���ͥ,�ƻ����������ͥ,���ǲ���\",\"ReportId\":\"1442316614\",\"ID\":2},\"BI_ExternalProfessionalService\":{\"EmotionDuration\":\"4-8-30\",\"HomeLifeDuration\":\"2-20-30\",\"ReportId\":\"1442316614\",\"NursingDemandChange\":\"0\",\"NursingGoal\":\"1\",\"PersonalLifeDuration\":\"5-6-40\",\"ID\":1},\"BI_GuardianAndEmergencyContactInformation\":{\"EAddress\":\"�õ���\",\"EEMail\":\"gg@163.com\",\"EHomePhone\":\"1552552\",\"EMobile\":\"15214523625\",\"EName\":\"������\",\"EPostalCode\":\"100085\",\"ERelationship\":\"1\",\"GAddress\":\"��������\",\"GEMail\":\"fr@163.com\",\"GHomePhone\":\"6256325\",\"GMobile\":\"15625662562\",\"GName\":\"�ſ�\",\"GPostalCode\":\"100083\",\"GRelationship\":\"0\",\"ReportId\":\"1442316614\",\"ID\":1},\"BI_HomePrimaryCargiversInformation\":{\"Address\":\"��һ��\",\"Age\":\"45\",\"EMail\":\"gf@ch.com\",\"HomeCaregiversStatus\":\"1,2\",\"HomeCargiversServices\":\"1,2\",\"HomePhone\":\"56325632\",\"WorkingDays\":\"20-30\",\"LiveWithOldPeople\":\"0\",\"Mobile\":\"1556962556\",\"Name\":\"����\",\"PostalCode\":\"150562\",\"Relationship\":\"1\",\"ReportId\":\"1442316614\",\"Sex\":\"0\",\"Weekend\":\"8-20\",\"ID\":1},\"BI_IDInformation\":{\"DisabilityCard\":\"21563245\",\"DisabledSoldierCertificate\":\"52563315\",\"ReportId\":\"1442316614\",\"IDNumber\":\"256225635865\",\"MedicareCardNumber\":\"22562553\",\"Name\":\"�\",\"NameUsedBefore\":\"�ȫ\",\"ID\":2},\"BI_ManagementInformation\":{\"Address\":\"��һ����\",\"EMail\":\"dg@163.com\",\"HomePhone\":\"526325622\",\"ReportId\":\"1442316614\",\"Mobile\":\"15248266254\",\"Name\":\"���ǵ�\",\"Organ\":\"����\",\"PostalCode\":\"100022\",\"ID\":1},\"BI_NowLivingCondition\":{\"EconomicSources\":\"0\",\"FinancialDifficulties\":\"0\",\"ReportId\":\"1442316614\",\"LiveSpouseHealth\":\"0\",\"LivingConditions\":\"3\",\"LivingEnvironment\":\"0\",\"MedicalPayment\":\"0\",\"OtherConditions\":\"0\",\"ID\":1},\"BI_PersonalInformation\":{\"BirthDate\":\"2015-09-15\",\"CensusRegisterAddress\":\"�����к�����\",\"CulturalDegree\":\"1\",\"EMail\":\"drf@163.com\",\"HomePhone\":\"62563252\",\"UsingLanguage\":\"0\",\"LiveAddress\":\"�����\",\"MaritalStatus\":\"1\",\"Mobile\":\"15623256225\",\"Nation\":\"0\",\"PlaceofOrigin\":\"����\",\"PostalCode\":\"100256\",\"ReligiousBelief\":\"1\",\"ReportId\":\"1442316614\",\"Sex\":\"1\",\"ID\":1},\"BI_SupplementaryInformation\":{\"SupplementaryInfo\":\"ggkjhgh\",\"ReportId\":\"1442316614\",\"ID\":1},\"SF_PersonalSupplement\":{\"Abdomen\":\"����\",\"AbdomenOther\":\"һ��\",\"AssistantTool\":\"555\",\"AssistantToolDemand\":\"��һ\",\"AssistantToolOther\":\"556\",\"ChildrenStatus\":\"����\",\"DCDiet\":\"һ\",\"DefecationCondition\":\"����\",\"DefecationConstipationAndDiarrhea\":\"����\",\"DefecationDrugUse\":\"��\",\"DefecationReason\":\"�ǲ�\",\"DietCondition\":\"�Һ�\",\"DietDrinkingWater\":\"����\",\"DietProtein\":\"��һ\",\"DietVegetables\":\"����\",\"DrugUseCondition\":\"��\",\"HomeAir\":\"��\",\"HomeDryHumidity\":\"45\",\"HomeLighting\":\"����\",\"HomeSmell\":\"��\",\"HomeTemperature\":\"20\",\"VitalSignsSystolicPressure\":\"128\",\"MFAHoldenWalkingAbility\":\"��һ\",\"MFAMuscleForce\":\"����\",\"MFAMuscularTension\":\"����\",\"MFAPainScore\":\"����\",\"MFAPositionBalance\":\"һ��\",\"MFASittingBalance\":\"����\",\"NursingStatus\":\"����\",\"OccupationPreRetirement\":\"һ�˵���\",\"PersonalHygiene\":\"�õ�\",\"PersonsFace\":\"��\",\"PsychologyCondition\":\"����\",\"PsychologyDrugUse\":\"��\",\"PsychologyInfluence\":\"����\",\"PsychologyReason\":\"һ��\",\"ReportId\":\"1442316614\",\"RiskLevel\":\"�ں�\",\"RoomBed\":\"����\",\"RoomDressing\":\"��һ\",\"RoomEnviroment\":\"��\",\"RoomGetupIntheNight\":\"�ú�\",\"RoomOther\":\"��\",\"RoomRemark\":\"�˵�\",\"RoomToilet\":\"�˲�\",\"SkinEdima\":\"��\",\"SkinOtherPosition\":\"����\",\"SkinPresstheRedCondition\":\"�õ�\",\"SkinPresstheRedPosition\":\"����\",\"SkinPresstheRedSize\":\"����\",\"SkinPressureSoreCondition\":\"�õ�\",\"SkinPressureSoreExudationCondition\":\"����\",\"SkinPressureSorePosition\":\"����\",\"SkinPressureSoreSize\":\"����\",\"SleepCondition\":\"�˺�\",\"SleepDrugUse\":\"��һҲ\",\"SleepInfluence\":\"����\",\"SleepOther\":\"����\",\"SleepReason\":\"����\",\"ThoraxChest\":\"�Ҳ�\",\"ThoraxChestOther\":\"һ��\",\"UrinationCondition\":\"����\",\"UrinationDrugUse\":\"����\",\"UrinationInfluence\":\"����\",\"UrinationReason\":\"��һ\",\"VitalSignsBloodGlucoseMeasurementTime\":\"һ��\",\"VitalSignsBloodGlucoseMeasurementValue\":\"����\",\"VitalSignsBodyTemperature\":\"37\",\"VitalSignsBreathingCondition\":\"����\",\"VitalSignsBreathingTimes\":\"����\",\"VitalSignsDiastolicBloodPressure\":\"68\",\"VitalSignsPulseConfition\":\"����\",\"VitalSignsPulseTimes\":\"����\",\"ID\":1},\"PromiseDesc1\":\"true\",\"PromiseDesc2\":\"true\",\"PromiseDesc3\":\"true\",\"ReportDate\":\"2015-09-15\",\"ReportId\":\"1442316614\",\"SF_MentalStateAssessment\":{\"AddressOrientaionInstruction\":\"����\",\"AddressOrientaionScore\":\"63\",\"AssessmentResult\":\"�Ǻ���\",\"AttentionAndCalculationInstruction\":\"��һ��\",\"AttentionAndCalculationScore\":\"63\",\"COPMActuality\":\"���ҵ�\",\"COPMDegreeofSatisfaction\":\"����\",\"COPMNowDemand\":\"����һ\",\"CompositionAbilityScore\":\"78\",\"CompostionAbilityInstruction\":\"������\",\"CulturalDegree\":\"����\",\"ExecutiveOrderInstruction\":\"��һ����\",\"ExecutiveOrderScore\":\"87\",\"WritingScore\":\"45\",\"MemoryInstruction\":\"��\",\"MemoryScore\":\"66\",\"NamedInstruction\":\"�ǲ�\",\"NamedScore\":\"89\",\"ReadingComprehensionInstruction\":\"���ں�\",\"ReadingComprehensionScore\":\"78\",\"RecollectInstruction\":\"��,����\",\"RecollectScore\":\"63\",\"RepeataSentenceInstruction\":\"������\",\"RepeataSentenceScore\":\"69\",\"ReportId\":\"1442316614\",\"SumScore\":\"52\",\"TimeOrientaionInstruction\":\"һ����\",\"TimeOrientaionScore\":\"68\",\"WritingInstruction\":\"һ��\",\"ID\":1},\"ID\":1442316614}"));
//
//				HttpEntity httpEntity;
//				try {
//					httpEntity = new UrlEncodedFormEntity(params, "utf-8");
//					httpRequest.setEntity(httpEntity);
//					HttpClient httpClient = new DefaultHttpClient();
//					// 连接超时
//					httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000 * 10);
//					// 读取超时
//					httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000 * 10);
//					HttpResponse httpResponse = httpClient.execute(httpRequest);
//					if (httpResponse.getStatusLine().getStatusCode() == 200) {
//						result = EntityUtils.toString(httpResponse.getEntity());
//						JSONObject jsonobject;
//						jsonobject = new JSONObject(result);
//						boolean isSuccess = jsonobject.getBoolean("success");
//						String message = "上传失败";
//						if (isSuccess) {
//							message = "true";
//							setMessage(100, message, 1);
//						} else {
//							if (jsonobject.getString("message") != null) {
//								message = jsonobject.getString("message");
//							}
//							setMessage(100, message, 1);
//						}
//					} else {
//						setMessage(100, "网络异常，请检查网络！", 2);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					setMessage(100, "网络异常，请检查网络", 2);
//				}
//			}
//		}).start();
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("AssessmentReportData",AssessmentReportData);
		params.addBodyParameter("applicantPhoto",new File(applicantPhoto));
		params.addBodyParameter("huazhongPhoto",new File(huazhongPhoto));
		
		HttpUtil.post("saveReport.action", params, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				
				SignResult signResult = GsonUtils.json2bean(responseInfo.result,SignResult.class);
				Log.v("TAG", "tijiao:" +responseInfo.result.toString());
				if (Boolean.parseBoolean(signResult.getSuccess())) {
					setMessage(100, "true", 1);
				}else {
					setMessage(100, signResult.getMessage(), 1);
				}
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				
				setMessage(100, "提交失败，请重试", 2);
			}
		});

	}
	public void getCopmsRequest(final String scheduleId, final String custID) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpPost httpRequest = new HttpPost(path_copm);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("scheID", scheduleId));
				params.add(new BasicNameValuePair("custID", custID));
				// params.add(new BasicNameValuePair("AssessmentReportData",
				// "{\"AI_ActivityofDailyLliving\":{\"BathExplain\":\"0\",\"BathScore\":\"5\",\"BedChairTransferExplain\":\"0\",\"BedChairTransferScore\":\"15\",\"DailyActivityLevel\":\"1\",\"DressingExplain\":\"1\",\"DressingScore\":\"5\",\"EatExplain\":\"1\",\"EatScore\":\"5\",\"FlatWalkingExplain\":\"2\",\"FlatWalkingScore\":\"5\",\"GoToTheToiletExplain\":\"0\",\"GoToTheToiletScore\":\"10\",\"UrineControlScore\":\"5\",\"ModificationExplain\":\"0\",\"ModificationScore\":\"5\",\"ReportId\":\"1442316614\",\"StoolControlExplain\":\"0\",\"StoolControlScore\":\"10\",\"SumScore\":\"75\",\"UpAndDownStairsExplain\":\"0\",\"UpAndDownStairsScore\":\"10\",\"UrineControlExplain\":\"1\",\"ID\":2},\"AI_AssessmentBasisInformation\":{\"Age\":\"15\",\"AssessmentReason\":\"2\",\"isSelf\":\"0\",\"Name\":\"fhv\",\"RelationWithOldPeople\":\"1\",\"ReportId\":\"1442316614\",\"Screening\":\"cg\",\"Telephone\":\"1526256322\",\"ID\":1},\"AI_AssessmentSupplementaryInformation\":{\"AssessmentSupplementaryInfo\":\"gvhjjh,\u0027һ�����������������������������������Һò�������\",\"ReportId\":\"1442316614\",\"ID\":1},\"AI_MainAssessorInformation\":{\"BelongInstitution\":\"����\",\"EMail\":\"dt@cg.com\",\"HomeAddress\":\"������\",\"HomePhone\":\"52632563\",\"ReportId\":\"1442316614\",\"Mobile\":\"1524852625\",\"Name\":\"��С��\",\"PostalCode\":\"100032\",\"ID\":1},\"AI_MentalState\":{\"AggressiveBehaviorExplain\":\"0\",\"CognitiveFunctionExplain\":\"1\",\"CognitiveFunctionTestAnswer\":\"�ֱ�-ƻ��-����\",\"DepressiveSymptomsExplain\":\"0\",\"ReportId\":\"1442316614\",\"MSLevel\":\"1\",\"MSSumScore\":\"1\",\"ID\":1},\"AI_SensoryPerceptionAndCommunication\":{\"CommunicationExplain\":\"1\",\"ConsciousnessLevelExplain\":\"2\",\"ListeningExplain\":\"0\",\"ReportId\":\"1442316614\",\"SPCExplain\":\"1\",\"SPCSumScore\":\"3\",\"VisionExplain\":\"0\",\"ID\":1},\"Applicant\":\"�ſ�\",\"BI_DemandService\":{\"ReportId\":\"1442316614\",\"ServiceFrequencyCode\":\"2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,5\",\"ServiceFrequencyName\":\"��ĩ,������,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,ÿ��,һ����,����\",\"ID\":1},\"BI_DiagnosedDisease\":{\"DietaryRestrictionDisease\":\"0\",\"HighRiskDisease\":\"0\",\"ReportId\":\"1442316614\",\"InfectiousDiseases\":\"0\",\"OtherDisease\":\"0\",\"ID\":2},\"BI_EarlyResults\":{\"FirstAdvices\":\"1,2\",\"FirstImpressionCodes\":\"2,4,6\",\"FirstImpressionContents\":\"�ͱ���ͥ,�ƻ����������ͥ,���ǲ���\",\"ReportId\":\"1442316614\",\"ID\":2},\"BI_ExternalProfessionalService\":{\"EmotionDuration\":\"4-8-30\",\"HomeLifeDuration\":\"2-20-30\",\"ReportId\":\"1442316614\",\"NursingDemandChange\":\"0\",\"NursingGoal\":\"1\",\"PersonalLifeDuration\":\"5-6-40\",\"ID\":1},\"BI_GuardianAndEmergencyContactInformation\":{\"EAddress\":\"�õ���\",\"EEMail\":\"gg@163.com\",\"EHomePhone\":\"1552552\",\"EMobile\":\"15214523625\",\"EName\":\"������\",\"EPostalCode\":\"100085\",\"ERelationship\":\"1\",\"GAddress\":\"��������\",\"GEMail\":\"fr@163.com\",\"GHomePhone\":\"6256325\",\"GMobile\":\"15625662562\",\"GName\":\"�ſ�\",\"GPostalCode\":\"100083\",\"GRelationship\":\"0\",\"ReportId\":\"1442316614\",\"ID\":1},\"BI_HomePrimaryCargiversInformation\":{\"Address\":\"��һ��\",\"Age\":\"45\",\"EMail\":\"gf@ch.com\",\"HomeCaregiversStatus\":\"1,2\",\"HomeCargiversServices\":\"1,2\",\"HomePhone\":\"56325632\",\"WorkingDays\":\"20-30\",\"LiveWithOldPeople\":\"0\",\"Mobile\":\"1556962556\",\"Name\":\"����\",\"PostalCode\":\"150562\",\"Relationship\":\"1\",\"ReportId\":\"1442316614\",\"Sex\":\"0\",\"Weekend\":\"8-20\",\"ID\":1},\"BI_IDInformation\":{\"DisabilityCard\":\"21563245\",\"DisabledSoldierCertificate\":\"52563315\",\"ReportId\":\"1442316614\",\"IDNumber\":\"256225635865\",\"MedicareCardNumber\":\"22562553\",\"Name\":\"�\",\"NameUsedBefore\":\"�ȫ\",\"ID\":2},\"BI_ManagementInformation\":{\"Address\":\"��һ����\",\"EMail\":\"dg@163.com\",\"HomePhone\":\"526325622\",\"ReportId\":\"1442316614\",\"Mobile\":\"15248266254\",\"Name\":\"���ǵ�\",\"Organ\":\"����\",\"PostalCode\":\"100022\",\"ID\":1},\"BI_NowLivingCondition\":{\"EconomicSources\":\"0\",\"FinancialDifficulties\":\"0\",\"ReportId\":\"1442316614\",\"LiveSpouseHealth\":\"0\",\"LivingConditions\":\"3\",\"LivingEnvironment\":\"0\",\"MedicalPayment\":\"0\",\"OtherConditions\":\"0\",\"ID\":1},\"BI_PersonalInformation\":{\"BirthDate\":\"2015-09-15\",\"CensusRegisterAddress\":\"�����к�����\",\"CulturalDegree\":\"1\",\"EMail\":\"drf@163.com\",\"HomePhone\":\"62563252\",\"UsingLanguage\":\"0\",\"LiveAddress\":\"�����\",\"MaritalStatus\":\"1\",\"Mobile\":\"15623256225\",\"Nation\":\"0\",\"PlaceofOrigin\":\"����\",\"PostalCode\":\"100256\",\"ReligiousBelief\":\"1\",\"ReportId\":\"1442316614\",\"Sex\":\"1\",\"ID\":1},\"BI_SupplementaryInformation\":{\"SupplementaryInfo\":\"ggkjhgh\",\"ReportId\":\"1442316614\",\"ID\":1},\"SF_PersonalSupplement\":{\"Abdomen\":\"����\",\"AbdomenOther\":\"һ��\",\"AssistantTool\":\"555\",\"AssistantToolDemand\":\"��һ\",\"AssistantToolOther\":\"556\",\"ChildrenStatus\":\"����\",\"DCDiet\":\"һ\",\"DefecationCondition\":\"����\",\"DefecationConstipationAndDiarrhea\":\"����\",\"DefecationDrugUse\":\"��\",\"DefecationReason\":\"�ǲ�\",\"DietCondition\":\"�Һ�\",\"DietDrinkingWater\":\"����\",\"DietProtein\":\"��һ\",\"DietVegetables\":\"����\",\"DrugUseCondition\":\"��\",\"HomeAir\":\"��\",\"HomeDryHumidity\":\"45\",\"HomeLighting\":\"����\",\"HomeSmell\":\"��\",\"HomeTemperature\":\"20\",\"VitalSignsSystolicPressure\":\"128\",\"MFAHoldenWalkingAbility\":\"��һ\",\"MFAMuscleForce\":\"����\",\"MFAMuscularTension\":\"����\",\"MFAPainScore\":\"����\",\"MFAPositionBalance\":\"һ��\",\"MFASittingBalance\":\"����\",\"NursingStatus\":\"����\",\"OccupationPreRetirement\":\"һ�˵���\",\"PersonalHygiene\":\"�õ�\",\"PersonsFace\":\"��\",\"PsychologyCondition\":\"����\",\"PsychologyDrugUse\":\"��\",\"PsychologyInfluence\":\"����\",\"PsychologyReason\":\"һ��\",\"ReportId\":\"1442316614\",\"RiskLevel\":\"�ں�\",\"RoomBed\":\"����\",\"RoomDressing\":\"��һ\",\"RoomEnviroment\":\"��\",\"RoomGetupIntheNight\":\"�ú�\",\"RoomOther\":\"��\",\"RoomRemark\":\"�˵�\",\"RoomToilet\":\"�˲�\",\"SkinEdima\":\"��\",\"SkinOtherPosition\":\"����\",\"SkinPresstheRedCondition\":\"�õ�\",\"SkinPresstheRedPosition\":\"����\",\"SkinPresstheRedSize\":\"����\",\"SkinPressureSoreCondition\":\"�õ�\",\"SkinPressureSoreExudationCondition\":\"����\",\"SkinPressureSorePosition\":\"����\",\"SkinPressureSoreSize\":\"����\",\"SleepCondition\":\"�˺�\",\"SleepDrugUse\":\"��һҲ\",\"SleepInfluence\":\"����\",\"SleepOther\":\"����\",\"SleepReason\":\"����\",\"ThoraxChest\":\"�Ҳ�\",\"ThoraxChestOther\":\"һ��\",\"UrinationCondition\":\"����\",\"UrinationDrugUse\":\"����\",\"UrinationInfluence\":\"����\",\"UrinationReason\":\"��һ\",\"VitalSignsBloodGlucoseMeasurementTime\":\"һ��\",\"VitalSignsBloodGlucoseMeasurementValue\":\"����\",\"VitalSignsBodyTemperature\":\"37\",\"VitalSignsBreathingCondition\":\"����\",\"VitalSignsBreathingTimes\":\"����\",\"VitalSignsDiastolicBloodPressure\":\"68\",\"VitalSignsPulseConfition\":\"����\",\"VitalSignsPulseTimes\":\"����\",\"ID\":1},\"PromiseDesc1\":\"true\",\"PromiseDesc2\":\"true\",\"PromiseDesc3\":\"true\",\"ReportDate\":\"2015-09-15\",\"ReportId\":\"1442316614\",\"SF_MentalStateAssessment\":{\"AddressOrientaionInstruction\":\"����\",\"AddressOrientaionScore\":\"63\",\"AssessmentResult\":\"�Ǻ���\",\"AttentionAndCalculationInstruction\":\"��һ��\",\"AttentionAndCalculationScore\":\"63\",\"COPMActuality\":\"���ҵ�\",\"COPMDegreeofSatisfaction\":\"����\",\"COPMNowDemand\":\"����һ\",\"CompositionAbilityScore\":\"78\",\"CompostionAbilityInstruction\":\"������\",\"CulturalDegree\":\"����\",\"ExecutiveOrderInstruction\":\"��һ����\",\"ExecutiveOrderScore\":\"87\",\"WritingScore\":\"45\",\"MemoryInstruction\":\"��\",\"MemoryScore\":\"66\",\"NamedInstruction\":\"�ǲ�\",\"NamedScore\":\"89\",\"ReadingComprehensionInstruction\":\"���ں�\",\"ReadingComprehensionScore\":\"78\",\"RecollectInstruction\":\"��,����\",\"RecollectScore\":\"63\",\"RepeataSentenceInstruction\":\"������\",\"RepeataSentenceScore\":\"69\",\"ReportId\":\"1442316614\",\"SumScore\":\"52\",\"TimeOrientaionInstruction\":\"һ����\",\"TimeOrientaionScore\":\"68\",\"WritingInstruction\":\"һ��\",\"ID\":1},\"ID\":1442316614}"));
				
				HttpEntity httpEntity;
				try {
					httpEntity = new UrlEncodedFormEntity(params, "utf-8");
					httpRequest.setEntity(httpEntity);
					HttpClient httpClient = new DefaultHttpClient();
					// 连接超时
					httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000 * 10);
					// 读取超时
					httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000 * 10);
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						result = EntityUtils.toString(httpResponse.getEntity());
						JSONObject jsonobject;
						jsonobject = new JSONObject(result);
//						Log.v("TAG", "result : " + result.toString());
//						Log.v("TAG", "jsonobject : " + jsonobject.toString());
						boolean isSuccess = jsonobject.getBoolean("success");
						String message = jsonobject.getString("resultData");
						if (isSuccess) {
							setMessage(101, message, 1);
						} else {
							if (jsonobject.getString("message") != null) {
								message = jsonobject.getString("message");
							}
							setMessage(101, message, 2);
						}
					} else {
						setMessage(101, "网络异常，请检查网络！", 2);
					}
				} catch (Exception e) {
					e.printStackTrace();
					setMessage(101, "网络异常，请检查网络！", 2);
				}
			}
		}).start();
		
	}

	public void setMessage(int arg, String result, int flaglogout) {
		//
		Message message = new Message();
		message.arg1 = arg;
		Bundle bundle = new Bundle();
		bundle.putString("result", result);
		bundle.putInt("flag", flaglogout);
		message.setData(bundle);
		handler.sendMessage(message);
	}
}
