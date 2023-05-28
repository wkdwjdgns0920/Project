package com.KoreaIT.jjh.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.service.MemberService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.Member;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	ArticleService articleService;
	@Autowired
	private Rq rq;
	
	
	
	@RequestMapping("/usr/member/findLoginPw")	//	비밀번호 찾기 페이지를 보여줌
	public String showFindLoginPw() {

		return "usr/member/findLoginPw";
	}

	@RequestMapping("/usr/member/doFindLoginPw")	//	비밀번호 찾기를 실행
	@ResponseBody
	public String doFindLoginPw(@RequestParam(defaultValue = "/") String afterFindLoginPwUri, String loginId,
			String email) {

		Member member = memberService.getMemberByLoginId(loginId);
		//	loginId에 맞는 회원의 정보

		if (member == null) {
			return Ut.jsHistoryBack("F-1", "존재하지 않는 아이디입니다");
		}
		//	loginId에 맞는 회원의 정보가 없으면 그에 맞는 알림창을 띄우고 뒤로가기

		if (member.getEmail().equals(email) == false) {
			return Ut.jsHistoryBack("F-2", "이메일이 일치하지 않습니다");
		}
		//	loginId에 맞는 회원정보의 email이 다르면 일치하지 않는 알림창을 띄우고 뒤로가기

		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);
		//	임시비밀번호로 회원의 정보를 수정하고 메일을 발송한 결과데이터

		return Ut.jsReplace(notifyTempLoginPwByEmailRd.getResultCode(), notifyTempLoginPwByEmailRd.getMsg(),
				afterFindLoginPwUri);
		//	임시비밀번호로 회원의 정보를 수정하고 이메일로 임시비밀번호를 발송하고 afterFindLoginPwUri페이지로 이동
	}
	
	//	로그인 아이디찾기 페이지를 보여줌
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId() {

		return "usr/member/findLoginId";
	}
	
	//	로그인 아이디 찾기 실행페이지
	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(@RequestParam(defaultValue = "/") String afterFindLoginIdUri, String name,
			String email) {
		
		Member member = memberService.getMemberByNameAndEmail(name, email);
		//	회원의 이름과 이메일을 통해서 일치하는 회원의 정보를 가져옴

		if (member == null) {
			return Ut.jsHistoryBack("F-1", "등록되지 않은 회원입니다");
		}
		//	일치하는 회원의 정보가 없으면 알림창을 띄우고 뒤로가기

		return Ut.jsReplace("S-1", Ut.f("아이디 [ %s ]", member.getLoginId()), afterFindLoginIdUri);
		//	이름과 회원의 이메일이 맞으면 로그인아디를 알림창에 보여주고 afterFindLoginIdUri페이지로 이동
	}
	
	
	//	회원정보 수정을 실행하는 페이지
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String loginId,String loginPw,String loginPwConfirm ,String name, String nickname, String cellphoneNum, String email) {
		
		loginPw.trim();
		loginPwConfirm.trim();
		name.trim();
		nickname.trim();
		cellphoneNum.trim();
		email.trim();
		//	사용자에게 입력받은 수정할 회원의 정보의 공백을 제거함
		
		Member member = memberService.getMemberByLoginId(loginId);
		//	loginId를 통해서 회원의 정보를 가져옴
		
		if(loginPw.equals(loginPwConfirm) == false) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 일하게 입력하지 않았습니다");
		}
		//	비밀번호와 비밀번호확인이 일치하지 않으면 알림창을 띄우고 뒤로가기
		
		if (Ut.empty(loginPw)) {
			loginPw = rq.getLoginedMember().getLoginPw();
		}
		//	로그인 비밀번호가 입력되지 않았으면 로그인한 회원의 비밀번호를 수정할 회원의 비밀번호로 설정
		
		if (Ut.empty(name)) {
			name = null;
		}
		//	수정할 회원의 이름이 비었으면 null로 설정
		
		if (Ut.empty(nickname)) {
			nickname = null;
		}
		//	수정할 회원의 닉네임이 비었으면 null로 설정
		
		if (Ut.empty(cellphoneNum)) {
			cellphoneNum = null;
		}
		//	수정할 회원의 전화번호가 비었으면 null로 설정
		
		if (Ut.empty(email)) {
			email = null;
		}
		//	수정할 회원의 이메일이 비었으면 null로 설정
		
		ResultData modifyRd = memberService.modify(rq.getLoginedMemberId(), loginPw, name, nickname, cellphoneNum,
				email);
		//	회원정보 수정에 대한 데이터를 전달받음
		
		if(modifyRd.isFail()) {
			return Ut.jsHistoryBack(modifyRd.getResultCode(), modifyRd.getMsg());
		}
		//	회원정보 수정에 실패하였으면 그에대한 알림창을 보여주고 뒤로가기
		
		return Ut.jsReplace(modifyRd.getResultCode(), modifyRd.getMsg(), "../member/myPage");
		//	회원정보 수정성공 알림창을 보여주고 myPage로 이동
	}
	
	
	//	회원정보 수정페이로 이동
	@RequestMapping("usr/member/modify")
	public String modify() {

		return "usr/member/modify";
	}
	
	//	회원 비밀번호를 체크하는 페이지
	@RequestMapping("/usr/member/doCheckPw")
	@ResponseBody
	public String doCheckPw(String loginPw) {

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 입력해주세요");
		}
		//	비밀번호를 입력하지 않았으면 알림창을 띄우고 뒤로가기

		if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 확인해주세요");
		}
		//	로그인한 회원이 비밀번호가 일치하지 않으면 알림창을 띄우고 뒤로가기

		return Ut.jsReplace("S-1", "비밀번호확인!", "modify");
		//	로그인한 회원의 비밀번호가 일치한다는 알림창과 회원정보 수정페이지로 이동
	}

	//	비밀번호 확인페이지로 이동
	@RequestMapping("/usr/member/checkPw")
	public String showCheckPw() {

		return "usr/member/checkPw";
	}

	//	회원정보페이지(myPage)로 이동
	@RequestMapping("/usr/member/myPage")
	public String showMyPage(Model model) {
			
		if(rq.isLogined() == false) {
			return rq.jsHistoryBackOnView("로그인후에 이용해주세요");
		}
		
		List<Article> articles = articleService.getArticlesByMemberId(rq.getLoginedMemberId());
		
		model.addAttribute("articles",articles);
		
		return "usr/member/myPage";
	}
	
	//	비밀번호가 사용할 수 있는지를 확인하는 페이지
	@RequestMapping("/usr/member/checkValidPw")
	@ResponseBody
	public ResultData checkValidPw(String loginPw) {

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요");
		}
		//	비밀번호가 입력되지 않은 것에 대한 정보

		if(Ut.validationPasswd(loginPw)) {
			return ResultData.from("F-2", "최소 8자리에 숫자, 문자, 특수문자 사용해주세요!!");
		}
		//	비밀번호 유효성검사를 실시 후 사용가능하지 않은 것에 대한 정보

		return ResultData.from("S-1", "사용 가능!", "loginPw", loginPw);
		//	비밀번호가 사용가능한 것에 대한 정보
	}
	
	//	이메일 유효성검사 페이지
	@RequestMapping("/usr/member/checkValidEmail")
	@ResponseBody
	public ResultData checkValidEmail(String email) {

		if (Ut.empty(email)) {
			return ResultData.from("F-1", "이메일을 입력해주세요");
		}
		//	이메일이 입력되지 않은것에 대한 데이터
		
		if(Ut.isValidEmail(email) == false) {
			return ResultData.from("F-2", "이메일형식을 확인해주세요");
		}
		//	이메일 유효성검사에 실패한 것에 대한 데이터

		return ResultData.from("S-1", "사용 가능!", "email", email);
		//	사용할수 있는 이메일인 것에 대한 데이터
	}
	
	//	로그인 아이디 중복검사페이지
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String loginId) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		//	loginId가 입력되지 않은 것에 대한 데이터
		
		Member existsMember = memberService.getMemberByLoginId(loginId);
		//	입력받은 로그인아이디와 일치한 회원의 정보를 가져옴
		
		if (existsMember != null) {
			return ResultData.from("F-2", "해당 아이디는 이미 사용중이야", "loginId", loginId);
		}
		//	로그인아이디가 일치한 것이 있을경우 사용중이 아이디인것에 대한 정보를 리턴

		return ResultData.from("S-1", "사용 가능!", "loginId", loginId);
		//	사용가능한 아이디라는 것에 대한 정보를 전달
	}
	
	//	회원가입실행하는 페이지
	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email, @RequestParam(defaultValue = "/") String afterLoginUri) {

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
		}
		if(Ut.validationPasswd(loginPw)) {
			return Ut.jsHistoryBack("F-A", "최소 8자리에 숫자, 문자, 특수문자 사용해주세요");
		}
		if (Ut.empty(name)) {
			return Ut.jsHistoryBack("F-3", "이름을 입력해주세요");
		}
		if (Ut.empty(nickname)) {
			return Ut.jsHistoryBack("F-4", "닉네임을 입력해주세요");
		}
		if (Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-5", "전화번호를 입력해주세요");
		}
		if (Ut.empty(email)) {
			return Ut.jsHistoryBack("F-6", "이메일을 입력해주세요");
		}
		if(Ut.isValidEmail(email) == false) {
			return Ut.jsHistoryBack("F-11", "이메일형식을 확인해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		//	회원가입 실행에 대한 결과데이터를 받음

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}
		//	회원가입이 실패하였다면 그에대한 알림창을 띄우고 뒤로가기

		Member member = memberService.getMemberById((int) joinRd.getData1());
		//	회원가입된 id를 통해서 회원의 정보를 가져옴
		
		String afterJoinUri = "../member/login?afterLoginUri=" + Ut.getEncodedUri(afterLoginUri);
		//	로그인 후에 이동할 페이지를 설정(회원가입페이지로 오기전에 실행한 페이지에 대한주소)

		return Ut.jsReplace(joinRd.getResultCode(), joinRd.getMsg(), afterJoinUri);
		//	회원가입에 성공했다는 알림창과 함께 로그인페이지로 이동
	}
	
	//	회원가입 페이지로 이동
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	//	로그아웃실행페이지
	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public String doLogout(String afterLogoutUri) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인안함");
		}
		//	로그인이 안되어 있을경우 뒤로가기
		
		rq.logout();
		//	rq에서 isLogined을 false로 바꿈
		
		return Ut.jsReplace("S-1", "로그아웃!", afterLogoutUri);
		//	로그아웃성공에 대한 알림창과 로그아웃을 하기전에 페이지로 이동
	}
	
	
	//	로그인페이지로 이동
	@RequestMapping("usr/member/login")
	public String login() {

		return "usr/member/login";
	}
	
	//	로그인을 실행하는 페이지
	@RequestMapping("usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, @RequestParam(defaultValue = "/") String afterLoginUri) {

		if (rq.isLogined() == true) {
			return Ut.jsHistoryBack("F-1", "로그인상태");
		}
		//	로그인상태이면 알림창을 띄우고 뒤로가기

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-2", "아이디릅 입력해주세요");
		}
		//	아이디가 입력되지 았으면 알림창을 띄우고 뒤로가기
		
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}
		//	비밀번호가 입력되지 않았으면 알림창을 띄우고 뒤로가기

		Member member = memberService.getMemberByLoginId(loginId);
		//	loginId와 일치하는 회원의 정보를 가져옴
		
		if (member == null) {
			return Ut.jsHistoryBack("F-4", Ut.f("%s 아이디는 존재하지 않는 아이디 입니다", loginId));
		}
		//	loginId와 일치하는 회원의 정보가 없으면 알림창을 띄우고 뒤로가기
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-5", "비밀번호가 틀립니다");
		}
		//	로그인 비밀번호가 loginId와 일치하는 회원의 비밀번호와 일치하지 않으면 알림창을 띄우고 뒤로가기

		rq.login(member);
		//	rq에서 isLogined를 true로 바꿈
		
		return Ut.jsReplace("S-1", "로그인성공", afterLoginUri);
		//	로그인성공의 알림창을 띄우고 로그인전에 보고 있던 페이지로 이동
	}
}