package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;

/**
 * ���Ž���view��ӿ�
 */
public interface IDialView {
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
