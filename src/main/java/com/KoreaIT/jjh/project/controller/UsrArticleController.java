package com.KoreaIT.jjh.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrArticleController {

	@Autowired
	ArticleService articleService;

	// 액션메서드
	
	@RequestMapping("usr/article/modify")
	public String modify(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(article == null) {
			return rq.jsHistoryBackOnView("게시글이 존재하지 않습니다");
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(),article);
		
		if(actorCanModifyRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanModifyRd.getMsg());
		}
		
		model.addAttribute("article",article);
		
		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요 Con");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(),article);
		if(actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
		}

		ResultData modifyRd = articleService.modifyArticle(id, title, body);

		return Ut.jsReplace(modifyRd.getResultCode(),modifyRd.getMsg(), Ut.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요Con");
		}

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}

		if (article.getId() != rq.getLoginedMemberId()) {
			System.out.println(article.getId());
			System.out.println(rq.getLoginedMemberId());
			return Ut.jsHistoryBack("F-3", "삭제 권한이 없음@@");
		}

		ResultData deleteRd = articleService.deleteArticle(id);
		
		if(deleteRd.isFail()) {
			Ut.jsHistoryBack(deleteRd.getResultCode(), deleteRd.getMsg());
		}

		return Ut.jsReplace("S-1", Ut.f("%d번 게시글 삭제!", id), Ut.f("list", id));
	}

	@RequestMapping("usr/article/detail")
	public String showDetail(Model model, HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("usr/article/write")
	public String write(HttpServletRequest req, String title, String body) {

		return "usr/article/write";
	}

	@RequestMapping("usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요Con");
		}

		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		int id = articleService.write(rq.getLoginedMemberId(), title, body);
		System.out.println(rq.getLoginedMemberId() + "===========글쓰기!!===========");

		return Ut.jsReplace("S-1", "글쓰기", Ut.f("detail?id=%d", id));
	}

	@RequestMapping("usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}
}