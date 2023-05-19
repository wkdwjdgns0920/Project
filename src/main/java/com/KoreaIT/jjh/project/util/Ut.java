package com.KoreaIT.jjh.project.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Ut {

	public static String f(String msg, Object... args) {

		return String.format(msg, args);
	}

	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
		}
		
		if (obj instanceof Integer) {
			return (int) obj == 0;
		}
		
		if (obj instanceof String == false) {
			return true;
		}

		String str = (String) obj;

		return str.trim().length() == 0;
	}

	public static String jsHistoryBack(String resultCode, String msg) {

		if (msg == null) {
			msg = "";
		}

		return Ut.f("""
				<script>
					var msg = '%s'.trim();
					if(msg.length > 0) {
						alert(msg);
					}
					history.back();
				</script>
				""", msg);
	}

	public static String jsReplace(String resultCode, String msg, String uri) {

		if (msg == null) {
			msg = "";
		}

		if (uri == null) {
			uri = "/";
		}

		return Ut.f("""
				<script>
					var msg = '%s'.trim();
					if(msg.length > 0) {
						alert(msg);
					}
					location.replace('%s');
				</script>
				""", msg, uri);
	}
	
	public static String getEncodedCurrentUri(String currentUri) {

		try {
			return URLEncoder.encode(currentUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return currentUri;
		}

	}
	
	public static String getEncodedUri(String uri) {
		try {
			return URLEncoder.encode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return uri;
		}

	}

	public static Map<String, String> getParamMap(HttpServletRequest req) {
		Map<String, String> param = new HashMap<>();

		Enumeration<String> parameterNames = req.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String paramValue = req.getParameter(paramName);

			param.put(paramName, paramValue);
		}

		return param;
	}
	
	//	비밀번호 유효성 검사 메서드
	public static boolean validationPasswd(String loginpw){
	    Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
	    // 최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함
	    Matcher m = p.matcher(loginpw);
	    //	입력받은 비밀번호 유효성 검사를 실행
	    if(m.matches()){
	        return false;
	    }
	    //	사용할 수 없는 비밀번호일 경우 false값을 반환

	    return true;
	    //	사용할 수 있는 비밀번호일 경우 true값을 반환
	}
	
	
	//	이메일 유효성 검사 메서드
	public static boolean isValidEmail(String email) {
		boolean err = false;	//	err의 값을 기본 false로 설정
		
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";	//	이메일 유효성검사를 확인할 문자열
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		//	이메일의 유효성 검사를 함
		if (m.matches()) {
			err = true;
			//	이메일 유효성검사를 통화하면 err를 true로 변환
		}
		return err;
		//	이메일 유효성검사의 값을 반환(통과 true, 실패 false)
	}
	
	public static String getTempPassword(int length) {
		int index = 0;
		char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			index = (int) (charArr.length * Math.random());
			sb.append(charArr[index]);
		}

		return sb.toString();
	}
	
}
