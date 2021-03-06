package cn.tedu.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	// 带 1 个参数的构造方法，用于在程序中直接创建控件的对象
	public CustomTextView(Context context) {
		super(context);
	}

	// 带 2/3 个参数的构造方法，用于在布局文件(res\layou下的xml文件)中设计控件
	public CustomTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// 带 2/3 个参数的构造方法，用于在布局文件(res\layou下的xml文件)中设计控件
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// 自定义的代码
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
