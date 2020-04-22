package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.model.IContactModel;
import cn.tedu.youlu.model.IContactModel.Callback;
import cn.tedu.youlu.model.impl.ContactModel;
import cn.tedu.youlu.presenter.IContactPresenter;
import cn.tedu.youlu.view.IContactView;

public class ContactPresenter implements IContactPresenter{
	private IContactModel model;
	private IContactView view;
	
	public ContactPresenter(IContactView view) {
		model = new ContactModel();
		this.view = view;
	}
	
	@Override
	public void loadAllContacts() {
		//调用业务层  查询联系人列表
		model.loadContacts(new Callback() {
			public void onResponse(List<Contact> contacts) {
				//业务已经完成  获取业务执行的
				//结果 然后调用view的方法 更新UI
				view.showContactList(contacts);
			}
		});
	}
	
}

