<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<link rel="stylesheet" href="/resource/startPage.css" />
<%@ include file="../common/head.jspf"%>
	
</body>
<body onload="showImage()">
	

<script type="text/javascript">
	var imgArray = new Array();
	imgArray[0] = "https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcRs6w5YHcaCVjTB_X_Nbqx9fxuYqvvR1eBbG0CUMuyL_oKxal84GYhSSouw8HMv5WLlirN5hGdphZzpTrZAnqXkflf5MQ";
	imgArray[1] = "https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110255503251.jpg";
	imgArray[2] = "https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110301566521.jpg";
	imgArray[3] = "https://lh5.googleusercontent.com/p/AF1QipP2LQDefthNled7iiCFcV5ONpsXxa8uBch8LUsQ=w548-h318-n-k-no";
	
	function showImage() {
		var imgNum = Math.round(Math.random() * 2);
		var objImg = document.getElementById("introImg");
		objImg.src = imgArray[imgNum];
		setTimeout("showImage()", 1000);
	}
</script>

<img class="main_body" src="https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110257537201.jpg" alt="">
 <blockquote>
	<div class="polaroid">
		<div class="card">
			<div class="image">
				<img class="img_box" id="introImg" border="0">
			</div>
			<p>asd</p>
		</div>
	</div>
	<div class="polaroid">
		<div class="card">
			<div class="image">
				<img src="https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110255503251.jpg" alt="">
			</div>
			<p>asd</p>
		</div>
	</div>
	<div class="polaroid">
		<div class="card">
			<div class="image">
				<img src="https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110301566521.jpg" alt="">
			</div>
			<p>asd</p>
		</div>
	</div>
	<cite>From the song "Things We Left Behind" by Atlas</cite>
</blockquote>

<%@ include file="../common/foot.jspf"%>