package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;

public class AgendaByCategoryActivity extends CalendarActivity implements OnItemSelectedListener {
	
	private Spinner spinner;
	private GridView agendaGridView;
	private ArrayList<Event> events;
	private String categoryName;
	
	protected final static String DEFAULT_CATEGORY = "default";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_by_category);
		
		categoryName = new String();
		categoryName = DEFAULT_CATEGORY;
		events = new ArrayList<Event>();
		// add categories in spinner	
		spinner = (Spinner) findViewById(R.id.agenda_category_spinner);
		// construct spinner item array by getting all categories from database
		CategoryManager manager = Manager.getInstance().getCategoryManager();
		ArrayList<Category> list = manager.readAllCategory();
		Iterator<Category> itr = list.iterator();
		ArrayList<String> options = new ArrayList<String>();
		options.add(EventActivity.DEFAULT_CATEGORY);
		Category c;
		String s;
		while (itr.hasNext()) {
			c = itr.next();
			s = c.getName();
			// exclude from showing the default category
			if (!(s.equalsIgnoreCase(DEFAULT_CATEGORY))) {
				options.add(s);
			}
		}

		// add spinner item array to spinner dynamically
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.agenda_category_select);
		RelativeLayout.LayoutParams create_params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams return_params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// add a create button to the relative layout dynamically
		Button create_button = new Button(this);
		create_button.setText(R.string.agenda_category);
		create_button.setOnClickListener(new View.OnClickListener() {

			@Override
			
			public void onClick(View v) {
				
//				Intent intent = new Intent(
//						AgendaByCategoryActivity.this,
//						AgendaViewActivity.class);
//				
//				intent.putExtra("selectedCategory",
//						categoryName);
//				startActivity(intent);
				onResume();
				
			}
		});
		
		create_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		create_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layout.addView(create_button, create_params);

		// add a return button to the relative layout dynamically
		Button return_button = new Button(this);
		return_button.setText(R.string.cancel);
		return_button.setOnClickListener(new View.OnClickListener() {

			@Override
			// return to last activity
			public void onClick(View v) {
				finish();
			}
		});
		return_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		return_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		layout.addView(return_button, return_params);
		
		this.agendaGridView = (GridView) findViewById(R.id.gridViewAgendaByCategory);

	}
	
	public void sortEvents( ArrayList<Event> events ) {
		Collections.sort(events, new Comparator<Event>(){
			  public int compare(Event e1, Event e2) {
			    return e1.getStartDate().compareTo(e2.getStartDate());
			  }
			});
		
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
		populateModels();
//		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//		getActionBar().setSelectedNavigationItem(4);
	}
	
	public void populateModels() {

		System.out.println("Agenda Category :: " + categoryName );
		events =(ArrayList<Event>)Manager.getInstance().getEventManager().readEventsByCategory(categoryName);
		System.out.println("Events :: " + events.size());
		if(events.size() > 1) {
			sortEvents(events);
		}	
		this.agendaGridView.setAdapter(new CustomAgendaGridAdaptor( AgendaByCategoryActivity.this, events));
		    
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long arg3) {
		categoryName = (String) parent.getItemAtPosition(pos);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
