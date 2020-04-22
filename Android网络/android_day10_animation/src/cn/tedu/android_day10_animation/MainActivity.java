package cn.tedu.android_day10_animation;

import cn.tedu.android_day09_animation.R;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		image = (TextView) findViewById(R.id.image);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			// 执行帧动画
			// startAnimation();
			startAnimationCode();
			break;
			
		case R.id.btn_display:
			// 启动属性动画
			startPropertyAnimation();
			break;
		}
	}

	// 启动属性动画
	private void startPropertyAnimation() {
		ObjectAnimator anim = ObjectAnimator.ofFloat(image, "abc", 1f, 0.1f);
		// 给anim添加“值修改监听”
		//当ObjectAnimator根据某时刻计算出插值后
		//执行onAnimationUpdate监听方法
		anim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float f = (Float) animation.getAnimatedValue();
				image.setScaleX(2*f);
				image.setScaleY(2*f);
			}
		});
		anim.setDuration(5000);
		// 设置匀速的时间插值器
		// anim.setInterpolator(new LinearInterpolator());
		anim.start(); // 启动属性动画
	}

	// 使用代码执行帧动画
	private void startAnimationCode() {
		AnimationDrawable drawable = new AnimationDrawable();
		drawable.addFrame(getResources().getDrawable(R.drawable.h1), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h2), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h3), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h4), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h5), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h6), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h7), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h8), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h9), 150);
		drawable.addFrame(getResources().getDrawable(R.drawable.h10), 150);
		image.setBackground(drawable);
		drawable.setOneShot(false);
		drawable.start();
	}

	// 执行帧动画
	private void startAnimation() {
		// 1.定义帧动画的配置文件
		// 2.给ImageView设置背景 AnimationDrawable
		image.setBackgroundResource(R.drawable.anim);
		// 3.drawable.start()
		AnimationDrawable drawable = (AnimationDrawable) image.getBackground();
		drawable.start();
	}
	
}
