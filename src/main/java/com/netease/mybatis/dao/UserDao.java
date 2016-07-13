package com.netease.mybatis.dao;


import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Person;
import com.netease.java.bean.Product;
import com.netease.java.bean.Transaction;

@Component(value=constVar.UserDao)
public interface UserDao {

	@Select("select userType from Person where userName=#{userName} and password=#{password}")
	public String getUserType(@Param("userName")String userName,@Param("password") String password);
	
	@Select("select *from Person")
	public List<Person> getUsers();
	
	@Select("select id from Person where userName=#{userName}")
	public String getId(@Param("userName") String userName);
	
	@Results({
		@Result(id=true,property="id",column="id"),
		@Result(property="buyPrice",column="buyPrice"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="buyNum",column="buyNum"),
		@Result(property="buyTime",column="buyTime")
		}
	)
	@Select("select t.buyNum,t.buyPrice,t.buyTime,c.icon,c.title,c.id from trx as t,content as c,person as p where c.id=t.contentId and p.id=t.personId and p.id=#{id}")
	@ResultType(value=Product.class)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public List<Product> getBuyList(Person person);
	
	@Select("select NOW()")
	public String NOW();
}
