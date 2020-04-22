package cn.tedu.musicclient.app;

import java.util.List;

import android.app.Application;
import cn.tedu.musicclient.dal.IDao;
import cn.tedu.musicclient.dal.MusicDaoFactory;
import cn.tedu.musicclient.entity.Music;

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
