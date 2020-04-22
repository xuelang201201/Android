package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.entity.Sms;

public interface ISmsModel {
	
	/**
	 * 通过threadId 查询所有短消息
	 * @param threadId
	 * @return
	 */
	List<Sms> findSmsByThreadId(int threadId);
	
	/**
	 * 加载所有会话数据 
	 * @return
	 */
	List<Conversation> loadAllConversations();
	
}
