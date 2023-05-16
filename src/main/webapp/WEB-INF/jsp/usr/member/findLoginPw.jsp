<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER FindLoginPw" />
<%@ include file="../common/head.jspf"%>
<hr />

<!-- 입력된 아이디, 이메일 전체지우기 -->
<script>
	$(document).ready(function() {
		$(".id_empty").click(function() {
			$(".id_put").val("");
			$('.id_put').focus();
		});
	});

	$(document).ready(function() {
		$(".pw_empty").click(function() {
			$(".pw_put").val("");
			$('.pw_put').focus();
		});
	});
</script>

<!-- findLoginId 폼 -->
<section class="findLogin_con">
	<div class="findLoginForm p-2">
		<form action="../member/doFindLoginPw" method="POST">
			<input type="hidden" name="afterFindLoginUri" value="${param.afterLoginUri }" />
			<div class="login_box">비밀번호 찾기</div>
			<div class="h-30"></div>
			<div>아이디</div>
			<div class="put_box">
				<input class="id_put" type="text" autocomplete="off" placeholder="아이디" name="loginId" />
				<span class="empty_btn btn btn-xs id_empty">X</span>
			</div>

			<div>이메일</div>
			<div class="put_box">
				<input class="pw_put mt_3" type="text" autocomplete="off" placeholder="e-mail" name="email" />
				<div class="empty_btn btn btn-xs pw_empty">X</div>
			</div>
			<div class="h-30"></div>
			<button class="btn_put input input-bordered" type="submit">비밀번호 찾기</button>
		</form>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>