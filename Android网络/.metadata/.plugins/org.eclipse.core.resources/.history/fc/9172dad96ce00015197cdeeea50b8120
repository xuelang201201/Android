package dao;

import entity.User;

public interface UserDao {
	/***
	 * 通过登录账号查询用户信息
	 * @param name 登录账号  
	 * @return 
	 *   user!=null  返回查到的用户对象
	 *   user==null  查无此人 
	 * @throws Exception
	 */
	public User findByLoginname(String name)throws Exception;
	
	/***
	 * 保存用户信息
	 * @param user
	 * @throws Exception
	 */
	public void save(User user)throws Exception;
	
}
