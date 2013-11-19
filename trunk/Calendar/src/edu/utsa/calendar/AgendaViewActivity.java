
package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * 
 * @author Mejbah 
 * Activity class for Agena View/ List view
 *
 */
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
		
	    this.startDate = Calendar.getInstance();
				
		this.agendaViewHeader = (TextView) findViewById(R.id.agendaViewHeader);
	    this.agendaGridView = (GridView) findViewById(R.id.gridAgendaView);
		agendaGridView.setPadding(8, 8, 8, 8);

		
		
		addListenerOnButton(agendaViewHeader);
		
	}
	
	

	@Override
	protected void onResume() {
		
		super.onResume();
		
		populateModels();
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getActionBar().setSelectedNavigationItem(4);
	}
    /**
     * Gets the data and fills up the view
     */
    public void populateModels() {
    	
    	endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		
		endDate.add(Calendar.DATE, 10 );  // add 10 days with start date
		
	    events =(ArrayList<Event>) Manager.getInstance().getEventManager().readEvents(startDate,endDate);
		//System.out.println("No of events found in agenda view activity " + events.size());
		

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
   
    /**
     * 
     * @return Calendar instance of startDate of AgendaView
     */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * 
	 * @param startDate : It is the start date from which agenda view start viewing agendas
	 */

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
    
	/**
	 * 
	 * @return end date for ageda view
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param endDate : It is the start date from which agenda view ends viewing agendas
	 */


	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}


}
