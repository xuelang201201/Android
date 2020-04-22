package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Contact;

/**
 * 联系人的业务接口
 */
public interface IContactModel {
	
	/**
	 * 查询联系人数据库，获取所有联系人 
	 * 并且封装成List<Contact>  执行回调方法
	 */
	void loadContacts(Callback callback);
	
	
	/** 声明回调接口 */
	public static interface Callback{
		void onResponse(List<Contact> contact);
	}
}
