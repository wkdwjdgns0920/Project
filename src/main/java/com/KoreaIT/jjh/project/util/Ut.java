package com.KoreaIT.jjh.project.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
	
}
