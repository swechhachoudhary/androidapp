package com.acadplnr;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CourseDatabase extends Activity implements OnClickListener {
	
	Button C_update;
	EditText c_name,ins_name,wlink;
	TextView c_name_text,ins_name_text,weblink_text;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_edit);
		
		C_update = (Button) findViewById(R.id.bUpdate);
		c_name =(EditText)findViewById(R.id.courseName);
		ins_name =(EditText)findViewById(R.id.instructor);
		wlink =(EditText)findViewById(R.id.weblink);
		ins_name_text =(TextView)findViewById(R.id.in_name);
		c_name_text =(TextView)findViewById(R.id.c_name);
		weblink_text =(TextView)findViewById(R.id.w_link);
		C_update.setOnClickListener(this);
		
	}


	



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	switch(v.getId()){
		
		case R.id.bUpdate:
			boolean didItWORK = true;
		
			try {
					String cname = c_name.getText().toString();
					String insname = ins_name.getText().toString();
					String welink = wlink.getText().toString();
					Crs_database_help entry = new Crs_database_help(CourseDatabase.this);
					entry.open();
					//entry.createEntry(cname,insname,welink);
					entry.KeyRowIdUpdate();
					entry.close();
					}
				catch(Exception e){
					didItWORK =false;
					String error = e.toString();
					Dialog h = new Dialog(this);
					h.setTitle(" :(");
					TextView tv1 = new TextView(this);
					tv1.setText(error);
					h.setContentView(tv1);
					h.show();
				}
				finally{
					if(didItWORK){    
						Dialog d = new Dialog(this);
						d.setTitle("YEAH !");
						TextView tv = new TextView(this);
						tv.setText("Success!");
						d.setContentView(tv);
						d.show();
						finish();
						Intent i = new Intent(CourseDatabase.this,MainActivity.class);
						startActivity(i);
					}
				}
		break;
		
		}
		
	}

}
