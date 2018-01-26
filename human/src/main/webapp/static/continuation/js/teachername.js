
//初始化
$(function() {
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/teacher/continuationteachername/query.html', // 请求后台的URL（*）
		// method: 'get', //请求方式（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", // 必须的,post
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		// sortOrder : "asc", //排序方式
		queryParams : queryParams, // 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : true, // 是否显示所有的列
		showRefresh : false, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : false, // 是否启用点击选中行
		// height: 170, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', // 工具按钮用哪个容器
		toolbarAlign : 'left',
		columns :
			[
			 {
				 field : 'class_code',
				 title : '班号',
				 align : 'center'
			 },
			 {
				 field : 'class_name',
				 title : '班级名称',
				 align : 'center'
			 },
			 {
				 field : 'all_teacher_name',
				 title : '系统中教师名称',
				 align : 'center'
			 },
			 {
				 field : 'teacher_name_list',
				 title : '手动填写教师名称',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var html = "";
					 $.each(value, function(index, teacher_name){
						 html += "/" + teacher_name;
					 });
					 if(html != ""){
						 html = html.substring(1);
					 }
					 return html;
				 }
			 },
			 {
				 field : '',
				 title : '操作',
				 align : 'center',
				 formatter : function(value, row, index) {
					 var html = "<button  class='layui-btn layui-btn-mini' onclick='return continueconfigchange(\"" + row.class_code	 + "\");'>" 
					 			+"<i class='fa fa-check'></i>&nbsp;修改</button >";
					 return html;
				 }
			 } ],
			 onLoadSuccess : function(dataAll) {

			 },
			 onLoadError : function() {
				 // mif.showErrorMessageBox("数据加载失败！");
			 }
	});
};

//获取参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		class_code : $.trim($("#class_code").val()),
		class_name : $.trim($("#class_name").val()),
		teacher_name : $.trim($("#teacher_name").val()),
		is_add : $("#is_add").val()
	};
}

//打开新增页
function addcontinueteachername() {
	layer.open({
		type : 2,
		title : '新增班级教师',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '50%', '85%' ],
		content : jsBasePath + '/teacher/continuationteachername/addview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

// 打开修改页
function continueconfigchange(class_code) {
	layer.open({
		type : 2,
		title : '修改班级教师',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '800px', '90%' ],
		content : jsBasePath + '/teacher/continuationteachername/changeview.html?class_code=' + class_code,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});