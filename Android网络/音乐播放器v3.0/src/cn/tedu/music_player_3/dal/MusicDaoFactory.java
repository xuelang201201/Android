package cn.tedu.music_player_3.dal;

import android.content.Context;
import cn.tedu.music_player_3.entity.Music;

/**
 * 获取歌曲信息的类的对象的工厂
 */
public class MusicDaoFactory {
	/**
	 * 私有构造方法
	 */
	private MusicDaoFactory() {
	}
	
	/**
	 * 获取歌曲信息的类的对象
	 * @param context
	 * 
	 * @return 歌曲信息的类的对象
	 */
	public static IDao<Music> newInstance(Context context) {
		return new MediaStoreMusicDao(context);
	}
}
