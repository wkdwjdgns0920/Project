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
	
	

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {

		if (Ut.empty(relTypeCode)) {
			return Ut.jsHistoryBack("F-1", "relTypeCode 을(를) 입력해주세요");
		}
		if (Ut.empty(relId)) {
			return Ut.jsHistoryBack("F-2", "relId 을(를) 입력해주세요");
		}
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-3", "body 을(를) 입력해주세요");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = (int) writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", relId);
		}

		return Ut.jsReplace(writeReplyRd.getResultCode(), writeReplyRd.getMsg(), replaceUri);
	}
	
	
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public ResultData doModify(int id, String body) {
		
		System.out.println("==================================수정======================");
		System.out.println(id + "+" + body );
		
		Reply reply = replyService.getReply(id);

		if (reply == null) {
			
			return ResultData.from("F-1", Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return ResultData.from("F-2", Ut.f("%d번 댓글에 대한 권한이 없습니다", id));
		}

		ResultData modifyReplyRd = replyService.modifyReply(id, body);

//		if (Ut.empty(replaceUri)) {
//			switch (reply.getRelTypeCode()) {
//			case "article":
//				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
//				break;
//			}
//		}

		System.out.println("========2222222222222222222222222222222222222");
		
		return ResultData.from(modifyReplyRd.getResultCode(), modifyReplyRd.getMsg());
	}

	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, String replaceUri) {

		Reply reply = replyService.getReply(id);

		if (reply == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 댓글에 대한 권한이 없습니다", id));
		}

		ResultData deleteReplyRd = replyService.deleteReply(id);

		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}

		return Ut.jsReplace(deleteReplyRd.getResultCode(), deleteReplyRd.getMsg(), replaceUri);
	}

}