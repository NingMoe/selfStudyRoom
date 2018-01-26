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
	<fieldset class="layui-elem-field" style="padding: 15px;" >
		<legend>功能区&nbsp;</legend>
		<form class="layui-form"  id="subForm">
		<input type="hidden" id="empId" name="empId" value="${users.empId }">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="white-space: nowrap;">快速新增:</label>
					<div class="layui-input-inline" >
							<input class="layui-input"  lay-verify="email|isTeach"  name="emailAddr" placeholder="输入导师邮箱地址" id="emailAddr">
					</div>
					</div>
			<div class="layui-inline">
				<button type="button"
				class="layui-btn" lay-submit="" lay-filter="demo1"><li class="fa fa-check-square-o"></li>
				&nbsp;提交
			</button>
			</div>
		</div>
		</form>
		</fieldset>
		<div class="y-role" style="margin-top: 10px;">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/js/employee/emp_cfig_teach.js"></script>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/basic/employee/queryTeachList.html', //请求后台的URL（*）
    			//method: 'get',      //请求方式（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			sidePagination : "server",
    			striped : true, //是否显示行间隔色
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			sortable : false, //是否启用排序
    			//sortOrder : "asc", //排序方式
    			dataField:'teachList',
    			queryParams : {empId:$("#empId").val()}, //传递参数（*）
    			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    			strictSearch : false,
    			showRefresh : false, //是否显示刷新按钮
    			minimumCountColumns : 2, //最少允许的列数
    			clickToSelect : false, //是否启用点击选中行
    			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "teachId", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			columns : [{
    				field : 'teachId',
    				title : '导师工号',
    				align : 'center',
    				width:'100px'
    			}, {
    				field : 'teachName',
    				title : '姓名',
    				switchable : false,
    				align : 'center',
    				width:'150px'
    			},{
    				field : 'emailAddr',
    				title : '邮箱',
    				align : 'center' ,
    				width:'250px',
    				formatter : function(value, row, index) {
    						if(value!=''){
    							return "<a href='Mailto:"+value+"@xdf.cn'>"+value+"@xdf.cn</a>";
    						}
    				}
    			}, {
    				field : 'comName',
    				title : '机构',
    				align : 'center',
    				width:'250px'
    			}, {
    				field : 'deptName',
    				title : '部门',
    				align : 'center',
    				width:'250px'
    			}, {
    				field : '',
    				align : 'center',
    				title : '解除',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini layui-btn-danger' onclick='return delSuper(\"" + row.teachId + "\");'><i class='fa fa-times'></i>&nbsp;删除</button >&nbsp;";
    					return op;
    				}
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	
    </script>
</body>
</html>