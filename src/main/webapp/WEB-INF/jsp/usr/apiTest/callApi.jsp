<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="대전광역시 story" />

<%@ include file="../common/head.jspf"%>

<!--
	0 = PTY 	=>강수형태 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4), 빗방울(5), 빗방울/눈날림(6), 눈날림(7)
	1 = "REH" 	=>습도
	2 = "RN1"	=>1시간강수량(mm)
	3 = "T1H"	=>기온
	4 = "UUU"	=>동서바람성분 m/s
	5 = "VEC"	=>풍향(deg)
	6 = "VVV"	=>남북바람성분(m/s)
	7 = "WSD"	=>풍속(m/s)
-->

<script>
var xhr = new XMLHttpRequest();
var url = 'https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst'; /*URL*/
var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('12'); /**/
queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent('20230526'); /**/
queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent('2300'); /**/
queryParams += '&' + encodeURIComponent('nx') + '=' + encodeURIComponent('55'); /**/
queryParams += '&' + encodeURIComponent('ny') + '=' + encodeURIComponent('127'); /**/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
     
        var response = JSON.parse(this.responseText);
        var data = response.response.body;	//	items.item[]
       	
        console.log(response);
        
        //	강수형태 가져오기
		var pty = data.items.item[6].fcstValue;
        
        //	하늘상태값 가져오기
        var sky = data.items.item[5].fcstValue;	//	하늘상태
        
        if(pty == "0"){
        	if(sky == "1") {
        		sky = "맑음";
        	} else if (sky == "3"){
        		sky = "구름많음";
        	} else {
        		sky = "흐림";
        	} 
        } else if(pty == "1") {
        	sky = "비내림";
        } else if(pty == "2") {
        	sky = "비/눈";
        } else if(pty == "3") {
        	sky = "눈";
        } else {
        	sky = "소나기";
        }
        
        var skyBox = document.createElement("div");
        skyBox.innerHTML = "<div class='skyBox'>날씨 : " + sky + " </div>"
        skyContainer.appendChild(skyBox);
        
        //	날씨이미지
        var skyImgBox = document.createElement("div");
        if(sky == "맑음"){
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/맑음.png'/></div>"        	
        } else if (sky == "구름많음") {
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/구름많음.png'/></div>" 
        } else if (sky == "흐림"){
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/흐림.png'/></div>" 
        } else if (sky == "비내림"){
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/비내림.png'/></div>"
        } else if (sky == "비/눈"){
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/비눈.png'/></div>"
        } else if (sky == "눈"){
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/눈.png'/></div>"
        } else {
        	skyImgBox.innerHTML = "<div class='skyImgBox'><img src='/resources/img/소나기.png'/></div>"
        }
        
        skyImgContainer.appendChild(skyImgBox);
        
        
        //	기온(℃)
        var tmp = data.items.item[0].fcstValue;
        var tmpBox = document.createElement("div");
        tmpBox.innerHTML = "<div class='tmpBox'>현재온도 : " + tmp + " ℃</div>"
        tmpContainer.appendChild(tmpBox);
        
		//	일일 최저기온(℃)
        var tmx = data.items.item[0].fcstValue;
        var tmxBox = document.createElement("div");
        tmxBox.innerHTML = "<div class='tmxBox'>최저기온 : " + tmx + " ℃</div>"
        tmxContainer.appendChild(tmxBox);
        
		//  일일 최고기온(℃)
        var tmn = data.items.item[0].fcstValue;
        var tmnBox = document.createElement("div");
        tmnBox.innerHTML = "<div class='tmnBox'>최고기온 : " + tmn + " ℃</div>"
        tmnContainer.appendChild(tmnBox);
        
        //	강수확률(%)
        var pop = data.items.item[7].fcstValue;
        var popBox = document.createElement("div");
        popBox.innerHTML = "<div class='popBox'>강수확률 : " + pop + " %</div>"
        popContainer.appendChild(popBox);
        
		//	1시간 강수량(mm)
        var pcp = data.items.item[9].fcstValue;
        var pcpBox = document.createElement("div");
        pcpBox.innerHTML = "<div class='pcpBox'>강수량 : " + pcp + " </div>"
        pcpContainer.appendChild(pcpBox);
        
		//	습도(%)
        var reh = data.items.item[10].fcstValue;
        var rehBox = document.createElement("div");
        rehBox.innerHTML = "<div class='rehBox'>습도 : " + reh + " %</div>"
        rehContainer.appendChild(rehBox);
    }
};

xhr.send('');
</script>
	<div id=skyImgContainer></div>
	<div id="skyContainer"></div>
	<div id="tmpContainer"></div>
	<div id="popContainer"></div>
	<div id="pcpContainer"></div>
	<div id="rehContainer"></div>
	<div id="tmnContainer"></div>
	<div id="tmxContainer"></div>



<%@ include file="../common/foot.jspf"%>