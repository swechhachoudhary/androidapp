package com.acadplnr;



import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Courses extends ListActivity {
	
		//ARRAY LIST al which lists all courses and prompts user to click to get corresponding details
		//view adapts to show as a menu;
		ArrayList<String> al = new ArrayList<String>();
		
		


	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		// setting content as listview
		int  NO_COURSE_ADDED = 0;
		
		setListAdapter(new ArrayAdapter<String>(Courses.this, android.R.layout.simple_list_item_1, al));
		
        Crs_database_help dinfo = new Crs_database_help(this);
		dinfo.open();
	
		String[] data;
		String alldata ;
		
		if(dinfo.GetAllCourses() == null)
		{
			NO_COURSE_ADDED = 1;
			al.add("noCourse");
		}
		else 
		{
			
			alldata = dinfo.GetAllCourses();
			data = alldata.split("-");
			//splits data into an array of strings containing course_names
			
			for(int j=0;j<data.length;j++)
			{
				al.add(data[j]);
			}
			
		}
		
		dinfo.close();
		
		
		
		
	}
	
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		
		
		
		String lcourses = al.get(0);
		
			try{
				Class Myclass = Class.forName("com.acadplnr." + lcourses);
				Intent myintent = new Intent(Courses.this,Myclass);
				Bundle basket = new Bundle();
				position++;
				String Position = "" + position;
				basket.putString("position", Position);
				myintent.putExtras(basket);
				startActivity(myintent);
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				String error = e.toString();
				Dialog h = new Dialog(this);
				h.setTitle(" :(");
				TextView tv1 = new TextView(this);
				tv1.setText(error);
				h.setContentView(tv1);
				h.show();
			}
		}
		
		
		}

	
	


