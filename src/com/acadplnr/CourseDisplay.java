package com.acadplnr;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CourseDisplay extends Activity implements OnClickListener {

	Button beditcourse;
	TextView courseNm, courseLnk, instuctname,lec_st,lec_et,tut_st,tut_et,lab_st,lab_et,
			lec_v,tut_v,lab_v,lec_d,lab_d,tut_d,off_hr_add;
	public String ClickedPosition,CourseLink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coursedisplay);
		beditcourse = (Button) findViewById(R.id.editbutton);
		beditcourse.setOnClickListener(this);
		
		courseNm = (TextView) findViewById(R.id.coursename);
		courseLnk = (TextView) findViewById(R.id.add_weblink);
		instuctname = (TextView) findViewById(R.id.add_instructor_name);
		
		lec_st = (TextView) findViewById(R.id.add_lecture_starttime);
		tut_st = (TextView) findViewById(R.id.add_tutorial_starttime);
		lab_st = (TextView) findViewById(R.id.add_lab_starttime);
		
	//	lec_et = (TextView) findViewById(R.id.add_lecture_endtime);
	//	tut_et = (TextView) findViewById(R.id.add_tutorial_endtime);
	//	lab_et = (TextView) findViewById(R.id.add_lab_endtime);
		
		lec_v = (TextView) findViewById(R.id.add_lec_venue);
		tut_v = (TextView) findViewById(R.id.add_tut_venue);
		lab_v = (TextView) findViewById(R.id.add_lab_venue);
		
		lec_d = (TextView) findViewById(R.id.add_lecture_days);
		lab_d = (TextView) findViewById(R.id.add_lab_days);
		tut_d = (TextView) findViewById(R.id.add_tutorial_days);
		off_hr_add = (TextView) findViewById(R.id.add_office_hr_add);
		
		courseLnk.setClickable(true);
		courseLnk.setOnClickListener(this);
		
		Bundle gotBasket = getIntent().getExtras();
		ClickedPosition = gotBasket.getString("position");
		
		beditcourse.setOnClickListener(this);
		
		long l = Long.parseLong(ClickedPosition);
		Crs_database_help info = new Crs_database_help(this);
		try{
		info.open();
		String all_courses = info.GetCourseDetailById(l);
		String[] data = all_courses.split("-");
		for(int i=0;i<16;i++)
		{
			if(data[i].contentEquals("null")|| data[i]==null)
			{
				if(i==6||i==12||i==9)
					data[i]=" ";
				else
					data[i]="Not Yet Set";
				
			}
			else
			{
				if(i==5||i==8||i==11)
					data[i] = data[i] + " to " + data[i+1];
			}
		}
		CourseLink = data[1];
		courseNm.setText(data[0]);
		courseLnk.setText(data[1]);
		instuctname.setText(data[2]);
		off_hr_add.setText(data[3]);
		lec_d.setText(data[4]);
		lec_st.setText(data[5]);
	//	lec_et.setText(data[6]);
		lab_d.setText(data[7]);
		lab_st.setText(data[8]);
	//	lab_et.setText(data[9]);
		tut_d.setText(data[10]);
		tut_st.setText(data[11]);
	//  tut_et.setText(data[12]);
		lec_v.setText(data[13]);
		lab_v.setText(data[14]);
		tut_v.setText(data[15]);
		}catch (Exception e)
		{
			String error = e.toString();
			Dialog h = new Dialog(this);
			h.setTitle(" :(");
			TextView tv1 = new TextView(this);
			tv1.setText(error);
			h.setContentView(tv1);
			h.show();
		}
		info.close();
		
		
		
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.editbutton:
			Bundle v = new Bundle();
			String value =  ClickedPosition ;
        	v.putString("clkpos", value);
			Intent w = new Intent("com.acadplnr.Update");
	        w.putExtras(v);
	        
	        try{
	   		startActivity(w);
	        }
	        catch(Exception e){
		
				String error = e.toString();
				Dialog h = new Dialog(this);
				h.setTitle(" :(");
				TextView tv1 = new TextView(this);
				tv1.setText(error);
				h.setContentView(tv1);
				h.show();
			}
	        break;
		case  R.id.add_weblink :
			String url = CourseLink;
			if (!url.startsWith("http://") && !url.startsWith("https://"))
				   url = "http://" + url;
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
	        finish();
		}
		
	}
	

	
}
