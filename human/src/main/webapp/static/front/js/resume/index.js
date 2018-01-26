function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var positionId=$("#positionId").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/finishDelivery.html",
		data : {
			resumeSeekerId:resumeSeekerId,
			positionId:positionId
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/home/index.html";  
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