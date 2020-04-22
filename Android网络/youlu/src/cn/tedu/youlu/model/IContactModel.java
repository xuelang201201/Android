package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Contact;

/**
 * ��ϵ�˵�ҵ��ӿ�
 */
public interface IContactModel {
	
	/**
	 * ��ѯ��ϵ�����ݿ⣬��ȡ������ϵ�� 
	 * ���ҷ�װ��List<Contact>  ִ�лص�����
	 */
	void loadContacts(Callback callback);
	
	
	/** �����ص��ӿ� */
	public static interface Callback{
		void onResponse(List<Contact> contact);
	}
}
