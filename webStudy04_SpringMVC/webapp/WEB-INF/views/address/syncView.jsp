<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h4><spring:message code="hi"/></h4>
<spring:message code="address.addId" var="addIdMsg"/>
<spring:message code="address.addName" var="addNameMsg"/>
<spring:message code="address.addHp" var="addHpMsg"/>
<spring:message code="address.address" var="addressMsg"/>

<div>
	<form:form modelAttribute="address" action="${cPath }/address/addInsert.do" method="post">
		<form:input path="addName" placeholder="${addNameMsg }"/>
		<form:errors path="addName" element="span" cssClass="error"/>
		<form:input path="addHp" placeholder="${addHpMsg }"/>
		<form:errors path="addHp" element="span" cssClass="error"/>
		<form:input path="address" placeholder="${addressMsg }"/>
		<form:errors path="address" element="span" cssClass="error"/>
		<form:button type="submit">주소록 등록</form:button>
	</form:form>
</div>
<table>
	<thead>
		<tr>
			<th>${addIdMsg }</th>
			<th>${addNameMsg }</th>
			<th>${addHpMsg }</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:forEach items="${addList }" var="addVO">
			<tr>
				<td>${addVO.addId }</td>
				<td>${addVO.addName }</td>
				<td>${addVO.addHp }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="detailArea">

</div>






