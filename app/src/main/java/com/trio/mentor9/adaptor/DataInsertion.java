package com.trio.mentor9.adaptor;

import java.util.regex.Matcher;

import com.trio.mentor9.R;

import java.util.regex.Pattern;

import android.content.Context;

public class DataInsertion {
	private Context tx;
	private String questions[];
	private String answer_true[];

	private String QUESTION;
	private String ANS_true = "lol";
	private String ANS_fake1;
	private String ANS_fake2;
	private String LOCATION;

	private final String Q_A_TYPE = "ques2answer";
	// private String UNIT_1 = "01";
	private int UNIT_1 = 1;

	private final String TYPE_AnswerTheQuestion = "_4_";
	private final String TYPE_grammar = "_7_";
	private  int Row_id = 0;
	private  int ROW_ID_GRAMMAR = 0;
	private int TOTAL = 0;
	private int ANSWER_THE_QUESTION = 4;
	private int GRAMMAR = 7;

	private static int row_count = 0;
	private static int total_at;
	private final String ALBERT_EINSTEIN = "Albert Einstein";
	private final String BENJAMIN_FRANKLIN = "Benjamin Franklin - Founding father of the United States";
	private final String VERY_FUNNY = ":D :P :D";
	private final String FUNNY = "funny";
	private final String A_T = "a_t";
	
	private AnsTheQuestionDbAdaptor ansTheQuestionDbAdaptor;
	private GrammarDbAdaptor grammarDbAdaptor;
	private QuoteAdaptor quoteAdaptor;
	private DataBaseHelper dbHelper;


	public DataInsertion(Context c,DataBaseHelper DataBase) {
		tx = c;
		dbHelper = DataBase;
	}

	public void InsertAll() {
		insertQueAndAnswer();
		InsertQuote();
		insertGrammar();
	}

	private void insert(int type, String unit, String question_array[],
			String answer_true_array[], String answer_fake1_array[],
			String answer_fake2_array[]) {
		questions = question_array;
		answer_true = answer_true_array;
		if (type == ANSWER_THE_QUESTION) {
			for (int i = 0; i < questions.length; i++) {
				QUESTION = questions[i];
				ANS_true = answer_true[i];
				ANS_fake1 = answer_fake1_array[i];
				ANS_fake2 = answer_fake2_array[i];
				//stringCut(question, ANSWER_THE_QUESTION);
				++Row_id;
				String Ques_id = unit + TYPE_AnswerTheQuestion + Row_id;
				ansTheQuestionDbAdaptor.insertExercise(Row_id, Ques_id, unit,
						Q_A_TYPE, QUESTION, ANS_true, ANS_fake1, ANS_fake2
						);
				/*
				 * AnsTheQuestionDbAdaptor.insertwithTrueAnsOnly(Row_id,
				 * Ques_id, unit, Q_A_TYPE, QUESTION, ANS_true, LOCATION);
				 */
			}
		} else if (type == GRAMMAR) {
			for (int i = 0; i < questions.length; i++) {
				String question = questions[i];
				ANS_true = answer_true[i];
				ANS_fake1 = answer_fake1_array[i];
				ANS_fake2 = answer_fake2_array[i];
				stringCut(question, GRAMMAR);
				++ROW_ID_GRAMMAR;
				String Ques_id = "G" + TYPE_grammar + ROW_ID_GRAMMAR;
				int grammarType = Integer.valueOf(unit);
				grammarDbAdaptor.insertExercise(ROW_ID_GRAMMAR, Ques_id,
						grammarType, QUESTION, ANS_true, ANS_fake1, ANS_fake2,
						LOCATION);
				/*
				 * GrammarDbAdaptor.insertwithTrueAnsOnly(ROW_ID_GRAMMAR,
				 * Ques_id, grammarType, "grammar", QUESTION, ANS_true,
				 * LOCATION);
				 */
			}
		}
	}

	private void stringCut(String original, int type) {
		int start = 0;
		Pattern pattern = null;
		Matcher m = null;
		if (type == ANSWER_THE_QUESTION) {
			pattern = Pattern.compile("\\?");

			m = pattern.matcher(original);

			if (m.find()) {
				start = m.start();
			}
		} else if (type == GRAMMAR) {
			pattern = Pattern.compile("@");
			m = pattern.matcher(original);

			start = 0;
			if (m.find()) {
				start = m.start() - 1;

			}
		}
		QUESTION = original.substring(0, start + 1);
		pattern = Pattern.compile("@");
		m = pattern.matcher(original);
		if (m.find()) {
			start = m.start();

		}

		int end = original.length();

		LOCATION = original.substring(start + 1, end);
	}

