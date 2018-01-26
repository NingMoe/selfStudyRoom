function sign(id){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/activity/sign/mySign.html",
		data : {
			id : id
		},
		success : function(data){
			layer.close(index);
			if(data.flag){
				var activityTime=$("#activityTime").val();								
				window.location.href = jsBasePath+"/activity/sign/toSignSuccess.html?id="+id+"&activityTime="+activityTime;												
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