<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Modify" />
<%@ include file="../common/head.jspf"%>


<div class="thema_box">
	<img class="flexCardTh" src="/resources/img/테마4.jpg" alt="" />
</div>
<section class="div_center">
	<div class="mt-8 text-xl po-rel w-800">
		<form action="../article/doModify" method="POST">
		<input type="hidden" name="id" value="${article.id }" />
		<table class="table table-zebra w-full">
			<colgroup>
				<col width="150" />
			</colgroup>
			<tbody>
				<tr>
					<th>번호</th>
					<td class="text-center">${article.id }</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td class="text-center">${article.regDate.substring(2,16) }</td>
				</tr>
				<tr>
					<th>수정날짜</th>
					<td class="text-center">${article.updateDate.substring(2,16) }</td>
				</tr>
				<tr>
					<th>첨부 이미지</th>
						<td>
							<input name="file__article__0__extra__Img__1" placeholder="이미지를 선택해주세요" type="file" />
						</td>
					</tr>
				<tr>
					<th>작성자</th>
					<td class="text-center">${article.extra_writer }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input class="w-full" name="title" type="text" value="${article.title }" placeholder="제목" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="w-full modify_body" type="text" name="body" placeholder="내용을 입력해주세요" />${article.body }</textarea>
					</td>
				</tr>
				<tr>
					<th></th>
					<td class="modify_btn">
						<button type="submit" class="btn btn-active btn-ghost">수정</button>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<div class="btns lowMenu flex justify-end">
		<button class="btn-text-link m-1" type="button" onclick="history.back();">뒤로가기</button>
		<c:if test="${article.actorCanModify }">
			<a class="btn-text-link m-1" href="../article/modify?id=${article.id }">수정</a>
		</c:if>
		<c:if test="${article.actorCanDelete }">
			<a class="btn-text-link m-1" onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false;"
				href="../article/doDelete?id=${article.id }">삭제</a>
		</c:if>
	</div>
</div>

<style>
.modify_body {
	height: 300px;
}
.modify_btn {
	display: flex;
	justify-content: flex-end;
}
.thema_box {
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}
</style>



<%@ include file="../common/foot.jspf"%>