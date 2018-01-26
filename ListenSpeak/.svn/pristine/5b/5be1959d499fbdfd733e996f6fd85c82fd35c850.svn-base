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
	<form class="layui-form" action="">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in" id="collapseOne">
    		<label class="layui-form-label" style="width:150px;">KEY</label>
    		<div class="layui-input-inline">
      			<input type="text" id="key" name="key" autocomplete="off" class="layui-input">
    		</div>
    		<label class="layui-form-label" style="width:150px;">名称</label>
    		<div class="layui-input-inline">
      			<input type="text" id="name" name="name" autocomplete="off" class="layui-input">
    		</div>
			<button  type="button" id="searchBtn"
			class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		</div>
  		</fieldset>
  		</form>
  		
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
             <div id="toolbar"
				style="width: 98%; margin: 0 auto; margin-bottom: 10px;">
				<button onClick="return add()" type="button" class="layui-btn">
					<li class="fa fa-plus-square"></li> &nbsp;新增班级
				</button>
			</div>
            <table class="tableList" id="dicTable" lay-filter="dic">
			</table>
        </div>
    </div>
</body>
<script type="text/html" id="StatusTpl">
 			{{ d.status == '1' ? '正常' : (d.status == '2' ? '禁用' : '无效') }}
		</script>

<script type="text/html" id="paperBar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script type="text/javascript">
	layui.use([ 'table', 'laypage' ], function() {
		var table = layui.table, laypage = layui.laypage;
		var tableIns = table.render({
			elem : '#dicTable',
			url : jsBasePath + '/basic/dic/query.html',
			page : {
				layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
				groups : 3, //只显示 1 个连续页码
				first : false, //不显示首页
				last : false
			},
			cols : [ [ {
				field : 'id',
				width : "20%",
				title : 'ID',
				align : "center"
			}, {
				field : 'key',
				width : "20%",
				title : '字典项KEY',
				align : "center"
			}, {
				field : 'name',
				width : "20%",
				title : '字典项名称',
				align : "center"
			},{
				field : 'status',
				width : "20%",
				title : '状态',
				align : "center",
				templet : "#StatusTpl"
			}, {
				fixed : 'right',
				width : "20%",
				title : '操作',
				align : 'center',
				toolbar : '#paperBar'
			} ] ]
		});

		table.on('tool(dic)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				edit(data.id);
			} 
		});
		$('#searchBtn').bind('click', function() {
			reloadTable();
		});

		function reloadTable() {
			tableIns.reload({
				where : {
					key : $("#key").val(),
	    			name:$("#name").val()
				}
			});
		}

	    
	    /**
	     * 编辑字典数据
	     */
	    function edit(id){
	    	 var url = jsBasePath+"/basic/dic/toEdit.html?id="+id;
	    	 layer.open({
	    		 type: 2,
	    		 shade : [ 0.5, '#000' ],    		 offset : [ '5%' ],
	    		 area: ['60%','80%'],
	    		 title: "编辑数据", //不显示标题
	    		 content:url, //捕获的元素
	    		 cancel: function(index){
	    			 layer.close(index);
	    		 },
	    		 end:function(){}
	      	 });
	    }

	});
	 /**
     * 新增字典
     */
    function add(){
    	 var url = jsBasePath+"/basic/dic/toAdd.html";
    	 layer.open({
    		 type: 2,
    		 shade : [ 0.5, '#000' ],
    		 offset : [ '10%' ],
    		 area: ['480px','400px'],
    		 title: "新增字典", //不显示标题
    		 content:url, //捕获的元素
    		 cancel: function(index){
    			 layer.close(index);
    		 },
    		 end:function(){}
      	 });
    	 return false;
    }
</script>
</html>