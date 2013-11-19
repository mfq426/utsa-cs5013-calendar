package edu.utsa.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

/**
 * <p>
 * This is the Class for Details Agenda View of Calendar. 
 * It shows details information of agenda.
 */
 
public class AgendaDetailsActivity extends Activity {

	private String details;
	private ListView listAgendaDetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_details);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    details = extras.getString("event_details");
		}
		TextView descriptionText = new TextView(this);
		descriptionText.setText(String.valueOf(details));
		listAgendaDetails = (ListView) findViewById(R.id.listAgendaDetailsView);
		listAgendaDetails.addView(descriptionText);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_details, menu);
		return true;
	}

}

