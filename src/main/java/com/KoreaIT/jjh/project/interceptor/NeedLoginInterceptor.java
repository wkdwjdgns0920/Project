package com.KoreaIT.jjh.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {

		System.out.println("==================로그인 인터셉터===========================");
		
		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined() == false) {
			rq.printHistoryBackJs("로그인후에 이용해주세요!!!!!!!!");
			
			return false;
		}
		
		
		return true;
	}

}