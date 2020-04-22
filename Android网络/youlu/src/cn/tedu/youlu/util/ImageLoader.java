package cn.tedu.youlu.util;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.tarena.youlu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

public class ImageLoader {
	//�������ڴ洢�ڴ滺��ͼƬ��hashmap
	private HashMap<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();
	private Context context;
	private List<ImageLoaderTask> tasks = new ArrayList<ImageLoaderTask>();
	//����������ѭ���񼯺ϵĹ����߳�
	private Thread workThread;
	private boolean isLoop = true;
	private AbsListView listView;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_IMAGE_SUCCESS:
				//��ImageView����Bitmap
				ImageLoaderTask task=(ImageLoaderTask) msg.obj;
				ImageView ivAlbum=(ImageView) listView.findViewWithTag(task.position);
				if(ivAlbum!=null){
					if(task.bitmap!=null){
						ivAlbum.setImageBitmap(task.bitmap);
					}else{
						ivAlbum.setImageResource(R.drawable.ic_launcher);
					}
				}
				break;
			}
		}
	};

	public static final int HANDLER_LOAD_IMAGE_SUCCESS=1;

	
	
	public ImageLoader(Context context, AbsListView listView) {
		this.context = context;
		this.listView = listView;
		workThread = new Thread(){
			public void run() {
				//������ѭ���񼯺�  
				while(isLoop){
					if(!tasks.isEmpty()){
						//������������������  ��ô��ȡ��ִ��
						ImageLoaderTask task=tasks.remove(0);
						Bitmap bitmap=loadBitmap(task.photoId);
						task.bitmap = bitmap;
						//��bitmap���õ�ImageView��(�����߳������)
						Message msg =new Message();
						msg.what =HANDLER_LOAD_IMAGE_SUCCESS;
						msg.obj = task;
						handler.sendMessage(msg);
					}else{
						//���������û���������  �����߳���ȴ�
						try {
							synchronized (workThread) {
								workThread.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}; 
		//���������߳�  ��ʼ��ѭ
		workThread.start();
	}
	
	/**
	 * ����ͼƬ
	 * @param path     images/junshengjinshi.jpg
	 * @return
	 */
	public Bitmap loadBitmap(int photoId){
		try {
			Bitmap bitmap=BitmapUtils.loadBitmap(context, photoId);
			//�����ص���bitmap�浽�ڴ滺��cache��
			SoftReference<Bitmap> ref = new SoftReference<Bitmap>(bitmap);
			cache.put(photoId, ref);
			//���ļ��д洢bitmapͼƬ
			File file=new File(context.getCacheDir(),photoId+".jpg");
			//file-->  /data/data/com.tarena.musicclient/cache/images/jsjs.jpg
			BitmapUtils.save(bitmap, file);
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * �첽��ʾͼƬ
	 * @param imageView  Ŀ��ؼ�
	 * @param path  ͼƬ·��
	 * @param position  
	 */
	public void displayImage(ImageView imageView, int photoId, int position){
		//���photoId == 0  
		if(photoId == 0){
			imageView.setImageResource(R.drawable.img01g_15);
			return;
		}
		
		imageView.setTag(position);
		//����ڴ滺���к�����Ҫ��ͼƬ ��ֱ������
		SoftReference<Bitmap> ref=cache.get(photoId);
		if(ref!=null){  //��ǰ���
			Bitmap bitmap = ref.get();  
			if(bitmap != null){ //����ͼƬ����
				Log.i("info", "��ͼƬ�Ǵ��ڴ��ж�ȡ��");
				imageView.setImageBitmap(bitmap);
				return;
			}
		}
		
		//����ڴ滺����û��  ��ȥ�ļ�
		//�����ж�ȡ 
		File file=new File(context.getCacheDir(), photoId+".jpg");
		String filepath=file.getAbsolutePath();
		Bitmap bitmap=BitmapUtils.loadBitmap(filepath);
		if(bitmap!=null){  //���ļ������ж�����bitmap
			Log.i("info", "���ļ������ж�ȡ��...");
			//���ڴ����ٴ�һ��
			cache.put(photoId, new SoftReference<Bitmap>(bitmap));
			imageView.setImageBitmap(bitmap);
			return;
		}

		//�����񼯺������ͼƬ��������
		ImageLoaderTask task = new ImageLoaderTask();
		task.photoId = photoId;
		task.position = position;
		tasks.add(task);
		//���ѹ����߳�  �����ɻ�
		synchronized (workThread) {
			workThread.notify();
		}


	}
	
	/**
	 * ��װһ��ͼƬ��������
	 */
	class ImageLoaderTask{
		int photoId;
		String path;  //ͼƬ·��
		Bitmap bitmap; //ͼƬ����  
		int position;  //������ǰ������Ե����ĸ�item
	}

	public void stopThread() {
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	
}
