function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var content = $("#content").val();	
	if($.trim(content)== ""){
    	$.alert("建议或意见必填!");
    	layer.close(index);
    	return false;
    }
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/wechat/binding/library/saveMyFeedBack2.html",
		data : {
			content:content
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