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
	public void deleteArticle(int id);

	@Update("""
			UPDATE article
			SET regDate = NOW(),
			updateDate = NOW(),
			title = #{title},
			`body` = #{body}
			WHERE id = #{id}
			""")
	public void modifyArticle(int id, String title, String body);

	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticle(int id);
	
	
	@Select("""
			SELECT *
			FROM article
			""")
	public List<Article> getArticles();

	@Insert("""
			INSERT INTO article
			SET regDate = NOW(),
			updateDate = NOW(),
			title = #{title},
			`body` = #{body}
			""")
	public void write(String title, String body);
	
	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastId();

}
