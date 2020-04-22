package cn.tedu.image_store.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import cn.tedu.image_store.R;
import cn.tedu.image_store.adapter.ImageAdapter;
import cn.tedu.image_store.app.ImageStoreApplication;
import cn.tedu.image_store.entity.Image;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
	
	private GridView gvImages;
	private ImageAdapter adapter;
	private List<Image> images;
	private ImageStoreApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 获取数据源
		app = (ImageStoreApplication) getApplication();
		images = app.getImages();
		
		/*IDao<Image> dao = new ImageDao(this);
		images = dao.query();
		for (Image image : images) {
			Log.d("tedu", "image -> " + image);
		}*/
		
		gvImages = (GridView) findViewById(R.id.gv_images);
		
		adapter = new ImageAdapter(this, images);
		
		gvImages.setAdapter(adapter);
		gvImages.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(
			AdapterView<?> parent,
			View view,
			int position,
			long id) {
		Intent intent = new Intent(this, DisplayImageActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
	}
}
