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
		//����ҵ���  ��ѯ��ϵ���б�
		model.loadContacts(new Callback() {
			public void onResponse(List<Contact> contacts) {
				//ҵ���Ѿ����  ��ȡҵ��ִ�е�
				//��� Ȼ�����view�ķ��� ����UI
				view.showContactList(contacts);
			}
		});
	}
	
}

