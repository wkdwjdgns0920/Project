package com.KoreaIT.jjh.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.project.service.ArticleService;
import com.KoreaIT.jjh.project.service.MemberService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.Member;
import com.KoreaIT.jjh.project.vo.Rq;

@Controller
public class UsrAdminController {

	@Autowired
	ArticleService articleService;
	@Autowired
	MemberService memberService;
	@Autowired
	Rq rq;

	// 액션메서드
	
	@RequestMapping("usr/admin/deleteMembers")
	@ResponseBody
	public String deleteMembers(@RequestParam(defaultValue = "") String ids) {
		
		List<Integer> memberIds = new ArrayList<>();
		
		for(String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}
		
		memberService.deleteMembers(memberIds);
		
		return Ut.jsReplace("S-1", "선택된 회원을 탈퇴하였습니다", "../admin/list");
	}
	
	@RequestMapping("usr/admin/deleteArticles")
	@ResponseBody
	public String deleteArticles(@RequestParam(defaultValue = "") String ids) {
		
		List<Integer> articleIds = new ArrayList<>();
		
		for(String idStr : ids.split(",")) {
			articleIds.add(Integer.parseInt(idStr));
		}
		
		articleService.deleteArticles(articleIds);
		
		return Ut.jsReplace("S-1", "선택된 게시글을 삭제하였습니다", "../admin/list");
	}
	
	@RequestMapping("usr/admin/list")
	public String showList(Model model, @RequestParam(defaultValue = "0") int boardId,
			@RequestParam(defaultValue = "loginId,name,nickname") String mSearchKeywordTypeCode,
			@RequestParam(defaultValue = "") String mSearchKeyword, @RequestParam(defaultValue = "1") int mPage,
			@RequestParam(defaultValue = "title,body") String aSearchKeywordTypeCode,
			@RequestParam(defaultValue = "") String aSearchKeyword, @RequestParam(defaultValue = "1") int aPage) {
		
		if(!rq.isLogined()) {
			return rq.jsHistoryBackOnView("로그인 후에 이용해주세요");
		}
		int authLevel = rq.getLoginedMember().getAuthLevel();
		System.out.println(authLevel+"==============================================================");
		if(authLevel != 7) {
			return rq.jsHistoryBackOnView("권한이 없습니다");
		}
		
		
		int membersCount = memberService.getMembersCount(mSearchKeywordTypeCode, mSearchKeyword);
		int articlesCount = articleService.getArticlesCount(boardId, aSearchKeywordTypeCode, aSearchKeyword);

		int memberItemsInAPage = 10;
		int memberPagesCount = (int) Math.ceil((double) membersCount / memberItemsInAPage);

		int articleItemsInAPage = 10;
		int articlePagesCount = (int) Math.ceil((double) articlesCount / articleItemsInAPage);

		List<Member> members = memberService.getForPrintMembers(mSearchKeywordTypeCode, mSearchKeyword,
				memberItemsInAPage, mPage);

		List<Article> articles = articleService.getForPrintArticles(boardId, articleItemsInAPage, aPage,
				aSearchKeywordTypeCode, aSearchKeyword);
		
		model.addAttribute("authLevel", authLevel);
		model.addAttribute("mSearchKeywordTypeCode", mSearchKeywordTypeCode);
		model.addAttribute("mSearchKeyword", mSearchKeyword);
		model.addAttribute("aSearchKeywordTypeCode", aSearchKeywordTypeCode);
		model.addAttribute("aSearchKeyword", aSearchKeyword);

		model.addAttribute("articlePagesCount", articlePagesCount);
		model.addAttribute("memberPagesCount", memberPagesCount);
		
		model.addAttribute("aPage", aPage);
		model.addAttribute("mPage", mPage);
		
		model.addAttribute("membersCount", membersCount);
		model.addAttribute("articlesCount", articlesCount);

		model.addAttribute("members", members);
		model.addAttribute("articles", articles);
		
		
		return "usr/admin/list";
	}
	
}