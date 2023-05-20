<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="event2" />

<%@ include file="../common/head.jspf"%>
<script>
    var itemsPerPage = 10;
    var page = ${param.page};
    var startPage = (page - 1) * itemsPerPage + 1;
    var endPage = page * itemsPerPage;

    var xhr = new XMLHttpRequest();
    var url = 'https://apis.data.go.kr/6300000/openapi2022/tourspot/gettourspot'; /*URL*/
    var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('142');
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

                var eventElement = document.createElement("div");
				
                eventElement.innerHTML = "<div class=tourspot"+ i +" onclick=showApi("+i+")> <span>"+ (i+1) +"</span> tourspotSumm: <span class='tourspot-summ'>" + tourspotSumm + "</span> </div> <br>";
                eventElement.innerHTML += "<div class=tourspot-details"+ i +" style='display: none';> <div>mapLat: " + mapLat + "</div> <div>mapLot: " + mapLot + "</div> <div>mngTime: " + mngTime + "</div> <div>pkgFclt: " + pkgFclt + "</div> <div>refadNo: " + refadNo + "</div> <div>tourspotAddr: " + tourspotAddr + "</div> </div>";
                /* eventElement.innerHTML += "<div>mapLot: " + mapLot + "</div>";
                eventElement.innerHTML += "<div>mngTime: " + mngTime + "</div>";
                eventElement.innerHTML += "<div>pkgFclt: " + pkgFclt + "</div>";
                eventElement.innerHTML += "<div>refadNo: " + refadNo + "</div>";
                eventElement.innerHTML += "<div>tourspotAddr: " + tourspotAddr + "</div>";
                eventElement.innerHTML += "</div>"; */

                titleContainer.appendChild(eventElement);
            }
            
        }
    };

    xhr.send('');
</script>
<script>
function showApi(el) {
    // 해당 요소 가져오기
    var details = document.querySelector(".tourspot-details" + el); // 

    // tourspot-details display설정
    details.style.display = details.style.display === "none" ? "block" : "none";
  }
</script>


<section class="event_section">
	<div id="titleContainer"></div>
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
<%@ include file="../common/foot.jspf"%>