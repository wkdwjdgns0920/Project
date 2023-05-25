<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<section class="div_center">
	<div class="mt-8 text-xl bor-b po-rel w-800">

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
						<th>첨부 이미지</th>
						<td>
							<img class="img_file rounded-xl" src="${rq.getImgUri(article.id)}" onerror="${rq.profileFallbackImgOnErrorHtml}"
								alt="" />
							<div>${rq.getImgUri(article.id)}</div>
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
			
			<!-- Ajax활용 reaction -->
			<script>
			function like_reaction() {
			var relId = ${param.id};
			var actorId = ${rq.loginedMemberId}
			alert('좋아요!');
			// ajax활용하여 reaction실행
			$.get('../reactionPoint/like', {
				isAjax : 'Y',
				relTypeCode : 'article',
				relId : relId
			}, function(data) {
				if (data.success) {
					alert(data.data1);
					$('.total_point').html('<span class="total_point">' + data.data1 + '</span>')
					$('.reaction_btn').html('<span class="bg_blue cursor cancelLike" style="background: linen blue; font-size: 2em" onclick="cancelLike_reaction()">&#128525;</span><div class="empty_box"></div><span class="cursor disLikeL" style="background: linen; font-size: 2em" onclick="alert(this.title)" title="좋아요를 취소해">&#128557;</span>')
				} else {
					alert('안됨');
				}

			}, 'json');
			};
			
			function cancelLike_reaction() {
				var relId = ${param.id};
				var actorId = ${rq.loginedMemberId}
				alert('좋아요취소');
				// ajax활용하여 reaction실행
				$.get('../reactionPoint/cancelLike', {
					isAjax : 'Y',
					relTypeCode : 'article',
					relId : relId
				}, function(data) {
					if (data.success) {
						$('.total_point').html('<span class="total_point">' + data.data1 + '</span>')
						$('.reaction_btn').html('<span class="bg_blue cursor like" style="background: linen; font-size: 2em" onclick="like_reaction()">&#128525;</span><div class="empty_box"></div><span class="cursor disLike" style="background: linen; font-size: 2em" onclick="disLike_reaction()">&#128557;</span>')
					} else {
						alert('안됨');
					}

				}, 'json');
				};
				
				function disLike_reaction() {
					var relId = ${param.id};
					var actorId = ${rq.loginedMemberId}
					alert('싫어요!');
					// ajax활용하여 reaction실행
					$.get('../reactionPoint/disLike', {
						isAjax : 'Y',
						relTypeCode : 'article',
						relId : relId
					}, function(data) {
						if (data.success) {
							$('.reaction_btn').html('<span class="bg_blue cursor likeD" style="background: linen; font-size: 2em" onclick="alert(this.title)" title="싫어요를 취소해">&#128525;</span><div class="empty_box"></div><span class="cursor bg_red cancelDisLike" style="background: linen red; font-size: 2em" onclick="cancelDisLike_reaction()">&#128557;</span>')
						} else {
							alert('안됨');
						}

					}, 'json');
					};
					
				function cancelDisLike_reaction() {
					var relId = ${param.id};
					var actorId = ${rq.loginedMemberId}
					alert('싫어요취소');
					// ajax활용하여 reaction실행
					$.get('../reactionPoint/cancelDisLike', {
						isAjax : 'Y',
						relTypeCode : 'article',
						relId : relId
					}, function(data) {
						if (data.success) {
							$('.reaction_btn').html('<span class="bg_blue cursor like" style="background: linen; font-size: 2em" onclick="like_reaction()">&#128525;</span><div class="empty_box"></div><span class="cursor disLike" style="background: linen; font-size: 2em" onclick="cancelDisLike_reaction()">&#128557;</span>')
						} else {
							alert('안됨');
						}

					}, 'json');
					};
			</script>

			<style>
				.cursor {
					cursor: pointer;
				}
				.reaction_btn {
					display: flex;
				}
				
			</style>

			<div>
				<div class="h-30"></div>
				<div class="div_center reaction_box">
					<c:if test="${actorCanReaction }">
						<div class="reaction_btn">
							<span class="cursor like" style="background: linen; font-size: 2em" onclick="like_reaction()">&#128525;</span>
							<div class="empty_box"></div>
							<span class="cursor disLike" style="background: linen; font-size: 2em" onclick="disLike_reaction()">&#128557;</span>
						</div>
					</c:if>
				</div>
				<div class="div_center reaction_box">
					<c:if test="${actorCanCancelLikeReaction }">
						<div class="reaction_btn">
							<span class="bg_blue cursor cancelLike" style="background: linen blue; font-size: 2em" onclick="cancelLike_reaction()">&#128525;</span>
							<div class="empty_box"></div>
							<span class="cursor disLikeL" style="background: linen; font-size: 2em" onclick="alert(this.title)" title="좋아요를 취소해">&#128557;</span>
						</div>
					</c:if>
				</div>
				<div class="div_center reaction_box">
					<c:if test="${actorCanCancelDisLikeReaction }">
						<div class="reaction_btn">
							<span class="bg_blue cursor likeD" style="background: linen; font-size: 2em" onclick="alert(this.title)" title="싫어요를 취소해">&#128525;</span>
							<div class="empty_box"></div>
							<span class="cursor bg_red cancelDisLike" style="background: linen red; font-size: 2em" onclick="cancelDisLike_reaction()">&#128557;</span>
						</div>
					</c:if>
				</div>
				<div class="h-30"></div>
				<div class="div_center mb-20">
					<span>좋아요 :&nbsp</span>
					<span class="total_point">${article.likePoint }</span>
				</div>
			</div>
		</div>
		<div class="btns lowMenu flex justify-end mt_10">
			<!-- 디테일페이지 이동버튼 -->
			<div class="movePage_btn">
				<a href="detail?id=${param.id -1 }">
					<button class="btn btn-active btn-ghost">이전글</button>
				</a>
				<a href="detail?id=${param.id +1 }">
					<button class="btn btn-active btn-ghost">다음글</button>
				</a>
				<a href="list">
					<button class="btn btn-active btn-ghost">목록</button>
				</a>
			</div>
			<div class="empty_box"></div>
			<button class="btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>
			<c:if test="${article.actorCanModify }">
				<a class="btn btn-active btn-ghost" href="../article/modify?id=${article.id }">수정</a>
			</c:if>
			<c:if test="${article.actorCanDelete }">
				<a class="btn btn-active btn-ghost" onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false;"
					href="../article/doDelete?id=${article.id }">삭제</a>
			</c:if>
		</div>
	</div>
