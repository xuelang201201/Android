package cn.tedu.musicclient.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * ͼƬѹ���Ĺ�����
 */
public class BitmapUtils {
	
	/**
	 * ͨ��path·���ҵ�bitmap ���ҷ���
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
	 * ��file�ļ��д洢bitmapͼƬ
	 * @param bitmap
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public static void save(Bitmap bitmap, File file) throws FileNotFoundException{
		if(!file.getParentFile().exists()){
			//������Ŀ¼
			file.getParentFile().mkdirs();
		}
		//��file��д��ͼƬ����
		FileOutputStream fos=new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, fos);
	}
	
	/**
	 * ����Bitmap����
	 * @param bytes   ����Դ
	 * @param width   Ŀ��ͼƬ�Ŀ��
	 * @param height  Ŀ��ͼƬ�ĸ߶�
	 * @return
	 */
	public static Bitmap loadBitmap(byte[] bytes, int width, int height){
		//Options�з�װ��Bitmapfactory����
		//ͼƬʱ�漰���Ĳ���ѡ��
		Options opts=new Options();
		//�������ر߽�����
		opts.inJustDecodeBounds=true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		//��ȡͼƬԭʼ�Ŀ��
		int w=opts.outWidth/width;
		int h=opts.outHeight/height;
		int scale = w>h ? w : h;
		opts.inSampleSize = scale;
		opts.inJustDecodeBounds=false;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
	}
}




