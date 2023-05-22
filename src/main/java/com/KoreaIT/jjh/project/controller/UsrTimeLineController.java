package com.KoreaIT.jjh.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.KoreaIT.jjh.project.service.GenFileService;
import com.KoreaIT.jjh.project.service.ReplyService;
import com.KoreaIT.jjh.project.service.TimeLineService;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Rq;
import com.KoreaIT.jjh.project.vo.TimeLine;

@Controller
public class UsrTimeLineController {

	@Autowired
	TimeLineService timeLineService;
	@Autowired
	ReplyService replyService;
	@Autowired
	private Rq rq;
	@Autowired
	private GenFileService genFileService;

	// 액션메서드

	@RequestMapping("usr/timeLine/doWrite")
	@ResponseBody
	public String doWrite(String title, MultipartRequest multipartRequest) {

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
		}

		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-2", "제목을 입력해주세요");
		}
		

		int id = timeLineService.upLoad(rq.getLoginedMemberId(), title);
		//	게시글작성하고 마지막으로 작성된 게시글의 id를 가져옴
		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);

			if (multipartFile.isEmpty() == false) {
				genFileService.save(multipartFile, id);
			}
		}

		return Ut.jsReplace("S-1", "업로드", "/");
	}

	@RequestMapping("usr/timeLine/write")
	public String write() {

		return "usr/timeLine/write";
	}
	
	@RequestMapping("usr/timeLine/list")
	public String showList(Model model, @RequestParam(defaultValue = "1") int page) {

		int itemInAPage = 10; // 한 페이지당 들어갈 게시글의 갯수 설정

		List<TimeLine> timeLines = timeLineService.getTimeLines(itemInAPage, page);
		
		int timeLinesCount = timeLineService.getTimeLinesCount();

		int pagesCount = (int) Math.ceil(timeLinesCount / (double) itemInAPage);
		// 페이지의 갯수를 설정,게시글 갯수/10


		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("timeLinesCount", timeLinesCount);
		model.addAttribute("timeLines", timeLines);


		return "usr/timeLine/list";
	}

