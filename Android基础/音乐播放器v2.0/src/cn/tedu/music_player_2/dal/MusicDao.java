package cn.tedu.music_player_2.dal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Environment;
import cn.tedu.music_player_2.entity.Music;

/**
 * Music数据访问对象
 */
class MusicDao implements IDao<Music> {

	@Override
	public List<Music> getData() {
		List<Music> musics = new ArrayList<Music>();
		
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			if (musicDir.exists()) {
				File[] files = musicDir.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						if (files[i].getName().toLowerCase(Locale.CHINA).endsWith(".mp3")) {
							Music music = new Music();
							music.setPath(files[i].getAbsolutePath());
							music.setTitle(files[i].getName().substring(0, files[i].getName().length() - 4));
							musics.add(music);
						}
					}
				}
			}
		}
		return musics;
	}
	
}
