package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Sms;

public interface ISmsView {
	/**
	 * ��������Դ
	 * @param smss
	 */
	void setList(List<Sms> smss);
	
	/**
	 * ��ʾ�б�   setList���������
	 */
	void showList();
}
