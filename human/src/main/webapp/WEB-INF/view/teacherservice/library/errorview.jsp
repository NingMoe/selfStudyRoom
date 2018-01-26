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
		<div class="layui-form">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
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
			      <select id="book_type" name="interest" lay-filter="aihao" lay-search="">
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
			    <div class="layui-input-block">
			      <select id="valid" name="interest" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="1">已解决</option>
			        <option value="0">未解决</option>
			      </select>
			    </div>
			  </div>
			  
			  <div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">报错时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="left_report_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">报错时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="right_report_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
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
            	<button onClick="selecttrue();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;解决报错
				</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#processDefTable').bootstrapTable('destroy');
    		$("#processDefTable").bootstrapTable({
    			url : jsBasePath + '/teacher/libraryerror/query.html', //请求后台的URL（*）
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
    				field : 'type_name',
    				title : '分类名称',
    				align : 'center'
    			},{
    				field : 'book_name',
    				title : '书籍名称',
    				align : 'center',
    			},{
    				field : 'report_name',
    				title : '报错人名称',
    				align : 'center',
    			},{
    				field : 'report_time',
    				title : '报错时间',
    				align : 'center'
    			},{
    				field : 'valid',
    				title : '状态',
    				align : 'center' ,
    				formatter: function (value, row, index) {
    					if(value != null && value=="0"){
    						return "未解决";
    					}
    					
    					if(value != null && value=="1"){
    						return "已解决";
    					}
    					
    	    	    } 
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {}
    		});
    	};
    	
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			book_name : $.trim($("#book_name").val()),
    			book_type : $.trim($("#book_type").val()),
    			valid : $.trim($("#valid").val()),
    			left_report_time : $.trim($("#left_report_time").val()),
    			right_report_time : $.trim($("#right_report_time").val())
    		};
    	}
    	
    	function selecttrue(){
    		var ids = getSelectId("processDefTable");
    		if(ids == null || ids == ''){
    			layer.msg("请至少选择一条记录");
    			return false;
    		}
    		$.ajax({
    			url : jsBasePath + "/teacher/libraryerror/selecttrue.html",
    			type : "POST",
    			dataType : "json",
    			data : {
    				ids : ids
    			},
    			success : function(data){
    				layer.msg(data.message);
    				if(data.flag){
    					initTable();
    				}
    			},
    			error : function(date){
    				alert("网络出错，请重新发送。");
    			}
    		});
    	}
    	
	   
    	$(function() {
    		initTable();
    	});
    </script>
    
    <!-- layui.use -->
    <script type="text/javascript">
    	layui.use(['form', 'laydate'], function(){
	      var form = layui.form()
	      ,layer = layui.layer
	      ,laydate = layui.laydate;
	    });
    	
	    layui.use('laydate', function(){
	    	  var laydate = layui.laydate;
	    	  var start = {
	    	      istoday: false
	    	    , istime: true
	    	    , format: 'YYYY-MM-DD hh:mm:ss'
	    	    ,choose: function(datas){
	    	      end.min = datas; //开始日选好后，重置结束日的最小日期
	    	      end.start = datas //将结束日的初始值设定为开始日
	    	    }
	    	  };
	    	  var end = {
	    	      istoday: false
	    	    , istime: true
	    	    , format: 'YYYY-MM-DD hh:mm:ss'
	    	    ,choose: function(datas){
	    	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    	    }
	    	  };
			  document.getElementById('left_report_time').onclick = function(){
			       start.elem = this;
			       laydate(start);
			  }
			  document.getElementById('right_report_time').onclick = function(){
			       end.elem = this
			       laydate(end);
			  }
		});
    </script>
</body>
</html>