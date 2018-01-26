$(function(){
	FastClick.attach(document.body);
});
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var projectName = $("#projectName").val();
	var responsibilityDescribe = $("#responsibilityDescribe").val();	
	var companyName = $("#companyName").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var projectDescribe = $("#projectDescribe").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	var rpeId=$("#rpeId").val();	
	var resumeId=$("#resumeId").val();
	var positionId=$("#positionId").val();
	if($.trim(projectName) ==""){
    	$.alert("项目名称必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(startTime)== ""){
    	$.alert("项目开始时间必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(endTime)!= "" && startTime>=endTime){
    	$.alert("结束时间必须大于开始时间!");
    	layer.close(index);
    	return false;
    }
	if($.trim(projectDescribe)== ""){
    	$.alert("项目描述必填!");
    	layer.close(index);
    	return false;
    }
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/updateProjectExperience.html",
		data : {
			id:rpeId,
			projectName:projectName,
			responsibilityDescribe:responsibilityDescribe,
			companyName:companyName,
			startTime:startTime,
			endTime:endTime,
			projectDescribe:projectDescribe
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/getProjectExperience.html?resumeSeekerId="+resumeSeekerId+"&positionId="+positionId+"&resumeId="+resumeId;  
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
//删除
function deleteById(){
	var rpeId=$("#rpeId").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	$.confirm("确认删除该条项目经验?", function() {
		  //点击确认后的回调函数
		var index = layer.load(1, {shade: [0.8, '#393D49']});
		deleteProjectExperienceById(rpeId,resumeSeekerId,index);
	  }, function() {
		  //点击取消后的回调函数
	});
}
function deleteProjectExperienceById(rpeId,resumeSeekerId,index){
	var resumeId=$("#resumeId").val();
	var positionId=$("#positionId").val();
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/deleteProjectExperience.html",
		data : {
			id:rpeId
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/getProjectExperience.html?resumeSeekerId="+resumeSeekerId+"&positionId="+positionId+"&resumeId="+resumeId;  
				});				
			}else{
			   $.alert(data.msg, function() {
				   layer.close(index); 
			    });
			}
		},
		error : function(data, status, e) {	
			$.alert("ajax请求出错"+e);
			layer.close(index);
		}
	});	
}