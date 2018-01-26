layui.use(['form'], function(){
    var form = layui.form();
    initTable();
});
function initTable() {
	//初始化Table 不 
	$('#libraryBuyTable').bootstrapTable('destroy');
	$("#libraryBuyTable").bootstrapTable({
		url : jsBasePath + '/teacher/libraryFeedBack/query.html', //请求后台的URL（*）
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
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
		columns : [{
			field : 'companyName',
			title : '学校名称',
			align : 'center'
		},{
			field : 'deptName',
			title : '部门名称',
			align : 'center'
		},{
			field : 'name',
			title : '反馈人姓名',
			align : 'center'
		},{
			field : 'bookName',
			title : '书籍名称',
			align : 'center'
		},{
			field : 'bookAuthor',
			title : '书籍作者',
			align : 'center'
		},{
			field : 'reason',
			title : '推荐理由',
			align : 'center'
		}, {
			field : 'createTime',
			title : '反馈时间',
			align : 'center'
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
		bookName : $.trim($("#bookName").val()),
		bookAuthor :$.trim($("#bookAuthor").val()),
		name : $.trim($("#name").val())
	};
}
//导出
function exportAll(id){
	window.location.href = jsBasePath+"/teacher/libraryFeedBack/exportAll.html";
}