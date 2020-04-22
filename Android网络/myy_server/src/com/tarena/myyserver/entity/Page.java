package com.tarena.myyserver.entity;

import java.io.Serializable;

public class Page implements Serializable {

	private int id;
	private String name;
	private String content;
	private long time;

	public Page() {
		// TODO Auto-generated constructor stub
	}

	public Page(int id, String name, String content, long time) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
