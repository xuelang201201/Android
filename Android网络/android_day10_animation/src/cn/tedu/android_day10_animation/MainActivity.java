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
			// ִ��֡����
			// startAnimation();
			startAnimationCode();
			break;
			
		case R.id.btn_display:
			// �������Զ���
			startPropertyAnimation();
			break;
		}
	}

	// �������Զ���
	private void startPropertyAnimation() {
		ObjectAnimator anim = ObjectAnimator.ofFloat(image, "abc", 1f, 0.1f);
		// ��anim��ӡ�ֵ�޸ļ�����
		//��ObjectAnimator����ĳʱ�̼������ֵ��
		//ִ��onAnimationUpdate��������
		anim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float f = (Float) animation.getAnimatedValue();
				image.setScaleX(2*f);
				image.setScaleY(2*f);
			}
		});
		anim.setDuration(5000);
		// �������ٵ�ʱ���ֵ��
		// anim.setInterpolator(new LinearInterpolator());
		anim.start(); // �������Զ���
	}

	// ʹ�ô���ִ��֡����
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

	// ִ��֡����
	private void startAnimation() {
		// 1.����֡�����������ļ�
		// 2.��ImageView���ñ��� AnimationDrawable
		image.setBackgroundResource(R.drawable.anim);
		// 3.drawable.start()
		AnimationDrawable drawable = (AnimationDrawable) image.getBackground();
		drawable.start();
	}
	
}
