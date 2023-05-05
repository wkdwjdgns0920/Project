package com.KoreaIT.jjh.project.util;

public class Ut {
	
	public static String f (String msg, Object...args) {
		
		return String.format(msg, args);
	}
	
	public static boolean empty(Object obj) {
		
		if(obj == null) {
			return true;
		}
		
		if(obj instanceof String == false) {
			return true;
		}
		
		String str = (String)obj;
		
		return str.trim().length() == 0;
	}
}
