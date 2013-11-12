package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EventManager{
	
	private DatabaseHelper storageHandler;
	private CategoryManager categoryManager;
	
	public EventManager(DatabaseHelper storageHandler, CategoryManager categoryManager) {
		super();
		this.storageHandler = storageHandler;
		this.categoryManager = categoryManager;
	}
	
	
	public void createEvent( Event event ) {
		// Query for create event

		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		ContentValues values = new ContentValues();
		values.put(storageHandler.getEventsStartTime(), event.getStartDate().getTimeInMillis());
		values.put(storageHandler.getEventsEndTime(), event.getEndDate().getTimeInMillis());
		values.put(storageHandler.getEventsDescription(),event.getDescription());
		values.put(storageHandler.getEVENTS_CATEGORY(), event.getCategoryID());
		values.put(storageHandler.getEVENTS_TOTAL_OCCURANCE(), event.getTotalOccurance());
		values.put(storageHandler.getEVENTS_OCCURANCE_INDEX(), event.getOccuranceIndex());
				 
		// Inserting Row
		db.insert(storageHandler.getEventTableName(), null, values);
		db.close(); // Closing database connection
	}
	
	public List<Event> readEvents(java.util.Calendar sDate, java.util.Calendar eDate) {
        List<Event> eventList = new ArrayList<Event>();
        
        
        // Read all entry for category table
        List<Category> categoryList = categoryManager.readAllCategory();        
        
        // Select all events with start time between sDate and eDate 
        String selectQueryStartTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsStartTime()+ " >= " +sDate.getTimeInMillis()+ " AND "+  storageHandler.getEventsEndTime() + " <= " + eDate.getTimeInMillis();
 
        SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursorStartTime = db.rawQuery(selectQueryStartTime, null);
 
        // looping through all rows and adding to list
        if (cursorStartTime.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursorStartTime.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	endDate.setTimeInMillis(Long.valueOf(cursorStartTime.getString(3)).longValue());
            	            	
                Event event = new Event( Integer.parseInt(cursorStartTime.getString(0)), startDate, endDate, cursorStartTime.getString(4),cursorStartTime.getString(1),Integer.parseInt(cursorStartTime.getString(5)),Integer.parseInt(cursorStartTime.getString(6)));
            	
                // Set the category color
                for (Category ct : categoryList) {
                    
        	    	if(ct.getName().equals(event.getCategoryID())){
        	    		event.setColor(ct.getColor());
        	    	}
        	    		    	
        	    }
                
            	// Adding contact to list
                eventList.add(event);
                //System.out.println(event.toString());
            } while (cursorStartTime.moveToNext());
        }
 
        
        // Select all events with end time between sDate and eDate 
        String selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsStartTime()+ " < " +sDate.getTimeInMillis()+ " AND "+  storageHandler.getEventsEndTime() + " <= " + eDate.getTimeInMillis() + " AND " + storageHandler.getEventsEndTime() + " >= " + sDate.getTimeInMillis();
        //String selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsEndTime()+ " < " +sDate.getTimeInMillis()+ " AND "+ eDate.getTimeInMillis();
 
        Cursor cursorEndTime = db.rawQuery(selectQueryEndTime, null);
 
        // looping through all rows and adding to list
        if (cursorEndTime.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	endDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(3)).longValue());
            	            	
                Event event = new Event( Integer.parseInt(cursorEndTime.getString(0)), startDate, endDate, cursorEndTime.getString(4),cursorEndTime.getString(1),Integer.parseInt(cursorEndTime.getString(5)),Integer.parseInt(cursorEndTime.getString(6)));
            	
                // Set the category color
                for (Category ct : categoryList) {
                    
        	    	if(ct.getName().equals(event.getCategoryID())){
        	    		event.setColor(ct.getColor());
        	    	}
        	    		    	
        	    }
                
            	// Adding contact to list
                eventList.add(event);
                //System.out.println(event.toString());
            } while (cursorEndTime.moveToNext());
        }
        
        selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsStartTime()+ " > " + sDate.getTimeInMillis()+ " AND "+  storageHandler.getEventsStartTime() + " <= " + eDate.getTimeInMillis() + " AND " + storageHandler.getEventsEndTime() + " > " + eDate.getTimeInMillis();
        //String selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsEndTime()+ " < " +sDate.getTimeInMillis()+ " AND "+ eDate.getTimeInMillis();
 
        cursorEndTime = db.rawQuery(selectQueryEndTime, null);
 
        // looping through all rows and adding to list
        if (cursorEndTime.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	endDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(3)).longValue());
            	            	
                Event event = new Event( Integer.parseInt(cursorEndTime.getString(0)), startDate, endDate, cursorEndTime.getString(4),cursorEndTime.getString(1),Integer.parseInt(cursorEndTime.getString(5)),Integer.parseInt(cursorEndTime.getString(6)));
            	
                // Set the category color
                for (Category ct : categoryList) {
                	
                    
        	    	if(ct.getName().equals(event.getCategoryID())){
        	    		event.setColor(ct.getColor());
        	    	}
        	    		    	
        	    }
                
            	// Adding contact to list
                eventList.add(event);
                //System.out.println(event.toString());
            } while (cursorEndTime.moveToNext());
        }
        
        selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsStartTime()+ " < " + sDate.getTimeInMillis()+" AND " + storageHandler.getEventsEndTime() + " > " + eDate.getTimeInMillis();
        //String selectQueryEndTime = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsEndTime()+ " < " +sDate.getTimeInMillis()+ " AND "+ eDate.getTimeInMillis();
 
        cursorEndTime = db.rawQuery(selectQueryEndTime, null);
 
        // looping through all rows and adding to list
        if (cursorEndTime.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	endDate.setTimeInMillis(Long.valueOf(cursorEndTime.getString(3)).longValue());
            	            	
                Event event = new Event( Integer.parseInt(cursorEndTime.getString(0)), startDate, endDate, cursorEndTime.getString(4),cursorEndTime.getString(1),Integer.parseInt(cursorEndTime.getString(5)),Integer.parseInt(cursorEndTime.getString(6)));
            	
                // Set the category color
                for (Category ct : categoryList) {
                	
                    
        	    	if(ct.getName().equals(event.getCategoryID())){
        	    		event.setColor(ct.getColor());
        	    	}
        	    		    	
        	    }
                
            	// Adding contact to list
                eventList.add(event);
                //System.out.println(event.toString());
            } while (cursorEndTime.moveToNext());
        }
        
        // return contact list
        return eventList;
    }

	public List<Event> getConflictedEvents(java.util.Calendar sDate, java.util.Calendar eDate) {
      
        
        // return contact list
        return readEvents(sDate, eDate);
    }
	
	public void deleteEvent(Calendar startDate, Calendar endDate){
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		// Deleting events
		db.delete(storageHandler.getEventTableName(), 
	            storageHandler.getEventsStartTime() + "= ? AND "+ storageHandler.getEventsEndTime()+ " = ?", new String[] { String.valueOf(startDate.getTimeInMillis()),String.valueOf(endDate.getTimeInMillis())});
		db.close();
				
	}
	
	/**
	 * Updates event with Id
	 * @param eventId
	 * @param event
	 */
	public void updateEvent( int eventId, Event event ) {
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		
		String strFilter = "_id=" + eventId;
		ContentValues args = new ContentValues();
		args.put(storageHandler.getEventsDescription(), event.getDescription());
		db.update(storageHandler.getEventTableName(), args, strFilter, null);
		
		db.close();
		
		
	}
	
	/**
	 * 
	 * @param eventId
	 * @return event object if there is a event with the eventId otherwise returns null
	 */
	
	public Event getEventById( int eventId ) {
		

        String selectQuery = "SELECT  * FROM " + storageHandler.getEventTableName()+ " WHERE " +storageHandler.getEventsKeyId() + " = " + eventId;
        
        SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
     // Read all entry for category table
        List<Category> categoryList = categoryManager.readAllCategory();
        Event event = null;
        if (cursor.moveToFirst()) {
            do {
                //Event contact = new Event();
            	
            	Calendar startDate = Calendar.getInstance();
            	startDate.setTimeInMillis(Long.valueOf(cursor.getString(2)).longValue());
            	Calendar endDate = Calendar.getInstance();
            	endDate.setTimeInMillis(Long.valueOf(cursor.getString(3)).longValue());
            	            	
                event = new Event( Integer.parseInt(cursor.getString(0)), startDate, endDate, cursor.getString(4),cursor.getString(1),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)));
            	
                // Set the category color
                for (Category ct : categoryList) {
                    
        	    	if(ct.getName().equals(event.getCategoryID())){
        	    		event.setColor(ct.getColor());
        	    	}
        	    		    	
        	    }
                
            	//System.out.println(event.toString());
            } while (cursor.moveToNext());
        }
        
        
        return event;
		
		
	}
	
	
	
}