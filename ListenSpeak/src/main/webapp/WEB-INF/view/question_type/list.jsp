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
</head>
<body>
	<div class="main-wrap">
		<div class="y-role">
			<!--工具栏-->
			<div id="toolbar">
					<button id="addBtn" type="button" class="layui-btn">
						<li class="fa fa-plus-square"></li> &nbsp;新增题型
					</button>
			</div>
			<!--/工具栏-->
			<!--文字列表-->
			<table class="tableList" id="questionTypeTable" lay-filter="qtype" style="width: 100%"></table>
		</div>
	</div>
</body>
<script type="text/html" id="kmTpl">
 	{{ d.status == '1' ? '英语' : '' }}
</script>
    
<script type="text/html" id="typebar">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>

<script type="text/javascript">
layui.use(['table','laypage'], function(){
	var table = layui.table,laypage = layui.laypage;
	var tableIns = table.render({
		elem: '#questionTypeTable',
		url : jsBasePath + '/questionType/query.html', //请求后台的URL（*）
		page: {
			first: false,
			last: false,
			curr: location.hash.replace('#!fenye=', ''),
			hash: 'fenye'
		},
		cols: [[
				{type:'numbers',title: '序号',width:"6%"},
		        {field:'name', width:"20%", title: '题型名称',align:"center"},
		        {field:'subject', width:"20%", title: '科目',align:"center",templet:"#kmTpl"},
		        {field:'remark', width:"20%", title: '题型描述',align:"center"},
		        {width:"34%", title: '操作',align:"center", toolbar: '#typebar'}
		]]
	});
	
	table.on('tool(qtype)', function(obj){
		var data = obj.data;
		if(obj.event === 'edit'){
			toEdit(data.id);
		}
	});
	
	$("#addBtn").bind("click",function(){
		toAdd();
	});
	
	function reloadTable(){
		tableIns.reload({
			where: {
				
			}
		});
	}
	
	function toAdd() {
		var url = jsBasePath + "/questionType/toAdd.html";
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "新增题型", //
			offset : ['4%'],
			area : ['844px','600px'],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				reloadTable();
			}
		});
	}
	
	//编辑
	function toEdit(id) {
		var url = jsBasePath + "/questionType/toEdit.html?id="+id;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "编辑题型", //
			offset : [ '4%' ],
			area : [  '844px','600px'],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				reloadTable();
			}
		});
	}
});
</script>
</html>