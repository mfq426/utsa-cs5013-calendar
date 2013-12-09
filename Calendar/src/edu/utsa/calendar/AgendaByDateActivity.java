package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class AgendaByDateActivity extends CalendarActivity {
	
	private int fromYear;
	private int fromMonth;
	private int fromDay;
	private int fromHour;
	private int fromMinute;
	private int toYear;
	private int toMonth;
	private int toDay;
	private int toHour;
	private int toMinute;
	private Calendar from;
	private Calendar to;
	private GridView agendaGridView;
	private ArrayList<Event> events;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// default start and end date range
		from = Calendar.getInstance();
		to = Calendar.getInstance();
		to.setTime(from.getTime());
		to.add(Calendar.DATE, 10 );  // add 10 days with start date
		
		final String INCOMPLETE_USER_INPUT = getResources().getString(
				R.string.incomplte_input);
		events = new ArrayList<Event>();
		setContentView(R.layout.activity_agenda_by_date);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.agenda_date_select);
		RelativeLayout.LayoutParams create_params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams return_params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// add a create button to the relative layout dynamically
		Button create_button = new Button(this);
		create_button.setText(R.string.agenda_date);
		create_button.setOnClickListener(new View.OnClickListener() {

			@Override

			public void onClick(View v) {
				if (getData()) {
					if (verifyData()) {
						

						EventManager manager = Manager.getInstance()
								.getEventManager();




						from = Calendar.getInstance();
						from.set(fromYear, fromMonth, fromDay, fromHour,
								fromMinute, 0);

						from.clear(Calendar.MILLISECOND);

						to = Calendar.getInstance();
						to.set(toYear, toMonth, toDay, toHour, toMinute,
								0);

						to.clear(Calendar.MILLISECOND);

						events = (ArrayList<Event>)manager.readEvents(from, to);


					} else {
						popup(INCOMPLETE_USER_INPUT);
					}
				}

				
				onResume();						
			}
		});

		create_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		create_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layout.addView(create_button, create_params);

		// add a return button to the relative layout dynamically
		Button return_button = new Button(this);
		return_button.setText(R.string.cancel);
		return_button.setOnClickListener(new View.OnClickListener() {

			@Override
			// return to last activity
			public void onClick(View v) {
				finish();
			}
		});
		return_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		return_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		layout.addView(return_button, return_params);

		this.agendaGridView = (GridView) findViewById(R.id.gridViewAgendaByDate);

	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		populateModels();
//		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//		getActionBar().setSelectedNavigationItem(4);
	}
	
	public void populateModels() {
		if(events.size() > 1) {
			sortEvents(events);
		}	
		System.out.println("Events :: " + events.size());
		this.agendaGridView.setAdapter(new CustomAgendaGridAdaptor( AgendaByDateActivity.this, events));
		    
	}
	
	public void sortEvents( ArrayList<Event> events ) {
		Collections.sort(events, new Comparator<Event>(){
			  public int compare(Event e1, Event e2) {
			    return e1.getStartDate().compareTo(e2.getStartDate());
			  }
			});
		
		
	}



//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.agenda_by_date, menu);
//		return true;
//	}

	
	
	/**
	 * show date picker dialog to enable user to select event start date
	 * 
	 * @param view
	 *            event start date view
	 */
	public void showFromDatePickerDialog(View view) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.agenda_from_date);
		date.setArguments(args);
		date.show(getFragmentManager(), "fromDatePicker");
	}

	/**
	 * show time picker dialog to enable user to select event start time of day
	 * 
	 * @param view
	 *            event start time of day view
	 */
	public void showFromTimePickerDialog(View view) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.agenda_from_time);
		time.setArguments(args);
		time.show(getFragmentManager(), "fromTimePicker");
	}

	/**
	 * show date picker dialog to enable user to select event end date
	 * 
	 * @param view
	 *            event end date view
	 */
	public void showToDatePickerDialog(View view) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.agenda_to_date);
		date.setArguments(args);
		date.show(getFragmentManager(), "toDatePicker");
	}

	/**
	 * show time picker dialog to enable user to select event end time of day
	 * 
	 * @param view
	 *            event end time of day view
	 */
	public void showToTimePickerDialog(View view) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.agenda_to_time);
		time.setArguments(args);
		time.show(getFragmentManager(), "toTimePicker");
	}

	

	/**
	 * collect user input from all view components
	 * 
	 * @return true if all required fields are filled; otherwise false
	 */
	protected boolean getData() {
		TextView from = (TextView) findViewById(R.id.agenda_from_date);
		String tmp = from.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		String[] s = tmp.split("/");
		String[] time;
		fromYear = Integer.parseInt(s[2]);
		fromMonth = Integer.parseInt(s[0]) - 1;
		fromDay = Integer.parseInt(s[1]);

		TextView f_time = (TextView) findViewById(R.id.agenda_from_time);
		tmp = f_time.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split(" ");
		if (s[1].equals("AM")) {
			time = s[0].split(":");
			fromHour = Integer.parseInt(time[0]);
			fromMinute = Integer.parseInt(time[1]);
		} else {
			time = s[0].split(":");
			fromHour = Integer.parseInt(time[0]) + 12;
			fromMinute = Integer.parseInt(time[1]);
		}

		TextView to = (TextView) findViewById(R.id.agenda_to_date);
		tmp = to.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split("/");
		toYear = Integer.parseInt(s[2]);
		toMonth = Integer.parseInt(s[0]) - 1;
		toDay = Integer.parseInt(s[1]);

		TextView t_time = (TextView) findViewById(R.id.agenda_to_time);
		tmp = t_time.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split(" ");
		if (s[1].equals("AM")) {
			time = s[0].split(":");
			toHour = Integer.parseInt(time[0]);
			toMinute = Integer.parseInt(time[1]);
		} else {
			time = s[0].split(":");
			toHour = Integer.parseInt(time[0]) + 12;
			toMinute = Integer.parseInt(time[1]);
		}

		
		return true;
	}

	/**
	 * verify the user input data to make sure that event start time is earlier
	 * than end time and if it is weekly repeating event, the repeating times is
	 * positive value
	 * 
	 * @return true if all conditions meet; otherwise false
	 */
	protected boolean verifyData() {
		Calendar fromDate, toDate;

		fromDate = Calendar.getInstance();
		fromDate.set(fromYear, fromMonth, fromDay, fromHour, fromMinute, 0);

		toDate = Calendar.getInstance();
		toDate.set(toYear, toMonth, toDay, toHour, toMinute, 0);

		if (fromDate.after(toDate) || fromDate.equals(toDate))
			return false;

		

		return true;
	}

	/**
	 * give user feedback when something goes wrong
	 * 
	 * @param text
	 *            error clue
	 */
	protected void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
