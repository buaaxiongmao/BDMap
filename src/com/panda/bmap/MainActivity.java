package com.panda.bmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MainActivity extends Activity {

	private BMapManager manager = null;
	private MapView mapView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在setContentView之前初始化BMapManager对象
		manager = new BMapManager(getApplication());
		//设置key，初始化地图管理器
		manager.init("TRin0iMUYSqrMMZ9FhmNPZib", null);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.bmapsView);
		mapView.setBuiltInZoomControls(true);
		// 获取mapview的控制权
		MapController controller = mapView.getController();
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		//设置中心点
		controller.setCenter(point);
		//不知道这是什么
		controller.setZoom(12);
		if (getIntent().getExtras() != null) {
			String command = getIntent().getExtras().getString("command");
			if ("1".equals(command)) {
				Toast.makeText(this, "trafficMap", 0).show();
				//显示实时交通信息
				mapView.setTraffic(true);
			}else if("2".equals(command)) {
				//设置卫星地图
				mapView.setSatellite(true);
			}
		}
	}

	@Override
	protected void onDestroy() {
		mapView.destroy();
		if (manager != null) {
			manager.destroy();
			manager = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		if (manager != null) {
			manager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();
		if (manager != null) {
			manager.start();
		}
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
