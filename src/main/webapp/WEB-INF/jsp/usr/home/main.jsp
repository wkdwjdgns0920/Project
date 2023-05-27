<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>
<!-- flexCard파일 사용 -->
<link rel="stylesheet" href="/resource/flexCard.css" />
	
</body>
<body onload="showImage()">

<script>
var xhr = new XMLHttpRequest();
var url = 'https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst'; /*URL*/
var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('12'); /**/
queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent(${date}); /**/
queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent(${time}); /**/
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
        
		/* 강수형태(PTY) 코드
		없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)  */
		/* 하늘상태(SKY) 코드
		맑음(1), 구름많음(3), 흐림(4) */
		
        
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

<script type="text/javascript">
	var imgArray = new Array();
	imgArray[0] = "https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110257537201.jpg";
	imgArray[1] = "https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110255503251.jpg";
	imgArray[2] = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTEwMDFfMTU4%2FMDAxNjMzMDU0OTI4OTIx.uC2VNflY2MoH_l-6nJk9E9lgc3vd7ok6xFUB3DxsOfEg.m6LzD1V1SMfJiiHlBoXrYTwTzcUKkWjERT9-xnCgv5Qg.JPEG.ppspr%2F%25BB%25D1%25B8%25AE%25B0%25F8%25BF%25F83-1.jpg&type=sc960_832";
	
	function showImage() {
		var imgNum = Math.round(Math.random() * 2);
		var objImg = document.getElementById("introImg");
		objImg.src = imgArray[imgNum];
		setTimeout("showImage()", 2000);
	}
	
	
</script>

<!-- <img class="main_body" src="https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110257537201.jpg" alt=""> -->
<div class="main_body">
	<img class="img_box" id="introImg" border="0">
	<form action="">
	<div class="search_box">
		<input class="main_search" type="text" />
		<div class="empty_box"></div>
		<div class="search_submit">
			<button type="submit"><img class="search_btn" src="/resources/img/search1.jpg"/></button>
		</div>
		</form>
	</div>
	<div class="weatherBox">
		<div id=skyImgContainer></div>
		<div>
			<div id="skyContainer"></div>
			<div id="tmpContainer"></div>
			<div id="popContainer"></div>
			<div id="pcpContainer"></div>
			<div id="rehContainer"></div>
			<div id="tmnContainer"></div>
			<div id="tmxContainer"></div>
		</div>
	</div>
	
<!-- </div> -->
<div class="h-100"></div>

<!-- flexCard -->
<section class="flexCard">
<div class="main2">

<div class="options">

   <div class="option active option_1" style="--optionBackground:url(https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1553041875109.jpg);" title="1" >
      <div class="shadow"></div>
      <div class="label">
         
         <div class="info">
            <div class="main">대청호 벚꽃축제</div>
            <div class="sub"></div>
            
         </div>
      </div>
   </div>
   <div class="option option_2" style="--optionBackground:url(https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1461904424401.jpg);" title="2" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">유성온천문화축제</div>
            <div class="sub"></div>
            
         </div>
      </div>
   </div>
   <div class="option option_3" style="--optionBackground:url(https://www.barefootfesta.com:444/mobile/project/design/main/main_img01.jpg);" title="3" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">계족산맨발축제</div>
            <div class="sub"></div>
            
         </div>
      </div>
   </div>
   <div class="option option_4" style="--optionBackground:url(https://www.daejeontoday.com/news/photo/201903/541285_192161_5837.jpg);" title="4" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">대청호 대덕뮤직페스티벌</div>
            <div class="sub"></div>
            
         </div>
      </div>
   </div>
   <div class="option option_5" style="--optionBackground:url(https://www.timenews.co.kr/web/news/article_image/81d532e05ec6b0d7f2b10f9f362f4f88);" title="5" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">대전 0시 뮤직페스티벌</div>
            <div class="sub"></div>
            
         </div>
      </div>
   </div>
</div>

</div>
</section>

<div class="h-500"></div>

<!-- flexCard 액션관련 -->
<script>
$(".option").click(function(){
	if (!$(this).hasClass("active")) {
		$(".option").removeClass("active");
		$(this).addClass("active");
	} else {
		replace_page(this.title);
	}
	$(".option").removeAttr("onclick");
});

function replace_page(el) {
	if(el == 1) {
		location.replace("festvDetail?id=2");
	}
	if(el == 2) {
		location.replace("festvDetail?id=5");
	}
	if(el == 3) {
		location.replace("festvDetail?id=4");
	}
	if(el == 4) {
		location.replace("festvDetail?id=7");
	}
	if(el == 5) {
		location.replace("festvDetail?id=10");
	}
	$(this).removeAttribute("onclick","replace_page("+this.title+")");
}

</script>

<style>
.main_search {
	width: 500px;
	height: 70px;
	border-radius: 20px;
	padding: 0 30px;
	font-size: 2rem;
}
.search_box {
	z-index: 10;
	position: absolute;
	display:flex;
	top:50%;
	left: 38%;
	border-radius: 20px;
}
.weatherBox {
	display: flex;
	z-index: 10;
	position: absolute;
	top:25%;
	left: auto;
	right: 10%;
	border-radius: 20px;
	background-color: gold;
}
.skyImgBox {
	height: 100px;
	width: 100px;
	border-radius: 20px;
}
#skyImgContainer {
	border-radius: 20px;
}
.search_submit{
	width: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}
.search_btn {
	border-radius: 30px;;
}
</style>
 
<%@ include file="../common/foot.jspf"%>