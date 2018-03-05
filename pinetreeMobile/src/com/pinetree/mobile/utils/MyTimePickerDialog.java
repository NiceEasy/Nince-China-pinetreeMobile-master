package com.pinetree.mobile.utils;

import java.util.Calendar;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * @author hailang
 * 
 */
public class MyTimePickerDialog {

	Context ctx;
	View editview;
	Calendar calendar;

	public MyTimePickerDialog(Context context, View view, Calendar calendar) {
		this.ctx = context;
		this.editview = view;
		this.calendar = calendar;
	}

	public void showMyTimePickerDialog() {
		new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				String hourOfDayStr = hourOfDay + "";
				String minuteStr = minute + "";
				if (hourOfDay < 10) {
					hourOfDayStr = "0" + hourOfDay;
				}
				if (minute < 10) {
					minuteStr = "0" + minute;
				}
				((EditText) editview).setText("" + hourOfDayStr + ":" + minuteStr);
			}
//		}, calendar.get(Calendar.HOUR), calendar.get(Calendar.HOUR_OF_DAY), true).show();
		}, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

	}

}
