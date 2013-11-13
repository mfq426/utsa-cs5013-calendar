package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ModifyEventActivity provides the user the capability to update and remove the existing events
 * @author Lu Liu
 */
public class ModifyEventActivity extends Activity implements OnItemSelectedListener{
	
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
	private String description;
	private boolean checked=false;
	private int occurance;
	private String categoryName;
	private final static String DEFAULT = "default";
	private final static String EVENT_TIME_CONFLICT = "event time conflict";
	private final static String INVALID_USER_INPUT = "invalid user input";
	private final static String INCOMPLETE_USER_INPUT = "incomplete user input";
	
	// if the event is a weekly periodical event, we created it as multiple events for consecutive weeks
	private Calendar[] oldfrom; // events start time
	private Calendar[] oldto; // events end time
	private int index; // the position of the event picked by user among the serial of events
	private int total; // how many weeks the weekly periodical event lasts
	private EventManager manager;
	
	// set attributes with event information
	private void setData(Event event) {
		fromYear = event.getStartDate().get(Calendar.YEAR);
		fromMonth = event.getStartDate().get(Calendar.MONTH) + 1;
		fromDay = event.getStartDate().get(Calendar.DAY_OF_MONTH);
		fromHour = event.getStartDate().get(Calendar.HOUR_OF_DAY);
		fromMinute = event.getStartDate().get(Calendar.MINUTE);
		
		toYear = event.getEndDate().get(Calendar.YEAR);
		toMonth = event.getEndDate().get(Calendar.MONTH) + 1;
		toDay = event.getEndDate().get(Calendar.DAY_OF_MONTH);
		toHour = event.getEndDate().get(Calendar.HOUR_OF_DAY);
		toMinute = event.getEndDate().get(Calendar.MINUTE);
		
		description = event.getDescription();
		occurance = event.getTotalOccurance();
		total = occurance;
		if(occurance > 1) {
			checked = true;
		}
		index = event.getOccuranceIndex();
		categoryName = event.getCategoryID();
		
		oldfrom = new Calendar[total];
		oldto = new Calendar[total];
		
		oldfrom[index-1] = event.getStartDate();
		oldto[index-1] = event.getEndDate();
		
		for(int i=index-2; i>=0; i--) {
			oldfrom[i] = Calendar.getInstance();
			oldfrom[i].set(fromYear, fromMonth-1, fromDay, fromHour, fromMinute, 0);
			oldfrom[i].add(Calendar.DAY_OF_MONTH, -7*(index-1-i));
			oldto[i] = Calendar.getInstance();
			oldto[i].set(toYear, toMonth-1, toDay, toHour, toMinute, 0);
			oldto[i].add(Calendar.DAY_OF_MONTH, -7*(index-1-i));
		}
		
		for(int i=index; i<=total-1; i++) {
			oldfrom[i] = Calendar.getInstance();
			oldfrom[i].set(fromYear, fromMonth-1, fromDay, fromHour, fromMinute, 0);
			oldfrom[i].add(Calendar.DAY_OF_MONTH, 7*(i-(index-1)));
			oldto[i] = Calendar.getInstance();
			oldto[i].set(toYear, toMonth-1, toDay, toHour, toMinute, 0);
			oldto[i].add(Calendar.DAY_OF_MONTH, 7*(i-(index-1)));
		}		
	}
	
