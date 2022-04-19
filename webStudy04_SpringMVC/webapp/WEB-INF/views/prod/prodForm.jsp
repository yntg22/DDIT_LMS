<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form modelAttribute="prod" method="post" enctype="multipart/form-data">
	<c:if test="${command eq 'UPDATE' }">
		<form:hidden path="prodId" required="true"/>	
	</c:if>
	<c:if test="${command ne 'UPDATE' }">
		<form:hidden path="prodId" />
	</c:if>

	<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td>
				<form:input path="prodName" required="true" class="form-control"/>
				<form:errors path="prodName" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<form:select path="prodLgu" class="form-control">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod['lprodGu'] }" label="${lprod.lprodNm }"/>
					</c:forEach>
				</form:select>
				<form:errors path="prodLgu" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<form:select path="prodBuyer"  class="form-control">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option value="${buyer.buyerId }" label="${buyer.buyerName }" 
							cssClass="${buyer.buyerLgu }"
						/>
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				
				<form:input path="prodCost" cssClass="form-control" required="true" type="number" />
				<form:errors path="prodCost" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<form:input path="prodPrice" cssClass="form-control" required="true" type="number" />
				<form:errors path="prodPrice" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<form:input path="prodSale" cssClass="form-control" required="true" type="number" />
				<form:errors path="prodSale" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>기본정보</th>
			<td>
				<form:input path="prodOutline" cssClass="form-control" required="true" />
				<form:errors path="prodOutline" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<form:textarea path="prodDetail" cssClass="form-control"/>
				<form:errors path="prodDetail" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<form:input path="prodImage" type="file" cssClass="form-control"/>
				<form:errors path="prodImg" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<form:input path="prodTotalstock" cssClass="form-control" required="true" type="number" />
				<form:errors path="prodTotalstock" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<form:input path="prodInsdate" type="date" cssClass="form-control" />
				<form:errors path="prodInsdate" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<form:input path="prodProperstock" cssClass="form-control" required="true" type="number" />
				<form:errors path="prodProperstock" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<form:input path="prodSize" cssClass="form-control" />
				<form:errors path="prodSize" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<form:input path="prodColor" cssClass="form-control" />
				<form:errors path="prodColor" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<form:input path="prodDelivery" cssClass="form-control" />
				<form:errors path="prodDelivery" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<form:input path="prodUnit" cssClass="form-control" />
				<form:errors path="prodUnit" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<form:input path="prodQtyin" type="number"  cssClass="form-control" />
				<form:errors path="prodQtyin" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>
				<form:input path="prodQtysale" type="number"  cssClass="form-control" />
				<form:errors path="prodQtysale" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<form:input path="prodMileage" type="number"  cssClass="form-control" />
				<form:errors path="prodMileage" cssClass="error" element="span" />
			</td>
		</tr>
		<tr>	
			<td colspan="2">
				<form:button type="submit" class="btn btn-success">저장</form:button>
				<form:button type="reset" class="btn btn-warning">취소</form:button>
			</td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	let prodBuyerTag = $("[name=prodBuyer]").val("${prod.prodBuyer }");
	$("[name=prodLgu]").on("change", function(){
		let selectedLgu = $(this).val();
		if(!selectedLgu){
			$(prodBuyerTag).find("option").show();
		}else{
			$(prodBuyerTag).find("option").hide();
			$(prodBuyerTag).find("option."+selectedLgu).show();
			$(prodBuyerTag).find("option:first").show();
		}
	}).val("${prod.prodLgu }");

</script>

















