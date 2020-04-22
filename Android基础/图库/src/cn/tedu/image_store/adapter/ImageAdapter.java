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
		
		// �жϵ�ǰposition��Ӧ��ImageView�Ƿ��Ѿ�����Task
		ShowImageTask task = tasks.get(holder.image);
		if(task != null) {
			// ԭ������������ȡ�������������
			task.cancel(true);
			task = null;
			tasks.remove(holder.image);
		}
		// ���������µ�����
		task = new ShowImageTask(image, holder.image);
		// �����������ImageView
		tasks.put(holder.image, task);
		// ִ������
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
			// ����Ƿ���ڻ����Bitmap
			if(image.getBitmap() == null) {
				// -- ����ͼƬ�ļ�����ȡBitmap --
				// ��ߵ���С����
				int rate = 8;
				// ����ֵ
				Bitmap bm = null;
				// ����ʱ�Ĳ���
				BitmapFactory.Options opts = new BitmapFactory.Options();
				// ����ģ�����ͼƬ�����Ի�ȡͼƬ�ߴ�
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(image.getPath(), opts);
				int imageWidth = opts.outWidth;
				int imageHeight = opts.outHeight;
				Log.d("tedu", "image width=" + imageWidth + ", height=" + imageHeight);
				// ��ͼƬ��ԭʼ��߷�װ��Image����
				image.setWidth(imageWidth);
				image.setHeight(imageHeight);
				// ������С����
				if(imageWidth > imageHeight) {
					rate = imageHeight / IMAGE_MAX_SIZE;
				} else {
					rate = imageWidth / IMAGE_MAX_SIZE;
				}
				Log.d("tedu", "rate=" + rate);
				// ������С����
				opts.inSampleSize = rate;
				// ȡ��ģ�����ͼƬ
				opts.inJustDecodeBounds = false;
				// ִ�н���ͼƬ�ļ����õ�Bitmap
				bm = BitmapFactory.decodeFile(image.getPath(), opts);
				// �����־
				Log.d("tedu", "bitmap widht=" + bm.getWidth() + ", height=" + bm.getHeight() + ", size=" + (bm.getByteCount() / 1024) + "KB");
				// ��Bitmap����
				image.setBitmap(bm);
			} else {
				 // �Ѿ������������Ҫִ���κ�����
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
