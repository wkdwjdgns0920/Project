<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jspf"%>

<!-- 조회수 -->
<script>
	var paramId = $
	{
		param.id
	};

	function articleDetail_increaseHitCount() {

		const localStorageKey = 'article__' + params.id + '__alreadyView';
		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCount', {
			id : paramId,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article_hitCount').empty().html(data.data1);
		}, 'json');
	}

	$(function() {
		articleDetail_increaseHitCount();
	})
</script>

<!-- 댓글 -->
<script>
	let ReplyWrite__submitFormDone = false;
	function ReplyWrite__submitForm(form) {
		if (ReplyWrite__submitFormDone) {
			return;
		}
		form.body.value = form.body.value.trim();
		if (form.body.value.length < 3) {
			alert('3글자 이상 입력하세요');
			form.body.focus();
			return;
		}
		ReplyWrite__submitFormDone = true;
		form.submit();
	}
</script>

<div class="mt-8 text-xl bor-b po-rel">
	<div class="container mx-auto px-3">
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
					<th>작성자</th>
					<td class="text-center">${article.extra_writer }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td class="text-center">
						<span class="article_hitCount">${article.hitCount }</span>
					</td>
				</tr>
				<tr>
					<th>추천</th>
					<td class="text-center">
						<span>좋아요 : ${article.likePoint }&nbsp;</span>
						<span>싫어요 : ${article.disLikePoint }&nbsp;</span>
						<c:if test="${actorCanReaction }">
							<div>
								<span>
									<span>&nbsp;</span>
									<a
										href="/usr/reactionPoint/like?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
										class="btn btn-xs">좋아요 👍</a>
								</span>
								<span>
									<span>&nbsp;</span>
									<a
										href="/usr/reactionPoint/disLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
										class="btn btn-xs">싫어요 👎</a>
								</span>
							</div>
						</c:if>
						<c:if test="${actorCanCancelLikeReaction }">
							<div>
								<span>
									<span>&nbsp;</span>
									<a
										href="/usr/reactionPoint/cancelLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
										class="btn btn-xs">좋아요 👍</a>
								</span>
								<span>
									<span>&nbsp;</span>
									<a onclick="alert(this.title); return false;"
										title="좋아요를 먼저 취소해" class="btn btn-xs">싫어요 👎</a>
								</span>
							</div>
						</c:if>
						<c:if test="${actorCanCancelDisLikeReaction }">
							<div>
								<span>
									<span>&nbsp;</span>
									<a onclick="alert(this.title); return false;"
										title="싫어요를 먼저 취소해" class="btn btn-xs">좋아요 👍</a>
								</span>
								<span>
									<span>&nbsp;</span>
									<a
										href="/usr/reactionPoint/cancelDisLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
										class="btn btn-xs">싫어요 👎</a>

								</span>
							</div>
						</c:if>
					</td>
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
	<div class="btns lowMenu flex justify-end">
		<button class="btn-text-link m-1" type="button"
			onclick="history.back();">뒤로가기</button>
		<c:if test="${article.actorCanModify }">
			<a class="btn-text-link m-1"
				href="../article/modify?id=${article.id }">수정</a>
		</c:if>
		<c:if test="${article.actorCanDelete }">
			<a class="btn-text-link m-1"
				onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false;"
				href="../article/doDelete?id=${article.id }">삭제</a>
		</c:if>
	</div>
</div>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<c:if test="${rq.logined }">
				<form action="../reply/doWrite" method="POST"
					onsubmit="ReplyWrite__submitForm(this); return false;">
					<input type="hidden" name="relTypeCode" value="article" />
					<input type="hidden" name="relId" value="${article.id }" />
					<input type="hidden" name="replaceUri" value="${rq.currentUri }" />
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>

						<tbody>
							<tr>
								<th>댓글</th>
								<td>
									<textarea class="input input-bordered w-full max-w-xs"
										type="text" name="body" placeholder="내용을 입력해주세요" /></textarea>
								</td>
							</tr>
							<tr>
								<th></th>
								<td>
									<button type="submit" value="작성" />
									댓글 작성
									</button>
								</td>
							</tr>
						</tbody>

					</table>
				</form>
			</c:if>
			<c:if test="${!rq.logined}">
				<div class="reply_box_nl">
					댓글을 작성하려면 &nbsp;
					<a class="reply_box_a" href="/usr/member/login">로그인</a>
					&nbsp; 해주세요
				</div>
			</c:if>
		</div>
	</div>
</section>

<section class="mt-5">
	<div class="container mx-auto px-3">
		<h1 class="text-3xl">댓글 리스트(${repliesCount })</h1>
	</div>
	<div class="reply_box">
		<c:forEach var="reply" items="${replies }">
			<div class="r-t">${reply.id }</div>
			<div>${reply.extra__writer }</div>
			
			<div class="reply_body${reply.id }">${reply.body }</div>
			
			<div>${reply.regDate.substring(0,16) }</div>
			
			<div class="modify_btn_box">
				<c:if test="${reply.actorCanModify }">
					<button class="modify-btn btn" onclick="modifyReply(${reply.id})" >수정하기</button>
					<button class="doModify-btn btn" onclick="doModifyReply(${reply.id})"  style="display:none;">수정하기2</button>
				</c:if>
			</div>
			
			<hr />
		</c:forEach>
	</div>
	<div class="flex justify-center mt-3">

		<c:set var="pageLen" value="4" />
		<c:set var="startPage" value="${page - pageLen >=1 ? page - pageLen : 1 }" />
		<c:set var="endPage" value="${page + pageLen <= pagesCount ? page + pageLen : pagesCount }" />
		
		
		<c:set var="baseUri" value="detail?id=${param.id }" />
	

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
</section>


<!-- 댓글수정 -->
<!-- <script>
function modifyReply(el) {
    var replyBody = $('.reply_body' + el).html();
    
    $('.reply_body' + el).empty();
    const form = $(el).closest('form').get(0);
    
    $('.reply_body' + el).html('<input class="mt-2 reply_modify_box" value="'+ replyBody +'">');
    
    $('.modify-btn').css('display', 'none');
    $('.doModify-btn').css('display', 'inline');
}

</script> -->
<!-- <script>
function doModifyReply(el) {
	var id = el;
	var body = $('.reply_body' + el '> .reply_modify_box').val();
	
	$.get('../reply/doModify', {
		id : id,
		body : body,
		ajaxMode : 'Y'
	}, function(data) {
		alert(data.msg);
		location.reload();
	}, 'json');
}
</script> -->


<%@ include file="../common/foot.jspf"%>