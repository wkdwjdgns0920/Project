package com.KoreaIT.jjh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.TimeLineRepository;
import com.KoreaIT.jjh.project.vo.TimeLine;

@Service
public class TimeLineService {
	
	@Autowired
	TimeLineRepository timeLineRepository;
	
	public int upLoad(int actorId, String title) {
		timeLineRepository.upLoad(actorId, title );
		
		return timeLineRepository.getLastId();
	}

	public List<TimeLine> getTimeLines(int itemInAPage, int page) {
		
		int limitFrom = (page - 1) * itemInAPage;	//	(현재 페이지 -1) * 한 페이지당 들어갈 게시글의 갯수 (몇번부터 limit개 가져올지에 대한 번호를 구함)
		int limit = itemInAPage;	//	한 페이지당 들어가야할 게시글의 갯수
		
		return timeLineRepository.getTimeLines(limitFrom, limit);
	}

	public int getTimeLinesCount() {
		
		return timeLineRepository.getTimeLinesCount();
	}

}
