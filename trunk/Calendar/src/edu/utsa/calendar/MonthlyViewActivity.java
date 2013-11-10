package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
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

public class MonthlyViewActivity extends Activity {

	
	private CategoryManager categoryManager;
	private EventManager eventManager;
	private GridView gridView;
	private TextView monthViewHeader;
	private Calendar startDate;
	private Calendar endDate;
	
	private static ArrayList<String> monthWorks;
	private static ArrayList<String> workDayIndicator;
	
	private int padding=0;
		
	private void setMonthWorks(){
    	
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
	
private void setDate(){
		
		
	// get the current date and set the start date to the beginning of the month	
	startDate = Calendar.getInstance();
	startDate.set(Calendar.HOUR_OF_DAY, 0);
	startDate.set(Calendar.MINUTE, 0);
	startDate.set(Calendar.SECOND, 0);
	startDate.set(Calendar.MILLISECOND, 0);
	startDate.set(Calendar.DAY_OF_MONTH, 1);			
	//System.out.println(new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa").format(startDate.getTime()));
	
	// get the current date and set the start date to the beginning of the month    
	endDate = Calendar.getInstance();
	endDate.set(Calendar.HOUR_OF_DAY, 23);
	endDate.set(Calendar.MINUTE, 59);
	endDate.set(Calendar.SECOND, 59);
	endDate.set(Calendar.MILLISECOND, 999);
	endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
	//System.out.println(new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa").format(endDate.getTime()));
				
}
	
	
	private void populateFields(){
		

		setMonthWorks();
	    monthViewHeader.setText(new SimpleDateFormat("MMMM").format(startDate.getTime())+ " "+ new SimpleDateFormat("yyyy").format(startDate.getTime()));
	            	  
	   
	    //populate the day of the months
	    int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
	   
	    
	    if(Calendar.SUNDAY==dayOfWeek){
			padding = 0;				
		}
		else if(Calendar.MONDAY==dayOfWeek){
			padding = 1;				
		}
		else if(Calendar.TUESDAY==dayOfWeek){
			padding = 2;				
		}
		else if(Calendar.WEDNESDAY==dayOfWeek){
			padding = 3;				
		}
		else if(Calendar.THURSDAY==dayOfWeek){
			padding = 4;				
		}
		else if(Calendar.FRIDAY==dayOfWeek){
			padding = 5;				
		}
		else if(Calendar.SATURDAY==dayOfWeek){
			padding = 6;
		}
		else{
			System.out.println("Day format not found");
		}
	    
	    // add empty filed to the calendar array
	    
	    for(int i=0;i<padding;i++){
	    	
	    	monthWorks.add("  ");
	    	workDayIndicator.add("0");
	    	
	    }
	    
	    for(int i=1; i<= endDate.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
	    	monthWorks.add("   "+i+"   ");
	    	workDayIndicator.add("0");
	    }
	    
	    
	    List<Event> events = ((GlobalVariables) this.getApplication()).getEventManager().readEvents(startDate,endDate); 
	    
	    for (Event ev : events) {
            
	    	
	    	int dayOfMonth = ev.getmStartDate().get(Calendar.DAY_OF_MONTH);
	    	
	    	workDayIndicator.set(6+padding+dayOfMonth, "1");
	    			    	
	    }
	    gridView.setAdapter(new CalendarEntryAdapterMonth(this,monthWorks.toArray(new String[monthWorks.size()]),workDayIndicator.toArray(new String[workDayIndicator.size()])));
	    
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monthly_view);
			        
	    // delete this item, only for testing
		
		// Entry 1
		/*		Calendar startDate = Calendar.getInstance();
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
				
		*/		
		gridView = (GridView) findViewById(R.id.gridViewMonthly);
	    monthViewHeader = (TextView) findViewById(R.id.monthViewHeader);
	    this.setDate();
	    
	    
	    addListenerOnButton(gridView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		String[] actions = new String[] {
    	        "Select Action",
				"Daily View",
    	        "Weekly View ",
    	        "Monthly View",
    	        "Agenda View",
    	        "Create Event",
    	        "Create Category"
    	    };
   
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions);
     
        /** Enabling dropdown list navigation for the action bar */
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setTitle("");
      
        /** Defining Navigation listener */
        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
 
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
               
            	if(itemPosition==0){
            		//do nothing
            	}
            	else if(itemPosition==1){
                	Intent intent = new Intent(MonthlyViewActivity.this, DailyViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==2){
                
                	Intent intent = new Intent(MonthlyViewActivity.this, WeeklyViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
     				
                }
                else if(itemPosition==3){
                	Intent intent = new Intent(MonthlyViewActivity.this, MonthlyViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==4){
                	Intent intent = new Intent(MonthlyViewActivity.this, AgendaViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==5){
                	Intent intent = new Intent(MonthlyViewActivity.this, NewEventActivity.class);
                	startActivity(intent);
                	
                }
                else if(itemPosition==6){
                	
                }
                
               
                return false;
            }
        };
 
        /** Setting dropdown items and item navigation listener for the actionbar */
        getActionBar().setListNavigationCallbacks(adapter, navigationListener);
    	
    	//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 protected void onResume(){
	       super.onResume();
	       this.populateFields();
	       
	       
	 }
	 
	 public void addListenerOnButton(GridView gridView) {
    	 
	    	ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextImageButton);
	    	
	    	imageButtonNext.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
	 
					
					Toast.makeText(MonthlyViewActivity.this,"Loading ...", Toast.LENGTH_SHORT).show();
					startDate.add(Calendar.DATE, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
					endDate.add(Calendar.DATE, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
					

					MonthlyViewActivity.this.onResume();
				}
	 
			});
	 
	    	ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevImageButton);
	    	
	    	imageButtonPrev.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
	 
				   Toast.makeText(MonthlyViewActivity.this,"Loading ...", Toast.LENGTH_SHORT).show();
				   endDate.add(Calendar.DATE, (-1)*endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				   startDate.add(Calendar.DATE, (-1)*endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				   MonthlyViewActivity.this.onResume();
	 
				}
	 
			});
	    	
	    	gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
	            	if(position>(6+padding)){
	            		if(workDayIndicator.get(position).equals("1")){
	            			Toast.makeText(MonthlyViewActivity.this, "Loading the events", Toast.LENGTH_SHORT).show();
	            			// call DayViewActivity here
	            		}
	            		else{
	            			Toast.makeText(MonthlyViewActivity.this, "No events in this day", Toast.LENGTH_SHORT).show();
	            			// call DayViewActicity here
	            		}
	            			
	            		
	            		
	            		
	            	}
	            	
	            	//Intent intent = new Intent(WeeklyViewActivity.this, NewEventActivity.class);
	            	// pass the calling activity to my NewEventActivity;
	            	//intent.putExtra(NewEventActivity.CALLING_ACTIVITY, NewEventActivity.WEEKLY_VIEW_ACTIVITY);
	                //startActivity(intent);
	            	
	            }
	        });
	    	  	
	    	
	    	
		}

	 

}
