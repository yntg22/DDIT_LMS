<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>웰컴 페이지</h4>
현재까지 누적 방문자수 : ${usercount }명
<c:set var="authMember" value="${sessionScope.authMember }"/>
<c:if test="${not empty authMember }">
	<h4>접속자 리스트 using only HTTP @! 테스트입니당</h4>
	<ul>
		<c:forEach items="${userList }" var="user">
			<li>${user.memName }</li>
		</c:forEach>
	</ul>	
</c:if>
<h4 id="timeArea"></h4>
<script type="text/javascript">
	document.getElementById("timeArea").innerHTML = new Date();
</script>
















