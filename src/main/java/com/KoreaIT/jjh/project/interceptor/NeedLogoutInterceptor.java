package com.KoreaIT.jjh.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Rq;

@Component
public class NeedLogoutInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined() == true) {
			rq.printHistoryBackJs("F-B","로그아웃후에 이용해주세요@@@");
			
			return false;
		}
		
		
		return true;
	}

}