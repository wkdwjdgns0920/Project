<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jspf"%>

<script>
	var paramId = ${param.id};
	
	function articleDetail_increaseHitCount(){
		
		const localStorageKey = 'article__' + params.id + '__alreadyView';
		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true); 
		
		$.get('../article/doIncreaseHitCount',{
			id : paramId,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article_hitCount').empty().html(data.data1);
		},'json');
	}
	
	$(function(){
		articleDetail_increaseHitCount();
	})
	
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
									<a href="/usr/reactionPoint/cancelLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
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
									<a href="/usr/reactionPoint/cancelDisLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
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




<%@ include file="../common/foot.jspf"%>