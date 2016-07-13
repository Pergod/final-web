package com.netease.java.bean;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class Product {
	
	private int id;
	private String title;
	private String image;
	private String summary;
	private String detail;
	private Boolean isBuy;
	private Boolean isSell;
	private String buyTime;
	
	private BigInteger price;
	private BigInteger buyPrice;
	private int buyNum;
	private int saleNum;
	
	private int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Boolean getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}

	public Boolean getIsSell() {
		return isSell;
	}

	public void setIsSell(Boolean isSell) {
		this.isSell = isSell;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public BigInteger getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigInteger buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
