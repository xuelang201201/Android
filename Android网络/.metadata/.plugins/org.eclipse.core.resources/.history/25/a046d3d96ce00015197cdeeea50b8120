package service;

import java.util.List;

import entity.Emp;

/**处理员工业务*/
public interface EmpService {
	/**
	 * 根据id查询员工业务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Emp findById(int id)throws Exception;
	
	/**
	 * 查询所有员工信息业务
	 * @return
	 * @throws Exception
	 */
	public List<Emp> findAll()throws Exception;
	
	public List<Emp> findAll(int index, int pagesize)throws Exception;
	
	/**
	 * 更新员工信息业务
	 * @param e
	 * @throws Exception
	 */
	public void updateEmp(Emp e)throws Exception;
	
	/**
	 * 移除员工业务
	 * @param id
	 * @throws Exception
	 */
	public void removeEmp(int id)throws Exception;
	
	/**
	 * 添加员工业务
	 * @param e 
	 * @throws Exception
	 */
	public void addEmp(Emp e) throws Exception;
	
}
