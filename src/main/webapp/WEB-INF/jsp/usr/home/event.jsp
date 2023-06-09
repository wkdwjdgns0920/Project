<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="대전광역시 공연행사정보" />

<%@ include file="../common/head.jspf"%>
<!-- 공연API -->
<script>
	var itemsPerPage = 5;
	var page = ${param.page	};
	var startPage = (page - 1) * itemsPerPage + 1;
	var endPage = page * itemsPerPage;

	var xhr = new XMLHttpRequest();
	var url = 'http://apis.data.go.kr/6300000/eventDataService/eventDataListJson'; /*URL*/
	var queryParams = '?'
			+ encodeURIComponent('serviceKey')
			+ '='
			+ 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('') + '=' + encodeURIComponent(''); /**/
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {

			var response = JSON.parse(this.responseText);
			var eventData = response.msgBody;

			console.log(eventData);

			/* 캘린더에 공연날짜설정*/
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView : 'dayGridMonth',
				events : eventData.map(function(event) {
					return {
						title : event.title,
						start : event.beginDt,
					};
				})
			});

			calendar.render(); //캘린더그리기

			for (var i = startPage - 1; i < endPage; i++) {
				var title = eventData[i].title;
				var beginDt = eventData[i].beginDt;
				var placeCdNm = eventData[i].placeCdNm;
				var targetCdNm = eventData[i].targetCdNm;

				var eventElement = document.createElement("div");
				eventElement.innerHTML = "<div class='schedule'><br>공연: " + title + "<br>날짜: "
						+ beginDt + "<br>장소: " + placeCdNm + "<br>관람연령: "
						+ targetCdNm + "<br></div>";
				titleContainer.appendChild(eventElement);

			}

		}
	};

	xhr.send('');
</script>

<!-- 캘린더관련 -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js'></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth'
		});
		calendar.render();
	});
</script>


<style>
.thema_box {
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}
.bg-w {
	background-color: white;
}
.showTitle{
	font-size: 2rem;
	text-align: center;
}
.schedule_box{
	color: black;
	background-color: white;
}
.schedule {
	padding: 0 10px;
}
</style>

<div class="thema_box">
	<img class="flexCardTh" src="/resources/img/테마3.jpg" alt="" />
</div>

<section class="div_center mt_50">
	<div class="schedule_box animate__animated animate__backInLeft">
		<div class="showTitle">공연정보</div>
		<div class="" id="titleContainer"></div>
	</div>
	<div class="empty_box"></div>
	<div class="animate__animated animate__backInLeft bg-w" id='calendar'></div>
</section>

<div class="flex justify-center mt-3">

	<c:set var="startPage" value="1" />
	<c:set var="endPage" value="2" />

	<div>
		<c:forEach begin="${startPage }" end="${endPage }" var="i">
			<a class="p-1 ${param.page == i ? 'page_active btn-active' : '' }" href="?page=${i }">${i }</a>
		</c:forEach>

	</div>
</div>





<%@ include file="../common/foot.jspf"%>