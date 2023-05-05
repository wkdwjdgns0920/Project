<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Login" />
<%@ include file="../common/head.jspf"%>

<div class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<form action="../member/doLogin" method="POST">
				<table border="1">
					<colgroup>
						<col width="200" />
					</colgroup>
					<tbody>
						<tr>
							<th>아이디</th>
							<td>
								<input class="input input-bordered w-full max-w-xs" type="text" autocomplete="off" placeholder="아이디를 입력해"
									name="loginId" />
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input class="input input-bordered w-full max-w-xs" type="text" autocomplete="off" placeholder="비밀번호를 입력해"
									name="loginPw" />
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<button type="submit">버튼</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>