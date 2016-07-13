package com.netease.test;

import java.math.BigInteger;
import java.sql.Time;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Person;
import com.netease.java.bean.Product;
import com.netease.java.bean.Transaction;
import com.netease.mybatis.dao.ProductsDao;
import com.netease.mybatis.dao.TransactionDao;
import com.netease.mybatis.dao.UserDao;

public class TestDao {
	private static ApplicationContext context=new ClassPathXmlApplicationContext(constVar.applicationContext);

	public static void main(String[] args) {
		
		UserDao userDao=context.getBean(constVar.UserDao, UserDao.class);
//		ProductsDao productsDao=context.getBean(constVar.ProductsDao, ProductsDao.class);
//		
//		List<Product> products=productsDao.getProducts();
//		for (Product product : products) {
//			System.out.println("id:"+product.getId());
//			System.out.println("价格:"+product.getPrice());
//			System.out.println("标题:"+product.getTitle());
//			System.out.println("图片Url:"+product.getImage());
//			System.out.println("摘要:"+product.getSummary());
//			System.out.println("详情:"+product.getDetail());
//			System.out.println("=============================");
//		}
//		TransactionDao transactionDao=context.getBean(constVar.TransactionDao, TransactionDao.class);
//		List<Transaction> transactions=transactionDao.getTransactionMsg();
//		for (Transaction transaction : transactions) {
//			System.out.println("id:"+transaction.getId());
//			System.out.println("Person Id:"+transaction.getPersonId());
//			System.out.println("Product Id:"+transaction.getProductId());
//			System.out.println("Price:"+transaction.getPrice());
//			System.out.println("Time:"+transaction.getTime());
//			System.out.println("=============================");
//		}
//		System.out.println("-------------------------------");
//		Person person=new Person();
//		person.setId(1);
//		List<Product> buyList=userDao.getBuyList(person);
//		for (Product product : buyList) {
//			System.out.println("id:"+product.getId());
//			System.out.println("价格:"+product.getPrice());
//			System.out.println("标题:"+product.getTitle());
//			System.out.println("图片Url:"+product.getImage());
//			System.out.println("摘要:"+product.getSummary());
//			System.out.println("详情:"+product.getDetail());
//			System.out.println("=============================");
//		}
//		System.out.println("-------------------------------");
		String time=userDao.NOW();
		System.out.println(time);
	}
}
