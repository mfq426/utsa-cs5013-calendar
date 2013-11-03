package com.utsa.cs5013.entity;

import java.util.Date;

public class Event {
	private int mID;
	private Date mStartDate;
	private Date mEndDate;
	private int mCategoryID;
	private String mDescription;

	public int getID() {
		return mID;
	}

	public void setID(int pID) {
		mID = pID;
	}

	public Date getStartDate() {
		return mStartDate;
	}

	public void setStartDate(Date pStartDate) {
		mStartDate = pStartDate;
	}

	public Date getEndDate() {
		return mEndDate;
	}

	public void setEndDate(Date pEndDate) {
		mEndDate = pEndDate;
	}

	public int getCategoryID() {
		return mCategoryID;
	}

	public void setCategoryID(int pCategoryID) {
		mCategoryID = pCategoryID;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String pDescription) {
		mDescription = pDescription;
	}

	
	
}
