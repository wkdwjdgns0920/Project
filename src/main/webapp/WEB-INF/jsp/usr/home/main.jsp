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

   <div class="option active" style="--optionBackground:url(/resources/img/img1.jpg);" title="1" >
      <div class="shadow"></div>
      <div class="label">
         
         <div class="info">
            <div class="main">Blonkisoaz</div>
            <div class="sub">Omuke trughte a otufta</div>
            <div><a href="#">해당페이지로 이동</a></div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img2.jpg);" title="2" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">Oretemauw</div>
            <div class="sub">Omuke trughte a otufta</div>
            <div><a href="#">해당페이지로 이동</a></div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img3.jpg);" title="3" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">Iteresuselle</div>
            <div class="sub">Omuke trughte a otufta</div>
            <div><a href="#">해당페이지로 이동</a></div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img4.jpg);" title="4" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">Idiefe</div>
            <div class="sub">Omuke trughte a otufta</div>
            <div><a href="#">해당페이지로 이동</a></div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/f19901f50b79604839ca761cd6d74748/tumblr_o65rohhkQL1qho82wo1_1280.jpg);" title="5" >
      <div class="shadow"></div>
      <div class="label">
         <div class="info">
            <div class="main">Inatethi</div>
            <div class="sub">Omuke trughte a otufta</div>
            <div><a href="#">해당페이지로 이동</a></div>
         </div>
      </div>
   </div>
</div>

</div>
</section>

<!-- flexCard 액션관련 -->
<script>
var clickCount;

$(".option").click(function(){
	$(".option").removeClass("active");
	$(".option").removeClass("option_1");
	$(this).addClass("active");
	$(this).addClass("option_1");
	
   });

$(document).on("click", ".option_1", function(){
	alert('실행!');
});
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