package com.netease.freemarker.config;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netease.constant.var.constVar;
import com.netease.java.bean.Person;
import com.netease.java.bean.Product;
import com.netease.web.controller.FrontController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerLoader {

	public static void resolveTemplate(HttpServletRequest request, HttpServletResponse response, String ftlName,
			Person p) throws IOException, TemplateException {

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDirectoryForTemplateLoading(
				new File(request.getSession().getServletContext().getRealPath("/") + "template" + File.separator));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template fTemplate = configuration.getTemplate(ftlName);
		Writer out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", request.getAttribute("productList"));

		if ((p.getUsername() == null || p.getPassword() == null)) {
			if (ftlName.equals(constVar.showFtl)) {
				map.put("product", request.getAttribute("product"));
			}
		} else {
			map.put("user", p);
			if (ftlName.equals(constVar.showFtl) || ftlName.equals(constVar.publicSubmitFtl)
					|| ftlName.equals(constVar.editFtl) || ftlName.equals(constVar.editSubmitFtl)) {
				map.put("product", request.getAttribute("product"));
			}
			if (ftlName.equals(constVar.accountFtl)) {
				map.put("buyList", request.getAttribute("buyList"));
			}
		}
		fTemplate.process(map, out);
		out.flush();
		out.close();
	}
}
