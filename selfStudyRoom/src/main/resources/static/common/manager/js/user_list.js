/**
 * 新增用户
 */
function userAdd(){
	 var url = "/manager/user/toAdd";
	  layer.open({
        type: 2,
        shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
        area: ['520px','80%'],      
        title: "新增用户", //不显示标题
        content:url, //捕获的元素
        cancel: function(index){
          layer.close(index);
        },
        end:function(){
      	
       }
   });
	  return false;
}

/**
 * 编辑用户
 */
function userEdit(id){
	 var url = "/manager/user/toEdit?id="+id;
	 layer.open({
	        type: 2,
	        shade : [ 0.5, '#000' ],
			offset : [ '5%' ],
	        area: ['520px','80%'],      
	        title: "编辑用户", //不显示标题
	        content:url, //捕获的元素
	        cancel: function(index){
	          layer.close(index);
	        },
	        end:function(){
	      	
	       }
	   });
	return false;
}

/**
 * 禁用、删除用户
 */
function userDisable(id,status){
	var m="";
	if(status==-1){
		m="确认要删除用户?";
	}
	if(status==2){
		m="确认要禁用用户?";
	}
	if(status==1){
		m="确认要启用用户?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post("/manager/user/updateStatus",{deleteIds:id,status:status},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						window.location.reload();
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

//配置用户角色
function toCfgUserRole(id){
	var url = "/manager/user/toCfgUserRole?userId="+id;
	  layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
		 offset : [ '5%' ],
	     area: ['80%','90%'],	    
	     title: "配置用户角色", //不显示标题
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	     
	     }
	 });
	 return false;
}


