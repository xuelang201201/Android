package service;

import entity.User;
import exception.NameAlreadyUseException;
import exception.NameOrPwdErrorException;

public interface UserService {
	/**
	 * 登录业务
	 * @param name  账号
	 * @param pwd	   密码
	 * @throws Exception  系统异常
	 * @throws NameOrPwdErrorException 账号密码输入错误
	 */
	public void login(String name, String pwd)throws Exception;
	
	/**
	 * 注册业务 
	 * @param user  待注册的用户信息
	 * @throws Exception	系统异常
	 * @throws NameAlreadyUseException	账号已存在
	 */
	public void regist(User user)throws Exception;
	
}
