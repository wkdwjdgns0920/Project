package com.KoreaIT.jjh.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String doModify(HttpSession httpSession, int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
		}
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}
		if(article.getMemberId() != loginedMemberId) {
			return Ut.jsHistoryBack("F-3", Ut.f("%d번 게시글에대한 수정권한이 없습니다", id));
		}

		ResultData modifyRd = articleService.modifyArticle(id, title, body);

		return Ut.jsReplace("S-1", Ut.f("%d번 게시글 수정!", id), Ut.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
		}
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);
		
		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}

		if(article.getId() != loginedMemberId) {
			return Ut.jsHistoryBack("F-3","삭제 권한이 없음");
		}

		ResultData deleteRd = articleService.deleteArticle(id);

		return Ut.jsReplace("S-1", Ut.f("%d번 게시글 삭제!", id), Ut.f("list", id));
	}

	@RequestMapping("usr/article/detail")
	public String showDetail(Model model, HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인후에 이용해주세요", "isLogined", isLogined);
		}
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		int id = articleService.write(loginedMemberId, title, body);

		Article article = articleService.getArticle(id);

		return ResultData.from("S-1", "글쓰기성공", "article", article);
	}

	@RequestMapping("usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}
}
