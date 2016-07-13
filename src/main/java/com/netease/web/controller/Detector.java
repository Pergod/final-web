package com.netease.web.controller;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Person;
import com.netease.java.bean.Product;
import com.netease.java.bean.Transaction;
import com.netease.mybatis.dao.ProductsDao;
import com.netease.mybatis.dao.TransactionDao;
import com.netease.mybatis.dao.UserDao;

public class Detector {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(constVar.applicationContext);
	
	public static int getUserType(String userName, String password) {
		UserDao dao = context.getBean(constVar.UserDao, UserDao.class);
		if (dao.getUserType(userName, password)!=null) {
			if (dao.getUserType(userName, password).equals("0")) {
				return 0;
			} else if (dao.getUserType(userName, password).equals("1")) {
				return 1;
			}
		}
		return -1;
	}
	
	public static int getUserId(String userName) {
		UserDao dao=context.getBean(constVar.UserDao, UserDao.class);
		if (dao.getId(userName)!=null) {
			return Integer.valueOf(dao.getId(userName));
		}
		return -1;
	}
	
	public static BigInteger getProductPrice(Product product) {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		BigInteger ProductPrice=dao.getProductPrice(product);
		return ProductPrice;
	}
	
	public static Boolean publicProduct(Product product) {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		if (dao.PublicProduct(product)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static List<Product> getProductList() {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		List<Product> products=dao.getProducts();
		return products;
	}
	
	public static Product getProduct(int id) {
		ProductsDao dao=context.getBean(constVar.ProductsDao,ProductsDao.class);
		return dao.getProduct(id);
	}
	
	public static Boolean updateProduct(Product product) {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		if (dao.UpdateProduct(product)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean deleteProduct(int id) {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		if (dao.DeleteProduct(id)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean buyProduct(Transaction trx) {
		TransactionDao dao=context.getBean(constVar.TransactionDao, TransactionDao.class);
		if (dao.BuyProduct(trx)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static List<Product> getBuyList(Person person) {
		UserDao dao=context.getBean(constVar.UserDao, UserDao.class);
		List<Product> buyList=dao.getBuyList(person);
		return buyList;
	}
	
	public static String getbuyTime() {
		UserDao dao=context.getBean(constVar.UserDao, UserDao.class);
		return dao.NOW();
	}
	
	public static Boolean getProductCondition(int productId) {
		TransactionDao dao=context.getBean(constVar.TransactionDao, TransactionDao.class);
		if (dao.getTrxId(productId)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static List<Product> getNotBuyProductList() {
		ProductsDao dao=context.getBean(constVar.ProductsDao, ProductsDao.class);
		List<Product> notBuyProductList=dao.GetNotBuyProductList();
		return notBuyProductList;
	}
	
	public static Product getBuyItem(int productId) {
		TransactionDao dao=context.getBean(constVar.TransactionDao, TransactionDao.class);
		Product product=dao.getBuyItem(productId);
		return product;
	}
}
