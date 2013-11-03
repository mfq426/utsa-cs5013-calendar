package edu.utsa.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class WeeklyViewActivity extends Activity {

static final String[] weekWorks = new String[]{
    	
    	"Time","SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT",
    	"9.00","Mee","   ","   ","   ","   ","   ","   ",
    	"10.00","","","","","","","",
    	"11.00","","Pla","","","","","",
    	"12.00","","","","","Cla","","",
    	"1.00","","","","","","","",
    	"2.00","","Cla","","","","Pra","",
    	"3.00","","","Wor","","","","",
    	"4.00","","","","","","","",
    	"5.00","","","Swi","","Cla","","",
    	"6.00","Swi","","","","","","",
    	"7.00","","","","Wor","","","",
    	"8.00","","Cla","","","","","",
    	"9.00","Din","","","Mee","","","",
    	"10.00","","","","Sho","","","",
    	"11.00","","Sho","","","","",""   	
    };
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);
        
        GridView gridView = (GridView) findViewById(R.id.gridView);
               
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.grid_layout,weekWorks);
        
        //gridView.setPadding(8, 8, 8, 8);
        //gridView.setAdapter(adapter);
       
        gridView.setAdapter(new CalendarEntryAdapter(this,weekWorks));
        
        addListenerOnButton(gridView);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weekly_view, menu);
        

        return true;
    }
    
    private void startActivity() {
    	Intent intent = new Intent(this, NewEventActivity.class);
    	// pass the calling activity to my NewEventActivity;
    	intent.putExtra(NewEventActivity.CALLING_ACTIVITY, NewEventActivity.WEEKLY_VIEW_ACTIVITY);
        startActivity(intent);
    }
    
    public void addListenerOnButton(GridView gridView) {
    	 
    	ImageButton imageButtonNext = (ImageButton) findViewById(R.id.nextImageButton);
    	
    	imageButtonNext.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(WeeklyViewActivity.this,
				"Next button clicked", Toast.LENGTH_SHORT).show();
 
			}
 
		});
 
    	ImageButton imageButtonPrev = (ImageButton) findViewById(R.id.prevImageButton);
    	
    	imageButtonPrev.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(WeeklyViewActivity.this,
				"Previous button clicked", Toast.LENGTH_SHORT).show();
 
			}
 
		});
    	
    	gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
            	startActivity();
            	
            }
        });
    	  	
    	
    	
	}

}
