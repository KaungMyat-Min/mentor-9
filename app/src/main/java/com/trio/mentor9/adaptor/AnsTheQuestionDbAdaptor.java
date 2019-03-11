package com.trio.mentor9.adaptor;

import java.util.ArrayList;

import com.trio.mentor9.model.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class AnsTheQuestionDbAdaptor extends DataBaseHelper {

	private  final String table_name = "AnswerTheQuestion";

	private final String QUES = "question";
	private final String ANS_true = "answer";
	private final String ANS_fake1 = "answer_fake1";
	private final String ANS_fake2 = "answer_fake2";
	//private final String LOCATION = "location";
	private final String ROW_ID = "_id";
	private final String TYPE = "_type";
	private final String UNIT_NO = "_UNIT_NO";
	private final String ATTEMPT = "attempt";
	private final String SUCCESS = "success";

	private final String QUES_ID = "question_id";
	private final String createTable = "create table " + table_name + " ( "
			+ ROW_ID + " int primary key, " + QUES_ID + " text not null,"
			+ UNIT_NO + " text not null," + TYPE + " text not null," + QUES
			+ " text not null," + ANS_true + " text not null," + ANS_fake1
			+ " text," + ANS_fake2 + " text," + ATTEMPT + " int," + SUCCESS
			+ " int)";

	private final String dropTable = "DROP TABLE IF EXISTS " + table_name;
	
	// private SQLiteDatabase mdb;
	// private Context mctx;
	// private DataBaseHelper mdbHelper;

	private ArrayList<Question> array;

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

	public AnsTheQuestionDbAdaptor(Context tx) {
		super(tx);
	}

	public long insertExercise(int id, String Ques_id, String unitNo,
			String type, String Question, String Answer, String Answer_fake1,
			String Answer_fake2) {
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(ROW_ID, id);
		valuesToInsert.put(QUES_ID, Ques_id);
		valuesToInsert.put(UNIT_NO, unitNo);
		valuesToInsert.put(TYPE, type);
		valuesToInsert.put(QUES, Question);
		valuesToInsert.put(ANS_true, Answer);
		valuesToInsert.put(ANS_fake1, Answer_fake1);
		valuesToInsert.put(ANS_fake2, Answer_fake2);
		
		long idd = insert(table_name, valuesToInsert);

		return idd;
	}

	public long insertwithTrueAnsOnly(int id, String Ques_id, String unitNo,
			String type, String Question, String Answer, String Location) {
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(ROW_ID, id);
		valuesToInsert.put(QUES_ID, Ques_id);
		valuesToInsert.put(UNIT_NO, unitNo);
		valuesToInsert.put(TYPE, type);
		valuesToInsert.put(QUES, Question);
		valuesToInsert.put(ANS_true, Answer);
		//valuesToInsert.put(LOCATION, Location);
		long idd = insert(table_name, valuesToInsert);
		return idd;
	}

	public ArrayList<Question> selectQuesWithUnit(int unitNo) {

		String whereClause = null;
		if (unitNo <10){
		whereClause = UNIT_NO + " = \'0" + unitNo + "\'";
		}else{
			whereClause = UNIT_NO + " = \'" + unitNo + "\'";
		}
		
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
				ques.setUNIT_NO(cc.getString(cc.getColumnIndex(UNIT_NO)));
				ques.setQUES(cc.getString(cc.getColumnIndex(QUES)));
				ques.setANS_true(cc.getString(cc.getColumnIndex(ANS_true)));
				ques.setANS_fake1(cc.getString(cc.getColumnIndex(ANS_fake1)));
				ques.setANS_fake2(cc.getString(cc.getColumnIndex(ANS_fake2)));
				//ques.setLOCATION(cc.getString(cc.getColumnIndex(LOCATION)));
				ques.setSUCCESS(cc.getInt(cc.getColumnIndex(SUCCESS)));
				ques.setATTEMPT(cc.getInt(cc.getColumnIndex(ATTEMPT)));
				ques.setQUE_ID(cc.getString(cc.getColumnIndex(QUES_ID)));
				array.add(ques);
				cc.moveToNext();
			}
			cc.close();
			return array;

		}
		return null;

	}

	
	public boolean updateQuestionAttempt(String QuestionId, int attempt,
			int success) {
		ContentValues valuesToUpdate = new ContentValues();

		valuesToUpdate.put(ATTEMPT, attempt);
		valuesToUpdate.put(SUCCESS, success);
		String whereClause = QUES_ID + " = \'" + QuestionId + "\'";
		return update(table_name, whereClause, valuesToUpdate);

	}

	public boolean deleteTask(String QuestionId) {
		String whereClause = QUES_ID + " = \'" + QuestionId + "\'";
		return delete(table_name, whereClause);

	}

	public String getCreatetable() {
		return createTable;
	}

	public String getDroptable() {
		return dropTable;
	}

}
