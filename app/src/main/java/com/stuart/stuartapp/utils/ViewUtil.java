package com.stuart.stuartapp.utils;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

public class ViewUtil {

	/**
	 * @param tv
	 * @param baseText
	 *            if the string of highlightText is a subset of the string of
	 *            baseText,highlight the string of highlightText.
	 */
	public static void showTextHighlight(TextView tv, String baseText) {
		if ((null == tv) || (null == baseText)) {
			return;
		}

		int index = baseText.indexOf(",");
		if (index < 0) {
			tv.setText(baseText);
			// tv.setVisibility(View.GONE);
			return;
		}
		baseText = baseText.replace(",", "   ");
		index += 2;

		/**
		 * "<u><font color=#FF0000 >"+str+"</font></u>"; //with underline
		 * "<font color=#FF0000 >"+str+"</font>"; //without underline
		 */
		
		Spanned spanned = Html.fromHtml(
				"<font color=#ff0000 >" +
				baseText.substring(0, index) +"</font>"
				+ "<font color=#1222ED >"
				+ baseText.substring(index, baseText.length()) + "</font>"
				);

		tv.setText(spanned);
	}


}
