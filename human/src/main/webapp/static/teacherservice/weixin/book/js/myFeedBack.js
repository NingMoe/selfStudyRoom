function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var bookName = $("#bookName").val();
	var bookAuthor = $("#bookAuthor").val();
	var reason = $("#reason").val();
	if($.trim(bookName) ==""){
    	$.alert("书籍名称必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(bookAuthor)== ""){
    	$.alert("书籍作者必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(reason)== ""){
    	$.alert("推荐理由必填!");
    	layer.close(index);
    	return false;
    }
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/wechat/binding/library/saveMyFeedBack.html",
		data : {
			bookName:bookName,
			bookAuthor:bookAuthor,
			reason:reason
		},
		success: function(data){
			layer.close(index);
			if(data.flag){				 
				$.alert(data.message, function() {
					window.location.href=jsBasePath+"/wechat/binding/library/wxview.html";
				});				
			}else{			  
			   $.alert(data.message, function() {				   
			    });
			}
		},
		error : function(data, status, e) {	
			layer.close(index);
			$.alert("ajax请求出错"+e);			
		}
	});	
}