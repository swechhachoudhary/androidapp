package com.acadplnr;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class Timeedit extends Activity implements OnTimeChangedListener, OnClickListener{
	
	
	long hr, min, hr2, min2;
	int hri, mini, hr2i, min2i, i;
	TimePicker timepick, timepick2;
	CheckBox cbmon, cbtue, cbwed, cbthu, cbfri, cbsat, cbsun;
	EditText venue;
	Button bdone,cancel;
	String days,daysi, tim,timi, tim2,tim2i, v, vi,timef[], timef2[], daysf[];
	Bundle gotbasket;
	Time t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timedate);
		gotbasket = getIntent().getExtras();
		days = gotbasket.getString("day");
		tim = gotbasket.getString("start");
		tim2 =  gotbasket.getString("end");
		v =  gotbasket.getString("ven");
		
		
		daysf=days.split(" ");
		
		if(tim.contentEquals("NotYetSet")){
		t = new Time();
		t.setToNow();
		hri = t.hour;
		mini = t.minute;
		hr2i = hri + ((mini + 50) / 60);
		min2i = (mini + 50) % 60;
		}
		else{
			timef=tim.split(":");
			timef2=tim2.split(":");
			hr = Long.parseLong(timef[0]);
			min = Long.parseLong(timef[1]);
			hr2 = Long.parseLong(timef2[0]);
			min2 = Long.parseLong(timef2[1]);
			hri=(int)hr;
			hr2i=(int)hr2;
			mini=(int)min;
			min2i=(int)min2;
		}
		
		
		timepick = (TimePicker) findViewById(R.id.time);
		timepick2 = (TimePicker) findViewById(R.id.time2);
		timepick.setIs24HourView(true);
		timepick2.setIs24HourView(true);
		timepick.setCurrentHour(hri);
		timepick.setCurrentMinute(mini);
		timepick2.setCurrentHour(hr2i);
		timepick2.setCurrentMinute(min2i);
		
		cbmon = (CheckBox) findViewById(R.id.mon);
		cbtue = (CheckBox) findViewById(R.id.tue);
		cbwed = (CheckBox) findViewById(R.id.wed);
		cbthu = (CheckBox) findViewById(R.id.thu);
		cbfri = (CheckBox) findViewById(R.id.fri);
		cbsat = (CheckBox) findViewById(R.id.sat);
		cbsun = (CheckBox) findViewById(R.id.sun);
		venue = (EditText) findViewById(R.id.venue);
		bdone = (Button) findViewById(R.id.done);
		cancel = (Button) findViewById(R.id.cancel);
		timepick.setOnTimeChangedListener(this);
		timepick2.setOnTimeChangedListener(this);
		bdone.setOnClickListener(this);
		cancel.setOnClickListener(this);

		for(i=0;i<daysf.length;i++)
		{
			if(daysf[i].contentEquals("M")){
				cbmon.setChecked(true);
			}
			if(daysf[i].contentEquals("T")){
				cbtue.setChecked(true);
			}
			if(daysf[i].contentEquals("W")){
				cbwed.setChecked(true);
			}
			if(daysf[i].contentEquals("t")){
				cbthu.setChecked(true);
			}
			if(daysf[i].contentEquals("F")){
				cbfri.setChecked(true);
			}
			if(daysf[i].contentEquals("S")){
				cbsat.setChecked(true);
			}
			if(daysf[i].contentEquals("s")){
				cbsun.setChecked(true);
			}
			
			
		}
		venue.setText(v);
		
	}

	@Override
	public void onClick(View arg0) {
		
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.done:
			daysi="";
			timi="";
			tim2i="";
			if (cbmon.isChecked())
				daysi = daysi + "M ";
			if (cbtue.isChecked())
				daysi = daysi + "T ";
			if (cbwed.isChecked())
				daysi = daysi + "W ";
			if (cbthu.isChecked())
				daysi = daysi + "t ";
			if (cbfri.isChecked())
				daysi = daysi + "F ";
			if (cbsat.isChecked())
				daysi = daysi + "S ";
			if (cbsun.isChecked())
				daysi = daysi + "s ";
			if (hr2i< hri || ((hri == hr2i) && (min2i <= mini))) {
				Toast t = Toast.makeText(Timeedit.this,"ending time should be greater than beginning time, enter time again",
								Toast.LENGTH_LONG);
				t.show();
				break;
			}
			timi = timi + hri + ":" + mini;
			tim2i = tim2i + hr2i + ":" + min2i;
			Bundle basket = new Bundle();
			basket.putString("start_time", timi);
			basket.putString("end_time", tim2i);
			basket.putString("day", daysi);
			basket.putString("ven", venue.getText().toString());
			Intent i = new Intent("");
			i.putExtras(basket);
			setResult(RESULT_OK, i);
			finish();
			break;
		case R.id.cancel:
			/*try{
				Bundle basket1 = new Bundle();
				basket1.putString("start_time", tim);
				basket1.putString("end_time", tim2);
				basket1.putString("day", days);
				basket1.putString("ven", v);
				Intent i1 = new Intent("");
				i1.putExtras(basket1);
				setResult(RESULT_OK, i1);*/
				finish();
				/*break;
			
			}catch(Exception e){
				
				String error = e.toString();
				Dialog h = new Dialog(this);
				h.setTitle(" :(");
				TextView tv1 = new TextView(this);
				tv1.setText(error);
				h.setContentView(tv1);
				h.show();
			}*/
		}

	}
	

	@Override
	public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.time:
			hri = timepick.getCurrentHour();
			mini = timepick.getCurrentMinute();
			hr2i = hri + ((mini + 50) / 60);
			min2i = (mini + 50) % 60;
			timepick2.setCurrentHour(hr2i);
			timepick2.setCurrentMinute(min2i);
			break;
		case R.id.time2:
			hr2i = timepick2.getCurrentHour();;
			min2i = timepick2.getCurrentMinute();
			hri = timepick.getCurrentHour();
			mini = timepick.getCurrentMinute();
			
		}
	}
}

