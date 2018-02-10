package com.lanyus.springmvcmybatis.controller;

import com.lanyus.springmvcmybatis.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/spring")
public class HelloController {
	@Resource
	TestService service;
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", service.print(2));
		return "hello";
	}

	@RequestMapping("/success")
	public void getlist(){
		service.updateContent(2,"1");
	}

	@RequestMapping("/del")
	public void del(){
		service.delete(2);
	}

	@RequestMapping("/insert")
	public void insert(){
		service.newContent(7,"1");
	}
}