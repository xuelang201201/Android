package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.model.ISmsModel;
import cn.tedu.youlu.model.impl.SmsModel;
import cn.tedu.youlu.presenter.IConversationPresenter;
import cn.tedu.youlu.view.IConversationView;

public class ConversationPresenter implements IConversationPresenter {
	private ISmsModel model;
	private IConversationView view;
	
	public ConversationPresenter(IConversationView view) {
		this.view = view;
		model = new SmsModel();
	}
	
	@Override
	public void loadConversation() {
		List<Conversation> cs =model.loadAllConversations();
		view.setList(cs);
		view.showList();
	}

}
