package com.KoreaIT.jjh.project.util;

public class Ut {

	public static String f(String msg, Object... args) {

		return String.format(msg, args);
	}

	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
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

}
