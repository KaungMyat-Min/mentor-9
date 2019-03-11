package com.trio.mentor9.adaptor;

import java.util.ArrayList;
import com.trio.mentor9.R;
import com.trio.mentor9.model.Question;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListShowAdaptor extends BaseAdapter {

	private Context context;
	private TextView question_Text;
	private TextView ans_true_Text;
	private TextView questino_no;

	private ArrayList<Question> question_detail;
	Typeface mTypeface;

	public ListShowAdaptor(Context c, ArrayList<Question> question) {
		context = c;
		this.question_detail = question;
		// mTypeface =
		// Typeface.createFromAsset(c.getApplicationContext().getAssets(),
		// "fonts/SmartZawgyi_v3.ttf");
	}

	@Override
	public int getCount() {
		if (question_detail != null) {
			return question_detail.size();
		} else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if (question_detail != null) {
			return question_detail.get(position);
		} else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (question_detail != null) {
			if (convertView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = layoutInflater.inflate(R.layout.item_question,
						null);
			}
			if (question_detail != null) {
				Question q = question_detail.get(position);
				
				questino_no = (TextView) convertView
						.findViewById(R.id.question_item_no);
				question_Text = (TextView) convertView
						.findViewById(R.id.question_item_text);
				ans_true_Text = (TextView) convertView
						.findViewById(R.id.question_item_answer_true);
				
				questino_no.setText(String.valueOf(position + 1));
				question_Text.setText(q.getQUES());
				ans_true_Text.setText(q.getANS_true());

			}
			return convertView;
		} else
			return null;
	}

}
