package com.KoreaIT.jjh.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.jjh.project.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Insert("""
			<script>
				INSERT INTO reply
				SET regDate = NOW(),
				updateDate = NOW(),
				memberId = #{actorId},
				relTypeCode = #{relTypeCode},
				relId =#{relId},
				`body`= #{body}
			</script>
			""")

	void writeReply(int actorId, String relTypeCode, int relId, String body);
	//	댓글작성

	@Select("""
			<script>
				SELECT LAST_INSERT_ID()
			</script>
			""")
	int getLastInsertId();
	
	@Select("""
			<script>
				SELECT R.*, M.nickname AS extra__writer
				FROM reply AS R
				LEFT JOIN `member` AS M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
				ORDER BY R.id ASC
				<if test="limitFrom >= 0">
					LIMIT #{limitFrom}, #{limit}
				</if>
			</script>
			""")
	List<Reply> getForPrintReplies(int actorId, String relTypeCode, int relId, int limitFrom, int limit);
	//	page에 들어갈 댓글들을 가져옴
	
	@Select("""
			SELECT *
			FROM `reply`
			WHERE id = #{id}
			""")
	Reply getReply(int id);
	//	id번의 댓글을 가져옴
	
	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
			""")
	int deleteReply(int id);
	//	댓글 삭제를 하고 실행한 열에 대한 갯수를 전달
	
	@Update("""
			UPDATE `reply`
			SET `body` = #{body}
			WHERE id = #{id}
			""")
	int modifyReply(int id, String body);
	//	id번 댓글을 수정하고 성공한 열에 대한 값을 전달
	
	@Select("""
			SELECT COUNT(*)
			FROM `reply`
			WHERE relId = #{reldId}
			""")
	int getRepliesCount(int relId);
	//	relId번 게시글에 대한 댓글수를 가져옴
}