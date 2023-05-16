<!DOCTYPE html>
<html>
<head>
<title>startPage</title>
<link rel="stylesheet" href="/resource/test.css" />
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
</head>
<body onload="showImage()">
	<div class="img_box">
		<img class="img_box" id="introImg" border="0">
	</div>
	<div class="img_box">
		<img class="" src="https://lh5.googleusercontent.com/p/AF1QipP2LQDefthNled7iiCFcV5ONpsXxa8uBch8LUsQ=w548-h318-n-k-no" alt="">
		<img class="" src="https://lh5.googleusercontent.com/p/AF1QipMKvD8zzZLzVmKZENxJsHxT-uO1hHN5LBQAr7jm=w548-h318-n-k-no" alt="">
		<img class="" src="https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcS0943dhl2Kk2dalLCV6fWtZIVwb5B-PSc5pVV_QWFaTmbr9O8pOGiGTuXlAmgfypQh8VaMbv_dAKJb-xixLC6gEmqHFA" alt="">
		<img class="" src="https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcRs6w5YHcaCVjTB_X_Nbqx9fxuYqvvR1eBbG0CUMuyL_oKxal84GYhSSouw8HMv5WLlirN5hGdphZzpTrZAnqXkflf5MQ" alt="">
		<img class="" src="" alt="https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcQLLiQENWSaeK_jK6UXQ-BB7QmV7e8ezWNlCrwib97jM89VQ6hvoj1VUOwTjyt3XCi9A7e0tJ5VVwP1bteAOv3zsLPZjg">
		<img class="" src="" alt="">
		
	</div>
</body>
</html>