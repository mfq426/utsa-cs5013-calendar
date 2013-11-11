package edu.utsa.calendar;

import java.util.ArrayList;
import java.util.List;

public class DailyViewModel {
	private List<Event> mEvents = new ArrayList<Event>();
	private String mTimeLebel;
	
	private String sCreateLabel;

	public void addEvent(Event pEvent) {
		mEvents.add(pEvent);
	}

	public List<Event> getEvents() {
		return mEvents;
	}
	public void setTimeLebel(String pTimeLebel) {
		mTimeLebel = pTimeLebel;
	}

	public String getTimeLebel() {
		return mTimeLebel;
	}
	
	public String getCreateLabel() {
		return sCreateLabel;
	}
	
	public void setCreateLabel(String pCreateLabel) {
		sCreateLabel = pCreateLabel;
	}
	
	public void removeEvents(){
		mEvents.clear();
	}
}
