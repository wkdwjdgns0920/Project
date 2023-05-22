package com.KoreaIT.jjh.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLine {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	
	private String extra_writer;

	private boolean actorCanDelete;
	private boolean actorCanModify;
	
}
