package com.lanyus.user.controller;

import com.lanyus.user.entity.User;
import com.lanyus.user.service.UserService;
import com.lanyus.core.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
//@Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中
@Controller
@RequestMapping("/spring/user")
public class UserController {

	@Resource
	UserService userService;

	@RequestMapping("/checkuser")
	@ResponseBody
	public Map<String,Object> getUserList(HttpServletRequest request, HttpServletResponse response)throws IOException {
		String account = request.getParameter("userName");
		String password = request.getParameter("password");
		User user_info = userService.getuser(account);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user_info == null) { // 用户名有误或已被禁用
			result.put("success", true);
			result.put("result", 1);
			return result;
		}
//		if (!user_info.getPassword().equals(MD5.crypt(password))) { // 密码错误
		if (!user_info.getPassword().equals(password)) { // 密码错误
			result.put("success", true);
			result.put("result", -2);
			return result;
		}
		request.getSession().setAttribute(Constant.SESSION_ROLE_IDS, user_info.getUserid());
		request.getSession().setAttribute(Constant.SESSION_SYS_USER, user_info.getUsername());
		result.put("success", true);
		result.put("result", 1);
		return result;
	}
//	@RequestMapping("/home")
//	public void home(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		if (request.getSession().getAttribute(Constant.SESSION_SYS_USER) == null) {
////			logout(request,response);
////			return "";
//		} else {
//			Object id  = request.getSession().getAttribute(Constant.SESSION_SYS_USER);
////			return "main";
//			String contextPath = request.getContextPath();
//			response.sendRedirect(contextPath + "/index.jsp");
//		}
//	}
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response) throws IOException{
			return "main";

	}

	@RequestMapping("/deleteuser")
	public void delleteUser(){

	}

	@RequestMapping("/insertuser")
	public void insertUser(){

	}
}