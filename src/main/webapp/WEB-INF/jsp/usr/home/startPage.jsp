<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="연습" />

<%@ include file="../common/head.jspf"%>
<!-- 공통 js파일 사용 -->
<script src="/resource/startPage.css" defer="defer"></script>
<link rel='stylesheet'
	href='https://unpkg.com/locomotive-scroll@4.0.6/dist/locomotive-scroll.min.css' />
<script
	src='https://unpkg.com/locomotive-scroll@4.0.6/dist/locomotive-scroll.min.js'></script>


<script>
</script>

<div class="main2">

<div class="options">

   <div class="option active" style="--optionBackground:url(/resources/img/img1.jpg);" title="1" >
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-walking"></i>
         </div>
         <div class="info">
            <div class="main">Blonkisoaz</div>
            <div class="sub">Omuke trughte a otufta</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img2.jpg);" title="2" >
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-snowflake"></i>
         </div>
         <div class="info">
            <div class="main">Oretemauw</div>
            <div class="sub">Omuke trughte a otufta</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img3.jpg);" title="3" >
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-tree"></i>
         </div>
         <div class="info">
            <div class="main">Iteresuselle</div>
            <div class="sub">Omuke trughte a otufta</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(/resources/img/img4.jpg);" title="4" >
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-tint"></i>
         </div>
         <div class="info">
            <div class="main">Idiefe</div>
            <div class="sub">Omuke trughte a otufta</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/f19901f50b79604839ca761cd6d74748/tumblr_o65rohhkQL1qho82wo1_1280.jpg);" title="5" >
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-sun"></i>
         </div>
         <div class="info">
            <div class="main">Inatethi</div>
            <div class="sub">Omuke trughte a otufta</div>
         </div>
      </div>
   </div>
</div>

</div>

<style>
.main2 {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    height: 100vh;
    font-family: 'Roboto', sans-serif;
    transition: .25s;
}
.main2  .credit {
    position: absolute;
    bottom: 20px;
    left: 20px;
    color: inherit;
}
.main2  .options {
    display: flex;
    flex-direction: row;
    align-items: stretch;
    overflow: hidden;
    min-width: 600px;
    max-width: 900px;
    width: calc(100% - 100px);
    height: 400px;
}
@media screen and (max-width: 718px) {
    .main2  .options {
        min-width: 520px;
    }
    .main2  .options .option:nth-child(5) {
        display: none;
    }
}
@media screen and (max-width: 638px) {
    .main2  .options {
        min-width: 440px;
    }
    .main2  .options .option:nth-child(4) {
        display: none;
    }
}
@media screen and (max-width: 558px) {
    .main2  .options {
        min-width: 360px;
    }
    .main2  .options .option:nth-child(3) {
        display: none;
    }
}
@media screen and (max-width: 478px) {
    .main2  .options {
        min-width: 280px;
    }
    .main2  .options .option:nth-child(2) {
        display: none;
    }
}
.main2  .options .option {
    position: relative;
    overflow: hidden;
    min-width: 60px;
    margin: 10px;
    background: var(--optionBackground, var(--defaultBackground, #E6E9ED));
    background-size: auto 120%;
    background-position: center;
    cursor: pointer;
    transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
.main2  .options .option:nth-child(1) {
    --defaultBackground:#ED5565;
}
.main2  .options .option:nth-child(2) {
    --defaultBackground:#FC6E51;
}
.main2  .options .option:nth-child(3) {
    --defaultBackground:#FFCE54;
}
.main2  .options .option:nth-child(4) {
    --defaultBackground:#2ECC71;
}
.main2  .options .option:nth-child(5) {
    --defaultBackground:#5D9CEC;
}
.main2  .options .option:nth-child(6) {
    --defaultBackground:#AC92EC;
}
.main2  .options .option.active {
    flex-grow: 10000;
    transform: scale(1);
    max-width: 600px;
    margin: 0px;
    border-radius: 40px;
    background-size: auto 100%;
}
.main2  .options .option.active .shadow {
    box-shadow: inset 0 -120px 120px -120px black, inset 0 -120px 120px -100px black;
}
.main2  .options .option.active .label {
    bottom: 20px;
    left: 20px;
}
.main2  .options .option.active .label .info > div {
    left: 0px;
    opacity: 1;
}
.main2  .options .option:not(.active) {
    flex-grow: 1;
    border-radius: 30px;
}
.main2  .options .option:not(.active) .shadow {
    bottom: -40px;
    box-shadow: inset 0 -120px 0px -120px black, inset 0 -120px 0px -100px black;
}
.main2  .options .option:not(.active) .label {
    bottom: 10px;
    left: 10px;
}
.main2  .options .option:not(.active) .label .info > div {
    left: 20px;
    opacity: 0;
}
.main2  .options .option .shadow {
    position: absolute;
    bottom: 0px;
    left: 0px;
    right: 0px;
    height: 120px;
    transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
.main2  .options .option .label {
    display: flex;
    position: absolute;
    right: 0px;
    height: 40px;
    transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
.main2  .options .option .label .icon {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    min-width: 40px;
    max-width: 40px;
    height: 40px;
    border-radius: 100%;
    background-color: white;
    color: var(--defaultBackground);
}
.main2  .options .option .label .info {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 10px;
    color: white;
    white-space: pre;
}
.main2  .options .option .label .info > div {
    position: relative;
    transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95), opacity 0.5s ease-out;
}
.main2  .options .option .label .info .main {
    font-weight: bold;
    font-size: 1.2rem;
}
.main2  .options .option .label .info .sub {
    transition-delay: .1s;
}

</style>



<script>
let clickCount =0;


$(".option").click(function(){
	const y = clickCount++;

	   $(".option").removeClass("active");
	   $(this).addClass("active");
	   $(this).attr("onclick","replace_page("+this.title+")");
	   
	   if(clickCount == 1){
		   window.lacation.reload;
		   alert('언제 실행되?');
	   }
	});

function replace_page(el) {
	if(el == 1){
	alert(el);
	}
	if(el == 2){
		alert(el);
	}
	if(el == 3){
		alert(el);
	}
	if(el == 4){
		alert(el);
	}
	if(el == 5){
		alert(el);
	}
	
}



/* $(".options .option:nth-child(1)").click(function(){
	location.replace('/'); 
	}); */

</script>


<%@ include file="../common/foot.jspf"%>