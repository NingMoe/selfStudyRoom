$(function(){
	$("#telOrCardNo").focus();
});
//签到
function sign(){
	var telOrCardNo=$("#telOrCardNo").val().trim();	
	if(telOrCardNo.length==4){
		var index = layer.load(1, {shade: [0.8, '#393D49']});
		//第一步：校验长度及字符	
		var myreg = /^[A-Za-z0-9]{4}$/;  
		if (!myreg.test(telOrCardNo)) { 
			layer.alert("请输入数字或者英文字母!",{icon:2});
			layer.close(index);
		    return false;  
		}
		//第二步:校验时间及手机号或身份证是否存在
		var activityId=$("#activityId").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/activity/sign/checkSignTime.html",
			data : {
				activityId : activityId,
				telOrCardNo:telOrCardNo
			},
			success : function(data){
				layer.close(index);
				if(data.flag){
					var id=data.id;
					var activityTime=$("#activityTime").val();
					if(data.onlyFlag){					
						window.location.href = jsBasePath+"/activity/sign/toSignSuccess.html?id="+id+"&activityTime="+activityTime;
					}else{
						window.location.href = jsBasePath+"/activity/sign/toMySign.html?ids="+id+"&activityTime="+activityTime;
					}								
				}else{				
					layer.alert(data.message,{icon:2});
				}
			},
			error : function(data){
				layer.close(index);
				layer.alert("网络错误,请联系管理员!",{icon:2});
			}
		});		
	}	
}
//签到详情
function signDetails(){
	var activityId=$("#activityId").val();
	window.location.href = jsBasePath+"/activity/sign/signDetails.html?activityId="+activityId;
}