layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;
    initTable();
    initTable2();
});
function initTable() {
	//初始化Table 不 
	$('#ccrTable').bootstrapTable('destroy');
	$("#ccrTable").bootstrapTable({
		url : jsBasePath + '/continued_class/matchData/queryClassDetails.html', //请求后台的URL（*）
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
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [
		      [
				{
				     title: "配课明细表",				
				     halign: 'center',
				     align: 'center',
				     colspan: 16
				   }
		       ], 
		       [
				 {
					field : '',
					title : '生成时间',
					halign : 'center',
					colspan: 3,
					rowspan: 1
				},{
					field : '',
					title : '在读班级',
					halign : 'center',
					colspan: 6,
					rowspan: 1
				},{
					field : '',
					title : '续班班级',
					halign : 'center',
					colspan: 6,
					rowspan: 1
				},{
					field : '',
					title : '操作',
					halign : 'center',
					colspan: 1,
					rowspan: 1
				}
		       ],
		       [
				{
			      checkbox : true,
			      fieId : 'id',
		 		  align : 'center'
		         },{
		 			field : '',
		 			title : '序号',
		 			align : 'center',
		 			formatter : function(value, row, index) {
		 				var page = $("#ccrTable").bootstrapTable("getPage");
		 				return page.pageSize * (page.pageNumber - 1) + index + 1;
		 			}
		 		},{
					field : 'createTime',
					title : '生成时间',
					align : 'center'
				  },{
						field : 'oClassCode',
						title : '班号',
						align : 'center'
					},{
						field : 'oClassName',
						title : '班级名称',
						align : 'center'
					},{
						field : 'oSchoolAreaName',
						title : '校区',
						align : 'center'
					}, {
						field : 'oGrade',
						title : '年级',
						align : 'center'
					}, {
						field : 'oSubject',
						title : '科目',
						align : 'center'
					},{
						field : 'oTeacherName',
						title : '教师',
						align : 'center'
					} ,{
						field : 'cClassCode',
						title : '班号',
						align : 'center'
					},{
						field : 'cClassName',
						title : '班级名称',
						align : 'center'
					},{
						field : 'cSchoolAreaName',
						title : '校区',
						align : 'center'
					}, {
						field : 'cGrade',
						title : '年级',
						align : 'center'
					}, {
						field : 'cSubject',
						title : '科目',
						align : 'center'
					},{
						field : 'cTeacherName',
						title : '教师',
						align : 'center'
					},{
						field : '',
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var re="";
								re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return deleteByIds(\"" 
									+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
							return re;
						}
					}   		        
		        ]          
	     ],
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
		name : $.trim($("#name").val()),
		code : $.trim($("#code").val()),
		oClassCode : $.trim($("#oClassCode").val()),
		oClassName : $.trim($("#oClassName").val()),
		oSchoolAreaName: $.trim($("#oSchoolAreaName").val()),
		oGrade: $.trim($("#oGrade").val()),
		oTeacherName : $.trim($("#oTeacherName").val()),
		oSubject :  $.trim($("#oSubject").val())
	};
}
//添加配课
function add(){
	 var url = jsBasePath+"/continued_class/matchData/toAdd.html?ruleId="+$("#ruleId").val()+"&code="+$("#code").val()+"&name="+$("#name").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增学员配课数据", //
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
function deleteByIds(id){
	layer.confirm("确定删除该条续班匹配数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/matchData/delete.html",{deleteIds:id},function(data,status){
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
	deleteByIds(ids);
}

function initTable2() {
	//初始化Table 不 
	$('#rcTable').bootstrapTable('destroy');
	$("#rcTable").bootstrapTable({
		url : jsBasePath + '/continued_class/matchData/queryRecommendClass.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		//sortOrder : "asc", //排序方式
		queryParams : queryParams2, //传递参数（*）
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
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar2', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [
		      [
				{
				     title: "推荐明细表",				
				     halign: 'center',
				     align: 'center',
				     colspan: 10
				   }
		       ],
		       [
				{
			      checkbox : true,
			      fieId : 'id',
		 		  align : 'center'
		         },{
		 			field : '',
		 			title : '序号',
		 			align : 'center',
		 			formatter : function(value, row, index) {
		 				var page = $("#rcTable").bootstrapTable("getPage");
		 				return page.pageSize * (page.pageNumber - 1) + index + 1;
		 			}
		 		},{
					field : 'createTime',
					title : '生成时间',
					align : 'center'
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
					} ,{
						field : '',
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var re="";
								re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return deleteRcByIds(\"" 
									+ row.id + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
							return re;
						}
					}   		        
		        ]          
	     ],
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
function queryParams2(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		ruleId : $.trim($("#ruleId").val()),
		name : $.trim($("#name").val()),
		code : $.trim($("#code").val()),
		classCode : $.trim($("#classCode").val()),
		className : $.trim($("#className").val()),
		schoolAreaName: $.trim($("#schoolAreaName").val()),
		grade: $.trim($("#grade").val()),
		teacherName : $.trim($("#teacherName").val()),
		subject :  $.trim($("#subject").val())
	};
}
//添加推荐班级
function addRecommendClass(){
	 var url = jsBasePath+"/continued_class/matchData/toAddRc.html?ruleId="+$("#ruleId").val()+"&code="+$("#code").val()+"&name="+$("#name").val();
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "新增学员推荐班级数据", //
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
function deleteRcByIds(id){
	layer.confirm("确定删除该推荐班级数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/continued_class/matchData/deleteRc.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#rcTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
}
//批量删除
function bath_delRecommendClass(){
	var ids=getSelectId("rcTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	deleteRcByIds(ids);
}


