package cn.tedu.image_store.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;
import cn.tedu.image_store.R;
import cn.tedu.image_store.app.ImageStoreApplication;
import cn.tedu.image_store.entity.Image;

public class DisplayImageActivity extends Activity implements
		View.OnClickListener {
	/**
	 * Application
	 */
	private ImageStoreApplication app;
	/**
	 * ����Դ
	 */
	private List<Image> images;
	/**
	 * ��ǰ��ʾ��ͼƬ������
	 */
	private int currentImageIndex;
	/**
	 * ��ʾͼƬ�Ŀؼ�
	 */
	// private ImageView ivImage;
	private ImageSwitcher isImageSwitcher;
	/**
	 * ��ť����һ��
	 */
	private Button btnPrevious;
	/**
	 * ��ť����һ��
	 */
	private Button btnNext;
	/**
	 * �ж����Ʋ���ʱ����¼�İ���ʱx������λ��
	 */
	private float actionDownX;
	/**
	 * ����
	 */
	private Animation left2RightIn;
	/**
	 * ����
	 */
	private Animation left2RightOut;
	/**
	 * ����
	 */
	private Animation Right2LeftIn;
	/**
	 * ����
	 */
	private Animation Right2LeftOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_image);

		// ��ʼ���ؼ�
		// ivImage = (ImageView) findViewById(R.id.iv_image);
		btnPrevious = (Button) findViewById(R.id.btn_previous);
		btnNext = (Button) findViewById(R.id.btn_next);
		isImageSwitcher = (ImageSwitcher) findViewById(R.id.is_image_switcher);

		// ���Ƴ�ʼ��ImageSwitcher�����2���Ӽ�ImageView
		isImageSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView iv = new ImageView(DisplayImageActivity.this);
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_CENTER);
				iv.setImageResource(R.drawable.ic_launcher);
				return iv;
			}
		});

		// Ϊ�ؼ����ü�����
		btnPrevious.setOnClickListener(this);
		btnNext.setOnClickListener(this);

		// ��ȡ����Դ
		app = (ImageStoreApplication) getApplication();
		images = app.getImages();

		// ��ȡͼƬ��position��������һ�������е����item��position
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		currentImageIndex = position;

		// ��ʾͼƬ
		displayImage();
		
		// ������������
		int animationDuration = 1500;
		left2RightIn = AnimationUtils.loadAnimation(this, R.anim.left2right_in);
		left2RightOut = AnimationUtils.loadAnimation(this, R.anim.left2right_out);
		Right2LeftIn = AnimationUtils.loadAnimation(this, R.anim.right2left_in);
		Right2LeftOut = AnimationUtils.loadAnimation(this, R.anim.right2left_out);
		left2RightIn.setDuration(animationDuration);
		left2RightOut.setDuration(animationDuration);
		Right2LeftIn.setDuration(animationDuration);
		Right2LeftOut.setDuration(animationDuration);
	}

	private static final int IMAGE_MAX_SIZE = 480;

	private class ShowImageTask extends AsyncTask<Void, Void, Void> {
		private Image image;

		public ShowImageTask(Image image) {
			super();
			this.image = image;
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (image.getBigBitmap() == null) {
				BitmapFactory.Options opts = new BitmapFactory.Options();
				if (image.getWidth() == 0 || image.getHeight() == 0) {
					opts.inJustDecodeBounds = true;
					BitmapFactory.decodeFile(image.getPath(), opts);
					image.setWidth(opts.outWidth);
					image.setHeight(opts.outHeight);
					opts.inJustDecodeBounds = false;
				}

				int rate = 1;
				if (image.getWidth() > image.getHeight()) {
					rate = image.getHeight() / IMAGE_MAX_SIZE;
				} else {
					rate = image.getWidth() / IMAGE_MAX_SIZE;
				}

				opts.inSampleSize = rate * 2;
				Bitmap bm = BitmapFactory.decodeFile(image.getPath(), opts);
				image.setBigBitmap(bm);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// imageView.setImageBitmap(image.getBigBitmap());
			BitmapDrawable bd = new BitmapDrawable(getResources(),
					image.getBigBitmap());
			isImageSwitcher.setImageDrawable(bd);
		}
	}

	/**
	 * ��ʾͼƬ
	 */
	private void displayImage() {
		Image image = images.get(currentImageIndex);
		ShowImageTask task = new ShowImageTask(image);
		task.execute();
	}

	/**
	 * ��һ��
	 */
	private void previous() {
		currentImageIndex--;
		if (currentImageIndex < 0) {
			currentImageIndex = images.size() - 1;
		}
		isImageSwitcher.setInAnimation(left2RightIn);
		isImageSwitcher.setOutAnimation(left2RightOut);
		displayImage();
	}

	/**
	 * ��һ��
	 */
	private void next() {
		currentImageIndex++;
		if (currentImageIndex >= images.size()) {
			currentImageIndex = 0;
		}
		isImageSwitcher.setInAnimation(Right2LeftIn);
		isImageSwitcher.setOutAnimation(Right2LeftOut);
		displayImage();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_previous:
			previous();
			break;

		case R.id.btn_next:
			next();
			break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			actionDownX = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			if (event.getX() - actionDownX > 80) {
				// left -> right : previous
				previous();
			}
			if (actionDownX - event.getX() > 80) {
				// right -> left : next
				next();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
}
