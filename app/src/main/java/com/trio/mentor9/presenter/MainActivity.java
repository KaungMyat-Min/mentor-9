package com.trio.mentor9.presenter;

import java.util.ArrayList;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.R;
import com.trio.mentor9.adaptor.UserDataDbAdaptor;
import com.trio.mentor9.frag.*;
import com.trio.mentor9.presenter.common.BaseActivity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;

    private FragmentManager mFragManager;

    /*
     * private CharSequence mTitle; private CharSequence mDrawerTitle;
     */

    private TextView textClickHelp;
    private TextView textClickSetting;
    private TextView textAddUser;

    private ActionBarDrawerToggle mDrwerToggle;

    private final String USER_CHOICE_KEY = "user_choice";
    private final int READ_PARAGRAPH = 0;
    private final int READ_QUE = 1;
    private final int CHAL_QUE = 2;
    private final int READ_GRAMMAR = 3;
    private final int CHAL_GRAMMAR = 4;
    private int userChoice;

    private int but_ok_check;
    private String currentUser;
    private final String KEY_CURRENT_USER = "current_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

        userChoice = i.getIntExtra(USER_CHOICE_KEY, 0);

        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        currentUser = pref.getString(KEY_CURRENT_USER, null);

        /* mTitle = mDrawerTitle = getTitle(); */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mdrawer_layout);

        textAddUser = (TextView) findViewById(R.id.text_click_add_user);
        textClickHelp = (TextView) findViewById(R.id.text_click_help);
        textClickSetting = (TextView) findViewById(R.id.text_click_setting);

        /*mNavList = (ListView) findViewById(R.id.mlist_slidermenu);*/


        mFragManager = getSupportFragmentManager();

        addFragment();
        textAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDailog();
            }
        });

        textClickHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityHelp.class);
                startActivity(i);
            }
        });

        textClickSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        PreferencesActivity.class);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(i);

            }
        });

        mDrwerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                /* getActionBar().setTitle(mTitle); */
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                /* getActionBar().setTitle(mTitle); */
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrwerToggle);
    }

    private void addFragment() {
        Fragment mUnitList = null;
        Bundle args = new Bundle();
        switch (userChoice) {
            case READ_PARAGRAPH:
                args.putInt(Constant.USER_CHOICE, READ_PARAGRAPH);
                mUnitList = new FragUnitList();
                mUnitList.setArguments(args);

                break;
            case READ_QUE:
                args.putInt(Constant.USER_CHOICE, READ_QUE);
                mUnitList = new FragUnitList();
                mUnitList.setArguments(args);
                break;
            case CHAL_QUE:
                if (currentUser == null) {
                    ShowDailog();
                }
                args.putInt(Constant.USER_CHOICE, CHAL_QUE);
                args.putString(Constant.CURRENT_USER, currentUser);
                mUnitList = new FragUnitList();
                mUnitList.setArguments(args);

                break;
            case READ_GRAMMAR:
                args.putInt(Constant.USER_CHOICE, READ_GRAMMAR);
                mUnitList = new FragUnitList();
                mUnitList.setArguments(args);

                break;
            case CHAL_GRAMMAR:
                if (currentUser == null) {
                    ShowDailog();
                }
                args.putInt(Constant.USER_CHOICE, CHAL_GRAMMAR);
                args.putString(Constant.CURRENT_USER, currentUser);
                mUnitList = new FragUnitList();
                mUnitList.setArguments(args);
                break;

        }

        mFragManager.beginTransaction().add(R.id.mframe_container, mUnitList)
                .commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrwerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrwerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void ShowDailog() {

        final int CHANGE_USER = 1;
        final int ADD_USER = 2;
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(true);
        dialog.setTitle(R.string.dialog_title_add_user);


        final ListView user_name_list = (ListView) dialog
                .findViewById(R.id.dialog_user_list);
        final Button but_OK = (Button) dialog.findViewById(R.id.dialog_ok);
        final Button but_delete = (Button) dialog.findViewById(R.id.dialog_delete);

        but_OK.setVisibility(View.INVISIBLE);
        final EditText userName = (EditText) dialog
                .findViewById(R.id.dialog_text);

        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/SmartZawgyi_v3.ttf");
        userName.setTypeface(tf);
        final UserDataDbAdaptor userDataDbAdaptor = new UserDataDbAdaptor(getApplicationContext());


        userName.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                but_OK.setText("Add User");
                dialog.setTitle(R.string.dialog_title_add_user);
                but_OK.setVisibility(View.VISIBLE);
                but_ok_check = ADD_USER;
                String user = userName.getText().toString();
                ArrayList<String> user_name_array = userDataDbAdaptor.selectUsers(user);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_list_item_1,
                        user_name_array);
                user_name_list.setAdapter(adapter);

            }
        });

        ArrayList<String> user_name_array = userDataDbAdaptor.selectUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, user_name_array);
        user_name_list.setAdapter(adapter);

        user_name_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView tv = (TextView) view;
                userName.setText(tv.getText());
                but_OK.setText("Change user");
                but_delete.setVisibility(View.VISIBLE);
                dialog.setTitle(R.string.dialog_title_change_user);
                but_ok_check = CHANGE_USER;

            }

        });


        but_OK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.hide();
                currentUser = userName.getText().toString();

				/*if (but_ok_check == CHANGE_USER) {
					UserDataDbAdaptor.selectAllAndUpdateAttempt(currentUser);
				}*/
                SharedPreferences pref = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                Editor editor = pref.edit();
                editor.putString(KEY_CURRENT_USER, currentUser);
                editor.apply();
            }
        });

        but_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                currentUser = userName.getText().toString();
                userDataDbAdaptor.open();
                userDataDbAdaptor.delete(currentUser);
                userDataDbAdaptor.close();
                userName.setText("");

            }
        });
        dialog.show();
    }

}
