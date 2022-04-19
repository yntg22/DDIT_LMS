<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>login/loginForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<style type="text/css">
html,body {
  height: 100%;
}

body {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message }");
	</script>
</c:if>
</head>
<body>
<!-- 1. 인증에 성공했을때 체크박스를 체크한 경우, 로그인에 성공한 아이디를 일주일동안 저장. -->
<!-- 2. 인증에 성공했을때 체크박스를 해제한 경우, 저장된 아이디가 있는 경우, 삭제. -->
<!-- 3. 저장된 아이디가 있으면,  아이디 초기값과, 체크박스 상태 복원 -->
<form class="form-signin" action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
	<h1 class="h3 mb-3 font-weight-normal text-center">Please sign in</h1>
  <label for="memId" class="sr-only">Account</label>
  <input type="text" id="memId" name="memId" value="${cookie.idCookie.value }" 
  	class="form-control mb-2" placeholder="Account" required autofocus>
  <label for="memPass" class="sr-only">Password</label>
  <input type="password" id="memPass" name="memPass" class="form-control" placeholder="Password" required>
  <div class="checkbox mb-3 d-flex justify-content-center form-group">
    <label>
      <input type="checkbox" name="saveId" value="save"  ${empty cookie.idCookie?"":"checked" }> 아이디저장
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
</form>
</body>
</html>











