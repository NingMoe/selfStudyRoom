//解除微信绑定
function removeBinding(){
	var resumeSeekerId=$("#resumeSeekerId").val();
	$.confirm("确认解除微信绑定?", function() {
		  //点击确认后的回调函数
		var index = layer.load(1, {shade: [0.8, '#393D49']});
		removeById(resumeSeekerId,index);
	  }, function() {
		  //点击取消后的回调函数
	});
}
function removeById(resumeSeekerId,index){
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/mine/removeBinding.html",
		data : {
			resumeSeekerId:resumeSeekerId
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/mine/tomain.html";
				});				
			}else{
			   layer.close(index); 
			   $.alert(data.msg, function() {				   
			    });
			}
		},
		error : function(data, status, e) {	
			layer.close(index);
			$.alert("ajax请求出错"+e);			
		}
	});	
}