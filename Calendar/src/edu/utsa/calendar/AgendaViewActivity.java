package edu.utsa.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class AgendaViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_view, menu);
		return true;
	}

}
