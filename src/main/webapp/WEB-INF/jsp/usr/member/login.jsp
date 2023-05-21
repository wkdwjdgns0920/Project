<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Login" />
<%@ include file="../common/head.jspf"%>

<!-- 입력된 아이디, 비밀번호 전체지우기 -->
<script>
	$(document).ready(function() {
		$(".id_empty").click(function() {
			$(".id_put").val("");
		});
	});

	$(document).ready(function() {
		$(".pw_empty").click(function() {
			$(".pw_put").val("");
		});
	});
</script>

<!-- 비우기버튼보여지게 -->
<script>
	function loginIdEmptyBtn() {
		var id_text = document.querySelector(".id_put").value;
		var btn = document.querySelector(".id_empty");

		if (id_text != "") {
			console.log('asdasd');
			btn.style.display = "block";
		}
	}
	
	function loginPwEmptyBtn() {
		var pw_text = document.querySelector(".pw_put").value;
		var btn = document.querySelector(".pw_empty");

		if (pw_text != "") {
			console.log('asdasd');
			btn.style.display = "block";
		}
	}
	
	
</script>

<!-- 로그인폼 -->
<section class="login_con">
	<div class="loginForm p-2">
		<form action="../member/doLogin" method="POST">
			<input type="hidden" name="afterLoginUri" value="${param.afterLoginUri }" />
			<div class="login_box">로그인 폼</div>
			<div class="h-30"></div>
			<div class="put_box">
				<input class="id_put" type="text" autocomplete="off" placeholder="아이디" name="loginId" onblur="loginIdEmptyBtn()" />
				<span class="empty_btn btn btn-xs id_empty" style="display: none;">X</span>
			</div>

			<div class="put_box">
				<input class="pw_put mt_3" type="text" autocomplete="off" placeholder="비밀번호" name="loginPw" onblur="loginPwEmptyBtn()" />
				<div class="empty_btn btn btn-xs pw_empty" style="display: none;">X</div>
			</div>
			<div class="h-30"></div>
			<button class="btn_put input input-bordered" type="submit">로그인</button>
		</form>
		<div class="loginForm_low">
			<span class="loginForm_low_item">
				<a href="findLoginId">아이디 찾기</a>
			</span>
			<span>|</span>
			<span class="loginForm_low_item">
				<a href="findLoginPw">비밀번호 찾기</a>
			</span>
			<span>|</span>
			<span class="loginForm_low_item">
				<a href="${rq.joinUri }">회원가입</a>
			</span>
		</div>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>