package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;
import entity.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User findByLoginname(String name) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="select * from user where loginname=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, name);
		ResultSet res=stmt.executeQuery();
		User user=null;
		if(res.next()){
			user=new User();
			user.setId(res.getInt("id"));
			user.setEmail(res.getString("email"));
			user.setLoginname(res.getString("loginname"));
			user.setRealname(res.getString("realname"));
			user.setPassword(res.getString("password"));
		}
		return user;
	}

	@Override
	public void save(User user) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="insert into user (loginname, password, realname, email) values (?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, user.getLoginname());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getRealname());
		stmt.setString(4, user.getEmail());
		stmt.executeUpdate();
	}
}
