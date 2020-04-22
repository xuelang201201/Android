package service;

import util.DBUtil;
import util.DaoFactory;
import dao.UserDao;
import entity.User;
import exception.NameAlreadyUseException;
import exception.NameOrPwdErrorException;

public class UserServiceImpl implements UserService{
	private UserDao dao=(UserDao)DaoFactory.getInstance("userDao");
	
	@Override
	public void login(String name, String pwd) throws Exception {
		User user=dao.findByLoginname(name);
		if(user==null || !user.getPassword().equals(pwd)){
			//账号或密码错误
			DBUtil.close();
			throw new NameOrPwdErrorException();
		}
	}
	
	public void regist(User user) throws Exception {
		//查询是否账号已经存在
		User dbUser=dao.findByLoginname(user.getLoginname());
		if(dbUser!=null){
			//如果存在
			DBUtil.close();
			throw new NameAlreadyUseException();
		}
		//如果不存在则继续注册
		dao.save(user);
		DBUtil.close();
	}
}
