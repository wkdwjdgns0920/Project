package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ArticleRepository;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public ResultData deleteArticle(int id) {
		int affectedRow = articleRepository.deleteArticle(id);

		if (affectedRow != 1) {
			return ResultData.from("F-4", "게시물삭제실패", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "게시물삭제", "affectedRow", affectedRow);

	}

	public ResultData modifyArticle(int id, String title, String body) {
		int affectedRow = articleRepository.modifyArticle(id, title, body);

		if (affectedRow != 1) {
			return ResultData.from("F-2", "게시물수정실패", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글수정", id), "affectedRow", affectedRow);
	}
	
	public ResultData actorCanModify(int actorId, Article article) {
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2", "해당게시글에 수정권한이 없습니다");
		}
		return ResultData.from("S-1", "수정가능");
	}
	
	public ResultData actorCanDelete(int actorId, Article article) {

		if (article.getMemberId() != actorId) {
			return ResultData.from("F-A", "삭제권한이 없습니다");
		}

		return ResultData.from("S-A", "삭제가능");
	}
	
	private void updateForPrintData(int actorId, Article article) {
		if (article == null) {
			return;
		}
		
		ResultData actorCanModifyRd = actorCanModify(actorId, article);
		article.setActorCanModify(actorCanModifyRd.isSuccess());
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setActorCanDelete(actorCanDeleteRd.isSuccess());
	}
	

	public Article getForPrintArticle(int actorId, int id) {

		Article article = articleRepository.getForPrintArticle(id);

		updateForPrintData(actorId, article);

		return article;
	}

	public Article getArticle(int id) {
		
		return articleRepository.getArticle(id);
	}

	public int write(int actorId, String title, String body) {
		articleRepository.write(actorId, title, body);

		return articleRepository.getLastId();
	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

	public int getLastId() {
		return articleRepository.getLastId();
	}

	public List<Article> getForPrintArticles() {

		return articleRepository.getForPrintArticles();
	}

}