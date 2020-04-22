package com.bignerdranch.android.friends.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {
    /**
     * 静态Bitmap估算方法。
     * 该方法先确认屏幕的尺寸，然后按此缩放图像。
     * 这样，就能保证载入的ImageView永远不会过大。
     * @param id
     * @param activity
     * @return
     */
    public static Bitmap getScaledBitmap(int id, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaledBitmap(id, size.x, size.y);
    }

    public static Bitmap getScaledBitmap(int id, int destWidth, int destHeight) {
        // 读取磁盘上的图片的大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(String.valueOf(id), options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        // 确定缩放的比例
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // 读入并创建最终位图
        return BitmapFactory.decodeFile(String.valueOf(id), options);
    }
}
