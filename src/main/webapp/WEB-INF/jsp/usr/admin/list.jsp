<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name }" />
<%@ include file="../common/head.jspf"%>

<style>
.thema_box {
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}

</style>



<div class="thema_box">
	<img class="flexCardTh" src="/resources/img/테마4.jpg" alt="" />
</div>

<section class="div_center mt-8 animate__animated animate__backInUp ">

	<div class="mt-8 text-xl po-rel list_bg">
		
		<div class="flex mb-4">
			<div>
				게시물 갯수 :
				<span class="badge">${articlesCount }</span>
				개
			</div>
			<div class="flex-grow"></div>
			<form action="">
				<input type="hidden" name="boardId" value="${param.boardId }" />
				<select data-value="${param.aSearchKeywordType }" name="aSearchKeywordType" class="select select-ghost">
					<option value="title">제목</option>
					<option value="body">내용</option>
					<option value="title,body">제목 + 내용</option>
				</select>
				<input value="${param.aSearchKeyword }" maxlength="20" name="aSearchKeyword" class="input input-bordered" type="text"
					placeholder="검색어를 입력해주세요" />
				<button class="btn btn-ghost" type=submit>검색</button>
			</form>
		</div>
		<table class="table table-zebra w-full list_fir">
			<thead>
				<colgroup>
					<col width="50" />
					<col width="50" />
					<col width="100" />
					<col width="150" />
					<col width="150" />
					<col width="300" />
					<col width="50" />
					<col width="50" />
				</colgroup>
				<tr>
					<th>
						<input type="checkbox" class="checkBox_all_a" />
					</th>
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
						<th>
							<input type="checkbox" class="checkBox_a" value="${article.id }" />
						</th>
						<td class="">${article.id }</td>
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
			
			<div>
				<button class="btn btn-error a_del_btn">선택삭제</button>
			</div>

			<form hidden method="POST" name="a_del_form" action="../admin/deleteArticles">
				<input type="hidden" name="ids" value="" />
			</form>

			
		
		
		<div class="flex justify-center mt-3">

			<c:set var="pageLen" value="2" />
			<c:set var="startPage" value="${aPage - pageLen >= 1 ? aPage - pageLen : 1 }" />
			<c:set var="endPage" value="${aPage + pageLen <= articlesCount ? aPage + pageLen : articlesCount }" />
			<c:if test="${aPage == 1 || aPage == 2 || aPage == 3}">
				<c:set var="endPage" value="${startPage + 4}" />
			</c:if>
			<c:if test="${endPage ==  articlesCount || endPage == articlesCount -1}">
				<c:set var="startPage" value="${endPage - 4}" />
			</c:if>
			<c:if test="${articlesCount == 1 || articlesCount == 2 || articlesCount == 3 || articlesCount == 4  }">
				<c:set var="endPage" value="${articlesCount}" />
			</c:if>	
			
			<c:set var="baseUri" value="?boardId=${boardId }" />
			<c:set var="baseUri" value="${baseUri }&aSearchKeywordType=${aSearchKeywordType}" />
			<c:set var="baseUri" value="${baseUri }&aSearchKeyword=${aSearchKeyword}" />

			<c:if test="${aPage > 1 }">
				<a class="" href="${baseUri }&aPage=1">◀◀</a> &nbsp&nbsp
			<a class="" href="${baseUri }&aPage=${aPage-1 }">◀</a>
			</c:if>

			<div>
				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="p-1 ${aPage == i ? 'btn-active page_active' : '' }" href="${baseUri }&aPage=${i }">${i }</a>
				</c:forEach>

				<c:if test="${aPage < articlesCount }">
					<a class="" href="${baseUri }&aPage=${aPage + 1 }">▶</a>&nbsp&nbsp
				<a class="" href="${baseUri }&aPage=${articlesCount }">▶▶</a>
				</c:if>

			</div>
		</div>
	</div>
</section>

<!-- 체크박스 전체선택 -->
<!-- 선택된 게시글 삭제 -->
<script>
			$('.checkBox_all_a').change(function() {
			      const $all = $(this);
			      const allChecked = $all.prop('checked');	
			      $('.checkBox_a').prop('checked', allChecked);
			    });
			    $('.checkBox_a').change(function() {
			      const checkboxArticleIdCount = $('.checkBox_a').length;
			      const checkboxArticleIdCheckedCount = $('.checkBox_a:checked').length;
			      const allChecked = checkboxArticleIdCount == checkboxArticleIdCheckedCount;
			      $('.checkBox_all_a').prop('checked', allChecked);
			      
			    });
			</script>
			<script>
    		$('.a_del_btn').click(function() {
      			const values = $('.checkBox_a:checked').map((index, el) => el.value).toArray();
      			if ( values.length == 0 ) {
       		 		alert('삭제할 게시글을 선택 해주세요.');
       		 		return;
     			}
      			if ( confirm('정말 삭제하시겠습니까?') == false ) {
        			return;
     			}
      			document['a_del_form'].ids.value = values.join(',');
      			document['a_del_form'].submit();
    		});
</script>

