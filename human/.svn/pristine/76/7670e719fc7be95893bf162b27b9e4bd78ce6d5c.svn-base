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
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
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
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
		</div>
		</fieldset>
		
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="roleTable">
            </table>
            <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			</button>
			<button onClick="bath_roleDisable(0)" type="button"
				class="layui-btn"><li class="fa fa-check-square"></li>
				&nbsp;批量启用
			</button>
			<button onClick="bath_roleDisable(1)" type="button"
				class="layui-btn layui-btn-warm"><li class="fa fa-minus-square"></li>
				&nbsp;批量禁用
			</button>
			<button onClick="bath_roleDisable(-1)" type="button"
				class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量删除
			</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/manager/js/role_list.js"></script>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#roleTable').bootstrapTable('destroy');
    		$("#roleTable").bootstrapTable({
    			url : jsBasePath + '/manager/role/queryRole.html', //请求后台的URL（*）
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
    			columns : [ {
    				checkbox : true,
    				fieId : 'id'
    			}, {
    				field : 'number',
    				title : '序号',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var page = $("#roleTable").bootstrapTable("getPage");
    					return page.pageSize * (page.pageNumber - 1) + index + 1;
    				}
    			}, {
    				field : 'roleName',
    				title : '角色名',
    				align : 'center'
    			}, {
    				field : 'roleDesc',
    				title : '角色说明',
    				align : 'center' /* ,
        			formatter: function (value, row, index) {
            	    	if(value==1){
            	    		return  "准考证通知";
            	    	}
            	    	if(value==0){
            	    		return  "查分通知";
            	    	}
            	    } */
    			}, {
    				field : 'createUserName',
    				title : '创建人',
    				align : 'center'
    			}, {
    				field : 'createTime',
    				title : '创建时间',
    				align : 'center'
    			}, {
    				field : 'updateUserName',
    				title : '修改人',
    				align : 'center'
    			}, {
    				field : 'updateTime',
    				title : '修改时间',
    				align : 'center'
    			}, {
    				field : 'status',
    				title : '状态',
    				align : 'center',
    				formatter : function(value, row, index) {
    					if (value == 0) {
    						return "<font class='normal'>正常</font>";
    					}
    					if (value == 1) {
    						return "<font class='disable'>禁用</font>";
    					}
    				}
    			}, {
    				field : '',
    				align : 'center',
    				title : '操作',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return configRoleResource(\"" + row.id + "\");'><i class='fa fa-cog'></i>&nbsp;配置权限</button >&nbsp;";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
    					if (row.status == 0) {
    						op += "<button  class='layui-btn layui-btn-warm layui-btn-mini' onclick='return roleDisable(\"" + row.id + "\",1);'><i class='fa fa-minus-square'></i>&nbsp;禁用</button >&nbsp;";
    					}
    					if (row.status == 1) {
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return roleDisable(\"" + row.id + "\",0);'><i class='fa fa-check-square'></i>&nbsp;启用</button >&nbsp;";
    					}
    					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return roleDisable(\"" + row.id + "\",-1);'><i class='fa fa-remove'></i>&nbsp;删除</button >";
    					return op;
    				}
    			} ],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	}
    	;
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			roleName : $.trim($("#roleName").val())
    		};
    	}
    
    	$(function() {
    		initTable();
    	});
    </script>
</body>
</html>