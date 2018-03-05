package com.pinetree.mobile.db;

import java.util.ArrayList;
import java.util.List;

import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.NoteBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @������	ע�Ͳ�ѯ
 * @author wcm 
 * @createDate 2015-10-13 ����9:17:57
 */
public class NoteOperationDB {
	private SQLiteDatabase db;
	Context context;
	String dbFile;
	String path;

	public NoteOperationDB(Context context) {
		this.context = context;
		path = AssetHelper.getPath(context, false) + "/" + context.getString(R.string.str_note_db);
	}

	public NoteBean queryNote(String guid) {
		NoteBean noteBean = new NoteBean();
		Cursor cursor = null;
		db = SQLiteDatabase.openOrCreateDatabase(path, null);
		String sql = "select * from NoteInfo where guid= '"+guid+"'";
		cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			noteBean.setGoal(cursor.getString(cursor.getColumnIndex("goal")));
			noteBean.setDefinition(cursor.getString(cursor.getColumnIndex("definition")));
			noteBean.setMethod(cursor.getString(cursor.getColumnIndex("method")));
			noteBean.setRecord(cursor.getString(cursor.getColumnIndex("record")));
			noteBean.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
		}
		cursor.close();
		db.close();
		return noteBean;

	}

	
}
