package cn.tedu.tween_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private TextView tvHelloWorld;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvHelloWorld = (TextView) findViewById(R.id.tv_helloworld);
		
		findViewById(R.id.btn_alpha).setOnClickListener(this);
		findViewById(R.id.btn_rotate).setOnClickListener(this);
		findViewById(R.id.btn_scale).setOnClickListener(this);
		findViewById(R.id.btn_translate).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_alpha:
			Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
			tvHelloWorld.startAnimation(alphaAnimation);
			break;
			
		case R.id.btn_rotate:
			Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
			tvHelloWorld.startAnimation(rotateAnimation);
			break;
			
		case R.id.btn_scale:
			Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
			tvHelloWorld.startAnimation(scaleAnimation);
			break;
			
		case R.id.btn_translate:
			Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
			tvHelloWorld.startAnimation(translateAnimation);
			break;
		}
	}
}
