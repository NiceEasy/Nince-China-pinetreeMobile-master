package com.pinetree.mobile.utils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.question.adapter.AdapterCheckBox;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.Copm;
import com.pinetree.mobile.bean.CopmInfo;
import com.pinetree.mobile.bean.InitData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.Toast;

public class Tools {
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String TIME_FORMAT2 = "HH:mm";

	/**
	 *取得当前系统时间，返回java.util.Date类型
	 * 
	 * @see java.util.Date
	 * @return java.util.Date 返回服务器当前系统时间
	 */
	public static String getCurrDate() {
		Date date = new java.util.Date();
		return getFormatDate(date, DATE_FORMAT);
	}

	public static String getCurrTime() {
		Date date = new java.util.Date();
		return getFormatDate(date, TIME_FORMAT2);
	}

	/**
	 * 根据格式得到格式化后的日期
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 * @return String 返回格式化后的日期，格式由参数<code>format</code> 定义，如yyyy-MM-dd，如2006-02-15
	 */
	public static String getFormatDate(java.util.Date currDate, String format) {
		if (currDate == null) {
			return "";
		}
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.format(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
			try {
				return dtFormatdB.format(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static void showToast(Context mContext, String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	public static int getCurrentTimeMillis() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 增加RadioButton,横向
	 * 
	 * @param mContext
	 * @param array_s
	 * @param rg
	 */
	public static void addRadioButtonH(Context mContext, String[] array_s, RadioGroup rg) {
		rg.setOrientation(LinearLayout.HORIZONTAL);

		for (int i = 0; i < array_s.length; i++) {
			RadioButton tempButton = new RadioButton(mContext);
			tempButton.setText(array_s[i]);
			tempButton.setId(i);
			tempButton.setButtonDrawable(mContext.getResources().getDrawable(R.drawable.bt_radio_selector));

			RadioGroup.LayoutParams params_rb = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params_rb.setMargins(20, 0, 0, 0);

			// rg.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			rg.addView(tempButton, params_rb);
			if (i == 0) {
				tempButton.setChecked(true);
			}
		}
	}

	/**
	 * 增加RadioButton,竖向
	 * 
	 * @param mContext
	 * @param array_s
	 * @param rg
	 */
	public static void addRadioButtonV(Context mContext, String[] array_s, RadioGroup rg) {
		rg.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < array_s.length; i++) {
			RadioButton tempButton = new RadioButton(mContext);
			tempButton.setText(array_s[i]);
			tempButton.setId(i);
			tempButton.setButtonDrawable(mContext.getResources().getDrawable(R.drawable.bt_radio_selector));
			rg.addView(tempButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			if (i == 0) {
				tempButton.setChecked(true);
			}
		}
	}

	/**
	 * 
	 *  @功能描述 动态设置ListView的高度
	 * @author wcm
	 * @param listView
	 * @createDate 2015-9-6 下午3:23:12
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		AdapterCheckBox listAdapter = (AdapterCheckBox) listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static String getCheckBoxStatus(List<CheckBox> cb) {
		String str_t = "";
		if(cb!= null)
		{
			for (int i = 0; i < cb.size(); i++) {
				if (cb.get(i).isChecked()) {
					str_t += i + ",";
				}				
			}
			
			if (str_t.indexOf(",") != -1) {
				str_t = str_t.substring(0, str_t.length() - 1);
			}
		}
		
		return str_t;
	}

	public static String getCheckBoxStatus1(List<CheckBox> cb) {
		String str_t = "";
		if(cb!= null)
		{
			for (int i = 0; i < cb.size(); i++) {
				if (cb.get(i).isChecked()) {
					str_t += i + ",";
				}
				else
				{
					str_t += i + ",";
				}
				
			}
			
			if (str_t.indexOf(",") != -1) {
				str_t = str_t.substring(0, str_t.length() - 1);
			}
		}
		
		return str_t;
	}
	
	public static String getCheckBoxStatusText(List<CheckBox> cb) {
		String str_t = "";
		if(cb != null)
		{
			for (int i = 0; i < cb.size(); i++) {
				if (cb.get(i).isChecked()) {
					str_t += cb.get(i).getText() + ",";
				}
			}
			if (str_t.indexOf(",") != -1) {
				str_t = str_t.substring(0, str_t.length() - 1);
			}
		}
		
		return str_t;
	}

	public static String getCheckBoxStatusTextOther(List<CheckBox> cb) {
		String str_t = "";
		if(cb!=null)
		{
			for (int i = 0; i < cb.size() - 1; i++) {
				if (cb.get(i).isChecked()) {
					str_t += cb.get(i).getText() + ",";
				}
			}
			if (str_t.indexOf(",") != -1) {
				str_t = str_t.substring(0, str_t.length() - 1);
			}
		}
		
		return str_t;
	}

	public static String getMrgValue(MultiLineRadioGroup mrg) {
		String item = "";
		int[] items = {};
		items = mrg.getCheckedItems();
		for (int j = 0; j < items.length; j++) {
			item += items[j] + ",";
		}
		if (items.length > 0) {
			item = item.substring(0, item.lastIndexOf(","));
		}
		return item;
	}

	public static CustomProgressDialog progressDialog = null;

	public static void startProgressDialog(Context context, String message) {
		if (message == null) {
			message = "加载中...";
		}
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(context).setMessage(message);
		}
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();
	}

	public static void stopProgressDialog(Context context) {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	/**
	 * 
	 * @功能描述 验证是否是身份证号码
	 * @author wcm
	 * @param idNum
	 * @return
	 * @createDate 2015-9-17 上午9:31:27
	 */
	public static boolean isIDNumber(String idNum) {
		// 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNumMatcher = idNumPattern.matcher(idNum);
		if (idNumMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @功能描述 从身份证号码中提取出生日期
	 * @author wcm
	 * @param idNum
	 * @return
	 * @createDate 2015-9-17 上午9:31:48
	 */
	public static String getBirthdayFromIDNumber(String idNum) {
		String result = "";
		if (isIDNumber(idNum)) {
			// 如果是身份证号码，定义正则表达式提取出身份证中的出生日期
			Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");// 身份证上的前6位以及出生年月日
			//通过Pattern获得Matcher
			Matcher birthDateMather = birthDatePattern.matcher(idNum);
			//通过Matcher获得用户的出生年月日
			if (birthDateMather.find()) {
				String year = birthDateMather.group(1);
				String month = birthDateMather.group(2);
				String date = birthDateMather.group(3);
				// 输出用户的出生年月日
				result = year + "-" + month + "-" + date;
			}
		} else {
			// 如果不是，输出信息提示用户
			// System.out.println("您输入的并不是身份证号");
			return result;
		}
		return result;
	}

	/**
	 * 
	 * @功能描述 验证是否是正确的email地址
	 * @author wcm
	 * @param email
	 * @return
	 * @createDate 2015-9-17 上午11:23:28
	 */
	public static boolean isEmailAdress(String email) {
		boolean isMatched = false;
		if (!email.equals("")) {
			String check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			isMatched = matcher.matches();
			return isMatched;
		}
		return isMatched;
	}

	public static InitData jsonToInitData(String jsonStr, Class<?> cl) {
		InitData obj = null;
		Gson gson = new Gson();

		obj = (InitData) gson.fromJson(jsonStr, cl);

		return obj;
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static ArrayList<Copm> getCopmFromJson(String jsonData) {
		ArrayList<Copm> arrayList = new ArrayList<Copm>();

		Type listType = new TypeToken<LinkedList<Copm>>() {
		}.getType();
		Gson gson = new Gson();
		LinkedList<Copm> lss = gson.fromJson(jsonData, listType);

		for (Iterator iterator = lss.iterator(); iterator.hasNext();) {
			Copm sa = (Copm) iterator.next();
			arrayList.add(sa);
		}
		return arrayList;
	}
	public static ArrayList<CopmInfo> getCopmInfoFromJson(String jsonData) {
		ArrayList<CopmInfo> arrayList = new ArrayList<CopmInfo>();
		
		Type listType = new TypeToken<LinkedList<CopmInfo>>() {
		}.getType();
		Gson gson = new Gson();
		LinkedList<CopmInfo> lss = gson.fromJson(jsonData, listType);
		
		for (Iterator iterator = lss.iterator(); iterator.hasNext();) {
			CopmInfo sa = (CopmInfo) iterator.next();
			arrayList.add(sa);
		}
		return arrayList;
	}
}
