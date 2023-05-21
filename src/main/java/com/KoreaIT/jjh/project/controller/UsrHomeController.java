package com.KoreaIT.jjh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	//	메인페이지로 이동
	@RequestMapping("usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	//	"/"만 입력시에 메인페이지로 이동
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	
	@RequestMapping("usr/home/event")
	public String showEventPage() {
		
		return "usr/home/event";
	}
	
	@RequestMapping("usr/home/event2")
	public String event2(@RequestParam(defaultValue = "1") int page) {

		return "usr/home/event2";
	}
	
	@RequestMapping("usr/home/news")
	public String news() {
		
		return "usr/home/news";
	}
	
	@RequestMapping("usr/home/detailNews")
	public String detailNews(int id) {
		
		return "usr/home/detailNews";
	}
	
	@RequestMapping("usr/home/startPage")
	public String startPage() {
		return "usr/home/startPage";
	}
	
	@RequestMapping("usr/home/test")
	public String test() {
		return "usr/home/test";
	}
	
}
