package cn.tedu.image_store.dao;

import android.content.Context;
import cn.tedu.image_store.entity.Image;

public class ImageDaoFactory {
	
	/**
	 * 私有构造方法
	 */
	private ImageDaoFactory() {
	}
	
	/**
	 * 获得图片信息的类的对象
	 * @param context 上下文对象
	 * @return 图片信息的类的对象
	 */
	public static IDao<Image> newInstance(Context context) {
		return new ImageDao(context);
	}
}
