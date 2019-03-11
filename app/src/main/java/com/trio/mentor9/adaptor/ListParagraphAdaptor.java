package com.trio.mentor9.adaptor;

import com.trio.mentor9.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListParagraphAdaptor extends BaseAdapter{
	String [] paragraphs;
	Context context;
	
	public ListParagraphAdaptor(String [] paragraphs, Context context){
		this.paragraphs= paragraphs;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return paragraphs.length;
	}

	@Override
	public Object getItem(int position) {
		if (paragraphs != null){
			return paragraphs[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = layoutInflater.inflate(R.layout.item_eng_text,
				null);
		TextView textView = (TextView) convertView.findViewById(R.id.item_eng_text);
		textView.setText(paragraphs[position]);
		return convertView;
	}
	
}
