package com.pinetree.mobile.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.pinetree.mobile.R;
import com.pinetree.mobile.bean.AreaEntity;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * 
 * @������	�������������ѯ
 * @author wcm 
 * @createDate 2015-10-13 ����9:17:57
 */
public class AreaOperationDB {
	private SQLiteDatabase db;
	Context context;
	String dbFile;
	String path;

	public AreaOperationDB(Context context) {
		this.context = context;
		path = AssetHelper.getPath(context, false) + "/" + context.getString(R.string.str_bj_db);
	}

	public List<AreaEntity> queryArea(String jlareaid, String sql) {
		List<AreaEntity> list = new ArrayList<AreaEntity>();
		Cursor cursor = null;
		db = SQLiteDatabase.openOrCreateDatabase(path, null);
		cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			AreaEntity area = new AreaEntity();
			area.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			area.setParentid(cursor.getString(cursor.getColumnIndex("parentid")));
			area.setName(cursor.getString(cursor.getColumnIndex("name")));
			area.setFullname(cursor.getString(cursor.getColumnIndex("fullname")));
			list.add(area);
		}
		cursor.close();
		db.close();
		return list;

	}

	public List<AreaEntity> queryAreaById(String sql) {
		List<AreaEntity> list = new ArrayList<AreaEntity>();
		Cursor cursor = null;
		db = SQLiteDatabase.openOrCreateDatabase(path, null);
		cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			AreaEntity area = new AreaEntity();
			area.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			area.setParentid(cursor.getString(cursor.getColumnIndex("parentid")));
			area.setName(cursor.getString(cursor.getColumnIndex("name")));
			area.setFullname(cursor.getString(cursor.getColumnIndex("fullName")));
			list.add(area);
		}
		db.close();
		return list;

	}
}
