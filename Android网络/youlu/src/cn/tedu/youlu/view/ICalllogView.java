package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;

/**
 * ͨ����¼����view��ӿ�
 */
public interface ICalllogView {
	/**
	 * ����Calllog���ݼ���
	 * @param logs
	 */
	void setCalllogList(List<Calllog> logs);
	
	/**
	 * ��ʾlistView
	 */
	void showList();
}