</section>

<!-- 댓글작성폼 -->
<section class="text-xl reply_form div_center">
	<c:if test="${rq.logined }">
		<form action="../reply/doWrite" method="POST" onsubmit="ReplyWrite__submitForm(this); return false;">
			<input type="hidden" name="relTypeCode" value="article" />
			<input type="hidden" name="relId" value="${article.id }" />
			<input type="hidden" name="replaceUri" value="${rq.currentUri }" />
			<div class="reply_write_box px-3">
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
		<div class="reply_box check_login">
			댓글을 작성하려면 &nbsp;
			<a class="reply_box_a" href="${rq.loginUri }">로그인</a>
			&nbsp; 해주세요
		</div>
	</c:if>
</section>



<!-- 댓글리스트 -->
<section class="mt-5 reply_list div_center">
	<div>
		<div class="mx-auto px-3">
			<h1 class="text-3xl">댓글 리스트(${repliesCount })</h1>
		</div>
		<div class="reply_box w-800">
			<c:forEach var="reply" items="${replies }">
				<div class="r-t replyId_${reply.id }" id="${reply.id }">${reply.id }</div>
				<div>${reply.extra__writer }</div>

				<div class="reply_body_${reply.id }" id="">${reply.body }</div>

				<textarea class="input input-bordered w-full reply_write_body reply_modify_body_${reply.id }" type="text"
					name="body" placeholder="내용을 입력해주세요" style="display: none;" />${reply.body }</textarea>

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
    var modifyTxt = document.querySelector(".reply_modify_body_" + replyId);
    var replyBody = document.querySelector(".reply_body_" + replyId);

    // 수정창 영역의 표시 스타일을 전환
    modifyTxt.style.display = modifyTxt.style.display === "none" ? "block" : "none";

    // 수정버튼의 표시 스타일에 따라 버튼 텍스트 변경
    btn.textContent = modifyTxt.style.display === "none" ? "수정하기" : "취소";
    // 수정창의 표시 스타일에 따라 doModify_btn 디스플레이 설정
    doModify_btn.style.display = modifyTxt.style.display === "none" ? "none" : "inline";
    // 수정창의 표시 스타일에 따라 댓글창 디스플레이 설정
    replyBody.style.display = modifyTxt.style.display === "none" ? "block" : "none";
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
	</div>
</section>


<%@ include file="../common/foot.jspf"%>