package com.geno.chaoli.forum;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Constants
{
	public static final String tagNetwork = "Network";

	public static final String tagDraw = "Draw";

	public static final String msgTitle = "Title";

	public static final String msgSum = "Sum";

	public static final String msgAuthor = "Author";

	public static final String msgAvatar = "Avatar";

	public static final String statusStart = "Start";

	public static final String statusFinish = "Finish";

	public static final String stringConvPerPage = "ConversationsPerPage";

	public static Object get(String key, Context context)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

		switch (key)
		{
			case stringConvPerPage:
				return sp.getInt(stringConvPerPage, 50);
			default:
				return null;
		}
	}

	public static int getConvPerPage(Context context)
	{
		return (int) get(stringConvPerPage, context);
	}
}