	// construct user friendly time display, like 4:04 AM
	private String constructTime(int hourOfDay, int minute) {
		String suffix;
		if (hourOfDay < 12) {
			suffix = " AM";
			if (minute < 10)
				return String.valueOf(hourOfDay) + ":" + 0 + String.valueOf(minute) + suffix;
			else
				return String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + suffix;
		}
		else {
			suffix = " PM";
			if (minute < 10)
				return String.valueOf(hourOfDay-12) + ":" + 0 + String.valueOf(minute) + suffix;
			else
				return String.valueOf(hourOfDay-12) + ":" + String.valueOf(minute) + suffix;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_event);
		
		manager = Manager.getInstance().getEventManager();
		
		Intent intent = getIntent();
		int event_id = intent.getIntExtra("event_id", -100);
		Event e = manager.getEventById(event_id);
		
		setData(e);
		// dynamically set the views
		String s;
		TextView from_date = (TextView)findViewById(R.id.from_date_m);
		from_date.setText(fromMonth + "/" + fromDay + "/" + fromYear);
		
		TextView from_time = (TextView)findViewById(R.id.from_time_m);
		s = constructTime(fromHour, fromMinute);
		from_time.setText(s);
		
		TextView to_date = (TextView)findViewById(R.id.to_date_m);
		to_date.setText(toMonth + "/" + toDay + "/" + toYear);
		
		TextView to_time = (TextView)findViewById(R.id.to_time_m);
		s = constructTime(toHour, toMinute);
		to_time.setText(s);
		
		EditText what = (EditText)findViewById(R.id.what_m);
		what.setText(description);
		
		CheckBox checkbox = (CheckBox)findViewById(R.id.periodical_m);
		if(checked) {
			checkbox.setPressed(true);
		}
		
		TextView times = (TextView)findViewById(R.id.times_m);
		times.setText(String.valueOf(occurance));
		// construct spinner item array by getting all categories from database
		Spinner spinner = (Spinner)findViewById(R.id.category_spinner_m);
		CategoryManager category_manager = Manager.getInstance().getCategoryManager();
		ArrayList<Category> list = category_manager.readAllCategory();
		Iterator<Category> itr = list.iterator();
		ArrayList<String> options = new ArrayList<String>();
		Category c;
		int pos=-1;
		int i=0;
		while(itr.hasNext()) {
			c = itr.next();
			s = c.getName();
			// exclude from showing the default category
			if(!(s.equalsIgnoreCase(DEFAULT))) {
				options.add(s);
				i++;
				if(s.equals(categoryName)) {
					pos = i;
				}
			}
		}
		// add spinner item array to spinner dynamically
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		if(i>0&&pos!=-1) {
			spinner.setSelection(pos);
			adapter.notifyDataSetChanged();
		}
		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_event, menu);
		return true;
	}
	
	/**
	 * show date picker dialog to enable user to select event start date
	 * @param event start date view 
	 */
	public void showFromDatePickerDialog(View view) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.from_date_m);
		date.setArguments(args);
		date.show(getFragmentManager(), "fromDatePicker");
	}

	/**
	 * show time picker dialog to enable user to select event start time of day
	 * @param event start time of day view
	 */
	public void showFromTimePickerDialog(View view) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.from_time_m);
		time.setArguments(args);
		time.show(getFragmentManager(), "fromTimePicker");
	}

	/**
	 * show date picker dialog to enable user to select event end date
	 * @param event end date view
	 */
	public void showToDatePickerDialog(View view) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.to_date_m);
		date.setArguments(args);
		date.show(getFragmentManager(), "toDatePicker");
	}

	/**
	 * show time picker dialog to enable user to select event end time of day
	 * @param event end time of day view
	 */
	public void showToTimePickerDialog(View view) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.to_time_m);
		time.setArguments(args);
		time.show(getFragmentManager(), "toTimePicker");
	}
	
	/**
	 * handle the event when the checkbox is checked
	 * @param weekly repeating checkbox
	 */
	public void onCheckboxClicked(View view) {
		checked = ((CheckBox) view).isChecked();
	}
	
	// collect user input from all view components
	private boolean getData() {
		TextView from = (TextView)findViewById(R.id.from_date_m);
		String tmp = from.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		String[] s = tmp.split("/");
		String[] time;
		fromYear = Integer.parseInt(s[2]);
		fromMonth = Integer.parseInt(s[0]) - 1;
		fromDay = Integer.parseInt(s[1]);
		
		TextView f_time = (TextView)findViewById(R.id.from_time_m);
		tmp = f_time.getText().toString();
		if(tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split(" ");
		if(s[1].equals("AM")) {
			time = s[0].split(":");
			fromHour = Integer.parseInt(time[0]);
			fromMinute = Integer.parseInt(time[1]);
		} else {
			time =s[0].split(":");
			fromHour = Integer.parseInt(time[0]) + 12;
			fromMinute = Integer.parseInt(time[1]);
		}
		
		TextView to = (TextView)findViewById(R.id.to_date_m);
		tmp = to.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split("/");
		toYear = Integer.parseInt(s[2]);
		toMonth = Integer.parseInt(s[0]) - 1;
		toDay = Integer.parseInt(s[1]);
		
		TextView t_time = (TextView)findViewById(R.id.to_time_m);
		tmp = t_time.getText().toString();
		if(tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split(" ");
		if(s[1].equals("AM")) {
			time = s[0].split(":");
			toHour = Integer.parseInt(time[0]);
			toMinute = Integer.parseInt(time[1]);
		} else {
			time =s[0].split(":");
			toHour = Integer.parseInt(time[0]) + 12;
			toMinute = Integer.parseInt(time[1]);
		}
		
		EditText editText = (EditText)findViewById(R.id.what_m);
		tmp = editText.getText().toString();
		if(tmp == null || tmp.isEmpty())
			return false;
		description = tmp;
		
		if(checked) {
			editText = (EditText)findViewById(R.id.times_m);
			tmp = editText.getText().toString();
			if(tmp == null || tmp.isEmpty())
				return false;
			try {
				occurance = Integer.parseInt(tmp);
			} catch(NumberFormatException e) {
				return false;
			}
		}
		
		return true;
	}
	
	// verify the user input data to make sure that event start time is earlier than end time 
	// and if it is weekly repeating event, the repeating times is positive value
	private boolean verifyData() {
		Calendar fromDate, toDate;
		
		fromDate = Calendar.getInstance();
		fromDate.set(fromYear, fromMonth, fromDay, fromHour, fromMinute, 0);
		
		toDate = Calendar.getInstance();
		toDate.set(toYear,  toMonth, toDay, toHour, toMinute, 0);
		
		if(fromDate.after(toDate) || fromDate.equals(toDate))
			return false;
		
		if(occurance <= 0)
			return false;
		
		return true;
	}
	
	// give user feedback when something goes wrong
	private void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	// check whether instances are in the corresponding arrays or not
	private boolean contains(Calendar from, Calendar to) {
		boolean flag = false;
		for(int i=0; i<=total-1; i++) {
			if(from.equals(oldfrom[i]) && to.equals(oldto[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * update event accordingly
	 * @param modify event button
	 */
	public void modifyEvent(View view) {
		
		if(getData()) {
			if (verifyData()) {
				Calendar[] from = new Calendar[occurance];
				Calendar[] to = new Calendar[occurance];
				
				List<Event> list = null;
				boolean flag = true;
				Iterator<Event> iterator;
				Event e;
				
				// check event time conflict
				for(int i=0; i<occurance; i++) {
					from[i] = Calendar.getInstance();
					from[i].set(fromYear, fromMonth, fromDay, fromHour, fromMinute, 0);
					from[i].add(Calendar.DAY_OF_MONTH, 7*i);
					to[i] = Calendar.getInstance();
					to[i].set(toYear, toMonth, toDay, toHour, toMinute, 0);
					to[i].add(Calendar.DAY_OF_MONTH, 7*i);
					
					list = manager.readEvents(from[i], to[i]);
					iterator = list.iterator();
					if(list.size() != 0) {
						while(iterator.hasNext()) {
							e = iterator.next();
							boolean b = contains(e.getStartDate(), e.getEndDate());
							if(!b) {
								flag = false;
								break;
							}
						}
					}
				}
				
				// resolve event time conflict
				if(flag) {
					removeEvent(view);
					for(int i=0; i<occurance; i++) {
						e = new Event(from[i], to[i], categoryName, description, occurance, i+1);
						manager.createEvent(e);
					}
					
					finish();
				} else {
					popup(EVENT_TIME_CONFLICT);
				}
			} else{
				popup(INVALID_USER_INPUT);
			}
		} else {
			popup(INCOMPLETE_USER_INPUT);
		}
	}
	
	/**
	 * delete event
	 * @param remove event button
	 */
	public void removeEvent(View view) {
		for(int i=0; i<=total-1; i++) {
			manager.deleteEvent(oldfrom[i], oldto[i]);
		}
		finish();
	}
	
	/**
	 * return to the last activity
	 * @param return button
	 */
	public void cancel(View v) {
		finish();
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		categoryName = (String)parent.getItemAtPosition(pos);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
