package edu.utsa.calendar;

import java.io.Serializable;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class CategoryManager implements Serializable{

	private DatabaseHelper storageHandler;
	private static final long serialVersionUID = 0L;
	
	public CategoryManager(DatabaseHelper storageHandler) {
		super();
		this.storageHandler = storageHandler;
	}

	public void createCategory(String mName, int mColor, String mDescription){
		
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		ContentValues values = new ContentValues();
		//values.put(DatabaseHelper., mName); // Contact Name
		//values.put(KEY_COLOR, mColor);
		//values.put(KEY_DESCRIPTION, mDescription);// Contact Phone Number
		 
		// Inserting Row
		//db.insert(, null, values);
		db.close(); // Closing database connection
			
	
	}
}
