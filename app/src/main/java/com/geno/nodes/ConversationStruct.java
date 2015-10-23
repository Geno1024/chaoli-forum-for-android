package com.geno.nodes;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

public class ConversationStruct
{
	private String author;
	private Drawable authorAvatar;
	private String lastReply;
	private Drawable lastReplyAvatar;
	private String convTitle;
	private String convSummary;
	private String lastReplyTime;
	private String replyCount;
	private String category;

	public ConversationStruct()
	{
		author = "";
		authorAvatar = null;
		lastReply = "";
		lastReplyAvatar = null;
		convTitle = "";
		convSummary = "";
		lastReplyTime = "";
		replyCount = "";
		category = "";
	}

	public ConversationStruct(@Nullable String author, @Nullable Drawable authorAvatar, @Nullable String lastReply, @Nullable Drawable lastReplyAvatar, @Nullable String convTitle, @Nullable String convSummary, @Nullable String lastReplyTime, @Nullable String replyCount, @Nullable String category)
	{
		this.author = author == null ? "" : author;
		this.authorAvatar = authorAvatar;
		this.lastReply = lastReply == null ? "" : lastReply;
		this.lastReplyAvatar = lastReplyAvatar;
		this.convTitle = convTitle == null ? "" : convTitle;
		this.convSummary = convSummary == null ? "" : convSummary;
		this.lastReplyTime = lastReplyTime == null ? "" : lastReplyTime;
		this.replyCount = replyCount == null ? "" : replyCount;
		this.category = category == null ? "" : category;
	}

	public String getAuthor() {return author;}

	public Drawable getAuthorAvatar() {return authorAvatar;}

	public String getLastReply() {return lastReply;}

	public Drawable getLastReplyAvatar() {return lastReplyAvatar;}

	public String getConvTitle() {return convTitle;}

	public String getConvSummary() {return convSummary;}

	public String getLastReplyTime() {return lastReplyTime;}

	public String getReplyCount() {return replyCount;}

	public String getCategory() {return category;}

	public ConversationStruct setAuthor(String author) {this.author = author; return this;}

	public ConversationStruct setAuthorAvatar(Drawable authorAvatar) {this.authorAvatar = authorAvatar; return this;}

	public ConversationStruct setLastReply(String lastReply) {this.lastReply = lastReply; return this;}

	public ConversationStruct setLastReplyAvatar(Drawable lastReplyAvatar) {this.lastReplyAvatar = lastReplyAvatar; return this;}

	public ConversationStruct setConvTitle(String convTitle) {this.convTitle = convTitle; return this;}

	public ConversationStruct setConvSummary(String convSummary) {this.convSummary = convSummary; return this;}

	public ConversationStruct setLastReplyTime(String lastReplyTime) {this.lastReplyTime = lastReplyTime; return this;}

	public ConversationStruct setReplyCount(String replyCount) {this.replyCount = replyCount; return this;}

	public ConversationStruct setCategory(String category) {this.category = category; return this;}
}
