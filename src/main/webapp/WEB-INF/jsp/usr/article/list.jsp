<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article List" />
<%@ include file="../common/head.jspf"%>

<div class="mt-8 text-xl bor-b">
<table class="table table-zebra w-full ">
	<thead>
	<colgroup>
		<col width="50"/>
		<col width="100"/>
		<col width="150"/>
		<col width="300"/>
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
</div>



<%@ include file="../common/foot.jspf"%>