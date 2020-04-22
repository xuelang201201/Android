package service;

import java.util.List;

import entity.Emp;

/**����Ա��ҵ��*/
public interface EmpService {
	/**
	 * ����id��ѯԱ��ҵ��
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Emp findById(int id)throws Exception;
	
	/**
	 * ��ѯ����Ա����Ϣҵ��
	 * @return
	 * @throws Exception
	 */
	public List<Emp> findAll()throws Exception;
	
	public List<Emp> findAll(int index, int pagesize)throws Exception;
	
	/**
	 * ����Ա����Ϣҵ��
	 * @param e
	 * @throws Exception
	 */
	public void updateEmp(Emp e)throws Exception;
	
	/**
	 * �Ƴ�Ա��ҵ��
	 * @param id
	 * @throws Exception
	 */
	public void removeEmp(int id)throws Exception;
	
	/**
	 * ���Ա��ҵ��
	 * @param e 
	 * @throws Exception
	 */
	public void addEmp(Emp e) throws Exception;
	
}
