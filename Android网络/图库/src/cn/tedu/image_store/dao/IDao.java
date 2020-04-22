package cn.tedu.image_store.dao;

import java.util.List;

public interface IDao<T> {
	
	List<T> query();
	
}
