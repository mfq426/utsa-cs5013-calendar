package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeeklyViewActivity extends CalendarActivity {

	private GridView gridView;
	private TextView weekViewHeader;
	private Calendar startDate;
	private Calendar endDate;
		
		
	private static String[] weekWorks = new String[]{
    	
    	"Time","SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT",
    	" 1am","   ","   ","   ","   ","   ","   ","   ",
    	" 2am","   ","   ","   ","   ","   ","   ","   ",
    	" 3am","   ","   ","   ","   ","   ","   ","   ",
    	" 4am","   ","   ","   ","   ","   ","   ","   ",
    	" 5am","   ","   ","   ","   ","   ","   ","   ",
    	" 6am","   ","   ","   ","   ","   ","   ","   ",
    	" 7am","   ","   ","   ","   ","   ","   ","   ",
    	" 8am","   ","   ","   ","   ","   ","   ","   ",
    	" 9am","   ","   ","   ","   ","   ","   ","   ",
    	"10am","   ","   ","   ","   ","   ","   ","   ",
    	"11am","   ","   ","   ","   ","   ","   ","   ",
    	"12pm","   ","   ","   ","   ","   ","   ","   ",
    	" 1pm","   ","   ","   ","   ","   ","   ","   ",
    	" 2pm","   ","   ","   ","   ","   ","   ","   ",
    	" 3pm","   ","   ","   ","   ","   ","   ","   ",
    	" 4pm","   ","   ","   ","   ","   ","   ","   ",
    	" 5pm","   ","   ","   ","   ","   ","   ","   ",
    	" 6pm","   ","   ","   ","   ","   ","   ","   ",
    	" 7pm","   ","   ","   ","   ","   ","   ","   ",
    	" 8pm","   ","   ","   ","   ","   ","   ","   ",
    	" 9pm","   ","   ","   ","   ","   ","   ","   ",
    	"10pm","   ","   ","   ","   ","   ","   ","   ",
    	"11pm","   ","   ","   ","   ","   ","   ","   ",
    	"12am","   ","   ","   ","   ","   ","   ","   ",
    	
    };
	
   	
	private void setDate(){
		
		
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
		int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
		
		if(Calendar.SUNDAY==dayOfWeek){
			startDate.add(Calendar.DATE, 0);
			endDate.add(Calendar.DATE, 6);
			
		}
		else if(Calendar.MONDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -1);
			endDate.add(Calendar.DATE, 5);
			
		}
		else if(Calendar.TUESDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -2);
			endDate.add(Calendar.DATE, 4);
			
		}
		else if(Calendar.WEDNESDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -3);
			endDate.add(Calendar.DATE, 3);
			
		}
		else if(Calendar.THURSDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -4);
			endDate.add(Calendar.DATE, 2);
			
		}
		else if(Calendar.FRIDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -5);
			endDate.add(Calendar.DATE, 1);
			
		}
		else if(Calendar.SATURDAY==dayOfWeek){
			startDate.add(Calendar.DATE, -6);
			endDate.add(Calendar.DATE, 0);
			
		}
		else{
			System.out.println("Day format not found");
		}
		
		
		//set time to initial values
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		
		
	}

    private void setWeekWorks(){
    	weekWorks = new String[]{
    	    	
    	    	"Time","SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT",
    	    	" 1am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 2am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 3am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 4am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 5am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 6am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 7am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 8am","   ","   ","   ","   ","   ","   ","   ",
    	    	" 9am","   ","   ","   ","   ","   ","   ","   ",
    	    	"10am","   ","   ","   ","   ","   ","   ","   ",
    	    	"11am","   ","   ","   ","   ","   ","   ","   ",
    	    	"12pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 1pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 2pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 3pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 4pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 5pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 6pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 7pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 8pm","   ","   ","   ","   ","   ","   ","   ",
    	    	" 9pm","   ","   ","   ","   ","   ","   ","   ",
    	    	"10pm","   ","   ","   ","   ","   ","   ","   ",
    	    	"11pm","   ","   ","   ","   ","   ","   ","   ",
    	    	"12am","   ","   ","   ","   ","   ","   ","   ",
    	    	
    	    };
    		
    }
	private void populateFields(){
	
		setWeekWorks();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy",java.util.Locale.getDefault());
	    weekViewHeader.setText(sdf.format(startDate.getTime())+" to "+sdf.format(endDate.getTime()));
	    
	    
	    	  
	    List<Event> events = ((GlobalVariables) this.getApplication()).getEventManager().readEvents(startDate,endDate); 
	    
	    for (Event ev : events) {
            
	    	
	    	int dayOfWeek = ev.getStartDate().get(Calendar.DAY_OF_WEEK);
	    	int timeOfDay = ev.getStartDate().get(Calendar.HOUR_OF_DAY);
	    	int columnName=0;
	    	

			if(Calendar.SUNDAY==dayOfWeek){
				columnName = 1;				
			}
			else if(Calendar.MONDAY==dayOfWeek){
				columnName = 2;				
			}
			else if(Calendar.TUESDAY==dayOfWeek){
				columnName = 3;				
			}
			else if(Calendar.WEDNESDAY==dayOfWeek){
				columnName = 4;				
			}
			else if(Calendar.THURSDAY==dayOfWeek){
				columnName = 5;				
			}
			else if(Calendar.FRIDAY==dayOfWeek){
				columnName = 6;				
			}
			else if(Calendar.SATURDAY==dayOfWeek){
				columnName = 7;
			}
			else{
				System.out.println("Day format not found");
			}
			System.out.println(timeOfDay);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",java.util.Locale.getDefault());
			System.out.println(sdf.format(ev.getStartDate().getTime()));
	    	weekWorks[8*timeOfDay+columnName] = ev.getDescription();
	    	
	    	
    }
	    gridView.setAdapter(new CalendarEntryAdapterWeek(this,weekWorks));
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);
        
        
        gridView = (GridView) findViewById(R.id.gridViewWeekly);
        gridView.setBackgroundColor(Color.GRAY);
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        weekViewHeader = (TextView) findViewById(R.id.weekViewHeader);
        this.setDate();
        //this.populateFields();
        addListenerOnButton(gridView);
        
    }


    
    protected void onResume()
    {
       super.onResume();
       this.populateFields();
       
       
    }

    
    
    public void addListenerOnButton(GridView gridView) {
    	 
    	ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextImageButton);
    	
    	imageButtonNext.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				
				Toast.makeText(WeeklyViewActivity.this,"Loading ...", Toast.LENGTH_SHORT).show();
				startDate.add(Calendar.DATE, 7);
				endDate.add(Calendar.DATE, 7);
				WeeklyViewActivity.this.onResume();
			}
 
		});
 
    	ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevImageButton);
    	
    	imageButtonPrev.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(WeeklyViewActivity.this,"Loading ...", Toast.LENGTH_SHORT).show();
			   startDate.add(Calendar.DATE, -7);
			   endDate.add(Calendar.DATE, -7);
			   WeeklyViewActivity.this.onResume();
 
			}
 
		});
    	
    	gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
            	//Intent intent = new Intent(WeeklyViewActivity.this, NewEventActivity.class);
            	// pass the calling activity to my NewEventActivity;
            	//intent.putExtra(NewEventActivity.CALLING_ACTIVITY, NewEventActivity.WEEKLY_VIEW_ACTIVITY);
                //startActivity(intent);
            	
            }
        });
    	  	
    	
    	
	}

}
