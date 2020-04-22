package cn.tedu.dict;

import java.util.List;

/**
 * 访问数据的接口
 * @param <T> 数据的数据类型
 */
public interface IDao<T> {
	/**
	 * 增加数据
	 * @param t 数据对象
	 * @return 数据在数据表中的记录id，如果增加失败，则返回-1
	 */
	long insert(T t);
	
	/**
	 * 删除数据
	 * @param id 被删除的数据id
	 * @return 受影响的行的数量
	 */
	int delete(long id);
	
	/**
	 * 修改数据
	 * @param t 数据对象，该对象中必须包含数据的id
	 * @return 受影响的行的数量
	 */
	int update(T t);
	
	/**
	 * 查询数据
	 * @param whereClause where子句
	 * @param whereArgs where子句中各i？对应的值，如果where子句中没有？，则该参数可以为null
	 * @param orderBy order by子句
	 * @return 数据的List集合
	 */
	List<T> query(String whereClause, String[] whereArgs, String orderBy);
}
