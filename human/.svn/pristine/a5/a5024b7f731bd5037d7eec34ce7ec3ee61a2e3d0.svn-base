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
				<label class="layui-form-label" style="width: 86px;">图书名称 </label>
				<div class="layui-input-inline" style="width: 92px;">
					<input type="text" name="name" id="book_name"
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
			    <label class="layui-form-label">状态</label>
			    <div class="layui-form layui-input-block">
			      <select id="valid" name="interest" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="0">已禁用</option>
			        <option value="1">已启用</option>
			      </select>
			    </div>
			</div>
			  
			 <div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 114px;">最小登记时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="left_create_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">最大登记时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="right_create_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			  
			  <div class="layui-inline" style="margin: 0 auto 10px auto">
					<button id="sr" type="button"	class="layui-btn">
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
				&nbsp;新增图书
				</button>
				<button onClick="updateexcel()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入
				</button>
				<button onClick="select_load()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;导出选中
				</button>
				<button onClick="update_valid(1)" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量启用
				</button>
				<button onClick="update_valid(0)" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量禁用
				</button>
            </div>
        </div>
    </div>
    <!-- layui.use -->
    <script type="text/javascript" src="<%=basePath %>/static/teacherservice/book/js/book.js"></script>
    <script type="text/javascript">
    	function initTable(form) {
    		//初始化Table 不 
    		$('#processDefTable').bootstrapTable('destroy');
    		$("#processDefTable").bootstrapTable({
    			url : jsBasePath + '/teacher/library/query.html', //请求后台的URL（*）
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
    			columns : [ {
    				checkbox : true,
    				fieId : 'id'
    			},{
    				field : 'book_name',
    				title : '书籍名称',
    				align : 'center'
    			},{
    				field : 'book_author',
    				title : '作者',
    				align : 'center',
    			},{
    				field : 'book_publishing',
    				title : '出版社',
    				align : 'center',
    			},{
    				field : 'book_info',
    				title : '书籍简介',
    				align : 'center'
    			},{
    				field : 'book_cover',
    				title : '封面图片',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null){
    						return "<img style='height : 25px; width : 25px;' src='http://hrms-img.oss-cn-shanghai.aliyuncs.com/"+value+"'/>";
    					}
    	    	    	return "";
    	    	    }
    			},{
    				field : 'book_count',
    				title : '书籍数量',
    				align : 'center'
    			},{
    				field : 'book_price',
    				title : '书籍单价',
    				align : 'center'
    			},{
    				field : 'book_type_name',
    				title : '书籍分类',
    				align : 'center'
    			},{
    				field : 'book_address',
    				title : '摆放位置',
    				align : 'center'
    			},{
    				field : 'book_school',
    				title : '归属机构',
    				align : 'center'
    			},{
    				field : 'create_time',
    				title : '登记时间',
    				align : 'center',
    			},{
    				field : 'valid',
    				title : '是否启用',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null && value=="0"){
    						return "<div class=\"layui-form-item\" pane=\"\">"+
  					      "<div class=\"layui-form\"><input name=\"open\" lay-skin=\"switch\" lay-filter=\"switchTest\" type=\"checkbox\" bookid='"+row.id+"'></div></div>";
    					}
    					if(value != null && value=="1"){
    						return "<div class=\"layui-form-item\" pane=\"\">"+
    					      "<div class=\"layui-form\"><input checked='' name=\"open\" lay-skin=\"switch\" lay-filter=\"switchTest\" type=\"checkbox\" bookid='"+row.id+"'></div></div>";
    					}
    	    	    	return "";
    	    	    } 
    			},{
    				field : 'act_status',
    				title : '操作',
    				align : 'center' ,
    				formatter: function (value, row, index) {
    					return "<button  class='layui-btn layui-btn-mini' onclick='return change(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;修改</button >&nbsp;"+
    					"<button  class='layui-btn layui-btn-mini' onclick='return deletebook(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;删除</button >&nbsp;";
    	    	    } 
    			}],
    			onLoadSuccess : function(dataAll) {
    				form.render();
    			},
    			onLoadError : function() {
    			}
    		});
    	};
    	
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			book_name : $.trim($("#book_name").val()),
    			book_type : $.trim($("#book_type").val()),
    			valid : $.trim($("#valid").val()),
    			left_create_time : $.trim($("#left_create_time").val()),
    			right_create_time : $.trim($("#right_create_time").val()),
    		};
    	}
    </script>
</body>
</html>