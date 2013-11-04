package edu.utsa.calendar;


public class Event {
	private int mID;
	private java.util.Calendar mStartDate;
	private java.util.Calendar mEndDate;
	private int mCategoryID;
	private String mDescription;

	public int getID() {
		return mID;
	}

	public void setID(int pID) {
		mID = pID;
	}

	public Event(int mID, java.util.Calendar mStartDate, java.util.Calendar mEndDate, int mCategoryID,
			String mDescription) {
		super();
		this.mID = mID;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mCategoryID = mCategoryID;
		this.mDescription = mDescription;
	}

	public java.util.Calendar getStartDate() {
		return mStartDate;
	}

	public void setStartDate(java.util.Calendar pStartDate) {
		mStartDate = pStartDate;
	}

	public java.util.Calendar getEndDate() {
		return mEndDate;
	}

	public void setEndDate(java.util.Calendar pEndDate) {
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

