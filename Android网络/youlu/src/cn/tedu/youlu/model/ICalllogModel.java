package cn.tedu.youlu.model;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;

/**
 * 通话记录相关业务接口
 */
public interface ICalllogModel {
	/**
	 * 查询所有的通话记录
	 * @return
	 */
	List<Calllog>  findAllCalllogs();
}
