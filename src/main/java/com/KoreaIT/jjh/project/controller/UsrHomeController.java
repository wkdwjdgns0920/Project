package com.KoreaIT.jjh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	@RequestMapping("usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("usr/home/travel")
	public String showTravelPage() {
		
		return "usr/home/travel";
	}
	
	@RequestMapping("usr/home/event")
	public String showEventPage() {
		
		return "usr/home/event";
	}
	
	@RequestMapping("usr/home/startPage")
	public String startPage() {
		return "usr/home/startPage";
	}
	
}
