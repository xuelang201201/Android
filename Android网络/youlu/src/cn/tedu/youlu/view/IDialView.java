package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;

/**
 * 拨号界面view层接口
 */
public interface IDialView {
	/**
	 * 设置Calllog数据集合
	 * @param logs
	 */
	void setCalllogList(List<Calllog> logs);
	
	/**
	 * 显示listView
	 */
	void showList();
}
