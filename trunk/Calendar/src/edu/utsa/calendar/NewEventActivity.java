package edu.utsa.calendar;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class NewEventActivity extends Activity {

	private int callingActivity;
	public static final String CALLING_ACTIVITY = "calling_activity";
	public static final int DAILY_VIEW_ACTIVITY = 1;
	public static final int WEEKLY_VIEW_ACTIVITY = 2;
	public static final int MONTHLY_VIEW_ACTIVITY = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		callingActivity = intent.getIntExtra(CALLING_ACTIVITY, 1000);
		setContentView(R.layout.activity_new_event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_event, menu);
		return true;
	}

	public void showFromDatePickerDialog(View v) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.from_date);
		date.setArguments(args);
		date.show(getFragmentManager(), "fromDatePicker");
	}

	public void showFromTimePickerDialog(View v) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.from_time);
		time.setArguments(args);
		time.show(getFragmentManager(), "fromTimePicker");
	}

	public void showToDatePickerDialog(View v) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.to_date);
		date.setArguments(args);
		date.show(getFragmentManager(), "toDatePicker");
	}

	public void showToTimePickerDialog(View v) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.to_time);
		time.setArguments(args);
		time.show(getFragmentManager(), "toTimePicker");
	}

	public void newCategory(View v) {
		Intent intent = new Intent(this, NewCategoryActivity.class);
		startActivity(intent);
	}

	public void createEvent(View v) {
		// check the validity of user inputs
		
		// bogus code to enable other people to work on their own code
		int fromYear = 2013;
		int fromMonth = 10;
		int fromDate = 2;
		int fromHour = 10;
		int fromMinute = 0;
		
		int toYear = 2013;
		int toMonth = 10;
		int toDate = 2;
		int toHour = 10;
		int toMinute = 30;
		
		boolean weekly_repeating = true;
		int occurance = 10;
		int category = 1;
		String description = "Meeting";
		
		int[] from = new int[5];
		int[] to = new int[5];
		from[0] = fromYear;
		from[1] = fromMonth;
		from[2] = fromDate;
		from[3] = fromHour;
		from[4] = fromMinute;
		to[0] = toYear;
		to[1] = toMonth;
		to[2] = toDate;
		to[3] = toHour;
		to[4] = toMinute;

		
		//creating some database entry manually
		Calendar startDate, endDate;
		
		// Entry 1
		startDate = Calendar.getInstance();
		startDate.set(2013, 11, 8, 11, 20, 0);
		
		endDate = Calendar.getInstance();
		startDate.set(2013, 11, 8, 12, 20, 0);
		
    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "walking with friends");
		
    	// Entry 2
    	startDate = Calendar.getInstance();
    	startDate.set(2013, 10, 8, 1, 20, 0);
    	
    	endDate = Calendar.getInstance();
    	startDate.set(2013, 10, 8, 1, 40, 0);
    			
    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "meeting with prof");
    	
    	// Entry 2
    	startDate = Calendar.getInstance();
    	startDate.set(2013, 10, 7, 1, 20, 0);
    			
    	endDate = Calendar.getInstance();
    	startDate.set(2013, 10, 7, 1, 30, 0);
    			
    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "sleeping");
    	    	
    	    	
		// pass user inputs though intent to the calling activity
		Intent intent;
		switch (callingActivity) {
			case DAILY_VIEW_ACTIVITY:
				intent = new Intent(this, DailyViewActivity.class);
				startActivity(intent);
				break;
			case WEEKLY_VIEW_ACTIVITY:
				intent = new Intent(this, WeeklyViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //this is needed to start an activity, otherwise oncreate method is called
				startActivity(intent);
				break; //Lu I(jamil) added this
			case MONTHLY_VIEW_ACTIVITY:
				intent = new Intent(this, MonthlyViewActivity.class);
				startActivity(intent);
				break; //Lu I(jamil) added this
			default:
				System.out.println("The activity invoke new event is not legitmate.");
				break; //Lu I(jamil) replaced this, before here was a return, which returns it without starting the activity
						// also I need to add the startActivity(intent) to each 'case clause' because putting it at the end generates an error 
						// which say intent may not be initialized
		}
		
		//intent.putExtra("from", from);
		//intent.putExtra("to", to);
		//intent.putExtra("weekly_repeating", weekly_repeating);
		//intent.putExtra("occurance", occurance);
		//intent.putExtra("category", category);
		//intent.putExtra("description", description);
		
		
	}
}
