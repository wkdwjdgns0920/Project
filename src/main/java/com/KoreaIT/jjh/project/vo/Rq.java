package com.KoreaIT.jjh.project.vo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.KoreaIT.jjh.project.service.MemberService;
import com.KoreaIT.jjh.project.util.Ut;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {

	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	private Map<String, String> paramMap;

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;

		this.session = req.getSession();
		
		paramMap = Ut.getParamMap(req);

		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;


		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
	}
	
	//	session에 로그인한 사용자의 id를 지움
	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	//session에 로그인한 사용자의 id를 넣음
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	//	뒤로가기
	public void printHistoryBackJs(String resultCode, String msg) {
		resp.setContentType("text/html; charset=UTF-8");	// 글자깨짐설정
		print(Ut.jsHistoryBack(resultCode, msg));	//	알림창을 띄우고 뒤로가기
	}
	
	//	자바스크립트 코드 변환
	public void print(String str) {
		try {
			resp.getWriter().append(str + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//	뒤로가기
	public String jsHistoryBackOnView(String msg) {
		req.setAttribute("msg", msg);	//	알림창에 띄울 메시지를 전달
		req.setAttribute("historyBack", true);	//	뒤로가기를 실행할지에 대한 정보 전달

		return "usr/common/js";

	}
	
	//	Rq를 시작과 동시에 한번 실행시켜주기 위한 메서드(로그인정보를 위해)(페이지 실행시에 매번 Rq객체를 생성하지 않기위해)
	public void InitOnBeforeActionInterceptor() {

	}
	
	//	현재 페이지와 query문을 가져오기위한 메서드
	public String getCurrentUri() {
		String currentUri = req.getRequestURI();	//	현재 페이지를 가져옴
		String queryString = req.getQueryString();	//	현제 페이지의 query문을 가져옴

		if (queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}
		//	query문이 있을경우 현재페이지의 Uri에 "?query"를 붙여서 현재페이지의 주소를 수정
		
		return currentUri;
	}
	
	//	현재 페이지를 인코딩하기 위한 메서드
	public String getEncodedCurrentUri() {
		return Ut.getEncodedCurrentUri(getCurrentUri());
	}
	
	//	로그인후에 이동할 페이지를 설정
	public String getLoginUri() {
		return "../member/login?afterLoginUri=" + getAfterLoginUri();
	}
	
	//	로그인 후에 이동할 페이지를 설정하는 메서드
	private String getAfterLoginUri() {
		String requestUri = req.getRequestURI();

		switch (requestUri) {
		case "/usr/member/login":	//	login의 경우
			
		case "/usr/member/join":	//	join의 경우
			return Ut.getEncodedUri(paramMap.get("afterLoginUri"));
			//	

		}

		return getEncodedCurrentUri();
	}
	
	//	로그아웃 후에 이동할 페이지
	public String getLogoutUri() {
		return "../member/doLogout?afterLogoutUri=" + getEncodedCurrentUri();
	}
	
	//	회원가입후에 이동할 페이지
	public String getJoinUri() {
		return "/usr/member/join?afterLoginUri=" + getAfterLoginUri();
	}
	
}
