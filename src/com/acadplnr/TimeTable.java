package com.acadplnr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TimeTable extends Activity{
	TextView tvMonC,tvMonT,tvTusC,tvTusT,tvWedC,tvWedT,tvThurC,tvThurT,
	tvFriC,tvFriT,tvSatC,tvSatT,tvSunC,tvSunT;
	String courseMon="",courseTus="",courseWed="",courseThur="",courseFri="",courseSat="",courseSun="",
			timeMon="",timeTus="",timeWed="",timeThur="",timeFri="",timeSat="",timeSun="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_table);
		tvMonC = (TextView) findViewById(R.id.tvMonC);
		tvTusC = (TextView) findViewById(R.id.tvTusC);
		tvWedC = (TextView) findViewById(R.id.tvWedC);
		tvThurC = (TextView) findViewById(R.id.tvThurC);
		tvFriC = (TextView) findViewById(R.id.tvFriC);
		tvSatC = (TextView) findViewById(R.id.tvSatC);
		tvSunC = (TextView) findViewById(R.id.tvSunC);
		tvMonT = (TextView) findViewById(R.id.tvMonT);
		tvTusT = (TextView) findViewById(R.id.tvTusT);
		tvWedT = (TextView) findViewById(R.id.tvWedT);
		tvThurT = (TextView) findViewById(R.id.tvThurT);
		tvFriT = (TextView) findViewById(R.id.tvFriT);
		tvSatT = (TextView) findViewById(R.id.tvSatT);
		tvSunT = (TextView) findViewById(R.id.tvSunT);
		
		
		Crs_database_help info = new Crs_database_help(this);
		info.open();
		try{
			int count = info.getCount();
			for(int j=0;j<count;j++)
			{
				String detail = info.GetCourseDetailById(j+1);
				String data[] = detail.split("-");
				String[] lec_days = data[4].split(" ");
				String[] lab_days = data[7].split(" ");
				String[] tut_days = data[10].split(" ");
				
				
				for(int i=0;i<16;i++)
				{
					if(data[i].contentEquals("null")|| data[i]==null)
					{
						if(i==6||i==12||i==9)
							data[i]=" ";
						else
							data[i]="Not Yet Set";
						
					}
				}
				
				
				for(int i=0;i<lec_days.length;i++)
				{
					if(lec_days[i].contentEquals("M")){
						courseMon = courseMon + data[0]+"(lec)" + "\n";
						timeMon = timeMon + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("T")){
						courseTus = courseTus + data[0]+"(lec)" + "\n";
						timeTus = timeTus + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("W")){
						courseWed = courseWed + data[0]+"(lec)" + "\n";
						timeWed = timeWed + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("t")){
						courseThur = courseThur + data[0]+"(lec)" + "\n";
						timeThur = timeThur + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("F")){
						courseFri = courseFri + data[0]+"(lec)" + "\n";
						timeFri = timeFri + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("S")){       // 's' and 's' 
						courseSat = courseSat + data[0]+"(lec)" + "\n";
						timeSat = timeSat + data[5] +" to "+data[6];
					}
					if(lec_days[i].contentEquals("s")){
						courseSun = courseSat + data[0]+"(lec)" + "\n";
						timeSun = timeSat + data[5] +" to "+data[6];
					}
				}
				for(int i=0;i<lab_days.length;i++)
				{
					if(lab_days[i].contentEquals("M")){
						courseMon = courseMon + data[0]+"(lab)" + "\n";
						timeMon = timeMon + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("T")){
						courseTus = courseTus + data[0]+"(lab)" + "\n";
						timeTus = timeTus + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("W")){
						courseWed = courseWed + data[0]+"(lab)" + "\n";
						timeWed = timeWed + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("t")){
						courseThur = courseThur + data[0]+"(lab)" + "\n";
						timeThur = timeThur + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("F")){
						courseFri = courseFri + data[0]+"(lab)" + "\n";
						timeFri = timeFri + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("S")){       // 's' and 's' 
						courseSat = courseSat + data[0]+"(lab)" + "\n";
						timeSat = timeSat + data[8] +" to "+data[9]+ "\n";
					}
					if(lab_days[i].contentEquals("s")){
						courseSun = courseSun + data[0]+"(lab)" + "\n";
						timeSun = timeSun + data[8] +" to "+data[9]+ "\n";
					}
				}
				for(int i=0;i<tut_days.length;i++)
				{
					if(tut_days[i].contentEquals("M")){
						courseMon = courseMon + data[0]+"(tut)" + "\n";
						timeMon = timeMon + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("T")){
						courseTus = courseTus + data[0]+"(tut)" + "\n";
						timeTus = timeTus + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("W")){
						courseWed = courseWed + data[0]+"(tut)" + "\n";
						timeWed = timeWed + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("t")){
						courseThur = courseThur + data[0]+"(tut)" + "\n";
						timeThur = timeThur + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("F")){
						courseFri = courseFri + data[0]+"(tut)" + "\n";
						timeFri = timeFri + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("S")){       // 's' and 's' 
						courseSat = courseSat + data[0]+"(tut)" + "\n";
						timeSat = timeSat + data[11] +" to "+data[12]+ "\n";
					}
					if(tut_days[i].contentEquals("s")){
						courseSun = courseSun + data[0]+"(tut)" + "\n";
						timeSun = timeSun + data[11] +" to "+data[12]+ "\n";
					}
					
					
				}
				
			}
		}catch (Exception e)
		{
			
		}
		
	    
		
	    tvMonC.setText(courseMon);
	    tvMonT.setText(timeMon);
	    tvTusC.setText(courseTus);
	    tvTusT.setText(timeTus);
	    tvWedC.setText(courseWed);
	    tvWedT.setText(timeWed);
	    tvThurC.setText(courseThur);
	    tvThurT.setText(timeThur);
	    tvFriC.setText(courseFri);
	    tvFriT.setText(timeFri);
	    tvSatC.setText(courseSat);
	    tvSatT.setText(timeSat);
	    tvSunC.setText(courseSun);
	    tvSunT.setText(timeSun);
	    
	   
	}
}