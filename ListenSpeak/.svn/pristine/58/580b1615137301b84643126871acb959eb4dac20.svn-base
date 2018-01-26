<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%@include file="/WEB-INF/view/common/bootstrapTable.jsp"%>
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/themes/default/default.css" />
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css" />
</head>
<style type="text/css">
table {
	table-layout: fixed;
}

table tr td {
	text-overflow: ellipsis; /* for IE */
	-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
	overflow: hidden;
	white-space: nowrap;
}
.fixed-table-pagination{
position: absolute !important;
bottom: -35px;
</style>
</head>
<body>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			>
			<input type="hidden" id="id" value="${lct.id}"> <input
				type="hidden" id="paperId" value="${lct.paperId}">
			<legend 
				href="#collapseOne" style="color: #1AA094;">
				已配置试卷&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<div class="y-role">
				<div id="toolbar"></div>
				<table class="tableList2" id="ccrTable2">

				</table>
			</div>
		</fieldset>
	</div>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				试卷预览&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" id="collapseOne">
					<label class="layui-form-label" style="width: 10%;">试卷类型</label>
					<div class="layui-input-inline" style="width: 10%;">
						<select name="sourceType" id="sourceType">
							<option value="">请选择</option>
							<c:forEach var="sType" items="${sourceType }">
								<option value="${sType.dataValue }">${sType.name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="layui-form-label" style="width: 10%;">年份</label>
					<div class="layui-input-inline" style="width: 10%;">
						<select name="year" id="year">
							<option value="">请选择</option>
							<c:forEach var="ye" items="${year }">
								<option value="${ye.name }">${ye.name }</option>
							</c:forEach>
						</select>
					</div>
					<button onClick="initTable()" type="button" class="layui-btn">
						<li class="fa fa-search"></li>&nbsp;搜索
					</button>
				</div>
			</form>
			<div class="y-role">
				<div id="toolbar"></div>
				<table class="tableList" id="ccrTable">

				</table>
			</div>
		</fieldset>
	</div>
</body>
<script type="text/javascript">
	layui.use('form', function() {
		var form = layui.form;
		initTable();
		initTable2();
		//监听省份选择事件
		form.on('select(provinceId)', function(data) {
			$("#city").empty();
			var index = layer.load(3, {
				shade : [ 0.3 ]
			});
			$.post(jsBasePath + "/basic/areaInfo/getArea.html", {
				areaLevel : 2,
				parentAreaCode : data.value
			}, function(data, status) {
				layer.close(index);
				var proHtml = "<option value=''>请选择</option>";
				$.each(data, function(i, org) {
					proHtml += "<option value='" + org.id +"'>" + org.areaName
							+ "</option>";
				})
				$("#city").html(proHtml);
				form.render();
			}, "json");
			return false;
		});

	});

	function initTable() {
		var columns = [
				{
					field : 'name',
					title : '试卷名称',
					align : 'center',
				},
				{
					field : 'createTime',
					title : '创建时间',
					align : 'center',
				},
				{
					field : 'useTimes',
					title : '使用次数',
					align : 'center',
				},
				{
					field : 'testNumber',
					title : '题量',
					align : 'center',
				},
				{
					field : 'createName',
					title : '创建人',
					align : 'center',
				},
				{
					field : '',
					title : '操作',
					align : 'center',
					width : "30%",
					// 						width:"20%",
					formatter : function(value, row, index) {
						var re = "";
						re += "<button  class='layui-btn layui-btn-mini' onclick='return  usePaper(\""
								+ row.id
								+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;使用试卷</button>";
						re += "<button  class='layui-btn layui-btn-mini'  onclick='return paperView(\""
								+ row.id
								+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;预览试卷</button>";
						return re;
					}
				} ];
		//初始化Table 不 
		$('#ccrTable').bootstrapTable('destroy');
		$("#ccrTable").bootstrapTable({
			url : jsBasePath + '/lstBasePaper/query.html', //请求后台的URL（*）
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
			showExport : true,
			showType : "all",
			fileName : "测试管理",
			columns : columns,
			onLoadSuccess : function(dataAll) {
			},
			onLoadError : function() {
				//mif.showErrorMessageBox("数据加载失败！");
			}
		});
	}
	
	//已新增数据
	function initTable2() {
		var columns = [
				{
					field : 'name',
					title : '试卷名称',
					align : 'center',
				},
				{
					field : 'createTime',
					title : '创建时间',
					align : 'center',
				},
				{
					field : 'useTimes',
					title : '使用次数',
					align : 'center',
				},
				{
					field : 'testNumber',
					title : '题量',
					align : 'center',
				},
				{
					field : 'createName',
					title : '创建人',
					align : 'center',
				},
				{
					field : '',
					title : '操作',
					align : 'center',
					width : "30%",
					// 						width:"20%",
					formatter : function(value, row, index) {
						var re = "";
						re += "<button  class='layui-btn layui-btn-mini'  onclick='return paperView(\""
								+ row.id
								+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;预览试卷</button>";
						re += "<button  class='layui-btn layui-btn-mini' onclick='return del(\""
								+ row.id
								+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除</button>";
						return re;
					}
				} ];
		//初始化Table 不 
		$('#ccrTable2').bootstrapTable('destroy');
		$("#ccrTable2").bootstrapTable({
			url : jsBasePath + '/lstBasePaper/query.html', //请求后台的URL（*）
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
			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			smartDisplay : true, // 智能显示 pagination 和 cardview 等
			toolbar : '#toolbar', //工具按钮用哪个容器
			toolbarAlign : 'left',
			showExport : true,
			showType : "all",
			fileName : "测试管理",
			columns : columns,
			onLoadSuccess : function(dataAll) {
			},
			onLoadError : function() {
				//mif.showErrorMessageBox("数据加载失败！");
			}
		});
	}
	//传递的参数
	function queryParams(params) {
		var paperId = $.trim($("#paperId").val());
		if (paperId == "" || paperId == null) {
			paperId = 0;
		}
		var isConfig="2";
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			year : $.trim($("#year").val()),
			sourceType : $.trim($("#sourceType").val()),
			
			paperId : paperId,
			isConfig:isConfig
		};
	}
	
	//传递的参数
	function queryParams2(params) {
		var paperId = $.trim($("#paperId").val());
		if (paperId == "" || paperId == null) {
			paperId = 0;
		}
		var isConfig="1";
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			paperId : paperId,
			isConfig:isConfig,
		};
	}
	// 		试卷预览
	function paperView(id) {
		var page = 0;
		var url = jsBasePath + "/lstQuestion/index.html?id=" + id + "&page="
				+ page;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "预览试卷", //
			offset : [ '4%' ],
			area : [ '1000px', '80%' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}

	//使用试卷
	function usePaper(id) {
		var testId = $("#id").val();
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$.post(jsBasePath + "/lstClassTest/paperConfig.html", {
			"paperId" : id,
			"testId" : testId
		}, function(data, status) {
			layer.close(index);
			if (data != null) {
				layer.msg(data.message);
				if (data.flag == true) {
					$("#paperId").val(data.paperId)					
					$("#ccrTable").bootstrapTable('refresh');
					$("#ccrTable2").bootstrapTable('refresh');
				}
			}
		}, "json");
	}
	//删除
	function del(id){
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		var testId = $("#id").val();
		$.post(jsBasePath + "/lstClassTest/deletePaper.html", {
			"paperId" : id,
			"testId" : testId
		}, function(data, status) {
			layer.close(index);
			if (data != null) {
				layer.msg(data.message);
				if (data.flag == true) {
					$("#paperId").val(data.paperId)	;				
					$("#ccrTable").bootstrapTable('refresh');
					$("#ccrTable2").bootstrapTable('refresh');
				}
			}
		}, "json");
	}
</script>
</html>