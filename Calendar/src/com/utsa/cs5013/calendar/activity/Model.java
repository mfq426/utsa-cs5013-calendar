package com.utsa.cs5013.calendar.activity;

public class Model {

	private String name;
	private boolean selected;
	private String event = "";

	public String getEvent() {
		return event;
	}

	public void setEvent(String pEvent) {
		event = pEvent;
	}

	public Model(String name) {
		this.name = name;
		selected = false;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}