package com.bignerdranch.android.recyclerviewdemo;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerHolder extends RecyclerView.ViewHolder {

    /**
     * 用于存储当前item当中的view
     */
    private SparseArray<View> mViews;

    public RecyclerHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T findView(int viewId) {
        View view = mViews.get(viewId);
        // 集合中没有，则从item当中获取，并存入集合当中
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerHolder setText(int viewId, String text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecyclerHolder setText(int viewId, int text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecyclerHolder setImageResource(int viewId, int imageId) {
        ImageView image = findView(viewId);
        image.setImageResource(imageId);
        return this;
    }

    public RecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView image = findView(viewId);
        image.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerHolder setImageNet(int viewId, String url) {
        ImageView image = findView(viewId);
        // 使用你所用的网络框架等
        return this;
    }
}
