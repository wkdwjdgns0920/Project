<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				var refadNo = eventData.items[i].refadNo;
				var tourspotAddr = eventData.items[i].tourspotAddr;
				var tourspotNm = eventData.items[i].tourspotNm;
				var cnvenFcltGuid = eventData.items[i].cnvenFcltGuid;

				var eventIdElement = document.createElement("div");
				var eventTitleElement = document.createElement("div");
				var eventBodyElement = document.createElement("div");

				eventIdElement.innerHTML = "<div class=eventId>" + (i + 1)	+ " </div>"
				idContainer.appendChild(eventIdElement);

				eventTitleElement.innerHTML = "<div class=eventTitle" + i + " onclick=showApi(" + i + ") style='cursor: pointer'> " + tourspotSumm	+ " </div>"
				titleContainer.appendChild(eventTitleElement);

				eventBodyElement.innerHTML = "<div class=eventBody"+ i +" style='display: none;'>mngTime : " + mngTime + "<br>주소 :" + tourspotAddr + "<br>tourspotNm :" + tourspotNm	+ "<br>cnvenFcltGuid : " + cnvenFcltGuid + "<br> <button class='btn-text-link btn btn-active btn-ghost' onclick=showMap("+ mapLat +  ',' + mapLot +")>위치보기</button> </div>"
				bodyContainer.appendChild(eventBodyElement);
			}
			
			var lat = 36.366615;
            var lot = 127.38376;
            showMap(lat, lot);
		}
	};

	xhr.send('');
</script>

<!-- title 클릭시에 안의 내용보여주기 -->
<script>
	function showApi(el) {
		
		// 해당 요소 가져오기
		var details = document.querySelector(".eventBody" + el); // 


		// details display설정
		details.style.display = details.style.display === "none" ? "block": "none";
	}
	

</script>

<section class="event_section div_center">
	<div class="mt-8 text-xl div_center w-00">
		<table class="table w-700">
			<thead>
			<colgroup>
				<col width="50" />
				<col width="100" />
			</colgroup>
			<tr>
				<th>번호</th>
				<th>관광지</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div id="idContainer"></div>
					</td>
					<td>
						<div id="titleContainer"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<div id="map" style="width: 500px; height: 500px;"></div>
		<div id="bodyContainer"></div>
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
					<a class="p-1 test ${param.page == i ? 'btn-active' : '' }"
						href="?page=${i }">${i }</a>
				</c:forEach>

				<c:if test="${page < pagesCount }">
					<a class="" href="?page=${page + 1 }">▶</a>&nbsp&nbsp
				<a class="" href="?page=${pagesCount }">▶▶</a>
				</c:if>

			</div>
		</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3047bd817887b648d90088cc385d3845&libraries=services"></script>
<!-- 지도API -->
<!-- 지도API -->
<script>
function showMap(lat1,lot1){
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