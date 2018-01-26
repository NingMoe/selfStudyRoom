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
		<form class="layui-form" >
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">机构:</label>
				<div class="layui-input-inline" >
					<select id="companyId"    lay-filter="companyId">
					<option value="">请选择</option>
					<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}">${com.companyName}</option>
					</c:forEach>
					</select>
				</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">部门:</label>
					<div class="layui-input-inline" >
								<select id="deptId" lay-search="">
									<option value="">请选择</option>
								</select>
							</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label">模版名:</label>
				<div class="layui-input-inline" >
					<input type="text" id="temName" 
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">状态:</label>
				<div class="layui-input-inline" >
					<select id="state"   >
					<option value="">请选择</option>
					<option value="0">有效</option>
					<option  value="1">禁用</option>
					</select>
				</div>
				</div>
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
			</div>
		</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            <sec:authorize ifAnyGranted='ROLE_add_mail_temp'>
            <button onClick="add();" type="button"
				class="layui-btn"><li class="fa fa-plus"></li>
				&nbsp;新增模版
			</button></sec:authorize>
			<sec:authorize ifAnyGranted='ROLE_del_mail_temp'><button  class='layui-btn layui-btn-danger' onclick='return bath_delTem();'><i class='fa fa-minus-square'></i>&nbsp;批量禁用</button ></sec:authorize>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/js/mailTemp/mailTemp_list.js"></script>
    <script type="text/javascript">
	    function initTable() {
	    	//初始化Table 不 
	    	$('#tableList').bootstrapTable('destroy');
	    	$("#tableList").bootstrapTable({
	    		url : jsBasePath + '/basic/mailTemp/queryTem.html', //请求后台的URL（*）
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
	    		columns : [{
	    			checkbox : true,
	    			fieId : 'id'
	    		}, {
	    			field : 'temCompanyName',
	    			title : '机构',
	    			align: 'center'
	    		}, {
	    			field : 'temDeptName',
	    			title : '部门',
	    			align : 'center' 
	    		}, {
	    			field : 'temName',
	    			title : '模版名',
	    			switchable : false,
	    			align : 'center' 
	    		}, {
	    			field : 'state',
	    			title : ' 状态',
	    			align : 'center',
	    			formatter : function(value, row, index) {
	    				if (value == '0') {
	    					return "<font class='normal'>正常</font>";
	    				}
	    				if (value ==  '1') {
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
	    				if (row.state == 0) {
	    				    op += "<sec:authorize ifAnyGranted='ROLE_edit_mail_temp'><button  class='layui-btn layui-btn-mini' onclick='return toEdit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp; </sec:authorize>";
	    					op += "<sec:authorize ifAnyGranted='ROLE_del_mail_temp'><button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return delTem(\"" + row.id + "\",1);'><i class='fa fa-minus-square'></i>&nbsp;禁用</button >&nbsp; </sec:authorize>";
	    				}
	    				return op;
	    			}
	    		}/*,{
	    			field : 'temDesc',
	    			title : '内容',
	    			align : 'left'
	    		}*/],
	    		onLoadSuccess : function(dataAll) {},
	    		onLoadError : function() {
	    		}
	    	});
	    }
    </script>
</body>
</html>