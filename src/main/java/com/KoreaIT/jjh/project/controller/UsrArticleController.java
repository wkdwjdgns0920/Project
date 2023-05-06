package com.KoreaIT.jjh.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.ResultData;

@Controller
public class UsrArticleController {
	
	@Autowired
	ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1",Ut.f("%d 번 게시글은 없어",id),"article",article);
		}
		
		ResultData modifyRd = articleService.modifyArticle(id, title, body);

		return ResultData.from(modifyRd.getResultCode(), modifyRd.getMsg(),modifyRd.getData1Name(),modifyRd.getData1());
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다",id),"article",article);
		}

		ResultData deleteRd = articleService.deleteArticle(id);

		return ResultData.from(deleteRd.getResultCode(), deleteRd.getMsg(),deleteRd.getData1Name(),deleteRd.getData1());
	}

	@RequestMapping("usr/article/detail")
	@ResponseBody
	public Object detail(int id) {
		Article article =articleService.getArticle(id);
		if(article == null) {
			return ResultData.from("F-1", "해당게시글은 없어","article",article);			
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
