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
	var originalFlag=$("#originalFlag").val();	
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
		url : jsBasePath+"/front/resume/insertProjectExperience.html",
		data : {			
			resumeId:resumeId,
			projectName:projectName,
			responsibilityDescribe:responsibilityDescribe,
			companyName:companyName,
			startTime:startTime,
			endTime:endTime,
			projectDescribe:projectDescribe,
			resumeSeekerId:resumeSeekerId,
			originalFlag:originalFlag
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