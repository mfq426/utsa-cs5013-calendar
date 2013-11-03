package edu.utsa.calendar;

import java.io.Serializable;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class EventManager implements Serializable{
	
	private DatabaseHelper storageHandler;
	private static final long serialVersionUID = 0L;
	
	public EventManager(DatabaseHelper storageHandler) {
		super();
		this.storageHandler = storageHandler;
	}
	
	
	public void createEvent( MyDate mStartDate, MyDate mEndDate, int mCategoryID,
			String mDescription ) {
		// Query for create event

		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		ContentValues values = new ContentValues();
		values.put(storageHandler.getEventsStartTimeYear(), mStartDate.getYear());
		values.put(storageHandler.getEventsStartTimeMonth(), mStartDate.getMonth());
		values.put(storageHandler.getEventsStartTimeDay(), mStartDate.getDay());
		values.put(storageHandler.getEventsStartTimeHour(), mStartDate.getHour());
		values.put(storageHandler.getEventsStartTimeMinute(), mStartDate.getMinute());
		values.put(storageHandler.getEventsStartTimeSecond(), mStartDate.getSecond());
		values.put(storageHandler.getEventsEndTimeYear(), mEndDate.getYear());
		values.put(storageHandler.getEventsEndTimeMonth(), mEndDate.getMonth());
		values.put(storageHandler.getEventsEndTimeDay(), mEndDate.getDay());
		values.put(storageHandler.getEventsEndTimeHour(), mEndDate.getHour());
		values.put(storageHandler.getEventsEndTimeMinute(), mEndDate.getMinute());
		values.put(storageHandler.getEventsEndTimeSecond(), mEndDate.getSecond());
		values.put(storageHandler.getEventsDescription(),mDescription);
		

		 
		// Inserting Row
		db.insert(storageHandler.getEventTableName(), null, values);
		db.close(); // Closing database connection
	}

}