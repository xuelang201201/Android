package cn.tedu.music_player_3.dal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import cn.tedu.music_player_3.entity.Music;

public class MediaStoreMusicDao implements IDao<Music> {
	private Context context;
	
	public MediaStoreMusicDao(Context context) {
		super();
		setContext(context);
	}

	public void setContext (Context context) {
		if (context == null) {
			throw new IllegalArgumentException("����Context������Ϊnull������");
		}
		this.context = context;
	}
	
	@Override
	public List<Music> getData() {
		// ׼������ֵ
		List<Music> musics = new ArrayList<Music>();
		
		// ׼��ContentResolver
		ContentResolver cr = context.getContentResolver();
		
		// ׼��Uri
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		
		// ִ�в�ѯ������ȡ���
		String[] projection = {
				"_id", // 0
				"_data", // 1 -> path
				"title", // 2
				"_size", // 3
				"duration", // 4
				"artist", // 5
				"album", // 6
				"album_key" // 7
		};
		
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;
		Cursor c = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		
		// �����������
		if (c != null && c.moveToFirst()) {
			Music music;
			for (; !c.isAfterLast(); c.moveToNext()) {
				music = new Music();
				music.setId(c.getLong(0));
				music.setPath(c.getString(1));
				music.setTitle(c.getString(2));
				music.setSize(c.getInt(3));
				music.setDuration(c.getInt(4));
				music.setArtist(c.getString(5));
				music.setAlbum(c.getString(6));
				music.setAlbumKey(c.getString(7));
				music.setAlbumArt(getAlbumByKey(music.getAlbumKey()));
				musics.add(music);
				Log.d("tedu", "" + music);
			}
		}		
		
		// �ͷ���Դ
		if (c != null && !c.isClosed()) {
			c.close();
			c = null;
		}
		
		// ����
		return musics;
	}

	private String getAlbumByKey(String albumKey) {
		// �жϲ����Ƿ���Ч
		if (albumKey == null) {
			return null;
		}
		
		// ��������ֵ
		String result = null;
		
		// ContentResolver
		ContentResolver cr = context.getContentResolver();
		
		// Uri
		Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
		
		// ִ�в�ѯ
		String[] projection = { "album_art" };
		String selection = "album_key=?";
		String[] selectionArgs = { albumKey };
		String sortOrder = null;
		Cursor c = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		
		// ��ȡ��ѯ���
		if (c != null && c.moveToFirst()) {
			result = c.getString(0);
		}
		
		// �ͷ���Դ
		if (c != null && !c.isClosed()) {
			c.close();
			c = null;
		}
		
		return result;
	}

}
