package com.KoreaIT.jjh.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.service.BoardService;
import com.KoreaIT.jjh.project.service.ReactionPointService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.Board;
import com.KoreaIT.jjh.project.vo.ResultData;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	BoardService boardService;
	@Autowired
	ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	// 액션메서드

	@RequestMapping("usr/article/list")
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "title,body") String searchKeywordType,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			return rq.jsHistoryBackOnView("없는 board입니다");
		}

		int itemInAPage = 10;

		List<Article> articles = articleService.getForPrintArticles(boardId, itemInAPage, page, searchKeywordType,
				searchKeyword);

		int articlesCount = articleService.getArticlesCount(boardId, searchKeywordType, searchKeyword);
		int pagesCount = (int) Math.ceil(articlesCount / (double) itemInAPage);
		int startPage = 1;
		int endPage = 10;

		model.addAttribute("searchKeywordType", searchKeywordType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("board", board);
		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("usr/article/modify")
	public String modify(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBackOnView("게시글이 존재하지 않습니다");
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanModifyRd.getMsg());
		}

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요 Con");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		if (actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
		}

		ResultData modifyRd = articleService.modifyArticle(id, title, body);

		return Ut.jsReplace(modifyRd.getResultCode(), modifyRd.getMsg(), Ut.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요Con");
		}

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
		}

		ResultData actorCanDeleteRd = articleService.actorCanDelete(rq.getLoginedMemberId(), article);
		if (actorCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(actorCanDeleteRd.getResultCode(), actorCanDeleteRd.getMsg());
		}

		ResultData deleteRd = articleService.deleteArticle(id);

		if (deleteRd.isFail()) {
			Ut.jsHistoryBack(deleteRd.getResultCode(), deleteRd.getMsg());
		}

		return Ut.jsReplace("S-1", Ut.f("%d번 게시글 삭제!", id), Ut.f("list", id));
	}

	@RequestMapping("usr/article/detail")
	public String showDetail(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		ResultData actorCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), "article", id);
		
		
		model.addAttribute("actorCanReaction",actorCanReactionRd.isSuccess());
		model.addAttribute("article", article);
		
		if(actorCanReactionRd.isFail()) {
			int sumReactionPointByMemberId = (int) actorCanReactionRd.getData1();
			
			if(sumReactionPointByMemberId > 0) {
				model.addAttribute("actorCanCancelLikeReaction", true);
			} else {
				model.addAttribute("actorCanCancelDisLikeReaction", true);
			}
		}

		return "usr/article/detail";
	}

	@RequestMapping("usr/article/doIncreaseHitCount")
	@ResponseBody
	public ResultData doIncreaseHitCount(int id) {

		ResultData increaseHitCountRd = articleService.doIncreaseHitCount(id);

		if (increaseHitCountRd.isFail()) {
			return increaseHitCountRd;
		}
		
		return ResultData.newData(increaseHitCountRd, "hitCount", articleService.getArticleHitCount(id));
	}

	@RequestMapping("usr/article/write")
	public String write(String title, String body) {

		return "usr/article/write";
	}

	@RequestMapping("usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String title, String body) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요Con");
		}

		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		int id = articleService.write(rq.getLoginedMemberId(), title, body, boardId);
		System.out.println(rq.getLoginedMemberId() + "===========글쓰기!!===========");

		return Ut.jsReplace("S-1", "글쓰기", Ut.f("detail?id=%d", id));
	}

}