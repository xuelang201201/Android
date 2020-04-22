package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Conversation;

public interface IConversationView {
	
	/**
	 * 给view层设置数据源
	 * @param cs
	 */
	void setList(List<Conversation> cs);
	
	/**
	 * 显示列表  在调用该方法前  
	 * 必须调用setList()
	 */
	void showList();
}



