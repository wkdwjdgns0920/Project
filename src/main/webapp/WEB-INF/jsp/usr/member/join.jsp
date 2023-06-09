<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="JOIN" />
<%@ include file="../common/head.jspf"%>

<!-- 회원가입 실행전에 체크 -->
<script>
	let submitJoinFormDone = false;
	let validLoginId = "";
	let validEmail = false;
	let validPw = false;

	function submitJoinForm(form) {
		if (submitJoinFormDone) {
			alert('처리중입니다');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		if (validPw == false) {
			alert('최소 8자리에 숫자, 문자, 특수문자 사용해주세요');
			form.loginPw.focus();
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value == 0) {
			alert('비밀번호 확인을 입력해주세요');
			form.loginPwConfirm.focus();
			return;
		}
		if (form.loginPwConfirm.value != form.loginPw.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();
			return;
		}
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value == 0) {
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value == 0) {
			alert('이메일을 입력해주세요');
			form.email.focus();
			return;
		}
		if (validEmail == false) {
			alert('이메일형식을 확인해주세요');
			form.email.focus();
			return;
		}
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value == 0) {
			alert('전화번호를 입력해주세요');
			form.cellphoneNum.focus();
			return;
		}
		submitJoinFormDone = true;
		form.submit();
	}

	/* 아이디 입력시에 Ajax로 아이디 중복검사 */
	function checkLoginIdDup(el) {
		$('.checkDupId_msg').empty();
		const form = $(el).closest('form').get(0);

		if (form.loginId.value.length == 0) {
			validLoginId = '';
			return;
		}

		if (validLoginId == form.loginId.value) {
			return;
		}

		$.get('../member/getLoginIdDup', {
			isAjax : 'Y',
			loginId : form.loginId.value
		}, function(data) {

			if (data.success) {
				$('.checkDupId_msg').html(
						'<div class="can_use">' + data.msg + '</div>')
				validLoginId = data.data1;
			} else {
				$('.checkDupId_msg').html(
						'<div class="cant_use">' + data.msg + '</div>')
				validLoginId = '';
			}

		}, 'json');

	}

	/* Ajax로 비밀번호 유효성 확인 */
	function validCheckPw(el) {
		$('.validPw_msg').empty();
		const form = $(el).closest('form').get(0);

		$.get('../member/checkValidPw', {
			isAjax : 'Y',
			loginPw : form.loginPw.value
		}, function(data) {

			if (data.success) {
				$('.validPw_msg').html(
						'<div class="can_use">' + data.msg + '</div>')
				validPw = true;
			} else {
				$('.validPw_msg').html(
						'<div class="cant_use">' + data.msg + '</div>')
			}

		}, 'json');

	}

	/* 이메일 유효성 확인 */
	function validCheckEmail(el) {
		$('.validEmail_msg').empty();
		const form = $(el).closest('form').get(0);

		$.get('../member/checkValidEmail', {
			isAjax : 'Y',
			email : form.email.value
		}, function(data) {

			if (data.success) {
				$('.validEmail_msg').html(
						'<div class="can_use">' + data.msg + '</div>')
				validEmail = true;
			} else {
				$('.validEmail_msg').html(
						'<div class="cant_use">' + data.msg + '</div>')
			}

		}, 'json');

	}
</script>

<section class="div_center mt_50">
	<div class="mt-8 text-xl bor-b po-rel w-650">
		<form class="" method="POST" action="../member/doJoin" onsubmit="submitJoinForm(this); return false;">
			<input type="hidden" name="afterLoginUri" value="${param.afterLoginUri}" />
			<table class="table table-zebra w-full">
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input onblur="checkLoginIdDup(this)" name="loginId" class="w-full input input-bordered  max-w-xs"
								placeholder="아이디를 입력해주세요" />
							<div class="checkDupId_msg"></div>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input onblur="validCheckPw(this)" name="loginPw" class="w-full input input-bordered  max-w-xs"
								placeholder="비밀번호를 입력해주세요" />
							<div class="validPw_msg"></div>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input name="loginPwConfirm" class="w-full input input-bordered  max-w-xs" placeholder="비밀번호 확인을 입력해주세요" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input name="name" class="w-full input input-bordered  max-w-xs" placeholder="이름을 입력해주세요" />
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>
							<input name="nickname" class="w-full input input-bordered  max-w-xs" placeholder="닉네임을 입력해주세요" />
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input name="cellphoneNum" class="w-full input input-bordered  max-w-xs" placeholder="전화번호를 입력해주세요" />
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input onblur="validCheckEmail(this)" name="email" class="w-full input input-bordered  max-w-xs"
								placeholder="이메일을 입력해주세요" />
							<div class="validEmail_msg"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<button class="btn btn-active btn-ghost w-320" type="submit" value="회원가입">회원가입</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<button class="btn-text-link btn btn-active btn-ghost back_btn" type="button" onclick="history.back();">뒤로가기</button>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>