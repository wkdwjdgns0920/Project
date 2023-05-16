<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jspf"%>

<script>
	const params = {}
	params.id = parseInt('${param.id}');
</script>

<!-- 조회수 -->
<script>
	var paramId = ${param.id};

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
<!-- 게시글상세보기 -->
<div class="mt-8 text-xl bor-b po-rel">
	<div class="mx-auto px-3">
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
									<a href="/usr/reactionPoint/like?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
										class="btn btn-xs">좋아요 👍</a>
								</span>
								<span>
									<span>&nbsp;</span>
									<a href="/usr/reactionPoint/disLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
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
									<a onclick="alert(this.title); return false;" title="좋아요를 먼저 취소해" class="btn btn-xs">싫어요 👎</a>
								</span>
							</div>
						</c:if>
						<c:if test="${actorCanCancelDisLikeReaction }">
							<div>
								<span>
									<span>&nbsp;</span>
									<a onclick="alert(this.title); return false;" title="싫어요를 먼저 취소해" class="btn btn-xs">좋아요 👍</a>
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

<!-- 댓글작성폼 -->
<section class="text-xl reply_form">
	<c:if test="${rq.logined }">
		<form action="../reply/doWrite" method="POST" onsubmit="ReplyWrite__submitForm(this); return false;">
			<input type="hidden" name="relTypeCode" value="article" />
			<input type="hidden" name="relId" value="${article.id }" />
			<input type="hidden" name="replaceUri" value="${rq.currentUri }" />
			<div class="reply_write_box mx-auto px-3">
				<div>댓글을 입력해주세요</div>
				<div class="reply_write__box flex justify-between">
					<div>
						<textarea class="input input-bordered w-full reply_write_body" type="text" name="body" placeholder="내용을 입력해주세요" /></textarea>
					</div>
					<button class="btn reply_write_btn" type="submit" value="작성">댓글작성</button>
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${!rq.logined}">
		<div class="reply_box_check_login">
			댓글을 작성하려면 &nbsp;
			<a class="reply_box_a" href="${rq.loginUri }">로그인</a>
			&nbsp; 해주세요
		</div>
	</c:if>
</section>



<!-- 댓글리스트 -->
<section class="mt-5 reply_list">
	<div class="mx-auto px-3">
		<h1 class="text-3xl">댓글 리스트(${repliesCount })</h1>
	</div>
	<div class="reply_box">
		<c:forEach var="reply" items="${replies }">
			<div class="r-t replyId_${reply.id }" id="${reply.id }">${reply.id }</div>
			<div>${reply.extra__writer }</div>

			<div class="reply_body_${reply.id }" id="">${reply.body }</div>

			<textarea class="input input-bordered w-full reply_write_body reply_modify_body_${reply.id }" type="text" name="body"
				placeholder="내용을 입력해주세요" style="display: none;" />${reply.body }</textarea>

			<div class="reply_regDate">${reply.regDate.substring(0,16) }</div>

			<div class="modify_btn_box">
				<c:if test="${reply.actorCanModify }">
					<button class="reply_modify_btn_${reply.id }" onclick="showModifyForm(${reply.id})">수정하기</button>
					<button class="p-1 reply_doModify_btn_${reply.id }" onclick="modifyReply(${reply.id})" style="display: none;">수정하기2</button>
				</c:if>
			</div>
			<hr />
		</c:forEach>
	</div>

	<!-- 댓글수정 -->
	<script>
  function showModifyForm(replyId) {
    // 해당 요소 가져오기
    var btn = document.querySelector(".reply_modify_btn_" + replyId); // 댓글수정 보여지는 버튼
    var doModify_btn = document.querySelector(".reply_doModify_btn_" + replyId); // 댓글 수정하기 버튼
    var textarea = document.querySelector(".reply_modify_body_" + replyId);

    // 텍스트 영역의 표시 스타일을 전환
    textarea.style.display = textarea.style.display === "none" ? "block" : "none";

    // 텍스트 영역의 표시 스타일에 따라 버튼 텍스트 변경
    btn.textContent = textarea.style.display === "none" ? "수정하기" : "취소";
    // 텍스트 영역의 표시 스타일에 따라 doModify_btn 디스플레이 설정
    doModify_btn.style.display = textarea.style.display === "none" ? "none" : "inline";
  }
  
  function modifyReply(replyId) {
	    // 해당 텍스트 영역과 해당 값을 가져옵니다.
	    var textarea = document.querySelector(".reply_modify_body_" + replyId);
	    var modifydBody = textarea.value; // 수정된 댓글 본문 가져오기
	    var paramId = ${param.id};
	    
	      // ajax활용하여 doModify실행
			$.get('../reply/doModify', {
				isAjax : 'Y',
				id : replyId,
				body : modifydBody
			}, function(data) {

				if (data.success) {
					alert('수정성공!@#');
				} else {
					alert('수정실패!@#');
				}

			}, 'json');
      
	      // 댓글이 성공적으로 업데이트된 후 페이지를 새로고침
	      window.location.reload();
	    }

</script>


	<!-- 댓글페이징 -->
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


<%@ include file="../common/foot.jspf"%>