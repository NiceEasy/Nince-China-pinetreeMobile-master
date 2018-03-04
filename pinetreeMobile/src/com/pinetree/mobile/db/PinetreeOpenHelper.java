package com.pinetree.mobile.db;

import java.util.List;

import com.pinetree.mobile.bean.PlanList;
import com.pinetree.mobile.bean.PlanSubList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PinetreeOpenHelper extends SQLiteOpenHelper {

	/**
	 * 用户信息建表语句
	 */
	public static final String CREATE_USERINFO = "create table UserInfo(id integer primary key autoincrement,departmentId text,"
			+ "documentNo text,employeeId text,employeeName text,id_ text,sex text,subsidiaryId text,tel text,url1 text)";
	/**
	 * 日程安排建表语句 
	*/
	public static final String CREATE_SCHEDULE_WORK = "create table ScheduleWork(id integer primary key autoincrement,beginTime text,beginTimeSub text,"
			+ "custAddress text,custID text,custName text,custPhone text,endTime text,endTimeSub text,id_ text,isResign text,isSign text,haveSign text"
			+ ",prodName text,loadDataTag text,signResignTag text,reimbursement text,sortDate DATETIME,lat text,lng text,sex text,age text,reportStatus text," +
			"isVouchRec text,vouchAmount text,renewalAmount text,prodID text,prodType text,empNum text,projectId text,beforeName text," +
			"idNum text,healthCard text,disabledSoldierPaper text,disabledPaper text,birth text,familyName text,placeOrigin text,cityAddress text," +
			"linkPhone2 text,postCode text,custEmail text,categoryID text)";

	/**
	 * 签到签退表建表语句SignInOut
	 */
	public static final String CREATE_SIGNINOUT = "create table SignInOut(id integer primary key autoincrement,taskId text,isSignInOut text,date text," +
			"state text,empNum text)";
	
	/**
	 * 创建客户签字表
	 */
	public static final String CREATE_CUSTOMERSIGN="create table CustomerSign(id integer primary key autoincrement,customerName text,serverDate text,"
			+ "serverName text,signContract text,contractExtension text,reimbursementAmount text,specialCircumstances text,signImageUri text,"
			+ "relation text,signMoney text,scheduleId text,photoImageUri text,saveState text,submitState text,payerName text,vouchAmount text," +
			"renewalAmount text,signRelation text)";
	/**
	 * 担保金额数据缓存表
	 */
	public static final String CREATE_ASSURE_CACHE="create table Assure_Cache(id integer primary key autoincrement,custID text,custName text,employeeID text,"
			+ "employeeName text,account text,vouchDate text,status text)";
	/**
	 * 添加担保金额表
	 */
	public static final String CREATE_ADD_ASSURE="create table add_Assure(id integer primary key autoincrement,custID text,custName text,employeeID text,"
			+ "employeeName text,account text,vouchDate text,status text,isShow text)";
	
	/**
	 * 智力状态检查评估表
	 */
	public static final String CREATE_BRAIN_STATE="create table brain_state(id integer primary key autoincrement,scheID text,custID text,sjdx text,"
			+ "dddx text,jy text,zyyjs text,hy text,mm text,fsjz text,zxml text,ydlj text,sx text,gtnl text,education text,assess text,result text,"
			+ "walkAbility text,createTime text,isNew text)";

	
	/**
	 * 日常生活自理能力表
	 */
	public static final String CREATE_EVERYDAY_STATE = "create table everyday_state(id integer primary key autoincrement,scheID text,custID text," +
			"jc text,xz text,zs text,cy text,db text,xb text,yc text,qyzy text,pdz text,sxlt text,assess text,result text,note text,createTime text,isNew text)";
	
	
	/**
	 * 药品使用表
	 */
	private static final String CREATE_DRUG_STATE = "create table drug_state(id integer primary key autoincrement,scheID text,custID text," +
			"ys text,sm text,pb text,pn text,sl text,tl text,yy text,yis text,xli text,grws text,xt text,mb text,hx text,xy text,tww text,mr text,sz text," +
			"yc text,yh text,xb text,fb text,createTime text,isNew text)";
	
	
	
	/**
	 * 基本情况表
	 */
	private static final String CREATE_BASIC_INFORMATION = "create table basic_information(id integer primary key autoincrement,scheID text,custID text," +
			"marriage text,occupational text,child text,phz text,nurse text,lighting text,air text,humidity text,temperature text,odor text,economy text,wc text,cdw text," +
			"hj text,zz text,qy text,fjzxl text,assist text,history text,drug text,createTime text,isNew text)";
	
	
	
	/**
	 * 步行能力评定标表
	 */
	private static final String CREATE_SPORT_ASSESS = "create table sport_assess(id integer primary key autoincrement,scheID text,custID text," +
			"zwph text,lwph text,bxnl text,jl text,jzl text,ttpf text,jl_yc text,jzl_yc text,ttpf_yc text,jzl_yc_zc text,jzl_yc_qg text,jzl_yc_rc text,jl_yc_zc text," +
			"jl_yc_qg text,jl_yc_rc text,ttpf_yc_zc text,ttpf_yc_qg text,ttpf_yc_rc text,jl_yc_zc_shou text,jl_yc_zc_sz text,jl_yc_zc_xz text,jl_yc_zc_qt text," +
			"jl_yc_qg_bj text,jl_yc_qg_yj text,jl_yc_qg_tj text,jl_yc_qg_xj text,jl_yc_qg_fj text,jl_yc_qg_qt text,jl_yc_rc_sz text,jl_yc_rc_xz text,jl_yc_rc_jiao text," +
			"jl_yc_rc_qt text,jzl_yc_zc_shou text,jzl_yc_zc_sz text,jzl_yc_zc_xz text,jzl_yc_zc_qt text,jzl_yc_qg_bj text,jzl_yc_qg_yj text,jzl_yc_qg_tj text," +
			"jzl_yc_qg_xj text,jzl_yc_qg_fj text,jzl_yc_qg_qt text,jzl_yc_rc_sz text,jzl_yc_rc_xz text,jzl_yc_rc_jiao text,jzl_yc_rc_qt text,ttpf_yc_zc_shou text, " +
			"ttpf_yc_zc_sz text,ttpf_yc_zc_xz text,ttpf_yc_zc_qt text,ttpf_yc_qg_bj text,ttpf_yc_qg_yj text,ttpf_yc_qg_tj text,ttpf_yc_qg_xj text,ttpf_yc_qg_fj text," +
			"ttpf_yc_qg_qt text,ttpf_yc_rc_sz text,ttpf_yc_rc_xz text,ttpf_yc_rc_jiao text,ttpf_yc_rc_qt text,fxdj text,createTime text,isNew text)"  ;
	
	
	/**
	 * 服务记录表
	 */
	private static final String CREATE_SERVER_RECORD = "create table server_record(id integer primary key autoincrement,custID text,scheduleId text," +
			"age text,bloodsugar text,breath text,content text,custName text,empName text,endDate text,highpressure text,lowpressure text,planID text," +
			"planList text,planSubList text,pulse text,sex text,recorddate text,startDate text,targetDate text,saveState text,submitState text,createTime text," +
			"otherSub text,isShow text,isRecord text,temperature text)";
	
	/**
	 * COPM表
	 */
	private static final String CREATE_COPM = "create table copm_record(id integer primary key autoincrement,custID text,scheID text," +
			"content text,situation text,satisf text,xianPoint text,zuoyeChange text,manPoint text,manChange text,number text," +
			"submitState text,saveState text,createTime text,unmodifiableState text,isLast text,situationSum text,satisfSum text,isNew text," +
			"specialAccount text)";
	

	/**
	 * COPM表
	 */
	private static final String CREATE_COPM_ADD = "create table copm_add(id integer primary key autoincrement,custID text,scheID text," +
			"content text,situation text,satisf text,xianPoint text,zuoyeChange text,manPoint text,manChange text,number text," +
			"submitState text,saveState text,createTime text,unmodifiableState text,isLast text,situationSum text,satisfSum text,isNew text," +
			"specialAccount text)";
	
	/**
	 * 
	 * 六个评估报告的提交状态表
	 */
	private static final String CREATE_RECORD_STATE  = "create table record_state(id integer primary key autoincrement,custID text,scheID text,basicSubmitState text," +
			"brainSubmitState text,everydaySubmitState text,drugSubmitState text,sportSubmitState text,copmSubmitState text)";
	
	/**
	 * 特殊情况表
	 */
	private static final String CREATE_SPECIAL_RECORD = "create table special_record(id integer primary key autoincrement,custID text,special text)";
	
	/**
	 * 所有康复措施数据表
	 */
	private static final String CREATE_RECOVERY_MEASURES = "create table rehabilitation_measure(_id integer primary key " +
			"autoincrement,id text,title text,fatherid text,content text,type text,bigTitle text,smallTitle text,bigTitleId text)";
	
	/**
	 * 所有健康宣教数据表
	 */
	private static final String CREATE_HEALTH_EDUCATION = "create table health_education(_id integer primary key autoincrement," +
			"id text,title text,fatherid text,content text,type text,bigTitle text,smallTitle text,bigTitleId text)";
	
	
	/**
	 * 所有可选写过评估报告但没写康复护理计划的客户表
	 */
	private static final String CREATE_ASSESS_CUSTOMER= "create table assess_customer(id integer primary key autoincrement" +
			")";
	
	
	/**
	 * 康复护理计划表-基本数据
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_BASIC= "create table " +
			"rehabilitation_nursing_plan(id integer primary key autoincrement,planID text,custID text," +
			"cudeID text,custName text,createUser text,sex text,age text,illhis text,checkbody text," +
			"adlscore text,mmsescore text,druguse text,notice text,comitStatus text,signinDate text," +
			"createTime text,version text,scheId text,content text,submitState text,saveState text," +
			"empName text,serDate text)";
	/**
	 * 康复护理计划表-病史记录
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_HISTORY = "create table " +
			"regainHistoryRecords(id integer primary key autoincrement,planID text,num text," +
			"hisRecName text,description text)";
	
	/**
	 * 康复护理计划表-现存问题
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_CURRQUESTION = "create table " +
			"currQuestion(id integer primary key autoincrement,planID text,num text,qtype text," +
			"question text,term text,sortIndex text)";
	
	/**
	 * 康复护理计划表-拟解决问题
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_SOLVEQUESTION = "create table" +
			" solveQuestion(id integer primary key autoincrement,planID text,num text,qtype text," +
			"question text,term text,sortIndex text)";
	
	/**
	 * 康复护理计划表-康复目标
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_REGAINTARGET = "create table" +
			" regainTarget(id integer primary key autoincrement,planID text,num text,content text," +
			"term text,sortIndex text)";
	
	/**
	 * 康复护理计划表-康复措施
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_REGAINTARGETSUB = "create table" +
			" regainTargetSub(id integer primary key autoincrement,planID text,num text,content text," +
			"sortIndex text,targetID text,bigTitle text,smallTitle text,planNum text,realNum text)";
	
	/**
	 * 康复护理计划表-健康宣教
	 */
	private static final String CREATE_REHABILITATION_NURSING_PLAN_REGAINPUBLICIZE = "create table" +
			" regainPublicize(id integer primary key autoincrement,planID text,num text,content text," +
			"bigTitle text,smallTitle text)";
	
	public PinetreeOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SCHEDULE_WORK);// 创建日程安排表
		db.execSQL(CREATE_SIGNINOUT);// 创建签到签退表
		db.execSQL(CREATE_CUSTOMERSIGN);//创建客户签字表
		db.execSQL(CREATE_ASSURE_CACHE);//创建担保金额缓存表
		db.execSQL(CREATE_ADD_ASSURE);//添加担保金额表
		db.execSQL(CREATE_BRAIN_STATE);//智力状态评估表
		db.execSQL(CREATE_EVERYDAY_STATE);//日常生活自理能力表
		db.execSQL(CREATE_DRUG_STATE);//查体（药品使用）表
		db.execSQL(CREATE_BASIC_INFORMATION);//基本情况表
		db.execSQL(CREATE_SPORT_ASSESS);//步行能力评定表
		db.execSQL(CREATE_SERVER_RECORD);//每日情况记录表
		db.execSQL(CREATE_RECORD_STATE);//评估表提交状态
		db.execSQL(CREATE_SPECIAL_RECORD);// 特殊情况表
		db.execSQL(CREATE_COPM);//COPM表
		db.execSQL(CREATE_COPM_ADD);//COPM 动态添加表
		db.execSQL(CREATE_RECOVERY_MEASURES);//所有康复措施数据表
		db.execSQL(CREATE_HEALTH_EDUCATION);//所有健康宣教数据表
		db.execSQL(CREATE_ASSESS_CUSTOMER);//所有可选写过评估报告但没写康复护理计划的客户表
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_BASIC);//康复护理计划表-基本数据
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_HISTORY);//康复护理计划表-病史记录
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_CURRQUESTION);//康复护理计划表-现存问题
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_SOLVEQUESTION);//康复护理计划表-拟解决问题
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_REGAINTARGET);//康复护理计划表-康复目标
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_REGAINTARGETSUB);//康复护理计划表-康复措施
		db.execSQL(CREATE_REHABILITATION_NURSING_PLAN_REGAINPUBLICIZE);//康复护理计划表-健康宣教
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
