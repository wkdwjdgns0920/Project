<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<link rel="stylesheet" href="/resource/startPage.css" />
<%@ include file="../common/head.jspf"%>

<script>
const images = document.querySelectorAll('.slider span');
const sliderContainer = document.querySelector('slider-container');
const slider = document.querySelector('.slider');
const prevBtn = document.querySelector('.leftBtn');
const nextBtn = document.querySelector('.rightBtn');

let current = 1;
const imgSize = images[0].clientWidth;

slider.style.transform = `translateX(${-imgSize}px)`;

prevBtn.addEventListener('click',()=>{
    if( current <= 0) return;
    slider.style.transition = '400ms ease-in-out transform';
    current--;
    slider.style.transform = `translateX(${-imgSize * current}px)`;
})

nextBtn.addEventListener('click',()=>{
    if( current >= images.length -1 ) return;
    slider.style.transition = '400ms ease-in-out transform';
    current++;
    slider.style.transform = `translateX(${-imgSize * current}px)`;
})

slider.addEventListener('transitionend', ()=> {
    if(images[current].classList.contains('first-img')){
        slider.style.transition = 'none';
        current = images.length - 2;
        slider.style.transform = `translateX(${-imgSize * current}px)`;
    }
    if(images[current].classList.contains('last-img')){
        slider.style.transition = 'none';
        current = images.length - current;
        slider.style.transform = `translateX(${-imgSize * current}px)`;
    }
})
</script>

<div id="slide">
        <div class="slider-container">
            <span class="leftBtn">
                <i class="fa-solid fa-chevron-left"></i>
            </span>
            <div class="slider">
                <span class="first-img">
                    <div class="text-wrap">
                        <h5>WEEKLY DESIGN</h5>
                        <h1>사용자가 좋아하는 디자인, WEB/APP 디자인</h1>
                        <h4>WEB/APP 바로가기 ></h4>
                    </div>
                    <img src="4.png" alt="0" />
                </span>
                <span>
                    <div class="text-wrap">
                        <h5>LOUDSOURCING</h5>
                        <h1>디자인부터 아이디어까지, &nbsp; 19만 전문가에게 의뢰하세요</h1>
                        <h4>콘테스트 개최하기 ></h4>
                    </div>
                    <img src="1.png" alt="1" />
                </span>
                <span>
                    <div class="text-wrap">
                        <h5>WEEKLY DESIGN</h5>
                        <h1>디자인 트렌드는 라우드소싱 2022 로고/BI 디자인</h1>
                        <h4>로고/BI 바로가기 ></h4>
                    </div>
                    <img src="2" alt="2" />
                </span>
                <span>
                    <div class="text-wrap">
                        <h5>WEEKLY DESIGN</h5>
                        <h1>사용자가 좋아하는 디자인, WEB/APP 디자인</h1>
                        <h4>WEB/APP 바로가기 ></h4>
                    </div>
                    <img src="3" alt="3" />
                </span>
                <span class="last-img">
                    <div class="text-wrap">
                        <h5>LOUDSOURCING</h5>
                        <h1>디자인부터 아이디어까지, &nbsp; 19만 전문가에게 의뢰하세요</h1>
                        <h4>콘테스트 개최하기 ></h4>
                    </div>
                    <img src="1.png" alt="4" />
                </span>
            </div>
            <span class="rightBtn">
                <i class="fa-solid fa-chevron-right"></i>
            </span>
        </div>
    </div>
 

<%@ include file="../common/foot.jspf"%>