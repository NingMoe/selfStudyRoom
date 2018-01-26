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
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
</head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<div class="layui-form">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 58px;">部门</label>
				<div class="layui-input-inline" style="width: 92px;">
					<input type="text" name="dept_name" id="dept_name"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
			    <label class="layui-form-label">分类</label>
			    <div class="layui-input-block">
			      <select id="book_type" name="interest" lay-filter="aihao">
			      		<option value="">全部</option>
			      <c:if test="${flag }">
			      	<c:forEach var="booktype" items="${list }">
			      		<option value="${booktype.id }">${booktype.type_name }</option>
			      	</c:forEach>
			      </c:if>
			      </select>
			    </div>
			</div>
			  
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 86px;">图书名称</label>
				<div class="layui-input-inline" style="width: 92px;">
					<input type="text" name="book_name" id="book_name"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
			    <label class="layui-form-label" style="width: 86px;">是否归还</label>
			    <div class="layui-input-block">
			      <select id="is_return" name="is_return" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="0">未还</option>
			        <option value="1">已还</option>
			      </select>
			    </div>
			</div>
					
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 142px;">超过多少天未还的</label>
				<div class="layui-input-inline" style="width: 64px;">
					<input type="number" name="out_borrow_time" id="out_borrow_time"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 114px;">最小借书时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="left_borrow_time" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" type="text">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 114px;">最大借书时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="right_borrow_time" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" type="text">
				</div>
			</div>	
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 114px;">最小还书时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="left_return_time" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" type="text">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 114px;">最大还书时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="right_return_time" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" type="text">
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
            </div>
        </div>
    </div>
    <!-- layui.use -->
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/FileSaver.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/teacherservice/book_borrow_list/js/book_borrow_list.js"></script>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#processDefTable').bootstrapTable('destroy');
    		$("#processDefTable").bootstrapTable({
    			url : jsBasePath + '/teacher/libraryborrow/query.html', //请求后台的URL（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			striped : true, //是否显示行间隔色
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			pagination : true, //是否显示分页（*）
    			sortable : false, //是否启用排序
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
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			toolbar : '#toolbar', //工具按钮用哪个容器
    			toolbarAlign : 'left',
    			showExport: true,
    			showType : "all",
    			fileName : "图书借阅表",
    			columns : [ {
    				checkbox : true,
    				fieId : 'borrow_id',
    				class : "noexport"
    			},{
    				field : 'borrow_schoolid',
    				title : 'school_id',
    				align : 'center'
    			}, {
    				field : 'company_name',
    				title : '学校名称',
    				align : 'center' 
    			}, {
    				field : 'dept_name',
    				title : '部门',
    				align : 'center' 
    			}, {
    				field : 'email_addr',
    				title : '邮箱前缀',
    				align : 'center' 
    			}, {
    				field : 'name',
    				title : '用户姓名',
    				align : 'center'
    			}, {
    				field : 'book_name',
    				title : '书籍名称',
    				align : 'center'
    			}, {
    				field : 'type_name',
    				title : ' 分类名称',
    				align : 'center'
    			}, {
    				field : 'borrow_time',
    				align : 'center',
    				title : '借出时间'
    			} ,{
    				field : 'return_time',
    				align : 'center',
    				title : '归还时间'
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {}
    		});
    	};
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			dept_name : $.trim($("#dept_name").val()),
    			book_name : $.trim($("#book_name").val()),
    			book_type : $.trim($("#book_type").val()),
    			is_return : $.trim($("#is_return").val()),
    			out_borrow_time : $.trim($("#out_borrow_time").val()),
    			left_borrow_time : $.trim($("#left_borrow_time").val()),
    			right_borrow_time : $.trim($("#right_borrow_time").val()),
    			left_return_time : $.trim($("#left_return_time").val()),
    			right_return_time : $.trim($("#right_return_time").val()),
    		};
    	}
    	
    	$(function() {
    		initTable();
    	});
    </script>
</body>
</html>