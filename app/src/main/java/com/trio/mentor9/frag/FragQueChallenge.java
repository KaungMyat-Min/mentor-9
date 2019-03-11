package com.trio.mentor9.frag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.trio.mentor9.Constant;
import com.trio.mentor9.adaptor.AnsTheQuestionDbAdaptor;
import com.trio.mentor9.adaptor.GrammarDbAdaptor;
import com.trio.mentor9.adaptor.UserDataDbAdaptor;
import com.trio.mentor9.R;
import com.trio.mentor9.model.Question;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragQueChallenge extends Fragment {

    private int unit;

    private TextView resultTextView, questionNoTextView, attemptTextView,
            questionTextView, answerShowTextView, answerCheckTextView;
    private Button nextButton, falseButton, trueButton;
    private int no;
    private boolean result = false;


    private int userChoice;


    //private int but_ok_check;
    //private final String IS_THERE_USER_KEY = "isThereAnyUser";
    //private boolean isAnyUser;

    private LinkedHashSet<Integer> randomIndexSet;
    private ArrayList<Question> questionArray;
    private Iterator<Integer> iterator;

    private String currentUser = "^_^";
    private String currentQueId;
    private int currentAttempt;
    private int currentSuccess;
    private final String KEY_CURRENT_USER = "current_user";
    private String actionBarTitle;
    private UserDataDbAdaptor userDataDbAdaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        Bundle bundle = getArguments();
        unit = bundle.getInt(Constant.UNIT_NO) + 1;
        userChoice = bundle.getInt(Constant.USER_CHOICE);


        View v = inflater.inflate(R.layout.frag_que_challenge, null);

        resultTextView = (TextView) v.findViewById(R.id.che_show_true_or_false);
        questionNoTextView = (TextView) v.findViewById(R.id.che_no_of_que);
        attemptTextView = (TextView) v.findViewById(R.id.che_attempt);
        questionTextView = (TextView) v.findViewById(R.id.che_question_text);
        answerShowTextView = (TextView) v.findViewById(R.id.che_ans_show);
        answerCheckTextView = (TextView) v.findViewById(R.id.che_ans_check);


        // previousButton = (Button) v.findViewById(R.id.che_previous_question);
        nextButton = (Button) v.findViewById(R.id.che_next_question);
        falseButton = (Button) v.findViewById(R.id.che_button_false);
        trueButton = (Button) v.findViewById(R.id.che_button_true);


        falseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showResult(!result);
            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showResult(result);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterator.hasNext()) {
                    populateField();
                }

            }
        });

        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(getActivity()
                        .getApplicationContext());
        currentUser = pref.getString(KEY_CURRENT_USER, "(ʘ.ʘ)");
        userDataDbAdaptor = new UserDataDbAdaptor(getActivity().getApplicationContext());
        getData(unit);
        populateField();

        //...........custom action bar starts here..............
        View actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView();
        TextView actionBarTitleTextView = (TextView) actionbar.findViewById(R.id.action_bar_text);
        actionBarTitleTextView.setText(this.actionBarTitle);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
		/*SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getActivity()
						.getApplicationContext());
		currentUser = pref.getString(KEY_CURRENT_USER, null);
		getData(unit);
		populateField();*/

    }

    private void getData(int unit) {
        randomIndexSet = new LinkedHashSet<Integer>();

        if (userChoice == Constant.CHAL_QUE) {
            AnsTheQuestionDbAdaptor ansTheQuestionDbAdaptor = new AnsTheQuestionDbAdaptor(getActivity().getApplicationContext());
            questionArray = ansTheQuestionDbAdaptor.selectQuesWithUnit(unit);
            String[] unit_titles = getResources().getStringArray(R.array.unit_titles_for_action_bar_title);
            actionBarTitle = unit_titles[unit - 1];
        } else if (userChoice == Constant.CHAL_GRAMMAR) {
            GrammarDbAdaptor grammarDbAdaptor = new GrammarDbAdaptor(getActivity().getApplicationContext());
            questionArray = grammarDbAdaptor.selectGrammarWithUnit(
                    unit - 1);
            String[] grammar_names = getResources().getStringArray(R.array.types_of_grammars);
            actionBarTitle = grammar_names[unit - 1];
        }

        while (randomIndexSet.size() < questionArray.size()) {
            randomIndexSet.add((int) (Math.random() * questionArray.size()));
        }
        iterator = randomIndexSet.iterator();
    }

    private void populateField() {
        result = false;
        int index = 0;
        if (iterator.hasNext()) {
            index = iterator.next();

            Question question = questionArray.get(index);
            currentQueId = question.getQUE_ID();
            questionNoTextView.setText(++no + "/" + questionArray.size());
            questionTextView.setText(question.getQUES());

            int ansType = (int) (Math.random() * 3);
            switch (ansType) {
                case 0:
                    answerShowTextView.setText(question.getANS_true());
                    result = true;
                    break;
                case 1:
                    answerShowTextView.setText(question.getANS_fake1());
                    answerCheckTextView.setText(question.getANS_true());
                    result = false;
                    break;
                case 2:
                    answerShowTextView.setText(question.getANS_fake2());
                    answerCheckTextView.setText(question.getANS_true());
                    result = false;
                    break;

            }

            question = userDataDbAdaptor.selectQuestion(currentUser, currentQueId);
            currentAttempt = question.getATTEMPT() + 1;
            currentSuccess = question.getSUCCESS();
            attemptTextView.setText(currentSuccess + "/" + currentAttempt);

            answerCheckTextView.setVisibility(View.INVISIBLE);
            resultTextView.setVisibility(View.GONE);

            // previousButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.INVISIBLE);

            trueButton.setVisibility(View.VISIBLE);
            falseButton.setVisibility(View.VISIBLE);
        }

    }

    private void showResult(boolean answer) {
        if (answer) {
            currentSuccess = currentSuccess + 1;

            resultTextView.setText("Hi, " + currentUser + ".\nYour answer is TRUE");
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setBackgroundColor(getActivity().getResources()
                    .getColor(R.color.que_chel_result_background_right));
            answerCheckTextView.setVisibility(View.INVISIBLE);

        } else {

            resultTextView.setText("Your answer is WRONG");
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setBackgroundColor(getActivity().getResources()
                    .getColor(R.color.que_chel_result_background_wrong));
            answerCheckTextView.setVisibility(View.VISIBLE);

        }

		/*if (userChoice == CHAL_QUE) {
			AnsTheQuestionDbAdaptor.updateQuestionAttempt(currentQueId,
					currentAttempt, currentSuccess);
		} else if (userChoice == CHAL_GRAMMAR) {
			GrammarDbAdaptor.updateGrammarAttempt(currentQueId, currentAttempt,
					currentSuccess);
		}*/
        userDataDbAdaptor.open();
        userDataDbAdaptor.insertNewAttempt(currentUser, currentQueId, currentAttempt,
                currentSuccess);
        userDataDbAdaptor.close();
        // previousButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);

        trueButton.setVisibility(View.INVISIBLE);
        falseButton.setVisibility(View.INVISIBLE);
    }

/*	private void updateDb(String username, String que_id, int attempt,
			int success) {
		userDataDbAdaptor.updateAttempt(username, que_id, attempt, success);
		if (userChoice == CHAL_QUE) {
			ansTheQuestionDbAdaptor.updateQuestionAttempt(que_id, attempt,
					success);
		}
		if (userChoice == CHAL_GRAMMAR) {
			grammarDbAdaptor.updateGrammarAttempt(que_id, attempt, success);
		}
	}

	private void syncUserData(String userName) {
		userDataDbAdaptor.selectAllAndUpdateAttempt(userName);
	}

	private ArrayList<String> getUserNameList() {
		return UserDataDbAdaptor.selectUsers();
	}

	private ArrayList<String> getUserNameList(String searchName) {
		return UserDataDbAdaptor.selectUsers(searchName);
	}

	private class But_ok_listener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}*/

    private class But_ok_listener_2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }

    }
}