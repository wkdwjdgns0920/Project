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

		int id = replyRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다", id), "id", id);
	}

	public List<Reply> getForPrintReplies(int actorId, String relTypeCode, int relId, int itemInAPage, int page) {
		
		int limitFrom = (page - 1) * itemInAPage;
		int limit = itemInAPage;
		
		List<Reply> replies = replyRepository.getForPrintReplies(actorId, relTypeCode, relId, limitFrom, limit);
		
		for(Reply reply : replies) {
			controlForPrintData(actorId,reply);
		}
		
		return replies;
	}

	private void controlForPrintData(int actorId, Reply reply) {
		if(reply == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId,reply);
		reply.setActorCanDelete(actorCanDeleteRd.isSuccess());
		
		ResultData actorCanModifyRd = actorCanModify(actorId,reply);
		reply.setActorCanModify(actorCanModifyRd.isSuccess());
		
	}

	private ResultData actorCanModify(int actorId, Reply reply) {
		if(reply.getMemberId() != actorId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없음");
		}
		return ResultData.from("S-1", "댓글수정가능");
	}

	private ResultData actorCanDelete(int actorId, Reply reply) {
		if(reply.getMemberId() != actorId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없음");
		}
		
		return ResultData.from("S-1", "댓글삭제가능");
	}

	public Reply getReply(int id) {
		
		return replyRepository.getReply(id);
	}

	public ResultData deleteReply(int id) {
		int affectedRow = replyRepository.deleteReply(id);
		
		if(affectedRow != 1) {
			return ResultData.from("F-3", "댓글삭제실패", "affectedRow", affectedRow);
		}
		
		return ResultData.from("S-1", "댓글삭제", "affectedRow", affectedRow);
	}

	public ResultData modifyReply(int id, String body) {
		int affectedRow = replyRepository.modifyReply(id,body);
		
		if(affectedRow != 1) {
			return ResultData.from("F-3", "댓글수정실패", "affectedRow", affectedRow);
		}
		
		
		
		return ResultData.from("S-1", "댓글수정", "affectedRow", affectedRow);
	}

	public int getRepliesCount(int relId) {
		
		return replyRepository.getRepliesCount(relId);
	}
}