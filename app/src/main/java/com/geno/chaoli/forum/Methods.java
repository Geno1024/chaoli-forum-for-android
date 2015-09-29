package com.geno.chaoli.forum;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class Methods
{
	public static boolean chkNetwork(Context context)
	{
		NetworkInfo n = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		return n.isConnected();
	}
}
