package com.geno.chaoli.forum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class LoginActivity extends Activity
{
	protected EditText username, password;
	protected Button submit;
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
					Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
		submit.setOnClickListener(login);
	}
}
