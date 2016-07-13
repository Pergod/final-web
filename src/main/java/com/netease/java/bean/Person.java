package com.netease.java.bean;

import java.util.List;

public class Person {
	
	private int id;
	private String username;
	private String password;
	private String nickname;
	private int usertype=-1;
	private List<Product> buyList;
	
	public Person(int id, String username, String password, String nickname, int usertype) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.usertype = usertype;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public List<Product> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<Product> buyList) {
		this.buyList = buyList;
	}
}
