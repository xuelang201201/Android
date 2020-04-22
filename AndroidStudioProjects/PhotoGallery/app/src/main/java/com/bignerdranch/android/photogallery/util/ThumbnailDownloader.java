package com.bignerdranch.android.photogallery.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 专用的后台线程，实现按需下载图片
 *
 * 注意，ThumbnailDownloader类使用了<T>泛型参数。ThumbnailDownloader类
 * 的使用者（这里指PhotoGalleryFragment），需要使用某些对象来识别每次下载，
 * 并确定该使用下载图片更新哪个UI元素。有了泛型参数，实施起来方便了很多。
 * @param <T>
 */
public class ThumbnailDownloader<T> extends HandlerThread {
    private static final String TAG = "ThumbnailDownloader";
    private static final int MESSAGE_DOWNLOAD = 0; // 用来识别下载请求消息
    private static final int MESSAGE_PRELOAD = 1; // 用来识别预加载请求消息

    private Boolean mHasQuit = false;
    private Handler mRequestHandler;
    private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>(); // 存取和请求关联的URL下载链接
    private Handler mResponseHandler; // 存放来自于主线程的Handler
    private ThumbnailDownloadListener<T> mThumbnailDownloadListener;

    private LruCache<String, Bitmap> mLruCache; // 创建Cache

    public interface ThumbnailDownloadListener<T> {
        void onThumbnailDownloaded(T target, Bitmap thumbnail);
    }

    public void setThumbnailDownloadListener(ThumbnailDownloadListener<T> listener) {
        mThumbnailDownloadListener = listener;
    }

    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;

        // 获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 在每次存入缓存时调用
                return value.getByteCount();
            }
        };
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_DOWNLOAD:
                        T target = (T) msg.obj;
                        Log.i(TAG, "Got a request for URL: " + mRequestMap.get(target));
                        handleRequest(target);
                        break;

                    case MESSAGE_PRELOAD:
                        String url = (String) msg.obj;
                        downloadImage(url);
                        break;
                }
            }
        };
    }

    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }

    /**
     * 存根方法
     * @param target 标识具体那次下载
     * @param url URL下载链接
     */
    public void queueThumbnail(T target, String url) {
        Log.i(TAG, "Got a URL: " + url);

        if(url == null) {
            mRequestMap.remove(target);
        } else {
            mRequestMap.put(target, url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target)
                    .sendToTarget();
        }
    }

    public void preloadImage(String url) {
        mRequestHandler.obtainMessage(MESSAGE_PRELOAD, url)
                .sendToTarget();
    }

    // 从缓存中获取数据
    public Bitmap getCachedImage(String url) {
        return mLruCache.get(url);
    }

    /**
     * 清除队列中所有的请求
     */
    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestHandler.removeMessages(MESSAGE_PRELOAD);
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        mLruCache.evictAll();
    }

    private void handleRequest(final T target) {
        final String url = mRequestMap.get(target);
        final Bitmap bitmap;

        if (url == null) {
            return;
        }

        bitmap = downloadImage(url);
        Log.i(TAG, "Bitmap created");

        // 图片下载与显示
        mResponseHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mRequestMap.get(target) != url ||
                        mHasQuit) {
                    return;
                }

                mRequestMap.remove(target);
                mThumbnailDownloadListener.onThumbnailDownloaded(target, bitmap);
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap;
        if (url == null) {
            return null;
        }

        bitmap = mLruCache.get(url);
        if (bitmap != null) {
            return bitmap;
        }

        try {
            byte[] bitmapBytes = new FlickrFetchr().getUrlBytes(url);
            bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            mLruCache.put(url, bitmap);
            Log.i(TAG, "Downloaded & cached image: " + url);
            return bitmap;
        } catch (IOException ioe) {
            Log.e(TAG, "Error downloading image" + ioe);
            return null;
        }
    }
}
