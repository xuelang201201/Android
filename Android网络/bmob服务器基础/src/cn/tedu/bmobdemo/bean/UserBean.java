package cn.tedu.bmobdemo.bean;

import cn.bmob.v3.BmobObject;

public class UserBean extends BmobObject {

	String username;
	String password;
	boolean gender; // true ÄÐ   false Å®
	String url; // Í·ÏñµØÖ·

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
