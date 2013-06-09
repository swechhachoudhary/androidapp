package com.acadplnr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Notification extends Activity{
	TextView HttpStuff;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		HttpStuff = (TextView) findViewById(R.id.tvHttp);
		GetMethodEx test = new GetMethodEx();
		String returned;
		try{
			returned = test.getInternetData();
			HttpStuff.setText(returned);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}  
}
