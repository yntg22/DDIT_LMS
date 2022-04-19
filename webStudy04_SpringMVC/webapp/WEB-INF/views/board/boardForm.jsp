<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<form:form modelAttribute="board" enctype="multipart/form-data"
	action="${cPath }/board/new" method="post"
 	class="border border-primary"
>
<table class="table">
	<tr>
		<th>제목</th>
		<td>
			<form:input path="boTitle" class="form-control" />
			<form:errors path="boTitle" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<form:input path="boWriter" class="form-control" />
			<form:errors path="boWriter" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>
			<form:input path="boIp" readonly="true" class="form-control" 
				value="${pageContext.request.remoteAddr }"
			/>
			<form:errors path="boIp" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>
			<form:input type="email" path="boMail" class="form-control" />
			<form:errors path="boMail" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>
			<form:password path="boPass" class="form-control" />
			<form:errors path="boPass" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>신규 첨부파일</th>
		<td>
			<input type="file" name="boFiles" class="form-control"/>
			<input type="file" name="boFiles" class="form-control"/>
			<input type="file" name="boFiles" class="form-control"/>
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<form:textarea path="boContent" class="form-control" />
			<form:errors path="boContent" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" class="btn btn-primary"/>
			<input type="button" value="목록으로" class="btn btn-secondary linkBtn"
				data-href="${cPath }/board"
			/>
		</td>
	</tr>
</table>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('boContent', {
		filebrowserImageUploadUrl:CONTEXTPATH+"/board/image?type=image"
	});
</script>









