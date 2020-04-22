package cn.tedu.musicclient.app;

import java.util.List;

import android.app.Application;
import cn.tedu.musicclient.dal.IDao;
import cn.tedu.musicclient.dal.MusicDaoFactory;
import cn.tedu.musicclient.entity.Music;

public class MusicPlayerApplication extends Application {
	/**
	 * 歌曲的数据源
	 */
	private List<Music> musics;
	
	@Override
	public void onCreate() {
		// 准备数据源
		IDao<Music> dao = MusicDaoFactory.newInstance();
		musics = dao.getData();
	}
	
	public List<Music> getMusics() {
		return musics;
	}
}
