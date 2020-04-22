package cn.tedu.image_store.app;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.image_store.dao.IDao;
import cn.tedu.image_store.dao.ImageDaoFactory;
import cn.tedu.image_store.entity.Image;

import android.app.Application;

/**
 * Application, 在本例中用于把图片数据提供给Activity
 */
public class ImageStoreApplication extends Application {
	private List<Image> images;

	@Override
	public void onCreate() {
		IDao<Image> dao = ImageDaoFactory.newInstance(this);
		setImages(dao.query());
	}
	
	public void setImages(List<Image> images) {
		if (images == null) {
			images = new ArrayList<Image>();
		}
		this.images = images;
	}
	
	public List<Image> getImages() {
		return images;
	}
}
