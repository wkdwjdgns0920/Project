package com.KoreaIT.jjh.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.jjh.project.vo.Article;

@Mapper
public interface ArticleRepository {

	@Delete("""
			DELETE FROM article
			WHERE id = #{id}
			""")
	public int deleteArticle(int id);

	@Update("""
			<script>
			UPDATE article
			SET
				<if test="title != null and title != ''">title = #{title},</if>
				<if test="body != null and body != ''">`body` = #{body},</if>
				updateDate= NOW()
			WHERE id = #{id}
			</script>
			""")
	public int modifyArticle(int id, String title, String body);

	@Select("""
			SELECT A.*, M.name AS extra_writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE A.id = #{id};
			""")
	public Article getForPrintArticle(int id);

	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticle(int id);

	@Select("""
			<script>
			SELECT A.*, M.name AS extra_writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE 1
				<if test="boardId != 0">
					AND A.boardId = #{boardId}
				</if>
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'" >
							AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
						</when>
						<when test="searchKeywordType == 'body'" >
							AND A.body LIKE CONCAT('%',#{searchKeyword},'%')
						</when>
						<otherwise>
							AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
							OR A.body LIKE CONCAT('%',#{searchKeyword},'%')
						</otherwise>
					</choose>
				</if>
			ORDER BY A.id DESC
			<if test="limitFrom >= 0">
			LIMIT #{limitFrom}, #{limit}
			</if>
			</script>
			""")
	public List<Article> getForPrintArticles(int boardId, int limitFrom, int limit, String searchKeywordType, String searchKeyword);

	@Select("""
			SELECT *
			FROM article
			ORDER BY id DESC
			""")
	public List<Article> getArticles();

	@Insert("""
			INSERT INTO article
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{actorId},
			boardId = #{boardId},
			title = #{title},
			`body` = #{body}
			""")
	public void write(int actorId, String title, String body, int boardId);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastId();

	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			<if test="searchKeyword != ''">
				<choose>
					<when test="searchKeywordType == 'title'" >
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<when test="searchKeywordType == 'body'" >
						AND A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<otherwise>
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
						OR A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</otherwise>
				</choose>
			</if>
			</script>
			""")
	public int getArticlesCount(int boardId, String searchKeywordType, String searchKeyword);

}