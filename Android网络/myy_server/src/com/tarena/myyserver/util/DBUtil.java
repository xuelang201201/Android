package com.tarena.myyserver.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static ThreadLocal<Connection> pool =
		new ThreadLocal<Connection>();
	public static String URL="";
	
	public static Connection getConnection() throws Exception{
		//ȥTheadLocal����һ��  �ж���û��
		Connection conn=pool.get();
		//û��  ����   ��һ��
		if(conn==null){
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+URL);
			//����ThreadLocal
			pool.set(conn);
		}
		//��  ��ȡ
		return conn;
	}
	
	public static void close()throws Exception{
		Connection conn = pool.get();
		if(conn!=null){
			conn.close();
			pool.set(null);
		}
	}
	
	//��������
	public static void openTransaction()throws Exception{
		Connection conn=getConnection();
		conn.setAutoCommit(false);
	}
	
	//�ύ����
	public static void commit()throws Exception{
		Connection conn=pool.get();
		conn.commit();
	}
}
