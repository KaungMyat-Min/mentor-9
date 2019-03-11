package com.trio.mentor9.customview;

import com.trio.mentor9.frag.FragEngText;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CustomTextView extends TextView{

	private final int SPEAK_OUT = 11;
	private final int SPEAK_SLOW = 9;
	
	public CustomTextView(Context context) {
		super(context);
		init();
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	

	public CustomTextView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
		}

	private void init(){
	
		Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/SmartZawgyi_v3.ttf");
		setTypeface(mTypeface);	
		setCustomSelectionActionModeCallback(new CustomActionMode());

	}
	
	private class CustomActionMode implements ActionMode.Callback{
		@Override
		public boolean onPrepareActionMode(ActionMode mode,
				Menu menu) {
			menu.removeItem(android.R.id.selectAll);
			menu.removeItem(android.R.id.cut);
			menu.removeItem(android.R.id.copy);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			FragEngText.tts.stop();
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add(0, SPEAK_SLOW, 0, "Slow");
			menu.add(0, SPEAK_OUT, 0, "Read This");
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode,
				MenuItem item) {
			switch (item.getItemId()) {
			case SPEAK_OUT:
				FragEngText.tts.setSpeechRate(0.85f);
				FragEngText.speakOut(getSelectedText());
				return true;

			case SPEAK_SLOW:
				FragEngText.tts.setSpeechRate(0.6f);
				FragEngText.speakOut(getSelectedText());
			}

			return false;
		}
		private CharSequence getSelectedText() {//bug may occurs here
			
			
			if (isFocused()) {
				int selStart = getSelectionStart();
				int selEnd = getSelectionEnd();
				return getText().subSequence(selStart, selEnd);
			}
			return null;
		}
	}
	
	
}
