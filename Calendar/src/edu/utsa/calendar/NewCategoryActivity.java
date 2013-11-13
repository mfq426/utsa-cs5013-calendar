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

/**
 * NewCategoryActivity in responsible of create category on user's demand
 * @author Lu Liu
 */
public class NewCategoryActivity extends Activity {
	
	private String name;
	private String description;
	private int color;
	
	private final static String NAME_IS_TAKEN = "category name is taken";
	private final static String INCOMPLETE_USER_INPUT = "incomplete user input";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_category);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_category, menu);
		return true;
	}
	
	// collect user inputs
	private void getData() {
		EditText editText = (EditText)findViewById(R.id.name_text);
		name = editText.getText().toString();
		
		editText = (EditText)findViewById(R.id.description_text);
		description = editText.getText().toString();
		
		ColorPicker picker = (ColorPicker) findViewById(R.id.color_picker);
		color = picker.getColor();
	}
	
	// verify user inputs
	private boolean verifyData() {
		if(name == null || name.isEmpty() || description == null || description.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * create category by go through getting user inputs, verifying user inputs and resolving category name conflict
	 * @param creat category button
	 */
	public void createCategory(View view) {
		getData();
		if(verifyData()) {
			CategoryManager manager = Manager.getInstance().getCategoryManager();
			ArrayList<Category> list = manager.readCategory(name);
			if(list.size()>0) {
				popup(NAME_IS_TAKEN);
			} else {
				Category c = new Category(color, name, description);
				manager.addCategory(c);
				finish();
			}
		} else {
			popup(INCOMPLETE_USER_INPUT);
		}
	}
	
	// give user feedback when something goes wrong
	private void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	/**
	 * return to the last activity
	 * @param return button
	 */
	public void cancel(View view) {
		finish();
	}

}
