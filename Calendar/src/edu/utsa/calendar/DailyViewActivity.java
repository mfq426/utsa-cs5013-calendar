package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.utsa.calendar.InteractiveArrayAdapter.ViewHolder;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DailyViewActivity extends CalendarActivity {

	private InteractiveArrayAdapter mAdapter = null;
	View previous = null;
	int preVposition = -1;
	private ListView mListView;
	private TextView dayViewHeader;
	private Calendar startDate;
	private Calendar endDate;
	private Calendar selectedDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_view);

		mListView = (ListView) findViewById(R.id.listView1);
		dayViewHeader = (TextView) findViewById(R.id.dayViewHeader);

		addListenerOnButton(mListView);
		selectedDate = Calendar.getInstance();
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar6));
		// mListView.setAdapter(mAdapter);
	}

	private void populateModel() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy", java.util.Locale.getDefault());
		if (startDate == null) {
			startDate = Calendar.getInstance();
			endDate = Calendar.getInstance();
		}
		startDate.setTime(selectedDate.getTime());
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		endDate.setTime(selectedDate.getTime());
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);

		String header = sdf.format(selectedDate.getTime());
		dayViewHeader.setText(header);
		List<Event> events = Manager.getInstance().getEventManager().readEvents(startDate, endDate);
		List<DailyViewModel> modelList = new ArrayList<DailyViewModel>();
		for (String hour : CalendarData.s12Hours) {

			DailyViewModel dailyModel = new DailyViewModel();
			dailyModel.setTimeLebel(hour);
			modelList.add(dailyModel);

		}

		for (Event event : events) {

			Calendar startDateOfEvent = event.getStartDate();
			int startHourOfDay = startDateOfEvent.get(Calendar.HOUR_OF_DAY);
			int sday = startDateOfEvent.get(Calendar.DAY_OF_MONTH);
			DailyViewModel tempDailyModel = modelList.get(startHourOfDay);

			tempDailyModel.addEvent(event);
			Calendar endDateOfEvent = event.getEndDate();
			int endHourOfDay = endDateOfEvent.get(Calendar.HOUR_OF_DAY);
			int eday = startDateOfEvent.get(Calendar.DAY_OF_MONTH);
			if (sday == eday && (endHourOfDay > startHourOfDay)) {
				while (startHourOfDay <= endHourOfDay) {
					startHourOfDay += 1;
					tempDailyModel = modelList.get(startHourOfDay);
					tempDailyModel.addEvent(event);
				}
			}

		}
		mAdapter = new InteractiveArrayAdapter(this, modelList);
		mListView.setAdapter(mAdapter);

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void addListenerOnButton(ListView pListView) {

		ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextButton);

		imageButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View pView) {
				// DailyViewActivity.this.onResume();
				selectedDate.add(Calendar.DATE, 1);

				Intent intent = getIntent();
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.animation_slide_in_left, R.anim.animation_slide_out_right);

			}

		});

		ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevButton);

		imageButtonPrev.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View pView) {
				selectedDate.add(Calendar.DATE, -1);
				Intent intent = getIntent();
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.animation_slide_in_right, R.anim.animation_slide_out_left);
			}

		});

		pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> l, View v, final int position, long id) {
				// TODO Auto-generated method stub
				DailyViewModel item = (DailyViewModel) mAdapter.getItem(position);
				if (item.getEvents().isEmpty()) {
					item.setCreateLabel(" + New Event");
					Event dummyEvent = new Event(-100, null, null, "", " + New Event", 0, 0);
					item.addEvent(dummyEvent);
				}

				final ViewHolder holder = (ViewHolder) v.getTag();
				holder.grid.setBackgroundResource(R.drawable.list_selected);
				if (preVposition == position) {

					if (!item.getEvents().isEmpty()) {
						// item.setCreateLabel(" + New Event");
					}
					// Intent intent = new Intent(DailyViewActivity.this,
					// NewEventActivity.class);
					// pass the calling activity to my NewEventActivity;
					// intent.putExtra(NewEventActivity.CALLING_ACTIVITY,
					// NewEventActivity.DAILY_VIEW_ACTIVITY);
					// startActivity(intent);

				} else if (previous != null) {
					previous.setBackgroundResource(getResources().getColor(android.R.color.transparent));

					DailyViewModel itemOld = (DailyViewModel) mAdapter.getItem(preVposition);
					itemOld.removeEvents();

					previous = holder.grid;
				} else {
					previous = holder.grid;
				}

				preVposition = position;

				mAdapter.notifyDataSetChanged();
				List<ArrayAdapter<Event>> adapterList = mAdapter.getAdapterList();

				for (ArrayAdapter<Event> arrayAdapter : adapterList) {

					arrayAdapter.notifyDataSetChanged();
				}

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			Long time = bundle.getLong("selectedDay");
			if (time != null && time > 0) {
				selectedDate = Calendar.getInstance();
				selectedDate.setTimeInMillis(time);
				time = Long.valueOf(0);
				getIntent().putExtra("selectedDay", time);

			}
		}
		populateModel();
		preVposition = -1;
		previous = null;
	}

}
