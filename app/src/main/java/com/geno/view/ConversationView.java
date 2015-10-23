package com.geno.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geno.chaoli.forum.R;
import com.geno.nodes.ConversationStruct;

public class ConversationView extends RelativeLayout
{
	private TextView author;
	private ImageView authorAvatar;
	private TextView lastReply;
	private ImageView lastReplyAvatar;
	private TextView convTitle;
	private TextView convSummary;
	private TextView lastReplyTime;
	private TextView replyCount;
	private TextView category;

	public ConversationView(Context context, ConversationStruct struct)
	{
		super(context);
		init(context, struct);
	}

	public ConversationView(Context context)
	{
		super(context);
		init(context, null);
	}

	private void init(Context context, ConversationStruct struct)
	{
		View.inflate(context, R.layout.conversationview, this);
		author = (TextView) findViewById(R.id.author);
		authorAvatar = (ImageView) findViewById(R.id.authorAvatar);
		//lastReply = (TextView) findViewById(R.id.lastReply);
		convTitle = (TextView) findViewById(R.id.convTitle);
		convSummary = (TextView) findViewById(R.id.convSummary);
		if (struct != null)
		{
			author.setText(struct.getAuthor());
		}
	}

	public ConversationView setAuthorAvatar(Drawable d)
	{
		this.authorAvatar.setImageDrawable(d);
		return this;
	}

	public Drawable getauthorAvatar()
	{
		return this.authorAvatar.getDrawable();
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
