package dao;

import java.util.List;

import entity.Emp;

public interface EmpDao {
	/***
	 * 根据员工的id  更新该员工的其他数据 
	 * @param e
	 * @throws Exception
	 */
	public void update(Emp e)throws Exception;
	
	/***
	 * 通过id  查询相应的员工数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Emp findById(int id) throws Exception;
	
	/**
	 * 查询所有的员工信息 
	 * @return  员工对象组成的集合
	 * @throws Exception
	 */
	public List<Emp> findAll() throws Exception; 
	
	/**
	 * 删除员工信息
	 * @param id  待删除员工的id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 将员工信息插入数据库  
	 * @param e 待添加的员工对象
	 * @throws Exception
	 */
	public void save(Emp e) throws Exception;

	public List<Emp> findAll(int index, int pagesize)throws Exception;

}
