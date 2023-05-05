package com.KoreaIT.jjh.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.vo.Article;

@Controller
public class UsrArticleController {
	
	@Autowired
	ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 글은 존재하지 않습니다";
		}

		articleService.modifyArticle(id, title, body);

		return id + "번 글을 수정했습니다";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 글은 존재하지 않습니다";
		}

		articleService.deleteArticle(id);

		return id + "번 글을 삭제했습니다";
	}

	@RequestMapping("usr/article/detail")
	@ResponseBody
	public Object detail(int id) {
		Article article =articleService.getArticle(id);
		if(article == null) {
			return "해당 게시글은 존재하지 않습니다";			
		}
		return article;
	}

	@RequestMapping("usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		int id = articleService.write(title, body);
		
		Article article = articleService.getArticle(id);
		
		return article;
	}

	@RequestMapping("usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}
}
