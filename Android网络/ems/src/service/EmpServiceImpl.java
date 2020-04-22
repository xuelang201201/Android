package service;

import java.util.List;

import util.DBUtil;
import util.DaoFactory;

import dao.EmpDao;
import dao.EmpDaoImpl;

import entity.Emp;

public class EmpServiceImpl implements EmpService{
	private EmpDao dao=(EmpDao)DaoFactory.getInstance("empDao");
	
	@Override
	public Emp findById(int id) throws Exception {
		Emp e=dao.findById(id);
		DBUtil.close();
		return e;
	}

	@Override
	public List<Emp> findAll() throws Exception {
		List<Emp> emps=dao.findAll();
		DBUtil.close();
		return emps;
	}

	@Override
	public List<Emp> findAll(int index, int pagesize) throws Exception {
		List<Emp> emps=dao.findAll(index, pagesize);
		DBUtil.close();
		return emps;
	}
	
	@Override
	public void updateEmp(Emp e) throws Exception {
		DBUtil.openTransaction();
		dao.update(e);
		DBUtil.commit();
		DBUtil.close();
	}

	@Override
	public void removeEmp(int id) throws Exception {
		DBUtil.openTransaction();
		dao.delete(id);
		DBUtil.commit();
		DBUtil.close();
	}

	@Override
	public void addEmp(Emp e) throws Exception {
		DBUtil.openTransaction();
		dao.save(e);
		DBUtil.commit();
		DBUtil.close();
	}
	
}
