package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ArticleRepository;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Article;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public ResultData deleteArticle(int id) {
		int affectedRow = articleRepository.deleteArticle(id);
		//	id번 게시글을 삭제하고 성공한 열에 대한 갯수를 받음

		if (affectedRow != 1) {
			return ResultData.from("F-4", "게시물삭제실패", "affectedRow", affectedRow);
		}
		//	게시글 삭제를 실패한 정보를 전달

		return ResultData.from("S-1", "게시물삭제", "affectedRow", affectedRow);
		//	게시글 삭제성공에 대한 정보를 전달
	}

	public ResultData modifyArticle(int id, String title, String body) {
		int affectedRow = articleRepository.modifyArticle(id, title, body);
		//	사용자에게 받은 게시글 번호와 수정할 제목, 내용을 가지고 수정하고 성공한 행에 대한 갯수를 받음

		if (affectedRow != 1) {
			return ResultData.from("F-2", "게시물수정실패", "affectedRow", affectedRow);
		}
		//	수정한 열의 갯수가 1이 아닐경우 수정실패에 대한 정보를 넘김

		return ResultData.from("S-1", Ut.f("%d번 게시글수정", id), "affectedRow", affectedRow);
		//	수정성공에 대한 정보를 넘김
	}

	public ResultData actorCanModify(int actorId, Article article) {
		if (article.getMemberId() != actorId) {
			return ResultData.from("F-2", "해당게시글에 수정권한이 없습니다");
		}	//	게시글의 memberId와 로그인한 사용자의 id가 같지 않으면 수정권한이 없음을 체크
		
		return ResultData.from("S-1", "수정가능");	
		//	게시글의 memberId와 로그인한 사용자의 id가 같으면 수정권한이 있음을 체크
	}

	public ResultData actorCanDelete(int actorId, Article article) {

		if (article.getMemberId() != actorId) {
			return ResultData.from("F-A", "삭제권한이 없습니다");
		}
		//	게시글에 memberId와 로그인한 사용자의 id가 다르면 삭제권한이 없다는 정보를 보냄

		return ResultData.from("S-A", "삭제가능");
		//	게시글삭제권한이 있다는 정보를 보냄
	}

	private void updateForPrintData(int actorId, Article article) {
		if (article == null) {
			return;
		}
		//	가져온 게시글이 없으면 return

		ResultData actorCanModifyRd = actorCanModify(actorId, article);	//	로그인한 사용자가 게시글을 수정할 수 있는지 체크함
		article.setActorCanModify(actorCanModifyRd.isSuccess());
		//	로그인한 사용자가 게시글을 수정할 수 있으면 Article dto에 수정가능을 남김(수정가능을 true값으로 변환)(수정할 수 없으면 false)

		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);	//	로그인한 사용자가 게시글을 삭제할 수 있는지 체크함
		article.setActorCanDelete(actorCanDeleteRd.isSuccess());
		//	로그인한 사용자가 게시글을 삭제할 수 있으면 Article dto에 삭제가능을 남김(삭제가능을 true값으로 변환)(삭제할 수 없으면 false)
	}

	public Article getForPrintArticle(int actorId, int id) {

		Article article = articleRepository.getForPrintArticle(id);
		//	id번 게시글과 작성자에 대한 정보를 가져옴

		updateForPrintData(actorId, article);
		//	로그인한 사용자가 수정과 삭제가 가능한지 확인

		return article;
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
		// id번 게시글에 대한 정보를 가져옴
	}

	public int write(int actorId, String title, String body, int boardId) {
		articleRepository.write(actorId, title, body, boardId);
		//	게시글 작성

		return articleRepository.getLastId();
		//	마지막으로 작성된 게시글의 번호를 가져옴
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
		//	게시글들을 가져옴
	}

	public int getLastId() {
		return articleRepository.getLastId();
		//	마지막으로 작성된 id를 가져옴
	}

	public List<Article> getForPrintArticles(int boardId, int itemInAPage, int page, String searchKeywordType,
			String searchKeyword) {
		
		int limitFrom = (page - 1) * itemInAPage;	//	(현재 페이지 -1) * 한 페이지당 들어갈 게시글의 갯수 (몇번부터 limit개 가져올지에 대한 번호를 구함)
		int limit = itemInAPage;	//	한 페이지당 들어가야할 게시글의 갯수
		return articleRepository.getForPrintArticles(boardId, limitFrom, limit, searchKeywordType, searchKeyword);
		//	게시글들을 전달
	}
	
	public List<Article> getForPrintArticlesByHitCount(int boardId, int itemInAPage, int page, String searchKeywordType,
			String searchKeyword) {
		int limitFrom = (page - 1) * itemInAPage;	//	(현재 페이지 -1) * 한 페이지당 들어갈 게시글의 갯수 (몇번부터 limit개 가져올지에 대한 번호를 구함)
		int limit = itemInAPage;	//	한 페이지당 들어가야할 게시글의 갯수
		return articleRepository.getForPrintArticlesByHitCount(boardId, limitFrom, limit, searchKeywordType, searchKeyword);
	}

	public int getArticlesCount(int boardId, String searchKeywordType, String searchKeyword) {

		return articleRepository.getArticlesCount(boardId, searchKeywordType, searchKeyword);
		//	boardId와 SearchKeyword에 맞는 게시글의 갯수를 전달
	}

	public ResultData doIncreaseHitCount(int id) {

		int affectedRow = articleRepository.doIncreaseHitCount(id);
		//	id번 게시글의 조회수를 1증가시키고 성공에 관한 숫자를 전달받음

		if (affectedRow != 1) {
			return ResultData.from("F-1", "조회수증가 실패", "affectedRow", affectedRow);
		}
		//	조회수 증가에 실패하였으면 그에대한 정보를 전달
		
		return ResultData.from("S-1", "조회수 증가", "affectedRow", "affectedRow");
		//	조회수 증가에 성공하였다는 정보를 전달
	}

	public int getArticleHitCount(int id) {
		return articleRepository.getArticleHitCount(id);
		//	id번 게시글의 조회수를 가져옴
	}

	public ResultData increaseLikePoint(int relId) {
		int affectedRow = articleRepository.increaseLikePoint(relId);
		//	relId번의 게시글의 좋아요를 증가시키고 성공한 행의 숫자를 받음

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요증가 실패", "affectedRow", affectedRow);
		}
		//	좋아요증가에 실패했다면 그에 대한 정보를 전달

		return ResultData.from("S-1", "좋아요증가", "affectedRow", affectedRow);
		//	좋아요증가 성공에 대한 정보를 전달
	}

	public ResultData increaseDisLikePoint(int relId) {
		int affectedRow = articleRepository.increaseDisLikePoint(relId);
		//	relId번의 게시글의 싫어요를 증가시키고 성공한 행의 숫자를 받음
		
		if (affectedRow != 1) {
			return ResultData.from("F-1", "싫어요증가 실패", "affectedRow", affectedRow);
		}
		//	싫어요를 실패한 정보를 전달

		return ResultData.from("S-1", "싫어요증가", "affectedRow", affectedRow);
		//	싫어요증가 성공에 대한 정보를 전달
	}

	public ResultData decreaseLikeReactionPoint(int relId) {

		int affectedRow = articleRepository.decreaseLikeReationPoint(relId);
		// 좋아요를 감소시키고 성공,실패에 관한 숫자를 받음

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요감소실패", "affectedRow", affectedRow);
		}
		// 좋아요 감소에 실패한 정보를 전달
		
		return ResultData.from("S-1", "좋아요감소", "affectedRow", affectedRow);
		//	좋아요 감소를 성공한 정보를 전달
	}

	public ResultData decreaseDisLikeReactionPoint(int relId) {
		int affectedRow = articleRepository.decreaseDisLikeReationPoint(relId);
		//	싫어요 점수를 감소시키고 성공실패에 관한 숫자를 전달받음

		if (affectedRow != 1) {
			return ResultData.from("F-1", "싫어요감소실패", "affectedRow", affectedRow);
		}
		//	싫어요 감소실패에 대한 정보를 전달

		return ResultData.from("S-1", "싫어요감소", "affectedRow", affectedRow);
		//	싫어요 감소 성공에 대한 정보를 전달
	}

	public int getSumLikePointById(int relId) {
		
		return articleRepository.getSumLikePointById(relId);
	}

	public List<Article> getArticlesByMemberId(int actorId) {
		
		return articleRepository.getArticlesByMemberId(actorId);
	}

	public void deleteArticles(List<Integer> articleIds) {
		
		for (int articleId : articleIds) {
			Article article = getArticle(articleId);

			if (article != null) {
				deleteArticle(article);
				
			}
		}
		
	}

	private void deleteArticle(Article article) {
		articleRepository.deleteArticle(article.getId());
	}

}