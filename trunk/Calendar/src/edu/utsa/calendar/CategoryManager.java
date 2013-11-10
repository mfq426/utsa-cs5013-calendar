package edu.utsa.calendar;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class CategoryManager implements Serializable{

	private DatabaseHelper storageHandler;
	private static final long serialVersionUID = 0L;
	
	public CategoryManager(DatabaseHelper storageHandler) {
		super();
		this.storageHandler = storageHandler;
	}
	
	/**
	 * Add a new/conflicting category to the database
	 * @param category
	 */
	public void addCategory( Category c ){
		
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		
		ContentValues values = new ContentValues();
		values.put(storageHandler.getCategoryType(), c.getName());
		values.put(storageHandler.getCategoryColor(), c.getColor());
		values.put(storageHandler.getCategoryDescription(), c.getDescription());
				 
		// Inserting Row
		db.insert(storageHandler.getCategoryTableName(), null, values);
		db.close(); // Closing database connection	
			
	
	}
	
	/**
	 * Delete the category in DB with the same name
	 * @param Category object with all field initialized
	 */
	
	public void deleteCategory( Category c ){
		
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		// Deleting Row
		db.delete(storageHandler.getCategoryTableName(), 
	            storageHandler.getCategoryType() + "= " + c.getName(), 
	            null);
		db.close(); // Closing database connection	
			
	
	}
	
	/**
	 * Delete the category in DB with "name"
	 * @param Category object with all field initialized
	 */
	
	public void deleteCategory( String name ){
		
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		// Deleting Row
		db.delete(storageHandler.getCategoryTableName(), 
	            storageHandler.getCategoryType() + "= " + name, 
	            null);
		db.close(); // Closing database connection	
			
	
	}
	
	/**
	 * Delete the category in DB with color = "color"
	 * @param Category object with all field initialized
	 */
	
	public void deleteCategory( int color ){
		
		SQLiteDatabase db = storageHandler.getWritableDatabase();
		 
		// Deleting Row
		db.delete(storageHandler.getCategoryTableName(), 
	            storageHandler.getCategoryColor() + "= " + color, 
	            null);
		db.close(); // Closing database connection	
			
	
	}
	
	/**
	 *Reads the categories in database has name = "name" 
	 * @param name/type of the categoy
	 * @return arraylist of categories with type "name", list size is 0 if no such category
	 */
	public ArrayList<Category> readCategory( String name ) {
		ArrayList<Category>categoryList = new ArrayList<Category>();
		String selectQuery = "SELECT  * FROM " + storageHandler.getCategoryTableName()+ 
				" WHERE " +storageHandler.getCategoryType()+ " = " + "\"" + name + "\"";
		SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
            do {
			
            	Category c = new Category( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), name, cursor.getString(3));
			
            	categoryList.add(c);
            } while(cursor.moveToNext());
		}
		
		return categoryList;
		
	}
	
	/**
	 *Reads the categories in database has name = "name" 
	 * @param name/type of the categoy
	 * @return arraylist of categories with type "name", list size is 0 if no such category
	 */
	public ArrayList<Category> readCategory( int color ) {
		ArrayList<Category>categoryList = new ArrayList<Category>();
		String selectQuery = "SELECT  * FROM " + storageHandler.getCategoryTableName()+ 
				" WHERE " +storageHandler.getCategoryType()+ " = " + "\"" + color + "\"";
		SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
            do {
			
            	Category c = new Category( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
			
            	categoryList.add(c);
            } while(cursor.moveToNext());
		}
		
		return categoryList;
		
	}
	/**
	 * reads all the categories from the database
	 * @return list of all the existing categories
	 */
	
	public ArrayList<Category> readAllCategory( ) {
		ArrayList<Category>categoryList = new ArrayList<Category>();
		String selectQuery = "SELECT  * FROM " + storageHandler.getCategoryTableName();
		SQLiteDatabase db = storageHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
            do {
			
            	Category c = new Category( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
			
            	categoryList.add(c);
            } while(cursor.moveToNext());
		}
		
		return categoryList;
		
	}
	
	
}
