package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

public class AgendaViewActivity extends Activity {
	
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
	    // TODO: remove the following set of stardate : it is just for testing
//	    this.startDate.set(2013, 10, 24, 11, 20, 0);
				
		
		this.agendaViewHeader = (TextView) findViewById(R.id.agendaViewHeader);
	    this.agendaGridView = (GridView) findViewById(R.id.gridAgendaView);
		agendaGridView.setPadding(8, 8, 8, 8);

		
		
		
		this.agendaViewHeader.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				System.out.println("<<<< IN onClick :: AgendaViewHeader >>>>");
				
				setStartDate(getEndDate());
				onResume();
				
			}
		});
		
	}
	
	

	@Override
	protected void onResume() {
		
		super.onResume();
		
		endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		
		endDate.add(Calendar.DATE, 10 );  // add 10 days with start date
		
	    events =(ArrayList<Event>) ((GlobalVariables) this.getApplication()).getEventManager().readEvents(startDate,endDate);
		System.out.println("No of events found in agenda view activity " + events.size());
		
		//this.agendaViewHeader = (TextView) findViewById(R.id.agendaViewHeader);
		this.startDateString = new SimpleDateFormat("dd/MM/yyyy").format(startDate.getTime());
		this.endDateString = new SimpleDateFormat("dd/MM/yyyy").format(endDate.getTime());
		
		agendaViewHeader.setText("Agenda from " + startDateString + " to " + endDateString );
	    //this.agendaGridView = (GridView) findViewById(R.id.gridAgendaView);
		//agendaGridView.setPadding(8, 8, 8, 8);
		agendaGridView.setAdapter(new CustomAgendaGridAdaptor( AgendaViewActivity.this, events));
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



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_view, menu);
		return true;
	}

}
