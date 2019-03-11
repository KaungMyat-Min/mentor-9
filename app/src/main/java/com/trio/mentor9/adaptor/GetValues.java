package com.trio.mentor9.adaptor;

import java.util.ArrayList;

import com.trio.mentor9.R;

import android.content.Context;
import android.content.res.Resources;

public class GetValues {
	private final int difference = 0;
	private Resources res;

	public GetValues(Context c) {
		res = c.getResources();
	}

	public CharSequence getApplicationName() {
		return res.getString(R.string.app_name);
	}

	// methods from swipe view
	public CharSequence getActionBarTitleEng(int unitNO) {// for action bar
															// title eng
		switch (unitNO - difference + 1) {
		case 1:
			return res.getString(R.string.unit_01_title_eng);
		case 2:
			return res.getString(R.string.unit_02_title_eng);
		case 3:
			return res.getString(R.string.unit_03_title_eng);
		case 4:
			return res.getString(R.string.unit_04_title_eng);
		case 5:
			return res.getString(R.string.unit_05_title_eng);
		case 6:
			return res.getString(R.string.unit_06_title_eng);
		case 7:
			return res.getString(R.string.unit_07_title_eng);
		case 8:
			return res.getString(R.string.unit_08_title_eng);
		case 9:
			return res.getString(R.string.unit_09_title_eng);
		case 10:
			return res.getString(R.string.unit_10_title_eng);
		}
		return null;
	}

	public CharSequence getActionBarTitleMM(int unitNO) {// for action bar title
															// mm
		switch (unitNO - difference + 1) {
		case 1:
			return res.getString(R.string.unit_01_title_mm);
		case 2:
			return res.getString(R.string.unit_02_title_mm);
		case 3:
			return res.getString(R.string.unit_03_title_mm);
		case 4:
			return res.getString(R.string.unit_04_title_mm);
		case 5:
			return res.getString(R.string.unit_05_title_mm);
		case 6:
			return res.getString(R.string.unit_06_title_mm);
		case 7:
			return res.getString(R.string.unit_07_title_mm);
		case 8:
			return res.getString(R.string.unit_08_title_mm);
		case 9:
			return res.getString(R.string.unit_09_title_mm);
		case 10:
			return res.getString(R.string.unit_10_title_mm);
		}
		return null;
	}

	public int getNoOfParagraph(int unitNO) {// for slide menu paragraph no

		switch (unitNO - difference + 1) {
		case 1:
			return Integer.valueOf(res
					.getString(R.string.unit_01_no_of_paragraph));
		case 2:
			return Integer.valueOf(res
					.getString(R.string.unit_02_no_of_paragraph));
		case 3:
			return Integer.valueOf(res
					.getString(R.string.unit_03_no_of_paragraph));

		case 4:
			return Integer.valueOf(res
					.getString(R.string.unit_04_no_of_paragraph));
		case 5:
			return Integer.valueOf(res
					.getString(R.string.unit_05_no_of_paragraph));
		case 6:
			return Integer.valueOf(res
					.getString(R.string.unit_06_no_of_paragraph));
		case 7:
			return Integer.valueOf(res
					.getString(R.string.unit_07_no_of_paragraph));
		case 8:
			return Integer.valueOf(res
					.getString(R.string.unit_08_no_of_paragraph));
		case 9:
			return Integer.valueOf(res
					.getString(R.string.unit_09_no_of_paragraph));
		case 10:
			return Integer.valueOf(res
					.getString(R.string.unit_10_no_of_paragraph));
		}
		return 0;
	}

	/*
	 * public ArrayList<String> getImageLabels(int unitNO) {// for slide menu
	 * image String[] image_label = null; ArrayList<String> image_lb = new
	 * ArrayList<String>(); switch (unitNO - difference + 1) { case 1:
	 * image_label = res.getStringArray(R.array.unit_1_image_label); break; case
	 * 2: image_label = res.getStringArray(R.array.unit_2_image_label); break;
	 * case 3: image_label = res.getStringArray(R.array.unit_3_image_label);
	 * break; case 4: image_label =
	 * res.getStringArray(R.array.unit_4_image_label); break; case 5:
	 * image_label = res.getStringArray(R.array.unit_5_image_label); break; case
	 * 6: image_label = res.getStringArray(R.array.unit_6_image_label); break;
	 * case 7: image_label = res.getStringArray(R.array.unit_7_image_label);
	 * break; case 9: image_label =
	 * res.getStringArray(R.array.unit_9_image_label); break; case 10:
	 * image_label = res.getStringArray(R.array.unit_10_image_label); break;
	 * 
	 * } if (image_label != null) { for (String label : image_label) {
	 * image_lb.add(label); } } return image_lb; }
	 */

