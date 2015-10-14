package com.geno.chaoli.forum;

import android.app.Activity;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;

@SuppressWarnings("HandlerLeak")
public class ConversationListFragment extends Fragment
{
	public String[] conversationTitleList = new String[/*SettingsConstants.getConvPerPage(getActivity())*/50];
	public String[] conversationSumList = new String[50];
	public String[] conversationAuthorList = new String[51];
	public Drawable[] conversationAvatarList = new Drawable[50];

	public ConversationView[] conversationViewList = new ConversationView[50];
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
					Toast.makeText(getActivity(), msg.getData().getString("value"), Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Log.v("Draw", "Start");
					for (int i = 0; i < conversationTitleList.length; i++)
					{
						conversationViewList[i].setConvTitle(conversationTitleList[i]).setConvSum(conversationSumList[i]).setAuthor(conversationAuthorList[i]).setAvatar(conversationAvatarList[i]);
					}
					Log.v("Draw", "End");
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
				Log.v("Network", "Start");
				Document doc = Jsoup.connect("https://chaoli.club").timeout(30000).get();
				Log.v("Network", "Finish");
				Elements titles = doc.select("strong.title");
				Elements sums = doc.select("div.excerpt");
				Elements authors = doc.select("span.lastPostMember");
				Elements avatar = doc.select("img.avatar");
				int i = 0;
				for (Element e : titles)
				{
					conversationTitleList[i] = e.text().trim();
					i++;
				}
				i = 0;
				for (Element e : sums)
				{
					conversationSumList[i] = e.text().trim();
					i++;
				}
				i = 0;
				for (Element e : authors)
				{
					if (i % 2 == 0)conversationAuthorList[i / 2] = e.text().trim();
					i++;
				}
				i = 0;
				for (Element e : avatar)
				{
					if (i % 2 == 0)conversationAvatarList[i / 2] = Methods.getDrawableByUrl(e.absUrl("src"));
					i++;
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
		ScrollView s = new ScrollView(getActivity());
		LinearLayout l = new LinearLayout(getActivity());
		l.setOrientation(LinearLayout.VERTICAL);
		s.addView(l);
		for (int i = 0; i < 50; i++)
		{
			conversationViewList[i] = new ConversationView(getActivity());
			l.addView(conversationViewList[i], ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
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
