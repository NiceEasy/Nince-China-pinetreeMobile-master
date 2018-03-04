package com.pinetree.mobile.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fr.android.app.activity.IFAboutActivity4Pad;
import com.pinetree.mobile.bean.BasicInformation;
import com.pinetree.mobile.bean.BrainScore;
import com.pinetree.mobile.bean.Copm;
import com.pinetree.mobile.bean.Customer;
import com.pinetree.mobile.bean.CustomerSign;
import com.pinetree.mobile.bean.DrugState;
import com.pinetree.mobile.bean.EverydayState;
import com.pinetree.mobile.bean.GuarantyMoney;
import com.pinetree.mobile.bean.PlanList;
import com.pinetree.mobile.bean.PlanSubList;
import com.pinetree.mobile.bean.RecordSubmitState;
import com.pinetree.mobile.bean.RecoveryNursingPlan;
import com.pinetree.mobile.bean.RegainHistoryRecords;
import com.pinetree.mobile.bean.RegainPublicize;
import com.pinetree.mobile.bean.RegainQuestion;
import com.pinetree.mobile.bean.RegainTarget;
import com.pinetree.mobile.bean.RegainTargetSub;
import com.pinetree.mobile.bean.RehabilitationMeasures;
import com.pinetree.mobile.bean.ServerRecord;
import com.pinetree.mobile.bean.SignInOut;
import com.pinetree.mobile.bean.SpecialCircumstances;
import com.pinetree.mobile.bean.SportAssess;
import com.pinetree.mobile.bean.User.UserInfo;
import com.pinetree.mobile.bean.UserInfoDB;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class PinetreeDB {

	/**
	 * 数据库名
	 */
	public static final String DB_NAME = "PinetreeCache";
	/**
	 * 数据库版本
	 */
	public static final int VERSION = 2;

	private SQLiteDatabase db;
	private static PinetreeDB pinetreeDB;// 数据库

	/**
	 * 将构造私有化
	 */
	private PinetreeDB(Context context) {
		PinetreeOpenHelper pinetreeOpenHelper = new PinetreeOpenHelper(context,
				DB_NAME, null, VERSION);
		db = pinetreeOpenHelper.getWritableDatabase();
	}

	/**
	 * 获取PinetreeDB的实例
	 */
	public synchronized static PinetreeDB getInstance(Context context) {
		if (pinetreeDB == null) {
			pinetreeDB = new PinetreeDB(context);
		}
		return pinetreeDB;
	}

	/**
	 * 将用户信息存到数据库中
	 */
	public void saveUserInfo(UserInfo userInfo) {
		if (userInfo != null) {
			ContentValues values = new ContentValues();
			values.put("departmentId", userInfo.getDepartmentId());
			values.put("documentNo", userInfo.getDocumentNo());
			values.put("employeeId", userInfo.getEmployeeId());
			values.put("employeeName", userInfo.getEmployeeName());
			values.put("id_", userInfo.getId());
			values.put("sex", userInfo.getSex());
			values.put("subsidiaryId", userInfo.getSubsidiaryId());
			values.put("tel", userInfo.getTel());
			values.put("url1", userInfo.getUrl1());
			db.insert("UserInfo", null, values);
		}
	}

	/**
	 * 查询用户信息表中根据登录名(雇员名称)=？查询雇员id select employeeId from UserInfo where
	 * employeeName='李明'
	 */
	public String selectEmployeeId(String employeeName) {
		String employeeId = null;
		Cursor cursor = db.rawQuery(
				"select employeeId from UserInfo where employeeName='"
						+ employeeName + "'", null);
		if (cursor.moveToFirst()) {
			do {
				employeeId = cursor.getString(cursor
						.getColumnIndex("employeeId"));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return employeeId;
	}

	/**
	 * 查询登陆名（雇员名）对应的所有雇员信息，封装成对象返回
	 */
	public UserInfoDB getUserInfo(String employeeName) {
		UserInfoDB userInfo = new UserInfoDB();
		Cursor cursor = db.rawQuery(
				"select * from UserInfo where employeeName='" + employeeName
						+ "'", null);
		if (cursor.moveToFirst()) {
			do {
				userInfo.setDepartmentId(cursor.getString(cursor.getColumnIndex("departmentId")));
				userInfo.setDocumentNo(cursor.getString(cursor.getColumnIndex("documentNo")));
				userInfo.setEmployeeId(cursor.getString(cursor.getColumnIndex("employeeId")));
				userInfo.setEmployeeName(cursor.getString(cursor.getColumnIndex("employeeName")));
				userInfo.setId(cursor.getString(cursor.getColumnIndex("id_")));
				userInfo.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				userInfo.setSubsidiaryId(cursor.getString(cursor.getColumnIndex("subsidiaryId")));
				userInfo.setTel(cursor.getString(cursor.getColumnIndex("tel")));
				userInfo.setUrl1(cursor.getString(cursor.getColumnIndex("url1")));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return userInfo;
	}

	/**
	 * 将日程安排中客户信息插入到数据中
	 */
	public void saveCustomer(Customer customer) {

		if (customer != null) {
			ContentValues values = new ContentValues();
			values.put("beginTime", customer.getBeginTime());
			values.put("beginTimeSub", customer.getBeginTimeSub());
			values.put("custAddress", customer.getCustAddress());
			values.put("custID", customer.getCustID());
			values.put("custName", customer.getCustName());
			values.put("custPhone", customer.getCustPhone());
			values.put("endTime", customer.getEndTime());
			values.put("endTimeSub", customer.getEndTimeSub());
			values.put("id_", customer.getId());
			values.put("haveSign", customer.getHaveSign());
			values.put("prodName", customer.getProdName());
			values.put("loadDataTag", customer.getLoadDataTag());
			values.put("signResignTag", customer.getSignResignTag());
			values.put("reimbursement", customer.getReimbursement());
			values.put("lat", customer.getLat());
			values.put("lng", customer.getLng());
			values.put("sex", customer.getSex());
			values.put("age", customer.getAge());
			values.put("isResign", customer.getIsResign());
			values.put("isSign", customer.getIsSign());
			values.put("isVouchRec", customer.getIsVouchRec());
			values.put("vouchAmount", customer.getVouchAmount());
			values.put("renewalAmount", customer.getRenewalAmount());
			values.put("prodID", customer.getProdID());
			values.put("prodType", customer.getProdType());
			values.put("empNum", customer.getEmpNum());
			values.put("projectId", customer.getProjectId());
			values.put("beforeName", customer.getBeforeName());
			values.put("idNum", customer.getIdNum());
			values.put("healthCard", customer.getHealthCard());
			values.put("disabledSoldierPaper", customer.getDisabledSoldierPaper());
			values.put("disabledPaper", customer.getDisabledPaper());
			values.put("birth", customer.getBirth());
			values.put("familyName", customer.getFamilyName());
			values.put("placeOrigin", customer.getPlaceOrigin());
			values.put("cityAddress", customer.getCityAddress());
			values.put("linkPhone2", customer.getLinkPhone2());
			values.put("postCode", customer.getPostCode());
			values.put("custEmail", customer.getCustEmail());
			values.put("categoryID", customer.getCategoryID());
			
			String dateTime = customer.getBeginTime().substring(0, 10) + " "
					+ customer.getBeginTime().substring(11, 19);
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(dateTime);
				values.put("sortDate", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			db.insert("ScheduleWork", null, values);
		}
	}
	/**
	 * 更新经纬度
	 * 
	 */
	public boolean updateCustomer(String id,String lat,String lng) {
		if (id != null) {
			ContentValues values = new ContentValues();
			values.put("lat", lat);
			values.put("lng", lng);
			int row = db.update("ScheduleWork", values, "id_=?",
					new String[] { id});
			if (row == 0) {
				return false;
			} else {
				return true;
				
			}
		}
		return false;
	}

	/**
	 * 删除缓存的所有日程安排表数据
	 */
	public void deleteAllCustomer() {
		db.delete("ScheduleWork", null, null);
	}

	/**
	 * 查询本地缓存的日程安排数据
	 */
	public List<Customer> selectCustomerWork() {

		List<Customer> list = new ArrayList<Customer>();
		Cursor cursor = db.rawQuery("select * from ScheduleWork", null);
		if (cursor.moveToLast()) {
			do {
				Customer customer = new Customer();
				String a = cursor.getString(cursor.getColumnIndex("beginTime"));
				customer.setBeginTime(cursor.getString(cursor.getColumnIndex("beginTime")));
				customer.setBeginTimeSub(cursor.getString(cursor.getColumnIndex("beginTimeSub")));
				customer.setCustAddress(cursor.getString(cursor.getColumnIndex("custAddress")));
				customer.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				customer.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				customer.setCustPhone(cursor.getString(cursor.getColumnIndex("custPhone")));
				customer.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
				customer.setEndTimeSub(cursor.getString(cursor.getColumnIndex("endTimeSub")));
				customer.setId(cursor.getString(cursor.getColumnIndex("id_")));
				customer.setHaveSign(cursor.getString(cursor.getColumnIndex("haveSign")));
				customer.setProdName(cursor.getString(cursor.getColumnIndex("prodName")));
				customer.setLoadDataTag(cursor.getString(cursor.getColumnIndex("loadDataTag")));
				customer.setSignResignTag(cursor.getString(cursor.getColumnIndex("signResignTag")));
				customer.setReimbursement(cursor.getString(cursor.getColumnIndex("reimbursement")));
				customer.setLat(cursor.getString(cursor.getColumnIndex("lat")));
				customer.setLng(cursor.getString(cursor.getColumnIndex("lng")));
				customer.setAge(cursor.getString(cursor.getColumnIndex("age")));
				customer.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				customer.setIsResign(cursor.getString(cursor.getColumnIndex("isResign")));
				customer.setIsSign(cursor.getString(cursor.getColumnIndex("isSign")));
				customer.setIsVouchRec(cursor.getString(cursor.getColumnIndex("isVouchRec")));
				customer.setVouchAmount(cursor.getString(cursor.getColumnIndex("vouchAmount")));
				customer.setRenewalAmount(cursor.getString(cursor.getColumnIndex("renewalAmount")));
				customer.setProdID(cursor.getString(cursor.getColumnIndex("prodID")));
				customer.setProdType(cursor.getString(cursor.getColumnIndex("prodType")));
				customer.setEmpNum(cursor.getString(cursor.getColumnIndex("empNum")));
				customer.setProjectId(cursor.getString(cursor.getColumnIndex("projectId")));
				customer.setBeforeName(cursor.getString(cursor.getColumnIndex("beforeName")));
				customer.setIdNum(cursor.getString(cursor.getColumnIndex("idNum")));
				customer.setHealthCard(cursor.getString(cursor.getColumnIndex("healthCard")));
				customer.setDisabledSoldierPaper(cursor.getString(cursor.getColumnIndex("disabledSoldierPaper")));
				customer.setDisabledPaper(cursor.getString(cursor.getColumnIndex("disabledPaper")));
				customer.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
				customer.setFamilyName(cursor.getString(cursor.getColumnIndex("familyName")));
				customer.setPlaceOrigin(cursor.getString(cursor.getColumnIndex("placeOrigin")));
				customer.setCityAddress(cursor.getString(cursor.getColumnIndex("cityAddress")));
				customer.setLinkPhone2(cursor.getString(cursor.getColumnIndex("linkPhone2")));
				customer.setPostCode(cursor.getString(cursor.getColumnIndex("postCode")));
				customer.setCustEmail(cursor.getString(cursor.getColumnIndex("custEmail")));
				customer.setCategoryID(cursor.getString(cursor.getColumnIndex("categoryID")));
				list.add(customer);
			} while (cursor.moveToPrevious());
		}
		cursor.close();
		return list;
	}

	/**
	 * 根据日程id查询本地数据库中客户日程数据
	 */
	public Customer getCustomer(String id) {
		Customer customer1 = new Customer();
		Cursor cursor = db.rawQuery(
				"select * from ScheduleWork where id_='" + id + "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				customer1.setBeginTime(cursor.getString(cursor.getColumnIndex("beginTime")));
				customer1.setBeginTimeSub(cursor.getString(cursor.getColumnIndex("beginTimeSub")));
				customer1.setCustAddress(cursor.getString(cursor.getColumnIndex("custAddress")));
				customer1.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				customer1.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				customer1.setCustPhone(cursor.getString(cursor.getColumnIndex("custPhone")));
				customer1.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
				customer1.setEndTimeSub(cursor.getString(cursor.getColumnIndex("endTimeSub")));
				customer1.setId(cursor.getString(cursor.getColumnIndex("id_")));
				customer1.setHaveSign(cursor.getString(cursor.getColumnIndex("haveSign")));
				customer1.setProdName(cursor.getString(cursor.getColumnIndex("prodName")));
				customer1.setLoadDataTag(cursor.getString(cursor.getColumnIndex("loadDataTag")));
				customer1.setSignResignTag(cursor.getString(cursor.getColumnIndex("signResignTag")));
				customer1.setReimbursement(cursor.getString(cursor.getColumnIndex("reimbursement")));
				customer1.setLat(cursor.getString(cursor.getColumnIndex("lat")));
				customer1.setLng(cursor.getString(cursor.getColumnIndex("lng")));
				customer1.setAge(cursor.getString(cursor.getColumnIndex("age")));
				customer1.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				customer1.setIsResign(cursor.getString(cursor.getColumnIndex("isResign")));
				customer1.setIsSign(cursor.getString(cursor.getColumnIndex("isSign")));
				customer1.setIsVouchRec(cursor.getString(cursor.getColumnIndex("isVouchRec")));
				customer1.setVouchAmount(cursor.getString(cursor.getColumnIndex("vouchAmount")));
				customer1.setRenewalAmount(cursor.getString(cursor.getColumnIndex("renewalAmount")));
				customer1.setProdID(cursor.getString(cursor.getColumnIndex("prodID")));
				customer1.setProdType(cursor.getString(cursor.getColumnIndex("prodType")));
				customer1.setEmpNum(cursor.getString(cursor.getColumnIndex("empNum")));
				customer1.setProjectId(cursor.getString(cursor.getColumnIndex("projectId")));
				customer1.setBeforeName(cursor.getString(cursor.getColumnIndex("beforeName")));
				customer1.setIdNum(cursor.getString(cursor.getColumnIndex("idNum")));
				customer1.setHealthCard(cursor.getString(cursor.getColumnIndex("healthCard")));
				customer1.setDisabledSoldierPaper(cursor.getString(cursor.getColumnIndex("disabledSoldierPaper")));
				customer1.setDisabledPaper(cursor.getString(cursor.getColumnIndex("disabledPaper")));
				customer1.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
				customer1.setFamilyName(cursor.getString(cursor.getColumnIndex("familyName")));
				customer1.setPlaceOrigin(cursor.getString(cursor.getColumnIndex("placeOrigin")));
				customer1.setCityAddress(cursor.getString(cursor.getColumnIndex("cityAddress")));
				customer1.setLinkPhone2(cursor.getString(cursor.getColumnIndex("linkPhone2")));
				customer1.setPostCode(cursor.getString(cursor.getColumnIndex("postCode")));
				customer1.setCustEmail(cursor.getString(cursor.getColumnIndex("custEmail")));
				customer1.setCategoryID(cursor.getString(cursor.getColumnIndex("categoryID")));
				return customer1;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 向签到签退表中插入数据
	 */
	public boolean saveSignInOut(SignInOut signInOut) {
		if (signInOut != null) {
			ContentValues values = new ContentValues();
			values.put("taskId", signInOut.getTaskId());
			values.put("isSignInOut", signInOut.getIsSignInOut());
			values.put("date", signInOut.getDate());
			values.put("state", signInOut.getState());
			values.put("empNum", signInOut.getEmpNum());
			long row = db.insert("SignInOut", null, values);
			if (row == -1) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 通过日程id修改签到签退表中的数据
	 */
	public boolean updateSignInOut(String taskId, SignInOut signInOut) {
		if (!"".equals(signInOut) && !"".equals(signInOut) && null != taskId
				&& null != signInOut) {
			ContentValues values = new ContentValues();
			values.put("taskId", signInOut.getTaskId());
			values.put("isSignInOut", signInOut.getIsSignInOut());
			values.put("date", signInOut.getDate());
			values.put("state", signInOut.getState());
			values.put("empNum", signInOut.getEmpNum());
			int row = db.update("SignInOut", values, "taskId=?",
					new String[] { taskId });
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean updateSignInOutState(String taskId, String date) {
		if (taskId != null) {
			ContentValues values = new ContentValues();
			values.put("state", "0");
			int row = db.update("SignInOut", values, "taskId=? and date=?",
					new String[] { taskId, date });
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 查询签到签退表中的所有数据
	 */
	public List<SignInOut> selectAllSignInOut() {
		List<SignInOut> list = new ArrayList<SignInOut>();
		Cursor cursor = db.rawQuery("select * from SignInOut",null);
		if (cursor.moveToLast()) {
			do {
				SignInOut signInOut = new SignInOut();
				signInOut.setDate(cursor.getString(cursor.getColumnIndex("date")));
				signInOut.setIsSignInOut(cursor.getString(cursor.getColumnIndex("isSignInOut")));
				signInOut.setTaskId(cursor.getString(cursor.getColumnIndex("taskId")));
				signInOut.setState(cursor.getString(cursor.getColumnIndex("state")));
				signInOut.setEmpNum(cursor.getString(cursor.getColumnIndex("empNum")));
				list.add(signInOut);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	/**
	 * 删除签到签退表15天之前的数据
	 */
	public void deleteSignInOut(String id) {
		if (!"".equals(id)) {
			db.delete("SignInOut", "taskId=?", new String[] { id });
		}
	}
	/**
	 * 根据日程id查询签到签退 select isSignInOut from SignInOut where taskId="000000"
	 */
	public String selectSignInOut(String taskId) {
		String isSignInOut = null;
		Cursor cursor = db.rawQuery(
				"select isSignInOut from SignInOut where taskId='" + taskId+ "'", null);
		if (cursor.moveToFirst()) {
			do {
				isSignInOut = cursor.getString(cursor
						.getColumnIndex("isSignInOut"));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return isSignInOut;
	}

	/**
	 * 查询签到签退表中操作过的所有数据isSignInOut！=2 select * from SignInOut where isSignInOut
	 * <> 2
	 */
	public List<SignInOut> getSignInOut() {
		ArrayList<SignInOut> list = new ArrayList<SignInOut>();
		Cursor cursor = db.rawQuery("select * from SignInOut where state <> 0",
				null);
		if (cursor.moveToFirst()) {
			do {
				SignInOut signInOut = new SignInOut();
				signInOut.setDate(cursor.getString(cursor.getColumnIndex("date")));
				signInOut.setIsSignInOut(cursor.getString(cursor.getColumnIndex("isSignInOut")));
				signInOut.setTaskId(cursor.getString(cursor.getColumnIndex("taskId")));
				signInOut.setState(cursor.getString(cursor.getColumnIndex("state")));
				signInOut.setEmpNum(cursor.getString(cursor.getColumnIndex("empNum")));
				list.add(signInOut);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 向客户签字表插入数据 
	 */
	public boolean saveCustomerSign(CustomerSign customerSign) {
		if (customerSign != null) {
			ContentValues values = new ContentValues();
			values.put("customerName", customerSign.getCustomerName());
			values.put("serverDate", customerSign.getServerDate());
			values.put("serverName", customerSign.getServerName());
			values.put("signContract", customerSign.getSignContract());
			values.put("contractExtension", customerSign.getContractExtension());
			values.put("reimbursementAmount",
					customerSign.getReimbursementAmount());
			values.put("specialCircumstances",
					customerSign.getSpecialCircumstances());
			values.put("signImageUri", customerSign.getSignImageUri());
			values.put("relation", customerSign.getRelation());
			values.put("signRelation", customerSign.getSignRelation());
			values.put("signMoney", customerSign.getSignMoney());
			values.put("scheduleId", customerSign.getScheduleId());
			values.put("photoImageUri", customerSign.getPhotoImageUri());
			values.put("submitState", "1");
			values.put("saveState", "1");
			values.put("vouchAmount", customerSign.getVouchAmount());
			values.put("renewalAmount", customerSign.getRenewalAmount());
			values.put("payerName", customerSign.getPayerName());
			long row = db.insert("CustomerSign", null, values);
			if (row == -1) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 根据日程id查询本地数据库中客户签字表信息
	 */
	public CustomerSign getCustomerSign(String id) {
		CustomerSign customerSign = new CustomerSign();
		Cursor cursor = db.rawQuery(
				"select * from CustomerSign where scheduleId='" + id + "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				customerSign.setCustomerName(cursor.getString(cursor.getColumnIndex("customerName")));
				customerSign.setServerDate(cursor.getString(cursor.getColumnIndex("serverDate")));
				customerSign.setServerName(cursor.getString(cursor.getColumnIndex("serverName")));
				customerSign.setSignContract(cursor.getString(cursor.getColumnIndex("signContract")));
				customerSign.setContractExtension(cursor.getString(cursor.getColumnIndex("contractExtension")));
				customerSign.setReimbursementAmount(cursor.getString(cursor.getColumnIndex("reimbursementAmount")));
				customerSign.setSpecialCircumstances(cursor.getString(cursor.getColumnIndex("specialCircumstances")));
				customerSign.setSignImageUri(cursor.getString(cursor.getColumnIndex("signImageUri")));
				customerSign.setRelation(cursor.getString(cursor.getColumnIndex("relation")));
				customerSign.setSignRelation(cursor.getString(cursor.getColumnIndex("signRelation")));
				customerSign.setSignMoney(cursor.getString(cursor.getColumnIndex("signMoney")));
				customerSign.setScheduleId(cursor.getString(cursor.getColumnIndex("scheduleId")));
				customerSign.setPhotoImageUri(cursor.getString(cursor.getColumnIndex("photoImageUri")));
				customerSign.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				customerSign.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				customerSign.setVouchAmount(cursor.getString(cursor.getColumnIndex("vouchAmount")));
				customerSign.setRenewalAmount(cursor.getString(cursor.getColumnIndex("renewalAmount")));
				customerSign.setPayerName(cursor.getString(cursor.getColumnIndex("payerName")));
				return customerSign;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return null;
	}
	/**
	 * 查询签字表中的所有数据
	 */
	public List<CustomerSign> selectAllCustomerSign() {
		List<CustomerSign> list = new ArrayList<CustomerSign>();
		Cursor cursor = db.rawQuery("select * from CustomerSign",null);
		if (cursor.moveToLast()) {
			do {
				CustomerSign customerSign = new CustomerSign();
				customerSign.setCustomerName(cursor.getString(cursor.getColumnIndex("customerName")));
				customerSign.setServerDate(cursor.getString(cursor.getColumnIndex("serverDate")));
				customerSign.setServerName(cursor.getString(cursor.getColumnIndex("serverName")));
				customerSign.setSignContract(cursor.getString(cursor.getColumnIndex("signContract")));
				customerSign.setContractExtension(cursor.getString(cursor.getColumnIndex("contractExtension")));
				customerSign.setReimbursementAmount(cursor.getString(cursor.getColumnIndex("reimbursementAmount")));
				customerSign.setSpecialCircumstances(cursor.getString(cursor.getColumnIndex("specialCircumstances")));
				customerSign.setSignImageUri(cursor.getString(cursor.getColumnIndex("signImageUri")));
				customerSign.setRelation(cursor.getString(cursor.getColumnIndex("relation")));
				customerSign.setSignRelation(cursor.getString(cursor.getColumnIndex("signRelation")));
				customerSign.setSignMoney(cursor.getString(cursor.getColumnIndex("signMoney")));
				customerSign.setScheduleId(cursor.getString(cursor.getColumnIndex("scheduleId")));
				customerSign.setPhotoImageUri(cursor.getString(cursor.getColumnIndex("photoImageUri")));
				customerSign.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				customerSign.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				customerSign.setVouchAmount(cursor.getString(cursor.getColumnIndex("vouchAmount")));
				customerSign.setRenewalAmount(cursor.getString(cursor.getColumnIndex("renewalAmount")));
				customerSign.setPayerName(cursor.getString(cursor.getColumnIndex("payerName")));
				list.add(customerSign);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	/**
	 * 根据日程id修改保存和提交按钮状态 0表示已经保存 1表示没保存
	 */
	public boolean updateSaveCustomerSign(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("saveState", "0");
			int row = db.update("CustomerSign", values,"scheduleId=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改保存和提交按钮状态 0表示已经提交 1表示没提交
	 */
	public boolean updateCustomerSign(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("submitState", "0");
			values.put("saveState", "0");
			int row = db.update("CustomerSign", values,"scheduleId=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据日程id删除客户签字表中对应的信息
	 */
	public void deleteCustomerSign(String id) {
		if (!"".equals(id)) {
			db.delete("CustomerSign", "scheduleId=?", new String[] { id });
		}
	}

	/**
	 * 向担保金额缓存表插入数据
	 */
	public void saveAssureCache(GuarantyMoney guarantyMoney) {
		if (guarantyMoney != null) {
			ContentValues values = new ContentValues();
			values.put("custName", guarantyMoney.getCustName());
			values.put("custID", guarantyMoney.getCustID());
			values.put("employeeName", guarantyMoney.getEmployeeName());
			values.put("employeeID", guarantyMoney.getEmployeeID());
			values.put("account", guarantyMoney.getAccount());
			values.put("status", guarantyMoney.getStatus());
			values.put("vouchDate", guarantyMoney.getVouchDate());
			db.insert("Assure_Cache", null, values);
		}
	}

	/**
	 * 根据客户id查询所有缓存的担保金额
	 */
	public List<GuarantyMoney> selectAssureCacheByCustId(String custId) {
		List<GuarantyMoney> list = new ArrayList<GuarantyMoney>();
		Cursor cursor = db.rawQuery("select * from Assure_Cache where custID='"
				+ custId + "'", null);
		if (cursor.moveToFirst()) {
			do {
				GuarantyMoney guarantyMoney = new GuarantyMoney();
				guarantyMoney.setCustID(cursor.getString(cursor
						.getColumnIndex("custID")));
				guarantyMoney.setCustName(cursor.getString(cursor
						.getColumnIndex("custName")));
				guarantyMoney.setEmployeeID(cursor.getString(cursor
						.getColumnIndex("employeeID")));
				guarantyMoney.setEmployeeName(cursor.getString(cursor
						.getColumnIndex("employeeName")));
				guarantyMoney.setAccount(cursor.getString(cursor
						.getColumnIndex("account")));
				guarantyMoney.setStatus(cursor.getString(cursor
						.getColumnIndex("status")));
				guarantyMoney.setVouchDate(cursor.getString(cursor
						.getColumnIndex("vouchDate")));
				list.add(guarantyMoney);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 添加担保金额
	 */
	public boolean saveAddAssure(GuarantyMoney guarantyMoney) {
		if (guarantyMoney != null) {
			ContentValues values = new ContentValues();
			values.put("custName", guarantyMoney.getCustName());
			values.put("custID", guarantyMoney.getCustID());
			values.put("employeeName", guarantyMoney.getEmployeeName());
			values.put("employeeID", guarantyMoney.getEmployeeID());
			values.put("account", guarantyMoney.getAccount());
			values.put("status", guarantyMoney.getStatus());
			values.put("vouchDate", guarantyMoney.getVouchDate());
			values.put("isShow", "1");// 1显示 0不显示
			long row = db.insert("add_Assure", null, values);
			if (row == -1) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 更具查询可显示的add担保金额
	 */
	public List<GuarantyMoney> selectAddAssureByCustId(String custId) {
		List<GuarantyMoney> list = new ArrayList<GuarantyMoney>();
		Cursor cursor = db.rawQuery("select * from add_Assure where custID='"
				+ custId + "' and isShow='1'", null);
		if (cursor.moveToFirst()) {
			do {
				GuarantyMoney guarantyMoney = new GuarantyMoney();
				guarantyMoney.setCustID(cursor.getString(cursor
						.getColumnIndex("custID")));
				guarantyMoney.setCustName(cursor.getString(cursor
						.getColumnIndex("custName")));
				guarantyMoney.setEmployeeID(cursor.getString(cursor
						.getColumnIndex("employeeID")));
				guarantyMoney.setEmployeeName(cursor.getString(cursor
						.getColumnIndex("employeeName")));
				guarantyMoney.setAccount(cursor.getString(cursor
						.getColumnIndex("account")));
				guarantyMoney.setStatus(cursor.getString(cursor
						.getColumnIndex("status")));
				guarantyMoney.setVouchDate(cursor.getString(cursor
						.getColumnIndex("vouchDate")));
				list.add(guarantyMoney);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 根据客户id删除所有的担保缓存
	 */
	public void deleteAllGuarant(String custID) {
		db.delete("Assure_Cache", "custID=?", new String[] { custID });
	}

	/**
	 * 
	 * 修改添加担保表中的isShow字段，让担保不显示
	 */
	public boolean updateAddAssureNotShow(String custID, String vouchDate) {
		if (!"".equals(custID) && !"".equals(vouchDate) && null != custID
				&& null != vouchDate) {
			ContentValues values = new ContentValues();
			values.put("isShow", "0");
			int row = db.update("add_Assure", values,
					"custID=? and vouchDate=?", new String[] { custID,
							vouchDate });
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 添加智力状态
	 */
	public boolean saveBrainState(BrainScore brainScore){
		if (null!=brainScore) {
			ContentValues values = new ContentValues();
			values.put("scheID", brainScore.getScheID());
			values.put("custID", brainScore.getCustID());
			values.put("sjdx", brainScore.getSjdx());
			values.put("dddx", brainScore.getDddx());
			values.put("jy", brainScore.getJy());
			values.put("zyyjs", brainScore.getZyyjs());
			values.put("hy", brainScore.getHy());
			values.put("mm", brainScore.getMm());
			values.put("fsjz", brainScore.getFsjz());
			values.put("zxml", brainScore.getZxml());
			values.put("ydlj", brainScore.getYdlj());
			values.put("sx", brainScore.getSx());
			values.put("gtnl", brainScore.getGtnl());
			values.put("education", brainScore.getEducation());
			values.put("assess", brainScore.getAssess());
			values.put("result", brainScore.getResult());
			values.put("createTime", brainScore.getCreateTime());
			values.put("isNew", brainScore.getIsNew());
			long row = db.insert("brain_state", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据日程id查询智力状态对象
	 */
	public BrainScore getBrainScoreByScheID(String scheID){
		BrainScore brainScore = new BrainScore();
		Cursor cursor = db.rawQuery(
				"select * from brain_state where scheID='" +scheID+ "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				brainScore.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				brainScore.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				brainScore.setSjdx(cursor.getString(cursor.getColumnIndex("sjdx")));
				brainScore.setDddx(cursor.getString(cursor.getColumnIndex("dddx")));
				brainScore.setJy(cursor.getString(cursor.getColumnIndex("jy")));
				brainScore.setZyyjs(cursor.getString(cursor.getColumnIndex("zyyjs")));
				brainScore.setHy(cursor.getString(cursor.getColumnIndex("hy")));
				brainScore.setMm(cursor.getString(cursor.getColumnIndex("mm")));
				brainScore.setFsjz(cursor.getString(cursor.getColumnIndex("fsjz")));
				brainScore.setZxml(cursor.getString(cursor.getColumnIndex("zxml")));
				brainScore.setYdlj(cursor.getString(cursor.getColumnIndex("ydlj")));
				brainScore.setSx(cursor.getString(cursor.getColumnIndex("sx")));
				brainScore.setGtnl(cursor.getString(cursor.getColumnIndex("gtnl")));
				brainScore.setEducation(cursor.getString(cursor.getColumnIndex("education")));
				brainScore.setAssess(cursor.getString(cursor.getColumnIndex("assess")));
				brainScore.setResult(cursor.getString(cursor.getColumnIndex("result")));
				brainScore.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				brainScore.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return brainScore;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 根据客户id查询智力状态对象的最新数据
	 */
	public BrainScore getBrainScoreOfNewest(String custID,String isNew){
		BrainScore brainScore = new BrainScore();
		Cursor cursor = db.rawQuery("select * from brain_state where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				brainScore.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				brainScore.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				brainScore.setSjdx(cursor.getString(cursor.getColumnIndex("sjdx")));
				brainScore.setDddx(cursor.getString(cursor.getColumnIndex("dddx")));
				brainScore.setJy(cursor.getString(cursor.getColumnIndex("jy")));
				brainScore.setZyyjs(cursor.getString(cursor.getColumnIndex("zyyjs")));
				brainScore.setHy(cursor.getString(cursor.getColumnIndex("hy")));
				brainScore.setMm(cursor.getString(cursor.getColumnIndex("mm")));
				brainScore.setFsjz(cursor.getString(cursor.getColumnIndex("fsjz")));
				brainScore.setZxml(cursor.getString(cursor.getColumnIndex("zxml")));
				brainScore.setYdlj(cursor.getString(cursor.getColumnIndex("ydlj")));
				brainScore.setSx(cursor.getString(cursor.getColumnIndex("sx")));
				brainScore.setGtnl(cursor.getString(cursor.getColumnIndex("gtnl")));
				brainScore.setEducation(cursor.getString(cursor.getColumnIndex("education")));
				brainScore.setAssess(cursor.getString(cursor.getColumnIndex("assess")));
				brainScore.setResult(cursor.getString(cursor.getColumnIndex("result")));
				brainScore.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				brainScore.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return brainScore;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return null;
	}
	/**
	 * 根据日程id删除智力状态表数据
	 */
	public void deleteBrainScoreByscheID(String scheID){
		if (!"".equals(scheID)) {
			db.delete("brain_state", "scheID=?", new String[]{scheID});
		}
	}
	
	/**
	 * 根据客户ID删除最新智力状态表数据
	 */
	public void deleteBrainScoreByCustID(String custID,String isNew){
		if (!"".equals(custID)) {
			db.delete("brain_state", "scheID=? and isNew=?", new String[]{custID,isNew});
		}
	}
	
	/**
	 * 保存每天日常生活自理能力的状态
	 */
	public boolean saveEverydayStateByScheID(EverydayState everydayState) {
		if (null != everydayState) {
			ContentValues values = new ContentValues();
			values.put("scheID", everydayState.getScheID());
			values.put("custID", everydayState.getCustID());
			values.put("jc", everydayState.getJc());
			values.put("xz", everydayState.getXz());
			values.put("zs", everydayState.getZs());					
			values.put("cy", everydayState.getCy());
			values.put("db", everydayState.getDb());
			values.put("xb", everydayState.getXb());
			values.put("yc", everydayState.getYc());
			values.put("qyzy", everydayState.getQyzy());
			values.put("pdz", everydayState.getPdz());		
			values.put("sxlt", everydayState.getSxlt());
			values.put("assess", everydayState.getAssess());
			values.put("result", everydayState.getResult());
			values.put("note", everydayState.getNote());
			values.put("createTime", everydayState.getCreateTime());	
			values.put("isNew", everydayState.getIsNew());

			long row = db.insert("everyday_state", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * 根据日程id查询日常生活自理状态对象
	 */
	public EverydayState getEverydayStateByScheID(String scheID) {
		
		EverydayState everydayState = new EverydayState();
		Cursor cursor = db.rawQuery(
				"select * from everyday_state where scheID='" +scheID+ "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				everydayState.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				everydayState.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				everydayState.setJc(cursor.getString(cursor.getColumnIndex("jc")));								
				everydayState.setXz(cursor.getString(cursor.getColumnIndex("xz")));
				everydayState.setZs(cursor.getString(cursor.getColumnIndex("zs")));
				everydayState.setCy(cursor.getString(cursor.getColumnIndex("cy")));
				everydayState.setDb(cursor.getString(cursor.getColumnIndex("db")));
				everydayState.setXb(cursor.getString(cursor.getColumnIndex("xb")));
				everydayState.setYc(cursor.getString(cursor.getColumnIndex("yc")));
				everydayState.setQyzy(cursor.getString(cursor.getColumnIndex("qyzy")));
				everydayState.setPdz(cursor.getString(cursor.getColumnIndex("pdz")));
				everydayState.setSxlt(cursor.getString(cursor.getColumnIndex("sxlt")));				
				everydayState.setAssess(cursor.getString(cursor.getColumnIndex("assess")));
				everydayState.setResult(cursor.getString(cursor.getColumnIndex("result")));
				everydayState.setNote(cursor.getString(cursor.getColumnIndex("note")) );
				everydayState.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				everydayState.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return everydayState;
			} while (cursor.moveToNext());
		}
		cursor.close();			
		return null;		
	}
	
	/**
	 * 根据客户id查询最新的日常生活自理状态数据
	 */
	public EverydayState getEverydayStateOfNewest(String custID,String isNew) {
		
		EverydayState everydayState = new EverydayState();
		Cursor cursor = db.rawQuery("select * from everyday_state where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				everydayState.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				everydayState.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				everydayState.setJc(cursor.getString(cursor.getColumnIndex("jc")));								
				everydayState.setXz(cursor.getString(cursor.getColumnIndex("xz")));
				everydayState.setZs(cursor.getString(cursor.getColumnIndex("zs")));
				everydayState.setCy(cursor.getString(cursor.getColumnIndex("cy")));
				everydayState.setDb(cursor.getString(cursor.getColumnIndex("db")));
				everydayState.setXb(cursor.getString(cursor.getColumnIndex("xb")));
				everydayState.setYc(cursor.getString(cursor.getColumnIndex("yc")));
				everydayState.setQyzy(cursor.getString(cursor.getColumnIndex("qyzy")));
				everydayState.setPdz(cursor.getString(cursor.getColumnIndex("pdz")));
				everydayState.setSxlt(cursor.getString(cursor.getColumnIndex("sxlt")));				
				everydayState.setAssess(cursor.getString(cursor.getColumnIndex("assess")));
				everydayState.setResult(cursor.getString(cursor.getColumnIndex("result")));
				everydayState.setNote(cursor.getString(cursor.getColumnIndex("note")) );
				everydayState.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				everydayState.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return everydayState;
			} while (cursor.moveToNext());
		}
		cursor.close();			
		return null;		
	}
	
	/**
	 * 根据日程ID删除日常生活自理能力状态表
	 */
	public void deleteEverydayStateByScheID(String scheID) {		
		db.delete("everyday_state", "scheID=?", new String[]{scheID});
	}
	
	/**
	 * 根据客户ID删除最新日常生活自理能力状态数据
	 */
	public void deleteEverydayStateByCustID(String custID,String isNew) {		
		db.delete("everyday_state", "custID=? and isNew=?", new String[]{custID,isNew});
	}
	
	/**
	 * 根据日程ID保存药品使用状态
	 */
	public boolean saveDrugStateByScheID (DrugState drugstate){
		if (null!=drugstate) {
			ContentValues values = new ContentValues();
			values.put("scheID", drugstate.getScheID());
			values.put("custID", drugstate.getCustID());
			values.put("ys", drugstate.getYs());
			values.put("sm", drugstate.getSm());		
			values.put("pb", drugstate.getPb());
			values.put("pn", drugstate.getPn());			
			values.put("sl", drugstate.getSl());			
			values.put("tl", drugstate.getTl());
			values.put("yy", drugstate.getYy());
			values.put("yis", drugstate.getYis());
			values.put("xli", drugstate.getXli());
			values.put("grws", drugstate.getGrws());	
			values.put("xt", drugstate.getXt());			
			values.put("mb", drugstate.getMb());
			values.put("hx", drugstate.getHx());
			values.put("xy", drugstate.getXy());
			values.put("mr", drugstate.getMr());		
			values.put("sz", drugstate.getSz());
			values.put("yc", drugstate.getYc());			
			values.put("yh", drugstate.getYh());			
			values.put("xb", drugstate.getXb());
			values.put("fb", drugstate.getFb());	
			values.put("tww", drugstate.getTww());
			values.put("createTime", drugstate.getCreateTime());	
			values.put("isNew", drugstate.getIsNew());
			long row = db.insert("drug_state", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}				
		return false;
		
	}
	
	
	/**
	 * 根据日程id查询查体（原药品使用表）状态对象
	 */
	public DrugState getDrugStateByScheID(String scheID) {
		
		DrugState drugState = new DrugState();
		Cursor cursor = db.rawQuery("select * from drug_state where scheID='" +scheID+ "'",null);
		if (cursor.moveToFirst()) {
			do {
				drugState.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				drugState.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				drugState.setYs(cursor.getString(cursor.getColumnIndex("ys")));								
				drugState.setSm(cursor.getString(cursor.getColumnIndex("sm")));
				drugState.setPb(cursor.getString(cursor.getColumnIndex("pb")));				
				drugState.setPn(cursor.getString(cursor.getColumnIndex("pn")));				
				drugState.setSl(cursor.getString(cursor.getColumnIndex("sl")));
				drugState.setTl(cursor.getString(cursor.getColumnIndex("tl")));
				drugState.setYy(cursor.getString(cursor.getColumnIndex("yy")));
				drugState.setYis(cursor.getString(cursor.getColumnIndex("yis")));
				drugState.setXli(cursor.getString(cursor.getColumnIndex("xli")));				
				drugState.setGrws(cursor.getString(cursor.getColumnIndex("grws")));				
				drugState.setXt(cursor.getString(cursor.getColumnIndex("xt")));
				drugState.setMb(cursor.getString(cursor.getColumnIndex("mb")));
				drugState.setHx(cursor.getString(cursor.getColumnIndex("hx")));
				drugState.setXy(cursor.getString(cursor.getColumnIndex("xy")));
				drugState.setMr(cursor.getString(cursor.getColumnIndex("mr")));
				drugState.setSz(cursor.getString(cursor.getColumnIndex("sz")));
				drugState.setYc(cursor.getString(cursor.getColumnIndex("yc")));
				drugState.setYh(cursor.getString(cursor.getColumnIndex("yh")));
				drugState.setXb(cursor.getString(cursor.getColumnIndex("xb")));
				drugState.setFb(cursor.getString(cursor.getColumnIndex("fb")));		
				drugState.setTww(cursor.getString(cursor.getColumnIndex("tww")));
				drugState.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				drugState.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return drugState;
			} while (cursor.moveToNext());
		}
		cursor.close();	
		return null;		
	}
	
	/**
	 * 根据客户id查询最新查体（原药品使用表）数据
	 */
	public DrugState getDrugState(String custID,String isNew) {
		
		DrugState drugState = new DrugState();
		Cursor cursor = db.rawQuery("select * from drug_state where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				drugState.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				drugState.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				drugState.setYs(cursor.getString(cursor.getColumnIndex("ys")));								
				drugState.setSm(cursor.getString(cursor.getColumnIndex("sm")));
				drugState.setPb(cursor.getString(cursor.getColumnIndex("pb")));				
				drugState.setPn(cursor.getString(cursor.getColumnIndex("pn")));				
				drugState.setSl(cursor.getString(cursor.getColumnIndex("sl")));
				drugState.setTl(cursor.getString(cursor.getColumnIndex("tl")));
				drugState.setYy(cursor.getString(cursor.getColumnIndex("yy")));
				drugState.setYis(cursor.getString(cursor.getColumnIndex("yis")));
				drugState.setXli(cursor.getString(cursor.getColumnIndex("xli")));				
				drugState.setGrws(cursor.getString(cursor.getColumnIndex("grws")));				
				drugState.setXt(cursor.getString(cursor.getColumnIndex("xt")));
				drugState.setMb(cursor.getString(cursor.getColumnIndex("mb")));
				drugState.setHx(cursor.getString(cursor.getColumnIndex("hx")));
				drugState.setXy(cursor.getString(cursor.getColumnIndex("xy")));
				drugState.setMr(cursor.getString(cursor.getColumnIndex("mr")));
				drugState.setSz(cursor.getString(cursor.getColumnIndex("sz")));
				drugState.setYc(cursor.getString(cursor.getColumnIndex("yc")));
				drugState.setYh(cursor.getString(cursor.getColumnIndex("yh")));
				drugState.setXb(cursor.getString(cursor.getColumnIndex("xb")));
				drugState.setFb(cursor.getString(cursor.getColumnIndex("fb")));		
				drugState.setTww(cursor.getString(cursor.getColumnIndex("tww")));
				drugState.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				drugState.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return drugState;
			} while (cursor.moveToNext());
		}
		cursor.close();	
		return null;		
	}
	
	/**
	 * 根据日程ID删除查体(原药品使用)状态表
	 */
	public void deleteDrugStateByScheID(String scheID) {		
		db.delete("drug_state", "scheID=?", new String[]{scheID});	
	}
	
	/**
	 * 根据客户ID删除最新查体(原药品使用)状态表
	 */
	public void deleteDrugStateByCustID(String custID,String isNew) {		
		db.delete("drug_state", "custID=? and isNew=?", new String[]{custID,isNew});	
	}
	
	/**
	 * 根据日程ID保存基本情况
	 */
	public boolean saveBasicInformationByScheID (BasicInformation basicInformation){
		if (null != basicInformation) {
			ContentValues values = new ContentValues();
			values.put("scheID", basicInformation.getScheID());
			values.put("custID", basicInformation.getCustID());
			values.put("marriage", basicInformation.getMarriage());
			values.put("occupational",basicInformation.getOccupational());
			values.put("child", basicInformation.getChild());
			values.put("phz", basicInformation.getPhz());
			values.put("nurse", basicInformation.getNurse());
			values.put("lighting", basicInformation.getLighting());
			values.put("air", basicInformation.getAir());
			values.put("humidity", basicInformation.getHumidity());
			values.put("temperature", basicInformation.getTemperature());
			values.put("odor", basicInformation.getOdor());
			values.put("economy", basicInformation.getEconomy());
			values.put("wc", basicInformation.getWc());
			values.put("cdw", basicInformation.getCdw());
			values.put("hj", basicInformation.getHj());
			values.put("zz", basicInformation.getZz());
			values.put("qy", basicInformation.getQy());
			values.put("fjzxl", basicInformation.getFjzxl());
			values.put("assist", basicInformation.getAssist());
			values.put("history", basicInformation.getHistory());
			values.put("drug", basicInformation.getDrug());					
			values.put("createTime", basicInformation.getCreateTime());			
			values.put("isNew", basicInformation.getIsNew());
			long row = db.insert("basic_information", null, values);
			if (row == -1) {
				return false;
			} else {
				System.out.println("网络上拿到数据插入本地唱歌");
				return true;
			}
		}				
		return false;
		
	}
	
	
	/**
	 * 根据日程id查询基本情况 状态对象
	 */
	public BasicInformation getBasicInformationByScheID(String scheID) {
		
		BasicInformation basicInformation = new BasicInformation();
		Cursor cursor = db.rawQuery(
				"select * from basic_information where scheID='" +scheID+ "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				basicInformation.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				basicInformation.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				basicInformation.setMarriage(cursor.getString(cursor.getColumnIndex("marriage")));		
				basicInformation.setOccupational(cursor.getString(cursor.getColumnIndex("occupational")));
				basicInformation.setChild(cursor.getString(cursor.getColumnIndex("child")));
				basicInformation.setPhz(cursor.getString(cursor.getColumnIndex("phz")));
				basicInformation.setNurse(cursor.getString(cursor.getColumnIndex("nurse")));
				basicInformation.setLighting(cursor.getString(cursor.getColumnIndex("lighting")));
				basicInformation.setAir(cursor.getString(cursor.getColumnIndex("air")));
				basicInformation.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
				basicInformation.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
				basicInformation.setOdor(cursor.getString(cursor.getColumnIndex("odor")));
				basicInformation.setEconomy(cursor.getString(cursor.getColumnIndex("economy")));
				basicInformation.setWc(cursor.getString(cursor.getColumnIndex("wc")));
				basicInformation.setCdw(cursor.getString(cursor.getColumnIndex("cdw")));
				basicInformation.setHj(cursor.getString(cursor.getColumnIndex("hj")));
				basicInformation.setZz(cursor.getString(cursor.getColumnIndex("zz")));
				basicInformation.setQy(cursor.getString(cursor.getColumnIndex("qy")));
				basicInformation.setFjzxl(cursor.getString(cursor.getColumnIndex("fjzxl")));
				basicInformation.setAssist(cursor.getString(cursor.getColumnIndex("assist")));
				basicInformation.setHistory(cursor.getString(cursor.getColumnIndex("history")));
				basicInformation.setDrug(cursor.getString(cursor.getColumnIndex("drug")));								
				basicInformation.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				basicInformation.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return basicInformation;
			} while (cursor.moveToNext());
		}
		cursor.close();			
		return null;		
	}
	
	/**
	 * 根据客户id查询最新基本情况数据
	 */
	public BasicInformation getBasicInformationOfNewest(String custID,String isNew) {
		
		BasicInformation basicInformation = new BasicInformation();
		Cursor cursor = db.rawQuery("select * from basic_information where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				basicInformation.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				basicInformation.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				basicInformation.setMarriage(cursor.getString(cursor.getColumnIndex("marriage")));		
				basicInformation.setOccupational(cursor.getString(cursor.getColumnIndex("occupational")));
				basicInformation.setChild(cursor.getString(cursor.getColumnIndex("child")));
				basicInformation.setPhz(cursor.getString(cursor.getColumnIndex("phz")));
				basicInformation.setNurse(cursor.getString(cursor.getColumnIndex("nurse")));
				basicInformation.setLighting(cursor.getString(cursor.getColumnIndex("lighting")));
				basicInformation.setAir(cursor.getString(cursor.getColumnIndex("air")));
				basicInformation.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
				basicInformation.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
				basicInformation.setOdor(cursor.getString(cursor.getColumnIndex("odor")));
				basicInformation.setEconomy(cursor.getString(cursor.getColumnIndex("economy")));
				basicInformation.setWc(cursor.getString(cursor.getColumnIndex("wc")));
				basicInformation.setCdw(cursor.getString(cursor.getColumnIndex("cdw")));
				basicInformation.setHj(cursor.getString(cursor.getColumnIndex("hj")));
				basicInformation.setZz(cursor.getString(cursor.getColumnIndex("zz")));
				basicInformation.setQy(cursor.getString(cursor.getColumnIndex("qy")));
				basicInformation.setFjzxl(cursor.getString(cursor.getColumnIndex("fjzxl")));
				basicInformation.setAssist(cursor.getString(cursor.getColumnIndex("assist")));
				basicInformation.setHistory(cursor.getString(cursor.getColumnIndex("history")));
				basicInformation.setDrug(cursor.getString(cursor.getColumnIndex("drug")));								
				basicInformation.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				basicInformation.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return basicInformation;
			} while (cursor.moveToNext());
		}
		cursor.close();			
		return null;		
	}
	
	/**
	 * 根据日程id查询基本情况所有数据
	 */
	public List<BasicInformation> getAllBasicInformation() {
		List<BasicInformation> list = new ArrayList<BasicInformation>();
		Cursor cursor = db.rawQuery("select * from basic_information",null);
		if (cursor.moveToLast()) {
			do {
				BasicInformation basicInformation = new BasicInformation();
				basicInformation.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				basicInformation.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				basicInformation.setMarriage(cursor.getString(cursor.getColumnIndex("marriage")));		
				basicInformation.setOccupational(cursor.getString(cursor.getColumnIndex("occupational")));
				basicInformation.setChild(cursor.getString(cursor.getColumnIndex("child")));
				basicInformation.setPhz(cursor.getString(cursor.getColumnIndex("phz")));
				basicInformation.setNurse(cursor.getString(cursor.getColumnIndex("nurse")));
				basicInformation.setLighting(cursor.getString(cursor.getColumnIndex("lighting")));
				basicInformation.setAir(cursor.getString(cursor.getColumnIndex("air")));
				basicInformation.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
				basicInformation.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
				basicInformation.setOdor(cursor.getString(cursor.getColumnIndex("odor")));
				basicInformation.setEconomy(cursor.getString(cursor.getColumnIndex("economy")));
				basicInformation.setWc(cursor.getString(cursor.getColumnIndex("wc")));
				basicInformation.setCdw(cursor.getString(cursor.getColumnIndex("cdw")));
				basicInformation.setHj(cursor.getString(cursor.getColumnIndex("hj")));
				basicInformation.setZz(cursor.getString(cursor.getColumnIndex("zz")));
				basicInformation.setQy(cursor.getString(cursor.getColumnIndex("qy")));
				basicInformation.setFjzxl(cursor.getString(cursor.getColumnIndex("fjzxl")));
				basicInformation.setAssist(cursor.getString(cursor.getColumnIndex("assist")));
				basicInformation.setHistory(cursor.getString(cursor.getColumnIndex("history")));
				basicInformation.setDrug(cursor.getString(cursor.getColumnIndex("drug")));								
				basicInformation.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				basicInformation.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				list.add(basicInformation);
			} while (cursor.moveToNext());
		}
		cursor.close();			
		return list;		
	}
	

	
	/**
	 * 根据日程ID删除基本情况状态表
	 */
	public void deleteBasicInformationByScheID(String scheID) {		
		System.out.println("根据日程id删除基本情况表中的数据，%%%%%%%%%%%%");
		db.delete("basic_information", "scheID=?", new String[]{scheID});	
	}
	
	/**
	 * 根据客户ID删除最新基本情况数据
	 */
	public void deleteBasicInformationByCustID(String custID,String isNew) {		
		db.delete("basic_information", "custID=? and isNew=?", new String[]{custID,isNew});	
	}
	
	
	
	/**
	 * 根据日程ID保存步行功能评定表
	 */
	public boolean saveSportAssessByScheID (SportAssess sportAssess){
		if (null != sportAssess) {
			ContentValues values = new ContentValues();
			values.put("scheID", sportAssess.getScheID());
			values.put("custID", sportAssess.getCustID());
			values.put("zwph", sportAssess.getZwph());
			values.put("lwph", sportAssess.getLwph());
			values.put("bxnl", sportAssess.getBxnl());
			values.put("jl", sportAssess.getJl());
			values.put("jzl", sportAssess.getJzl());
			values.put("ttpf", sportAssess.getTtpf());
			values.put("jl_yc", sportAssess.getJl_yc());
			values.put("jzl_yc", sportAssess.getJzl_yc());
			values.put("ttpf_yc", sportAssess.getTtpf_yc());
			values.put("jzl_yc_zc", sportAssess.getJzl_yc_zc());
			values.put("jzl_yc_qg", sportAssess.getJzl_yc_qg());
			values.put("jzl_yc_rc", sportAssess.getJzl_yc_rc());
			values.put("jl_yc_zc", sportAssess.getJl_yc_zc());
			values.put("jl_yc_qg", sportAssess.getJl_yc_qg());
			values.put("jl_yc_rc", sportAssess.getJl_yc_rc());			
			values.put("ttpf_yc_zc", sportAssess.getTtpf_yc_zc());
			values.put("ttpf_yc_qg", sportAssess.getTtpf_yc_qg());
			values.put("ttpf_yc_rc", sportAssess.getTtpf_yc_rc());
			values.put("jl_yc_zc_shou", sportAssess.getJl_yc_zc_shou());
			values.put("jl_yc_zc_sz", sportAssess.getJl_yc_zc_sz());
			values.put("jl_yc_zc_xz", sportAssess.getJl_yc_zc_xz());
			values.put("jl_yc_zc_qt", sportAssess.getJl_yc_zc_qt());
			values.put("jl_yc_qg_bj", sportAssess.getJl_yc_qg_bj());
			values.put("jl_yc_qg_yj", sportAssess.getJl_yc_qg_yj());
			values.put("jl_yc_qg_tj", sportAssess.getJl_yc_qg_tj());
			values.put("jl_yc_qg_xj", sportAssess.getJl_yc_qg_xj());
			values.put("jl_yc_qg_fj", sportAssess.getJl_yc_qg_fj());
			values.put("jl_yc_qg_qt", sportAssess.getJl_yc_qg_qt());
			values.put("jl_yc_rc_sz", sportAssess.getJl_yc_rc_sz());
			values.put("jl_yc_rc_xz", sportAssess.getJl_yc_rc_xz());
			values.put("jl_yc_rc_jiao", sportAssess.getJl_yc_rc_jiao());
			values.put("jl_yc_rc_qt", sportAssess.getJl_yc_rc_qt());								
			values.put("jzl_yc_zc_shou", sportAssess.getJzl_yc_zc_shou());
			values.put("jzl_yc_zc_sz", sportAssess.getJzl_yc_zc_sz());
			values.put("jzl_yc_zc_xz", sportAssess.getJzl_yc_zc_xz());
			values.put("jzl_yc_zc_qt", sportAssess.getJzl_yc_zc_qt());
			values.put("jzl_yc_qg_bj", sportAssess.getJzl_yc_qg_bj());
			values.put("jzl_yc_qg_yj", sportAssess.getJzl_yc_qg_yj());
			values.put("jzl_yc_qg_tj", sportAssess.getJzl_yc_qg_tj());
			values.put("jzl_yc_qg_xj", sportAssess.getJzl_yc_qg_xj());
			values.put("jzl_yc_qg_fj", sportAssess.getJzl_yc_qg_fj());
			values.put("jzl_yc_qg_qt", sportAssess.getJzl_yc_qg_qt());
			values.put("jzl_yc_rc_sz", sportAssess.getJzl_yc_rc_sz());
			values.put("jzl_yc_rc_xz", sportAssess.getJzl_yc_rc_xz());
			values.put("jzl_yc_rc_jiao", sportAssess.getJzl_yc_rc_jiao());
			values.put("jzl_yc_rc_qt", sportAssess.getJzl_yc_rc_qt());		
			values.put("ttpf_yc_zc_shou", sportAssess.getTtpf_yc_zc_shou());
			values.put("ttpf_yc_zc_sz", sportAssess.getTtpf_yc_zc_sz());
			values.put("ttpf_yc_zc_xz", sportAssess.getTtpf_yc_zc_xz());
			values.put("ttpf_yc_zc_qt", sportAssess.getTtpf_yc_zc_qt());
			values.put("ttpf_yc_qg_bj", sportAssess.getTtpf_yc_qg_bj());
			values.put("ttpf_yc_qg_yj", sportAssess.getTtpf_yc_qg_yj());
			values.put("ttpf_yc_qg_tj", sportAssess.getTtpf_yc_qg_tj());
			values.put("ttpf_yc_qg_xj", sportAssess.getTtpf_yc_qg_xj());
			values.put("ttpf_yc_qg_fj", sportAssess.getTtpf_yc_qg_fj());
			values.put("ttpf_yc_qg_qt", sportAssess.getTtpf_yc_qg_qt());
			values.put("ttpf_yc_rc_sz", sportAssess.getTtpf_yc_rc_sz());
			values.put("ttpf_yc_rc_xz", sportAssess.getTtpf_yc_rc_xz());
			values.put("ttpf_yc_rc_jiao", sportAssess.getTtpf_yc_rc_jiao());
			values.put("ttpf_yc_rc_qt", sportAssess.getTtpf_yc_rc_qt());
			values.put("fxdj", sportAssess.getFxdj());
			values.put("createTime", sportAssess.getCreateTime());		
			values.put("isNew", sportAssess.getIsNew());
			long row = db.insert("sport_assess", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}				
		return false;
		
	}
	
	/**
	 * 根据日程id查询步行功能评定对象
	 */
	public SportAssess getSportAssessByScheID(String scheID) {
		
		SportAssess sportAssess = new SportAssess();
		Cursor cursor = db.rawQuery(
				"select * from sport_assess where scheID='" +scheID+ "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				sportAssess.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				sportAssess.setCustID(cursor.getString(cursor.getColumnIndex("custID")));						
				sportAssess.setZwph(cursor.getString(cursor.getColumnIndex("zwph")));
				sportAssess.setLwph(cursor.getString(cursor.getColumnIndex("lwph")));				
				sportAssess.setBxnl(cursor.getString(cursor.getColumnIndex("bxnl")));
				sportAssess.setJl(cursor.getString(cursor.getColumnIndex("jl")));						
				sportAssess.setJzl(cursor.getString(cursor.getColumnIndex("jzl")));
				sportAssess.setTtpf(cursor.getString(cursor.getColumnIndex("ttpf")));		
				sportAssess.setJl_yc(cursor.getString(cursor.getColumnIndex("jl_yc")));
				sportAssess.setJzl_yc(cursor.getString(cursor.getColumnIndex("jzl_yc")));						
				sportAssess.setTtpf_yc(cursor.getString(cursor.getColumnIndex("ttpf_yc")));
				sportAssess.setJzl_yc_zc(cursor.getString(cursor.getColumnIndex("jzl_yc_zc")));				
				sportAssess.setJzl_yc_qg(cursor.getString(cursor.getColumnIndex("jzl_yc_qg")));
				sportAssess.setJzl_yc_rc(cursor.getString(cursor.getColumnIndex("jzl_yc_rc")));						
				sportAssess.setJl_yc_zc(cursor.getString(cursor.getColumnIndex("jl_yc_zc")));
				sportAssess.setJl_yc_qg(cursor.getString(cursor.getColumnIndex("jl_yc_qg")));
				sportAssess.setJl_yc_rc(cursor.getString(cursor.getColumnIndex("jl_yc_rc")));										
				sportAssess.setTtpf_yc_zc(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc")));
				sportAssess.setTtpf_yc_qg(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg")));
				sportAssess.setTtpf_yc_rc(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc")));			
				sportAssess.setJl_yc_zc_shou(cursor.getString(cursor.getColumnIndex("jl_yc_zc_shou")));
				sportAssess.setJl_yc_zc_sz(cursor.getString(cursor.getColumnIndex("jl_yc_zc_sz")));		
				sportAssess.setJl_yc_zc_xz(cursor.getString(cursor.getColumnIndex("jl_yc_zc_xz")));
				sportAssess.setJl_yc_zc_qt(cursor.getString(cursor.getColumnIndex("jl_yc_zc_qt")));						
				sportAssess.setJl_yc_qg_bj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_bj")));
				sportAssess.setJl_yc_qg_yj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_yj")));				
				sportAssess.setJl_yc_qg_tj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_tj")));
				sportAssess.setJl_yc_qg_xj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_xj")));						
				sportAssess.setJl_yc_qg_fj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_fj")));
				sportAssess.setJl_yc_qg_qt(cursor.getString(cursor.getColumnIndex("jl_yc_qg_qt")));
				sportAssess.setJl_yc_rc_sz(cursor.getString(cursor.getColumnIndex("jl_yc_rc_sz")));										
				sportAssess.setJl_yc_rc_xz(cursor.getString(cursor.getColumnIndex("jl_yc_rc_xz")));
				sportAssess.setJl_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("jl_yc_rc_jiao")));
				sportAssess.setJl_yc_rc_qt(cursor.getString(cursor.getColumnIndex("jl_yc_rc_qt")));				
				sportAssess.setJzl_yc_zc_shou(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_shou")));
				sportAssess.setJzl_yc_zc_sz(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_sz")));		
				sportAssess.setJzl_yc_zc_xz(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_xz")));
				sportAssess.setJzl_yc_zc_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_qt")));						
				sportAssess.setJzl_yc_qg_bj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_bj")));
				sportAssess.setJzl_yc_qg_yj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_yj")));				
				sportAssess.setJzl_yc_qg_tj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_tj")));
				sportAssess.setJzl_yc_qg_xj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_xj")));						
				sportAssess.setJzl_yc_qg_fj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_fj")));
				sportAssess.setJzl_yc_qg_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_qt")));
				sportAssess.setJzl_yc_rc_sz(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_sz")));										
				sportAssess.setJzl_yc_rc_xz(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_xz")));
				sportAssess.setJzl_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_jiao")));
				sportAssess.setJzl_yc_rc_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_qt")));				
				sportAssess.setTtpf_yc_zc_shou(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_shou")));
				sportAssess.setTtpf_yc_zc_sz(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_sz")));		
				sportAssess.setTtpf_yc_zc_xz(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_xz")));
				sportAssess.setTtpf_yc_zc_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_qt")));						
				sportAssess.setTtpf_yc_qg_bj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_bj")));
				sportAssess.setTtpf_yc_qg_yj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_yj")));				
				sportAssess.setTtpf_yc_qg_tj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_tj")));
				sportAssess.setTtpf_yc_qg_xj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_xj")));						
				sportAssess.setTtpf_yc_qg_fj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_fj")));
				sportAssess.setTtpf_yc_qg_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_qt")));
				sportAssess.setTtpf_yc_rc_sz(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_sz")));										
				sportAssess.setTtpf_yc_rc_xz(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_xz")));
				sportAssess.setTtpf_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_jiao")));
				sportAssess.setTtpf_yc_rc_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_qt")));
				sportAssess.setFxdj(cursor.getString(cursor.getColumnIndex("fxdj")));
				sportAssess.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				sportAssess.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return sportAssess;
			} while (cursor.moveToNext());
		}
		cursor.close();	
		return null;		
	}
	
	/**
	 * 根据客户id查询最新步行功能评定数据
	 */
	public SportAssess getSportAssessOfNewest(String custID,String isNew) {
		
		SportAssess sportAssess = new SportAssess();
		Cursor cursor = db.rawQuery("select * from sport_assess where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				sportAssess.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				sportAssess.setCustID(cursor.getString(cursor.getColumnIndex("custID")));						
				sportAssess.setZwph(cursor.getString(cursor.getColumnIndex("zwph")));
				sportAssess.setLwph(cursor.getString(cursor.getColumnIndex("lwph")));				
				sportAssess.setBxnl(cursor.getString(cursor.getColumnIndex("bxnl")));
				sportAssess.setJl(cursor.getString(cursor.getColumnIndex("jl")));						
				sportAssess.setJzl(cursor.getString(cursor.getColumnIndex("jzl")));
				sportAssess.setTtpf(cursor.getString(cursor.getColumnIndex("ttpf")));		
				sportAssess.setJl_yc(cursor.getString(cursor.getColumnIndex("jl_yc")));
				sportAssess.setJzl_yc(cursor.getString(cursor.getColumnIndex("jzl_yc")));						
				sportAssess.setTtpf_yc(cursor.getString(cursor.getColumnIndex("ttpf_yc")));
				sportAssess.setJzl_yc_zc(cursor.getString(cursor.getColumnIndex("jzl_yc_zc")));				
				sportAssess.setJzl_yc_qg(cursor.getString(cursor.getColumnIndex("jzl_yc_qg")));
				sportAssess.setJzl_yc_rc(cursor.getString(cursor.getColumnIndex("jzl_yc_rc")));						
				sportAssess.setJl_yc_zc(cursor.getString(cursor.getColumnIndex("jl_yc_zc")));
				sportAssess.setJl_yc_qg(cursor.getString(cursor.getColumnIndex("jl_yc_qg")));
				sportAssess.setJl_yc_rc(cursor.getString(cursor.getColumnIndex("jl_yc_rc")));										
				sportAssess.setTtpf_yc_zc(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc")));
				sportAssess.setTtpf_yc_qg(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg")));
				sportAssess.setTtpf_yc_rc(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc")));			
				sportAssess.setJl_yc_zc_shou(cursor.getString(cursor.getColumnIndex("jl_yc_zc_shou")));
				sportAssess.setJl_yc_zc_sz(cursor.getString(cursor.getColumnIndex("jl_yc_zc_sz")));		
				sportAssess.setJl_yc_zc_xz(cursor.getString(cursor.getColumnIndex("jl_yc_zc_xz")));
				sportAssess.setJl_yc_zc_qt(cursor.getString(cursor.getColumnIndex("jl_yc_zc_qt")));						
				sportAssess.setJl_yc_qg_bj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_bj")));
				sportAssess.setJl_yc_qg_yj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_yj")));				
				sportAssess.setJl_yc_qg_tj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_tj")));
				sportAssess.setJl_yc_qg_xj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_xj")));						
				sportAssess.setJl_yc_qg_fj(cursor.getString(cursor.getColumnIndex("jl_yc_qg_fj")));
				sportAssess.setJl_yc_qg_qt(cursor.getString(cursor.getColumnIndex("jl_yc_qg_qt")));
				sportAssess.setJl_yc_rc_sz(cursor.getString(cursor.getColumnIndex("jl_yc_rc_sz")));										
				sportAssess.setJl_yc_rc_xz(cursor.getString(cursor.getColumnIndex("jl_yc_rc_xz")));
				sportAssess.setJl_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("jl_yc_rc_jiao")));
				sportAssess.setJl_yc_rc_qt(cursor.getString(cursor.getColumnIndex("jl_yc_rc_qt")));				
				sportAssess.setJzl_yc_zc_shou(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_shou")));
				sportAssess.setJzl_yc_zc_sz(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_sz")));		
				sportAssess.setJzl_yc_zc_xz(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_xz")));
				sportAssess.setJzl_yc_zc_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_zc_qt")));						
				sportAssess.setJzl_yc_qg_bj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_bj")));
				sportAssess.setJzl_yc_qg_yj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_yj")));				
				sportAssess.setJzl_yc_qg_tj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_tj")));
				sportAssess.setJzl_yc_qg_xj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_xj")));						
				sportAssess.setJzl_yc_qg_fj(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_fj")));
				sportAssess.setJzl_yc_qg_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_qg_qt")));
				sportAssess.setJzl_yc_rc_sz(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_sz")));										
				sportAssess.setJzl_yc_rc_xz(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_xz")));
				sportAssess.setJzl_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_jiao")));
				sportAssess.setJzl_yc_rc_qt(cursor.getString(cursor.getColumnIndex("jzl_yc_rc_qt")));				
				sportAssess.setTtpf_yc_zc_shou(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_shou")));
				sportAssess.setTtpf_yc_zc_sz(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_sz")));		
				sportAssess.setTtpf_yc_zc_xz(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_xz")));
				sportAssess.setTtpf_yc_zc_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_zc_qt")));						
				sportAssess.setTtpf_yc_qg_bj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_bj")));
				sportAssess.setTtpf_yc_qg_yj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_yj")));				
				sportAssess.setTtpf_yc_qg_tj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_tj")));
				sportAssess.setTtpf_yc_qg_xj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_xj")));						
				sportAssess.setTtpf_yc_qg_fj(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_fj")));
				sportAssess.setTtpf_yc_qg_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_qg_qt")));
				sportAssess.setTtpf_yc_rc_sz(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_sz")));										
				sportAssess.setTtpf_yc_rc_xz(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_xz")));
				sportAssess.setTtpf_yc_rc_jiao(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_jiao")));
				sportAssess.setTtpf_yc_rc_qt(cursor.getString(cursor.getColumnIndex("ttpf_yc_rc_qt")));
				sportAssess.setFxdj(cursor.getString(cursor.getColumnIndex("fxdj")));
				sportAssess.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				sportAssess.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				return sportAssess;
			} while (cursor.moveToNext());
		}
		cursor.close();	
		return null;		
	}
	
	/**
	 * 根据日程ID删除步行功能评定表
	 */
	public void deleteSportAssessByScheID(String scheID) {	
		System.out.println("最后一张表-------------删除");
		db.delete("sport_assess", "scheID=?", new String[]{scheID});	
	}
	
	/**
	 * 根据客户ID删除最新步行功能评定数据
	 */
	public void deleteSportAssessByCustID(String custID,String isNew) {	
		db.delete("sport_assess", "custID=? and isNew=?", new String[]{custID,isNew});	
	}
	
	/**
	 * 添加COPM
	 */
	public void saveCopm(Copm copm){
		if (null!=copm) {
			ContentValues values = new ContentValues();
			values.put("scheID", copm.getScheID());
			values.put("custID", copm.getCustID());
			values.put("content", copm.getContent());
			values.put("situation", copm.getSituation());
			values.put("satisf", copm.getSatisf());
			values.put("xianPoint", copm.getXianPoint());
			values.put("zuoyeChange", copm.getZuoyeChange());
			values.put("manPoint", copm.getManPoint());
			values.put("number", copm.getNumber());
			values.put("createTime", copm.getCreateTime());
			values.put("manChange", copm.getManChange());
			values.put("unmodifiableState", copm.getUnmodifiableState());
			values.put("isLast", copm.getIsLast());
			values.put("createTime", copm.getCreateTime());
			values.put("saveState", copm.getSaveState());
			values.put("submitState", copm.getSubmitState());
			values.put("satisfSum", copm.getSatisfSum());
			values.put("situationSum", copm.getSituationSum());
			values.put("isNew", copm.getIsNew());
			values.put("specialAccount", copm.getSpecialAccount());
			db.insert("copm_record", null, values);
			
		}
	}
	
	/**
	 * 根据客户id查询COPM
	 */
	public List<Copm> getCopmByCustID(String custID){
		List<Copm> copmList = new ArrayList<Copm>();
		
		Cursor cursor = db.rawQuery("select * from copm_record where custID='" +custID+ "'",null);
		if (cursor.moveToFirst()) {
			do {
				Copm copm = new Copm();
				copm.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				copm.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				copm.setContent(cursor.getString(cursor.getColumnIndex("content")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setManPoint(cursor.getString(cursor.getColumnIndex("manPoint")));
				copm.setManChange(cursor.getString(cursor.getColumnIndex("manChange")));
				copm.setXianPoint(cursor.getString(cursor.getColumnIndex("xianPoint")));
				copm.setZuoyeChange(cursor.getString(cursor.getColumnIndex("zuoyeChange")));
				copm.setNumber(cursor.getString(cursor.getColumnIndex("number")));
				copm.setSatisf(cursor.getString(cursor.getColumnIndex("satisf")));
				copm.setSituation(cursor.getString(cursor.getColumnIndex("situation")));
				copm.setUnmodifiableState(cursor.getString(cursor.getColumnIndex("unmodifiableState")));
				copm.setIsLast(cursor.getString(cursor.getColumnIndex("isLast")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				copm.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				copm.setSatisfSum(cursor.getString(cursor.getColumnIndex("satisfSum")));
				copm.setSituationSum(cursor.getString(cursor.getColumnIndex("situationSum")));
				copm.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				copm.setSpecialAccount(cursor.getString(cursor.getColumnIndex("specialAccount")));
				copmList.add(copm);
				
			} while (cursor.moveToNext());
		}
		cursor.close();
		return copmList;
	}
	
	/**
	 * 根据客户id查询最新COPM数据
	 */
	public List<Copm> getCopmOfNewest(String custID,String isNew){
		List<Copm> copmList = new ArrayList<Copm>();
		Cursor cursor = db.rawQuery("select * from copm_record where custID='" +custID+ "' and isNew='0'",null);
		if (cursor.moveToFirst()) {
			do {
				Copm copm = new Copm();
				copm.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				copm.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				copm.setContent(cursor.getString(cursor.getColumnIndex("content")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setManPoint(cursor.getString(cursor.getColumnIndex("manPoint")));
				copm.setManChange(cursor.getString(cursor.getColumnIndex("manChange")));
				copm.setXianPoint(cursor.getString(cursor.getColumnIndex("xianPoint")));
				copm.setZuoyeChange(cursor.getString(cursor.getColumnIndex("zuoyeChange")));
				copm.setNumber(cursor.getString(cursor.getColumnIndex("number")));
				copm.setSatisf(cursor.getString(cursor.getColumnIndex("satisf")));
				copm.setSituation(cursor.getString(cursor.getColumnIndex("situation")));
				copm.setUnmodifiableState(cursor.getString(cursor.getColumnIndex("unmodifiableState")));
				copm.setIsLast(cursor.getString(cursor.getColumnIndex("isLast")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				copm.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				copm.setSatisfSum(cursor.getString(cursor.getColumnIndex("satisfSum")));
				copm.setSituationSum(cursor.getString(cursor.getColumnIndex("situationSum")));
				copm.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				copm.setSpecialAccount(cursor.getString(cursor.getColumnIndex("specialAccount")));
				copmList.add(copm);
				
			} while (cursor.moveToNext());
		}
		cursor.close();
		return copmList;
	}
	
	/**
	 * 根据服务日程id查询COPM
	 */
	public List<Copm> getCopmByScheID(String scheID){
		List<Copm> copmList = new ArrayList<Copm>();
		
		Cursor cursor = db.rawQuery("select * from copm_record where scheID='" +scheID+ "'",null);
		if (cursor.moveToFirst()) {
			do {
				Copm copm = new Copm();
				copm.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				copm.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				copm.setContent(cursor.getString(cursor.getColumnIndex("content")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setManPoint(cursor.getString(cursor.getColumnIndex("manPoint")));
				copm.setManChange(cursor.getString(cursor.getColumnIndex("manChange")));
				copm.setXianPoint(cursor.getString(cursor.getColumnIndex("xianPoint")));
				copm.setZuoyeChange(cursor.getString(cursor.getColumnIndex("zuoyeChange")));
				copm.setNumber(cursor.getString(cursor.getColumnIndex("number")));
				copm.setSatisf(cursor.getString(cursor.getColumnIndex("satisf")));
				copm.setSituation(cursor.getString(cursor.getColumnIndex("situation")));
				copm.setUnmodifiableState(cursor.getString(cursor.getColumnIndex("unmodifiableState")));
				copm.setIsLast(cursor.getString(cursor.getColumnIndex("isLast")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				copm.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				copm.setSatisfSum(cursor.getString(cursor.getColumnIndex("satisfSum")));
				copm.setSituationSum(cursor.getString(cursor.getColumnIndex("situationSum")));
				copm.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				copm.setSpecialAccount(cursor.getString(cursor.getColumnIndex("specialAccount")));
				copmList.add(copm);
				
			} while (cursor.moveToNext());
		}
		cursor.close();
		return copmList;
	}
	/**
	 * 根据日程id删除Copm表
	 */
	public void deleteCopmByScheID(String scheID){
		db.delete("copm_record", "scheID=?", new String[]{scheID});
	}
	/**
	 * 根据number删除Copm表
	 */
	public void deleteCopmByNumber(String number){
		db.delete("copm_record", "number=?", new String[]{number});
	}
	/**
	 * 根据客户id删除Copm表
	 */
	public void deleteCopmByCustID(String custID){
		db.delete("copm_record", "custID=?", new String[]{custID});
	}
	
	/**
	 * 根据客户id删除最新Copm数据
	 */
	public void deleteCopmByCustIDOfNewest(String custID,String isNew){
		db.delete("copm_record", "custID=? and isNew=?", new String[]{custID,isNew});
	}
	
	/**
	 * 添加COPM
	 */
	public void saveCopmAdd(Copm copm){
		if (null!=copm) {
			ContentValues values = new ContentValues();
			values.put("scheID", copm.getScheID());
			values.put("custID", copm.getCustID());
			values.put("content", copm.getContent());
			values.put("situation", copm.getSituation());
			values.put("satisf", copm.getSatisf());
			values.put("xianPoint", copm.getXianPoint());
			values.put("zuoyeChange", copm.getZuoyeChange());
			values.put("manPoint", copm.getManPoint());
			values.put("number", copm.getNumber());
			values.put("createTime", copm.getCreateTime());
			values.put("manChange", copm.getManChange());
			values.put("unmodifiableState", copm.getUnmodifiableState());
			values.put("isLast", copm.getIsLast());
			values.put("createTime", copm.getCreateTime());
			values.put("saveState", copm.getSaveState());
			values.put("submitState", copm.getSubmitState());
			values.put("satisfSum", copm.getSatisfSum());
			values.put("situationSum", copm.getSituationSum());
			values.put("isNew", copm.getIsNew());
			values.put("specialAccount", copm.getSpecialAccount());
			db.insert("copm_add", null, values);
			
		}
	}
	
	/**
	 * 根据客户id查询COPM
	 */
	public List<Copm> getCopmAddByScheID(String scheID){
		List<Copm> copmList = new ArrayList<Copm>();
		
		Cursor cursor = db.rawQuery("select * from copm_add where scheID='" +scheID+ "'",null);
		if (cursor.moveToFirst()) {
			do {
				Copm copm = new Copm();
				copm.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				copm.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				copm.setContent(cursor.getString(cursor.getColumnIndex("content")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setManPoint(cursor.getString(cursor.getColumnIndex("manPoint")));
				copm.setManChange(cursor.getString(cursor.getColumnIndex("manChange")));
				copm.setXianPoint(cursor.getString(cursor.getColumnIndex("xianPoint")));
				copm.setZuoyeChange(cursor.getString(cursor.getColumnIndex("zuoyeChange")));
				copm.setNumber(cursor.getString(cursor.getColumnIndex("number")));
				copm.setSatisf(cursor.getString(cursor.getColumnIndex("satisf")));
				copm.setSituation(cursor.getString(cursor.getColumnIndex("situation")));
				copm.setUnmodifiableState(cursor.getString(cursor.getColumnIndex("unmodifiableState")));
				copm.setIsLast(cursor.getString(cursor.getColumnIndex("isLast")));
				copm.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				copm.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				copm.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				copm.setSatisfSum(cursor.getString(cursor.getColumnIndex("satisfSum")));
				copm.setSituationSum(cursor.getString(cursor.getColumnIndex("situationSum")));
				copm.setIsNew(cursor.getString(cursor.getColumnIndex("isNew")));
				copm.setSpecialAccount(cursor.getString(cursor.getColumnIndex("specialAccount")));
				copmList.add(copm);
				
			} while (cursor.moveToNext());
		}
		cursor.close();
		return copmList;
	}
	
	/**
	 * 根据客户id删除CopmAdd表
	 */
	public void deleteCopmAddByCustID(String custID){
		db.delete("copm_add", "custID=?", new String[]{custID});
	}
	/**
	 * 根据客户id删除CopmAdd表
	 */
	public void deleteCopmAddByScheID(String scheID){
		db.delete("copm_add", "scheID=?", new String[]{scheID});
	}
	/**
	 * 添加每日情况记录
	 */
	public boolean saveServerRecord(ServerRecord serverRecord){
		if (null != serverRecord) {
			ContentValues values = new ContentValues();
			values.put("scheduleId", serverRecord.getScheduleId());
			values.put("custID", serverRecord.getCustID());
			values.put("age", serverRecord.getAge());
			values.put("bloodsugar", serverRecord.getBloodsugar());
			values.put("breath", serverRecord.getBreath());
			values.put("content", serverRecord.getContent());
			//获取系统时间
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
			String createTimeDB = formatDate.format(new Date())+"T"+formatTime.format(new Date());
			values.put("createTime", createTimeDB);
			values.put("custName", serverRecord.getCustName());
			values.put("empName", serverRecord.getEmpName());
			values.put("endDate", serverRecord.getEndDate());
			values.put("highpressure", serverRecord.getHighpressure());
			values.put("lowpressure", serverRecord.getLowpressure());
			values.put("otherSub", serverRecord.getOtherSub());
			values.put("planID", serverRecord.getPlanID());
			values.put("pulse", serverRecord.getPulse());
			values.put("sex", serverRecord.getSex());
			values.put("recorddate", serverRecord.getRecorddate());
			values.put("startDate", serverRecord.getStartDate());
			values.put("targetDate", serverRecord.getTargetDate());
			values.put("saveState", serverRecord.getSaveState());
			values.put("submitState", serverRecord.getSubmitState());
			values.put("isRecord", serverRecord.getIsRecord());
			values.put("temperature", serverRecord.getTemperature());
			
			List<PlanList> planList = serverRecord.getPlanList();
			StringBuffer planSb=new StringBuffer();
			for (int i = 0; i < planList.size(); i++) {
				if (i!=planList.size()-1) {
					planSb.append(planList.get(i).getContent()+"|");
				}else {
					planSb.append(planList.get(i).getContent());
				}
			}
			values.put("planList", planSb.toString());
			
			List<PlanSubList> planSubList = serverRecord.getPlanSubList();
			StringBuffer planSubSb = new StringBuffer();
			for (int i = 0; i < planSubList.size(); i++) {
				PlanSubList planSubList2 = planSubList.get(i);
				if (i!=planSubList.size()-1) {
					planSubSb.append(planSubList2.getBigTitle()+"^"+planSubList2.getContent()+"^"+planSubList2.getPlanNum()+"^"+planSubList2.getRealNum()+"|");
				}else {
					planSubSb.append(planSubList2.getBigTitle()+"^"+planSubList2.getContent()+"^"+planSubList2.getPlanNum()+"^"+planSubList2.getRealNum());
				}
			}
			values.put("planSubList", planSubSb.toString());
			values.put("isShow", "1");// 1显示 0不显示
			
			long row = db.insert("server_record", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查询每日情况
	 */
	public List<ServerRecord> getSerScore(String custId){
		ArrayList<ServerRecord> list = new ArrayList<ServerRecord>();
		Cursor cursor = db.rawQuery("select * from server_record where custID='" +custId+ "'",null);
		if (cursor.moveToFirst()) {
			do {
				ServerRecord serverRecord = new ServerRecord();
				serverRecord.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				serverRecord.setAge(cursor.getString(cursor.getColumnIndex("age")));
				serverRecord.setScheduleId(cursor.getString(cursor.getColumnIndex("scheduleId")));
				serverRecord.setBloodsugar(cursor.getString(cursor.getColumnIndex("bloodsugar")));
				serverRecord.setBreath(cursor.getString(cursor.getColumnIndex("breath")));
				serverRecord.setContent(cursor.getString(cursor.getColumnIndex("content")));
				serverRecord.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				serverRecord.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				serverRecord.setEmpName(cursor.getString(cursor.getColumnIndex("empName")));
				serverRecord.setEndDate(cursor.getString(cursor.getColumnIndex("endDate")));
				serverRecord.setHighpressure(cursor.getString(cursor.getColumnIndex("highpressure")));
				serverRecord.setLowpressure(cursor.getString(cursor.getColumnIndex("lowpressure")));
				serverRecord.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
				serverRecord.setOtherSub(cursor.getString(cursor.getColumnIndex("otherSub")));
				serverRecord.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				serverRecord.setPulse(cursor.getString(cursor.getColumnIndex("pulse")));
				serverRecord.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				serverRecord.setRecorddate(cursor.getString(cursor.getColumnIndex("recorddate")));
				serverRecord.setStartDate(cursor.getString(cursor.getColumnIndex("startDate")));
				serverRecord.setTargetDate(cursor.getString(cursor.getColumnIndex("targetDate")));
				serverRecord.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				serverRecord.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				serverRecord.setIsRecord(cursor.getString(cursor.getColumnIndex("isRecord")));
				
				String plan = cursor.getString(cursor.getColumnIndex("planList"));
				ArrayList<PlanList> planList = new ArrayList<PlanList>();
				if (!"".equals(plan)&&null!=plan) {
					if (plan.contains("|")) {
						String[] planArr = plan.split("\\|");
						for (String str : planArr) {
							PlanList planListObj = new PlanList();
							planListObj.setContent(str);
							planList.add(planListObj);
						}
					}else {
						PlanList planListObj = new PlanList();
						planListObj.setContent(plan);
						planList.add(planListObj);
					}
				}
				serverRecord.setPlanList(planList);

				ArrayList<PlanSubList> planSubList = new ArrayList<PlanSubList>();
				String planSub = cursor.getString(cursor.getColumnIndex("planSubList"));
				if (!"".equals(planSub)&&null!=planSub) {
					if (planSub.contains("|")) {
						String[] splitS = planSub.split("\\|");
						for (int i = 0; i < splitS.length; i++) {
							String[] split = splitS[i].split("\\^");
							PlanSubList planSubListObj = new PlanSubList();
							planSubListObj.setBigTitle(split[0]);
							planSubListObj.setContent(split[1]);
							planSubListObj.setPlanNum(split[2]);
							planSubListObj.setRealNum(split[3]);
							planSubList.add(planSubListObj);
						}
					}else {
						String[] split = planSub.split("\\^");
						PlanSubList planSubListObj = new PlanSubList();
						planSubListObj.setBigTitle(split[0]);
						planSubListObj.setContent(split[1]);
						planSubListObj.setPlanNum(split[2]);
						planSubListObj.setRealNum(split[3]);
						planSubList.add(planSubListObj);
					}
				}
				serverRecord.setPlanSubList(planSubList);
				
				list.add(serverRecord);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	/**
	 * 查询每日情况 通过日程ID
	 */
	public List<ServerRecord> getSerScoreByScheduleId(String scheduleId){
		ArrayList<ServerRecord> list = new ArrayList<ServerRecord>();
		Cursor cursor = db.rawQuery("select * from server_record where scheduleId='" +scheduleId+ "' and isShow='1'",null);
		if (cursor.moveToFirst()) {
			do {
				ServerRecord serverRecord = new ServerRecord();
				serverRecord.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				serverRecord.setAge(cursor.getString(cursor.getColumnIndex("age")));
				serverRecord.setScheduleId(cursor.getString(cursor.getColumnIndex("scheduleId")));
				serverRecord.setBloodsugar(cursor.getString(cursor.getColumnIndex("bloodsugar")));
				serverRecord.setBreath(cursor.getString(cursor.getColumnIndex("breath")));
				serverRecord.setContent(cursor.getString(cursor.getColumnIndex("content")));
				serverRecord.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				serverRecord.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				serverRecord.setEmpName(cursor.getString(cursor.getColumnIndex("empName")));
				serverRecord.setEndDate(cursor.getString(cursor.getColumnIndex("endDate")));
				serverRecord.setHighpressure(cursor.getString(cursor.getColumnIndex("highpressure")));
				serverRecord.setLowpressure(cursor.getString(cursor.getColumnIndex("lowpressure")));
				serverRecord.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
				serverRecord.setOtherSub(cursor.getString(cursor.getColumnIndex("otherSub")));
				serverRecord.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				serverRecord.setPulse(cursor.getString(cursor.getColumnIndex("pulse")));
				serverRecord.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				serverRecord.setRecorddate(cursor.getString(cursor.getColumnIndex("recorddate")));
				serverRecord.setStartDate(cursor.getString(cursor.getColumnIndex("startDate")));
				serverRecord.setTargetDate(cursor.getString(cursor.getColumnIndex("targetDate")));
				serverRecord.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				serverRecord.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				serverRecord.setIsRecord(cursor.getString(cursor.getColumnIndex("isRecord")));
				
				String plan = cursor.getString(cursor.getColumnIndex("planList"));
				ArrayList<PlanList> planList = new ArrayList<PlanList>();
				if (!"".equals(plan)&&null!=plan) {
					if (plan.contains("|")) {
						String[] planArr = plan.split("\\|");
						for (String str : planArr) {
							PlanList planListObj = new PlanList();
							planListObj.setContent(str);
							planList.add(planListObj);
						}
					}else {
						PlanList planListObj = new PlanList();
						planListObj.setContent(plan);
						planList.contains(planListObj);
					}
				}
				serverRecord.setPlanList(planList);
				
				ArrayList<PlanSubList> planSubList = new ArrayList<PlanSubList>();
				String planSub = cursor.getString(cursor.getColumnIndex("planSubList"));
				if (!"".equals(planSub)&&null!=planSub) {
					if (planSub.contains("|")) {
						String[] splitS = planSub.split("\\|");
						for (int i = 0; i < splitS.length; i++) {
							String[] split = splitS[i].split("\\^");
							PlanSubList planSubListObj = new PlanSubList();
							planSubListObj.setBigTitle(split[0]);
							planSubListObj.setContent(split[1]);
							planSubListObj.setPlanNum(split[2]);
							planSubListObj.setRealNum(split[3]);
							planSubList.add(planSubListObj);
						}
					}else {
						String[] split = planSub.split("\\^");
						PlanSubList planSubListObj = new PlanSubList();
						planSubListObj.setBigTitle(split[0]);
						planSubListObj.setContent(split[1]);
						planSubListObj.setPlanNum(split[2]);
						planSubListObj.setRealNum(split[3]);
						planSubList.add(planSubListObj);
					}
				}
				serverRecord.setPlanSubList(planSubList);
				
				list.add(serverRecord);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	/**
	 * 根据客户id删除服务记录表数据
	 */
	public void deleteServerRecord(){
		db.delete("server_record", "isShow=?", new String[]{"0"});
	}
	/**
	 * 根据日程id删除服务记录表数据
	 */
	public void deleteServerRecordByScheID(String scheduleId){
		db.delete("server_record", "scheduleId=?", new String[]{scheduleId});
	}
	/**
	 * 根据日程id修改修改添加的服务记录是否显示
	 */
	public boolean updateServerRecord(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("isShow", "0");
			int row = db.update("server_record", values,"scheduleId=?", new String[] {id });
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改修改添加的服务记录提交保存按钮状态
	 */
	public boolean updateServerRecordButton(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("submitState", "0");
			int row = db.update("server_record", values,"scheduleId=?", new String[] {id });
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 设置评估报告提交状态
	 */
	public boolean setRecordSubmitState(RecordSubmitState recordSubmitState){
		if (null!=recordSubmitState) {
			ContentValues values = new ContentValues();
			values.put("custID", recordSubmitState.getCustID());
			values.put("scheID", recordSubmitState.getScheID());
			values.put("basicSubmitState", recordSubmitState.getBasicSubmitState());
			values.put("brainSubmitState", recordSubmitState.getBrainSubmitState());
			values.put("everydaySubmitState", recordSubmitState.getEverydaySubmitState());
			values.put("drugSubmitState", recordSubmitState.getDrugSubmitState());
			values.put("sportSubmitState", recordSubmitState.getSportSubmitState());
			values.put("copmSubmitState", recordSubmitState.getCopmSubmitState());
			long row = db.insert("record_state", null, values);
			if (row == -1) {
				return false;
			} else {
				return true;
			}
		}
		return false;
		
	}
	/**
	 * 获取评估报告提交状态
	 */
	public RecordSubmitState getRecordSubmitState(String scheID){
		RecordSubmitState recordSubmitState = new RecordSubmitState();
		Cursor cursor = db.rawQuery(
				"select * from record_state where scheID='" +scheID+ "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				recordSubmitState.setScheID(cursor.getString(cursor.getColumnIndex("scheID")));
				recordSubmitState.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				recordSubmitState.setBasicSubmitState(cursor.getString(cursor.getColumnIndex("basicSubmitState")));								
				recordSubmitState.setBrainSubmitState(cursor.getString(cursor.getColumnIndex("brainSubmitState")));
				recordSubmitState.setEverydaySubmitState(cursor.getString(cursor.getColumnIndex("everydaySubmitState")));				
				recordSubmitState.setDrugSubmitState(cursor.getString(cursor.getColumnIndex("drugSubmitState")));				
				recordSubmitState.setSportSubmitState(cursor.getString(cursor.getColumnIndex("sportSubmitState")));
				recordSubmitState.setCopmSubmitState(cursor.getString(cursor.getColumnIndex("copmSubmitState")));
				return recordSubmitState;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return null;		
	}
	
	/**
	 * 根据日程id删除评估报告提交状态表数据
	 */
	public void deleteRecordSubmitState(String scheID){
		db.delete("record_state", "scheID=?", new String[]{scheID});
	}
	/**
	 * 根据日程id修改基本情况表提交状态
	 */
	public boolean updateRecordSubmitStateBasic(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("basicSubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改智力表提交状态
	 */
	public boolean updateRecordSubmitStateBrain(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("brainSubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改日常生活表提交状态
	 */
	public boolean updateRecordSubmitStateEveryday(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("everydaySubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改查体-基本提交状态
	 */
	public boolean updateRecordSubmitStateDrug(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("drugSubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改查体-运动功能评定提交状态
	 */
	public boolean updateRecordSubmitStateSport(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("sportSubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据日程id修改COPM提交状态
	 */
	public boolean updateCopmSubmitState(String id){
		if (!"".equals(id)&&null!=id) {
			ContentValues values = new ContentValues();
			values.put("copmSubmitState", "1");
			int row = db.update("record_state", values,"scheID=?", new String[] {id});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 向特殊情况表中保存数据
	 */
	public void saveSpecialCircumstances(SpecialCircumstances specialCircumstances) {
		if (specialCircumstances != null) {
			ContentValues values = new ContentValues();
			values.put("custID", specialCircumstances.getCustID());
			values.put("special", specialCircumstances.getSpecial());
			db.insert("special_record", null, values);
		}
	}

	/**
	 * 根据客户id查询特殊情况表中内容
	 */
	public List<SpecialCircumstances> selectSpecialCircumstancesByCustId(String custID) {
		List<SpecialCircumstances> list = new ArrayList<SpecialCircumstances>();
		Cursor cursor = db.rawQuery("select * from special_record where custID='"
				+ custID + "'", null);
		if (cursor.moveToFirst()) {
			do {
				SpecialCircumstances specialCircumstances = new SpecialCircumstances();
				specialCircumstances.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				specialCircumstances.setSpecial(cursor.getString(cursor.getColumnIndex("special")));
				list.add(specialCircumstances);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	
	/**
	 * 根据日程id删除特殊情况表中内容
	 */
	public void deleteSpecialCircumstances(String custID){
		db.delete("special_record", "custID=?", new String[]{custID});
	}
	
	/**
	 * 保存所有康复措施数据
	 */
	public void saveRehabilitationMeasure(RehabilitationMeasures rehabilitationMeasures){
		if (rehabilitationMeasures != null) {
			ContentValues values = new ContentValues();
			values.put("id", rehabilitationMeasures.getId());
			values.put("title", rehabilitationMeasures.getTitle());
			values.put("fatherid", rehabilitationMeasures.getFatherid());
			values.put("content", rehabilitationMeasures.getContent());
			values.put("type", rehabilitationMeasures.getType());
			values.put("bigTitle", rehabilitationMeasures.getBigTitle());
			values.put("smallTitle", rehabilitationMeasures.getSmallTitle());
			values.put("bigTitleId", rehabilitationMeasures.getBigTitleId());
			db.insert("rehabilitation_measure", null, values);
		}
	}
	
	/**
	 * 获取所有康复措施数据的大标题
	 */
	public List<RehabilitationMeasures> getRehabilitationMeasuresOfBigTitle(){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from rehabilitation_measure where type='0'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures rehabilitationMeasures = new RehabilitationMeasures();
				rehabilitationMeasures.setId(cursor.getString(cursor.getColumnIndex("id")));
				rehabilitationMeasures.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				rehabilitationMeasures.setType(cursor.getString(cursor.getColumnIndex("type")));
				list.add(rehabilitationMeasures);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	
	}
	
	/**
	 * 获取所有康复措施数据的大标题所属的小标题   或  获取所有康复措施数据的大标题所属的小标题所属的内容
	 */
	public List<RehabilitationMeasures> getRehabilitationMeasuresOfBigTitleoOfSmallTitle(String fatherid,String type){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from rehabilitation_measure where type='"+type +"'and fatherid='"+fatherid +"'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures rehabilitationMeasures = new RehabilitationMeasures();
				rehabilitationMeasures.setId(cursor.getString(cursor.getColumnIndex("id")));
				rehabilitationMeasures.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				rehabilitationMeasures.setFatherid(cursor.getString(cursor.getColumnIndex("fatherid")));
				rehabilitationMeasures.setContent(cursor.getString(cursor.getColumnIndex("content")));
				rehabilitationMeasures.setType(cursor.getString(cursor.getColumnIndex("type")));
				rehabilitationMeasures.setBigTitle(cursor.getString(cursor.getColumnIndex("bigTitle")));
				rehabilitationMeasures.setSmallTitle(cursor.getString(cursor.getColumnIndex("smallTitle")));
				rehabilitationMeasures.setBigTitleId(cursor.getString(cursor.getColumnIndex("bigTitleId")));
				list.add(rehabilitationMeasures);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	
	}
	
	/**
	 * 删除所有康复措施数据
	 */
	public void deleteRehabilitationMeasures(){
		db.delete("rehabilitation_measure", null, null);
	}
	
	/**
	 * 保存所有健康宣教施数据
	 */
	public void saveHealthEducation(RehabilitationMeasures rehabilitationMeasures){
		if (rehabilitationMeasures != null) {
			ContentValues values = new ContentValues();
			values.put("id", rehabilitationMeasures.getId());
			values.put("title", rehabilitationMeasures.getTitle());
			values.put("fatherid", rehabilitationMeasures.getFatherid());
			values.put("content", rehabilitationMeasures.getContent());
			values.put("type", rehabilitationMeasures.getType());
			values.put("bigTitle", rehabilitationMeasures.getBigTitle());
			values.put("smallTitle", rehabilitationMeasures.getSmallTitle());
			values.put("bigTitleId", rehabilitationMeasures.getBigTitleId());
			db.insert("health_education", null, values);
		}
	}
	
	/**
	 * 获取所有健康宣教数据的大标题
	 */
	public List<RehabilitationMeasures> getHealthEducationsOfBigTitle(){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from health_education where type='0'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures rehabilitationMeasures = new RehabilitationMeasures();
				rehabilitationMeasures.setId(cursor.getString(cursor.getColumnIndex("id")));
				rehabilitationMeasures.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				rehabilitationMeasures.setFatherid(cursor.getString(cursor.getColumnIndex("fatherid")));
				rehabilitationMeasures.setContent(cursor.getString(cursor.getColumnIndex("content")));
				rehabilitationMeasures.setType(cursor.getString(cursor.getColumnIndex("type")));
				rehabilitationMeasures.setBigTitle(cursor.getString(cursor.getColumnIndex("bigTitle")));
				rehabilitationMeasures.setSmallTitle(cursor.getString(cursor.getColumnIndex("smallTitle")));
				rehabilitationMeasures.setBigTitleId(cursor.getString(cursor.getColumnIndex("bigTitleId")));
				list.add(rehabilitationMeasures);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	
	}
	
	/**
	 * 获取所有健康宣教数据的大标题所属的小标题   或  获取所有健康宣教数据的大标题所属的小标题所属的内容
	 */
	public List<RehabilitationMeasures> getHealthEducationOfBigTitleoOfSmallTitle(String fatherid,String type){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from health_education where type='"+type +"'and fatherid='"+fatherid +"'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures rehabilitationMeasures = new RehabilitationMeasures();
				rehabilitationMeasures.setId(cursor.getString(cursor.getColumnIndex("id")));
				rehabilitationMeasures.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				rehabilitationMeasures.setFatherid(cursor.getString(cursor.getColumnIndex("fatherid")));
				rehabilitationMeasures.setContent(cursor.getString(cursor.getColumnIndex("content")));
				rehabilitationMeasures.setType(cursor.getString(cursor.getColumnIndex("type")));
				rehabilitationMeasures.setBigTitle(cursor.getString(cursor.getColumnIndex("bigTitle")));
				rehabilitationMeasures.setSmallTitle(cursor.getString(cursor.getColumnIndex("smallTitle")));
				rehabilitationMeasures.setBigTitleId(cursor.getString(cursor.getColumnIndex("bigTitleId")));
				list.add(rehabilitationMeasures);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	
	}
	
	/**
	 * 删除所有健康宣教数据
	 */
	public void deleteHealthEducation(){
		db.delete("health_education", null, null);
	}
	
	/**
	 * 保存康复护理计划表-基本数据
	 */
	public int saveRecoveryNursingPlan(RecoveryNursingPlan recoveryNursingPlan){
		if (recoveryNursingPlan != null) {
			ContentValues values = new ContentValues();
			values.put("planID", recoveryNursingPlan.getId());
			values.put("custID", recoveryNursingPlan.getCustID());
			values.put("cudeID", recoveryNursingPlan.getCudeID());
			values.put("custName", recoveryNursingPlan.getCustName());
			values.put("sex", recoveryNursingPlan.getSex());
			values.put("age", recoveryNursingPlan.getAge());
			values.put("illhis", recoveryNursingPlan.getIllhis());
			values.put("checkbody", recoveryNursingPlan.getCheckbody());
			values.put("adlscore", recoveryNursingPlan.getAdlscore());
			values.put("mmsescore", recoveryNursingPlan.getMmsescore());
			values.put("druguse", recoveryNursingPlan.getDruguse());
			values.put("notice", recoveryNursingPlan.getNotice());
			values.put("comitStatus", recoveryNursingPlan.getComitStatus());
			values.put("signinDate", recoveryNursingPlan.getSigninDate());
			values.put("createTime", recoveryNursingPlan.getCreateTime());
			values.put("version", recoveryNursingPlan.getVersion());
			values.put("submitState", recoveryNursingPlan.getSubmitState());
			values.put("saveState", recoveryNursingPlan.getSaveState());
			values.put("empName", recoveryNursingPlan.getEmpName());
			values.put("serDate", recoveryNursingPlan.getSerDate());
			long row = db.insert("rehabilitation_nursing_plan", null, values);
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * 获取康复护理计划表-基本数据
	 */
	public RecoveryNursingPlan getRecoveryNursingPlan(String planID){
		RecoveryNursingPlan recoveryNursingPlan = new RecoveryNursingPlan();
		Cursor cursor = db.rawQuery("select * from rehabilitation_nursing_plan where planID='"+planID+"'", null);
		if (cursor.moveToLast()) {
			do {
				recoveryNursingPlan.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				recoveryNursingPlan.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				recoveryNursingPlan.setCudeID(cursor.getString(cursor.getColumnIndex("cudeID")));
				recoveryNursingPlan.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				recoveryNursingPlan.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				recoveryNursingPlan.setAge(cursor.getString(cursor.getColumnIndex("age")));
				recoveryNursingPlan.setIllhis(cursor.getString(cursor.getColumnIndex("illhis")));
				recoveryNursingPlan.setCheckbody(cursor.getString(cursor.getColumnIndex("checkbody")));
				recoveryNursingPlan.setAdlscore(cursor.getString(cursor.getColumnIndex("adlscore")));
				recoveryNursingPlan.setMmsescore(cursor.getString(cursor.getColumnIndex("mmsescore")));
				recoveryNursingPlan.setDruguse(cursor.getString(cursor.getColumnIndex("druguse")));
				recoveryNursingPlan.setNotice(cursor.getString(cursor.getColumnIndex("notice")));
				recoveryNursingPlan.setComitStatus(cursor.getString(cursor.getColumnIndex("comitStatus")));
				recoveryNursingPlan.setSigninDate(cursor.getString(cursor.getColumnIndex("signinDate")));
				recoveryNursingPlan.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				recoveryNursingPlan.setVersion(cursor.getString(cursor.getColumnIndex("version")));
				recoveryNursingPlan.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				recoveryNursingPlan.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				recoveryNursingPlan.setEmpName(cursor.getString(cursor.getColumnIndex("empName")));
				recoveryNursingPlan.setSerDate(cursor.getString(cursor.getColumnIndex("serDate")));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return recoveryNursingPlan;
		
	}
	
	/**
	 * 获取所有康复护理计划表-基本数据
	 */
	public List<RecoveryNursingPlan> getAllRecoveryNursingPlan(){
		List<RecoveryNursingPlan> list = new ArrayList<RecoveryNursingPlan>();
		Cursor cursor = db.rawQuery("select * from rehabilitation_nursing_plan", null);
		if (cursor.moveToFirst()) {
			do {
				RecoveryNursingPlan recoveryNursingPlan = new RecoveryNursingPlan();
				recoveryNursingPlan.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				recoveryNursingPlan.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
				recoveryNursingPlan.setCudeID(cursor.getString(cursor.getColumnIndex("cudeID")));
				recoveryNursingPlan.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
				recoveryNursingPlan.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				recoveryNursingPlan.setAge(cursor.getString(cursor.getColumnIndex("age")));
				recoveryNursingPlan.setIllhis(cursor.getString(cursor.getColumnIndex("illhis")));
				recoveryNursingPlan.setCheckbody(cursor.getString(cursor.getColumnIndex("checkbody")));
				recoveryNursingPlan.setAdlscore(cursor.getString(cursor.getColumnIndex("adlscore")));
				recoveryNursingPlan.setMmsescore(cursor.getString(cursor.getColumnIndex("mmsescore")));
				recoveryNursingPlan.setDruguse(cursor.getString(cursor.getColumnIndex("druguse")));
				recoveryNursingPlan.setNotice(cursor.getString(cursor.getColumnIndex("notice")));
				recoveryNursingPlan.setComitStatus(cursor.getString(cursor.getColumnIndex("comitStatus")));
				recoveryNursingPlan.setSigninDate(cursor.getString(cursor.getColumnIndex("signinDate")));
				recoveryNursingPlan.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				recoveryNursingPlan.setVersion(cursor.getString(cursor.getColumnIndex("version")));
				recoveryNursingPlan.setSubmitState(cursor.getString(cursor.getColumnIndex("submitState")));
				recoveryNursingPlan.setSaveState(cursor.getString(cursor.getColumnIndex("saveState")));
				recoveryNursingPlan.setEmpName(cursor.getString(cursor.getColumnIndex("empName")));
				recoveryNursingPlan.setSerDate(cursor.getString(cursor.getColumnIndex("serDate")));
				list.add(recoveryNursingPlan);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
		
	}
	
	/**
	 * 根据康复护理计划ID修改 提交状态
	 */
	public boolean updateRecoveryNursingPlan(String planID){
		if (!"".equals(planID)&&null!=planID) {
			ContentValues values = new ContentValues();
			values.put("comitStatus", "1");
			int row = db.update("rehabilitation_nursing_plan", values,"planID=?", new String[] {planID});
			if (row == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除康复护理计划表-基本数据
	 */
	public void deleteRecoveryNursingPlan(String planID){
		if (!"".equals(planID)) {
			db.delete("rehabilitation_nursing_plan", "planID=?", new String[]{planID});
		}
	}
	
	/**
	 * 保存康复护理计划表-病史记录
	 */
	public int saveHistoryRecords(RegainHistoryRecords regainHistoryRecords){
		if (regainHistoryRecords != null) {
			ContentValues values = new ContentValues();
			values.put("planID", regainHistoryRecords.getPlanID());
			values.put("num", regainHistoryRecords.getNum());
			values.put("hisRecName", regainHistoryRecords.getHisRecName());
			values.put("description", regainHistoryRecords.getDescription());
			long row = db.insert("regainHistoryRecords", null, values);
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * 获取康复护理计划表-病史记录
	 */
	public List<RegainHistoryRecords> getHistoryRecords(String planID){
		List<RegainHistoryRecords> list = new ArrayList<RegainHistoryRecords>();
		Cursor cursor = db.rawQuery("select * from regainHistoryRecords where planID='"+ planID +"'", null);
		if (cursor.moveToLast()) {
			do {
				RegainHistoryRecords regainHistoryRecords = new RegainHistoryRecords();
				regainHistoryRecords.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				regainHistoryRecords.setNum(cursor.getString(cursor.getColumnIndex("num")));
				regainHistoryRecords.setHisRecName(cursor.getString(cursor.getColumnIndex("hisRecName")));
				regainHistoryRecords.setDescription(cursor.getString(cursor.getColumnIndex("description")));
				list.add(regainHistoryRecords);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}  
	
	/**
	 * 删除康复护理计划表-病史记录
	 */
	public void deleteHistoryRecords(String planID){
		if (!"".equals(planID)) {
			db.delete("regainHistoryRecords", "planID=?", new String[]{planID});
		}
	}
	
	/**
	 * 保存康复护理计划表-现存问题或康复护理计划表-拟解决问题
	 */
	public int saveRegainQuestion(RegainQuestion regainQuestion){
		if (regainQuestion != null) {
			long row=-1;
			ContentValues values = new ContentValues();
			values.put("planID", regainQuestion.getPlanID());
			values.put("num", regainQuestion.getNum());
			values.put("question", regainQuestion.getQuestion());
			values.put("qtype", regainQuestion.getQtype());
			values.put("term", regainQuestion.getTerm());
			values.put("sortIndex", regainQuestion.getSortIndex());
			if ("0".equals(regainQuestion.getQtype())) {
				row = db.insert("currQuestion", null, values);
			}else if ("1".equals(regainQuestion.getQtype())) {
				row = db.insert("solveQuestion", null, values);
			}
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
		
	}
	
	/**
	 * 获取康复护理计划表-现存问题或康复护理计划表-拟解决问题
	 */
	public List<RegainQuestion> getRegainQuestion(String planID,String qtype){
		List<RegainQuestion> list = new ArrayList<RegainQuestion>();
		Cursor cursor = null;
		if (!"".equals(qtype) && "0".equals(qtype)) {
			cursor = db.rawQuery("select * from currQuestion where planID='"+ planID +"'", null);
		}else if(!"".equals(qtype) && "1".equals(qtype)){
			cursor = db.rawQuery("select * from solveQuestion where planID='"+ planID +"'", null);
		}
		if (cursor.moveToFirst()) {
			do {
				RegainQuestion regainQuestion = new RegainQuestion();
				regainQuestion.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				regainQuestion.setNum(cursor.getString(cursor.getColumnIndex("num")));
				regainQuestion.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
				regainQuestion.setQtype(cursor.getString(cursor.getColumnIndex("qtype")));
				regainQuestion.setTerm(cursor.getString(cursor.getColumnIndex("term")));
				regainQuestion.setSortIndex(cursor.getString(cursor.getColumnIndex("sortIndex")));
				list.add(regainQuestion);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
		
	}
	
	/**
	 * 删除康复护理计划表-现存问题或康复护理计划表-拟解决问题
	 */
	public void deleteRegainQuestion(String planID,String qtype){
		if (!"".equals(planID)) {
			if (!"".equals(qtype) && "0".equals(qtype)) {
				db.delete("currQuestion", "planID=?", new String[]{planID});
			}else if (!"".equals(qtype) && "1".equals(qtype)) {
				db.delete("solveQuestion", "planID=?", new String[]{planID});
			}
		}
	}
	
	/**
	 * 保存康复护理计划表-康复目标
	 */
	public int saveRegainTarget(RegainTarget regainTarget){
		if (regainTarget != null) {
			ContentValues values = new ContentValues();
			values.put("planID", regainTarget.getPlanID());
			values.put("num", regainTarget.getNum());
			values.put("content", regainTarget.getContent());
			values.put("term", regainTarget.getTerm());
			values.put("sortIndex", regainTarget.getSortIndex());
			long row = db.insert("regainTarget", null, values);
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * 获取康复护理计划表-康复目标
	 */
	public List<RegainTarget> getRegainTarget(String planID){
		List<RegainTarget> list = new ArrayList<RegainTarget>();
		Cursor cursor = db.rawQuery("select * from regainTarget where planID='"+ planID +"'", null);
		if (cursor.moveToFirst()) {
			do {
				RegainTarget regainTarget = new RegainTarget();
				regainTarget.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				regainTarget.setNum(cursor.getString(cursor.getColumnIndex("num")));
				regainTarget.setContent(cursor.getString(cursor.getColumnIndex("content")));
				regainTarget.setTerm(cursor.getString(cursor.getColumnIndex("term")));
				regainTarget.setSortIndex(cursor.getString(cursor.getColumnIndex("sortIndex")));
				list.add(regainTarget);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
		
	} 
	
	/**
	 * 删除康复护理计划表-康复目标
	 */
	public void deleteRegainTarget(String planID){
		if (!"".equals(planID)) {
			db.delete("regainTarget", "planID=?", new String[]{planID});
		}
	}
	
	/**
	 * 保存康复护理计划表-康复措施
	 */
	public int saveRegainTargetSub(RehabilitationMeasures regainTargetSub){
		if (regainTargetSub != null) {
			ContentValues values = new ContentValues();
			values.put("planID", regainTargetSub.getPlanID());
			values.put("num", regainTargetSub.getNum());
			values.put("content", regainTargetSub.getContent());
			values.put("sortIndex", regainTargetSub.getSortIndex());
			values.put("bigTitle", regainTargetSub.getBigTitle());
			values.put("smallTitle", regainTargetSub.getSmallTitle());
			long row = db.insert("regainTargetSub", null, values);
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
		
	}
	
	/**
	 * 获取康复护理计划表-康复措施
	 */
	public List<RehabilitationMeasures> getRegainTargetSub(String planID){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from regainTargetSub where planID='"+ planID +"'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures regainTargetSub = new RehabilitationMeasures();
				regainTargetSub.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				regainTargetSub.setNum(cursor.getString(cursor.getColumnIndex("num")));
				regainTargetSub.setContent(cursor.getString(cursor.getColumnIndex("content")));
				regainTargetSub.setSortIndex(cursor.getString(cursor.getColumnIndex("sortIndex")));
				regainTargetSub.setBigTitle(cursor.getString(cursor.getColumnIndex("bigTitle")));
				regainTargetSub.setSmallTitle(cursor.getString(cursor.getColumnIndex("smallTitle")));
				list.add(regainTargetSub);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}  
	
	/**
	 * 删除康复护理计划表-康复措施
	 */
	public void deleteRegainTargetSub(String planID){
		if (!"".equals(planID)) {
			db.delete("regainTargetSub", "planID=?", new String[]{planID});
		}
	}
	
	/**
	 * 保存康复护理计划表-健康宣教
	 */
	public int saveRegainPublicize(RehabilitationMeasures regainPublicize){
		if (regainPublicize != null) {
			ContentValues values = new ContentValues();
			values.put("planID", regainPublicize.getPlanID());
			values.put("num", regainPublicize.getNum());
			values.put("content", regainPublicize.getContent());
			values.put("bigTitle", regainPublicize.getBigTitle());
			values.put("smallTitle", regainPublicize.getSmallTitle());
			long row = db.insert("regainPublicize", null, values);
			if (row == -1) {
				return -1;
			} else {
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * 获取康复护理计划表-健康宣教
	 */
	public List<RehabilitationMeasures> getRegainPublicize(String planID){
		List<RehabilitationMeasures> list = new ArrayList<RehabilitationMeasures>();
		Cursor cursor = db.rawQuery("select * from regainPublicize where planID='"+ planID +"'", null);
		if (cursor.moveToFirst()) {
			do {
				RehabilitationMeasures regainPublicize = new RehabilitationMeasures();
				regainPublicize.setPlanID(cursor.getString(cursor.getColumnIndex("planID")));
				regainPublicize.setNum(cursor.getString(cursor.getColumnIndex("num")));
				regainPublicize.setContent(cursor.getString(cursor.getColumnIndex("content")));
				regainPublicize.setBigTitle(cursor.getString(cursor.getColumnIndex("bigTitle")));
				regainPublicize.setSmallTitle(cursor.getString(cursor.getColumnIndex("smallTitle")));
				list.add(regainPublicize);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}  
	
	/**
	 * 删除康复护理计划表-健康宣教
	 */
	public void deleteRegainPublicize(String planID){
		if (!"".equals(planID)) {
			db.delete("regainPublicize", "planID=?", new String[]{planID});
		}
	}
	
	
}
