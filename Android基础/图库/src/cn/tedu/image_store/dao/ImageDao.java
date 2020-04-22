package cn.tedu.image_store.dao;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.image_store.entity.Image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageDao implements IDao<Image> {
	private Context context;
	
	public ImageDao(Context context) {
		setContext(context);
	}
	
	public void setContext(Context context) {
		if(context == null) {
			throw new IllegalArgumentException("����Context������Ϊnull������");
		}
		this.context = context;
	}

	@Override
	public List<Image> query() {
		// ׼������ֵ
		List<Image> images = new ArrayList<Image>();
		
		// ׼��ContentResolver
		ContentResolver cr = context.getContentResolver();
		
		// ׼��Uri
		Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		
		// ִ�ж�ȡ���ݣ�����ȡCursor
		String[] projection = {
			"_id", // 0
			"_data", // 1
			"_size", // 2
			"_display_name", // 3 
			"width", // 4
			"height" // 5
		};
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;
		Cursor c = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		
		// ��鲢����Cursor
		if(c != null && c.moveToFirst()) {
			Image image;
			for(; !c.isAfterLast(); c.moveToNext()) {
				image = new Image();
				image.setId(c.getLong(0));
				image.setPath(c.getString(1));
				image.setSize(c.getInt(2));
				image.setDisplayName(c.getString(3));
				image.setWidth(c.getInt(4));
				image.setHeight(c.getInt(5));
				images.add(image);
			}
		}
		
		// �ͷ���Դ
		if(c != null && !c.isClosed()) {
			c.close();
			c = null;
		}
		
		// ����
		return images;
	}
	

}