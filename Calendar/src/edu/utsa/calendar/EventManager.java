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
	
	
	public void createEvent( Event event ) {
		// Query for create event

		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		ContentValues values = new ContentValues();
		values.put(storageHandler.getEventsStartTime(), event.getmStartDate().getTimeInMillis());
		values.put(storageHandler.getEventsEndTime(), event.getmEndDate().getTimeInMillis());
		values.put(storageHandler.getEventsDescription(),event.getmDescription());
		values.put(storageHandler.getEVENTS_CATEGORY(), event.getmCategoryID());
		values.put(storageHandler.getEVENTS_TOTAL_OCCURANCE(), event.getTotalOccurance());
		values.put(storageHandler.getEVENTS_OCCURANCE_INDEX(), event.getOccuranceIndex());
				 
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
            	            	
                Event event = new Event( Integer.parseInt(cursor.getString(0)), startDate, endDate, cursor.getString(4),cursor.getString(1),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)));
            	                               
            	// Adding contact to list
                eventList.add(event);
                System.out.println(event.toString());
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return eventList;
    }

	public List<Event> getConflictedEvents(java.util.Calendar sDate, java.util.Calendar eDate) {
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
            	 Event event = new Event( Integer.parseInt(cursor.getString(0)), startDate, endDate, cursor.getString(4),cursor.getString(1),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)));
             	
            	// Adding contact to list
                eventList.add(event);
                System.out.println(event.toString());
            } while (cursor.moveToNext());
        }
 
        
     // Select All Query
        selectQuery = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsEndTime()+ " BETWEEN " +sDate.getTimeInMillis()+ " AND "+ eDate.getTimeInMillis();
 
       cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursor.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursor.getString(3)).longValue());
            	
            	System.out.println(cursor.getString(1));
            	 Event event = new Event( Integer.parseInt(cursor.getString(0)), startDate, endDate, cursor.getString(4),cursor.getString(1),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)));
             	
            	// Adding contact to list
                eventList.add(event);
                System.out.println(event.toString());
            } while (cursor.moveToNext());
        }
 
        
        // *** need to implement the event conflict if it is within the events
        
        // return contact list
        return eventList;
    }
	
	public void deleteEvent(Calendar startDate, Calendar endDate){
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		// Deleting events
		db.delete(storageHandler.getEventTableName(), 
	            storageHandler.getEventsStartTime() + "= ? AND"+ storageHandler.getEventsEndTime()+ " = ?", new String[] { String.valueOf(startDate.getTimeInMillis()),String.valueOf(endDate.getTimeInMillis())});
		db.close();
		
		
		
	}
}