package com.KoreaIT.jjh.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String title;
	private String body;
	private int hitCount;
	private int likePoint;
	private int disLikePoint;
	
	private String extra_writer;

	private boolean actorCanDelete;
	private boolean actorCanModify;
	
}
