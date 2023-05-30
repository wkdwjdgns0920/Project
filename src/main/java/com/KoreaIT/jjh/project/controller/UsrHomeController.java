package com.KoreaIT.jjh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.KoreaIT.jjh.project.util.DateTime;

@Controller
public class UsrHomeController {
	
	//	메인페이지로 이동
	@RequestMapping("usr/home/main")
	public String showMain(Model model) {
		
		String date = DateTime.getNowDate();
        int nowDate = Integer.parseInt(date);
		String time = DateTime.getNowTime();
		int nowTime = Integer.parseInt(time);
        
		System.out.println(nowTime);
		System.out.println(time);
		if(nowTime < 210) {
			nowDate -= 1;
			date = Integer.toString(nowDate);
			time = "2300";
			
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 210 && nowTime < 510) {
			time = "0210";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 510 && nowTime < 810) {
			time = "0510";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}  else if (nowTime > 810 && nowTime < 1110) {
			time = "0810";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1110 && nowTime < 1410) {
			time = "1110";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1410 && nowTime < 1710) {
			time = "1410";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1710 && nowTime < 2010) {
			time = "1710";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 2010 && nowTime < 2310) {
			time = "2010";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}  else if (nowTime > 2310 && nowTime < 2400) {
			time = "2310";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}
		
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
	
	@GetMapping
	
	
	@RequestMapping("usr/home/story")
	public String story() {
		
		return "usr/home/story";
	}
	

	// 연습
	@RequestMapping("usr/home/startPage")
	public String startPage(Model model) {
		
		String date = DateTime.getNowDate();
        int nowDate = Integer.parseInt(date);
		String time = DateTime.getNowTime();
		int nowTime = Integer.parseInt(time);
        
		System.out.println(nowTime);
		System.out.println(time);
		if(nowTime < 210) {
			nowDate -= 1;
			date = Integer.toString(nowDate);
			time = "2300";
			
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 210 && nowTime < 510) {
			time = "0210";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 510 && nowTime < 810) {
			time = "0510";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}  else if (nowTime > 810 && nowTime < 1110) {
			time = "0810";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1110 && nowTime < 1410) {
			time = "1110";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1410 && nowTime < 1710) {
			time = "1410";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 1710 && nowTime < 2010) {
			time = "1710";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		} else if (nowTime > 2010 && nowTime < 2310) {
			time = "2010";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}  else if (nowTime > 2310 && nowTime < 2400) {
			time = "2310";
			model.addAttribute("date",date);
	        model.addAttribute("time",time);
		}
		
		return "usr/home/startPage";
	}
	
	@RequestMapping("usr/home/test")
	public String test(int page) {
		
		return "usr/home/test";
	}
	
}
