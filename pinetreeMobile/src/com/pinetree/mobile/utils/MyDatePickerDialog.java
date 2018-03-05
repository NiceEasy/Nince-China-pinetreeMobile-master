package com.pinetree.mobile.utils;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * 日期选择对话框
 * 
 * @author hailang
 * 
 */
public class MyDatePickerDialog {

	Context ctx;
	View editview;
	Calendar calendar;

	public MyDatePickerDialog(Context context, View view, Calendar calendar) {
		this.ctx = context;
		this.editview = view;
		this.calendar = calendar;
	}

	public void showMyDatePickerDialog() {
		new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				String dateMonth = (monthOfYear + 1) + "";
				String dateDayOfMonth = dayOfMonth + "";
				if (monthOfYear < 9) {
					dateMonth = "0" + (monthOfYear + 1);
				}
				if (dayOfMonth < 10) {
					dateDayOfMonth = "0" + dayOfMonth;
				}
				((EditText) editview).setText("" + year + "-" + dateMonth + "-" + dateDayOfMonth);
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
	}

}
