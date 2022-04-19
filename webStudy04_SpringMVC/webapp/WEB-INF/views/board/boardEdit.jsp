<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<form:form modelAttribute="board" id="boardEditForm" class="border border-primary"
	enctype="multipart/form-data"
	action="${cPath }/board/${board.boNo }" method="post">
	<input type="hidden" name="_method" value="put" />	
	<form:hidden path="boNo"/>
<table class="table">
	<tr>
		<th>제목</th>
		<td>
			<form:input path="boTitle" 
				class="form-control" />
			<form:errors path="boTitle" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<form:input path="boWriter" 
				class="form-control" />
			<form:errors path="boWriter" class="error" element="span" />
		</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>
			<form:input path="boIp" readonly="true" 
				 class="form-control" />
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
		<th>기존 첨부파일</th>
		<td>
			<c:forEach items="${board.attatchList }" var="attatch">
				<span>
					${attatch.attFilename }
					<input type="button" value="삭제" class="attatchDelBtn"
						data-att-no="${attatch.attNo }"
					/>
				</span>
			</c:forEach>
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

<script type="text/javascript" src="${cPath }/resources/js/board/boardEdit.js"></script>









