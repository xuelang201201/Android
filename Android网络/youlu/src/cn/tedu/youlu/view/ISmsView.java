package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Sms;

public interface ISmsView {
	/**
	 * 设置数据源
	 * @param smss
	 */
	void setList(List<Sms> smss);
	
	/**
	 * 显示列表   setList方法后调用
	 */
	void showList();
}
