package edu.utsa.calendar.test;

import java.util.List;

import edu.utsa.calendar.Category;
import edu.utsa.calendar.CategoryManager;
import edu.utsa.calendar.DatabaseHelper;
import android.test.AndroidTestCase;

public class CategoryManagerTest extends AndroidTestCase {
	private DatabaseHelper mDatabaseHelper = null;
	private CategoryManager mCategoryManager = null;
	@Override
	public void setUp() throws Exception {
		super.setUp();
		mDatabaseHelper = new DatabaseHelper(getContext(),"CalendarDBTest");
		mCategoryManager = new CategoryManager(mDatabaseHelper);
	}

	@Override
	protected void tearDown() throws Exception {
		mDatabaseHelper.close();

		super.tearDown();
	}

	public void testAddCategory() throws Exception {
		Category category = new Category(1,12345, "sports", "Sports");
		mCategoryManager.addCategory(category);
		List<Category> categoryList = mCategoryManager.readCategory("sports");
		assertEquals(1,categoryList.size());
		assertEquals("sports",categoryList.get(0).getName());
	}
	
	
	public void testDeleteCategory() throws Exception {
		
		List<Category> categoryList = mCategoryManager.readCategory("sports");
		mCategoryManager.deleteCategory(categoryList.get(0));
		categoryList = mCategoryManager.readCategory("sports");
		assertEquals(0,categoryList.size());
		
	}
	
	public void testUpdateCategoryName() throws Exception {
		Category category = new Category(1,12345, "sports", "Sports");
		mCategoryManager.addCategory(category);
		Category updatedCategory = new Category(1,12345, "IndoorSports", "IndoorSports");
		mCategoryManager.updateCategoryName(1, updatedCategory);
		List<Category> categoryList = mCategoryManager.readCategory("sports");
		assertEquals(0,categoryList.size());
		categoryList = mCategoryManager.readCategory("IndoorSports");
		assertEquals(1,categoryList.size());
	}
	
	public void testReadAllCategory() throws Exception {
		Category category = new Category(1,12345, "sports", "Sports");
		mCategoryManager.addCategory(category);
		category = new Category(2,23456, "meeting", "Meeting");
		mCategoryManager.addCategory(category);
		category = new Category(2,23456, "travel", "Travel");
		mCategoryManager.addCategory(category);
		
		List<Category> categoryList = mCategoryManager.readAllCategory();
		
		assertEquals(3,categoryList.size());
	}
	

	
}