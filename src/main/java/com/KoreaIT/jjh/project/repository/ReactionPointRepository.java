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

	
	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{actorId}
			""")
	void cancelLikeReactionPoint(int actorId, String relTypeCode, int relId);

	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{actorId}
			""")
	void cancelDisLikeReactionPoint(int actorId, String relTypeCode, int relId);
	
	

}