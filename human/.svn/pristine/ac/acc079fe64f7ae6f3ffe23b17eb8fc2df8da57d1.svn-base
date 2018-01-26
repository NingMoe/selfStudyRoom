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
	var resumeSeekerId=$("#resumeSeekerId").val();
	var originalFlag=$("#originalFlag").val();	
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
		url : jsBasePath+"/front/resume/insertEdu.html",
		data : {
			resumeId:resumeId,
			schoolName:schoolName,
			education:education,
			major:major,
			startTime:startTime,
			endTime:endTime,
			resumeSeekerId:resumeSeekerId,
			originalFlag:originalFlag
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