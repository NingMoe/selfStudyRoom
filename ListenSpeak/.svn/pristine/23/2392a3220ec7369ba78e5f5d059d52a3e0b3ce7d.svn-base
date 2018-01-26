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
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline">
				<label class="layui-form-label">角色名:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id=roleName 
						autocomplete="off" class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<button id="searchBtn" type="button" class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
		</div>
		</fieldset>
		
		
		<div class="y-role">
			<div class="layui-btn-group" style="margin-top: 20px;">
				<button class="layui-btn" onClick="add()">新增角色</button>
				<button class="layui-btn" style="margin-left: 15px;" onClick="bath_roleDisable(0)">批量启用</button>
				<button class="layui-btn" style="margin-left: 15px;" onClick="bath_roleDisable(1)">批量禁用</button>
				<button class="layui-btn" style="margin-left: 15px;" onClick="bath_roleDisable(-1)">批量删除</button>
			</div>
			 <table class="tableList"  id="roleTable" lay-filter="role" style="width: 100%"></table>
		</div>
    </div>
    <script type="text/html" id="statusTpl">
 	{{ d.status == '0' ? '<font class=\"normal\">正常</font>' : '<font class=\"disable\">禁用</font>' }}
	</script>
    
    <script type="text/html" id="rolebar">
  	<a class="layui-btn layui-btn-xs" lay-event="pzqx">配置权限</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	{{#  if(d.status == '0'){ }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="jinyong">禁用</a>
  	{{#  } }}
	{{#  if(d.status == '1'){ }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="qiyong">启用</a>
  	{{#  } }}
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
    
<script type="text/javascript" src="<%=basePath %>/static/manager/js/role_list.js"></script>
<script type="text/javascript">
layui.use(['table','laypage'], function(){
	table = layui.table,laypage = layui.laypage;
	var tableIns = table.render({
		elem: '#roleTable',
		url : jsBasePath + '/manager/role/queryRole.html',
		page: {
			first: false,
			last: false,
			curr: location.hash.replace('#!fenye=', ''),
			hash: 'fenye'
		},
		cols: [[
				{type:'checkbox',width:"3%",fixed: 'left'},
				{type:'numbers',title: '序号',width:"3%"},
		        {field:'roleName', width:"8%", title: '角色名',sort:true,align:"center"},
		        {field:'roleDesc', width:"12%", title: '角色描述',align:"center"},
		        {field:'createUserName', width:"11%", title: '创建人',align:"center"},
		        {field:'createTime', title: '创建时间', width: '12%',align:"center"},
		        {field:'updateUserName',width:"11%", title: '修改人',align:"center"},
		        {field:'updateTime',width:"12%", title: '修改时间',align:"center",sort:true},
		        {field:'status',width:"8%", title: '状态', align:'center',templet:"#statusTpl"},
		        {width:"20%", title: '操作',align:"center", toolbar: '#rolebar'}
		]]
	});
	
	table.on('tool(role)', function(obj){
		var data = obj.data;
		if(obj.event === 'pzqx'){
			configRoleResource(data.id);
		}
		
		if(obj.event === 'edit'){
			edit(data.id);
		}
		
		if(obj.event === 'jinyong'){
			roleDisable(data.id,'1');
		}
		
		if(obj.event === 'qiyong'){
			roleDisable(data.id,'0');
		}
		
		
		if(obj.event === 'del'){
			roleDisable(data.id,'-1');
		}
	});
    				  
	$('#searchBtn').bind('click', function(){
		reloadTable();
	});
	
	function reloadTable(){
		var roleName = $("#roleName");
		tableIns.reload({
			where: {
				roleName: roleName.val()
			}
		});
	}
});
</script>
</body>
</html>