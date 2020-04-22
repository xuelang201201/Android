package com.bignerdranch.android.lifeshow.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class FlickrFetchr {

    private Context mContext;
    public ImageLoader mImageLoader;

    public FlickrFetchr(Context context) {
        mContext = context;
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mQueue, new BitmapCache());
    }
}
