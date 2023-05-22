<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="대전광역시 문화축제 정보" />

<%@ include file="../common/head.jspf"%>
<script>
	var xhr = new XMLHttpRequest();
	var url = 'https://apis.data.go.kr/6300000/openapi2022/festv/getfestv'; /*URL*/
	var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
	queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('13');
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			
			var response = JSON.parse(this.response);
			var eventData = response.response.body;
			let id = ${param.id};
			
			console.log(eventData.items[id]);
			
			
			var festvNm = eventData.items[${param.id -1}].festvNm;
			var festvAddr = eventData.items[${param.id -1}].festvAddr;
			var festvDtlAddr = eventData.items[${param.id -1}].festvDtlAddr;
			var festvPrid = eventData.items[${param.id -1}].festvPrid;
			var festvSumm = eventData.items[${param.id -1}].festvSumm;
			var festvTpic = eventData.items[${param.id -1}].festvTpic;
			
			var idBox = document.createElement("div");
			var festvNmBox = document.createElement("div");
			var festvAddrBox = document.createElement("div");
			var festvDtlAddrBox = document.createElement("div");
			var festvPridBox = document.createElement("div");
			var festvSummBox = document.createElement("div");
			var festvTpicBox = document.createElement("div");
			
			idBox.innerHTML = "<div class='mt_10'>" + id + "</div>"
			festvNmBox.innerHTML = "<div class='mt_10'>" + festvNm + "</div>"
			festvAddrBox.innerHTML = "<div class='mt_10'>" + festvAddr + "</div>"
			festvDtlAddrBox.innerHTML = "<div class='mt_10'>" + festvDtlAddr + "</div>"
			festvPridBox.innerHTML = "<div class='mt_10'>" + festvPrid + "</div>"
			festvSummBox.innerHTML = "<div class='mt_10' style='white-space : pre-wrap;' >" + festvSumm + "</div>"
			festvTpicBox.innerHTML = "<div class='mt_10'>" + festvTpic + "</div>"
			
			idContainer.appendChild(idBox);
			festvNmContainer.appendChild(festvNmBox);
			festvAddrContainer.appendChild(festvAddrBox);
			festvDtlAddrContainer.appendChild(festvDtlAddrBox);
			festvPridContainer.appendChild(festvPridBox);
			festvSummContainer.appendChild(festvSummBox);
			festvTpicContainer.appendChild(festvTpicBox);
			
			showMap(festvAddr,festvNm);
		}
	};

	xhr.send('');
</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3047bd817887b648d90088cc385d3845&libraries=services"></script>
<script>

function showMap(festvAddr,festvNm) {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  

	//지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	//주소로 좌표를 검색합니다
	geocoder.addressSearch(festvAddr, function(result, status) {

	// 정상적으로 검색이 완료됐으면 
	 if (status === kakao.maps.services.Status.OK) {

	    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	    // 결과값으로 받은 위치를 마커로 표시합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: coords
	    });

	    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    map.setCenter(coords);
	} 
	
	});
}
    
</script>

<section class="mt-8 text-xl div_center">
	<div class="w-800">
	<table class="table table-zebra w-full">

		<tbody>
			<c:choose>
				<c:when test="${param.id == 1 }">
					<tr>
						<div class="div_center mb-20">
							<img src="https://www.newstnt.com/news/photo/201911/40653_33359_4942.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 2 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1553041875109.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 3 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://mblogthumb-phinf.pstatic.net/MjAxNzA1MTJfMTQg/MDAxNDk0NTI4MTY0ODgx.HjeOwQNGczmjevfbyKv0zZBWEcZa5nyy0YNcYF0G41Ug.EjUKx9j9B5BQBcY3UyAPprEsaU5K2ZraMHqMn_nO79Ug.JPEG.first_seogu/%EB%8C%80%EC%A0%84_%EC%84%9C%EA%B5%AC%ED%9E%90%EB%A7%81_%EC%95%84%ED%8A%B8%ED%8E%98%EC%8A%A4%ED%8B%B0%EB%B2%8C%E2%94%83%EC%95%84%ED%8A%B8_%EB%B9%9B_%ED%84%B0%EB%84%9016.jpg?type=w800" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 4 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.barefootfesta.com:444/mobile/project/design/main/main_img01.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 5 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1461904424401.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 6 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://cdn.ggilbo.com/news/photo/201911/725823_562028_88.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 7 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.daejeontoday.com/news/photo/201903/541285_192161_5837.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 8 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://cdn.ccdailynews.com/news/photo/202110/2090065_559742_2722.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 9 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://image.여기유.com/content_festival/2020011311055815788811582751.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 10 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.timenews.co.kr/web/news/article_image/81d532e05ec6b0d7f2b10f9f362f4f88" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 11 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://img2.yna.co.kr/etc/inner/KR/2018/10/17/AKR20181017088900063_01_i_P4.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 12 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://www.thesegye.com/news/data/20221016/p1065578299763795_321_thum.jpg" alt="" />
						</div>
					</tr>
				</c:when>
				<c:when test="${param.id == 13 }">
					<tr>
						<div class="div_center mb-20">
							<img class="" src="https://newsimg.sedaily.com/2023/01/02/29KBGC3TII_1.jpg" alt="" />
						</div>
					</tr>
				</c:when>
			</c:choose>
				<tr>
					<th>번호</th>
					<td>
						<div id="idContainer"></div>
					</td>
				</tr>
				<tr>
					<th>행사주제</th>
					<td>
						<div id="festvTpicContainer"></div>
					</td>
				</tr>
				<tr>
					<th>행사명</th>
					<td>
						<div id="festvNmContainer"></div>
					</td>
				</tr>
				<tr>
					<th>행사주소</th>
					<td>
						<div id="festvAddrContainer"></div>
					</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>
						<div id="festvDtlAddrContainer"></div>
					</td>
				</tr>
				<tr>
					<th>행사기간</th>
					<td>
						<div id="festvPridContainer"></div>
					</td>
				</tr>
				<tr>
					<th>소개</th>
					<td>
						<div id="festvSummContainer"></div>
					</td>
				</tr>
				

		</tbody>
	</table>
	<div class="mt-8">위치정보</div>
	<div class="div_center">
		<div id="map" style="width: 100%; height: 500px;"></div>
	</div>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>