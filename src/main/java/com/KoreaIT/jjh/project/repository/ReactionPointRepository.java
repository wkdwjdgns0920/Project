package com.KoreaIT.jjh.project.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {
	
	
	@Select("""
				SELECT IFNULL(SUM(RP.point),0)
				FROM reactionPoint AS RP
				WHERE RP.relTypeCode = #{relTypeCode}
				AND RP.relId = #{relId}
				AND RP.memberId = #{actorId}
			""")
	int getSumReactionPointByMemberId(int actorId, String relTypeCode, int relId);
	//	relId번 게시글에 대한 사용자의 추천점수 합
	
	@Insert("""
				INSERT INTO reactionPoint
				SET regDate = NOW(),
				updateDate = NOW(),
				relTypeCode = #{relTypeCode},
				relId = #{relId},
				memberId = #{actorId},
				`point` = 1
			""")
	int likeReation(int actorId, String relTypeCode, int relId);
	//	게시글에 좋아요를 1증가시킴
	
	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{actorId},
			`point` = -1
		""")
	int disLikeReation(int actorId, String relTypeCode, int relId);
	//	좋아요를 취소하고 성공한 열에 대한 갯수를 전달

	
	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{actorId}
			""")
	void cancelLikeReactionPoint(int actorId, String relTypeCode, int relId);
	//	사용자가 relId번 게시글에 대해 좋아요기록을 삭제함
	
	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{actorId}
			""")
	void cancelDisLikeReactionPoint(int actorId, String relTypeCode, int relId);
	// 사용자가 relId번 게시글의 싫어요를 한 기록을 삭제함
	
}