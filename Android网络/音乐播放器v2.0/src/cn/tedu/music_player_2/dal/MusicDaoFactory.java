package cn.tedu.music_player_2.dal;

import cn.tedu.music_player_2.entity.Music;

/**
 * MusicDao������ʹ�ù������Լ��ٳ����MusicDao.java�������
 */
public class MusicDaoFactory {
	/**
	 * ��ȡ IDao<Music> ��ʵ��
	 * @return IDao<Music>��ʵ��
	 */
	public static IDao<Music> newInstance() {
		return new MusicDao();
	}
}
