$(function(){
	FastClick.attach(document.body);
});
function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var companyName = $("#companyName").val();
	var position = $("#position").val();
	var workType = $("#workType").val();	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var minSalary = $("#minSalary").val();
	var maxSalary = $("#maxSalary").val();
	var describes = $("#describes").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	var originalFlag=$("#originalFlag").val();	
	var resumeId=$("#resumeId").val();
	var positionId=$("#positionId").val();
	if($.trim(companyName) ==""){
    	$.alert("公司名称必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(position)== ""){
    	$.alert("岗位名称必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(startTime)== ""){
    	$.alert("工作开始时间必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(endTime)!= "" && startTime>=endTime){
    	$.alert("结束时间必须大于开始时间!");
    	layer.close(index);
    	return false;
    }
	if($.trim(minSalary)!= "" && $.trim(maxSalary)!= "" && parseInt(minSalary)>=parseInt(maxSalary)){
    	$.alert("职位薪金最高值必须大于最低值!");
    	layer.close(index);
    	return false;
    }	
	if($.trim(describes)== ""){
    	$.alert("工作描述必填!");
    	layer.close(index);
    	return false;
    }
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/insertWorkHistory.html",
		data : {
			resumeId:resumeId,
			companyName:companyName,
			position:position,
			workType:workType,
			startTime:startTime,
			endTime:endTime,
			minSalary:minSalary,
			maxSalary:maxSalary,
			describes:describes,
			resumeSeekerId:resumeSeekerId,
			originalFlag:originalFlag
		},
		success: function(data){
			if(data.flag){
				layer.close(index); 
				$.alert(data.msg, function() {
					window.location.href=jsBasePath+"/front/resume/getWorkHistory.html?resumeSeekerId="+resumeSeekerId+"&positionId="+positionId+"&resumeId="+resumeId;  
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