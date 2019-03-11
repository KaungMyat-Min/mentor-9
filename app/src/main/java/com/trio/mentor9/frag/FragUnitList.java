package com.trio.mentor9.frag;

import java.util.ArrayList;

import com.trio.mentor9.Constant;
import com.trio.mentor9.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class FragUnitList extends Fragment {

    private ArrayList<String> ItemList;

    //private String user;
    private TextView action_bar_title_text_view;
    private ListView slidemenu;
    private ListView listViewToShow;
    private ArrayAdapter<String> mArrayAdapter;

    public static final boolean ASEAN = true;

    private final int READ_PARAGRAPH = 0;
    private final int READ_QUE = 1;
    private final int CHAL_QUE = 2;
    private final int READ_GRAMMAR = 3;
    private final int CHAL_GRAMMAR = 4;
    private int userChoice;

    private String currentUser;

    private Context context;
    private Activity activity;


    /*private int but_ok_check;

    private final String KEY_CURRENT_USER = "curent_user";

    private final String UNIT_LIST_TO_SWIPE = "unit_list_to_swipe";
*/
    public FragUnitList() {
        ItemList = new ArrayList<String>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        userChoice = bundle.getInt(Constant.USER_CHOICE);
        currentUser = bundle.getString(Constant.CURRENT_USER);


        View v = inflater.inflate(R.layout.frag_units_list, container, false);

        getListToShow();

        listViewToShow = (ListView) v.findViewById(R.id.munit_list);
        activity = getActivity();
        context = activity.getApplicationContext();

        mArrayAdapter = new ArrayAdapter<String>(getActivity()
                .getApplicationContext(), R.layout.units_list_item, ItemList);

        listViewToShow.setAdapter(mArrayAdapter);

        slidemenu = (ListView) getActivity()
                .findViewById(R.id.mlist_slidermenu);

        slidemenu.setVisibility(ListView.INVISIBLE);

        // ........... custom action bar starts here............
        ActionBar mActionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = (LayoutInflater) getActivity()
                .getApplicationContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View vv = inflator.inflate(R.layout.action_mode_custom_title, null);
        action_bar_title_text_view = (TextView) vv
                .findViewById(R.id.action_bar_text);

        Typeface typeface = Typeface.createFromAsset(getActivity()
                        .getApplicationContext().getAssets(),
                "fonts/SmartZawgyi_v3.ttf");
        action_bar_title_text_view.setTypeface(typeface);

        action_bar_title_text_view.setText(getActionBarTitle());

        mActionBar.setCustomView(vv);

        // ..............custom actionbar end here...........
        setOnClickListener_listViewToShow();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar mActionBar = ((AppCompatActivity) (getActivity()))
                .getSupportActionBar();
        mActionBar.setTitle(getString(R.string.app_name));
        if (!mActionBar.isShowing()) {
            mActionBar.show();
        }
    }

    private void getListToShow() {
        String unit_title[] = null;
        if (userChoice == READ_GRAMMAR || userChoice == CHAL_GRAMMAR) {
            if (ItemList.size() == 0) {
                unit_title = getResources().getStringArray(
                        R.array.type_of_grammars);
                for (String unitTitle : unit_title) {
                    ItemList.add(unitTitle);
                }
            }
        } else {
            if (ItemList.size() == 0) {

                unit_title = getResources().getStringArray(R.array.unit_titles);
                for (String unitTitle : unit_title) {
                    ItemList.add(unitTitle);
                }
            }
        }
    }

    private String getActionBarTitle() {
        String[] titles = getResources().getStringArray(R.array.array_home);
        return titles[userChoice];
    }

    /*
     * private void removeActionBarTabs() { ActionBar mActionBar =
     * ((ActionBarActivity) getActivity()) .getSupportActionBar();
     *
     * mActionBar.invalidateOptionsMenu(); }
     */
    private final int DIFFERENCE = 1284;


    private void setOnClickListener_listViewToShow() {
        listViewToShow
                .setOnItemClickListener(new ListView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        /*
                         * SharedPreferences pref = null; Editor editor = null;
                         */
                        Fragment selectedUnitOrGrammarType = null;

                        FragmentManager fgManager = getFragmentManager();

                        FragmentTransaction fgts = fgManager.beginTransaction();

                        Bundle args = new Bundle();
                        switch (userChoice) {
                            case READ_PARAGRAPH:
                                args.putInt(Constant.UNIT_NO, position);
                                args.putInt(Constant.USER_CHOICE, READ_PARAGRAPH);

                                selectedUnitOrGrammarType = new SwipeViewActivity();
                                selectedUnitOrGrammarType.setArguments(args);

                                break;
                            case READ_QUE:
                                args.putInt(Constant.UNIT_NO, position);
                                args.putInt(Constant.USER_CHOICE, READ_QUE);

                                selectedUnitOrGrammarType = new SwipeViewActivity();
                                selectedUnitOrGrammarType.setArguments(args);

                                break;
                            case CHAL_QUE:

                                args.putInt(Constant.UNIT_NO, position);
                                args.putInt(Constant.USER_CHOICE, CHAL_QUE);
                                args.putString(Constant.CURRENT_USER, currentUser);


                                selectedUnitOrGrammarType = new FragQueChallenge();
                                selectedUnitOrGrammarType.setArguments(args);

                                break;
                            case READ_GRAMMAR:

                                args.putInt(Constant.GRAMMAR_TYPE, position);
                                selectedUnitOrGrammarType = new FragGrammarShow();
                                selectedUnitOrGrammarType.setArguments(args);

                                break;
                            case CHAL_GRAMMAR:

                                args.putInt(Constant.UNIT_NO, position);
                                args.putInt(Constant.USER_CHOICE, CHAL_GRAMMAR);
                                args.putString(Constant.CURRENT_USER, currentUser);

                                selectedUnitOrGrammarType = new FragQueChallenge();
                                selectedUnitOrGrammarType.setArguments(args);

                                break;
                        }
                        fgts.replace(R.id.mframe_container,
                                selectedUnitOrGrammarType, "swipe");
                        fgts.addToBackStack(null);
                        // fgts.addToBackStack(UNIT_LIST_TO_SWIPE);
                        fgts.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fgts.commit();

                    }

                });
    }
}
