package com.trio.mentor9.presenter;

import com.trio.mentor9.R;
import com.trio.mentor9.adaptor.AnsTheQuestionDbAdaptor;
import com.trio.mentor9.adaptor.DataBaseHelper;
import com.trio.mentor9.adaptor.DataInsertion;
import com.trio.mentor9.adaptor.GrammarDbAdaptor;
import com.trio.mentor9.adaptor.QuoteAdaptor;
import com.trio.mentor9.model.Quote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private static int DELAY_TIME = 2;
	private int progressStatus = 0;
	private TextView text_body;
	private TextView text_body_mm;
	private TextView text_author;
	private Button button;
	private Button button_translate;
	protected ProgressBar mProgressbar;
	private ProgressBar progressBar_inserting;
	protected boolean mbActive;

	int waited = 0;
	private final String DATA_BASE_READY_CHECK_KEY = "database_check";
	private final String DATA_BASE_VERSION_CHECK_KEY = "database_version";
	private final String INSERTION_CHECK_KEY = "data_insertion";
	private final String DATA_BASE_SIZE = "database_size";
	private final String TOTAL_A_T_KEY = "total_a_t";
	private final String QUOTE_TYPE_KEY = "quoteTypeToDisplay";
	private final String IS_TYPE_CHOSED_KEY = "istypechoosed";
	private final int DISPLAY_FUNNY_QUOTE = 1;
	private final String DISPLAY_FUNNY_QUOTE_STRING = "1";
	private final int DISPLAY_A_T_QUOTE = 2;
	private final String DISPLAY_A_T_QUOTE_STRING = "2";
	private boolean isDataBaseReady;
	private Button button_ask_light;
	private Button button_ask_heavy;

	private boolean isInsertionComplete = true;
	private boolean isTypeChoosed;
	private int databaseVersion;
	private int row_count;
	private int total_at;
	// private int TOTAL;
	// private final String TOTAL_KEY = "total_keY";
	private int quoteToDiaplay;
	// private QuoteAdaptor quoteAdaptor;

	// private final String ALBERT_EINSTEIN = "Albert Einstein";
	// private final String BENJAMIN_FRANKLIN =
	// "Benjamin Franklin - Founding father of the United States";
	// private final String VERY_FUNNY = ":D :P :D";
	// private final String FUNNY = "funny";
	// private final String A_T = "a_t";
	private Thread t;
	private Thread dataThread;
	private Thread insertGrammar;
	private Thread insertQuote;
	private Thread insertQueAns;

	private boolean isinsertGrammar;
	private boolean isInsertQuote;
	private boolean isInsertQueAns;

	private AnsTheQuestionDbAdaptor ansTheQuestionDbAdaptor;
	private GrammarDbAdaptor grammarDbAdaptor;
	private QuoteAdaptor quoteAdaptor;

	private Quote quote;
	private SharedPreferences pref;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressbar.setProgress(progressStatus);
			if (!isInsertionComplete && isinsertGrammar && isInsertQuote
					&& isInsertQueAns) {
				
				ansTheQuestionDbAdaptor.close();
				grammarDbAdaptor.close();
				quoteAdaptor.close();
				
				isInsertionComplete = true;
				isDataBaseReady = true;
				Editor edit_pref = pref.edit();
				edit_pref.putBoolean(DATA_BASE_READY_CHECK_KEY, true);
				edit_pref.putInt(DATA_BASE_SIZE, DataInsertion.getRow_count());
				edit_pref.putInt(TOTAL_A_T_KEY, DataInsertion.getTotal_at());
				// edit_pref.putInt(TOTAL_KEY, DataInsertion.getTOTAL());
				// edit_pref.putBoolean(INSERTION_CHECK_KEY,
				// isInsertionComplete);
				edit_pref.putInt(DATA_BASE_VERSION_CHECK_KEY,
						DataBaseHelper.DATABASE_VERSION);

				edit_pref.apply();
			}

			if (progressStatus == 30) {
				if (quote != null && isTypeChoosed) {
					text_body.setText(Html.fromHtml(quote.getQuote_body()));
					text_body_mm.setText(Html.fromHtml(quote.getQuote_mm()));
					button_translate.setVisibility(View.VISIBLE);
				} else {
					text_body
							.setText("It may take a while to start the application for a first time.\n\"Even miracles take a little time.\"");
					text_body_mm
							.setText("App ကိုစတင္ဖို႔ လိုတာေလးေတြ ျပင္ေနပါတယ္။ နည္းနည္းေလာက္ေစာင့္ေပးပါ\n \"ဘုရားသခင္ ဖန္ဆင္းတဲ့ ကိစၥေတာင္မွ အခ်ိန္နည္းနည္းေတာ့ယူရတယ္ေလ  :D :D\" ");
					text_body.setVisibility(View.INVISIBLE);
					text_body_mm.setVisibility(View.VISIBLE);
					button_translate.setVisibility(View.VISIBLE);
				}
			} else if (progressStatus == 70) {
				if (quote != null && isTypeChoosed)

					text_author.setText(Html.fromHtml(quote.getQuote_author()));
				else
					text_author.setText("Fairy Godmother, Cinderella");

			} else if (progressStatus == 100 && isInsertionComplete
					&& isTypeChoosed) {
				progressBar_inserting.setVisibility(View.INVISIBLE);
				mProgressbar.setVisibility(View.INVISIBLE);
				button.setVisibility(View.VISIBLE);

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				if (mbActive) {
					try {
						while (progressStatus < 100) {
							Thread.sleep(DELAY_TIME * 10);
							progressStatus += 1;
							handler.sendEmptyMessage(0);
						}
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}

			}
		});

		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		text_body = (TextView) findViewById(R.id.splash_text_body);
		text_body_mm = (TextView) findViewById(R.id.splash_text_body_mm);
		text_author = (TextView) findViewById(R.id.splash_text_author);
		progressBar_inserting = (ProgressBar) findViewById(R.id.progressBar_inserting);
		button = (Button) findViewById(R.id.splash_button);
		button_translate = (Button) findViewById(R.id.splash_button_translate);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(SplashActivity.this, ActivityHome.class);
				startActivity(i);
				SplashActivity.this.finish();
			}
		});

		button_translate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!text_body.isShown()) {
					text_body.setVisibility(View.VISIBLE);
					text_body_mm.setVisibility(View.INVISIBLE);
					button_translate.setText("To Myan");
				} else if (!text_body_mm.isShown()) {
					text_body.setVisibility(View.INVISIBLE);
					text_body_mm.setVisibility(View.VISIBLE);
					button_translate.setText("To Eng");
				}

			}
		});
		//
		//
		// get pref values
		//
		//

		pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		databaseVersion = pref.getInt(DATA_BASE_VERSION_CHECK_KEY, 0);
		isDataBaseReady = databaseVersion == DataBaseHelper.DATABASE_VERSION;
		
		row_count = pref.getInt(DATA_BASE_SIZE, 0);
		total_at = pref.getInt(TOTAL_A_T_KEY, 0);
		// TOTAL = pref.getInt(TOTAL_KEY, 0);
		quoteToDiaplay = Integer.valueOf(pref.getString(QUOTE_TYPE_KEY,
				DISPLAY_FUNNY_QUOTE_STRING));
		isTypeChoosed = pref.getBoolean(IS_TYPE_CHOSED_KEY, false);
		//
		//
		// dataquery
		//
		//
	/*	dbHelper = new DataBaseHelper(this.getApplicationContext());
		dbHelper.open();*/
		if (!isDataBaseReady || !isTypeChoosed) {// typedchoosed is required for
													// as user
													// sometime close the app
													// but
													// insertion is
													// complete
			isInsertionComplete = false;
			button_ask_light = (Button) findViewById(R.id.splash_button_ask_light);
			button_ask_heavy = (Button) findViewById(R.id.splash_button_ask_heavy);
			setListenerOn_askButtons();

			text_body.setText("ဘယ္လို မွတ္သားစရာစကားေတြကို ဖတ္ခ်င္လဲ");
			text_body.setVisibility(View.VISIBLE);
			dataThread = new Thread(new DataQuery());
			dataThread.start();
		}

		quote = getData();

		mProgressbar = (ProgressBar) findViewById(R.id.progressBar);
		mProgressbar.setMax(100);

		mbActive = true;

		text_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				text_body.setVisibility(View.INVISIBLE);
				text_body_mm.setVisibility(View.VISIBLE);

			}
		});

		text_body_mm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				text_body.setVisibility(View.VISIBLE);
				text_body_mm.setVisibility(View.INVISIBLE);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (t.isAlive()) {
			t.interrupt();
		}
	}

	@Override
	public void onBackPressed() {

	}

	private Quote getData() {
		Quote quote = null;

		if (isDataBaseReady && isTypeChoosed) {
			quoteAdaptor = new QuoteAdaptor(getApplicationContext());
			quoteAdaptor.open();
			if (quoteToDiaplay == DISPLAY_A_T_QUOTE) {

				int random_row = 0;
				do {
					random_row = (int) (Math.random() * row_count + 1);
				} while (random_row > total_at);
				quote = quoteAdaptor.selectA_T(random_row);

			} else if (quoteToDiaplay == DISPLAY_FUNNY_QUOTE) {

				int random_row = 0;
				while (random_row < total_at + 1) {
					random_row = (int) (Math.random() * row_count + 1);
				}
				quote = quoteAdaptor.selectFunny(random_row);
			}

			quoteAdaptor.close();
			mbActive = true;
			t.start();
		}

		return quote;
	}

	private void setListenerOn_askButtons() {
		mbActive = true;
		final Editor edit_pref = pref.edit();
		button_ask_light.setVisibility(View.VISIBLE);
		button_ask_heavy.setVisibility(View.VISIBLE);

		button_ask_light.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				t.start();
				isTypeChoosed = true;
				edit_pref.putString(QUOTE_TYPE_KEY, DISPLAY_FUNNY_QUOTE_STRING);
				edit_pref.putBoolean(IS_TYPE_CHOSED_KEY, isTypeChoosed);
				edit_pref.apply();
				button_ask_heavy.setVisibility(View.GONE);
				button_ask_light.setVisibility(View.GONE);
				progressBar_inserting.setVisibility(View.VISIBLE);
				mProgressbar.setVisibility(View.INVISIBLE);

			}
		});
		button_ask_heavy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				t.start();
				isTypeChoosed = true;
				edit_pref.putBoolean(IS_TYPE_CHOSED_KEY, isTypeChoosed);
				edit_pref.putString(QUOTE_TYPE_KEY, DISPLAY_A_T_QUOTE_STRING);
				edit_pref.apply();
				button_ask_heavy.setVisibility(View.GONE);
				button_ask_light.setVisibility(View.GONE);
				progressBar_inserting.setVisibility(View.VISIBLE);
				mProgressbar.setVisibility(View.INVISIBLE);
			}
		});

	}

	private class DataQuery implements Runnable {// .............datainsertion
													// starts here

		public void run() {

			if (!isDataBaseReady) {
				
				ansTheQuestionDbAdaptor = new AnsTheQuestionDbAdaptor(
						getApplicationContext());
				grammarDbAdaptor = new GrammarDbAdaptor(getApplicationContext());
				quoteAdaptor = new QuoteAdaptor(getApplicationContext());
				
				ansTheQuestionDbAdaptor.open();
				grammarDbAdaptor.open();
				quoteAdaptor.open();

				// final DataInsertion insert = new DataInsertion(
				// getApplicationContext());
				// insert.InsertAll();
				insertGrammar = new Thread(new Runnable() {
					public void run() {
						new DataInsertion(getApplicationContext(),grammarDbAdaptor)
								.insertGrammar();
						isinsertGrammar = true;
						handler.sendEmptyMessage(0);
					}
				});
				insertQueAns = new Thread(new Runnable() {
					public void run() {
						new DataInsertion(getApplicationContext(),ansTheQuestionDbAdaptor)
								.insertQueAndAnswer();
						isInsertQueAns = true;
						handler.sendEmptyMessage(0);
					}
				});
				insertQuote = new Thread(new Runnable() {
					public void run() {
						new DataInsertion(getApplicationContext(),quoteAdaptor)
								.InsertQuote();
						isInsertQuote = true;
						handler.sendEmptyMessage(0);
					}
				});
				insertGrammar.start();
				insertQueAns.start();
				insertQuote.start();

				

				handler.sendEmptyMessage(0);
			}
		}
	}
}