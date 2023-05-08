<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Write" />
<%@ include file="../common/head.jspf"%>

<div class="mt-8 text-xl bor-b po-rel">
	<form action="../article/doWrite">
		<table class="table table-zebra w-full ">
			<colgroup>
				<col width="50" />
				<col width="300" />
			</colgroup>
			<tbody>
				<tr>
					<th>작성자</th>
					<td>${rq.loginedMember.nickname}</td>
				</tr>
				<tr>
					<th>게시판</th>
					<td>
						<select name="boardId">
							<option value="1">공지사항</option>
							<option value="2">자유</option>
							<option value="3">QNA</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input class="text_box" type="text" name="title" placeholder="제목" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="body_box" name="body" type="text" placeholder="내용"></textarea>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<button type="submit">글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div class="btns flex justify-end">
	<button class="btn-text-link m-1" type="button" onclick="history.back();">뒤로가기</button>
</div>

<%@ include file="../common/foot.jspf"%>