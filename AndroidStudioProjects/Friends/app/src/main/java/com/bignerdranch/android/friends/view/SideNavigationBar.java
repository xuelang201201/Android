package com.bignerdranch.android.friends.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.bignerdranch.android.friends.R;

public class SideNavigationBar extends View {

	OnTouchLetterListener mListener;
	Paint mPaint;
	String[] mLetters = {
			"❤", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "#"};

	int mTextColor; // 自定义属性，字体颜色
	int mTextSize; // 自定义属性，字体大小

	/**
	 * 双参数构造应用的场合：
	 * 当你在布局文件中，声明自定义View时
	 *
	 * @param context 上下文对象
	 * @param attrs
	 */
	public SideNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttr(context, attrs);

		// 初始化画笔
		initPaint();
	}

	/**
	 * 从布局文件中读取自定义属性
	 * @param context
	 * @param attrs
	 */
	private void initAttr(Context context, AttributeSet attrs) {
		TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.SideNavigationBar);
		mTextColor = t.getColor(R.styleable.SideNavigationBar_text_color, Color.GRAY);
		mTextSize = t.getDimensionPixelSize(
				R.styleable.SideNavigationBar_text_size,
				(int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
		t.recycle(); // 回收
	}

	public void setListener(OnTouchLetterListener listener) {
		this.mListener = listener;
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		mPaint = new Paint();
		mPaint.setColor(mTextColor);
		mPaint.setTextSize(mTextSize);
		mPaint.setStyle(Style.FILL);
		mPaint.setTypeface(Typeface.MONOSPACE); // 字体
	}

	/**
	 * 单参数构造器：
	 * 当使用代码创建视图对象的时候，需要使用单参数构造器
	 * 需要使用各种setter（set方法）为视图提供属性
	 * @param context 上下文
	 */
	public SideNavigationBar(Context context) {
		super(context);
	}

	/**
	 * 把我希望的view的样子“画”到屏幕上
	 * 如果要画画，就需要画布(canvas), 画笔(mPaint)
	 * onDraw方法为咱们提供了一个画布
	 * 任何在这个画布上画的内容，都是显示在屏幕上
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 将文本画到屏幕上
		for (int i = 0; i < mLetters.length; i++) {
			// x = 框的宽度 / 2 - 字的宽度 / 2
			// y = 框的高度 / 2 + 字的高度 / 2 + 若干框的高度

			// 框的宽度 = 整体的自定义的控件的宽度
			// 而获得整体视图的宽度用getWidth方法；
			int width = getWidth();

			// 框的高度 = 整体的自定义的控件的高度 / mLetters.length;
			// 而获得整体视图的高度用getHeight方法；
			int height = getHeight() / mLetters.length;

			// 字的宽度和高度
			Rect bounds = new Rect();
			mPaint.getTextBounds(mLetters[i], 0, mLetters[i].length(), bounds);

			// 文字的宽度
			int letterwidth = bounds.width();
			// 文字的高度
			int letterheight = bounds.height();

			float x = width / 2 - letterwidth / 2;
			float y = height / 2 + letterheight / 2 + height * i;
			canvas.drawText(mLetters[i], x, y, mPaint);
		}
	}

	/**
	 * 手指在自定义View上按下或者滑动或者抬起
	 * 该方法都会被调用
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				setBackgroundColor(Color.TRANSPARENT);
				// 手指的位置
				float y = event.getY();
				// 通过手指的位置和自定义视图整体高度的比值
				// 计算出要去字符串的下标值
				int idx = (int) ((y * mLetters.length) / getHeight());
				// 如果下标值取值合理(0~mLetters.length)
				if (idx >= 0 && idx < mLetters.length) {
					if (mListener != null) { // 监听器不为空
						String str = mLetters[idx];
						mListener.onTouchLetter(str);
					}
				}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				// 或者default：
				setBackgroundColor(Color.TRANSPARENT);
				if (mListener != null) {
					mListener.onFinishTouch();
				}
				break;
		}
		return true;
	}

	public interface OnTouchLetterListener {
		/**
		 * 当用户手指在自定义View上滑动的时候
		 * 调用该方法，并将当前手指位置的字母
		 * 作为参数传到该方法中
		 * @param str
		 */
		void onTouchLetter(String str);
		/**
		 * 当用户手指离开了自定义
		 */
		void onFinishTouch();
	}
}
