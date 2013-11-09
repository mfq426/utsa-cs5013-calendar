package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.utsa.calendar.InteractiveArrayAdapter.ViewHolder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
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

public class DailyViewActivity extends Activity {

	private ArrayAdapter<DailyViewModel> mAdapter = null;
	TextView previous = null;
	int preVposition = 0;
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
		
		

		// mAdapter = new InteractiveArrayAdapter(this, getModel());
		// mListView.setAdapter(mAdapter);
	}

	
	private void populateModel() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm", java.util.Locale.getDefault());
		if(startDate==null){
			startDate = Calendar.getInstance();
			endDate = Calendar.getInstance();
		}
		startDate.setTime(selectedDate.getTime());
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		endDate.setTime( selectedDate.getTime());
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		
		String header = sdf.format(selectedDate.getTime());
		dayViewHeader.setText(header);
		List<Event> events = ((GlobalVariables) this.getApplication()).getEventManager().readEvents(startDate, endDate);
		List<DailyViewModel> modelList = new ArrayList<DailyViewModel>();
		for (String hour : CalendarData.s12Hours) {

			DailyViewModel dailyModel = new DailyViewModel();
			dailyModel.setTimeLebel(hour);
			modelList.add(dailyModel);

		}

		for (Event event : events) {

			Calendar startDateOfEvent = event.getStartDate();
			int hourOfDay = startDateOfEvent.get(Calendar.HOUR_OF_DAY);
			DailyViewModel tempDailyModel = modelList.get(hourOfDay);
			tempDailyModel.addEvent(event);
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
				//DailyViewActivity.this.onResume();
				selectedDate.add(Calendar.DATE, 1);
				
				Intent intent = getIntent();
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				overridePendingTransition(R.anim.animation, R.anim.animation2);
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
				overridePendingTransition(R.anim.animation, R.anim.animation2);
			}

		});

		pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> l, View v, final int position, long id) {
				// TODO Auto-generated method stub
				DailyViewModel item = (DailyViewModel) mAdapter.getItem(position);
				if (item.getEvents().isEmpty()) {
					item.setCreateLabel(" + New Event");
				}

				final ViewHolder holder = (ViewHolder) v.getTag();
				holder.text2.setBackgroundResource(R.drawable.list_selected);
				if (preVposition == position) {
					Intent intent = new Intent(DailyViewActivity.this, NewEventActivity.class);
	            	// pass the calling activity to my NewEventActivity;
	            	//intent.putExtra(NewEventActivity.CALLING_ACTIVITY, NewEventActivity.DAILY_VIEW_ACTIVITY);
	                startActivity(intent);
	            	

				} else if (previous != null) {
					previous.setBackgroundResource(getResources().getColor(android.R.color.transparent));
					item = (DailyViewModel) mAdapter.getItem(preVposition);
					item.setCreateLabel("");
					previous = holder.text2;
				} else {
					previous = holder.text2;
				}

				preVposition = position;

				mAdapter.notifyDataSetChanged();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		populateModel();

	}

}
