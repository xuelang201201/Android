package cn.tedu.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private float downX; 
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d("tedu", "ACTION_DOWN, x=" + event.getX() + ", y=" + event.getY());
			downX = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			Log.i("tedu", "ACTION_UP, x=" + event.getX() + ", y=" + event.getY());
			if (event.getX() - downX > 60) {
				Log.w("tedu", "left -> right");
			}
			
			if (downX - event.getX() > 60) {
				Log.w("tedu", "right -> left");
			}
			break;
			
		case MotionEvent.ACTION_MOVE:
			Log.v("tedu", "ACTION_MOVE, x=" + event.getX() + ", y=" + event.getY());
			break;
		}
		return super.onTouchEvent(event);
	}
}
