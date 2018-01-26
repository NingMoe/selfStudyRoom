function save(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	var expectWorkPlace = $("#expectWorkPlace").val();
	var expectWorkSalary1 = $("#minSalary").val();
	var expectWorkSalary2 = $("#maxSalary").val();
	var resumeSeekerId=$("#resumeSeekerId").val();
	var resumeIntentionId=$("#resumeIntentionId").val();
	var originalFlag=$("#originalFlag").val();
	var flag=$("#flag").val();
	var positionId=$("#positionId").val();
	var resumeId=$("#resumeId").val();
	if($.trim(expectWorkPlace) ==""){
    	$.alert("期望工作地点必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(expectWorkSalary1)== ""){
    	$.alert("期望薪金最低值必填!");
    	layer.close(index);
    	return false;
    }
	if($.trim(expectWorkSalary2)== ""){
    	$.alert("期望薪金最高值必填!");
    	layer.close(index);
    	return false;
    }
	if(parseInt(expectWorkSalary1)>=parseInt(expectWorkSalary2)){
    	$.alert("期望薪金最高值必须大于最低值!");
    	layer.close(index);
    	return false;
    }	
	$.ajax({
		type : "post",
		dataType : "json",
		url : jsBasePath+"/front/resume/updateInterntion.html",
		data : {
			id : resumeIntentionId,//获取的id 必须传
			expectWorkPlace:expectWorkPlace,
			minSalary:expectWorkSalary1,
			maxSalary:expectWorkSalary2,
			resumeSeekerId:resumeSeekerId,
			resumeId:resumeId,
			originalFlag:originalFlag,
			flag:flag
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