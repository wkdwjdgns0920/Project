<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="연습" />

<%@ include file="../common/head.jspf"%>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js'></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView: 'dayGridMonth',
			events: [
				{
					title: '이벤트 제목',
					start: '2023-05-10'
				}
			]
		});
		calendar.render();
	});
</script>

<section class="div_center">
	<div id='calendar'></div>
</section>




<script src='fullcalendar/dist/index.global.js'></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
	  var calendarEl = document.getElementById('calendar');

	  var calendar = new Calendar(calendarEl, {
	    plugins: [ dayGridPlugin ]
	  });

	  calendar.render();
	});
</script>

</body>
</html>