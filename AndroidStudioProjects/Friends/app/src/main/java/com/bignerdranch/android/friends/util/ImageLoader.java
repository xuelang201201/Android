package com.bignerdranch.android.friends.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.bignerdranch.android.friends.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {
    //声明用于存储内存缓存图片的LruCache
    private LruCache<Integer, Bitmap> mLruCache;
    private Context mContext;
    private List<ImageLoaderTask> tasks = new ArrayList<ImageLoaderTask>();
    //声明用于轮循任务集合的工作线程
    private Thread workThread;
    private boolean isLoop = true;
    private RecyclerView mRecyclerView;

    public static final int HANDLER_LOAD_IMAGE_SUCCESS = 1;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HANDLER_LOAD_IMAGE_SUCCESS:
                    //给ImageView设置Bitmap
                    ImageLoaderTask task=(ImageLoaderTask) msg.obj;
                    ImageView ivAlbum=(ImageView) mRecyclerView.findViewWithTag(task.position);
                    if(ivAlbum != null){
                        if(task.bitmap != null){
                            ivAlbum.setImageBitmap(task.bitmap);
                        }else{
                            ivAlbum.setImageResource(R.drawable.ic_avatar);
                        }
                    }
                    break;
            }
        }
    };

    public ImageLoader(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
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
                        Message msg = new Message();
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

        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 设置图片缓存大小为程序最大可用内存的1/8
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<Integer, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        //启动工作线程  开始轮循
        workThread.start();
    }

    /**
     * 下载图片
     * @param photoId images/junshengjinshi.jpg
     * @return
     */
    public Bitmap loadBitmap(int photoId){
        try {
            Bitmap bitmap=BitmapUtils.loadBitmap(mContext, photoId);
            //把下载到的bitmap存到内存缓存cache中
            mLruCache.put(photoId, bitmap);
            //向文件中存储bitmap图片
            File file = new File(mContext.getCacheDir(), photoId + ".jpg");
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
     * @param photoId  图片路径
     * @param position
     */
    public void displayImage(ImageView imageView, int photoId, int position){
        //如果photoId == 0
        if(photoId == 0){
            imageView.setImageResource(R.drawable.ic_avatar);
            return;
        }

        imageView.setTag(position);
        //如果内存缓存中含有需要的图片 则直接设置
        Bitmap bitmap = mLruCache.get(photoId);
        if (bitmap != null) { //缓存图片还在
            Log.i("info", "该图片是从内存中读取的");
            imageView.setImageBitmap(bitmap);
            return;
        }

        //如果内存缓存中没有  则去文件
        //缓存中读取
        File file = new File(mContext.getCacheDir(), photoId + ".jpg");
        String filepath = file.getAbsolutePath();
        bitmap = BitmapUtils.loadBitmap(filepath);
        if(bitmap != null){  //从文件缓存中读到了bitmap
            Log.i("info", "从文件缓存中读取的...");
            //向内存中再存一次
            mLruCache.put(photoId, bitmap);
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
    class ImageLoaderTask {
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
