package cn.tedu.music_player_3.dal;

import android.content.Context;
import cn.tedu.music_player_3.entity.Music;

/**
 * ��ȡ������Ϣ����Ķ���Ĺ���
 */
public class MusicDaoFactory {
	/**
	 * ˽�й��췽��
	 */
	private MusicDaoFactory() {
	}
	
	/**
	 * ��ȡ������Ϣ����Ķ���
	 * @param context
	 * 
	 * @return ������Ϣ����Ķ���
	 */
	public static IDao<Music> newInstance(Context context) {
		return new MediaStoreMusicDao(context);
	}
}
