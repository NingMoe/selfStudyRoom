/**
 * 确认配置角色
 */
function cfgUserRole(){
	var checkStatus = table.checkStatus("roleTable");
	var ids = $(table.checkStatus("roleTable").data).map(function(){return this.id}).get().join();
	var m="";
	if(ids==""){
		m="当前用户未配置任何角色，是否继续?";
	}else{
		m="确定为当前用户设置所选角色?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			 var index1 = layer.load(3, {shade: [0.3]});
			$.post("/manager/user/cfgUserRole",{roleIds:ids,userId:userId},function(data,status){
				layer.close(index1);
				layer.close(index);
				if(data!=null){
					layer.alert(data.message,function(index2){
						layer.close(index2);
						location.href="/manager/user/toCfgUserRole?userId="+userId;
					});					
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}