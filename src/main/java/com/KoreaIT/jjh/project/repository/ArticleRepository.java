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
	//	id번 게시글을 삭제하고 성공한 열에 대한 갯수를 전달

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
	//	id번 게시글을 수정하고 수정한 열에대한 갯수를 넘김
	

	@Select("""
			SELECT A.*, M.name AS extra_writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE A.id = #{id};
			""")
	public Article getForPrintArticle(int id);
	// id번 게시글과 작성자를 가져옴

	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticle(int id);
	//	id번 게시글에 대한 정보를 가져옴
	
	
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
	//	boardId에 맞게 searchKeyword가 있다면 그에 맞는 게시글들을 limitFrom개 가져옴
	

	@Select("""
			SELECT *
			FROM article
			ORDER BY id DESC
			""")
	public List<Article> getArticles();
	//	게시글들을 내림차순으로 가져옴

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
	//	게시글 작성

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastId();
	//	마지막으로 작성된 게시글의 id를 가져옴

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
	//	boardId에 맞게 searchKeyword가 있다면 그에 맞는 갯수를 가져옴
	
	@Update("""
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			""")
	public int doIncreaseHitCount(int id);
	// id번 게시글의 조회수를 1증가시킴
	
	@Select("""
			SELECT hitCount
			FROM article
			WHERE id = #{id}
			""")
	public int getArticleHitCount(int id);
	//	id번 게시글에 조회수를 가져옴
	
	
	@Update("""
			UPDATE article
			SET likePoint = likePoint + 1
			WHERE id = #{relId}
			""")
	public int increaseLikePoint(int relId);
	//	relId번의 게시물의 좋아요를 1증가시킴
	
	
	@Update("""
			UPDATE article
			SET DisLikePoint = DisLikePoint + 1
			WHERE id = #{relId}
			""")
	public int increaseDisLikePoint(int relId);
	//	relId번 게시글의 싫어요증가
	
	@Update("""
			UPDATE article
			SET likePoint = likePoint - 1
			WHERE id = #{relId}
			""")
	public int decreaseLikeReationPoint(int relId);
	//	좋아요감소를 함
	
	
	@Update("""
			UPDATE article
			SET DisLikePoint = DisLikePoint - 1
			WHERE id = #{relId}
			""")
	public int decreaseDisLikeReationPoint(int relId);
	//	싫어요 감소

}