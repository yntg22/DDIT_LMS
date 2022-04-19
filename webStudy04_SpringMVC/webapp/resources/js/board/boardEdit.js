/**
 * 
 */
	CKEDITOR.replace('boContent', {
		filebrowserImageUploadUrl:CONTEXTPATH+"/board/image?type=image"
	});

	let boardEditForm = $("#boardEditForm");
	$(".attatchDelBtn").on("click", function(){
		let attNo = $(this).data("attNo");
		$(this).parents("span:first").hide();
		let newInput = $("<input>").attr({
							"type":"text"
							, "name":"delAttNos"
							, "value":attNo
						});
		boardEditForm.append(newInput);
	});