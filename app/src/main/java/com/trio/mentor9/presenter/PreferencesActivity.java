package com.trio.mentor9.presenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.trio.mentor9.R;

public class PreferencesActivity extends PreferenceActivity {

	private SharedPreferences pref;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.userpreference);

		pref = PreferenceManager.getDefaultSharedPreferences(this);

		pref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {

			@Override
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {
			}
		});

	}

}