//	@RequestMapping("usr/article/detail")
//	public String showDetail(Model model, int id, @RequestParam(defaultValue = "1") int page) {
//
//		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
//		// 게시글의 번호에 맞는 게시글과 작성자이름을 가져옴
//		
//		if(article == null) {
//			return rq.jsHistoryBackOnView("게시글이 존재하지 않습니다");
//		}
//
//		ResultData actorCanReactionRd = reactionPointService.actorCanReaction(rq.getLoginedMemberId(), "article", id);
//		// 좋아요와 싫어요를 누를 수 있는지 확인하고 사용자가 이 게시글에 추천을한 총 점수를 가져옴
//
//		int itemInAPage = 5;
//		// 댓글이 한 페이지에 보여지는 갯수 설정
//
//		List<Reply> replies = replyService.getForPrintReplies(rq.getLoginedMemberId(), "article", id, itemInAPage,
//				page); // 페이지 번호에 맞는 댓글목록을 가져옴
//
//		int repliesCount = replyService.getRepliesCount(id); // id번 게시글에 대해 달린 댓글수를 가져옴
//
//		int pagesCount = (int) Math.ceil(repliesCount / (double) itemInAPage); // 댓글페이지가 몇페이지 필요한지 구함
//
//		model.addAttribute("pagesCount", pagesCount); // 댓글페이지를 JSP파일로 넘김
//		model.addAttribute("page", page); // 현재 몇번째 페이지인지 넘김
//		model.addAttribute("replies", replies); // 페이지에 맞는 댓글목록을 넘김
//		model.addAttribute("repliesCount", repliesCount); // 댓글의 갯수를 넘김
//		model.addAttribute("actorCanReaction", actorCanReactionRd.isSuccess()); // 로그인한 사용자가 좋아요와 싫어요를 누를 수 있는지
//																				// boolean값으로 넘김
//		model.addAttribute("article", article); // id번째 게시글을 넘김
//
//		if (actorCanReactionRd.getResultCode().equals("F-2")) { // 로그인한 사용자가 추천을 할 수 없을 경우
//			int sumReactionPointByMemberId = (int) actorCanReactionRd.getData1();
//			// 로그인한 사용자의 총 추천점수
//
//			if (sumReactionPointByMemberId > 0) {
//				model.addAttribute("actorCanCancelLikeReaction", true); // 추천점수의 합이 0보다 클 경우 좋아요를 취소할 수 있는지의 boolean값을
//																		// 넘김
//			} else {
//				model.addAttribute("actorCanCancelDisLikeReaction", true); // 추천점수의 합이 0보다 작을 경우 싫어요 취소할 수 있는지의
//																			// boolean값을 넘김
//			}
//		}
//
//		return "usr/article/detail";
//	}
//
//	@RequestMapping("usr/article/modify") // 게시글 수정페이지를 보여줌
//	public String modify(Model model, int id) {
//
//		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
//		// id번 게시글을 가져옴
//
//		if (article == null) {
//			return rq.jsHistoryBackOnView("게시글이 존재하지 않습니다");
//		} // id번 게시글이 없으면 뒤로돌아가기
//
//		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
//		// 로그인한 사용자가 수정가능한지 체크
//
//		if (actorCanModifyRd.isFail()) {
//			return rq.jsHistoryBackOnView(actorCanModifyRd.getMsg());
//		}
//		// 로그인한 사용자가 게시글을 수정할 수 없으면 뒤로가기
//
//		model.addAttribute("article", article); // 게시글에 대한 정보를 JSP페이지로 넘김
//
//		return "usr/article/modify";
//	}
//
//	@RequestMapping("/usr/article/doModify") // 게시글수정에 대한 정보를 전달받고 수정함
//	@ResponseBody
//	public String doModify(int id, String title, String body) {
//
//		if (rq.isLogined() == false) {
//			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
//		}
//		// 로그인을 했는지에 대한 체크
//
//		Article article = articleService.getArticle(id); // id번 게시글에 대한 정보를 가져옴
//
//		if (article == null) {
//			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
//		}
//		// id번 게시글이 없으면 게시글이 없다는 알림창을 보여주고 뒤로가기
//
//		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
//		// 로그인한 사용자가 수정할 수 있는지 체크
//
//		if (actorCanModifyRd.isFail()) {
//			return Ut.jsHistoryBack(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
//		}
//		// 로그인한 사용자가 수정할수 없다면 수정할 수 없다는 알림창을 띄우고 뒤로가기
//
//		ResultData modifyRd = articleService.modifyArticle(id, title, body);
//		// 게시글을 수정하였는지에 대한 정보를 가짐
//
//		if (modifyRd.isFail()) {
//			return Ut.jsHistoryBack(modifyRd.getResultCode(), modifyRd.getMsg());
//		}
//		// 게시글 수정이 실패하였으면 수정실패에 대한 메시지를 알림창에 띄우고 뒤로가기
//
//		return Ut.jsReplace(modifyRd.getResultCode(), modifyRd.getMsg(), Ut.f("detail?id=%d", id));
//		// 게시글 수정성공에 대한 알림창을 띄우고 수정한 게시글로 이동
//	}
//
//	@RequestMapping("/usr/article/doDelete") // 게시글 삭제페이지
//	@ResponseBody
//	public String doDelete(int id) {
//
//		if (rq.isLogined() == false) {
//			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요Con");
//		}
//		// 로그인확인
//
//		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
//		// id번 게시글에 관한 정보와 게시글에 대한 삭제권한이 있는지를 확인
//
//		if (article == null) {
//			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없어", id));
//		}
//		// 가져온 게시글이 없으면 없다는 알림창을 띄우고 뒤로가기
//
//		ResultData actorCanDeleteRd = articleService.actorCanDelete(rq.getLoginedMemberId(), article);
//		// 로그인한 사용자가 게시글삭제권한이 있는지를 확인함
//
//		if (actorCanDeleteRd.isFail()) {
//			return Ut.jsHistoryBack(actorCanDeleteRd.getResultCode(), actorCanDeleteRd.getMsg());
//		}
//		// 로그인한 사용자가 삭제권한이 없으면 삭제를 할 수 없다는 메시지를 알림창에 띄우고 뒤로가기
//
//		ResultData deleteRd = articleService.deleteArticle(id);
//		// id번 게시글 삭제를 하고 성공실패에 대한 정보를 받음
//
//		if (deleteRd.isFail()) {
//			Ut.jsHistoryBack(deleteRd.getResultCode(), deleteRd.getMsg());
//		}
//		// 게시글 삭제를 실패하였으면 실패에 관한 메시지를 알림창으로 띄우고 뒤로가기
//
//		return Ut.jsReplace("S-1", Ut.f("%d번 게시글 삭제!", id), "list");
//		// 게시글 삭제를 성공하였다는 메시지를 알림창으로 띄우고 게시글 리스트로 돌아감
//	}
//
//	@RequestMapping("usr/article/doIncreaseHitCount") // 게시글 조회수를 증가시키는 페이지
//	@ResponseBody
//	public ResultData doIncreaseHitCount(int id) {
//
//		ResultData increaseHitCountRd = articleService.doIncreaseHitCount(id);
//		// id번 게시글 조회수를 증가시키고 성공,실패에 관한 정보를 받음
//
//		if (increaseHitCountRd.isFail()) {
//			return increaseHitCountRd;
//		}
//		// 조회수증가를 실패하였다면 그에 대한 정보를 return함
//
//		return ResultData.newData(increaseHitCountRd, "hitCount", articleService.getArticleHitCount(id));
//		// 조회수증가에 성공했다면 그에대한 정보와, id번 게시글에 대한 조회수를 전달함
//	}
}