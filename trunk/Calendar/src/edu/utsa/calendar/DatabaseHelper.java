package edu.utsa.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	
	private final static int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "CalenderDb";
    private static final String EVENT_TABLE_NAME = "Events";
    private static final String CATEGORY_TABLE_NAME = "Events";
    private static final String EVENTS_KEY_ID = "event_id";
    private static final String EVENTS_START_TIME = "start_time"; //TODO: what should be the data type for data ?
    private static final String EVENTS_END_TIME = "end_time";//TODO: what should be the data type for data ?
    private static final String EVENTS_DESCRIPTION = "details";//TODO: what should be the data type for data ?
 
//  "Category" Table Columns names
    private static final String CATEGORY_KEY_ID = "event_id";
    private static final String CATEGORY_COLOR = "start_time"; //TODO: what should be the data type for data ?
    private static final String CATEGORY_TYPE = "end_time";//TODO: what should be the data type for data ?
    private static final String CATEGORY_DESCRIPTION = "details";//TODO: what should be the data type for data ?

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    /**
     * Create Two Tables : Events and Categories 
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_EVENTS_TABLE = "CREATE TABLE " + EVENT_TABLE_NAME + "("
                + EVENTS_KEY_ID + " INTEGER PRIMARY KEY," + EVENTS_DESCRIPTION + " TEXT,"
                + EVENTS_START_TIME + " TEXT" + EVENTS_END_TIME + " TEXT"+ ")";
        db.execSQL(CREATE_EVENTS_TABLE);
        
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CATEGORY_TABLE_NAME + "("
                + CATEGORY_KEY_ID + " INTEGER PRIMARY KEY," + CATEGORY_COLOR + " TEXT,"
                + CATEGORY_TYPE + " TEXT" + CATEGORY_DESCRIPTION + " TEXT"+ ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
 
        // Create tables again
        onCreate(db);
		
	}
	
	public SQLiteDatabase getDatabaseForRead() {
		return this.getReadableDatabase();
	}
	
	public SQLiteDatabase getDatabaseForWrite() {
		return this.getWritableDatabase();
	}

	public int getDATABASE_VERSION() {
		return DATABASE_VERSION;
	}

	
	public static String getEventTableName() {
		return EVENT_TABLE_NAME;
	}

	public static String getCategoryTableName() {
		return CATEGORY_TABLE_NAME;
	}

	public static String getEventsKeyId() {
		return EVENTS_KEY_ID;
	}

	public static String getEventsStartTime() {
		return EVENTS_START_TIME;
	}

	public static String getEventsEndTime() {
		return EVENTS_END_TIME;
	}

	public static String getEventsDescription() {
		return EVENTS_DESCRIPTION;
	}

	public static String getCategoryKeyId() {
		return CATEGORY_KEY_ID;
	}

	public static String getCategoryColor() {
		return CATEGORY_COLOR;
	}

	public static String getCategoryType() {
		return CATEGORY_TYPE;
	}

	public static String getCategoryDescription() {
		return CATEGORY_DESCRIPTION;
	}
	
	
	
	

}
