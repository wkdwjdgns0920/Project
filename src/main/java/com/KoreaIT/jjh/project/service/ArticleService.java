package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ArticleRepository;
import com.KoreaIT.jjh.project.vo.Article;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
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
