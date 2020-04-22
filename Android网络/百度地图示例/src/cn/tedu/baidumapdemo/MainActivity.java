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

	private LocationClient client; // 定位“客户端”
	private MyLocationListener listener; // 定位监听器
	private BaiduMap baiduMap; // 百度地图

	private LatLng centerPoint;

	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);   
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
		//注意该方法要再setContentView方法之前实现  
		SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.activity_main);  
		//获取地图控件引用  
		mMapView = (MapView) findViewById(R.id.bmapView);  

		initHeaderView();
		initBaiduMap();
	}  

	private void initBaiduMap() {
		baiduMap = mMapView.getMap(); // 赋值
		// TODO
		// 改变一下百度地图的初始比例尺
		baiduMap.setMaxAndMinZoomLevel(20, 17);
		// 为地图上的覆盖物(marker)添加点击监听器
		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {

				// 获得被点击的marker中信息，来构建一个InfoWindow
				Bundle bundle = marker.getExtraInfo();

				if (bundle != null) {
					String name = bundle.getString("name");
					String address = bundle.getString("address");
					String number = bundle.getString("number");

					// 创建显示这些内容的InfoWindow
					View view = getLayoutInflater().inflate(R.layout.poi_infowindow, null);
					TextView tvName = (TextView) view.findViewById(R.id.tv_poi_name);
					tvName.setText(name);
					TextView tvAddress = (TextView) view.findViewById(R.id.tv_poi_address);
					tvAddress.setText(address);
					TextView tvNumber = (TextView) view.findViewById(R.id.tv_poi_number);

					// 商家有可能未留电话，所以number有可能为空
					if (TextUtils.isEmpty(number)) {
						// "", null isEmpty方法返回true
						tvNumber.setText("电话未知");
					} else {
						tvNumber.setText(number);
					}

					InfoWindow infoWindow = new InfoWindow(view, marker.getPosition(), -120);
					baiduMap.hideInfoWindow(); // 把现在地图上显示的InfoWindow都隐藏
					baiduMap.showInfoWindow(infoWindow); // 把新创建的显示出来
				}
				return true;
			}
		});
	}

	private void initHeaderView() {
		headerView = findViewById(R.id.headerview);
		ImageView ivLeft = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		// 设置ivLeft图片颜色从灰色 ---> 白色
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
	 * 定位使用者的位置
	 */
	private void getMyLocation() {
		client = new LocationClient(this);
		listener = new MyLocationListener();

		// 对location进行海量的定位设置
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
		int span=1000;
		option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);//可选，默认false,设置是否使用gps
		option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死  
		option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		client.setLocOption(option);

		client.registerLocationListener(listener);
		client.start();
	}

	/**
	 * 根据一个地址，找到该地址对应的经纬度
	 * 再将该经纬度标识在地图上
	 */
	private void getAddress(String address) {
		// 专门进行地理位置查询
		// 通过地址 --> 经纬度：地理位置信息查询
		// 通过经纬度 --> 地址：反向地理位置信息查询
		GeoCoder geoCoder = GeoCoder.newInstance();
		// geoCoder指定一个监听器
		geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
				// TODO 通过经纬度查地址成功

			}

			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				// 通过地址查经纬度成功
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(MainActivity.this, "神秘地址未找到", Toast.LENGTH_LONG).show();
				} else {

					centerPoint = result.getLocation();

					// 创建覆盖物对象
					MarkerOptions overlay = new MarkerOptions();
					// 设置覆盖物的两个必须的属性
					// 覆盖物的位置(LatLnt类型的对象，里面含有精度和纬度信息)
					overlay.position(result.getLocation());
					// 覆盖物的图标
					overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
					// 添加覆盖物(overlay)
					baiduMap.addOverlay(overlay);

					// 创建msu对象，msu中含有一个位置点信息(是latlng类型的对象)
					MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(result.getLocation());
					// 移动屏幕中心点，移动到的位置是由msu来决定的
					baiduMap.animateMapStatus(msu);
				}
			}
		});
		// geoCoder指定要查询的内容
		GeoCodeOption opts = new GeoCodeOption();
		opts.city(address.substring(0, 2));
		opts.address(address);
		// 发起查询
		geoCoder.geocode(opts);
	}

	/**
	 * 搜索附近兴趣点
	 */
	private void getNearby() {
		// POI:point of interest 兴趣点（感兴趣的点）
		// 1）中心点，2）搜索范围（搜索半径），3）搜索关键字（"美食", "宾馆", "银行"）
		// PoiSearch是专门进行兴趣点搜索的类
		PoiSearch poiSearch = PoiSearch.newInstance();
		// poiSearch指定一个监听器。当搜索完毕后，BaiduSDK会回调该监听器中的方法，并将搜索结果作为参数传入
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

			@Override
			public void onGetPoiResult(PoiResult result) {
				// 使用搜索结果
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(MainActivity.this, "没有找到，请扩大搜索范围", Toast.LENGTH_LONG).show();
				} else {
					List<PoiInfo> pois = result.getAllPoi();
					// 遍历pois，将每一个兴趣点都添加到地图上
					for (PoiInfo poiInfo : pois) {

						MarkerOptions overlay = new MarkerOptions();
						overlay.position(poiInfo.location);
						overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka));
						Marker marker = (Marker) baiduMap.addOverlay(overlay);
						// 往覆盖物中添加信息(商家的名称，地址，电话...)
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

		// 设定搜索参数(中心点，搜索范围，关键字)
		PoiNearbySearchOption opts = new PoiNearbySearchOption();
		opts.keyword("美食"); // 关键词 
		opts.radius(3000); // 搜索半径
		opts.location(centerPoint); // 中心点
		// 发起搜索
		poiSearch.searchNearby(opts);
	}

	/**
	 * 地图截图
	 */
	private void getScreenshot() {
		baiduMap.snapshot(new SnapshotReadyCallback() {
			
			@Override
			public void onSnapshotReady(Bitmap bitmap) {
				// 将内存中的地图截图，保存到SD卡
				try {
					File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), System.currentTimeMillis()+".png");
					OutputStream stream = new FileOutputStream(file);
					bitmap.compress(CompressFormat.PNG, 100, stream);
					Toast.makeText(MainActivity.this, "截图完毕", Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// 根据定位成功与否进行不同的处理
			int code = location.getLocType();
			double lat = 0; // 纬度
			double lng = 0; // 经度
			if (code == 61 || code == 66 || code == 161) {
				// 定位成功
				// 从方法参数location中获得经度和纬度信息
				lat = location.getLatitude();
				lng = location.getLongitude();
			} else {
				// 定位失败
				// 手动指定一个经度或纬度信息
				// 成都高升桥高朋大道东方希望：104.041647 30.625611
				// 成都天府广场：104.065326 30.656984
				lat = 30.656984;
				lng = 104.065326;
			}

			// 根据经纬度，将该点显示在百度地图上
			centerPoint = new LatLng(lat, lng);
			MarkerOptions point = new MarkerOptions();
			// 设置该点的位置(经纬度)
			point.position(new LatLng(lat, lng));
			// 设置该点的样子(图示)
			point.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
			baiduMap.addOverlay(point);

			// 添加信息窗InfoWindow, 显示“我在这”
			View view = getLayoutInflater().inflate(R.layout.mylocation_infowindow, null);
			InfoWindow infoWindow = new InfoWindow(view, new LatLng(lat, lng), -120);
			baiduMap.showInfoWindow(infoWindow);

			// 挪动屏幕中心点
			MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(new LatLng(lat, lng));
			baiduMap.animateMapStatus(msu);

			// 停止定位
			if (client.isStarted()) {
				client.stop();
			}
		}
	}

	@Override  
	protected void onDestroy() {  
		super.onDestroy();  
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
		mMapView.onDestroy();  
	}  
	@Override  
	protected void onResume() {  
		super.onResume();  
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
		mMapView.onResume();  
	}  
	@Override  
	protected void onPause() {  
		super.onPause();  
		//在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
		mMapView.onPause();  
	}  
}
