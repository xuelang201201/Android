package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Contact;

/**
 * ��ϵ�˽�����ص� View��ӿ�
 */
public interface IContactView {
	/**
	 * ��ʾ��ϵ���б�  
	 * �����Ѿ���ȡ������ϵ���б����� 
	 */
	void showContactList(List<Contact> contacts);
	
}



