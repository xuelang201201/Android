package cn.tedu.music_player_2.dal;

import cn.tedu.music_player_2.entity.Music;

/**
 * MusicDao工厂，使用工厂可以减少程序对MusicDao.java类的依赖
 */
public class MusicDaoFactory {
	/**
	 * 获取 IDao<Music> 的实例
	 * @return IDao<Music>的实例
	 */
	public static IDao<Music> newInstance() {
		return new MusicDao();
	}
}
