package com.KoreaIT.jjh.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("usr/reactionPoint/like")
	@ResponseBody
	public String like(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(acotrCanReactionRd.isFail()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		
		ResultData likeRd = reactionPointService.likeReation(rq.getLoginedMemberId(),relTypeCode,relId);
		
		if(likeRd.isFail()) {
			return Ut.jsHistoryBack(likeRd.getResultCode(), likeRd.getMsg());
		}
		
		return Ut.jsReplace(likeRd.getResultCode(), likeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
	}
	
	@RequestMapping("usr/reactionPoint/cancelLike")
	@ResponseBody
	public String cancelLike(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(acotrCanReactionRd.isSuccess()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		
		ResultData cancelLikeRd = reactionPointService.cancelLikeReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		
		if(cancelLikeRd.isFail()) {
			return Ut.jsHistoryBack(cancelLikeRd.getResultCode(), cancelLikeRd.getMsg());
		}
		
		return Ut.jsReplace(cancelLikeRd.getResultCode(), cancelLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
	}
	
	@RequestMapping("usr/reactionPoint/disLike")
	@ResponseBody
	public String disLike(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(acotrCanReactionRd.isFail()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		
		ResultData disLikeRd = reactionPointService.disLikeReation(rq.getLoginedMemberId(),relTypeCode,relId);
		
		if(disLikeRd.isFail()) {
			return Ut.jsHistoryBack(disLikeRd.getResultCode(), disLikeRd.getMsg());
		}
		
		return Ut.jsReplace(disLikeRd.getResultCode(), disLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
	}

	@RequestMapping("usr/reactionPoint/cancelDisLike")
	@ResponseBody
	public String cancelDisLike(String relTypeCode, int relId, String replaceUri) {
		
		ResultData acotrCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(acotrCanReactionRd.isSuccess()) {
			return Ut.jsHistoryBack(acotrCanReactionRd.getResultCode(), acotrCanReactionRd.getMsg());
		}
		
		ResultData cancelDisLikeRd = reactionPointService.cancelDisLikeReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		
		if(cancelDisLikeRd.isFail()) {
			return Ut.jsHistoryBack(cancelDisLikeRd.getResultCode(), cancelDisLikeRd.getMsg());
		}
		
		return Ut.jsReplace(cancelDisLikeRd.getResultCode(), cancelDisLikeRd.getMsg(), Ut.f("../article/detail?id=%d", relId));
	}

}