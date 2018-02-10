package com.lanyus.user.controller;

import com.lanyus.user.entity.User;
import com.lanyus.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/spring/user")
public class UserController {

	@Resource
	UserService userService;

	@RequestMapping("/getuserlist")
	public void getUserList(HttpServletRequest request, HttpServletResponse response)throws IOException {
		String account = request.getParameter("userName");
		String password = request.getParameter("password");
		User user_info = userService.getuser(account);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
	}

	@RequestMapping("/deleteuser")
	public void delleteUser(){

	}

	@RequestMapping("/insertuser")
	public void insertUser(){

	}
}