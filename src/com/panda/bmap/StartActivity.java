package com.panda.bmap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity implements OnClickListener {
	private Button bt_myMap , bt_trafficMap , bt_satellite;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		bt_myMap = (Button) findViewById(R.id.bt_myMap);
		bt_trafficMap = (Button) findViewById(R.id.bt_trafficMap);
		bt_satellite = (Button) findViewById(R.id.bt_satellite);
		bt_myMap.setOnClickListener(this);
		bt_trafficMap.setOnClickListener(this);
		bt_satellite.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bt_myMap:
			intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			
			break;
		case R.id.bt_trafficMap:
			intent = new Intent(this,MainActivity.class);
			intent.putExtra("command", "1");
			startActivity(intent);
			break;
		case R.id.bt_satellite:
			intent = new Intent(this,MainActivity.class);
			intent.putExtra("command", "2");
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
}
