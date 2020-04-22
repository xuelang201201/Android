package cn.tedu.custom_view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
	
	private ImageView ivImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivImage = (ImageView) findViewById(R.id.iv_image);
		
		findViewById(R.id.btn_draw).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 准备新的Bitmap
		Bitmap bm = Bitmap.createBitmap(300, 200, Config.ARGB_8888);
		// 将Bitmap应用到Canvas
		Canvas canvas = new Canvas(bm);
		// 创建画笔
		Paint paint = new Paint();
		// 设置画笔颜色
		paint.setColor(Color.parseColor("#00ff00"));
		// 设置画笔空心
		paint.setStyle(Style.STROKE);
		// 设置画笔的粗细
		paint.setStrokeWidth(4f);
		// 设置消除锯齿
		paint.setAntiAlias(true);
		
		// 绘制整个Canvas的颜色(背景颜色)
		canvas.drawColor(Color.parseColor("#ff0000"));
		
		// 绘制矩形
		// canvas.drawRect(100f, 50f, 200f, 150f, paint);
		
		// 绘制圆形
		canvas.drawCircle(bm.getWidth() / 2, bm.getHeight() / 2, 50f, paint);
		
		// 将Bitmap显示到ImageView控件中
		ivImage.setImageBitmap(bm);
	}
}
