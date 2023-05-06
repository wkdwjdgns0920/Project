package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ArticleRepository;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public ResultData deleteArticle(int id) {
		int affectedRow = articleRepository.deleteArticle(id);
		
		if(affectedRow != 1) {
			return ResultData.from("F-2", "게시물삭제실패","affectedRow",affectedRow);
		}
		
		return ResultData.from("S-1", "게시물삭제","affectedRow",affectedRow);
		
	}

	public ResultData modifyArticle(int id, String title, String body) {
		int affectedRow = articleRepository.modifyArticle(id, title, body);
		
		if(affectedRow != 1) {
			return ResultData.from("F-2", "게시물수정실패", "affectedRow", affectedRow);
		}
		
		return ResultData.from("S-1", "게시물수정", "affectedRow", affectedRow);
	}

	public Article getArticle(int id) {
		
		return articleRepository.getArticle(id);
	}

	public int write(String title, String body) {
		articleRepository.write(title, body);
		
		return articleRepository.getLastId();
	}

	public List<Article> getArticles() {
		
		return articleRepository.getArticles();
	}

	public int getLastId() {
		return articleRepository.getLastId();
	}

}