package com.trio.mentor9.adaptor;

import java.util.ArrayList;
import com.trio.mentor9.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NavDrawerAdaptor extends BaseAdapter {

	private Context context;
	private TextView mTextView;

	private ArrayList<String> navDrawerItems;
	Typeface mTypeface ;

	public NavDrawerAdaptor(Context c, ArrayList<String> navDrawerItems) {
		context = c;
		this.navDrawerItems = navDrawerItems;
		mTypeface = Typeface.createFromAsset(c.getApplicationContext().getAssets(), "fonts/SmartZawgyi_v3.ttf");
	}

	@Override
	public int getCount() {
		if (navDrawerItems != null) {
			return navDrawerItems.size();
		} else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if (navDrawerItems != null) {
			return navDrawerItems.get(position);
		} else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (navDrawerItems != null) {
			if (convertView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = layoutInflater.inflate(
						R.layout.mdrawer_list_item, null);
			}
			if (navDrawerItems != null) {
				mTextView = (TextView) convertView
						.findViewById(R.id.m_drawer_item_title);
				mTextView.setText(navDrawerItems.get(position));
				mTextView.setTypeface(mTypeface);
				
			}
			return convertView;
		} else
			return null;
	}
}
