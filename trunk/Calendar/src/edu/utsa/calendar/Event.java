package edu.utsa.calendar;

import java.util.Calendar;


/**
 * This is the entity class for event and store all the necessary information
 * for event
 * 
 * @author Jamiul, Mostafa, Mejbah
 * 
 */

public class Event {
	private int mID;
	private java.util.Calendar mStartDate;
	private java.util.Calendar mEndDate;
	private String mCategoryID;
	private String mDescription;
	private int eColor;
	private int totalOccurance;
	private int occuranceIndex;

	/**
	 * Constructor
	 * 
	 * @param mID
	 *            event id
	 * @param mStartDate
	 *            event start date
	 * @param mEndDate
	 *            event end date
	 * @param mCategoryID
	 *            event category name
	 * @param mDescription
	 *            event description
	 * @param totalOccurance
	 *            for weekly repeating events, represents how many time it will
	 *            occur
	 * @param occuranceIndex
	 *            for weekly repeating events, represents the index among the
	 *            repeating events
	 */
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
		this.eColor = -1; // set the color of events to -1. It will populate
							// when reading from the database
	}

	/**
	 * Constructor
	 * 
	 * @param mStartDate
	 *            event start date
	 * @param mEndDate
	 *            event end date
	 * @param mCategoryID
	 *            event category name
	 * @param mDescription
	 *            event description
	 * @param totalOccurance
	 *            for weekly repeating events, represents how many time it will
	 *            occur
	 * @param occuranceIndex
	 *            for weekly repeating events, represents the index among the
	 *            repeating events
	 */
	public Event(Calendar mStartDate, Calendar mEndDate, String mCategoryID,
			String mDescription, int totalOccurance, int occuranceIndex) {
		super();
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mCategoryID = mCategoryID;
		this.mDescription = mDescription;
		this.totalOccurance = totalOccurance;
		this.occuranceIndex = occuranceIndex;
		this.eColor = -1;
		this.mID = -1;
	}

	public java.util.Calendar getStartDate() {
		return mStartDate;
	}

	public void setStartDate(java.util.Calendar mStartDate) {
		this.mStartDate = mStartDate;
	}

	public java.util.Calendar getEndDate() {
		return mEndDate;
	}

	public void setEndDate(java.util.Calendar mEndDate) {
		this.mEndDate = mEndDate;
	}

	public String getCategoryID() {
		return mCategoryID;
	}

	public void setCategoryID(String mCategoryID) {
		this.mCategoryID = mCategoryID;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public int getColor() {
		return eColor;
	}

	public void setColor(int eColor) {
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

	public int getID() {
		return mID;
	}

	public void setID(int pID) {
		mID = pID;
	}

}
