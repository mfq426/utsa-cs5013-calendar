package edu.utsa.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	
	private final static int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "CalenderDb";
    private static final String EVENT_TABLE_NAME = "Event";
    private static final String CATEGORY_TABLE_NAME = "Category";
    private static final String EVENTS_KEY_ID = "event_id";
    private static final String EVENTS_START_TIME_YEAR = "start_time_year"; 
    private static final String EVENTS_START_TIME_MONTH = "start_time_month";
    private static final String EVENTS_START_TIME_DAY = "start_time_day";
    private static final String EVENTS_START_TIME_HOUR = "start_time_hour";
    private static final String EVENTS_START_TIME_MINUTE = "start_time_minute";
    private static final String EVENTS_START_TIME_SECOND = "start_time_second";
    private static final String EVENTS_END_TIME_YEAR = "end_time_year"; 
    private static final String EVENTS_END_TIME_MONTH = "end_time_month";
    private static final String EVENTS_END_TIME_DAY = "end_time_day";
    private static final String EVENTS_END_TIME_HOUR = "end_time_hour";
    private static final String EVENTS_END_TIME_MINUTE = "end_time_minute";
    private static final String EVENTS_END_TIME_SECOND = "end_time_second";
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
		String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENT_TABLE_NAME + "("
                + EVENTS_KEY_ID + " INTEGER PRIMARY KEY," 
				+ EVENTS_DESCRIPTION + " TEXT,"
                + EVENTS_START_TIME_YEAR +" INTEGER,"
                + EVENTS_START_TIME_MONTH +" INTEGER,"
                + EVENTS_START_TIME_DAY +" INTEGER,"
                + EVENTS_START_TIME_HOUR +" INTEGER,"
                + EVENTS_START_TIME_MINUTE +" INTEGER,"
                + EVENTS_START_TIME_SECOND +" INTEGER,"
                + EVENTS_END_TIME_YEAR +" INTEGER,"
                + EVENTS_END_TIME_MONTH +" INTEGER,"
                + EVENTS_END_TIME_DAY +" INTEGER,"
                + EVENTS_END_TIME_HOUR +" INTEGER,"
                + EVENTS_END_TIME_MINUTE +" INTEGER,"
                + EVENTS_END_TIME_SECOND +" INTEGER"
                +  ")";
        db.execSQL(CREATE_EVENTS_TABLE);
       
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CATEGORY_TABLE_NAME + "("
                + CATEGORY_KEY_ID + " INTEGER PRIMARY KEY," + CATEGORY_COLOR + " TEXT,"
                + CATEGORY_TYPE + " TEXT," + CATEGORY_DESCRIPTION + " TEXT"+ ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
		
	}

	public static String getEventsStartTimeYear() {
		return EVENTS_START_TIME_YEAR;
	}

	public static String getEventsStartTimeMonth() {
		return EVENTS_START_TIME_MONTH;
	}

	public static String getEventsStartTimeDay() {
		return EVENTS_START_TIME_DAY;
	}

	public static String getEventsStartTimeHour() {
		return EVENTS_START_TIME_HOUR;
	}

	public static String getEventsStartTimeMinute() {
		return EVENTS_START_TIME_MINUTE;
	}

	public static String getEventsStartTimeSecond() {
		return EVENTS_START_TIME_SECOND;
	}

	public static String getEventsEndTimeYear() {
		return EVENTS_END_TIME_YEAR;
	}

	public static String getEventsEndTimeMonth() {
		return EVENTS_END_TIME_MONTH;
	}

	public static String getEventsEndTimeDay() {
		return EVENTS_END_TIME_DAY;
	}

	public static String getEventsEndTimeHour() {
		return EVENTS_END_TIME_HOUR;
	}

	public static String getEventsEndTimeMinute() {
		return EVENTS_END_TIME_MINUTE;
	}

	public static String getEventsEndTimeSecond() {
		return EVENTS_END_TIME_SECOND;
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
