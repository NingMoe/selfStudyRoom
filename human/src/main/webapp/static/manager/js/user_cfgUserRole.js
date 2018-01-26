/**
 * 确认配置角色
 */
function cfgUserRole(){
	var m="";
	var ids=getSelectId("roleTable");
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
			$.post(jsBasePath+"/manager/user/cfgUserRole.html",{roleIds:ids,userId:userId},function(data,status){
				layer.close(index1);
				layer.close(index);
				if(data!=null){
					layer.alert(data.message);
					if(data.flag==false){
						initTable();
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}