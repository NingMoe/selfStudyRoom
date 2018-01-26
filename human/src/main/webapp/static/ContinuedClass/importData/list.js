layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
    initTable();
});

function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/continued_class/importData/query.html', //请求后台的URL（*）
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
		height:$(window).height()-$("#serachFrom").height()-52,       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
			formatter : function(value, row, index) {
				var page = $("#ccrTable").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'classCode',
			title : '班号',
			align : 'center'
		},{
			field : 'className',
			title : '班级名称',
			align : 'center'
		},{
			field : 'schoolAreaName',
			title : '校区',
			align : 'center'
		}, {
			field : 'grade',
			title : '年级',
			align : 'center'
		}, {
			field : 'subject',
			title : '科目',
			align : 'center'
		},{
			field : 'teacherName',
			title : '教师',
			align : 'center'
		},{
			field : 'deptName',
			title : '部门',
			align : 'center'
		},{
			field : 'kuokeFlag',
			title : '是否扩科',
			align : 'center',
			formatter : function(value, row, index) {
				if (value=='1') {
					return "<font class='normal'>否</font>";
				}else {
					return "<font class='disable'>是</font>";
				}
			}
		},{
			field : 'topFlag',
			title : '是否尖子',
			align : 'center',
			formatter : function(value, row, index) {
				if (value=='1') {
					return "<font class='normal'>否</font>";
				}else if (value=='0'){
					return "<font class='disable'>是</font>";
				}else{
					return "<font class='disable'>超</font>";
				}
			}
		},{
			field : 'createUser',
			title : '添加人',
			align : 'center'
		}, {
			field : 'createTime',
			title : '添加时间',
			align : 'center'
		}, {
			field : 'updateUser',
			title : '修改人',
			align : 'center'
		}, {
			field : 'updateTime',
			title : '修改时间',
			align : 'center'
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var type=$("#type").val();
				var re="";				
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return del(\"" 
						+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
					re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return refresh(\"" 
						+ row.classCode + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;刷新</button>";
					if(type=="1"){
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return getStudents(\"" 
							+ row.classCode + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;获取学员</button>";
					}
				return re;
			}
		}],
		onLoadSuccess : function(dataAll) {
			
		},
		onLoadError : function() {
			
		},
		onPageChange : function(number, size) {
			$("html,body").animate({scrollTop:0},1000);
		}
	});
}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		ruleId : $.trim($("#ruleId").val()),
		type: $.trim($("#type").val()),
		classCode : $.trim($("#classCode").val()),
		schoolAreaName: $.trim($("#schoolAreaName").val()),
		subject: $.trim($("#subject").val()),
		teacherName : $.trim($("#teacherName").val()),
		deptName :  $.trim($("#deptName").val()),
		grade :  $.trim($("#grade").val())
	};
}
//添加
function add(){
	 var url = jsBasePath+"/continued_class/importData/toAdd.html?ruleId="+$("#ruleId").val()+"&type="+$("#type").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增班级数据", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
 });
	  return false;
}
//编辑
function edit(id){
	 var url = jsBasePath+"/continued_class/importData/toEdit.html?id="+id+"&type="+$("#type").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "编辑班级数据", //
		 offset : [ '10%' ],
	     area: ['600px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}
//删除
function del(id){
	layer.confirm("确定删除该班级数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/importData/delete.html",{deleteIds:id,type:$("#type").val()},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#ccrTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除
function bath_del(){
	var ids=getSelectId("ccrTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	del(ids);
}
//全部删除
function delAll(){
	layer.confirm("确定删除全部班级数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/importData/deleteAll.html",{ruleId:$("#ruleId").val(),type:$("#type").val()},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#ccrTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量导入
function bath_add(){
	 var url = jsBasePath+"/continued_class/importData/batch_add.html?ruleId="+$("#ruleId").val()+"&type="+$("#type").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "批量导入班级数据", //
		 offset : [ '10%' ],
	     area: ['500px','50%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
 });
	  return false;
}
//批量导出
function bath_export(){
	var ids=getSelectId("ccrTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
  window.location.href = jsBasePath+"/continued_class/importData/exportSelect.html?ruleId="+$("#ruleId").val()+"&type="+$("#type").val()+"&ids="+ids;
}
//全部导出
function exportAll(){
	window.location.href = jsBasePath+"/continued_class/importData/exportAll.html?ruleId="+$("#ruleId").val()+"&type="+$("#type").val();
}
//刷新
function refresh(classCode){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/importData/refresh.html",
		data :{classCodes:classCode},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});				
			}else{
				layer.alert(res.message,{icon:1},function(){					
					window.location.reload();
				});
			}	
		}
	});
}
//批量刷新
function bath_refresh(){
	var checks=$("#ccrTable").bootstrapTable('getSelections');
	var classCodes="";
	for(var i = 0; i < checks.length; i++){
		classCodes=classCodes+checks[i].classCode+",";
	};
	if(classCodes==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	refresh(classCodes);	
}
//全部刷新
function refreshAll(){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/importData/refreshAll.html",
		data :{ruleId:$("#ruleId").val(),type:$("#type").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{
				layer.alert(res.message,{icon:1},function(){						
					window.location.reload();
				});
			}
		}
	});
}
//获取学员
function getStudents(classCode){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/importData/getStudentsByClassCode.html",
		data :{ruleId:$("#ruleId").val(),classCodes:classCode},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});				
			}else{
				layer.alert(res.message,{icon:1},function(){					
					window.location.reload();
				});
			}	
		}
	});
}
//批量获取学员
function bath_getStudents(){
	var checks=$("#ccrTable").bootstrapTable('getSelections');
	var classCodes="";
	for(var i = 0; i < checks.length; i++){
		classCodes=classCodes+checks[i].classCode+",";
	};
	if(classCodes==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	getStudents(classCodes);	
}
//获取全部学员
function getAllStudents(){
	var index = layer.load(3, {shade: [0.3]});
	$.ajax({
		url : jsBasePath+"/continued_class/importData/getAllStudents.html",
		data :{ruleId:$("#ruleId").val(),type:$("#type").val()},
		async:false,
		dataType : "json",
		type : "post",
		success:function(res){
			layer.close(index);
			if(!res.flag){
				layer.alert(res.message,{icon:2});
			}else{
				layer.alert(res.message,{icon:1},function(){						
					window.location.reload();
				});
			}
		}
	});
}
//删除全部学员
function delAllStudents(){
	layer.confirm("确定删除全部全部学员么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/importData/delAllStudents.html",{ruleId:$("#ruleId").val()},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#ccrTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//按开课日期过滤学员
function getAllStudentsByDate(){
	var url = jsBasePath+"/continued_class/importData/filterStudentsByDate.html?ruleId="+$("#ruleId").val()+"&type="+$("#type").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "过滤学员", //
		 offset : [ '10%' ],
	     area: ['500px','60%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
   });
   return false;
}
