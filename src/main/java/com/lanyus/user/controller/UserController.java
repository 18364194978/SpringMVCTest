package com.lanyus.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/spring/user")
public class UserController {

	@RequestMapping("/getuserlist")
	public void getUserList(HttpServletRequest request, HttpServletResponse response)throws IOException {
		String user_name = request.getParameter("userName");
		String password = request.getParameter("password");
	}

	@RequestMapping("/deleteuser")
	public void delleteUser(){

	}

	@RequestMapping("/insertuser")
	public void insertUser(){

	}
}