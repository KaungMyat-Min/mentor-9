package com.trio.mentor9.frag;

import java.util.ArrayList;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.adaptor.AnsTheQuestionDbAdaptor;
import com.trio.mentor9.adaptor.ListShowAdaptor;
import com.trio.mentor9.R;
import com.trio.mentor9.model.Question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FragQueShow extends Fragment {

    private int unit;
    private ArrayList<Question> questionArray;
    private ListView questionList;
    private ImageButton fab;
	
/*	private final int CHAL_GRAMMAR = 4;
	private int userChoice;
	private int grammarType;*/


    //private final String SWIPE_2_CHALLENGE = "swipeToChallenge";

	/*private final int CHAL_QUE = 2;
	private final String KEY_CURRENT_USER = "current_user";

	 ..................dataBase sections.............. 
	private DataBaseHelper dbAdaptor;
	private AnsTheQuestionDbAdaptor ansTheQuesAdaptor;
	private TextView question;
	private TextView location;
	private TextView answer;*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        unit = getArguments().getInt(Constant.UNIT_NO);
        View v = inflater.inflate(R.layout.frag_que_show, container, false);
        fab = (ImageButton) v.findViewById(R.id.fab_que_show);

        questionList = (ListView) v.findViewById(R.id._question_show);
        populateFields();
        setListeneron_FAB();
        return v;
    }

    private void populateFields() {


        int i = unit + 1;
        questionArray = new AnsTheQuestionDbAdaptor(getActivity().getApplicationContext()).selectQuesWithUnit(i);


        ListShowAdaptor listAdaptor = new ListShowAdaptor(getActivity()
                .getApplicationContext(), questionArray);
        questionList.setAdapter(listAdaptor);
        questionList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView answerTextView = (TextView) view
                        .findViewById(R.id.question_item_answer_true);
                if (answerTextView.isShown()) {
                    answerTextView.setVisibility(View.GONE);
                } else {
                    answerTextView.setVisibility(View.VISIBLE);
                }
            }

        });

    }

    private void setListeneron_FAB() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SwipeViewActivity.navigateToQueChallenge(unit, 2);

            }
        });

    }
}
