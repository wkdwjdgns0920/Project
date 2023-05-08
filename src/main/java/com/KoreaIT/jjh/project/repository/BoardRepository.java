package com.KoreaIT.jjh.project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.jjh.project.vo.Board;

@Mapper
public interface BoardRepository {
	
	
	@Select("""
			SELECT *
			FROM board AS B
			WHERE id = #{boardId}
			AND delStatus = 0
			""")
	Board getBoardById(int boardId);


}