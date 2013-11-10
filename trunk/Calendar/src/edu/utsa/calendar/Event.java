package edu.utsa.calendar;

import java.util.Calendar;


public class Event {
	private int mID;
	private java.util.Calendar mStartDate;//
	private java.util.Calendar mEndDate;//
	private String mCategoryID;//
	private String mDescription;//
	private int eColor;
	private int totalOccurance;//
	private int occuranceIndex;//
	
	public int getID() {
		return mID;
	}

	public void setID(int pID) {
		mID = pID;
	}

	// need to delete this constructor
	public Event(int mID, java.util.Calendar mStartDate, java.util.Calendar mEndDate, String mCategoryID,
			String mDescription) {
		super();
		this.mID = mID;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mCategoryID = mCategoryID;
		this.mDescription = mDescription;
	}

	public Event(int mID, Calendar mStartDate, Calendar mEndDate,
			String mCategoryID, String mDescription, int totalOccurance,
			int occuranceIndex) {
		super();
		this.mID = mID;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mCategoryID = mCategoryID;
		this.mDescription = mDescription;
		this.totalOccurance = totalOccurance;
		this.occuranceIndex = occuranceIndex;
	}

	public Event( Calendar mStartDate, Calendar mEndDate,
			String mCategoryID, String mDescription, int totalOccurance,
			int occuranceIndex) {
		super();
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mCategoryID = mCategoryID;
		this.mDescription = mDescription;
		this.totalOccurance = totalOccurance;
		this.occuranceIndex = occuranceIndex;
	}
	public int getmID() {
		return mID;
	}

	public void setmID(int mID) {
		this.mID = mID;
	}

	public java.util.Calendar getmStartDate() {
		return mStartDate;
	}

	public void setmStartDate(java.util.Calendar mStartDate) {
		this.mStartDate = mStartDate;
	}

	public java.util.Calendar getmEndDate() {
		return mEndDate;
	}

	public void setmEndDate(java.util.Calendar mEndDate) {
		this.mEndDate = mEndDate;
	}

	public String getmCategoryID() {
		return mCategoryID;
	}

	public void setmCategoryID(String mCategoryID) {
		this.mCategoryID = mCategoryID;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public int geteColor() {
		return eColor;
	}

	public void seteColor(int eColor) {
		this.eColor = eColor;
	}

	public int getTotalOccurance() {
		return totalOccurance;
	}

	public void setTotalOccurance(int totalOccurance) {
		this.totalOccurance = totalOccurance;
	}

	public int getOccuranceIndex() {
		return occuranceIndex;
	}

	public void setOccuranceIndex(int occuranceIndex) {
		this.occuranceIndex = occuranceIndex;
	}

		
}

