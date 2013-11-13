package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import android.app.Activity;
import android.content.Context;
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
 * NewEventActivity is an Android activity in charge of rendering view, receiving user inputs and creating events on demand. 
 * @author Lu Liu 
 */
public class NewEventActivity extends Activity implements OnItemSelectedListener{
	
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
	private boolean checked = false;
	private int occurance = 1;
	private String categoryName;
	
	private final static String DEFAULT_CATEGORY = "default";
	private final static String EVENT_TIME_CONFLICT = "event time conflict";
	private final static String INVALID_USER_INPUT = "invalid user input";
	private final static String INCOMPLETE_USER_INPUT = "incomplete user input";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
		
		// construct spinner item array by getting all categories from database
		CategoryManager manager = Manager.getInstance().getCategoryManager();
		ArrayList<Category> list = manager.readAllCategory();
		Iterator<Category> itr = list.iterator();
		ArrayList<String> options = new ArrayList<String>();
		Category c;
		String s;
		while(itr.hasNext()) {
			c = itr.next();
			s = c.getName();
			// exclude from showing the default category
			if(!(s.equalsIgnoreCase(DEFAULT_CATEGORY))) {
				options.add(s);
			}
		}
		
		// add spinner item array to spinner dynamically
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_event, menu);
		return true;
	}

	/**
	 * show date picker dialog to enable user to select event start date
	 * @param event start date view 
	 */
	public void showFromDatePickerDialog(View view) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.from_date);
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
		args.putInt("time_view", R.id.from_time);
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
		args.putInt("date_view", R.id.to_date);
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
		args.putInt("time_view", R.id.to_time);
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
	
	/**
	 * collect user input from all view components
	 * @return a boolean value, it is true if all required fields are filed; otherwise return false
	 */
	private boolean getData() {
		TextView from = (TextView)findViewById(R.id.from_date);
		String tmp = from.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		String[] s = tmp.split("/");
		String[] time;
		fromYear = Integer.parseInt(s[2]);
		fromMonth = Integer.parseInt(s[0]) - 1;
		fromDay = Integer.parseInt(s[1]);
		
		TextView f_time = (TextView)findViewById(R.id.from_time);
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
		
		TextView to = (TextView)findViewById(R.id.to_date);
		tmp = to.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		s = tmp.split("/");
		toYear = Integer.parseInt(s[2]);
		toMonth = Integer.parseInt(s[0]) - 1;
		toDay = Integer.parseInt(s[1]);
		
		TextView t_time = (TextView)findViewById(R.id.to_time);
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
		
		EditText editText = (EditText)findViewById(R.id.what);
		tmp = editText.getText().toString();
		if(tmp == null || tmp.isEmpty())
			return false;
		description = tmp;
		
		if(checked) {
			editText = (EditText)findViewById(R.id.times);
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
	
	/**
	 * verify the user input data to make sure that event start time is earlier than end time and if it is weekly repeating event, the repeating times is positive value
	 * @return a boolean value, if all the conditions meets; otherwise return false
	 */
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
	
	private void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	/**
	 * handle user's request of creating event by collecting user inputs, verifying inputs and resolving event time conflict
	 * @param create event button
	 */
	public void createEvent(View view) {
		
		if(getData()) {
			if (verifyData()) {
				Calendar[] from = new Calendar[occurance];
				Calendar[] to = new Calendar[occurance];
				
				EventManager manager = Manager.getInstance().getEventManager();
				List<Event> list = null;
				boolean flag = true;
				
				// check event time conflict
				for(int i=0; i<occurance; i++) {
					from[i] = Calendar.getInstance();
					from[i].set(fromYear, fromMonth, fromDay, fromHour, fromMinute, 0);
					from[i].add(Calendar.DAY_OF_MONTH, 7*i);
					
					to[i] = Calendar.getInstance();
					to[i].set(toYear, toMonth, toDay, toHour, toMinute, 0);
					to[i].add(Calendar.DAY_OF_MONTH, 7*i);
					
					System.out.println(from[i].toString());
					System.out.println(to[i].toString());
					list = manager.getConflictedEvents(from[i], to[i]);
					if(list.size() > 0) {
						flag = false;
						break;
					}
				}
				
				// resolve event time conflict
				if(flag) {
					Event e;
					// if user requests to create a weekly periodical event, we create event one by one for multiple weeks
					for(int i=0; i<occurance; i++) {
						e = new Event(from[i], to[i], categoryName, description, occurance, i+1);
						// save newly created event to database
						manager.createEvent(e);
					}
					
					finish();
				} else {
					popup(EVENT_TIME_CONFLICT);
				}
			} else {
				popup(INVALID_USER_INPUT);
			}
		} else {
			popup(INCOMPLETE_USER_INPUT);
		}
	}

	/**
	 * return to the last activity
	 * @param return button
	 */
	public void cancel(View view) {
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
