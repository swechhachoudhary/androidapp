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

public class Update extends Activity implements OnClickListener {

	Button bdone, blec, btut, blab;
	EditText c_name, ins_name, wlink, off;
	String ClickedPosition, lecd, lecst, lecet, lecv, tutd, tutst, tutet, tutv,
			labd, labst, labet, labv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editadd);

		bdone = (Button) findViewById(R.id.bdoneeditadd);
		blec = (Button) findViewById(R.id.lectureschedule);
		btut = (Button) findViewById(R.id.tutorialschedule);
		blab = (Button) findViewById(R.id.labschedule);
		c_name = (EditText) findViewById(R.id.coursename);
		ins_name = (EditText) findViewById(R.id.instructorname);
		wlink = (EditText) findViewById(R.id.courselink);
		off = (EditText) findViewById(R.id.officehour_and_address);

		bdone.setOnClickListener(this);
		blec.setOnClickListener(this);
		btut.setOnClickListener(this);
		blab.setOnClickListener(this);
		Bundle gotBasket = getIntent().getExtras();
		ClickedPosition = gotBasket.getString("clkpos");

		Crs_database_help info = new Crs_database_help(this);

		info.open();
		String RowId = ClickedPosition;
		long l = Long.parseLong(RowId);
		String c = info.GetCourseDetailById(l);

		if (c != null) {

			String[] data;
			String delimiter = "-";
			data = c.split(delimiter);

			c_name.setText(data[0]);

			if (data[1].contentEquals("null"))
				wlink.setText("NotYetSet");
			else
				wlink.setText(data[1]);

			if (data[2].contentEquals("null"))
				ins_name.setText("NotYetSet");
			else
				ins_name.setText(data[2]);
			
			if (data[3].contentEquals("null"))
				off.setText("NotYetSet");
			else
				off.setText(data[3]);
			
			if (data[4].contentEquals("null"))
				lecd = "NotYetSet";
			else
				lecd = data[4];
			
			if (data[5].contentEquals("null"))
				lecst = "NotYetSet";
			else
				lecst = data[5];
			
			if (data[6].contentEquals("null"))
				lecet = "NotYetSet";
			else
				lecet = data[6];
			
			if (data[7].contentEquals("null"))
				labd = "NotYetSet";
			else
				labd = data[7];
			
			if (data[8].contentEquals("null"))
				labst = "NotYetSet";
			else
				labst = data[8];
			
			if (data[9].contentEquals("null"))
				labet = "NotYetSet";
			else
				labet = data[9];
			
			if (data[10].contentEquals("null"))
				tutd = "NotYetSet";
			else
				tutd = data[10];
			
			if (data[11].contentEquals("null"))
				tutst = "NotYetSet";
			else
				tutst = data[11];
			
			if (data[12].contentEquals("null"))
				tutet = "NotYetSet";
			else
				tutet = data[12];
			
			if (data[13].contentEquals("null"))
				lecv = "NotYetSet";
			else
				lecv = data[13];
			
			if (data[14].contentEquals("null"))
				labv = "NotYetSet";
			else
				labv = data[14];
			
			if (data[15].contentEquals("null"))
				tutv = "NotYetSet";
			else
				tutv = data[15];

		} 
		info.close();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.bdoneeditadd:
			boolean didItWORK = true;

			try {
				String cname = c_name.getText().toString();
				String insname = ins_name.getText().toString();
				String welink = wlink.getText().toString();
				String office = off.getText().toString();
				Crs_database_help entry = new Crs_database_help(Update.this);
				entry.open();
				long l = Long.parseLong(ClickedPosition);
				entry.UpdateCourse(l, cname, insname, welink, office, lecd,
						lecst, lecet, tutd, tutst, tutet, labd, labst, labet,
						lecv, labv, tutv);
				entry.close();
			} catch (Exception e) {
				didItWORK = false;
				String error = e.toString();
				Dialog h = new Dialog(this);
				h.setTitle(" :(");
				TextView tv1 = new TextView(this);
				tv1.setText(error);
				h.setContentView(tv1);
				h.show();
			} finally {
				if (didItWORK) {
					Dialog d = new Dialog(this);
					d.setTitle("YEAH !");
					TextView tv = new TextView(this);
					tv.setText("Success!");
					d.setContentView(tv);
					d.show();
					finish();
					Intent i = new Intent(Update.this, MainActivity.class);
					startActivity(i);
				}
			}
			break;

		case R.id.labschedule:
			Bundle lab = new Bundle();
			lab.putString("start", labst);
			lab.putString("end", labet);
			lab.putString("ven", labv);
			lab.putString("day", labd);
			try{
			Intent i1 = new Intent("com.acadplnr.Timeedit");
			i1.putExtras(lab);
			startActivityForResult(i1, 1);
			} catch (Exception e) {
				String error = e.toString();
				Dialog h = new Dialog(this);
				h.setTitle(" :(");
				TextView tv1 = new TextView(this);
				tv1.setText(error);
				h.setContentView(tv1);
				h.show();
			}
			break;
		case R.id.lectureschedule:
			Bundle lecture = new Bundle();
			lecture.putString("start", lecst);
			lecture.putString("end", lecet);
			lecture.putString("ven", lecv);
			lecture.putString("day", lecd);
			Intent i2 = new Intent("com.acadplnr.Timeedit");
			i2.putExtras(lecture);
			startActivityForResult(i2, 2);
			break;
		case R.id.tutorialschedule:
			Bundle tutorial = new Bundle();
			tutorial.putString("start", tutst);
			tutorial.putString("end", tutet);
			tutorial.putString("ven", tutv);
			tutorial.putString("day", tutd);
			Intent i3 = new Intent("com.acadplnr.Timeedit");
			i3.putExtras(tutorial);
			startActivityForResult(i3, 3);
			break;

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			Bundle received_basket = data.getExtras();
			if (requestCode == 1) {
				labst = "" + received_basket.getString("start_time");
				labet = "" + received_basket.getString("end_time");
				labv = "" + received_basket.getString("ven");
				labd = "" + received_basket.getString("day");

			}
			if (requestCode == 2) {
				lecst = "" + received_basket.getString("start_time");
				lecet = "" + received_basket.getString("end_time");
				lecv = "" + received_basket.getString("ven");
				lecd = "" + received_basket.getString("day");
			}
			if (requestCode == 3) {
				tutst = "" + received_basket.getString("start_time");
				tutet = "" + received_basket.getString("end_time");
				tutv = "" + received_basket.getString("ven");
				tutd = "" + received_basket.getString("day");
			}
		}

	}
}
