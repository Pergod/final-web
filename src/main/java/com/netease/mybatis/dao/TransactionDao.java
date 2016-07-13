package com.netease.mybatis.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Product;
import com.netease.java.bean.Transaction;

@Component(value=constVar.TransactionDao)
public interface TransactionDao {
	
	@Results({
		@Result(id=true,property="id",column="id"),
		@Result(property="personId",column="personId"),
		@Result(property="contentId",column="contentId"),
		@Result(property="buyPrice",column="buyPrice"),
		@Result(property="buyNum",column="buyNum"),
		@Result(property="buyTime",column="buyTime")
		}
	)
	@Insert("insert into trx(contentId,personId,buyPrice,buyTime,buyNum) values(#{contentId},#{personId},#{buyPrice},#{buyTime},#{buyNum})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Boolean BuyProduct(Transaction trx);
	
	@Select("select * from trx")
	public List<Transaction> getTransactionMsg();
	
	@Results({
		@Result(id=true,property="id",column="id"),
		@Result(property="personId",column="personId"),
		@Result(property="productId",column="contentId"),
		@Result(property="price",column="price"),
		@Result(property="number",column="number")
		}
	)
	@Select("select price from trx where contentId=#{productId}")
	public BigInteger getTrxPrice(@Param("productId")int productId);
	
	
	@Select("select id from trx where contentId=#{productId}")
	public String getTrxId(@Param("productId") int productId);
	
	@Results({
		@Result(id=true,property="id",column="id"),
		@Result(property="buyPrice",column="buyPrice"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="buyNum",column="buyNum"),
		@Result(property="buyTime",column="buyTime"),
		@Result(property="detail",column="text")
		}
	)
	@Select("select t.buyNum,t.buyPrice,t.buyTime,c.icon,c.title,c.id,c.price,c.text from trx as t,content as c where c.id=t.contentId and c.id=#{id}")
	@ResultType(value=Product.class)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Product getBuyItem(@Param("id")int productId);
}
