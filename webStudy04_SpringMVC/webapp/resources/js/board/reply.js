/**
 * 
 */
//====================댓글 CRUD==========================
	$(document).ajaxError(function(event, xhr, settings, error){
		console.log(error);
	});
	function recursivePage(page){
		searchForm.find("[name='page']").val(page--);
		searchForm.submit();
	}
	function commonSuccess(resp){
		if(resp.result == "OK"){
			replyInsertForm.get(0).reset();
			replyModal.modal("hide");
			recursivePage(resp.page);
		}else if(resp.message){
			alert(resp.message);
		}
	}
	// 수정
	function updateReply(event){
		let reply = $(this).parents("tr:first").data("reply");
		for(let prop in reply){
			$(replyUpdateForm).find("[name='"+prop+"'][type!='password']").val(reply[prop]);
		}
		replyModal.modal("show");
	}
	// 삭제
	function deleteReply(event){
		if(!confirm("정말 지울텐가?")) return false;
		let reply = $(this).parents("tr:first").data("reply");
		$(replyDeleteForm).find("[name='repNo']").val(reply.repNo);
		replyDeleteModal.modal("show");
	}
	
	let listTable = $("#replyTable").on("click", ".updateBtn", updateReply)
									.on("click", ".delBtn", deleteReply)
									.find("#listBody");
	
	let replyModal = $("#replyModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	}).on("shown.bs.modal", function(){
		$(this).find("[name='page']").val(searchForm.find("[name='page']").val());
		$(this).find(":text:first").focus().select();
	});
	let replyDeleteModal = $("#replyDeleteModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	}).on("shown.bs.modal", function(){
		$(this).find("[name='page']").val(searchForm.find("[name='page']").val());
		$(this).find(":password").focus();
	});
	
	let options ={
		dataType : "json",
		success :commonSuccess,
		beforeSubmit:function(){
			replyDeleteModal.modal("hide");
		}
	}
	
	let replyInsertForm = $("#replyInsertForm").ajaxForm(options);
	let replyUpdateForm = replyModal.find("form").ajaxForm(options);
	let replyDeleteForm = replyDeleteModal.find("form")
							.ajaxForm(options);
//========================================================	
	
//====================덧글 페이징=======================
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	}).on("click", ".scrollTop", function(event){
		$(this).parents("[class*='overflow']:first").animate({scrollTop:0}, 1000);
	});;
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			pagingArea.empty();
			let replyList = resp.dataList;
			let trTags = [];
			if(replyList){
				$(replyList).each(function(idx, reply){
					let tr = $("<tr>");
					tr.append(
							$("<td>").text(reply.rnum)
									.addClass("text-center"),
							$("<td>").html(reply.repContent)
									.addClass("text-left"),
							$("<td>").text(reply.repWriter+"("+reply.repMail+")")
									.addClass("text-center"),
							$("<td>").text(reply.repDate)
									.addClass("text-center"),	
							$("<td>").append(
								$("<input>").attr({
									type:"button",
									value:"수정"
								}).addClass("btn btn-info mr-2 updateBtn"),		
								$("<input>").attr({
									type:"button",
									value:"삭제"
								}).addClass("btn btn-danger mr-2 delBtn")		
							).addClass("text-center")	
					).data("reply", reply);
					trTags.push(tr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").text("댓글이 없음.")									
					)
				);
			}
			listTable.html(trTags);
			pagingArea.html(resp.pagingRepStyle);
			let scrollElement = listTable.parents("[class*='overflow']:first");
			scrollElement.animate({scrollTop:scrollElement.get(0).scrollHeight}, 1000);
		},
		error : function(errResp) {
			console.log(errResp);
		}
	}).submit(); // 페이지 로드 후 1페이지의 댓글 요청.
//========================================================