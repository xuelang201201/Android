package com.charles.coolmusic.dal;

import java.util.List;

/**
 * 获取数据的接口
 * @param <T> 数据的类型
 */
public interface IDao<T> {
    /**
     * 获取数据
     * @return 数据的List集合，返回值永不为null
     */
    List<T> getData();
}
