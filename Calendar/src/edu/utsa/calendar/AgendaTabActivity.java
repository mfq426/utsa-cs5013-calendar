package edu.utsa.calendar;

import android.app.ActionBar;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class AgendaTabActivity extends CalendarActivity {
	LocalActivityManager mlam;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_tab);
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		mlam = new LocalActivityManager(this, false);
        mlam.dispatchCreate(savedInstanceState);
		tabHost.setup(mlam);

		TabSpec tab1 = tabHost.newTabSpec("First Tab");
		TabSpec tab2 = tabHost.newTabSpec("Second Tab");

		tab1.setIndicator("Date Filter");
		tab1.setContent(new Intent(this, AgendaByDateActivity.class));

		tab2.setIndicator("Category Filter");
		tab2.setContent(new Intent(this, AgendaByCategoryActivity.class));

		tabHost.addTab(tab1);
		tabHost.addTab(tab2);

		tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 55;
		tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 55;

	}
	
	@Override
    protected void onResume(){
        super.onResume();
        mlam.dispatchResume();
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getActionBar().setSelectedNavigationItem(4);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mlam.dispatchPause(isFinishing());
    }
    
    

}
