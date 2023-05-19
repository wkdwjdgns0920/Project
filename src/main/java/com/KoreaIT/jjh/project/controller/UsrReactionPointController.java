package com.KoreaIT.jjh.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.service.ReactionPointService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrReactionPointController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	// 액션메서드
	
	//	게시글의 좋아요를 실행해주는 페이지
	@RequestMapping("usr/reactionPoint/like")
	@ResponseBody
	public String like(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		//	로그인한 회원이 게시글에 추천을 할 수 있는지에 대한 데이터
		
		if(acotrCanReactionRd.isFail()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		//	로그인한 회원이 게시글에 대해 추천을 할 수 없다면 알림창을 띄우고 뒤로가기
		
		ResultData likeRd = reactionPointService.likeReation(rq.getLoginedMemberId(),relTypeCode,relId);
		//	좋아요증가를 실행한 것에 대한 데이터를 받음
		
		if(likeRd.isFail()) {
			return Ut.jsHistoryBack(likeRd.getResultCode(), likeRd.getMsg());
		}
		//	좋아요증가에 실패한 경우 실패에 대한 메시지를 알림창에 띄우고 뒤로가기
		
		return Ut.jsReplace(likeRd.getResultCode(), likeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
		//	좋아요성공에 대한 알림창을 띄우고 좋아요를 누른 게시글로 이동
	}
	
	//	좋아요취소실행 페이지
	@RequestMapping("usr/reactionPoint/cancelLike")
	@ResponseBody
	public String cancelLike(String relTypeCode, int relId, @RequestParam(defaultValue = "/") String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		//	게시글에 대해 로그인한 사용자가 추천을 할 수 있는지에 대한 데이터를 받음
		
		if(acotrCanReactionRd.isSuccess()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		//	사용자가 추천을 할 수 있으면 알림창을 띄우고 뒤로가기
		
		ResultData cancelLikeRd = reactionPointService.cancelLikeReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		//	relId번 게시글의 좋아요취소에 대한 데이터
		
		if(cancelLikeRd.isFail()) {
			return Ut.jsHistoryBack(cancelLikeRd.getResultCode(), cancelLikeRd.getMsg());
		}
		//	좋아요취소실패시에 알림창을 띄우고 뒤로가기
		
		return Ut.jsReplace(cancelLikeRd.getResultCode(), cancelLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
		//	좋아요취소에 대한 알림창을 띄우고 좋아요취소를한 게시글의 디테일페이지로 이동
	}
	
	//	싫어요를 실행하는 페이지
	@RequestMapping("usr/reactionPoint/disLike")
	@ResponseBody
	public String disLike(String relTypeCode, int relId, @RequestParam(defaultValue = "/") String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		//	게시글에 대해 추천을 할 수 있는지에 대한 데이터
		
		if(acotrCanReactionRd.isFail()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		//	게시글에 대해 추천을 할 수 없으면 알림창을 띄우고 뒤로가기
		
		ResultData disLikeRd = reactionPointService.disLikeReation(rq.getLoginedMemberId(),relTypeCode,relId);
		//	싫어요를 실행한 데이터를 가짐
		
		if(disLikeRd.isFail()) {
			return Ut.jsHistoryBack(disLikeRd.getResultCode(), disLikeRd.getMsg());
		}
		//	싫어요를 실패했을 경우 알림창을 띄우고 뒤로가기
		
		return Ut.jsReplace(disLikeRd.getResultCode(), disLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
		//	싫어요성공에 대한 알림창을 띄우고 싫어요를 한 게시글의 디테일페이지로 이동
	}

	//	싫어요 취소 페이지
	@RequestMapping("usr/reactionPoint/cancelDisLike")
	@ResponseBody
	public String cancelDisLike(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		//	게시글에 대해 추천을 할 수 있는지에 대한 데이터
		
		if(acotrCanReactionRd.isSuccess()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		//	게시글에 대해 추천을 할 수 없으면 알림창을 띄우고 뒤로가기
		
		ResultData cancelDisLikeRd = reactionPointService.cancelDisLikeReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		//	relId번 게시글의 싫어요취소에 대한 데이터
		
		if(cancelDisLikeRd.isFail()) {
			return Ut.jsHistoryBack(cancelDisLikeRd.getResultCode(), cancelDisLikeRd.getMsg());
		}
		//	싫어요취소를 실패한경우 알림창을 띄우고 뒤로가기
		
		return Ut.jsReplace(cancelDisLikeRd.getResultCode(), cancelDisLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
		//	싫어요취소를 했다는 알림창을 띄우고 싫어요 취소한 게시글의 디테일페이지로 이동
	}

}