package edu.utsa.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarEntryAdapter extends BaseAdapter {

	private Context context;
	private String[] text ={};
	
	
	public CalendarEntryAdapter(Context c,String[] text){
		
		
		context = c;
		this.text = text;
		
	}

	public int getCount(){
		
		return text.length;
	}

	public Object getItem(int position){
		
		return null;
	}
	
	public long getItemId(int position){
		
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
		View gridView;
	     
        if (convertView == null) {
 
            gridView = new View(context);
 
            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.grid_entry , null);
 
            // set value into textview
             
            TextView textView = (TextView) gridView
                    .findViewById(R.id.textViewGridEntry);

            textView.setText(text[position]);
            //textView.setBackgroundColor(Color.parseColor("#d3d3d3"));
 
             
        } else {

           gridView = (View) convertView;
        }
 
        return gridView;
	}
	
}
