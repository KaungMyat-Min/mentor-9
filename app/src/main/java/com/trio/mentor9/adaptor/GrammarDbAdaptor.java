package com.trio.mentor9.adaptor;

import java.util.ArrayList;

import com.trio.mentor9.model.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class GrammarDbAdaptor extends DataBaseHelper{

	public GrammarDbAdaptor(Context tx) {
		super(tx);
		// TODO Auto-generated constructor stub
	}

	private static final String table_name = "Grammar";

	private  final String QUES = "question";
	private  final String ANS_true = "answer";
	private  final String ANS_fake1 = "answer_fake1";
	private  final String ANS_fake2 = "answer_fake2";
	private  final String LOCATION = "location";
	private  final String ROW_ID = "_id";
	private  final String TYPE = "_type";// question type = grammer
	private  final String GRAMMAR_TYPE = "_GRAMMAR_TYPE";
	private  final String ATTEMPT = "attempt";
	private  final String SUCCESS = "success";
	private  final String TYPE_GRAMMAR = "grammar";
	
	/*public static final String G_DIRECT_INDIRECT = "direct_indirect";
	public static final String G_ACTIVE_PASSIVE="active_passive";
	public static final String G_IF_UNLESS="if_unless";
	public static final String G_AFTER="after";
	public static final String G_WITHOUT_VING="without_ving";
	public static final String G_NO_SOONER_THAN="no_sooner_than";
	public static final String G_ALTHOUGH_IN_SPITE_OF="although_in_spite_of";
	public static final String G_SO_THAT_SUCH_THAT="so_that_such_that";
	public static final String G_TOO_TO_NOT_ENOUGH_TO="too_to_not_enough_to";
	public static final String G_WHEN_BY_THE_TIME="when_by_the_time";
	public static final String G_Ving="ving";
	public static final String G_NOUNS_IN_APPOISITION="nouns_in_apposition";
	public static final String G_IT_IS_IT_WAS="it_is_it_was";
	public static final String G_IT_TAKE_IT_TOOK="it_take_it_took";
	public static final String G_NOUN_WHO_WHICH="noun_who_which";
	public static final String G_NOT_ONLY_BUT_ALSO="not_only_but_also";
	public static final String G_NEITHER="neither";
	public static final String G_BOTH_AND="both_and";
	public static final String G_EITHER_OR="either_or";
	public static final String G_THE_MORE_THE_LESS_PATTERN="the_more_the_less_pattern";
	public static final String G_THERE_IS_NO_NOUN_THAT="there_is_no_noun_that";
	public static final String G_THERE_IS_NO_AS_AS="there_is_as_as";
	public static final String G_ER_THAN_2_NOT_AS_AS="er_than_2_not_as_as";
	public static final String G_AS_AS="as_as";
	public static final String G_ONE_OF_THE_EST="one_of_the_est";
	public static final String G_VERB_ER_THAN="verb_er_than";
	public static final String G_ER_TAHN_MOST="er_than_most";*/

	private  final String QUES_ID = "question_id";
	private  final String createGrammarTable = "create table "
			+ table_name + " ( " + ROW_ID + " int primary key, " + QUES_ID
			+ " text not null," + GRAMMAR_TYPE + " int not null," + TYPE
			+ " text not null," + QUES + " text not null," + ANS_true
			+ " text not null," + ANS_fake1 + " text," + ANS_fake2 + " text,"
			+ ATTEMPT + " int," + SUCCESS + " int," + LOCATION
			+ " text not null)";

	private  final String dropGrammarTable = "DROP TABLE IF EXISTS "
			+ table_name;
	// private  SQLiteDatabase mdb;
	// private  Context mctx;
	// private DataBaseHelper mdbHelper;

	private  ArrayList<Question> array;

	// modification starts here

	/*
	 * public AnsTheQuestionDbAdaptor(Context tx) { mctx = tx; }
	 */

	/*
	 * public void close() { if (mdb != null && !mdb.isOpen()) mdb.close(); }
	 * 
	 * public AnsTheQuestionDbAdaptor open() throws SQLException { mdbHelper =
	 * new DataBaseHelper(mctx); if (mdb == null || !mdb.isOpen()) mdb =
	 * mdbHelper.getWritableDatabase();
	 * 
	 * return this; }
	 */
	public  long insertExercise(int id, String Ques_id,
			int grammar_type, String Question, String Answer,
			String Answer_fake1, String Answer_fake2, String Location) {
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(ROW_ID, id);
		valuesToInsert.put(QUES_ID, Ques_id);
		valuesToInsert.put(GRAMMAR_TYPE, grammar_type);
		valuesToInsert.put(TYPE, TYPE_GRAMMAR);
		valuesToInsert.put(QUES, Question);
		valuesToInsert.put(ANS_true, Answer);
		valuesToInsert.put(ANS_fake1, Answer_fake1);
		valuesToInsert.put(ANS_fake2, Answer_fake2);
		valuesToInsert.put(LOCATION, Location);
		long idd = insert(table_name, valuesToInsert);

		return idd;
	}

	public  long insertwithTrueAnsOnly(int id, String Ques_id,
			String unitNo, int type, String Question, String Answer,
			String Location) {
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(ROW_ID, id);
		valuesToInsert.put(QUES_ID, Ques_id);
		valuesToInsert.put(GRAMMAR_TYPE, unitNo);
		valuesToInsert.put(TYPE, type);
		valuesToInsert.put(QUES, Question);
		valuesToInsert.put(ANS_true, Answer);
		valuesToInsert.put(LOCATION, Location);
		long idd =insert(table_name, valuesToInsert);
		return idd;
	}

	public  ArrayList<Question> selectGrammarWithUnit(int grammarType) {

		String whereClause = GRAMMAR_TYPE + " = \'" + grammarType
				+ "\' order by " + LOCATION;
		open();
		Cursor cc = select(table_name, whereClause);

		close();
		if (cc != null) {
			boolean b = cc.moveToFirst();
			array = new ArrayList<Question>();
			while (!cc.isAfterLast()) {
				Question ques = new Question();
				ques.setROW_ID(cc.getString(cc.getColumnIndex(ROW_ID)));
				ques.setTYPE(cc.getString(cc.getColumnIndex(TYPE)));
				ques.setUNIT_NO(cc.getString(cc.getColumnIndex(GRAMMAR_TYPE)));
				ques.setQUES(cc.getString(cc.getColumnIndex(QUES)));
				ques.setANS_true(cc.getString(cc.getColumnIndex(ANS_true)));
				ques.setANS_fake1(cc.getString(cc.getColumnIndex(ANS_fake1)));
				ques.setANS_fake2(cc.getString(cc.getColumnIndex(ANS_fake2)));
				ques.setLOCATION(cc.getString(cc.getColumnIndex(LOCATION)));
				ques.setSUCCESS(cc.getInt(cc.getColumnIndex(SUCCESS)));
				ques.setATTEMPT(cc.getInt(cc.getColumnIndex(ATTEMPT)));
				ques.setQUE_ID((cc.getString(cc.getColumnIndex(QUES_ID))));
				array.add(ques);
				cc.moveToNext();
			}
			cc.close();
			return array;

		}
		return null;

	}

	public  ArrayList<Question> selectGrammarWithRegion(int unitNo,
			String Region) {
		String whereClause = LOCATION + "like \'%" + Region + "%\' and "
				+ GRAMMAR_TYPE + " = \'" + unitNo + "\' order by " + LOCATION;
		open();
		Cursor cc = select(table_name, whereClause);
close();
		if (cc != null) {
			boolean b = cc.moveToFirst();

			array = new ArrayList<Question>();
			while (!cc.isAfterLast()) {
				Question ques = new Question();
				ques.setROW_ID(cc.getString(cc.getColumnIndex(ROW_ID)));
				ques.setTYPE(cc.getString(cc.getColumnIndex(TYPE)));
				ques.setUNIT_NO(cc.getString(cc.getColumnIndex(GRAMMAR_TYPE)));
				ques.setQUES(cc.getString(cc.getColumnIndex(QUES)));
				ques.setANS_true(cc.getString(cc.getColumnIndex(ANS_true)));
				ques.setANS_fake1(cc.getString(cc.getColumnIndex(ANS_fake1)));
				ques.setANS_fake2(cc.getString(cc.getColumnIndex(ANS_fake2)));
				ques.setLOCATION(cc.getString(cc.getColumnIndex(LOCATION)));
				ques.setSUCCESS(cc.getInt(cc.getColumnIndex(SUCCESS)));
				ques.setATTEMPT(cc.getInt(cc.getColumnIndex(ATTEMPT)));
				ques.setQUE_ID((cc.getString(cc.getColumnIndex(QUES_ID))));
				array.add(ques);
				cc.moveToNext();
			}
			cc.close();
			return array;
		}
		return null;

	}

	public  ArrayList<Question> selectGrammarWithYear(int unitNo,
			String year) {
		String whereClause = LOCATION + "like \'%" + year + "%\' and "
				+ GRAMMAR_TYPE + " = \'" + unitNo + "\' order by " + LOCATION;
open();
		Cursor cc = select(table_name, whereClause);
close();
		if (cc != null) {

			boolean b = cc.moveToFirst();
			array = new ArrayList<Question>();
			while (!cc.isAfterLast()) {
				Question ques = new Question();
				ques.setROW_ID(cc.getString(cc.getColumnIndex(ROW_ID)));
				ques.setTYPE(cc.getString(cc.getColumnIndex(TYPE)));
				ques.setUNIT_NO(cc.getString(cc.getColumnIndex(GRAMMAR_TYPE)));
				ques.setQUES(cc.getString(cc.getColumnIndex(QUES)));
				ques.setANS_true(cc.getString(cc.getColumnIndex(ANS_true)));
				ques.setANS_fake1(cc.getString(cc.getColumnIndex(ANS_fake1)));
				ques.setANS_fake2(cc.getString(cc.getColumnIndex(ANS_fake2)));
				ques.setLOCATION(cc.getString(cc.getColumnIndex(LOCATION)));
				ques.setSUCCESS(cc.getInt(cc.getColumnIndex(SUCCESS)));
				ques.setATTEMPT(cc.getInt(cc.getColumnIndex(ATTEMPT)));
				ques.setQUE_ID((cc.getString(cc.getColumnIndex(QUES_ID))));
				array.add(ques);
				cc.moveToNext();
			}
			cc.close();
			return array;
		}
		return null;

	}

	public  boolean updateGrammarAttempt(String QuestionId, int attempt,
			int success) {
		ContentValues valuesToUpdate = new ContentValues();

		valuesToUpdate.put(ATTEMPT, attempt);
		valuesToUpdate.put(SUCCESS, SUCCESS);
		String whereClause = QUES_ID + " = \'" + QuestionId+"\'";
		return update(table_name, whereClause, valuesToUpdate);

	}

	public  boolean deleteTask(long Row_id) {
		String whereClause = ROW_ID + "=" + Row_id;
		return delete(table_name, whereClause);

	}

	public  String getGrammarCreatetable() {
		return createGrammarTable;
	}

	public  String getGrammarDroptable() {
		return dropGrammarTable;
	}

}
