package com.tarena.myyserver.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static ThreadLocal<Connection> pool =
		new ThreadLocal<Connection>();
	public static String URL="";
	
	public static Connection getConnection() throws Exception{
		//去TheadLocal中拿一次  判断有没有
		Connection conn=pool.get();
		//没有  创建   存一次
		if(conn==null){
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+URL);
			//存入ThreadLocal
			pool.set(conn);
		}
		//有  获取
		return conn;
	}
	
	public static void close()throws Exception{
		Connection conn = pool.get();
		if(conn!=null){
			conn.close();
			pool.set(null);
		}
	}
	
	//开启事务
	public static void openTransaction()throws Exception{
		Connection conn=getConnection();
		conn.setAutoCommit(false);
	}
	
	//提交事务
	public static void commit()throws Exception{
		Connection conn=pool.get();
		conn.commit();
	}
}
