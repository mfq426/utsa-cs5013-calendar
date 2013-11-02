package com.utsa.cs5013.calendar.activity;

import java.util.ArrayList;
import java.util.List;

import com.utsa.calendar.R;
import com.utsa.cs5013.calendar.activity.InteractiveArrayAdapter.ViewHolder;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	ArrayAdapter<Model> adapter = null;
	TextView previous = null;
	int preVposition = 0;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		adapter = new InteractiveArrayAdapter(this, getModel());
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		Model item = (Model) getListAdapter().getItem(position);

		item.setEvent(" + New Event");

		final InteractiveArrayAdapter.ViewHolder holder = (ViewHolder) v.getTag();
		holder.text2.setBackgroundResource(R.drawable.list_selector);
		if (preVposition == position) {
			Toast.makeText(v.getContext(), "Create Event", Toast.LENGTH_LONG).show();

		} else if (previous != null) {
			previous.setBackgroundResource(getResources().getColor(android.R.color.transparent));
			item = (Model) getListAdapter().getItem(preVposition);
			item.setEvent("");
			previous = holder.text2;
		} else {
			previous = holder.text2;
		}

		preVposition = position;

		adapter.notifyDataSetChanged();
	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		for (int i = 1; i < 25; i++) {
			String time = "";
			if (i < 12) {
				if (i < 10) {
					time = "0" + i + " " + "AM";
				} else
					time = i + " " + "AM";
			} else {
				time = i + " " + "PM";
			}
			list.add(get(time));

		}
		return list;
	}

	private Model get(String s) {
		return new Model(s);
	}

}
