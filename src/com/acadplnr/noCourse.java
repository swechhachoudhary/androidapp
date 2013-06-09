package com.acadplnr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class noCourse extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView v = new TextView(this);
		v.setText("No Courses set yet");
		
		
		setContentView(v);
	}

	
}
