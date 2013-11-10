package edu.utsa.calendar;


public class Category {
	private int mID;
	private String mName;
	private int mColor;
	private String mDescription;

	public Category(int mID,  int mColor, String mName, String mDescription) {
		super();
		this.mID = mID;
		this.mName = mName;
		this.mColor = mColor;
		this.mDescription = mDescription;
	}

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
