package edu.utsa.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAgendaGridAdaptor extends BaseAdapter {
	Context context;
	
	//ArrayList<Event> items =new ArrayList<Event>();
	//TODO: Replace three lists below with one event object list
	// dummy lists
	ArrayList<Event> agendaList;
    ArrayList<String> startTimes;
    ArrayList<String> agendaDay;
    ArrayList<String> agendaText;

	public CustomAgendaGridAdaptor(Context context, ArrayList<Event> agendaList ) {
		super();
		this.context = context;
		
		this.agendaList = agendaList;
		
		startTimes = new ArrayList<String>();
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
			String starDateString = new SimpleDateFormat("dd/MM/yyyy").format(startDate.getTime());
			startTimes.add(starDateString);
			String agendDayString = new SimpleDateFormat("EEEE").format(startDate.getTime());
			agendaDay.add(agendDayString);
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
		// TODO Auto-generated method stub
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
	        tv1.setTextSize(10);
	        //tv1.setTextColor(Color.WHITE);
	        tv1.setGravity(Gravity.LEFT);
	        tv1.setPadding(0, 5, 10, 5);
//	        //tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
//	                                                          LinearLayout.LayoutParams.FILL_PARENT,
//	                                                          (float) 3.0));

	        tv2 = new TextView(context);
	        tv2.setTextSize(10);
	        //tv2.setTextColor(Color.WHITE);  
	        tv2.setGravity(Gravity.LEFT);
	        tv2.setPadding(10, 5, 10, 5);
//	        //tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
//	                                                              LinearLayout.LayoutParams.FILL_PARENT,
//	                                                              (float) 4.0));

	        tv3 = new TextView(context);
	        tv3.setTextSize(10);
	        //tv2.setTextColor(Color.WHITE);  
	        tv3.setGravity(Gravity.LEFT);
	        tv1.setPadding(10, 5, 0, 5);

	        ll = new LinearLayout(context);
	        ll.setOrientation(LinearLayout.HORIZONTAL);
	        //ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
	        ll.setPadding(5, 5, 5, 10);

	        tv1.setText(String.valueOf(agendaDay.get(position)));
	        tv2.setText(String.valueOf(startTimes.get(position)));
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
	    
/* agenda details is not needed now **/		
//			ll.setOnClickListener(new View.OnClickListener() {
//							
//				@Override
//				public void onClick(View v) {
//					String detailsData = new String(agendaText.get(position));
////					System.out.println("<<<< IN onClick :: AgendaView >>>>");
//					Intent intent = new Intent( context , AgendaDetailsActivity.class);
//					intent.putExtra("event_details", "AgendaDetails");
//					context.startActivity(intent);
//					
//				}
//			});
		
	    return ll;
	}
	

}

