package com.trio.mentor9.adaptor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.database.sqlite.SQLiteDatabase;


import com.trio.mentor9.model.Quote;

public class QuoteAdaptor extends DataBaseHelper {
	public QuoteAdaptor(Context tx) {
		super(tx);
		// TODO Auto-generated constructor stub
	}

	private  final String table_name = "Quotes";

	private  final String AUTHOR = "quote_author";
	private  final String BODY = "quote_body";
	private  final String MYANMAR = "quote_myanmar";
	public  final String ROW_ID = "_id";
	public  final String TYPE = "_type";
	public  final String createTable_Quote = "create table " + table_name
			+ " ( " + ROW_ID + " int primary key, " + AUTHOR
			+ " text not null," + BODY + " text not null," + MYANMAR
			+ " text not null," + TYPE + " text not null)";
	public  final String SQL_DELETE_Quote = "DROP TABLE IF EXISTS "
			+ table_name;
	private  SQLiteDatabase mdb;

	private  final String FUNNY = "\'funny\'";
	private  final String A_T = "'a_t'";

	public  long insertQuote(int id, String author, String English,
			String Myanmar, String type) {
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(ROW_ID, id);
		valuesToInsert.put(AUTHOR, author);
		valuesToInsert.put(BODY, English);
		valuesToInsert.put(MYANMAR, Myanmar);
		valuesToInsert.put(TYPE, type);
		long idd = insert(table_name, valuesToInsert);
		return idd;
	}

	public  Quote selectWithId_Quote(long id) {
		String whereClause = ROW_ID + "=" + id;
		open();
		Cursor cc = select(table_name, whereClause);
		close();
		if (cc != null && cc.moveToFirst()) {
			Quote q = new Quote();
			int i = cc.getColumnIndex(AUTHOR);
			q.setQuote_author(cc.getString(cc.getColumnIndex(AUTHOR)));
			q.setQuote_body(cc.getString(cc.getColumnIndex(BODY)));
			q.setQuote_mm(cc.getString(cc.getColumnIndex(MYANMAR)));
			q.setQuote_type(cc.getString(cc.getColumnIndex(TYPE)));
			return q;
		}
		return null;
	}

	public  Quote selectA_T(long id) {
		String whereClause = ROW_ID + "=" + id + " and " + TYPE + "=" + A_T;
		// String whree = ROW_ID + "=" + id;
		open();
		Cursor cc = select(table_name, whereClause);
		close();
		if (cc != null) {
			boolean b = cc.moveToFirst();

			Quote q = new Quote();
			int i = cc.getColumnIndex(AUTHOR);
			q.setQuote_author(cc.getString(cc.getColumnIndex(AUTHOR)));
			q.setQuote_body(cc.getString(cc.getColumnIndex(BODY)));
			q.setQuote_mm(cc.getString(cc.getColumnIndex(MYANMAR)));
			q.setQuote_type(cc.getString(cc.getColumnIndex(TYPE)));
			return q;
		}
		return null;
	}

	public  Quote selectFunny(long id) {
		String whereClause = ROW_ID + "=" + id + " and " + TYPE + "= " + FUNNY;
		// String whree = ROW_ID + "=" + id;
		open();
		Cursor cc = select(table_name, whereClause);
		close();
		if (cc != null) {
			boolean b = cc.moveToFirst();

			Quote q = new Quote();
			int i = cc.getColumnIndex(AUTHOR);
			q.setQuote_author(cc.getString(cc.getColumnIndex(AUTHOR)));
			q.setQuote_body(cc.getString(cc.getColumnIndex(BODY)));
			q.setQuote_mm(cc.getString(cc.getColumnIndex(MYANMAR)));
			q.setQuote_type(cc.getString(cc.getColumnIndex(TYPE)));
			return q;
		}
		return null;
	}

	public  boolean updateQuote(String author, String body, String type,
			long Row_id) {
		ContentValues valuesToUpdate = new ContentValues();
		valuesToUpdate.put(AUTHOR, author);
		valuesToUpdate.put(BODY, body);
		valuesToUpdate.put(TYPE, type);
		String whereClause = ROW_ID + "=" + Row_id;
		return update(table_name, whereClause, valuesToUpdate);

	}

	public  boolean deleteQuote(long Row_id) {
		String whereClause = ROW_ID + "=" + Row_id;
		return delete(table_name, whereClause);

	}
}
