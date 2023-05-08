<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name }" />
<%@ include file="../common/head.jspf"%>


<script>

</script>

<div class="mt-8 text-xl bor-b">
	<div class="flex mb-4">
		<div>
			게시물 갯수 :
			<span class="badge">${articlesCount }</span>
			개
		</div>
		<div class="flex-grow"></div>
		<form action="">
			<input type="hidden" name="boardId" value="${param.boardId }" />
			<select data-value="${param.searchKeywordType }" name="searchKeywordType" class="select select-ghost">
				<option value="title">제목</option>
				<option value="body">내용</option>
				<option value="title,body">제목 + 내용</option>
			</select>
			<input value="${param.searchKeyword }" maxlength="20" name="searchKeyword" class="input input-bordered" type="text"
				placeholder="검색어를 입력해주세요" />
			<button class="btn btn-ghost" type=submit>검색</button>
		</form>
	</div>
	<table class="table table-zebra w-full ">
		<thead>
		<colgroup>
			<col width="50" />
			<col width="100" />
			<col width="150" />
			<col width="300" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th>작성날짜</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
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
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="flex justify-center mt-3">

		<c:set var="pageLen" value="4" />
		<c:set var="startPage" value="${page - pageLen >=1 ? page - pageLen : 1 }" />
		<c:set var="endPage" value="${startPage + pageLen <= pagesCount ? startPage + pageLen : pagesCount }" />

		<c:set var="baseUri" value="?boardId=${boardId }" />
		<c:set var="baseUri" value="${baseUri }&searchKeywordType=${searchKeywordType}" />
		<c:set var="baseUri" value="${baseUri }&searchKeyword=${searchKeyword}" />

		<c:if test="${page > 1 }">
			<a class="" href="${baseUri }&page=1">◀◀</a> &nbsp&nbsp
			<a class="" href="${baseUri }&page=${page-1 }">◀</a>
		</c:if>

		<div>
			<c:forEach begin="${startPage }" end="${endPage }" var="i">
				<a class="p-1 ${param.page == i ? 'btn-active' : '' }" href="${baseUri }&page=${i }">${i }</a>
			</c:forEach>

			<c:if test="${page < pagesCount }">
				<a class="" href="${baseUri }&page=${page + 1 }">▶</a>&nbsp&nbsp
				<a class="" href="${baseUri }&page=${pagesCount }">▶▶</a>
			</c:if>

		</div>
	</div>
</div>




<%@ include file="../common/foot.jspf"%>