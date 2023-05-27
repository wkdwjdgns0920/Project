package com.KoreaIT.jjh.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

	/** 포맷팅 현재 날짜/시간 반환 Str */
	public static String getNowDate() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		// 포맷팅 현재 날짜/시간 출력

		return formatedNow;
	}
	
	public static String getNowTime() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		String formatedNow = now.format(DateTimeFormatter.ofPattern("HHmm"));
		// 포맷팅 현재 날짜/시간 출력

		return formatedNow;
	}
}
