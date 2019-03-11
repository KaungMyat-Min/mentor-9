package com.trio.mentor9.presenter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trio.mentor9.R;
import com.trio.mentor9.presenter.common.BaseActivity;

public class ActivityHelp extends BaseActivity {
	private ImageView image;
	private Button button_next;
	private Button button_previous;
	private TextView text;
	private int pageShown;
	private Button button_facebook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		image = (ImageView) findViewById(R.id.help_image_view);
		button_next = (Button) findViewById(R.id.help_button_next);
		button_previous = (Button) findViewById(R.id.help_button_previous);
		button_facebook = (Button) findViewById(R.id.help_join_facebook);
		
		text = (TextView) findViewById(R.id.help_text_view);
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/SmartZawgyi_v3.ttf");
		text.setTypeface(typeface);
		populateField(pageShown);

		button_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pageShown < 4) {
					populateField(pageShown + 1);
				} else {
					Intent i = new Intent(ActivityHelp.this, MainActivity.class);
					startActivity(i);
					ActivityHelp.this.finish();
				}
			}
		});

		button_previous.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				populateField(pageShown - 1);
			}
		});
		button_facebook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToFacebookPage();				
			}
		});
	}
private void goToFacebookPage(){
	Intent facebookAppIntent;
	try{
		facebookAppIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/1597683110505744"));
		startActivity(facebookAppIntent);
	}catch(ActivityNotFoundException e){
		//facebookAppIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://facebook.com/Mentor"));
		//startActivity(facebookAppIntent);
	}
}
	private void populateField(int page) {
		pageShown = page;
		switch (page) {
		case 0:
			/* image.setImageResource(R.drawable.hekp_speak); */
			text.setText(Html.fromHtml(getResources().getString(
					R.string.help_welcome_text_mm)));
			if(image.isShown())image.setVisibility(View.INVISIBLE);
			if(!text.isShown()) text.setVisibility(View.VISIBLE);
			button_next.setText("Next");
			button_previous.setVisibility(View.INVISIBLE);
			break;
/*		case 1:
			text.setText(Html.fromHtml(getResources().getString(
					R.string.help_explain_poems)));

			 image.setImageResource(R.drawable.help_at_eng_frag); 
			 button_next.setText("Next Tip"); 
			button_previous.setText("Previous");
			button_previous.setVisibility(View.VISIBLE);
			break;
*/		case 1:
			text.setText(Html.fromHtml(getResources().getString(
					R.string.help_feature)));
			/*
			 * image.setImageResource(R.drawable.help_at_image_frag);
			 * button_next.setText("OK. I got it.");
			 * button_previous.setText("Previous");
			 */
			if(image.isShown())image.setVisibility(View.INVISIBLE);
			if(!text.isShown()) text.setVisibility(View.VISIBLE);
			button_previous.setText("Previous tip");
			button_previous.setVisibility(View.VISIBLE);
			break;

		case 2:
			/*
			 * text.setText(Html.fromHtml(getResources().getString(
			 * R.string.help_welcome_text)));
			 */
			if(!image.isShown())image.setVisibility(View.VISIBLE);
			if(text.isShown()) text.setVisibility(View.INVISIBLE);
			image.setImageResource(R.drawable.hekp_speak);
			button_next.setText("Next Tip");
			button_previous.setText("Previous tip");
			break;
/*		case 3:
			
			 * text.setText(Html.fromHtml(getResources().getString(
			 * R.string.help_explain_poems)));
			 
			if(!image.isShown())image.setVisibility(View.VISIBLE);
			if(text.isShown()) text.setVisibility(View.INVISIBLE);
			image.setImageResource(R.drawable.help_at_eng_frag);
			break;*/
	/*	case 3:
			
			 * text.setText(Html.fromHtml(getResources().getString(
			 * R.string.help_feature)));
			 
			if(!image.isShown())image.setVisibility(View.VISIBLE);
			if(text.isShown()) text.setVisibility(View.INVISIBLE);
			image.setImageResource(R.drawable.help_at_image_frag);
			break;*/
		
		case 3:
			
			text.setText(Html.fromHtml((getResources().getString(R.string.help_motto))));
			
			
			/* image.setImageResource(R.drawable.help_at_image_frag); */
			if(image.isShown())image.setVisibility(View.INVISIBLE);
			if(!text.isShown()) text.setVisibility(View.VISIBLE);
			button_next.setText("OK. I got it.");
			button_previous.setText("Previous");
			break;
		}
	}
}
