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
	<style type="text/css">
	.layui-form-label {
	width: 110px !important;
}
	</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">考试列表&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
	  <div class="layui-form-item" hidden="true">			
		<input type="text" id="search" hidden="true"  class="layui-input" value="search">			
	</div>	
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%;">班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="stclassNo"  autocomplete="off" placeholder="请输入班号" class="layui-input">
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" >考试日期:</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="exam_start">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="exam_end">
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
             <div id="toolbar">
             <sec:authorize ifAnyGranted='ROLE_BM_BJ'>
            	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增数据
			   </button>
			   <button onClick="return bath_del()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
				</button>
				<button onClick="return bath_add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量新增
			   </button>
			</sec:authorize>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
<sec:authorize ifNotGranted="ROLE_BM_BJ">
<script type="text/javascript" src="<%=basePath %>/static/northamerica/stuexam/list_not.js"></script>
</sec:authorize>
<sec:authorize ifAnyGranted='ROLE_BM_BJ' >
<script type="text/javascript" src="<%=basePath %>/static/northamerica/stuexam/list.js"></script>
</sec:authorize>
<script type="text/javascript">
function bath_add(){
	layer.open({
		type: 2,
		title: '批量新增',
		shadeClose: false,
		shade: 0.8,
		offset : ['20%'],
		area: ['393px', '248px'],
		content: jsBasePath+'/student/exam/toAddExcel.html',
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
			
				    location.reload(); 
		}
	});
}
</script>
</html>