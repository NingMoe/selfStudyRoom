<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form">
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 86px;">分类名称</label>
				<div class="layui-input-inline" style="width: 92px;">
					<input type="text" name="type_name" id="type_name"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
			    <label class="layui-form-label" style="width: 90px;">是否禁用</label>
			    <div class="layui-input-block">
			      <select id="valid" name="valid" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="0">禁用</option>
			        <option value="1">启用</option>
			      </select>
			    </div>
			</div>

			  <div class="layui-inline" style="margin: 0 auto 10px auto">
					<button onClick="initTable()" type="button"	class="layui-btn">
					<li class="fa fa-search"></li>
					&nbsp;搜索
			  </button>
			  </div>
		</div>
		</div>
		</fieldset>
		
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="processDefTable">
            </table>
            <div id="toolbar">
            	<button onClick="add()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;新建分类
				</button>
				<button onClick="selectijy()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量禁用
				</button>
				<button onClick="selectiqy()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量启用
				</button>
            </div>
        </div>
    </div>
    <!-- layui.use -->
    <script type="text/javascript" src="<%=basePath %>/static/teacherservice/book_type/js/book_type.js"></script>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#processDefTable').bootstrapTable('destroy');
    		$("#processDefTable").bootstrapTable({
    			url : jsBasePath + '/teacher/librarytype/query.html', //请求后台的URL（*）
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
    			},{
    				field : 'type_name',
    				title : '分类名称',
    				align : 'center'
    			}, {
    				field : 'type_cover_url',
    				title : '分类图片',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null){
    						return "<img style='height : 25px; width : 25px;' src='http://hrms-img.oss-cn-shanghai.aliyuncs.com/"+value+"'/>";
    					}
    	    	    	return "";
    	    	    } 
    			}, {
    				field : 'weight',
    				title : '权重',
    				align : 'center' 
    			}, {
    				field : 'valid',
    				title : '是否启用',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value == 0){
    						return "已禁用";
    					}
    					if(value == 1){
    						return "已启用";
    					}
    	    	    	return "";
    	    	    }  
    			} , {
    				field : '',
    				title : '操作',
    				align : 'center',
    				formatter: function (value, row, index) {
    	    	    	return "<button  class='layui-btn layui-btn-mini' onclick='return change(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;修改</button >&nbsp;"+
    					"<button  class='layui-btn layui-btn-mini' onclick='return deletebooktype(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;删除</button >&nbsp;";
    	    	    }  
    			}],
    			onLoadSuccess : function(dataAll) {
    			},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			type_name : $.trim($("#type_name").val()),
    			valid : $.trim($("#valid").val())
    		};
    	}
    	
    	$(function() {
    		initTable();
    	});
    </script>
</body>
</html>