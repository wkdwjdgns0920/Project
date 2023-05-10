package com.KoreaIT.jjh.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String body;
	private int likePoint;
	private int disLikePoint;

	private String extra__writer;

	private boolean actorCanModify;
	private boolean actorCanDelete;

}