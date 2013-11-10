package edu.utsa.calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
	
	public static final int DAILY_VIEW_MODE = 0;
	public static final int WEEKLY_VIEW_MODE = 1;
	public static final int MONTHLY_VIEW_MODE = 2;
	public static final int AGENDA_VIEW_MODE = 3;
	
	public static final int NEW_EVENT_VIEW = 100;
	
	private SharedPreferences mPrefs;
	private int mCurViewMode;
	private CategoryManager categoryManager;
	private EventManager eventManager;
	private DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		// First create the database
		databaseHelper = new DatabaseHelper(this);
		
		// Next create CategoryManager and EventManager
		categoryManager = new CategoryManager(databaseHelper);
		eventManager = new EventManager(databaseHelper,categoryManager);
		
		// Set into the global variables
		((GlobalVariables) this.getApplication()).setEventManager(eventManager);
		((GlobalVariables) this.getApplication()).setCategoryManager(categoryManager);
		
		
		// go to the last view user stayed
		mPrefs = getSharedPreferences("view", 0);
		mCurViewMode = mPrefs.getInt("view_mode", MONTHLY_VIEW_MODE);
		
		Intent firstView;
		switch(mCurViewMode) {
			case DAILY_VIEW_MODE:
				firstView = new Intent(this, DailyViewActivity.class);
				break;
			case WEEKLY_VIEW_MODE:
				firstView = new Intent(this, WeeklyViewActivity.class);
				break;
			case MONTHLY_VIEW_MODE:
				firstView = new Intent(this, MonthlyViewActivity.class);
				break;
			case AGENDA_VIEW_MODE:
				firstView = new Intent(this, AgendaViewActivity.class);
				break;
			case NEW_EVENT_VIEW:
				firstView = new Intent(this, NewEventActivity.class);
				break;
			default:
				System.out.println("Error");
				return;
		
		}
		
			
		startActivity(firstView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
