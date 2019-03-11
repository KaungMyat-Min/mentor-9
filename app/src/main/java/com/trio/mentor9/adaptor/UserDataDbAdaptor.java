package com.trio.mentor9.adaptor;

import java.util.ArrayList;

import com.trio.mentor9.model.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
public class UserDataDbAdaptor extends DataBaseHelper {

	public UserDataDbAdaptor(Context tx) {
		super(tx);
		// TODO Auto-generated constructor stub
	}

	private final String table_name = "UserData";

	private String USER_NAME = "user_name";// user_name ko dynamic
											// phit ag lote
	private final String QUES_ID = "question_id";
	private final String ROW_ID = "_id";
	private final String UNIT_NO = "_UNIT_NO";
	private final String ATTEMPT = "attempt";
	private final String SUCCESS = "success";
	private final String LOCATION = "location";
	private final String TYPE = "_type";

	private final String createTable = "create table " + table_name + " ( "
			+ ROW_ID + " int primary key, " + USER_NAME + " text not null,"
			+ QUES_ID + " text not null," + UNIT_NO + " text," + ATTEMPT
			+ " int not null," + SUCCESS + " int not null," + LOCATION
			+ " text," + TYPE + " text)";

	private final String dropTable = "DROP TABLE IF EXISTS " + table_name;

	ArrayList<String> user_names;

	public long insertNewAttempt(String userName, String Que_id, int attempt,
			int success) {
		String whereClause = null;
		select(table_name, whereClause);
		ContentValues valuesToInsert = new ContentValues();
		valuesToInsert.put(USER_NAME, userName);
		valuesToInsert.put(QUES_ID, Que_id);
		valuesToInsert.put(ATTEMPT, attempt);
		valuesToInsert.put(SUCCESS, success);
		long idd = insert(table_name, valuesToInsert);
		return idd;
	}

/*	public void selectAllAndUpdateAttempt(String username) {
		String whereClause = USER_NAME + " = \'" + username + "\' order by "
				+ QUES_ID;
		open();
		Cursor cc = select(table_name, whereClause);
		close();
		if (cc != null) {
			boolean b = cc.moveToFirst();
			// array = new ArrayList<Question>();
			while (!cc.isAfterLast()) {
				String que_id = cc.getString(cc.getColumnIndex(QUES_ID));
				int attempt = cc.getInt(cc.getColumnIndex(ATTEMPT));
				int success = cc.getInt(cc.getColumnIndex(SUCCESS));
				boolean check = AnsTheQuestionDbAdaptor.updateQuestionAttempt(
						que_id, attempt, success);
				cc.moveToNext();
			}
			cc.close();
		}
	}*/

	public ArrayList<String> selectUsers() {
		String whereClause = null;
		String[] columns = { USER_NAME };
		open();
		Cursor cc = selectDistinct(table_name, columns, whereClause);
		close();
		ArrayList<String> user_names = new ArrayList<String>();
		if (cc != null) {
			boolean b = cc.moveToFirst();
			while (!cc.isAfterLast()) {

				user_names.add(cc.getString(cc.getColumnIndex(USER_NAME)));
				cc.moveToNext();

			}
		}
		return user_names;
	}

	public ArrayList<String> selectUsers(String userName) {
		String whereClause = USER_NAME + " like '%" + userName + "%'";
		String[] columns = { USER_NAME };
		open();
		Cursor cc = selectDistinct(table_name, columns, whereClause);
		close();
		ArrayList<String> user_names = new ArrayList<String>();
		if (cc != null) {
			boolean b = cc.moveToFirst();
			while (!cc.isAfterLast()) {

				user_names.add(cc.getString(cc.getColumnIndex(USER_NAME)));
				cc.moveToNext();

			}
		}
		return user_names;
	}

	public Question selectQuestion(String userName, String Que_id) {
		String whereClause = USER_NAME + "= \'" + userName + "\' and "
				+ QUES_ID + "= \'" + Que_id + "\'";
		Question question = new Question();
		open();
		Cursor cc = select(table_name, whereClause);
		close();
		if (cc != null && cc.moveToFirst()) {
			question.setATTEMPT(cc.getInt(cc.getColumnIndex(ATTEMPT)));
			question.setSUCCESS(cc.getInt(cc.getColumnIndex(SUCCESS)));
		}
		return question;
	}

	public boolean updateAttempt(String userName, String QuestionId,
			int attempt, int success) {
		ContentValues valuesToUpdate = new ContentValues();
		valuesToUpdate.put(ATTEMPT, attempt);
		valuesToUpdate.put(SUCCESS, SUCCESS);

		String whereClause = QUES_ID + " = " + QuestionId + " and " + USER_NAME
				+ " = \'" + userName + "\'";
		return update(table_name, whereClause, valuesToUpdate);

	}

	public boolean delete(String userName) {
		String whereClause = USER_NAME + "=\'" + userName + "\'";

		return delete(table_name, whereClause);

	}

	public String getCreatetable() {
		return createTable;
	}

	public String getDroptable() {
		return dropTable;
	}

}
