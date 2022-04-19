<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.validation/jquery.validate.min.js"></script>
<form:form modelAttribute="member" method="post" id="memberForm" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>
				<form:input path="memId" class="form-control" maxlength="15"/>
				<form:errors path="memId" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<form:password path="memPass" class="form-control" />
				<form:errors path="memPass" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>회원이미지</th>
			<td>
				<form:input type="file" path="memImage" class="form-control" />
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>
				<form:input path="memName" class="form-control" />
				<form:errors path="memName" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>
				<form:input path="memRegno1" class="form-control" />
				<form:errors path="memRegno1" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<form:input path="memRegno2" class="form-control" />
				<form:errors path="memRegno2" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<form:input path="memBir" type="date" class="form-control" />
				<form:errors path="memBir" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<form:input path="memZip" class="form-control" />
				<form:errors path="memZip" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<form:input path="memAdd1" class="form-control" />
				<form:errors path="memAdd1" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<form:input path="memAdd2" class="form-control" />
				<form:errors path="memAdd2" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>
				<form:input path="memHometel" class="form-control" />
				<form:errors path="memHometel" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>
				<form:input path="memComtel" class="form-control" />
				<form:errors path="memComtel" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<form:input path="memHp" class="form-control" />
				<form:errors path="memHp" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<form:input path="memMail" type="email" class="form-control" />
				<form:errors path="memMail" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<form:input path="memJob" class="form-control" />
				<form:errors path="memJob" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<form:input path="memLike" class="form-control" />
				<form:errors path="memLike" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>
				<form:input path="memMemorial" class="form-control" />
				<form:errors path="memMemorial" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>
				<form:input path="memMemorialday" type="date" class="form-control" />
				<form:errors path="memMemorialday" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<form:button type="submit" class="btn btn-success">저장</form:button> 
				<form:button type="reset" class="btn btn-secondary">취소</form:button>
			</td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	$("#memberForm").validate({
		rules:{
			memId:{
				required:true,
				maxlength:15,
				remote:{
					url:"${pageContext.request.contextPath}/member/idCheck.do",
					method:"post"
				}
			}
		},
		messages:{
			memId:{
				required:"아이디 필수",
				maxlength:"최대 길이 15글자",
				remote:"중복 아이디"
			}
		}
	});
</script>





