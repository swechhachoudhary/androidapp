package com.acadplnr;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class course1 extends Activity {
	
	String ClickedPosition;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_template);	
		
		Bundle gotBasket = getIntent().getExtras();
		ClickedPosition = gotBasket.getString("position");
		
		
		//opening database to write to course content
		Crs_database_help info = new Crs_database_help(this);
		
		info.open();
		String RowId = ClickedPosition;
		long l = Long.parseLong(RowId);
		String c = info.GetCourseDetailById(l);
		
		
		

		TextView ASDFG = (TextView) findViewById(R.id.c_name);
		TextView pqrs = (TextView) findViewById(R.id.w_link);
		TextView turv = (TextView) findViewById(R.id.in_name);
		
		
		if (c!= null)
		{
			
		String[] data;
		String delimiter = "-";
		data = c.split(delimiter);
		
		ASDFG.setText(data[0]);
		
		pqrs.setText(data[1]);
		
		turv.setText(data[2]);
		
		}
		else 
		{
			ASDFG.setText("NotYetSet");
			
			pqrs.setText("NotYetSet");
			
			turv.setText("NotYetSet");
		}
		
		info.close();
		ASDFG.setLongClickable(true);
		ASDFG.setOnLongClickListener(new View.OnLongClickListener() {
		
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent i = new Intent("com.acadplnr.CourseDatabase");
				startActivity(i);
				
				return false;
				
				

			}
		});
		
		
		
	}
		
	

}
