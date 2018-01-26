//继续签到
function sign(){
	var activityTime=$("#activityTime").val();
	window.location.href = jsBasePath+"/activity/sign/"+activityTime+".html";
}
//撤销
function revoke(){
	var signInfoId=$("#signInfoId").val();
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
						sign();						
					});												
				}else{				
					layer.alert(data.message,{icon:2});
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}