<section class="div_center mt-8 animate__animated animate__backInUp ">

	<div class="mt-8 text-xl po-rel list_bg">
		
		<div class="flex mb-4">
			<div>
				회원 수 :
				<span class="badge">${membersCount }</span>
				개
			</div>
			<div class="flex-grow"></div>
			<form action="">
				<select data-value="${param.mSearchKeywordType }" name="mSearchKeywordType" class="select select-ghost">
					<option value="loginId">로그인아이디</option>
					<option value="name">회원이름</option>
					<option value="nickname">회원이름</option>
					<option value="loginId,name,nickname">아이디 + 이름 + 닉네임</option>
				</select>
				<input value="${param.mSearchKeyword }" maxlength="20" name="mSearchKeyword" class="input input-bordered" type="text"
					placeholder="검색어를 입력해주세요" />
				<button class="btn btn-ghost" type=submit>검색</button>
			</form>
		</div>
		<table class="table table-zebra w-full list_fir">
			<thead>
				<colgroup>
					<col width="50" />
					<col width="50" />
					<col width="100" />
					<col width="150" />
					<col width="150" />
					<col width="300" />
					<col width="50" />
					<col width="50" />
				</colgroup>
				<tr>
					<th>
						<input type="checkbox" class="checkBox_all_m" />
					</th>
					<th>번호</th>
					<th>가입날짜</th>
					<th>수정날짜</th>
					<th>로그인아이디</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>탈퇴여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${members }">
					<tr>
						<td>
							<input type="checkbox" class="checkBox_m" value="${member.id }" />
						</td>
						<td class="">${member.id }</td>
						<td>${member.regDate.substring(2,16) }</td>
						<td>${member.updateDate.substring(2,16) }</td>
						<td>${member.loginId }</td>
						<td>${member.name }</td>
						<td>${member.nickname }</td>
						<c:if test="${member.delStatus == false }">
							<td>활동</td>
						</c:if>
						<c:if test="${member.delStatus == true }">
							<td>탈퇴</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div>
				<button class="btn btn-error m_del_btn">선택삭제</button>
			</div>

			<form hidden method="POST" name="m_del_form" action="../admin/deleteMembers">
				<input type="hidden" name="ids" value="" />
			</form>
		
		<div class="flex justify-center mt-3">

			<c:set var="pageLen" value="2" />
			<c:set var="startPage" value="${mPage - pageLen >= 1 ? mPage - pageLen : 1 }" />
			<c:set var="endPage" value="${mPage + pageLen <= membersCount ? mPage + pageLen : membersCount }" />
			<c:if test="${mPage == 1 || mPage == 2 || mPage == 3}">
				<c:set var="endPage" value="${startPage + 4}" />
			</c:if>
			<c:if test="${endPage ==  membersCount || endPage == membersCount -1}">
				<c:set var="startPage" value="${endPage - 4}" />
			</c:if>
			<c:if test="${membersCount == 1 || membersCount == 2 || membersCount == 3 || membersCount == 4  }">
				<c:set var="endPage" value="${membersCount}" />
			</c:if>	
			
			<c:set var="baseUri" value="?boardId=${boardId }" />
			<c:set var="baseUri" value="${baseUri }&mSearchKeywordType=${mSearchKeywordType}" />
			<c:set var="baseUri" value="${baseUri }&mSearchKeyword=${mSearchKeyword}" />

			<c:if test="${mPage > 1 }">
				<a class="" href="${baseUri }&mPage=1">◀◀</a> &nbsp&nbsp
			<a class="" href="${baseUri }&mPage=${mPage-1 }">◀</a>
			</c:if>

			<div>
				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="p-1 ${mPage == i ? 'btn-active page_active' : '' }" href="${baseUri }&mPage=${i }">${i }</a>
				</c:forEach>

				<c:if test="${mPage < membersCount }">
					<a class="" href="${baseUri }&mPage=${mPage + 1 }">▶</a>&nbsp&nbsp
				<a class="" href="${baseUri }&mPage=${membersCount }">▶▶</a>
				</c:if>

			</div>
		</div>
	</div>
</section>

<script>
			$('.checkBox_all_m').change(function() {
			      const $all = $(this);
			      const allChecked = $all.prop('checked');
			      $('.checkBox_m').prop('checked', allChecked);
			    });
			    $('.checkBox_m').change(function() {
			      const checkboxMemberIdCount = $('.checkBox_m').length;
			      const checkboxMemberIdCheckedCount = $('.checkBox_m:checked').length;
			      const allChecked = checkboxMemberIdCount == checkboxMemberIdCheckedCount;
			      $('.checkBox_all_m').prop('checked', allChecked);
			      
			    });
			</script>

			<script>
    		$('.m_del_btn').click(function() {
      			const values = $('.checkBox_m:checked').map((index, el) => el.value).toArray();
      			if ( values.length == 0 ) {
       		 		alert('삭제할 회원을 선택 해주세요.');
       		 		return;
     			}
      			if ( confirm('정말 삭제하시겠습니까?') == false ) {
        			return;
     			}
      			document['m_del_form'].ids.value = values.join(',');
      			document['m_del_form'].submit();
    		});
</script>

<%@ include file="../common/foot.jspf"%>