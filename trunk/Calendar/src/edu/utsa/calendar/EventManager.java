package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EventManager{
	
	private DatabaseHelper storageHandler;
	
	
	public EventManager(DatabaseHelper storageHandler) {
		super();
		this.storageHandler = storageHandler;
	}
	
	
	public void createEvent( java.util.Calendar startDate, java.util.Calendar endDate, int mCategoryID,
			String mDescription ) {
		// Query for create event

		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		ContentValues values = new ContentValues();
		values.put(storageHandler.getEventsStartTime(), startDate.getTimeInMillis());
		values.put(storageHandler.getEventsEndTime(), endDate.getTimeInMillis());
		values.put(storageHandler.getEventsDescription(),mDescription);
				 
		// Inserting Row
		db.insert(storageHandler.getEventTableName(), null, values);
		db.close(); // Closing database connection
	}
	
	public List<Event> readEvents(java.util.Calendar sDate, java.util.Calendar eDate) {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsStartTime()+ " BETWEEN " +sDate.getTimeInMillis()+ " AND "+ eDate.getTimeInMillis();
 
        SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursor.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursor.getString(3)).longValue());
            	
            	System.out.println(cursor.getString(1));
                Event event = new Event( Integer.parseInt(cursor.getString(0)), startDate, endDate, 0,
            			cursor.getString(1));
            	// Adding contact to list
                eventList.add(event);
                System.out.println(event.toString());
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return eventList;
    }

}