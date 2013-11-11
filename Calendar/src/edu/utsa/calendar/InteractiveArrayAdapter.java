package edu.utsa.calendar;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.calendar.R;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class InteractiveArrayAdapter extends ArrayAdapter<DailyViewModel> {

	private final List<DailyViewModel> list;
	private final Activity context;
	private List<ArrayAdapter<Event>> mAdapterList = new ArrayList<ArrayAdapter<Event>>();

	public InteractiveArrayAdapter(Activity context, List<DailyViewModel> list) {
		super(context, R.layout.list_item, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text1;
		protected View grid;
		protected CheckBox checkbox;
	}
	
	static class ViewHolderEvent {
		protected TextView text;
		
	}
	
	public List<ArrayAdapter<Event>> getAdapterList() {
		return mAdapterList;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.list_item, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text1 = (TextView) view.findViewById(R.id.textView1);
			GridView gView =(GridView) view.findViewById(R.id.dailyEventlist);
			
			gView.setNumColumns(3);
			
			final DailyViewModel dailyViewModel = list.get(position);
			final ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this.context,
					R.layout.event_list_entry, dailyViewModel.getEvents()){
				
				@Override
				public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
					View localView = null;
					if(pConvertView == null){
						LayoutInflater inflator = context.getLayoutInflater();
						localView = inflator.inflate(R.layout.event_list_entry, null);
						ViewHolderEvent viewHolder = new ViewHolderEvent();
						viewHolder.text = (TextView) localView.findViewById(R.id.textViewEvent);
						localView.setTag(viewHolder);
					}else{
						localView = pConvertView;
					}
					ViewHolderEvent localHolder = (ViewHolderEvent)localView.getTag();
					if(dailyViewModel.getEvents().size()==0){
						localHolder.text.setText(dailyViewModel.getCreateLabel());
					}
					if(dailyViewModel.getEvents().size() >= pPosition){
						Event item = getItem(pPosition);
						localHolder.text.setText(item.getDescription());
						localHolder.text.setBackgroundColor(item.getColor());
					}
					return localView;
				}
			};
			mAdapterList.add(adapter);
			gView.setAdapter(adapter);
	 
			gView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
					Event gridEvent = adapter.getItem(position);
					if(gridEvent!=null){
						if(gridEvent.getID()==-100){
							Intent intent = new Intent(context, NewEventActivity.class);
							
							context.startActivity(intent);
						}else{
							Intent intent = new Intent(context, ModifyEventActivity.class);
							intent.putExtra("event_id", gridEvent.getID());
							context.startActivity(intent);
						}
				   Toast.makeText(getContext(),	gridEvent.getDescription(), Toast.LENGTH_SHORT).show();
					}
					else{
						
					}
					
					adapter.notifyDataSetChanged();
				}
			});
			viewHolder.grid = gView;
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
				//Event event = events.get(0);
				//holder.text2.setText(event.getDescription());
			} else{
				/* TextView textview = new TextView(context);
				textview.setText(dailyViewModel.getCreateLabel());
				holder.grid = textview;*/
			}
		}
		/*
		 * if (position % 2 == 0) view.setBackgroundColor(0x30FF0000); else
		 * view.setBackgroundColor(0x300000FF);
		 */
		return view;
	}

}
