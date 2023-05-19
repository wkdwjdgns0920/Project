package com.KoreaIT.jjh.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.service.ReplyService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Reply;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	private Rq rq;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleService articleSevice;
	
	
	//	댓글쓰기 실행 페이지
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {

		if (Ut.empty(relTypeCode)) {
			return Ut.jsHistoryBack("F-1", "relTypeCode 을(를) 입력해주세요");
		}
		//	게시글의 코드가 없다면 알림창을 띄우고 뒤로가기
		
		if (Ut.empty(relId)) {
			return Ut.jsHistoryBack("F-2", "relId 을(를) 입력해주세요");
		}
		//	게시글에 대한 번호의 정보가 없다면 알림창을 띄우고 뒤로가기
		
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-3", "body 을(를) 입력해주세요");
		}
		//	댓글내용이 없다면 알림창을 띄우고 뒤로가기

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);
		//	댓글작성에 대한 데이터
		
		int id = (int) writeReplyRd.getData1();
		//	마지막으로 작성된 댓글의 id를 가져옴
		
		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", relId);
		}
		//	댓글작성후에 돌아갈 데이터가 비었으면 댓글작성한 게시글의 디테일페이지로 replaceUri를 설정

		return Ut.jsReplace(writeReplyRd.getResultCode(), writeReplyRd.getMsg(), replaceUri);
		//	댓글작성에 대한 알림창을 띄우고 댓글을 작성한 게시글의 디테일페이지로 이동
	}
	
	
	//	댓글을 수정실행을 하는 페이지
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public ResultData doModify(int id, String body) {
		
		Reply reply = replyService.getReply(id);
		// id번의 댓글을 가져옴

		if (reply == null) {
			return ResultData.from("F-1", Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}
		//	가져온 댓글이 없다면 댓글이 존재하지 않다는 데이터를 리턴
		
		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return ResultData.from("F-2", Ut.f("%d번 댓글에 대한 권한이 없습니다", id));
		}
		//	가져온 댓글의 작성자id와 로그인한 회원의 id가 일치하지 않으면 댓글에 대한 권한이 없다는 데이터를 리턴 

		ResultData modifyReplyRd = replyService.modifyReply(id, body);
		//	댓글 수정에 대한 데이터

//		if (Ut.empty(replaceUri)) {
//			switch (reply.getRelTypeCode()) {
//			case "article":
//				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
//				break;
//			}
//		}
		
		return ResultData.from(modifyReplyRd.getResultCode(), modifyReplyRd.getMsg());
		//	댓글수정 성공에 대한 데이터를 리턴
	}
	
	//	댓글 삭제실행 페이지
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, String replaceUri) {

		Reply reply = replyService.getReply(id);
		// id번의 댓글을 가져옴
		
		if (reply == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}
		//	가져온 댓글이 없다면 댓글이 존재하지 않다는 데이터를 리턴
		
		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 댓글에 대한 권한이 없습니다", id));
		}
		//	가져온 댓글의 작성자id와 로그인한 회원의 id가 일치하지 않으면 댓글에 대한 권한이 없다는 데이터를 리턴 
		
		ResultData deleteReplyRd = replyService.deleteReply(id);
		//	댓글삭제에 대한 정보

		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}
		//	댓글삭제후에 돌아갈 페이지에 대한 정보가 없을경우, 댓글의 relTypeCode가 article인 경우 삭제한 댓글의 게시글에 디테일페이지로 replaceUri를 설정

		return Ut.jsReplace(deleteReplyRd.getResultCode(), deleteReplyRd.getMsg(), replaceUri);
		//	댓글삭제 후 알림창을 띄우고 삭제한 댓글의 게시글에 디테일페이지로 이동
	}

}