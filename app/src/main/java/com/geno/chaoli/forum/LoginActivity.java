package com.geno.chaoli.forum;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class LoginActivity extends Activity
{
	protected EditText username, password;
	protected Button submit;

	static Context context = null;

	static class loginHandler extends Handler
	{
		// WeakReference to the outer class's instance.
		private WeakReference<LoginActivity> mOuter;
		public loginHandler(LoginActivity activity)
		{
			mOuter = new WeakReference<LoginActivity>(activity);
		}

		public void handleMessage(Message msg)
		{
			LoginActivity outer = mOuter.get();
			if (outer != null)
			{
				// Do something with outer as your wish.
				switch (msg.what)
				{
					case 0:
						Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private loginHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		setTitle(R.string.action_login);
		handler = new loginHandler(this);
		context = getApplicationContext();
		username = (EditText) findViewById(R.id.activity_login_username);
		password = (EditText) findViewById(R.id.activity_login_password);
		submit = (Button) findViewById(R.id.activity_login_submit);
		View.OnClickListener login = new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Thread t = new Thread
				(new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								URL url = new URL("https://chaoli.club");
								URLConnection c = url.openConnection();
								c.setConnectTimeout(10000);
								c.setReadTimeout(10000);
								InputStream i = c.getInputStream();
								DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
								DocumentBuilder builder = factory.newDocumentBuilder();
								Document doc = builder.parse(i);
								String test = doc.getElementById("conversations").getElementsByTagName("ul").item(0).getChildNodes().item(0).getChildNodes().item(1).getNodeValue();
								Message m = new Message();
								m.what = 0;
								m.obj = test;
								handler.sendMessage(m);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				);
				t.start();
			}
		};
		submit.setOnClickListener(login);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
	}
}
