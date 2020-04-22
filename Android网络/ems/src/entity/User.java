package entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 768023542346L;
	
	private int id;
	private String loginname;
	private String password;
	private String realname;
	private String email;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String loginname, String password, String realname,
			String email) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.realname = realname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
