//签到
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
				layer.alert(data.message,{icon:1},function(index1){
					layer.close(index1);
					window.location.reload();					
				});												
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
//撤销
function revoke(signInfoId){
	layer.confirm("确定撤销签到?撤销后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/activity/sign/revoke.html",{id:signInfoId},function(data,status){
				layer.close(index);
				if(data.flag){
					layer.alert(data.message,{icon:1},function(index1){
						layer.close(index1);
						window.location.reload();						
					});												
				}else{				
					layer.alert(data.message,{icon:2});
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//搜索
function serachInfo(activityId,deptorschool){
	var name=$.trim($("#serachId").val());
	window.location.href = jsBasePath+"/activity/sign/toDeptSignDetails.html?activityId="+activityId+"&deptorschool="+deptorschool+"&name="+name;
}