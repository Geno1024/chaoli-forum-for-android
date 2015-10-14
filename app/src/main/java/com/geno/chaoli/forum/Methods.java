package com.geno.chaoli.forum;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URL;
import java.net.URLConnection;

public final class Methods
{
	public static Drawable getDrawableByUrl(String URL)
	{
		Drawable d = null;
		try
		{
			d = Drawable.createFromStream((new java.net.URL(URL)).openStream(), "avatar.png");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return d;
	}

	public static final class Network
	{
		public static boolean chkSwitchOpen(Context context)
		{
			NetworkInfo n = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
			return n.isConnected();
		}

		public static boolean chkAvailable(Context context)
		{
			NetworkInfo n = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
			return n.isAvailable();
		}
	}
}
