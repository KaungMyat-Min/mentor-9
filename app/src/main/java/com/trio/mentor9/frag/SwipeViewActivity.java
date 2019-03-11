package com.trio.mentor9.frag;

import java.util.ArrayList;

import com.trio.mentor9.Constant;
import com.trio.mentor9.adaptor.GetValues;
import com.trio.mentor9.adaptor.NavDrawerAdaptor;
import com.trio.mentor9.adaptor.PageAdaptor;
import com.trio.mentor9.R;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SwipeViewActivity extends Fragment {

    public static final String TAG = SwipeViewActivity.class.getSimpleName();

    private ViewPager mViewPager;
    private PageAdaptor pageAdaptor;
    private static int unitNO;
    private ActionBar mActionBar;
    private ListView slidemenu;
    private DrawerLayout mDrawerLayout;

    private int noOFpara;
    private ArrayList<String> paragraphs;
    private int shownFragNo = 1;
    private final int FACTS_FRAG = -1;
    private final int QUE_SHOW_FRAG = 0;
    private final int ENG_FRAG = 1;
    private final int IMG_FRAG = 2;
    private PagerTitleStrip pagerTitle;

    private CharSequence action_bar_eng;
    // private CharSequence action_bar_mm;

    private GetValues values;
    // private final int DIFFERENCE = 1284;

    private TextView action_bar_text_view;
    private View vv;

    private final int READ_PARAGRAPH = 0;
    private final int READ_QUE = 1;
    private static final int CHAL_QUE = 2;
    private static final int CHAL_GRAMMAR = 4;
    private int userChoice;
    private static String currentUser;
    private final String KEY_CURRENT_USER = "curent_user";
    private Context context;
    private Activity activity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        unitNO = bundle.getInt(Constant.UNIT_NO);
        userChoice = bundle.getInt(Constant.USER_CHOICE);
        context = getActivity();
        activity = getActivity();


        View v = inflater.inflate(R.layout.swipe_view_activity, container,
                false);
        fgmanager = getFragmentManager();
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        currentUser = pref.getString(KEY_CURRENT_USER, null);

        values = new GetValues(context);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(
                R.id.mdrawer_layout);
        slidemenu = (ListView) activity.findViewById(R.id.mlist_slidermenu);

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        pagerTitle = (PagerTitleStrip) v.findViewById(R.id.pager_title_strip);

        mActionBar = ((AppCompatActivity) activity).getSupportActionBar();
        // action_bar_mm = values.getActionBarTitleMM(unitNO);
        action_bar_eng = values.getActionBarTitleEng(unitNO);

        //
        //
        // action bar update start here
        //
        //
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vv = inflator.inflate(R.layout.action_mode_custom_title, null);
        action_bar_text_view = (TextView) vv.findViewById(R.id.action_bar_text);
        /*
         * Typeface typeface = Typeface.createFromAsset(getActivity()
         * .getApplicationContext().getAssets(), "fonts/SmartZawgyi_v3.ttf");
         * action_bar_text_view.setTypeface(typeface);
         */
        mActionBar.setCustomView(vv);
        setcustomTitle(action_bar_eng);

        if (!mActionBar.isShowing()) {
            mActionBar.show();
            pagerTitle.setVisibility(View.VISIBLE);
        }

        pageAdaptor = new PageAdaptor(unitNO, getChildFragmentManager(),
                values, context);
        mViewPager.setAdapter(pageAdaptor);

        if (userChoice == READ_PARAGRAPH) {
            shownFragNo = ENG_FRAG;
            mViewPager.setCurrentItem(ENG_FRAG);
        } else if (userChoice == READ_QUE) {
            mViewPager.setCurrentItem(QUE_SHOW_FRAG);
            shownFragNo = READ_QUE;
        }

        populateSlideMenu();
        slidemenu.setVisibility(View.VISIBLE);
        slidemenu.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                slidemenu.setItemChecked(position, true);
                slidemenu.setSelection(position);
                mDrawerLayout.closeDrawer(Gravity.LEFT);

                if (shownFragNo != IMG_FRAG) {
                    FragEngText.moveTextView(position);

                } else {
                    FragImage.moveImage(position);
                }
            }

        });

        mViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageSelected(int arg0) {
                        if (arg0 == FACTS_FRAG) {

                            setcustomTitle(action_bar_eng);

                        } else {

                            setcustomTitle(action_bar_eng.toString()
                                    .toUpperCase());
                        }

                        shownFragNo = arg0;
                        populateSlideMenu();
                        if (shownFragNo != IMG_FRAG
                                && shownFragNo != FACTS_FRAG) {

                            pagerTitle.setVisibility(View.VISIBLE);

                        } else {

                            pagerTitle.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int arg0) {

                    }
                });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        action_bar_text_view.setText(values.getApplicationName());
    }

    // ..............fragTransation methods start here.............

    private static FragmentManager fgmanager;

    // private static final String
    // SWIPE_2_QUE_CHALLENGE="swipe_view_to_challenge";

    public static void navigateToQueChallenge(int unitNO, int userchoice) {
        FragQueChallenge fragQueChallenge = null;
        Bundle args = new Bundle();
        args.putInt(Constant.UNIT_NO, unitNO);
        args.putString(Constant.CURRENT_USER, currentUser);

        if (userchoice == CHAL_QUE) {

            args.putInt(Constant.USER_CHOICE, Constant.CHAL_QUE);

        } else if (userchoice == CHAL_GRAMMAR) {
            args.putInt(Constant.USER_CHOICE, Constant.CHAL_GRAMMAR);

        }

        fragQueChallenge = new FragQueChallenge();
        fragQueChallenge.setArguments(args);

        FragmentTransaction fragTrans = fgmanager.beginTransaction();
        // Fragment frag = fgmanager.findFragmentByTag(SWIPE_2_QUE_CHALLENGE);

        fragTrans.replace(R.id.mframe_container, fragQueChallenge);
        fragTrans.addToBackStack(null);
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit();
    }

    // .........fragTransation methods end here...............

    private void setcustomTitle(CharSequence title) {
        action_bar_text_view.setText(title);
    }

    private void populateSlideMenu() {
        if (paragraphs != null && !paragraphs.isEmpty()) {
            paragraphs.clear();
        }
        noOFpara = values.getNoOfParagraph(unitNO);

        if (shownFragNo != FACTS_FRAG && shownFragNo != QUE_SHOW_FRAG) {
            if (shownFragNo != IMG_FRAG) {

                noOFpara = values.getNoOfParagraph(unitNO);
                for (int i = 1; i < noOFpara + 1; i++) {
                    paragraphs.add("စာပိုဒ္ " + i);
                }

                NavDrawerAdaptor drawerAdaptor = new NavDrawerAdaptor(context,
                        paragraphs);
                slidemenu.setAdapter(drawerAdaptor);
                slidemenu.setVisibility(View.VISIBLE);
                return;
            }

            ArrayList<String> aa = values.getImageLabels(unitNO);
            if (aa != null) {
                NavDrawerAdaptor drawerAdaptor = new NavDrawerAdaptor(context,
                        aa);
                slidemenu.setAdapter(drawerAdaptor);
                slidemenu.setVisibility(View.VISIBLE);
            }
        } else {
            slidemenu.setVisibility(View.INVISIBLE);
        }

    }
}