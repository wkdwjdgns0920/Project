<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER MYPAGE" />
<%@ include file="../common/head.jspf"%>
<hr />

<style>
.myPage_bg {
	position: absolute;
	z-index: -10;	
}
.myPageTitle {
	margin-bottom: 20px;
	font-size: 2rem;
	font-weight: bold;
}
.myPageList {
	font-size: 2rem;
	font-weight: bold;
	padding: 10px;
	z-index: 19;
}
.bg-w {
	background-color: white;
}
</style>

<div class="myPage_bg">
	<img class="flexCardTh" src="/resources/img/테마2.jpg" alt="" />
</div>
<section class="div_center mt_50">
	<div class="mt-8 text-xl po-rel w-800">
		<div class="div_center">
			<div class="myPageTitle">회원정보</div>
		</div>
		<div class="mx-auto px-3">
			<table border="1" class="table table-zebra w-full">
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
						<th>이름</th>
						<td>${rq.loginedMember.name }</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${rq.loginedMember.nickname }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${rq.loginedMember.cellphoneNum }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${rq.loginedMember.email }</td>
					</tr>
					<tr>
						<th>회원정보 수정하기</th>
						<td>
							<a href="../member/checkPw" class="btn btn-active btn-ghost">회원정보 수정</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>

<section class="div_center mt_50">
	<div class="mt-8 text-xl w-800">
		<div class="div_center bg-w">
			<div class="myPageList bg-w">나의 게시글</div>
		</div>
		<div class="mx-auto px-3 bg-w">
			<table class="table table-zebra w-full">
			<thead>
			<colgroup>
				<col width="50" />
				<col width="100" />
				<col width="150" />
				<col width="150" />
				<col width="300" />
				<col width="50" />
				<col width="50" />
			</colgroup>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr>
						<td class="flex justify-center">${article.id }</td>
						<td>${article.regDate.substring(2,16) }</td>
						<td>${article.extra_writer }</td>
						<td>
							<a class="hover:underline" href="../article/detail?id=${article.id }">${article.title }</a>
						</td>
						<td>${article.body }</td>
						<td>${article.hitCount }</td>
						<td>${article.likePoint }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>