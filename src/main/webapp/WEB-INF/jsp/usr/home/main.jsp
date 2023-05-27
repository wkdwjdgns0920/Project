<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>
<!-- flexCard파일 사용 -->
<link rel="stylesheet" href="/resource/flexCard.css" />
	
</body>
<body onload="showImage()">
	

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
	
</div>
<div class="h-800"></div>

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