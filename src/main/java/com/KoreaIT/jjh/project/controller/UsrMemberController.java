package com.KoreaIT.jjh.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Autowired
	private Rq rq;
	
	
	
	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPw() {

		return "usr/member/findLoginPw";
	}

	@RequestMapping("/usr/member/doFindLoginPw")
	@ResponseBody
	public String doFindLoginPw(@RequestParam(defaultValue = "/") String afterFindLoginPwUri, String loginId,
			String email) {

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-1", "존재하지 않는 아이디입니다");
		}

		if (member.getEmail().equals(email) == false) {
			return Ut.jsHistoryBack("F-2", "이메일이 일치하지 않습니다");
		}

		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);

		return Ut.jsReplace(notifyTempLoginPwByEmailRd.getResultCode(), notifyTempLoginPwByEmailRd.getMsg(),
				afterFindLoginPwUri);
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId() {

		return "usr/member/findLoginId";
	}

	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(@RequestParam(defaultValue = "/") String afterFindLoginIdUri, String name,
			String email) {

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return Ut.jsHistoryBack("F-1", "등록되지 않은 회원입니다");
		}

		return Ut.jsReplace("S-1", Ut.f("아이디 [ %s ]", member.getLoginId()), afterFindLoginIdUri);
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String loginId,String loginPw,String loginPwConfirm ,String name, String nickname, String cellphoneNum, String email) {
		
		loginPw.trim();
		loginPwConfirm.trim();
		name.trim();
		nickname.trim();
		cellphoneNum.trim();
		email.trim();
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(loginPw.equals(loginPwConfirm) == false) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 일하게 입력하지 않았습니다");
		}
		
		if (Ut.empty(loginPw)) {
			loginPw = rq.getLoginedMember().getLoginPw();
		}
		if (Ut.empty(name)) {
			name = null;
		}
		if (Ut.empty(nickname)) {
			nickname = null;
		}
		if (Ut.empty(cellphoneNum)) {
			cellphoneNum = null;
		}
		if (Ut.empty(email)) {
			email = null;
		}

		ResultData modifyRd = memberService.modify(rq.getLoginedMemberId(), loginPw, name, nickname, cellphoneNum,
				email);

		return Ut.jsReplace(modifyRd.getResultCode(), modifyRd.getMsg(), "../member/myPage");
	}

	@RequestMapping("usr/member/modify")
	public String modify() {

		return "usr/member/modify";
	}

	@RequestMapping("/usr/member/doCheckPw")
	@ResponseBody
	public String doCheckPw(String loginPw) {

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 입력해주세요");
		}

		if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 확인해주세요");
		}

		return Ut.jsReplace("S-1", "비밀번호확인!", "modify");
	}

	@RequestMapping("/usr/member/checkPw")
	public String showCheckPw() {

		return "usr/member/checkPw";
	}

	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {

		return "usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/checkValidPw")
	@ResponseBody
	public ResultData checkValidPw(String loginPw) {

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요");
		}

		if(Ut.validationPasswd(loginPw)) {
			return ResultData.from("F-2", "최소 8자리에 숫자, 문자, 특수문자 사용해주세요!!");
		}

		return ResultData.from("S-1", "사용 가능!", "loginPw", loginPw);
	}
	
	@RequestMapping("/usr/member/checkValidEmail")
	@ResponseBody
	public ResultData checkValidEmail(String email) {

		if (Ut.empty(email)) {
			return ResultData.from("F-1", "이메일을 입력해주세요");
		}
		
		if(Ut.isValidEmail(email) == false) {
			return ResultData.from("F-2", "이메일형식을 확인해주세요");
		}

		return ResultData.from("S-1", "사용 가능!", "email", email);
	}
	
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String loginId) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}

		Member existsMember = memberService.getMemberByLoginId(loginId);

		if (existsMember != null) {
			return ResultData.from("F-2", "해당 아이디는 이미 사용중이야", "loginId", loginId);
		}

		return ResultData.from("S-1", "사용 가능!", "loginId", loginId);
	}
	
	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email, @RequestParam(defaultValue = "/") String afterLoginUri) {

//		if (Ut.empty(loginId)) {
//			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
//		}
//		if (Ut.empty(loginPw)) {
//			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
//		}
//		if(Ut.validationPasswd(loginPw)) {
//			return Ut.jsHistoryBack("F-A", "최소 8자리에 숫자, 문자, 특수문자 사용해주세요");
//		}
//		if (Ut.empty(name)) {
//			return Ut.jsHistoryBack("F-3", "이름을 입력해주세요");
//		}
//		if (Ut.empty(nickname)) {
//			return Ut.jsHistoryBack("F-4", "닉네임을 입력해주세요");
//		}
//		if (Ut.empty(cellphoneNum)) {
//			return Ut.jsHistoryBack("F-5", "전화번호를 입력해주세요");
//		}
//		if (Ut.empty(email)) {
//			return Ut.jsHistoryBack("F-6", "이메일을 입력해주세요");
//		}
//		if(Ut.isValidEmail(email) == false) {
//			return Ut.jsHistoryBack("F-11", "이메일형식을 확인해주세요");
//		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}

		Member member = memberService.getMemberById((int) joinRd.getData1());
		
		String afterJoinUri = "../member/login?afterLoginUri=" + Ut.getEncodedUri(afterLoginUri);

		return Ut.jsReplace(joinRd.getResultCode(), joinRd.getMsg(), afterJoinUri);
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}

	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public String doLogout(String afterLogoutUri) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인안함");
		}

		rq.logout();

		return Ut.jsReplace("S-1", "로그아웃!", afterLogoutUri);	
	}

	@RequestMapping("usr/member/login")
	public String login() {

		return "usr/member/login";
	}

	@RequestMapping("usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, @RequestParam(defaultValue = "/") String afterLoginUri) {

		if (rq.isLogined() == true) {
			return Ut.jsHistoryBack("F-1", "로그인상태");
		}

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-2", "아이디릅 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-4", Ut.f("%s 아이디는 존재하지 않는 아이디 입니다", loginId));
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-5", "비밀번호가 틀립니다");
		}

		rq.login(member);

		return Ut.jsReplace("S-1", "로그인성공", afterLoginUri);
	}
}