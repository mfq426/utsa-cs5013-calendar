package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeeklyViewActivity extends CalendarActivity {

	private GridView gridView;
	private TextView weekViewHeader;
	private Calendar startDate;
	private Calendar endDate;
		
	private static int DAY_IN_NEXT_WEEK=8;
		
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
	
	private static int [] weekWorkColor = new int[200];
	private static int [] eventID = new int[200];
   	
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
    		
    	weekWorkColor = new int[200];
    	eventID = new int[200];
    }
	private void populateFields(){
	
		setWeekWorks();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy",java.util.Locale.getDefault());
	    weekViewHeader.setText(sdf.format(startDate.getTime())+" to "+sdf.format(endDate.getTime()));
	    
	    
	    	  
	    List<Event> events = ((GlobalVariables) this.getApplication()).getEventManager().readEvents(startDate,endDate); 
	    
	    for (Event ev : events) {
            
	    	
	    	int dayOfWeekStart = ev.getStartDate().get(Calendar.DAY_OF_WEEK);
	    	int timeOfDayStart = ev.getStartDate().get(Calendar.HOUR_OF_DAY);
	    	
	    	int dayOfWeekEnd = ev.getEndDate().get(Calendar.DAY_OF_WEEK);
	    	int timeOfDayEnd = ev.getEndDate().get(Calendar.HOUR_OF_DAY);
	    	
	    	
	    	int columnNameStart=0;
  	
			if(Calendar.SUNDAY==dayOfWeekStart){
				columnNameStart = 1;				
			}
			else if(Calendar.MONDAY==dayOfWeekStart){
				columnNameStart = 2;				
			}
			else if(Calendar.TUESDAY==dayOfWeekStart){
				columnNameStart = 3;				
			}
			else if(Calendar.WEDNESDAY==dayOfWeekStart){
				columnNameStart = 4;				
			}
			else if(Calendar.THURSDAY==dayOfWeekStart){
				columnNameStart = 5;				
			}
			else if(Calendar.FRIDAY==dayOfWeekStart){
				columnNameStart = 6;				
			}
			else if(Calendar.SATURDAY==dayOfWeekStart){
				columnNameStart = 7;
			}
			else{
				System.out.println("Day format not found");
			}
			
			
			int columnNameEnd=0;
		  	
			if(Calendar.SUNDAY==dayOfWeekEnd){
				columnNameEnd = 1;				
			}
			else if(Calendar.MONDAY==dayOfWeekEnd){
				columnNameEnd = 2;				
			}
			else if(Calendar.TUESDAY==dayOfWeekEnd){
				columnNameEnd = 3;				
			}
			else if(Calendar.WEDNESDAY==dayOfWeekEnd){
				columnNameEnd = 4;				
			}
			else if(Calendar.THURSDAY==dayOfWeekEnd){
				columnNameEnd = 5;				
			}
			else if(Calendar.FRIDAY==dayOfWeekEnd){
				columnNameEnd = 6;				
			}
			else if(Calendar.SATURDAY==dayOfWeekEnd){
				columnNameEnd = 7;
			}
			else{
				System.out.println("Day format not found");
			}
			
			//System.out.println(new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa").format(ev.getStartDate().getTime()));
			//System.out.println(new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa").format(ev.getEndDate().getTime()));
			
			//if end day in the next week then set the columnNameEnd to the end day of this week
			if(columnNameEnd<columnNameStart){
				columnNameEnd=DAY_IN_NEXT_WEEK;
			}
			// set the work and color 
			for(int i=columnNameStart;i<=columnNameEnd;i++){
				
				if(columnNameEnd==columnNameStart){ // event ends in the same day
					for(int j=timeOfDayStart;j<=timeOfDayEnd;j++){
						weekWorks[8*j+i] = ev.getDescription();
						eventID[8*j+i]=ev.getID();
						//set the category color
						weekWorkColor[8*j+i] = ev.getColor();
						
						
					}
				}
				else{ // event spans for multiple days
					System.out.println("Jamil in multiple days");
					if(i==columnNameStart){ //first day of the event, color should be from start time to the end of the day
						for(int j=timeOfDayStart;j<=24;j++){
							weekWorks[8*j+i] = ev.getDescription();
							eventID[8*j+i]=ev.getID();
							//set the category color
							weekWorkColor[8*j+i] = ev.getColor();
							
							
						}
					}
					else if(i==columnNameEnd){ //last day of the event, color should be from 00 Hour to end hour of the event
						for(int j=1;j<=timeOfDayEnd;j++){
							weekWorks[8*j+i] = ev.getDescription();
							eventID[8*j+i]=ev.getID();
							//set the category color
							weekWorkColor[8*j+i] = ev.getColor();
							
							
						}
					}
					else{ // middle day of events, all field should get the event description and color
						
						for(int j=1;j<=24;j++){
							weekWorks[8*j+i] = ev.getDescription();
							eventID[8*j+i]=ev.getID();
							//set the category color
							weekWorkColor[8*j+i] = ev.getColor();
						
					}
				
				
				}
				
				
			}
			
			if((i+1)==DAY_IN_NEXT_WEEK){
				break;
			}
				//System.out.println(timeOfDayStart);
			//sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",java.util.Locale.getDefault());
			//System.out.println(sdf.format(ev.getStartDate().getTime()));
	    	//weekWorks[8*timeOfDayStart+columnNameStart] = ev.getDescription();
	    	
			}	
	    	
    }
	    gridView.setAdapter(new CalendarEntryAdapterWeek(this,weekWorks,weekWorkColor));
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
            	if(eventID[position]!=0){
            		Intent intent = new Intent(WeeklyViewActivity.this, ModifyEventActivity.class);
            		intent.putExtra("event_id", eventID[position]);
            		startActivity(intent);
            	}
            	else if(weekWorks[position].equals("   ")){
            		Intent intent = new Intent(WeeklyViewActivity.this, NewEventActivity.class);
            		startActivity(intent);
            	}
            	
            	  	
            }
        });
    	  	
    	
    	
	}

}
