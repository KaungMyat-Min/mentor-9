package com.trio.mentor9.presenter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.trio.mentor9.R;
import com.trio.mentor9.presenter.common.BaseActivity;

public class ActivityHome extends BaseActivity {
	private ListView homeList;
	private final String USER_CHOICE_KEY="user_choice";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		homeList = (ListView) findViewById(R.id.list_home);
		String[] homeItems = getResources().getStringArray(R.array.array_home);
		
		
		
		ArrayAdapter<String> adaptor = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.item_home_list, homeItems);

		
		homeList.setAdapter(adaptor);
		setListeneron_HOMELIST();
	}

	private void setListeneron_HOMELIST() {
		homeList.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			Intent i = new Intent(ActivityHome.this,MainActivity.class);
				i.putExtra(USER_CHOICE_KEY,position);
			
				startActivity(i);
			}
			
		});
		
	}
}
