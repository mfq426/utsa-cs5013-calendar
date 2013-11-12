package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.utsa.calendar.InteractiveArrayAdapter.ViewHolder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class AgendaViewActivity extends CalendarActivity {
	
	private String startDateString;
	private String endDateString;
	    
	private Calendar startDate;
	private Calendar endDate;
	
	private TextView agendaViewHeader;
	private GridView agendaGridView;
	private ArrayList<Event> events;
    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_agenda_view);
		
		
		/*******************Delete this: only for adding dummy data ********
 // delete this item, only for testing
		
		// Entry 1
				Calendar startDate = Calendar.getInstance();
				startDate.set(2013, 11, 8, 11, 20, 0);
				
				Calendar endDate = Calendar.getInstance();
				endDate.set(2013, 11, 8, 12, 20, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "walking with friends");
				
		    	
		    	// Entry 1
				startDate = Calendar.getInstance();
				startDate.set(2013, 11, 8, 11, 20, 0);
				
				endDate = Calendar.getInstance();
				endDate.set(2013, 11, 8, 12, 20, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "Dinner");
				
		    	
		    	// Entry 1
				startDate = Calendar.getInstance();
				startDate.set(2013, 10, 25, 11, 20, 0);
				
				endDate = Calendar.getInstance();
				endDate.set(2013, 10, 25, 12, 20, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "Meeing with Dr. Ruan");
				
		    	
		    	// Entry 1
				startDate = Calendar.getInstance();
				startDate.set(2013, 11, 19, 23, 00, 0);
				
				endDate = Calendar.getInstance();
				endDate.set(2013, 11, 19, 23, 5, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "Bioinformatics deadline");
				
		    	
		    	// Entry 1
				startDate = Calendar.getInstance();
				endDate.set(2013, 12, 17, 17, 00, 0);
				
				endDate = Calendar.getInstance();
				endDate.set(2013, 12, 17, 19, 20, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "Soft Engg Exam");
				
		    	
		    	// Entry 1
				startDate = Calendar.getInstance();
				startDate.set(2013, 10, 24, 11, 20, 0);
				
				endDate = Calendar.getInstance();
				endDate.set(2013, 10, 24, 12, 20, 0);
				
		    	((GlobalVariables) this.getApplication()).getEventManager().createEvent(startDate, endDate, 0, "BIBM deadline");
				
		
		
		************************end dummy data ***************************/
		 
	    this.startDate = Calendar.getInstance();
				
		
		this.agendaViewHeader = (TextView) findViewById(R.id.agendaViewHeader);
	    this.agendaGridView = (GridView) findViewById(R.id.gridAgendaView);
		agendaGridView.setPadding(8, 8, 8, 8);

		
		
		addListenerOnButton(agendaViewHeader);
//		this.agendaViewHeader.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {				
//				setStartDate(getEndDate());
//				onResume();
//				
//			}
//		});
		
	}
	
	

	@Override
	protected void onResume() {
		
		super.onResume();
		
		populateModels();
		
	}

    public void populateModels() {
    	
    	endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		
		endDate.add(Calendar.DATE, 10 );  // add 10 days with start date
		
	    events =(ArrayList<Event>) Manager.getInstance().getEventManager().readEvents(startDate,endDate);
		System.out.println("No of events found in agenda view activity " + events.size());
		

		this.startDateString = new SimpleDateFormat("dd/MM/yyyy").format(startDate.getTime());
		this.endDateString = new SimpleDateFormat("dd/MM/yyyy").format(endDate.getTime());
		
		agendaViewHeader.setText("From " + startDateString + " to " + endDateString );
		agendaGridView.setAdapter(new CustomAgendaGridAdaptor( AgendaViewActivity.this, events));
    
    }

    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void addListenerOnButton(TextView textView) {

		ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextButton);

		imageButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setStartDate(getEndDate());
				onResume();				
			}

		});

		ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevButton);

		imageButtonPrev.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				Calendar newStartDate = Calendar.getInstance();
				newStartDate.setTime(getStartDate().getTime());
				newStartDate.add(Calendar.DATE, -10);
				setStartDate(newStartDate);
				onResume();
				
				
				
			}

		});

		
	}

	public Calendar getStartDate() {
		return startDate;
	}



	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}



	public Calendar getEndDate() {
		return endDate;
	}



	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}


}
