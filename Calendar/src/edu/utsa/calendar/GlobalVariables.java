package edu.utsa.calendar;

import android.app.Application;

public class GlobalVariables extends Application{

	private EventManager eventManager;
	private CategoryManager categoryManager;
	
	public GlobalVariables() {
		
	}
	
	public EventManager getEventManager() {
		return eventManager;
	}
	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	public CategoryManager getCategoryManager() {
		return categoryManager;
	}
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

}
