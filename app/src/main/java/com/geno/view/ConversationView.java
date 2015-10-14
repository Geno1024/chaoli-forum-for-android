package com.geno.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geno.chaoli.forum.R;

public class ConversationView extends RelativeLayout
{
	private ImageView avatar;
	private TextView author;
	private TextView convTitle;
	private TextView convSummary;

	public ConversationView(Context context)
	{
		super(context);
		View.inflate(context, R.layout.conversationview, this);
		avatar = (ImageView) findViewById(R.id.avatar);
		author = (TextView) findViewById(R.id.author);
		convTitle = (TextView) findViewById(R.id.convTitle);
		convSummary = (TextView) findViewById(R.id.convSummary);
	}

	public ConversationView setAvatar(Drawable d)
	{
		this.avatar.setImageDrawable(d);
		return this;
	}

	public ConversationView setAvatar(ImageView v)
	{
		this.avatar = v;
		return this;
	}

	public Drawable getAvatar()
	{
		return this.avatar.getDrawable();
	}

	public ConversationView setAuthor(String author)
	{
		this.author.setText(author);
		return this;
	}

	public String getAuthor()
	{
		return this.author.getText().toString();
	}

	public ConversationView setConvTitle(String convTitle)
	{
		this.convTitle.setText(convTitle);
		return this;
	}

	public String getConvTitle()
	{
		return this.convTitle.getText().toString();
	}

	public ConversationView setConvSum(String convSum)
	{
		this.convSummary.setText(convSum);
		return this;
	}

	private String getConvSum()
	{
		return this.convSummary.getText().toString();
	}
}
