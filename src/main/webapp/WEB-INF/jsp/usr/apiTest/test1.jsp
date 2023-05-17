<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="TRAVEL" />

<%@ include file="../common/head.jspf"%>


<c:forEach var="sb" items="sb">
	<div>${sb}</div>
</c:forEach>

<%@ include file="../common/foot.jspf"%>