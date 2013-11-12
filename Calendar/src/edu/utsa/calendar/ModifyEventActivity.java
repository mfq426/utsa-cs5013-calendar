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
	
	private Calendar[] oldfrom;
	private Calendar[] oldto;
	private int index;
	private int total;
	private EventManager manager;
	
	private void setData(Event e) {
		fromYear = e.getStartDate().get(Calendar.YEAR);
		fromMonth = e.getStartDate().get(Calendar.MONTH) + 1;
		fromDay = e.getStartDate().get(Calendar.DAY_OF_MONTH);
		fromHour = e.getStartDate().get(Calendar.HOUR_OF_DAY);
		fromMinute = e.getStartDate().get(Calendar.MINUTE);
		
		toYear = e.getEndDate().get(Calendar.YEAR);
		toMonth = e.getEndDate().get(Calendar.MONTH) + 1;
		toDay = e.getEndDate().get(Calendar.DAY_OF_MONTH);
		toHour = e.getEndDate().get(Calendar.HOUR_OF_DAY);
		toMinute = e.getEndDate().get(Calendar.MINUTE);
		
		description = e.getDescription();
		occurance = e.getTotalOccurance();
		total = occurance;
		if(occurance > 1) {
			checked = true;
		}
		index = e.getOccuranceIndex();
		categoryName = e.getCategoryID();
		
		oldfrom = new Calendar[total];
		oldto = new Calendar[total];
		
		oldfrom[index-1] = e.getStartDate();
		oldto[index-1] = e.getEndDate();
		
		Calendar tmp1 = e.getStartDate();
		Calendar tmp2 = e.getEndDate();
		for(int i=index-2; i>=0; i--) {
			tmp1.add(Calendar.DAY_OF_MONTH, -7);
			oldfrom[i] = tmp1;
			tmp2.add(Calendar.DAY_OF_MONTH, -7);
			oldto[i] = tmp2;
		}
		
		tmp1 = e.getStartDate();
		tmp2 = e.getEndDate();
		for(int i=index; i<=total-1; i++) {
			tmp1.add(Calendar.DAY_OF_MONTH, 7);
			oldfrom[i] = tmp1;
			tmp2.add(Calendar.DAY_OF_MONTH, 7);
			oldto[i] = tmp2;
		}		
		
	}
	
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
		
		manager = ((GlobalVariables) this.getApplication()).getEventManager();
		
		Intent intent = getIntent();
		int event_id = intent.getIntExtra("event_id", -100);
		Event e = manager.getEventById(event_id);
		
		setData(e);
		
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
		
		Spinner spinner = (Spinner)findViewById(R.id.category_spinner_m);
		CategoryManager category_manager = ((GlobalVariables) this.getApplication()).getCategoryManager();
		ArrayList<Category> list = category_manager.readAllCategory();
		Iterator<Category> itr = list.iterator();
		ArrayList<String> options = new ArrayList<String>();
		Category c;
		int pos=-1;
		int i=0;
		while(itr.hasNext()) {
			c = itr.next();
			s = c.getName();
			if(!(s.equalsIgnoreCase("default"))) {
				options.add(s);
				i++;
				if(s.equals(categoryName)) {
					pos = i;
				}
			}
		}
		
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

	public void modifyEvent(View v) {
		
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
				
				if(flag) {
					removeEvent(v);
					
					for(int i=0; i<occurance; i++) {
						e = new Event(from[i], to[i], categoryName, description, occurance, i+1);
						manager.createEvent(e);
					}
					
					finish();
				} else {
					popup("event time conflict");
				}
			} else{
				popup("invalid user input");
			}
		} else {
			popup("incomplete user input");
		}
	}
	
	public void removeEvent(View v) {
		for(int i=0; i<=total-1; i++) {
			manager.deleteEvent(oldfrom[i], oldto[i]);
		}
		finish();
	}
	

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
