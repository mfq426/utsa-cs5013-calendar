package edu.utsa.calendar;

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

		// pass user inputs though intent to the calling activity
		Intent intent;
		switch (callingActivity) {
			case DAILY_VIEW_ACTIVITY:
				intent = new Intent(this, DailyViewActivity.class);
				break;
			case WEEKLY_VIEW_ACTIVITY:
				intent = new Intent(this, WeeklyViewActivity.class);
			case MONTHLY_VIEW_ACTIVITY:
				intent = new Intent(this, MonthlyViewActivity.class);
			default:
				System.out.println("The activity invoke new event is not legitmate.");
				return;
		}
		
		intent.putExtra("from", from);
		intent.putExtra("to", to);
		intent.putExtra("weekly_repeating", weekly_repeating);
		intent.putExtra("occurance", occurance);
		intent.putExtra("category", category);
		intent.putExtra("description", description);
		
		startActivity(intent);
	}
}
