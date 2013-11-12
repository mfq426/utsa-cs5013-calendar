package edu.utsa.calendar.test;

import java.util.Calendar;
import java.util.List;

import edu.utsa.calendar.Category;
import edu.utsa.calendar.CategoryManager;
import edu.utsa.calendar.DatabaseHelper;
import edu.utsa.calendar.Event;
import edu.utsa.calendar.EventManager;
import android.test.AndroidTestCase;

public class EventManagerTest extends AndroidTestCase {
	private DatabaseHelper mDatabaseHelper = null;
	private CategoryManager mCategoryManager = null;
	private EventManager mEventManager = null;
	@Override
	public void setUp() throws Exception {
		super.setUp();
		mDatabaseHelper = new DatabaseHelper(getContext(),"CalendarDBTest");
		mCategoryManager = new CategoryManager(mDatabaseHelper);
		mEventManager = new EventManager(mDatabaseHelper, mCategoryManager);
		
	}

	@Override
	protected void tearDown() throws Exception {
		mDatabaseHelper.close();

		super.tearDown();
	}

	public void testCreateEvent() throws Exception {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 30);
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 50);
		Event event = new Event(startDate,endDate,"12"," Class", 1,1);
		mEventManager.createEvent(event);
		List<Event> readEvents = mEventManager.readEvents(startDate, endDate);
		
		assertEquals(1,readEvents.size());
		assertEquals("Class",readEvents.get(0).getDescription());
	}
	
	public void testDeleteEvent() throws Exception {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 30);
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 50);
		
		mEventManager.deleteEvent(startDate, endDate);
		List<Event> readEvents = mEventManager.readEvents(startDate, endDate);
		
		assertEquals(0,readEvents.size());
		
	}
	
	public void testGetEventById() throws Exception {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 30);
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 50);
		Event event = new Event(startDate,endDate,"12"," Class", 1,1);
		mEventManager.createEvent(event);
		List<Event> readEvents = mEventManager.readEvents(startDate, endDate);
		id = readEvents.get(1).getID();
		Event event = mEventManager.getEventById(id);
		assertEquals(id, event.getID());
		
	}
	
	public void testReadEvent() throws Exception {
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 30);
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 50);
		Event event = new Event(startDate,endDate,"12"," Class", 1,1);
		mEventManager.createEvent(event);
		
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 10);
		
		endDate.set(Calendar.HOUR_OF_DAY, 8);
		endDate.set(Calendar.MINUTE, 50);
		event = new Event(startDate,endDate,"10"," Meeting ", 1,1);
		mEventManager.createEvent(event);
		
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 30);
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 50);
		
		List<Event> events = mEventManager.readEvents(startDate, endDate);
		
		assertEquals(2, events.size());
		
	}
	
	


	
}
