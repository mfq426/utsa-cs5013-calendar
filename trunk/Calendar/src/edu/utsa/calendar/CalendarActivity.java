package edu.utsa.calendar;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
               
            	// save last stay view (Daily, Weekly, Monthly, Agenda) to preference
            	SharedPreferences mPrefs = getSharedPreferences("view", 0);
            	Editor edit = mPrefs.edit();
            	
            	if(itemPosition==0){
            		//do nothing
            	}
            	else if(itemPosition==1){
            		
            		edit.clear();
            		edit.putInt("view_mode", MainActivity.DAILY_VIEW_MODE);
            		edit.commit();
            		
                	Intent intent = new Intent( CalendarActivity.this, DailyViewActivity.class);
     				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
                	Calendar instance = Calendar.getInstance();
                	intent.putExtra("selectedDay", instance.getTimeInMillis());
     				startActivity(intent);
                }
                else if(itemPosition==2){
            		edit.clear();
            		edit.putInt("view_mode", MainActivity.WEEKLY_VIEW_MODE);
            		edit.commit();
                
                	Intent intent = new Intent(CalendarActivity.this, WeeklyViewActivity.class);
     				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
     				
                }
                else if(itemPosition==3){
                	
            		edit.clear();
            		edit.putInt("view_mode", MainActivity.MONTHLY_VIEW_MODE);
            		edit.commit();
                	
                	Intent intent = new Intent(CalendarActivity.this, MonthlyViewActivity.class);
     				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
     				startActivity(intent);
                }
                else if(itemPosition==4){
                	
            		edit.clear();
            		edit.putInt("view_mode", MainActivity.AGENDA_VIEW_MODE);
            		edit.commit();
                	
                	Intent intent = new Intent(CalendarActivity.this, AgendaViewActivity.class);
     				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
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
