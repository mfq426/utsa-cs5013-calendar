package com.utsa.cs5013.entity;

import java.util.Date;

public class Category {
	private int mID;
	private String mName;
	private int mColor;
	private String mDescription;

	public int getID() {
		return mID;
	}

	public void setID(int pID) {
		mID = pID;
	}

	public String getName() {
		return mName;
	}

	public void setName(String pName) {
		mName = pName;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int pColor) {
		mColor = pColor;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String pDescription) {
		mDescription = pDescription;
	}

}
