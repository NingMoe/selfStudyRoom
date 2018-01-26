
/**
 * 新增角色
 */
function add(){
	 var url = jsBasePath+"/manager/role/toAdd.html";
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
      area: ['520px','80%'],
      //scrollbar: false,
      title: "新增角色", //不显示标题
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
function edit(id){
	 var url = jsBasePath+"/manager/role/toEdit.html?id="+id;
	  layer.open({
       type: 2,
       shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
       area: ['520px','80%'],
       //scrollbar: false,
       title: "编辑角色", //不显示标题
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
function roleDisable(id,status){
	if(status==-1){
		m="确认要删除角色?";
	}
	if(status==1){
		m="确认要禁用角色?";
	}
	if(status==0){
		m="确认要启用角色?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/manager/role/updateStatus.html",{deleteIds:id,status:status},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#roleTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

/**
 * 禁用、删除角色(批量)
 */
function bath_roleDisable(status){
	var ids=getSelectId("roleTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	var m="";
	if(status==-1){
		m="确认要删除所选角色?";
	}
	if(status==1){
		m="确认要禁用所选角色?";
	}
	if(status==0){
		m="确认要启用所选角色?";
	}
	layer.confirm(m, {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/manager/role/updateStatus.html",{deleteIds:ids,status:status},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#roleTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

/**
 * 分配角色权限
 */
function configRoleResource(id){
	 var url = jsBasePath+"/manager/role/toCfgRoleRes.html?id="+id;
	  layer.open({
           type: 2,
           shade : [ 0.5, '#000' ],
			offset : [ '5%' ],
			title : [ '配置权限' ],
           area: ['350px','85%'],
           content:url, //捕获的元素
           cancel: function(index){
               layer.close(index);
           },
           end:function(){
           }
       });
}

