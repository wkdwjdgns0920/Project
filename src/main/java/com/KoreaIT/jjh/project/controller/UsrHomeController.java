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
	public String showEventPage(@RequestParam(defaultValue = "1") int page) {
		
		return "usr/home/event";
	}
	
	@RequestMapping("usr/home/tourspot")
	public String tourspot(@RequestParam(defaultValue = "1") int page) {

		return "usr/home/tourspot";
	}
	
	@RequestMapping("usr/home/festv")
	public String festv(@RequestParam(defaultValue = "1") int page) {
		
		return "usr/home/festv";
	}
	
	@RequestMapping("usr/home/festvDetail")
	public String festvDetail(int id) {
		
		return "usr/home/festvDetail";
	}
	
	@RequestMapping("usr/home/test")
	public String test(int page) {
		
		return "usr/home/test";
	}
	
	
	
	// 연습
	@RequestMapping("usr/home/startPage")
	public String startPage() {
		return "usr/home/startPage";
	}
	
}
