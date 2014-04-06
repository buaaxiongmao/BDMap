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
		// ��setContentView֮ǰ��ʼ��BMapManager����
		manager = new BMapManager(getApplication());
		//����key����ʼ����ͼ������
		manager.init("TRin0iMUYSqrMMZ9FhmNPZib", null);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.bmapsView);
		mapView.setBuiltInZoomControls(true);
		// ��ȡmapview�Ŀ���Ȩ
		MapController controller = mapView.getController();
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		//�������ĵ�
		controller.setCenter(point);
		//��֪������ʲô
		controller.setZoom(12);
		if (getIntent().getExtras() != null) {
			String command = getIntent().getExtras().getString("command");
			if ("1".equals(command)) {
				Toast.makeText(this, "trafficMap", 0).show();
				//��ʾʵʱ��ͨ��Ϣ
				mapView.setTraffic(true);
			}else if("2".equals(command)) {
				//�������ǵ�ͼ
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
