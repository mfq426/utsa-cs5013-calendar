package com.utsa.cs5013.database;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_EVENT_NAME = "Event";
	public static final String TABLE_EVENT_FILE = "table_event.sqlite";
	public static final String TABLE_CATEGORY_NAME = "Category";
	public static final String TABLE_CATEGORY_FILE = "table_category.sqlite";
	private static final String DATABASE_NAME = "calendar.db";
	private static final int DATABASE_VERSION = 1;
	private Context mContext = null;
	private String currentTable;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(createTableQuery(mContext, TABLE_EVENT_FILE));
		database.execSQL(createTableQuery(mContext, TABLE_CATEGORY_FILE));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_NAME);
		onCreate(db);
	}

	private String createTableQuery(Context context, String fileName) {
		AssetManager assets = context.getAssets();
		StringBuffer buffer = new StringBuffer();
		try {
			InputStream reader = assets.open(fileName);

			int data = reader.read();
			while (data != -1) {
				char theChar = (char) data;
				buffer.append(theChar);
				data = reader.read();
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}
}
