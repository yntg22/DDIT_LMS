<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered">
	<tr>
		<th>글번호</th>
		<td>${board.boNo }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${board.boTitle }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.boWriter } [${board.boMail }]</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>${board.boIp }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${board.boDate }</td>
	</tr>
	<tr>
		<th>카운트 데이터</th>
		<td>
			조회수 : <span class="border border-primary">${board.boHit }</span>
			추천수 : <span class="border border-secondary">${board.boRec }</span>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
				<c:url value="/board/${board.boNo }/attatch/${attatch.attNo }" var="downloadURL" />
				<a href="${downloadURL }">${attatch.attFilename }</a> 
				<c:if test="${not vs.last }">
					| &nbsp;&nbsp;&nbsp;
				</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.boContent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" class="btn btn-primary d-inline linkBtn" 
				value="수정" data-href="${cPath }/board/${board.boNo}/form"/>
			<input type="button" class="btn btn-danger d-inline" value="삭제" id="delBtn"/>
			<form id="deleteForm" action="${cPath }/board/${board.boNo}" method="post" class="form-inline d-none" >
				<input type="hidden" name="_method" value="delete" />
				<input type="hidden" name="boNo" value="${board.boNo }" />
				<input type="password" name="boPass" class="form-control" placeholder="비밀번호" required/>
				<input type="submit" class="btn btn-danger" value="삭제" />
				<input type="reset" class="btn btn-secondary" value="취소" id="resetBtn"/>
			</form>
		</td>
	</tr>
</table>
	
<!-- 덧글 처리 UI 추가 -->
<form method="post" class="form-inline" id="replyInsertForm" action="${cPath }/board/${board.boNo }/reply">
	<input type="hidden" name="page"  required value="1"/>
	<input type="hidden" name="boNo" value="${board.boNo }"/>
	<input type="hidden" name="repNo" />
	<table class="col-md-10">
		<tr>
			<td>
				<input type="text" class="form-control mb-2" name="repWriter" placeholder="작성자" maxlength="15"/>
			</td>
			<td>
				<input type="password" class="form-control mb-2" name="repPass" placeholder="비밀번호"/>
			</td>
			<td>
				<input type="email" class="form-control mb-2" name="repMail" placeholder="이메일" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="repContent"></textarea>
				</div>
			</td>
			<td colspan="3">
				<input type="submit" value="전송" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form>

<table id="replyTable" class="table table-bordered">
	<thead class="table-dark">
		<tr>
			<th class="text-center">일련번호</th>
			<th class="text-center">내용</th>
			<th class="text-center">작성자</th>
			<th class="text-center">작성일</th>
			<th class="text-center"></th>
		</tr>
	</thead>
	<tbody id="listBody" class="overflow-auto">
	
	</tbody>
	<tfoot>
		<tr>
			<td id="pagingArea" colspan="6"></td>
		</tr>
	</tfoot>
</table>

<form id="searchForm" action="${cPath }/board/${board.boNo}/reply">
	<input type="hidden" name="page" />
</form>

<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${cPath }/board/${board.boNo }/reply" method="post">
      	<input type="hidden" name="page"  required />
      	<input type="hidden" name="_method" value="put">
      	<input type="hidden" name="repNo" required/>
      	<input type="hidden" name="boNo"  required value="${board.boNo }"/>
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<input type="text" required name="repWriter" class="form-control" placeholder="작성자" />
	      			</td>
	      			<td>
	      				<input type="password" required name="repPass" class="form-control" placeholder="비밀번호"/>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="repContent"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade" id="replyDeleteModal" tabindex="-1" aria-labelledby="replyDeleteModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${cPath }/board/${board.boNo }/reply" method="post">
        <input type="hidden" name="page"  required />
      	<input type="hidden" name="_method" value="delete">
      	<input type="hidden" name="repNo" required/>
      	<input type="hidden" name="boNo"  required value="${board.boNo }"/>
	      <div class="modal-body">
   				<input type="password" required name="repPass" class="form-control" placeholder="비밀번호"/>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">삭제</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>	
	
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>	
<script type="text/javascript" src="${cPath }/resources/js/board/reply.js"></script>	


<script type="text/javascript">
	$("#delBtn,#resetBtn").on("click", function(){
		$("#deleteForm").toggleClass("d-inline").toggleClass("d-none");
		$("#delBtn").toggleClass("d-inline").toggleClass("d-none");
	});
</script>