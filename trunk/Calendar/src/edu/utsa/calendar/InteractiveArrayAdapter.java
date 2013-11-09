package edu.utsa.calendar;

import android.widget.ArrayAdapter;

import java.util.List;

import edu.utsa.calendar.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<DailyViewModel> {

	private final List<DailyViewModel> list;
	private final Activity context;

	public InteractiveArrayAdapter(Activity context, List<DailyViewModel> list) {
		super(context, R.layout.list_item, list);
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
			// viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			// ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		if (list.size() >= position) {
			DailyViewModel dailyViewModel = list.get(position);

			holder.text1.setText(dailyViewModel.getTimeLebel());
			List<Event> events = dailyViewModel.getEvents();
			if (events.size() > 0) {
				Event event = events.get(0);
				holder.text2.setText(event.getDescription());
			} else
				holder.text2.setText(dailyViewModel.getCreateLabel());
		}
		/*
		 * if (position % 2 == 0) view.setBackgroundColor(0x30FF0000); else
		 * view.setBackgroundColor(0x300000FF);
		 */
		return view;
	}

}
