package dao;

import java.util.List;

import entity.Emp;

public interface EmpDao {
	/***
	 * ����Ա����id  ���¸�Ա������������ 
	 * @param e
	 * @throws Exception
	 */
	public void update(Emp e)throws Exception;
	
	/***
	 * ͨ��id  ��ѯ��Ӧ��Ա������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Emp findById(int id) throws Exception;
	
	/**
	 * ��ѯ���е�Ա����Ϣ 
	 * @return  Ա��������ɵļ���
	 * @throws Exception
	 */
	public List<Emp> findAll() throws Exception; 
	
	/**
	 * ɾ��Ա����Ϣ
	 * @param id  ��ɾ��Ա����id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * ��Ա����Ϣ�������ݿ�  
	 * @param e ����ӵ�Ա������
	 * @throws Exception
	 */
	public void save(Emp e) throws Exception;

	public List<Emp> findAll(int index, int pagesize)throws Exception;

}
