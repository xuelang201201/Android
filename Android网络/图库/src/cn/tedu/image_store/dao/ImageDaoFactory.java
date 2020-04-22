package cn.tedu.image_store.dao;

import android.content.Context;
import cn.tedu.image_store.entity.Image;

public class ImageDaoFactory {
	
	/**
	 * ˽�й��췽��
	 */
	private ImageDaoFactory() {
	}
	
	/**
	 * ���ͼƬ��Ϣ����Ķ���
	 * @param context �����Ķ���
	 * @return ͼƬ��Ϣ����Ķ���
	 */
	public static IDao<Image> newInstance(Context context) {
		return new ImageDao(context);
	}
}
