package cn.tedu.musicclient.dal;

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
