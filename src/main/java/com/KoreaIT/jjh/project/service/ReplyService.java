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

	public List<Reply> getForPrintReplies(int actorId, String relTypeCode, int relId) {
		
		return replyRepository.getForPrintReplies(actorId, relTypeCode, relId);
	}
}