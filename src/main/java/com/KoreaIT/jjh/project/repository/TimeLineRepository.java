package com.KoreaIT.jjh.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.jjh.project.vo.TimeLine;

@Mapper
public interface TimeLineRepository {
	
	
	@Insert("""
			INSERT INTO timeLine
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{actorId},
			title = #{title},
			""")
	public void upLoad(int actorId, String title);
	
	
	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastId();

	
	@Select("""
			<script>
			SELECT T.*, M.name AS extra_writer
			FROM timeLine AS T
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE 1
			ORDER BY A.id DESC
			<if test="limitFrom >= 0">
			LIMIT #{limitFrom}, #{limit}
			</if>
			</script>
			""")
	public List<TimeLine> getTimeLines(int limitFrom, int limit);

	@Select("""
			SELECT COUNT(*)
			FROM timeLine
			""")
	public int getTimeLinesCount();
}
