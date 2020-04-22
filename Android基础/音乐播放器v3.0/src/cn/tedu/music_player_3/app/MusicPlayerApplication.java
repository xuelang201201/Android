package cn.tedu.music_player_3.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import cn.tedu.music_player_3.dal.IDao;
import cn.tedu.music_player_3.dal.MusicDaoFactory;
import cn.tedu.music_player_3.entity.Music;

/**
 * Application，在本例中，用于吧歌曲的数据源提供给Activity、Service
 */
public class MusicPlayerApplication extends Application {
	private List<Music> musics;
	
	@Override
	public void onCreate() {
		IDao<Music> dao = MusicDaoFactory.newInstance();
		List<Music> data = dao.getData();
		setMusics(data);
	}
	
	public void setMusics(List<Music> musics) {
		if (musics == null) {
			musics = new ArrayList<Music>();
		}
		this.musics = musics;
	}
	
	public List<Music> getMusics() {
		return musics;
	}
}
