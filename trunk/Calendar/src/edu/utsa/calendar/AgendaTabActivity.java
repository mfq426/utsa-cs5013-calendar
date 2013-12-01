package edu.utsa.calendar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class AgendaTabActivity extends TabActivity implements OnTabChangeListener{

	
	  @Override
      public void onCreate(Bundle savedInstanceState)
      {
              super.onCreate(savedInstanceState);
              
              setContentView(R.layout.activity_tab);

              TabHost tabHost =  getTabHost();


              TabSpec tab1 = tabHost.newTabSpec("First Tab");
              TabSpec tab2 = tabHost.newTabSpec("Second Tab");

            
              tab1.setIndicator("Date Filter");
              tab1.setContent(new Intent(this,NewEventActivity.class));
              
              tab2.setIndicator("Category Filter");
              tab2.setContent(new Intent(this,NewEventActivity.class));

           
              tabHost.addTab(tab1);
              tabHost.addTab(tab2);
              
              tabHost.getTabWidget().getChildAt(0).getLayoutParams().height =55;
              tabHost.getTabWidget().getChildAt(1).getLayoutParams().height =55;

      }

	
	@Override
	public void onTabChanged(String pTabId) {
		// TODO Auto-generated method stub
		
	}

}
