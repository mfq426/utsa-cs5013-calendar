package com.utsa.cs5013.handler;

import com.utsa.cs5013.database.DatabaseHelper;
import com.utsa.cs5013.util.Query;
import com.utsa.cs5013.util.QueryUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StorageHandler {

	private SQLiteDatabase mDatabaseReader;
	private SQLiteDatabase mDatabaseWriter;
	private DatabaseHelper mDBHelper;

	public StorageHandler(Context context) {
		mDBHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		mDatabaseWriter = mDBHelper.getWritableDatabase();
	}

	public void close() {
		mDBHelper.close();
	}

	public void save(Object o) {

	}

	public void update(Object o) {

	}

	public void delete(Object o) {

		mDatabaseWriter.beginTransaction();
		Query query = QueryUtil.getDeleteQuery(o);
		mDatabaseWriter.delete(query.getTableName(), query.getWhereClause(), query.getWhereArgs());
		mDatabaseWriter.endTransaction();

	}
}
