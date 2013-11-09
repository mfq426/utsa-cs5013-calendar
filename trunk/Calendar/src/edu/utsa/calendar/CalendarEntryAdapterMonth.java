package edu.utsa.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarEntryAdapterMonth extends BaseAdapter{

	private Context context;
	private String[] text ={};
	private String[] workIndicator = {};
	
	
	public CalendarEntryAdapterMonth(Context c,String[] text, String[] workIndicator){
		
		
		context = c;
		this.text = text;
		this.workIndicator = workIndicator;
		
		
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

            gridView = inflater.inflate( R.layout.grid_entry_month , null);
 
            //textView.setBackgroundColor(Color.parseColor("#d3d3d3"));
 
             
        } else {

           gridView = (View) convertView;
        }
        
        // set value into textview
        textView = (TextView) gridView
                .findViewById(R.id.textViewGridEntryMonth);
        textView.setText(text[position]);
        if(workIndicator[position].equals("1")){
        	textView.setBackgroundResource(R.drawable.circle);
        }
        else
        {
        	
        	// do nothing
        }
        
        return gridView;
	}
	
}
