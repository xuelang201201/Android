package com.tarena.myyserver.dao;

import java.util.List;

import com.tarena.myyserver.entity.Page;

public interface PageDao {
	
	public List<Page> findAll()throws Exception;
	
	public Page findById(int id)throws Exception;
	
	public int save(Page page)throws Exception;
	
	
	
}
