package edu.utsa.calendar;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class CalendarActivity extends Activity{

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
    	        "Create Category",
    	        "Delete Category"
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
                	Intent intent = new Intent( CalendarActivity.this, DailyViewActivity.class);
     				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
                	Calendar instance = Calendar.getInstance();
                	instance.add(Calendar.DAY_OF_MONTH, -2);
                	intent.putExtra("selectedDay", instance.getTimeInMillis());
     				startActivity(intent);
                }
                else if(itemPosition==2){
                
                	Intent intent = new Intent(CalendarActivity.this, WeeklyViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
     				
                }
                else if(itemPosition==3){
                	Intent intent = new Intent(CalendarActivity.this, MonthlyViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==4){
                	Intent intent = new Intent(CalendarActivity.this, AgendaViewActivity.class);
     				//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==5){
                	Intent intent = new Intent(CalendarActivity.this, NewEventActivity.class);
                	//intent.putExtra(NewEventActivity.CALLING_ACTIVITY, NewEventActivity.WEEKLY_VIEW_ACTIVITY);
                	//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
                    startActivity(intent);
                	
                }
                else if(itemPosition==6){
                	Intent intent = new Intent(CalendarActivity.this, NewCategoryActivity.class);
                	startActivity(intent);
                }
                else if(itemPosition==7) {
                	Intent intent = new Intent(CalendarActivity.this, ModifyCategoryActivity.class);
                	startActivity(intent);
                }
                
               
                return false;
            }
        };
 
        /** Setting dropdown items and item navigation listener for the actionbar */
        getActionBar().setListNavigationCallbacks(adapter, navigationListener);
    	
    	//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
