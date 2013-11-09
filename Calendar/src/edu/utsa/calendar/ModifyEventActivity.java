package edu.utsa.calendar;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyEventActivity extends Activity implements OnItemSelectedListener{
	
	private int callingActivity;
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
	private int occurance;
	private int categoryId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_event);
		
		// get Data from the calling activity
		
		TextView from_date = (TextView)findViewById(R.id.from_date_m);
		from_date.setText("11/7/2013");
		
		TextView from_time = (TextView)findViewById(R.id.from_time_m);
		from_time.setText("6:04 PM");
		
		TextView to_date = (TextView)findViewById(R.id.to_date_m);
		to_date.setText("11/7/2013");
		
		TextView to_time = (TextView)findViewById(R.id.to_time_m);
		to_time.setText("7:00 PM");
		
		EditText what = (EditText)findViewById(R.id.what_m);
		what.setText("dinner with friend");
		
		CheckBox checkbox = (CheckBox)findViewById(R.id.periodical_m);
		checkbox.setPressed(true);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_event, menu);
		return true;
	}
	
	public void showFromDatePickerDialog(View v) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.from_date_m);
		date.setArguments(args);
		date.show(getFragmentManager(), "fromDatePicker");
	}

	public void showFromTimePickerDialog(View v) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.from_time_m);
		time.setArguments(args);
		time.show(getFragmentManager(), "fromTimePicker");
	}

	public void showToDatePickerDialog(View v) {
		DatePickerFragment date = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putInt("date_view", R.id.to_date_m);
		date.setArguments(args);
		date.show(getFragmentManager(), "toDatePicker");
	}

	public void showToTimePickerDialog(View v) {
		TimePickerFragment time = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("time_view", R.id.to_time_m);
		time.setArguments(args);
		time.show(getFragmentManager(), "toTimePicker");
	}
	
	public void onCheckboxClicked(View v) {
		checked = ((CheckBox) v).isChecked();
	}
	
	private boolean getData() {
		TextView from = (TextView)findViewById(R.id.from_date);
		String tmp = from.getText().toString();
		if (tmp == null || tmp.isEmpty())
			return false;
		String[] s = tmp.split("/");
		String[] time;
		fromYear = Integer.parseInt(s[0]);
		fromMonth = Integer.parseInt(s[1]);
		fromDay = Integer.parseInt(s[2]);
		
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
		toYear = Integer.parseInt(s[0]);
		toMonth = Integer.parseInt(s[1]);
		toDay = Integer.parseInt(s[2]);
		
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

	public void modifyEvent(View v) {
		
		if(getData()) {
			if (verifyData()) {
				Calendar[] from = new Calendar[occurance];
				Calendar[] to = new Calendar[occurance];
				
				EventManager manager = ((GlobalVariables) this.getApplication()).getEventManager();
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
					
					list = manager.readEvents(from[i], to[i]);
					if(list.size() != 0) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int i=0; i<occurance; i++) {
						manager.createEvent(from[i], to[i], categoryId, description);
					}
					
					// pass user inputs though intent to the calling activity
					//jump();
					finish();
				} else {
					popup("event time conflict");
				}
			}
			
			popup("invalid user input");
		} else {
			popup("incomplete user input");
		}
	}
	
	public void removeEvent(View v) {
		
	}
	
	/*private void jump() {
		Intent intent;
		switch (callingActivity) {
			case NewEventActivity.DAILY_VIEW_ACTIVITY:
				intent = new Intent(this, DailyViewActivity.class);
				startActivity(intent);
				break;
			case NewEventActivity.WEEKLY_VIEW_ACTIVITY:
				intent = new Intent(this, WeeklyViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //this is needed to start an activity, otherwise oncreate method is called
				startActivity(intent);
				break; 
			case NewEventActivity.MONTHLY_VIEW_ACTIVITY:
				intent = new Intent(this, MonthlyViewActivity.class);
				startActivity(intent);
				break; 
			default:
				System.out.println("The activity invoke new event is not legitmate.");
				break;
		}
	}*/
	

	public void cancel(View v) {
		//jump();
		finish();
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String tmp = (String)parent.getItemAtPosition(pos);
		
		// convert string to int
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
