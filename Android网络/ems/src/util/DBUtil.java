package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static ThreadLocal<Connection> conns
		=new ThreadLocal<Connection>();
	public static String URL="";
	/**
	 *  ��ȥThreadlocal��Ѱ�� ��ǰ�߳��Ƿ��Ѿ�
		������Connection��������У���ֱ�ӷ��ء�
		û�� �����´���
	 */
	public static Connection getConnection() throws Exception{
		Connection conn=conns.get();
		if(conn==null){
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+URL);
			//����ThreadLocal
			conns.set(conn);
		}
		return conn;
	}
	
	/**
	 * �ر����Ӷ���
	 * @throws Exception
	 */
	public static void close()throws Exception{
		Connection conn=conns.get();
		if(conn!=null){
			conn.close();
			//��ThreadLocal�е�connection������null
			conns.set(null);
		}
	}
	/***
	 * �������ݿ�����
	 * @throws Exception
	 */
	public static void openTransaction()throws Exception{
		Connection conn=getConnection();
		conn.setAutoCommit(false);
	}
	
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public static void commit()throws Exception{
		Connection conn=conns.get();
		conn.commit();
	}
	
}


