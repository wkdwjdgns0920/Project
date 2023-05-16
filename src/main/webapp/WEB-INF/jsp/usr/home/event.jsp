<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="TRAVEL" />

<%@ include file="../common/head.jspf"%>
<script>
var xhr = new XMLHttpRequest();
var url = 'http://apis.data.go.kr/6300000/eventDataService/eventDataListJson'; /*URL*/
var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'y5HTZhhwhcvQyk77GS%2FnJZn3cy3z%2FGy86ese9SbgZ60sMmoIdHrIzpxTFbNUABK9%2BluK3Rt2wawKZE3wjxgCGA%3D%3D'; /*Service Key*/
queryParams += '&' + encodeURIComponent('') + '=' + encodeURIComponent(''); /**/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
    }
};

xhr.send('');
</script>

<%@ include file="../common/foot.jspf"%>