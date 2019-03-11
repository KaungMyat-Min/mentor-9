package com.trio.mentor9.frag;

import java.util.ArrayList;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.adaptor.GrammarDbAdaptor;
import com.trio.mentor9.adaptor.ListShowAdaptor;

import com.trio.mentor9.R;
import com.trio.mentor9.model.Question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FragGrammarShow extends Fragment {

    private int unit;
    private ArrayList<Question> questionArray;
    private ListView questionList;
    private int grammarType;
    private ImageButton fab;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        this.grammarType = bundle.getInt(Constant.GRAMMAR_TYPE);


        View v = inflater.inflate(R.layout.frag_grammar_show, container, false);
        fab = (ImageButton) v.findViewById(R.id.fab_grammar_show);
        fab.setVisibility(View.GONE);
        questionList = (ListView) v.findViewById(R.id._grammar_show);
        String[] grammar_names = getResources().getStringArray(R.array.types_of_grammars);
        RelativeLayout actionBarView = (RelativeLayout) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView();
        TextView actionBarTextView = (TextView) actionBarView.findViewById(R.id.action_bar_text);

        actionBarTextView.setText(grammar_names[grammarType]);
        populateFields();
        setListeneron_FAB();
        return v;
    }

    private void populateFields() {
        int DIFFERENCE = 1284;

        GrammarDbAdaptor grammarDBadaptor = new GrammarDbAdaptor(getActivity().getApplicationContext());
        questionArray = grammarDBadaptor.selectGrammarWithUnit(grammarType);


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

                SwipeViewActivity.navigateToQueChallenge(unit, 4);

            }
        });

    }
}
