<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="TRAVEL" />

<%@ include file="../common/head.jspf"%>
<script>
	var xhr = new XMLHttpRequest();
	var url = 'http://apis.data.go.kr/6300000/eventDataService/eventDataListJson'; /*URL*/
	var queryParams = '?'
			+ encodeURIComponent('serviceKey')
			+ '='
			+ 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('beginDt') + '=' + encodeURIComponent('2017-01-01');/**/
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			 var response = JSON.parse(this.responseText);
		        var eventData = response.msgBody;
		        var container = document.getElementById('titleContainer'); // HTML에 값을 넣을 컨테이너 요소 선택
		        
		        // 각 데이터의 title 값을 HTML에 삽입
		        for (var i = 0; i < eventData.length; i++) {
		            var title = eventData[i].title;
		            var titleElement = document.createElement('p'); // 새로운 <p> 요소 생성
		            titleElement.innerHTML = title; // <p> 요소에 title 값을 할당
		            container.appendChild(titleElement); // 컨테이너에 <p> 요소 추가
		            
		            var beginDt = eventData[i].beginDt;
		            console.log(beginDt);
		        }

		}
	};
	xhr.send('');
</script>

<div id="titleContainer"></div>

<%@ include file="../common/foot.jspf"%>