package com.utsa.cs5013.calendar.activity;

import android.widget.ArrayAdapter;

import java.util.List;

import com.utsa.calendar.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<Model> {

  private final List<Model> list;
  private final Activity context;

  public InteractiveArrayAdapter(Activity context, List<Model> list) {
    super(context, R.layout.activity_day, list);
    this.context = context;
    this.list = list;
  }

  static class ViewHolder {
	    protected TextView text1;
	    protected TextView text2;
	    protected CheckBox checkbox;
	  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = null;
    if (convertView == null) {
      LayoutInflater inflator = context.getLayoutInflater();
      view = inflator.inflate(R.layout.list_item, null);
      final ViewHolder viewHolder = new ViewHolder();
      viewHolder.text1 = (TextView) view.findViewById(R.id.textView1);
      viewHolder.text2 = (TextView) view.findViewById(R.id.textView2);
      
      view.setTag(viewHolder);
      //viewHolder.checkbox.setTag(list.get(position));
    } else {
      view = convertView;
      //((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
    }
    
    System.out.println(position +" "+ list.size());
    ViewHolder holder = (ViewHolder) view.getTag();
    if(list.size()>= position){
    	
    holder.text1.setText(list.get(position).getName());
    holder.text2.setText(list.get(position).getEvent());
    }
   /* if (position % 2 == 0)
		view.setBackgroundColor(0x30FF0000);
	else
		view.setBackgroundColor(0x300000FF);*/
    return view;
  }
} 
