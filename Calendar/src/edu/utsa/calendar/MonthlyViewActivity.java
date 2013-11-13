package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Purpose of this class is to draw monthly activity properly. It will on demand retrieve event information from database based on start date and end date
 * @author Jamiul
 *
 */

public class MonthlyViewActivity extends CalendarActivity {

	private CategoryManager categoryManager;
	private EventManager eventManager;
	private GridView gridView;
	private TextView monthViewHeader;
	private Calendar startDate;
	private Calendar endDate;

	private static ArrayList<String> monthWorks;
	private static ArrayList<String> workDayIndicator;

	private int padding = 0;

	/**
	 * Set the variable text to populate new event information
	 */
	private void setMonthWorks() {

		monthWorks = new ArrayList<String>();
		workDayIndicator = new ArrayList<String>();

		monthWorks.add("SUN");
		monthWorks.add("MON");
		monthWorks.add("TUE");
		monthWorks.add("WED");
		monthWorks.add("THU");
		monthWorks.add("FRI");
		monthWorks.add("SAT");

		workDayIndicator.add("0");
		workDayIndicator.add("0");
		workDayIndicator.add("0");
		workDayIndicator.add("0");
		workDayIndicator.add("0");
		workDayIndicator.add("0");
		workDayIndicator.add("0");

	}

	/**
	 * Set the startDate and endDate variable to the start date and end date of
	 * this week
	 */
	
	private void setDate() {

		// get the current date and set the start date to the beginning of the
		// month
		startDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		startDate.set(Calendar.DAY_OF_MONTH, 1);
		

		// get the current date and set the start date to the end of the
		// month
		endDate = Calendar.getInstance();
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		endDate.set(Calendar.DAY_OF_MONTH,
				endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		

	}

	
	/**
	 * This method reads event information from the database and populate them
	 */
	private void populateFields() {

		setMonthWorks();
		monthViewHeader.setText(new SimpleDateFormat("MMMM").format(startDate
				.getTime())
				+ " "
				+ new SimpleDateFormat("yyyy").format(startDate.getTime()));

		// populate the day of the months
		int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);

		if (Calendar.SUNDAY == dayOfWeek) {
			padding = 0;
		} else if (Calendar.MONDAY == dayOfWeek) {
			padding = 1;
		} else if (Calendar.TUESDAY == dayOfWeek) {
			padding = 2;
		} else if (Calendar.WEDNESDAY == dayOfWeek) {
			padding = 3;
		} else if (Calendar.THURSDAY == dayOfWeek) {
			padding = 4;
		} else if (Calendar.FRIDAY == dayOfWeek) {
			padding = 5;
		} else if (Calendar.SATURDAY == dayOfWeek) {
			padding = 6;
		} else {
			System.out.println("Day format not found");
		}

		// add empty filed to the calendar array

		for (int i = 0; i < padding; i++) {

			monthWorks.add("  ");
			workDayIndicator.add("0");

		}

		for (int i = 1; i <= endDate.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			monthWorks.add("   " + i + "   ");
			workDayIndicator.add("0");
		}

		List<Event> events = Manager.getInstance()
				.getEventManager().readEvents(startDate, endDate);

		for (Event ev : events) {

			int dayOfMonth = ev.getStartDate().get(Calendar.DAY_OF_MONTH);

			workDayIndicator.set(6 + padding + dayOfMonth, "1");

		}
		gridView.setAdapter(new CalendarEntryAdapterMonth(this, monthWorks
				.toArray(new String[monthWorks.size()]), workDayIndicator
				.toArray(new String[workDayIndicator.size()])));

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monthly_view);

		gridView = (GridView) findViewById(R.id.gridViewMonthly);
		gridView.setBackgroundColor(Color.GRAY);
	    gridView.setVerticalSpacing(1);
	    gridView.setHorizontalSpacing(1);
		monthViewHeader = (TextView) findViewById(R.id.monthViewHeader);
		this.setDate();

		addListenerOnButton(gridView);

	}

	protected void onResume() {
		super.onResume();
		this.populateFields();

	}

	
	/**
	 * Action listen for the user actions
	 * 
	 * @param gridView
	 *            gird view to listen
	 */
	public void addListenerOnButton(GridView gridView) {

		ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextImageButton);

		imageButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(MonthlyViewActivity.this, "Loading ...",
						Toast.LENGTH_SHORT).show();
				startDate.add(Calendar.DATE,
						startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate.add(Calendar.DATE,
						startDate.getActualMaximum(Calendar.DAY_OF_MONTH));

				MonthlyViewActivity.this.onResume();
			}

		});

		ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevImageButton);

		imageButtonPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(MonthlyViewActivity.this, "Loading ...",
						Toast.LENGTH_SHORT).show();
				endDate.add(Calendar.DATE,
						(-1) * endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				startDate.add(Calendar.DATE,
						(-1) * endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				MonthlyViewActivity.this.onResume();

			}

		});

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				if (position > (6 + padding)) {
					if (workDayIndicator.get(position).equals("1")) {
						Toast.makeText(MonthlyViewActivity.this,
								"Loading the events", Toast.LENGTH_SHORT)
								.show();
						Intent intent = new Intent(MonthlyViewActivity.this,DailyViewActivity.class);
						Calendar newDate = startDate;
						newDate.add(Calendar.DAY_OF_MONTH, position-7-padding);
						System.out.println(newDate.toString());
						intent.putExtra("selectedDay", newDate.getTimeInMillis());
						startActivity(intent);
						
					} else {
						Toast.makeText(MonthlyViewActivity.this,
								"No events in this day", Toast.LENGTH_SHORT)
								.show();
						
					}

				}

			}
		});

	}

}
