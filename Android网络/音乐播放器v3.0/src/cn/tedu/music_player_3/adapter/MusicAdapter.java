package cn.tedu.music_player_3.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.tedu.music_player_3.R;
import cn.tedu.music_player_3.entity.Music;
import cn.tedu.music_player_3.util.CommonUtils;

public class MusicAdapter extends BaseAdapter<Music> {

	public MusicAdapter(Context context, List<Music> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ��ȡ��Ҫ����ʾ������
		Music music = getData().get(position);
		
		// ����ģ�壬׼���������ݵĿؼ�
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.music_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.tv_music_item_title);
			holder.duration = (TextView) convertView.findViewById(R.id.tv_music_item_duration);
			holder.artist = (TextView) convertView.findViewById(R.id.tv_music_item_artist);
			holder.album = (TextView) convertView.findViewById(R.id.tv_music_item_album);
			holder.image = (ImageView) convertView.findViewById(R.id.iv_music_item_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// ��ؼ����������
		holder.title.setText(music.getTitle());
		holder.artist.setText(music.getArtist());
		holder.album.setText(music.getAlbum());
		holder.duration.setText(CommonUtils.getFormattedTime(music.getDuration()));
		
		// ��ʾͼƬ
		holder.image.setImageResource(R.drawable.ic_launcher);
		if(music.getAlbumArt() != null) {
			Bitmap bm = BitmapFactory.decodeFile(music.getAlbumArt());
			if(bm != null) {
				holder.image.setImageBitmap(bm);
			}
		}
		
		// ����
		return convertView;
	}
	
	class ViewHolder {
		TextView title, duration, artist, album;
		ImageView image;
	}

}
