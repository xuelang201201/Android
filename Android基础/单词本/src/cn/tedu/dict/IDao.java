package cn.tedu.dict;

import java.util.List;

/**
 * �������ݵĽӿ�
 * @param <T> ���ݵ���������
 */
public interface IDao<T> {
	/**
	 * ��������
	 * @param t ���ݶ���
	 * @return ���������ݱ��еļ�¼id���������ʧ�ܣ��򷵻�-1
	 */
	long insert(T t);
	
	/**
	 * ɾ������
	 * @param id ��ɾ��������id
	 * @return ��Ӱ����е�����
	 */
	int delete(long id);
	
	/**
	 * �޸�����
	 * @param t ���ݶ��󣬸ö����б���������ݵ�id
	 * @return ��Ӱ����е�����
	 */
	int update(T t);
	
	/**
	 * ��ѯ����
	 * @param whereClause where�Ӿ�
	 * @param whereArgs where�Ӿ��и�i����Ӧ��ֵ�����where�Ӿ���û�У�����ò�������Ϊnull
	 * @param orderBy order by�Ӿ�
	 * @return ���ݵ�List����
	 */
	List<T> query(String whereClause, String[] whereArgs, String orderBy);
}
