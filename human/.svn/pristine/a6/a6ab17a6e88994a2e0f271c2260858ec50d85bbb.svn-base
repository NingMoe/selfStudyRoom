var rankinfo_id = getQueryString("id");

$(function() {
	initTable();
	btnclick();
});

function btnclick(){
	$("#sr").click(function() {
		initTable();
	});

	$("#back_btn").bind("click", function() {
		window.location.href = jsBasePath + '/teacher/rankinfo/infoview.html';
	});
}

function initTable() {
	// 初始化Table 不
	$('#processDefTable').bootstrapTable('destroy');
	$("#processDefTable").bootstrapTable(
					{
						url : jsBasePath + '/teacher/rankclasses/query.html', // 请求后台的URL（*）
						// method: 'get',//请求方式（*）
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
						// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
						uniqueId : "id", // 每一行的唯一标识，一般为主键列
						showToggle : false, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						smartDisplay : true, // 智能显示 pagination 和 cardview 等
						toolbar : '#toolbar', // 工具按钮用哪个容器
						toolbarAlign : 'left',
						columns : [
								{
									checkbox : true,
				    				fieId : 'id'
								},
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
									field : 'teacher_name',
									title : '教师名称',
									align : 'center',
								},
								{
									field : 'act_status',
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										return "<button  class='layui-btn layui-btn-mini' onclick='return changeview(\""
												+ row.id
												+ "\");'><i class='fa fa-check'></i>&nbsp;编辑</button >&nbsp;"
												+ "<button  class='layui-btn layui-btn-mini' onclick='return remove(\""
												+ row.id
												+ "\");'><i class='fa fa-check'></i>&nbsp;删除</button >&nbsp;";
									}
								} ],
						onLoadSuccess : function(dataAll) {

						},
						onLoadError : function() {
							// mif.showErrorMessageBox("数据加载失败！");
						}
					});
};

function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		rankinfo_id : rankinfo_id,
		class_code : $.trim($("#class_code").val()),
		class_name : $.trim($("#class_name").val()),
		teacher_name : $.trim($("#teacher_name").val())
	};
}

//新增页面
function addview(){
	layer.open({
	    type: 2,
	    title: '新增班级信息',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
		area: ['500px', '400px'],
		content: jsBasePath+'/teacher/rankclasses/addview.html?rankinfo_id='+rankinfo_id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//上传excel
function uploadexcel(){
	layer.open({
		type: 2,
		title: '批量导入',
		shadeClose: false,
		shade: 0.8,
		offset : ['20%'],
		area: ['330px', '220px'],
		content: jsBasePath+'/teacher/rankclasses/uploadview.html?rankinfo_id='+rankinfo_id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//下载excel
function downloadexcel(){
	window.location.href = jsBasePath+'/teacher/rankclasses/download.html?rankinfo_id='+rankinfo_id;
}

//选择删除
function removeselect(){
	var ids = getSelectId("processDefTable");
	if(ids == ""){
		layer.msg("请先选择要删除的班级");
		return false;
	}
	layer.confirm("确认要删除选中的班级?", {
		btn: ['是','否'] ,//按钮
		offset: '10%',
		btnAlign:'c'
		}, function(index){
			$.ajax({
				url : jsBasePath + "/teacher/rankclasses/deleteselect.html",
				type : "POST",
				dataType : "json",
				data : {
					ids : ids
				},
				success : function(data){
					layer.msg(data.message);
					if(data.flag){
						$("#processDefTable").bootstrapTable('refresh');
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

//全部删除
function removeall(){
	layer.confirm("确认要删除全部班级?", {
		btn: ['是','否'] ,//按钮
		offset: '10%',
		btnAlign:'c'
		}, function(index){
			$.ajax({
				url : jsBasePath + "/teacher/rankclasses/deleteall.html",
				type : "POST",
				dataType : "json",
				data : {
					rankinfo_id : rankinfo_id
				},
				success : function(data){
					layer.msg(data.message);
					if(data.flag){
						$("#processDefTable").bootstrapTable('refresh');
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

//编辑页面
function changeview(id){
	layer.open({
	    type: 2,
	    title: '修改班级信息',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
		area: ['500px', '400px'],
		content: jsBasePath+'/teacher/rankclasses/changeview.html?id='+id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//删除
function remove(id){
	layer.confirm("确认要删除该班级?", {
		btn: ['是','否'] ,//按钮
		offset: '10%',
		btnAlign:'c'
		}, function(index){
			$.ajax({
				url : jsBasePath + "/teacher/rankclasses/delete.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id
				},
				success : function(data){
					layer.msg(data.message);
					if(data.flag){
						$("#processDefTable").bootstrapTable('refresh');
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

// JavaScript Document
function getQueryString(key) {
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	var result = window.location.search.substr(1).match(reg);
	return result ? decodeURIComponent(result[2]) : null;
}