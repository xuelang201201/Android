package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;

/**
 * ͨ����¼���ҵ��ӿ�
 */
public interface ICalllogModel {
	/**
	 * ��ѯ���е�ͨ����¼
	 * @return
	 */
	List<Calllog>  findAllCalllogs();
}
