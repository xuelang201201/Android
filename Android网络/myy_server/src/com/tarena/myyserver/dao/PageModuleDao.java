package com.tarena.myyserver.dao;

import java.util.List;

import com.tarena.myyserver.entity.PageModule;

public interface PageModuleDao {
	
	public List<PageModule> findAll()throws Exception;
	
}
