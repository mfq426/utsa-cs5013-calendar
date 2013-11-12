package edu.utsa.calendar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.larswerkman.holocolorpicker.ColorPicker;

public class NewCategoryActivity extends Activity {
	
	private String name;
	private String description;
	private int color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_category);
		// Show the Up button in the action bar.
		//setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_category, menu);
		return true;
	}

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	private void getData() {
		EditText editText = (EditText)findViewById(R.id.name_text);
		name = editText.getText().toString();
		
		editText = (EditText)findViewById(R.id.description_text);
		description = editText.getText().toString();
		
		ColorPicker picker = (ColorPicker) findViewById(R.id.color_picker);
		color = picker.getColor();
	}
	
	private boolean verifyData() {
		if(name == null || name.isEmpty() || description == null || description.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void createCategory(View v) {
		getData();
		if(verifyData()) {
			CategoryManager manager = Manager.getInstance().getCategoryManager();
			ArrayList<Category> list = manager.readCategory(name);
			if(list.size()>0) {
				popup("category name is taken");
			} else {
				Category c = new Category(color, name, description);
				manager.addCategory(c);
				finish();
			}
		} else {
			popup("incomplete user input");
		}
	}
	
	private void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	public void cancel(View v) {
		finish();
	}

}
