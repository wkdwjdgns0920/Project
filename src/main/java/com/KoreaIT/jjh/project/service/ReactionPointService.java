package com.KoreaIT.jjh.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ReactionPointRepository;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class ReactionPointService {

	@Autowired
	private ReactionPointRepository reactionPointRepository;
	@Autowired
	private ArticleService articleService;

	public ResultData actorCanReaction(int actorId, String relTypeCode, int relId) {

		if (actorId == 0) {
			return ResultData.from("F-1", "로그인을 해주세요");
		}

		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode,
				relId);

		if (sumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "이미 추천을 했음", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}

		return ResultData.from("S-1", "추천가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}

	public ResultData likeReation(int actorId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.likeReation(actorId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요실패", "affectedRow", affectedRow);
		}

		switch (relTypeCode) {
		case "article":
			articleService.increaseLikePoint(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요", "affectedRow", affectedRow);
	}

	public ResultData disLikeReation(int actorId, String relTypeCode, int relId) {
		int affectedRow = reactionPointRepository.disLikeReation(actorId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "싫어요실패", "affectedRow", affectedRow);
		}

		switch (relTypeCode) {
		case "article":
			articleService.increaseDisLikePoint(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요", "affectedRow", affectedRow);
	}

	public ResultData cancelLikeReactionPoint(int actorId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.cancelLikeReactionPoint(actorId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요취소실패", "affectedRow", affectedRow);
		}

		switch (relTypeCode) {
		case "article":
			articleService.decreaseLikeReationPoint(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요취소", "affectedRow", affectedRow);
	}

	public ResultData cancelDisLikeReactionPoint(int actorId, String relTypeCode, int relId) {
		
		int affectedRow = reactionPointRepository.cancelDisLikeReactionPoint(actorId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요취소실패", "affectedRow", affectedRow);
		}

		switch (relTypeCode) {
		case "article":
			articleService.decreaseDisLikeReationPoint(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요취소", "affectedRow", affectedRow);
	}

}
