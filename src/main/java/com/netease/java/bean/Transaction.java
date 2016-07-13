package com.netease.java.bean;

import java.math.BigInteger;
import java.sql.Time;

public class Transaction {
	
	private int id;
	private int personId;
	private int contentId;
	private BigInteger buyPrice;
	private String buyTime;
	private int buyNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public BigInteger getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigInteger buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

}
