package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Conversation;

public interface IConversationView {
	
	/**
	 * ��view����������Դ
	 * @param cs
	 */
	void setList(List<Conversation> cs);
	
	/**
	 * ��ʾ�б�  �ڵ��ø÷���ǰ  
	 * �������setList()
	 */
	void showList();
}



