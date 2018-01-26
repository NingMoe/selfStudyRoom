function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var insideRecommend = $("#insideRecommend").val();
	var insideRelation = $("#insideRelation").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	var originalFlag=$("#originalFlag").val();
	var positionId=$("#positionId").val();
	var resumeId=$("#resumeId").val();
	if($.trim(insideRecommend) ==""){
    	$.alert("推荐人邮箱必填!");
    	layer.close(index);
    	return false;
    }
	
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/updateInsideRecommend.html",
		data : {
			id:resumeId,
			insideRecommend:insideRecommend,
			insideRelation:insideRelation,
			resumeSeekerId:resumeSeekerId,
			originalFlag:originalFlag
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/index.html?positionId="+positionId+"&resumeId="+resumeId;  
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