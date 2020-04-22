package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.entity.Sms;

public interface ISmsModel {
	
	/**
	 * ͨ��threadId ��ѯ���ж���Ϣ
	 * @param threadId
	 * @return
	 */
	List<Sms> findSmsByThreadId(int threadId);
	
	/**
	 * �������лỰ���� 
	 * @return
	 */
	List<Conversation> loadAllConversations();
	
}
