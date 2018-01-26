
//初始化
$(function() {
	initTable();
});

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable({
		url : jsBasePath + '/ielts/manager/querybook.html', // 请求后台的URL（*）
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
				 field : 'id',
				 title : 'ID',
				 align : 'center'
			 },
			 {
				 field : 'book_name',
				 title : '书籍名称',
				 align : 'center'
			 },
			 {
				 field : '',
				 title : '操作',
				 align : 'center',
				 formatter : function(value, row, index) {
					 return "<sec:authorize ifAnyGranted='ROLE_act_isagree'>&nbsp;</sec:authorize>" +
					 	"<button  class='layui-btn layui-btn-mini' onclick='return bookchange(\"" + row.id	 + "\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;修改</button >" +
					 	"&nbsp;<button  class='layui-btn layui-btn-mini' onclick='return delbook(\"" + row.id + "\");'>" +
					 	"<i class='fa fa-check'></i>&nbsp;删除</button >";
						 //layui-btn-danger
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
		pageNow : params.offset / params.limit + 1
	};
}

//打开新增学员信息页
function addbook() {
	layer.open({
		type : 2,
		title : '新增书籍',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '45%' ],
		content : jsBasePath + '/ielts/manager/addbookview.html',
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改学员信息页
function bookchange(id) {
	layer.open({
		type : 2,
		title : '修改书籍',
		shadeClose : false,
		shade : 0.8,
		offset : [ '10%' ],
		area : [ '40%', '45%' ],
		content : jsBasePath + '/ielts/manager/changebookview.html?id='+id,
		end : function() {
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

/**
 * 删除
 */
function delbook(id){
	layer.confirm("确认删除选中的行？确认后删除所有学员关联该书籍。", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
	}, function(index){
		layer.close(index);
		$.ajax({
			url : jsBasePath + "/ielts/manager/deletebook.html",
			type : "POST",
			dataType : "json",
			data : {
				id : id
			},
			success : function(date){
				layer.alert(date.message);
				if(date.flag){
					initTable();
				}
			},
			error : function(date){
				alert("网络出错，请重新发送。");
			}
		});
	}, function(index){
		layer.close(index);
	});
}

layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
});