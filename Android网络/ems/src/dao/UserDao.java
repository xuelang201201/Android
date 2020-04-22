package dao;

import entity.User;

public interface UserDao {
	/***
	 * ͨ����¼�˺Ų�ѯ�û���Ϣ
	 * @param name ��¼�˺�  
	 * @return 
	 *   user!=null  ���ز鵽���û�����
	 *   user==null  ���޴��� 
	 * @throws Exception
	 */
	public User findByLoginname(String name)throws Exception;
	
	/***
	 * �����û���Ϣ
	 * @param user
	 * @throws Exception
	 */
	public void save(User user)throws Exception;
	
}
