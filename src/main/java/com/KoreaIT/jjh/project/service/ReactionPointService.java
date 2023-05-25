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
		}	// 로그인을 했는지 체크

		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode,
				relId);	//	로그인한 사용자가 relId번 게시글에 대한 추천점수의 합을 가져옴

		if (sumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "이미 추천을 했음", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}	//	추천점수의 합이 0이 아니면 이미 추천을 했고 추천점수의 합을 넘김

		return ResultData.from("S-1", "추천가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		//	추천점수 합이 0인경우 추천가능하다는 것과 추천점수의 합을 넘김
	}
	
	//	좋아요증가
	public ResultData likeReation(int actorId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.likeReation(actorId, relTypeCode, relId);
		//	relId번 게시글에 대해 좋아요를 증가시키고 성공한 행에 대한 갯수를 받음
		
		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요실패", "affectedRow", affectedRow);
		}
		//	성공한 행이 1이 아니면 좋아요 실패에 대한 데이터를 전달함

		switch (relTypeCode) {
		case "article":
			articleService.increaseLikePoint(relId);
			break;
		}
		//	좋아요를 누른 relTypeCode가 "article"이면 relId번의 article의 좋아요를 증가시킴
		
		int sumLikePoint = articleService.getSumLikePointById(relId);
		//	해당게시글에 대한 좋아요의 합 가져오기
		
		return ResultData.from("S-1", "좋아요", "sumLikePoint", sumLikePoint);
		//	좋아요 증가성공에 대한 데이터를 리턴
	}

	public ResultData disLikeReation(int actorId, String relTypeCode, int relId) {
		int affectedRow = reactionPointRepository.disLikeReation(actorId, relTypeCode, relId);
		//	싫어요를 성공한 열에 대한 갯수를 전달받음
		
		if (affectedRow != 1) {
			return ResultData.from("F-1", "싫어요실패", "affectedRow", affectedRow);
		}
		//	싫어요를 취소한 열이 1이 아닐경우 싫어요 실패에 대한 데이터를 전달

		switch (relTypeCode) {
		case "article":
			articleService.increaseDisLikePoint(relId);
			break;
		}
		//	싫어요를 한 게시글의 relTypeCode가 article일 경우 relId번 게시글에 싫어요를 증가

		return ResultData.from("S-1", "싫어요", "affectedRow", affectedRow);
		//	싫어요를 성공한 것에 대한 데이터를 리턴
	}

	public ResultData cancelLikeReactionPoint(int actorId, String relTypeCode, int relId) {

		reactionPointRepository.cancelLikeReactionPoint(actorId, relTypeCode, relId);
		//	로그인한 사용자가 relId번 게시글의 좋아요를 한 기록을 삭제함

		switch (relTypeCode) {
		case "article":
			articleService.decreaseLikeReactionPoint(relId);
			break;
		}
		//	좋아요를 취소한 relTypeCode가 article인 경우 relId번 게시글의 좋아요를 감소

		return ResultData.from("S-1", "좋아요취소");
		//	좋아요취소 성공에 대한 데이터를 넘김
	}

	public ResultData cancelDisLikeReactionPoint(int actorId, String relTypeCode, int relId) {
		
		reactionPointRepository.cancelDisLikeReactionPoint(actorId, relTypeCode, relId);
		//	로그인한 사용자가 relId번 게시글의 싫어요를 한 기록을 삭제함

		switch (relTypeCode) {
		case "article":
			articleService.decreaseDisLikeReactionPoint(relId);
			break;
		}
		//	싫어요를 취소한 relTypeCode가 article이면 relId번 게시글의 싫어요를 감소시킴

		return ResultData.from("S-1", "싫어요취소");
		//	싫어요취소에 대한 데이터를 리턴함
	}

}
