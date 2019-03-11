package com.trio.mentor9.frag;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trio.mentor9.common.Constant;
import com.trio.mentor9.adaptor.GetValues;
import com.trio.mentor9.R;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

public class FragImage extends Fragment {

	private static boolean isEverythingReady = false;

	private static Resources res;
	private int unitNo;
	private static WebView imageView;
	private static TextView imageLabel;
	private static TextView imageDetail;
	private Button button_next;
	private Button button_previous;
	static private Button button_detail;
	private static LinearLayout imageDetailContainer;

	private static String[] images_name;
	private static String[] images_discription;
	// private static String[] image_detials;
	private static int shown_image_pst = 0;
	private GetValues values;
	private String label, detail;

	static private CharSequence url_prefix = "<html><body><center><img src=\"";
	static private CharSequence url_postfix_height = "\" height=\"97%\"/> </center></body></html>";
	static private CharSequence url_postfix_width = "\" width=\"100%\"/> </center></body></html>";
	private Pattern pattern;

	static private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		unitNo = getArguments().getInt(Constant.UNIT_NO);
		values = new GetValues(getActivity());
		context = getActivity();

		shown_image_pst = 0;
		View v = inflater.inflate(R.layout.frag_image, container, false);

		imageView = (WebView) v.findViewById(R.id.image_view);
		imageDetail = (TextView) v.findViewById(R.id.image_detail);
		imageLabel = (TextView) v.findViewById(R.id.image_label);
		button_detail = (Button) v.findViewById(R.id.image_detail_button);
		imageDetailContainer = (LinearLayout) v
				.findViewById(R.id.image_detail_container);
		button_next = (Button) v.findViewById(R.id.image_next);

		imageView.setBackgroundColor(0xd7d7d7);
		WebSettings setting = imageView.getSettings();
		setting.setBuiltInZoomControls(true);
		setting.setSupportZoom(true);
		setting.setDisplayZoomControls(false);

		button_next.setVisibility(View.VISIBLE);
		button_previous = (Button) v.findViewById(R.id.image_previous);
		button_previous.setVisibility(View.VISIBLE);

		pattern = Pattern.compile("@");

		//
		// update the method for unit sync
		//

		getData();
		populateFields(0);
		setonclickListener();

		return v;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		context = null;
	}

	private void getData() {
		res = getResources();
		images_name = values.getImageTitle(unitNo);
		images_discription = values.getImageDiscription(unitNo);
		// image_detials = values.getImageDetails(unitNo);
		if (images_name != null && images_discription != null) {
			isEverythingReady = true;
		} else
			isEverythingReady = false;
	}

	private void populateFields(int positon) {
		if (isEverythingReady) {

			String name = images_name[positon];

			Drawable image = getDrawable(name,context);
			int height = image.getMinimumHeight();
			int width = image.getMinimumWidth();
			String url = null;
			if (height > width) {
				url = url_prefix + name + url_postfix_height;
			} else {
				url = url_prefix + name + url_postfix_width;
			}

			imageView.loadDataWithBaseURL("file:///android_asset/", url,
					"text/html", "utf-8", null);

			StringCut(images_discription[positon]);
			imageLabel.setText(Html.fromHtml(label));
			if (detail != null) {
				imageDetail.setText(detail);

				imageDetailContainer.setVisibility(View.INVISIBLE);

				button_detail.setVisibility(View.VISIBLE);

			} else {
				imageDetailContainer.setVisibility(View.INVISIBLE);
				button_detail.setVisibility(View.INVISIBLE);
			}
			button_detail.setText("Show Detail");

		} else {
			imageLabel
					.setText("There is no image to display now.\nYou can ask any image you want at our facebook page.\nWe'd add them in the next versions.");
			button_next.setVisibility(View.INVISIBLE);
			button_previous.setVisibility(View.INVISIBLE);
			button_detail.setVisibility(View.INVISIBLE);

		}
	}

	static private Drawable getDrawable(String fileName, Context context) {
		Drawable drawable = null;
		InputStream inputStream = null;
		try {
			inputStream = context.getAssets().open(fileName);
			drawable = Drawable.createFromStream(inputStream, null);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return drawable;
	}

	private void StringCut(String original) {

		Matcher m = pattern.matcher(original);

		int start = 0;
		int end = original.length();
		if (m.find()) {
			start = m.start();
			label = original.substring(0, start);

			detail = original.substring(start + 1, end);
		} else {
			label = original;
			detail = null;
		}
	}

	public static void moveImage(int position) {
		if (isEverythingReady) {

			String name = images_name[position];

			Drawable image = getDrawable(name,context);
			int height = image.getMinimumHeight();
			int width = image.getMinimumWidth();
			String url = null;
			if (height > width) {
				url = url_prefix + name + url_postfix_height;
			} else {
				url = url_prefix + name + url_postfix_width;
			}

			imageView.loadDataWithBaseURL("file:///android_asset/", url,
					"text/html", "utf-8", null);

			String original = images_discription[position];
			Pattern pattern = Pattern.compile("@");
			Matcher m = pattern.matcher(original);

			int start = 0;
			int end = original.length();
			String label = null;
			String detail = null;
			if (m.find()) {
				start = m.start();
				label = original.substring(0, start);

				detail = original.substring(start + 1, end);
			} else {
				label = original;
			}

			imageLabel.setText(Html.fromHtml(label));
			if (detail != null) {
				imageDetail.setText(detail);
				imageDetailContainer.setVisibility(View.INVISIBLE);
				button_detail.setText("Show Detail");
				button_detail.setVisibility(View.VISIBLE);
			}else{
				imageDetail.setVisibility(View.INVISIBLE);
				button_detail.setVisibility(View.INVISIBLE);
			}

			shown_image_pst = position;
		}
	}

	private void setonclickListener() {
		button_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shown_image_pst < images_name.length) {
					if (shown_image_pst != images_name.length - 1) {
						++shown_image_pst;
					} else {
						Toast.makeText(
								context,
								"There is no more images for this unit. More image will come in next version",
								Toast.LENGTH_SHORT).show();
						return;
					}
					populateFields(shown_image_pst);

				}
			}
		});
		button_previous.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shown_image_pst >= 0) {
					if (shown_image_pst != 0) {
						--shown_image_pst;
					} else {
						Toast.makeText(
								context,
								"There is no more images for this unit. More image will come in next version",
								Toast.LENGTH_SHORT).show();
						return;
					}

					populateFields(shown_image_pst);

				}

			}
		});

		button_detail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!imageDetail.isShown()) {
					imageDetailContainer.setVisibility(View.VISIBLE);
					button_detail.setText("Hide Detail");
				} else {
					imageDetailContainer.setVisibility(View.INVISIBLE);
					button_detail.setText("Show Detail");
				}
			}
		});
	}
}
