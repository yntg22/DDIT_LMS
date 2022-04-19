<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav class="navbar navbar-expand-md navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="${cPath }">Company name</a>
	  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <ul class="navbar-nav px-3">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/member/memberList.do">회원관리</a>
	    </li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/prod/prodList.do">상품관리</a>
	    </li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/address/addressMng">주소록관리(비동기)</a>
	    </li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/address/addressMngSync">주소록관리(동기)</a>
	    </li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/buyer/buyerList.do">거래처 관리</a>
	    </li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${cPath }/board">자유게시판</a>
	    </li>
	  </ul>
	 <ul class="col navbar-nav d-flex justify-content-end">
	  	<c:set var="authMember" value="${sessionScope.authMember }" />
	    <c:choose>
	    	<c:when test="${empty authMember}">
	    		<li class="nav-item text-nowrap">
					<a class="nav-link" href="${cPath }/member/memberInsert.do">회원가입</a>
	    		</li>
	    		<li class="nav-item text-nowrap">
					<a class="nav-link" href="${cPath }/login/loginForm.do">로그인</a>
	    		</li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item text-nowrap">
	    			<span class="nav-link">
					<a class="mr-2" href="${pageContext.request.contextPath }/myPage.do">${authMember.memName }님</a>
					<a href="${pageContext.request.contextPath }/login/logout.do" id="logoutBtn">로그아웃</a>
					</span>
					<form method="post" id="hiddenForm"></form>
					<script>
						const hiddenForm = $("#hiddenForm");
						$("#logoutBtn").on("click", function(event){
							event.preventDefault();
							let href = this.href;
							hiddenForm.attr("action", href);
							hiddenForm.submit();
							hiddenForm.attr("action", "");
						});
					</script>
				</li>
	    	</c:otherwise>
	    </c:choose>
	  </ul>
</nav>