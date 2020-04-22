package cn.tedu.music_player_3.dal;

import cn.tedu.music_player_3.entity.Music;

public class MusicDaoFactory {
	/**
	 * ˽�й��췽��
	 */
	private MusicDaoFactory() {
	}
	
	/**
	 * ��ȡ������Ϣ����Ķ���
	 * 
	 * @return ������Ϣ����Ķ���
	 */
	public static IDao<Music> newInstance() {
		return new LocalMusicDao();
	}
}
