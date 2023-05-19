package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.ReplyRepository;
import com.KoreaIT.jjh.project.util.Ut;
import com.KoreaIT.jjh.project.vo.Reply;
import com.KoreaIT.jjh.project.vo.ResultData;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	
	
	
	public ResultData<Integer> writeReply(int actorId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(actorId, relTypeCode, relId, body);
		//	댓글작성
		
		int id = replyRepository.getLastInsertId();
		//	마지막으로 작성된 댓글의 id를 가져옴

		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다", id), "id", id);
		//	댓글작성에 대한 데이터를 리턴
	}
	
	
	public List<Reply> getForPrintReplies(int actorId, String relTypeCode, int relId, int itemInAPage, int page) {
		
		int limitFrom = (page - 1) * itemInAPage;	//	가져올 댓글 갯수의 시작번호
		int limit = itemInAPage;	//	한페이지에 들어갈 댓글의 갯수
		
		List<Reply> replies = replyRepository.getForPrintReplies(actorId, relTypeCode, relId, limitFrom, limit);
		//	page에 들어갈 댓글들에 대한 정보를 가져옴
		
		for(Reply reply : replies) {
			controlForPrintData(actorId,reply);
		}
		//	가져온 댓글들에 대해 업데이트 권한이 있는지를 체크
		
		return replies;
	}
	
	// 댓글에 대한 권한을 Reply에 설정
	private void controlForPrintData(int actorId, Reply reply) {
		if(reply == null) {
			return;
		}
		// 가져온 댓글이 없다면 return
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId,reply);	//	사용자가 댓글을 삭제할 수 있는지 확인
		reply.setActorCanDelete(actorCanDeleteRd.isSuccess());	//	댓글을 삭제할 수 있는지에 대한 boolean값을 dto에 설정
		
		ResultData actorCanModifyRd = actorCanModify(actorId,reply);	//	사용자가 댓글을 수정할 수 있는지 확인
		reply.setActorCanModify(actorCanModifyRd.isSuccess());	//	댓글을 수정할 수 있는지에 대한 boolean값을 dto에 설정 
		
	}
	
	// 사용자가 댓글을 수정할 수 있는지 확인
	private ResultData actorCanModify(int actorId, Reply reply) {
		if(reply.getMemberId() != actorId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없음");
		}
		//	해당 댓글을 수정할 수 없다는 데이터를 리턴
		
		return ResultData.from("S-1", "댓글수정가능");
		//	해당 댓글수정 가능하다는 정보를 리턴
	}
	
	// 사용자가 댓글을 삭제할 수 있는지 확인
	private ResultData actorCanDelete(int actorId, Reply reply) {
		if(reply.getMemberId() != actorId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없음");
		}
		//	해당 댓글을 삭제할 수 없다는 데이터를 리턴
		
		return ResultData.from("S-1", "댓글삭제가능");
		//	해당 댓글삭제가 가능하다는 정보를 리턴
	}
	
	// id번의 댓글을 가져옴
	public Reply getReply(int id) {
		return replyRepository.getReply(id);
	}
	
	
	public ResultData deleteReply(int id) {
		int affectedRow = replyRepository.deleteReply(id);
		//	댓글삭제 성공,실패에 대한 데이터
		
		if(affectedRow != 1) {
			return ResultData.from("F-3", "댓글삭제실패", "affectedRow", affectedRow);
		}
		//	댓글삭제에 실패한 경우 실패한 정보를 리턴
		
		return ResultData.from("S-1", "댓글삭제", "affectedRow", affectedRow);
		//	댓글삭제성공에 대한 정보를 리턴
	}

	public ResultData modifyReply(int id, String body) {
		int affectedRow = replyRepository.modifyReply(id,body);
		//	댓글수정 성공실패에 대한 정보
		
		if(affectedRow != 1) {
			return ResultData.from("F-3", "댓글수정실패", "affectedRow", affectedRow);
		}
		//	댓글수정에 실패한 경우 그에대한 정보를 리턴함
		
		return ResultData.from("S-1", "댓글수정", "affectedRow", affectedRow);
		//	댓글수정에 성공한 경우 그에대한 정보를 리턴
	}
	
	//	relId번 게시글에 달린 댓글의 수를 가져옴
	public int getRepliesCount(int relId) {
		return replyRepository.getRepliesCount(relId);
	}
}