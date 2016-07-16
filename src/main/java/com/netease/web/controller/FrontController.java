package com.netease.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Options;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.netease.constant.var.constVar;
import com.netease.freemarker.config.FreeMarkerLoader;
import com.netease.java.bean.Person;
import com.netease.java.bean.Product;
import com.netease.java.bean.Transaction;
import com.netease.mybatis.dao.UserDao;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
public class FrontController {
	private Person p = new Person(0, null, null, null, -1);

	@RequestMapping(value = "/")
	public void Index(HttpServletRequest request, HttpServletResponse response) throws TemplateException, IOException {
		int userType = p.getUsertype();
		String listType = request.getParameter("type");
		if (listType == null) {
			List<Product> productList = Detector.getProductList();
			if (userType != -1) {
				for (Product product : productList) {
					Boolean condition = Detector.getProductCondition(product.getId());
					if (userType == 0) {
						product.setIsBuy(condition);
					} else {
						product.setIsSell(condition);
					}
				}
			}
			request.setAttribute("productList", productList);
		} else if (listType.equals("1")) {
			List<Product> productList = Detector.getNotBuyProductList();
			request.setAttribute("productList", productList);
		}
		FreeMarkerLoader.resolveTemplate(request, response, constVar.indexFtl, p);
	}

	@RequestMapping(value = "/login")
	public void LoginPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException {
		FreeMarkerLoader.resolveTemplate(request, response, constVar.loginFtl, p);
	}

	@RequestMapping(value = "/logout")
	public void LogoutPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException {
		request.setAttribute("user", null);
		p.setUsername(null);
		p.setPassword(null);
		FreeMarkerLoader.resolveTemplate(request, response, constVar.loginFtl, p);
	}

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap LoginOperate(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response, ModelMap map)
			throws IOException, TemplateException {

		int type = Detector.getUserType(userName, password);
		p.setId(Detector.getUserId(userName));
		p.setUsername(userName);
		p.setPassword(password);
		p.setUsertype(type);
		
		map.addAttribute("user", p);

		if (type == 1 || type == 0) {
			map.addAttribute("code", 200);
			map.addAttribute("result", true);
			map.addAttribute("message", "登录成功");
		} else {
			map.addAttribute("code", 500);
			map.addAttribute("result", false);
			map.addAttribute("message", "用户名或密码错误");
		}
		return map;
	}

	@RequestMapping(value = "/api/delete", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap Delete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id,
			ModelMap map) {
		if (Detector.deleteProduct(id)) {
			map.addAttribute("code", 200);
			map.addAttribute("message", "删除成功");
			map.addAttribute("result", true);
		}
		return map;
	}

	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap Upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile imageFile, ModelMap map) throws IOException, TemplateException {
		int status = response.getStatus();
		if (!imageFile.isEmpty()) {
			String imageFilePath = request.getSession().getServletContext().getRealPath("/") + "image" + File.separator
					+ imageFile.getOriginalFilename();
			System.out.println("imageFilePath=" + imageFilePath);
			String fileUploadPath = "./image" + File.separator + imageFile.getOriginalFilename();
			imageFile.transferTo(new File(imageFilePath));
			map.addAttribute("status", status);
			map.addAttribute("message", "上传成功");
			map.addAttribute("result", fileUploadPath);
		}
		return map;
	}

	@RequestMapping(value = "/api/buy", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap Buy(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Product> buyList,
			ModelMap map) throws IOException, TemplateException {
		for (Product product : buyList) {

			String buyTime = Detector.getbuyTime();
			int productId = product.getId();
			int buyNum = product.getNumber();
			BigInteger buyPrice = Detector.getProductPrice(product);
			int personId = Detector.getUserId(p.getUsername());

			product.setIsBuy(true);
			product.setIsSell(true);

			Transaction transaction = new Transaction();
			transaction.setContentId(productId);
			transaction.setBuyPrice(buyPrice);
			transaction.setPersonId(personId);
			transaction.setBuyNum(buyNum);
			transaction.setBuyTime(buyTime);

			if (Detector.buyProduct(transaction)) {
				map.addAttribute("code", 200);
				map.addAttribute("message", "购买成功");
				map.addAttribute("result", true);
			}
		}
		return map;
	}

	@RequestMapping(value = "/account")
	public void Account(HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException {
		List<Product> buyList = Detector.getBuyList(p);
		request.setAttribute("buyList", buyList);
		FreeMarkerLoader.resolveTemplate(request, response, constVar.accountFtl, p);
	}

	@RequestMapping(value = "/settleAccount")
	public void SettleAccount(HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException {
		FreeMarkerLoader.resolveTemplate(request, response, constVar.settleAccountFtl, p);
	}

	@RequestMapping(value = "/show")
	public void Show(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
		int userType = p.getUsertype();
		int productId = Integer.valueOf(request.getParameter("id"));
		Product product;
		if (userType != -1) {
			Boolean condition = Detector.getProductCondition(productId);
			if (condition) {
				product = Detector.getBuyItem(productId);
			}else {
				product = Detector.getProduct(productId);
			}
			if (userType == 0) {
				product.setIsBuy(condition);
			} else {
				product.setIsSell(condition);
			}
			request.setAttribute("product", product);
		}
		FreeMarkerLoader.resolveTemplate(request, response, constVar.showFtl, p);
	}

	@RequestMapping(value = "/public")
	public void Public(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
		FreeMarkerLoader.resolveTemplate(request, response, constVar.publicFtl, p);
	}

	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public void PublicSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Product product)
			throws IOException, TemplateException {
		if (Detector.publicProduct(product) && (response.getStatus() == 200)) {
			request.setAttribute("product", product);
		}
		FreeMarkerLoader.resolveTemplate(request, response, constVar.publicSubmitFtl, p);
	}

	@RequestMapping(value = "/edit")
	public void Edit(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
		Product product = Detector.getProduct(Integer.valueOf(request.getParameter("id")));
		request.setAttribute("product", product);
		FreeMarkerLoader.resolveTemplate(request, response, constVar.editFtl, p);
	}

	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public void EditSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Product product)
			throws IOException, TemplateException {
		int code = response.getStatus();
		if (code == 200) {
			Detector.updateProduct(product);
		}
		request.setAttribute("code", code);
		request.setAttribute("product", product);
		FreeMarkerLoader.resolveTemplate(request, response, constVar.editSubmitFtl, p);
	}
}
