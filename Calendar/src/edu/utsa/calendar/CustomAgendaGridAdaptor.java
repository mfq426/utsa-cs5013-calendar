
package edu.utsa.calendar;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author Mejbah
 * This file is the custom adaptror for the grid view used in agenda view activity
 *
 */

public class CustomAgendaGridAdaptor extends BaseAdapter {
	Context context;
	
	ArrayList<Event> agendaList;
    ArrayList<String> startTimes;
    ArrayList<String> endTimes;
    
    ArrayList<String> agendaDay;
    ArrayList<String> agendaText;

	public CustomAgendaGridAdaptor(Context context, ArrayList<Event> agendaList ) {
		super();
		this.context = context;
		
		this.agendaList = agendaList;
		
		startTimes = new ArrayList<String>();
		endTimes = new ArrayList<String>();
		agendaDay = new ArrayList<String>();
		agendaText = new ArrayList<String>();
	
		
		System.out.println("No of events found :: " + agendaList.size());
		createListforFields();
		
		
	}

	@Override
	public int getCount() {
		
		return agendaList.size();
    }
	
	public void createListforFields() {
		
		System.out.println("In agenda fields create lists;");
		for( Event e : agendaList ) {
			Calendar startDate = e.getStartDate();
			String startDateString = new SimpleDateFormat("dd MMM HH:mm").format(startDate.getTime());
			startTimes.add(startDateString);
			Calendar endDate = e.getEndDate();
			String endDateString = new SimpleDateFormat("dd MMM HH:mm").format(endDate.getTime());
			endTimes.add(endDateString);
			String agendaDayString = new SimpleDateFormat("EEEE").format(startDate.getTime());
			agendaDay.add(agendaDayString);
			agendaText.add(e.getDescription());
		}
		System.out.println("Agenda fields data filled up");
	}

	@Override
	public Object getItem(int position) {
		return agendaText.get(position);
	//	return agendaList.get(position);
	
	}

	@Override
	public long getItemId(int arg0) {
		
		return 0;
	}

	@Override
	public View getView(final int position , View convertView, ViewGroup parent) {
		TextView tv1;
	    TextView tv2;
	    TextView tv3;
	    LinearLayout ll;
	    
	    if (convertView == null) {
	        tv1 = new TextView(context);
	        tv1.setTextSize(8);
	        tv1.setGravity(Gravity.LEFT);
	        tv1.setPadding(0, 5, 10, 5);

	        tv2 = new TextView(context);
	        tv2.setTextSize(8);
  	        tv2.setGravity(Gravity.LEFT);
	        tv2.setPadding(10, 5, 10, 5);

	        tv3 = new TextView(context);
	        tv3.setTextSize(8);
  	        tv3.setGravity(Gravity.LEFT);
	        tv1.setPadding(10, 5, 0, 5);

	        ll = new LinearLayout(context);
	        ll.setOrientation(LinearLayout.HORIZONTAL);
	        System.out.println("Event color " + agendaList.get(position).getColor());
	        ll.setBackgroundColor(agendaList.get(position).getColor());
	       
	        ll.setPadding(5, 5, 5, 10);

	        tv1.setText(String.valueOf(agendaDay.get(position)));
	        String duration = new String(startTimes.get(position) + " - " + endTimes.get(position));
	        System.out.println("Text view 2 :::" + duration);
	        tv2.setText(String.valueOf(duration));
	        tv3.setText(String.valueOf(agendaText.get(position)));
	        ll.addView(tv1);
	        ll.addView(tv2);
	        ll.addView(tv3);
	    }
	    else {
	    	ll = (LinearLayout) convertView;
	        tv1 = (TextView) ll.getChildAt(0);
	        tv2 = (TextView) ll.getChildAt(1);
	        tv3 = (TextView) ll.getChildAt(2);
	        
	        tv1.setText(String.valueOf(agendaDay.get(position)));
	        tv2.setText(String.valueOf(startTimes.get(position)));
	        tv3.setText(String.valueOf(agendaText.get(position)));
	    }
		
        // add onclickLinstener with each linear layout
			ll.setOnClickListener(new View.OnClickListener() {
							
				@Override
				public void onClick(View v) {
					// On click of each row/linear layout pass the event id for the event to ModifyEvent activity
					Intent intent = new Intent(context, ModifyEventActivity.class);
					intent.putExtra("event_id", agendaList.get(position).getID());
					context.startActivity(intent);
					
				}
			});
		
	    return ll;
	}
	

}

