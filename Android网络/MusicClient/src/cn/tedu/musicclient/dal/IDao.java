package cn.tedu.musicclient.dal;

import java.util.List;

/**
 * 数据访问接口
 * 
 * @param <T> 访问的数据的类型
 */
public interface IDao<T> {
	
	/**
	 * 获取数据
	 * @return 数据的List集合
	 */
	List<T> getData();
}