	// fragEng methods

	public String[] getTextEng(int unitNo) {
		switch (unitNo - difference + 1) {
		case 1:

			return res.getStringArray(R.array.unit_01_combined);

		case 2:
			return res.getStringArray(R.array.unit_02_combined);

		case 3:
			return res.getStringArray(R.array.unit_03_combined);

		case 4:
			return res.getStringArray(R.array.unit_04_combined);

		case 5:
			return res.getStringArray(R.array.unit_05_combined);

		case 6:
			return res.getStringArray(R.array.unit_06_combined);

		case 7:
			return res.getStringArray(R.array.unit_07_combined);

		case 8:
			return res.getStringArray(R.array.unit_08_combined);

		case 9:
			return res.getStringArray(R.array.unit_09_combined);

		case 10:
			return res.getStringArray(R.array.unit_10_combined);

		}
		return null;

	}

	//
	//
	// image methode
	//
	//
	public String[] getImageTitle(int unitNo) {
		// update this method only

		switch (unitNo - difference + 1) {
		
		/*  case 1: return (String[])
		  res.getStringArray(R.array.unit1_image_title);
		  
		  case 2: return (String[])
		  res.getStringArray(R.array.unit2_image_title);
		  
		  case 3: return (String[])
		  res.getStringArray(R.array.unit3_image_title);*/
		  
		  case 4: return (String[])
		  res.getStringArray(R.array.unit4_image_title);
		/*  
		  case 5: return (String[])
		  res.getStringArray(R.array.unit5_image_title);
		  
		  case 6: return (String[])
		  res.getStringArray(R.array.unit6_image_title);
		 */

		case 7:
			return (String[]) res.getStringArray(R.array.unit7_image_title);
		case 8:
			return (String[]) res.getStringArray(R.array.unit8_image_title);

		case 9:
			return (String[]) res.getStringArray(R.array.unit9_image_title);

		case 10:
			return (String[]) res.getStringArray(R.array.unit10_image_title);

		default:
			return null;
			// isPossibleToPopulate = false;
		}

	}

	public ArrayList<String> getImageLabels(int unitNO) {// for slide menu image
															// title

		String[] image_label = null;
		ArrayList<String> image_lb = new ArrayList<String>();

		switch (unitNO - difference + 1) {
		/*
		  case 1: image_label = res.getStringArray(R.array.unit_1_image_label);
		  break; case 2: image_label =
		  res.getStringArray(R.array.unit_2_image_label); break; case 3:
		  image_label = res.getStringArray(R.array.unit_3_image_label); break;*/
		  case 4: image_label = res.getStringArray(R.array.unit_4_image_label);
		  break; 
		  /*case 5: image_label =
		  res.getStringArray(R.array.unit_5_image_label); break; case 6:
		  image_label = res.getStringArray(R.array.unit_6_image_label); break;*/
		 
		case 7:
			image_label = res.getStringArray(R.array.unit_7_image_label);
			break;
		case 8:
			image_label = res.getStringArray(R.array.unit_8_image_label);
			break;
		case 9:
			image_label = res.getStringArray(R.array.unit_9_image_label);
			break;
		case 10:
			image_label = res.getStringArray(R.array.unit_10_image_label);
			break;
		}
		if (image_label != null) {
			for (String label : image_label) {
				image_lb.add(label);
			}
		}
		return image_lb;
	}

	public String[] getImageDiscription(int unitNo) {

		switch (unitNo - difference + 1) {
		
		/*  case 1:
		  
		  return (String[]) res
		  .getStringArray(R.array.unit_1_image_discription);
		  
		  case 2: return (String[]) res
		  .getStringArray(R.array.unit_2_image_discription);
		  
		  case 3: return (String[]) res
		  .getStringArray(R.array.unit_3_image_discription);*/
		  
		  case 4: return (String[]) res
		  .getStringArray(R.array.unit_4_image_discription);
		  
		/*  case 5: return (String[]) res
		  .getStringArray(R.array.unit_5_image_discription);
		  
		  case 6: return (String[]) res
		  .getStringArray(R.array.unit_6_image_discription);
		 */

		case 7:
			return (String[]) res
					.getStringArray(R.array.unit_7_image_discription);

		case 8:
			return (String[]) res
					.getStringArray(R.array.unit_8_image_discription);

		case 9:
			return (String[]) res
					.getStringArray(R.array.unit_9_image_discription);

		case 10:
			return (String[]) res
					.getStringArray(R.array.unit_10_image_discription);

		default:
			return null;

		}

	}

	public String[] getImageDetails(int unitNo) {
		return null;
	}
}
