<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/fileUploadForm.jsp</title>
</head>
<body>
<h4> 파일 업로드 양식 </h4>
<form action="<c:url value='/12/fileUpload_Framework.do'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader" />
	<input type="file" name="uploadFile" />
	<input type="submit" value="업로드" />
</form>
<pre>
<c:if test="${not empty uploader }">
업로더 : ${uploader }
<c:remove var="uploader" scope="session"/>
</c:if>
<c:if test="${not empty uploadFile }">
업로드 완료된 파일 : ${uploadFile }
<c:url value='/12/fileDownload.do' var="downloadURL">
	<c:param name="file" value="${uploadFile['saveName'] }" />
</c:url>
<a href="${downloadURL }">다운로드</a>
<%-- <c:remove var="fileMetaData" scope="session"/> --%>
</c:if>
</pre>
</body>
</html>













