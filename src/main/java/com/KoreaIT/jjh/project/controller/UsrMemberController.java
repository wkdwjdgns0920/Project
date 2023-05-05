package com.KoreaIT.jjh.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.MemberService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Member;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;
//	@Autowired
//	Rq rq;

	@RequestMapping("usr/member/join")
	@ResponseBody
	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요");
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요");
		}
		if (Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return ResultData.from(joinRd.getResultCode(), joinRd.getMsg());
		}

		Member member = memberService.getMemberById((int) joinRd.getData1());

		return ResultData.from(joinRd.getResultCode(), joinRd.getMsg(), "member", member);
	}

	@RequestMapping("usr/member/login")
	public String login(String loginId, String loginPw) {

		return "usr/member/login";
	}

//	@RequestMapping("usr/member/doLogin")
//	@ResponseBody
//	public ResultData doLogin(String loginId, String loginPw) {
//
//		if (rq.isLogined()) {
//			return ResultData.from("F-1", "로그아웃후에 이용해주세요");
//		}
//		if (Ut.empty(loginId)) {
//			return ResultData.from("F-2", "아이디릅 입력해주세요");
//		}
//		if (Ut.empty(loginPw)) {
//			return ResultData.from("F-3", "비밀번호를 입력해주세요");
//		}
//
//		Member member = memberService.getMemberByLoginId(loginId);
//
//		if (member == null) {
//			return ResultData.from("F-4", Ut.f("%s 아이디는 존재하지 않는 아이디 입니다", loginId));
//		}
//		if(member.getLoginPw().equals(loginPw)==false) {
//			return ResultData.from("F-5", "비밀번호가 틀립니다");
//		}
//		
//		rq.login(member);
//
//		return ResultData.from("S-1", "로그인성공");
//	}
}
