package cn.tedu.baidumapdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class MainActivity extends Activity {  

	private View headerView;
	private View menu;
	private MapView mMapView;

	private LocationClient client; // ��λ���ͻ��ˡ�
	private MyLocationListener listener; // ��λ������
	private BaiduMap baiduMap; // �ٶȵ�ͼ

	private LatLng centerPoint;

	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);   
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
		//ע��÷���Ҫ��setContentView����֮ǰʵ��  
		SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.activity_main);  
		//��ȡ��ͼ�ؼ�����  
		mMapView = (MapView) findViewById(R.id.bmapView);  

		initHeaderView();
		initBaiduMap();
	}  

	private void initBaiduMap() {
		baiduMap = mMapView.getMap(); // ��ֵ
		// TODO
		// �ı�һ�°ٶȵ�ͼ�ĳ�ʼ������
		baiduMap.setMaxAndMinZoomLevel(20, 17);
		// Ϊ��ͼ�ϵĸ�����(marker)��ӵ��������
		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {

				// ��ñ������marker����Ϣ��������һ��InfoWindow
				Bundle bundle = marker.getExtraInfo();

				if (bundle != null) {
					String name = bundle.getString("name");
					String address = bundle.getString("address");
					String number = bundle.getString("number");

					// ������ʾ��Щ���ݵ�InfoWindow
					View view = getLayoutInflater().inflate(R.layout.poi_infowindow, null);
					TextView tvName = (TextView) view.findViewById(R.id.tv_poi_name);
					tvName.setText(name);
					TextView tvAddress = (TextView) view.findViewById(R.id.tv_poi_address);
					tvAddress.setText(address);
					TextView tvNumber = (TextView) view.findViewById(R.id.tv_poi_number);

					// �̼��п���δ���绰������number�п���Ϊ��
					if (TextUtils.isEmpty(number)) {
						// "", null isEmpty��������true
						tvNumber.setText("�绰δ֪");
					} else {
						tvNumber.setText(number);
					}

					InfoWindow infoWindow = new InfoWindow(view, marker.getPosition(), -120);
					baiduMap.hideInfoWindow(); // �����ڵ�ͼ����ʾ��InfoWindow������
					baiduMap.showInfoWindow(infoWindow); // ���´�������ʾ����
				}
				return true;
			}
		});
	}

	private void initHeaderView() {
		headerView = findViewById(R.id.headerview);
		ImageView ivLeft = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		// ����ivLeftͼƬ��ɫ�ӻ�ɫ ---> ��ɫ
		ivLeft.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		menu = findViewById(R.id.menuLayout);

		ivLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (menu.getVisibility() != View.VISIBLE) {
					menu.setVisibility(View.VISIBLE);
				} else {
					menu.setVisibility(View.GONE);
				}
			}
		});
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.tv_menu_my_position:
			getMyLocation();
			break;
		case R.id.tv_menu_location1:
			getAddress(((TextView)v).getText().toString());
			break;
		case R.id.tv_menu_location2:
			getAddress(((TextView)v).getText().toString());
			break;
		case R.id.tv_menu_search_nearby:
			getNearby();
			break;
		case R.id.tv_menu_screenshot:
			getScreenshot();
			break;
		}
		menu.setVisibility(View.INVISIBLE);
	}

	/**
	 * ��λʹ���ߵ�λ��
	 */
	private void getMyLocation() {
		client = new LocationClient(this);
		listener = new MyLocationListener();

		// ��location���к����Ķ�λ����
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
		option.setCoorType("bd09ll");//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ
		int span=1000;
		option.setScanSpan(span);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
		option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
		option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
		option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
		option.setIsNeedLocationDescribe(true);//��ѡ��Ĭ��false�������Ƿ���Ҫλ�����廯�����������BDLocation.getLocationDescribe��õ�����������ڡ��ڱ����찲�Ÿ�����
		option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI�����������BDLocation.getPoiList��õ�
		option.setIgnoreKillProcess(false);//��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��  
		option.SetIgnoreCacheException(false);//��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�
		option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ
		client.setLocOption(option);

		client.registerLocationListener(listener);
		client.start();
	}

	/**
	 * ����һ����ַ���ҵ��õ�ַ��Ӧ�ľ�γ��
	 * �ٽ��þ�γ�ȱ�ʶ�ڵ�ͼ��
	 */
	private void getAddress(String address) {
		// ר�Ž��е���λ�ò�ѯ
		// ͨ����ַ --> ��γ�ȣ�����λ����Ϣ��ѯ
		// ͨ����γ�� --> ��ַ���������λ����Ϣ��ѯ
		GeoCoder geoCoder = GeoCoder.newInstance();
		// geoCoderָ��һ��������
		geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
				// TODO ͨ����γ�Ȳ��ַ�ɹ�

			}

			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				// ͨ����ַ�龭γ�ȳɹ�
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(MainActivity.this, "���ص�ַδ�ҵ�", Toast.LENGTH_LONG).show();
				} else {

					centerPoint = result.getLocation();

					// �������������
					MarkerOptions overlay = new MarkerOptions();
					// ���ø�������������������
					// �������λ��(LatLnt���͵Ķ������溬�о��Ⱥ�γ����Ϣ)
					overlay.position(result.getLocation());
					// �������ͼ��
					overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
					// ��Ӹ�����(overlay)
					baiduMap.addOverlay(overlay);

					// ����msu����msu�к���һ��λ�õ���Ϣ(��latlng���͵Ķ���)
					MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(result.getLocation());
					// �ƶ���Ļ���ĵ㣬�ƶ�����λ������msu��������
					baiduMap.animateMapStatus(msu);
				}
			}
		});
		// geoCoderָ��Ҫ��ѯ������
		GeoCodeOption opts = new GeoCodeOption();
		opts.city(address.substring(0, 2));
		opts.address(address);
		// �����ѯ
		geoCoder.geocode(opts);
	}

	/**
	 * ����������Ȥ��
	 */
	private void getNearby() {
		// POI:point of interest ��Ȥ�㣨����Ȥ�ĵ㣩
		// 1�����ĵ㣬2��������Χ�������뾶����3�������ؼ��֣�"��ʳ", "����", "����"��
		// PoiSearch��ר�Ž�����Ȥ����������
		PoiSearch poiSearch = PoiSearch.newInstance();
		// poiSearchָ��һ������������������Ϻ�BaiduSDK��ص��ü������еķ������������������Ϊ��������
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

			@Override
			public void onGetPoiResult(PoiResult result) {
				// ʹ���������
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(MainActivity.this, "û���ҵ���������������Χ", Toast.LENGTH_LONG).show();
				} else {
					List<PoiInfo> pois = result.getAllPoi();
					// ����pois����ÿһ����Ȥ�㶼��ӵ���ͼ��
					for (PoiInfo poiInfo : pois) {

						MarkerOptions overlay = new MarkerOptions();
						overlay.position(poiInfo.location);
						overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka));
						Marker marker = (Marker) baiduMap.addOverlay(overlay);
						// ���������������Ϣ(�̼ҵ����ƣ���ַ���绰...)
						Bundle bundle = new Bundle();
						bundle.putString("name", poiInfo.name);
						bundle.putString("address", poiInfo.address);
						bundle.putString("number", poiInfo.phoneNum);
						marker.setExtraInfo(bundle);
					}
				}
			}

			@Override
			public void onGetPoiDetailResult(PoiDetailResult arg0) {
				// TODO
			}
		});

		// �趨��������(���ĵ㣬������Χ���ؼ���)
		PoiNearbySearchOption opts = new PoiNearbySearchOption();
		opts.keyword("��ʳ"); // �ؼ��� 
		opts.radius(3000); // �����뾶
		opts.location(centerPoint); // ���ĵ�
		// ��������
		poiSearch.searchNearby(opts);
	}

	/**
	 * ��ͼ��ͼ
	 */
	private void getScreenshot() {
		baiduMap.snapshot(new SnapshotReadyCallback() {
			
			@Override
			public void onSnapshotReady(Bitmap bitmap) {
				// ���ڴ��еĵ�ͼ��ͼ�����浽SD��
				try {
					File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), System.currentTimeMillis()+".png");
					OutputStream stream = new FileOutputStream(file);
					bitmap.compress(CompressFormat.PNG, 100, stream);
					Toast.makeText(MainActivity.this, "��ͼ���", Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// ���ݶ�λ�ɹ������в�ͬ�Ĵ���
			int code = location.getLocType();
			double lat = 0; // γ��
			double lng = 0; // ����
			if (code == 61 || code == 66 || code == 161) {
				// ��λ�ɹ�
				// �ӷ�������location�л�þ��Ⱥ�γ����Ϣ
				lat = location.getLatitude();
				lng = location.getLongitude();
			} else {
				// ��λʧ��
				// �ֶ�ָ��һ�����Ȼ�γ����Ϣ
				// �ɶ������Ÿ���������ϣ����104.041647 30.625611
				// �ɶ��츮�㳡��104.065326 30.656984
				lat = 30.656984;
				lng = 104.065326;
			}

			// ���ݾ�γ�ȣ����õ���ʾ�ڰٶȵ�ͼ��
			centerPoint = new LatLng(lat, lng);
			MarkerOptions point = new MarkerOptions();
			// ���øõ��λ��(��γ��)
			point.position(new LatLng(lat, lng));
			// ���øõ������(ͼʾ)
			point.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
			baiduMap.addOverlay(point);

			// �����Ϣ��InfoWindow, ��ʾ�������⡱
			View view = getLayoutInflater().inflate(R.layout.mylocation_infowindow, null);
			InfoWindow infoWindow = new InfoWindow(view, new LatLng(lat, lng), -120);
			baiduMap.showInfoWindow(infoWindow);

			// Ų����Ļ���ĵ�
			MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(new LatLng(lat, lng));
			baiduMap.animateMapStatus(msu);

			// ֹͣ��λ
			if (client.isStarted()) {
				client.stop();
			}
		}
	}

	@Override  
	protected void onDestroy() {  
		super.onDestroy();  
		//��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
		mMapView.onDestroy();  
	}  
	@Override  
	protected void onResume() {  
		super.onResume();  
		//��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
		mMapView.onResume();  
	}  
	@Override  
	protected void onPause() {  
		super.onPause();  
		//��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
		mMapView.onPause();  
	}  
}
