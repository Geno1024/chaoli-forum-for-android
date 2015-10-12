package com.geno.chaoli.forum;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsConstants
{
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
