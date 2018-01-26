layui.use(['form','laydate'], function(){
    var form = layui.form();
    var laydate = layui.laydate;    
    initTable();
});

function initTable() {
			//初始化Table
	        $('#managerTaskTable').bootstrapTable('destroy');
			$("#managerTaskTable").bootstrapTable({
				url : jsBasePath + '/manager/trigger/query.html', //请求后台的URL（*）
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
				uniqueId : "jobId", //每一行的唯一标识，一般为主键列
				showToggle : false, //是否显示详细视图和列表视图的切换按钮
				cardView : false, //是否显示详细视图
				detailView : false, //是否显示父子表
				smartDisplay : true, // 智能显示 pagination 和 cardview 等
				toolbar : '#toolbar', //工具按钮用哪个容器
				toolbarAlign : 'left',
				columns : [{
					field : 'jobGroup',
					title : '任务分组',
					align : 'left',
					halign : "center"
				}, {
					field : 'jobName',
					title : '任务名称',
					align : 'center',
				}, {
					field : 'jobStatus',
					align : 'center',
					halign : "center",
					title : '任务状态',
					formatter : function(value, row, index) {
						if (value == "NONE") {
							return "未知";
						}
						if (value == "NORMAL") {
							return "正常";
						}
						if (value == "PAUSED") {
							return "暂停";
						}
						if (value == "COMPLETE") {
							return "";
						}
						if (value == "ERROR") {
							return "错误";
						}
						if (value == "BLOCKED") {
							return "锁定";
						}
					}
				}, {
					field : 'isConcurrent',
					align : 'center',
					halign : "center",
					title : '是否并发',
					formatter : function(value, row, index) {
						if (value == "1") {
							return "是";
						}
						if (value == "0") {
							return "否";
						}
					}
				}, {
					field : 'cronExpression',
					align : 'center',
					title : '运行表达式'
				}, {
					field : 'description',
					align : 'center',
					title : '任务描述'
				}, {
					field : 'targetObject',
					align : 'center',
					title : 'bean名'
				}, {
					field : 'targetMethod',
					align : 'center',
					title : '方法名'
				}, {
					field : 'startTime',
					align : 'center',
					title : '创建时间',
					width:'120'
				}, {
					field : 'previousTime',
					align : 'center',
					title : '上次运行'
				}, {
					field : 'nextTime',
					align : 'center',
					title : '下次运行'
				}, {
					field : '',
					align : 'center',
					title : '操作',
					switchable : false,
					formatter : function(value, row, index) {
						var str = "<a onclick='return testJob(\"" + row.jobName + "\",\"" + row.jobGroup + "\");'>触发</a>&nbsp;"
						if (row.jobStatus == 'PAUSED') {
							str = str + "<a onclick='return resumeJob(\"" + row.jobName + "\",\"" + row.jobGroup + "\");'>恢复</a>&nbsp;"
						}
						if (row.jobStatus == "NORMAL") {
							str = str + "<a onclick='return pauseJob(\"" + row.jobName + "\",\"" + row.jobGroup + "\");'>暂停</a>&nbsp;"
						}
						str = str + "<a onclick='return updateCronExpression(\"" + row.triggerName + "\",\"" + row.triggerGroup + "\",\"" + row.cronExpression + "\");'>更新表达式</a>&nbsp;"
							+ "&nbsp;<a href='javascript:void(0);' onclick='deleteTask(\""+row.jobId + "\")'>删除</a>";
						return str;
					}
				}],
				onLoadSuccess : function(dataAll) {},
				onLoadError : function() {
				}
			});
	}
//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1
	};
}
//查询或刷新
function query(){
	$('#managerTaskTable').bootstrapTable('refresh');
}
//暂停定时任务
function pauseJob(jobName, jobGroup) {
	$.post(jsBasePath +"/manager/trigger/pauseJob.html", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (data) {
			layer.msg("任务暂停成功!");
			query();
		}
		else {
			layer.msg("任务暂停失败，请稍后再试!");
		}
	}, "json");		
}
//恢复暂停任务
function resumeJob(jobName, jobGroup) {
	$.post(jsBasePath +"/manager/trigger/resumeJob.html", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (data) {
			layer.msg("任务恢复成功!");
			query();
		}
		else {
			layer.msg("任务恢复失败，请稍后再试!");
		}
	}, "json");			
}
	
//立即触发任务
function testJob(jobName, jobGroup) {
	$.post(jsBasePath +"/manager/trigger/testJob.html", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (data) {
			layer.msg("任务触发成功!");
			query();
		}else {
			layer.msg("任务触发失败，请稍后再试!");
		}
	}, "json");			
}
	
//更新表达式
function updateCronExpression(triggerName, triggerGroup, cron) {
	layer.prompt({
		formType : 0,
		value : cron,
		title : '更新表达式'
	}, function(val) {
		$.post(jsBasePath +"/manager/trigger/updateCronExpression.html",
			{
			triggerName : triggerName,
			triggerGroup : triggerGroup,
			cronExpression : val
			}, function(data, status) {
				if (!data) {
					layer.msg("更新失败，请检查表达式或稍后再试！");
				}
				else {
					layer.msg("更新成功！");
					query();
				}
			}, "json");
	});		
}
		
//新增定时任务		
function jobAdd(){
	 layer.open({
		 type: 2,
		 shade : [ 0.5, '#000' ],
		 title : '新增任务',
		 offset : [ '10%' ],
		 area: ['580px','500px'],
		 content:jsBasePath + '/manager/trigger/addJobView.html', 
		 cancel: function(index){
			 layer.close(index);
		 },
		 end:function(){
			 
		 }
	 });
	 return false;
}
	
//删除任务
function deleteTask(uniqId){
	layer.confirm('任务删除后将不能恢复，是否确认删除?', function(index){
		var row = $("#managerTaskTable").bootstrapTable('getRowByUniqueId', uniqId);
		$.post(jsBasePath +"/manager/trigger/deleteJob.html", {
			jobName : row.jobName,
			jobGroup : row.jobGroup
		}, function(data, status) {
			if (data) {
				layer.msg("任务删除成功!");
				query();
			}
			else {
				layer.msg("任务删除失败，请稍后再试!");
			}
		}, "json");
		  
		  layer.close(index);
	});		
}	
