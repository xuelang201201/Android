package cn.tedu.musicclient.dal;

import cn.tedu.musicclient.entity.Music;

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
