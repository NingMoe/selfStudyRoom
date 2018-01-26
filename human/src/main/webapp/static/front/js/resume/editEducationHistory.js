$(function(){
	FastClick.attach(document.body);
});
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var schoolName = $("#schoolName").val();
	var education = $("#education").val();
	var major = $("#major").val();	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var rehId=$("#rehId").val();	
	var resumeSeekerId=$("#resumeSeekerId").val();
	var resumeId=$("#resumeId").val();
	var positionId=$("#positionId").val();
	if($.trim(schoolName) ==""){
    	$.alert("学校必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(education)== ""){
    	$.alert("学历必选!");
    	layer.close(index);
    	return false;
    }
	if($.trim(major)== ""){
    	$.alert("专业必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(startTime)== ""){
    	$.alert("入学时间必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(endTime)!= "" && startTime>=endTime){
    	$.alert("毕业时间必须大于入学时间!");
    	layer.close(index);
    	return false;
    }	
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/editEdu.html",
		data : {
			id:rehId,
			schoolName:schoolName,
			education:education,
			major:major,
			startTime:startTime,
			endTime:endTime	
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/getEducationHistory.html?resumeSeekerId="+resumeSeekerId+"&positionId="+positionId+"&resumeId="+resumeId;  
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
	var rehId=$("#rehId").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	$.confirm("确认删除该条教育经历?", function() {
		  //点击确认后的回调函数
		var index = layer.load(1, {shade: [0.8, '#393D49']});
		deleteEduById(rehId,resumeSeekerId,index);
	  }, function() {
		  //点击取消后的回调函数
	});
}
function deleteEduById(rehId,resumeSeekerId,index){
	var resumeId=$("#resumeId").val();
	var positionId=$("#positionId").val();
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/deleteEdu.html",
		data : {
			id:rehId
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/getEducationHistory.html?resumeSeekerId="+resumeSeekerId+"&positionId="+positionId+"&resumeId="+resumeId;  
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



