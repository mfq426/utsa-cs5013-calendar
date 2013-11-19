package edu.utsa.calendar;

/**
 * 
 * @author Mejbah : entity class for event categories
 *
 */

public class Category {
	private int mID;
	private String mName;
	private int mColor;
	private String mDescription;
 /**
  * Category Constructor
  * 
  * @param mID id of the category
  * @param mColor color of the category
  * @param mName name of the category
  * @param mDescription description of the category
  */
	public Category(int mID,  int mColor, String mName, String mDescription) {
		super();
		this.mID = mID;
		this.mName = mName;
		this.mColor = mColor;
		this.mDescription = mDescription;
	}
	
	
	public Category( int mColor, String mName, String mDescription ) {
		super();
		this.mID = -1;
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

	/**
     * set name to the category 
     * @param pName name of category
     */
	public void setName(String pName) {
		mName = pName;
	}

	public int getColor() {
		return mColor;
	}

	/**
     * set color to the category 
     * @param pColor color of category
     */
	public void setColor(int pColor) {
		mColor = pColor;
	}

	public String getDescription() {
		return mDescription;
	}
    
	/**
     * set the category description
     * @param pDescription description of category
     */
	public void setDescription(String pDescription) {
		mDescription = pDescription;
	}

}
