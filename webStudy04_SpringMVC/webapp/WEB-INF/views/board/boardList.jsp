<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
	<thead class="thead thead-dark">
		<tr>
			<th>일련번호</th>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
				
				</div>
				<h4>검색용 UI</h4>
				<div id="searchDIV" class="border border-warning form-inline">
					<select name="searchType" class="form-control mr-2">
						<option value>전체</option>
						<option value="TITLE">제목</option>
						<option value="WRITER">작성자</option>
						<option value="CONTENT">내용</option>
					</select>
					<input type="text" name="searchWord"  class="form-control mr-2"/>
					<input type="button" value="검색" id="searchBtn" class="btn btn-primary mr-2"/>
					<input type="button" value="새글쓰기" class="btn btn-primary linkBtn"
						data-href="${cPath }/board/new"
					/>
					
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<div class="border border-primary">
<h4>Hidden Form</h4>
<form id="searchForm">
	<input type="text" name="page"/>
	<input type="text" name="searchType"/>
	<input type="text" name="searchWord"/>
</form>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog  modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/board/boardList.js"></script>
<%-- <c:if test="${not empty success }">
<script type="text/javascript">
	listBody.find("#TR_"+${success.boNo}).trigger("click");
</script>
</c:if> --%>















