package com.geno.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConversationView extends RelativeLayout
{
	private ImageView avatar;
	private TextView convTitle;
	private TextView convSummary;

	public ConversationView(Context context)
	{
		super(context);
	}
}
