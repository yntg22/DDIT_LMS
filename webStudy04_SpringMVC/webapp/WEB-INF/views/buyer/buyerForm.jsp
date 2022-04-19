<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form modelAttribute="buyer" method="post" 
			action="${cPath }${currentAction }">
<form:hidden path="buyerId" readonly="true"/>			
<table class="table table-bordered">
	<tr>
		<th>거래처명</th>
		<td><form:input path="buyerName" cssClass="form-control"
				required="required" />
			<form:errors path="buyerName" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>거래처분류코드</th>
		<td>
			<select name="buyerLgu" class="form-control">
				<option value="">상품분류</option>
				<c:forEach items="${lprodList }" var="lprod">
					<option value="${lprod.lprodGu }">
						${lprod.lprodNm }
					</option>
				</c:forEach>
			</select>
			<form:errors path="buyerLgu" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>은행</th>
		<td><form:input path="buyerBank" cssClass="form-control" />
			<form:errors path="buyerBank" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>계좌</th>
		<td><form:input path="buyerBankno" cssClass="form-control" />
			<form:errors path="buyerBankno" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td><form:input path="buyerBankname" cssClass="form-control" />
			<form:errors path="buyerBankname" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><form:input path="buyerZip" cssClass="form-control" />
			<form:errors path="buyerZip" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>주소1</th>
		<td><form:input path="buyerAdd1" cssClass="form-control" />
			<form:errors path="buyerAdd1" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>주소2</th>
		<td><form:input path="buyerAdd2" cssClass="form-control" />
			<form:errors path="buyerAdd2" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>회사번호</th>
		<td><form:input path="buyerComtel" cssClass="form-control"
				required="required" />
			<form:errors path="buyerComtel" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>팩스번호</th>
		<td><form:input path="buyerFax" cssClass="form-control"
				required="required" />
			<form:errors path="buyerFax" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><form:input path="buyerMail" cssClass="form-control"
				required="required" />
			<form:errors path="buyerMail" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>담당자</th>
		<td><form:input path="buyerCharger" cssClass="form-control" />
			<form:errors path="buyerCharger" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td><form:input path="buyerTelext" cssClass="form-control" />
			<form:errors path="buyerTelext" element="span" cssClass="error" /></td>
	</tr>
	<tr>
	<td colspan="2">
		<input type="submit" value="저장" class="btn btn-primary"/>
		<input type="reset" value="취소" class="btn btn-warning"/>
		<a class="btn btn-secondary" href='<c:url value="/buyer/buyerList.do"/>'>목록으로</a>
	</td>
</tr>
</table>
</form:form>
<script type="text/javascript" src="${cPath }/resources/js/jquery.validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/jquery.validation/additional-methods.min.js"></script>
<script type="text/javascript">
	$("[name=buyerLgu]").val("${buyer.buyerLgu}")
</script>
