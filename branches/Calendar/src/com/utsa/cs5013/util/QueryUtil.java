package com.utsa.cs5013.util;

import com.utsa.cs5013.entity.Category;
import com.utsa.cs5013.entity.Event;

public class QueryUtil {

	public static Query getDeleteQuery(Object pObj) {
		Query query = new Query();
		if (pObj instanceof Event) {
			query.setTableName("Event");
			Event event = (Event) pObj;
			query.setWhereClause("ID = ?");
			query.setWhereArgs(new String[] { String.valueOf(event.getID()) });
		} else if (pObj instanceof Category) {
			query.setTableName("Category");
			Category category = (Category) pObj;
			query.setWhereClause("ID = ?");
			query.setWhereArgs(new String[] { String.valueOf(category.getID()) });
		}

		return query;
	}

}
