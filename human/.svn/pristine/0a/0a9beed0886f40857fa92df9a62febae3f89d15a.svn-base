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
	<div class="main-wrap layui-form">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">活动名称</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="name" id="act_name"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">活动开始时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="act_start_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">活动结束时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="act_end_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			<br/>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">报班开始时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="class_start_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">报班结束时间</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input id="class_end_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off"
					 class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
			    <label class="layui-form-label" style="width: 120px;">状态</label>
			    <div class="layui-input-inline" style="width: 150px;">
			      <select id="act_status" name="interest" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="0">失效</option>
			        <option value="1">待审核</option>
			        <option value="2">不通过</option>
			        <option value="3">已经发布</option>
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
				&nbsp;新增活动
				</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#processDefTable').bootstrapTable('destroy');
    		$("#processDefTable").bootstrapTable({
    			url : jsBasePath + '/teacher/activity/query.html', //请求后台的URL（*）
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
    			//height: 170,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			toolbar : '#toolbar', //工具按钮用哪个容器
    			toolbarAlign : 'left',
    			columns : [ {
    				field : 'id',
    				title : 'ID',
    				align : 'center'
    			},{
    				field : 'act_name',
    				title : '活动名称',
    				align : 'center'
    			},{
    				field : 'act_img',
    				title : '活动图片',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null){
    						return "<img style='height : 25px; width : 25px;' src='http://hrms-img.oss-cn-shanghai.aliyuncs.com/"+value+"'/>";
    					}
    	    	    	return "";
    	    	    }
    			},{
    				field : 'act_info',
    				title : '活动详细',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null && value.length > 10){
    						return "<span title='"+value+"'>"+value.substring(0,5)+"……<span>";
    					}
    	    	    	return value;
    	    	    }
    			},{
    				field : 'act_start_time',
    				title : '活动开始时间',
    				align : 'center'
    			},{
    				field : 'act_end_time',
    				title : '活动结束时间',
    				align : 'center'
    			},{
    				field : 'class_start_time',
    				title : '报班开始时间',
    				align : 'center'
    			},{
    				field : 'class_end_time',
    				title : '报班结束时间',
    				align : 'center'
    			},{
    				field : 'act_publish_time',
    				title : '开始时间',
    				align : 'center'
    			},{
    				field : 'act_url',
    				title : '活动链接',
    				align : 'center'
    			},{
    				field : 'act_school',
    				title : '归属机构',
    				align : 'center'
    			},{
    				field : 'act_department',
    				title : '归属部门',
    				align : 'center',
    			},{
    				field : 'act_status',
    				title : '发布状态',
    				align : 'center',
    				formatter: function (value, row, index) {
    					if(value != null && value=="0"){
    						return "失效";
    					}
    					
    					if(value != null && value=="1"){
    						return "待审核";
    					}
    					
    					if(value != null && value=="2"){
    						return "不通过";
    					}
    					
    					if(value != null && value=="3"){
    						return "已经发布";
    					}
    	    	    	return "";
    	    	    } 
    			},{
    				field : 'act_status',
    				title : '操作',
    				align : 'center' ,
    				formatter: function (value, row, index) {
    					if(value != null && value=="0"){
    						return "";
    					}
    					
    					if(value != null && value=="1"){
    						return "<sec:authorize ifAnyGranted='ROLE_act_isagree'><button  class='layui-btn layui-btn-mini' onclick='return isAgree(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;通过</button >&nbsp;<button  class='layui-btn layui-btn-mini layui-btn-danger' onclick='return isBack(\""+row.id+"\");'><i class='fa fa-times'></i>&nbsp;不通过</button >&nbsp;</sec:authorize>"+
    								"<sec:authorize ifNotGranted='ROLE_act_isagree'><button  class='layui-btn layui-btn-mini' onclick='return change(\""+row.id+"\");'><i class='fa fa-check'></i>&nbsp;修改</button >&nbsp;</sec:authorize>";
    					}
    					
    					if(value != null && value=="2"){
    						return "";
    					}
    	    	    } 
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			act_name : $.trim($("#act_name").val()),
    			act_start_time : $.trim($("#act_start_time").val()),
    			act_end_time : $.trim($("#act_end_time").val()),
    			class_start_time : $.trim($("#class_start_time").val()),
    			class_end_time : $.trim($("#class_end_time").val()),
    			act_status : $.trim($("#act_status").val())
    		};
    	}
    	
    	function add(){
    		layer.open({
    		    type: 2,
    		    title: '新增活动',
    		    shadeClose: false,
    		    shade: 0.8,
    		    offset : ['20%'],
    		    area: ['400px', '300px'],
    		    content: jsBasePath+'/teacher/activity/addview.html',
    		    end:function(){
    		    	$("#processDefTable").bootstrapTable('refresh');
    		    }
    		}); 
    	}
    	
    	//修改活动信息
    	function change(id){
    		layer.open({
    		    type: 2,
    		    title: '修改活动',
    		    shadeClose: false,
    		    shade: 0.8,
    		    offset : ['20%'],
    		    area: ['400px', '300px'],
    		    content: jsBasePath+'/teacher/activity/changeview.html?id='+id,
    		    end:function(){
    		    	$("#processDefTable").bootstrapTable('refresh');
    		    }
    		}); 
    	}
    	
    	//申请不通过
    	function isBack(id){
    		$.ajax({
    			url : jsBasePath + "/teacher/activity/isagree.html",
    			type : "POST",
    			dataType : "json",
    			data : {
    				id : id,
    				act_status : 2
    			},
    			success : function(date){
    				location.reload();
    				alert(date.message);
    			},
    			error : function(date){
    				alert("网络出错，请重新发送。");
    			}
    		});
    		
    		return 1;
    	}
    	
    	//申请通过
    	function isAgree(id){
    		$.ajax({
    			url : jsBasePath + "/teacher/activity/isagree.html",
    			type : "POST",
    			dataType : "json",
    			data : {
    				id : id,
    				act_status : 3
    			},
    			success : function(date){
    				location.reload();
    				alert(date.message);
    			},
    			error : function(date){
    				alert("网络出错，请重新发送。");
    			}
    		});
    		
    		return 1;
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
			  document.getElementById('act_start_time').onclick = function(){
			       start.elem = this;
			       laydate(start);
			  }
			  document.getElementById('act_end_time').onclick = function(){
			       end.elem = this
			       laydate(end);
			  }
		});
	    
	    layui.use('laydate', function(){
	    	  var laydate = layui.laydate;
	    	  var start = {
	    	   istoday: false
	    	    , istime: true
	    	    ,format: 'YYYY-MM-DD hh:mm:ss'
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
			  document.getElementById('class_start_time').onclick = function(){
			       start.elem = this;
			       laydate(start);
			  }
			  document.getElementById('class_end_time').onclick = function(){
			       end.elem = this
			       laydate(end);
			  }
		});
    </script>
</body>
</html>