	// .............QuestionAndAnswer Insertion start here..............
	public void insertQueAndAnswer() {
		ansTheQuestionDbAdaptor = (AnsTheQuestionDbAdaptor) dbHelper;
		int dBversion = DataBaseHelper.DATABASE_VERSION;
		switch (dBversion) {
		default:
			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(UNIT_1),
					tx.getResources().getStringArray(R.array.unit_1_4_que),
					tx.getResources().getStringArray(
							R.array.unit_1_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_1_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_1_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_2_4_que),
					tx.getResources().getStringArray(
							R.array.unit_2_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_2_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_2_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_3_4_que),
					tx.getResources().getStringArray(
							R.array.unit_3_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_3_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_3_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_4_4_que),
					tx.getResources().getStringArray(
							R.array.unit_4_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_4_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_4_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_5_4_que),
					tx.getResources().getStringArray(
							R.array.unit_5_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_5_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_5_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_6_4_que),
					tx.getResources().getStringArray(
							R.array.unit_6_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_6_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_6_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_7_4_que),
					tx.getResources().getStringArray(
							R.array.unit_7_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_7_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_7_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_8_4_que),
					tx.getResources().getStringArray(
							R.array.unit_8_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_8_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_8_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					"0" + String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_9_4_que),
					tx.getResources().getStringArray(
							R.array.unit_9_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_9_4_answer_fake1), tx.getResources()
							.getStringArray(R.array.unit_9_4_answer_fake2));

			insert(ANSWER_THE_QUESTION,
					String.valueOf(++UNIT_1),
					tx.getResources().getStringArray(R.array.unit_10_4_que),
					tx.getResources().getStringArray(
							R.array.unit_10_4_answer_true),
					tx.getResources().getStringArray(
							R.array.unit_10_4_answer_fake1),
					tx.getResources().getStringArray(
							R.array.unit_10_4_answer_fake2));

			
		}
	}

	// .................Question And Answer Insertion ends here...........

	// ................grammar insertion starts here.....................

	public void insertGrammar() {
		int dBversion = DataBaseHelper.DATABASE_VERSION;
		grammarDbAdaptor = (GrammarDbAdaptor) dbHelper;
		int grammarType = 0;
		switch (dBversion) {

		default:

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_direct_indirect),
					tx.getResources().getStringArray(
							R.array.g_true_direct_indirect),
					tx.getResources().getStringArray(
							R.array.g_f1_direct_indirect), tx.getResources()
							.getStringArray(R.array.g_f2_direct_indirect));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_active_passive),
					tx.getResources().getStringArray(
							R.array.g_true_active_passive),
					tx.getResources().getStringArray(
							R.array.g_f1_active_passive),
					tx.getResources().getStringArray(
							R.array.g_f2_active_passive));
			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_if_unless), tx.getResources()
					.getStringArray(R.array.g_true_if_unless), tx
					.getResources().getStringArray(R.array.g_f1_if_unless),
					tx.getResources().getStringArray(R.array.g_f2_if_unless));
			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_after), tx.getResources()
					.getStringArray(R.array.g_true_after), tx.getResources()
					.getStringArray(R.array.g_f1_after), tx.getResources()
					.getStringArray(R.array.g_f2_after));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources()
							.getStringArray(R.array.g_q_without_ving),
					tx.getResources().getStringArray(
							R.array.g_true_without_ving),
					tx.getResources().getStringArray(
							R.array.g_f1_without_ving),
					tx.getResources().getStringArray(
							R.array.g_f2_without_ving));
			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_sooner_than), tx
					.getResources().getStringArray(R.array.g_true_sooner_than),
					tx.getResources()
							.getStringArray(R.array.g_f1_sooner_than),
					tx.getResources()
							.getStringArray(R.array.g_f2_sooner_than));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_although_in_spite_of),
					tx.getResources().getStringArray(
							R.array.g_true_although_in_spite_of),
					tx.getResources().getStringArray(
							R.array.g_f1_although_in_spite_of),
					tx.getResources().getStringArray(
							R.array.g_f2_although_in_spite_of));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_so_that_such_that),
					tx.getResources().getStringArray(
							R.array.g_true_so_that_such_that),
					tx.getResources().getStringArray(
							R.array.g_f1_so_that_such_that),
					tx.getResources().getStringArray(
							R.array.g_f2_so_that_such_that));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_too_to_enough_to),
					tx.getResources().getStringArray(
							R.array.g_true_too_to_enough_to),
					tx.getResources().getStringArray(
							R.array.g_f1_too_to_enough_to), tx.getResources()
							.getStringArray(R.array.g_f2_too_to_enough_to));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_when_by_the_time),
					tx.getResources().getStringArray(
							R.array.g_true_when_by_the_time),
					tx.getResources().getStringArray(
							R.array.g_f1_when_by_the_time), tx.getResources()
							.getStringArray(R.array.g_f2_when_by_the_time));

			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_ving), tx.getResources()
					.getStringArray(R.array.g_true_ving), tx.getResources()
					.getStringArray(R.array.g_f1_ving), tx.getResources()
					.getStringArray(R.array.g_f2_ving));

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_nouns_in_apposition),
					tx.getResources().getStringArray(
							R.array.g_true_nouns_in_apposition),
					tx.getResources().getStringArray(
							R.array.g_f1_nouns_in_apposition),
					tx.getResources().getStringArray(
							R.array.g_f2_nouns_in_apposition));

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources()
							.getStringArray(R.array.g_q_it_is_it_was),
					tx.getResources().getStringArray(
							R.array.g_true_it_is_it_was),
					tx.getResources().getStringArray(
							R.array.g_f1_it_is_it_was),
					tx.getResources().getStringArray(
							R.array.g_f2_it_is_it_was));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_it_took_it_take),
					tx.getResources().getStringArray(
							R.array.g_true_it_took_it_take),
					tx.getResources().getStringArray(
							R.array.g_f1_it_took_it_take), tx.getResources()
							.getStringArray(R.array.g_f2_it_took_it_take));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_noun_who_which_that),
					tx.getResources().getStringArray(
							R.array.g_true_noun_who_which_that),
					tx.getResources().getStringArray(
							R.array.g_f1_noun_who_which_that),
					tx.getResources().getStringArray(
							R.array.g_f2_noun_who_which_that));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_not_only_but_also),
					tx.getResources().getStringArray(
							R.array.g_true_not_only_but_also),
					tx.getResources().getStringArray(
							R.array.g_f1_not_only_but_also),
					tx.getResources().getStringArray(
							R.array.g_f2_not_only_but_also));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_neither_nor_neither_of),
					tx.getResources().getStringArray(
							R.array.g_true_neither_nor_neither_of),
					tx.getResources().getStringArray(
							R.array.g_f1_neither_nor_neither_of),
					tx.getResources().getStringArray(
							R.array.g_f2_neither_nor_neither_of));

			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_both_and), tx.getResources()
					.getStringArray(R.array.g_true_both_and), tx
					.getResources().getStringArray(R.array.g_f1_both_and), tx
					.getResources().getStringArray(R.array.g_f2_both_and));

			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_either_or), tx.getResources()
					.getStringArray(R.array.g_true_either_or), tx
					.getResources().getStringArray(R.array.g_f1_either_or),
					tx.getResources().getStringArray(R.array.g_f2_either_or));

			

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_there_is_no_noun_that),
					tx.getResources().getStringArray(
							R.array.g_true_there_is_no_noun_that),
					tx.getResources().getStringArray(
							R.array.g_f1_there_is_no_noun_that),
					tx.getResources().getStringArray(
							R.array.g_f2_there_is_no_noun_that));

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_there_is_no_as_as),
					tx.getResources().getStringArray(
							R.array.g_true_there_is_no_as_as),
					tx.getResources().getStringArray(
							R.array.g_f1_there_is_no_as_as),
					tx.getResources().getStringArray(
							R.array.g_f2_there_is_no_as_as));

			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_not_as_as), tx.getResources()
					.getStringArray(R.array.g_true_not_as_as), tx
					.getResources().getStringArray(R.array.g_f1_not_as_as),
					tx.getResources().getStringArray(R.array.g_f2_not_as_as));

			++TOTAL;
			insert(GRAMMAR, String.valueOf(++grammarType), tx.getResources()
					.getStringArray(R.array.g_q_as_as), tx.getResources()
					.getStringArray(R.array.g_true_as_as), tx.getResources()
					.getStringArray(R.array.g_f1_as_as), tx.getResources()
					.getStringArray(R.array.g_f2_as_as));

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources().getStringArray(
							R.array.g_q_one_of_the_est),
					tx.getResources().getStringArray(
							R.array.g_true_one_of_the_est),
					tx.getResources().getStringArray(
							R.array.g_f1_one_of_the_est),
					tx.getResources().getStringArray(
							R.array.g_f2_one_of_the_est));

			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources()
							.getStringArray(R.array.g_q_verb_er_than),
					tx.getResources().getStringArray(
							R.array.g_true_verb_er_than),
					tx.getResources().getStringArray(
							R.array.g_f1_verb_er_than),
					tx.getResources().getStringArray(
							R.array.g_f2_verb_er_than));
			++TOTAL;
			insert(GRAMMAR,
					String.valueOf(++grammarType),
					tx.getResources()
							.getStringArray(R.array.g_q_er_than_most),
					tx.getResources().getStringArray(
							R.array.g_true_er_than_most),
					tx.getResources().getStringArray(
							R.array.g_f1_er_than_most),
					tx.getResources().getStringArray(
							R.array.g_f2_er_than_most));
		}
	}

	// .................grammar insertion ends here......................

	// .............Quote insertion starts here...............
	public void InsertQuote() {
		quoteAdaptor =(QuoteAdaptor) dbHelper;
		String[] quotes_body = tx.getResources().getStringArray(
				R.array.albert_a_t);
		String[] quotes_mm = tx.getResources().getStringArray(
				R.array.albert_a_t_mm);

		for (int i = 0; i < quotes_body.length; i++) {
			String eistein_a_t = quotes_body[i];
			++row_count;
			quoteAdaptor.insertQuote(row_count, ALBERT_EINSTEIN, eistein_a_t,
					quotes_mm[i], A_T);
		}

		quotes_body = tx.getResources().getStringArray(
				R.array.splash_benjamin_franklin_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_benjamin_franklin_body_mm);
		for (int i = 0; i < quotes_body.length; i++) {
			String b_j_a_t = quotes_body[i];
			++row_count;
			quoteAdaptor.insertQuote(row_count, BENJAMIN_FRANKLIN, b_j_a_t,
					quotes_mm[i], A_T);
		}

		quotes_body = tx.getResources().getStringArray(
				R.array.splash_various_a_t_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_various_a_t_body_mm);
		String quotes_author[] = tx.getResources().getStringArray(
				R.array.splash_various_a_t_auhtor);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], A_T);
		}
		total_at = row_count;
		// funny start here
		quotes_body = tx.getResources().getStringArray(R.array.albert_funny);
		quotes_mm = tx.getResources().getStringArray(R.array.albert_funny_mm);
		for (int i = 0; i < quotes_body.length; i++) {
			String eisten_funny = quotes_body[i];
			++row_count;
			quoteAdaptor.insertQuote(row_count, ALBERT_EINSTEIN, eisten_funny,
					quotes_mm[i], FUNNY);
		}

		quotes_body = tx.getResources().getStringArray(
				R.array.splash_benjamin_franklin_funny);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_benjamin_franklin_funny_mm);
		for (int i = 0; i < quotes_body.length; i++) {
			String b_j_funny = quotes_body[i];
			++row_count;
			quoteAdaptor.insertQuote(row_count, BENJAMIN_FRANKLIN, b_j_funny,
					quotes_mm[i], FUNNY);
		}
		quotes_body = tx.getResources().getStringArray(
				R.array.splash_quote_var_author_funny_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_quote_var_author_funny_body_mm);
		for (int i = 0; i < quotes_body.length; i++) {
			String very_funny = quotes_body[i];
			++row_count;
			quoteAdaptor.insertQuote(row_count, VERY_FUNNY, very_funny,
					quotes_mm[i], FUNNY);
		}

		quotes_body = tx.getResources().getStringArray(R.array.kpop_a_t);
		quotes_mm = tx.getResources().getStringArray(R.array.kpop_a_t_mm);
		quotes_author = tx.getResources()
				.getStringArray(R.array.kpop_authors);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], FUNNY);
		}
		quotes_body = tx.getResources().getStringArray(R.array.naruto_quote);
		quotes_mm = tx.getResources().getStringArray(R.array.naruto_quote_mm);
		quotes_author = tx.getResources().getStringArray(
				R.array.naruto_author);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], FUNNY);
		}
		quotes_body = tx.getResources().getStringArray(
				R.array.quote_disney_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.quote_disney_body_mm);
		quotes_author = tx.getResources().getStringArray(
				R.array.quote_disney_author);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], FUNNY);
		}
		quotes_body = tx.getResources().getStringArray(
				R.array.splash_celi_funny_quote_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_celi_funny_quote_body_mm);
		quotes_author = tx.getResources().getStringArray(
				R.array.splash_celi_funny_quote_author);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], FUNNY);
		}
		quotes_body = tx.getResources().getStringArray(
				R.array.splash_quote_hero_body);
		quotes_mm = tx.getResources().getStringArray(
				R.array.splash_quote_hero_body_mm);
		quotes_author = tx.getResources().getStringArray(
				R.array.splash_quote_hero_author);
		for (int i = 0; i < quotes_body.length; i++) {
			++row_count;
			quoteAdaptor.insertQuote(row_count, quotes_author[i],
					quotes_body[i], quotes_mm[i], FUNNY);
		}
	}

	// ................quote insertion ends here................

	public static int getRow_count() {
		return row_count;
	}

	public static int getTotal_at() {
		return total_at;
	}
public int getTOTAL(){
	return TOTAL;
}
}
