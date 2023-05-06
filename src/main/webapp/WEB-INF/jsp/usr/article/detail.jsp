<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article List" />
<%@ include file="../common/head.jspf"%>

<div class="mt-8 text-xl bor-b">
	<table class="table table-zebra w-full ">
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
				<th>작성자</th>
				<td class="text-center">${article.extra_writer }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td></td>
			</tr>
			<tr>
				<th>추천</th>
				<td></td>
			</tr>
			<tr>
				<th>제목</th>
				<td class="text-center">${article.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td class="text-center">${article.body }</td>
			</tr>
		</tbody>
	</table>
</div>



<%@ include file="../common/foot.jspf"%>