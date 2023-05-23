<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="대전광역시 문화축제 정보" />

<%@ include file="../common/head.jspf"%>
<script>
	var xhr = new XMLHttpRequest();
	var url = 'https://apis.data.go.kr/6300000/openapi2022/festv/getfestv'; /*URL*/
	var queryParams = '?'
			+ encodeURIComponent('serviceKey')
			+ '='
			+ 'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('pageNo') + '='
			+ encodeURIComponent('1');
	queryParams += '&' + encodeURIComponent('numOfRows') + '='
			+ encodeURIComponent('13');
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {

			var response = JSON.parse(this.response);
			var eventData = response.response.body;

			console.log(eventData);

			for (var i = 0; i < Object.keys(eventData.items).length; i++) {
				console.log(eventData.items[i].festvNm);
				var eventTitle = eventData.items[i].festvNm;

				var titleBox = document.createElement("div");
				var idBox = document.createElement("div");

				titleBox.innerHTML = "<div class='mt_10'> <a href='festvDetail?id="	+ (i + 1) + "'>" + eventTitle + "</a> </div>";
				idBox.innerHTML = "<div class='mt_10'>" + (i + 1) + "</div>"

				titleContainer.appendChild(titleBox);
				idContainer.appendChild(idBox);
			}

		}
	};

	xhr.send('');
</script>

<%-- <section class="mt-8 div_center">
	<div class="w-500 text-xl">
	<table class="table table-zebra w-full ">
		<thead>
		<colgroup>
			<col width="50" />
			<col width="50" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th></th>
			<th>행사</th>
		</tr>
		</thead>
		<tbody>
				<tr>
					<td>
						<div id="idContainer"></div>
					</td>
					<td></td>
					<td>
						<div id="titleContainer"></div>
					</td>
				</tr>
		</tbody>
	</table>
	</div>
</section> --%>


<section class="mt-8 div_center">
<div>
	<div class="w-500 h-00 text-xl flec h-300">
	
		<div class="flec">
			1<div class="i_box w-00">
				<img src="https://www.newstnt.com/news/photo/201911/40653_33359_4942.jpg" alt="" />
				<a href="festvDetail?id=1" >asd</a>
			</div>
			<div class="empty_box"></div>
			2<div class="i_box">
				<img class="" src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1553041875109.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			3<div class="i_box">
				<img class="" src="https://mblogthumb-phinf.pstatic.net/MjAxNzA1MTJfMTQg/MDAxNDk0NTI4MTY0ODgx.HjeOwQNGczmjevfbyKv0zZBWEcZa5nyy0YNcYF0G41Ug.EjUKx9j9B5BQBcY3UyAPprEsaU5K2ZraMHqMn_nO79Ug.JPEG.first_seogu/%EB%8C%80%EC%A0%84_%EC%84%9C%EA%B5%AC%ED%9E%90%EB%A7%81_%EC%95%84%ED%8A%B8%ED%8E%98%EC%8A%A4%ED%8B%B0%EB%B2%8C%E2%94%83%EC%95%84%ED%8A%B8_%EB%B9%9B_%ED%84%B0%EB%84%9016.jpg?type=w800" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			4<div class="i_box">
				<img class="" src="https://www.barefootfesta.com:444/mobile/project/design/main/main_img01.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			5<div class="i_box">
				<img class="" src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1461904424401.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
		</div>
	</div>
		
	<div class="w-500 h-00 text-xl flec h-300">
		<div class="flec">
			1<div class="i_box w-00">
				<img class="" src="https://cdn.ggilbo.com/news/photo/201911/725823_562028_88.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			2<div class="i_box">
				<img class="" src="https://www.daejeontoday.com/news/photo/201903/541285_192161_5837.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			3<div class="i_box">
				<img class="" src="https://cdn.ccdailynews.com/news/photo/202110/2090065_559742_2722.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			4<div class="i_box">
				<img class="" src="https://image.여기유.com/content_festival/2020011311055815788811582751.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			5<div class="i_box">
				<img class="" src="https://www.timenews.co.kr/web/news/article_image/81d532e05ec6b0d7f2b10f9f362f4f88" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
		</div>
	</div>
	
	<div class="w-500 h-00 text-xl flec h-300">
		<div class="flec">
			1<div class="i_box w-00">
				<img class="" src="https://cdn.ggilbo.com/news/photo/201911/725823_562028_88.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			2<div class="i_box">
				<img class="" src="https://www.daejeontoday.com/news/photo/201903/541285_192161_5837.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
			<div class="empty_box"></div>
			3<div class="i_box">
				<img class="" src="https://cdn.ccdailynews.com/news/photo/202110/2090065_559742_2722.jpg" alt="" />
				<div class="tit" id="titleContainer"></div>
			</div>
		</div>
	</div>
	
</div>	
</section>


<style>
.i_box {
	height: 250px;
	width: 250px;
}

.h-300 {
	height: 300px;
}

.tit {
	height: 50px;
}

.flec {
	display: flex;
	justify-content: space-between;
}
.al-cc {
	display: flex;
	align-items: center;
}
</style>


<%@ include file="../common/foot.jspf"%>