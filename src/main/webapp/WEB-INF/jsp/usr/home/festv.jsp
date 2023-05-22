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
			
			console.log(eventData);
			
			for(var i = 0; i < Object.keys(eventData.items).length; i++){
				console.log(eventData.items[i].festvNm);
				var eventTitle = eventData.items[i].festvNm;
				
				var titleBox = document.createElement("div");
				var idBox = document.createElement("div");
				
				titleBox.innerHTML = "<div class='mt_10'> <a href='festvDetail?id="+ (i+1) +"'>"+ eventTitle +"</a> </div>";
				idBox.innerHTML = "<div class='mt_10'>" + (i+1) + "</div>"
				
				titleContainer.appendChild(titleBox);
				idContainer.appendChild(idBox);
			}
			
		}
	};

	xhr.send('');
</script>

<section class="mt-8 div_center">
	<div class="w-500 text-xl">
	<table class="table table-zebra w-full ">
		<thead>
		<colgroup>
			<col width="50" />
			<col width="50" />
			<col width="50" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th></th>
			<th>행사</th>
		</tr>
		</thead>
		<tbody>
				<tr>
					<td>
						<div id="idContainer"></div>
					</td>
					<td></td>
					<td>
						<div id="titleContainer"></div>
					</td>
				</tr>
		</tbody>
	</table>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>