package com.geno.chaoli.forum;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.geno.view.ConversationView;
import com.unknown.TextDrawable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;

@SuppressWarnings("HandlerLeak")
public class ConversationListFragment extends Fragment
{
	public String[] conversationTitleList = new String[/*Constants.getConvPerPage(getActivity())*/50];
	public String[] conversationSumList = new String[50];
	public String[] conversationAuthorList = new String[51];
	public Drawable[] conversationAvatarList = new Drawable[51];

	public ViewGroup cont;

	public ConversationView[] conversationViewList = new ConversationView[50];

	public ScrollView s;
	public LinearLayout l;
	private class ConversationListHandler extends Handler
	{
		private final WeakReference<MainActivity> conversationListActivity;
	
		public ConversationListHandler(MainActivity activity)
		{
			conversationListActivity = new WeakReference<>(activity);
		}
	
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			switch (msg.what)
			{
				case 0:
					new Thread(runnable).start();
					break;
				case 1:
					cont.removeAllViews();
					break;
				case 2:
					Log.v(Constants.tagDraw, Constants.statusStart);
					for (int i = 0; i < conversationTitleList.length; i++)
					{
						conversationViewList[i] = new ConversationView(getActivity());
						l.addView(conversationViewList[i], ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
						conversationViewList[i].setConvTitle(conversationTitleList[i]).setConvSum(conversationSumList[i]).setAuthor(conversationAuthorList[i]).setAvatar(conversationAvatarList[i]);
					}
					Log.v(Constants.tagDraw, Constants.statusFinish);
			}
		}
	}
	
	private ConversationListHandler handler = new ConversationListHandler(new MainActivity());

	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			try
			{
				Log.v(Constants.tagNetwork, Constants.statusFinish);
				Document doc = Jsoup.connect("https://chaoli.club").timeout(30000).get();
				Log.v(Constants.tagNetwork, "Finish");
				Elements titles = doc.select("strong.title");
				Elements sums = doc.select("div.excerpt");
				Elements authors = doc.select("span.lastPostMember");
				Elements avatar = doc.select("img.avatar, span.avatar");
				Log.v("count", titles.size() + ", " + sums.size() + ", " + authors.size() + ", " + avatar.size());
				for (int i = 0; i < 50; i++)
				{
					conversationTitleList[i] = titles.get(i).text().trim();
					Log.i(Constants.msgTitle, i + ". " + conversationTitleList[i]);
					conversationSumList[i] = sums.get(i).text().trim();
					Log.i(Constants.msgSum, i + ". " + conversationSumList[i]);
					conversationAuthorList[i] = authors.get(i * 2).text().trim();
					Log.i(Constants.msgAuthor, i + ". " + conversationAuthorList[i]);
					Element r = avatar.get(i * 2);
					String g = r.absUrl("src");
					if (g.equals(""))
					{
						TextDrawable t = new TextDrawable(getActivity());
						t.setTextColor(0xFFB0B0B0);
						Canvas c = new Canvas();
						c.drawColor(0xFFB0B0B0);
						t.draw(c);
						t.setText(r.text().trim());
						conversationAvatarList[i] = t;
						Log.i(Constants.msgAvatar, i + ". " + r.text().trim());
					}
					else
					{
						conversationAvatarList[i] = Methods.getDrawableByUrl(g);
						Log.i(Constants.msgAvatar, i + ". " + g);
					}
				}
				handler.sendEmptyMessage(2);
			}
			catch (Exception e)
			{e.printStackTrace();}
		}
	};

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static ConversationListFragment newInstance(int sectionNumber)
	{
		ConversationListFragment fragment = new ConversationListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ConversationListFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		s = new ScrollView(getActivity());
		l = new LinearLayout(getActivity());
		l.setOrientation(LinearLayout.VERTICAL);
		s.addView(l);
		cont = container;
		container.addView(s);
		handler.sendEmptyMessage(0);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}
}
