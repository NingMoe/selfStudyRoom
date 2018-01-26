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
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				试卷预览&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" id="collapseOne">
				<label class="layui-form-label" style="width:10%;">试卷类型</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="sourceType" id="sourceType" >
					        <option value="">请选择</option>
	    					<c:forEach var="sType" items="${sourceType }">
		       	              <option value="${sType.dataValue }">${sType.name }</option>
		                    </c:forEach>
	    				</select>
				</div>
				<label class="layui-form-label" style="width:10%;">年份</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="year" id="year" >
					        <option value="">请选择</option>
	    					<c:forEach var="ye" items="${year }">
		       	              <option value="${ye.name }">${ye.name }</option>
		                    </c:forEach>
	    				</select>
				</div>
				<label class="layui-form-label" style="width:10%;">状态</label>
				<div class="layui-input-inline" style="width:10%;">
					   <select name="status" id="status" >
					        <option value="0">请选择</option>
		       	            <option value="1">未发布</option>
		       	            <option value="2">已发布</option>
	    				</select>
				</div>
				<button id="searchBtn" type="button"
			class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
				</div>
			</form>
		</fieldset>
		<div class="y-role">
			<div id="toolbar">
			<!--文字列表-->
			<button onClick="return enterPaper()" type="button" class="layui-btn"> 
						<li class="fa fa-plus-square"></li> &nbsp;新增试卷
			</button>
<!-- 			<button onClick="return bath_del()" type="button" class="layui-btn layui-btn-danger">  -->
<!-- 						<li class="fa fa-remove"></li> &nbsp;批量删除 -->
<!-- 			</button> -->
			</div>
			<table class="tableList" id="ccrTable" lay-filter="paper" style="width: 99%">
			
			</table>
		</div>
	</div>
</body>
<script type="text/html" id="paperStatusTpl">
 			{{ d.status == '1' ? '未发布' : (d.status == '2' ? '已发布' : '无效') }}
		</script>

		<script type="text/html" id="paperBar" >
  			{{#  if(d.status == '1'){ }}
			<a class="layui-btn layui-btn-xs" lay-event="toInsertTest">录入题目</a>
  			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    		<a class="layui-btn layui-btn-xs" lay-event="introduced">发布</a>
			<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
  			{{#  } }}
			{{#  if(d.status == '2'){ }}
			<a class="layui-btn layui-btn-xs" lay-event="paperView">预览</a>
			{{#  } }}
			
		</script>
<script type="text/javascript">
	layui.use([ 'table', 'laypage' ], function() {
		var table = layui.table, laypage = layui.laypage;
		var status=$("#status").val();
		var tableIns = table.render({
			elem : '#ccrTable',
			url : jsBasePath + '/lstBasePaper/query.html',
			where :{"status":status},
			page : {
				layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
				groups : 3, //只显示 1 个连续页码
				first : false, //不显示首页
				last : false
			},
			cols: [[
				    {field:'name', width:"25%", title: '试卷名称',align:"center"},
				    {field:'createTime', width:"24%", title: '创建时间',align:"center"},
				    {field:'useTimes', width:"8%", title: '使用次数',align:"center"},
				    {field:'testNumber', width:"8%", title: '题量',align:"center"},
				    {field:'status',width:"10%", title: '状态',align:"center",templet:"#paperStatusTpl"},
				    { width:"25%", title: '操作', align:'center', toolbar: '#paperBar'} ]]
		});

		table.on('tool(paper)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'edit'){
		    	edit(data.id);
		    } else if(obj.event === 'del'){
		    	del(data.id);
		    } else if(obj.event === 'toInsertTest'){
		    	toInsertTest(data.id);
		    }else if(obj.event=='paperView'){
		    	paperView(data.id);
		    }else if(obj.event='introduced'){
		    	introduced(data.id);
		    }
		 });
			$('#searchBtn').bind('click', function(){
				  reloadTable();
			  });
		  
		  function reloadTable(){
			  var year = $("#year");
			  var status = $("#status");
			  var sourceType = $("#sourceType");
			  tableIns.reload({
				where: {
					year :  year.val(),
					sourceType:sourceType.val(),
					status:status.val()
				}
			  });
		  }
		  
	
//		试卷预览
	function paperView(id) {
		var page=0;
		var url = jsBasePath + "/lstQuestion/index.html?id="+id+"&page="+page;
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
	
	//删除
	function del(id){
		layer.confirm("确定删除该条数据么?删除后不可恢复!", {
			  btn: ['是','否'] ,//按钮
			  offset: '10%',
			  btnAlign:'c'
			}, function(index){
				$.post(jsBasePath+"/lstBasePaper/delete.html",{ids:id},function(data,status){
					layer.close(index);
					if(data!=null){
						layer.msg(data.message);
							reloadTable();
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
	
	function  edit(id){
		var url = jsBasePath + "/lstBasePaper/toEdit.html?id="+id;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "录入试题", //
			offset : [ '4%' ],
			area : [ '682px', '500px' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
	//添加试题
	function toInsertTest(id){
		var url = jsBasePath + "/lstBasePaper/toInsertTest.html?"+"id="+id;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "录入试题", //
			offset : [ '4%' ],
			area : [ '90%', '90%' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
	//发布试卷
	function introduced(id){
		layer.confirm("确定要发布该试卷吗?发布后不可修改试卷!", {
			  btn: ['是','否'] ,//按钮
			  offset: '10%',
			  btnAlign:'c'
			}, function(index){
				$.post(jsBasePath+"/lstBasePaper/introduced.html",{id:id},function(data,status){
					layer.close(index);
					if(data.flag==false){
						layer.alert(data.message,{icon:2});
					}else{
						layer.alert(data.message,{icon:1},function(){
							location.reload(); 
							closeFrame();
						});
					}
				},"json");
			}, function(index){
				layer.close(index);
		});
	}
	});
	//录入试卷
	function enterPaper(){
		var url = jsBasePath + "/lstBasePaper/toAdd.html"
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "录入试题", //
			offset : [ '4%' ],
			area : [ '600px', '500px' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
</script>
</html>