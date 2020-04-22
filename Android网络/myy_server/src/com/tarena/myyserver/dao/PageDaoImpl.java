
package com.tarena.myyserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tarena.myyserver.entity.Page;
import com.tarena.myyserver.util.DBUtil;

public class PageDaoImpl implements PageDao {

	
	@Override
	public Page findById(int id) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="select * from page where id="+id;
		Statement stmt=conn.createStatement();
		ResultSet res=stmt.executeQuery(sql);
		Page p=null;
		if(res.next()){
			p=new Page();
			p.setId(res.getInt("id"));
			p.setName(res.getString("name"));
			p.setContent(res.getString("content"));
			p.setTime(res.getLong("time"));
		}
		DBUtil.close();
		return p;
	}
	
	@Override
	public List<Page> findAll() throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="select * from page";
		Statement stmt=conn.createStatement();
		ResultSet res=stmt.executeQuery(sql);
		List<Page> pages=new ArrayList<Page>();
		while(res.next()){
			Page p=new Page();
			p.setId(res.getInt("id"));
			p.setName(res.getString("name"));
			p.setContent(res.getString("content"));
			p.setTime(res.getLong("time"));
			pages.add(p);
		}
		DBUtil.close();
		return pages;
	}

	@Override
	public int save(Page page) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="insert into page (name, content, time) values (?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if(page.getName()==null){
			stmt.setString(1, "我的美一页");
		}else{
			stmt.setString(1, page.getName());
		}
		stmt.setString(2, page.getContent());
		stmt.setLong(3, System.currentTimeMillis());
		stmt.executeUpdate();
		ResultSet res=stmt.getGeneratedKeys();
		res.next();
		int id=res.getInt(1);
		DBUtil.close();
		return id;
	}

}







