package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.MemberRepository;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Member;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	@Value("${custom.siteName}")
	private String siteName;

	private MailService mailService;

	public MemberService(MailService mailService, MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		this.mailService = mailService;
	}
	
	//	임시비밀번호를 email로 전송하는 메서드
	public ResultData notifyTempLoginPwByEmail(Member actor) {
		String title = "[" + siteName + "] 임시 패스워드 발송";	//	비밀번호찾기 메일의 제목
		String tempPassword = Ut.getTempPassword(6);	//	임시 비밀번호
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";	// 메일의 내용
		body += "<a href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";	//	메일의 내용

		ResultData sendResultData = mailService.send(actor.getEmail(), title, body);
		//	메일전송에 대한 결과정보

		if (sendResultData.isFail()) {
			return sendResultData;
		}
		//	메일전송 실패시에 리턴하는 정보
		
		setTempPassword(actor, tempPassword);
		//	임시비밀번호로 회원정보를 수정

		return ResultData.from("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다.");
		//	임시비밀번호로 회원정보를 수정하고 메일전송에 성공했다는 결과데이터
	}
	
	//	임시비밀번호로 회원의 정보를 수정함
	private void setTempPassword(Member actor, String tempPassword) {
		memberRepository.modify(actor.getId(), tempPassword, null, null, null, null);
		//	회원정보 수정
	}

	public ResultData modify(int actorId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		int affectedRow = memberRepository.modify(actorId, loginPw, name, nickname, cellphoneNum, email);
		//	회원정보를 수정하고 수정한 행에 대한 갯수를 받음

		if (affectedRow != 1) {
			return ResultData.from("F-2", "회원정보수정 실패", "affectedRow", affectedRow);
		}
		//	수정한 행이 1이 아니면 수정실패에 대한 정보를 전달함

		return ResultData.from("S-1", "회원정보수정!", "affectedRow", affectedRow);
		//	회원정보 수정을 성공한 것에 대한 데이터를 전달함
	}

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		Member existsMember = getMemberByLoginId(loginId);
		//	입력받은 로그인 아이디와 일치한 회원의 정보를 가져옴

		if (existsMember != null) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s)입니다", loginId));
		}
		//	입력받은 로그인아이디와 일치한 회원이 있으면 이미 사용중인 아이디인 것을 알리는 데이터를 리턴

		existsMember = getMemberByNameAndEmail(name, email);
		//	입력받은 이름과 이메일이 일치한 회원의 정보를 가져옴
		
		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다", name, email));
		}
		//	입력받은 이름과 이메일이 일치한 회원이 있으면 사용중인 이름과 이메일이라는 데이터를 리턴
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		//	회원가입을	실행(member테이블에 insert)
		
		int id = memberRepository.getLastInsertId();
		//	마지막에 회원가입된 회원의 id를 가져옴

		return ResultData.from("S-1", "회원가입이 완료되었습니다", "id", id);
		//	회원가입이 완료되었다는 데이터와 회원의 id를 리턴함
	}
	
	//	회원의 이름과 이메일을 통해 일치하는 회원의 정보를 가져옴
	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}
	
	//	loginId를 통해서 회원에 대한 정보를 가져옴
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	//	id에 대한 회원의 정보를 가져옴
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public int getMembersCount(String mSearchKeywordTypeCode, String mSearchKeyword) {
		
		return memberRepository.getMembersCount(mSearchKeywordTypeCode,mSearchKeyword);
	}

	public List<Member> getForPrintMembers(String mSearchKeywordTypeCode, String mSearchKeyword,
			int memberItemsInAPage, int mPage) {
		
		int limitFrom = (mPage - 1) * memberItemsInAPage;
		int limitTake = memberItemsInAPage;
		List<Member> members = memberRepository.getForPrintMembers(mSearchKeywordTypeCode, mSearchKeyword,
				limitFrom, limitTake);
		
		return members;
	}

	public void deleteMembers(List<Integer> memberIds) {
		for (int memberId : memberIds) {
			Member member = getMemberById(memberId);

			if (member != null) {
				deleteMember(member);
			}
		}
	}

	private void deleteMember(Member member) {
		memberRepository.deleteMember(member.getId());
	}

}
