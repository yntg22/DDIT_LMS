<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
      	<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>API Examples</span>
          <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
            <span data-feather="plus-circle"></span>
          </a>
        </h6>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/server/browsing.do">
              <span data-feather="file"></span>
              	서버 파일 브라우징
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/employee/treeView.do">
              <span data-feather="users"></span>
              	직원 조직도
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/12/fileUploadForm.jsp">
              <span data-feather="file"></span>
              	파일업다운로드 샘플
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/example/fullCalendar">
              <span data-feather="layers"></span>
              FullCalendar
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/example/wsEcho">
              <span data-feather="layers"></span>
              WebSocketEcho
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/example/stompEcho">
              <span data-feather="layers"></span>
              STOMP Echo
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${cPath }/example/stompPush">
              <span data-feather="layers"></span>
              Push message(using STOMP)
            </a>
          </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>Saved reports</span>
          <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
            <span data-feather="plus-circle"></span>
          </a>
        </h6>
        <ul class="nav flex-column mb-2">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Current month
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Last quarter
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Social engagement
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Year-end sale
            </a>
          </li>
          <c:forEach items="${menuList }" var="menu">
	          <li class="nav-item">
	          	<a class="nav-link" href="${cPath }${menu.menuURL }">
	          		${menu.menuText }
	          	</a>
	          </li>
          </c:forEach>
        </ul>
      </div>
    </nav>
    
    
    
    
    
    
    
    
    
    
    