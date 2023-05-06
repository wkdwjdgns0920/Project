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
	public ResultData doModify(HttpSession httpSession, int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인후에 이용해주세요");
		}
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-2", Ut.f("%d번 게시글은 없어", id), "article", article);
		}
		ResultData actorCanModifyRd = articleService.actorCanModify(loginedMemberId, article);
		if (actorCanModifyRd.isFail()) {
			return ResultData.from(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
		}

		ResultData modifyRd = articleService.modifyArticle(id, title, body);

		return ResultData.from(modifyRd.getResultCode(), modifyRd.getMsg(), modifyRd.getData1Name(),
				modifyRd.getData1());
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인후에 이용해주세요");
		}
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getArticle(id);
		if (article == null) {
			return ResultData.from("F-2", Ut.f("%d번 게시물이 존재하지 않습니다", id), "article", article);
		}

		ResultData actorCanDeleteRd = articleService.actorCanDelete(loginedMemberId, article);
		if (actorCanDeleteRd.isFail()) {
			return ResultData.from(actorCanDeleteRd.getResultCode(), actorCanDeleteRd.getMsg());
		}

		ResultData deleteRd = articleService.deleteArticle(id);

		return ResultData.from(deleteRd.getResultCode(), deleteRd.getMsg(), deleteRd.getData1Name(),
				deleteRd.getData1());
	}

	@RequestMapping("usr/article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getForPrintArticle(id);
		
		model.addAttribute("article",article);
		
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
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}
}
