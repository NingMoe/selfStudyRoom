<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>教研组长列表</title>
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
			<label class="layui-form-label" style="width:10%;">姓名</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input type="text" id="name" autocomplete="off" class="layui-input">
			</div>
			<label class="layui-form-label" style="width:10%;">教研组</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="jyz" id="jyz">
					<option value="">请选择</option>
   					<c:forEach items="${jyzs }" var="jyz">
   						<option value="${jyz.name }">${jyz.name }</option>
   					</c:forEach>
     			</select>
			</div>
			
			<button onClick="initTable()" type="button"
						class="layui-btn"><li class="fa fa-search"></li>
					   &nbsp;搜索
				</button>
		</div>		
		</div>
		</form>
	</fieldset>
		
		<div class="y-role">
            <table class="tableList" id="jyzTable">
            </table>
            <div id="toolbar">
            	<button type="button" id="add" class="layui-btn"><li class="fa fa-add"></li>&nbsp;添加数据</button>
            </div>
        </div>
    </div>
    <script src="<%=basePath %>/static/jw/jyzList.js" type="text/javascript"></script>
</body>
</html>