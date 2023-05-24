<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER FindLoginId" />
<%@ include file="../common/head.jspf"%>
<hr />

<!-- 입력된 이름, 이메일 전체지우기 -->
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
<!-- 비우기버튼보여지게 -->
<script>
	function loginIdEmptyBtn() {
		var id_text = document.querySelector(".id_put").value;
		var btn = document.querySelector(".id_empty");

		if (id_text != "") {
			btn.style.display = "block";
		} else if(id_text == ""){
			btn.style.display = "none";
		}
	}
	
	function loginPwEmptyBtn() {
		var pw_text = document.querySelector(".pw_put").value;
		var btn = document.querySelector(".pw_empty");

		if (pw_text != "") {
			btn.style.display = "block";
		} else if(pw_text == ""){
			btn.style.display = "none";
		}
	}
</script>

<!-- findLoginId 폼 -->
<section class="findLogin_con">
	<div class="findLoginForm p-2">
		<form action="../member/doFindLoginId" method="POST">
			<input type="hidden" name="afterFindLoginUri" value="${param.afterLoginUri }" />
			<div class="login_box">아이디 찾기</div>
			<div class="h-30"></div>
			<div>이름</div>
			<div class="put_box">
				<input class="id_put" type="text" autocomplete="off" placeholder="이름" name="name" onblur="loginIdEmptyBtn()"/>
				<span class="empty_btn btn btn-xs id_empty" style="display: none;">X</span>
			</div>

			<div>이메일</div>
			<div class="put_box">
				<input class="pw_put mt_3" type="text" autocomplete="off" placeholder="e-mail" name="email" onblur="loginPwEmptyBtn()"/>
				<div class="empty_btn btn btn-xs pw_empty" style="display: none;">X</div>
			</div>
			<div class="h-30"></div>
			<button class="btn_put input input-bordered" type="submit">아이디 찾기</button>
		</form>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>