package edu.utsa.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarEntryAdapterWeek extends BaseAdapter {

	private Context context;
	private String[] text ={};
	private int[] color;
	
	
	public CalendarEntryAdapterWeek(Context c,String[] text, int[] color){
		
		
		context = c;
		this.text = text;
		this.color = color;
		
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
		TextView textView;
	     
        if (convertView == null) {
 
            gridView = new View(context);
 
            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.grid_entry_week , null);
 
            //textView.setBackgroundColor(Color.parseColor("#d3d3d3"));
 
             
        } else {

           gridView = (View) convertView;
        }
        // set value into textview
        textView = (TextView) gridView
                .findViewById(R.id.textViewGridEntryWeek);
        textView.setText(text[position]);
        if(color[position]!=-1){
        	System.out.println(color[position]);
        	textView.setBackgroundColor(color[position]);
        	
        }
        return gridView;
	}
	
}
