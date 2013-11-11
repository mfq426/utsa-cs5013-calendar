package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

public class ModifyCategoryActivity extends Activity {
	
	private static final String PROMPT = "select category to delete";
	private static final String INFO = "No category to delete";
	private static final String DELETE = "delete";
	private static final String RETURN = "return";
	private static final String PROMPT2 = "please select category";
	private String categoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_category);
		
		final CategoryManager manager = ((GlobalVariables) this.getApplication()).getCategoryManager();
		ArrayList<Category> list = manager.readAllCategory();
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.delete_category_view);
		ArrayList<String> options = new ArrayList<String>();
		Category c;
		String s;
		
		if(list.size() > 0) {
			Iterator<Category> itr = list.iterator();
			while(itr.hasNext()) {
				c = itr.next();
				s = c.getName();
				if(!(s.equalsIgnoreCase("default"))) {
					options.add(s);
				}
			}
			
			RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			TextView tv = new TextView(this);
			tv.setId(1);
			tv.setText(PROMPT);
			params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
			
			Spinner spinner = new Spinner(this);
			spinner.setId(2);
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int pos, long id) {
						// TODO Auto-generated method stub
						categoryName = (String)parent.getItemAtPosition(pos);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
			});
			params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
			params2.addRule(RelativeLayout.BELOW, tv.getId());
			
			Button button1 = new Button(this);
			button1.setId(3);
			button1.setText(DELETE);
			button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(categoryName == null || categoryName.isEmpty()) {
						popup(PROMPT2);
					} else {
						manager.deleteCategory(categoryName);
						finish();
					}
				}
			});
			params3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			
			Button button2 = new Button(this);
			button2.setId(4);
			button2.setText(RETURN);
			button2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
					
				}
			});
			params4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			
			layout.addView(tv, params1);
			layout.addView(spinner, params2);
			layout.addView(button1, params3);
			layout.addView(button2, params4);
			
		} else {
			RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			TextView tv = new TextView(this);
			tv.setId(1);
			tv.setText(INFO);
			params1.addRule(RelativeLayout.CENTER_HORIZONTAL);

			Button button = new Button(this);
			button.setId(2);
			button.setText(RETURN);
			params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			layout.addView(tv, params1);
			layout.addView(button, params2);
		}
	}
	
	private void popup(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_category, menu);
		return true;
	}

}
