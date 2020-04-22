package cn.tedu.image_store.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.tedu.image_store.R;
import cn.tedu.image_store.entity.Image;

public class ImageAdapter extends BaseAdapter<Image> {

	public ImageAdapter(Context context, List<Image> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater()
					.inflate(R.layout.image_item, null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.iv_image_item_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.image.setImageResource(R.drawable.ic_launcher);

		Image image = getData().get(position);
		
		// 判断当前position对应的ImageView是否已经存在Task
		ShowImageTask task = tasks.get(holder.image);
		if(task != null) {
			// 原本存在任务，则取消并且清除任务
			task.cancel(true);
			task = null;
			tasks.remove(holder.image);
		}
		// 创建本次新的任务
		task = new ShowImageTask(image, holder.image);
		// 将任务关联到ImageView
		tasks.put(holder.image, task);
		// 执行任务
		task.execute();

		return convertView;
	}
	
	private Map<ImageView, ShowImageTask> tasks = new HashMap<ImageView, ImageAdapter.ShowImageTask>();
	
	private static final int IMAGE_MAX_SIZE = 130;

	private class ShowImageTask extends AsyncTask<Void, Void, Void> {
		private Image image;
		private ImageView imageView;

		public ShowImageTask(Image image, ImageView imageView) {
			super();
			this.image = image;
			this.imageView = imageView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			Log.i("tedu", "--- doInBackground() ---");
			// 检查是否存在缓存的Bitmap
			if(image.getBitmap() == null) {
				// -- 解码图片文件，获取Bitmap --
				// 宽高的缩小比例
				int rate = 8;
				// 返回值
				Bitmap bm = null;
				// 解码时的参数
				BitmapFactory.Options opts = new BitmapFactory.Options();
				// 设置模拟解码图片，尝试获取图片尺寸
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(image.getPath(), opts);
				int imageWidth = opts.outWidth;
				int imageHeight = opts.outHeight;
				Log.d("tedu", "image width=" + imageWidth + ", height=" + imageHeight);
				// 把图片的原始宽高封装到Image对象
				image.setWidth(imageWidth);
				image.setHeight(imageHeight);
				// 计算缩小比例
				if(imageWidth > imageHeight) {
					rate = imageHeight / IMAGE_MAX_SIZE;
				} else {
					rate = imageWidth / IMAGE_MAX_SIZE;
				}
				Log.d("tedu", "rate=" + rate);
				// 设置缩小比例
				opts.inSampleSize = rate;
				// 取消模拟解码图片
				opts.inJustDecodeBounds = false;
				// 执行解码图片文件，得到Bitmap
				bm = BitmapFactory.decodeFile(image.getPath(), opts);
				// 输出日志
				Log.d("tedu", "bitmap widht=" + bm.getWidth() + ", height=" + bm.getHeight() + ", size=" + (bm.getByteCount() / 1024) + "KB");
				// 将Bitmap缓存
				image.setBitmap(bm);
			} else {
				 // 已经缓存过，则不需要执行任何任务
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			imageView.setImageBitmap(image.getBitmap());
		}

	}

	class ViewHolder {
		ImageView image;
	}

}
