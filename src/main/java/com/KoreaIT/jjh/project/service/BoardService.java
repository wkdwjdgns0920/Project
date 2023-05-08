package com.KoreaIT.jjh.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.project.repository.BoardRepository;
import com.KoreaIT.jjh.project.vo.Board;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardById(int boardId) {
		
		return boardRepository.getBoardById(boardId);
	}

	
}