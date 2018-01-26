<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline">
				<label class="layui-form-label">手机号:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id="phone" 
						autocomplete="off" class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id="name" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<button id="searchBtn" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
			
		</div>
		</fieldset>
		
		<div class="y-role">
            <!--工具栏-->
            <div id="toolbar">
            	<button onClick="userAdd()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			</button>
			<button onClick="bath_userDisable(1)" type="button"
				class="layui-btn"><li class="fa fa-check-square"></li>
				&nbsp;批量启用
			</button>
			<button onClick="bath_userDisable(2)" type="button"
				class="layui-btn layui-btn-warm"><li class="fa fa-minus-square"></li>
				&nbsp;批量禁用
			</button>
            </div>
            <!--/工具栏-->
            <table class="layui-table"  id="userTable" lay-filter="user_filter" style="width: 100%"></table>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/manager/js/user_list.js"></script>
    <script type="text/html" id="diffTpl">
 		{{ d.status == '1' ? '正常' : '禁用' }}
	</script>
	<script type="text/html" id="userbar">
  		<a class="layui-btn layui-btn-xs" lay-event="configuration_roles">配置角色</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		{{# if(d.status == '1'){  }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disable">禁用</a>
		{{# }else if(d.status == '2'){ }}
			<a class="layui-btn layui-btn-xs" lay-event="enable">启用</a>
		{{# } }}
	</script>
	<script type="text/javascript">
		layui.use(['table','laypage'], function(){
			var table = layui.table,laypage = layui.laypage;
			var tableIns = table.render({
				elem: '#userTable',
				url: jsBasePath + '/manager/user/queryUser.html',
				page: {
					first: false,
					last: false,
					curr: location.hash.replace('#!fenye=', ''),
					hash: 'fenye'
				},
				cols: [[
				        {field:'name', title: '姓名', width:"10%", align:"center", fixed: 'left'},
				        {field:'phone', title: '手机号', width:"10%", align:"center", fixed: 'left'},
				        {field:'schoolName', title: '学校', width:"10%", align:"center"},
				        {field:'subjectName', title: '科目', width: '10%', minWidth: '100', align:"center"},
				        {field:'gradeName', title: '年级', width:"10%", minWidth: '100', align:"center"},
				        {field:'createTime', title: '创建时间', width:"10%", align:"center"},
				        {field:'createUser', title: '创建人', width:"10%", align:"center"},
				        {field:'status', title: '状态', width:"10%", align:"center", templet:"#diffTpl"},
				        {field:'', title: '操作', width:"20%", align:'center', fixed: 'right', toolbar: '#userbar'}
				]]
			});
			
			table.on('tool(user_filter)', function(obj){
				var data = obj.data;
				if(obj.event === 'disable'){
					layer.confirm('确认禁用？', function(index){
						userDisable(data.id ,2);
					});
				}
				if(obj.event === 'enable'){
					layer.confirm('确认启用？', function(index){
						userDisable(data.id,1);
					});
				}
				
				if(obj.event === 'configuration_roles'){
					toCfgUserRole(data.id);
				}
				
				if(obj.event === 'edit'){
					userEdit(data.id);
				}
			});
					  
			$('#searchBtn').bind('click', function(){
				reloadTable();
			});
			
			function reloadTable(){
				var phone = $.trim($("#phone").val());
				var name = $.trim($("#name").val());
				tableIns.reload({
					where: {
						phone : phone,
		    			name : name
					}
				});
			}
		});
	</script>	
</body>
</html>