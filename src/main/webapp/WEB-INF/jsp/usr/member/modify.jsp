<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER Modify" />
<%@ include file="../common/head.jspf"%>
<hr />



<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<form action="../member/doModify" method="POST" onsubmit="if(confirm('정말 수정하시겠습니까?')==false) return false;">
				<input type="hidden" name="loginId" value="${rq.loginedMember.loginId }" />
				<table border="1">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>가입일</th>
							<td>${rq.loginedMember.regDate }</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td>${rq.loginedMember.loginId }</td>
						</tr>
						<tr>
							<th>새 비밀번호</th>
							<td>
								<input name="loginPw" class="input input-bordered w-full max-w-xs" placeholder="새 비밀번호를 입력해주세요" type="text"
									autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th>새 비밀번호 확인</th>
							<td>
								<input name="loginPwConfirm" class="input input-bordered w-full max-w-xs" placeholder="새 비밀번호 확인을 입력해주세요"
									type="text" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								<input name="name" value="${rq.loginedMember.name }" class="input input-bordered w-full max-w-xs"
									placeholder="이름을 입력해주세요" type="text" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td>
								<input name="nickname" value="${rq.loginedMember.nickname }" class="input input-bordered w-full max-w-xs"
									placeholder="닉네임을 입력해주세요" type="text" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>
								<input name="cellphoneNum" value="${rq.loginedMember.cellphoneNum }"
									class="input input-bordered w-full max-w-xs" placeholder="전화번호를 입력해주세요" type="text" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>
								<input name="email" value="${rq.loginedMember.email }" class="input input-bordered w-full max-w-xs"
									placeholder="이메일을 입력해주세요" type="text" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<button type="submit" value="수정" />
								수정
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="btns">
			<button class="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>


		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>