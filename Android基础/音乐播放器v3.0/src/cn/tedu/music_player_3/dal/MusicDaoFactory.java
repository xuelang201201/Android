package cn.tedu.music_player_3.dal;

import cn.tedu.music_player_3.entity.Music;

public class MusicDaoFactory {
	/**
	 * 私有构造方法
	 */
	private MusicDaoFactory() {
	}
	
	/**
	 * 获取歌曲信息的类的对象
	 * 
	 * @return 歌曲信息的类的对象
	 */
	public static IDao<Music> newInstance() {
		return new LocalMusicDao();
	}
}
