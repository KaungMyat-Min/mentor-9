package com.trio.mentor9.frag;

import java.util.Locale;

import com.trio.mentor9.Constant;
import com.trio.mentor9.R;
import com.trio.mentor9.adaptor.GetValues;
import com.trio.mentor9.adaptor.ListParagraphAdaptor;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FragEngText extends Fragment {
    SharedPreferences pref;
    Editor prefEditor;

    private int UnitNo;


    private static boolean isEveryThingReady = false;
    private static TextView mTextView;
    private static ScrollView s;
    private static ListView listText;

    public static TextToSpeech tts;
    private boolean isTTSready = false;

    private final int SPEAK_OUT = 11;
    private final int SPEAK_SLOW = 9;

    private Thread t;
    private final int APP_FONT = 0;
    private final int PHONE_FONT = 1;

    private GetValues values;
    private int currentTextView;

    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        UnitNo = getArguments().getInt(Constant.UNIT_NO);
        values = new GetValues(context);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                tts = new TextToSpeech(getActivity().getApplicationContext(),
                        new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                if (status == TextToSpeech.SUCCESS) {
                                    isTTSready = true;
                                    Toast.makeText(
                                            context,
                                            "test to speak success",
                                            Toast.LENGTH_SHORT).show();

                                    int result = tts.setLanguage(Locale.US);

                                    if (result == TextToSpeech.LANG_MISSING_DATA
                                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                                        Toast.makeText(
                                                context,
                                                "Your phone is not supported the read out function",
                                                Toast.LENGTH_LONG).show();
                                        Log.e("TTS",
                                                "Language is noot supported");
                                    } else {
                                        speakOut("");

                                    }
                                } else {
                                    Toast.makeText(
                                            context,
                                            "TTS Init Fail", Toast.LENGTH_LONG)
                                            .show();
                                    Log.e("TTS", "INIT FAIL");
                                }
                            }
                        });
            }
        });
        t.start();

        View v = inflater.inflate(R.layout.frag_eng_text, container, false);

        pref = PreferenceManager.getDefaultSharedPreferences(context);
        s = (ScrollView) v.findViewById(R.id.eng_scroll);
        listText = (ListView) v.findViewById(R.id.list_eng_text);

        String[] paragraphs = getData();
        ListParagraphAdaptor adapter = new ListParagraphAdaptor(paragraphs, context);
        listText.setAdapter(adapter);


        setListeners();
        isEveryThingReady = true;
        return v;
    }

    @Override
    public void onStart() {

        super.onStart();
		/*
		String pref_eng_font_size_key = "pref_eng_font_size_key";
		String pref_eng_font_type_key = "prefEngFontTypeKey";

		float fontsize = Float.valueOf(pref.getString(pref_eng_font_size_key,
				"16"));
		int fontType = Integer.valueOf(pref.getString(pref_eng_font_type_key,
				"1"));
		if(mTextView!=null){
		mTextView.setTextSize(fontsize);
		if (fontType == APP_FONT) {
			Typeface mTypeface = Typeface.createFromAsset(getActivity()
					.getAssets(), "fonts/SmartZawgyi_v3.ttf");
			mTextView.setTypeface(mTypeface);
		} else if (fontType == PHONE_FONT) {
			Typeface mTypeface = Typeface.DEFAULT;
			mTextView.setTypeface(mTypeface);
		}
		}
	*/
    }


    private String[] getData() {
        return values.getTextEng(UnitNo);
    }

    private void setListeners() {

        listText.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Resources res = getActivity().getResources();
                if (currentTextView != position) {
                    if (mTextView != null) {
                        mTextView.setBackgroundColor(res.getColor(R.color.item_eng_text_background_color));
                    }

                    mTextView = (TextView) listText.getChildAt(position);
                    mTextView.setBackgroundColor(Color.WHITE);
                }
            }

        });
    }


    @SuppressWarnings("deprecation")
    public static void speakOut(CharSequence ss) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(ss, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(ss.toString(), TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    public static void moveTextView(int position) {
        final int positionf = position;
        if (isEveryThingReady) {
            listText.postDelayed(new Runnable() {

                @Override
                public void run() {

                    //listText.smoothScrollToPosition(positionf);

                    listText.setSelection(positionf);


                }
            }, 100);


        }
    }

    public static void stopSpeak() {
        if (tts != null) {
            tts.stop();
        }
    }

    @Override
    public void onDestroy() {
        if (t.isAlive()) {
            t.interrupt();

        }
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        super.onDestroy();
    }

}
