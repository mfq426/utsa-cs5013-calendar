package edu.utsa.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

public class AgendaViewActivity extends Activity {
	
	    
    //TODO add eventlist member
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_agenda_view);
		
		
		GridView agendaGridView = (GridView) findViewById(R.id.gridAgendaView);
		agendaGridView.setPadding(8, 8, 8, 8);
		agendaGridView.setAdapter(new CustomAgendaGridAdaptor( AgendaViewActivity.this));
		
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_view, menu);
		return true;
	}

}
