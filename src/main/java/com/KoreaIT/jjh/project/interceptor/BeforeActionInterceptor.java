package com.KoreaIT.jjh.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.jjh.project.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private Rq rq;
	

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		System.out.println("=========================================================");

		rq.InitOnBeforeActionInterceptor();

		return true;
	}

}