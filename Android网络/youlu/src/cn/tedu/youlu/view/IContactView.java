package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Contact;

/**
 * 联系人界面相关的 View层接口
 */
public interface IContactView {
	/**
	 * 显示联系人列表  
	 * 传递已经获取到的联系人列表数据 
	 */
	void showContactList(List<Contact> contacts);
	
}



