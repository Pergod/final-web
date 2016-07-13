package com.netease.test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Person;
import com.netease.web.controller.FrontController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TestFreeMarker {
	public static void main(String[] args) {
		try {
//			login();
			index();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static void login() throws IOException, TemplateException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDirectoryForTemplateLoading(new File(constVar.ftlPath));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template fTemplate = configuration.getTemplate("login.ftl");
//		Person p=new Person();
		
//		Map<String, Object> user = new HashMap<String, Object>();
//		user.put("username", p.getUserName());
//		user.put("password", p.getPassword());
//		user.put("usertype", p.getUserType());

		Writer out = new OutputStreamWriter(System.out, "UTF-8");
		fTemplate.process(null, out);

		out.flush();
		out.close();
	}
	
	public static void index() throws IOException, TemplateException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDirectoryForTemplateLoading(new File(constVar.ftlPath));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template fTemplate = configuration.getTemplate("index.ftl");
		
//		Person p=new Person();
//		Map<String, Object> user = new HashMap<String, Object>();
//		user.put("username", p.getUserName());
//		user.put("password", p.getPassword());
//		user.put("usertype", p.getUserType());

		Writer out = new OutputStreamWriter(System.out, "UTF-8");
		fTemplate.process(null, out);

		out.flush();
		out.close();
	}
}
