package service;

import entity.User;
import exception.NameAlreadyUseException;
import exception.NameOrPwdErrorException;

public interface UserService {
	/**
	 * ��¼ҵ��
	 * @param name  �˺�
	 * @param pwd	   ����
	 * @throws Exception  ϵͳ�쳣
	 * @throws NameOrPwdErrorException �˺������������
	 */
	public void login(String name, String pwd)throws Exception;
	
	/**
	 * ע��ҵ�� 
	 * @param user  ��ע����û���Ϣ
	 * @throws Exception	ϵͳ�쳣
	 * @throws NameAlreadyUseException	�˺��Ѵ���
	 */
	public void regist(User user)throws Exception;
	
}
