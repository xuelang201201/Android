package com.bignerdranch.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private static final String PARENT_STATE_KEY = "ParentStateKey";

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    // 使用代码创建视图
    public BoxDrawingView(Context context) {
        this(context, null);
    }

    // 使用布局文件创建视图
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 半透明红色
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        // 米白色背景
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                // 重置画布状态
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate(); // 强制BoxDrawingView重新绘制自己
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG, action + " at x=" + current.x +
                ", y=" + current.y);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 填充背景
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        // 保存父类状态
        Parcelable parentState = super.onSaveInstanceState();
        // 用Bundle保存状态
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARENT_STATE_KEY, parentState);

        int boxNumber = 1;

        for (Box box : mBoxen) {
            float[] pointsArray = {
                    box.getOrigin().x,
                    box.getOrigin().y,
                    box.getCurrent().x,
                    box.getCurrent().y
            };

            // 保存
            bundle.putFloatArray("box" + boxNumber, pointsArray);
            boxNumber ++;
        }
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // 恢复视图的状态（boxes）
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(PARENT_STATE_KEY));

            // 恢复盒子的名字作为key
            String prefixName = "box";
            int boxCount = 1;

            // 当发现一个key的时候画一个盒子
            while (bundle.containsKey(prefixName + boxCount)) {
                // 从bundle中得到x和y的值
                float[] pointsArray = bundle.getFloatArray(prefixName + boxCount);

                // 从保存的数组中创建盒子
                PointF origin = new PointF(pointsArray[0], pointsArray[1]);
                PointF current = new PointF(pointsArray[2], pointsArray[3]);
                Box box = new Box(origin);
                box.setCurrent(current);

                mBoxen.add(box);
                boxCount ++;
            }
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
