package cn.tedu.music_player_2.dal;

import java.util.List;

/**
 * ���ݷ��ʽӿ�
 * 
 * @param <T> ���ʵ����ݵ�����
 */
public interface IDao<T> {
	
	/**
	 * ��ȡ����
	 * @return ���ݵ�List����
	 */
	List<T> getData();
}
