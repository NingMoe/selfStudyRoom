layui.use(['form'], function(){
    var form = layui.form();
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#menuTable').bootstrapTable('destroy');
	$("#menuTable").bootstrapTable({
		url : jsBasePath + '/customer/centerManager/query.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		//sortOrder : "asc", //排序方式
		queryParams : queryParams, //传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, //是否显示所有的列
		showRefresh : false, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		clickToSelect : false, //是否启用点击选中行
		//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [ {
			checkbox : true,
			fieId : 'id'
		}, {
			field : '',
			title : '序号',
			align : 'center',
			width:'50',
			formatter : function(value, row, index) {
				var page = $("#menuTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'name',
			title : '名称',
			width:'100',
			align : 'center'
		},{
			field : 'url',
			title : '跳转链接',
			width:'150',
			align : 'center',
			class : "fixtd"
		}, {
			field : 'filter',
			title : '权限分配类型',
			align : 'center',
			width:'100',
			formatter : function(value, row, index) {
				if(value=="1"){
					return "全部可见";
				}
				if(value=="2"){
					return "按部门分配";
				}
			}
		}, {
			field : 'sort',
			title : '排序',
			width:'50',
			align : 'center'
		}, {
			field : 'modelName',
			title : '模块名',
			width:'100',
			align : 'center'
		},{
			field : 'status',
			title : '状态',
			width:'50',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == 0) {
					return "<font class='normal'>启用</font>";
				}
				if (value ==1) {
					return "<font class='disable'>禁用</font>";
				}
			}
		},{
			field : '',
			title : '操作',
			width:'200',
			align : 'center',
			formatter : function(value, row, index) {
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
					if(row.status==0){
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
							+ row.id + "\",1);'><i class='fa fa-pencil-square-o'></i>&nbsp;禁用</button>";
					}
					if(row.status==1){
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
							+ row.id + "\",0);'><i class='fa fa-pencil-square-o'></i>&nbsp;启用</button>";
					}
					if(row.filter=="2"){
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return grantDept(\"" 
							+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;分配部门</button>";
					}
					
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		name : $.trim($("#name").val()),
		modelId: $.trim($("#modelId").val()),
	};
}
//添加
function add(){
	 var url = jsBasePath+"/customer/centerManager/toAdd.html";
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增菜单", //
		 offset : [ '4%' ],
	     area: ['500px','70%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 initTable();
	     }
});
	  return false;
}
//编辑
function edit(id){
	 var url = jsBasePath+"/customer/centerManager/toEdit.html?id="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑菜单", //
		 offset : [ '4%' ],
		 area: ['500px','70%'],	    
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 initTable();
	     }
	 });
	 return false;
}

//分配部门
function grantDept(id){
	 var url = jsBasePath+"/customer/centerManager/toGrantDept.html?menuId="+id;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "分配部门", //
		 offset : [ '4%' ],
		 area: ['500px','70%'],	      
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 initTable();
	     }
	 });
	 return false;
}

function del(id,status){
	$.ajax({
		type:"post",
		data:{"status":status,"id":id},
		dataType:"json",
		url:jsBasePath+"/customer/centerManager/del.html",
		success:function(data){
			if(data.flag){
				if(status==0){
					layer.alert("启用成功",{icon:1},function(index){
						layer.close(index);
						initTable();
					});
				}
				if(status==1){
					layer.alert("禁用成功",{icon:1},function(index){
						layer.close(index);
						initTable();
					});
				}
			}else{
				layer.alert("操作失败",{icon:2},function(index){
					layer.close(index);
				});
			}
		},
		error:function(){
			layer.alert("操作失败，请联系系统管理员",{icon:2},function(index){
				layer.close(index);
			});
		}
		
	});
}