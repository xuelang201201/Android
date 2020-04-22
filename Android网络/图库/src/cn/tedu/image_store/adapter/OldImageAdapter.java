package cn.tedu.image_store.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.tedu.image_store.R;
import cn.tedu.image_store.entity.Image;

public class OldImageAdapter extends BaseAdapter<Image>{

	public OldImageAdapter(Context context, List<Image> data) {
		super(context, data);
	}
	
	private int IMAGE_MAX_SIZE = 135;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.image_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.iv_image_item_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Image image = getData().get(position);
		
		holder.image.setImageResource(R.drawable.ic_launcher);
		
		DecodeFileThread thread = new DecodeFileThread(image, holder.image);
		thread.start();

		// if(bitmaps.size() <= position || bitmaps.get(position) == null) {
		// // bitmaps.add(BitmapFactory.decodeFile(image.getPath(), opts));
		// Log.d("tedu", "decode 第" + (position + 1) + "张图片.");
		// }
		// holder.image.setImageBitmap(bitmaps.get(position));

		return convertView;
	}

	private class DecodeFileThread extends Thread {
		private Image image;
		private ImageView imageView;

		public DecodeFileThread(Image image, ImageView imageView) {
			this.image = image;
			this.imageView = imageView;
		}

		@Override
		public void run() {
			if (image.getBitmap() == null) {
				BitmapFactory.Options opts = new BitmapFactory.Options();
				if (image.getWidth() > 135 || image.getHeight() > 135) {
					if (image.getWidth() > image.getHeight()) {
						opts.inSampleSize = image.getHeight() / IMAGE_MAX_SIZE;
					} else {
						opts.inSampleSize = image.getWidth() / IMAGE_MAX_SIZE;
					}
				} else {
					opts.inSampleSize = 1;
				}
				Bitmap bm = BitmapFactory.decodeFile(image.getPath(), opts);
				image.setBitmap(bm);
			}
			imageView.post(new Runner());
		}

		private class Runner implements Runnable {
			@Override
			public void run() {
				imageView.setImageBitmap(image.getBitmap());
			}
		}
	}

	// private List<Bitmap> bitmaps = new ArrayList<Bitmap>();

	class ViewHolder {
		ImageView image;
	}

}
