package cn.tedu.music_player_2.app;

import java.util.List;

import android.app.Application;
import cn.tedu.music_player_2.dal.IDao;
import cn.tedu.music_player_2.dal.MusicDaoFactory;
import cn.tedu.music_player_2.entity.Music;

public class MusicPlayerApplication extends Application {
	/**
	 * ����������Դ
	 */
	private List<Music> musics;
	
	@Override
	public void onCreate() {
		// ׼������Դ
		IDao<Music> dao = MusicDaoFactory.newInstance();
		musics = dao.getData();
	}
	
	public List<Music> getMusics() {
		return musics;
	}
}
