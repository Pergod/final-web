package com.netease.mybatis.dao;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Product;

@Component(value=constVar.ProductsDao)
public interface ProductsDao {
	
		@Results({
			@Result(id=true,property="id",column="id"),
			@Result(property="price",column="price"),
			@Result(property="title",column="title"),
			@Result(property="image",column="icon"),
			@Result(property="summary",column="abstract"),
			@Result(property="detail",column="text"),
		}
	)
	@Select("select * from content")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public List<Product> getProducts();
	
		@Results({
			@Result(id=true,property="id",column="id"),
			@Result(property="price",column="price"),
			@Result(property="title",column="title"),
			@Result(property="image",column="icon"),
			@Result(property="summary",column="abstract"),
			@Result(property="detail",column="text"),
		}
	)	
	@Select("select * from content where id=#{id}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Product getProduct(@Param("id") int id);
	
		@Results({
			@Result(id=true,property="id",column="id"),
			@Result(property="price",column="price"),
			@Result(property="title",column="title"),
			@Result(property="image",column="icon"),
			@Result(property="summary",column="abstract"),
			@Result(property="detail",column="text"),
		}
	)
		
	@Insert("insert into content(price,title,icon,abstract,text) values(#{price},#{title},#{image},#{summary},#{detail})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Boolean PublicProduct(Product product);
	
		@Results({
			@Result(id=true,property="id",column="id"),
			@Result(property="price",column="price"),
			@Result(property="title",column="title"),
			@Result(property="image",column="icon"),
			@Result(property="summary",column="abstract"),
			@Result(property="detail",column="text"),
		}
	)
		
	@Update("update content set price=#{price},title=#{title},icon=#{image},abstract=#{summary},text=#{detail} where id=#{id}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Boolean UpdateProduct(Product product);
	
		
		@Results({
			@Result(id=true,property="id",column="id"),
			@Result(property="price",column="price"),
			@Result(property="title",column="title"),
			@Result(property="image",column="icon"),
			@Result(property="summary",column="abstract"),
			@Result(property="detail",column="text"),
		}
	)
	@Delete("delete from content where id=#{id}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Boolean DeleteProduct(@Param("id") int id);
		
	@Select("select price from content where id=#{id}")
	public BigInteger getProductPrice(Product product);
	
	@Results({
		@Result(id=true,property="id",column="id"),
		@Result(property="price",column="price"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text"),
		}
	)
	@Select("select * from content as c where id not in(select contentId from trx as t where c.id=t.contentId)")
	public List<Product> GetNotBuyProductList();
}
