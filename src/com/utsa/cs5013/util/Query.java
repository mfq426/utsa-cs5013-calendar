package com.utsa.cs5013.util;

public class Query {
	
	private String mTableName;
	private String mWhereClause;
	private String[] mWhereArgs;
	private boolean isError;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean pIsError) {
		isError = pIsError;
	}

	public String getTableName() {
		return mTableName;
	}

	public void setTableName(String pTableName) {
		mTableName = pTableName;
	}

	public String getWhereClause() {
		return mWhereClause;
	}

	public void setWhereClause(String pWhereClause) {
		mWhereClause = pWhereClause;
	}

	public String[] getWhereArgs() {
		return mWhereArgs;
	}

	public void setWhereArgs(String[] pWhereArgs) {
		mWhereArgs = pWhereArgs;
	}

}
