<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>
	
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