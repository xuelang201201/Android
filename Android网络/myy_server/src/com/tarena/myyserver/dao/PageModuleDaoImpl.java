package com.tarena.myyserver.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tarena.myyserver.entity.PageModule;
import com.tarena.myyserver.util.DBUtil;

public class PageModuleDaoImpl implements PageModuleDao {

	@Override
	public List<PageModule> findAll() throws Exception {
		String sql="select * from page_module";
		Connection conn=DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet res=stmt.executeQuery(sql);
		List<PageModule> modules=new ArrayList<PageModule>();
		while(res.next()){
			PageModule m=new PageModule();
			m.setId(res.getInt("id"));
			m.setName(res.getString("name"));
			m.setSnapshot(res.getString("snapshot"));
			m.setContent(res.getString("content"));
			modules.add(m);
		}
		DBUtil.close();
		return modules;
	}

}
