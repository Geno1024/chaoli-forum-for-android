package com.geno.chaoli.forum;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings("HandlerLeak")
public class LoginActivity extends Activity
{
	protected EditText username, password;
	protected Button submit;

	private class LoginHandler extends Handler
	{
		private final WeakReference<LoginActivity> loginActivity;

		public LoginHandler(LoginActivity activity)
		{
			loginActivity = new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			LoginActivity activity = loginActivity.get();
			if (activity != null)
			{
				switch (msg.what)
				{
					case 0:
						new Thread(runnable).start();
						break;
					case 1:
						Toast.makeText(LoginActivity.this, msg.getData().getString("value"), Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private LoginHandler handler = new LoginHandler(this);

	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			try
			{
				Document doc = Jsoup.connect("https://chaoli.club").get();
				Elements el = doc.select("strong.title");;
				for (Element e : el)
				Log.i("Elements", e.text().trim());
			}
			catch (Exception e)
			{e.printStackTrace();}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		setTitle(R.string.action_login);
		username = (EditText) findViewById(R.id.activity_login_username);
		password = (EditText) findViewById(R.id.activity_login_password);
		submit = (Button) findViewById(R.id.activity_login_submit);
		View.OnClickListener login = new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				handler.sendEmptyMessage(0);
			}
		};
		submit.setOnClickListener(login);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		//handler.removeCallbacksAndMessages(null);
	}
}
