<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Login" />
<%@ include file="../common/head.jspf"%>

<div class="loginForm flex items-end p-2">
	<form action="../member/doLogin" method="POST">
		<div class="login_box">로그인 폼</div>
		<div class="h-30"></div>
		<input class="input input-bordered w-full max-w-xs" type="text" autocomplete="off" placeholder="아이디" name="loginId" />
		
		<input class="input input-bordered w-full max-w-xs" type="text" autocomplete="off" placeholder="비밀번호" name="loginPw" />
		
		<button class="input input-bordered w-full max-w-xs" type="submit">로그인</button>

	</form>
</div>