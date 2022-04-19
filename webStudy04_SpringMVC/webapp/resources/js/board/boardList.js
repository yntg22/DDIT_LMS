/**
 * 
 */
const VIEWURLPTRN = CONTEXTPATH+"/board/%V";
//let modalWindow = $("#exampleModal").on("hidden.bs.modal", function(){
//	$(this).find(".modal-body").empty();
//});
let listBody = $("#listBody").on("click", "tr", function(){
	let viewURL = $(this).data("href");
	if(!viewURL) return false;
	
	$.ajax({
		url : viewURL,
		dataType : "html",
		success : function(resp) {
			modalWindow.find(".modal-body").html(resp);
			modalWindow.modal('show');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	
}).css("cursor", "pointer");
let pagingArea = $("#pagingArea");

let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		pagingArea.empty();
		searchForm[0].page.value="";
		
		let boardList = paging.dataList;
		let trTags = [];
		if(boardList && boardList.length > 0){
			$.each(boardList, function(idx, board){
				let viewURL = VIEWURLPTRN.replace("%V", board.boNo);
				let trTag = $("<tr>").addClass("linkBtn")
									.data("href", viewURL)
									.prop("id", "TR_"+board.boNo)
									.append(
										$("<td>").html(board.rnum)	
										, $("<td>").html(board.boNo)	
										, $("<td>").html(board.boTitle)	
										, $("<td>").html(board.boWriter)	
										, $("<td>").html(board.boDate)	
										, $("<td>").html(board.boHit)	
										, $("<td>").html(board.boRec)	
									);
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").html(
							$("<td>").attr("colspan", "6")
									 .html("검색 조건에 맞는 게시글이 없음.")
						);
			trTags.push(trTag);
		} // if end
		
		listBody.append(trTags);
		pagingArea.html(paging.pagingHTMLBS)
		
	} // success end
	, resetForm : false
}).submit();

pagingArea.on("click", "a", function(event){
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
	return false;
});
let searchDIV = $("#searchDIV").on("click", "#searchBtn", function(){
	let inputs = searchDIV.find("[name]");
	$(inputs).each(function(index, ipt){
//			console.log(this.name)
		let name = this.name;
		let value = $(this).val();
		searchForm.find("[name="+name+"]").val(value);
	});
	searchForm.submit();
});