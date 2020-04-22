package cn.tedu.youlu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.ContactsContract.Data;

/**
 * 图片压缩的工具类
 */
public class BitmapUtils {
	
	/**
	 * 通过path路径找到bitmap 并且返回
	 * @param path
	 * @return
	 */
	public static Bitmap loadBitmap(String path){
		File file=new File(path);
		if(!file.exists()){
			return null;
		}
		return BitmapFactory.decodeFile(path);
	}
	
	/**
	 * 向file文件中存储bitmap图片
	 * @param bitmap
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public static void save(Bitmap bitmap, File file) throws FileNotFoundException{
		if(!file.getParentFile().exists()){
			//创建父目录
			file.getParentFile().mkdirs();
		}
		//向file中写入图片数据
		FileOutputStream fos=new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, fos);
	}
	
	/**
	 * 加载Bitmap对象
	 * @param bytes   数据源
	 * @param width   目标图片的宽度
	 * @param height  目标图片的高度
	 * @return
	 */
	public static Bitmap loadBitmap(byte[] bytes, int width, int height){
		//Options中封装了Bitmapfactory解析
		//图片时涉及到的参数选项
		Options opts=new Options();
		//仅仅加载边界属性
		opts.inJustDecodeBounds=true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		//获取图片原始的宽高
		int w=opts.outWidth/width;
		int h=opts.outHeight/height;
		int scale = w>h ? w : h;
		opts.inSampleSize = scale;
		opts.inJustDecodeBounds=false;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
	}

	/**
	 * 通过photoId 去data表中查找头像
	 * 获取bitmap
	 * @param context
	 * @param photoId
	 * @return
	 */
	public static Bitmap loadBitmap(Context context, int photoId) {
		ContentResolver r=context.getContentResolver();
		Uri uri=Data.CONTENT_URI;
		String[] columns={
				Data.DATA15  //0
		};
		Cursor c=r.query(uri, columns, Data._ID+"=?", new String[]{""+photoId}, null);
		Bitmap bitmap=null;
		if(c.moveToNext()){
			//查到了头像
			byte[] bytes=c.getBlob(0);
			bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
		c.close();
		return bitmap;
	}
}




