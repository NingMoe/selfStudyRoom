
/**
 * 新增用户
 */
function userAdd(){
	 var url = jsBasePath+"/manager/user/toAdd.html";
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
      area: ['520px','80%'],
      //scrollbar: false,
      title: "新增用户", //不显示标题
      content:url, //捕获的元素
      cancel: function(index){
          layer.close(index);
      },
      end:function(){
      	//query();
      }
  });
	  return false;
}

/**
 * 编辑用户
 */
function userEdit(id){
	 var url = jsBasePath+"/manager/user/toEdit.html?id="+id;
	  layer.open({
       type: 2,
       shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
       area: ['520px','80%'],
       //scrollbar: false,
       title: "编辑用户", //不显示标题
       content:url, //捕获的元素
       cancel: function(index){
           layer.close(index);
       },
       end:function(){
       	//query();
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
	if(status==1){
		m="确认要禁用用户?";
	}
	if(status==0){
		m="确认要启用用户?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/manager/user/updateStatus.html",{deleteIds:id,status:status},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#userTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

/**
 * 禁用、删除用户(批量)
 */
function bath_userDisable(status){
	var ids=getSelectId("userTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	var m="";
	if(status==-1){
		m="确认要删除所选用户?";
	}
	if(status==1){
		m="确认要禁用所选用户?";
	}
	if(status==0){
		m="确认要启用所选用户?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/manager/user/updateStatus.html",{deleteIds:ids,status:status},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#userTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

function toCfgUserRole(id){
	var url = jsBasePath+"/manager/user/toCfgUserRole.html?userId="+id;
	  layer.open({
     type: 2,
     shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
     area: ['80%','90%'],
     //scrollbar: false,
     title: "配置用户角色", //不显示标题
     content:url, //捕获的元素
     cancel: function(index){
         layer.close(index);
     },
     end:function(){
     	//query();
     }
 });
	  return false;
}

function toCfgDept(id){
	var url = jsBasePath+"/manager/user/toCfgDept.html?userId="+id;
	  layer.open({
     type: 2,
     shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
     area: ['350px','85%'],
     //scrollbar: false,
     title: "配置管理部门", //不显示标题
     content:url, //捕获的元素
     cancel: function(index){
         layer.close(index);
     },
     end:function(){
     	//query();
     }
 });
	  return false;
}

