package com.KoreaIT.jjh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	@RequestMapping("usr/home/main")
	@ResponseBody
	public String showMain() {
		return "Main";
	}
	
}
