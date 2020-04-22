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
	//声明用于存储内存缓存图片的hashmap
	private HashMap<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();
	private Context context;
	private List<ImageLoaderTask> tasks = new ArrayList<ImageLoaderTask>();
	//声明用于轮循任务集合的工作线程
	private Thread workThread;
	private boolean isLoop = true;
	private AbsListView listView;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_IMAGE_SUCCESS:
				//给ImageView设置Bitmap
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
				//不断轮循任务集合  
				while(isLoop){
					if(!tasks.isEmpty()){
						//如果集合中有任务对象  那么获取并执行
						ImageLoaderTask task=tasks.remove(0);
						Bitmap bitmap=loadBitmap(task.photoId);
						task.bitmap = bitmap;
						//把bitmap设置到ImageView中(在主线程中完成)
						Message msg =new Message();
						msg.what =HANDLER_LOAD_IMAGE_SUCCESS;
						msg.obj = task;
						handler.sendMessage(msg);
					}else{
						//如果集合中没有任务对象  工作线程则等待
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
		//启动工作线程  开始轮循
		workThread.start();
	}
	
	/**
	 * 下载图片
	 * @param path     images/junshengjinshi.jpg
	 * @return
	 */
	public Bitmap loadBitmap(int photoId){
		try {
			Bitmap bitmap=BitmapUtils.loadBitmap(context, photoId);
			//把下载到的bitmap存到内存缓存cache中
			SoftReference<Bitmap> ref = new SoftReference<Bitmap>(bitmap);
			cache.put(photoId, ref);
			//向文件中存储bitmap图片
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
	 * 异步显示图片
	 * @param imageView  目标控件
	 * @param path  图片路径
	 * @param position  
	 */
	public void displayImage(ImageView imageView, int photoId, int position){
		//如果photoId == 0  
		if(photoId == 0){
			imageView.setImageResource(R.drawable.img01g_15);
			return;
		}
		
		imageView.setTag(position);
		//如果内存缓存中含有需要的图片 则直接设置
		SoftReference<Bitmap> ref=cache.get(photoId);
		if(ref!=null){  //以前存过
			Bitmap bitmap = ref.get();  
			if(bitmap != null){ //缓存图片还在
				Log.i("info", "该图片是从内存中读取的");
				imageView.setImageBitmap(bitmap);
				return;
			}
		}
		
		//如果内存缓存中没有  则去文件
		//缓存中读取 
		File file=new File(context.getCacheDir(), photoId+".jpg");
		String filepath=file.getAbsolutePath();
		Bitmap bitmap=BitmapUtils.loadBitmap(filepath);
		if(bitmap!=null){  //从文件缓存中读到了bitmap
			Log.i("info", "从文件缓存中读取的...");
			//向内存中再存一次
			cache.put(photoId, new SoftReference<Bitmap>(bitmap));
			imageView.setImageBitmap(bitmap);
			return;
		}

		//向任务集合中添加图片下载任务
		ImageLoaderTask task = new ImageLoaderTask();
		task.photoId = photoId;
		task.position = position;
		tasks.add(task);
		//唤醒工作线程  起来干活
		synchronized (workThread) {
			workThread.notify();
		}


	}
	
	/**
	 * 封装一个图片下载任务
	 */
	class ImageLoaderTask{
		int photoId;
		String path;  //图片路径
		Bitmap bitmap; //图片对象  
		int position;  //描述当前任务针对的是哪个item
	}

	public void stopThread() {
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	
}
