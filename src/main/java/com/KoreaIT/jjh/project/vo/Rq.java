package com.KoreaIT.jjh.project.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.jjh.project.util.Ut;

import lombok.Getter;

public class Rq {

	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;

	private HttpSession session;
	private HttpServletRequest req;
	private HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession httpSession = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;

		this.req = req;
		this.resp = resp;
		this.session = req.getSession();

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public void login() {
		session.setAttribute("loginedMemberId", loginedMemberId);
	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		print("<script>");
		if (!Ut.empty(msg)) {
			print("alert('" + msg + "');");
		}
		print("history.back();");
		print("</script>");
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
