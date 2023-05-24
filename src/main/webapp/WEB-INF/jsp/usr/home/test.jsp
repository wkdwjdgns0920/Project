<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="대전광역시 문화관광(관광지)" />

<%@ include file="../common/head.jspf"%>
<script>
	var itemsPerPage = 10;
	var page = ${param.page};
	var startPage = (page - 1) * itemsPerPage + 1;
	var endPage = page * itemsPerPage;

	var xhr = new XMLHttpRequest();
	var url = 'https://apis.data.go.kr/6300000/openapi2022/tourspot/gettourspot'; /*URL*/
	var queryParams = '?'
			+ encodeURIComponent('serviceKey')
			+ '='
			+ 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('pageNo') + '='
			+ encodeURIComponent('1'); /**/
	queryParams += '&' + encodeURIComponent('numOfRows') + '='
			+ encodeURIComponent('142');
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			var response = JSON.parse(this.responseText);
			var eventData = response.response.body;

			console.log(eventData);

			for (var i = startPage - 1; i < endPage; i++) {
				var tourspotSumm = eventData.items[i].tourspotSumm;
				var mapLat = eventData.items[i].mapLat;
				var mapLot = eventData.items[i].mapLot;
				var mngTime = eventData.items[i].mngTime;
				var pkgFclt = eventData.items[i].pkgFclt;
				var urlAddr = eventData.items[i].urlAddr;
				var tourspotDtlAddr = eventData.items[i].tourspotDtlAddr;
				var tourspotNm = eventData.items[i].tourspotNm;
				var cnvenFcltGuid = eventData.items[i].cnvenFcltGuid;

				var eventElement = document.createElement("tr");

				eventElement.innerHTML = "<td><div class=eventId>" + (i + 1)
						+ " </div></td><td><div class=eventTitle_" + i
						+ " onclick=showModal(" + i
						+ ") style='cursor: pointer'> " + tourspotSumm
						+ " </div></td>"

				event_box.appendChild(eventElement);

				var spot_title = document.createElement("div");
				spot_title.innerHTML = "<div class='tourSpot_Title_"+ i +"' style='display:none;'>주제 : "+ tourspotSumm + " </div>"
				detail_title.appendChild(spot_title);
				
				var spot_body = document.createElement("div");
				spot_body.innerHTML = "<div class='tourSpot_Body_"+ i +"' style='display:none;'>이용시간 : "+ mngTime+ "<br>위치 : "+ tourspotNm+ "<br>도로명주소 : "+ tourspotDtlAddr+ "<br>안내소 : "+ cnvenFcltGuid+ "<br>사이트 : "+ urlAddr+ "<br><br><button class='btn-text-link btn btn-active btn-ghost' onclick=showMap("+ mapLat +  ',' + mapLot +")>위치보기</button></div>";
				detail_body.appendChild(spot_body);

				var modal_btn = document.createElement("button");
				modal_btn.innerHTML = "<button class='detail_close_btn' onclick='close_Modal(" + i
						+ ")' style='display:none;'>X</button>"
				detail_close.appendChild(modal_btn);

			}

			/* var lat = 36.366615;
			var lot = 127.38376;
			showMap(lat, lot); */
		}
	};

	xhr.send('');
</script>

<script>
	
</script>

<!-- 페이지 알림섹션 -->
<section class="topSection">
	<img class="topImg" src="/resources/img/img4.jpg" />
	<div class="explain">문화관광(관광지)</div>
</section>

<style>
.modal_box{
	display: none;
}
.map_box{
	width: 600px;
	height: 500px;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
	z-index: 12;
	display: none;
}
.sopt_detail {
	width: 600px;
	height: 500px;
	background-color: white;
	color: black;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
	z-index: 11;
	border-radius: 20px;
	font-size: 25px;
	padding:50px;
}
.modal_empty {
	height: 100px;
}
.modal_empty2 {
	height: 50px;
}
.sopt_detail_bg {
	/* display: none; */
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, .4);
	position: fixed;
	top: 0;
	left: 0;
	z-index: 10;
}

.z-index-0 {
	z-index: -100;
}

#detail_close {
	display: flex;
	justify-content: end;
	height: 50px;
	width: 50px;
}

.detail_close_btn {
	width: 50px;
	height: 50px;
	background-color: rgba(0, 0, 0, .5);
	color: rgba(0, 0, 0, .2);
	text-align: center;
	border-radius: 50px;
	font-size: 2rem;
	position: fixed;
	top: 10%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
	z-index: 11;
}

#detail_body {
	margin-bottom: 50px;
}


</style>

<!-- 모달창 디스플레이 설정 -->
<script>
function close_Modal(el) {
	var modalBox = document.querySelector(".modal_box");
	var title = document.querySelector(".tourSpot_Title_" + el);
	var body = document.querySelector(".tourSpot_Body_" + el);
	var close = document.querySelector(".detail_close_btn");
	var map_box = document.querySelector(".map_box");

	modalBox.style.display = "none";
	title.style.display = "none";
	body.style.display = "none";
	close.style.display = "none";
	map_box.style.display = "none";
}

function showModal(el) {
	var modalBox = document.querySelector(".modal_box");
	var title = document.querySelector(".tourSpot_Title_" + el);
	var body = document.querySelector(".tourSpot_Body_" + el );
	var close = document.querySelector(".detail_close_btn");

	modalBox.style.display = "block";
	title.style.display = "block";
	body.style.display = "block";
	close.style.display = "block";
}
</script>

<!-- 모달창 -->
<div class="modal_box">
	
	<div class="sopt_detail_bg"></div>
	<div id="detail_close"></div>
	<div class="sopt_detail">
		<div id="detail_title"></div>
		<div class="modal_empty"></div>
		<div id="detail_body"></div>
	</div>
	<div class="map_box">
		<div id="map" style="width: 500px; height: 500px;"></div>
	</div>
</div>

<section class="event_section div_center">
	<div class="mt-8 text-xl div_center w-00">
		<table class="table w-700 mr-20">
			<thead>
			<colgroup>
				<col width="50" />
				<col width="100" />
			</colgroup>
			<tr class="ta-l">
				<th>번호</th>
				<th>관광지</th>
			</tr>
			</thead>
			<tbody id="event_box">
			</tbody>
		</table>
	</div>
</section>
<div class="flex justify-center mt-3">

	<c:set var="pageLen" value="4" />
	<c:set var="startPage" value="1" />
	<c:set var="endPage" value="14" />

	<c:if test="${page > 1 }">
		<a class="" href="?page=1">◀◀</a> &nbsp&nbsp
			<a class="" href="?page=${page-1 }">◀</a>
	</c:if>

	<div>
		<c:forEach begin="${startPage }" end="${endPage }" var="i">
			<a class="p-1 test ${param.page == i ? 'btn-active' : '' }" href="?page=${i }">${i }</a>
		</c:forEach>

		<c:if test="${page < pagesCount }">
			<a class="" href="?page=${page + 1 }">▶</a>&nbsp&nbsp
				<a class="" href="?page=${pagesCount }">▶▶</a>
		</c:if>

	</div>
</div>

<!-- 지도API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3047bd817887b648d90088cc385d3845&libraries=services"></script>
<script>
	function showMap(lat1, lot1) {
		var map_box = document.querySelector(".map_box");
		map_box.style.display = "block";
		
		lat = lat1;
		lot = lot1;

		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(lat, lot),
			level : 3
		};

		var map = new kakao.maps.Map(container, options);

		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(lat, lot);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);
	}
</script>
<%@ include file="../common/foot.jspf"%>