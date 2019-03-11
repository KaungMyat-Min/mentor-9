package com.trio.mentor9.frag;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.R;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragFacts extends Fragment {
    private int unit;
    private final int DIFFERENCE = 0;
    private TextView factTextView;
    private Button nextButton, previousButton;

    private int index;

    private String[] facts;

    private Context context;
    private Resources res;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag_strange_facts, null);
        context = getActivity();
        unit = getArguments().getInt(Constant.UNIT_NO);
        factTextView = (TextView) v
                .findViewById(R.id.strange_fact_body_text_view);
        nextButton = (Button) v.findViewById(R.id.fact_next);
        previousButton = (Button) v.findViewById(R.id.fact_previous);

        facts = getData(unit);
        populateField();

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (index < facts.length) {
                    if (index != facts.length - 1) {
                        ++index;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "You have known ALL I know", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    populateField();

                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (index >= 0) {
                    if (index != 0) {
                        --index;
                    } else {
                        Toast.makeText(
                                getActivity().getApplicationContext(),
                                "There is no more facts for this unit. More facts will come in next version",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    populateField();

                }

            }
        });

        return v;
    }

    private String[] getData(int unit) {
        switch (unit - DIFFERENCE + 1) {/*
		case 1:
			return getResources().getStringArray(R.array.unit_1_strange_facts);
		case 2:
			return null;
		case 3:
			return getResources().getStringArray(R.array.unit_3_strange_facts);
		case 4:
			return getResources().getStringArray(R.array.unit_4_strange_facts);
		case 5:
			return null;
		case 6:
			return getResources().getStringArray(R.array.unit_6_strange_facts);
		case 7:
			return getResources().getStringArray(R.array.unit_7_strange_facts);
		case 8:
			return getResources().getStringArray(R.array.unit_8_strange_facts);
		case 9:
			return getResources().getStringArray(R.array.unit_9_strange_facts);
		case 10:
			return getResources().getStringArray(R.array.unit_10_strange_facts);
		*/
        }
        return null;
    }

    private void populateField() {
        if (facts != null) {
            try {
                factTextView.setText(Html.fromHtml(facts[index]));

            } catch (ArrayIndexOutOfBoundsException e) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "You have known ALL I know", Toast.LENGTH_LONG).show();
            }
        } else {
            nextButton.setVisibility(View.INVISIBLE);
            previousButton.setVisibility(View.INVISIBLE);
        }
    }
}
