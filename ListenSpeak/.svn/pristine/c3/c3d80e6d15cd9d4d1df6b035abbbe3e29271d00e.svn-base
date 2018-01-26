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
</style>
</head>
<body>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				学生列表&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" id="collapseOne">
				<input type='hidden' id="classCode" name="classCode" value="${sc.classCode}"/>
				<label class="layui-form-label" style="width: 12%;">姓名</label>
				<div class="layui-input-inline" style="width: 15%;">
					<input type="text" id="name" autocomplete="off"
						placeholder="请输入学生姓名" class="layui-input" >
					</div>
				<label class="layui-form-label" style="width: 12%;">审核状态</label>
				<div class="layui-input-inline" style="width: 15%;">
					<select name="status" id="status">
						<option value="2">审核通过</option>
						<option value="1">未审核</option>
						<option value="3">审核不通过</option>
						<option value="">请选择</option>
					</select>
				</div>
				<label class="layui-form-label" style="width: 12%;">手机号</label>
				<div class="layui-input-inline" style="width: 15%;">
					<input type="text" id="phone" autocomplete="off"
						placeholder="请输学生手机号" class="layui-input"  >
					</div>
				<button id="searchBtn" type="button" style="width: 8%;"
				class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
				</div>
			</form>
		</fieldset>
		<div class="y-role">
			<div id="toolbar">
			<!--文字列表-->
<!-- 				<button onClick="return bath_del()" type="button" class="layui-btn">  -->
<!-- 							<li class="fa fa-plus-square"></li> &nbsp;批量删除 -->
<!-- 				</button> -->
			</div>
			<table class="tableList" id="ccrTable" lay-filter="student" >
			
			</table>
		</div>
		<script type="text/html" id="studentBar">
  			{{#  if(d.status == '1'){ }}
			<a class="layui-btn layui-btn-xs" lay-event="verify">审核</a>
 			 {{#  } }}
			<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
</script>
	</div>
</body>
<script type="text/javascript">
		layui.use(['table','laypage'], function() {
			var table = layui.table,laypage = layui.laypage,layer = layui.layer;
			var status= $("#status").val();
			 var classCode = $("#classCode").val();
			var tableIns =table.render({
				elem: '#ccrTable',
				url: jsBasePath + '/studentclass/query.html',
				where:{"status":status,"classCode":classCode},
				cellMinWidth: 80,
				page: { //详细参数可参考 laypage 组件文档
					layout: ['count', 'prev', 'page', 'next', 'skip','limit'],
					groups:3, //只显示 1 个连续页码
				    first: false, //不显示首页
				    last: false //不显示尾页
				},
				cols: [[{field:'name', width:"30%", title: '姓名',align:"center"},
// 				    {field:'xdfNumber', width:"25%", title: '学员ID',align:"center"},
				    {field:'phone', width:"30%", title: '手机号',align:"center"},
				    {fixed: 'right', width:"40%", title: '操作', align:'center', toolbar: '#studentBar'}]]
			});
			table.on('tool(student)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'verify'){
			    	verify(data.id)
			    } else if(obj.event === 'del'){
			    	del(data.id);
			    } 
			 });
			
			 $('#searchBtn').bind('click', function(){
				  reloadTable();
			  });
			 
			 function reloadTable(){
				  var classCode = $("#classCode");
				  var phone = $("#phone");
				  var name = $("#name");
				  var status = $("#status");
				  tableIns.reload({
					where: {
						classCode : classCode.val(),
						phone :  phone.val(),
						name :  name.val(),
						status : status.val(),
					}
				  });
			  }
		});
		 
// 		新增
		function add() {
			var url = jsBasePath + "/lstclass/toAdd.html";
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '80%', '80%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}
// 		编辑
		function edit(id) {
			var url = jsBasePath + "/lstclass/toEdit.html?id="+id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '80%', '80%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}
		
		function goStudentInfo(id){
			var url = jsBasePath + "/lstclass/toStudentInfo.html?id="+id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '80%', '80%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}
		//审核
		function verify(id){
			var url = jsBasePath + "/studentclass/toVerify.html?id="+id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '30%', '50%' ],
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
					$.post(jsBasePath+"/studentclass/delete.html",{ids:id},function(data,status){
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
			del(ids);
		}
	</script>
</html>