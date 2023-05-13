<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER CheckPw" />
<%@ include file="../common/head.jspf"%>
<hr />

<script>
$(document).ready(function() {
	$(".pw_empty").click(function() {
		$(".checkPw_put").val("");
	});
});
</script>

<section class="checkPw_con">
	<div class="checkPw_Form p-2">
		<form action="../member/doCheckPw" method="POST">
			<div class="checkPw_box">회원정보 수정</div>
			<div class="h-30"></div>
			<div class="checkPw_put_box">
				<div class="checkPwId_put">${rq.loginedMember.loginId }</div>
			</div>

			<div class="checkPw_put_box">
				<input class="checkPw_put mt_3" type="text" autocomplete="off" placeholder="비밀번호" name="loginPw" />
				<div class="empty_btn btn btn-xs pw_empty">X</div>
			</div>
			<div class="h-30"></div>
			<button class="btn_put input input-bordered" type="submit">확인</button>
		</form>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>